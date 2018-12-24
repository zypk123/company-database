package cy.its.service.standardization.dataMaker;

import cy.its.service.common.ConstValue;
import cy.its.service.common.DateUtil;
import cy.its.service.common.dataModel.SurveyUpgrade_ServiceStatus;
import cy.its.service.common.ioc.Export;
import cy.its.service.common.ioc.Import;
import cy.its.service.standardization.dataMaker.originalModel.SurveyUpgrade_ProcessStatusInfo;
import cy.its.service.standardization.dictionary.ServerCache;
import cy.its.service.standardization.validator.BaseValidator;

@Export
public class SurveyUpgrade_ProcessStatusMaker
		extends DataArrayMaker<SurveyUpgrade_ProcessStatusInfo, SurveyUpgrade_ServiceStatus> {

	@Import
	ServerCache serverCache;

	public SurveyUpgrade_ProcessStatusMaker() {
		super("服务状态[监控服务器升级改造]", SurveyUpgrade_ProcessStatusInfo.class, SurveyUpgrade_ServiceStatus.class,
				ConstValue.ROUTE_KEY_ORIGINAL_PROCESS_STATUS_FOR_UPGRADE,
				ConstValue.ROUTE_KEY_STANDARD_PROCESS_STATUS_FOR_UPGRADE, "original_process_status", false);
	}

	String UNREG = "服务状态所属服务器未注册";

	@Override
	SurveyUpgrade_ServiceStatus translate(SurveyUpgrade_ProcessStatusInfo input) {
		if (serverCache.isExisted(input.getHostIp())) {
			SurveyUpgrade_ServiceStatus status = new SurveyUpgrade_ServiceStatus();
			status.setHostIp(input.getHostIp());
			status.setProcessID(input.getProcessID());
			status.setProcessName(input.getProcessName());
			status.setProcessDesc(input.getProcessDesc());

			status.setProcessStartTime(DateUtil.parseDateTime(input.getProcessStartTime()));
			status.setUserName(input.getUserName());
			status.setExpires(input.getExpires());
			status.setMessageType(input.getMessageType());
			status.setSoftVersion(input.getSoftVersion());
			status.setStatusGenTime(DateUtil.parseDateTime(input.getStatusGenTime()));
			status.setProcessStatus(input.getProcessStatus());
			status.setProcessMemory(input.getProcessMemory());
			status.setProcessCpu(input.getProcessCpu());
			status.setHandleNum(input.getHandleNum());
			status.setDoRecordNum(input.getDoRecordNum());
			status.setTypeNums(input.getTypeNums());
			status.setCountStartTime(DateUtil.parseDateTime(input.getCountStartTime()));
			status.setCountCycle(input.getCountCycle());
			status.setReportCycle(input.getReportCycle());
			status.setExtendedProperties(input.getExtendedProperties());

			return status;
		} else {
			input.validateResult = UNREG;
		}

		return null;
	}

	@Override
	BaseValidator<SurveyUpgrade_ServiceStatus> validator() {
		return null;
	}

}
