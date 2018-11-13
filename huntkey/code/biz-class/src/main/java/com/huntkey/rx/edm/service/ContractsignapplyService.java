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
 * 合同签订单类实体
 *
 */
@Service
public class ContractsignapplyService {


    public Result passContractSignOrder(ParamsVo params) {
        params.setClassName("Contractsignapply");
        params.setMethodName("passContractSignOrder");
        return ExecUtil.exec(params);
    }

    public Result save(ParamsVo params) {
        params.setClassName("Contractsignapply");
        params.setMethodName("save");
        return ExecUtil.exec(params);
    }

    public Result load(ParamsVo params) {
        params.setClassName("Contractsignapply");
        params.setMethodName("load");
        return ExecUtil.exec(params);
    }

    public Result sign(ParamsVo params) {
        params.setClassName("Contractsignapply");
        params.setMethodName("sign");
        return ExecUtil.exec(params);
    }

    public Result submit(ParamsVo params) {
        params.setClassName("Contractsignapply");
        params.setMethodName("submit");
        return ExecUtil.exec(params);
    }

    public Result query(ParamsVo params) {
        params.setClassName("Contractsignapply");
        params.setMethodName("query");
        return ExecUtil.exec(params);
    }



}