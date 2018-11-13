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
 * PPI配置单类实体
 *
 */
@Service
public class PpiassignmentService {


    public Result defOrderPass(ParamsVo params) {
        params.setClassName("Ppiassignment");
        params.setMethodName("defOrderPass");
        return ExecUtil.exec(params);
    }

    public Result getSourceTree(ParamsVo params) {
        params.setClassName("Ppiassignment");
        params.setMethodName("getSourceTree");
        return ExecUtil.exec(params);
    }

    public Result getPpiaDetail(ParamsVo params) {
        params.setClassName("Ppiassignment");
        params.setMethodName("getPpiaDetail");
        return ExecUtil.exec(params);
    }

    public Result patchPPIAssignment(ParamsVo params) {
        params.setClassName("Ppiassignment");
        params.setMethodName("patchPPIAssignment");
        return ExecUtil.exec(params);
    }

    public Result canclePpia(ParamsVo params) {
        params.setClassName("Ppiassignment");
        params.setMethodName("canclePpia");
        return ExecUtil.exec(params);
    }

    public Result getFyr(ParamsVo params) {
        params.setClassName("Ppiassignment");
        params.setMethodName("getFyr");
        return ExecUtil.exec(params);
    }

    public Result submitPPIAssignment(ParamsVo params) {
        params.setClassName("Ppiassignment");
        params.setMethodName("submitPPIAssignment");
        return ExecUtil.exec(params);
    }



}