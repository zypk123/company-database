package cy.its.violation.domain.criteria;

import java.util.List;

import cy.its.com.domain.Criteria;

/**
 * 以o_开头的表明是更新字段
 * 
 * @author jinhb
 */
public class ViolationCriteria extends Criteria {
	// 更新字段
	public String UvehicleRegId;

	/**
	 * 
	 */
	public String o_discardedType;
	/**
	 * 
	 */
	public String o_discardedBy;
	/**
	 * 
	 */
	public String o_discardedTime;
	/**
	 * 
	 */
	public String o_specialVehType;
	/**
	 * 
	 */
	public String o_discardedReason;
	/**
	 * 
	 */
	public String o_statusFlag;
	/**
	 * 
	 */
	public String o_entryBy;
	/**
	 * 
	 */
	public String o_entryTime;
	/**
	 * 
	 */
	public String o_uploadFlag;
	/**
	 * 
	 */
	public String o_uploadBy;
	/**
	 * 
	 */
	public String o_uploadTime;
	/**
	 * 
	 */
	public String o_speedingType;
	/**
	 * 
	 */
	public String o_collectionType;
	/**
	 * 
	 */
	public String o_collectionPolice;
	/**
	 * 
	 */
	private String o_exportFlag;
	/**
	 * 
	 */
	public String o_exportTime;
	/**
	 * 
	 */
	public String o_remark;
	/**
	 * 
	 */
	public String o_updateTime;

	// 号牌号码、号牌种类、号牌颜色、采集单位、
	// 采集设备、违法时间、违法类型、记录状态（新纪录、已录入、已废弃）、
	// 采集方式、废弃原因、特殊车辆、是否包含特殊车辆、
	// 违法时间、速度、测速类别
	// 上传标记
	// 导出标记
	// 是否未识别号牌

	/**
	 * 精确号牌号码
	 */
	public String plateNbr;

	/**
	 * 模糊号牌号码
	 */
	public String fuzzyPlateNbr;

	/**
	 * 号牌种类
	 */
	public String plateType;

	/**
	 * 号牌颜色
	 */
	public String plateColor;

	/**
	 * 采集单位
	 */
	public String orgCode;
	/**
	 * 采集单位权限代码
	 */
	public String org_authority_code;

	/**
	 * 采集设备
	 */
	public String deviceSysNbr;

	/**
	 * 采集点位
	 */
	public List<String> LstSiteCode;

	/**
	 * 违法开始时间
	 */
	public String violationBeginTime;

	/**
	 * 违法结束时间
	 */
	public String violationEndTime;

	/**
	 * 上传开始时间
	 */
	public String uploadBeginTime;

	/**
	 * 上传结束时间
	 */
	public String uploadEndTime;

	/**
	 * 违法类型(030)： 0：大车占道 1：超高速 2：超低速 3：逆行 4：闯红灯 5：压黄线 6：违章停车 7：区间超速 8：禁行 9：其他
	 * a：违法占道 b：遮挡号牌 c：不按导向行驶 d：压线 e：未系安全带
	 */
	public List<String> lstViolationType;

	/**
	 * 记录状态（301）,0：新记录；1：已录入；2：废弃记录
	 */
	public List<String> lstStatusFlag;
	
	/**
	 * 记录状态（301）,0：新记录；1：已录入；2：废弃记录
	 */
	public List<String> lstCollectionType;

	/**
	 * 记录状态
	 */
	public String statusFlag;

	/**
	 * 废弃原因(312) 01 异常数据 02 特殊车辆 21套牌车 22 假牌车 23 白名单车 24 军警车 25 农用车 26 摩托车 03
	 * 重复记录 04 无效图像 05 号牌不全 06 无号牌 07 超时 08 其他
	 */
	public String discardedReason;

	/**
	 * 废弃类别
	 */
	public String discardedType;

	/**
	 * 车速下限值
	 */
	public Integer speedMin;

	/**
	 * 车速上限值
	 */
	public Integer speedMax;

	/**
	 * 采集方式 1：闯红灯设备 2：公路卡口设备 3：测速设备 4：闭路电视 5：移动摄像 6：警务通 7：区间测速 8：卫星定位装置 9：其它电子设备
	 */
	public String collectionType;

	/**
	 * 测速类别 ：1 移动测速 2 区间测速 3 固定点测速 4 卡口线圈测速 5 卡口雷达测速
	 */
	public String speedingType;

	/**
	 * 上传标记 0 未上传 1 待上传 2 已上传 3 上传失败
	 */
	public String uploadFlag;

	/**
	 * 特殊车辆类型
	 */
	public String specialVehType;

	/**
	 * 是否包含特殊车辆 0或空: 不包含; 1:包含;
	 */
	public String isIncludeSpecial;

	/**
	 * 是否包含异常车辆
	 */
	// public String isIncludeSpecial;
	public Boolean isArmyPlate;

	public Boolean isUnknownPlate;
}