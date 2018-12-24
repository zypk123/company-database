package cy.its.service.device.statusAnalysis.util;

import java.util.List;

import cy.its.service.common.dataModel.DeviceStatus;
import cy.its.service.device.statusAnalysis.data.SurveyData;
import cy.its.service.device.statusAnalysis.model.DeviceCfg;

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
	 * 处理监测数据
	 * 用于确定最新的数据上传时间
	 * @param data
	 */
	void handleSurveyData(SurveyData data);

	/**
	 * 处理设备心跳
	 * @param heart
	 */
	void handleStatus(DeviceStatus heart);

	/**
	 * 发布所有状态
	 */
	void publishAllStatus();

	/**
	 * 检查所有设备系统的变化情况
	 */
	void checkChangeOfSysCfg();

}