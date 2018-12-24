package cy.its.vehTrack.rest.dto;

import java.util.List;

public class AccompanyStatBean {
	/**
	 * 主车号牌
	 */
	private String plate_nbr;
	
	/**
	 * 主车号牌类型
	 */
	private String plate_type;

	/**
	 * 跟随车辆号牌
	 */
	private String accplatenbr;
	
	/**
	 * 跟随车辆号牌类型
	 */
	private String accPlateType;
	
	/**
	 * 伴随次数
	 */
	private int acccount;
	
	/**
	 * 伴随明细对象列表
	 */
	private List<AccompanyDetailBean> detailList;

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

	public String getAccplatenbr() {
		return accplatenbr;
	}

	public void setAccplatenbr(String accplatenbr) {
		this.accplatenbr = accplatenbr;
	}

	public String getAccPlateType() {
		return accPlateType;
	}

	public void setAccPlateType(String accPlateType) {
		this.accPlateType = accPlateType;
	}

	public int getAcccount() {
		return acccount;
	}

	public void setAcccount(int acccount) {
		this.acccount = acccount;
	}

	public List<AccompanyDetailBean> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<AccompanyDetailBean> detailList) {
		this.detailList = detailList;
	}
	
	
}
