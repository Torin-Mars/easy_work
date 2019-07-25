package com.mtl.pro.easy_work.listener;

import com.mtl.pro.easy_work.common.StaticFileds;
import com.mtl.pro.easy_work.entity.TimeReminders;
import com.mtl.pro.easy_work.service.RemindersService;
import com.mtl.pro.easy_work.utils.StringUtil;
import com.mtl.pro.easy_work.utils.WxMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by MTL on 2019/6/18
 */
@Component
public class StartReminderThreadListener implements InitializingBean {

    @Autowired
    private RemindersService remindersService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void afterPropertiesSet() throws Exception {
        new Thread(new Runnable() {
            private int curDate;

            @Override
            public void run() {
                try {
                    //初始化连接
                    WxMsg.initConnect();

                    //初始化时间列表
                    initTimes();
                    Calendar instance = Calendar.getInstance();
                    instance.setTime(new Date());
                    curDate = instance.get(Calendar.DAY_OF_MONTH);

                    while (true) {
                        Date date = new Date();
                        instance.setTime(date);
                        if (curDate != instance.get(Calendar.DAY_OF_MONTH)){
                            initTimes();
                            curDate = instance.get(Calendar.DAY_OF_MONTH);
                        }

                        String cur = StringUtil.to2Number(instance.get(Calendar.HOUR_OF_DAY))
                                + ":" + StringUtil.to2Number(instance.get(Calendar.MINUTE));
                        if (StaticFileds.getTimes().contains(cur)) {
                            // 发送消息

                            List<String> reminders = remindersService.selectByTime(cur);
                            if (reminders != null && reminders.size()!=0){
                                String msg = toMsg(reminders);
                                WxMsg.send("老大", msg);
                            }
                            //清除此处的消息
                            StaticFileds.timesRem(cur);
                        }
                        Thread.sleep(1000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error(e.getMessage());
                } finally {
                    WxMsg.stop();
                }
            }

            private String toMsg(List<String> reminders) {

                StringBuffer stringBuffer = new StringBuffer();
                for(String str : reminders){
                    stringBuffer.append("- ").append(str).append("\n");
                }
                return stringBuffer.toString();
            }

            private void initTimes() {
                List<TimeReminders> timeReminders = remindersService.selectTimes();
                for(TimeReminders tr : timeReminders){
                    String time = tr.getTime();
                    StaticFileds.timesAdd(time);
                }
            }
        }).start();
    }
}
