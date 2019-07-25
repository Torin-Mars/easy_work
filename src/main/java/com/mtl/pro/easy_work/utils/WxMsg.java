package com.mtl.pro.easy_work.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by MTL on 2019/6/18
 */
public class WxMsg {

    private static Logger logger = LoggerFactory.getLogger(WxMsg.class);

    private static Socket s;
    private static OutputStream os;

    public static boolean initConnect() throws IOException {

        try {
            s = new Socket("127.0.0.1", 8801);
            //2.通过Socket获取流：
            os = s.getOutputStream();

            logger.info("初始化微信接口连接成功");
            os.write(("老大" + "#i#" + "重新连接成功").getBytes("utf-8"));
            os.flush();
            return true;
        }catch (Exception e1){
            logger.error("无法连接服务器");
            return false;
        }
    }

    public static void send(String username, String msg) throws IOException {
        try {
            os.write((username + "#i#" + msg).getBytes("utf-8"));
            logger.info(username + "#i#" + msg);
            os.flush();
        }catch (Exception e){
            if (initConnect()){
                os.write((username + "#i#" + msg).getBytes("utf-8"));
                logger.info(username + "#i#" + msg);
                os.flush();
            }
        }
    }

    public static void stop(){
        try {
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
