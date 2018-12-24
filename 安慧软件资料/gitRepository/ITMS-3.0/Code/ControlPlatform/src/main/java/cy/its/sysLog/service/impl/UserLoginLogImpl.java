package cy.its.sysLog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.platform.common.utils.SqlHelper;
import cy.its.sysLog.client.UserLoginMapper;
import cy.its.sysLog.domain.UserLogin;
import cy.its.sysLog.service.UserLoginLogI;

/**
* @Title: UserLoginLogImpl.java 
* @Package cy.its.sysLog.service.impl 
* @Description: 用户登录 记录 
* @author lil@cychina.cn
* @date 2016年3月11日 下午4:25:29 
* @version V1.0   
 */
@Service
public class UserLoginLogImpl implements UserLoginLogI{
	@Autowired
	private UserLoginMapper userLoginMapper;//注入数据库操作类
	
	@Override
	public int insert(UserLogin record) {
		return userLoginMapper.insert(record);
	}

	@Override
	public String getUserId(String name) {
		return userLoginMapper.getUserId(name);
	}

}
