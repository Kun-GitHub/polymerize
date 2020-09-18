package com.juiniot.common.utils.xls;

import org.apache.poi.hssf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * XLS文件读取工具类
 * 
 * @author 卢俊生
 */
public class XLSImportor {

	private final static DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.##");
	private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
	private int firstDataRow = 1; // 有效数据起始行索引
	private int dataSize = 0; // 有效数据行数
	private int cellSize = 0; // 有效数据列数
	private boolean resolve = true; // 文档解析是否成功;

	private HSSFWorkbook wb = null;
	private HSSFSheet sheet = null;

	/**
	 * 构造方法
	 * 
	 * @param realPath
	 *            文件绝对路径
	 * @param cellSize
	 *            有效列数(<=0时,读各行有效列)
	 *
	 *            起始有效数据行索引
	 */
	public XLSImportor(String realPath, int cellSize, int firstRow, boolean delRecFile) {
		InputStream fis = null;
		try {
			fis = new FileInputStream(realPath);
			wb = new HSSFWorkbook(fis);
			sheet = wb.getSheetAt(0);
			this.cellSize = cellSize > 0 ? cellSize : this.cellSize;
			firstDataRow = firstRow > 0 ? firstRow : firstDataRow;
			dataSize = sheet.getLastRowNum() - firstDataRow + 2;
		} catch (Exception e) {
			resolve = false;
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (delRecFile) {
				File file = new File(realPath);
				if (file.exists()) {
					file.delete();
				}
			}
		}
	}

	/**
	 * 获取所有有效数据行
	 * 
	 * @return List<String[]>
	 */
	public List<String[]> getAllRow() {
		List<String[]> data = new ArrayList<String[]>();

		for (int rowIndex = 0; rowIndex < dataSize; rowIndex++) {
			String[] row = getRow(rowIndex);
			if (row != null && row.length > 0) {
				data.add(row);
			}
		}

		return data;
	}

	/**
	 * 获取行
	 * 
	 * @param rowIndex
	 *            行索引(首行为0)
	 * @return String[]
	 */
	public String[] getRow(int rowIndex) {
		String[] data = null;
		HSSFRow row = sheet.getRow(firstDataRow + rowIndex - 1);
		if (row != null && row.getPhysicalNumberOfCells() > 0) {
			data = new String[cellSize];
			int minCellSize = minCellSize(row.getLastCellNum());
			if (cellSize == 0) {
				data = new String[minCellSize];
			}
			for (int cellIndex = 0; cellIndex < minCellSize; cellIndex++) {
				data[cellIndex] = getCellValue(row.getCell(cellIndex));
			}
			if (cellSize > minCellSize) {
				data = fixArray(data);
			}
		}

		return data;
	}

	/**
	 * 获取单元格
	 * 
	 * @param rowIndex
	 *            行索引(首行为0)
	 * @param cellIndex
	 *            列索引(首列为0)
	 * @return String
	 */
	public String getCell(int rowIndex, int cellIndex) {
		String data = "";
		HSSFRow row = sheet.getRow(firstDataRow + rowIndex - 1);
		if (row != null) {
			data = getCellValue(row.getCell(cellIndex));
		}
		return data;
	}

	/**
	 * 获取单元格值
	 * 
	 * @param cell
	 *            单元格
	 * @return String
	 */
	private String getCellValue(HSSFCell cell) {
		String data = "";
		if (cell != null) {
			switch (cell.getCellType()) {
			case HSSFCell.CELL_TYPE_NUMERIC:
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					data = formatDate(cell.getDateCellValue());
				} else {
					data = formatNumeric(cell.getNumericCellValue());
				}
				break;
			case HSSFCell.CELL_TYPE_STRING:
				data = cell.getStringCellValue().trim();
				break;
			default:
				break;
			}
		}

		return data;
	}

	public int titleLength() {
		int length = 0;
		HSSFRow row = sheet.getRow(0);
		if (row != null) {
			length = row.getPhysicalNumberOfCells();
		}
		return length;
	}

	/**
	 * 有效数据行数
	 * 
	 * @return int
	 */
	public int dataSize() {
		return dataSize;
	}

	/**
	 * 文档是否解析成功
	 * 
	 * @return boolean
	 */
	public boolean isResolve() {
		return resolve;
	}

	public int cellSize() {
		return cellSize;
	}

	private int minCellSize(int lastCellNum) {
		return cellSize == 0 || lastCellNum < cellSize ? lastCellNum : cellSize;
	}

	private static String formatDate(Date date) {
		return DATE_FORMAT.format(date);
	}

	private static String formatNumeric(double num) {
		return DECIMAL_FORMAT.format(num);
	}

	public static boolean hasContent(String[] arr) {
		boolean hasContent = false;
		for (String cell : arr) {
			if (null != cell && !"".equals(cell.trim())) {
				hasContent = true;
				break;
			}
		}
		return hasContent;
	}

	private String[] fixArray(String[] arr) {
		if (hasContent(arr)) {
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] == null) {
					arr[i] = "";
				}
			}
		} else {
			arr = new String[0];
		}
		return arr;
	}

	/**
	 * 设置获取时间格式(默认为'yyyy-MM-dd')
	 * 
	 * @param pattern
	 *            格式
	 */
	public void setDatePattern(String pattern) {
		DATE_FORMAT = new SimpleDateFormat(pattern);
	}

	/*
	 * public String toString(){ StringBuilder src = new StringBuilder();
	 * src.append("{"); if (rowNum > 0 && cellNum > 0) {
	 * src.append("[").append(data[0][0]); for (int j = 1; j < cellNum; j++) {
	 * src.append(",").append(data[0][j]); } src.append("]"); for (int i = 1; i
	 * < rowNum; i++) { src.append(",[").append(data[i][0]); for (int j = 1; j <
	 * cellNum; j++) { src.append(",").append(data[i][j]); } src.append("]"); }
	 * } src.append("}"); return src.toString(); }
	 */

}
