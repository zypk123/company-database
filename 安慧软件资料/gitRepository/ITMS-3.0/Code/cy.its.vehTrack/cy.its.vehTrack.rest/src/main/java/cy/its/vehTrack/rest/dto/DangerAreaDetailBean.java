package cy.its.vehTrack.rest.dto;

public class DangerAreaDetailBean {
	/**
	 * 车牌号牌
	 */
	private String plate_nbr;

	/**
	 * 高危区域出现总次数
	 */
	private int plateCount;

	/**
	 * 点位代码
	 */
	private String site_code;

	private String site_name;

	/**
	 * 高危区域中在某点位出现次数
	 */
	private int siteCount;

	/**
	 * 号牌种类
	 */
	private String plate_type;

	/**
	 * 车辆品牌
	 */
	private String vehicle_brand;

	/**
	 * 车身颜色
	 */
	private String vehicle_color;

	public String getPlate_nbr() {
		return plate_nbr;
	}

	public void setPlate_nbr(String plate_nbr) {
		this.plate_nbr = plate_nbr;
	}

	public int getPlateCount() {
		return plateCount;
	}

	public void setPlateCount(int plateCount) {
		this.plateCount = plateCount;
	}

	public String getSite_code() {
		return site_code;
	}

	public void setSite_code(String site_code) {
		this.site_code = site_code;
	}

	public int getSiteCount() {
		return siteCount;
	}

	public void setSiteCount(int siteCount) {
		this.siteCount = siteCount;
	}

	public String getPlate_type() {
		return plate_type;
	}

	public void setPlate_type(String plate_type) {
		this.plate_type = plate_type;
	}

	public String getVehicle_brand() {
		return vehicle_brand;
	}

	public void setVehicle_brand(String vehicle_brand) {
		this.vehicle_brand = vehicle_brand;
	}

	public String getVehicle_color() {
		return vehicle_color;
	}

	public void setVehicle_color(String vehicle_color) {
		this.vehicle_color = vehicle_color;
	}

	public String getSite_name() {
		return site_name;
	}

	public void setSite_name(String site_name) {
		this.site_name = site_name;
	}

}
