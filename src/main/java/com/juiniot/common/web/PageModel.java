package com.juiniot.common.web;

import com.juiniot.common.utils.Global;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页模型
 * @author ZHIFEN
 */
public class PageModel<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private int page = 1; // 当前页

	private int pageSize = Global.getPageSize(); // 每页数据条数

	private int totalRows = 0; // 总数据数

	private int totalPages = 0; // 总页数

	private List<T> list = new ArrayList<T>(); // 数据记录集
	

	/**
	 * 初始化
	 */
	public void init(int page, int totalRows, int pageSize) {
		this.page = page;
		this.totalRows = totalRows;
		this.pageSize = pageSize;
		this.pageInit();
	}

	private void pageInit() {
		if ((totalRows % pageSize) == 0) {
			totalPages = totalRows / pageSize == 0 ? 1 : totalRows / pageSize;
		} else {
			totalPages = totalRows / pageSize + 1;
		}
		
		if (totalRows == 0) {
			totalPages = 0;
		}
		else if (page > totalPages) {
			page = totalPages;
		}
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

}
