package cy.its.vehTrack.repository.bigData.model;

import cy.its.com.dto.BaseDto;

public class CloneCarCriteriaBD extends BaseDto{

	private String plate_nbr;
	private String startTime;
	private String endTime;
	private String clone_flag;
	
	/**
	 * 历史查询总数
	 */
	private int totalCount;
	

	public String getPlate_nbr() {
		return plate_nbr;
	}

	public void setPlate_nbr(String plate_nbr) {
		this.plate_nbr = plate_nbr;
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

	public String getClone_flag() {
		return clone_flag;
	}

	public void setClone_flag(String clone_flag) {
		this.clone_flag = clone_flag;
	}
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
}
