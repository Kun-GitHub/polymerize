package com.juiniot.common.utils;

import org.apache.commons.lang.StringUtils;

import java.util.Random;

/**
 * 字符串处理工具类
 * 
 */
public class StringUtil extends StringUtils{


	/**
	 * 判断对象是否为空
	 * 
	 * @param obj
	 *            待判断对象
	 * @return boolean true-空(null/空字符串/纯空白字符),false-非空
	 */
	public static final boolean isBlank(Object obj) {
		return obj == null || StringUtils.isBlank(obj.toString());
	}

	/**
	 * 判断字符串是否包含内容(与isBlank方法相反)
	 * 
	 * @param obj
	 *            待判断对象
	 * @return boolean true-有内容,false-空(null/空字符串/纯空白字符)
	 */
	public static final boolean hasText(Object obj) {
		return !isBlank(obj);
	}

	/**
	 * 转换为字符串
	 * 
	 * @param obj
	 *            待转换对象
	 * @return String obj为空时返回空字符串
	 */
	public static final String toString(Object obj) {
		return obj == null ? "" : obj.toString();
	}

	/**
	 * 返回一个对象的字符串,多数是处理字符串的.
	 * 
	 * @param obj
	 *            待处理对象
	 * @return 字符串
	 */
	public static final String trim(Object obj) {
		return obj == null ? "" : obj.toString().trim();
	}

    /**
     * 连接字符串
     *
     * @param o
     * @param flag
     * @return
     */
    public static String join(Object[] o, String flag) {
        StringBuffer str_buff = new StringBuffer();

        for (int i = 0, len = o.length; i < len; i++) {
            str_buff.append(String.valueOf(o[i]));
            if (i < len - 1) str_buff.append(flag);
        }

        return str_buff.toString();
    }

	public static String createNoncestr(int length) {
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String res = "";
		for (int i = 0; i < length; i++) {
			Random rd = new Random();
			res += chars.charAt(rd.nextInt(chars.length() - 1));
		}
		return res.replace("-", "");
	}

}
