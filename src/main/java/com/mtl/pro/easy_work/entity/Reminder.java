package com.mtl.pro.easy_work.entity;

import java.util.Date;

public class Reminder {
    private Integer id;

    private Integer trId;

    private String reminder;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTrId() {
        return trId;
    }

    public void setTrId(Integer trId) {
        this.trId = trId;
    }

    public String getReminder() {
        return reminder;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder == null ? null : reminder.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}