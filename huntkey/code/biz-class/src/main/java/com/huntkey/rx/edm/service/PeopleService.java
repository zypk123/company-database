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
 * 自然人类实体
 *
 */
@Service
public class PeopleService {


    public Result queryPeopleInfo(ParamsVo params) {
        params.setClassName("People");
        params.setMethodName("queryPeopleInfo");
        return ExecUtil.exec(params);
    }

    public Result addUserInfo(ParamsVo params) {
        params.setClassName("People");
        params.setMethodName("addUserInfo");
        return ExecUtil.exec(params);
    }

    public Result MyTest888(ParamsVo params) {
        params.setClassName("People");
        params.setMethodName("MyTest888");
        return ExecUtil.exec(params);
    }

    public Result dajiang140(ParamsVo params) {
        params.setClassName("People");
        params.setMethodName("dajiang140");
        return ExecUtil.exec(params);
    }

    public Result Search(ParamsVo params) {
        params.setClassName("People");
        params.setMethodName("Search");
        return ExecUtil.exec(params);
    }

    public Result queryEnterpriseList(ParamsVo params) {
        params.setClassName("People");
        params.setMethodName("queryEnterpriseList");
        return ExecUtil.exec(params);
    }

    public Result queryUserInfo(ParamsVo params) {
        params.setClassName("People");
        params.setMethodName("queryUserInfo");
        return ExecUtil.exec(params);
    }

    public Result updatePeopleInfo(ParamsVo params) {
        params.setClassName("People");
        params.setMethodName("updatePeopleInfo");
        return ExecUtil.exec(params);
    }

    public Result isVerificatCorrect(ParamsVo params) {
        params.setClassName("People");
        params.setMethodName("isVerificatCorrect");
        return ExecUtil.exec(params);
    }

    public Result addPeople(ParamsVo params) {
        params.setClassName("People");
        params.setMethodName("addPeople");
        return ExecUtil.exec(params);
    }

    public Result MyTest(ParamsVo params) {
        params.setClassName("People");
        params.setMethodName("MyTest");
        return ExecUtil.exec(params);
    }

    public Result queryPeopleInfoByIdCard(ParamsVo params) {
        params.setClassName("People");
        params.setMethodName("queryPeopleInfoByIdCard");
        return ExecUtil.exec(params);
    }

    public Result getEcosystemSession(ParamsVo params) {
        params.setClassName("People");
        params.setMethodName("getEcosystemSession");
        return ExecUtil.exec(params);
    }

    public Result getVerificatCode(ParamsVo params) {
        params.setClassName("People");
        params.setMethodName("getVerificatCode");
        return ExecUtil.exec(params);
    }

    public Result addPeopleEnteSet(ParamsVo params) {
        params.setClassName("People");
        params.setMethodName("addPeopleEnteSet");
        return ExecUtil.exec(params);
    }

    public Result test_time(ParamsVo params) {
        params.setClassName("People");
        params.setMethodName("test_time");
        return ExecUtil.exec(params);
    }

    public Result uploadImg(ParamsVo params) {
        params.setClassName("People");
        params.setMethodName("uploadImg");
        return ExecUtil.exec(params);
    }

    public Result login(ParamsVo params) {
        params.setClassName("People");
        params.setMethodName("login");
        return ExecUtil.exec(params);
    }

    public Result ASyncMethod9999(ParamsVo params) {
        params.setClassName("People");
        params.setMethodName("ASyncMethod9999");
        return ExecUtil.exec(params);
    }

    public Result updateCurrentJobInfo(ParamsVo params) {
        params.setClassName("People");
        params.setMethodName("updateCurrentJobInfo");
        return ExecUtil.exec(params);
    }

    public Result swichJob(ParamsVo params) {
        params.setClassName("People");
        params.setMethodName("swichJob");
        return ExecUtil.exec(params);
    }

    public Result getCurrentJobInfo(ParamsVo params) {
        params.setClassName("People");
        params.setMethodName("getCurrentJobInfo");
        return ExecUtil.exec(params);
    }

    public Result Users(ParamsVo params) {
        params.setClassName("People");
        params.setMethodName("Users");
        return ExecUtil.exec(params);
    }

    public Result queryEnterprise(ParamsVo params) {
        params.setClassName("People");
        params.setMethodName("queryEnterprise");
        return ExecUtil.exec(params);
    }

    public Result MyTest345(ParamsVo params) {
        params.setClassName("People");
        params.setMethodName("MyTest345");
        return ExecUtil.exec(params);
    }

    public Result clearCurrentStatus(ParamsVo params) {
        params.setClassName("People");
        params.setMethodName("clearCurrentStatus");
        return ExecUtil.exec(params);
    }



}