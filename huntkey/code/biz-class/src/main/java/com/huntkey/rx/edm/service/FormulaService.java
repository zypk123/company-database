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
 * 公式类实体
 *
 */
@Service
public class FormulaService {


    public Result relCondConfDataByPrplIdForClass(ParamsVo params) {
        params.setClassName("Formula");
        params.setMethodName("relCondConfDataByPrplIdForClass");
        return ExecUtil.exec(params);
    }

    public Result getObjectRelCondConfDataByPro(ParamsVo params) {
        params.setClassName("Formula");
        params.setMethodName("getObjectRelCondConfDataByPro");
        return ExecUtil.exec(params);
    }

    public Result auditRoles(ParamsVo params) {
        params.setClassName("Formula");
        params.setMethodName("auditRoles");
        return ExecUtil.exec(params);
    }



}