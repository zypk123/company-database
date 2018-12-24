package cy.its.common.duplex.rabbitMq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cy.its.platform.common.utils.Log4jFactoryProxy;
import cy.its.service.common.rabbitmqClient.BindRelation;
import cy.its.service.common.rabbitmqClient.ExchangeInfo;
import cy.its.service.common.rabbitmqClient.ExchangeType;
import cy.its.service.common.rabbitmqClient.MQAddress;
import cy.its.service.common.rabbitmqClient.MQGateWay;
import cy.its.service.common.rabbitmqClient.QueueHandler;

/**
* @Title: MQUtil.java 
* @Package com.cy.rabbitMq 
* @Description:MQ工具类，只在工程启动的时候初始化一次
* @author lil@cychina.cn
* @date 2015年11月19日 下午2:05:53 
* @version V1.0   
 */
public class MQEntrance {
	/**
	 * 通道信息
	 */
	private static List<QueueHandler> queueHandlers;
	
	private static Log4jFactoryProxy log = Log4jFactoryProxy.getSingleton(MQEntrance.class);
	
	/**
	 *存放接收业务类
	 */
	private static Map<String,IMqReceiver>  ireceivermap;
	
	
	static{
		
		ireceivermap  = new  HashMap();
	}
	
	private MQEntrance() {
		//需要CHNAL
    	MQAddress mqAddress = new MQAddress(RabbitMqProperties.getIp(), "/",RabbitMqProperties.getUser(), RabbitMqProperties.getPwd());
		List<BindRelation> bindRelations = new ArrayList<BindRelation>();
		queueHandlers = new ArrayList<QueueHandler>();
		//根据配置文件，实例化对象
		for (String key : RabbitMqProperties.getMap().keySet()) {
			   Class instance;
			try {
				instance = Class.forName(RabbitMqProperties.getMap().get(key));
				IMqReceiver  imr  = (IMqReceiver) instance.newInstance();
				//缓存对象
				ireceivermap.put(key,imr);
				queueHandlers.add(new QueueHandler(key+"_"+System.currentTimeMillis(),false, true, key,imr));
			} catch (Exception e) {
				log.error(RabbitMqProperties.getMap().get(key)+"：接收服务启动失败！");
				log.error(e.getStackTrace());
			}
		}
		//接收类加载完成
		bindRelations.add(new BindRelation(new ExchangeInfo(RabbitMqProperties.getDataCenter(), true, false, ExchangeType.TOPIC),
				queueHandlers));
		try {
			//初始化信息
			MQGateWay.synInit(mqAddress,bindRelations, s->{log.info(s);}, s->{log.error(s);});
		} catch (Exception e) {
			log.error("初始化MQ信息出现问题！");
			log.error(e.getStackTrace());
		}	
	}

	public static Map<String, IMqReceiver> getIreceivermap() {
		return ireceivermap;
	}
	
	
}
