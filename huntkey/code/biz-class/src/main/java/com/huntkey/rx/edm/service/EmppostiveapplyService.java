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
 * 转正申请单类实体
 *
 */
@Service
public class EmppostiveapplyService {


    public Result queryPositive(ParamsVo params) {
        params.setClassName("Emppostiveapply");
        params.setMethodName("queryPositive");
        return ExecUtil.exec(params);
    }

    public Result download(ParamsVo params) {
        params.setClassName("Emppostiveapply");
        params.setMethodName("download");
        return ExecUtil.exec(params);
    }

    public Result submitPositive(ParamsVo params) {
        params.setClassName("Emppostiveapply");
        params.setMethodName("submitPositive");
        return ExecUtil.exec(params);
    }

    public Result loadPositive(ParamsVo params) {
        params.setClassName("Emppostiveapply");
        params.setMethodName("loadPositive");
        return ExecUtil.exec(params);
    }

    public Result uploadPositive(ParamsVo params) {
        params.setClassName("Emppostiveapply");
        params.setMethodName("uploadPositive");
        return ExecUtil.exec(params);
    }

    public Result addPositive(ParamsVo params) {
        params.setClassName("Emppostiveapply");
        params.setMethodName("addPositive");
        return ExecUtil.exec(params);
    }

    public Result empPositive(ParamsVo params) {
        params.setClassName("Emppostiveapply");
        params.setMethodName("empPositive");
        return ExecUtil.exec(params);
    }



}