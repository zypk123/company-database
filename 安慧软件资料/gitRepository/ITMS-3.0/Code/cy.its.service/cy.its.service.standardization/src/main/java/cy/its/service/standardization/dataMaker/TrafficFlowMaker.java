package cy.its.service.standardization.dataMaker;

import java.math.BigDecimal;

import cy.its.service.common.ConstValue;
import cy.its.service.common.DateUtil;
import cy.its.service.common.StringUtil;
import cy.its.service.common.dataModel.TrafficFlow;
import cy.its.service.common.ioc.Export;
import cy.its.service.common.ioc.Import;
import cy.its.service.standardization.dataMaker.originalModel.TrafficStats;
import cy.its.service.standardization.dictionary.DirectCache;
import cy.its.service.standardization.dictionary.RegionCache;
import cy.its.service.standardization.dictionary.SurveyUpgrade_DeviceCache;
import cy.its.service.standardization.dictionary.model.DeviceRegion;
import cy.its.service.standardization.dictionary.model.IDeviceInfo;
import cy.its.service.standardization.validator.BaseValidator;
import cy.its.service.standardization.validator.TrafficFlowValidator;

@Export
public class TrafficFlowMaker extends DataMaker<TrafficStats, TrafficFlow> {

//	@Import
//	DeviceCache deviceCache;

	@Import
	RegionCache regionCache;

	@Import
	SurveyUpgrade_DeviceCache surveyUpgrade_DeviceCache;
	
	@Import
	DirectCache directCache;

	public TrafficFlowMaker() {
		super("流量", TrafficStats.class, TrafficFlow.class, ConstValue.ROUTE_KEY_ORIGINAL_FLOW,
				ConstValue.ROUTE_KEY_STANDARD_FLOW, "original_traffic_flow");
	}

	@Override
	TrafficFlow translate(TrafficStats input) {
		if (input.getOtherTrafficParams() != null) {
			String devKey = input.getOtherTrafficParams().get(ConstValue.DEVICE_KEY);
			if (!StringUtil.isNullOrEmpty(devKey)) {
				IDeviceInfo dInfo = surveyUpgrade_DeviceCache.get(devKey);
				if (dInfo != null) {
					return convert(input, dInfo);
				} else {
					input.validateResult = STR_DEVICE_UNREG;
				}
			} else {
				input.validateResult = STR_EXT_NOKEY;
			}
		} else {
			input.validateResult = STR_EXT_NULL;
		}

		return null;
	}

	/**
	 * 区间产生的流量
	 * 
	 * @param input
	 * @param region
	 * @return
	 */
	@SuppressWarnings("unused")
	private TrafficFlow convert(TrafficStats input, DeviceRegion region) {
		TrafficFlow flow = new TrafficFlow();
		flow.setSiteCode(region.getExitsitecode());
		flow.setDeviceSysNbr(region.getRegionalcode());
		flow.setDirectionType(region.getDirectiontype());
		flow.setOrgPrivilegeCode(region.getOrgprivilege());
		flow.setDeviceNbr(region.getRegionalcode());
		formatCount(input, flow);
		return flow;
	}

	/**
	 * 单点产生的流量
	 * 
	 * @param input
	 * @param devInfo
	 * @return
	 */
	private TrafficFlow convert(TrafficStats input, IDeviceInfo devInfo) {
		TrafficFlow flow = new TrafficFlow();
		flow.setSiteCode(devInfo.getSiteCode());
		flow.setDeviceSysNbr(devInfo.getSysNbr());
		flow.setDirectionType(directCache.getDirectType(
				devInfo.getSiteId(), input.getDriveDirection()));
		flow.setOrgPrivilegeCode(devInfo.getOrgPrivilegeCode());
		flow.setDeviceNbr(devInfo.getDeviceNbr());
		flow.setDeviceId(devInfo.getDeviceId());
		flow.setSysComponentId(devInfo.getSysComponentId());
		formatCount(input, flow);
		return flow;
	}

	/**
	 * 计算流量统计
	 * 
	 * @param input
	 * @param flow
	 */
	private void formatCount(TrafficStats input, TrafficFlow flow) {
		flow.setLane(String.valueOf(input.getDriveLane()));
		flow.setCountTime(DateUtil.parseDate(input.getStatsTime()));
		flow.setPeriod(input.getStatsPeriod());
		flow.setTotalNum(input.getVehicleTotal());
		flow.setAvrSpeed(
				BigDecimal.valueOf(input.getVehicleSpeed()).setScale(ConstValue.INT_2, BigDecimal.ROUND_HALF_UP));
		flow.setAvrLength(
				BigDecimal.valueOf(input.getVehicleLength()).setScale(ConstValue.INT_2, BigDecimal.ROUND_HALF_UP));
		flow.setTimePercent(
				BigDecimal.valueOf(input.getTimeOccupyRatio()).setScale(ConstValue.INT_2, BigDecimal.ROUND_HALF_UP));
		flow.setSpacePercent(
				BigDecimal.valueOf(input.getSpaceOccupyRatio()).setScale(ConstValue.INT_2, BigDecimal.ROUND_HALF_UP));
		flow.setVehSpaceHeadway(
				BigDecimal.valueOf(input.getSpaceHeadway()).setScale(ConstValue.INT_2, BigDecimal.ROUND_HALF_UP));
		flow.setVehTimeHeadway(
				BigDecimal.valueOf(input.getTimeHeadway()).setScale(ConstValue.INT_2, BigDecimal.ROUND_HALF_UP));
		flow.setVehDensity(BigDecimal.valueOf(input.getDensity()).setScale(ConstValue.INT_2, BigDecimal.ROUND_HALF_UP));
		flow.setLowSpeedVehNum(
				BigDecimal.valueOf(input.getUnderSpeedTotal()).setScale(ConstValue.INT_2, BigDecimal.ROUND_HALF_UP));
		flow.setHighSpeedVehNum(
				BigDecimal.valueOf(input.getOverSpeedTotal()).setScale(ConstValue.INT_2, BigDecimal.ROUND_HALF_UP));
		flow.setLargeNum(input.getLargeVehicleTotal());
		flow.setMiddleNum(input.getMiddleVehicleTotal());
		flow.setSmallNum(input.getSmallVehicleTotal());
		flow.setMotorNum(input.getMotorVehicleTotal());
		flow.setSupperLenVehNum(
				BigDecimal.valueOf(input.getLongVehicleTotal()).setScale(ConstValue.INT_2, BigDecimal.ROUND_HALF_UP));
		flow.setOtherNum(input.getVehicleTotal() - input.getLargeVehicleTotal() - input.getMiddleVehicleTotal()
				- input.getSmallVehicleTotal() - input.getMotorVehicleTotal() - input.getLongVehicleTotal());
//		flow.setDeviceTime(flow.getCountTime().getTime());
	}

	@Override
	BaseValidator<TrafficFlow> validator() {
		return new TrafficFlowValidator();
	}
}
