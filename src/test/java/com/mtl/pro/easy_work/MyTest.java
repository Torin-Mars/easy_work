package com.mtl.pro.easy_work;

import com.mtl.pro.easy_work.utils.WxMsg;
import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by MTL on 2019/6/18
 */
public class MyTest {

    @Test
    public void testDuanLian(){
        try {
            WxMsg.initConnect();

            new Runnable() {
                @Override
                public void run() {
                    while (true){
                        try {
                            Thread.sleep(5000);
                            WxMsg.send("白盒子", "测试消息");
                            System.out.println("发消息了————————");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
            }.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void my(){
        HashSet<String> reminders = new HashSet<>();
        reminders.add("多喝水");
        reminders.add("少生病");
        reminders.add("开开心心哒");
        String msg = "小美女你好, 你醒啦, 这则消息是自动提醒的, 下面是提醒内容, 不用回复我, 回复也没有用, 老大还没开发回复的功能呢\n" +
                toMsg(reminders) + "今天天气暴雨, 可能是四川地震老天爷发脾气了, 你要注意保暖哈";
        System.out.println(msg);
    }

    private String toMsg(Collection<String> list){

        StringBuffer stringBuffer = new StringBuffer();
        for(String str : list){
            stringBuffer.append(str).append("\n");
        }
        return stringBuffer.toString();
    }

}
