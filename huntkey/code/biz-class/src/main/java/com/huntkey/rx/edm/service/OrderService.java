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
 * 单据类实体
 *
 */
@Service
public class OrderService {


    public Result syncLinkArithmetic(ParamsVo params) {
        params.setClassName("Order");
        params.setMethodName("syncLinkArithmetic");
        return ExecUtil.exec(params);
    }

    public Result deleteOrder(ParamsVo params) {
        params.setClassName("Order");
        params.setMethodName("deleteOrder");
        return ExecUtil.exec(params);
    }

    public Result submit(ParamsVo params) {
        params.setClassName("Order");
        params.setMethodName("submit");
        return ExecUtil.exec(params);
    }

    public Result arithmetic(ParamsVo params) {
        params.setClassName("Order");
        params.setMethodName("arithmetic");
        return ExecUtil.exec(params);
    }

    public Result auditInfo(ParamsVo params) {
        params.setClassName("Order");
        params.setMethodName("auditInfo");
        return ExecUtil.exec(params);
    }

    public Result audit(ParamsVo params) {
        params.setClassName("Order");
        params.setMethodName("audit");
        return ExecUtil.exec(params);
    }

    public Result orderList(ParamsVo params) {
        params.setClassName("Order");
        params.setMethodName("orderList");
        return ExecUtil.exec(params);
    }

    public Result pending(ParamsVo params) {
        params.setClassName("Order");
        params.setMethodName("pending");
        return ExecUtil.exec(params);
    }

    public Result effect(ParamsVo params) {
        params.setClassName("Order");
        params.setMethodName("effect");
        return ExecUtil.exec(params);
    }

    public Result asyncLinkArithmetic(ParamsVo params) {
        params.setClassName("Order");
        params.setMethodName("asyncLinkArithmetic");
        return ExecUtil.exec(params);
    }



}