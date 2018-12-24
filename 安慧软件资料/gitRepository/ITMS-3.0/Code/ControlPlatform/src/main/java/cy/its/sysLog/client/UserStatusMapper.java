package cy.its.sysLog.client;

import cy.its.sysLog.domain.UserStatus;

public interface UserStatusMapper {
	
    int insert(UserStatus record);
    
    String selectById(String userId);
    
    int  updateByUserId(UserStatus record);
}