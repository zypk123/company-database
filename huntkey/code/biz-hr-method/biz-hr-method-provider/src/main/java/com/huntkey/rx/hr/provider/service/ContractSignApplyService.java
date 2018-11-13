package com.huntkey.rx.hr.provider.service;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.hr.common.exception.ApplicationException;
import com.huntkey.rx.hr.common.model.ContractsignInfoDTO;


import java.util.List;

/**
 * 合同签订单类Service
 *
 * @author zhangyu
 * @create 2017-11-13 17:39
 **/
public interface ContractSignApplyService {

    /**
     * 合同签订单批准通过方法
     *
     * @return
     */
    Result passContractSignOrder(String orderInstanceId,String handlerType);

    /**
     * 人员合同单查询列表方法(带分页)
     * @param deptId        起始部门ID
     * @param legalPerson   合同法人
     * @param contractStart 合同结束日期起
     * @param contractEnd   合同结束日期至
     * @param contractState 合同状态
     * @param employeeType  员工类型
     * @param officParks    办公园区
     * @param entryStart    入职时间起
     * @param entryEnd      入职时间至
     * @param staffIdAndName 姓名工号
     * @param startPage
     * @param rows
     * @return
     */
    Result query(String deptId, String legalPerson, String contractStart,
                 String contractEnd, String contractState, String employeeType,
                 String officParks, String entryStart, String entryEnd,
                 String staffIdAndName, int startPage, int rows) throws Exception;


    /**
     * 合同签订单加载详情方法
     *
     * @param id 合同签订单id
     * @return
     * @throws ApplicationException
     */
    Result load(String id) throws Exception;

    /**
     * 合同签订单保存方法（含校验）
     *
     * @param contractsignInfoDTO
     * @return
     * @throws ApplicationException
     */
    Result save(ContractsignInfoDTO contractsignInfoDTO) throws Exception;

    /**
     * 合同签订单提交方法（含校验）
     *
     * @param contractsignInfoDTO
     * @return
     * @throws ApplicationException
     */
    Result submit(ContractsignInfoDTO contractsignInfoDTO) throws Exception;

    /**
     * 查询该部门下面所有子部门的id
     * @param deptId 部门id
     * @return
     */
    List<String> getChildDept(String deptId) throws Exception;

    /**
     * 获取员工签约明细内容
     * @param deptName      部门名称
     * @param signType      签约类型[1 新签 2续签 3解约]
     * @param edmployeeIds  选中的员工id
     * @param startPage     当前页
     * @param rows          每页记录数
     * @return
     */
    Result sign(String deptName, String signType, String edmployeeIds,int startPage,int rows) throws Exception;

    /**
     * 合同签订单审核意见
     *  @param auditSet
     * @author
     */
    Result audit(JSONObject auditSet);

}
