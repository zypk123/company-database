package com.huntkey.rx.hr.provider.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.edm.entity.JobpositionEntity;
import com.huntkey.rx.hr.common.constants.DeptPostSetOderConstants;
import com.huntkey.rx.hr.common.constants.MsgConstants;
import com.huntkey.rx.hr.provider.service.PostService;
import com.huntkey.rx.sceo.method.register.plugin.annotation.MethodRegister;
import com.huntkey.rx.sceo.method.register.plugin.entity.MethodExeFrequency;
import com.huntkey.rx.sceo.method.register.plugin.entity.ProgramCate;
import com.huntkey.rx.sceo.orm.service.OrmService;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author zhanggj
 * @createTime 2017/12/12
 * @desc
 */

@RestController
@Validated
@RequestMapping("/hr/deptPost")
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    OrmService ormService;

    /**
     * 查询该部门（或所有子部门）下，level层的岗位树，默认第一层和第二层的岗位信息
     *
     * @param deptId  部门ID
     * @param subFlag 是否含下级部门 "0"为不含下级，“1”含下级 可为空
     * @return
     */
    @MethodRegister(
            edmClass = DeptPostSetOderConstants.EDM_JOBPOSITION,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "查询部门下岗位信息（默认查两级）",
            getReqParamsNameNoPathVariable = {"deptId", "subFlag"}
    )
    @RequestMapping(value = "/queryPosts", method = RequestMethod.GET)
    Result queryPosts(@RequestParam @NotBlank(message = MsgConstants.MSG_HR_NOT_BLANK_DEPT_ID) String deptId,
                      @RequestParam(value = "subFlag", required = false) String subFlag,
                      @RequestParam(value = "level", required = false, defaultValue = "2") int level) throws Exception {
        Result result = new Result();
        JSONArray jobPositions = postService.queryPostsByDeptId(deptId, subFlag, level);
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(jobPositions);
        return result;
    }

    /**
     * 查询该部门下，当前岗位的子岗位,子岗位必须在该部门（或在该部门下的子部门）
     *
     * @param deptId    部门ID 不能为空
     * @param parPostId 汇报岗位ID 不能为空
     * @param subFlag   是否含下级部门 "0"为不含下级，“1”含下级 可为空
     * @param isSelf    是否含当前自己岗位 “true”包含，“false”不包含
     * @param level     查询层级数
     * @return
     */
    @MethodRegister(
            edmClass = DeptPostSetOderConstants.EDM_JOBPOSITION,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "查询当前部门下，当前岗位的子岗位",
            getReqParamsNameNoPathVariable = {"deptId", "parPostId", "isSelf", "subFlag", "level"}
    )
    @RequestMapping(value = "/queryPostsByParPostId", method = RequestMethod.GET)
    Result queryPostsByParPostId(@RequestParam @NotBlank(message = MsgConstants.MSG_HR_NOT_BLANK_DEPT_ID) String deptId,
                                 @RequestParam @NotBlank(message = MsgConstants.MSG_HR_NOT_BLANK_POST_ID) String parPostId,
                                 @RequestParam(value = "subFlag", required = false) String subFlag,
                                 @RequestParam @NotBlank String isSelf, @RequestParam int level) throws Exception {
        Result result = new Result();
        JSONArray jobPositions = postService.queryPostsByParPostId(deptId, parPostId, subFlag, isSelf, level);
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(jobPositions);
        return result;
    }


    /**
     * 查询当前岗位的上级岗位树
     *
     * @param postId 岗位ID
     * @param isSelf 是否含当前自己岗位 “true”包含，“false”不包含
     * @param level
     * @return
     */
    @MethodRegister(
            edmClass = DeptPostSetOderConstants.EDM_JOBPOSITION,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "查询当前岗位的上级岗位树",
            getReqParamsNameNoPathVariable = {"postId", "isSelf", "level"}
    )
    @RequestMapping(value = "/queryParPostsTreeById", method = RequestMethod.GET)
    Result queryParPostsTreeById(@RequestParam @NotBlank(message = MsgConstants.MSG_HR_NOT_BLANK_POST_ID) String postId,
                                 @RequestParam @NotBlank String isSelf, @RequestParam int level) throws Exception {
        Result result = new Result();
        JSONArray jobPositions = postService.queryParPostsTreeById(postId, isSelf, level);
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(jobPositions);
        return result;
    }


    /**
     * 查询指定部门岗位清单
     *
     * @param deptId
     * @param subFlag 0:不含下级部门（默认） 1：含下级部门
     * @return
     */
    @MethodRegister(
            edmClass = DeptPostSetOderConstants.EDM_JOBPOSITION,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "查询指定部门岗位清单",
            getReqParamsNameNoPathVariable = {"deptId", "subFlag"}
    )
    @RequestMapping(value = "/getPostsByDeptId", method = RequestMethod.GET)
    Result getPostsByDeptId(@RequestParam @NotBlank(message = MsgConstants.MSG_HR_NOT_BLANK_DEPT_ID) String deptId,
                            @RequestParam(value = "subFlag", required = false) String subFlag) throws Exception {
        Result result = new Result();
        JSONArray jobPositions = postService.getPostsByDeptId(deptId, subFlag);
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(jobPositions);
        return result;
    }


    /**
     * 统计指定部门岗位数
     *
     * @param deptId
     * @param subFlag 0:不含下级部门（默认） 1：含下级部门
     * @return
     */
    @MethodRegister(
            edmClass = DeptPostSetOderConstants.EDM_JOBPOSITION,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "统计指定部门岗位数",
            getReqParamsNameNoPathVariable = {"deptId", "subFlag"}
    )
    @RequestMapping(value = "/countPostsByDept", method = RequestMethod.GET)
    Result countPostsByDept(@RequestParam @NotBlank(message = MsgConstants.MSG_HR_NOT_BLANK_DEPT_ID) String deptId,
                            @RequestParam(value = "subFlag", required = false) String subFlag) throws Exception {
        Result result = new Result();
        JSONArray jobPositions = postService.getPostsByDeptId(deptId, subFlag);
        result.setRetCode(Result.RECODE_SUCCESS);
        if (jobPositions != null && jobPositions.size() > 0) {
            int countNum = jobPositions.size();
            result.setData(countNum);
            return result;
        }
        result.setData("0");
        return result;
    }


    /**
     * 查询指定部门在编岗位列表
     *
     * @param deptId
     * @param subFlag 0:不含下级部门（默认） 1：含下级部门
     * @return
     */
    @MethodRegister(
            edmClass = DeptPostSetOderConstants.EDM_JOBPOSITION,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "查询指定部门在编岗位列表",
            getReqParamsNameNoPathVariable = {"deptId", "subFlag"}
    )
    @RequestMapping(value = "/queryPostsEmpByDept", method = RequestMethod.GET)
    Result queryPostsEmpByDept(@RequestParam @NotBlank(message = MsgConstants.MSG_HR_NOT_BLANK_DEPT_ID) String deptId,
                               @RequestParam(value = "subFlag", required = false) String subFlag) throws Exception {
        Result result = new Result();
        JSONArray jobPositions = postService.getPostsEmpByDeptId(deptId, subFlag);
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(jobPositions);
        return result;
    }


    /**
     * 统计指定部门岗位在编人数
     *
     * @param deptId
     * @param subFlag 0:不含下级部门（默认） 1：含下级部门
     * @return
     */
    @MethodRegister(
            edmClass = DeptPostSetOderConstants.EDM_JOBPOSITION,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "统计指定部门岗位在编人数",
            getReqParamsNameNoPathVariable = {"deptId", "subFlag"}
    )
    @RequestMapping(value = "/countPostsEmpByDept", method = RequestMethod.GET)
    Result countPostsEmpByDept(@RequestParam @NotBlank(message = MsgConstants.MSG_HR_NOT_BLANK_DEPT_ID) String deptId,
                               @RequestParam(value = "subFlag", required = false) String subFlag) throws Exception {
        Result result = new Result();
        JSONArray jobPositions = postService.getPostsEmpByDeptId(deptId, subFlag);
        result.setRetCode(Result.RECODE_SUCCESS);
        if (jobPositions != null && jobPositions.size() > 0) {
            int countNum = jobPositions.size();
            result.setData(countNum);
            return result;
        }
        result.setData("0");
        return result;
    }

    /**
     * 查询岗位树
     *
     * @param parPostId
     * @param isSelf    是否包含父节点，默认包含
     * @param level     查询层级数
     * @return
     */
    @MethodRegister(
            edmClass = DeptPostSetOderConstants.EDM_JOBPOSITION,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "岗位树查询方法",
            getReqParamsNameNoPathVariable = {"parPostId", "isSelf", "level"}
    )
    @RequestMapping(value = "/getPostsTree", method = RequestMethod.GET)
    Result getPostsTree(@RequestParam(value = "parPostId", required = false, defaultValue = "") String parPostId,
                        @RequestParam(value = "isSelf", required = false, defaultValue = "true") String isSelf,
                        @RequestParam(value = "level", required = false, defaultValue = "2") int level) throws Exception {
        Result result = new Result();
        JSONArray jobPositions = postService.queryPostsTreeByParPostId(parPostId, isSelf, level);
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(jobPositions);
        return result;
    }


    /**
     * 部门岗位新增单据加载方法
     *
     * @param orderId
     * @return
     */
    @MethodRegister(
            edmClass = DeptPostSetOderConstants.EDM_DEPTPOSTSETORDER,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "部门岗位新增单据加载方法",
            getReqParamsNameNoPathVariable = {"orderId"}
    )
    @RequestMapping(value = "/loadPostAddOrder", method = RequestMethod.GET)
    Result loadPostAddOrder(@RequestParam @NotBlank(message = MsgConstants.MSG_HR_NOT_BLANK_POST_ORDER_ID) String orderId) throws Exception {
        Result result = new Result();
        JSONObject orderJson = null;
        orderJson = postService.queryPostSetOderById(orderId);
        if (orderJson == null) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg("未查到单据信息");
            return result;
        }
        JSONArray jobPositions = postService.queryOrderPostsSetByOrderId(orderId);
        orderJson.put("odps_dpost_set", jobPositions);
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(orderJson);
        return result;
    }


    /**
     * 保存岗位类（保存单个对象）
     *
     * @param data 岗位类对象JSON数据
     * @return
     */
    @MethodRegister(
            edmClass = DeptPostSetOderConstants.EDM_JOBPOSITION,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "保存岗位类（保存单个对象）"
    )
    @RequestMapping(value = "/savePost", method = RequestMethod.POST)
    public Result savePost(@RequestBody JobpositionEntity data) throws Exception {
        Result result = new Result();
        if (data == null) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg("岗位信息参数不能为空");
        }
        String id = postService.savePost(data);
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(id);
        return result;
    }


    //根据岗位ID注销岗位
    @MethodRegister(
            edmClass = DeptPostSetOderConstants.EDM_JOBPOSITION,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "根据岗位ID删除岗位对象",
            getReqParamsNameNoPathVariable = {"postId"}
    )
    @RequestMapping(value = "/deletePost", method = RequestMethod.GET)
    Result deletePost(@RequestParam @NotBlank(message = MsgConstants.MSG_HR_NOT_BLANK_POST_ID) String postId) throws Exception {
        Result result = new Result();
        String data = postService.deletePost(postId);
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(data);
        return result;
    }


    /**
     * 部门岗位新增单据保存方法（含校验）
     *
     * @param data
     * @return
     */
    @MethodRegister(
            edmClass = DeptPostSetOderConstants.EDM_DEPTPOSTSETORDER,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "部门岗位新增单据保存方法"
    )
    @RequestMapping(value = "/savePostAddOrder", method = RequestMethod.POST)
    public Result savePostAddOrder(@RequestBody String data) throws Exception {
        Result result = new Result();
        //返回单据信息
        JSONObject orderJson = postService.saveAddOrder(data);
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(orderJson);
        return result;
    }


    //若当前岗位存在单据类型=‘新增/修改/注销’的待审单据时，再点击三个操作按钮时提示用户，岗位存在待审单据；
    //若被选择的岗位的上级岗位或者上上级岗位存在单据类型=‘注销’且‘含下级’的待审单据时，点击三个操作按钮时提示用户，岗位存在待审单据
    @MethodRegister(
            edmClass = DeptPostSetOderConstants.EDM_DEPTPOSTSETORDER,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "部门岗位单据是否待审校验方法"
    )
    @RequestMapping(value = "/isExistCheckPostOrder", method = RequestMethod.POST)
    public Result isExistCheckPostOrder(@RequestBody @NotEmpty(message = "岗位ID数组不能为空") String data) throws Exception {
        Result result = new Result();
        List<String> jsonArray = postService.isExistCheckPostOrder(data);
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(jsonArray);
        return result;
    }

    //根据岗位ID查询岗位信息
    @MethodRegister(
            edmClass = DeptPostSetOderConstants.EDM_JOBPOSITION,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "根据岗位ID查询岗位信息"
    )
    @RequestMapping(value = "/queryPostsByIds", method = RequestMethod.POST)
    public Result queryPostsByIds(@RequestBody @NotEmpty(message = "岗位ID数组不能为空") String[] postIds) throws Exception {
        Result result = new Result();
        JSONArray jobPositions = postService.queryPostsByIds(postIds);
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(jobPositions);
        return result;
    }


    //根据岗位ID,取岗位的汇报岗位的任职人的姓名和工号
    @MethodRegister(
            edmClass = DeptPostSetOderConstants.EDM_JOBPOSITION,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "根据岗位ID,取岗位的汇报岗位的任职人的姓名和工号"
    )
    @RequestMapping(value = "/queryParPostsEmpByIds", method = RequestMethod.POST)
    public Result queryParPostsEmpByIds(@RequestBody @NotEmpty(message = "岗位ID数组不能为空") String[] postIds) throws Exception {
        Result result = new Result();
        JSONArray jobPositions = postService.queryParPostsEmpByIds(postIds);
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(jobPositions);
        return result;
    }


    //部门岗位新增单据加载方法
    @MethodRegister(
            edmClass = DeptPostSetOderConstants.EDM_DEPTPOSTSETORDER,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "部门岗位新增单据加载方法",
            getReqParamsNameNoPathVariable = {"orderId"}
    )
    @RequestMapping(value = "/loadPostEditOrder", method = RequestMethod.GET)
    public Result loadPostEditOrder(@RequestParam @NotBlank(message = MsgConstants.MSG_HR_NOT_BLANK_POST_ORDER_ID) String orderId) throws Exception {
        Result result = new Result();
        JSONObject orderJson = postService.queryPostSetOderById(orderId);
        if (orderJson == null) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg("未查到单据信息");
            return result;
        }
        JSONArray jobPositions = postService.loadAddOrder(orderId);
        orderJson.put("odps_dpost_set", jobPositions);
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(orderJson);
        return result;
    }


    //部门岗位修改单据保存方法
    @MethodRegister(
            edmClass = DeptPostSetOderConstants.EDM_DEPTPOSTSETORDER,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "部门岗位修改单据保存方法"
    )
    @RequestMapping(value = "/savePostEditOrder", method = RequestMethod.POST)
    public Result savePostEditOrder(@RequestBody @NotEmpty(message = "岗位数据不能为空") String data) throws Exception {
        Result result = new Result();
        JSONObject orderJson = postService.saveEditOrder(data);
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(orderJson);
        return result;
    }


    //部门岗位新增单据提交方法
    @MethodRegister(
            edmClass = DeptPostSetOderConstants.EDM_DEPTPOSTSETORDER,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "部门岗位新增单据提交方法"
    )
    @RequestMapping(value = "/submitPostAddOrder", method = RequestMethod.POST)
    public Result submitPostAddOrder(@RequestBody String data) throws Exception {
        return postService.submitPostAddOrder(data);
    }

    //部门岗位修改单据提交方法
    @MethodRegister(
            edmClass = DeptPostSetOderConstants.EDM_DEPTPOSTSETORDER,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "部门岗位修改单据提交方法"
    )
    @RequestMapping(value = "/submitPostEditOrder", method = RequestMethod.POST)
    public Result submitPostEditOrder(@RequestBody String data) throws Exception {
        return postService.submitPostEditOrder(data);
    }

    //部门岗位新增单据批准通过方法
    @MethodRegister(
            edmClass = DeptPostSetOderConstants.EDM_DEPTPOSTSETORDER,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "部门岗位新增单据批准通过方法",
            getReqParamsNameNoPathVariable = {"orderId"}
    )
    @RequestMapping(value = "/passPostAddOrder", method = RequestMethod.GET)
    public Result passPostAddOrder(@RequestParam @NotBlank(message = MsgConstants.MSG_HR_NOT_BLANK_POST_ORDER_ID) String orderId) throws Exception {
        Result result = new Result();
        List<String> resultdatas = postService.passAddOrder(orderId);
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(resultdatas);
        return result;
    }

    //部门岗位修改单据批准通过方法
    @MethodRegister(
            edmClass = DeptPostSetOderConstants.EDM_DEPTPOSTSETORDER,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "部门岗位修改单据批准通过方法",
            getReqParamsNameNoPathVariable = {"orderId"}
    )
    @RequestMapping(value = "/passPostEditOrder", method = RequestMethod.GET)
    public Result passPostEditOrder(@RequestParam @NotBlank(message = MsgConstants.MSG_HR_NOT_BLANK_POST_ORDER_ID) String orderId) throws Exception {
        Result result = new Result();
        List<String> resultdatas = postService.passEditOrder(orderId);
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(resultdatas);
        return result;
    }


    /**
     * 部门岗位设置单通过方法
     *
     * @return
     */
    @MethodRegister(
            edmClass = DeptPostSetOderConstants.EDM_DEPTPOSTSETORDER,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"orderInstanceId", "handlerType"},
            methodDesc = "部门岗位设置单通过方法")
    @RequestMapping(value = "/pass", method = RequestMethod.GET)
    public Result approve(@RequestParam(value = "orderInstanceId") String orderInstanceId,
                          @RequestParam(value = "handlerType") String handlerType) throws Exception {
        Result result = postService.pass(orderInstanceId, handlerType);
        result.setData(null);
        return result;
    }


    @MethodRegister(
            edmClass = DeptPostSetOderConstants.EDM_DEPTPOSTSETORDER,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"auditSet"},
            methodDesc = "部门岗位设置单填写审核意见方法")
    @RequestMapping(value = "/audit", method = RequestMethod.POST)
    public Result audit(@RequestBody JSONObject auditSet) throws Exception {
        return postService.audit(auditSet);
    }

    @RequestMapping(value = "/queryDelOrderByPostId", method = RequestMethod.GET)
    Result queryDelOrderByPostId(@RequestParam(value = "postId") String postId, @RequestParam(value = "ifParent") String ifParent) throws Exception {
        Result result = new Result();
        List<Map<String, Object>> order = postService.isExistDelCheckOrderByPostId(postId, ifParent);
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(order);
        return result;
    }


}
