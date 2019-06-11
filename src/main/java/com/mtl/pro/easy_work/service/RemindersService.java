package com.mtl.pro.easy_work.service;

import com.mtl.pro.easy_work.common.RespRes;
import com.mtl.pro.easy_work.entity.Reminder;
import com.mtl.pro.easy_work.entity.TimeReminders;

public interface RemindersService {
    RespRes getAll();

    int deleteByIds(Integer reId);

    RespRes addReminder(Reminder reminder);

    RespRes addTimeReminder(TimeReminders time);

    RespRes deleteTimeRe(Integer id);
}
