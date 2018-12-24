package cy.its.service.standardization.dataMaker;

import java.math.BigDecimal;
import java.util.Map;

import cy.its.service.common.ConstValue;
import cy.its.service.common.DateUtil;
import cy.its.service.common.dataModel.DeviceDataPath;
import cy.its.service.common.ioc.Export;
import cy.its.service.common.ioc.Import;
import cy.its.service.standardization.dataMaker.originalModel.PassDataPath;
import cy.its.service.standardization.dictionary.SurveyUpgrade_DeviceCache;
import cy.its.service.standardization.dictionary.model.IDeviceInfo;
import cy.its.service.standardization.validator.BaseValidator;
import cy.its.service.standardization.validator.DeviceDataPathValidator;

@Export
public class PassPathMaker extends DataMaker<PassDataPath, DeviceDataPath> {

	@Import
	SurveyUpgrade_DeviceCache surveyUpgrade_DeviceCache;

	public PassPathMaker() {
		super("¹ý³µ´«Êä¹ì¼£", PassDataPath.class, DeviceDataPath.class, ConstValue.ROUTE_KEY_ORIGINAL_PASS_PATH,
				ConstValue.ROUTE_KEY_STANDARD_DEVICE_DATA_PATH, "original_pass_path", true);
	}

	double d1000 = 1000.0;
	String serverReceivingTime = "serverReceivingTime";
	String afterserverReceivingTime = "afterserverReceivingTime";
	String ice2mqTime = "ice2mqTime";
	String preTime = "preTime";
	String afterTime = "afterTime";
	String uploadTime = "uploadTime";
	String uploadEndTime = "uploadEndTime";

	@Override
	DeviceDataPath translate(PassDataPath input) {

		IDeviceInfo di = surveyUpgrade_DeviceCache.get(input.getDeviceKey());

		if (di != null) {
			DeviceDataPath path = new DeviceDataPath();
			path.setDeviceId(di.getDeviceId());
			path.setDeviceSysNbr(di.getSysNbr());
			path.setSysComponentId(di.getSysComponentId());
			path.setDeviceNbr(input.getDeviceNbr());
			path.setDeviceKey(input.getDeviceKey());
			path.setOrgPrivilegeCode(di.getOrgPrivilegeCode());
			path.setSnapNbr(input.getSnapNbr());
			path.setSiteCode(di.getSiteCode());
			path.setRoadCode(di.getRoadCode());

			path.setPassTime(DateUtil.parseDate(input.getPassTime()));

			if (input.getDataTimePath() != null) {
				Map<String, String> dtp = input.getDataTimePath();
				path.setServerReceivingTime(DateUtil.parseDate(dtp.get(serverReceivingTime)));
				path.setAfterserverReceivingTime(DateUtil.parseDate(dtp.get(afterserverReceivingTime)));
				path.setIce2mqTime(DateUtil.parseDate(dtp.get(ice2mqTime)));
				path.setPreTime(DateUtil.parseDate(dtp.get(preTime)));
				path.setAfterTime(DateUtil.parseDate(dtp.get(afterTime)));
				path.setUploadTime(DateUtil.parseDate(dtp.get(uploadTime)));
				path.setUploadEndTime(DateUtil.parseDate(dtp.get(uploadEndTime)));
				if (path.getUploadEndTime() != null && path.getPassTime() != null) {
					path.setTotalTime(BigDecimal
							.valueOf((path.getUploadEndTime().getTime() - path.getPassTime().getTime()) / d1000)
							.setScale(ConstValue.INT_2, BigDecimal.ROUND_HALF_UP));
				}
			}
			
			return path;
		} else {
			input.validateResult = STR_DEVICE_UNREG;
		}

		return null;
	}

	@Override
	BaseValidator<DeviceDataPath> validator() {
		return new DeviceDataPathValidator();
	}

}
