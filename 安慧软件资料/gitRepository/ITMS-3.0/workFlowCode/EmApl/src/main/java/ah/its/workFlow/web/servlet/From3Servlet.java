package ah.its.workFlow.web.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
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

/**
 * Servlet implementation class From3Servlet
 */
@WebServlet("/From3Servlet")
public class From3Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ObjectMapper  mapper  = new ObjectMapper();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String loginName=URLEncoder.encode(request.getParameter("loginName"), "UTF-8");
		String token=URLEncoder.encode(request.getParameter("token"),"UTF-8");
		Map map = new HashMap<>();
		map.put("loginName", loginName);
		map.put("token", token);
		//到A包请求授权
		processUrlRequest(loginName,"authentication/validateFrom3",request,response,mapper.writeValueAsString(map));
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
	     //将map信息取出写入cookie
		 JSONObject  object = (JSONObject) JSONObject.parse(JSONObject.toJSONString(map.get("data")));
		 if(object!=null){
			 //缓存中放入，用户名、部门ID，还有团片服务器地址URLEncoder.encode("张三", "UTF-8")
			 Cookie cookie = new Cookie("loginName",object.getString("loginName"));
			 Cookie cookieName = new Cookie("userName",URLEncoder.encode(object.getString("userName"), "UTF-8"));
			 Cookie cookie1 = new Cookie("groupId",object.getString("groupId"));
			 Cookie cookie2 = new Cookie("servieImg",GlobalProerty.getGlobalProerty().service_img);
			 response.addCookie(cookie);
			 response.addCookie(cookie1);
			 response.addCookie(cookie2);
			 response.addCookie(cookieName);

			 response.sendRedirect("index.html");
			 
		 }else{//如果没有登录或者其他的到登录页
			 response.sendRedirect("login.html");
		 }
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
