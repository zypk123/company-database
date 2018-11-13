package com.huntkey.rx.hr.provider.dao;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.hr.common.model.EmployeeDTO;
import com.huntkey.rx.hr.common.model.EmployeeEntryApplyDTO;

/**
 * Created by weijian on 2017/11/16.
 */
public interface EmployeeEntryApplyDao {
    JSONObject load(String id) throws Exception;

    Result insertEmployeeEntryApply(EmployeeEntryApplyDTO employeeEntryApplyDTO) throws Exception;

    Result updateEmployeeEntryApply(EmployeeEntryApplyDTO employeeEntryApplyDTO) throws Exception;
}
