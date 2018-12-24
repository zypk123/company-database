package cy.its.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import cy.its.platform.common.utils.MD5Util;
/**
 * @author lilei
 * 处理登陆的    SERVLET
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	private ObjectMapper  mapper  = new ObjectMapper();
	/**
	 *
	 */
	private static final long serialVersionUID = -34186853386310955L;

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("loginName");
		String password = request.getParameter("password");
		String loginType = request.getParameter("loginType");
		String sessionId="";
		/**
		 * 采用以前方式，用户登录后
		 */
		if("1".equals(loginType)){
			//标志为1时，查询角色信息
			processUrlData("authentication/getAllRoleByStaffCode",request,response,"username",name);//请求获取角色服务
		}else if("2".equals(loginType)){
			//登出
			//获取当前用户token
			sessionId= getTokenValue(request);
			if(!"".equals(sessionId)){
				processUrlData("authentication/loginOut",request,response,"sessionId",sessionId);//请求获取角色服务
			}
		}else if("3".equals(loginType)){
			//刷新过期时间
			sessionId= getTokenValue(request);
			if(!"".equals(sessionId)){
				processUrlData("authentication/reFreshToken",request,response,"sessionId",sessionId);//请求获取角色服务
			}
		
		}else if("4".equals(loginType)){
			//统计在线用户数
			processUrlData("authentication/onlineUsers",request,response,"u","1");//请求获取角色服务
		}else{//登陆信息
			String role = request.getParameter("role");
			Map mapInfo = new HashMap();
			mapInfo.put("role", role);
			mapInfo.put("username", name);
			//请求服务的安全检查，登陆啊密码采用MD5方式加密。
			try {
				password  = MD5Util.md5(password);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			mapInfo.put("password", password);
			
			processUrlRequest(name,"authentication/validatelogin",request,response,mapper.writeValueAsString(mapInfo));
	
		}
	}
	
	/** 
	* @Title: getTokenValue 
	* @Description:从用户请求中获取token信息
	* @param @param request
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	private String getTokenValue(HttpServletRequest request) {
		String  sessionId ="";
		Cookie[]  cookies  = request.getCookies();
		for(Cookie cookie:cookies){
			if(null != cookie.getName()){
				if(("LoginToken").equals(cookie.getName())){
					sessionId = cookie.getValue();
				}
			}
		}
		return sessionId;
	}

	private void processUrlData(String serviceUrl,HttpServletRequest request,
			HttpServletResponse response,String paraInfo,String value) throws IOException {
		request.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=UTF-8");
        PrintWriter out=response.getWriter();
        StringBuffer params=new StringBuffer();
        params.append("1=1");
        params.append("&").append(paraInfo).append("=").append(value);
        if(serviceUrl != null){
            // 使用POST方式向目的服务器发送请求
            URL connect = new URL("http://"+GlobalProerty.getGlobalProerty().validate_ip+"/"+GlobalProerty.getGlobalProerty().authorityPName+"/service/"+serviceUrl);
            URLConnection connection = connect.openConnection();
            connection.setDoOutput(true);
            OutputStreamWriter paramout = new OutputStreamWriter(connection.getOutputStream());
            paramout.write(params.toString());
            paramout.flush();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            StringBuffer dataSend=new StringBuffer();
            while((line = reader.readLine()) != null){
                out.append(line);
                dataSend.append(line);
            }
            paramout.close();
            reader.close();
        }
	}

	private void processUrlRequest(String  loginName,String serviceUrl, HttpServletRequest request,
			HttpServletResponse response, String writeValueAsString) throws IOException {
		Map  map = new HashMap<>();
		//远程请求地址到
		String url= "http://"+GlobalProerty.getGlobalProerty().validate_ip+"/"+GlobalProerty.getGlobalProerty().authorityPName+"/service/"+serviceUrl+"?paramInfo="+writeValueAsString;
		URL restServiceURL = new URL(url);
		HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL.openConnection();
		httpConnection.setRequestMethod("GET");
		httpConnection.setRequestProperty("Accept", "application/json");
		if (httpConnection.getResponseCode() != 200) {
            throw new RuntimeException("HTTP GET Request Failed with Error code : "
                          + httpConnection.getResponseCode());
	     }
	     BufferedReader responseBuffer = new BufferedReader(new InputStreamReader((httpConnection.getInputStream()),"UTF-8"));
	     String output;
	     while ((output = responseBuffer.readLine()) != null) {
	           map= mapper.readValue(output, Map.class);
	     }
	     httpConnection.disconnect();
	     
	     if(map.get("data") != null ){
	    	 //写入客户端的cookie中
	    	 Cookie cookie = new Cookie("LoginToken",map.get("info").toString());
	    	 response.addCookie(cookie);
	     }
	     if("2".equals(map.get("status"))){
	 		//用户状态记录
			UserUtil.loginLog(loginName, request);
	     }
    	 //AJAX方式返回数据
    	 response.setContentType("application/x-json;charset=UTF-8");
    	 PrintWriter out = response.getWriter();
    	 out.write(JSONObject.toJSONString(map));//注意这里向jsp输出的流，在script中的截获方法
    	 out.flush();  
    	 out.close();
    	 //response.sendRedirect("index.jsp");
	}



	//重写doPost方法
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		doGet(request, response);
	}
}
