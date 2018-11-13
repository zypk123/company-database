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
@RequestMapping("/oozie/deptPost")
public class PostForOozieController {
    @Autowired
    PostService postService;
    @Autowired
    OrmService ormService;

    @MethodRegister(
            edmClass = DeptPostSetOderConstants.EDM_DEPTPOSTSETORDER,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "部门岗位设置单新增任务调度测试岗")
    @RequestMapping(value = "/savePostAddOrderForOozie", method = RequestMethod.POST)
    public Result savePostAddOrderForOozie(@RequestBody String data) throws Exception {
        Result result = new Result();
        //返回单据信息
        JSONObject orderJson = postService.savePostAddOrderForOozie(data);
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(orderJson);
        return result;
    }

    @MethodRegister(
            edmClass = DeptPostSetOderConstants.EDM_DEPTPOSTSETORDER,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"odpsRemark", "odpsType", "ordeStatus"},
            methodDesc = "部门岗位设置单任务调度测试岗通过方法")
    @RequestMapping(value = "/passPostAddOrderForOozie", method = RequestMethod.GET)
    public Result passPostAddOrderForOozie(@RequestParam(value = "odpsRemark") String odpsRemark, @RequestParam(value = "odpsType") String odpsType,
                                           @RequestParam(value = "ordeStatus") String ordeStatus, @RequestParam(value = "odpsName") String odpsName) throws Exception {
        Result result = new Result();
        List<String> resultdatas = postService.passAddOrderForOozie(odpsRemark, odpsType, ordeStatus, odpsName);
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(resultdatas);
        return result;
    }


    @MethodRegister(
            edmClass = DeptPostSetOderConstants.EDM_DEPTPOSTSETORDER,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "部门岗位设置单任务调度测试岗通过方法")
    @RequestMapping(value = "/passPostAddOrderForOozie2", method = RequestMethod.GET)
    public Result passPostAddOrderForOozie2() throws Exception {
        Result result = new Result();
        String odpsRemark="任务调度";
        String odpsType="0";
        String ordeStatus="1";
        String odpsName="任务调度";
        List<String> resultdatas = postService.passAddOrderForOozie(odpsRemark, odpsType, ordeStatus, odpsName);
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(resultdatas);
        return result;
    }

}
