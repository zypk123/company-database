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
 * 活动实例类实体
 *
 */
@Service
public class ActinstanceService {


    public Result actInstanceHandler(ParamsVo params) {
        params.setClassName("Actinstance");
        params.setMethodName("actInstanceHandler");
        return ExecUtil.exec(params);
    }

    public Result getPendingActInstances(ParamsVo params) {
        params.setClassName("Actinstance");
        params.setMethodName("getPendingActInstances");
        return ExecUtil.exec(params);
    }

    public Result getPendingCount(ParamsVo params) {
        params.setClassName("Actinstance");
        params.setMethodName("getPendingCount");
        return ExecUtil.exec(params);
    }

    public Result getApprovedByPerson(ParamsVo params) {
        params.setClassName("Actinstance");
        params.setMethodName("getApprovedByPerson");
        return ExecUtil.exec(params);
    }

    public Result getApprovalRecords(ParamsVo params) {
        params.setClassName("Actinstance");
        params.setMethodName("getApprovalRecords");
        return ExecUtil.exec(params);
    }



}