package com.huntkey.rx.hr.provider.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.edm.service.OrderRegService;
import com.huntkey.rx.edm.service.PeopleRegService;
import com.huntkey.rx.hr.common.exception.ApplicationException;
import com.huntkey.rx.hr.common.model.CurrentSessionEntity;
import com.huntkey.rx.sceo.common.entity.CurrentStatus;
import com.huntkey.rx.sceo.method.register.plugin.entity.ParamsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Component
public class BizFormService {
    private static Logger logger = LoggerFactory.getLogger(BizFormService.class);
    public static final String SUBMIT_METHOD_ARG_DEF = "orderDefId";
    public static final String SUBMIT_METHOD_ARG_INST = "orderInstanceId";
    public static final String AUDIT_METHOD_ARG_ACT_INSTANCE_ID = "actInstanceId";
    public static final String AUDIT_METHOD_ARG_OPINION = "opinion";
    public static final String AUDIT_METHOD_ARG_AUDIT_KEY = "auditKey";

    @Autowired
    private OrderRegService orderRegService;
    @Autowired
    private PeopleRegService peopleRegService;

    /**
     * 获取当前登录的用户对应的员工和岗位信息
     */
    public CurrentSessionEntity getCurrentSessionInfo() {
        CurrentSessionEntity sessionEntity = new CurrentSessionEntity();
        ParamsVo paramsVo = new ParamsVo();
        Result sessionResult = peopleRegService.getEcosystemSession(paramsVo);

        if (!Result.RECODE_SUCCESS.equals(sessionResult.getRetCode())) {
            throw new ApplicationException(Result.RECODE_ERROR, "获取用户Session信息出错，错误信息" + sessionResult.getErrMsg());
        }
        if (sessionResult.getData() == null) {
            throw new ApplicationException(Result.RECODE_ERROR, "获取用户Session信息出错，session数据为空");
        }
        JSONObject resultObj = JSON.parseObject(JSONObject.toJSONString(sessionResult.getData()));
        JSONObject currentStatus = resultObj.getJSONObject("currentStatus");
        if (currentStatus == null) {
            throw new ApplicationException(Result.RECODE_ERROR, "获取用户Session信息出错，当前登录信息为空");
        }
        CurrentStatus currentStatusObj = JSONObject.parseObject(currentStatus.toJSONString(),new TypeReference<CurrentStatus>() {});

        //取当前登录用户的员工信息
        if (currentStatusObj.getCurrentStaff() == null) {
            throw new ApplicationException(Result.RECODE_ERROR, "获取用户Session信息出错，当前登录员工信息为空");
        }
        String employeeId = currentStatusObj.getCurrentStaff().getId();
        if (employeeId == null) {
            throw new ApplicationException(Result.RECODE_ERROR, "获取用户Session信息出错，当前登录员工ID为空");
        }
        sessionEntity.setEmployeeId(employeeId);
        //取当前登录用户的岗位信息
        if (currentStatusObj.getCurrentPosition() == null) {
            throw new ApplicationException(Result.RECODE_ERROR, "获取用户Session信息出错，当前登录岗位信息为空");
        }
        String positionId = currentStatusObj.getCurrentPosition().getId();
        if (positionId == null) {
            throw new ApplicationException(Result.RECODE_ERROR, "获取用户Session信息出错，当前登录岗位ID为空");
        }
        sessionEntity.setPositionId(positionId);
        //取当前登录用户的企业信息
        if (currentStatusObj.getCurrentEnterprise() == null) {
            throw new ApplicationException(Result.RECODE_ERROR, "获取用户Session信息出错，当前登录企业信息为空");
        }
        String enterpriseId = currentStatusObj.getCurrentEnterprise().getId();
        if (enterpriseId == null) {
            throw new ApplicationException(Result.RECODE_ERROR, "获取用户Session信息出错，当前登录企业ID为空");
        }
        sessionEntity.setEnterpriseId(enterpriseId);

        return sessionEntity;
    }

    /**
     * 单据提交流程接口，会启动一个流程实例
     * @param orderDefId
     * @param orderInstanceId
     */
    public void submitWorkFlow(String orderDefId, String orderInstanceId) {
        ParamsVo params = new ParamsVo();
        Map<String, Object> map = new HashMap(2);
        map.put(SUBMIT_METHOD_ARG_DEF, orderDefId);
        map.put(SUBMIT_METHOD_ARG_INST, orderInstanceId);
        params.setParamObj(map);
        Result submitResult = orderRegService.submit(params);
        if (!Result.RECODE_SUCCESS.equals(submitResult.getRetCode())) {
            logger.error("提交流程失败，单据定义ID: {},单据实例ID:{}", orderDefId, orderInstanceId);
            throw new ApplicationException(Result.RECODE_ERROR, submitResult.getErrMsg());
        }
    }

    /**
     * 单据审批接口
     * @param actInstanceId 流程实例ID
     * @param opinion 审批意见
     * @param auditKey 审批类型
     */
    public void audit(String actInstanceId,String opinion,String auditKey){
        ParamsVo params = new ParamsVo();
        Map<String, Object> map = new HashMap(2);
        map.put(AUDIT_METHOD_ARG_ACT_INSTANCE_ID, actInstanceId);
        map.put(AUDIT_METHOD_ARG_OPINION, opinion);
        map.put(AUDIT_METHOD_ARG_AUDIT_KEY, auditKey);
        params.setParamObj(map);
        Result auditResult = orderRegService.audit(params);
        if (!Result.RECODE_SUCCESS.equals(auditResult.getRetCode())) {
            logger.error("审批失败，流程实例ID:{}，审批意见：{}，审批关键字：{}", actInstanceId,opinion,auditKey);
            throw new ApplicationException(Result.RECODE_ERROR, auditResult.getErrMsg());
        }
    }

}
