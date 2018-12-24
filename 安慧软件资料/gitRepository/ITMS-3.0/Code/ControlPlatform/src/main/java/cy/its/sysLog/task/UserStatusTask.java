package cy.its.sysLog.task;

import cy.its.platform.common.utils.Log4jFactoryProxy;
import cy.its.sysLog.domain.UserStatus;
import cy.its.sysLog.service.UserStatusLogI;

/**
* @Title: UserStatusTask.java 
* @Package cy.its.sysLog.task 
* @Description: 更新用户状态操作类
* @author lil@cychina.cn
* @date 2016年3月11日 下午4:49:39 
* @version V1.0   
 */
public class UserStatusTask extends Thread{
   
	private  Log4jFactoryProxy log4jFactoryProxy = Log4jFactoryProxy.getSingleton(UserStatusTask.class);
	
	private UserStatus userStatus;
	
	private UserStatusLogI userStatusLogImpl;
	

	public  UserStatusTask(UserStatusLogI imp, UserStatus log) {
		this.userStatus=log;
		this.userStatusLogImpl=imp;
	}
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 * 调用接口方法保存，信息
	 */
	@Override
    public void run() {
		
		int is = 0;
		try{
			is = userStatusLogImpl.updateUserStatus(userStatus);
		}catch(Exception e){
			//不做任何处理不影响正常的业务，最多日记记录不成功！
		}
		log4jFactoryProxy.info(is);
    }
}
