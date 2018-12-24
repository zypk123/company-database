package cy.its.service.convertFlow;


import java.text.ParseException;
import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.SchedulerException;

import cy.its.service.common.ice.grid.AppService;
import cy.its.service.common.rabbitmqClient.IReceiver;
import cy.its.service.common.rabbitmqClient.QueueHandler;
import cy.its.service.convertFlow.service.CacheManager;
import cy.its.service.convertFlow.service.ConverFlowService;
import cy.its.service.convertFlow.service.FlowScheduleService;
import cy.its.service.util.MQUtil;
import cy.its.service.util.QuartzManager;
import cy.its.service.util.RedisPoolUtil;
import cy.its.service.util.ServiceFileResource;

/**
* @Title: ConvertFlowAppService.java 
* @Package cy.its.road.convertFlow 
* @Description:5分钟流量接入转化服务
* @author lil@cychina.cn
* @date 2015年10月28日 上午11:29:02
* @version V1.0   
 */
public class ConvertFlowAppService  extends AppService {
	
	private static  Logger log=Logger.getLogger(ConvertFlowAppService.class);
	/**
	 * 初始化所有的资源文件
	 */
	/**
	* @Title: main 
	* @Description:启动定时器
	* @param @param agrs    设定文件 
	* @return void    返回类型 
	* @throws
	 */
    public  static  void  main(String[]  agrs){
    	ConvertFlowAppService capp = new ConvertFlowAppService();
    	capp.ConfigAllProperties();
    	Run(capp,agrs);
    }
    
	/** 
	* @Title: ConfigAllProperies 
	* @Description: 加载配置文件，该配置文件 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private  void ConfigAllProperties() {
		//资源文件初始化
		ServiceFileResource   sfr  = new ServiceFileResource("global.xml","sectionFlowLog.log");
		//初始化数据信息
		//CacheManager.initSectionist();
		//添加数据接收服务服务
		Class<?> clinstance  =ConverFlowService.class;
		IReceiver ic;
		try {
			ic = (IReceiver) clinstance.newInstance();
			//接收数据
			MQUtil.getQueueHandler().add(new QueueHandler("conver_flow_queue", false, true,"its_traffic_stats",ic));
			
			MQUtil.init();
		} catch (Exception e) {
			log.error("接收服务实例化失败，请检查配置，重新启动服务！");
			e.printStackTrace();
		}
		//redis实例库
		RedisPoolUtil.index  = 5;
	}
	
	private static Date getNewDate(int i) {
		Date date  = new  Date();
		if(i==0){
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
		}else if(i==1){
			date.setTime(date.getTime()+1000*60*60);
			date.setSeconds(0);
			date.setMinutes(5);
		}else {
			date.setTime(date.getTime()+1000*60*60*24);
			date.setHours(0);
			date.setMinutes(8);
			date.setSeconds(0);
		}
		return date;
	}

	@Override
	public void Start(String[] args) throws Exception {
		//启动定时任务
    	Date  date   = getNewDate(0);
    	try {
    		//5分钟运行一次
			QuartzManager.addJob("five_flow", "flow_service",date,FlowScheduleService.class,5*60);
			
		} catch (SchedulerException | ParseException e) {
			log.info("定时任务启动失败！");
			e.printStackTrace();
		}
		
	}

	@Override
	public void Stop() throws Exception {
		//停止接收MQ
		MQUtil.stop();
		//停止定时器
		QuartzManager.shutdown();
		
	}

	@Override
	public String getAppName() {
		// TODO Auto-generated method stub
		return "ConvertFlow";
	}
}
