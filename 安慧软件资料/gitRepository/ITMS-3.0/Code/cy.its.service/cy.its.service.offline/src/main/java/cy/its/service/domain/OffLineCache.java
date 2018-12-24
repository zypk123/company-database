package cy.its.service.domain;

import java.util.Map;

/**
* @Title: OffLineCache.java 
* @Package cy.its.service.domain 
* @Description: 离线缓存文件
* @author lil@O.cn
* @date 2015年11月18日 下午3:52:56 
* @version V1.0   
 */
public class OffLineCache {
	
	//文件路径为：root
	private static String  filePath = "D:/temp";
	
	//文件路径为：root
	private static String  httpIp = "127.0.0.1:8080";

	/**
     * 存放不同规则的导出
     */
	private  static  Map   exportMap;
	
	/**
	 * 初始化类
	 */
	private  OffLineCache(Map map,String  fpath,String httpip){
		exportMap = map;
		filePath = fpath;
		httpIp = httpip;
	}

	public static String getFilePath() {
		return filePath;
	}

	public static Map getMap() {
		return exportMap;
	}
	
	
	/** 
	* @Title: udateMap 
	* @Description: 更新配置文件时调用，基本很少
	* @param @param map    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public static void udateMap(Map map) {
		synchronized(OffLineCache.class){
			exportMap = map;
		}
	}
	
	public static String getHttpIp() {
		return httpIp;
	}
}
