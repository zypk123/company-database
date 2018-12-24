package cy.its.violation.rest.util;

import javax.servlet.http.HttpServletRequest;

public class ServerUtil {

	// 获取网络访问地址
	public static String getFileUrl(HttpServletRequest request) {
		String path = request.getRequestURL().toString();
		String projectName = request.getContextPath();
		return path.split(projectName)[0] + projectName;
	}

}
