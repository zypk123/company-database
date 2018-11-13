package com.huntkey.rx.hr.provider.service;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.edm.entity.EmpresignapplyEntity;
import com.huntkey.rx.hr.common.model.EmpresignApplyDTO;
import java.text.ParseException;
import java.util.List;

/**
 * 离职申请单类service
 *
 * @author zhangyu
 */
public interface EmpresignApplyService {

    /**
     * 查询离职申请单列表
     *
     * @param deptId    部门
     * @param type        日期类型
     * @param startTime   开始日期
     * @param endTime     结束日期
     * @param auditStatus 审核状态
     * @param staffInfo   员工查询信息：姓名/工号
     * @return
     */
    Result query(String deptId, String type, String startTime, String endTime, String auditStatus, String staffInfo, int startPage, int rows) throws ParseException;

    /**
     * 离职申请单加载详情方法
     *
     * @param id 离职申请单ID
     * @return
     */
    Result load(String id) throws ParseException;

    /**
     * 离职申请单保存方法（含校验）
     *
     * @return
     */
    Result save(JSONObject empresignApplyDTO);

    /**
     * 离职申请单提交方法（含校验）
     *
     * @return
     */
    Result submit(EmpresignApplyDTO empresignApplyDTO);

    /**
     * 离职申请单批准通过
     *
     * @param id
     * @return
     */
    Result pass(String id);

    /**
     * 离职申请单前端审核方法
     * @param jsonObject
     * @return
     */
     Result audit(JSONObject jsonObject);

    /**
     * 查询离职申请单，根据参数判断查询全部还是查询单个对象
     * @param orderIdValue
     * @param auditStatus 审核状态: 临时 1; 待审 2; 待核 3; 待批 4; 完成 5; 退回 6
     * @param startPage
     * @param rows
     * @return
     */
    List<EmpresignApplyDTO> findEmpresignApplyOrderList(String orderIdValue, String auditStatus, int startPage, int rows);

    /**
     * 根据主键查询单个对象
     *
     * @param id
     * @return
     */
    JSONObject findById(String id);

    /**
     * 更新离职单信息
     * @param empresignapplyEntity
     * @return
     */
    Result update(EmpresignapplyEntity empresignapplyEntity);

    /**
     * 单据审核方法
     * @param orderInstanceId
     * @param handlerType
     * @return
     */
    Result approve(String orderInstanceId,String handlerType);
}
