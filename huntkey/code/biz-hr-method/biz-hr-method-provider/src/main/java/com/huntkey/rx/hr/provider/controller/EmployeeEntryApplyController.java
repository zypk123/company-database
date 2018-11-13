package com.huntkey.rx.hr.provider.controller;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.hr.common.constants.EmployeeEntryApplyConstant;
import com.huntkey.rx.hr.common.model.EmployeeEntryApplyDTO;
import com.huntkey.rx.hr.provider.service.BizFormService;
import com.huntkey.rx.hr.provider.service.EmployeeEntryApplyService;
import com.huntkey.rx.sceo.method.register.plugin.annotation.MethodRegister;
import com.huntkey.rx.sceo.method.register.plugin.entity.MethodExeFrequency;
import com.huntkey.rx.sceo.method.register.plugin.entity.ProgramCate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * 职员入职单类
 */
@RestController
@Validated
@RequestMapping("/hr/employeeentryapply")
public class EmployeeEntryApplyController {
    @Value("${nginxIp}")
    String nginxIp;

    @Autowired
    private EmployeeEntryApplyService employeeEntryApplyService;
    @Autowired
    private BizFormService bizFormService;

    /**
     * 加载职员入职单
     *
     * @param id
     * @return
     */
    @MethodRegister(
            edmClass = EmployeeEntryApplyConstant.EDM_NAME,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "加载职员入职单",
            getReqParamsNameNoPathVariable = {"id"}
    )
    @RequestMapping(value = "/loadAddOrder", method = RequestMethod.GET)
    public Result loadAddOrder(@RequestParam(name = "id") String id) {
        Result result = employeeEntryApplyService.load(id);
        return result;
    }

    /**
     * 职员入职单保存方法
     *
     * @param employeeEntryApplyDTO
     * @return
     */
    @MethodRegister(
            edmClass = EmployeeEntryApplyConstant.EDM_NAME,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "职员入职单保存方法"
    )
    @RequestMapping(value = "/saveAddOrder", method = RequestMethod.POST)
    public Result saveAddOrder(@RequestBody EmployeeEntryApplyDTO employeeEntryApplyDTO) throws Exception {
        return employeeEntryApplyService.saveAddOrder(employeeEntryApplyDTO);
    }

    /**
     * 职员入职单提交方法
     *
     * @param employeeEntryApplyDTO
     * @return
     */
    @MethodRegister(
            edmClass = EmployeeEntryApplyConstant.EDM_NAME,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "职员入职单提交方法"
    )
    @RequestMapping(value = "/submitAddOrder", method = RequestMethod.PUT)
    public Result submitAddOrder(@RequestBody EmployeeEntryApplyDTO employeeEntryApplyDTO) throws Exception {
        Result result = employeeEntryApplyService.submitAddOrder(employeeEntryApplyDTO);
        return result;
    }


    /**
     * 职员入职单通过方法
     *
     * @param orderInstanceId
     * @param handlerType
     * @return
     */
    @MethodRegister(
            edmClass = EmployeeEntryApplyConstant.EDM_NAME,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "职员入职单/档案变更单通过方法",
            getReqParamsNameNoPathVariable = {"orderInstanceId", "handlerType"}
    )
    @RequestMapping(value = "/passAddOrder", method = RequestMethod.GET)
    public Result approve(@RequestParam(value = "orderInstanceId") String orderInstanceId,
                          @RequestParam(value = "handlerType") String handlerType) {
        Result result = employeeEntryApplyService.passAddOrder(orderInstanceId, handlerType);
        return result;
    }

    /**
     * 加载职员档案变更
     *
     * @param oeeoCode
     * @return
     */
    @MethodRegister(
            edmClass = EmployeeEntryApplyConstant.EDM_NAME,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "加载职员档案变更",
            getReqParamsNameNoPathVariable = {"employeeId", "oeeoCode"}
    )
    @RequestMapping(value = "/loadEditOrder", method = RequestMethod.GET)
    public Result loadEditOrder(@RequestParam(name = "employeeId") @NotNull(message = "员工ID不可为空") String employeeId, @RequestParam(name = "oeeoCode") @NotNull(message = "员工工号不可为空") String oeeoCode) throws Exception {
        Result result = employeeEntryApplyService.loadEditOrder(employeeId, oeeoCode);
        return result;
    }

    /**
     * 保存职员档案变更
     *
     * @param employeeEntryApplyDTO
     * @return
     */
    @MethodRegister(
            edmClass = EmployeeEntryApplyConstant.EDM_NAME,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "档案变更单据保存方法"
    )
    @RequestMapping(value = "/saveEditOrder", method = RequestMethod.PUT)
    public Result saveEditOrder(@RequestBody EmployeeEntryApplyDTO employeeEntryApplyDTO) throws Exception {
        Result result = employeeEntryApplyService.saveEditOrder(employeeEntryApplyDTO);
        return result;
    }

    /**
     * 档案变更单据提交方法
     *
     * @param employeeEntryApplyDTO
     * @return
     */
    @MethodRegister(
            edmClass = EmployeeEntryApplyConstant.EDM_NAME,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "档案变更单据提交方法"
    )
    @RequestMapping(value = "/submitEditOrder", method = RequestMethod.PUT)
    public Result submitEditOrder(@RequestBody EmployeeEntryApplyDTO employeeEntryApplyDTO) throws Exception {
        Result result = employeeEntryApplyService.submitEditOrder(employeeEntryApplyDTO);
        return result;
    }

    /**
     * 上传图片
     */
    @MethodRegister(
            edmClass = EmployeeEntryApplyConstant.EDM_NAME,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "图片上传方法"
    )
    @RequestMapping(value = "/uploadImg", method = RequestMethod.POST)
    public Result uploadImg(@RequestParam("file") MultipartFile file) {
        Result result = new Result();
        String path = nginxIp + employeeEntryApplyService.uploadImage(file);
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(path);
        return result;
    }

    @MethodRegister(
            edmClass = EmployeeEntryApplyConstant.EDM_NAME,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"auditSet"},
            methodDesc = "职员入职单/职员档案变更单填写审核意见方法")
    @RequestMapping(value = "/audit", method = RequestMethod.POST)
    public Result audit(@RequestBody JSONObject auditSet) {
        return employeeEntryApplyService.audit(auditSet);
    }
}
