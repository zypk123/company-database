package com.cy.security.service;

import java.util.List;

import com.cy.security.domain.SecurityDao;


public interface SecurityServiceI {
    
    /**获取所有用户信息
     * @return List<User>
    */
    List<SecurityDao> getPwdByStaffCode(String userName);
    
    
    /**获取所有用户信息
     * @return List<User>
    */
    List<SecurityDao> getAllRoleByStaffCode(String userName);
}
