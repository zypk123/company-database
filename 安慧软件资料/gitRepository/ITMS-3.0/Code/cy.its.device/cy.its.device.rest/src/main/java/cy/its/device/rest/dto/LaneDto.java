package cy.its.device.rest.dto;

import cy.its.com.dto.BaseDto;
/**
 * 车道实体类
 * @author qianfuxing
 *
 */
public class LaneDto extends BaseDto{
	private String laneId;		//车道ID
	private String sectionId;	//断面ID
	private String laneNbr;		//车道编号
	private String laneType;	//车道类型
	private String isRestrict;	//是否限行
	private String limitLarge;	//大车限高速
	private String limitSmall;	//小车限高速
	private String limitLower;	//限低速
	private String limitOthers;	//其他车限高速
	
	
	public String getLaneId() {
		return laneId;
	}
	public void setLaneId(String laneId) {
		this.laneId = laneId;
	}
	public String getSectionId() {
		return sectionId;
	}
	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}
	public String getLaneNbr() {
		return laneNbr;
	}
	public void setLaneNbr(String laneNbr) {
		this.laneNbr = laneNbr;
	}
	public String getLaneType() {
		return laneType;
	}
	public void setLaneType(String laneType) {
		this.laneType = laneType;
	}
	public String getIsRestrict() {
		return isRestrict;
	}
	public void setIsRestrict(String isRestrict) {
		this.isRestrict = isRestrict;
	}
	public String getLimitLarge() {
		return limitLarge;
	}
	public void setLimitLarge(String limitLarge) {
		this.limitLarge = limitLarge;
	}
	public String getLimitSmall() {
		return limitSmall;
	}
	public void setLimitSmall(String limitSmall) {
		this.limitSmall = limitSmall;
	}
	public String getLimitLower() {
		return limitLower;
	}
	public void setLimitLower(String limitLower) {
		this.limitLower = limitLower;
	}
	public String getLimitOthers() {
		return limitOthers;
	}
	public void setLimitOthers(String limitOthers) {
		this.limitOthers = limitOthers;
	}
	
}
