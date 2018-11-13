package com.huntkey.rx.hr.provider.controller;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.hr.common.model.EdmPostchangeapplyConstants;
import com.huntkey.rx.hr.provider.service.EmpPostChangeApplyService;
import com.huntkey.rx.sceo.method.register.plugin.annotation.MethodRegister;
import com.huntkey.rx.sceo.method.register.plugin.entity.MethodExeFrequency;
import com.huntkey.rx.sceo.method.register.plugin.entity.ProgramCate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liulang
 */
@RestController
@RequestMapping("/hr/postCancel")
public class PostCancelController {

    @Autowired
    private EmpPostChangeApplyService empPostChangeApplyService;

    @MethodRegister(
            edmClass = EdmPostchangeapplyConstants.EDM_EMPPOSTCHANGEAPPLY,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"empPostChangeApplyDTO"},
            methodDesc = "人员离岗单保存方法")
    @RequestMapping(value = "/saveDeleteOrder", method = RequestMethod.POST)
    public Result saveDeleteOrder(@RequestBody JSONObject empPostChangeApplyDTO) throws Exception{
        return empPostChangeApplyService.saveDeleteOrder(empPostChangeApplyDTO);
    }

    @MethodRegister(
            edmClass = EdmPostchangeapplyConstants.EDM_EMPPOSTCHANGEAPPLY,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"empPostChangeApplyDTO"},
            methodDesc = "人员离岗单提交方法")
    @RequestMapping(value = "/sumbitDeleteOrder", method = RequestMethod.POST)
    public Result sumbitDeleteOrder(@RequestBody JSONObject empPostChangeApplyDTO) throws Exception {
        return empPostChangeApplyService.submitDeleteOrder(empPostChangeApplyDTO);
    }


    @MethodRegister(
            edmClass = EdmPostchangeapplyConstants.EDM_EMPPOSTCHANGEAPPLY,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"id"},
            methodDesc = "人员离岗单加载详情方法")
    @RequestMapping(value = "/loadDeleteOrder", method = RequestMethod.GET)
    public Result loadDeleteOrder(@RequestParam String id) {
        return empPostChangeApplyService.loadDeleteOrder(id);
    }
}
