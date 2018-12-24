package cy.its.service.device.statusChecker.util;

import java.util.List;

import cy.its.service.common.dataModel.SurveyUpgrade_DeviceStatus;
import cy.its.service.device.statusChecker.model.DeviceCfg;
import cy.its.service.device.statusChecker.model.SurveyData;

/**
 * @Title: ISysManager.java
 * @Package cy.its.service.device.statusAnalysis.util
 * @Description: 设备系统管理接口定义
 * @author STJ lijun@cychina.cn
 * @date 2015年11月9日 下午5:00:33
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */
public interface ISysManager {

	/**
	 * 开始处理
	 * @param lstDevCfg
	 */
	void start(List<DeviceCfg> lstDevCfg);

	/**
	 * 停止处理
	 */
	void stop();

	/**
	 *  获取状态分析关注的设备类型
	 * @return
	 */
	String[] focusDeviceTypes();

	/**
	 * 处理设备心跳
	 * @param heart
	 * @throws Exception 
	 */
	void handleStatus(SurveyUpgrade_DeviceStatus heart) throws Exception;

	/**
	 * 发布所有状态
	 */
	void publishAllStatus();

	/**
	 * 检查所有设备系统的变化情况
	 */
	void checkChangeOfSysCfg();
	
	/**
	 * 接收设备监测数据
	 * @param surveyData
	 */
	void receiveSurveyData(SurveyData surveyData);

}