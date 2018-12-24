package cy.its.upload;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.oreilly.servlet.MultipartRequest;

import cy.its.platform.common.utils.SftpUtil;

/**
 * @Title: CosUploadServlet.java
 * @Package com.cy.upload
 * @Description: 上传SERVLET
 * @author lil@cychina.cn
 * @date 2015年8月13日 下午2:21:53
 * @version V1.0
 */
@WebServlet("/cosUploadServlet")
public class CosUploadServlet extends HttpServlet {

	private static final long serialVersionUID = -9116026245071140150L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		File fileDir = new File(this.getServletContext().getRealPath("/FileDir") + "/"
				+ new SimpleDateFormat("yyyyMMdd").format(new Date()));
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		// 设置上传文件的大小，超过这个大小 将抛出IOException异常，默认大小是20M。
		int inmaxPostSize = 500 * 1024 * 1024;
		MultipartRequest multirequest = null;
		// 上传文件重命名策略
		RenamePolicyCos myRenamePolicyCos = new RenamePolicyCos();
		JSONArray jsonSTR = new JSONArray();
		try {
			// MultipartRequest()有8种构造函数！
			multirequest = new MultipartRequest(request, fileDir.getAbsolutePath(), inmaxPostSize, "UTF-8",
					myRenamePolicyCos); // GBK中文编码模式上传文件
			Enumeration<String> filedFileNames = multirequest.getFileNames();
			String filedName = null;
			if (null != filedFileNames) {
				while (filedFileNames.hasMoreElements()) {
					filedName = filedFileNames.nextElement();// 文件文本框的名称
					// 获取该文件框中上传的文件，即对应到上传到服务器中的文件
					File uploadFile = multirequest.getFile(filedName);
					if (null != uploadFile && uploadFile.length() > 0) {
						JSONObject obj = new JSONObject();
						// String serverPath =
						// GlobalProerty.getGlobalProerty().service_ip + "/"
						// + GlobalProerty.getGlobalProerty().servicePName +
						// "/FileDir/"
						// + uploadFile.getParentFile().getName() + "/" +
						// uploadFile.getName();
						String serverPath = SftpUtil.upload(uploadFile);
						obj.put("url", serverPath);
						jsonSTR.add(obj);
						uploadFile.delete();
						deleteEmptyDir(new File(this.getServletContext().getRealPath("/FileDir")));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(jsonSTR.toJSONString());
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	private boolean deleteEmptyDir(File dirOrFile) {
		boolean deleteSuccess = true;
		if (dirOrFile.isDirectory()) {
			String[] children = dirOrFile.list();
			if (children.length == 0) {
				dirOrFile.delete();
			} else {
				for (int i = 0; i < children.length; i++) {
					if (!deleteEmptyDir(new File(dirOrFile, children[i]))) {
						deleteSuccess = false;
						break;
					}
				}
			}
		} else {
			deleteSuccess = false;
		}
		return deleteSuccess;
	}
}
