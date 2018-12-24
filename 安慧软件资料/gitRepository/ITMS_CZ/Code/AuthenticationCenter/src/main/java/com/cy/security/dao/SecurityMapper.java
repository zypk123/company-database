package com.cy.security.dao;

import java.util.List;

import com.cy.security.domain.SecurityDao;


public interface SecurityMapper {
    /**
     * @param userName
     * @return LIST
     * 用户登陆输入用户，返回角色信息
     */
    List<SecurityDao> getAllRoleByStaffCode(String userName);
    
    
    
    /**
     * @param userName
     * @return LIST
     * 用户登陆输入用户，返回密码信息
     */
    List<SecurityDao> getPwdByStaffCode(String userName);
    
 }