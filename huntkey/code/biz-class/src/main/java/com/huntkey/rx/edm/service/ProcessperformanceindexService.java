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
 * 绩效指标类实体
 *
 */
@Service
public class ProcessperformanceindexService {


    public Result indexCalculation(ParamsVo params) {
        params.setClassName("Processperformanceindex");
        params.setMethodName("indexCalculation");
        return ExecUtil.exec(params);
    }



}