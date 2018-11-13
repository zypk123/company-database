package com.huntkey.rx.edm.service;
import java.io.Serializable;
import java.util.*;
import com.huntkey.rx.base.BaseService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.method.register.plugin.entity.ParamsVo;
import com.huntkey.rx.sceo.method.register.plugin.util.ExecUtil;


/**
 *
 * 指标定义类实体
 *
 */
@Service
public class PpiformulaService {


    public Result queryPpiDefintionedPage(ParamsVo params) {
        params.setClassName("Ppiformula");
        params.setMethodName("queryPpiDefintionedPage");
        return ExecUtil.exec(params);
    }

    public Result queryPpiDefintioned(ParamsVo params) {
        params.setClassName("Ppiformula");
        params.setMethodName("queryPpiDefintioned");
        return ExecUtil.exec(params);
    }

    public Result queryPpiDefintionedDetail(ParamsVo params) {
        params.setClassName("Ppiformula");
        params.setMethodName("queryPpiDefintionedDetail");
        return ExecUtil.exec(params);
    }



}