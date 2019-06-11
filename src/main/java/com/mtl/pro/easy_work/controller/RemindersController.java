package com.mtl.pro.easy_work.controller;

import com.mtl.pro.easy_work.common.RespRes;
import com.mtl.pro.easy_work.entity.Reminder;
import com.mtl.pro.easy_work.entity.TimeReminders;
import com.mtl.pro.easy_work.service.RemindersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reminder")
public class RemindersController {

    @Autowired
    RemindersService remindersService;

    @GetMapping
    RespRes getAll(){
        return remindersService.getAll();
    }

    @DeleteMapping("/{reId}")
    RespRes delete(@PathVariable Integer reId){
        int i = remindersService.deleteByIds(reId);
        return RespRes.ok();
    }

    @PostMapping
    RespRes push(@RequestBody Reminder reminder){
        return remindersService.addReminder(reminder);
    }

    @PostMapping("/time")
    RespRes pushTime(@RequestBody TimeReminders timeReminders){
        return remindersService.addTimeReminder(timeReminders);
    }

    @DeleteMapping("/time/{id}")
    RespRes deleteTimeRe(@PathVariable Integer id){
        return remindersService.deleteTimeRe(id);
    }
}
