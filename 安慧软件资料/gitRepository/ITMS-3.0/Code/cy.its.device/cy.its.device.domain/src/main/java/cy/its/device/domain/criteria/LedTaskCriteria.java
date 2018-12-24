package cy.its.device.domain.criteria;

import java.util.Date;

import cy.its.com.domain.Criteria;

public class LedTaskCriteria extends Criteria {	
	
	// 机构、
	public String orgPrivilegeCode;

	// 道路、
	public String roadId;

	// 设备、
	public String deviceId;

	// 任务类型
	public String taskType;

	// 信息类型、
	public String messageType;

	// 创建时间范围、
	public Date createTimeFrom;

	// 创建时间范围、
	public Date createTimeTo;

	// 执行状态
	public String[] issueStatusArr;
	
	// 任务标识/流水号
	public String[] taskIdArr;
	
	// 节目编号列表
	public String[] programNoArr;
}
