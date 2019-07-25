package com.mtl.pro.easy_work.utils;

/**
 * Created by MTL on 2019/6/18
 */
public class StringUtil {

    public static String to2Number(int num){
        if (num<10){
            return "0" +num;
        }else {
            return num+"";
        }
    }
}
