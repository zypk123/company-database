package cy.its.device.domain.model.led;

/**
 * 定时节目
 * 
 * @author STJ
 *
 */
public class LedProgTimed extends LedProg {

	private LedTimedParam ledTimedParam;
	
//	private Short timeMode;
//
//	private String startDate;
//
//	private String endDate;
//
//	private String startTime;
//
//	private String endTime;
//
//	private String week;
	
	public LedProgTimed() {}
	public LedProgTimed(LedTimedParam ledTimedParam) {
		this.ledTimedParam = ledTimedParam;
	}
	
	
//	public LedProgTimed(Short timeMode, String startDate, String endDate, String startTime, String endTime,
//			String week) {
//		super();
//		this.timeMode = timeMode;
//		this.startDate = startDate;
//		this.endDate = endDate;
//		this.startTime = startTime;
//		this.endTime = endTime;
//		this.week = week;
//	}
//
//	public Short getTimeMode() {
//		return timeMode;
//	}
//
//	public void setTimeMode(Short timeMode) {
//		this.timeMode = timeMode;
//	}
//
//	public String getStartDate() {
//		return startDate;
//	}
//
//	public void setStartDate(String startDate) {
//		this.startDate = startDate;
//	}
//
//	public String getEndDate() {
//		return endDate;
//	}
//
//	public void setEndDate(String endDate) {
//		this.endDate = endDate;
//	}
//
//	public String getStartTime() {
//		return startTime;
//	}
//
//	public void setStartTime(String startTime) {
//		this.startTime = startTime;
//	}
//
//	public String getEndTime() {
//		return endTime;
//	}
//
//	public String getWeek() {
//		return week;
//	}
//
//	public void setWeek(String week) {
//		this.week = week;
//	}

	public LedTimedParam getLedTimedParam() {
		return ledTimedParam;
	}
	public void setLedTimedParam(LedTimedParam ledTimedParam) {
		this.ledTimedParam = ledTimedParam;
	}
	
	@Override
	public void checkPlayParams() throws Exception {
		if(this.ledTimedParam == null){
			throw new Exception("定时节目未指定定时参数");
		}
		
		this.ledTimedParam.check();
	}

}
