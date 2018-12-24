package cy.its.vehTrack.domain.model;

import java.math.BigDecimal;

public class CloneCarDB {
	
	/*套牌车分析ID（输出）*/
	private String clone_vehicle_id;
	/*车辆牌照(输入输出)*/
	private String plate_nbr;
	/*号牌类型(输入输出)*/
	private String plate_type;
	/*号牌颜色(输入输出)*/
	private String plate_color;
	/* 车牌品牌(输入输出)*/
	private String vehicle_brand;
	/*车辆颜色(输入输出)*/
	private String vehicle_color;
	/*注册车牌颜色（输出）*/
	private String register_plate_color;
	/*注册车牌品牌（输出）*/
	private String register_vehicle_brand;
	/*注册车辆颜色（输出）*/
	private String register_vehicle_color;
	/*注册车辆类型（输出）*/
	private String register_vehicle_type;
	/*设备编号1（输出）*/
	private String device_nbr1;
	/*抓拍编号1（输出）*/
	private String snap_nbr1;
	/*号牌颜色1（输出）*/
	private String plate_color1;
	/*点位1（输出）*/
	private String site_code1;
	/*过车时间1（输出）*/
	private String pass_time1;
	/*设备编号2（输出）*/
	private String device_nbr2;
	/*抓拍编号2（输出）*/
	private String snap_nbr2;
	/*号牌颜色2（输出）*/
	private String plate_color2;
	/*点位2（输出）*/
	private String site_code2;
	/*过车时间2（输出）*/
	private String pass_time2;
	/*（输出）*/
	private String count_form;
	/*（输出）*/
	private String confirm_reason;
	/*认标记。0-未确认；1-已确认；2-证据不足；3-识别错误*/
	/*确认描述*/
	private String confirm_desc;
	private String clone_flag;
	/*过车图片1（输出）*/
	private String image_url1;
	/*过车图片2（输出）*/
	private String image_url2;
	
	private String confirmor;
	
	private String confirm_time;
	
	private String site_name1;
	private String site_name2;
	 private BigDecimal siteLongitude1;
	 private BigDecimal siteLatitude1;
	 private BigDecimal siteLongitude2;
	 private BigDecimal siteLatitude2;
	 
	 /*两点之间的距离*/
	 private Double betweenDistance;
	

	public String getClone_vehicle_id() {
		return clone_vehicle_id;
	}

	public void setClone_vehicle_id(String clone_vehicle_id) {
		this.clone_vehicle_id = clone_vehicle_id;
	}

	public String getPlate_nbr() {
		return plate_nbr;
	}

	public void setPlate_nbr(String plate_nbr) {
		this.plate_nbr = plate_nbr;
	}

	public String getPlate_type() {
		return plate_type;
	}

	public void setPlate_type(String plate_type) {
		this.plate_type = plate_type;
	}

	public String getPlate_color() {
		return plate_color;
	}

	public void setPlate_color(String plate_color) {
		this.plate_color = plate_color;
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

	public String getRegister_plate_color() {
		return register_plate_color;
	}

	public void setRegister_plate_color(String register_plate_color) {
		this.register_plate_color = register_plate_color;
	}

	public String getRegister_vehicle_brand() {
		return register_vehicle_brand;
	}

	public void setRegister_vehicle_brand(String register_vehicle_brand) {
		this.register_vehicle_brand = register_vehicle_brand;
	}

	public String getRegister_vehicle_color() {
		return register_vehicle_color;
	}

	public void setRegister_vehicle_color(String register_vehicle_color) {
		this.register_vehicle_color = register_vehicle_color;
	}

	public String getRegister_vehicle_type() {
		return register_vehicle_type;
	}

	public void setRegister_vehicle_type(String register_vehicle_type) {
		this.register_vehicle_type = register_vehicle_type;
	}

	public String getDevice_nbr1() {
		return device_nbr1;
	}

	public void setDevice_nbr1(String device_nbr1) {
		this.device_nbr1 = device_nbr1;
	}

	public String getSnap_nbr1() {
		return snap_nbr1;
	}

	public void setSnap_nbr1(String snap_nbr1) {
		this.snap_nbr1 = snap_nbr1;
	}

	public String getPlate_color1() {
		return plate_color1;
	}

	public void setPlate_color1(String plate_color1) {
		this.plate_color1 = plate_color1;
	}

	public String getSite_code1() {
		return site_code1;
	}

	public void setSite_code1(String site_code1) {
		this.site_code1 = site_code1;
	}

	public String getPass_time1() {
		return pass_time1;
	}

	public void setPass_time1(String pass_time1) {
		this.pass_time1 = pass_time1;
	}

	public String getDevice_nbr2() {
		return device_nbr2;
	}

	public void setDevice_nbr2(String device_nbr2) {
		this.device_nbr2 = device_nbr2;
	}

	public String getSnap_nbr2() {
		return snap_nbr2;
	}

	public void setSnap_nbr2(String snap_nbr2) {
		this.snap_nbr2 = snap_nbr2;
	}

	public String getPlate_color2() {
		return plate_color2;
	}

	public void setPlate_color2(String plate_color2) {
		this.plate_color2 = plate_color2;
	}

	public String getSite_code2() {
		return site_code2;
	}

	public void setSite_code2(String site_code2) {
		this.site_code2 = site_code2;
	}

	public String getPass_time2() {
		return pass_time2;
	}

	public void setPass_time2(String pass_time2) {
		this.pass_time2 = pass_time2;
	}

	public String getCount_form() {
		return count_form;
	}

	public void setCount_form(String count_form) {
		this.count_form = count_form;
	}

	public String getConfirm_reason() {
		return confirm_reason;
	}

	public void setConfirm_reason(String confirm_reason) {
		this.confirm_reason = confirm_reason;
	}

	public String getClone_flag() {
		return clone_flag;
	}

	public void setClone_flag(String clone_flag) {
		this.clone_flag = clone_flag;
	}

	public String getImage_url1() {
		return image_url1;
	}

	public void setImage_url1(String image_url1) {
		this.image_url1 = image_url1;
	}

	public String getImage_url2() {
		return image_url2;
	}

	public void setImage_url2(String image_url2) {
		this.image_url2 = image_url2;
	}

	public String getConfirmor() {
		return confirmor;
	}

	public void setConfirmor(String confirmor) {
		this.confirmor = confirmor;
	}

	public String getConfirm_time() {
		return confirm_time;
	}

	public void setConfirm_time(String confirm_time) {
		this.confirm_time = confirm_time;
	}

	public String getSite_name1() {
		return site_name1;
	}

	public void setSite_name1(String site_name1) {
		this.site_name1 = site_name1;
	}

	public String getSite_name2() {
		return site_name2;
	}

	public void setSite_name2(String site_name2) {
		this.site_name2 = site_name2;
	}

	public BigDecimal getSiteLongitude1() {
		return siteLongitude1;
	}

	public void setSiteLongitude1(BigDecimal siteLongitude1) {
		this.siteLongitude1 = siteLongitude1;
	}

	public BigDecimal getSiteLatitude1() {
		return siteLatitude1;
	}

	public void setSiteLatitude1(BigDecimal siteLatitude1) {
		this.siteLatitude1 = siteLatitude1;
	}

	public BigDecimal getSiteLongitude2() {
		return siteLongitude2;
	}

	public void setSiteLongitude2(BigDecimal siteLongitude2) {	
		this.siteLongitude2 = siteLongitude2;
	}

	public BigDecimal getSiteLatitude2() {
		return siteLatitude2;
	}

	public void setSiteLatitude2(BigDecimal siteLatitude2) {
		this.siteLatitude2 = siteLatitude2;
	}

	public Double getBetweenDistance() {
		return betweenDistance;
	}

	public void setBetweenDistance(Double betweenDistance) {
		this.betweenDistance = betweenDistance;
	}

	public String getConfirm_desc() {
		return confirm_desc;
	}

	public void setConfirm_desc(String confirm_desc) {
		this.confirm_desc = confirm_desc;
	}
	
	

}
