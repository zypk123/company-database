package cy.its.sysLog.client;

import cy.its.sysLog.domain.UserLogin;

public interface UserLoginMapper {
	
    int insert(UserLogin record);

    int insertSelective(UserLogin record);

	String getUserId(String name);
}