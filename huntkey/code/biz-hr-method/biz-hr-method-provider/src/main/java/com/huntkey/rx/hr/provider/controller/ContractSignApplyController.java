package com.huntkey.rx.hr.provider.controller;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.hr.common.constants.ModelerConstants;
import com.huntkey.rx.hr.common.constants.MsgConstants;
import com.huntkey.rx.hr.common.model.ContractsignInfoDTO;
import com.huntkey.rx.hr.provider.service.ContractSignApplyService;
import com.huntkey.rx.sceo.method.register.plugin.annotation.MethodRegister;
import com.huntkey.rx.sceo.method.register.plugin.entity.MethodExeFrequency;
import com.huntkey.rx.sceo.method.register.plugin.entity.ProgramCate;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

/**
 * 合同签订单类Controller
 *
 * @author zhangyu
 * @create 2017-11-13 17:39
 **/
@RestController
@Validated
@RequestMapping("/hr/contractsignapply")
public class ContractSignApplyController {

    @Autowired
    private ContractSignApplyService contractSignApplyService;

    /**
     * 合同签订单批准通过方法
     *
     * @param orderInstanceId
     * @param handlerType
     * @return
     */
    @MethodRegister(
            edmClass = ModelerConstants.EDM_CONTRACTSIGNAPPLY,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"orderInstanceId", "handlerType"},
            methodDesc = "合同签订单类批准通过方法"
    )
    @RequestMapping(value = "/pass", method = RequestMethod.GET)
    public Result approve(@RequestParam(value = "orderInstanceId") String orderInstanceId,
                          @RequestParam(value = "handlerType") String handlerType) {
         Result result = contractSignApplyService.passContractSignOrder(orderInstanceId, handlerType);
         result.setData(null);
         return result;
    }

    /**
     * 人员合同单查询列表方法
     *
     * @return
     */
    @MethodRegister(
            edmClass = ModelerConstants.EDM_CONTRACTSIGNAPPLY,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            getReqParamsNameNoPathVariable = {"documentId", "employeeList", "type", "deptId", "legalPerson", "contractStart", "contractEnd", "contractState", "employeeType", "officParks", "entryStart", "entryEnd", "staffIdAndName", "startPage", "rows"},
            methodExeInterval = 10,
            methodDesc = "人员合同单查询列表方法"
    )
    /**
     * 人员合同单查询列表方法(带分页)
     * @param documentId    单据ID
     * @param employeeList  员工ID字符串
     * @param type          请求类型
     * @param deptId        起始部门ID
     * @param legalPerson   合同法人
     * @param contractStart 合同结束日期起
     * @param contractEnd   合同结束日期至
     * @param contractState 合同状态
     * @param employeeType  员工类型
     * @param officParks    办公园区
     * @param entryStart    入职时间起
     * @param entryEnd      入职时间至
     * @param staffIdAndName 姓名工号
     * @param startPage
     * @param rows
     * @return
     */
    @RequestMapping(value = "/queryEmpContract", method = RequestMethod.GET)
    Result query(@RequestParam(value = "deptId", required = false) String deptId,
                 @RequestParam(value = "legalPerson", required = false) String legalPerson,
                 @RequestParam(value = "contractStart", required = false) String contractStart,
                 @RequestParam(value = "contractEnd", required = false) String contractEnd,
                 @RequestParam(value = "contractState", required = false) String contractState,
                 @RequestParam(value = "employeeType", required = false) String employeeType,
                 @RequestParam(value = "officParks", required = false) String officParks,
                 @RequestParam(value = "entryStart", required = false) String entryStart,
                 @RequestParam(value = "entryEnd", required = false) String entryEnd,
                 @RequestParam(value = "staffIdAndName", required = false) String staffIdAndName,
                 @RequestParam(value = "startPage", required = false, defaultValue = "1") @Min(value = 1, message = MsgConstants.MSG_HR_PAGE_START_MIN) int startPage,
                 @RequestParam(value = "rows", required = false, defaultValue = "10") @Min(value = 1, message = MsgConstants.MSG_HR_PAGE_ROWS_MIN) int rows) throws Exception {
        return contractSignApplyService.query(deptId, legalPerson, contractStart, contractEnd, contractState, employeeType, officParks, entryStart, entryEnd, staffIdAndName, startPage, rows);
    }


    /**
     * 合同签订单加载详情方法
     *
     * @param id 合同签订单ID
     * @return
     */
    @MethodRegister(
            edmClass = ModelerConstants.EDM_CONTRACTSIGNAPPLY,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "合同签订单加载方法"
    )
    @RequestMapping(value = "/load/{id}", method = RequestMethod.POST)
    Result load(@PathVariable(value = "id") @NotBlank(message = MsgConstants.MSG_HR_NOT_BLANK_CS_ID) String id) throws Exception {

        return contractSignApplyService.load(id);
    }


    @MethodRegister(
            edmClass = ModelerConstants.EDM_CONTRACTSIGNAPPLY,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "保存合同签订单"
    )
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result save(@RequestBody ContractsignInfoDTO contractsignInfoDTO) throws Exception {
        return contractSignApplyService.save(contractsignInfoDTO);
    }


    @MethodRegister(
            edmClass = ModelerConstants.EDM_CONTRACTSIGNAPPLY,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "提交合同签订单"
    )
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public Result submit(@RequestBody ContractsignInfoDTO contractsignInfoDTO) throws Exception {
        return contractSignApplyService.submit(contractsignInfoDTO);
    }


    @MethodRegister(
            edmClass = ModelerConstants.EDM_CONTRACTSIGNAPPLY,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            getReqParamsNameNoPathVariable = {"deptName", "signType", "employeeIds", "startPage", "rows"},
            methodExeInterval = 10,
            methodDesc = "签约"
    )
    /**
     * 获取员工签约明细内容
     * @param deptName  部门名称
     * @param signType  签约类型[1 新签 2续签 3解约]
     * @param edmployeeIds  选中的员工id
     * @return
     */
    @RequestMapping(value = "/sign", method = RequestMethod.GET)
    public Result sign(@RequestParam(value = "deptName") String deptName,
                       @RequestParam(value = "signType") String signType,
                       @RequestParam(value = "employeeIds") String employeeIds,
                       @RequestParam(value = "startPage", required = false, defaultValue = "1") @Min(value = 1, message = MsgConstants.MSG_HR_PAGE_START_MIN) int startPage,
                       @RequestParam(value = "rows", required = false, defaultValue = "10") @Min(value = 1, message = MsgConstants.MSG_HR_PAGE_ROWS_MIN) int rows) throws Exception {
        return contractSignApplyService.sign(deptName, signType, employeeIds, startPage, rows);
    }

    @MethodRegister(
            edmClass = ModelerConstants.EDM_CONTRACTSIGNAPPLY,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"auditSet"},
            methodDesc = "合同签订单填写审核意见方法")
    @RequestMapping(value = "/audit", method = RequestMethod.POST)
    public Result audit(@RequestBody JSONObject auditSet) {
        return contractSignApplyService.audit(auditSet);
    }


}
