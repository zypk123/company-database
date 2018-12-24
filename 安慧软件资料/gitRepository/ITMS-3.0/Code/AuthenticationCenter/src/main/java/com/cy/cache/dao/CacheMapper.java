package com.cy.cache.dao;

import java.util.List;

import com.cy.cache.domain.CacheDao;

	
public interface CacheMapper {
     /**
      * 根据角色编码获取该所有角色的所有权限服务
      * @return List<SecurityDao>
      * 
      */
	  List<CacheDao> getServicesByRoleCode();
	  
	  
	  
     /**
      * 根据角色编码获取该所有角色的所有权限服务
      * @return List<SecurityDao>
      * 
      */
	  List<CacheDao> getServicesBykey(String serviceKey);
	  
 }