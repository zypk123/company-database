package cy.its.service.region.domain;

import java.util.Date;

/**
* @Title: VehTrackPass.java 
* @Package cy.its.service.section.domain 
* @Description: 暂时先需要这几个字段
* @author lil@cychina.cn
* @date 2015年11月4日 下午1:59:37 
* @version V1.0   
 */
public class VehTrackPass {
	/**
	* 点位代码
	*/
	private String siteCode;
	/**
	* 号牌号码
	*/
	private String plateNbr;
	
	/**
	* 号牌颜色(003)
	*/
	private String plateColor;
	/**
	* 过车时间
	*/
	private Date passTime;
	
	
	/**
	* 机构代码
	*/
	private String orgCode;
	
	/**
	* 机构权限代码
	*/
	private String orgPrivilegeCode;
	
	
	/**
	* 方向类型（070）
	*/
	private String directionType;
	
	
	public String getDirectionType() {
		return directionType;
	}

	public void setDirectionType(String directionType) {
		this.directionType = directionType;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getPlateNbr() {
		return plateNbr;
	}

	public void setPlateNbr(String plateNbr) {
		this.plateNbr = plateNbr;
	}

	public String getPlateColor() {
		return plateColor;
	}

	public void setPlateColor(String plateColor) {
		this.plateColor = plateColor;
	}

	public Date getPassTime() {
		return passTime;
	}

	public void setPassTime(Date passTime) {
		this.passTime = passTime;
	}

	public String getOrgPrivilegeCode() {
		return orgPrivilegeCode;
	}

	public void setOrgPrivilegeCode(String orgPrivilegeCode) {
		this.orgPrivilegeCode = orgPrivilegeCode;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	
}