package cy.its.device.rest.dto;

import cy.its.com.dto.BaseDto;

public class IntervalSysDto extends BaseDto {
	private String regionalId;				//区间ID
	private String regionalCode; 			//区间编号
	private String regionalName;			//区间名称
	private String districtCode;			//行政区划
	private String orgId;					//机构ID
	private String roadId;					//道路ID
	private String entrySiteId;				//起点ID
	private String exitSiteId;				//终点ID
	private String entrySiteName;			//起点名称
	private String exitSiteName;			//终点名称
	private String distance;				//区间距离
	private String directionType;			//方向类型
	private String directionName;			//方向名称
	private Short limitLarge;				//大车限高速
	private Short limitSmall;				//小车限高速
	private Long limitOthers;				//其它车限高速
	private Short limitLower;				//限低速
	private Integer limitLargeMargin;		//大车限速容许值
	private Integer limitSmallMargin;		//小车限速容许值
	private String relatedVariableSpeed;	//关联可变限速牌编号列表
	private String relatedLedId;			//关联LED列表
	private String integratePlatformNbr;	//综合平台设备登记编号
	private String enableFlag;				//启用标识。0-未启用；1-启用
	private String createBy;				//创建人
	private String createTime;				//创建时间
	private String updateBy;				//更新人员
	private String remark;					//备注
	private String verificationMark;
	private String expireDate;  			//证书有效期止
	
	
	public String getRegionalId() {
		return regionalId;
	}
	public void setRegionalId(String regionalId) {
		this.regionalId = regionalId;
	}
	public String getRegionalCode() {
		return regionalCode;
	}
	public void setRegionalCode(String regionalCode) {
		this.regionalCode = regionalCode;
	}
	public String getRegionalName() {
		return regionalName;
	}
	public void setRegionalName(String regionalName) {
		this.regionalName = regionalName;
	}
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getRoadId() {
		return roadId;
	}
	public void setRoadId(String roadId) {
		this.roadId = roadId;
	}
	public String getEntrySiteId() {
		return entrySiteId;
	}
	public void setEntrySiteId(String entrySiteId) {
		this.entrySiteId = entrySiteId;
	}
	public String getExitSiteId() {
		return exitSiteId;
	}
	public void setExitSiteId(String exitSiteId) {
		this.exitSiteId = exitSiteId;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getDirectionType() {
		return directionType;
	}
	public void setDirectionType(String directionType) {
		this.directionType = directionType;
	}
	public String getDirectionName() {
		return directionName;
	}
	public void setDirectionName(String directionName) {
		this.directionName = directionName;
	}
	public Short getLimitLarge() {
		return limitLarge;
	}
	public void setLimitLarge(Short limitLarge) {
		this.limitLarge = limitLarge;
	}
	public Short getLimitSmall() {
		return limitSmall;
	}
	public void setLimitSmall(Short limitSmall) {
		this.limitSmall = limitSmall;
	}
	public Long getLimitOthers() {
		return limitOthers;
	}
	public void setLimitOthers(Long limitOthers) {
		this.limitOthers = limitOthers;
	}
	public Short getLimitLower() {
		return limitLower;
	}
	public void setLimitLower(Short limitLower) {
		this.limitLower = limitLower;
	}
	public Integer getLimitLargeMargin() {
		return limitLargeMargin;
	}
	public void setLimitLargeMargin(Integer limitLargeMargin) {
		this.limitLargeMargin = limitLargeMargin;
	}
	public Integer getLimitSmallMargin() {
		return limitSmallMargin;
	}
	public void setLimitSmallMargin(Integer limitSmallMargin) {
		this.limitSmallMargin = limitSmallMargin;
	}
	public String getRelatedVariableSpeed() {
		return relatedVariableSpeed;
	}
	public void setRelatedVariableSpeed(String relatedVariableSpeed) {
		this.relatedVariableSpeed = relatedVariableSpeed;
	}
	public String getRelatedLedId() {
		return relatedLedId;
	}
	public void setRelatedLedId(String relatedLedId) {
		this.relatedLedId = relatedLedId;
	}
	public String getIntegratePlatformNbr() {
		return integratePlatformNbr;
	}
	public void setIntegratePlatformNbr(String integratePlatformNbr) {
		this.integratePlatformNbr = integratePlatformNbr;
	}
	public String getEnableFlag() {
		return enableFlag;
	}
	public void setEnableFlag(String enableFlag) {
		this.enableFlag = enableFlag;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getEntrySiteName() {
		return entrySiteName;
	}
	public void setEntrySiteName(String entrySiteName) {
		this.entrySiteName = entrySiteName;
	}
	public String getExitSiteName() {
		return exitSiteName;
	}
	public void setExitSiteName(String exitSiteName) {
		this.exitSiteName = exitSiteName;
	}
	public String getVerificationMark() {
		return verificationMark;
	}
	public void setVerificationMark(String verificationMark) {
		this.verificationMark = verificationMark;
	}
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	
	
	
	
		
}
