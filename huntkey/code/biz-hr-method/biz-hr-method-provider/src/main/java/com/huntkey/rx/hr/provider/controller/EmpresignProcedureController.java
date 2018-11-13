package com.huntkey.rx.hr.provider.controller;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.edm.entity.EmpresignprocedureEntity;
import com.huntkey.rx.hr.common.constants.MsgConstants;
import com.huntkey.rx.hr.common.util.JsonUtils;
import com.huntkey.rx.hr.provider.service.EmpresignProcedureService;
import com.huntkey.rx.sceo.method.register.plugin.annotation.MethodRegister;
import com.huntkey.rx.sceo.method.register.plugin.entity.MethodExeFrequency;
import com.huntkey.rx.sceo.method.register.plugin.entity.ProgramCate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

/**
 * 离职手续单Controller
 *
 * @author Created by --- on 2017/12/5.
 */
@RestController
@RequestMapping("/hr/empresignProcedure")
public class EmpresignProcedureController {
    @Autowired
    EmpresignProcedureService empresignProcedureService;

    /**
     * 离职手续单列表查询方法
     *
     * @param deptId      部门对象Id
     * @param type        离职时间类型：申请日期 cretime、预离职日期oera_app_date、批准离职日期oera_appr_date、实际离职日期oera_real_date
     * @param startTime   开始时间
     * @param endTime     结束时间
     * @param orderStatus 手续单单据状态：待审2、待批3、待核4、完成5、退回6
     * @param staffInfo   员工信息：姓名/工号
     * @param pageNum     开始页
     * @param pageSize    每页包含的记录数
     * @return
     */
    @MethodRegister(
            edmClass = "empresignprocedure",
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"deptId", "type", "startTime", "endTime", "orderStatus",
                    "staffInfo", "pageNum", "pageSize"},
            methodDesc = "离职手续单列表查询")
    @RequestMapping(value = "/empresignProcedureOrderList", method = RequestMethod.GET)
    public Result queryDeleteOrder(@RequestParam(value = "deptId", required = false) String deptId,
                                   @RequestParam(value = "type", required = false) String type,
                                   @RequestParam(value = "startTime", required = false) String startTime,
                                   @RequestParam(value = "endTime", required = false) String endTime,
                                   @RequestParam(value = "orderStatus", required = false) String orderStatus,
                                   @RequestParam(value = "staffInfo", required = false) String staffInfo,
                                   @RequestParam(required = false, defaultValue = "1") @Min(value = 1, message = MsgConstants.MSG_HR_PAGE_START_MIN) int pageNum,
                                   @RequestParam(required = false, defaultValue = "10") @Min(value = 1, message = MsgConstants.MSG_HR_PAGE_ROWS_MIN) int pageSize) {
        Result result = empresignProcedureService.queryDeleteOrderService(deptId, type, startTime, endTime, orderStatus, staffInfo, pageNum, pageSize);
        return result;
    }

    @MethodRegister(
            edmClass = "empresignprocedure",
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "根据单据ID加载离职手续单")
    @RequestMapping(value = "/loadEmpresignProcedureOrder/{orderId}", method = RequestMethod.GET)
    public Result loadEmpresignProcedureOrder(@PathVariable String orderId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(empresignProcedureService.loadEmpresignProcedureOrder(orderId));
        return result;
    }

    @MethodRegister(
            edmClass = "empresignprocedure",
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "离职手续单保存")
    @RequestMapping(value = "/saveEmpresignProcedureOrder", method = RequestMethod.POST)
    public Result saveEmpresignProcedureOrder(@RequestBody JSONObject empresign) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        Object params = JSONObject.parse(empresign.toJSONString());
        JsonUtils.camel2UnderLine(params);
		EmpresignprocedureEntity entity=JSONObject.parseObject(JSONObject.toJSONString(params), EmpresignprocedureEntity.class);
        result.setData(empresignProcedureService.saveEmpresignProcedureOrder(entity));
        return result;
    }

    @MethodRegister(
            edmClass = "empresignprocedure",
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "离职手续单提交")
    @RequestMapping(value = "/submitEmpresignProcedureOrder", method = RequestMethod.POST)
    public Result submitEmpresignProcedureOrder(@RequestBody JSONObject empresign) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        Object params = JSONObject.parse(empresign.toJSONString());
        JsonUtils.camel2UnderLine(params);
		EmpresignprocedureEntity entity=JSONObject.parseObject(JSONObject.toJSONString(params), EmpresignprocedureEntity.class);
        result.setData(empresignProcedureService.submitEmpresignProcedureOrder(entity));
        return result;
    }

    @MethodRegister(
            edmClass = "empresignprocedure",
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"orderInstanceId","handlerType"},
            methodDesc = "离职手续单通过")
    @RequestMapping(value = "/pass", method = RequestMethod.GET)
    public Result approve(@RequestParam(value = "orderInstanceId") String orderInstanceId,
                          @RequestParam(value = "handlerType") String handlerType) {
        Result result = empresignProcedureService.pass(orderInstanceId,handlerType);
        return result;
    }
    
    @MethodRegister(
            edmClass = "empresignprocedure",
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"auditSet"},
            methodDesc = "离职手续单填写审核意见方法")
    @RequestMapping(value = "/audit", method = RequestMethod.POST)
    public Result audit(@RequestBody JSONObject auditSet) {
        return empresignProcedureService.auditPostivieOrder(auditSet);
    }

}
