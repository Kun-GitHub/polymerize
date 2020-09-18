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
	private boolean success = true;

	// 返回信息
	private Object message;

	/**
	 * 返回{success：true}
	 */
	public static final BaseResponse SUCCESS = new BaseResponse(true);
	
	/**
	 * 返回{success：false}
	 */
	public static final BaseResponse FAILURE = new BaseResponse(false);

	public BaseResponse() {
		
	}

	public BaseResponse(boolean success) {
		this.success = success;
	}

	public BaseResponse(boolean success, Object message) {
		this.success = success;
		this.message = message;
	}

	/**
	 * 返回{success：true,message:"message"}
	 */
	public static BaseResponse success(Object message) {
		return new BaseResponse(true, message);
	}

	/**
	 * 返回{success：false,message:"message"}
	 */
	public static BaseResponse failure(Object message) {
		return new BaseResponse(false, message);
	}

	/**
	 * 判断是否成功
	 * @return success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * 设置成功标识
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * 获取返回信息
	 */
	public Object getMessage() {
		return message;
	}

	/**
	 * 设置返回信息
	 */
	public void setMessage(Object message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
