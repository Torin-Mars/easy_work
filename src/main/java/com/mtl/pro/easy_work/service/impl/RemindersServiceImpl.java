package com.mtl.pro.easy_work.service.impl;

import com.mtl.pro.easy_work.common.RespRes;
import com.mtl.pro.easy_work.dao.ReminderMapper;
import com.mtl.pro.easy_work.dao.TimeRemindersMapper;
import com.mtl.pro.easy_work.entity.Reminder;
import com.mtl.pro.easy_work.entity.ReminderExample;
import com.mtl.pro.easy_work.entity.TimeReminders;
import com.mtl.pro.easy_work.service.RemindersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RemindersServiceImpl implements RemindersService {

    @Autowired
    TimeRemindersMapper timeRemindersMapper;

    @Autowired
    ReminderMapper reminderMapper;

    @Override
    public RespRes getAll() {
        List<TimeReminders> timeReminders = timeRemindersMapper.selectByExample(null);
        return RespRes.ok(timeReminders);
    }

    @Override
    public int deleteByIds(Integer reId) {
        return reminderMapper.deleteByPrimaryKey(reId);
    }

    @Override
    public RespRes addReminder(Reminder reminder) {
        reminder.setId(reminderMapper.selectMaxId() + 1);
        reminderMapper.insert(reminder);
        return RespRes.ok(reminder);
    }

    @Override
    public RespRes addTimeReminder(TimeReminders time) {
        time.setId(timeRemindersMapper.selectMaxId() + 1);
        timeRemindersMapper.insert(time);
        return RespRes.ok(time);
    }

    @Override
    public RespRes deleteTimeRe(Integer id) {
        ReminderExample reminderExample = new ReminderExample();
        ReminderExample.Criteria criteria = reminderExample.createCriteria();
        criteria.andTrIdEqualTo(id);
        reminderMapper.deleteByExample(reminderExample);
        timeRemindersMapper.deleteByPrimaryKey(id);
        return RespRes.ok();
    }
}
