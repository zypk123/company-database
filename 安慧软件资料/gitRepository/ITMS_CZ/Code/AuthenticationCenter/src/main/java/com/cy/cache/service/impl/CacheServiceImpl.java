package com.cy.cache.service.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cy.cache.dao.CacheMapper;
import com.cy.cache.domain.CacheDao;
import com.cy.cache.service.CacheServiceI;

import cy.its.platform.common.utils.Log4jFactoryProxy;
import cy.its.platform.common.utils.RedisPoolUtil;

/**
* @Title: CacheServiceImpl.java 
* @Package com.cy.cache.service.impl 
* @Description:系统相关信息缓存 
* @author lil@cychina.cn
* @date 2015年11月12日 上午9:51:17 
* @version V1.0   
 */
@Service("cacheServiceImpl")

public class CacheServiceImpl implements CacheServiceI {

	
	private Log4jFactoryProxy log = Log4jFactoryProxy.getSingleton(CacheServiceImpl.class);
	/**
     * 使用@Autowired注解标注userMapper变量，
     * 当需要使用securityMapper时，Spring就会自动注入securityMapper
     */
    @Autowired
    private CacheMapper cacheMapper;//注入dao
    
    @Autowired
    private DataSource  dataSource;//注入数据库连接池
    
	@Override
	public void initServicesByRoleCode() {
		 log.info("缓存开始初始化");
	     List<CacheDao> list = cacheMapper.getServicesByRoleCode();
	     System.out.println("数据已提取");
	     list.stream() 
	                .collect( 
	                        Collectors.groupingBy( 
	                        		CacheDao::getCode, 
	                                Collectors.mapping(CacheDao::getName, 
	                                        Collectors.toList())))
	     .forEach((k,v)->{
	    	    //初始化之前 先删除以前的缓存
	    	    if(RedisPoolUtil.get(k)!=null){
	    	    	RedisPoolUtil.del(k);
	    	    }
	    	    //重新初始化
	    	 	RedisPoolUtil.put(k,JSONObject.toJSONString(v));
	    	 }
	     );
	     log.info("缓存数据完成");
	}
	
	@Override
	public void addMemeryCache(String key,String value) {
		RedisPoolUtil.put(key,value);
	}
	
	@Override
	public void addMemeryCache(String key,Map value) {
		RedisPoolUtil.hmput(key,value);
	}
	
	@Override
	public void updateMemeryCache(String key, Object value) {
		RedisPoolUtil.del(key);
		RedisPoolUtil.put(key,JSONObject.toJSONString(value));
	}

	@Override
	public void deleteMemeryCache(String key) {
		RedisPoolUtil.del(key);
	}
	@Override
	public Object getMemeryCacheByKey(String key) {
		return RedisPoolUtil.get(key);
	}
    
	/* (non-Javadoc)
	 * @see com.cy.cache.service.CacheServiceI#getServicesBykey(java.lang.String)
	 * 根据传入KEY 获取真正的服务地址
	 */
	@Override
	public List<CacheDao> getServicesBykey(String serviceKey) {
		//SqlHelper.getMapperSql(cacheMapper, "getServicesBykey", serviceKey);
		return cacheMapper.getServicesBykey(serviceKey);
	}

	@Override
	public void initKeyList() {
		List<String> keyList  = new ArrayList<String>();
		RedisPoolUtil.put("keyList", JSONArray.toJSONString(keyList));
	}

	@Override
	public void freshExpoireKey(String tokenId,int seconds) {
		RedisPoolUtil.updateExpireTime(tokenId, seconds);
	}

	@Override
	public void addMapCache(String key, Map value, int seconds) {
		RedisPoolUtil.hmputExpoire(key, value,seconds);
	}

	/* (non-Javadoc)
	 * @see com.cy.cache.service.CacheServiceI#addMemeryCache(java.lang.String, java.lang.String, int)
	 */
	@Override
	public void addMemeryCache(String key, String value, int seconds) {
		RedisPoolUtil.putExpire(key, value, seconds);
	}

	/* (non-Javadoc)
	 * @see com.cy.cache.service.CacheServiceI#getAllMemeryCacheKeys(java.lang.String)
	 */
	@Override
	public int getAllMemeryCacheKeys(String keys) {
		Set<String> list =  RedisPoolUtil.getKeys(keys);
		return list.size();
	}
}
