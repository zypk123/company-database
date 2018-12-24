package com.cy.web.service;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import cy.its.common.duplex.service.WebscoketServiceI;

/**
* @Title: WebscoketServiceFactory.java 
* @Package com.cy.web.service.impl 
* @Description: 服务创建类 
* @author lil@cychina.cn
* @date 2015年10月19日 下午2:20:42 
* @version V1.0   
 */
public class WebscoketServiceFactory{
	
    private static HashMap<String, WebscoketServiceI>  mapService = new HashMap<>();//存放生成的服务类
    
    private volatile static WebscoketServiceFactory webscoketServiceFactory;  
    
    /**
     * 私有的构造器
     */
    private WebscoketServiceFactory (){
    	
    }  
    
	/**
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException  
	* @Title: getServiceBean 
	* @Description: MAP中存在服务类则返回，如果
	* @param @param serviceKey
	* @param @param className
	* @param @return    设定文件 
	* @return WebscoketServiceI    返回类型 
	* @throws 
	*/
	public WebscoketServiceI getServiceBean(String serviceKey,String  className) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		if(mapService.get(serviceKey)== null){
    		Constructor c0=Class.forName(className).getDeclaredConstructor();   
            c0.setAccessible(true);   
            WebscoketServiceI wbsimpl=(WebscoketServiceI)c0.newInstance();
    		mapService.put(serviceKey, wbsimpl);
    		return wbsimpl;
		}else{
			return mapService.get(serviceKey);
		}
	}
    
    public static WebscoketServiceFactory getSingleWebscoketServiceFactory() {  
	    if (webscoketServiceFactory == null) {  
	        synchronized (WebscoketServiceFactory.class) {  
		        if (webscoketServiceFactory == null) {  
		        	webscoketServiceFactory = new WebscoketServiceFactory();  
		        }  
	        }  
	    }  
	    return webscoketServiceFactory;  
	}  

}
