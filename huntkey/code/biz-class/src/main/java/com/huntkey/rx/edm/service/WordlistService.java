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
 * 枚举列表类实体
 *
 */
@Service
public class WordlistService {


    public Result getEdmAttributeAndMethodById(ParamsVo params) {
        params.setClassName("Wordlist");
        params.setMethodName("getEdmAttributeAndMethodById");
        return ExecUtil.exec(params);
    }

    public Result getPropertiesByEdmcId(ParamsVo params) {
        params.setClassName("Wordlist");
        params.setMethodName("getPropertiesByEdmcId");
        return ExecUtil.exec(params);
    }

    public Result enumlist(ParamsVo params) {
        params.setClassName("Wordlist");
        params.setMethodName("enumlist");
        return ExecUtil.exec(params);
    }

    public Result getEnumByCode(ParamsVo params) {
        params.setClassName("Wordlist");
        params.setMethodName("getEnumByCode");
        return ExecUtil.exec(params);
    }

    public Result getEnum(ParamsVo params) {
        params.setClassName("Wordlist");
        params.setMethodName("getEnum");
        return ExecUtil.exec(params);
    }

    public Result getJointlyAttributeSet(ParamsVo params) {
        params.setClassName("Wordlist");
        params.setMethodName("getJointlyAttributeSet");
        return ExecUtil.exec(params);
    }

    public Result getPublishModelerClass(ParamsVo params) {
        params.setClassName("Wordlist");
        params.setMethodName("getPublishModelerClass");
        return ExecUtil.exec(params);
    }

    public Result updateEnum(ParamsVo params) {
        params.setClassName("Wordlist");
        params.setMethodName("updateEnum");
        return ExecUtil.exec(params);
    }

    public Result getEnumObjects(ParamsVo params) {
        params.setClassName("Wordlist");
        params.setMethodName("getEnumObjects");
        return ExecUtil.exec(params);
    }

    public Result deleteEnum(ParamsVo params) {
        params.setClassName("Wordlist");
        params.setMethodName("deleteEnum");
        return ExecUtil.exec(params);
    }



}