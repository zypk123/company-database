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
 * 离职手续单类实体
 *
 */
@Service
public class EmpresignprocedureService {


    public Result submitEmpresignProcedureOrder(ParamsVo params) {
        params.setClassName("Empresignprocedure");
        params.setMethodName("submitEmpresignProcedureOrder");
        return ExecUtil.exec(params);
    }

    public Result passEmpresignProcedureOrder(ParamsVo params) {
        params.setClassName("Empresignprocedure");
        params.setMethodName("passEmpresignProcedureOrder");
        return ExecUtil.exec(params);
    }

    public Result loadEmpresignProcedureOrder(ParamsVo params) {
        params.setClassName("Empresignprocedure");
        params.setMethodName("loadEmpresignProcedureOrder");
        return ExecUtil.exec(params);
    }

    public Result saveEmpresignProcedureOrder(ParamsVo params) {
        params.setClassName("Empresignprocedure");
        params.setMethodName("saveEmpresignProcedureOrder");
        return ExecUtil.exec(params);
    }

    public Result queryDeleteOrder(ParamsVo params) {
        params.setClassName("Empresignprocedure");
        params.setMethodName("queryDeleteOrder");
        return ExecUtil.exec(params);
    }



}