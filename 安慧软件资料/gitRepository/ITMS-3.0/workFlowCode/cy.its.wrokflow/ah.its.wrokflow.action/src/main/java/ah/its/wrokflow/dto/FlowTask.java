package ah.its.wrokflow.dto;

/**
* @Description: 任务列表
* @author lil@cychina.cn
* @version V1.0   
 */
public class FlowTask {
	
	/**
	 * @Description:任务ID,必选
	 */
	private  String flowTaskId;
	
	/**
	 * @Description:定义流程名称
	 */
	private  String ProcessDefinitionName;
	
	/**
	 * @Description:申请单申请人
	 */
	private  String aprroveUser;
	
	/**
	 * @Description:申请内容简介，可为空
	 */
	private  String aprroveContent;
	
	

	public String getFlowTaskId() {
		return flowTaskId;
	}

	public void setFlowTaskId(String flowTaskId) {
		this.flowTaskId = flowTaskId;
	}

	public String getProcessDefinitionName() {
		return ProcessDefinitionName;
	}

	public void setProcessDefinitionName(String processDefinitionName) {
		ProcessDefinitionName = processDefinitionName;
	}

	public String getAprroveUser() {
		return aprroveUser;
	}

	public void setAprroveUser(String aprroveUser) {
		this.aprroveUser = aprroveUser;
	}

	public String getAprroveContent() {
		return aprroveContent;
	}

	public void setAprroveContent(String aprroveContent) {
		this.aprroveContent = aprroveContent;
	}
	
}
