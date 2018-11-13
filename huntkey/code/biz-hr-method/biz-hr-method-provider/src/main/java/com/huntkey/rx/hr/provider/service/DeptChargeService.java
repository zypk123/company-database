package com.huntkey.rx.hr.provider.service;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.hr.common.model.DeptChargerApplyOrdeDTO;

/**
 * Created by weijian on 2017/11/8.
 */
public interface DeptChargeService {

    Result loadEmployeeInfo(String searchContent,String deptId);

    Result queryJobPosition(String employeeId,String deptId);

    Result saveDeptCharge(DeptChargerApplyOrdeDTO deptChargerApplyOrdeDTO) throws Exception;

    Result submit(DeptChargerApplyOrdeDTO deptChargerApplyOrdeDTO) throws Exception;

    Result pass(String orderInstanceId,String handlerType);

    Result loadDeptChargerApplyOrder(String id,String bmid);

    Result loadFromOrder(String id);

    Result audit(JSONObject auditSet);

}
