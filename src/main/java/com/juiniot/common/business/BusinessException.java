package com.juiniot.common.business;

/**
 * 业务逻辑层的  Exception 接口
 *
 */
public class BusinessException extends Exception {

	private static final long serialVersionUID = 1L;
	
	/**
	 * @param pEx
	 */
	public BusinessException(Exception pEx) {
		super(pEx);
	}

	/**
	 * @param pStrMsg
	 * @param pEx
	 */
	public BusinessException(String pStrMsg, Exception pEx) {
		super(pStrMsg, pEx);
	}

	/**
	 * @param pStrMsg
	 */
	public BusinessException(String pStrMsg) {
		super(pStrMsg);
	}
	
}
