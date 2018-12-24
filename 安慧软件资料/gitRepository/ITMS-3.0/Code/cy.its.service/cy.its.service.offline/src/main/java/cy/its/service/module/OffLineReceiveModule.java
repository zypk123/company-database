package cy.its.service.module;

import java.lang.reflect.Constructor;
import java.text.DecimalFormat;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.alibaba.druid.util.StringUtils;

import cy.its.service.domain.OffLine;
import cy.its.service.domain.OffLineCache;
import cy.its.service.domain.OffLineTask;
import cy.its.service.util.DBUtil;
import cy.its.service.util.ExecutorServiceUtil;


/**
* @Title: OffLineReceiveModule.java 
* @Package cy.its.service.module 
* @Description:MQ推送信息，接收到信息说明有任务需要处理
* @author lil@cychina.cn
* @date 2015年11月17日 下午7:58:09 
* @version V1.0   
 */
public class OffLineReceiveModule implements Job{
	//数据格式化
	DecimalFormat df3 = new DecimalFormat("#.00");
	
	String  sql= " select  task_id taskId,func_key funcKey,file_name fileName,export_sql exportSql,export_sql2 exportSql2,status,update_time updateTime,login_name loginName from T_SYS_EXPORT where  status = '1' ";//新任务
	
	/* (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 * 定时任务触发操作
	 */
	@Override
	public void execute(JobExecutionContext context){
		//查询数据库数据
		List<OffLine>   list = DBUtil.queryData(sql,OffLine.class);
		//遍历记录
		list.stream().forEach(ol->{
			String sql  = ol.getExportSql();
			//SQL不为空的时候才处理
			if(!StringUtils.isEmpty(sql)){
				String  className = (String) OffLineCache.getMap().get(ol.getFuncKey());
				Class cl;
				try {
					cl = Class.forName(className);
					Constructor c0=cl.getConstructor(new Class[]{String.class,String.class,String.class,String.class}); 
					OffLineTask  otask  = (OffLineTask) c0.newInstance(new String[]{ol.getTaskId(),ol.getExportSql(),ol.getFileName(),ol.getLoginName()});
					//更新为排队中
					updateTask(ol.getTaskId(),"2");
					//把任务加入资源池中
					ExecutorServiceUtil.addTask(otask);
				} catch (Exception e) {
					//更新状态，然后抛出异常
					updateTask(ol.getTaskId(),"5");
					e.printStackTrace();
				}
				
			}else{
				//更新为下载失败
				updateTask(ol.getTaskId(),"5");
			}
		});
	}
	
	/** 
	* @Title: updateTask 
	* @Description: 更新任务的状态
	* @param @param taskId
	* @param @param string    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void updateTask(String taskId, String status) {
		String  updateSql = "update T_SYS_EXPORT set  status = '"+status+"' where task_id= '"+taskId+"'";
		DBUtil.updateData(updateSql);
	}
}
