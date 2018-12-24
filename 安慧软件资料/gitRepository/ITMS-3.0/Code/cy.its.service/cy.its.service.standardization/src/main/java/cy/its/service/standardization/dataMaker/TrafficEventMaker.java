package cy.its.service.standardization.dataMaker;

import java.util.HashMap;
import java.util.Map;

import cy.its.service.common.ConstValue;
import cy.its.service.common.DateUtil;
import cy.its.service.common.dataModel.TrafficEvent;
import cy.its.service.common.ioc.Export;
import cy.its.service.common.ioc.Import;
import cy.its.service.standardization.dataMaker.originalModel.EventType;
import cy.its.service.standardization.dataMaker.originalModel.FrontTrafficEvent;
import cy.its.service.standardization.dictionary.SurveyUpgrade_DeviceCache;
import cy.its.service.standardization.dictionary.model.IDeviceInfo;
import cy.its.service.standardization.validator.BaseValidator;
import cy.its.service.standardization.validator.TrafficEventValidator;

@Export
public class TrafficEventMaker extends DataMaker<FrontTrafficEvent, TrafficEvent> {

//	@Import
//	DeviceCache deviceCache;

	@Import
	SurveyUpgrade_DeviceCache surveyUpgrade_DeviceCache;

	Map<EventType, String> eventType;

	/**
	 * 交通事件
	 */
	String CODE_TYPE_EVENT_TYPE = "434";

	public TrafficEventMaker() {
		super("交通事件", FrontTrafficEvent.class, TrafficEvent.class, ConstValue.ROUTE_KEY_ORIGINAL_EVENT,
				ConstValue.ROUTE_KEY_STANDARD_EVENT, "original_traffic_event");

		eventType = new HashMap<EventType, String>(11);
		eventType.put(EventType.CONGESTION, "0"); // 拥堵
		eventType.put(EventType.PARKING, "1"); // 停车
		eventType.put(EventType.INVERSE, "2"); // 逆行
		eventType.put(EventType.PEDESTRIAN, "3"); // 行人
		eventType.put(EventType.DEBRIS, "4"); // 遗留物抛洒物碎片
		eventType.put(EventType.SMOKE, "5"); // 烟雾
		eventType.put(EventType.OVERLINE, "6"); // 压线
		eventType.put(EventType.VEHICLECONTROLLIST, "7"); // 黑名单数据
		eventType.put(EventType.SPEED, "8"); // 超速
		eventType.put(EventType.LANECHANGE, "9"); // 变道
		eventType.put(EventType.TURNAROUND, "10"); // 掉头
	}

	@Override
	TrafficEvent translate(FrontTrafficEvent input) {
		IDeviceInfo di = surveyUpgrade_DeviceCache.get(input.getDeviceNo());
		if (di != null) {
			TrafficEvent evt = new TrafficEvent();
			evt.setDeviceId(di.getDeviceId());
			evt.setOrgCode(di.getOrgCode());
			evt.setDeviceSysNbr(di.getSysNbr());
			evt.setSiteCode(di.getSiteCode());
			evt.setAlarmEventType(CODE_TYPE_EVENT_TYPE);
			evt.setSubAlarmEvent(eventType.get(input.getType()));
			evt.setStartAlarmTime(DateUtil.parseDate(input.getTime()));
			evt.setAlarmDesc(input.getDesc());
			evt.setOrgPrivilegeCode(di.getOrgPrivilegeCode());
			return evt;
		} else {
			input.validateResult = STR_DEVICE_UNREG;
		}

		return null;
	}

	@Override
	BaseValidator<TrafficEvent> validator() {
		return new TrafficEventValidator();
	}

}
