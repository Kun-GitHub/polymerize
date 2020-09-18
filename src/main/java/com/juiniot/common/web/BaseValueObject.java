package com.juiniot.common.web;

import com.juiniot.common.utils.Global;

/**
 * 基础VO对象
 * 
 * @author ZHIFEN
 * 
 */

public class BaseValueObject {
	
	Integer page = 1; //当前页
	Integer startRow = 0; //开始行
	Integer pageSize = Global.getPageSize(); //每页显示记录数
	
	String columnName; //排序列 列名（对应实体的属性命名规范）
	String orderType;  //排序方式：asc或desc


	public Integer getPage() {
		return page == null ? 1 : page;
	}

	public void setPage(Integer page) {
		if (page == null || page == 0) {
			page = 1;
		}
		this.page = page;
		this.setStartRow((page - 1) * this.getPageSize());
	}

	public Integer getStartRow() {
		return startRow;
	}

	private void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}

	public Integer getPageSize() {
		return pageSize == null ? Global.getPageSize() : pageSize;
	}

	public void setPageSize(Integer pageSize) {
		if (pageSize == null || pageSize == 0) {
			pageSize = Global.getPageSize();
		}
		this.pageSize = pageSize;
		this.setStartRow((page - 1) * this.getPageSize());
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
}
