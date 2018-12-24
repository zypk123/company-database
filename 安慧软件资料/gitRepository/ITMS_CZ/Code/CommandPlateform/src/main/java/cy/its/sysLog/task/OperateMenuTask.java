package cy.its.sysLog.task;

import cy.its.platform.common.utils.Log4jFactoryProxy;
import cy.its.sysLog.domain.OperateMenu;
import cy.its.sysLog.service.OperateMenuLogI;

/**
* @Title: OperateMenuTask.java 
* @Package cy.its.sysLog.task 
* @Description:菜单操作
* @author lil@cychina.cn
* @date 2016年3月11日 下午4:50:19 
* @version V1.0   
 */
public class OperateMenuTask extends Thread{
	
	private  Log4jFactoryProxy log4jFactoryProxy = Log4jFactoryProxy.getSingleton(OperateMenuTask.class);
	
	private OperateMenu operateMenu;
	
	private OperateMenuLogI operateMenuLogImpl;
	

	public  OperateMenuTask(OperateMenuLogI imp, OperateMenu log) {
		this.operateMenu=log;
		this.operateMenuLogImpl=imp;
	}
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 * 调用接口方法保存，信息
	 */
	@Override
    public void run() {
		
		int is = 0;
		try{
			is = operateMenuLogImpl.insert(operateMenu);
		}catch(Exception e){
			//不做任何处理不影响正常的业务，最多日记记录不成功！
		}
		log4jFactoryProxy.info(is);
    }
}
