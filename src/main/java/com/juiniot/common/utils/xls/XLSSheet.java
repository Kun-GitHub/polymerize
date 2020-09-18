package com.juiniot.common.utils.xls;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * XLS表格对象
 * 
 * @author 卢俊生
 */
public class XLSSheet {

	private String name = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()); // sheet名

	/** 表格描述 **/
	private String desc;
	/** 是否表格备注型标题样式 **/
	private boolean isRemarkTitle = false;

	/**
	 * @return the isRemarkTitle
	 */
	public boolean isRemarkTitle() {
		return isRemarkTitle;
	}

	/**
	 * @param isRemarkTitle the isRemarkTitle to set
	 */
	public void setRemarkTitle(boolean isRemarkTitle) {
		this.isRemarkTitle = isRemarkTitle;
	}

	private List<Object[][]> groupHeaders = new ArrayList<Object[][]>();

	/** 列头 **/
	private String[] titles = {};

	/** 列宽(单位:像素),某列设为0则采用自动列宽 **/
	private short[] cellWeights = {};

	/** 列表数据,每个Object[]代表一行 **/
	private List<Object[]> data = new ArrayList<Object[]>();

	/** 函数对象集合 **/
	private List<XLSFormula> formulas = new ArrayList<XLSFormula>();

	/** 总列数 **/
	private int cellSize = 0;

	/** 标识各列是否金额类型:true-是,false-否 **/
	private boolean[] moneyCols = {};

	/** 标识各列是否百分比:true-是,false-否 **/
	private boolean[] percentCols = {};

	/** 标识各列是否强制使用文本格式:true-是,false-否 **/
//	private boolean[] textCols = {};

	public String toString() {
		return new StringBuilder().append("{SheetName:").append(name).append(",Description:").append(desc).append("}").toString();
	}

	/**
	 * 最大列索引
	 */
	public int maxCell() {
		return cellSize - 1;
	}

	/**
	 * 是否已设置表格描述
	 */
	public boolean hasDesc() {
		return desc != null && !"".equals(desc);
	}

	/**
	 * 是否已设置列头
	 */
	public boolean hasTitles() {
		return titles != null && titles.length > 0;
	}

	/**
	 * 是否已设置多层表头
	 */
	public boolean hasGroupHeaders() {
		return groupHeaders != null && groupHeaders.size() > 0;
	}

	/**
	 * 是否已设置列宽
	 */
	public boolean hasCellWeights() {
		return cellWeights != null && cellWeights.length > 0;
	}

	/**
	 * 总行数
	 */
	public int getRowSize() {
		return data.size() + getPosition();
	}

	/**
	 * 数据行偏移值(首行数据位置)
	 */
	public int getPosition() {
		int p = 0;
		if (hasDesc()) {
			p++;
		}
		if (hasTitles()) {
			p++;
		}
		if (hasGroupHeaders()) {
			p += groupHeaders.size();
		}
		return p;
	}

	/**
	 * 获取总列数
	 */
	public int getCellSize() {
		return cellSize;
	}

	/**
	 * 获取Sheet名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置Sheet名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取表格标题(描述)
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * 设置表格标题(描述)
	 */
	public void setDesc(String desc) {
		if (null != desc && !"".equals(desc)) {
			this.desc = desc;
			correctFormulas();
		}
	}

	/**
	 * 获取表格列头
	 */
	public String[] getTitles() {
		return titles;
	}

	/**
	 * 设置表格列头
	 */
	public void setTitles(String[] titles) {
		if (null != titles && titles.length > 0) {
			this.titles = titles;

			// 设置总列数
			if (titles.length > cellSize) {
				this.cellSize = titles.length;
			}
			correctFormulas();
		}
	}

	public List<Object[][]> getGroupHeaders() {
		return groupHeaders;
	}

	/**
	 * 设置多层表头
	 * 
	 * <pre>
	 * 格式:{{"名称", x, y},{},...},其中x,y分别为起始列和截止列(首列为1)
	 * </pre>
	 */
	public void setGroupHeaders(Object[][] groupHeader) {
		if (null != groupHeader && groupHeader.length > 0) {
			this.groupHeaders.add(groupHeader);
			correctFormulas();
		}
	}

	/**
	 * 获取列宽
	 */
	public short[] getCellWeights() {
		return cellWeights;
	}

	/**
	 * 设置列宽,覆盖原有设置(宽度单位为像素)
	 */
	public void setCellWeights(short[] cellWeights) {
		if (null != cellWeights && cellWeights.length > 0) {
			this.cellWeights = cellWeights;
		}
	}

	/**
	 * 设置某列列宽,不影响其它列(宽度单位为像素)
	 * 
	 * @param cellIndex
	 *            列索引(首列为1)
	 * @param weight
	 *            列宽(像素)
	 */
	public void setCellWeight(int cellIndex, short weight) {
		if (cellIndex > 0) {
			if (cellIndex > cellWeights.length) {
				short[] temp = new short[cellIndex];
				for (int i = 0; i < cellWeights.length; i++) {
					temp[i] = cellWeights[i];
				}
				cellWeights = temp;
			}
			cellWeights[cellIndex - 1] = weight;
		}
	}

	/**
	 * 获取内容(数据)
	 */
	public List<Object[]> getData() {
		return data;
	}

	/**
	 * 设置内容(数据)
	 */
	public void setData(List<Object[]> data) {
		if (null != data) {
			this.data = data;
			// 设置总列数
			for (Object[] row : data) {
				if (row.length > cellSize) {
					this.cellSize = row.length;
				}
			}
			correctFormulas();
		}
	}

	public List<XLSFormula> getFormulas() {
		return formulas;
	}

	public void setFormulas(List<XLSFormula> formulas) {
		if (formulas != null) {
			this.formulas = formulas;
			correctFormulas();
		}
	}

	public void addFormula(XLSFormula formula) {
		if (null != formula) {
			formulas.add(formula);
			correctFormulas();
		}
	}

	/**
	 * 判断某列存放的数据是否为金额
	 * 
	 * @param index
	 *            列索引(首列为0)
	 */
	public boolean isMoney(int index) {
		boolean isMoney = false;

		if (moneyCols.length > index) {
			isMoney = moneyCols[index];
		}

		return isMoney;
	}

	/**
	 * 指定存放金额数据的列,覆盖原有设置<BR>
	 * 如指定第1,3,4列存放金额数据,setMoneyCells(1,3,4);
	 * 
	 * @param indexs
	 *            列索引(首列为1)
	 */
	public void setMoneyCells(int... indexs) {
		int max = max(indexs);
		moneyCols = new boolean[max > cellSize ? max : cellSize];
		for (int index : indexs) {
			moneyCols[index - 1] = true;
			if (percentCols.length >= index) {
				percentCols[index - 1] = false;
			}
//			if (textCols.length >= index) {
//				textCols[index - 1] = false;
//			}
		}
	}

	/**
	 * 判断某列存放的数据是否为百分比
	 * 
	 * @param index
	 *            列索引(首列为0)
	 */
	public boolean isPercent(int index) {
		boolean isPercent = false;

		if (percentCols.length > index) {
			isPercent = percentCols[index];
		}

		return isPercent;
	}

	/**
	 * 指定百分比格式的列,覆盖原有设置<BR>
	 * 如指定第1,3,4列为百分比格式,setPercentsCells(1,3,4);
	 * 
	 * @param indexs
	 *            列索引(首列为1)
	 */
	public void setPercentsCells(int... indexs) {
		int max = max(indexs);
		percentCols = new boolean[max > cellSize ? max : cellSize];
		for (int index : indexs) {
			percentCols[index - 1] = true;
			if (moneyCols.length >= index) {
				moneyCols[index - 1] = false;
			}
//			if (textCols.length >= index) {
//				textCols[index - 1] = false;
//			}
		}
	}

	/**
	 * 判断某列存放的数据是否为百分比
	 * 
	 * @param indexs
	 *            列索引(首列为0)
	 */
//	public boolean isText(int index) {
//		boolean isPercent = false;
//
//		if (textCols.length > index) {
//			isPercent = textCols[index];
//		}
//
//		return isPercent;
//	}

	/**
	 * 指定强制使用文本格式的列,覆盖原有设置<BR>
	 * 如指定第1,3,4列强制使用文本格式,setTextCols(1,3,4);
	 * 
	 * @param indexs
	 *            列索引(首列为1)
	 */
//	public void setTextCols(int... indexs) {
//		int max = max(indexs);
//		textCols = new boolean[max > cellSize ? max : cellSize];
//		for (int index : indexs) {
//			textCols[index - 1] = true;
//			if (moneyCols.length >= index) {
//				moneyCols[index - 1] = false;
//			}
//			if (percentCols.length >= index) {
//				percentCols[index - 1] = false;
//			}
//		}
//	}
//
//	public void setTextCols() {
//		textCols = new boolean[cellSize];
//		for (int i = 0; i < cellSize; i++) {
//			textCols[i] = true;
//			if (moneyCols.length > i) {
//				moneyCols[i] = false;
//			}
//			if (percentCols.length > i) {
//				percentCols[i] = false;
//			}
//		}
//	}

	/**
	 * 获取整型数组中的最大值
	 * 
	 * @param cellNums
	 * @return
	 */
	private static int max(int... cellNums) {
		int max = 0;
		for (int num : cellNums) {
			if (num > max) {
				max = num;
			}
		}
		return max;
	}

	private void correctFormulas() {
		for (int i = 0; i < formulas.size(); i++) {
			formulas.set(i, formulas.get(i).correct(this));
		}
	}

}
