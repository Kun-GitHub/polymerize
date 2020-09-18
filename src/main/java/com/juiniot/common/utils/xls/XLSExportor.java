package com.juiniot.common.utils.xls;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFCellUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * XLS文件导出工具类,与 {@link XLSFile} 和 {@link XLSSheet} 配合使用
 * 
 * <pre>
 * eg: 
 * XLSFile xlsFile = new XLSFile();
 * xlsFile.setFileName("导出excel文件(默认为当前时间,精确到分钟)");
 * XLSSheet xlsSheet = new XLSSheet();
 * xlsSheet.setName("表格名(xls中标签位置)");
 * xlsSheet.setDesc("表格标题/描述(首行,可空)");
 * xlsSheet.setTitles(new String[] { "列头A", "列头B", "列头C"});
 * xlsSheet.setCellWeights(new int[] { 180, 0, 120 });//列宽,不设值时设置自动列宽
 * 
 * List<Object[]> data = new ArrayList<Object[]>();
 * data.add(new Object[]{"abc",123,new Date()});//一行
 * xlsSheet.setData(data);
 * 
 * xlsFile.addXlsSheet(xlsSheet); //可添加多个,或用setSheets(List<XLSSheet> sheets);
 * 
 * XLSExportor.export(getResponse(), xlsFile);//需传入HttpServletResponse对象
 * </pre>
 * 
 * @author 卢俊生
 */
public class XLSExportor {

	private static HSSFWorkbook wb = null;;

	/** 表格描述样式 **/
	private static HSSFCellStyle defaultDescStyle = null;

	/** 列头样式 **/
	private static HSSFCellStyle titleStyle = null;

	/** 一般内容样式 **/
	private static HSSFCellStyle dataStyle = null;

	/** 文本样式 **/
//	private static HSSFCellStyle textStyle = null;

	/** 日期样式 **/
	private static HSSFCellStyle dateStyle = null;

	/** 金额样式 **/
	private static HSSFCellStyle moneyStyle = null;

	/** 百分比样式 **/
	private static HSSFCellStyle percentStyle = null;

	/** 统计金额 */
	private static HSSFCellStyle totalNumStyle = null;

	/** 表格 **/
	private static HSSFSheet sheet = null;
	/** 行 **/
	private static HSSFRow row = null;
	/** 单元格 **/
	private static HSSFCell cell = null;

	/** XLS表格对象 **/
	private static XLSSheet xlsSheet = null;

	/** 日期格式 **/
	// private static String dateFmt = null;

	private static HSSFFormulaEvaluator evaluator = null;

	/**
	 * 初始化
	 */
	private static void init(XLSFile xlsFile) {
		wb = new HSSFWorkbook();
		evaluator = new HSSFFormulaEvaluator(wb);

		// 表格描述样式
		defaultDescStyle = wb.createCellStyle();
		defaultDescStyle.setFillForegroundColor(HSSFColor.SEA_GREEN.index); // 前景色CORNFLOWER_BLUE
		defaultDescStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		defaultDescStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
		defaultDescStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
		defaultDescStyle.setWrapText(true); // 自动换行
		defaultDescStyle.setBorderTop(HSSFCellStyle.BORDER_THIN); // 上边框
		defaultDescStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		defaultDescStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN); // 左边框
		defaultDescStyle.setBorderRight(HSSFCellStyle.BORDER_THIN); // 右边框
		HSSFFont descFont = wb.createFont();
		descFont.setFontName("Courier New"); // 字体名称
		descFont.setFontHeightInPoints((short) 12); // 字体高度
		descFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 宽度
		defaultDescStyle.setFont(descFont);

		// 列头样式
		titleStyle = wb.createCellStyle();
		titleStyle.setFillForegroundColor(HSSFColor.GOLD.index); // 前景色GOLD
		titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 左对齐
		titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
		titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN); // 上边框
		titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN); // 左边框
		titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN); // 右边框
		HSSFFont titleFont = wb.createFont();
		titleFont.setFontName("Courier New"); // 字体名称
		titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 宽度
		titleStyle.setFont(titleFont);

		HSSFFont dataFont = wb.createFont();
		dataFont.setFontName("Courier New"); // 字体名称
		// 一般内容单元格样式
		dataStyle = wb.createCellStyle();
		dataStyle.setBorderTop(HSSFCellStyle.BORDER_THIN); // 上边框
		dataStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		dataStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN); // 左边框
		dataStyle.setBorderRight(HSSFCellStyle.BORDER_THIN); // 右边框
//		dataStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 左对齐
		dataStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
		dataStyle.setFont(dataFont);
		dataStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0"));

//		textStyle = wb.createCellStyle();
//		textStyle.setBorderTop(HSSFCellStyle.BORDER_THIN); // 上边框
//		textStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
//		textStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN); // 左边框
//		textStyle.setBorderRight(HSSFCellStyle.BORDER_THIN); // 右边框
//		textStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
//		textStyle.setFont(dataFont);
//		textStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("@"));

		// 日期(时间)单元格样式
		dateStyle = wb.createCellStyle();
		dateStyle.setBorderTop(HSSFCellStyle.BORDER_THIN); // 上边框
		dateStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		dateStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN); // 左边框
		dateStyle.setBorderRight(HSSFCellStyle.BORDER_THIN); // 右边框
		dateStyle.setFont(dataFont);
		dateStyle.setDataFormat(wb.createDataFormat().getFormat(xlsFile.getDateFmt())); // 日期显示格式

		// 金额单元格样式
		moneyStyle = wb.createCellStyle();
		moneyStyle.setBorderTop(HSSFCellStyle.BORDER_THIN); // 上边框
		moneyStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		moneyStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN); // 左边框
		moneyStyle.setBorderRight(HSSFCellStyle.BORDER_THIN); // 右边框
		moneyStyle.setFont(dataFont);
		moneyStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat(xlsFile.getMoneyFmt())); // 金额显示格式

		// 百分比单元格样式
		percentStyle = wb.createCellStyle();
		percentStyle.setBorderTop(HSSFCellStyle.BORDER_THIN); // 上边框
		percentStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		percentStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN); // 左边框
		percentStyle.setBorderRight(HSSFCellStyle.BORDER_THIN); // 右边框
		percentStyle.setFont(dataFont);
		percentStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat(xlsFile.getPercnetFmt())); // 百分比显示格式

		// dateFmt = xlsFile.getDateFmt(); // 日期时间显示格式

		// 总计上的金额单元格样式
		totalNumStyle = wb.createCellStyle();
		totalNumStyle.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);// 前景色LIGHT_YELLOW
		totalNumStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//		totalNumStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT); // 水平居右
		totalNumStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
		totalNumStyle.setBorderTop(HSSFCellStyle.BORDER_THIN); // 上边框
		totalNumStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		totalNumStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN); // 左边框
		totalNumStyle.setBorderRight(HSSFCellStyle.BORDER_THIN); // 右边框
		titleFont.setFontName("Courier New"); // 字体名称
		titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 宽度
		totalNumStyle.setFont(titleFont);
		totalNumStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat(xlsFile.getMoneyFmt())); // 金额显示格式
	}

	/**
	 * 导出XLS文件
	 * 
	 * @param response
	 *            HttpServletResponse对象
	 * @param xlsFile
	 *            XLS文件对象
	 */
	public static void export(HttpServletResponse response, HttpServletRequest request, XLSFile xlsFile) {
		if (response == null || xlsFile == null || xlsFile.getSheets() == null || xlsFile.getSheets().isEmpty()) {
			StringBuilder msg = new StringBuilder("HttpServletResponse is null or XlsFile illegal:");
			msg.append("HttpServletResponse:").append(response == null ? "NULL;" : "NOT NULL;");
			msg.append("XLSFile:").append(xlsFile);
			throw new RuntimeException(msg.toString());
		}

		init(xlsFile); // 初始化
		OutputStream os = null;
		try {
			String filename = FileNameEncoder.encode(xlsFile.getFileName() + ".xls", request.getHeader("user-agent"));
			response.addHeader("Content-Disposition", "attachment;filename=" + filename);

			response.setContentType(xlsFile.CONTENT_TYPE);

			// 输出流
			os = response.getOutputStream();

			List<XLSSheet> sheets = xlsFile.getSheets();
			for (int sheetIndex = 0; sheetIndex < sheets.size(); sheetIndex++) {
				xlsSheet = sheets.get(sheetIndex);

				// 创建新表格,如重名则在原名后加下标
				if (wb.getSheet(xlsSheet.getName()) == null) {
					sheet = wb.createSheet(xlsSheet.getName());
				} else {
					sheet = wb.createSheet(xlsSheet.getName() + "(" + sheetIndex + ")");
				}

				// 表格标题描述,首行,根据sheet列数合并行单元格
				if (xlsSheet.hasDesc()) {
					sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, xlsSheet.maxCell())); // 合并单元格
					row = sheet.createRow(0);// 创建行
					row.setHeightInPoints(40);

					cell = row.createCell(0);// 创建单元格
					// 设置默认标题样式
					setRegionStyle(sheet, defaultDescStyle, 0, 0, 0, xlsSheet.maxCell());
					cell.setCellValue(xlsSheet.getDesc()); // 表格标题(描述)
				}

				if (xlsSheet.hasGroupHeaders()) {
					int rowIndex = xlsSheet.hasDesc() ? 1 : 0;
					List<Object[][]> groups = xlsSheet.getGroupHeaders();
					for (Object[][] group : groups) {
						row = sheet.createRow(rowIndex);// 创建行
						row.setHeightInPoints(18);
						for (int i = 0; i < group.length; i++) {
							Object[] item = group[i];
							// 合并单元格
							sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, Integer.parseInt(item[1].toString()) - 1, Integer.parseInt(item[2].toString()) - 1));
							// 创建单元格
							cell = row.createCell(Integer.parseInt(item[1].toString()) - 1);
							// 设置样式
							setRegionStyle(sheet, titleStyle, rowIndex, rowIndex, Integer.parseInt(item[1].toString()) - 1, Integer.parseInt(item[2].toString()) - 1);
							cell.setCellValue(item[0].toString());
						}
						rowIndex++;
					}
				}

				int position = xlsSheet.getPosition(); // 数据行偏移值(内容首行位置)

				// 列头
				if (xlsSheet.hasTitles()) {
					row = sheet.createRow(position - 1); // 创建行,位置:内容首行位置-1
					row.setHeightInPoints(18);
					for (int titleIndex = 0; titleIndex < xlsSheet.getTitles().length; titleIndex++) {
						cell = row.createCell(titleIndex); // 创建单元格
						cell.setCellStyle(titleStyle); // 设置样式
						cell.setCellValue(xlsSheet.getTitles()[titleIndex]); // 设值
					}
				}

				// 内容 (数据)
				Object[] rowData = null; // 行原始数据
				Object cellData = null; // 单元格原始数据
				for (int rowNum = 0; rowNum < xlsSheet.getData().size(); rowNum++) {
					rowData = xlsSheet.getData().get(rowNum); // 行原始数据
					row = sheet.createRow(rowNum + position); // 创建行

					for (int cellNum = 0; cellNum < xlsSheet.getCellSize(); cellNum++) {
						cell = row.createCell(cellNum); // 建立单元格
//						if (xlsSheet.isText(cellNum)) {
//							cell.setCellStyle(textStyle);
//						} else {
							cell.setCellStyle(dataStyle); // 设置样式
//						}
						cellData = rowData[cellNum];
						if (cellData != null) { // 20111116
							if (isNumeric(cellData.toString())) { // 数值
								cell.setCellValue(Double.valueOf(cellData.toString()));
								cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
								if (xlsSheet.isMoney(cellNum)) { // 判断该列是否金额
									cell.setCellStyle(moneyStyle);// 金额样式
								} else if (xlsSheet.isPercent(cellNum)) {
									cell.setCellStyle(percentStyle);// 百分比样式
								}
							} else if (cellData instanceof Date) { // 日期
								cell.setCellValue((Date) cellData);
								cell.setCellStyle(dateStyle);
							} else { // 其他数据类型,主要为字符串
								cell.setCellValue(cellData.toString());
							}
						}
					}
				}

				// 设置函数
				for (XLSFormula formula : xlsSheet.getFormulas()) {
					row = sheet.getRow(formula.getRow());

					// 如行不存在则创建新行
					if (row == null) {
						row = sheet.createRow(formula.getRow());
						for (int i = 0; i < xlsSheet.getCellSize(); i++) {
							cell = row.createCell(i);
							cell.setCellStyle(titleStyle); // 设置样式
						}
					}

					cell = row.createCell(formula.getCell()); // 创建单元格
					if (xlsSheet.isMoney(formula.getCell())) { // 判断该列是否金额
						cell.setCellStyle(totalNumStyle); // 设置样式
					} else {
						cell.setCellStyle(titleStyle); // 设置样式
					}

					if (formula.isText()) { // 判断是否简单文本
						cell.setCellValue(formula.getFormula());
					} else {
						cell.setCellFormula(formula.getFormula());
						cell = evaluator.evaluateInCell(cell); // 该语句可使函数即刻生效,如去除该语句,则导出后对应的值为0
					}
				}

				// 设置列宽,如为设置参数则采用自动列宽
				if (xlsSheet.hasCellWeights()) {
					short[] weights = xlsSheet.getCellWeights(); // 列宽数组

					// 需处理列宽数组长度与总列数不等的情况

					int loop = weights.length < xlsSheet.getCellSize() ? weights.length : xlsSheet.getCellSize();
					for (int cellNum = 0; cellNum < loop; cellNum++) {
						if (weights[cellNum] > 0) {
							sheet.setColumnWidth((short) cellNum, (short) 35.7 * weights[cellNum]);
						} else {
							sheet.autoSizeColumn(cellNum);
						}
					}
					for (int cellNum = loop; cellNum < xlsSheet.getCellSize(); cellNum++) {
						sheet.autoSizeColumn(cellNum);
					}
				} else { // 自动列宽
					for (int cellNum = 0; cellNum < xlsSheet.getCellSize(); cellNum++) {
						sheet.autoSizeColumn(cellNum);
					}
				}

			}

			wb.write(os);
		} catch (Exception e) {
			throw new RuntimeException("导出Excel文档错误!", e);
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					throw new RuntimeException("关闭输出流失败!", e);
				}
			}
		}

	}
	/**
	 * 导出XLS文件保存服务器的文件夹中
	 * DESC 电销销售用户池导出报表 连接超时 处理 专用方法
	 *            HttpServletResponse对象
	 * @param xlsFile
	 *            XLS文件对象
	 */
	public static void exportSaveFile(String path,String fileName, XLSFile xlsFile) {
		if (fileName == null || xlsFile == null || xlsFile.getSheets() == null || xlsFile.getSheets().isEmpty()) {
			StringBuilder msg = new StringBuilder("fileName is null or XlsFile illegal:");
			msg.append("fileName:").append(fileName == null ? "NULL;" : "NOT NULL;");
			msg.append("XLSFile:").append(xlsFile);
			throw new RuntimeException(msg.toString());
		}

		init(xlsFile); // 初始化
		FileOutputStream os = null;
		try {
//			fileName = FileNameEncoder.encode(xlsFile.getFileName() + ".xls", "GBK");
//			fileName = xlsFile.getFileName() + ".xls";
			// 写入文件
			if (!new File(path).exists()) {// 判断文件夹是否存在，不存在创建
				new File(path).mkdirs();
			}
			File file = new File(path+"/"+fileName);// 判断文件是否存在，不存在创建
			if(!file.isFile()){
				file.createNewFile();
			}
			
			os = new FileOutputStream(file);
			List<XLSSheet> sheets = xlsFile.getSheets();
			for (int sheetIndex = 0; sheetIndex < sheets.size(); sheetIndex++) {
				xlsSheet = sheets.get(sheetIndex);
				// 创建新表格,如重名则在原名后加下标
				if (wb.getSheet(xlsSheet.getName()) == null) {
					sheet = wb.createSheet(xlsSheet.getName());
				} else {
					sheet = wb.createSheet(xlsSheet.getName() + "(" + sheetIndex + ")");
				}

				// 表格标题描述,首行,根据sheet列数合并行单元格
				if (xlsSheet.hasDesc()) {
					sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, xlsSheet.maxCell())); // 合并单元格
					row = sheet.createRow(0);// 创建行
					row.setHeightInPoints(40);

					cell = row.createCell(0);// 创建单元格
					// 设置默认标题样式
					setRegionStyle(sheet, defaultDescStyle, 0, 0, 0, xlsSheet.maxCell());
					cell.setCellValue(xlsSheet.getDesc()); // 表格标题(描述)
				}

				if (xlsSheet.hasGroupHeaders()) {
					int rowIndex = xlsSheet.hasDesc() ? 1 : 0;
					List<Object[][]> groups = xlsSheet.getGroupHeaders();
					for (Object[][] group : groups) {
						row = sheet.createRow(rowIndex);// 创建行
						row.setHeightInPoints(18);
						for (int i = 0; i < group.length; i++) {
							Object[] item = group[i];
							// 合并单元格
							sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, Integer.parseInt(item[1].toString()) - 1, Integer.parseInt(item[2].toString()) - 1));
							// 创建单元格
							cell = row.createCell(Integer.parseInt(item[1].toString()) - 1);
							// 设置样式
							setRegionStyle(sheet, titleStyle, rowIndex, rowIndex, Integer.parseInt(item[1].toString()) - 1, Integer.parseInt(item[2].toString()) - 1);
							cell.setCellValue(item[0].toString());
						}
						rowIndex++;
					}
				}

				int position = xlsSheet.getPosition(); // 数据行偏移值(内容首行位置)

				// 列头
				if (xlsSheet.hasTitles()) {
					row = sheet.createRow(position - 1); // 创建行,位置:内容首行位置-1
					row.setHeightInPoints(18);
					for (int titleIndex = 0; titleIndex < xlsSheet.getTitles().length; titleIndex++) {
						cell = row.createCell(titleIndex); // 创建单元格
						cell.setCellStyle(titleStyle); // 设置样式
						cell.setCellValue(xlsSheet.getTitles()[titleIndex]); // 设值
					}
				}

				// 内容 (数据)
				Object[] rowData = null; // 行原始数据
				Object cellData = null; // 单元格原始数据
				for (int rowNum = 0; rowNum < xlsSheet.getData().size(); rowNum++) {
					rowData = xlsSheet.getData().get(rowNum); // 行原始数据
					row = sheet.createRow(rowNum + position); // 创建行

					for (int cellNum = 0; cellNum < xlsSheet.getCellSize(); cellNum++) {
						cell = row.createCell(cellNum); // 建立单元格
//						if (xlsSheet.isText(cellNum)) {
//							cell.setCellStyle(textStyle);
//						} else {
							cell.setCellStyle(dataStyle); // 设置样式
//						}
						cellData = rowData[cellNum];
						if (cellData != null) { // 20111116
							if (isNumeric(cellData.toString())) { // 数值
								cell.setCellValue(Double.valueOf(cellData.toString()));
								cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
								if (xlsSheet.isMoney(cellNum)) { // 判断该列是否金额
									cell.setCellStyle(moneyStyle);// 金额样式
								} else if (xlsSheet.isPercent(cellNum)) {
									cell.setCellStyle(percentStyle);// 百分比样式
								}
							} else if (cellData instanceof Date) { // 日期
								cell.setCellValue((Date) cellData);
								cell.setCellStyle(dateStyle);
							} else { // 其他数据类型,主要为字符串
								cell.setCellValue(cellData.toString());
							}
						}
					}
				}

				// 设置函数
				for (XLSFormula formula : xlsSheet.getFormulas()) {
					row = sheet.getRow(formula.getRow());
					// 如行不存在则创建新行
					if (row == null) {
						row = sheet.createRow(formula.getRow());
						for (int i = 0; i < xlsSheet.getCellSize(); i++) {
							cell = row.createCell(i);
							cell.setCellStyle(titleStyle); // 设置样式
						}
					}

					cell = row.createCell(formula.getCell()); // 创建单元格
					if (xlsSheet.isMoney(formula.getCell())) { // 判断该列是否金额
						cell.setCellStyle(totalNumStyle); // 设置样式
					} else {
						cell.setCellStyle(titleStyle); // 设置样式
					}

					if (formula.isText()) { // 判断是否简单文本
						cell.setCellValue(formula.getFormula());
					} else {
						cell.setCellFormula(formula.getFormula());
						cell = evaluator.evaluateInCell(cell); // 该语句可使函数即刻生效,如去除该语句,则导出后对应的值为0
					}
				}

				// 设置列宽,如为设置参数则采用自动列宽
				if (xlsSheet.hasCellWeights()) {
					short[] weights = xlsSheet.getCellWeights(); // 列宽数组

					// 需处理列宽数组长度与总列数不等的情况

					int loop = weights.length < xlsSheet.getCellSize() ? weights.length : xlsSheet.getCellSize();
					for (int cellNum = 0; cellNum < loop; cellNum++) {
						if (weights[cellNum] > 0) {
							sheet.setColumnWidth((short) cellNum, (short) 35.7 * weights[cellNum]);
						} else {
							sheet.autoSizeColumn(cellNum);
						}
					}
					for (int cellNum = loop; cellNum < xlsSheet.getCellSize(); cellNum++) {
						sheet.autoSizeColumn(cellNum);
					}
				} else { // 自动列宽
					for (int cellNum = 0; cellNum < xlsSheet.getCellSize(); cellNum++) {
						sheet.autoSizeColumn(cellNum);
					}
				}

			}

			wb.write(os);
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("写入Excel文档错误!", e);
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					throw new RuntimeException("关闭输出流失败!", e);
				}
			}
		}

	}
	/**
	 * 判断一个字符串是否为数值
	 * 
	 * @param str
	 *            字符串
	 * @return boolean
	 */
	private static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("^[+\\-]?((\\d*\\.\\d+)|(\\d+))$");
		Matcher matcher = pattern.matcher(str.trim());
		return matcher.matches();
	}

	private static void setRegionStyle(HSSFSheet sheet, HSSFCellStyle cs, int fromRow, int toRow, int fromCell, int toCell) {
		for (int i = fromRow; i <= toRow; i++) {
			HSSFRow row = HSSFCellUtil.getRow(i, sheet);
			for (int j = fromCell; j <= toCell; j++) {
				HSSFCell cell = HSSFCellUtil.getCell(row, (short) j);
				cell.setCellStyle(cs);
			}
		}
	}

	/**
	 * 下载文件
	 * 
	 * @param response
	 *            HttpServletResponse实例
	 * @param file
	 *            文件绝对路径
	 * @param fileName
	 *            保存默认文件名
	 */
	public static void download(HttpServletResponse response, String file, String fileName) {
		try {
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			OutputStream fos = null;
			InputStream fis = null;

			File uploadFile = new File(file);
			fis = new FileInputStream(uploadFile);
			bis = new BufferedInputStream(fis);
			fos = response.getOutputStream();
			bos = new BufferedOutputStream(fos);
			response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
			int bytesRead = 0;

			byte[] buffer = new byte[8192];
			while ((bytesRead = bis.read(buffer, 0, 8192)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}

			bos.flush();
			fis.close();
			bis.close();
			fos.close();
			bos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
