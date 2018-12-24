/**
 * @Title: ManualStateDto.java
 * @Package cy.its.trafficSituation.rest.dto
 * @Description: 人工干预dto
 * @author gyf guanyf@cychina.cn
 * @date 2015年11月18日 上午9:46:17
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.trafficSituation.rest.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cy.its.com.dto.BaseDto;
import cy.its.com.util.ObjectMapUtils;
import cy.its.trafficSituation.domain.model.TrafficManualState;

public class ManualStateDto extends BaseDto {
	 	private String stateControlId;

	    private String roadId;
	    
	    private String regionalId;

	    private String controlledState;

	    private String controlledBy;

	    private String controlTime;

	    private String orgPrivilegeCode;

	    private String startTime;

	    private String endTime;

	    private String reason;

		/**
		 * getter method
		 * @return the stateControlId
		 */
		
		public String getStateControlId() {
			return stateControlId;
		}

		/**
		 * setter method
		 * @param stateControlId the stateControlId to set
		 */
		
		public void setStateControlId(String stateControlId) {
			this.stateControlId = stateControlId;
		}

		/**
		 * getter method
		 * @return the roadId
		 */
		
		public String getRoadId() {
			return roadId;
		}

		/**
		 * setter method
		 * @param roadId the roadId to set
		 */
		
		public void setRoadId(String roadId) {
			this.roadId = roadId;
		}

		/**
		 * getter method
		 * @return the regionalId
		 */
		
		public String getRegionalId() {
			return regionalId;
		}

		/**
		 * setter method
		 * @param regionalId the regionalId to set
		 */
		
		public void setRegionalId(String regionalId) {
			this.regionalId = regionalId;
		}

		/**
		 * getter method
		 * @return the controlledState
		 */
		
		public String getControlledState() {
			return controlledState;
		}

		/**
		 * setter method
		 * @param controlledState the controlledState to set
		 */
		
		public void setControlledState(String controlledState) {
			this.controlledState = controlledState;
		}

		/**
		 * getter method
		 * @return the controlledBy
		 */
		
		public String getControlledBy() {
			return controlledBy;
		}

		/**
		 * setter method
		 * @param controlledBy the controlledBy to set
		 */
		
		public void setControlledBy(String controlledBy) {
			this.controlledBy = controlledBy;
		}

		/**
		 * getter method
		 * @return the controlTime
		 */
		
		public String getControlTime() {
			return controlTime;
		}

		/**
		 * setter method
		 * @param controlTime the controlTime to set
		 */
		
		public void setControlTime(String controlTime) {
			this.controlTime = controlTime;
		}

		/**
		 * getter method
		 * @return the orgPrivilegeCode
		 */
		
		public String getOrgPrivilegeCode() {
			return orgPrivilegeCode;
		}

		/**
		 * setter method
		 * @param orgPrivilegeCode the orgPrivilegeCode to set
		 */
		
		public void setOrgPrivilegeCode(String orgPrivilegeCode) {
			this.orgPrivilegeCode = orgPrivilegeCode;
		}

		/**
		 * getter method
		 * @return the startTime
		 */
		
		public String getStartTime() {
			return startTime;
		}

		/**
		 * setter method
		 * @param startTime the startTime to set
		 */
		
		public void setStartTime(String startTime) {
			this.startTime = startTime;
		}

		/**
		 * getter method
		 * @return the endTime
		 */
		
		public String getEndTime() {
			return endTime;
		}

		/**
		 * setter method
		 * @param endTime the endTime to set
		 */
		
		public void setEndTime(String endTime) {
			this.endTime = endTime;
		}

		/**
		 * getter method
		 * @return the reason
		 */
		
		public String getReason() {
			return reason;
		}

		/**
		 * setter method
		 * @param reason the reason to set
		 */
		
		public void setReason(String reason) {
			this.reason = reason;
		}
		public ManualStateDto(){
			
		}
		public ManualStateDto(TrafficManualState state){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ObjectMapUtils.parseObject(this, state);
			if(state.getControlTime()!=null){
				this.setControlTime(sdf.format(state.getControlTime()));
			}
			if(state.getStartTime()!=null){
				this.setStartTime(sdf.format(state.getStartTime()));
			}
			if(state.getEndTime()!=null){
				this.setEndTime(sdf.format(state.getEndTime()));
			}
		}
		public TrafficManualState parseToTraffic() throws ParseException{
			TrafficManualState state=new TrafficManualState();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ObjectMapUtils.parseObject(state,this);
			//更新时间及更新用户
			state.setControlTime(new Date());
			state.setControlledBy(this.getCurrentUserName());
			
			if(this.getStartTime()!=null){
				state.setStartTime(sdf.parse(this.getStartTime()));
			}
			if(this.getEndTime()!=null){
				state.setEndTime(sdf.parse(this.getEndTime()));
			}
			
			return state;
		}
}
