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
 * 职位定义维护单类实体
 *
 */
@Service
public class PostdefinitionorderService {


    public Result loadPostDefinitionOrder(ParamsVo params) {
        params.setClassName("Postdefinitionorder");
        params.setMethodName("loadPostDefinitionOrder");
        return ExecUtil.exec(params);
    }

    public Result positiondefinitionLoad(ParamsVo params) {
        params.setClassName("Postdefinitionorder");
        params.setMethodName("positiondefinitionLoad");
        return ExecUtil.exec(params);
    }

    public Result submitPositionDefinitionOrder(ParamsVo params) {
        params.setClassName("Postdefinitionorder");
        params.setMethodName("submitPositionDefinitionOrder");
        return ExecUtil.exec(params);
    }

    public Result approve(ParamsVo params) {
        params.setClassName("Postdefinitionorder");
        params.setMethodName("approve");
        return ExecUtil.exec(params);
    }

    public Result savePositionDefinitionOrder(ParamsVo params) {
        params.setClassName("Postdefinitionorder");
        params.setMethodName("savePositionDefinitionOrder");
        return ExecUtil.exec(params);
    }



}