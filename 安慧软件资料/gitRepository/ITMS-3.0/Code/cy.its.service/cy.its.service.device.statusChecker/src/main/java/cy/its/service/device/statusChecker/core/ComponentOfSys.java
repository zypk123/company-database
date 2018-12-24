package cy.its.service.device.statusChecker.core;

import java.util.Date;
import java.util.List;

import cy.its.service.common.ConstValue;
import cy.its.service.common.dataModel.SurveyUpgrade_ComponentStatusResult;
import cy.its.service.device.statusChecker.model.DeviceCfg;
import cy.its.service.device.statusChecker.util.StatusUtil;

class ComponentOfSys {

	private String sysComponentId;

	private String deviceNbr;
	
	private String deviceKey;

	private long statusUpdateTime;
	
	private Long deviceCurTime;
	
	private Integer timeDiff;

	private int statusType;

	private List<String> faultDetails;
	
	protected Long lastUploadTime;
	
	protected Long latestDataTimeInDevice;;
	

	/**
	 * 构造函数
	 * @param cfg
	 */
	public ComponentOfSys(DeviceCfg cfg) {
		this.config(cfg);
		this.statusType = ConstValue.DEV_STATUS_TYPE_NORMAL;
		this.faultDetails = null;
		this.statusUpdateTime = System.currentTimeMillis();
	}

	/**
	 * 初始化配置
	 * @param cfg
	 */
	public void config(DeviceCfg cfg) {
		this.sysComponentId = cfg.getCameraId();
		this.deviceNbr = cfg.getCameraNbr();
		this.deviceKey = cfg.getCameraKey();
	}

	/**
	 * 接收心跳数据
	 * @param statusCode
	 * @param statusTime
	 * @param vehicleTotal
	 * @return
	 * @throws Exception 
	 */
	public void receive(int statusCode, List<String> faultCodes, Long deviceCurTime, Integer timeDiff, long statusTime) throws Exception {		
		int newStatusType = StatusUtil.getStatusType(statusCode, faultCodes);
		this.statusUpdateTime = statusTime;
		this.statusType = newStatusType;
		this.faultDetails = faultCodes;
		this.deviceCurTime = deviceCurTime;
		this.timeDiff = timeDiff;
	}

	public void setLastUploadTime(Long lastUploadTime, Long dataTime){
		this.lastUploadTime = lastUploadTime;
		
		if (dataTime != null) {
			if (this.latestDataTimeInDevice == null) {
				this.latestDataTimeInDevice = dataTime;
			} else {
				if (this.latestDataTimeInDevice < dataTime) {
					this.latestDataTimeInDevice = dataTime;
				}
			}
		}
	}
	
	public int getStatusType() {
		return this.statusType;
	}

	public SurveyUpgrade_ComponentStatusResult result() {			
		return new SurveyUpgrade_ComponentStatusResult(
					this.sysComponentId, this.deviceNbr, 
					this.deviceKey, new Date(this.statusUpdateTime),
					this.statusType, this.faultDetails,
					this.deviceCurTime != null ? new Date(this.deviceCurTime) : null,
					this.timeDiff,
					this.lastUploadTime != null ? new Date(this.lastUploadTime) : null,
					this.latestDataTimeInDevice != null ? new Date(this.latestDataTimeInDevice): null);
	}

	public Long getDeivceCurTime() {
		return deviceCurTime;
	}

	public Integer getTimeDiff() {
		return timeDiff;
	}
}
