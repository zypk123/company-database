package cy.its.trafficSituation.rest.action;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;

/**
 * @author 
 *
 */
public interface ITrafficAllEventAction {
   
	/** 
	* @Description: 根据传入的KEYS获取所有字典数据
	* @param @param alarmType 1 气象 2是能见度 3 路感 4 设备检测，deviceSysNbr 设备编码
	* @param @return 设定文件 
	* @return Map 返回类型  带分页的数据
	* @throws 
	*/
	public JSONObject  queryAllTrafficAlarmEvent(String alarmType,String deviceSysNbr,String startDate,String endDate,String curPage,String pageSize);
	
}
