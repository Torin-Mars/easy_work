package com.mtl.pro.easy_work.service.impl;

import com.mtl.pro.easy_work.common.RespRes;
import com.mtl.pro.easy_work.dao.TaskMapper;
import com.mtl.pro.easy_work.entity.Task;
import com.mtl.pro.easy_work.entity.TaskExample;
import com.mtl.pro.easy_work.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO ..
 *
 * @auther liegeheijie@gmail.com
 * @date 2019/5/15 8:36
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskMapper taskMapper;

    @Override
    public RespRes delete(Integer id) {
        int i = taskMapper.deleteByPrimaryKey(id);
        return i>0? RespRes.ok() : RespRes.error();
    }

    @Override
    public RespRes update(Integer id, Integer done) {
        TaskExample example = new TaskExample();
        TaskExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        Task task = new Task();
        task.setDone(done);
        int i = taskMapper.updateByExampleSelective(task, example);
        return i>0? RespRes.ok() : RespRes.error();
    }

    @Override
    public RespRes save(Task task) {
        int insert = taskMapper.insert(task);
        return insert>0? RespRes.ok() : RespRes.error();
    }

    @Override
    public RespRes getAllTask() {
        return RespRes.ok(taskMapper.selectByExample(null));
    }
}
