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
 * 区域类实体
 *
 */
@Service
public class AreaService {


    public Result getCityByProvince(ParamsVo params) {
        params.setClassName("Area");
        params.setMethodName("getCityByProvince");
        return ExecUtil.exec(params);
    }

    public Result getProvinces(ParamsVo params) {
        params.setClassName("Area");
        params.setMethodName("getProvinces");
        return ExecUtil.exec(params);
    }



}