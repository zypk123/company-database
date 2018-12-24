package cy.its.service.standardization.dataMaker;

import cy.its.service.common.ConstValue;
import cy.its.service.common.DateUtil;
import cy.its.service.common.dataModel.SurveyUpgrade_ServerStatus;
import cy.its.service.common.ioc.Export;
import cy.its.service.common.ioc.Import;
import cy.its.service.standardization.dataMaker.originalModel.SurveyUpgrade_HostStatusInfo;
import cy.its.service.standardization.dictionary.ServerCache;
import cy.its.service.standardization.validator.BaseValidator;

@Export
public class SurveyUpgrade_ServerStatusMaker
		extends DataArrayMaker<SurveyUpgrade_HostStatusInfo, SurveyUpgrade_ServerStatus> {

	@Import
	ServerCache serverCache;

	public SurveyUpgrade_ServerStatusMaker() {
		super("服务器状态[监控服务器升级改造]", SurveyUpgrade_HostStatusInfo.class, SurveyUpgrade_ServerStatus.class,
				ConstValue.ROUTE_KEY_ORIGINAL_SERVER_STATUS_FOR_UPGRADE,
				ConstValue.ROUTE_KEY_STANDARD_SERVER_STATUS_FOR_UPGRADE, "original_server_status", false);
	}

	String UNREG = "服务器状态所属服务器未注册";

	@Override
	SurveyUpgrade_ServerStatus translate(SurveyUpgrade_HostStatusInfo input) {
		if (serverCache.isExisted(input.getHostIP())) {
			SurveyUpgrade_ServerStatus status = new SurveyUpgrade_ServerStatus();
			status.setHostIP(input.getHostIP());
			status.setHostName(input.getHostName());
			status.setSoftVersion(input.getSoftVersion());
			status.setHostStartTime(DateUtil.parseDateTime(input.getHostStartTime()));
			status.setHostCurrTime(DateUtil.parseDateTime(input.getHostCurrTime()));
			status.setNetworkUsage(input.getNetworkUsage());
			status.setMemoryTotal(input.getMemoryTotal());
			status.setMemoryUsage(input.getMemoryUsage());
			status.setCpuUsage(input.getCpuUsage());
			status.setInputPerSec(input.getInputPerSec());
			status.setOutputPerSec(input.getOutputPerSec());
			status.setTimeResult(input.getTimeResult());
			status.setExtendedProperties(input.getExtendedProperties());
			status.setMsgType(input.getMsgType());
			status.setReportCycle(input.getReportCycle());

			return status;
		} else {
			input.validateResult = UNREG;
		}

		return null;
	}

	@Override
	BaseValidator<SurveyUpgrade_ServerStatus> validator() {
		return null;
	}

}
