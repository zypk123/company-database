package com.cy.cache.service;

import java.util.Map;


public interface DataDictionaryManagerI {
	
	/** 
	* @Title: initMemoryCache 
	* @Description:配置文件固定，初始化所有数据字典信息
	* @return void 返回类型 
	* @throws 
	*/
	public void initMemoryCache(String path);
	
	/** 
	* @Title: initMemoryCache 
	* @Description:根据出入的KEY，跟新指定缓存
	* @param @param path    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void updateMemoryCache(String[] keys);
	
	
	/** 
	* @Title: initMemoryCache 
	* @Description:根据出入的KEY，查询缓存中的信息，如缓存中部存在，则查询数据库获取数据并更新缓存
	* @param @param path    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public Map queryMemoryCache(String[] keys);
	
}
