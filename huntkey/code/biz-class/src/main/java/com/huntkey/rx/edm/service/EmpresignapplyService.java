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
 * 离职申请单类实体
 *
 */
@Service
public class EmpresignapplyService {


    public Result loadEmpresignApply(ParamsVo params) {
        params.setClassName("Empresignapply");
        params.setMethodName("loadEmpresignApply");
        return ExecUtil.exec(params);
    }

    public Result queryEmpresignApply(ParamsVo params) {
        params.setClassName("Empresignapply");
        params.setMethodName("queryEmpresignApply");
        return ExecUtil.exec(params);
    }

    public Result submitEmpresignApply(ParamsVo params) {
        params.setClassName("Empresignapply");
        params.setMethodName("submitEmpresignApply");
        return ExecUtil.exec(params);
    }

    public Result passEmpresignApply(ParamsVo params) {
        params.setClassName("Empresignapply");
        params.setMethodName("passEmpresignApply");
        return ExecUtil.exec(params);
    }

    public Result saveEmpresignApply(ParamsVo params) {
        params.setClassName("Empresignapply");
        params.setMethodName("saveEmpresignApply");
        return ExecUtil.exec(params);
    }



}