package com.mtl.pro.easy_work.controller;

import com.mtl.pro.easy_work.common.RespRes;
import com.mtl.pro.easy_work.entity.Task;
import com.mtl.pro.easy_work.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * TODO ..
 *
 * @auther liegeheijie@gmail.com
 * @date 2019/5/15 8:29
 */

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping()
    RespRes get(){
        return taskService.getAllTask();
    }

    @PostMapping()
    RespRes save(@RequestBody Task task){
        return taskService.save(task);
    }

    @PutMapping("/{id}")
    RespRes update(@PathVariable Integer id, Integer done){
        return taskService.update(id, done);
    }

    @DeleteMapping("/{id}")
    RespRes delete(@PathVariable Integer id){
        return taskService.delete(id);
    }

}
