package cy.its.service.region.domain;


/**
* @Title: Section.java 
* @Package cy.its.service.section.domain 
* @Description: 区间对象
* @author lil@cychina.cn
* @date 2015年11月4日 下午2:01:17 
* @version V1.0   
 */
public class Region {
	/**
	 * 区间ID
	 */
	private String regionalId;
	/**
	 * 起点点位
	 */
	private String startSiteCode;
	/**
	 * 终点点位
	 */
	private String endSiteCode;

	/**
	* 方向类型（070）
	*/
	private String directionType;
	
	/**
	 * 区间距离
	 */
	private String  distance;
	
	/**
	 * 道路类型
	 */
	private String roadType;
	
    
	/**
	 *机构权限代码
	 */
    private String  orgPrivilegeCode;
    
    
	public String getOrgPrivilegeCode() {
		return orgPrivilegeCode;
	}

	public void setOrgPrivilegeCode(String orgPrivilegeCode) {
		this.orgPrivilegeCode = orgPrivilegeCode;
	}

	public String getRoadType() {
		return roadType;
	}

	public void setRoadType(String roadType) {
		this.roadType = roadType;
	}

	public String getRegionalId() {
		return regionalId;
	}

	public void setRegionalId(String regionalId) {
		this.regionalId = regionalId;
	}

	public String getStartSiteCode() {
		return startSiteCode;
	}

	public void setStartSiteCode(String startSiteCode) {
		this.startSiteCode = startSiteCode;
	}

	public String getEndSiteCode() {
		return endSiteCode;
	}

	public void setEndSiteCode(String endSiteCode) {
		this.endSiteCode = endSiteCode;
	}

	public String getDirectionType() {
		return directionType;
	}

	public void setDirectionType(String directionType) {
		this.directionType = directionType;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}
	
}
