package com.juiniot.common.web.preview;

/**
 * 功能权限枚举类
 * @author ZHIFEN
 *
 */
public enum FunAuth {
	

	NO_AUTH(0);//不需要权限控制
	
	long funId;

	FunAuth(long funId) {
		this.funId = funId;
	}

	public long getFunId() {
		return funId;
	}
	
	
}
