package com.cy.security.service;

import java.util.List;
import java.util.Map;

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


    /**
     * 这个是从申报审批系统act_id_user查询用户信息，因为管控平台用户信息在t_sys_user。
     * 发现2个表数据不一样，如果一样就删除下面方法，用getPwdByStaffCode
     * @param upperCase
     * @return
     */
	SecurityDao queryAppUserById(String upperCase);
}
