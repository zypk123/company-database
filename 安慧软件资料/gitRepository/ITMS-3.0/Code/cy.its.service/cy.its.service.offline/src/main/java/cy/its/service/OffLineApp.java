package cy.its.service;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.quartz.SchedulerException;

import cy.its.service.domain.OffLineCache;
import cy.its.service.imageQuery.cfg.dataAccess.DataSourceFactory;
import cy.its.service.module.OffLineReceiveModule;
import cy.its.service.util.DBUtil;
import cy.its.service.util.QuartzManager;
import cy.its.service.util.RedisPoolUtil;
import cy.its.service.util.ServiceFileResource;

/**
* @Title: OffLineApp.java 
* @Package cy.its.service 
* @Description: 离线下载启动类 
* @author lil@cychina.cn
* @date 2015年11月17日 下午7:55:52 
* @version V1.0   
 */
public class OffLineApp {
	
	private static  Logger log=Logger.getLogger(OffLineApp.class);
	/**
	 * 初始化所有的资源文件
	 */
	static{
		try {
			ConfigAllProperties();
		} catch (SQLException e) {
			log.error("数据库连接出现异常！");
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws SQLException  
	* @Title: ConfigAllProperies 
	* @Description: 加载配置文件，该配置文件 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private static void ConfigAllProperties() throws SQLException {
		//资源文件初始化
		ServiceFileResource   sfr  = new ServiceFileResource("global.xml","offline.log");
		DataSourceFactory.setDataSource(DBUtil.getDataSource());
		//redis实例库
		RedisPoolUtil.index  = 2;
		//解析文件，
		//加载本地配置文件
		InputStream   is = OffLineApp.class.getResourceAsStream("/config/properies/export.properties");
        Properties props = new Properties();
		try {
			props.load(is);
			is.close();
			Map  map  = new HashMap();
			for(Object t:props.keySet()){  
			    map.put(t.toString(),props.getProperty(t.toString()));
		    }  
			Constructor c0=OffLineCache.class.getDeclaredConstructor(new Class[]{Map.class,String.class,String.class});   
			c0.setAccessible(true);
			c0.newInstance(new Object[]{map,props.get("rootPath"),props.get("httpIp")});
		} catch (Exception e) {
			log.error("加载文件出现异常！");
			e.printStackTrace();
		}
		
		
	}
	
	public  static  void main(String[] args){
		System.out.println("开始");
		OffLineReceiveModule  pl  = new OffLineReceiveModule();
		try {
			QuartzManager.addJob("offlineTriger", "offline", new Date(), OffLineReceiveModule.class, 60);
		} catch (SchedulerException | ParseException e) {
			log.error("定时任务启动失败，请重新处理！");
			e.printStackTrace();
		}
	}
}
