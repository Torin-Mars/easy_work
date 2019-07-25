package com.mtl.pro.easy_work.service.impl;

import com.mtl.pro.easy_work.common.RespRes;
import com.mtl.pro.easy_work.common.StaticFileds;
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
        Integer integer = reminderMapper.selectMaxId();
        reminder.setId((integer == null ? 0 : integer) + 1);
        reminderMapper.insert(reminder);
        return RespRes.ok(reminder);
    }

    @Override
    public RespRes addTimeReminder(TimeReminders time) {
        Integer integer = timeRemindersMapper.selectMaxId();
        time.setId((integer == null ? 0 : integer) + 1);
        timeRemindersMapper.insert(time);
        //添加到提醒序列中
        StaticFileds.timesAdd(time.getTime());
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

    @Override
    public List<TimeReminders> selectTimes() {
        return timeRemindersMapper.selectByExample(null);
    }

    @Override
    public List<String> selectByTime(String time) {
        return timeRemindersMapper.selectReminderByTime(time);
    }
}
