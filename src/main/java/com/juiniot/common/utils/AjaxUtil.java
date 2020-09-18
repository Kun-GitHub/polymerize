package com.juiniot.common.utils;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AjaxUtil {
	
	/** 日志对象  */
	protected static final Logger log = LoggerFactory.getLogger(AjaxUtil.class);

	private static final String SUCCESS = "success";
	private static final String MESSAGE = "message";
	private static final String TYPE = "type"; 

	/**
	 * AJAX输出
	 * 
	 * @param content 内容
	 * @param type 类型
	 */
	private static final void ajax(HttpServletResponse response,Object content, String type) {
		try {
			response.setContentType(type + ";charset=UTF-8");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			PrintWriter writer = response.getWriter();
			writer.write(content == null ? "" : content.toString());
			writer.flush();
			writer.close();
			writer = null;
		} catch (IOException e) {
			log.error("AjaxUtil.ajax()方法异步输出数据出错.", e);
		}
	}

	/**
	 * AJAX输出文本
	 * 
	 * @param text 内容
	 */
	public static final void text(HttpServletResponse response,Object text) {
		ajax(response, text, "text/plain");
	}

	/**
	 * AJAX输出HTML
	 * 
	 * @param html 内容
	 */
	public static final void html(HttpServletResponse response,Object html) {
		ajax(response, html, "text/html");
	}

	/**
	 * AJAX输出XML
	 * 
	 * @param xml 内容
	 */
	public static final void xml(HttpServletResponse response,Object xml) {
		ajax(response, xml, "text/xml");
	}

	/**
	 * AJAX输出JSON
	 * 
	 * @param jsonString JSON字符串
	 */
	public static final void json(HttpServletResponse response,String jsonString) {
		ajax(response, jsonString, "application/json");
	}
	
	/**
	 * 输出响应结果(AJAX请求)
	 * 
	 * <pre>
	 * 	消息内容 : {"success":status}
	 * </pre>
	 * 
	 * @param status 状态
	 */
	public static final void ajax(HttpServletResponse response,boolean success) {
		JSONObject obj = new JSONObject();
		obj.put(SUCCESS, success);
		json(response,obj.toString());
	}
	
	/**
	 * 输出响应结果(AJAX请求)
	 * 
	 * <pre>
	 * 	消息内容 : {"success":status, "message":message}
	 * </pre>
	 * 
	 * @param status 状态
	 */
	public static final void ajax(HttpServletResponse response,boolean status,String message) {
		JSONObject obj = new JSONObject();
		obj.put(SUCCESS, status);
		obj.put(MESSAGE, message);
		json(response,obj.toString());
	}
	
	/**
	 * AJAX输出JSON
	 */
	public static final void success(HttpServletResponse response) {
		ajax(response, true);
	}
	
	/**
	 * AJAX输出JSON
	 * 
	 * @param message 输出信息
	 */
	public static final void success(HttpServletResponse response,String message) {
		ajax(response,true,message);
	}
	
	/**
	 * AJAX输出JSON
	 * 
	 */
	public static final void failure(HttpServletResponse response) {
		ajax(response, false);
	}
	
	/**
	 * AJAX输出JSON
	 * 
	 * @param message 输出信息
	 */
	public static final void failure(HttpServletResponse response,String message) {
		ajax(response,false,message);
	}
	
	/**
	 * AJAX输出JSON
	 * 
	 * @param message 输出信息
	 * @param type 信息类型：error,warning,info，主要用来区分返回的信息类型
	 */
	public static final void failure(HttpServletResponse response,String message,String type) {
		JSONObject obj = new JSONObject();
		obj.put(SUCCESS, false);
		obj.put(MESSAGE, message);
		obj.put(TYPE, type);
		json(response,obj.toString());
	}
	
	
}
