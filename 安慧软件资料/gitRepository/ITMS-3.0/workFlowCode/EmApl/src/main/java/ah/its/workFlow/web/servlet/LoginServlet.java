package ah.its.workFlow.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.fastjson.JSONObject;

import ah.its.workFlow.util.MD5Util;
import ah.its.wrokflow.action.SysUserActionI;
import ah.its.wrokflow.domain.Impl.SysUserDomainImpl;
import ah.its.wrokflow.repository.SysUserRepositoryI;

/**
* @Title: LoginServlet.java 
* @Package ah.its.workFlow.web.servlet 
* @Description:处理登录中所有的问题
* @author lil@cychina.cn
* @date 2016年4月5日 上午10:12:11 
* @version V1.0 
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	/**
	 * @Description:属性
	 */
	private static final long serialVersionUID = 3841675807862073145L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String loginName = request.getParameter("loginName");
		String password = request.getParameter("pwd");
		try {
			password  = MD5Util.md5(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		//验证登录，登录成功后，信息写入cookie，作为全局的缓存
		ApplicationContext springBeanFactory = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		SysUserDomainImpl  sysUserDomainImpl = (SysUserDomainImpl) springBeanFactory.getBean("sysUserDomainImpl");
		Map  map = sysUserDomainImpl.queryUserShipById(loginName, password);
		//如果验证通过，将用户信息写入cookie
		if(map.get("status").equals("1")){
	    	 //写入客户端的cookie中
			 JSONObject  obj = (JSONObject) JSONObject.parse(JSONObject.toJSONString(map));
			 JSONObject  user = obj.getJSONObject("user");
			 //缓存中放入，用户名、部门ID，还有团片服务器地址
	    	 Cookie cookie = new Cookie("loginName",loginName);
	    	 Cookie cookieName = new Cookie("userName",URLEncoder.encode(user.getString("first"),"utf-8"));
	    	 Cookie cookie1 = new Cookie("groupId",user.getString("groupId"));
	    	 Cookie cookie2 = new Cookie("servieImg",GlobalProerty.getGlobalProerty().service_img);
	    	 response.addCookie(cookie);
	    	 response.addCookie(cookie1);
	    	 response.addCookie(cookie2);
	    	 response.addCookie(cookieName);
	     }
	   	 //AJAX方式返回数据
	   	 response.setContentType("application/x-json;charset=UTF-8");
	   	 PrintWriter out = response.getWriter();
	   	 out.write(JSONObject.toJSONString(map));//注意这里向jsp输出的流，在script中的截获方法
	   	 out.flush();  
	   	 out.close();
   	 
	}
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}
	
	
}
