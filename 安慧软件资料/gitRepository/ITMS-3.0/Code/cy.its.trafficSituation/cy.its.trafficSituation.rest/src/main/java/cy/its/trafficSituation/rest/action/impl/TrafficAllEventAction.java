package cy.its.trafficSituation.rest.action.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cy.its.com.util.StringUtil;
import cy.its.device.domain.model.Meteorologic;
import cy.its.device.domain.service.ISiteService;
import cy.its.device.domain.service.ISystemService;
import cy.its.trafficSituation.domain.service.ITrafficAllEventService;
import cy.its.trafficSituation.rest.action.ITrafficAllEventAction;

@RestController
@RequestMapping("/trafficSituation/trafficAllEventAction")
public class TrafficAllEventAction implements ITrafficAllEventAction {

	@Autowired
	ITrafficAllEventService trafficAllEventService;

	@Autowired
	ISiteService siteService;
	
	@Autowired
	ISystemService SystemService;

 
	
 
	@Override
	@RequestMapping("/queryAllTrafficAlarmEvent")
	public JSONObject queryAllTrafficAlarmEvent(@RequestParam(value = "alarmType", required = true) String  alarmType,
			@RequestParam(value = "deviceSysNbr", required = true) String   deviceSysNbr,
			@RequestParam(value = "startDate") String   startDate,@RequestParam(value = "endDate") String   endDate
			,@RequestParam(value = "pageNumber") String   pageNumber,@RequestParam(value = "pageSize") String   pageSize) {
		Map map = new HashMap();
		Date startDt = null;
		Date endDt = null;
		SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (startDate != null && endDate != null) {
			try {
				startDt = sdft.parse(startDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				endDt = sdft.parse(endDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		map.put("alarmType", alarmType);
		map.put("deviceSysNbr", deviceSysNbr);
		map.put("startTime", startDt);
		map.put("endTime", endDt);

		Page pageRs= null;
		PageHelper.startPage(Integer.valueOf(pageNumber), Integer.valueOf(pageSize));
		List events = null;
		JSONObject jsonObj = new JSONObject();
		if(StringUtil.isNullOrEmpty(alarmType)){
			jsonObj.put("info", "请输入事件类型数据");
		}else{
			//map = new HashMap();
			if(alarmType.equals("1")){
				pageRs = (Page) trafficAllEventService.queryWeatherEvent(map);
			}else if(alarmType.equals("2")){
				pageRs =  (Page) trafficAllEventService.queryVisibilityEvent(map);
			}else if(alarmType.equals("3")){
				pageRs = (Page) trafficAllEventService.queryRoadSensorEvent(map);
			}else if(alarmType.equals("4")){
				pageRs = (Page) trafficAllEventService.queryTrafficEvent(map);
			}else{
				jsonObj.put("info", "暂时没有该类型数据！");
			}
		}
		
		JSONArray   lst  =  JSONArray.parseArray(JSONArray.toJSONString(pageRs.getResult()));
		String startAlarmTime = null;
		if (lst != null && lst.size() != 0) {
			for (int i = 0; i < lst.size(); i++) {
				JSONObject    jsonobj  =  (JSONObject)lst.get(i);
				// 根据siteCode获取siteName
				String siteCode = jsonobj.getString("siteCode");
				if (!StringUtil.isNullOrEmpty(siteCode)) {
					String siteName = siteService.siteOfCode(siteCode).getSiteName();
					jsonobj.put("siteName", siteName);
				}
				// Date转String
				if(jsonobj.getDate("startAlarmTime") != null) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					startAlarmTime = sdf.format(jsonobj.getDate("startAlarmTime"));
					jsonobj.put("startAlarmTime",startAlarmTime);
				}
			}
		}
		jsonObj.put("error","");
		JSONObject jsonObject = new JSONObject();
		if(pageRs == null){
			jsonObject.put("total", 0);
		}else{
			jsonObject.put("total", pageRs.getTotal());
		}
		jsonObject.put("rows", lst);
		jsonObj.put("result", jsonObject);
		return jsonObj;
	}
	
	//根据deviceId判断设备支持的气象采集类型并返回
	@RequestMapping("/queryMeteorologicType")
	public String queryMeteorologicType(@RequestParam(value = "deviceId" ,required = true) String deviceId)
	{   
		String meteorologicType = null;
		Meteorologic meteorologic = new Meteorologic();
		meteorologic = SystemService.meteorologicOfId(deviceId);
		if("1".equals(meteorologic.getIsWeatherSupport())){
			meteorologicType = "1";
		}else if("1".equals(meteorologic.getIsVisibilitySupport())){
			meteorologicType = "2";
		}else if("1".equals(meteorologic.getIsRoadsensorSupport())){
			meteorologicType = "3";
		}
		 
		return meteorologicType; 
	}
}
