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
 * 员工岗位调整单类实体
 *
 */
@Service
public class EmppostchangeapplyService {


    public Result passAddOrder(ParamsVo params) {
        params.setClassName("Emppostchangeapply");
        params.setMethodName("passAddOrder");
        return ExecUtil.exec(params);
    }

    public Result submitAddOrder(ParamsVo params) {
        params.setClassName("Emppostchangeapply");
        params.setMethodName("submitAddOrder");
        return ExecUtil.exec(params);
    }

    public Result loadAddOrder(ParamsVo params) {
        params.setClassName("Emppostchangeapply");
        params.setMethodName("loadAddOrder");
        return ExecUtil.exec(params);
    }

    public Result saveDeleteOrder(ParamsVo params) {
        params.setClassName("Emppostchangeapply");
        params.setMethodName("saveDeleteOrder");
        return ExecUtil.exec(params);
    }

    public Result loadEditOrder(ParamsVo params) {
        params.setClassName("Emppostchangeapply");
        params.setMethodName("loadEditOrder");
        return ExecUtil.exec(params);
    }

    public Result approve(ParamsVo params) {
        params.setClassName("Emppostchangeapply");
        params.setMethodName("approve");
        return ExecUtil.exec(params);
    }

    public Result saveAddOrder(ParamsVo params) {
        params.setClassName("Emppostchangeapply");
        params.setMethodName("saveAddOrder");
        return ExecUtil.exec(params);
    }

    public Result passOrder(ParamsVo params) {
        params.setClassName("Emppostchangeapply");
        params.setMethodName("passOrder");
        return ExecUtil.exec(params);
    }

    public Result loadDeleteOrder(ParamsVo params) {
        params.setClassName("Emppostchangeapply");
        params.setMethodName("loadDeleteOrder");
        return ExecUtil.exec(params);
    }

    public Result empPostRemove(ParamsVo params) {
        params.setClassName("Emppostchangeapply");
        params.setMethodName("empPostRemove");
        return ExecUtil.exec(params);
    }

    public Result submitEditOrder(ParamsVo params) {
        params.setClassName("Emppostchangeapply");
        params.setMethodName("submitEditOrder");
        return ExecUtil.exec(params);
    }

    public Result sumbitDeleteOrder(ParamsVo params) {
        params.setClassName("Emppostchangeapply");
        params.setMethodName("sumbitDeleteOrder");
        return ExecUtil.exec(params);
    }

    public Result passEditOrder(ParamsVo params) {
        params.setClassName("Emppostchangeapply");
        params.setMethodName("passEditOrder");
        return ExecUtil.exec(params);
    }

    public Result saveEditOrder(ParamsVo params) {
        params.setClassName("Emppostchangeapply");
        params.setMethodName("saveEditOrder");
        return ExecUtil.exec(params);
    }



}