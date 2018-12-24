package cy.its.violation.domain.model.violation;

import java.text.SimpleDateFormat;
import java.util.Date;

import cy.its.violation.domain.criteria.ConstantCode;

/**
 * 采集数据录入 适应于:数码取证;视频监控取证;视频录像取证;
 * 
 * @author STJ
 *
 */
public class ViolationInputDigital extends ViolationInput {

	/**
	 * 设备/区间编号
	 */
	private String deviceSysNbr;

	/**
	 * 抓拍编号
	 */
	public String snapNbr;

	/**
	 * 采集机构
	 */
	private String orgCode;

	/**
	 * 采集人员
	 */
	public String collectionPolice;

	/**
	 * 采集类型
	 */
	private String collectionType;

	/**
	 * 行政区划
	 */
	private String districtCode;

	/**
	 * 违法地点编码
	 */
	private String vioSiteCode;

	/**
	 * 违法地点描述
	 */
	private String addressDesc;

	/**
	 * 道路
	 */
	private String roadCode;

	/**
	 * 路口路段
	 */
	private String roadSectionCode;

	/**
	 * 米数
	 */
	public Short mileage;

	/**
	 * 号牌号码
	 */
	private String plateNbr;

	/**
	 * 号牌类型(002)
	 */
	private String plateType;

	/**
	 * 号牌颜色
	 */
	private String plateColor;

	/**
	 * 违法时间
	 */
	private Date violationTime;

	/**
	 * 违法类型
	 */
	private String violationType;

	/**
	 * 违法代码
	 */
	private String violationCode;

	/**
	 * 违法描述
	 */
	private String violationDesc;

	/**
	 * 车速
	 */
	public Integer speed;

	/**
	 * 大车限速值
	 */
	public Long limitLarge;

	/**
	 * 小车限速值
	 */
	public Long limitSmall;

	/**
	 * 超速比
	 */
	public Integer overSpeedPercent;

	/**
	 * 录入人
	 */
	public String entryBy;

	/**
	 * 图片存储路径
	 */
	public String image;

	/**
	 * 视频存储路径
	 */
	public String video;

	static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSSS");

	public ViolationInputDigital(String deviceNbr, String snapNbr, String orgCode, String collectionPolice,
			String collectionType, String districtCode, String vioSiteCode, String addressDesc, String roadCode,
			String roadSectionCode, Short mileage, String plateNbr, String plateType, String plateColor,
			Date violationTime, String violationType, String violationCode, String violationDesc, Integer speed,
			Long limitLarge, Long limitSmall, Integer limitLower, Integer overSpeedPercent, String entryBy,
			Date entryTime, String uploadFlag, String uploadBy, String imageAddress) {

		this.deviceSysNbr = deviceNbr;
		this.snapNbr = snapNbr;
		this.orgCode = orgCode;
		this.collectionPolice = collectionPolice;
		this.collectionType = collectionType;
		this.districtCode = districtCode;
		this.vioSiteCode = vioSiteCode;
		this.addressDesc = addressDesc;
		this.roadCode = roadCode;
		this.roadSectionCode = roadSectionCode;
		this.mileage = mileage;
		this.plateNbr = plateNbr;
		this.plateType = plateType;
		this.plateColor = plateColor;
		this.violationTime = violationTime;
		this.violationType = violationType;
		this.violationCode = violationCode;
		this.violationDesc = violationDesc;
		this.speed = speed;
		this.limitLarge = limitLarge;
		this.limitSmall = limitSmall;
		this.overSpeedPercent = overSpeedPercent;
		this.entryBy = entryBy;
		this.image = imageAddress;
	}

	@Override
	void ExcuteInput(Violation violation) throws Exception {

		violation.setExportFlag("0");// 默认为未导出
		violation.setLocalPunishFlag("1");// 默认为未处罚
		violation.setLockFlag("0");
		violation.uploadFlag = "0";

		violation.setDeviceNbr(this.deviceSysNbr);
		violation.setOrgCode(this.orgCode);
		violation.setCollectionType(this.collectionType);
		violation.setDistrictCode(this.districtCode);
		violation.setVioSiteCode(this.vioSiteCode);
		violation.setAddressDesc(this.addressDesc);
		violation.setRoadCode(this.roadCode);
		violation.setRoadSectionCode(this.roadSectionCode);
		violation.setPlateNbr(this.plateNbr);
		violation.setPlateType(this.plateType);
		violation.setPlateColor(this.plateColor);
		violation.setViolationTime(this.violationTime);
		violation.setViolationType(this.violationType);
		violation.setViolationCode(this.violationCode);
		violation.setViolationDesc(this.violationDesc);

		violation.snapNbr = this.snapNbr;
		violation.collectionPolice = this.collectionPolice;
		violation.mileage = this.mileage;
		violation.speed = this.speed;
		violation.limitLarge = this.limitLarge;
		violation.limitSmall = this.limitSmall;
		violation.overSpeedPercent = this.overSpeedPercent;
		violation.entryBy = this.entryBy;
		violation.entryTime = new Date();

		violation.image = this.image;
		violation.video = this.video;
		violation.setStatusFlag(ConstantCode.StatusFlag.CONFIRMED);

	}

	@Override
	protected boolean IsEmpty() {
		return false;
	}

}
