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
 * 部门责任人任免单类实体
 *
 */
@Service
public class DeptchargerapplyorderService {


    public Result deptChaAppOrdSaveDeptCharge(ParamsVo params) {
        params.setClassName("Deptchargerapplyorder");
        params.setMethodName("deptChaAppOrdSaveDeptCharge");
        return ExecUtil.exec(params);
    }

    public Result deptChaAppOrdLoadEmployeeInfo(ParamsVo params) {
        params.setClassName("Deptchargerapplyorder");
        params.setMethodName("deptChaAppOrdLoadEmployeeInfo");
        return ExecUtil.exec(params);
    }

    public Result loadDeptChargerApplyOrder(ParamsVo params) {
        params.setClassName("Deptchargerapplyorder");
        params.setMethodName("loadDeptChargerApplyOrder");
        return ExecUtil.exec(params);
    }

    public Result deptChaAppOrdPass(ParamsVo params) {
        params.setClassName("Deptchargerapplyorder");
        params.setMethodName("deptChaAppOrdPass");
        return ExecUtil.exec(params);
    }

    public Result deptChaAppOrdSubmit(ParamsVo params) {
        params.setClassName("Deptchargerapplyorder");
        params.setMethodName("deptChaAppOrdSubmit");
        return ExecUtil.exec(params);
    }

    public Result deptChaAppOrdQueryJobPosition(ParamsVo params) {
        params.setClassName("Deptchargerapplyorder");
        params.setMethodName("deptChaAppOrdQueryJobPosition");
        return ExecUtil.exec(params);
    }



}