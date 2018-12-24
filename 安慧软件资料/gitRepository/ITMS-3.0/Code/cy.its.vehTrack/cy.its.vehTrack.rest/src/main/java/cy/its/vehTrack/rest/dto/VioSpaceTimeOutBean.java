package cy.its.vehTrack.rest.dto;

import java.util.List;

public class VioSpaceTimeOutBean {

	private String dateStr;
	private String district_code;
	private String org_code;
	private String road_code;
	private String road_section_code;
	private String vio_site_code;
	private String vioCount;
	private String vioTypeCountStr;
	private String vioDensity;
	private List<DetailBean> vioTypeCountList;
	private String road_name;
	private String road_section_name;
	private String site_name;

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public String getSite_name() {
		return site_name;
	}

	public void setSite_name(String site_name) {
		this.site_name = site_name;
	}

	public String getRoad_section_name() {
		return road_section_name;
	}

	public void setRoad_section_name(String road_section_name) {
		this.road_section_name = road_section_name;
	}

	public String getRoad_name() {
		return road_name;
	}

	public void setRoad_name(String road_name) {
		this.road_name = road_name;
	}

	public String getDistrict_code() {
		return district_code;
	}

	public void setDistrict_code(String district_code) {
		this.district_code = district_code;
	}

	public String getOrg_code() {
		return org_code;
	}

	public void setOrg_code(String org_code) {
		this.org_code = org_code;
	}

	public String getRoad_code() {
		return road_code;
	}

	public void setRoad_code(String road_code) {
		this.road_code = road_code;
	}

	public String getRoad_section_code() {
		return road_section_code;
	}

	public void setRoad_section_code(String road_section_code) {
		this.road_section_code = road_section_code;
	}

	public String getVio_site_code() {
		return vio_site_code;
	}

	public void setVio_site_code(String vio_site_code) {
		this.vio_site_code = vio_site_code;
	}

	public String getVioCount() {
		return vioCount;
	}

	public void setVioCount(String vioCount) {
		this.vioCount = vioCount;
	}

	public String getVioTypeCountStr() {
		return vioTypeCountStr;
	}

	public void setVioTypeCountStr(String vioTypeCountStr) {
		this.vioTypeCountStr = vioTypeCountStr;
	}

	public String getVioDensity() {
		return vioDensity;
	}

	public void setVioDensity(String vioDensity) {
		this.vioDensity = vioDensity;
	}

	public List<DetailBean> getVioTypeCountList() {
		return vioTypeCountList;
	}

	public void setVioTypeCountList(List<DetailBean> vioTypeCountList) {
		this.vioTypeCountList = vioTypeCountList;
	}

	public class DetailBean {
		private String key;
		private Integer value;

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public Integer getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}

	}

}
