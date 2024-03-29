package com.juiniot.common.utils;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * URI编码器
 * 
 * <pre>
 * 实现请求参数的编码及解码;
 * 可解决中文乱码的问题
 * </pre>
 * 
 * @author ZHIFEN
 */
public class URICoder {

	/**
	 * 编码器(UTF-8)
	 * 
	 * <pre>
	 * 利用URLEncoder.encode两次编码;
	 * 如仅一次编码仍然有中文乱码的情况
	 * </pre>
	 * 
	 * @param data 需编码字符串
	 * @return 编码后字符串(null返回null)
	 */
	public static String encode(String data) {
		return encode(data, Global.getCharsetUtf8());
	}

	/**
	 * 解码器(UTF-8)
	 * 
	 * @param data 需解码字符串
	 * @return 解码后字符串(null返回null)
	 */
	public static String decode(String data) {
		return decode(data, Global.getCharsetUtf8());
	}

	/**
	 * 编码器
	 * 
	 * <pre>
	 * 利用URLEncoder.encode两次编码;
	 * 如仅一次编码仍然有中文乱码的情况
	 * </pre>
	 * 
	 * @param data 需编码字符串
	 * @param charset 编码类型
	 * @return 编码后字符串(null返回null)
	 */
	public static String encode(String data, String charset) {
		try {
			if (data == null || "".equals(data)) {
				return data;
			} else {
				return URLEncoder.encode(URLEncoder.encode(data, charset), charset);
			}
		} catch (Exception e) {
			throw new RuntimeException("encode error, charset:" + charset + ", data:" + data, e);
		}
	}

	/**
	 * 解码器
	 * 
	 * @param data 需解码字符串
	 * @param charset 编码类型
	 * @return 解码后字符串(null返回null)
	 */
	public static String decode(String data, String charset) {
		try {
			if (data == null || "".equals(data)) {
				return data;
			} else {
				return URLDecoder.decode(URLDecoder.decode(data, charset), charset);
			}
		} catch (Exception e) {
			throw new RuntimeException("decode error, charset:" + charset + ", data:" + data, e);
		}
	}
	
	public static void main(String[] args) {
		System.out.println(encode("解码器"));
	}

}
