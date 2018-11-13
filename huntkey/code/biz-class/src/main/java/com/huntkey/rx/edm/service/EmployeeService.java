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
 * 员工类实体
 *
 */
@Service
public class EmployeeService {


    public Result loadEmptyPost(ParamsVo params) {
        params.setClassName("Employee");
        params.setMethodName("loadEmptyPost");
        return ExecUtil.exec(params);
    }

    public Result deptEmptyPost(ParamsVo params) {
        params.setClassName("Employee");
        params.setMethodName("deptEmptyPost");
        return ExecUtil.exec(params);
    }

    public Result orderList(ParamsVo params) {
        params.setClassName("Employee");
        params.setMethodName("orderList");
        return ExecUtil.exec(params);
    }

    public Result getEmployeePositions(ParamsVo params) {
        params.setClassName("Employee");
        params.setMethodName("getEmployeePositions");
        return ExecUtil.exec(params);
    }

    public Result queryByDeptEmp(ParamsVo params) {
        params.setClassName("Employee");
        params.setMethodName("queryByDeptEmp");
        return ExecUtil.exec(params);
    }

    public Result deleteEmployee(ParamsVo params) {
        params.setClassName("Employee");
        params.setMethodName("deleteEmployee");
        return ExecUtil.exec(params);
    }

    public Result saveEmployee(ParamsVo params) {
        params.setClassName("Employee");
        params.setMethodName("saveEmployee");
        return ExecUtil.exec(params);
    }

    public Result deleteOrder(ParamsVo params) {
        params.setClassName("Employee");
        params.setMethodName("deleteOrder");
        return ExecUtil.exec(params);
    }

    public Result queryEmployee(ParamsVo params) {
        params.setClassName("Employee");
        params.setMethodName("queryEmployee");
        return ExecUtil.exec(params);
    }

    public Result findEmployeeById(ParamsVo params) {
        params.setClassName("Employee");
        params.setMethodName("findEmployeeById");
        return ExecUtil.exec(params);
    }

    public Result loadEmployeePost(ParamsVo params) {
        params.setClassName("Employee");
        params.setMethodName("loadEmployeePost");
        return ExecUtil.exec(params);
    }



}