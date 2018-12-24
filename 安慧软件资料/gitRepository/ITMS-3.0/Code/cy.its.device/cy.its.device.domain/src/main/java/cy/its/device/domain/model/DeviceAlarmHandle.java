package cy.its.device.domain.model;

public class DeviceAlarmHandle {

	/**
	 * 处置ID
	 */
	private String handleId;
	
	/**
	 * 报警ID
	 */
	private String alarmId;
	
	/**
	 * 是否有效0无效1有效
	 */
	private String isValidity;
	
	/**
	 * 处置说明
	 */
	private String handleDisc;
	
	/**
	 * 处置时间
	 */
	private String handleTime;
	
	/**
	 * 处置人
	 */
	private String handlePerson;

	public String getHandleId() {
		return handleId;
	}

	public void setHandleId(String handleId) {
		this.handleId = handleId;
	}

	public String getAlarmId() {
		return alarmId;
	}

	public void setAlarmId(String alarmId) {
		this.alarmId = alarmId;
	}

	public String getIsValidity() {
		return isValidity;
	}

	public void setIsValidity(String isValidity) {
		this.isValidity = isValidity;
	}

	public String getHandleDisc() {
		return handleDisc;
	}

	public void setHandleDisc(String handleDisc) {
		this.handleDisc = handleDisc;
	}

	public String getHandleTime() {
		return handleTime;
	}

	public void setHandleTime(String handleTime) {
		this.handleTime = handleTime;
	}

	public String getHandlePerson() {
		return handlePerson;
	}

	public void setHandlePerson(String handlePerson) {
		this.handlePerson = handlePerson;
	}
	
	
	
}
