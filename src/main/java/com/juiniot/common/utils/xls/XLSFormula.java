package com.juiniot.common.utils.xls;

/**
 * XLS函数对象
 * 
 * @author 卢俊生
 */
public class XLSFormula {

	private final static String DEF_CELL = "A";
	private final static String DEF_ROW_FIRST = "F";
	private final static String DEF_ROW_LAST = "L";

	/** 是否简单文本 **/
	private boolean isText;
	private Integer rowInit;

	/** 行索引(首值为0,默认末行) **/
	private Integer row;

	/** 列表索引(首值为0,默认0) **/
	private Integer cell;

	/** 内容(函数体/简单文本),如:SUM(A1:B2) **/
	private String formula;

	/** 函数名 **/
	private String name;

	/** 范围:起始单元格列索引(默认:"A"),如 A,B,C **/
	private String firstCell = DEF_CELL;
	/** 范围:起始单元格行索引("F":除表格描述,列头外的首行;"L":末行,默认:"F"),如 1,2,3 **/
	private String firstRowInit = DEF_ROW_FIRST;
	private Integer firstRow;
	/** 范围:截止单元格列索引(默认:"A"),如 A,B,C **/
	private String lastCell = DEF_CELL;
	/** 范围:截止单元格行索引("F":除表格描述,列头外的首行;"L":末行,默认:"L"),如 1,2,3 **/
	private String lastRowInit = DEF_ROW_LAST;
	private Integer lastRow;

	/**
	 * 根据XLSSheet相关参数调整XLSFormula各值
	 * 
	 * @param sheet
	 *            XLSSheet
	 * @return XLSFormula
	 */
	public XLSFormula correct(XLSSheet sheet) {
		if (rowInit == null) {
			row = sheet.getRowSize();
		}
		if (DEF_ROW_FIRST.equalsIgnoreCase(firstRowInit)) {
			firstRow = sheet.getPosition();
		} else if (DEF_ROW_LAST.equalsIgnoreCase(firstRowInit)) {
			firstRow = sheet.getRowSize();
		}
		if (DEF_ROW_FIRST.equalsIgnoreCase(lastRowInit)) {
			lastRow = sheet.getPosition();
		} else if (DEF_ROW_LAST.equalsIgnoreCase(lastRowInit)) {
			lastRow = sheet.getRowSize();
		}

		return this;
	}

	/**
	 * 构造方法
	 * 
	 * @param cell
	 *            列索引(首值为1)
	 * @param formula
	 *            内容(函数体/简单文本)
	 * @param isText
	 *            是否简单文本
	 */
	public XLSFormula(int cell, String formula, boolean isText) {
		this.cell = cell > 1 ? cell - 1 : 0;
		this.formula = formula;
		this.isText = isText;
	}

	/**
	 * 构造方法
	 * 
	 * @param row
	 *            行索引(首值为1)
	 * @param cell
	 *            列索引(首值为1)
	 * @param formula
	 *            内容(函数体/简单文本)
	 * @param isText
	 *            是否简单文本
	 */
	public XLSFormula(int row, int cell, String formula, boolean isText) {
		this.rowInit = row;
		this.row = rowInit > 1 ? rowInit - 1 : 0;
		this.cell = cell > 1 ? cell - 1 : 0;
		this.formula = formula;
		this.isText = isText;
	}

	/**
	 * 构造方法
	 * 
	 * @param cell
	 *            列索引(首值为1)
	 * @param name
	 *            函数名
	 * @param firstCell
	 *            范围:起始单元格列索引(默认:"A"),如 A,B,C
	 * @param lastCell
	 *            范围:截止单元格列索引(默认:"A"),如 A,B,C
	 */
	public XLSFormula(int cell, String name, String firstCell, String lastCell) {
		this.cell = cell > 1 ? cell - 1 : 0;
		this.name = null == name || "".equals(name) ? "" : name;
		this.firstCell = null == firstCell || "".equals(firstCell) ? DEF_CELL : firstCell;
		this.lastCell = null == lastCell || "".equals(lastCell) ? DEF_CELL : lastCell;
	}

	/**
	 * 构造方法
	 * 
	 * @param row
	 *            行索引(首值为1)
	 * @param cell
	 *            列索引(首值为1)
	 * @param name
	 *            函数名
	 * @param firstCell
	 *            范围:起始单元格列索引(默认:"A"),如 A,B,C
	 * @param lastCell
	 *            范围:截止单元格列索引(默认:"A"),如 A,B,C
	 */
	public XLSFormula(int row, int cell, String name, String firstCell, String lastCell) {
		this.rowInit = row;
		this.row = rowInit > 1 ? rowInit - 1 : 0;
		this.cell = cell > 1 ? cell - 1 : 0;
		this.name = null == name || "".equals(name) ? "" : name;
		this.firstCell = null == firstCell || "".equals(firstCell) ? DEF_CELL : firstCell;
		this.lastCell = null == lastCell || "".equals(lastCell) ? DEF_CELL : lastCell;
	}

	/**
	 * 构造方法
	 * 
	 * @param cell
	 *            列索引(首值为1)
	 * @param name
	 *            函数名
	 * @param firstCell
	 *            范围:起始单元格列索引(默认:"A"),如 A,B,C
	 * @param firstRow
	 *            范围:起始单元格行索引("F":除表格描述,列头外的首行;"L":末行,默认:"F"),如 1,2,3
	 * @param lastCell
	 *            范围:截止单元格列索引(默认:"A"),如 A,B,C
	 * @param lastRow
	 *            范围:截止单元格行索引("F":除表格描述,列头外的首行;"L":末行,默认:"L"),如 1,2,3
	 */
	public XLSFormula(int cell, String name, String firstCell, String firstRow, String lastCell, String lastRow) {
		this.cell = cell > 1 ? cell - 1 : 0;
		this.name = null == name || "".equals(name) ? "" : name;
		this.firstCell = null == firstCell || "".equals(firstCell) ? DEF_CELL : firstCell;
		this.firstRowInit = null == firstRow || "".equals(firstRow) ? DEF_ROW_FIRST : firstRow;
		this.lastCell = null == lastCell || "".equals(lastCell) ? DEF_CELL : lastCell;
		this.lastRowInit = null == lastRow || "".equals(lastRow) ? DEF_ROW_LAST : lastRow;

		if (!DEF_ROW_FIRST.equalsIgnoreCase(this.firstRowInit) && !DEF_ROW_LAST.equalsIgnoreCase(this.firstRowInit)) {
			this.firstRow = Integer.parseInt(this.firstRowInit);
		}
		if (!DEF_ROW_FIRST.equalsIgnoreCase(this.lastRowInit) && !DEF_ROW_LAST.equalsIgnoreCase(this.lastRowInit)) {
			this.lastRow = Integer.parseInt(this.lastRowInit);
		}
	}

	/**
	 * 构造方法
	 * 
	 * @param row
	 *            行索引(首值为1)
	 * @param cell
	 *            列索引(首值为1)
	 * @param name
	 *            函数名
	 * @param firstCell
	 *            范围:起始单元格列索引(默认:"A"),如 A,B,C
	 * @param firstRow
	 *            范围:起始单元格行索引("F":除表格描述,列头外的首行;"L":末行,默认:"F"),如 1,2,3
	 * @param lastCell
	 *            范围:截止单元格列索引(默认:"A"),如 A,B,C
	 * @param lastRow
	 *            范围:截止单元格行索引("F":除表格描述,列头外的首行;"L":末行,默认:"L"),如 1,2,3
	 */
	public XLSFormula(int row, int cell, String name, String firstCell, String firstRow, String lastCell, String lastRow) {
		this.rowInit = row;
		this.row = rowInit > 1 ? rowInit - 1 : 0;
		this.cell = cell > 1 ? cell - 1 : 0;
		this.name = null == name || "".equals(name) ? "" : name;
		this.firstCell = null == firstCell || "".equals(firstCell) ? DEF_CELL : firstCell;
		this.firstRowInit = null == firstRow || "".equals(firstRow) ? DEF_ROW_FIRST : firstRow;
		this.lastCell = null == lastCell || "".equals(lastCell) ? DEF_CELL : lastCell;
		this.lastRowInit = null == lastRow || "".equals(lastRow) ? DEF_ROW_LAST : lastRow;

		if (!DEF_ROW_FIRST.equalsIgnoreCase(this.firstRowInit) && !DEF_ROW_LAST.equalsIgnoreCase(this.firstRowInit)) {
			this.firstRow = Integer.parseInt(this.firstRowInit);
		}
		if (!DEF_ROW_FIRST.equalsIgnoreCase(this.lastRowInit) && !DEF_ROW_LAST.equalsIgnoreCase(this.lastRowInit)) {
			this.lastRow = Integer.parseInt(this.lastRowInit);
		}
	}

	public String getFormula() {
		if (null == formula || "".equals(formula)) {
			formula = name.concat("(").concat(firstCell).concat(firstRow.toString()).concat(":").concat(lastCell)
					.concat(lastRow.toString()).concat(")");
		}
		return formula;
	}

	public Integer getRow() {
		return row;
	}

	public Integer getCell() {
		return cell;
	}

	public boolean isText() {
		return isText;
	}

	public void setText(boolean isText) {
		this.isText = isText;
	}

}
