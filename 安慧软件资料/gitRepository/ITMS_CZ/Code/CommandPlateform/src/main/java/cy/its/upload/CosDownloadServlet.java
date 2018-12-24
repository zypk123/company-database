package cy.its.upload;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.ServletUtils;


/**
* @Title: CosDownloadServlet.java 
* @Package com.cy.upload 
* @Description:多文件上传处理
* @author lil@cychina.cn
* @date 2015年8月13日 上午10:15:01 
* @version V1.0   
 */
@WebServlet("/cosDownloadServlet")
public class CosDownloadServlet extends HttpServlet {
    
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String filePath = "\\FileDir\\";
		String fileName = "2010年10月安排_2010_10_29_04_52_06_178.txt";
		String Originalname = "2010年10月安排.txt";
		String isofilename = new String(Originalname.getBytes("gb2312"),
				"ISO8859-1");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ isofilename);
		ServletOutputStream out = null;
		try {
			out = response.getOutputStream();
			ServletUtils.returnFile(filePath + fileName, out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != out) {
				out.close();
			}
		}
	}

}
