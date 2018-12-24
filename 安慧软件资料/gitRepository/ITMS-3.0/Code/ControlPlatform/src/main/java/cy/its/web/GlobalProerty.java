package cy.its.web;

import java.util.HashMap;
import java.util.Map;

import cy.its.platform.common.utils.OrderedProperties;


/**
 * @author lilei
 * 系统全局属性初始化
 */
public final class GlobalProerty {
	
   public final   String projectPath;//项目路径
   public final   String model;//目前模式，开发，生产
   public final   String service_ip; //分布式地址
   public final   String validate_ip;//用户登录、服务验证、数据字典、wescoket 服务 ip 地址
   public final   String servicePName;//服务项目名称
   public final   String authorityPName;//认证授权验证服务项目名称
   public final   Map 	 routeMap;
   
   
   private static GlobalProerty globalProerty;  
   
   private GlobalProerty(OrderedProperties prop){
       service_ip=prop.getProperty("service_ip");
       model=prop.getProperty("model");
       projectPath=prop.getProperty("prefix");
       validate_ip=prop.getProperty("validate_ip");
       servicePName=prop.getProperty("servicePName");
       authorityPName=prop.getProperty("authorityPName");
       routeMap = new HashMap();
       /**
        *路由设置 ,样式如下：
        *routeKeys=device,sysManager
        * device=192.168.10.33:8080
        * sysManager=192.168.10.33:8081
        */
       String keys  = prop.getProperty("routeKeys")==null?"":prop.getProperty("routeKeys");
       if(!keys.equals("")){
    	   if(keys.indexOf(",")>-1){
    		   String key[] = keys.split(",");
    		   for(String tkey:key){
    			   routeMap.put(tkey, prop.getProperty(tkey));
    		   }
    	   }else{
    		   routeMap.put(keys, prop.getProperty(keys));
    	   }
       }
   }
   
	/** 
	* @Title: setGlobalProerty 
	* @Description: 被初始化一次 ，限制方包内访问
	* @param @param prop    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
   static void setGlobalProerty(OrderedProperties prop){
		   globalProerty = new GlobalProerty(prop);
   }
   
   /** 
	* @Title: getGlobalProerty 
	* @Description:提供全局的访问点 
	* @param @return    设定文件 
	* @return GlobalProerty    返回类型 
	* @throws 
	*/
	public  static GlobalProerty getGlobalProerty(){
		   return globalProerty; 
   }
}
