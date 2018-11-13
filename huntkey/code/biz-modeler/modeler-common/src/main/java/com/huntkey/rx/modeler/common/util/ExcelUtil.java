package com.huntkey.rx.modeler.common.util;


import com.huntkey.rx.modeler.common.model.vo.*;
import org.apache.poi.hssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @ClassName: ExcelUtil
 * @Description: Excel解析、生成工具类
 * @author: liangh
 * @date: 2017年4月16日上午8:34:46
 *
 */
public class ExcelUtil {

	private static Logger log = LoggerFactory.getLogger(ExcelUtil.class);

	public static HSSFWorkbook exportEdmClassVOToExcel(HSSFWorkbook wb, List<EdmClassVO> list){
		try{
			wb = getWorkbook_EdmClass(wb,list);
		}catch(Exception e){
			e.printStackTrace();
		}
		return wb;
	}

	public static HSSFWorkbook exportEdmPropertyVOToExcel(HSSFWorkbook wb, List<EdmPropertyVO> list){
		try{
			wb = getWorkbook_EdmProperty(wb,list);
		}catch(Exception e){
			e.printStackTrace();
		}
		return wb;
	}

	public static HSSFWorkbook exportEdmConvoluteVOToExcel(HSSFWorkbook wb, List<EdmConvoluteVO> list){
		try{
			wb = getWorkbook_EdmConvolute(wb,list);
		}catch(Exception e){
			e.printStackTrace();
		}
		return wb;
	}

	public static HSSFWorkbook exportEdmLinkVOToExcel(HSSFWorkbook wb, List<EdmLinkVO> list){
		try{
			wb = getWorkbook_EdmLink(wb,list);
		}catch(Exception e){
			e.printStackTrace();
		}
		return wb;
	}

	public static HSSFWorkbook exportEdmConnectVOToExcel(HSSFWorkbook wb, List<EdmConnectVO> list){
		try{
			wb = getWorkbook_EdmConnect(wb,list);
		}catch(Exception e){
			e.printStackTrace();
		}
		return wb;
	}

	public static HSSFWorkbook exportEdmUnitVOToExcel(HSSFWorkbook wb, List<EdmUnitVO> list){
		try{
			wb = getWorkbook_EdmUnit(wb,list);
		}catch(Exception e){
			e.printStackTrace();
		}
		return wb;
	}

	/*public static HSSFWorkbook exportEdmConstVOToExcel(HSSFWorkbook wb, List<EdmConstVO> list){
		try{
			wb = getWorkbook_EdmConst(wb,list);
		}catch(Exception e){
			e.printStackTrace();
		}
		return wb;
	}*/

	public static HSSFWorkbook exportEdmAttachmentVOToExcel(HSSFWorkbook wb, List<EdmAttachmentVO> list){
		try{
			wb = getWorkbook_EdmAttachment(wb,list);
		}catch(Exception e){
			e.printStackTrace();
		}
		return wb;
	}

	public static HSSFWorkbook exportEdmMethodVOToExcel(HSSFWorkbook wb, List<EdmMethodVO> list){
		try{
			wb = getWorkbook_EdmMethod(wb,list);
		}catch(Exception e){
			e.printStackTrace();
		}
		return wb;
	}

	public static HSSFWorkbook exportEdmIndexVOToExcel(HSSFWorkbook wb, List<EdmIndexVO> list) {
		try{
			wb = getWorkbook_EdmIndex(wb,list);
		}catch(Exception e){
			e.printStackTrace();
		}
		return wb;
	}



	public static void writeExcel(HSSFWorkbook wb, HttpServletResponse response){
		FileOutputStream fos = null;
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
			String fileName = sdf.format(new Date()) + ".xls";
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("utf-8"), "utf-8"));
			OutputStream os = response.getOutputStream();
			wb.write(os);
			os.flush();
			os.close();
		}catch(IOException e){
			e.printStackTrace();
		}finally {
			// 如果fos不是null，才需要close()
			if (fos != null) {
				// 为了保证close()一定会执行，就放到这里了
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static List<EdmClassVO> importEdmClassVOFromExcel(String fileUrl){
		List<EdmClassVO> list = new ArrayList<EdmClassVO>();
		try{
			InputStream instream = new FileInputStream(fileUrl);
			HSSFWorkbook workbook = new HSSFWorkbook(instream);
			HSSFSheet sheet = workbook.getSheet("类定义");
			int sheetSize = sheet.getLastRowNum();
			for(int i=0;i<sheetSize-1;i++){
				EdmClassVO ecv = new EdmClassVO();
				HSSFRow row = sheet.getRow(i+2);
				ecv.setParentName(row.getCell(2).getStringCellValue());
				ecv.setEdmcCode(row.getCell(3).getStringCellValue());
				ecv.setEdmcName(row.getCell(4).getStringCellValue());
				ecv.setEdmcNameEn(row.getCell(5).getStringCellValue());
				double seq = row.getCell(6).getNumericCellValue();
				ecv.setEdmcSeq((int)seq);
				ecv.setEdmcDesc(row.getCell(7).getStringCellValue());
				ecv.setIsDefined((byte)(row.getCell(8).getStringCellValue()=="是"?1:0));
				ecv.setEdmcVer(row.getCell(9).getStringCellValue());
				ecv.setEdmcPrincipal(row.getCell(10).getStringCellValue());
				ecv.setEdmcDesc(row.getCell(11).getStringCellValue());
				list.add(ecv);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public static List<EdmPropertyVO> importEdmPropertyFromExcel(String fileUrl){
		List<EdmPropertyVO> list = new ArrayList<EdmPropertyVO>();
		try{
			InputStream instream = new FileInputStream(fileUrl);
			HSSFWorkbook workbook = new HSSFWorkbook(instream);
			HSSFSheet sheet = workbook.getSheet("类-属性定义");
			int sheetSize = sheet.getLastRowNum();
			for(int i=0;i<sheetSize-1;i++){
				EdmPropertyVO epv = new EdmPropertyVO();
				HSSFRow row = sheet.getRow(i+2);
				epv.setEdmpEdmcName(row.getCell(2).getStringCellValue());
				epv.setEdmpCode(row.getCell(3).getStringCellValue());
				epv.setEdmpName(row.getCell(4).getStringCellValue());
				epv.setEdmpValueName(row.getCell(5).getStringCellValue());
				epv.setEdmpValueSize(row.getCell(6).getStringCellValue());
				epv.setEdmpValueLimit(row.getCell(7).getStringCellValue());
				epv.setEdmpFormula(row.getCell(8).getStringCellValue());
				epv.setEdmpValue(row.getCell(9).getStringCellValue());
				epv.setEdmpEdmmName(row.getCell(10).getStringCellValue());
				epv.setEdmpParentName(row.getCell(11).getStringCellValue());
				epv.setEdmpDesc(row.getCell(12).getStringCellValue());
				double seq = row.getCell(13).getNumericCellValue();
				epv.setEdmpSeq((int)seq);
				epv.setIsDefined((byte)(row.getCell(14).getStringCellValue()=="是"?1:0));
				list.add(epv);
				System.out.println(epv.toString());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public static List<EdmConvoluteVO> importEdmConvoluteVOFromExcel(String fileUrl){
		List<EdmConvoluteVO> list = new ArrayList<EdmConvoluteVO>();
		try{
			InputStream instream = new FileInputStream(fileUrl);
			HSSFWorkbook workbook = new HSSFWorkbook(instream);
			HSSFSheet sheet = workbook.getSheet("属性扩展-卷积");
			int sheetSize = sheet.getLastRowNum();
			for(int i=0;i<sheetSize-1;i++){
				EdmConvoluteVO ecv = new EdmConvoluteVO();
				HSSFRow row = sheet.getRow(i+2);
				ecv.setEdmcName(row.getCell(2).getStringCellValue());
				ecv.setEdmpName(row.getCell(3).getStringCellValue());

				ecv.setEdcoConvoluteFormula(row.getCell(6).getStringCellValue());
				list.add(ecv);
				System.out.println(ecv.toString());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public static List<EdmLinkVO> importEdmLinkVOFromExcel(String fileUrl){
		List<EdmLinkVO> list = new ArrayList<EdmLinkVO>();
		try{
			InputStream instream = new FileInputStream(fileUrl);
			HSSFWorkbook workbook = new HSSFWorkbook(instream);
			HSSFSheet sheet = workbook.getSheet("属性扩展-关联");
			int sheetSize = sheet.getLastRowNum();
			for(int i=0;i<sheetSize-1;i++){
				EdmLinkVO elv = new EdmLinkVO();
				HSSFRow row = sheet.getRow(i+2);
				elv.setEdmcName(row.getCell(2).getStringCellValue());
				elv.setEdmpName(row.getCell(3).getStringCellValue());
				elv.setEdmlEdmcNameLink(row.getCell(4).getStringCellValue());
				elv.setEdmlEdmpNameLink(row.getCell(5).getStringCellValue());
				elv.setEdmlCond(row.getCell(6).getStringCellValue());
				elv.setEdmlFormula(row.getCell(7).getStringCellValue());
				list.add(elv);
				System.out.println(elv.toString());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public static List<EdmConnectVO> importEdmConnectVOFromExcel(String fileUrl){
		List<EdmConnectVO> list = new ArrayList<EdmConnectVO>();
		try{
			InputStream instream = new FileInputStream(fileUrl);
			HSSFWorkbook workbook = new HSSFWorkbook(instream);
			HSSFSheet sheet = workbook.getSheet("属性扩展-联动");
			int sheetSize = sheet.getLastRowNum();
			for(int i=0;i<sheetSize-1;i++){
				EdmConnectVO ecv = new EdmConnectVO();
				HSSFRow row = sheet.getRow(i+2);
				ecv.setEdmcName(row.getCell(2).getStringCellValue());
				ecv.setEdmpName(row.getCell(3).getStringCellValue());
				byte preservable = 0;
				if("否".equals(row.getCell(4).getStringCellValue())){
					preservable = 0;
				}else if("是".equals(row.getCell(4).getStringCellValue())){
					preservable = 1;
				}
				ecv.setEdcnLinkPreservable(preservable);
				if("同步".equals(row.getCell(5).getStringCellValue())){
					ecv.setEdcnUpdateType("sync");
				}else if("异步".equals(row.getCell(5).getStringCellValue())){
					ecv.setEdcnUpdateType("async");
				}else if("定时".equals(row.getCell(5).getStringCellValue())){
					ecv.setEdcnUpdateType("time");
				}else{
					ecv.setEdcnUpdateType("other");
				}
				ecv.setEdcnUpdateTime(row.getCell(6).getStringCellValue());
				list.add(ecv);
				System.out.println(ecv.toString());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public static List<EdmUnitVO> importEdmUnitVOFromExcel(String fileUrl){
		List<EdmUnitVO> list = new ArrayList<EdmUnitVO>();
		try{
			InputStream instream = new FileInputStream(fileUrl);
			HSSFWorkbook workbook = new HSSFWorkbook(instream);
			HSSFSheet sheet = workbook.getSheet("属性扩展-单位");
			int sheetSize = sheet.getLastRowNum();
			for(int i=0;i<sheetSize-1;i++){
				EdmUnitVO euv = new EdmUnitVO();
				HSSFRow row = sheet.getRow(i+2);
				euv.setEdmcName(row.getCell(2).getStringCellValue());
				euv.setEdmpName(row.getCell(3).getStringCellValue());
				//euv.setEdunUnitType(row.getCell(4).getStringCellValue());
				euv.setEdmcQtyName(row.getCell(4).getStringCellValue());
				euv.setEdmpQtyName(row.getCell(5).getStringCellValue());
				list.add(euv);
				System.out.println(euv.toString());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public static List<EdmAttachmentVO> importEdmAttachmentVOFromExcel(String fileUrl) {
		List<EdmAttachmentVO> list = new ArrayList<EdmAttachmentVO>();
		try {
			InputStream instream = new FileInputStream(fileUrl);
			HSSFWorkbook workbook = new HSSFWorkbook(instream);
			HSSFSheet sheet = workbook.getSheet("类-附件");
			int sheetSize = sheet.getLastRowNum();
			for (int i = 0; i < sheetSize - 1; i++) {
				EdmAttachmentVO eav = new EdmAttachmentVO();
				HSSFRow row = sheet.getRow(i+2);
				eav.setEdmcName(row.getCell(2).getStringCellValue());
				if("网址".equals(row.getCell(3).getStringCellValue())){
					eav.setEdmaType((byte)1);
				}else if("文档".equals(row.getCell(3).getStringCellValue())){
					eav.setEdmaType((byte)2);
				}else{
					eav.setEdmaType((byte)0);
				}
				eav.setEdmaName(row.getCell(4).getStringCellValue());
				eav.setEdmaPath(row.getCell(5).getStringCellValue());
				int seq = Integer.parseInt(row.getCell(6).getStringCellValue());
				eav.setEdmaSeq(seq);
				list.add(eav);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	private static HSSFCellStyle getHSSFCellStyle(HSSFWorkbook wb){
		// 第四步，创建单元格，并设置值表头样式，设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setBorderBottom((byte)1);
		style.setBorderLeft((byte)1);
		style.setBorderRight((byte)1);
		style.setBorderTop((byte)1);
		HSSFFont titleFont = wb.createFont();
		titleFont.setFontName("宋体");
		titleFont.setFontHeightInPoints((short) 10);
		style.setFont(titleFont);
		return style;
	}

	private static void setCell(HSSFRow row, int column,String cellValue,HSSFCellStyle style){
		HSSFCell cell = row.createCell(column);
		cell.setCellValue(cellValue);
		cell.setCellStyle(style);
	}

	private static HSSFWorkbook getWorkbook_EdmClass(HSSFWorkbook wb, List<EdmClassVO> list){
		// 第一步，创建一个webbook，对应一个Excel文件
		//HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("类定义");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 1);
		HSSFCellStyle style = getHSSFCellStyle(wb);

		setCell(row, 1, "序号", style);
		setCell(row, 2, "父类", style);
		setCell(row, 3, "类编码", style);
		setCell(row, 4, "类名称", style);
		setCell(row, 5, "排序", style);
		setCell(row, 6, "描述", style);
		setCell(row, 7, "编码简称", style);
		setCell(row, 8, "是否实体", style);
		setCell(row, 9, "数据库", style);
		setCell(row, 10,"版本", style);
		setCell(row, 11,"负责人", style);

		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow((int) i + 2);
			EdmClassVO ecv = (EdmClassVO) list.get(i);
			// 第四步，创建单元格，并设置值
			String num = Integer.toString(i + 1);
			setCell(row, 1, num, style);
			setCell(row, 2, ecv.getParentName(), style);
			setCell(row, 3, ecv.getEdmcCode(), style);
			setCell(row, 4, ecv.getEdmcName(), style);
			if(StringUtils.isEmpty(ecv.getEdmcSeq())){
				setCell(row,5,"",style);
			}else{
				setCell(row,5, String.valueOf(ecv.getEdmcSeq()), style);
			}
			setCell(row,6, ecv.getEdmcDesc(), style);
			setCell(row, 7, ecv.getEdmcShortName().toUpperCase(), style);
			if(ecv.getIsEntity() == null){
				setCell(row,8,"", style);
			}else if(ecv.getIsEntity() == 1){
				setCell(row,8,"是", style);
			}else{
				setCell(row,8,"否", style);
			}
			setCell(row, 9, ecv.getDatabaseType(), style);
			setCell(row,10, ecv.getEdmcVer(), style);
			setCell(row,11, ecv.getEdmcPrincipal(), style);
		}
		return wb;
	}

	private static HSSFWorkbook getWorkbook_EdmProperty(HSSFWorkbook wb, List<EdmPropertyVO> list){
		// 第一步，创建一个webbook，对应一个Excel文件
		//HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("类-属性定义");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 1);
		HSSFCellStyle style = getHSSFCellStyle(wb);
		setCell(row,1, "序号", style);
		setCell(row,2, "所属类名(基类)", style);
		setCell(row,3, "属性代码", style);
		setCell(row,4, "属性名称", style);
		setCell(row,5, "数据类型", style);
		setCell(row,6, "长度", style);
		setCell(row,7, "属性类别", style);
		setCell(row,8, "父属性名称", style);
		setCell(row,9, "排序", style);
		setCell(row,10,"特征值", style);
		setCell(row,11,"备注", style);
		setCell(row,12,"责任人", style);

		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow((int) i + 2);
			EdmPropertyVO epv = (EdmPropertyVO) list.get(i);
			// 第四步，创建单元格，并设置值
			String num = Integer.toString(i + 1);
			setCell(row,1, num, style);
			setCell(row,2, epv.getEdmpEdmcName(), style);
			setCell(row,3, epv.getEdmpCode(), style);
			setCell(row,4, epv.getEdmpName(), style);
			setCell(row,5, epv.getEdmpDataName(), style);
			setCell(row,6, epv.getEdmpValueSize(), style);
			setCell(row,7, epv.getEdmpValueName(), style);
			setCell(row,8, epv.getEdmpParentName(), style);
			if(StringUtils.isEmpty(epv.getEdmpSeq())){
				setCell(row,9,"", style);
			} else {
				setCell(row,9, epv.getEdmpSeq().toString(), style);
			}
			if(epv.getIsCharacter() == null){
				setCell(row,10,"", style);
			}else if(epv.getIsCharacter() == 0){
				setCell(row,10,"否", style);
			}else if(epv.getIsCharacter() == 1){
				setCell(row,10,"是", style);
			}else{
				setCell(row,10,"", style);
			}
			setCell(row,11, epv.getEdmpDesc(), style);
			setCell(row,12, epv.getAdduser(), style);
		}
		return wb;
	}

	private static HSSFWorkbook getWorkbook_EdmConvolute(HSSFWorkbook wb, List<EdmConvoluteVO> list){
		// 第一步，创建一个webbook，对应一个Excel文件
		//HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("属性扩展-卷积");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 1);
		HSSFCellStyle style = getHSSFCellStyle(wb);
		setCell(row,1,"序号",style);
		setCell(row,2,"所属类名",style);
		setCell(row,3,"属性名称",style);
		setCell(row,4,"卷积公式",style);

		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow((int) i + 2);
			EdmConvoluteVO ecv = (EdmConvoluteVO) list.get(i);
			// 第四步，创建单元格，并设置值
			String num = Integer.toString(i+1);
			setCell(row,1,num,style);
			setCell(row,2,ecv.getEdmcName(),style);
			setCell(row,3,ecv.getEdmpName(),style);
			setCell(row,4,ecv.getEdcoConvoluteFormula(),style);
		}
		return wb;
	}

	private static HSSFWorkbook getWorkbook_EdmLink(HSSFWorkbook wb, List<EdmLinkVO> list) {
		// 第一步，创建一个webbook，对应一个Excel文件
		//HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("属性扩展-关联");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 1);
		HSSFCellStyle style = getHSSFCellStyle(wb);
		setCell(row,1,"序号",style);
		setCell(row,2,"所属类名",style);
		setCell(row,3,"属性名称",style);
		setCell(row,4,"关联类名",style);
		setCell(row,5,"关联属性",style);
		setCell(row,6,"关联定位条件",style);
		setCell(row,7,"关联计算公式",style);

		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow((int) i + 2);
			EdmLinkVO elv = (EdmLinkVO) list.get(i);
			// 第四步，创建单元格，并设置值
			String num = Integer.toString(i+1);
			setCell(row,1,num,style);
			setCell(row,2,elv.getEdmcName(),style);
			setCell(row,3,elv.getEdmpName(),style);
			setCell(row,4,elv.getEdmlEdmcNameLink(),style);
			setCell(row,5,elv.getEdmlEdmpNameLink(),style);
			setCell(row,6,elv.getEdmlCond(),style);
			setCell(row,7,elv.getEdmlFormula(),style);
		}
		return wb;
	}

	private static HSSFWorkbook getWorkbook_EdmConnect(HSSFWorkbook wb, List<EdmConnectVO> list) {
		// 第一步，创建一个webbook，对应一个Excel文件
		//HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("属性扩展-联动");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 1);
		HSSFCellStyle style = getHSSFCellStyle(wb);
		setCell(row,1,"序号",style);
		setCell(row,2,"所属类名",style);
		setCell(row,3,"属性名称",style);
		setCell(row,4,"是否保存联动记录",style);
		setCell(row,5,"联动响应类别",style);
		setCell(row,6,"联动响应时间",style);

		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow((int) i + 2);
			EdmConnectVO ecv = (EdmConnectVO) list.get(i);
			// 第四步，创建单元格，并设置值
			String num = Integer.toString(i+1);
			setCell(row,1,num,style);
			setCell(row,2,ecv.getEdmcName(),style);
			setCell(row,3,ecv.getEdmpName(),style);
			if(ecv.getEdcnLinkPreservable()==null){
				setCell(row,4,"",style);
			}else if(ecv.getEdcnLinkPreservable()==0){
				setCell(row,4,"否",style);
			}else if(ecv.getEdcnLinkPreservable()==1){
				setCell(row,4,"是",style);
			}else{
				setCell(row,4,"",style);
			}
			if(ecv.getEdcnUpdateType()==null){
				setCell(row,5,"",style);
			}else if("sync".equals(ecv.getEdcnUpdateType())){
				setCell(row,5,"同步",style);
			}else if("async".equals(ecv.getEdcnUpdateType())){
				setCell(row,5,"异步",style);
			}else if("time".equals(ecv.getEdcnUpdateType())){
				setCell(row,5,"定时",style);
			}else{
				setCell(row,5,"",style);
			}
			setCell(row,6,ecv.getEdcnUpdateTime(),style);
		}
		return wb;
	}

	private static HSSFWorkbook getWorkbook_EdmUnit(HSSFWorkbook wb, List<EdmUnitVO> list) {
		// 第一步，创建一个webbook，对应一个Excel文件
		//HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("属性扩展-单位");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 1);
		HSSFCellStyle style = getHSSFCellStyle(wb);
		setCell(row,1,"序号",style);
		setCell(row,2,"所属类名",style);
		setCell(row,3,"属性名称",style);
		/*setCell(row,4,"单位类别",style);*/
		setCell(row,4,"对应数值类名",style);
		setCell(row,5,"对应数值属性",style);

		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow((int) i + 2);
			EdmUnitVO euv = (EdmUnitVO) list.get(i);
			// 第四步，创建单元格，并设置值
			String num = Integer.toString(i+1);
			setCell(row,1,num,style);
			setCell(row,2,euv.getEdmcName(),style);
			setCell(row,3,euv.getEdmpName(),style);
			//setCell(row,4,euv.getEdunUnitType(),style);
			setCell(row,4,euv.getEdmcQtyName(),style);
			setCell(row,5,euv.getEdmpQtyName(),style);
		}
		return wb;
	}

	/*private static HSSFWorkbook getWorkbook_EdmConst(HSSFWorkbook wb, List<EdmConstVO> list) {
		// 第一步，创建一个webbook，对应一个Excel文件
		//HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("类-常量属性");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 1);
		HSSFCellStyle style = getHSSFCellStyle(wb);
		setCell(row,1,"序号",style);
		setCell(row,2,"所属类名",style);
		setCell(row,3,"属性代码",style);
		setCell(row,4,"属性名称",style);
		setCell(row,5,"属性值类型",style);
		setCell(row,6,"属性值长度",style);
		setCell(row,7,"属性限值",style);
		setCell(row,8,"属性值",style);
		setCell(row,9,"属性描述",style);
		setCell(row,10,"排序",style);
		setCell(row,11,"是否自定义",style);

		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow((int) i + 2);
			EdmConstVO ecv = (EdmConstVO) list.get(i);
			// 第四步，创建单元格，并设置值
			String num = Integer.toString(i+1);
			setCell(row,1,num,style);
			setCell(row,2,ecv.getEdmcName(),style);
			setCell(row,3,ecv.getEdctCode(),style);
			setCell(row,4,ecv.getEdctName(),style);
			setCell(row,5,ecv.getEdctValueTypeName(),style);
			setCell(row,6,ecv.getEdctValueSize(),style);
			setCell(row,7,ecv.getEdctValueLimit(),style);
			setCell(row,8,ecv.getEdctValue(),style);
			setCell(row,9,ecv.getEdctDesc(),style);
			if(StringUtils.isEmpty(ecv.getEdctSeq())){
				setCell(row,10,"",style);
			}else{
				setCell(row,10,ecv.getEdctSeq().toString(),style);
			}
			if(ecv.getIsDefined()==null){
				setCell(row,11,"",style);
			}else if(ecv.getIsDefined()==0){
				setCell(row,11,"否",style);
			}else if(ecv.getIsDefined()==1){
				setCell(row,11,"是",style);
			}else{
				setCell(row,11,"",style);
			}
		}
		return wb;
	}*/

	private static HSSFWorkbook getWorkbook_EdmAttachment(HSSFWorkbook wb, List<EdmAttachmentVO> list) {
		// 第一步，创建一个webbook，对应一个Excel文件
		//HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("类-附件");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 1);
		HSSFCellStyle style = getHSSFCellStyle(wb);
		setCell(row,1,"序号",style);
		setCell(row,2,"所属类名",style);
		setCell(row,3,"附件类型",style);
		setCell(row,4,"附件名称",style);
		setCell(row,5,"附件路径",style);
		setCell(row,6,"排序",style);
		if(list!=null){
			for (int i = 0; i < list.size(); i++) {
				row = sheet.createRow((int) i + 2);
				EdmAttachmentVO ecv = (EdmAttachmentVO) list.get(i);
				// 第四步，创建单元格，并设置值
				String num = Integer.toString(i+1);
				setCell(row,1,num,style);
				setCell(row,2,ecv.getEdmcName(),style);
				if(ecv.getEdmaType()==null){
					setCell(row,3,"",style);
				}else if(ecv.getEdmaType()==1){
					setCell(row,3,"网址",style);
				}else if(ecv.getEdmaType()==2){
					setCell(row,3,"文档",style);
				}else{
					setCell(row,3,"",style);
				}
				setCell(row,4,ecv.getEdmaName(),style);
				setCell(row,5,ecv.getEdmaPath(),style);
				if(StringUtils.isEmpty(ecv.getEdmaSeq())){
					setCell(row,6,"",style);
				}else{
					setCell(row,6,ecv.getEdmaSeq().toString(),style);
				}
			}
		}
		return wb;
	}

	private static HSSFWorkbook getWorkbook_EdmMethod(HSSFWorkbook wb, List<EdmMethodVO> list) {
		// 第一步，创建一个webbook，对应一个Excel文件
		//HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("类-方法");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 1);
		HSSFCellStyle style = getHSSFCellStyle(wb);
		setCell(row,1,"序号",style);
		setCell(row,2,"所属类名",style);
		setCell(row,3,"方法名称",style);
		setCell(row,4,"方法版本",style);
		setCell(row,5,"方法描述",style);
		setCell(row,6,"程序类别",style);
		setCell(row,7,"方法输入参数",style);
		setCell(row,8,"方法返回值",style);
		setCell(row,9,"是否可覆盖",style);
		setCell(row,10,"是否平台方法",style);
		setCell(row,11,"方法触发条件",style);
		setCell(row,12,"排序",style);
		setCell(row,13,"是否自定义",style);

		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow((int) i + 2);
			EdmMethodVO emv = (EdmMethodVO) list.get(i);
			// 第四步，创建单元格，并设置值
			String num = Integer.toString(i+1);
			setCell(row,1,num,style);
			setCell(row,2,emv.getEdmcName(),style);
			setCell(row,3,emv.getEdmmName(),style);
			setCell(row,4,emv.getEdmmVer(),style);
			setCell(row,5,emv.getEdmmDesc(),style);
			setCell(row,6,emv.getEdmProgramTypeName(),style);
			setCell(row,7,emv.getEdmEdmdInsertArgName(),style);
			setCell(row,8,emv.getEdmEdmdReturnName(),style);
			if(emv.getIsCover()==null){
				setCell(row,9,"",style);
			}else if(emv.getIsCover()==0){
				setCell(row,9,"否",style);
			}else if(emv.getIsCover()==1){
				setCell(row,9,"是",style);
			}else{
				setCell(row,9,"",style);
			}
			/*if(emv.getIsPlatformProgram()==null){
				setCell(row,10,"",style);
			}else if(emv.getIsPlatformProgram()==0){
				setCell(row,10,"否",style);
			}else if(emv.getIsPlatformProgram()==1){
				setCell(row,10,"是",style);
			}else{
				setCell(row,10,"",style);
			}*/
			setCell(row,11,emv.getEdmmTriggerCond(),style);
			if(StringUtils.isEmpty(emv.getEdmmSeq())){
				setCell(row,12,"",style);
			}else{
				setCell(row,12,emv.getEdmmSeq().toString(),style);
			}
			if(emv.getIsDefined()==null) {
				setCell(row,13,"",style);
			}else if(emv.getIsDefined()==0){
				setCell(row,13,"否",style);
			}else if(emv.getIsDefined()==1){
				setCell(row,13,"是",style);
			}else{
				setCell(row,13,"",style);
			}
		}
		return wb;
	}

	private static HSSFWorkbook getWorkbook_EdmIndex(HSSFWorkbook wb, List<EdmIndexVO> list) {

		HSSFSheet sheet = wb.createSheet("类-索引");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 1);
		HSSFCellStyle style = getHSSFCellStyle(wb);
		setCell(row,1,"序号",style);
		setCell(row,2,"类/属性集",style);
		setCell(row,3,"索引名称",style);
		setCell(row,4,"索引类型",style);
		setCell(row,5,"索引键列",style);
		if(list!=null && list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				row = sheet.createRow((int) i + 2);
				EdmIndexVO ecv = (EdmIndexVO) list.get(i);
				// 第四步，创建单元格，并设置值
				String num = Integer.toString(i+1);
				setCell(row,1,num,style);
				setCell(row,2,ecv.getEdmcName(),style);
				setCell(row,3,ecv.getIndexName(),style);
				if(ecv.getIndexType()==null){
					setCell(row,4,"",style);
				}else if("ordinary".equals(ecv.getIndexType())){
					setCell(row,4,"普通索引",style);
				}else if("only".equals(ecv.getIndexType())){
					setCell(row,4,"唯一索引",style);
				}else if("primary".equals(ecv.getIndexType())){
					setCell(row,4,"主键索引",style);
				}else if("combination".equals(ecv.getIndexType())){
					setCell(row,4,"组合索引",style);
				}else{
					setCell(row,4,"",style);
				}
				setCell(row,5,ecv.getIndexPropertyNames(),style);
			}
		}
		return wb;
	}
}
