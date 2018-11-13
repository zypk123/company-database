package com.huntkey.rx.hr.provider.controller;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.hr.common.constants.DeptChargeMsgConstants;
import com.huntkey.rx.hr.common.constants.DeptChargerApplyOrderConstants;
import com.huntkey.rx.hr.common.model.DeptChargerApplyOrdeDTO;
import com.huntkey.rx.hr.provider.service.BizFormService;
import com.huntkey.rx.hr.provider.service.DeptChargeService;
import com.huntkey.rx.sceo.method.register.plugin.annotation.MethodRegister;
import com.huntkey.rx.sceo.method.register.plugin.entity.MethodExeFrequency;
import com.huntkey.rx.sceo.method.register.plugin.entity.ProgramCate;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by weijian on 2017/11/8.
 */
@RestController
@Validated
@RequestMapping("/hr/deptcharge")
public class DeptChargeController {
    @Autowired
    private DeptChargeService deptChargeService;
    @Autowired
    private BizFormService bizFormService;
    /**
     * 根据姓名或工号检索人员信息
     *
     * @param searchContent
     * @return
     */
    @MethodRegister(
            edmClass	= DeptChargerApplyOrderConstants.EDM_DEPT_CHARGER_APPLY_ORDER,
            methodCate	= "人资系统方法",
            programCate	=	ProgramCate.Java,
            methodExeFrequency	=	MethodExeFrequency.Loop,
            methodExeInterval	= 10,
            methodDesc = "根据姓名或工号检索人员信息",
            getReqParamsNameNoPathVariable = {"searchContent","deptId"}
    )
    @RequestMapping(value = "/loadEmployeeInfo", method = RequestMethod.GET)
    Result deptChaAppOrdLoadEmployeeInfo(@RequestParam @NotBlank(message = DeptChargeMsgConstants.MSG_HR_NOT_BLANK_MDEP_SEARCH_CONTENT) String searchContent,
                                         @RequestParam(name = "deptId") String deptId) {
        return deptChargeService.loadEmployeeInfo(searchContent,deptId);
    }

    /**
     * 查询人员任职岗位
     *
     * @param employeeId
     * @return
     */
    @MethodRegister(
            edmClass	= DeptChargerApplyOrderConstants.EDM_DEPT_CHARGER_APPLY_ORDER,
            methodCate	= "人资系统方法",
            programCate	=	ProgramCate.Java,
            methodExeFrequency	=	MethodExeFrequency.Loop,
            methodExeInterval	= 10,
            methodDesc = "查询人员任职岗位",
            getReqParamsNameNoPathVariable = {"employeeId","deptId"}
    )
    @RequestMapping(value = "/queryJobPosition", method = RequestMethod.GET)
    Result deptChaAppOrdQueryJobPosition(@RequestParam @NotBlank(message = "员工ID不可为空") String employeeId,
                                         @RequestParam @NotBlank(message = "部门ID不可为空") String deptId) {
        return deptChargeService.queryJobPosition(employeeId,deptId);
    }

    /**
     * 保存部门责任人任免单
     * @params deptId
     * @return
     */
    @MethodRegister(
            edmClass	= DeptChargerApplyOrderConstants.EDM_DEPT_CHARGER_APPLY_ORDER,
            methodCate	= "人资系统方法",
            programCate	=	ProgramCate.Java,
            methodExeFrequency	=	MethodExeFrequency.Loop,
            methodExeInterval	= 10,
            methodDesc = "保存部门责任人任免单"
    )
    @RequestMapping(value = "/saveDeptCharge", method = RequestMethod.POST)
    Result deptChaAppOrdSaveDeptCharge(@RequestBody DeptChargerApplyOrdeDTO deptChargerApplyOrdeDTO) throws Exception {
        return deptChargeService.saveDeptCharge(deptChargerApplyOrdeDTO);
    }


    /**
     * 单据提交方法
     * @params data
     * @return
     */
    @MethodRegister(
            edmClass	= DeptChargerApplyOrderConstants.EDM_DEPT_CHARGER_APPLY_ORDER,
            methodCate	= "人资系统方法",
            programCate	=	ProgramCate.Java,
            methodExeFrequency	=	MethodExeFrequency.Loop,
            methodExeInterval	= 10,
            methodDesc = "责任人任免单提交方法"
    )
    @RequestMapping(value = "/submit", method = RequestMethod.PUT)
    Result deptChaAppOrdSubmit(@RequestBody DeptChargerApplyOrdeDTO deptChargerApplyOrdeDTO) throws Exception {
        Result result = deptChargeService.submit(deptChargerApplyOrdeDTO);
        return result;
    }

    /**
     * 单据通过方法
     * @params data
     * @return
     */
    @MethodRegister(
            edmClass	= DeptChargerApplyOrderConstants.EDM_DEPT_CHARGER_APPLY_ORDER,
            methodCate	= "人资系统方法",
            programCate	=	ProgramCate.Java,
            methodExeFrequency	=	MethodExeFrequency.Loop,
            methodExeInterval	= 10,
            methodDesc = "责任人任免单通过方法",
            getReqParamsNameNoPathVariable = {"orderInstanceId","handlerType"}
    )
    @RequestMapping(value ="/pass",method = RequestMethod.GET)
    Result approve(@RequestParam(value = "orderInstanceId") String orderInstanceId,
                             @RequestParam(value = "handlerType") String handlerType){
        return deptChargeService.pass(orderInstanceId,handlerType);
    }

    /**
     * 加载责任人任免单
     * @params id
     * @return
     */
    @MethodRegister(
            edmClass	= DeptChargerApplyOrderConstants.EDM_DEPT_CHARGER_APPLY_ORDER,
            methodCate	= "人资系统方法",
            programCate	=	ProgramCate.Java,
            methodExeFrequency	=	MethodExeFrequency.Loop,
            methodExeInterval	= 10,
            methodDesc = "加载责任人任免单",
            getReqParamsNameNoPathVariable = {"id","bmid"}
    )
    @RequestMapping(value = "/loadDeptChargerApplyOrder",method = RequestMethod.GET)
    Result loadDeptChargerApplyOrder(@RequestParam @NotBlank(message = "责任人任免单ID不可为空") String id,@RequestParam(value = "bmid",required = false) String bmid){
        return deptChargeService.loadDeptChargerApplyOrder(id,bmid);
    }

    /**
     * 加载责任人任免单
     * @params id
     * @return
     */
    @MethodRegister(
            edmClass	= DeptChargerApplyOrderConstants.EDM_DEPT_CHARGER_APPLY_ORDER,
            methodCate	= "人资系统方法",
            programCate	=	ProgramCate.Java,
            methodExeFrequency	=	MethodExeFrequency.Loop,
            methodExeInterval	= 10,
            methodDesc = "加载责任人任免单(单据结构)",
            getReqParamsNameNoPathVariable = {"id"}
    )
    @RequestMapping(value = "/loadFromOrder",method = RequestMethod.GET)
    Result loadFromOrder(@RequestParam @NotBlank(message = "责任人任免单ID不可为空") String id){
        return deptChargeService.loadFromOrder(id);
    }


    @MethodRegister(
            edmClass = DeptChargerApplyOrderConstants.EDM_DEPT_CHARGER_APPLY_ORDER,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"auditSet"},
            methodDesc = "部门责任人任免单填写审核意见方法")
    @RequestMapping(value = "/audit", method = RequestMethod.POST)
    public Result audit(@RequestBody JSONObject auditSet) {
        return deptChargeService.audit(auditSet);
    }
}
