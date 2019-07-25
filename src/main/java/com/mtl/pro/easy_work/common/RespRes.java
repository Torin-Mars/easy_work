package com.mtl.pro.easy_work.common;

/**
 * TODO ..
 *
 * @auther liegeheijie@gmail.com
 * @date 2019/5/15 8:31
 */
public class RespRes {
    private int code;
    private String msg;
    private Object data;

    public RespRes(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static RespRes ok(){
        return new RespRes(1, "", null);
    }

    public static RespRes ok(String msg){
        return new RespRes(1, msg, null);
    }

    public static RespRes ok(Object data){
        return new RespRes(1, "", data);
    }

    public static RespRes ok(String msg, Object data){
        return new RespRes(1, msg, data);
    }

    public static RespRes error(){
        return new RespRes(0, "", null);
    }

    public static RespRes error(String msg){
        return new RespRes(1, msg, null);
    }

    public static RespRes error(Object data){
        return new RespRes(1, "", data);
    }

    public static RespRes error(String msg, Object data){
        return new RespRes(1, msg, data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}