package ah.its.wrokflow.dto;

/**
* @Title: ProcessDefinitionBean.java 
* @Package cy.its.wrokflow.approve.dto 
* @Description: 前台展示定义流程列表信息
* @author lil@cychina.cn
* @date 2016年3月28日 下午4:02:40 
* @version V1.0   
 */
public class ProcessDefinitionBean {
	
	/**
	 * @Description:ID
	 */
	private  String ProcessDefinitionId;
	
	/**
	 * @Description:流程名称
	 */
	private  String ProcessDefinitionName;
	

	public String getProcessDefinitionId() {
		return ProcessDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		ProcessDefinitionId = processDefinitionId;
	}

	public String getProcessDefinitionName() {
		return ProcessDefinitionName;
	}

	public void setProcessDefinitionName(String processDefinitionName) {
		ProcessDefinitionName = processDefinitionName;
	}
	
}
