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
 * 资源类实体
 *
 */
@Service
public class ResourceService {


    public Result test(ParamsVo params) {
        params.setClassName("Resource");
        params.setMethodName("test");
        return ExecUtil.exec(params);
    }

    public Result addBill(ParamsVo params) {
        params.setClassName("Resource");
        params.setMethodName("addBill");
        return ExecUtil.exec(params);
    }



}