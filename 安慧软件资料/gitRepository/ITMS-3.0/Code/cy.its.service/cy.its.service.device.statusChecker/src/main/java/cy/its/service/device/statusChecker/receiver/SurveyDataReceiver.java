package cy.its.service.device.statusChecker.receiver;

import cy.its.service.common.ConstValue;
import cy.its.service.common.JsonUtil;
import cy.its.service.common.ioc.Export;
import cy.its.service.common.ioc.Import;
import cy.its.service.common.rabbitmqClient.QueueHandler;
import cy.its.service.device.statusChecker.model.SurveyData;
import cy.its.service.device.statusChecker.util.ISysManager;

@Export
public class SurveyDataReceiver implements StatusReceiver {
	
	@Import
	ISysManager sysManager;

	Class<SurveyData> clazz = SurveyData.class;
	
	@Override
	public Boolean receive(String routingKey, String message) {
		try {
			SurveyData s = JsonUtil.parseObject(message, clazz);
			sysManager.receiveSurveyData(s);
			
		} catch (Throwable e) {
		}
		
		return ConstValue.BOOL_TRUE;
	}

	@Override
	public QueueHandler queueHandler() {
		return new QueueHandler("StatusChecker_SurveyData", false, true,
				new String[] { 
						ConstValue.ROUTE_KEY_STANDARD_FLOW, 
						ConstValue.ROUTE_KEY_STANDARD_PASS,
						ConstValue.ROUTE_KEY_STANDARD_VIO,
						ConstValue.ROUTE_KEY_STANDARD_VISIBILITY,
						ConstValue.ROUTE_KEY_STANDARD_ROAD_SENSOR,
						ConstValue.ROUTE_KEY_STANDARD_WEATHER,
						ConstValue.ROUTE_KEY_STANDARD_EVENT
						},
				this);
	}

}
