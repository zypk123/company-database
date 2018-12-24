package ah.its.workFlow.web.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;


@WebServlet("/fileDelete")
public class FileDelete extends HttpServlet {
	
	/**
	 * @Description: Ù–‘
	 */
	private static final long serialVersionUID = -8171146389268636875L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//
		String contextPath = getServletContext().getRealPath("");  
		String filePath = request.getParameter("data");
		if(StringUtils.isNotEmpty(filePath)){
			String[]  strs = filePath.split(",");
			for(int i=0;i<strs.length;i++){
				if(StringUtils.isNotEmpty(strs[i])){
					String str = contextPath+File.separator+strs[i];
			        File  file = new File(str);
			        if(file.exists()){
			        	file.delete();
			        }
				}
			}
		}
	}
}
