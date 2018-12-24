package cy.its.platform.common.utils;

import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * @author lilei
 * @company 安徽超远
 * 日记代理类，系统全局设置
 * 单例模式 实现全局统一
 */
public class Log4jFactoryProxy {
   
   private  Logger log;
   
   private volatile static Log4jFactoryProxy log4jFactoryProxy;  
   
	/**
	 * @param classInfo 打印日志类
	 * 
	 */
	private Log4jFactoryProxy(Class classInfo){
		   log  = Logger.getLogger(classInfo);
	}
   
   /**
 * @param classinfo
 * @return 当前类实例，全局唯一
 * 实现同步控制
 */
public static Log4jFactoryProxy getSingleton(Class classinfo) {  
	    if (log4jFactoryProxy == null) {  
	        synchronized (Log4jFactoryProxy.class) {  
		        if (log4jFactoryProxy == null) {  
		        	log4jFactoryProxy = new Log4jFactoryProxy(classinfo);  
		        }  
	        }  
	    }  
	    return log4jFactoryProxy;  
   } 

	 /**
	 * @param message
	 * 转代理info方法
	 */
	public  void info(Object  message){
	   log.info(message);
   }
	/**
	* @param message
	* 转代理debug方法
	*/
   public  void debug(Object  message){
	   log.debug(message);
   }
	/**
	* @param message
	* 转代理error方法
	*/
   public  void error(Object  message){
	   log.error(message);
   }


	public void error(String message, SQLException e) {
		 log.error(message,e);
	}
}
