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
 * 部门类实体
 *
 */
@Service
public class DepttreeService {


    public Result deptTreeList(ParamsVo params) {
        params.setClassName("Depttree");
        params.setMethodName("deptTreeList");
        return ExecUtil.exec(params);
    }

    public Result deptIsEditable(ParamsVo params) {
        params.setClassName("Depttree");
        params.setMethodName("deptIsEditable");
        return ExecUtil.exec(params);
    }

    public Result depttreeQueryDeptEmployees(ParamsVo params) {
        params.setClassName("Depttree");
        params.setMethodName("depttreeQueryDeptEmployees");
        return ExecUtil.exec(params);
    }

    public Result depttreeLoadEmployeeInfo(ParamsVo params) {
        params.setClassName("Depttree");
        params.setMethodName("depttreeLoadEmployeeInfo");
        return ExecUtil.exec(params);
    }

    public Result deptList(ParamsVo params) {
        params.setClassName("Depttree");
        params.setMethodName("deptList");
        return ExecUtil.exec(params);
    }

    public Result deptDelete(ParamsVo params) {
        params.setClassName("Depttree");
        params.setMethodName("deptDelete");
        return ExecUtil.exec(params);
    }

    public Result getParentTreeById(ParamsVo params) {
        params.setClassName("Depttree");
        params.setMethodName("getParentTreeById");
        return ExecUtil.exec(params);
    }

    public Result depttreeSaveDeptCharge(ParamsVo params) {
        params.setClassName("Depttree");
        params.setMethodName("depttreeSaveDeptCharge");
        return ExecUtil.exec(params);
    }



}