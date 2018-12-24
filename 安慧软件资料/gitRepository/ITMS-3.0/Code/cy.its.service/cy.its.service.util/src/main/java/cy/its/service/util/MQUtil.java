package cy.its.service.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cy.its.service.common.rabbitmqClient.BindRelation;
import cy.its.service.common.rabbitmqClient.ExchangeInfo;
import cy.its.service.common.rabbitmqClient.ExchangeType;
import cy.its.service.common.rabbitmqClient.MQAddress;
import cy.its.service.common.rabbitmqClient.MQGateWay;
import cy.its.service.common.rabbitmqClient.QueueHandler;

/**
* @Title: MQUtil.java 
* @Package cy.its.service.surveillance.util 
* @Description: MQ连接工具类
* @author lil@cychina.cn
* @date 2015年10月24日 上午9:10:10 
* @version V1.0   
 */
public class MQUtil {
	  
	   private  static   List<QueueHandler>   queueHandlers;
	
	   private  static    Logger log=Logger.getLogger(MQUtil.class);
	   //属性值
	   private  static   String  propertity="";
	   
	   //配置文件路径
	   private  static   String  resource="";
	   //ip地址
	   private  static   String  ip;
	   //用户
	   private  static   String  user;
	   //密码
	   private  static   String  pwd;
	   //中心交换区名称
	   private  static   String  dataCenter;
	   
	   private MQUtil(){
		   
	   }
	   
	   private  MQUtil(String  propertity1,String  resource1){
		   propertity=propertity1;
		   resource=resource1;
		   //如果propertity不为空则需要，更改配置文件中日志的路径
		   try {
			  initAllProperties();
			} catch (Exception e) {
				log.error("mq连接初始化异常，请检查配置文件！");
				e.printStackTrace();
			}
	    }
	   
		/** 
		* @Title: initAllProperties 
		* @Description: 初始化所有数据
		* @param @throws Exception    设定文件 
		* @return void    返回类型 
		* @throws 
		*/
		private static  void initAllProperties() throws Exception {
			OrderedProperties prop = new OrderedProperties();//属性集合对象 
			InputStream fis =  new FileInputStream(resource);
			prop.load(fis);// 将属性文件流装载到Properties对象中 
			fis.close();// 关闭流 
			ip = prop.getProperty("rabbitMqIp");
			user ="guest";
			pwd ="guest";
			dataCenter ="SURVEY_CENTER";
			initMqChanel();
		}
		
		static MQAddress mqAddress;
		static List<BindRelation> bindRelations;
		/** 
		* @Title: initMqChanel 
		* @Description: 打通MQ通道
		* @param @throws Exception    设定文件 
		* @return void    返回类型 
		* @throws 
		*/
		private  static void  initMqChanel(){
			mqAddress = new MQAddress(ip,"/",user,pwd);
			bindRelations = new ArrayList<BindRelation>();
			queueHandlers = new ArrayList<QueueHandler>();
			bindRelations.add(new BindRelation(new ExchangeInfo(dataCenter, true, false,ExchangeType.TOPIC),
					queueHandlers));
			
		}
		
		public static  List<QueueHandler>  getQueueHandler(){
			return queueHandlers;
		}

		/** 
		* @Title: init 
		* @Description: 初始化通道信息
		* @param @throws Exception    设定文件 
		* @return void    返回类型 
		* @throws 
		*/
		public static void init() throws Exception {
			
			MQGateWay.init(mqAddress,bindRelations, (s)->{System.out.println(s);},
					(s)->{System.out.println(s);});
		}
		
		/** 
		* @Title: stop 
		* @Description: 关闭MQ接收
		* @param @throws Exception    设定文件 
		* @return void    返回类型 
		* @throws 
		*/
		public static void stop() throws Exception {
			MQGateWay.stop();
		}
		
		
}