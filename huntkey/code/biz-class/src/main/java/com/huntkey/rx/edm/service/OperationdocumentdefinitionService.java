package com.huntkey.rx.edm.service;
import java.io.Serializable;
import java.util.*;
import com.huntkey.rx.base.BaseService;
import org.springframework.stereotype.Service;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.method.register.plugin.entity.ParamsVo;
import com.huntkey.rx.sceo.method.register.plugin.util.ExecUtil;


/**
 *
 * 单据定义类实体
 *
 */
@Service
public class OperationdocumentdefinitionService {


    public Result create(ParamsVo params) {
        params.setClassName("Operationdocumentdefinition");
        params.setMethodName("create");
        return ExecUtil.exec(params);
    }

    public Result disable(ParamsVo params) {
        params.setClassName("Operationdocumentdefinition");
        params.setMethodName("disable");
        return ExecUtil.exec(params);
    }

    public Result enable(ParamsVo params) {
        params.setClassName("Operationdocumentdefinition");
        params.setMethodName("enable");
        return ExecUtil.exec(params);
    }

    public Result load(ParamsVo params) {
        params.setClassName("Operationdocumentdefinition");
        params.setMethodName("load");
        return ExecUtil.exec(params);
    }

    public Result get(ParamsVo params) {
        params.setClassName("Operationdocumentdefinition");
        params.setMethodName("get");
        return ExecUtil.exec(params);
    }

    public Result update(ParamsVo params) {
        params.setClassName("Operationdocumentdefinition");
        params.setMethodName("update");
        return ExecUtil.exec(params);
    }

    public Result madable(ParamsVo params) {
        params.setClassName("Operationdocumentdefinition");
        params.setMethodName("madable");
        return ExecUtil.exec(params);
    }

    public Result oddfList(ParamsVo params) {
        params.setClassName("Operationdocumentdefinition");
        params.setMethodName("oddfList");
        return ExecUtil.exec(params);
    }

    public Result delete(ParamsVo params) {
        params.setClassName("Operationdocumentdefinition");
        params.setMethodName("delete");
        return ExecUtil.exec(params);
    }

    public Result list(ParamsVo params) {
        params.setClassName("Operationdocumentdefinition");
        params.setMethodName("list");
        return ExecUtil.exec(params);
    }



}