package com.huntkey.rx.hr.provider.service;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.edm.entity.EmpresignprocedureEntity;

/**
 * 离职手续单服务层接口
 * @author Created by --- on 2017/12/5.
 */
public interface EmpresignProcedureService
{

    /**
     * 离职手续单列表查询方法
     * @param deptId 部门对象Id
     * @param type   离职时间类型：申请日期 cretime、预离职日期oera_app_date、批准离职日期oera_appr_date、实际离职日期oera_real_date
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param orderStatus  手续单单据状态：待审2、待批3、待核4、完成5、退回6
     * @param staffInfo    员工信息：姓名/工号
     * @param pageNum    开始页
     * @param pageSize   每页包含的记录数
     * @return
     */
    Result queryDeleteOrderService(String deptId, String type, String startTime, String endTime, String orderStatus, String staffInfo, int pageNum, int pageSize);
    
    JSONObject loadEmpresignProcedureOrder(String orderId);
    
    JSONObject saveEmpresignProcedureOrder(EmpresignprocedureEntity empresign);
    
    JSONObject submitEmpresignProcedureOrder(EmpresignprocedureEntity empresign);
    
    Result pass(String orderInstanceId,String handlerType);
    
    /**
     * 审核离职手续单
     *  @param auditSet
     * @author yaoss
     */
    Result auditPostivieOrder(JSONObject auditSet);
}
