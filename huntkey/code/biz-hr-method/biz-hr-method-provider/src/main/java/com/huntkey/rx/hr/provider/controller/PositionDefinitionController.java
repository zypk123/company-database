package com.huntkey.rx.hr.provider.controller;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.edm.entity.PositiondefinitionEntity;
import com.huntkey.rx.hr.common.constants.PositionDefinitionConstant;
import com.huntkey.rx.hr.common.model.PostDefinitionOrderConstants;
import com.huntkey.rx.hr.common.model.PostDefinitionOrderDTO;
import com.huntkey.rx.hr.provider.service.PositionDefinitionService;
import com.huntkey.rx.sceo.method.register.plugin.annotation.MethodRegister;
import com.huntkey.rx.sceo.method.register.plugin.entity.MethodExeFrequency;
import com.huntkey.rx.sceo.method.register.plugin.entity.ProgramCate;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Created by weijian on 2017/11/13.
 * 职位定义
 */
@RestController
@Validated
@RequestMapping("/hr/positiondefinition")
public class PositionDefinitionController {

    @Autowired
    private PositionDefinitionService positionDefinitionService;

    /**
     * 查询职位列表
     *
     * @param positionAttributeType 职位属性类别，0:直接职位; 1:间接职位
     * @param positionTypeCodeValue 职类代码区间，用,分割的字符串 不能有空格
     * @param postGradeValue        岗级代码区间，用,分割的字符串 不能有空格
     * @return
     */
    @MethodRegister(
            edmClass = PositionDefinitionConstant.EDM_NAME,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"positionAttributeType", "positionTypeCode", "postGrade"},
            methodDesc = "查询职位列表")
    @RequestMapping(value = "/positions", method = RequestMethod.GET)
    public Result queryPositionList(@RequestParam(value = "positionAttributeType", required = false) String positionAttributeType,
                                    @RequestParam(value = "positionTypeCode", required = false) String positionTypeCodeValue,
                                    @RequestParam(value = "postGrade", required = false) String postGradeValue) {
        return positionDefinitionService.queryPositionListService(positionAttributeType, positionTypeCodeValue, postGradeValue);
    }

    /**
     * 保存职位定义方法
     *
     * @return
     */
    @MethodRegister(
            edmClass = PositionDefinitionConstant.EDM_NAME,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "保存职位定义方法")
    @RequestMapping(value = "/positions", method = RequestMethod.POST)
    public Result savePosition() {
        String[][] positionList = new String[][]{{"研发组长-c", "0001010"}, {"研发组长-d", "0001011"}};

        List<String[]> list = Arrays.asList(positionList);

        List<PositiondefinitionEntity> positionTestList = new ArrayList<>();

        list.forEach(t -> {
            PositiondefinitionEntity entity = new PositiondefinitionEntity();
            entity.setRpof_type("10");
            entity.setRpof_prop("0");
            entity.setRpof_code(t[1]);
            entity.setRpof_name(t[0]);
            entity.setRpof_grade("05");
            positionTestList.add(entity);
        });

        return positionDefinitionService.savePositionService(positionTestList);
    }

    /**
     * 删除职位定义方法
     *
     * @return
     */
    @MethodRegister(
            edmClass = PositionDefinitionConstant.EDM_NAME,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "删除职位定义方法")
    @RequestMapping(value = "/positions", method = RequestMethod.DELETE)
    public Result deletePosition() {
        List<String> positionCodeList = new ArrayList<>();
        positionCodeList.add("376b3edd51ca4a9b970cd0ecc88765fa");
        positionCodeList.add("7adbaba9248f4b128a5f3d3326d4dbf6");
        return positionDefinitionService.deletePositionService(positionCodeList);
    }

    /**
     * 职位维护单加载方法（加载职位类数据）
     *
     * @return
     */
    @MethodRegister(
            edmClass = PostDefinitionOrderConstants.EDM_NAME,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"rpofType", "rpofProp"},
            methodDesc = "职位维护单加载方法")
    @RequestMapping(value = "/load", method = RequestMethod.GET)
    public Result positiondefinitionLoad(@RequestParam @NotBlank(message = "职位类别不可为空") String rpofType) {
        return positionDefinitionService.load(rpofType);
    }

    /**
     * 职位维护单保存方法
     *
     * @return
     */
    @MethodRegister(
            edmClass = PostDefinitionOrderConstants.EDM_NAME,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "职位维护单保存方法")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result savePositionDefinitionOrder(@RequestBody PostDefinitionOrderDTO postDefinitionOrderDTO) throws Exception {
        return positionDefinitionService.save(postDefinitionOrderDTO);
    }

    /**
     * 职位维护单提交方法
     *
     * @return
     */
    @MethodRegister(
            edmClass = PostDefinitionOrderConstants.EDM_NAME,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "职位维护单提交方法")
    @RequestMapping(value = "/submit", method = RequestMethod.PUT)
    public Result submitPositionDefinitionOrder(@RequestBody PostDefinitionOrderDTO postDefinitionOrderDTO) throws Exception {
        return positionDefinitionService.submit(postDefinitionOrderDTO);
    }

    /**
     * 职位维护单提交加载方法（加载单据数据）
     *
     * @return
     */
    @MethodRegister(
            edmClass = PostDefinitionOrderConstants.EDM_NAME,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "职位维护单提交加载方法（加载单据数据）",
            getReqParamsNameNoPathVariable = {"id"})
    @RequestMapping(value = "/loadPostDefinitionOrder", method = RequestMethod.GET)
    public Result loadPostDefinitionOrder(@RequestParam(value = "id") String id) {
        return positionDefinitionService.loadPostDefinitionOrder(id);
    }

    /**
     * 职位维护单通过方法
     *
     * @return
     */
    @MethodRegister(
            edmClass = PostDefinitionOrderConstants.EDM_NAME,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"orderInstanceId","handlerType"},
            methodDesc = "职位维护单通过方法")
    @RequestMapping(value = "/pass", method = RequestMethod.GET)
    public Result approve(@RequestParam(value = "orderInstanceId") String orderInstanceId,
                          @RequestParam(value = "handlerType") String handlerType) {
        return positionDefinitionService.pass(orderInstanceId,handlerType);
    }


    @MethodRegister(
            edmClass = PostDefinitionOrderConstants.EDM_NAME,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"auditSet"},
            methodDesc = "职位维护单填写审核意见方法")
    @RequestMapping(value = "/audit", method = RequestMethod.POST)
    public Result audit(@RequestBody JSONObject auditSet) {
        return positionDefinitionService.audit(auditSet);
    }
}
