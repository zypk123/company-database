package cy.its.violation.rest.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cy.its.violation.domain.model.violation.Violation;
import cy.its.violation.rest.action.impl.FileToZip;

public class ViolationExport {

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private static String fileseparator = File.separator == "/" ? "/" : "\\";

	/**
	 * 对查询到的违法数据生成xml文档并存储在服务器上
	 * 
	 * @param request
	 * @param vioLst
	 * @return
	 */
	public static String CreateFileFromData(HttpServletRequest request, List<Violation> vioLst) {
		String httpFileHref;

		//
		String serverDirectory = request.getSession().getServletContext().getRealPath("/") + fileseparator + "file";
		String exportDirectory = serverDirectory + fileseparator + dateFormat.format(new Date());
		File exportFile = new File(exportDirectory);
		// 如果文件夹布存在则新建
		if (!exportFile.exists()) {
			exportFile.mkdirs();
		}
		//
		vioLst.stream().parallel().forEach(vl -> {
			// 生成XML文件

			XmlExportGen.createXmlByViolation(vl, exportDirectory);
		});
		// 打包文件夹，返回打包文件夹名称
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
		String zipFileName = "违法导出" + format2.format(new Date());
		FileToZip.fileToZip(exportDirectory, serverDirectory, zipFileName);

		httpFileHref = ServerUtil.getFileUrl(request) + "/" + "file" + "/" + zipFileName + ".zip";
		// 删除文件夹
		deleteFile(exportFile);
		return httpFileHref;
	}

	// 删除文件夹及内部文件
	public static void deleteFile(File oldPath) {
		if (oldPath.exists()) {
			if (oldPath.isDirectory()) {
				File[] files = oldPath.listFiles();
				for (File file : files) {
					deleteFile(file);
				}
				oldPath.delete();
			} else {
				oldPath.delete();
			}
		}
	}

}
