package cy.its.service.analysisAlarm.domain;

/**
* @Title: GlobalProperty.java 
* @Package cy.its.service.zeroFlow.domain 
* @Description:全局参数
* @author lil@cychina.cn
* @date 2015年11月12日 下午6:57:19 
* @version V1.0   
 */
public class GlobalProperty {
  //断流最小值
  //1天内预警可开始时间
  private static  int startTime  =0;
  //1天内预警结束时间
  private static  int  endTime  =0;
  
  //分钟
  private static  int  delayTime = 30;
  
  private GlobalProperty(int value2,int  value3,int value4){
	  startTime = value2;
	  endTime = value3;
	  delayTime = value4;
  }
  
   public static int getStartTime() {
		return startTime;
   }
   public static int getEndTime() {
		return endTime;
   }
	
   public static int getDelayTime() {
	   return delayTime;
   }
}
