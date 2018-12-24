package com.cy.security.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.security.dao.SecurityMapper;
import com.cy.security.domain.SecurityDao;
import com.cy.security.service.SecurityServiceI;

import cy.its.platform.common.utils.Log4jFactoryProxy;


/**
 * @author lilei
 * 使用@Service注解将UserServiceImpl类标注为一个service
 * service的id是userService
 */

@Service("securityServiceImpl")
public class SecurityServiceImpl implements SecurityServiceI {

	
	private Log4jFactoryProxy log = Log4jFactoryProxy.getSingleton(SecurityServiceImpl.class);
	/**
     * 使用@Autowired注解标注userMapper变量，
     * 当需要使用securityMapper时，Spring就会自动注入securityMapper
     */
    @Autowired
    private SecurityMapper securityMapper;//注入dao
    
	public List<SecurityDao> getAllRoleByStaffCode(String userName) {
		 return securityMapper.getAllRoleByStaffCode(userName);
	}
	
	public List<SecurityDao> getPwdByStaffCode(String userName) {
		 return securityMapper.getPwdByStaffCode(userName);
	}
}
