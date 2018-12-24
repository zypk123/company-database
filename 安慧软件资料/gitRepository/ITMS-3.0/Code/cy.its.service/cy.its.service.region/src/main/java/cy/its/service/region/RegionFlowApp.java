	package cy.its.service.region;

import java.text.ParseException;
import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.SchedulerException;

import cy.its.service.common.ice.grid.AppService;
import cy.its.service.common.rabbitmqClient.IReceiver;
import cy.its.service.common.rabbitmqClient.QueueHandler;
import cy.its.service.region.module.CacheManager;
import cy.its.service.region.module.RegionReceiveModule;
import cy.its.service.region.module.RegionScheduleService;
import cy.its.service.util.MQUtil;
import cy.its.service.util.QuartzManager;
import cy.its.service.util.RedisPoolUtil;
import cy.its.service.util.ServiceFileResource;

/**
* @Title: RegionFlowApp.java 
* @Package cy.its.service.Region 
* @Description:区间平均车速
* @author lil@cychina.cn
* @date 2015年11月3日 上午10:25:21 
* @version V1.0   
 */
public class RegionFlowApp extends AppService {
	
	private static  Logger log=Logger.getLogger(RegionFlowApp.class);
	/**
	 * 初始化所有的资源文件
	 */
	/** 
	* @Title: ConfigAllProperies 
	* @Description: 加载配置文件，该配置文件 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private  void ConfigAllProperties() {
		//资源文件初始化
		ServiceFileResource   sfr  = new ServiceFileResource("global.xml","regionFlowLog.log");
		//添加数据接收服务服务
		Class<?> clinstance  =RegionReceiveModule.class;
		IReceiver ic;
		try {
			ic = (IReceiver) clinstance.newInstance();
			MQUtil.getQueueHandler().add(new QueueHandler("region_flow_queue", false, true,"its_pass_vehicle",ic));
			MQUtil.init();
		} catch (Exception e) {
			log.error("接收服务实例化失败，请检查配置，重新启动服务！");
			e.printStackTrace();
		}
		//redis实例库
		RedisPoolUtil.index  = 2;
	}
	
	public  static  void main(String[] args){
		
		RegionFlowApp  rapp = new RegionFlowApp();
		rapp.ConfigAllProperties();
		//启动实例
		Run(rapp, args);
	}

	/** 
	* @Title: getNewDate 
	* @Description:获取定时器执行时间
	* @param @return   设定文件 
	* @return Date    返回类型 
	* @throws 
	*/
	private static Date getNewDate() {
		Date date  = new  Date();
		int minute = date.getMinutes();
		minute = minute%10;
		int s = minute/10;
		if(minute <2){
			minute=2;
		}else if(minute < 7){
			minute=7;
		}else{
			minute=2;
		}
		date.setMinutes(s*10+minute);
		date.setSeconds(s*10);
		return date;
	}

	@Override
	public void Start(String[] args) throws Exception {
		//启动定时器
		Date date = getNewDate();
		try {
			
			QuartzManager.addJob("regionFlowTriger", "regionFlow",date, RegionScheduleService.class,60*5);
			
		} catch (SchedulerException | ParseException e) {
			log.error("启动定时器出错！");
			e.printStackTrace();
		}
	}

	@Override
	public void Stop() throws Exception {
		//关闭MQ接收通道
		MQUtil.stop();
		//关闭定时器
		QuartzManager.shutdown();
	}

	@Override
	public String getAppName() {
		
		return "RegionFlow";
	}
}
