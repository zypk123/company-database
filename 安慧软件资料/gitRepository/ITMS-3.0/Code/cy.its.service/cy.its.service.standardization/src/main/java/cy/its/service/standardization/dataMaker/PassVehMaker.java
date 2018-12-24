package cy.its.service.standardization.dataMaker;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import cy.its.service.common.dataModel.VehTrackPass;
import cy.its.service.common.ioc.Export;
import cy.its.service.common.ioc.Import;
import cy.its.service.common.ConstValue;
import cy.its.service.common.DateUtil;
//import cy.its.service.common.ImageUtil;
import cy.its.service.common.StringUtil;
import cy.its.service.standardization.dataMaker.originalModel.PassingVehicle;
import cy.its.service.standardization.dataMaker.originalModel.VehicleImageDescription;
import cy.its.service.standardization.dictionary.DirectCache;
import cy.its.service.standardization.dictionary.SurveyUpgrade_DeviceCache;
import cy.its.service.standardization.dictionary.VehBrandCache;
import cy.its.service.standardization.dictionary.model.IDeviceInfo;
import cy.its.service.standardization.dictionary.model.VehBrand;
import cy.its.service.standardization.util.Config;
import cy.its.service.standardization.validator.BaseValidator;
import cy.its.service.standardization.validator.VehTrackPassValidator;

@Export
public class PassVehMaker extends DataMaker<PassingVehicle, VehTrackPass> {

//	@Import
//	DeviceCache deviceCache;

	@Import
	SurveyUpgrade_DeviceCache surveyUpgrade_DeviceCache;
	
	@Import
	DirectCache directCache;

	@Import
	VehBrandCache vehBrandCache;

	Map<String, Integer> plateColor;
	Map<String, String> vehicleType;
	private final static String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private final static String JUNStart = "VKHBSLJNGC*";

	BigDecimal LEN_0 = BigDecimal.valueOf(0);
	BigDecimal LEN_60 = BigDecimal.valueOf(60);
	BigDecimal LEN_35 = BigDecimal.valueOf(35);
	BigDecimal LEN_100 = BigDecimal.valueOf(100);

	public PassVehMaker() {
		super("过车", PassingVehicle.class, VehTrackPass.class, ConstValue.ROUTE_KEY_ORIGINAL_PASS,
				ConstValue.ROUTE_KEY_STANDARD_PASS, "original_pass_vehicle");

		plateColor = new HashMap<String, Integer>(5);
		plateColor.put("0", 0);
		plateColor.put("1", 1);
		plateColor.put("2", 2);
		plateColor.put("3", 3);
		plateColor.put("4", 4);

		vehicleType = new HashMap<String, String>(6);
		vehicleType.put("00", "00");
		vehicleType.put("01", "01");
		vehicleType.put("02", "02");
		vehicleType.put("03", "03");
		vehicleType.put("05", "05");
		vehicleType.put("06", "06");
	}

	@Override
	VehTrackPass translate(PassingVehicle input) {
		if (input.getExtendedProperties() != null) {
			String devKey = input.getExtendedProperties().get(ConstValue.DEVICE_KEY);
			if (!StringUtil.isNullOrEmpty(devKey)) {
				IDeviceInfo deviceInfo = surveyUpgrade_DeviceCache.get(devKey);
				if (deviceInfo != null) {
					VehTrackPass veh_Pass = new VehTrackPass();
					veh_Pass.setDeviceKey(devKey);
					formatBaseInfo(input, deviceInfo, veh_Pass);
					formatVehicleLength(input, veh_Pass);
					formatFromProps(input, veh_Pass);
					formatPlate(input, veh_Pass);
					formatHeadTailImg(input, veh_Pass);
					formatLocalization(input, veh_Pass);
					formatVehicleShape(input, veh_Pass);
					input = null;
					deviceInfo = null;
					return veh_Pass;
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

	@Override
	BaseValidator<VehTrackPass> validator() {
		return new VehTrackPassValidator();
	}

	String fmt = "F:%d-%s-%s";

	/**
	 * 从扩展属性获取信息
	 * 
	 * @param input
	 * @param veh_Pass
	 */
	private void formatFromProps(PassingVehicle input, VehTrackPass veh_Pass) {
		if (input.getExtendedProperties() != null) {
			Map<String, String> ext = input.getExtendedProperties();

			if (!StringUtil.isNullOrEmpty(ext.get(ConstValue.STR_IMAGE_URLS))) {
//				veh_Pass.setImageUrlPath(ImageUtil
//						.encryptUrl(ext.get(ConstValue.STR_IMAGE_URLS).split(ConstValue.SEMICOLON)[ConstValue.INT_0]));
				veh_Pass.setImageUrlPath(ext.get(ConstValue.STR_IMAGE_URLS).split(ConstValue.SEMICOLON)[ConstValue.INT_0]);
			}

			veh_Pass.setTailPlateNbr(ext.get(ConstValue.STR_TAIL_PLATE_NBR));			
			veh_Pass.setVehicleColor(ext.get(ConstValue.STR_VEHICLE_COLOR));
			
			VehBrand brand = vehBrandCache.get(ext.get(ConstValue.STR_VEHICLE_BRAND),
					ext.get(ConstValue.STR_SUB_VEHICLE_LOGO));
			if(brand != null){
				veh_Pass.setVehicleBrand(brand.getBrand());
				veh_Pass.setVehicleSubBrand(brand.getSubBrand());
				brand = null;
			}

			ext = null;
		}

		if (StringUtil.isNullOrEmpty(veh_Pass.getImageUrlPath())) {
//			veh_Pass.setImageUrlPath(ImageUtil
//					.encryptUrl(String.format(fmt, ConstValue.INT_0, veh_Pass.getDeviceNbr(), veh_Pass.getSnapNbr())));
			veh_Pass.setImageUrlPath(String.format(fmt, ConstValue.INT_0, veh_Pass.getDeviceNbr(), veh_Pass.getSnapNbr()));
		}
	}

	/**
	 * 过车基本信息提取
	 * 
	 * @param input
	 * @param deviceInfo
	 * @param veh_Pass
	 */
	private void formatBaseInfo(PassingVehicle input, IDeviceInfo deviceInfo, VehTrackPass veh_Pass) {
		veh_Pass.setDeviceNbr(input.getDeviceNo());
		veh_Pass.setDeviceSysNbr(deviceInfo.getSysNbr());
		veh_Pass.setSnapNbr(input.getSnapNbr());
		veh_Pass.setLane(String.valueOf(input.getLane()));
		veh_Pass.setPassTime(DateUtil.parseDate(input.getCaptureTime()));
		veh_Pass.setPlateNbr(checkPlateNbr(input.getPlateNbr()));
		veh_Pass.setSpeed(input.getVehicleSpeed());
		veh_Pass.setDriveMode(String.valueOf(input.getDriveMode()));
		veh_Pass.setOrgCode(deviceInfo.getOrgCode());
		veh_Pass.setOrgAuthorityCode(deviceInfo.getOrgPrivilegeCode());
		veh_Pass.setDirectionName(input.getDriveDirection());
		veh_Pass.setDirectionType(directCache.getDirectType(deviceInfo.getSiteId(), input.getDriveDirection()));
		veh_Pass.setDistrictCode(deviceInfo.getDistrict());
		veh_Pass.setSiteCode(deviceInfo.getSiteCode());
		veh_Pass.setRoadType(deviceInfo.getRoadType());
		veh_Pass.setRoadSectionCode(deviceInfo.getRoadSegCode());
		veh_Pass.setRoadCode(deviceInfo.getRoadCode());
		veh_Pass.setDeviceId(deviceInfo.getDeviceId());
		veh_Pass.setSysComponentId(deviceInfo.getSysComponentId());
//		veh_Pass.setDeviceTime(veh_Pass.getPassTime().getTime());
	}

	/**
	 * 提取过车车辆长度
	 * 
	 * @param input
	 * @param veh_Pass
	 */
	private void formatVehicleLength(PassingVehicle input, VehTrackPass veh_Pass) {
		if (input.getVehicleLength() != null) {
			if (input.getVehicleLength().compareTo(LEN_100) >= ConstValue.INT_0) {
				BigDecimal len = input.getVehicleLength();
				veh_Pass.setVehicleLength(BigDecimal
						.valueOf(len.doubleValue() / Math.pow(ConstValue.INT_10,
								String.valueOf(len.intValue()).length() - ConstValue.INT_2))
						.setScale(ConstValue.INT_2, BigDecimal.ROUND_HALF_UP));

			} else {
				veh_Pass.setVehicleLength(
						input.getVehicleLength().setScale(ConstValue.INT_2, BigDecimal.ROUND_HALF_UP));
			}
		}
	}

	/**
	 * 处理车辆外形
	 * 
	 * @param input
	 * @param veh_Pass
	 */
	private void formatVehicleShape(PassingVehicle input, VehTrackPass veh_Pass) {
		// 处理车辆外形
		String vehType = input.getVehicleType();
		if (!StringUtil.isNullOrEmpty(vehType)) {
			vehType = vehType.trim();
		}

		if (StringUtil.isNullOrEmpty(vehType)) {
			veh_Pass.setVehicleShape(ConstValue.STR_00);
		} else {
			if (vehicleType.containsKey(vehType)) {
				veh_Pass.setVehicleShape(vehType);
			} else {
				veh_Pass.setVehicleShape(ConstValue.STR_00);
			}
		}

		if (ConstValue.STR_00.equals(veh_Pass.getVehicleShape())) {
			if (ConstValue.STR_TWO.equals(veh_Pass.getPlateColor())) {
				// 蓝牌车
				veh_Pass.setVehicleShape(ConstValue.STR_02);
			} else {
				BigDecimal len = veh_Pass.getVehicleLength();
				if (len != null) {
					if (len.compareTo(LEN_0) > ConstValue.INT_0) {
						if (len.compareTo(LEN_60) >= ConstValue.INT_0) {
							veh_Pass.setVehicleShape(ConstValue.STR_01);
						} else if (len.compareTo(LEN_35) >= ConstValue.INT_0) {
							veh_Pass.setVehicleShape(ConstValue.STR_02);
						} else {
							veh_Pass.setVehicleShape(ConstValue.STR_05);
						}
					}
				}
			}
		}
	}

	/**
	 * 获取头尾号牌信息
	 * 
	 * @param input
	 * @param veh_Pass
	 */
	private void formatHeadTailImg(PassingVehicle input, VehTrackPass veh_Pass) {
		// 获取车头和车尾图片地址
		VehicleImageDescription[] imageDescs = input.getImageDescriptions();
		if (imageDescs != null && imageDescs.length > ConstValue.INT_0 && imageDescs[ConstValue.INT_0] != null) {
			if (imageDescs[ConstValue.INT_0].getLocation() != null) {
				// 车头图片地址
				veh_Pass.setImageUrl1(imageDescs[ConstValue.INT_0].getLocation().getPosition());
			}

			if (imageDescs.length > ConstValue.INT_1 && imageDescs[ConstValue.INT_1] != null) {
				if (imageDescs[ConstValue.INT_1].getLocation() != null) {
					// 尾牌图片地址
					veh_Pass.setImageUrl2(imageDescs[ConstValue.INT_1].getLocation().getPosition());
				}
			}
		}
	}

	/**
	 * 处理号牌颜色 和类型
	 * 
	 * @param input
	 * @param veh_Pass
	 */
	private void formatPlate(PassingVehicle input, VehTrackPass veh_Pass) {
		// 处理号牌颜色 类型
		veh_Pass.setPlateColor(
				StringUtil.isNullOrEmpty(input.getPlateColor()) ? StringUtil.EMPTY_STR : input.getPlateColor().trim());

		if (StringUtil.isNullOrEmpty(input.getPlateColor())) {
			veh_Pass.setPlateColor(ConstValue.STR_FOUR);
		} else {
			if (!plateColor.containsKey(veh_Pass.getPlateColor())) {
				String pc = StringUtil.trimStart(veh_Pass.getPlateColor(), ConstValue.CHAR_ZERO);
				veh_Pass.setPlateColor(StringUtil.isNullOrEmpty(pc) ? ConstValue.STR_ZERO
						: (plateColor.containsKey(pc) ? pc : ConstValue.STR_FOUR));
			}
		}

		veh_Pass.setPlateType(GetPlateType(veh_Pass.getPlateNbr(), veh_Pass.getPlateColor(), input));
	}

	/**
	 * 处理车辆归属地
	 * 
	 * @param input
	 * @param veh_Pass
	 */
	private void formatLocalization(PassingVehicle input, VehTrackPass veh_Pass) {
		// 获取车辆归属地
		// 车辆归属地 0-未知、1-本市、2-本省,3-外省,4-军警车
		if (StringUtil.isNullOrEmpty(input.getPlateNbr())) {
			veh_Pass.setVehLocalization(ConstValue.STR_ZERO);
		} else if (ConstValue.STR_ZERO.equals(input.getPlateColor())) {
			veh_Pass.setVehLocalization(ConstValue.STR_FOUR);
		} else if (input.getPlateNbr().startsWith(Config.DEFAULT_VEHICLE_CITY)) {
			veh_Pass.setVehLocalization(ConstValue.STR_ONE);
		} else if (input.getPlateNbr().startsWith(Config.DEFAULT_VEHICLE_PROVINCE)) {
			veh_Pass.setVehLocalization(ConstValue.STR_TWO);
		} else {
			veh_Pass.setVehLocalization(ConstValue.STR_THREE);
		}
	}

	static char CHAR_MIN = 0x4e00;
	static char CHAR_MAX = 0x9fbb;
	static char CHAR_A = 'A';
	static char CHAR_Z = 'Z';
	static char CHAR_SPACE = ' ';

	private static String checkPlateNbr(String plate_nbr) {
		if (StringUtil.isNullOrEmpty(plate_nbr)) {
			return ConstValue.STR_UNKNOWN;
		}
		plate_nbr = plate_nbr.trim();
		int len = plate_nbr.length();
		if (len < ConstValue.INT_7 || len > ConstValue.INT_9) {
			return ConstValue.STR_UNKNOWN;
		}

		char c_0 = plate_nbr.charAt(ConstValue.INT_0);
		if (((c_0 < CHAR_MIN || c_0 > CHAR_MAX) && CHARS.indexOf(c_0) < ConstValue.INT_0)
				|| CHARS.indexOf(plate_nbr.charAt(ConstValue.INT_1)) < ConstValue.INT_0) {
			return ConstValue.STR_UNKNOWN;
		}

		// int max = ConstValue.INT_1;
		// char last = CHAR_SPACE;
		// for (char c : plate_nbr.toCharArray()) {
		// if (c == last) {
		// max++;
		// } else {
		// if (max >= ConstValue.INT_4) {
		// // 最大已经有4个了，由于最多只有7个，所以这个就是重复的
		// break;
		// }
		// max = ConstValue.INT_1;
		// last = c;
		// }
		// }
		//
		// if ((max >= ConstValue.INT_5 && last >= ConstValue.CHAR_ZERO && last
		// <= ConstValue.CHAR_NINE)
		// || (max >= ConstValue.INT_4 && last >= CHAR_A && last <= CHAR_Z)) {
		// return ConstValue.STR_UNKNOWN;
		// }

		return plate_nbr;
	}

	static String GetPlateType(String plate_nbr, String plate_color, PassingVehicle input) {
		if (ConstValue.STR_UNKNOWN.equals(plate_nbr)) {
			// 未知车牌
			return ConstValue.STR_99;
		} else if (plate_nbr.contains(ConstValue.PLATE_PRE_POLICE)) {
			// 警车
			return ConstValue.STR_23;
		} else if (plate_nbr.contains(ConstValue.PLATE_PRE_LEARN)) {
			// 教练车
			return ConstValue.STR_16;
		} else if (plate_nbr.contains(ConstValue.PLATE_PRE_TRAILER)) {
			// 外挂车
			return ConstValue.STR_15;
		} else if (plate_nbr.startsWith(ConstValue.PLATE_PRE_EMBASSY)) {
			// 使馆车
			return ConstValue.STR_03;
		} else if (plate_nbr.startsWith(ConstValue.PLATE_PRE_WJ)
				|| JUNStart.contains(plate_nbr.substring(ConstValue.INT_0, ConstValue.INT_1))) {
			// 军车
			return ConstValue.STR_23;
		}

		if (!StringUtil.isNullOrEmpty(input.getPlateType()) && !StringUtil.isNullOrEmpty(input.getPlateType().trim())) {
			return StringUtil.padLeft(input.getPlateType().trim(), ConstValue.INT_2, ConstValue.CHAR_ZERO);
		} else {
			if (StringUtil.isNullOrEmpty(plate_color) || ConstValue.STR_FOUR.equals(plate_color)) {
				return ConstValue.STR_99; // 其他
			} else if (ConstValue.STR_ONE.equals(plate_color)) {
				return ConstValue.STR_01; // 黄牌 - 大车
			} else {
				return ConstValue.STR_02; // 小车
			}
		}
	}
}
