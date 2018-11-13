package com.huntkey.rx.hr.provider.service;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;

/**
 * @author yaoss
 * @date 2017/12/01
 */
public interface EmpPostChangeApplyService {

    /**
     * 根据单据id 加载员工岗位新增单HR12 单据详情
     *
     * @param id 单据id
     * @return
     */
    Result loadAddOrder(String id);

    /**
     * 员工岗位新增单HR12保存
     *
     * @param empPostChangeApplyDTO <p>
     *                              {
     *                              "id":"如果传入就是更新，如果没有id参数，就是新增",
     *                              "oepcDeptBeg": "所属部门id",
     *                              "oepcBeg": "生效时间",
     *                              "oepcRemark": "备注",
     *                              "ordeAdduser": "制单人id",
     *                              "ordeDuty": "制单岗位id",
     *                              "ordeDept": "制单部门id",
     *                              "ordeRodeObj": "单据定义id",
     *                              "oepcChangSet": [
     *                              {
     *                              "oepcDept": "排岗部门id",
     *                              "oepcPost": "排岗岗位id",
     *                              "oepcEmp": "排岗员工id",
     *                              "oepcDtypType": "排岗任职方式"
     *                              }
     *                              ]
     *                              }
     *                              </p>
     * @return
     */
    Result saveAddOrder(JSONObject empPostChangeApplyDTO) throws Exception;

    /**
     * 员工岗位新增单HR12提交方法（含校验）
     *
     * @param empPostChangeApplyDTO 同saveAddOrder
     * @return
     */
    Result submitAddOrder(JSONObject empPostChangeApplyDTO) throws Exception;

    /**
     * 人员排岗单批准通过方法
     *
     * @param id 单据id
     * @return
     */
    Result passAddOrder(String id)throws Exception;

    /**
     * 根据单据id 加载员工岗位调整单HR13 单据详情
     *
     * @param id 单据id
     * @return
     */
    Result loadEditOrder(String id);

    /**
     * 员工岗位新增单HR13保存
     *
     * @param empPostEditApplyDTO <p>
     *                            {
     *                            "id":"如果传入就是更新，如果没有id参数，就是新增",
     *                            "oepcRemark":"备注",
     *                            "oepcBeg":"生效日期",
     *                            "ordeRodeObj":"单据定义id",
     *                            "ordeAdduser":"制单人id",
     *                            "ordeDuty":"制单岗位id",
     *                            "ordeDept":"制单部门id",
     *                            "oepcDeptBeg":"所属部门id",
     *                            "oepcChangSet": [
     *                            {
     *                            "oepcEmp": "调岗员工id",
     *                            "oepcPost": "调整新岗位id",
     *                            "oepcDept": "调整新部门id",
     *                            "oepcDeptOld":"旧部门id",
     *                            "oepcPostOld":"旧岗位id",
     *                            "oepcIskeep":"是否保留原岗位 0 不保留 1 保留",
     *                            "oepcDtypType":"新岗位任职方式",
     *                            "oepcDtypOld":"旧岗位任职方式",
     *                            "oepcChgrType":"负责人类型 主管人/协管人 0/1"
     *                            }
     *                            ]
     *                            }
     *                            </p>
     * @return
     */
    Result saveEditOrder(JSONObject empPostEditApplyDTO)throws Exception;

    /**
     * 员工岗位新增单HR13提交方法
     *
     * @param empPostEditApplyDTO 同saveEditOrder
     * @return
     */
    Result submitEditOrder(JSONObject empPostEditApplyDTO)throws Exception;

    /**
     * 人员调岗单批准通过方法
     *
     * @param id
     * @return
     */
    Result passEditOrder(String id)throws Exception;

    /**
     * 根据单据id，员工岗位调整单批准通过
     *
     * @param orderInstanceId 单据id
     * @param handlerType     流程处理类别
     * @return
     */
    Result passOrderById(String orderInstanceId,
                         String handlerType)throws Exception;

    /**
     * 人员离岗保存方法
     *
     * @param empPostChangeApplyDTO{ "id":"如果传入就是更新，如果没有id参数，就是新增",
     *                               "oepcRemark":"备注",
     *                               "oepcBeg":"生效日期",
     *                               "ordeRodeObj":"单据定义id",
     *                               "ordeAdduser":"制单人id",
     *                               "ordeDuty":"制单岗位id",
     *                               "ordeDept":"制单部门id",
     *                               "oepcDeptBeg":"所属部门id",
     *                               "oepcChangSet": [
     *                               {
     *                               "oepcDept": "部门id",
     *                               "oepcPost": "岗位id",
     *                               "oepcEmp": "离岗员工id",
     *                               }
     *                               ]
     *                               }
     * @return
     */
    Result saveDeleteOrder(JSONObject empPostChangeApplyDTO)throws Exception;

    /**
     * 人员离岗单加载详情方法
     *
     * @param id
     * @return
     */
    Result loadDeleteOrder(String id);

    /**
     * 人员离岗单提交方法（含校验）
     *
     * @param empPostChangeApplyDTO
     * @return
     */
    Result submitDeleteOrder(JSONObject empPostChangeApplyDTO)throws Exception;

    /**
     * 人员离岗单批准通过方法
     *
     * @param id
     * @return
     */
    Result passDeleteOrder(String id)throws Exception;

    /**
     * 审核单据
     *
     * @param auditSet
     * @return
     */
    Result auditOrder(JSONObject auditSet)throws Exception;
}
