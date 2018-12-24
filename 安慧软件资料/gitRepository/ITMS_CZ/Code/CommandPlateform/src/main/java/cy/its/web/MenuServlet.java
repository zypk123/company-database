package cy.its.web;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cy.its.platform.common.utils.NetworkUtil;
import cy.its.sysLog.domain.OperateMenu;
import cy.its.sysLog.service.OperateMenuLogI;
import cy.its.sysLog.task.LogTaskThroadManager;
import cy.its.sysLog.task.OperateMenuTask;

/**
 * @author lilei
 * 处理登陆的    SERVLET
 */
@WebServlet("/menuServlet")
public class MenuServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String loginName = request.getParameter("currentUserId");
		String menuId = request.getParameter("menuId");
		String menuName = request.getParameter("menuName");
		String orgId = request.getParameter("currentOrgId");
		
		
    	ApplicationContext springBeanFactory = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
    	OperateMenuLogI imp = (OperateMenuLogI) springBeanFactory.getBean("operateMenuLogImpl");
		//获取用户IP地址
		String  ip  =  getIp(request);
		OperateMenu   operateMenu  = new OperateMenu();
		operateMenu.setMenName(menuName);
		operateMenu.setMenuId(menuId);
		operateMenu.setOrgId(orgId);
		operateMenu.setOperIp(ip);
		operateMenu.setOperateTime(new Date());
		operateMenu.setUserId(loginName);
		operateMenu.setRemark("数据录入");
		OperateMenuTask task = new  OperateMenuTask(imp,operateMenu);
		LogTaskThroadManager.addTaskPool(task);
	}
	//重写doPost方法
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		doGet(request, response);
	}
	
	/** 
	* @Title: getIp 
	* @Description: 获取IP
	* @param @param request
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	private String getIp(HttpServletRequest request) {
		String ip = "";
		try {
			ip=NetworkUtil.getIpAddress(request);
		} catch (IOException e) {
			ip = "";
		}
		return ip;
	}

}
