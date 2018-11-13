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
 * 政府类实体
 *
 */
@Service
public class GovernmentService {


    public Result sadaf(ParamsVo params) {
        params.setClassName("Government");
        params.setMethodName("sadaf");
        return ExecUtil.exec(params);
    }



}