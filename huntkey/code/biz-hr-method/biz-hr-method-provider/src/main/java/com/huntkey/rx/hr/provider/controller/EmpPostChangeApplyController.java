package com.huntkey.rx.hr.provider.controller;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.hr.common.model.EdmApplyPositiveConstants;
import com.huntkey.rx.hr.common.model.EdmPostchangeapplyConstants;
import com.huntkey.rx.hr.provider.service.EmpPostChangeApplyService;
import com.huntkey.rx.sceo.method.register.plugin.annotation.MethodRegister;
import com.huntkey.rx.sceo.method.register.plugin.entity.MethodExeFrequency;
import com.huntkey.rx.sceo.method.register.plugin.entity.ProgramCate;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 员工岗位调整单控制类
 *
 * @author yaoss
 * @date 2017/12/01
 */
@RestController
@Validated
@RequestMapping("/hr/emppostchangeapply")
public class EmpPostChangeApplyController {


    @Autowired
    private EmpPostChangeApplyService empPostChangeApplyService;

    /**
     * 人员排岗单加载详情方法
     *
     * @param id
     * @return
     */

    @MethodRegister(
            edmClass = EdmPostchangeapplyConstants.EDM_EMPPOSTCHANGEAPPLY,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"id"},
            methodDesc = "人员排岗单加载详情方法")
    @RequestMapping(value = "/loadAddOrder", method = RequestMethod.GET)
    public Result loadAddOrder(@RequestParam(value = "id") String id) {
        Result result = empPostChangeApplyService.loadAddOrder(id);
        return result;
    }

    /**
     * 人员排岗单保存方法（含校验）
     *
     * @param empPostChangeApplyDTO
     * @return
     */
    @MethodRegister(
            edmClass = EdmPostchangeapplyConstants.EDM_EMPPOSTCHANGEAPPLY,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"empPostChangeApplyDTO"},
            methodDesc = "人员排岗单保存方法（含校验）")
    @RequestMapping(value = "/saveAddOrder", method = RequestMethod.POST)
    public Result saveAddOrder(@RequestBody JSONObject empPostChangeApplyDTO) throws Exception{
         return empPostChangeApplyService.saveAddOrder(empPostChangeApplyDTO);
    }

    /**
     * 人员排岗单提交方法（含校验）
     *
     * @param empPostChangeApplyDTO
     * @return
     */
    @MethodRegister(
            edmClass = EdmPostchangeapplyConstants.EDM_EMPPOSTCHANGEAPPLY,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"empPostChangeApplyDTO"},
            methodDesc = "人员排岗单提交方法（含校验）")
    @RequestMapping(value = "/submitAddOrder", method = RequestMethod.POST)
    public Result submitAddOrder(@RequestBody JSONObject empPostChangeApplyDTO) throws Exception {
        return empPostChangeApplyService.submitAddOrder(empPostChangeApplyDTO);
    }

    /**
     * 人员调岗单加载详情方法
     *
     * @param id
     * @return
     */
    @MethodRegister(
            edmClass = EdmPostchangeapplyConstants.EDM_EMPPOSTCHANGEAPPLY,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"id"},
            methodDesc = "人员调岗单加载详情方法")
    @RequestMapping(value = "/loadEditOrder", method = RequestMethod.GET)
    public Result loadEditOrder(@RequestParam(value = "id") String id) {
        Result result = empPostChangeApplyService.loadEditOrder(id);
        return result;
    }

    /**
     * 人员调岗单保存方法（含校验）
     *
     * @param empPostEditApplyDTO
     * @return
     */
    @MethodRegister(
            edmClass = EdmPostchangeapplyConstants.EDM_EMPPOSTCHANGEAPPLY,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"empPostEditApplyDTO"},
            methodDesc = "人员调岗单保存方法（含校验）")
    @RequestMapping(value = "/saveEditOrder", method = RequestMethod.POST)
    public Result saveEditOrder(@RequestBody JSONObject empPostEditApplyDTO) throws Exception{
       return empPostChangeApplyService.saveEditOrder(empPostEditApplyDTO);
    }

    /**
     * 人员调岗单提交方法（含校验）
     *
     * @param empPostEditApplyDTO
     * @return
     */
    @MethodRegister(
            edmClass = EdmPostchangeapplyConstants.EDM_EMPPOSTCHANGEAPPLY,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"empPostEditApplyDTO"},
            methodDesc = "人员调岗单提交方法（含校验）")
    @RequestMapping(value = "/submitEditOrder", method = RequestMethod.POST)
    public Result submitEditOrder(@RequestBody JSONObject empPostEditApplyDTO) throws Exception {
        return empPostChangeApplyService.submitEditOrder(empPostEditApplyDTO);
    }

    /**
     * 员工岗位调整单综合单据批准通过
     *
     * @param orderInstanceId
     * @param handlerType
     * @return
     * @author yaoss
     */
    @MethodRegister(
            edmClass = EdmPostchangeapplyConstants.EDM_EMPPOSTCHANGEAPPLY,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"orderInstanceId", "handlerType"},
            methodDesc = "员工岗位调整单综合单据批准通过")
    @RequestMapping(value = "/passOrder", method = RequestMethod.GET)
    public Result approve(@RequestParam(value = "orderInstanceId") String orderInstanceId,
                          @RequestParam(value = "handlerType") String handlerType) throws Exception {
      return empPostChangeApplyService.passOrderById(orderInstanceId, handlerType);
    }

    @MethodRegister(
            edmClass = EdmPostchangeapplyConstants.EDM_EMPPOSTCHANGEAPPLY,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "员工岗位调整单综合单据填写审核意见方法")
    @RequestMapping(value = "/audit", method = RequestMethod.POST)
    public Result audit(@RequestBody JSONObject auditSet) throws Exception{
        return empPostChangeApplyService.auditOrder(auditSet);
    }


}
