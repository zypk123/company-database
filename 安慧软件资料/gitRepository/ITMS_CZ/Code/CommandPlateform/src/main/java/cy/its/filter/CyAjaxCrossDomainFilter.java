package cy.its.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

import cy.its.web.GlobalProerty;

/**
* @Title: CyAjaxCrossDomainFilter.java 
* @Package cy.its.filter 
* @Description:1、service开头的访问服务，拦截用户请求，根据访问的模块，
* 获取到该模块是不是分布式部署，如果是则直接代理访问服务并返回数据
* @author lil@cychina.cn
* @date 2015年8月14日 下午1:58:49
* @version V1.0   
 */
@WebFilter(filterName="cyAjaxCrossDomainFilter",urlPatterns="/service/*")
public class CyAjaxCrossDomainFilter  implements Filter {
	
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 * 验证该用户是否可以访问服务
	 */
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		
		//开发模式没有代理，也不需要处理
		if(GlobalProerty.getGlobalProerty().model.equals("develop")){
			
			chain.doFilter(request, response);
			
		}else{
			//根据全局配置信息，模块是否分布式部署
			String url =((HttpServletRequest)request).getServletPath();
			String temp  = url.replace("/service/", "");
			String  urlNameSpace = url.split("/")[0];
			if(StringUtils.isEmpty(GlobalProerty.getGlobalProerty().routeMap.get(urlNameSpace))){//不代理
				chain.doFilter(request, response);
			}else{//代理
				String ipProject = (String) GlobalProerty.getGlobalProerty().routeMap.get(urlNameSpace);
				//开始代理
				doProxy((HttpServletRequest)request,response,ipProject);
				
			}
		}
		   
	}
	
	/**
	 * @param request
	 * @param response
	 * @param url
	 * @throws ServletException
	 * @throws IOException
	 * 转发URL请求，并返回数据对象
	 */
	protected void doProxy(HttpServletRequest request, ServletResponse response,String  ipPorject) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=UTF-8");
        PrintWriter out=response.getWriter();
        StringBuffer params=new StringBuffer();
        String serviceUrl=request.getServletPath();
        //所有参数都放到URL里面去
        params.append(request.getQueryString());
        // 使用POST方式向目的服务器发送请求
        URL connect = new URL("http://"+ipPorject+"/"+serviceUrl);
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

	public void destroy() {
		
	}
	
	public void init(FilterConfig arg0) throws ServletException {
		
	}
}
