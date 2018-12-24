package com.cy.cache.service;

import java.util.List;
import java.util.Map;

import com.cy.cache.domain.CacheDao;



public interface CacheServiceI {
    /**
     *初始化方法，初始化所有的角色对应的所有服务
     */
    void initServicesByRoleCode();
    /**
     * @param serviceKey
     * @return
     */
    List<CacheDao> getServicesBykey(String serviceKey);
    /**
     * @param key
     * @param value
     */
    void addMemeryCache(String key,String value);
    
    /**
     * @param key
     * @param value
     */
    void addMemeryCache(String key,String value,int seconds);
    
    /**
     * @param key
     * @param value
     */
    void addMemeryCache(String key,Map value);
    
    /**
     * @param key
     * @param value
     */
    void addMapCache(String key,Map value,int seconds);
    
    /**
     * @param key
     * @param value
     */
    void updateMemeryCache(String key,Object value);
    /**
     * @param key
     */
    void deleteMemeryCache(String key);
	/**
	 * @param key
	 * @return
	 */
	Object getMemeryCacheByKey(String key);
	
	/**
	 * 初始化KEY的集合，LIST<String>
	 */
	void initKeyList();
	
	/** 
	* @Title: freshExpoireKey 
	* @Description: 重新设置过期时间
	* @param @param tokenId
	* @param @param seconds    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	void freshExpoireKey(String tokenId, int seconds);
	
	/** 
	* @Title: getAllMemeryCacheKeys 
	* @Description:获取某一个模糊匹配KEY的数据
	* @param @param string
	* @param @return 设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	int getAllMemeryCacheKeys(String keys);
	
}
