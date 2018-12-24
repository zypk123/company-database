package cy.its.sysLog.task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
* @Title: LogTaskThroadManager.java 
* @Package cy.its.sysLog.task 
* @Description: 线程池，一般不关闭 
* @author lil@cychina.cn
* @date 2015年8月31日 下午7:07:55 
* @version V1.0   
 */
public class LogTaskThroadManager {
	
   private  static ExecutorService pool = Executors.newFixedThreadPool(3);//不需要关闭线程池
   
   /** 
	* @Title: addTaskPool 
	* @Description: 向线程池加入任务
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public static void  addTaskPool(Thread task){
		
		pool.execute(task);//添加任务到线程池中，由线程池处理
	
	}
}
