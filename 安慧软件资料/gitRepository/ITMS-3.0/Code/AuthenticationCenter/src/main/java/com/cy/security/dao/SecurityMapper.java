package com.cy.security.dao;

import java.util.List;
import java.util.Map;

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
   
    /**
     * 这个是从申报审批系统act_id_user查询用户信息，因为管控平台用户信息在t_sys_user。
     * 发现2个表数据不一样，如果一样就删除下面方法，用getPwdByStaffCode
     * @param userName
     * @return
     */
    SecurityDao queryAppUserById(String id);
 }