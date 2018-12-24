package com.cy.web;


/**
* @Title: GlobalProerty.java 
* @Package com.cy.web 
* @Description: 系统初始化信息，只提供get方法
* @author lil@cychina.cn
* @date 2015年10月30日 上午10:02:44 
* @version V1.0   
 */
public class GlobalProerty {
	
   private static  String projectPath="";//项目路径
   private static  String model="";//目前模式，开发，生产
   private static  String isDistributed="";//是否分布式
   private static  String service_ip=""; //分布式地址
   private static  String validate_ip="";//用户登录、服务验证、数据字典、wescoket 服务 ip 地址
   private static  String servicePName="";//服务项目名称
   private static  String authorityPName="";//认证授权验证服务项目名称
   
   private GlobalProerty(String projectPathc,String modelc,String isDistributedc,String service_ipc
		   ,String validate_ipc,String servicePNamec,String authorityPNamec){
	   projectPath=projectPathc;
	   model=modelc;
	   isDistributed=isDistributedc;
	   service_ip=service_ipc;
	   validate_ip=validate_ipc;
	   servicePName=servicePNamec;
	   authorityPName=authorityPNamec;
    }
   
	public static String getProjectPath() {
		return projectPath;
	}
	public static String getModel() {
		return model;
	}
	public static String getIsDistributed() {
		return isDistributed;
	}
	public static String getService_ip() {
		return service_ip;
	}
	public static String getValidate_ip() {
		return validate_ip;
	}
	public static String getServicePName() {
		return servicePName;
	}
	public static String getAuthorityPName() {
		return authorityPName;
	}
	
}
