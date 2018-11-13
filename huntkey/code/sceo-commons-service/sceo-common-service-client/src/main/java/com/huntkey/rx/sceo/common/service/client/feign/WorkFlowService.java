package com.huntkey.rx.sceo.common.service.client.feign;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.workflow.engine.common.model.OrderMsgVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created by zhanglb on 2017/4/14.
 */
@FeignClient(value = "workFlow-provider", fallback = WorkFlowService.class)
public interface WorkFlowService {

    @RequestMapping(value = "/workFlow/getFormId", method = RequestMethod.GET)
    Result getFormId(@RequestParam(value = "processDefinitionId") String processDefinitionId);

    @RequestMapping(value = "/workFlow/startProcessInstanceByKey", method = RequestMethod.POST)
    Result startProcessInstanceByKey(@RequestParam(value = "processDefinitionId") String processDefinitionId, @RequestParam(value = "businessKey", required = false) String businessKey,
                                     @RequestParam(value = "map", required = false) Map<String, Object> map);

    /**
     * 通过assignee或者candiate查询用户待办任务列表
     *
     * @param username 待办用户
     * @return result
     */
    @RequestMapping(value = "/workFlow/getTodoTaskList", method = RequestMethod.GET)
    Result getTodoTaskList(@RequestParam(value = "username") String username, @RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize);

    /**
     * 通过指定的candiateGroup查询用户待办任务列表
     *
     * @param candiateGroup 用户组
     * @return result
     */
    @RequestMapping(value = "/workFlow/getTodoTaskListByCandiateGroup", method = RequestMethod.GET)
    Result getTodoTaskListByCandiateGroup(@RequestParam(value = "candiateGroup") String candiateGroup, @RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize);

    /**
     * 审核流程通过方法
     *
     * @param taskId   任务ID
     * @param username 用户名
     * @param map      map
     * @return result
     */
    @RequestMapping(value = "/workFlow/auditProcess", method = RequestMethod.POST)
    Result auditProcess(@RequestParam(value = "taskId") String taskId, @RequestParam(value = "username") String username, Map<String, Object> map);

    /**
     * 转办流程
     *
     * @param taskId 当前任务节点ID
     * @param userId 被转办人Code
     * @return result
     */
    @RequestMapping(value = "/workFlow/transferAssignee", method = RequestMethod.POST)
    Result transferAssignee(@RequestParam(value = "taskId") String taskId, @RequestParam(value = "userId") String userId);

    /**
     * 会签操作
     *
     * @param taskId    当前任务ID
     * @param userCodes 会签人账号集合
     * @return result
     */
    @RequestMapping(value = "/workFlow/jointProcess", method = RequestMethod.POST)
    Result jointProcess(@RequestParam(value = "taskId") String taskId, @RequestParam(value = "userCodes") String[] userCodes);

    /**
     * 流程挂起
     *
     * @param processInstanceId 当前流程实例ID
     * @return result
     */
    @RequestMapping(value = "/workFlow/suspendProcess", method = RequestMethod.DELETE)
    Result suspendProcess(@RequestParam(value = "processInstanceId") String processInstanceId);

    /**
     * 流程激活
     *
     * @param processInstanceId 当前流程实例ID
     * @return result
     */
    @RequestMapping(value = "/workFlow/activateProcess", method = RequestMethod.POST)
    Result activateProcess(@RequestParam(value = "processInstanceId") String processInstanceId);

    /**
     * 增加用户组
     *
     * @param id   用户组id
     * @param name 用户组名称
     * @param type 用户组类型
     * @return result
     */
    @RequestMapping(value = "/workFlow/addGroup", method = RequestMethod.PUT)
    Result addGroup(@RequestParam(value = "id") String id, @RequestParam(value = "name") String name, @RequestParam(value = "type") String type);


    /**
     * 查询用户组
     *
     * @param id 用户组id
     * @return result
     */
    @RequestMapping(value = "/workFlow/getGroupById", method = RequestMethod.GET)
    Result getGroupById(@RequestParam(value = "id") String id);


    /**
     * 查询所有可用的流程列表
     *
     * @param deploymentId   部署Id
     * @param processDefId   流程定义Id
     * @param processDefKey  流程定义key
     * @param processDefName 流程名称
     * @param pageNum        页数
     * @param pageSize       分页的大小
     * @return result
     */
    @RequestMapping(value = "/workFlow/getProcessDefinitions", method = RequestMethod.GET)
    Result getProcessDefinitions(@RequestParam(value = "deploymentId", required = false) String deploymentId, @RequestParam(value = "processDefId", required = false) String processDefId, @RequestParam(value = "processDefKey", required = false) String processDefKey, @RequestParam(value = "processDefName", required = false) String processDefName, @RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize);


    /**
     * 根据任务Id查询流程申请信息
     *
     * @param taskId 任务Id
     * @return result
     */
    @RequestMapping(value = "/workFlow/getUserApplyVO", method = RequestMethod.GET)
    Result getUserApplyVO(@RequestParam(value = "taskId") String taskId);

    /**
     * 审核用户申请流程
     *
     * @param orderMsgVO 用户信息入参
     * @param taskId     任务Id
     * @param userCode   审核人
     * @return result
     */
    @RequestMapping(value = "/workFlow/auditUserApply", method = RequestMethod.POST)
    Result auditUserApply(@RequestBody OrderMsgVO orderMsgVO, @RequestParam(value = "taskId") String taskId, @RequestParam(value = "userCode") String userCode);

    /**
     * 查询流程审核信息
     *
     * @param orderMsgVO 用户信息入参
     * @return result
     */
    @RequestMapping(value = "/workFlow/getOrderMsg", method = RequestMethod.POST)
    Result getOrderMsg(@RequestBody OrderMsgVO orderMsgVO);

    /**
     * 查询已办流程
     *
     * @param username 处理人
     * @param pageNum  页数
     * @param pageSize 分页的大小
     * @return result
     */
    @RequestMapping(value = "/workFlow/getDoneList", method = RequestMethod.GET)
    Result getDoneList(@RequestParam(value = "username") String username, @RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize);

    /**
     * 查询流程定义列表
     *
     * @param pageNum  页数
     * @param pageSize 分页的大小
     * @return result
     */
    @RequestMapping(value = "/workFlow/getActivitiModels", method = RequestMethod.GET)
    Result getActivitiModels(@RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize);

    @RequestMapping(value = "/workFlow/backToTask", method = RequestMethod.POST)
    Result backToTask(@RequestParam(value = "taskId") String taskId, @RequestParam(value = "backTaskId") String backTaskId);
}
