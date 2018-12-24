package cy.its.sysLog.task;

import cy.its.platform.common.utils.Log4jFactoryProxy;
import cy.its.sysLog.domain.UserLogin;
import cy.its.sysLog.service.UserLoginLogI;

/**
* @Title: UserLoginTask.java 
* @Package cy.its.sysLog.task 
* @Description: 用户登录
* @author lil@cychina.cn
* @date 2016年3月11日 下午4:50:03 
* @version V1.0   
 */
public class UserLoginTask  extends Thread{
	
	private  Log4jFactoryProxy log4jFactoryProxy = Log4jFactoryProxy.getSingleton(UserLoginTask.class);
	
	private UserLogin userLogin;
	
	private UserLoginLogI userLoginLogImpl;
	

	public  UserLoginTask(UserLoginLogI imp, UserLogin log) {
		this.userLogin=log;
		this.userLoginLogImpl=imp;
	}
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 * 调用接口方法保存，信息
	 */
	@Override
    public void run() {
		
		int is = 0;
		try{
			is = userLoginLogImpl.insert(userLogin);
		}catch(Exception e){
			//不做任何处理不影响正常的业务，最多日记记录不成功！
		}
		log4jFactoryProxy.info(is);
    }
}
