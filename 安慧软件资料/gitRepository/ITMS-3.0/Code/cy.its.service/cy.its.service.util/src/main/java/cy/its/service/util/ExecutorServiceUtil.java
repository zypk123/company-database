package cy.its.service.util;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
* @Title: ExecutorServiceUtil.java 
* @Package cy.its.road.convertFlow.util 
* @Description: 任务线程池 需要顺序
* @author lil@cychina.cn
* @date 2015年10月29日 上午11:08:34 
* @version V1.0   
 */
public class ExecutorServiceUtil {
    
	/*
     * 线程池
     */
	private static ExecutorService singleThreadExecutor;
	
	/**
	 * 每次任务开始之前先加载该任务
	 */
	private static CopyOnWriteArrayList  preTask;
	
    static{
    	singleThreadExecutor = Executors.newCachedThreadPool();
    	preTask  = new CopyOnWriteArrayList();
    }
    
    /** 
    * @Title: addTask 
    * @Description:把任务放入线程池中
    * @param @param task    设定文件 
    * @return void    返回类型 
    * @throws 
    */
    public static void  addTask(Runnable task){
    	singleThreadExecutor.execute(task);
    }
    
    /** 
    * @Title: shutdown 
    * @Description: 如果有需要则关闭线程池
    * @param     设定文件 
    * @return void    返回类型 
    * @throws 
    */
    public  static  void shutdown(){
    	if(!singleThreadExecutor.isShutdown()){
    		singleThreadExecutor.shutdown();
    	}
    }
    
    /** 
    * @Title: removeAllPre 
    * @Description: 删除任务
    * @param @param object 设定文件 
    * @return void    返回类型  
    * @throws 
    */
    public static  void  removePre(Object  object){ 
       	preTask.remove(object);
    }
    
    /** 
    * @Title: getPreList 
    * @Description: 获取前置任务
    * @param @param object    设定文件 
    * @return void    返回类型 
    * @throws 
    */
    public static  List   getPreList(){
     	
    	return preTask;
    
    }
}
