package ah.its.workFlow.web.servlet;

import java.util.Properties;
/**
 * @author lilei
 * 系统全局属性初始化
 */
public final class GlobalProerty {
	
   public final   String projectPath;//项目路径
   public final   String model;//目前模式，开发，生产
   public final   String service_ip; //分布式地址
   public final   String service_img; //图片服务器地址
   public final   String validate_ip;//用户验证授权服务器地址
   public final   String authorityPName;//权限验证项目名称
   private static GlobalProerty globalProerty;  
   
   private GlobalProerty(Properties prop){
       service_ip=prop.getProperty("service_ip");
       model=prop.getProperty("model");
       projectPath=prop.getProperty("prefix");
       service_img=prop.getProperty("service_img");
       validate_ip=prop.getProperty("validate_ip");
       authorityPName=prop.getProperty("authorityPName");
   }
   
	/** 
	* @Title: setGlobalProerty 
	* @Description: 被初始化一次 ，限制方包内访问
	* @param @param prop    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
   static void setGlobalProerty(Properties prop){
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
