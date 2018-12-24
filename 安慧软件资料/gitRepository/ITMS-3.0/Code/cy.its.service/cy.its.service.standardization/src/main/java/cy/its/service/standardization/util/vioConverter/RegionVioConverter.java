package cy.its.service.standardization.util.vioConverter;

import cy.its.service.common.ConstValue;
import cy.its.service.common.StringUtil;
import cy.its.service.common.dataModel.Violation;
import cy.its.service.common.ioc.Export;
import cy.its.service.standardization.dataMaker.originalModel.ViolationVehicle;
import cy.its.service.standardization.dictionary.model.DeviceRegion;

@Export
public class RegionVioConverter extends VioConverter {

	public Violation convert(ViolationVehicle v, DeviceRegion region) {
		Violation info = new Violation();
		formatRegion(region, info);
		formatRegionLimit(v, region, info);
		commonFormat(v, info, ConstValue.STR_REGIONAL);

		// info.setJunctionCode(region.getRoadSegCode());
		// info.setRedLightEndTime();
		// info.setSpeedingType();
		// info.setSpecialVehType();
		// info.setVideo();
		// info.setRemark();

		return info;
	}

	/**
	 * 获取区间限速值
	 * 
	 * @param v
	 * @param region
	 * @param info
	 */
	private void formatRegionLimit(ViolationVehicle v, DeviceRegion region, Violation info) {
		// 计算限速值
		if (!formatSpeedLimit(v, info)) {
			if (region.getLimitlarge() != null) {
				info.setLimitLarge(region.getLimitlarge().intValue());
			}

			if (region.getLimitsmall() != null) {
				info.setLimitSmall(region.getLimitsmall().intValue());
			}

			if (region.getLimitlower() != null) {
				info.setLimitLower(region.getLimitlower().intValue());
			}
		}
	}

	/**
	 * 抽取区间信息
	 * 
	 * @param region
	 * @param info
	 */
	private void formatRegion(DeviceRegion region, Violation info) {
		info.setDeviceSysNbr(region.getRegionalcode());
		info.setDeviceNbr(region.getRegionalcode());
		info.setOrgCode(region.getOrgcode());
		info.setOrgAuthorityCode(region.getOrgprivilege());
		info.setDistrictCode(region.getDistrictcode());
		info.setVioSiteCode(region.getExitsitecode());
		info.setAddressDesc(region.getRegionalname());
		info.setRoadCode(region.getRoadcode());
		if (region.getDistance() != null) {
			info.setRegionDistance(region.getDistance().longValue());
		}
		info.setDirectionType(region.getDirectiontype());
		info.setDirectionName(region.getDirectionname());
		if (!StringUtil.isNullOrEmpty(region.getExitsitecode()) && region.getExitsitecode().length() > 9) {
			info.setMileage(Integer.valueOf(region.getExitsitecode().substring(9)));
		}
		info.setCollectionType(ConstValue.STR_SEVEN); // 7：区间测速
	}
}
