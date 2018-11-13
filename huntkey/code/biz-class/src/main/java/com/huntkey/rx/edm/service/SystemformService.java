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
 * 系统表单类实体
 *
 */
@Service
public class SystemformService {


    public Result systemFormList(ParamsVo params) {
        params.setClassName("Systemform");
        params.setMethodName("systemFormList");
        return ExecUtil.exec(params);
    }

    public Result getSystemForm(ParamsVo params) {
        params.setClassName("Systemform");
        params.setMethodName("getSystemForm");
        return ExecUtil.exec(params);
    }

    public Result addSystemForm(ParamsVo params) {
        params.setClassName("Systemform");
        params.setMethodName("addSystemForm");
        return ExecUtil.exec(params);
    }

    public Result deleteSystemForm(ParamsVo params) {
        params.setClassName("Systemform");
        params.setMethodName("deleteSystemForm");
        return ExecUtil.exec(params);
    }

    public Result deleteSystemForms(ParamsVo params) {
        params.setClassName("Systemform");
        params.setMethodName("deleteSystemForms");
        return ExecUtil.exec(params);
    }

    public Result updateSystemForm(ParamsVo params) {
        params.setClassName("Systemform");
        params.setMethodName("updateSystemForm");
        return ExecUtil.exec(params);
    }



}