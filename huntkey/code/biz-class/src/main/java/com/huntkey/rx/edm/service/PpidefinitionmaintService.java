package com.huntkey.rx.edm.service;
import java.io.Serializable;
import java.util.*;
import com.huntkey.rx.base.BaseService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.method.register.plugin.entity.ParamsVo;
import com.huntkey.rx.sceo.method.register.plugin.util.ExecUtil;


/**
 *
 * 指标定义维护类实体
 *
 */
@Service
public class PpidefinitionmaintService {


    public Result validateOpfmName(ParamsVo params) {
        params.setClassName("Ppidefinitionmaint");
        params.setMethodName("validateOpfmName");
        return ExecUtil.exec(params);
    }

    public Result validateAlikeIndex(ParamsVo params) {
        params.setClassName("Ppidefinitionmaint");
        params.setMethodName("validateAlikeIndex");
        return ExecUtil.exec(params);
    }

    public Result getResouse(ParamsVo params) {
        params.setClassName("Ppidefinitionmaint");
        params.setMethodName("getResouse");
        return ExecUtil.exec(params);
    }

    public Result defOrderPass(ParamsVo params) {
        params.setClassName("Ppidefinitionmaint");
        params.setMethodName("defOrderPass");
        return ExecUtil.exec(params);
    }

    public Result getCodes(ParamsVo params) {
        params.setClassName("Ppidefinitionmaint");
        params.setMethodName("getCodes");
        return ExecUtil.exec(params);
    }

    public Result cancleDefinition(ParamsVo params) {
        params.setClassName("Ppidefinitionmaint");
        params.setMethodName("cancleDefinition");
        return ExecUtil.exec(params);
    }

    public Result submitFormula(ParamsVo params) {
        params.setClassName("Ppidefinitionmaint");
        params.setMethodName("submitFormula");
        return ExecUtil.exec(params);
    }

    public Result validateVarName(ParamsVo params) {
        params.setClassName("Ppidefinitionmaint");
        params.setMethodName("validateVarName");
        return ExecUtil.exec(params);
    }

    public Result getMoniResouse(ParamsVo params) {
        params.setClassName("Ppidefinitionmaint");
        params.setMethodName("getMoniResouse");
        return ExecUtil.exec(params);
    }

    public Result fornulaOrderPass(ParamsVo params) {
        params.setClassName("Ppidefinitionmaint");
        params.setMethodName("fornulaOrderPass");
        return ExecUtil.exec(params);
    }

    public Result addDefinition(ParamsVo params) {
        params.setClassName("Ppidefinitionmaint");
        params.setMethodName("addDefinition");
        return ExecUtil.exec(params);
    }

    public Result ppiDefinitionDetail(ParamsVo params) {
        params.setClassName("Ppidefinitionmaint");
        params.setMethodName("ppiDefinitionDetail");
        return ExecUtil.exec(params);
    }

    public Result nullifyDefinition(ParamsVo params) {
        params.setClassName("Ppidefinitionmaint");
        params.setMethodName("nullifyDefinition");
        return ExecUtil.exec(params);
    }

    public Result getResouseProps(ParamsVo params) {
        params.setClassName("Ppidefinitionmaint");
        params.setMethodName("getResouseProps");
        return ExecUtil.exec(params);
    }

    public Result patchDefinition(ParamsVo params) {
        params.setClassName("Ppidefinitionmaint");
        params.setMethodName("patchDefinition");
        return ExecUtil.exec(params);
    }

    public Result getDeptTree(ParamsVo params) {
        params.setClassName("Ppidefinitionmaint");
        params.setMethodName("getDeptTree");
        return ExecUtil.exec(params);
    }

    public Result submitDefinition(ParamsVo params) {
        params.setClassName("Ppidefinitionmaint");
        params.setMethodName("submitDefinition");
        return ExecUtil.exec(params);
    }

    public Result patchFormula(ParamsVo params) {
        params.setClassName("Ppidefinitionmaint");
        params.setMethodName("patchFormula");
        return ExecUtil.exec(params);
    }



}