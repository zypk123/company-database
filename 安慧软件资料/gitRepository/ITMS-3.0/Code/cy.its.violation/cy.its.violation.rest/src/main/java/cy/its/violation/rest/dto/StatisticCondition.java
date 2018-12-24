package cy.its.violation.rest.dto;

public class StatisticCondition {

	public int pageNumber;

	public int pageSize;

	public int dataIndex;

	public String seriesName;

	public Boolean ViolationRedLight;

	public Boolean getIsArmyPlate() {
		return isArmyPlate;
	}

	public void setIsArmyPlate(Boolean isArmyPlate) {
		this.isArmyPlate = isArmyPlate;
	}

	public Boolean getIsUnknownPlate() {
		return isUnknownPlate;
	}

	public void setIsUnknownPlate(Boolean isUnknownPlate) {
		this.isUnknownPlate = isUnknownPlate;
	}

	public Boolean isArmyPlate;

	public Boolean isUnknownPlate;

	public Boolean getViolationRedLight() {
		return ViolationRedLight;
	}

	public void setViolationRedLight(Boolean violationRedLight) {
		ViolationRedLight = violationRedLight;
	}

	public int getDataIndex() {
		return dataIndex;
	}

	public void setDataIndex(int dataIndex) {
		this.dataIndex = dataIndex;
	}

	public String getSeriesName() {
		return seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	public String getViolationEndTime() {
		return violationEndTime;
	}

	public void setViolationEndTime(String violationEndTime) {
		this.violationEndTime = violationEndTime;
	}

	/**
	 * 操作名称
	 */
	public String operationName;

	/**
	 * 精确号牌号码
	 */
	public String plateNbr;

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	public String Total;

	public String Pages;

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
	 * 采集设备
	 */
	public String deviceNbr;

	/**
	 * 违法开始时间
	 */
	public String violationBeginTime;

	/**
	 * 违法结束时间
	 */
	public String violationEndTime;

	/**
	 * 违法类型(030)： 0：大车占道 1：超高速 2：超低速 3：逆行 4：闯红灯 5：压黄线 6：违章停车 7：区间超速 8：禁行 9：其他
	 * a：违法占道 b：遮挡号牌 c：不按导向行驶 d：压线 e：未系安全带
	 */
	public String violationType;

	/**
	 * 记录状态（301）,0：新记录；1：已录入；2：废弃记录
	 */
	public String statusFlag;

	/**
	 * 废弃原因(312) 01 异常数据 02 特殊车辆 21套牌车 22 假牌车 23 白名单车 24 军警车 25 农用车 26 摩托车 03
	 * 重复记录 04 无效图像 05 号牌不全 06 无号牌 07 超时 08 其他
	 */
	public String discardedReason;

	/**
	 * 车速下限值
	 */
	public String speedMin;

	/**
	 * 车速上限值
	 */
	public String speedMax;

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

	public String runStatus;

	public String getRunStatus() {
		return runStatus;
	}

	public void setRunStatus(String runStatus) {
		this.runStatus = runStatus;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNum) {
		this.pageNumber = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getPlateNbr() {
		return plateNbr;
	}

	public void setPlateNbr(String plateNbr) {
		this.plateNbr = plateNbr;
	}

	public String getTotal() {
		return Total;
	}

	public void setTotal(String total) {
		Total = total;
	}

	public String getPages() {
		return Pages;
	}

	public void setPages(String pages) {
		Pages = pages;
	}

	public String getFuzzyPlateNbr() {
		return fuzzyPlateNbr;
	}

	public void setFuzzyPlateNbr(String fuzzyPlateNbr) {
		this.fuzzyPlateNbr = fuzzyPlateNbr;
	}

	public String getPlateType() {
		return plateType;
	}

	public void setPlateType(String plateType) {
		this.plateType = plateType;
	}

	public String getPlateColor() {
		return plateColor;
	}

	public void setPlateColor(String plateColor) {
		this.plateColor = plateColor;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getDeviceNbr() {
		return deviceNbr;
	}

	public void setDeviceNbr(String deviceNbr) {
		this.deviceNbr = deviceNbr;
	}

	public String getViolationBeginTime() {
		return violationBeginTime;
	}

	public void setViolationBeginTime(String violationBeginTime) {
		this.violationBeginTime = violationBeginTime;
	}

	public String getViolationEndTim() {
		return violationEndTime;
	}

	public void setViolationEndTim(String violationEndTim) {
		this.violationEndTime = violationEndTim;
	}

	public String getViolationType() {
		return violationType;
	}

	public void setViolationType(String violationType) {
		this.violationType = violationType;
	}

	public String getStatusFlag() {
		return statusFlag;
	}

	public void setStatusFlag(String statusFlag) {
		this.statusFlag = statusFlag;
	}

	public String getDiscardedReason() {
		return discardedReason;
	}

	public void setDiscardedReason(String discardedReason) {
		this.discardedReason = discardedReason;
	}

	public String getSpeedMin() {
		return speedMin;
	}

	public void setSpeedMin(String speedMin) {
		this.speedMin = speedMin;
	}

	public String getSpeedMax() {
		return speedMax;
	}

	public void setSpeedMax(String speedMax) {
		this.speedMax = speedMax;
	}

	public String getCollectionType() {
		return collectionType;
	}

	public void setCollectionType(String collectionType) {
		this.collectionType = collectionType;
	}

	public String getSpeedingType() {
		return speedingType;
	}

	public void setSpeedingType(String speedingType) {
		this.speedingType = speedingType;
	}

	public String getUploadFlag() {
		return uploadFlag;
	}

	public void setUploadFlag(String uploadFlag) {
		this.uploadFlag = uploadFlag;
	}

	public String getSpecialVehType() {
		return specialVehType;
	}

	public void setSpecialVehType(String specialVehType) {
		this.specialVehType = specialVehType;
	}

	public String getIsIncludeSpecial() {
		return isIncludeSpecial;
	}

	public void setIsIncludeSpecial(String isIncludeSpecial) {
		this.isIncludeSpecial = isIncludeSpecial;
	}
}
