package com.huntkey.rx.hr.provider.service;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.hr.common.model.EmployeeEntryApplyDTO;
import org.springframework.web.multipart.MultipartFile;

/**
 * 入职单接口
 */
public interface EmployeeEntryApplyService {
    //职员入职单加载
    Result load(String id);

    //职员入职单保存
    Result saveAddOrder(EmployeeEntryApplyDTO employeeEntryApplyDTO) throws Exception;

    //职员入职单提交
    Result submitAddOrder(EmployeeEntryApplyDTO employeeEntryApplyDTO) throws Exception;

    //职员入职单通过
    Result passAddOrder(String orderInstanceId, String handlerType);

    //根据员工工号加载档案变更单
    Result loadEditOrder(String employeeId, String oeeoCode) throws Exception;

    //职员入职单变更保存
    Result saveEditOrder(EmployeeEntryApplyDTO employeeEntryApplyDTO) throws Exception;

    //档案变更单据提交方法(submitEditOrder)
    Result submitEditOrder(EmployeeEntryApplyDTO employeeEntryApplyDTO) throws Exception;

    String uploadImage(MultipartFile upLoadFile);

    Result audit(JSONObject auditSet);
}
