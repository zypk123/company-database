package com.cy.rest.service;

import java.util.List;
import java.util.Map;

import com.cy.security.domain.SecurityDao;


public interface SecurityServiceReSetI {
    
    /**获取所有用户信息
     * @return List<User>
    */
	 List<SecurityDao> getPwdByStaffCode(String param);
	 
	 
	 
    /**获取所有用户信息
     * @return List<User>
    */
	 List<SecurityDao> getAllRoleByStaffCode(String param);
	 
	 
	/**
	 * @param paramInfo
	 * @return
	 * 登陆验证的服务
	 */
	Map  validatelogin(String paramInfo);
	
	
	/**
	 * @param paramInfo
	 * @return
	 * 用户注销服务
	 */
	Map  loginOut(String paramInfo);
	
	/**
	 * @param paramInfo
	 * @return
	 * 服务访问的服务
	 */
	Map  authorityService(String paramInfo);
	
	/**
	 * @param paramInfo
	 * @return
	 * 服务访问的服务
	 */
	Map  reFreshToken(String paramInfo);
	
	/**
	 * @param paramInfo
	 * @return
	 * 统计在线用户数
	 */
	Map  onlineUsers();
	
	/**
	 * 从管控平台跳转验收授权
	 * @param paramInfo
	 * @return
	 */
	Map  validateFrom3(String paramInfo);
}
