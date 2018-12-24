package cy.its.device.domain.criteria;

import java.util.Date;

import cy.its.com.domain.Criteria;

public class LedPublishLogCriteria extends Criteria {
	// 机构、
	public String orgPrivilegeCode;
	
	// 道路、
	public String roadId;
	
	// 设备、
	public String deviceId;
	
	// 发布方式
	public String[] publishMethodArr;

	// 信息类型、
	public String messageType;

	// 下发时间范围、
	public Date publishTimeFrom;

	// 下发时间范围、
	public Date publishTimeTo;

	// 节目
	public String programId;

	// 任务类型
	public String taskType;
	
	// 发布结果
	public String[] resultArr;
	
	// 任务ID
	public String taskId;
	
}
