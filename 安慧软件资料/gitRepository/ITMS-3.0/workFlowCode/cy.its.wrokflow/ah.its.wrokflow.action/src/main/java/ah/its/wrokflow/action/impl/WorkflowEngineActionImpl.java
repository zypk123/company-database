package ah.its.wrokflow.action.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ah.its.wrokflow.action.WorkflowEngineActionI;
import ah.its.wrokflow.dto.CheckRecord;
import ah.its.wrokflow.dto.InstanceRecord;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.StringUtil;

@RestController
@RequestMapping("/workFlow/wfservice")
public class WorkflowEngineActionImpl implements WorkflowEngineActionI {

	/**
	 * 注入工作流引擎
	 */
	@Autowired
	private ProcessEngineFactoryBean processEngine;
	
	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired
	private RuntimeService runtimeService;

	@Autowired
    private TaskService taskService;

	@Autowired
    private HistoryService historyService;

	@Autowired
    private IdentityService IdentityService;
	
	@Autowired
    private ManagementService managementService;
	
	/**
	 * 根据JBPM.XML部署工作流
	 */
	@Override
	@RequestMapping(value ="/deployById")
	public Map deployById(@RequestParam(value ="fileUrl") String fileUrl) {
		Map  map = new HashMap();
		try{
			repositoryService.createDeployment().addClasspathResource(fileUrl).deploy();
			map.put("status","1");
		}catch(Exception e){
			/**
			 * 出现异常，失败标志
			 */
			map.put("status","0");
		}
		return map;
	}
	

	@Override
	@RequestMapping(value ="/queryDeployAll")
	public Map queryDeployAll(@RequestParam(value ="startPage") String startPage ,@RequestParam(value ="pageSize") String pageSize) {
		Map  map = new HashMap();
		long  count  = repositoryService.createProcessDefinitionQuery().count();
		List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().orderByDeploymentId().desc().listPage(Integer.valueOf(startPage),Integer.valueOf(pageSize));
		JSONArray    array  = new JSONArray();
		for(ProcessDefinition  pd :list){
			JSONObject   obj   = new  JSONObject();
			obj.put("processId", pd.getId());
			obj.put("processName", pd.getName());
			obj.put("version", pd.getVersion());
			obj.put("description", pd.getDescription());
			array.add(obj);
		}
		map.put("data", array);
		map.put("count",count);
		return map;
	}
	/**
	 * 启动流程，需要出入申请单ID
	 */
	@Override
	@RequestMapping(value ="/startProcessDefinitionById")
	public String startProcessDefinitionById(@RequestParam(value ="processDefinitionId") String processDefinitionId ,@RequestParam(value ="approveId") String approveId) {
		// TODO Auto-generated method stub
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinitionId,approveId);
		//返回流程实例 ID
		return processInstance.getActivityId();
	}
	
	/**
	 * 启动流程，需要出入申请单ID
	 */
	@Override
	@RequestMapping(value ="/startProcessDefinitionByKey")
	public String startProcessDefinitionByKey(@RequestParam(value ="processDefinitionkey") String processDefinitionkey,@RequestParam(value ="approveId") String approveId ) {
		// TODO Auto-generated method stub
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionkey,approveId);
		//返回流程实例 ID
		return processInstance.getId();
	}

	@Override
	@RequestMapping(value ="/completeClaimTask")
	public Map completeClaimTask(@RequestParam(value ="userId") String userId,@RequestParam(value ="taskIds") String taskIds) {
		Map  map = new HashMap();
		String[]  tmp = taskIds.split(",");
		try{
			for(String task:tmp){
				if(!StringUtil.isEmpty(task)){
					taskService.claim(task,userId);
				}
			}
			map.put("status", "1");
		}catch(Exception e){
			map.put("status", "0");
		}
		
		return map;
	}

	@Override
	@RequestMapping(value ="/getAllClaimTask")
	public Map getAllClaimTask(@RequestParam(value ="groupId") String groupId) {
		// TODO Auto-generated method stub
		SimpleDateFormat   sd  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		HashMap  map  = new HashMap();
		List<Task> tasks = taskService
                .createTaskQuery()
                .taskCandidateGroup(groupId)
                .list();
		JSONArray  array  = new JSONArray();
		for(Task  task :tasks){
			JSONObject obj= new JSONObject();
			obj.put("taskId",task.getId());
			obj.put("taskName", task.getName());
			obj.put("createTime", sd.format(task.getCreateTime()));
			obj.put("processInstanceId", task.getProcessInstanceId());
			array.add(obj);
		}
		map.put("data", array);
		map.put("count", array.size());
		return map;
	}

	@Override
	@RequestMapping(value ="/getAllClaimTaskByUser")
	public Map getAllClaimTaskByUser(@RequestParam(value ="userId") String userId ) {
		SimpleDateFormat   sd  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		HashMap  map  = new HashMap();
		List<Task> tasks = taskService
                .createTaskQuery()
                .taskAssignee(userId)
                .list();
		JSONArray  array  = new JSONArray();
		ProcessInstance pi = null;
		for(Task  task :tasks){
			JSONObject obj= new JSONObject();
			obj.put("taskId",task.getId());
			obj.put("taskName", task.getName());
			obj.put("createTime", sd.format(task.getCreateTime()));
			pi = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
			obj.put("approveId",pi.getBusinessKey());
			obj.put("processInstanceId", task.getProcessInstanceId());
			array.add(obj);
		}
		map.put("data", array);
		map.put("count", array.size());
		return map;
	}

	/**
	 * 任务审批
	 */
	@Override
	public Map completeCheckTask(String userId, String taskId,
			Map<String, Object> variables) {
		// TODO Auto-generated method stub
		Map  map  = new  HashMap();
		try{
			taskService.complete(taskId, variables);
			map.put("status", "1");
		}catch(Exception e){
			e.printStackTrace();
			map.put("status", "0");
		}
		return map;
	}
	@Override
	@RequestMapping(value ="/getProcessCheckRecord")
	public List<CheckRecord> getProcessCheckRecord(
			String ProcessDefinitionInstancetId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@RequestMapping(value ="/queryHistoryProcessInstance")
	public List<InstanceRecord> queryHistoryProcessInstance(
			String processDefinitionId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Map queryProcessInstanceStatus(String ProcessDefinitionInstanceId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getNextChk(String prcesssInsId) {
		 //流程标示  
		String  activtiName = null;
        String processDefinitionId = historyService.createHistoricProcessInstanceQuery().processInstanceId(prcesssInsId).singleResult().getProcessDefinitionId();  
        ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl)repositoryService).getDeployedProcessDefinition(processDefinitionId);  
        //执行实例  
        ExecutionEntity execution = (ExecutionEntity) runtimeService.createProcessInstanceQuery().processInstanceId(prcesssInsId).singleResult();  
        if(execution == null){
        	 return activtiName;  
        }
        //当前实例的执行到哪个节点  
        String activitiId = execution.getActivityId();  
        if(activitiId == null){
        	 return activtiName;  
        }
        //获得当前任务的所有节点  
        List<ActivityImpl> activitiList = def.getActivities();  
        String id = null;  
        for(ActivityImpl activityImpl:activitiList){    
            id = activityImpl.getId();   
            if(activitiId.equals(id)){  
            	activtiName= (String) activityImpl.getProperty("name");  
            }  
        }  
        return activtiName;  
	}


	@Override
	public boolean isOverByTask(String processInstanceId) {
	    List list = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).list();
	    if(list.size()>0){
	    	return false;
	    }else{
	    	return true;
	    }
	}

}
