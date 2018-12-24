package cy.its.service.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

/**
* @Title: Log4jConfig.java 
* @Package cy.its.service.surveillance.util 
* @Description:加载配置文件资源信息。
* @author lil@cychina.cn
* @date 2015年10月20日 下午2:07:10 
* @version V1.0   
 */
public class Log4jConfig {
   //属性值
   private  static   String  propertity;
   
   //配置文件路径
   private  static   String  resource;
   
   //配置文件路径
   private  static   String  logName;
   
   
   private  Log4jConfig(){}
   
 /**
 * @param propertity1
 * @param resource1
 * @param logName1
 * 日志初始化
 */
private  Log4jConfig(String  propertity1,String  resource1,String logName1){
	   propertity=propertity1;
	   resource=resource1;
	   logName=logName1;
	   //如果propertity不为空则需要，更改配置文件中日志的路径
		try {
		   PropertyConfigurator.configure(getNewProperty(propertity,resource));
		} catch (IOException e) {
			System.out.println("没找到Log4j配置文件，初始化失败！");
			e.printStackTrace();
		}
    }
   
	private  static Properties getNewProperty(String propertity, String resource) throws IOException {
		OrderedProperties prop = new OrderedProperties();//属性集合对象 
		InputStream fis = new FileInputStream(resource);
		prop.load(fis);// 将属性文件流装载到Properties对象中 
		fis.close();// 关闭流 
		Properties properies  =  new Properties();
		properies.put("log4j.rootLogger",prop.getProperty("LogLevel")+",A3");
		properies.put("log4j.appender.A3.Threshold",prop.getProperty("LogLevel"));
		properies.put("log4j.appender.A3.encoding", "UTF-8");
		properies.put("log4j.appender.A3", "org.apache.log4j.DailyRollingFileAppender");
		//properies.put("log4j.appender.A3", "org.apache.log4j.RollingFileAppender");
		properies.put("log4j.appender.A3.File", prop.getProperty("LogFolder")+"/monitorLog/"+logName);
		properies.put("log4j.appender.A3.ImmediateFlush", "true");
		properies.put("log4j.appender.A3.DatePattern","'_'yyyy-MM-dd");
		properies.put("log4j.appender.A3.layout", "org.apache.log4j.PatternLayout");
		properies.put("log4j.appender.A3.layout.ConversionPattern","%-d{yyyy/MM/dd HH:mm:ss} OSS %-5p [%c] - %m%n");
		return properies;
	} 
}