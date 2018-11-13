package com.huntkey.rx.hr.provider.service;


import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.hr.common.model.ApplyPositiveDTO;
import com.huntkey.rx.hr.common.model.EmpPostChangeApplyDTO;

import java.util.List;

/**
 * @author yaoss
 */
public interface PositiveApplyService {

    /**
     * 根据条件 查询 转正申请单
     * @param dept 部门id
     * @param starTime   开始时间
     * @param endTime    结束时间
     * @param no         员工id
     * @param ordeStatus  单据状态
     * @param oepaAuditIdea  转正审核意见
     * @param startPage   开始页
     * @param rows   显示条数
     * @return
     */
    Result queryPositive(String dept, String starTime, String endTime, String no,String ordeStatus,String oepaAuditIdea, int startPage, int rows);


    /**
     * 根据id查询员工信息
     *
     * @param id
     * @return
     */
    Result loadEmpData(String id);

    /**
     * 根据单据id加载单据信息
     *
     * @param id
     * @return
     */
    Result loadPositiveByOrdeId(String id);

    /**
     * 新增单个转正申请单对象
     * @param applyPositiveDTO
     * @return
     */
    Result savePositive(JSONObject applyPositiveDTO) throws Exception;


    /**
     * 员工转正以后，更新员工类转正日期，试用结果等信息。
     * @param orderInstanceId
     * @param handlerType
     * @return
     */
    Result empPositive(String orderInstanceId,String handlerType) throws Exception;

    /**
     * 获取指定部门下的所有部门id
     * @param deptId
     * @return
     */
    List<Object> getAllDept(String deptId);


    /**
     * 提交转正申请单
     * @param applyPositiveDTO
     * @return
     */
    Result submitAddOrder(JSONObject applyPositiveDTO) throws Exception;

    /**
     * 审核转正单据
     *  @param auditSet
     * @author yaoss
     * @return
     */
    Result auditPostivieOrder(JSONObject auditSet) throws Exception;

    /**
     * 定时任务修改转正状态
     * @return
     * @throws Exception
     */
    Result updateEmpPostive() throws Exception;
}