package com.mtl.pro.easy_work.dao;

import com.mtl.pro.easy_work.entity.Reminder;
import com.mtl.pro.easy_work.entity.ReminderExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReminderMapper {
    long countByExample(ReminderExample example);

    int deleteByExample(ReminderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Reminder record);

    int insertSelective(Reminder record);

    List<Reminder> selectByExample(ReminderExample example);

    Reminder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Reminder record, @Param("example") ReminderExample example);

    int updateByExample(@Param("record") Reminder record, @Param("example") ReminderExample example);

    int updateByPrimaryKeySelective(Reminder record);

    int updateByPrimaryKey(Reminder record);

    List<String> selectByTrId(Integer trId);

    Integer selectMaxId();
}