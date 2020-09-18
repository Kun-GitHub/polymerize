package com.juiniot.common.business;

/**
 * 排序类型
 */
public enum OrderType {
	
	ASC("ASC"),
	DESC("DESC");
	String type;
	
	OrderType(String type){
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
