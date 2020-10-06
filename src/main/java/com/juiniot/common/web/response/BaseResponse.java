package com.juiniot.common.web.response;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 基础响应对象
 * 
 * @author ZHIFEN
 */
public class BaseResponse {

	// 请求结果
	private int resultCode = -1;

	// 返回信息
	private String message = null;

	// 返回信息
	private Object resultData = null;

	/**
	 * 返回{success：true}
	 */
	public static final BaseResponse SUCCESS = new BaseResponse(0);
	
	/**
	 * 返回{success：false}
	 */
	public static final BaseResponse FAILURE = new BaseResponse(-1);

	public BaseResponse() {
		
	}

	public BaseResponse(int resultCode) {
		this.resultCode = resultCode;
	}

	public BaseResponse(int resultCode, String message) {
		this.resultCode = resultCode;
		this.message = message;
	}

	public BaseResponse(int resultCode, String message, Object resultData) {
		this.resultCode = resultCode;
		this.message = message;
		this.resultData = resultData;
	}

	/**
	 * 返回{success：true,message:"message"}
	 */
	public static BaseResponse success(String message) {
		return new BaseResponse(0, message);
	}
	public static BaseResponse success(String message, String resultData) {
		return new BaseResponse(0, message, resultData);
	}

	/**
	 * 返回{success：false,message:"message"}
	 */
	public static BaseResponse failure(String message) {
		return new BaseResponse(-1, message);
	}

	/**
	 * 判断是否成功
	 * @return success
	 */
	public int getResultCode() {
		return resultCode;
	}

	/**
	 * 设置成功标识
	 */
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	/**
	 * 获取返回信息
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * 设置返回信息
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResultData() {
		return resultData;
	}

	public void setResultData(Object resultData) {
		this.resultData = resultData;
	}
}
