package com.mtl.pro.easy_work.dao;

import com.mtl.pro.easy_work.entity.TimeReminders;
import com.mtl.pro.easy_work.entity.TimeRemindersExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TimeRemindersMapper {
    long countByExample(TimeRemindersExample example);

    int deleteByExample(TimeRemindersExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TimeReminders record);

    int insertSelective(TimeReminders record);

    List<TimeReminders> selectByExample(TimeRemindersExample example);

    TimeReminders selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TimeReminders record, @Param("example") TimeRemindersExample example);

    int updateByExample(@Param("record") TimeReminders record, @Param("example") TimeRemindersExample example);

    int updateByPrimaryKeySelective(TimeReminders record);

    int updateByPrimaryKey(TimeReminders record);

    Integer selectMaxId();
}