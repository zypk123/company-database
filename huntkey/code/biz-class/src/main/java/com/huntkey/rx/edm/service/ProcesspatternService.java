package com.huntkey.rx.edm.service;
import java.io.Serializable;
import java.util.*;
import com.huntkey.rx.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.Date;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.method.register.plugin.entity.ParamsVo;
import com.huntkey.rx.sceo.method.register.plugin.util.ExecUtil;


/**
 *
 * 流程定义类实体
 *
 */
@Service
public class ProcesspatternService {


    public Result getDeptNameByDeptId(ParamsVo params) {
        params.setClassName("Processpattern");
        params.setMethodName("getDeptNameByDeptId");
        return ExecUtil.exec(params);
    }

    public Result getConfigEnumerateJson(ParamsVo params) {
        params.setClassName("Processpattern");
        params.setMethodName("getConfigEnumerateJson");
        return ExecUtil.exec(params);
    }

    public Result findProcPatternById(ParamsVo params) {
        params.setClassName("Processpattern");
        params.setMethodName("findProcPatternById");
        return ExecUtil.exec(params);
    }

    public Result deleteProcPatternById(ParamsVo params) {
        params.setClassName("Processpattern");
        params.setMethodName("deleteProcPatternById");
        return ExecUtil.exec(params);
    }

    public Result findProcPatternByPage(ParamsVo params) {
        params.setClassName("Processpattern");
        params.setMethodName("findProcPatternByPage");
        return ExecUtil.exec(params);
    }

    public Result updateFlowConfig(ParamsVo params) {
        params.setClassName("Processpattern");
        params.setMethodName("updateFlowConfig");
        return ExecUtil.exec(params);
    }

    public Result addProcPattern(ParamsVo params) {
        params.setClassName("Processpattern");
        params.setMethodName("addProcPattern");
        return ExecUtil.exec(params);
    }

    public Result updateProcPattern(ParamsVo params) {
        params.setClassName("Processpattern");
        params.setMethodName("updateProcPattern");
        return ExecUtil.exec(params);
    }



}