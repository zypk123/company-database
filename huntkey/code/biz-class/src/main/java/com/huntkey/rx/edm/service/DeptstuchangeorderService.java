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
 * 部门结构异动单类实体
 *
 */
@Service
public class DeptstuchangeorderService {


    public Result passDeptAddOrder(ParamsVo params) {
        params.setClassName("Deptstuchangeorder");
        params.setMethodName("passDeptAddOrder");
        return ExecUtil.exec(params);
    }

    public Result passDeptDeleteOrder(ParamsVo params) {
        params.setClassName("Deptstuchangeorder");
        params.setMethodName("passDeptDeleteOrder");
        return ExecUtil.exec(params);
    }

    public Result passDeptMoveOrder(ParamsVo params) {
        params.setClassName("Deptstuchangeorder");
        params.setMethodName("passDeptMoveOrder");
        return ExecUtil.exec(params);
    }

    public Result loadDeptModifyOrder(ParamsVo params) {
        params.setClassName("Deptstuchangeorder");
        params.setMethodName("loadDeptModifyOrder");
        return ExecUtil.exec(params);
    }

    public Result deptRemovable(ParamsVo params) {
        params.setClassName("Deptstuchangeorder");
        params.setMethodName("deptRemovable");
        return ExecUtil.exec(params);
    }

    public Result saveDeptMoveOrder(ParamsVo params) {
        params.setClassName("Deptstuchangeorder");
        params.setMethodName("saveDeptMoveOrder");
        return ExecUtil.exec(params);
    }

    public Result saveDeptDeleteOrder(ParamsVo params) {
        params.setClassName("Deptstuchangeorder");
        params.setMethodName("saveDeptDeleteOrder");
        return ExecUtil.exec(params);
    }

    public Result loadDeptMoveOrder(ParamsVo params) {
        params.setClassName("Deptstuchangeorder");
        params.setMethodName("loadDeptMoveOrder");
        return ExecUtil.exec(params);
    }

    public Result loadDeptDeleteList(ParamsVo params) {
        params.setClassName("Deptstuchangeorder");
        params.setMethodName("loadDeptDeleteList");
        return ExecUtil.exec(params);
    }

    public Result submitDeptModifyOrder(ParamsVo params) {
        params.setClassName("Deptstuchangeorder");
        params.setMethodName("submitDeptModifyOrder");
        return ExecUtil.exec(params);
    }

    public Result saveDeptModifyOrder(ParamsVo params) {
        params.setClassName("Deptstuchangeorder");
        params.setMethodName("saveDeptModifyOrder");
        return ExecUtil.exec(params);
    }

    public Result passDeptModifyOrder(ParamsVo params) {
        params.setClassName("Deptstuchangeorder");
        params.setMethodName("passDeptModifyOrder");
        return ExecUtil.exec(params);
    }

    public Result submitDeptMoveOrder(ParamsVo params) {
        params.setClassName("Deptstuchangeorder");
        params.setMethodName("submitDeptMoveOrder");
        return ExecUtil.exec(params);
    }

    public Result loadDeptAddOrder(ParamsVo params) {
        params.setClassName("Deptstuchangeorder");
        params.setMethodName("loadDeptAddOrder");
        return ExecUtil.exec(params);
    }

    public Result saveDeptAddOrder(ParamsVo params) {
        params.setClassName("Deptstuchangeorder");
        params.setMethodName("saveDeptAddOrder");
        return ExecUtil.exec(params);
    }

    public Result submitDeptAddOrder(ParamsVo params) {
        params.setClassName("Deptstuchangeorder");
        params.setMethodName("submitDeptAddOrder");
        return ExecUtil.exec(params);
    }

    public Result queryMoveDeptList(ParamsVo params) {
        params.setClassName("Deptstuchangeorder");
        params.setMethodName("queryMoveDeptList");
        return ExecUtil.exec(params);
    }

    public Result submitDeptDeleteOrder(ParamsVo params) {
        params.setClassName("Deptstuchangeorder");
        params.setMethodName("submitDeptDeleteOrder");
        return ExecUtil.exec(params);
    }



}