package cy.its.common.duplex.rabbitMq;

import java.util.Map;

/**
* @Title: RabbitMqProperties.java 
* @Package com.cy.web 
* @Description: mq属性值
* @author lil@cychina.cn
* @date 2015年10月30日 上午10:06:52 
* @version V1.0   
 */
public class RabbitMqProperties {
	
	
	
    private static  String ip="";//IP
    private static  String user="";//用户
    private static  String pwd="";//密码
    private static  String dataCenter=""; //数据交换中心
    private static  String exchangeType="";//topic
    
    private static  Map<String,String>  map;//存放业务编码和对应实体类类名
	   
	private   RabbitMqProperties(String ip1,String user1,String pwd1,
			String dataCenter1,String exchangeType1,Map  map1){
		ip = ip1;
		user =  user1;
		pwd = pwd1;
		dataCenter= dataCenter1;
		exchangeType=exchangeType1;
		map=map1;
	} 
    
	public static String getIp() {
		return ip;
	}
	public static String getUser() {
		return user;
	}
	public static String getPwd() {
		return pwd;
	}
	public static String getDataCenter() {
		return dataCenter;
	}
	public static Map<String,String> getMap() {
		return map;
	}

	public static String getExchangeType() {
		return exchangeType;
	}
}
