package cy.its.service.standardization.util.vioConverter;

import cy.its.service.common.ConstValue;
import cy.its.service.common.StringUtil;
import cy.its.service.common.dataModel.Violation;
import cy.its.service.common.ioc.Export;
import cy.its.service.common.ioc.Import;
import cy.its.service.standardization.dataMaker.originalModel.ViolationVehicle;
import cy.its.service.standardization.dictionary.CollectTypeCache;
import cy.its.service.standardization.dictionary.DirectCache;
import cy.its.service.standardization.dictionary.model.IDeviceInfo;

@Export
public class SiteVioConverter extends VioConverter {

	@Import
	DirectCache directCache;

	@Import
	CollectTypeCache collectTypeCache;
	
	public Violation convert(ViolationVehicle v, IDeviceInfo dInfo) {
		Violation info = new Violation();
		
		formatDevInfo(v, dInfo, info);
		// 计算限速值
		formatSpeedLimit(v, info);

		commonFormat(v, info,
				v.getExtendedProperties() != null ?
						v.getExtendedProperties().get(ConstValue.STR_DRIVEMODE) : null);

		// info.setRegionDistance();
		// info.setRedLightEndTime();
		// info.setSpeedingType();
		// info.setSpecialVehType();
		// info.setVideo();
		// info.setRemark();

		return info;
	}

	/**
	 * 抽取设备信息
	 * 
	 * @param v
	 * @param dInfo
	 * @param info
	 */
	private void formatDevInfo(ViolationVehicle v, IDeviceInfo dInfo, Violation info) {
		info.setDeviceSysNbr(dInfo.getSysNbr());
		info.setDeviceNbr(dInfo.getDeviceNbr());
		info.setOrgCode(dInfo.getOrgCode());
		info.setOrgAuthorityCode(dInfo.getOrgPrivilegeCode());
		info.setDistrictCode(dInfo.getDistrict());
		info.setVioSiteCode(dInfo.getSiteCode());
		info.setAddressDesc(dInfo.getSiteName());
		info.setRoadCode(dInfo.getRoadCode());
		info.setRoadSectionCode(dInfo.getRoadSegCode());
		info.setCrossCode(dInfo.getCrossCode());
		if (!StringUtil.isNullOrEmpty(dInfo.getSiteCode()) && dInfo.getSiteCode().length() > ConstValue.INT_9) {
			info.setMileage(Integer.valueOf(dInfo.getSiteCode().substring(ConstValue.INT_9)));
		}
		info.setDirectionType(directCache.getDirectType(dInfo.getSiteId(), v.getDriveDirection()));
		info.setDirectionName(v.getDriveDirection());
		info.setCollectionType(collectTypeCache.getCollectType(dInfo.getDeviceType()));
		info.setDeviceId(dInfo.getDeviceId());
		info.setSysComponentId(dInfo.getSysComponentId());
	}
}
