package cy.its.vehTrack.rest.dto;

import java.util.List;

public class DangerAreaStatBean {
	
	/**
	 * 车牌号牌
	 */
	private String plate_nbr;
	
	/**
	 * 高危区域出现总次数
	 */
	private int platecount;
	
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

	/**
	 * 明细对象列表
	 */
	private List<DangerAreaDetailBean> detailList;
	
	/**
	 * 开始时间
	 */
	private String startTime;
	
	/**
	 * 结束时间
	 */
	private String endTime;

	public String getPlate_nbr() {
		return plate_nbr;
	}

	public void setPlate_nbr(String plate_nbr) {
		this.plate_nbr = plate_nbr;
	}

	public int getPlatecount() {
		return platecount;
	}

	public void setPlatecount(int platecount) {
		this.platecount = platecount;
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

	public List<DangerAreaDetailBean> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<DangerAreaDetailBean> detailList) {
		this.detailList = detailList;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	

}
