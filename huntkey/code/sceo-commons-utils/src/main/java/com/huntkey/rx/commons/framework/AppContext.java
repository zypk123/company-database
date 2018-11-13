
package com.huntkey.rx.commons.framework;

import org.springframework.context.ApplicationContext;

public class AppContext {

	private static ApplicationContext applicationContext;  
	  
    //获取上下文  
    public static ApplicationContext getApplicationContext() {  
        return applicationContext;  
    }  
  
    //设置上下文  
    public static void setApplicationContext(ApplicationContext applicationContext) {  
    	AppContext.applicationContext = applicationContext;  
    }  
  
    //通过名字获取上下文中的bean  
    public static Object getBean(String name){  
        return applicationContext.getBean(name);  
    }  
      
    //通过类型获取上下文中的bean  
    public static Object getBean(Class<?> requiredType){  
        return applicationContext.getBean(requiredType);  
    }  
}