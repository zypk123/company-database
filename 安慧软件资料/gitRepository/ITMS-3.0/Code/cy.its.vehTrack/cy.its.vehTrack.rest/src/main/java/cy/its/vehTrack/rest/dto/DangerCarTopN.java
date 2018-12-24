package cy.its.vehTrack.rest.dto;

public class DangerCarTopN {
	
	/**
	 * 车辆拍牌照
	 */
	private String plateNbr;
	
	/**
	 * 车牌类型
	 */
	private String plateType;
	
	/**
	 * 罚分
	 */
	private int fineScore;
	
	/**
	 * 罚分类型
	 */
	private int forceType;

	public String getPlateNbr() {
		return plateNbr;
	}

	public void setPlateNbr(String plateNbr) {
		this.plateNbr = plateNbr;
	}

	public String getPlateType() {
		return plateType;
	}

	public void setPlateType(String plateType) {
		this.plateType = plateType;
	}

	public int getFineScore() {
		return fineScore;
	}

	public void setFineScore(int fineScore) {
		this.fineScore = fineScore;
	}

	public int getForceType() {
		return forceType;
	}

	public void setForceType(int forceType) {
		this.forceType = forceType;
	}
	
	

}
