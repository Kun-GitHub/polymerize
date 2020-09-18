/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.juiniot.common.utils;

import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

/**
 * 全局配置类
 * @author zhifen
 */
public class Global {

    /**
     * 当前对象实例
     */
    private static Global global = new Global();
    /**
     * 保存全局属性值
     */
    private static Map<String, String> map = Maps.newHashMap();
    /**
     * 属性文件加载对象
     */
    private static PropertiesLoader loader = new PropertiesLoader("global.properties");

    /**
     * 获取当前对象实例
     */
    public static Global getInstance() {
        return global;
    }

    /**
     * 获取配置
     */
    public static String getConfig(String key) {
        String value = map.get(key);
        if (value == null) {
            value = loader.getProperty(key);
            map.put(key, value != null ? value : StringUtils.EMPTY);
        }
        return value;
    }

    /**
     * 获取配置
     */
    public static String getConfig(String key, String defaultStr) {
        String value = getConfig(key);
        if (StringUtils.isBlank(value)) {
            return defaultStr;
        }
        return value;
    }

    /**
     * 获取配置
     */
    public static Integer getConfigInt(String key) {
        String value = getConfig(key);

        if (StringUtils.isNumeric(value)) {
            return Integer.parseInt(value);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * 获取配置
     */
    public static Integer getConfigInt(String key, int defaultValue) {
        String value = getConfig(key);
        if (StringUtils.isNumeric(value)) {
            return Integer.parseInt(value);
        } else {
            return defaultValue;
        }

    }

    /**
     * 获取配置
     */
    public static Double getConfigDouble(String key) {
        String value = getConfig(key);

        try {
            return Double.parseDouble(value);
        } catch (Exception e) {
            return  null;
        }
    }

    /**
     * 获取配置
     */
    public static Double getConfigDouble(String key, double defaultValue) {
        String value = getConfig(key);
        try {
            return Double.parseDouble(value);
        } catch (Exception e) {
            return defaultValue;
        }

    }


    /**
     * 获取CharsetUtf8
     */
    public static String getCharsetUtf8() {
        return getConfig("charset.utf8");
    }

    /**
     * 获取分页参数
     */
    public static int getPageSize() {
        return getConfigInt("pageSize");
    }

    /**
     * 获取文件保存路径
     */
    public static String getFilePath() {
        return getConfig("filePath");
    }

}
