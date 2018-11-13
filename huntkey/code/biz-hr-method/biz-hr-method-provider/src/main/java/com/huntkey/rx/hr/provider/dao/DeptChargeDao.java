package com.huntkey.rx.hr.provider.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.hr.common.model.DeptChargerApplyOrdeDTO;
import com.huntkey.rx.sceo.serviceCenter.common.model.MergeParam;
import com.huntkey.rx.sceo.serviceCenter.common.model.SearchParam;

/**
 * Created by weijian on 2017/11/8.
 */
public interface DeptChargeDao {
    /**
     * 加载员工信息
     * @param searchContent
     * @return
     */
    Result loadEmployeeInfo(String searchContent,String deptId);

    /**
     * 插入负责人任免单
     * @param deptChargerApplyOrdeDTO
     * @return
     */
    Result insertDeptchargerapplyorder(DeptChargerApplyOrdeDTO deptChargerApplyOrdeDTO) throws Exception;

    /**
     * 修改负责人任免单
     * @param deptChargerApplyOrdeDTO
     * @return
     */
    Result updateDeptchargerapplyorder(DeptChargerApplyOrdeDTO deptChargerApplyOrdeDTO) throws Exception;

    /**
     * 加载责任人任免单
     * @param id
     * @return
     */
    Result loadDeptChargerApplyOrder(String id,String bmid);

    /**
     * 查询人员是否在本部门就职
     * @param deptId
     * @return
     */
    Result queryDeptEmployees(String deptId) throws Exception;

}
