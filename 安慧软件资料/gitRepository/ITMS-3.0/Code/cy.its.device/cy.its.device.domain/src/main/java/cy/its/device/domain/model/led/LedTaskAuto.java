package cy.its.device.domain.model.led;

import java.util.Date;

import cy.its.com.util.DateUtil;
import cy.its.device.domain.repository.ISysRepository;
import cy.its.device.domain.repository.led.ILedProgRepository;
import cy.its.device.domain.repository.led.ILedTaskRepository;

public class LedTaskAuto extends LedTask {

	/**
	 * 取证设备ID 非LED设备ID
	 */
	private String deviceId;

	/**
	 * 违法区间系统ID
	 */
	private String regionalId;

	private String taskFilter;

	private String enableFlag;

	private LedTimedParam ledTimedParam;

	private LedProgGrab ledProgGrad;

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getRegionalId() {
		return regionalId;
	}

	public void setRegionalId(String regionalId) {
		this.regionalId = regionalId;
	}

	public String getTaskFilter() {
		return taskFilter;
	}

	public void setTaskFilter(String taskFilter) {
		this.taskFilter = taskFilter;
	}

	public String getEnableFlag() {
		return enableFlag;
	}

	public void setEnableFlag(String enableFlag) {
		this.enableFlag = enableFlag;
	}

	public LedProgGrab getLedProgGrad() {
		return ledProgGrad;
	}

	public void setLedProgGrad(LedProgGrab ledProgGrad) {
		this.ledProgGrad = ledProgGrad;
	}

	public LedTimedParam getLedTimedParam() {
		return ledTimedParam;
	}

	public void setLedTimedParam(LedTimedParam ledTimedParam) {
		this.ledTimedParam = ledTimedParam;
	}

	public void checkPrivate(ISysRepository sysRepository) throws Exception {
		if (this.ledProgGrad == null) {
			throw new Exception("自动任务下未分配节目");
		}
		
		this.ledProgGrad.check(sysRepository);
	}

	private static final String FLAG_ENABLE = "1";
	private static final String FLAG_DISABLE = "0";

	public void generate(ILedTaskRepository ledTaskRepository, ILedProgRepository iLedProgRepository) {
		this.setEnableFlag(FLAG_ENABLE);
		this.setCreateTime(new Date());
		this.setIssueCount((short) 0);

		if (this.ledProgGrad != null) {
			this.ledProgGrad.generate(iLedProgRepository);
		}

		this.setTaskId(ledTaskRepository.generateTaskId());
	}

	public boolean isInEnabledStatus() {
		return FLAG_ENABLE.equals(this.enableFlag);
	}

	@Override
	public LedProg ledProgOfTask() {
		return ledProgGrad;
	}

	public void enable() throws Exception {
		if (FLAG_ENABLE.equals(this.enableFlag)) {
			throw new Exception("当前任务已处于启用状态");
		}

		this.enableFlag = FLAG_ENABLE;
	}

	public void disable() throws Exception {
		if (FLAG_DISABLE.equals(this.enableFlag)) {
			throw new Exception("当前任务已处于停用状态");
		}

		this.enableFlag = FLAG_DISABLE;
	}

	@Override
	public String getTimeString() {
		return ledTimedParam != null ? ledTimedParam.getParamString() : DateUtil.formatDate(this.getApproveTime());
	}
}