package com.huntkey.rx.commons.utils.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.huntkey.rx.commons.utils.datetime.DateUtil;

/**
 * 
 * @ClassName: ExcelUtil
 * @Description: Excel解析、生成工具类
 * @author: zhangyu
 * @date: 2017年4月11日上午8:34:46
 *
 */
public class ExcelUtil<T> {

	/**
	 * 解析excel 获取每个sheet中的每行数据
	 * 
	 * @Title: readExcel
	 * @param inputStream
	 *            文件流
	 * @param ignoreRows
	 *            忽略前几行（忽略第一行传1）
	 * @return
	 * @throws IOException
	 * @return List<String[]>
	 * @throws InvalidFormatException
	 */
	public static List<List<String[]>> readExcel(InputStream inputStream, int ignoreRows)
			throws IOException, InvalidFormatException {
		// 返回结果集
		List<List<String[]>> resultList = new ArrayList<List<String[]>>();
		boolean isNotNullRow = false;
		try {
			Workbook workbook = WorkbookFactory.create(inputStream);
			Cell cell = null;
			// 循环excel 获取每个sheet
			for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
				List<String[]> oneSheetList = new ArrayList<String[]>();
				// 根据索引获取sheet
				Sheet sheet = workbook.getSheetAt(sheetIndex);
				// 循环sheet 获取每行数据（从忽略行下一行开始）
				for (int rowIndex = ignoreRows; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
					// 如果该行每个cell的值都为空 则为false
					isNotNullRow = false;
					Row row = sheet.getRow(rowIndex);
					if (row == null)
						continue;
					String[] rowValueArray = new String[row.getLastCellNum()];
					// 获取行中的每列
					for (int columnIndex = 0; columnIndex < row.getLastCellNum(); columnIndex++) {
						cell = row.getCell(columnIndex);
						String cellValue = "";
						// 根据cell类型分别获取值
						if (cell != null) {
							switch (cell.getCellType()) {
							case Cell.CELL_TYPE_STRING:
								cellValue = cell.getStringCellValue();
								break;
							case Cell.CELL_TYPE_NUMERIC:
								if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
									Date date = cell.getDateCellValue();
									if (date != null) {
										cellValue = new SimpleDateFormat("yyyy-MM-dd").format(date);
									}
								} else {
									cellValue = new DecimalFormat("0").format(cell.getNumericCellValue());
								}
								break;
							case Cell.CELL_TYPE_FORMULA:
								// 导入时如果为公式生成的数据则无值
								if (!cell.getStringCellValue().equals("")) {
									cellValue = cell.getStringCellValue();
								} else {
									cellValue = cell.getNumericCellValue() + "";
								}
								break;
							case Cell.CELL_TYPE_BLANK:
								break;
							case Cell.CELL_TYPE_ERROR:
								cellValue = "";
								break;
							case Cell.CELL_TYPE_BOOLEAN:
								cellValue = cell.getBooleanCellValue() == true ? "Y" : "N";
								break;
							default:
								cellValue = "";
								break;
							}
						} else {
							cellValue = "";
						}

						rowValueArray[columnIndex] = cellValue.trim();
						// 有一个cell不为空则为true
						isNotNullRow = isNotNullRow == true ? true
								: (cellValue != null && !"".equals(cellValue.trim()));
					}

					if (isNotNullRow) {
						oneSheetList.add(rowValueArray);
					}
				}
				resultList.add(oneSheetList);
			}

		} catch (InvalidFormatException e) {
			e.printStackTrace();
			throw e;
		}

		return resultList;
	}

	/**
	 * 解析excel 获取每个sheet中的每行数据
	 * 
	 * @Title: readExcel
	 * @param path
	 *            excel路径（包含文件名）
	 * @param ignoreRows
	 *            忽略前几行（忽略第一行传1）
	 * @return List<String[]>
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
	public static List<List<String[]>> readExcel(String path, int ignoreRows)
			throws IOException, InvalidFormatException {

		return readExcel(new FileInputStream(new File(path)), ignoreRows);

	}

	/**
	 * 解析excel 获取每个sheet中的每行数据(默认第一行不取)
	 * 
	 * @Title: readExcel
	 * @param path
	 *            excel路径
	 * @return List<String[]>
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
	public static List<List<String[]>> readExcel(String path) throws IOException, InvalidFormatException {

		return readExcel(path, 1);
	}

	/**
	 * 解析excel 获取每个sheet中的每行数据(默认第一行不取)
	 * 
	 * @Title: readExcel
	 * @param inputStream
	 *            文件流
	 * @return List<String[]>
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
	public static List<List<String[]>> readExcel(InputStream inputStream) throws IOException, InvalidFormatException {

		return readExcel(inputStream, 1);
	}

	/**
	 * 将对象列表数据生成excel(2007) 放入数据流中
	 *
	 * @Title: exportExcel
	 * @param collection
	 *            Collection<T> 要生成excel的数据
	 * @param keys
	 *            String[] collection中泛型类中的属性名
	 * @param header
	 *            表格列名
	 * @param outputStream
	 *            输出流
	 * @param format
	 *            日期格式
	 * @return void
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws IOException
	 * @throws InvocationTargetException
	 */
	public void exportExcel(Collection<T> collection, String[] fieldNameArr, String[] headers,
			OutputStream outputStream, String format)
			throws NoSuchMethodException, IllegalAccessException, IOException, InvocationTargetException {

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet();
		sheet.setDefaultColumnWidth(20);

		// 标题行样式
		XSSFCellStyle cellStyleHeader = workbook.createCellStyle();
		cellStyleHeader.setBorderBottom(XSSFCellStyle.BORDER_THIN);// 底部边框
		cellStyleHeader.setBorderLeft(XSSFCellStyle.BORDER_THIN);// 左边框
		cellStyleHeader.setBorderRight(XSSFCellStyle.BORDER_THIN);// 右边框
		cellStyleHeader.setBorderTop(XSSFCellStyle.BORDER_THIN);// 上部边框
		cellStyleHeader.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 左右居中
		cellStyleHeader.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());// 前景色
		cellStyleHeader.setFillPattern(CellStyle.SOLID_FOREGROUND); // 设置填充样式
		// 生成标题字体
		XSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 11);// 字体大小
		font.setFontName("Arial");// 字体
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);// 粗体
		// 把字体应用到当前的样式
		cellStyleHeader.setFont(font);

		// 数据行样式
		XSSFCellStyle cellStyleData = workbook.createCellStyle();
		cellStyleData.setBorderBottom(XSSFCellStyle.BORDER_THIN);// 底部边框
		cellStyleData.setBorderLeft(XSSFCellStyle.BORDER_THIN);// 左边框
		cellStyleData.setBorderRight(XSSFCellStyle.BORDER_THIN);// 右边框
		cellStyleData.setBorderTop(XSSFCellStyle.BORDER_THIN);// 上部边框
		cellStyleData.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 左右居中
		cellStyleData.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 上下居中
		cellStyleData.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());// 前景色
		cellStyleData.setFillPattern(CellStyle.SOLID_FOREGROUND); // 设置填充样式

		// 生成数据字体
		XSSFFont dataFont = workbook.createFont();
		dataFont.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);// 非粗体
		dataFont.setFontHeightInPoints((short) 10);// 字体大小
		dataFont.setFontName("Arial");// 字体
		// 把字体应用到数据样式
		cellStyleData.setFont(dataFont);
		// 声明一个画图的顶级管理器
		XSSFDrawing patriarch = sheet.createDrawingPatriarch();

		// 表格标题行
		XSSFRow rowHeader = sheet.createRow(0);
		for (int i = 0; i < headers.length; i++) {
			XSSFCell cellHeader = rowHeader.createCell(i);
			cellHeader.setCellStyle(cellStyleHeader);
			cellHeader.setCellValue(headers[i]);
		}

		try {

			int indexRow = 1;
			int indexCell = 0;
			// 循环将数据放入sheet中
			for (T t : collection) {
				XSSFRow dataRow = sheet.createRow(indexRow++);
				indexCell = 0;

				for (String fieldName : fieldNameArr) {

					XSSFCell cellData = dataRow.createCell(indexCell++);
					cellData.setCellStyle(cellStyleData);
					String getFieldName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
					Class<?> classT = t.getClass();
					Method method = classT.getMethod(getFieldName, new Class[] {});
					Object value = method.invoke(t, new Object[] {});
					String valueStr = "";

					if (value instanceof Date) {
						valueStr = DateUtil.parseFormatDate((Date) value, format);
						cellData.setCellValue(valueStr);
					} else if (value instanceof Boolean) {
						valueStr = (Boolean) value ? "Y" : "N";
						cellData.setCellValue(valueStr);
					} else if (value instanceof byte[]) {
						// 有图片时，设置行高为60px;
						dataRow.setHeightInPoints(60);
						// 设置图片所在列宽度为80px,注意这里单位的一个换算
						sheet.setColumnWidth(indexCell, (int) (35.7 * 80));
						byte[] bsValue = (byte[]) value;
						XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0, 1023, 255, (short) 6, indexRow, (short) 6,
								indexRow);
						anchor.setAnchorType(2);
						patriarch.createPicture(anchor, workbook.addPicture(bsValue, XSSFWorkbook.PICTURE_TYPE_JPEG));
					} else {
						valueStr = String.valueOf(value == null ? "" : value);
						cellData.setCellValue(valueStr);
					}
				}

			}
			workbook.write(outputStream);
			outputStream.flush();
			outputStream.close();

		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			throw e;
		} catch (SecurityException e) {
			e.printStackTrace();
			throw e;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw e;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw e;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}

	}

	/**
	 * 将对象列表数据生成excel(2007) 放入数据流中 日期类型输出格式默认为yyyy-MM-dd
	 *
	 * @Title: exportExcel
	 * @param collection
	 *            Collection<T> 要生成excel的数据
	 * @param keys
	 *            String[] collection中泛型的属性名
	 * @param header
	 *            表格列名
	 * @param outputStream
	 *            输出流
	 * @return void
	 * @throws IOException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 */
	public void exportExcel(Collection<T> collection, String[] fieldNameArr, String[] headers,
			OutputStream outputStream)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, IOException {
		exportExcel(collection, fieldNameArr, headers, outputStream, "yyyy-MM-dd");
	}

	/**
	 * 将数据生成excel(2007) 放入数据流中
	 *
	 * @Title: exportExcel
	 * @param dataList
	 *            List<Map<String, String>> 要生成excel的数据
	 * @param keys
	 *            String[] dataList中每个map的key
	 * @param header
	 *            表格列名
	 * @param outputStream
	 *            输出流
	 * @return void
	 * @throws IOException
	 */
	public static void exportExcel(List<Map<String, String>> dataList, String[] keys, String[] headers,
			OutputStream outputStream, Map<String, String> title) throws IOException {

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet();
		sheet.setDefaultColumnWidth(20);
		// 标题样式
		XSSFCellStyle cellStyleTitle = workbook.createCellStyle();
		cellStyleTitle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 上下居中
		// 生成标题字体
		XSSFFont fontTitle = workbook.createFont();
		fontTitle.setFontHeightInPoints((short) 16);// 字体大小
		fontTitle.setFontName("楷体");// 字体
		// 把字体应用到当前的样式
		cellStyleTitle.setFont(fontTitle);

		// 生成标题文字字体
		XSSFFont fontTitleText = workbook.createFont();
		fontTitleText.setFontHeightInPoints((short) 10);// 字体大小
		fontTitleText.setFontName("楷体");// 字体

		// 标题行样式
		XSSFCellStyle cellStyleHeader = workbook.createCellStyle();
		cellStyleHeader.setBorderBottom(XSSFCellStyle.BORDER_THIN);// 底部边框
		cellStyleHeader.setBorderLeft(XSSFCellStyle.BORDER_THIN);// 左边框
		cellStyleHeader.setBorderRight(XSSFCellStyle.BORDER_THIN);// 右边框
		cellStyleHeader.setBorderTop(XSSFCellStyle.BORDER_THIN);// 上部边框
		cellStyleHeader.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 左右居中
		cellStyleHeader.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 上下居中
		cellStyleHeader.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());// 前景色
		cellStyleHeader.setFillPattern(CellStyle.SOLID_FOREGROUND);
		// 生成标题字体
		XSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 11);// 字体大小
		font.setFontName("Arial");// 字体
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);// 粗体
		// 把字体应用到当前的样式
		cellStyleHeader.setFont(font);

		// 数据行样式
		XSSFCellStyle cellStyleData = workbook.createCellStyle();
		cellStyleData.setBorderBottom(XSSFCellStyle.BORDER_THIN);// 底部边框
		cellStyleData.setBorderLeft(XSSFCellStyle.BORDER_THIN);// 左边框
		cellStyleData.setBorderRight(XSSFCellStyle.BORDER_THIN);// 右边框
		cellStyleData.setBorderTop(XSSFCellStyle.BORDER_THIN);// 上部边框
		cellStyleData.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 左右居中
		cellStyleData.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 上下居中
		cellStyleData.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());// 前景色
		cellStyleData.setFillPattern(CellStyle.SOLID_FOREGROUND);

		// 生成数据字体
		XSSFFont dataFont = workbook.createFont();
		dataFont.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);// 非粗体
		dataFont.setFontHeightInPoints((short) 10);// 字体大小
		dataFont.setFontName("Arial");// 字体
		// 把字体应用到数据样式
		cellStyleData.setFont(dataFont);

		// excel表格标题行
		XSSFRow titleRow = sheet.createRow(0);
		for (int i = 0; i < headers.length; i++) {
			XSSFCell cellHeader = titleRow.createCell(i);
			XSSFCellStyle cellStyle = (XSSFCellStyle) cellStyleTitle.clone();
			cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 左右居中
			cellHeader.setCellValue(title.get("title"));
			cellHeader.setCellStyle(cellStyle);
		}
		titleRow.setHeight((short) 1000);
		sheet.addMergedRegion(new CellRangeAddress(0, (short) 0, 0, (short) headers.length - 1));// 指定合并区域

		// excel表格检索条件行
		XSSFRow queryRow = sheet.createRow(1);
		for (int i = 0; i < headers.length; i++) {
			XSSFCell cellHeader = queryRow.createCell(i);
			XSSFCellStyle cellStyleRow = (XSSFCellStyle) cellStyleTitle.clone();
			cellStyleRow.setAlignment(XSSFCellStyle.ALIGN_LEFT);// 左右居左
			// 允许字体换行
			cellStyleRow.setWrapText(true);
			// 把字体应用到当前的样式
			cellStyleRow.setFont(fontTitleText);
			cellHeader.setCellValue(title.get("param"));
			cellHeader.setCellStyle(cellStyleRow);
		}
		queryRow.setHeight((short) 600);
		sheet.addMergedRegion(new CellRangeAddress(1, (short) 1, 0, (short) headers.length - 1));// 指定合并区域

		// 数据标题行
		XSSFRow rowHeader = sheet.createRow(2);
		for (int i = 0; i < headers.length; i++) {
			XSSFCell cellHeader = rowHeader.createCell(i);
			cellHeader.setCellStyle(cellStyleHeader);
			cellHeader.setCellValue(headers[i]);
		}

		int indexRow = 3;
		int indexCell = 0;
		// 循环将数据放入sheet中
		for (Map<String, String> dataMap : dataList) {
			XSSFRow dataRow = sheet.createRow(indexRow++);
			indexCell = 0;
			for (String key : keys) {
				XSSFCell cellData = dataRow.createCell(indexCell++);
				cellData.setCellStyle(cellStyleData);
				cellData.setCellValue(dataMap.get(key) == null ? "" : dataMap.get(key));
			}
		}

		try {
			workbook.write(outputStream);
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}

	}

}
