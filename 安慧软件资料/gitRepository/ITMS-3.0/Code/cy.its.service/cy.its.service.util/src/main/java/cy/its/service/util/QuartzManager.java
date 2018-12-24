package cy.its.service.util;

import java.text.ParseException;
import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
* @Title: QuartzManager.java 
* @Package cy.its.road.convertFlow.util 
* @Description: 定时任务管理类
* @author lil@cychina.cn
* @date 2015年10月28日 下午6:59:06 
* @version V1.0   
 */
public class QuartzManager {
	
	   private static SchedulerFactory sf = new StdSchedulerFactory();
	   private static String JOB_GROUP_NAME = "group1";
	   private static String TRIGGER_GROUP_NAME = "trigger1";
	   
	  
	   
	/** 
	* @Title: addJob 
	* @Description:定时任务处理
	* @param @param triggerName
	* @param @param jobName
	* @param @param time
	* @param @param clssType
	* @param @throws SchedulerException
	* @param @throws ParseException    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public static void addJob(String triggerName,String jobName,Date runTime,Class clssType,int seconds) 
	                               throws SchedulerException, ParseException{
	      Scheduler sched = sf.getScheduler();
	     //用于描叙Job实现类及其他的一些静态信息，构建一个作业实例
	       JobDetail jobDetail = JobBuilder.newJob(clssType).withIdentity(jobName, JOB_GROUP_NAME).build();
	       //触发器
	       Trigger trigger = TriggerBuilder.newTrigger()//创建一个新的TriggerBuilder来规范一个触发器
	    		   .withIdentity(triggerName, TRIGGER_GROUP_NAME)//给触发器起一个名字和组名
	    		   .startAt(runTime)//立即执行
	    		   .withSchedule(
		    		   SimpleScheduleBuilder.simpleSchedule()
		    		   .withIntervalInSeconds(seconds)//时间间隔  单位：秒
		    		   .repeatForever()//一直执行
	    		   )
	    		   .build();//产生触发器
	       sched.scheduleJob(jobDetail, trigger);
	       //启动
	       if(!sched.isShutdown())
	          sched.start();
    }
	
	
	/** 
	* @Title: shutdown 
	* @Description: 关闭定时器
	* @param @param schedName    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public  static void shutdown() {
		try {
			Scheduler currentScheduler = sf.getScheduler();
			currentScheduler.shutdown();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
