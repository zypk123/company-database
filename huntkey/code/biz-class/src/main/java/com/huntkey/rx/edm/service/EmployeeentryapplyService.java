package com.huntkey.rx.edm.service;
import java.io.Serializable;
import java.util.*;
import com.huntkey.rx.base.BaseService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.method.register.plugin.entity.ParamsVo;
import com.huntkey.rx.sceo.method.register.plugin.util.ExecUtil;


/**
 *
 * 职员入职单类实体
 *
 */
@Service
public class EmployeeentryapplyService {


    public Result loadEditOrder(ParamsVo params) {
        params.setClassName("Employeeentryapply");
        params.setMethodName("loadEditOrder");
        return ExecUtil.exec(params);
    }

    public Result saveAddOrder(ParamsVo params) {
        params.setClassName("Employeeentryapply");
        params.setMethodName("saveAddOrder");
        return ExecUtil.exec(params);
    }

    public Result submitEditOrder(ParamsVo params) {
        params.setClassName("Employeeentryapply");
        params.setMethodName("submitEditOrder");
        return ExecUtil.exec(params);
    }

    public Result loadAddOrder(ParamsVo params) {
        params.setClassName("Employeeentryapply");
        params.setMethodName("loadAddOrder");
        return ExecUtil.exec(params);
    }

    public Result passAddOrder(ParamsVo params) {
        params.setClassName("Employeeentryapply");
        params.setMethodName("passAddOrder");
        return ExecUtil.exec(params);
    }

    public Result passEditOrder(ParamsVo params) {
        params.setClassName("Employeeentryapply");
        params.setMethodName("passEditOrder");
        return ExecUtil.exec(params);
    }

    public Result saveEditOrder(ParamsVo params) {
        params.setClassName("Employeeentryapply");
        params.setMethodName("saveEditOrder");
        return ExecUtil.exec(params);
    }

    public Result uploadImg(ParamsVo params) {
        params.setClassName("Employeeentryapply");
        params.setMethodName("uploadImg");
        return ExecUtil.exec(params);
    }

    public Result submitAddOrder(ParamsVo params) {
        params.setClassName("Employeeentryapply");
        params.setMethodName("submitAddOrder");
        return ExecUtil.exec(params);
    }



}