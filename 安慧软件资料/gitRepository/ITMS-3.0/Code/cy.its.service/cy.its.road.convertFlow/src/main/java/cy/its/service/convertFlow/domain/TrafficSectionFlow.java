package cy.its.service.convertFlow.domain;

import java.util.Date;

import cy.its.service.util.TableColumn;

/**
* @Title:TrafficSectionFlow.java 
* @Package cy.its.road.convertFlow.domain 
* @Description:断面流量表
* @author lil@cychina.cn
* @date 2015年10月28日 下午6:39:00 
* @version V1.0   
 */
public class TrafficSectionFlow {
	
	//ID
	@TableColumn(name="SECTION_STATE_ID")
    private String sectionStateId;

    /*
     * 断面ID
     */
	@TableColumn(name="SECTION_ID")
    private String sectionId;

    /*
     * 区间内总车数
     */
	@TableColumn(name="TOTAL_NUM")
    private Integer totalNum;
	/*
	 * 大车数
	 */
	@TableColumn(name="LARGE_NUM")
    private Integer largeNum;
	/*
	 * 小车数
	 */
	@TableColumn(name="SMALL_NUM")
    private Integer smallNum;
	/*
	 * 其它车数
	 */
	@TableColumn(name="OTHER_NUM")
    private Integer otherNum;
	/*
	 * 平均车速
	 */
	@TableColumn(name="AVG_SPEED")
    private double avgSpeed;
	/*
	 * 车头时距
	 */
	@TableColumn(name="VEH_TAIL_SPACE")
    private double vehTailSpace;
	/*
	 * 数据更新时间
	 */
	@TableColumn(name="UPDATE_TIME")
    private Date updateTime;
	
	/*
	 * 数据权限过滤
	 */
	@TableColumn(name="ORG_PRIVILEGE_CODE")
    private String orgPrivilegeCode;
	

    public String getOrgPrivilegeCode() {
		return orgPrivilegeCode;
	}

	public void setOrgPrivilegeCode(String orgPrivilegeCode) {
		this.orgPrivilegeCode = orgPrivilegeCode;
	}

	public String getSectionStateId() {
        return sectionStateId;
    }

    public void setSectionStateId(String sectionStateId) {
        this.sectionStateId = sectionStateId;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getLargeNum() {
        return largeNum;
    }

    public void setLargeNum(Integer largeNum) {
        this.largeNum = largeNum;
    }

    public Integer getSmallNum() {
        return smallNum;
    }

    public void setSmallNum(Integer smallNum) {
        this.smallNum = smallNum;
    }

    public Integer getOtherNum() {
        return otherNum;
    }

    public void setOtherNum(Integer otherNum) {
        this.otherNum = otherNum;
    }

    /**
	 * @return the avgSpeed
	 */
	public double getAvgSpeed() {
		return avgSpeed;
	}

	/**
	 * @param avgSpeed the avgSpeed to set
	 */
	public void setAvgSpeed(double avgSpeed) {
		this.avgSpeed = avgSpeed;
	}

	public double getVehTailSpace() {
        return vehTailSpace;
    }

    public void setVehTailSpace(double vehTailSpace) {
        this.vehTailSpace = vehTailSpace;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}