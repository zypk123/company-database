package cy.its.service.device.faultMaintain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cy.its.service.common.ConstValue;
import cy.its.service.common.JsonUtil;
import cy.its.service.common.StringUtil;
import cy.its.service.common.dataModel.DeviceFault;
import cy.its.service.common.dataModel.SurveyUpgrade_DeviceStatus;
import cy.its.service.common.log.LogManager;
import cy.its.service.common.rabbitmqClient.IReceiver;

public class FaultStatusReceiver implements IReceiver {

	/**
	 * 故障码
	 */
	Map<String, String> faultMap;
	
	/**
	 * 故障码分类
	 */
	Map<String, String> faultTypeMap;
	
	public FaultStatusReceiver() {
		faultMap = new HashMap<String,String>();
		faultTypeMap = new HashMap<String,String>();
		
		faultMap.put("11000","补光异常");
		faultMap.put("11001","相机无联系");
		faultMap.put("11002","相机无图片");
		faultMap.put("11003","没有图像信号");
		faultMap.put("11004","相机数据错误");
		faultMap.put("12000","无雷达信号");
		faultMap.put("12001","雷达无联系");
		faultMap.put("12002","雷达无测速");
		faultMap.put("12003","雷达数据错误");
		faultMap.put("12004","高频率分机出错");
		faultMap.put("12005","放大电路故障");
		faultMap.put("12006","CPU故障");
		faultMap.put("13000","交流供电中断");
		faultMap.put("13001","ups电池电压偏低");
		faultMap.put("13002","ups失效");
		faultMap.put("13003","没有ups信号");
		faultMap.put("14000","线圈无联系");
		faultMap.put("14001","没有线圈信号");
		faultMap.put("14002","线圈数据故障");
		faultMap.put("15000","无法建立网络连接");
		faultMap.put("15001","无法连接服务器");
		faultMap.put("15002","接受服务器数据异常");
		faultMap.put("16000","无法打开GPS模块端口");
		faultMap.put("16001","无法接收GPS数据");
		faultMap.put("16002","GPS数据解析错误");
		faultMap.put("16003","GPS校时错误");
		faultMap.put("17000","能见度仪无传感器");
		faultMap.put("17001","重度污染");
		faultMap.put("17002","USB缺失或故障");
		faultMap.put("17003","端口打开故障");
		faultMap.put("17004","时间异常");
		faultMap.put("18000","LED端口打开故障");
		faultMap.put("18001","LED无数据");
		faultMap.put("99999","其他子故障");

		faultTypeMap.put("11","相机子故障");
		faultTypeMap.put("12","雷达子故障");
		faultTypeMap.put("13","电源子故障");
		faultTypeMap.put("14","线圈子故障");
		faultTypeMap.put("15","网络子故障");
		faultTypeMap.put("16","GPS故障子故障");
		faultTypeMap.put("17","能见度仪子故障");
		faultTypeMap.put("18","LED子故障");
		faultTypeMap.put("99","其他故障");

	}
	
	/**
	 * 新状态类型
	 */
	Class<SurveyUpgrade_DeviceStatus> clazz = SurveyUpgrade_DeviceStatus.class;
	
	/**
	 * 10字符串
	 */
	String TEN = "10";

	/**
	 * 故障报警类型 ： 1 故障
	 */
	String FAULT = "1";
	/**
	 * 采集方式。1-前端设备上传
	 */
	String COLLECT_METHOD = "1";
	
	String ERR_MSG = "故障数据处理失败:%s;数据:%s";
    String DUBUG_MSG = "接收故障数据:%s";

	@Override
	public Boolean receive(String routingKey, String message) {
		try {
			SurveyUpgrade_DeviceStatus status = JsonUtil.parseObject(message, clazz);
			if (status != null && 
				status.getStatusTime() != null &&
				!StringUtil.isNullOrEmpty(status.getDeviceSysId()) &&
				!StringUtil.isNullOrEmpty(status.getOrgPrivilegeCode())) {
				if (status.getStatusCode() == ConstValue.INT_0 && 
					status.getFaultCodes() != null &&
					status.getFaultCodes().size() > ConstValue.INT_0) {	
					boolean logged = false;
					for (String faultCode : status.getFaultCodes()) {
						if (faultMap.containsKey(faultCode)) {
							if(!logged) {
								logged = true;
							    LogManager.debug(String.format(DUBUG_MSG, message));
							}
							FaultWriter.write(convertToFault(status, faultCode));
						}
					}
				}
			}

			status = null;
		} catch (Throwable e) {
			LogManager.error(String.format(ERR_MSG, StringUtil.getErrorDetail(e), message));
		}

		return ConstValue.BOOL_TRUE;
	}

	/**
	 * 故障转换
	 * @param status
	 * @param faultCode
	 * @return
	 */
	private DeviceFault convertToFault(SurveyUpgrade_DeviceStatus status, String faultCode) {
		DeviceFault fault = new DeviceFault();
		fault.setDeviceId(status.getDeviceSysId());
		fault.setSysComponentId(status.getCameraId());
		fault.setFaultEventTime(new Date(status.getStatusTime()));
		fault.setFaultAlarm(FAULT);
		fault.setFaultType(faultCode.substring(ConstValue.INT_0, ConstValue.INT_2));
		fault.setFaultSubType(faultCode);
		fault.setCollectMethod(COLLECT_METHOD);
		fault.setRemark(String.format("%s:%s", faultTypeMap.get(fault.getFaultType()),
				faultMap.get(fault.getFaultSubType())));
		fault.setOrgPrivilegeCode(status.getOrgPrivilegeCode());
		
		return fault;
	}

}
