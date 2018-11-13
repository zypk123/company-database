package com.huntkey.rx.edm.service;
import java.io.Serializable;
import java.util.*;
import com.huntkey.rx.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.Date;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.method.register.plugin.entity.ParamsVo;
import com.huntkey.rx.sceo.method.register.plugin.util.ExecUtil;


/**
 *
 * 岗位类实体
 *
 */
@Service
public class JobpositionService {


    public Result execAuto(ParamsVo params) {
        params.setClassName("Jobposition");
        params.setMethodName("execAuto");
        return ExecUtil.exec(params);
    }

    public Result queryPostsByIds(ParamsVo params) {
        params.setClassName("Jobposition");
        params.setMethodName("queryPostsByIds");
        return ExecUtil.exec(params);
    }

    public Result queryPostsByParPostId(ParamsVo params) {
        params.setClassName("Jobposition");
        params.setMethodName("queryPostsByParPostId");
        return ExecUtil.exec(params);
    }

    public Result execAsync(ParamsVo params) {
        params.setClassName("Jobposition");
        params.setMethodName("execAsync");
        return ExecUtil.exec(params);
    }

    public Result countPostsEmpByDept(ParamsVo params) {
        params.setClassName("Jobposition");
        params.setMethodName("countPostsEmpByDept");
        return ExecUtil.exec(params);
    }

    public Result getPostsTree(ParamsVo params) {
        params.setClassName("Jobposition");
        params.setMethodName("getPostsTree");
        return ExecUtil.exec(params);
    }

    public Result savePost(ParamsVo params) {
        params.setClassName("Jobposition");
        params.setMethodName("savePost");
        return ExecUtil.exec(params);
    }

    public Result testLu(ParamsVo params) {
        params.setClassName("Jobposition");
        params.setMethodName("testLu");
        return ExecUtil.exec(params);
    }

    public Result getPostsByDeptId(ParamsVo params) {
        params.setClassName("Jobposition");
        params.setMethodName("getPostsByDeptId");
        return ExecUtil.exec(params);
    }

    public Result execReverseCall(ParamsVo params) {
        params.setClassName("Jobposition");
        params.setMethodName("execReverseCall");
        return ExecUtil.exec(params);
    }

    public Result execReverseCall1(ParamsVo params) {
        params.setClassName("Jobposition");
        params.setMethodName("execReverseCall1");
        return ExecUtil.exec(params);
    }

    public Result deletePost(ParamsVo params) {
        params.setClassName("Jobposition");
        params.setMethodName("deletePost");
        return ExecUtil.exec(params);
    }

    public Result execReverseCall2(ParamsVo params) {
        params.setClassName("Jobposition");
        params.setMethodName("execReverseCall2");
        return ExecUtil.exec(params);
    }

    public Result queryParPostsEmpByIds(ParamsVo params) {
        params.setClassName("Jobposition");
        params.setMethodName("queryParPostsEmpByIds");
        return ExecUtil.exec(params);
    }

    public Result loadJobPosition(ParamsVo params) {
        params.setClassName("Jobposition");
        params.setMethodName("loadJobPosition");
        return ExecUtil.exec(params);
    }

    public Result loadDpDelete(ParamsVo params) {
        params.setClassName("Jobposition");
        params.setMethodName("loadDpDelete");
        return ExecUtil.exec(params);
    }

    public Result loadJobPositionLuLu(ParamsVo params) {
        params.setClassName("Jobposition");
        params.setMethodName("loadJobPositionLuLu");
        return ExecUtil.exec(params);
    }

    public Result queryPostsEmpByDept(ParamsVo params) {
        params.setClassName("Jobposition");
        params.setMethodName("queryPostsEmpByDept");
        return ExecUtil.exec(params);
    }

    public Result loadJobPositionLu(ParamsVo params) {
        params.setClassName("Jobposition");
        params.setMethodName("loadJobPositionLu");
        return ExecUtil.exec(params);
    }

    public Result testLu2(ParamsVo params) {
        params.setClassName("Jobposition");
        params.setMethodName("testLu2");
        return ExecUtil.exec(params);
    }

    public Result countPostsByDept(ParamsVo params) {
        params.setClassName("Jobposition");
        params.setMethodName("countPostsByDept");
        return ExecUtil.exec(params);
    }

    public Result queryPosts(ParamsVo params) {
        params.setClassName("Jobposition");
        params.setMethodName("queryPosts");
        return ExecUtil.exec(params);
    }



}