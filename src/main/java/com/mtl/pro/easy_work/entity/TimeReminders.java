package com.mtl.pro.easy_work.entity;

import java.util.Date;
import java.util.List;

public class TimeReminders {
    private Integer id;

    private String time;

    private Integer user;

    private Date createTime;

    private List<Reminder> res;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<Reminder> getRes() {
        return res;
    }

    public void setRes(List<Reminder> res) {
        this.res = res;
    }
}