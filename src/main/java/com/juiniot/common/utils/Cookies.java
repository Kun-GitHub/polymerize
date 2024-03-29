package com.juiniot.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Cookie相关操作
 * Created by jianyu on 2016/12/16.
 */
public class Cookies {

    /**
     * 添加cookie
     *
     * @param response
     * @param name
     * @param value
     * @param maxAge 有效时间
     */
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    /**
     * 清除单个cookie
     *
     * @param response
     * @param name
     */
    public static void clearCookie(HttpServletResponse response, String name) {
        Cookie cookie = new Cookie(name, "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    /**
     * 检索所有cookie 封装到map集合 以其cookie name作为key cookie value作为value
     *
     * @param request
     * @return
     */
    public static Map<String, String> ReadCookieMap(HttpServletRequest request) {
        Map<String, String> cookieMap = new HashMap<String, String>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie.getValue());
            }
        }
        return cookieMap;
    }

    /**
     * 通过cookie name 获取 cookie value
     *
     * @param request
     * @param name
     * @return
     */
    public static String getValue(HttpServletRequest request, String name) {
        Map<String, String> cookieMap = ReadCookieMap(request);
        if (cookieMap.containsKey(name)) {
            String cookieValue = (String) cookieMap.get(name);
            return cookieValue;
        } else {
            return null;
        }
    }

}
