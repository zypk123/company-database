package cy.its.device.rest.dto;

import java.util.List;

import cy.its.com.dto.BaseDto;

public class TransectDto extends BaseDto {
	/************* 断面表信息 ******************/
	private String siteId;					//点位ID
	private String sectionId;				//断面ID
	private String laneNum;					//车道数
	private String hasEmergencyLane;		//有无应急车道
	private String hasBicycleLane;			//有无非机动车道
	private String hasPavement;				//有无人行道
	private String directionType;			//方向类型
	private String directionName;			//方向名称
	
	private String sectionIdStr;			//多个断面Id字符串
	private List<LaneDto> laneList;			//断面下所有车道
	private String enterOrExit; //进出城标记
	
	
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	public String getSectionId() {
		return sectionId;
	}
	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}
	public String getLaneNum() {
		return laneNum;
	}
	public void setLaneNum(String laneNum) {
		this.laneNum = laneNum;
	}
	public String getHasEmergencyLane() {
		return hasEmergencyLane;
	}
	public void setHasEmergencyLane(String hasEmergencyLane) {
		this.hasEmergencyLane = hasEmergencyLane;
	}
	public String getHasBicycleLane() {
		return hasBicycleLane;
	}
	public void setHasBicycleLane(String hasBicycleLane) {
		this.hasBicycleLane = hasBicycleLane;
	}
	public String getHasPavement() {
		return hasPavement;
	}
	public void setHasPavement(String hasPavement) {
		this.hasPavement = hasPavement;
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
	public String getSectionIdStr() {
		return sectionIdStr;
	}
	public void setSectionIdStr(String sectionIdStr) {
		this.sectionIdStr = sectionIdStr;
	}
	public List<LaneDto> getLaneList() {
		return laneList;
	}
	public void setLaneList(List<LaneDto> laneList) {
		this.laneList = laneList;
	}
	public String getEnterOrExit() {
		return enterOrExit;
	}
	public void setEnterOrExit(String enterOrExit) {
		this.enterOrExit = enterOrExit;
	}
	
	
	
}
