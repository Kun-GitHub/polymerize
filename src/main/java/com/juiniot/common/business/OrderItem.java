package com.juiniot.common.business;


/**
 * 
 * 排序项
 * 
 */
public class OrderItem {
	
	/**
	 * 列名（对应实体的属性命名规范）
	 */
	String columnName;  
	
	/**
	 * 排序方式
	 */
	String orderType = OrderType.DESC.getType();

	public String getColumnName() {
		return columnName;
	}
 
	public String getType() {
		return orderType;
	}

	
	/**
	 * @param columnName 列名（对应实体的属性命名规范）
	 * @param orderType 排序方式（asc或 desc）
	 */
	public <T,E> OrderItem(T columnName, E orderType) {
		this.columnName = columnName.toString();
		this.orderType = orderType.toString();
		if( !"asc".equalsIgnoreCase(this.orderType)){
			this.orderType = "desc";
		}
	}

}

