package cy.its.service.analysisAlarm;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.text.ParseException;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.quartz.SchedulerException;

import cy.its.service.analysisAlarm.domain.GlobalProperty;
import cy.its.service.analysisAlarm.module.RoadSensorModule;
import cy.its.service.analysisAlarm.module.SectionFlowModule;
import cy.its.service.analysisAlarm.module.TrafficEventModule;
import cy.its.service.analysisAlarm.module.TravalTimeModule;
import cy.its.service.analysisAlarm.module.VisibilityModule;
import cy.its.service.analysisAlarm.module.WeatherModule;
import cy.its.service.common.ice.grid.AppService;
import cy.its.service.common.rabbitmqClient.IReceiver;
import cy.its.service.common.rabbitmqClient.QueueHandler;
import cy.its.service.util.MQUtil;
import cy.its.service.util.QuartzManager;
import cy.its.service.util.RedisPoolUtil;
import cy.its.service.util.ServiceFileResource;

/**
* @Title: VisibilityApp.java 
* @Package cy.its.service.visibility 
* @Description:能见度服务入口
* @author lil@cychina.cn
* @date 2015年11月6日 上午10:20:09 
* @version V1.0   
 */
public class AnalysisAlarmApp  extends AppService {
	
	private static  Logger log=Logger.getLogger(AnalysisAlarmApp.class);
	/** 
	* @Title: ConfigAllProperies 
	* @Description: 加载配置文件，该配置文件 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private  void ConfigAllProperties() {
		//资源文件初始化
		ServiceFileResource   sfr  = new ServiceFileResource("global.xml","analysisAlarm.log");
		//加载本地配置文件
		InputStream   is;
        Properties props = new Properties();
        try {
        	
        	is = new FileInputStream(sfr.globalPath+sfr.FILE_SEPARATOR+"alarm.properties");
			props.load(is);
			//初始化全局变量
			Class<?> c = GlobalProperty.class;
			Constructor c0=c.getDeclaredConstructor(new Class[]{int.class,int.class,int.class});
			c0.setAccessible(true);
			c0.newInstance(new Object[]{Integer.valueOf(props.getProperty("startTime")),
					Integer.valueOf(props.getProperty("endTime")),
					Integer.valueOf(props.getProperty("delayTime"))}); 
			try {
				
				IReceiver ic3 = (IReceiver) VisibilityModule.class.newInstance();
				
				IReceiver ic4 = (IReceiver) TrafficEventModule.class.newInstance();
				IReceiver ic5 = (IReceiver) WeatherModule.class.newInstance();
				IReceiver ic6 = (IReceiver) RoadSensorModule.class.newInstance();
				IReceiver ic7 = (IReceiver) TravalTimeModule.class.newInstance();
				
				IReceiver ic8 = (IReceiver) SectionFlowModule.class.newInstance();
				
				//能见度
				MQUtil.getQueueHandler().add(new QueueHandler("traffic_event_visibility_queue", false, true,"its_visibility",ic3));
				//交通事件
				MQUtil.getQueueHandler().add(new QueueHandler("traffic_event_nalysis_queue", false, true,"its_traffic_event",ic4));
				//大风预警
				MQUtil.getQueueHandler().add(new QueueHandler("traffic_event_weather_queue", false, true,"its_weather",ic5));
				//路感
				MQUtil.getQueueHandler().add(new QueueHandler("traffic_event_roadSensor_queue", false, true,"its_roadsensor",ic6));
				//旅行时间超长
				MQUtil.getQueueHandler().add(new QueueHandler("traffic_event_travalTime_queue", false, true,"its_region_speed",ic7));
				//断流预警
				MQUtil.getQueueHandler().add(new QueueHandler("traffic_event_section_queue",false,true,"its_section_flow",ic8));
				
				//启动定时器，程序关闭时 关闭定时器
				Date date = getNewDate();
				try {
					QuartzManager.addJob("analysisTriger", "weatherAlarm",date, AlarmScheduleService.class,60*5);
				} catch (SchedulerException | ParseException e) {
					log.error("启动定时器出错！");
					e.printStackTrace();
				}
				
				MQUtil.init();
			} catch (Exception e) {
				log.error("接收服务实例化失败，请检查配置，重新启动服务！");
				e.printStackTrace();
			}
			//redis实例库
			RedisPoolUtil.index = 2;
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
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
	
	public  static  void  main(String[]  agrs){
		AnalysisAlarmApp   aal  = new AnalysisAlarmApp();
		aal.ConfigAllProperties();
		Run(aal,agrs);
	}

	@Override
	public void Start(String[] args) throws Exception {
		System.out.println("路况预警分析服务启动！");
	}

	@Override
	public void Stop() throws Exception {
		//停止接收数据
		MQUtil.stop();
		//停止调度信息
		QuartzManager.shutdown();
	}

	@Override
	public String getAppName() {
		// TODO Auto-generated method stub
		return "AnalysisAlarm";
	}
}
