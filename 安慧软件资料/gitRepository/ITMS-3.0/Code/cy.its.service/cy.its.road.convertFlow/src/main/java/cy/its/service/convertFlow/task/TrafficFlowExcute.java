package cy.its.service.convertFlow.task;

import java.util.ArrayList;
import java.util.List;

import cy.its.service.util.DBUtil;

/**
* @Title: TrafficFlowInsert.java 
* @Package cy.its.road.convertFlow.task 
* @Description:数据插入的任务
* @author lil@cychina.cn
* @date 2015年10月29日 下午4:07:07 
* @version V1.0   
 */
public class TrafficFlowExcute implements Runnable {
    
	//任务列表
	public List<String>  sqlList  = new  ArrayList<String>();
	
	/* 
	 * 执行插入方法
	 */
	@Override
	public void run() {
		//批量执行的方法
        DBUtil.executeBatchData(sqlList);
        
	}

}
