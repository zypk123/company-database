package com.cy.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author lilei
 *界面方法代理类
 *从界面接收请求，查询数据库中存在的服务，然后跨域访问
 *返回JSON对象
 */
@WebFilter(filterName="cyAjaxCrossDomainFilter",urlPatterns="/service/*")
public class CyAjaxCrossDomainFilter  implements Filter {
	
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 * Filter 1、是用户验证，查看用户是否登陆
	 * 2、查看用户角色是否具有功能访问权限
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
	            FilterChain chain) throws IOException, ServletException {
	        String key = request.getParameter("serviceKey");
	        getRequestPayload((HttpServletRequest)request);
	        //第一步做两个操作，1是查询用户是否登陆
	        //2 查看用户是否具有访问服务权限，如果有则返回URL 如果没有则返回 无权限访问界面
	        //从cookie获取token
	        if(key != null){
	        	String  loginToken="";
		        Cookie[] cookies = ((HttpServletRequest) request).getCookies(); 
		        for(Cookie c:cookies){
		        	if(c.getName().equals("LoginToken")){
		        		loginToken = c.getValue().toString();
		        	}
		        }
		        if(loginToken.equals("")){
		        	 request.getRequestDispatcher ("validateError.html").forward(request, response);//登陆信息不存在，请重新登陆
		        }
		        Map map = new HashMap<>();
		        map.put("serviceKey", key);
		        map.put("sessionId", loginToken);
		        map = validateService(map,request,response);
		        String status = (String) map.get("status");
		        if(status.equals("0")){
		        	writeError("登陆信息不存在或过期，请重新登陆",(HttpServletResponse) response);
		        }else if(status.equals("1")){
		        	writeError("你没权限访问该服务！",(HttpServletResponse) response);
		        }else{
		        	doProxy(request , response , map.get("serviceUrl").toString());//访问服务返回数据
		        }
	        }else{
	        	chain.doFilter(request, response);
	        }
	}

	private void writeError(String string,HttpServletResponse response) throws IOException {
		response.setStatus(403);
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
    	PrintWriter out=response.getWriter();
    	out.append(string);
	}

	/**
	 * @param map
	 * @param request
	 * @param response
	 * @return
	 */
	private Map validateService(Map map,ServletRequest request, ServletResponse response) {
		ObjectMapper  mapper  = new ObjectMapper();
		try {
			String  servieUrl = "http://192.168.10.33:9001/ac/service/validateService?paramInfo="+mapper.writeValueAsString(map);
			URL restServiceURL = new URL(servieUrl);
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
	
	/**
	 * @param request
	 * @param response
	 * @param url
	 * @throws ServletException
	 * @throws IOException
	 * 转发URL请求，并返回数据对象
	 */
	protected void doProxy(ServletRequest request, ServletResponse response,String serviceUrl) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=UTF-8");
        PrintWriter out=response.getWriter();
        String mac;
        StringBuffer params=new StringBuffer();
        params.append("1=1");
        //所有参数都放到URL里面去
		request.getParameterMap().forEach((id, val) ->  
			{
				try {
					params.append("&").append(id).append("=").append(val[0].toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		);
        if(serviceUrl != null){
            // 使用POST方式向目的服务器发送请求
            URL connect = new URL("http://192.168.10.33:9001/ac/"+serviceUrl);
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
	/**
	 * @param  key
	 * @return resful 接口地址，后面补充
	 */
	private String getUrlBy(String key) {
		return null;
	}
	public void destroy() {
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
	private String getRequestPayload(HttpServletRequest req) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();  
        try(BufferedReader reader = req.getReader();) {  
                 char[]buff = new char[1024];  
                 int len;  
                 while((len = reader.read(buff)) != -1) {  
                          sb.append(buff,0, len);  
                 }  
        }catch (IOException e) {  
                 e.printStackTrace();  
        }  
        return  URLDecoder.decode(sb.toString(),"UTF-8");  
   } 
}
