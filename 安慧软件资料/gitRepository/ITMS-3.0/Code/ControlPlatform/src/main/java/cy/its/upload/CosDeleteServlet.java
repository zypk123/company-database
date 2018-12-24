package cy.its.upload;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;

import cy.its.platform.common.utils.SftpUtil;

/**
 * @author 李磊
 *
 */
@WebServlet("/cosDeleteServlet")
public class CosDeleteServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		JSONObject jsonobj = new JSONObject();

		String filePaths = request.getParameter("filePaths");
		if (Strings.isNullOrEmpty(filePaths)) {
			jsonobj.put("info", "请传入正确的文件路径！");
		} else {
			String deleteFile = "";
			if (filePaths.contains(",")) {
				String[] paths = filePaths.split(",");
				for (String path : paths) {
					if (SftpUtil.delete(path)) {
						deleteFile += path + ",";
					}
				}
			} else {
				if (SftpUtil.delete(filePaths)) {
					deleteFile += filePaths + ",";
				}
			}
			jsonobj.put("info", "文件：" + deleteFile + "：已经被删除！");
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(jsonobj.toJSONString());
	}

}
