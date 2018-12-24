package cy.its.sysLog.task;

import cy.its.platform.common.utils.Log4jFactoryProxy;
import cy.its.sysLog.domain.SyOperationLog;
import cy.its.sysLog.service.SyOperationLogI;

/**
* @Title: LogTask.java 
* @Package cy.its.sysLog 
* @Description: 异步记录日志信息
* @author lil@cychina.cn
* @date 2015年8月31日 下午6:57:57 
* @version V1.0   
 */
public class LogTask  extends Thread{
	
	private  Log4jFactoryProxy log4jFactoryProxy = Log4jFactoryProxy.getSingleton(LogTask.class);
	
	private SyOperationLog syOperationLog;
	
	private SyOperationLogI SyOperationLogImpl;
	

	public  LogTask(SyOperationLogI imp, SyOperationLog log) {
		this.syOperationLog=log;
		this.SyOperationLogImpl=imp;
	}
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 * 调用接口方法保存，信息
	 */
	@Override
    public void run() {
		
		int is = 0;
		try{
			is = SyOperationLogImpl.insert(syOperationLog);
		}catch(Exception e){
			//不做任何处理不影响正常的业务，最多日记记录不成功！
		}
		log4jFactoryProxy.info(is);
    }
}
