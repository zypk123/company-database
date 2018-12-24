package cy.its.platform.common.utils;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
* @Title: SpringBeanFactoryUtil.java 
* @Package cy.its.utils 
* @Description: 提供全局访问SPRING bean接口
* @author lil@cychina.cn
* @date 2015年9月8日 下午6:52:51 
* @version V1.0   
 */
public class SpringBeanFactoryUtil {
    
    private  static WebApplicationContext wac;
	
    /** 
    * @Title: setServletContext 
    * @Description: 通过传入的信息初始化WebApplicationContext对象
    * @param @param servletContext    设定文件 
    * @return void    返回类型 
    * @throws 
    */
    public   static void  setServletContext(ServletContext servletContext){
    	wac = WebApplicationContextUtils.getWebApplicationContext(servletContext);
    }
    
    /** 
    * @Title: getBeanFromFactory 
    * @Description: 根据出入的名称获取服务BEAN
    * @param @param serviceName
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws 
    */
    public  static Object getBeanFromFactory(String  serviceName){
    	return wac.getBean(serviceName);
    }
}
