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
 * 园区类实体
 *
 */
@Service
public class ParkService {


    public Result getAll(ParamsVo params) {
        params.setClassName("Park");
        params.setMethodName("getAll");
        return ExecUtil.exec(params);
    }



}