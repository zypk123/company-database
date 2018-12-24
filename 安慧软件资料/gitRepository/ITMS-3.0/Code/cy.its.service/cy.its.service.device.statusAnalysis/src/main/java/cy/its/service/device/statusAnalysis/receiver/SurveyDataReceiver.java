package cy.its.service.device.statusAnalysis.receiver;

import cy.its.service.common.ConstValue;
import cy.its.service.common.JsonUtil;
import cy.its.service.common.StringUtil;
import cy.its.service.common.ioc.Export;
import cy.its.service.common.ioc.Import;
import cy.its.service.common.log.LogManager;
import cy.its.service.common.rabbitmqClient.QueueHandler;
import cy.its.service.device.statusAnalysis.data.SurveyData;
import cy.its.service.device.statusAnalysis.util.ISysManager;
import cy.its.service.device.statusAnalysis.util.StatusUtil;

/**
 * @Title: SurveyDataReceiver.java
 * @Package cy.its.service.device.statusAnalysis.receiver
 * @Description: 接收设备过车 违法 流量等数据
 * @author STJ lijun@cychina.cn
 * @date 2015年11月9日 上午11:29:48
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 *      Company: 安徽超远信息技术有限公司 Copyright: Copyright (c) 2015
 */
@Export
public class SurveyDataReceiver implements StatusReceiver {

	@Import
	ISysManager sysManger;
	
	Class<SurveyData> clazz = SurveyData.class;

	@Override
	public Boolean receive(String routingKey, String message) {
		try {
			SurveyData data = JsonUtil.parseObject(message, clazz);
			if (data != null && !StringUtil.isNullOrEmpty(data.getDeviceSysNbr()) && 
				!StatusUtil.isOldData(data.getDeviceTime())) {
				sysManger.handleSurveyData(data);
			}
		} catch (Throwable e) {
			LogManager.error(e);
		}
		return ConstValue.BOOL_TRUE;
	}

	@Override
	public QueueHandler queueHandler() {
		return new QueueHandler("StatusAnalysis_SurveyData", false, true,
				new String[] { ConstValue.ROUTE_KEY_STANDARD_FLOW, ConstValue.ROUTE_KEY_STANDARD_PASS,
						ConstValue.ROUTE_KEY_STANDARD_VIO },
				this);
	}

}
