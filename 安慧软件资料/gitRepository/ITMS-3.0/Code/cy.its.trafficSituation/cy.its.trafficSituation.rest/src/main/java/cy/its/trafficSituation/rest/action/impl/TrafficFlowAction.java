package cy.its.trafficSituation.rest.action.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import cy.its.trafficSituation.domain.model.TrafficDeviceFlow;
import cy.its.trafficSituation.domain.service.ITrafficFlowService;
import cy.its.trafficSituation.domain.service.impl.TrafficFlowService;
import cy.its.trafficSituation.rest.action.ITrafficFlowAction;

@RestController
@RequestMapping("/trafficSituation/TrafficFlowAction")
public class TrafficFlowAction implements ITrafficFlowAction{
	
	@Autowired
	ITrafficFlowService trafficFlowService;
	
	@Override
	@RequestMapping("/queryTrafficFlow")
	public JSONObject queryTrafficFlow(String deviceSysNbr, String startDate, String endDate) {
		
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
		// TODO Auto-generated method stub
		List<TrafficDeviceFlow> lst = null;
		try {
			 lst = trafficFlowService.countDeviceFlow(deviceSysNbr, startDt, endDt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		if(lst != null && lst.size() != 0 ){
//			for(int i = 0;i<lst.size();i++){
//				series[i] = lst.get(i).getAvgSpeed();
//			}
//		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", lst);
		System.out.println(jsonObject);
		return jsonObject;
	}

}
