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
 * 企业类实体
 *
 */
@Service
public class EnterpriseService {


    public Result addEnterPriseInfo(ParamsVo params) {
        params.setClassName("Enterprise");
        params.setMethodName("addEnterPriseInfo");
        return ExecUtil.exec(params);
    }

    public Result isSceoUrlExist(ParamsVo params) {
        params.setClassName("Enterprise");
        params.setMethodName("isSceoUrlExist");
        return ExecUtil.exec(params);
    }

    public Result AutoMethod003(ParamsVo params) {
        params.setClassName("Enterprise");
        params.setMethodName("AutoMethod003");
        return ExecUtil.exec(params);
    }

    public Result MyTest345(ParamsVo params) {
        params.setClassName("Enterprise");
        params.setMethodName("MyTest345");
        return ExecUtil.exec(params);
    }

    public Result ASyncMethod(ParamsVo params) {
        params.setClassName("Enterprise");
        params.setMethodName("ASyncMethod");
        return ExecUtil.exec(params);
    }

    public Result ASyncMethod111(ParamsVo params) {
        params.setClassName("Enterprise");
        params.setMethodName("ASyncMethod111");
        return ExecUtil.exec(params);
    }

    public Result test1(ParamsVo params) {
        params.setClassName("Enterprise");
        params.setMethodName("test1");
        return ExecUtil.exec(params);
    }

    public Result searchFile2(ParamsVo params) {
        params.setClassName("Enterprise");
        params.setMethodName("searchFile2");
        return ExecUtil.exec(params);
    }



}