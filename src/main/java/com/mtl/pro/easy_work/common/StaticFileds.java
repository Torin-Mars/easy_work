package com.mtl.pro.easy_work.common;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by MTL on 2019/6/18
 */
public class StaticFileds {
    private static Set<String> times;

    static {
        times = new HashSet<>();
    }

    public static synchronized void timesAdd(String time){
        times.add(time);
    }

    public static synchronized void timesRem(String add){
        times.remove(add);
    }

    public static Set<String> getTimes(){
        return times;
    }
}
