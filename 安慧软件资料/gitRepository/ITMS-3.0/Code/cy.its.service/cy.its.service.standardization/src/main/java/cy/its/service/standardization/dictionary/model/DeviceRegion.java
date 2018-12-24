package cy.its.service.standardization.dictionary.model;

import java.math.BigDecimal;

import cy.its.service.common.dataAccess.MapColumn;

public class DeviceRegion {
	@MapColumn
	private String regionalcode;

	@MapColumn
	private String regionalname;

	@MapColumn
	private String districtcode;

	@MapColumn
	private String directiontype;

	@MapColumn
	private String directionname;

	@MapColumn
	private BigDecimal distance;

	@MapColumn
	private BigDecimal limitlarge;

	@MapColumn
	private BigDecimal limitsmall;

	@MapColumn
	private BigDecimal limitlower;

	@MapColumn
	private String orgcode;

	@MapColumn
	private String orgprivilege;

	@MapColumn
	private String roadcode;

	@MapColumn
	private String entrysitecode;

	@MapColumn
	private String entrysitename;

	@MapColumn
	private String exitsitecode;

	@MapColumn
	private String exitsitename;

	public String getRegionalcode() {
		return regionalcode;
	}

	public void setRegionalcode(String regionalcode) {
		this.regionalcode = regionalcode;
	}

	public String getRegionalname() {
		return regionalname;
	}

	public void setRegionalname(String regionalname) {
		this.regionalname = regionalname;
	}

	public String getDistrictcode() {
		return districtcode;
	}

	public void setDistrictcode(String districtcode) {
		this.districtcode = districtcode;
	}

	public String getDirectiontype() {
		return directiontype;
	}

	public void setDirectiontype(String directiontype) {
		this.directiontype = directiontype;
	}

	public String getDirectionname() {
		return directionname;
	}

	public void setDirectionname(String directionname) {
		this.directionname = directionname;
	}

	public BigDecimal getDistance() {
		return distance;
	}

	public void setDistance(BigDecimal distance) {
		this.distance = distance;
	}

	public BigDecimal getLimitlarge() {
		return limitlarge;
	}

	public void setLimitlarge(BigDecimal limitlarge) {
		this.limitlarge = limitlarge;
	}

	public BigDecimal getLimitsmall() {
		return limitsmall;
	}

	public void setLimitsmall(BigDecimal limitsmall) {
		this.limitsmall = limitsmall;
	}

	public BigDecimal getLimitlower() {
		return limitlower;
	}

	public void setLimitlower(BigDecimal limitlower) {
		this.limitlower = limitlower;
	}

	public String getOrgcode() {
		return orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}

	public String getOrgprivilege() {
		return orgprivilege;
	}

	public void setOrgprivilege(String orgprivilege) {
		this.orgprivilege = orgprivilege;
	}

	public String getRoadcode() {
		return roadcode;
	}

	public void setRoadcode(String roadcode) {
		this.roadcode = roadcode;
	}

	public String getEntrysitecode() {
		return entrysitecode;
	}

	public void setEntrysitecode(String entrysitecode) {
		this.entrysitecode = entrysitecode;
	}

	public String getEntrysitename() {
		return entrysitename;
	}

	public void setEntrysitename(String entrysitename) {
		this.entrysitename = entrysitename;
	}

	public String getExitsitecode() {
		return exitsitecode;
	}

	public void setExitsitecode(String exitsitecode) {
		this.exitsitecode = exitsitecode;
	}

	public String getExitsitename() {
		return exitsitename;
	}

	public void setExitsitename(String exitsitename) {
		this.exitsitename = exitsitename;
	}
}
