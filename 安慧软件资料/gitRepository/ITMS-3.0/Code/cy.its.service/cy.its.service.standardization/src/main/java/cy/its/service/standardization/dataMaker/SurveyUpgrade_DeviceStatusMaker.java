package cy.its.service.standardization.dataMaker;

import java.util.Arrays;

import cy.its.service.common.ConstValue;
import cy.its.service.common.DateUtil;
import cy.its.service.common.JsonUtil;
import cy.its.service.common.StringUtil;
import cy.its.service.common.dataModel.SurveyUpgrade_DeviceStatus;
import cy.its.service.common.ioc.Export;
import cy.its.service.common.ioc.Import;
import cy.its.service.common.log.LogManager;
import cy.its.service.standardization.dataMaker.originalModel.SurveyUpgrade_DeviceStatusInfo;
import cy.its.service.standardization.dictionary.SurveyUpgrade_DeviceCache;
import cy.its.service.standardization.dictionary.model.IDeviceInfo;
import cy.its.service.standardization.validator.BaseValidator;

@Export
public class SurveyUpgrade_DeviceStatusMaker
		extends DataArrayMaker<SurveyUpgrade_DeviceStatusInfo, SurveyUpgrade_DeviceStatus> {

	@Import
	SurveyUpgrade_DeviceCache surveyUpgrade_DeviceCache;

	public SurveyUpgrade_DeviceStatusMaker() {
		super("设备状态[监控服务器升级改造]", SurveyUpgrade_DeviceStatusInfo.class, SurveyUpgrade_DeviceStatus.class,
				ConstValue.ROUTE_KEY_ORIGINAL_STATUS_FOR_UPGRADE, ConstValue.ROUTE_KEY_STANDARD_STATUS_FOR_UPGRADE,
				"original_device_status_for_upgrade", false);// "original_device_status_forSurveyUpgrade"
	}

	String STR_DEVICE_UNREG = "设备信息未注册";

	@Override
	SurveyUpgrade_DeviceStatus translate(SurveyUpgrade_DeviceStatusInfo input) {
		IDeviceInfo dev = surveyUpgrade_DeviceCache.get(input.getDeviceID());
		if (dev != null) {
			SurveyUpgrade_DeviceStatus status = new SurveyUpgrade_DeviceStatus();

			// 检查前端发送的设备编号和本地系统编号或组件编号是否一致
			if (ConstValue.STR_ONE.equals(dev.getDtype())) {
				// 匹配到的设备配置为系统
				if (!dev.getSysNbr().equals(input.getDeviceNo())) {
					LogManager.warn(String.format("设备状态[监控服务器升级改造]转换警告, 前端设备配置设备编号与本地不一致, 本地系统ID:%s, 编号:%s, 前端数据:%s",
							dev.getDeviceId(), dev.getSysNbr(), JsonUtil.serialize(input)));
				}
			} else {
				// 匹配到的设备配置为组件
				if (!dev.getDeviceNbr().equals(input.getDeviceNo())) {
					LogManager.warn(String.format("设备状态[监控服务器升级改造]转换, 前端设备配置设备编号与本地不一致, 本地组件ID:%s, 编号:%s, 前端数据:%s",
							dev.getSysComponentId(), dev.getDeviceNbr(), JsonUtil.serialize(input)));
				}
			}

			status.setDeviceKey(input.getDeviceID());
			status.setDeviceSysId(dev.getDeviceId());
			status.setDeviceSysNbr(dev.getSysNbr());
			status.setCameraNbr(dev.getDeviceNbr());
			status.setCameraId(dev.getSysComponentId());
			status.setStatusCode(input.getDeviceStatus());
			if (!StringUtil.isNullOrEmpty(input.getFaultCode())
					&& !StringUtil.isNullOrEmpty(input.getFaultCode().trim())) {
				status.setFaultCodes(Arrays.asList(input.getFaultCode().trim().split(ConstValue.COMMA)));
			}

			if (!StringUtil.isNullOrEmpty(input.getStatusTime())) {
				status.setStatusTime(DateUtil.parseDateTime(input.getStatusTime()));
			}

			if (!StringUtil.isNullOrEmpty(input.getDeviceStartTime())) {
				status.setStartTime(DateUtil.parseDateTime(input.getDeviceStartTime()));
			}

			if (!StringUtil.isNullOrEmpty(input.getDeviceCurrTime())) {
				status.setDeviceTime(DateUtil.parseDateTime(input.getDeviceCurrTime()));
			}
			
			status.setTimeDiff(input.getTimeDiff());

			status.setExtendedProperties(input.getExtendedProperties());

			status.setOrgPrivilegeCode(dev.getOrgPrivilegeCode());

			return status;
		} else {
			input.validateResult = STR_DEVICE_UNREG;
		}

		return null;
	}

	@Override
	BaseValidator<SurveyUpgrade_DeviceStatus> validator() {
		return null;
	}

}
