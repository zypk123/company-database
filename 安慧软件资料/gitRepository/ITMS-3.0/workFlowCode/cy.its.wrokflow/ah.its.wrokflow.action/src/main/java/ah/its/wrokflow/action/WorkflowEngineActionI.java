package ah.its.wrokflow.action;

import java.util.List;
import java.util.Map;

import ah.its.wrokflow.dto.CheckRecord;
import ah.its.wrokflow.dto.InstanceRecord;

/**
* @Package cy.its.wrokflow.approve.action 
* @Description: 主要处理跟工作流引擎方面的接口
* @author lil@cychina.cn
* @version V1.0   
 */
public interface WorkflowEngineActionI {
	
	/** 
	* @Description: 部署流程，该流程不在指定目录，可本地上传然后传入地址 
	* @param @param fileUrl
	* @param @return  成功标志 1部署成功 0失败
	* @return Map    返回类型
	* @throws 
	*/
	public Map deployById(String fileUrl);
	
	
	
	/** 
	* @Description: 分页方式获取所有的流程部署数据
	* @param @param startPage
	* @param @param pageSize
	* @param @return    设定文件 
	* @return Map    返回类型 
	* @throws 
	*/
	public Map queryDeployAll(String startPage, String pageSize);
	
	
	/** 
	* @Description: 提交申请单，然后启动流程，返回流程实例ID
	* @param @param ProcessDefinitionId
	* @param @param approveId
	* @param @return    启动流程实例的ID 
	* @return String    返回类型 
	* @throws 
	*/
	public String  startProcessDefinitionById(String ProcessDefinitionId,String approveId);
	
	
	
	/** 
	* @Description: 任务签收，根据任务ID签收任务,如果已分配本人 也可以调用此方法让人代签
	* @param @param userId
	* @param @param taskId
	* @param @return   成功1 失败0
	* @return Map    返回类型 
	* @throws 
	*/
	public Map  completeClaimTask(String userId,String taskId);
	
	
	/** 
	* @Description: 根据部门的ID 获取部门下所有的任务信息
	* @param @param groupId
	* @param @return     返回任务FlowTask集合,data list
	* @return Map    返回类型 
	* @throws 
	*/
	public Map  getAllClaimTask(String groupId);
	
	/** 
	* @Description: 通过用户获取用户下所有的工作流任务 
	* @param @param userId
	* @param @return   返回用户下所有工作流任务
	* @return List<FlowTask>    返回类型 
	* @throws 
	*/
	public Map  getAllClaimTaskByUser(String userId);
	
	
	
	/** 
	* @Description: 用户选择 任务审批
	* @param @param userId
	* @param @param taskId
	* @param @param variables
	* @param @return  成功1 失败0 
	* @return Map    返回类型 
	* @throws 
	*/
	public Map  completeCheckTask(String userId,String taskId,Map<String, Object> variables);
	
	/** 
	* @Description: 根据实例ID获取审批记录 
	* @param @param ProcessDefinitionInstancetId
	* @param @return    返回审批记录的集合 
	* @return List<CheckResult>    返回类型 
	* @throws 
	*/
	public List<CheckRecord>  getProcessCheckRecord(String ProcessDefinitionInstancetId);
	
	
	/** 
	* @Description: 根据流程ID获取该流程一共启动多少个实例
	* @param @param ProcessDefinitionId
	* @param @return    实例记录 
	* @return List<InstanceRecord>    返回类型 
	* @throws 
	*/
	public List<InstanceRecord>  queryHistoryProcessInstance(String ProcessDefinitionId);
	
	/** 
	* @Description: 根据流程实例ID，判定流程是否已经完成
	* @param @param ProcessDefinitionInstanceId
	* @param @return    staus 成功 1  失败0 
	* @return Map    返回类型 
	* @throws 
	*/
	public Map  queryProcessInstanceStatus(String ProcessDefinitionInstanceId);

	/** 
	* @Description: 通过KEY 启动流程实例
	* @param @param processDefinitionkey
	* @param @param approveId
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	public String startProcessDefinitionByKey(String processDefinitionkey,
			String approveId);



	/** 
	* @Description: 返回下个节点处理信息，如果没有则说明流程已经走完 
	* @param @param prcesssInsId
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	public String getNextChk(String prcesssInsId);
	
	
	/** 
	* @Description: 判断流程是否已结束
	* @param @param taskId
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws 
	*/
	public boolean isOverByTask(String processInstanceId);
	
	
}
