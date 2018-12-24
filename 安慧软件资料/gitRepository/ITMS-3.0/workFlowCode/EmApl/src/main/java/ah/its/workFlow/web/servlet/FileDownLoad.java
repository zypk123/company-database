package ah.its.workFlow.web.servlet;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.ServletUtils;


@WebServlet("/fileDirs/*")
public class FileDownLoad extends HttpServlet {
	
	/**
	 * @Description: Ù–‘
	 */
	private static final long serialVersionUID = 3181090730125190080L;
	

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//
		String filePath = request.getRequestURI();
		String contentPath = request.getContextPath();
		filePath=filePath.replace(contentPath, "");
		String temp = filePath.substring(filePath.lastIndexOf("/")+1,filePath.length());
		String[]  strs= temp.split("\\.");
		String isofilename = strs[0];
		if(isofilename.contains("%")){
			isofilename = URLDecoder.decode(isofilename,"UTF-8");
		}
		if("PNG".equals(strs[1].toUpperCase())||"JPG".equals(strs[1].toUpperCase())
				||"BMP".equals(strs[1].toUpperCase())||"GIF".equals(strs[1].toUpperCase())||"JPEG".equals(strs[1].toUpperCase())){
			response.setHeader("Content-Type","image/jped");
		}else{
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ isofilename+"."+strs[1]);
		}
		filePath = filePath.substring(0,filePath.lastIndexOf("/"))+"/"+isofilename+"."+strs[1];
		ServletOutputStream out = null;
		try {
			out = response.getOutputStream();
			ServletUtils.returnFile(getServletContext().getRealPath("")+"/"+filePath, out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != out) {
				out.close();
			}
		}
	}
}
