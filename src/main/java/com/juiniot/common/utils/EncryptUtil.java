package com.juiniot.common.utils;

/**
 * 加密工具类
 * Created by jianyu on 2016/12/16.
 */
public class EncryptUtil {

    private static final String key = "adjfieo5659:<>";

    public static final String getEncryptAccount(String account){
        String ssid = MD5Util.MD5(key + account + key);
        return ssid;
    }

}
