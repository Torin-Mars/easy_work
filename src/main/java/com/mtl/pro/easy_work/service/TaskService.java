package com.mtl.pro.easy_work.service;

import com.mtl.pro.easy_work.common.RespRes;
import com.mtl.pro.easy_work.entity.Task;

/**
 * TODO ..
 *
 * @auther liegeheijie@gmail.com
 * @date 2019/5/15 8:35
 */
public interface TaskService {
    RespRes getAllTask();

    RespRes save(Task task);

    RespRes delete(Integer id);

    RespRes update(Integer id, Integer done);
}
