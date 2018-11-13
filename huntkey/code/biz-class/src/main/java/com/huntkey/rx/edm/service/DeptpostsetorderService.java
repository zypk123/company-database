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
 * 部门岗位设置单类实体
 *
 */
@Service
public class DeptpostsetorderService {


    public Result savePostEditOrder(ParamsVo params) {
        params.setClassName("Deptpostsetorder");
        params.setMethodName("savePostEditOrder");
        return ExecUtil.exec(params);
    }

    public Result queryPostsByIds(ParamsVo params) {
        params.setClassName("Deptpostsetorder");
        params.setMethodName("queryPostsByIds");
        return ExecUtil.exec(params);
    }

    public Result passPostAddOrder(ParamsVo params) {
        params.setClassName("Deptpostsetorder");
        params.setMethodName("passPostAddOrder");
        return ExecUtil.exec(params);
    }

    public Result loadDpDeleteOrder(ParamsVo params) {
        params.setClassName("Deptpostsetorder");
        params.setMethodName("loadDpDeleteOrder");
        return ExecUtil.exec(params);
    }

    public Result savePostAddOrder(ParamsVo params) {
        params.setClassName("Deptpostsetorder");
        params.setMethodName("savePostAddOrder");
        return ExecUtil.exec(params);
    }

    public Result loadPostEditOrder(ParamsVo params) {
        params.setClassName("Deptpostsetorder");
        params.setMethodName("loadPostEditOrder");
        return ExecUtil.exec(params);
    }

    public Result isExistChangePostOrder(ParamsVo params) {
        params.setClassName("Deptpostsetorder");
        params.setMethodName("isExistChangePostOrder");
        return ExecUtil.exec(params);
    }

    public Result passDpDeleteOrder(ParamsVo params) {
        params.setClassName("Deptpostsetorder");
        params.setMethodName("passDpDeleteOrder");
        return ExecUtil.exec(params);
    }

    public Result isExistCheckPostOrder(ParamsVo params) {
        params.setClassName("Deptpostsetorder");
        params.setMethodName("isExistCheckPostOrder");
        return ExecUtil.exec(params);
    }

    public Result saveDpDeleteOrder(ParamsVo params) {
        params.setClassName("Deptpostsetorder");
        params.setMethodName("saveDpDeleteOrder");
        return ExecUtil.exec(params);
    }

    public Result loadPostAddOrder(ParamsVo params) {
        params.setClassName("Deptpostsetorder");
        params.setMethodName("loadPostAddOrder");
        return ExecUtil.exec(params);
    }

    public Result submitDpDeleteOrder(ParamsVo params) {
        params.setClassName("Deptpostsetorder");
        params.setMethodName("submitDpDeleteOrder");
        return ExecUtil.exec(params);
    }

    public Result queryPostEditOrder(ParamsVo params) {
        params.setClassName("Deptpostsetorder");
        params.setMethodName("queryPostEditOrder");
        return ExecUtil.exec(params);
    }

    public Result passPostEditOrder(ParamsVo params) {
        params.setClassName("Deptpostsetorder");
        params.setMethodName("passPostEditOrder");
        return ExecUtil.exec(params);
    }

    public Result submitPostAddOrder(ParamsVo params) {
        params.setClassName("Deptpostsetorder");
        params.setMethodName("submitPostAddOrder");
        return ExecUtil.exec(params);
    }

    public Result submitPostEditOrder(ParamsVo params) {
        params.setClassName("Deptpostsetorder");
        params.setMethodName("submitPostEditOrder");
        return ExecUtil.exec(params);
    }

    public Result checkSub(ParamsVo params) {
        params.setClassName("Deptpostsetorder");
        params.setMethodName("checkSub");
        return ExecUtil.exec(params);
    }



}