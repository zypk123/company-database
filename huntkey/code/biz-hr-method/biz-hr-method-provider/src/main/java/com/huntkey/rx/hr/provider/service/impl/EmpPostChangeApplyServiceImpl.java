package com.huntkey.rx.hr.provider.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.codingapi.tx.annotation.TxTransaction;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.edm.constant.EdmProperty;
import com.huntkey.rx.edm.constant.EmppostchangeapplyProperty;
import com.huntkey.rx.edm.constant.OrderProperty;
import com.huntkey.rx.edm.constant.RempRempPostSetaProperty;
import com.huntkey.rx.edm.entity.*;
import com.huntkey.rx.hr.common.constants.*;
import com.huntkey.rx.hr.common.exception.ApplicationException;
import com.huntkey.rx.hr.common.model.*;
import com.huntkey.rx.hr.common.util.DateDiff;
import com.huntkey.rx.hr.common.util.JsonUtils;
import com.huntkey.rx.hr.common.util.NullUtils;
import com.huntkey.rx.hr.provider.service.BaseService;
import com.huntkey.rx.hr.provider.service.BizFormService;
import com.huntkey.rx.hr.provider.service.EmpPostChangeApplyService;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;
import com.huntkey.rx.sceo.orm.common.type.SQLCurdEnum;
import com.huntkey.rx.sceo.orm.common.type.SQLSortEnum;
import com.huntkey.rx.sceo.orm.common.util.EdmUtil;
import com.huntkey.rx.sceo.orm.service.OrmService;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author yaoss
 */
@Service
public class EmpPostChangeApplyServiceImpl extends BaseService implements EmpPostChangeApplyService {

    @Autowired
    OrmService ormService;

    @Autowired
    BizFormService bizFormService;

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Result saveAddOrder(JSONObject empPostChangeApplyDTO) throws Exception {
        Result result = new Result();
        String json = empPostChangeApplyDTO.toJSONString();
        Object params = JSONObject.parse(json);
        // 驼峰转下划线
        JsonUtils.camel2UnderLine(params);
        // json转实体类
        EmppostchangeapplyEntity entity = JSONObject.parseObject(JSONObject.toJSONString(params), EmppostchangeapplyEntity.class);

        String errMsg = orderAddSessionInfo(entity);
        if (!StringUtil.isNullOrEmpty(errMsg)) {
            result.setData(null);
            result.setErrMsg(errMsg);
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            return result;
        }

        List<OepcOepcChangSetaEntity> list = entity.getOepc_chang_set();
        if (null != list && list.size() > 1) {
            for (int i = 0; i < list.size(); i++) {
                OepcOepcChangSetaEntity setaEntity = list.get(i);
                for (int j = 0; j < list.size(); j++) {
                    if (i == j) {
                        continue;
                    } else {
                        OepcOepcChangSetaEntity setaEntity2 = list.get(j);
                        if (setaEntity.getOepc_emp().equals(setaEntity2.getOepc_emp())) {
                            if (setaEntity.getOepc_dtyp_type().equals("0") && setaEntity2.getOepc_dtyp_type().equals("0")) {
                                result.setData(null);
                                String empName = loadEditOrderLoadEmpName(setaEntity.getOepc_emp());
                                result.setErrMsg(empName + "重复提交任职方式的岗位，保存失败！");
                                result.setRetCode(Result.RECODE_VALIDATE_ERROR);
                                return result;
                            }
                        }
                    }
                }
            }
        }

        entity.setOepc_type(EmpPostChangeApplyConstant.ADD_ORDER);
        JSONObject returnData = saveAddOrEditOrder(entity, NumberConstants.PREFIX_EMP_POST_CHANGE_APPLY_ADD);
        result.setData(returnData);
        result.setErrMsg("保存或更新人员排岗单信息成功");
        result.setRetCode(Result.RECODE_SUCCESS);

        return result;
    }

    private String orderAddSessionInfo(EmppostchangeapplyEntity entity) throws Exception {
        // 单据定义类型id
        String ordeRodeObj = entity.getOrde_rode_obj();
        // 制单人
        String ordeAddUser = entity.getOrde_adduser();
        // 制单岗位
        String ordeDuty = entity.getOrde_duty();
        // 制单部门
        String ordeDept = entity.getOrde_dept();

        if (StringUtil.isNullOrEmpty(ordeRodeObj)) {
            return "必须传递参数ordeRodeObj的值作为单据类型定义id保存";
        }
        if (StringUtil.isNullOrEmpty(ordeAddUser)) {
            return "必须传递参数ordeAdduser的值作为制单人id保存";
        }
        if (StringUtil.isNullOrEmpty(ordeDuty)) {
            return "必须传递参数ordeDuty的值作为制单岗位id保存";
        }
        if (StringUtil.isNullOrEmpty(ordeDept)) {
            return "必须传递参数ordeDept的值作为制单部门id保存";
        }
        //根据session中的当前员工和岗位赋值单据的制单人和制单岗位
        CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
        entity.setOrde_adduser(ordeAddUser);
        entity.setOrde_duty(ordeDuty);
        entity.setOrde_dept(ordeDept);
        entity.setEdmd_ente(sessionEntity.getEnterpriseId());
        if (StringUtil.isNullOrEmpty(entity.getId())) {
            entity.setOrde_date(new Date());
            entity.setCretime(entity.getOrde_date());
            entity.setCreuser(sessionEntity.getEmployeeId());
        } else {
            entity.setModtime(new Date());
            entity.setOrde_date(entity.getModtime());
            entity.setModuser(sessionEntity.getEmployeeId());
        }
        return "";
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public Result loadAddOrder(String id) {
        Result result = new Result();
        try {
            // 待返回的数据
            JSONObject returnData = new JSONObject();
            JSONObject baseInfoData = loadOrderBaseInfo(id);
            if (null == baseInfoData) {
                return catchNoResult("未找到单据'" + id + "'的信息");
            }
            returnData.putAll(baseInfoData);
            // 获取属性集oepcChangSet
            List<Map<String, Object>> queryListSet = getAddOrderOepcChangSetMaps(id);
            // 属性集待返回数据
            JSONArray oepcChangSetData = new JSONArray();
            if (null != queryListSet && queryListSet.size() > 0) {
                for (Map<String, Object> mapSet : queryListSet) {
                    JSONObject temp = new JSONObject();
                    // loadEmployee信息
                    // 员工基本信息，公司信息，教育信息
                    temp.putAll(loadAddOrderLoadEmployee(NullUtils.valueOf(mapSet.get(EmpPostChangeApplyConstant.OEPC_EMP))));
                    // load岗位信息
                    JSONObject jobObj = loadAddOrderLoadJob(NullUtils.valueOf(mapSet.get(EmpPostChangeApplyConstant.OEPC_POST)));
                    // 汇报岗位任职人id
                    Object pPostEmpId = jobObj.get(JobPositionConstants.RPOS_PPOST);
                    if (null != pPostEmpId) {
                        String empName = loadEditOrderLoadEmpName(pPostEmpId.toString());
                        jobObj.put(EmployeeConstants.UP_PERSON, empName);
                    } else {
                        jobObj.put(EmployeeConstants.UP_PERSON, "");
                    }
                    temp.putAll(jobObj);
                    // 属性集子元素ID
                    temp.put(EmpPostChangeApplyConstant.ID, NullUtils.valueOf(mapSet.get(EmpPostChangeApplyConstant.ID)));
                    // 单据ID
                    temp.put(EmpPostChangeApplyConstant.PID, NullUtils.valueOf(mapSet.get(EmpPostChangeApplyConstant.PID)));
                    // 部门Id
                    temp.put(EmpPostChangeApplyConstant.OEPC_DEPT, NullUtils.valueOf(mapSet.get(EmpPostChangeApplyConstant.OEPC_DEPT)));
                    // 部门名字
                    temp.put(DeptTreeConstants.MDEP_NAME, loadEditOrderLoadDeptName(NullUtils.valueOf(mapSet.get(EmpPostChangeApplyConstant.OEPC_DEPT))));
                    // 办公园区
                    temp.putAll(loadAddOrderPark(NullUtils.valueOf(mapSet.get(EmpPostChangeApplyConstant.OEPC_DEPT))));
                    // 岗位ID
                    temp.put(EmpPostChangeApplyConstant.OEPC_POST, NullUtils.valueOf(mapSet.get(EmpPostChangeApplyConstant.OEPC_POST)));
                    // 任职方式
                    temp.put(EmpPostChangeApplyConstant.OEPC_DTYP_TYPE, NullUtils.valueOf(mapSet.get(EmpPostChangeApplyConstant.OEPC_DTYP_TYPE)));
                    // 员工ID
                    temp.put(EmpPostChangeApplyConstant.OEPC_EMP, NullUtils.valueOf(mapSet.get(EmpPostChangeApplyConstant.OEPC_EMP)));
                    oepcChangSetData.add(temp);
                }
            }
            returnData.put(EmpPostChangeApplyConstant.OEPC_CHANG_SET, oepcChangSetData);
            // 转驼峰
            JsonUtils.underLine2Camel(returnData);
            result.setData(returnData);
        } catch (Exception e) {
            return catchException(e);
        }

        return result;
    }

    @Override
    public Result submitAddOrder(JSONObject empPostChangeApplyDTO) throws Exception {
        // 保存
        Result saveResult = saveAddOrder(empPostChangeApplyDTO);
        if (saveResult.getRetCode().equals(Result.RECODE_SUCCESS)) {
            JSONObject saveData = (JSONObject) saveResult.getData();
            String orderId = saveData.getString(NodeConstant.ID);
            // 校验
            Result validResult = validAddOrder(orderId);
            if (validResult.getRetCode().equals(Result.RECODE_VALIDATE_ERROR)) {
                validResult.setErrMsg(validResult.getErrMsg() + ",当前临时单提交失败！");
                return validResult;
            }
        } else {
            return catchNoResult(saveResult.getErrMsg());
        }
        // 提交流程
        return submitUpdateStatus(saveResult);
    }

    @TxTransaction(rollbackFor = Exception.class)
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Result submitUpdateStatus(Result saveResult) throws Exception {
        Result result = new Result();
        if (saveResult.getRetCode().equals(Result.RECODE_SUCCESS)) {
            // 参见saveAddOrder中的returnData，所以强制类型转换
            JSONObject saveData = (JSONObject) saveResult.getData();
            String orderId = saveData.getString(NodeConstant.ID);
            String orderNbr = saveData.getString(EmpPostChangeApplyConstant.ORDE_NBR);

            // 提交流程
            String orderDefId = saveData.getString(OrderConstants.ORDE_RODE_OBJ);
            bizFormService.submitWorkFlow(orderDefId, orderId);

            // 更新状态
            EmppostchangeapplyEntity updateEntity = new EmppostchangeapplyEntity();
            updateEntity.setOrde_status(OrderConstants.ORDE_STATUS_2);
            updateEntity.setOrde_date(new Date());
            updateEntity.setId(orderId);
            ormService.updateSelective(updateEntity);

            // 待返回数据
            JSONObject returnData = new JSONObject();
            // 插入返回的数据
            returnData.put(NodeConstant.ID, orderId);
            returnData.put(EmpPostChangeApplyConstant.ORDE_NBR, orderNbr);
            result.setData(returnData);
            result.setRetCode(Result.RECODE_SUCCESS);
            result.setErrMsg("提交单据成功！");

        } else {
            result.setData(null);
            result.setErrMsg(saveResult.getErrMsg());
            result.setRetCode(Result.RECODE_SUCCESS);
        }
        return result;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Result passAddOrder(String id) throws Exception {
        Result result = new Result();

        // SELECT oepc_dept , oepc_post , oepc_dtyp_type , oepc_emp , pid , id FROM oepc_oepc_chang_seta WHERE pid = ? AND is_del = 0
        List<Map<String, Object>> queryListSet = getAddOrderOepcChangSetMaps(id);
        if (null != queryListSet && queryListSet.size() > 0) {
            Date begDate = new Date();
            for (Map<String, Object> mapSet : queryListSet) {
                //查询岗位类岗级和汇报岗位的岗位id
                String oepcPost = NullUtils.valueOf(mapSet.get(EmpPostChangeApplyConstant.OEPC_POST));
                String oepcDept = NullUtils.valueOf(mapSet.get(EmpPostChangeApplyConstant.OEPC_DEPT));
                JSONObject jo = getPgradeAndPpostId(oepcPost);
                //员工类.任岗经历.岗级
                String rposGrade = jo.getString(JobPositionConstants.RPOS_GRADE);
                //员工类.任岗经历.汇报上级
                String rposEmp = jo.getString(JobPositionConstants.RPOS_EMP);
                // 任职方式
                String dutyType = NullUtils.valueOf(mapSet.get(EmpPostChangeApplyConstant.OEPC_DTYP_TYPE));

                //更新岗位类.任职人=记录任职人，岗位类.任职方式=记录任职方式
                JobpositionEntity entity = new JobpositionEntity();
                entity.setRpos_emp(NullUtils.valueOf(mapSet.get(EmpPostChangeApplyConstant.OEPC_EMP)));
                entity.setRpos_duty_type(dutyType);
                entity.setId(oepcPost);
                // UPDATE jobposition SET rpos_emp = ? , rpos_duty_type = ? , modtime = ? WHERE id = ?
                ormService.updateSelective(entity);
                //员工类.任岗经历新增记录，生效日期，岗位，上级，岗级，任职方式
                RempRempPostSetaEntity postSetaEntity = new RempRempPostSetaEntity();
                postSetaEntity.setPid(NullUtils.valueOf(mapSet.get(EmpPostChangeApplyConstant.OEPC_EMP)));
                postSetaEntity.setRemp_post_beg(begDate);
                postSetaEntity.setRemp_post_his(oepcPost);
                postSetaEntity.setRemp_pemp_his(rposEmp);
                postSetaEntity.setRemp_pgrad_his(rposGrade);
                postSetaEntity.setRemp_dtyp_his(dutyType);
                postSetaEntity.setClassName(EmployeeConstants.EDM_EMPLOYEE);

                CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
                postSetaEntity.setCreuser(sessionEntity.getEmployeeId());
                postSetaEntity.setCretime(new Date());

                //INSERT INTO remp_remp_post_seta ( remp_post_his , remp_pgrad_his , remp_pemp_his , remp_post_beg , pid , id , cretime , modtime ) VALUES ( ? , ? , ? , ? , ? , ? , ? , ? )
                ormService.insertSelective(postSetaEntity);
                //判断任职方式为任职时，更新员工类.岗位,部门，岗级，法人
                if (EmpPostChangeApplyConstant.DUTY_TYPE_0.equals(dutyType)) {
                    EmployeeEntity employeeEntity = new EmployeeEntity();
                    employeeEntity.setRemp_post(oepcPost);
                    employeeEntity.setRemp_pgrade(rposGrade);
                    employeeEntity.setRemp_dept(oepcDept);
                    employeeEntity.setRemp_mcop(loadEditOrderLoadDeptMCop(oepcDept));
                    employeeEntity.setId(NullUtils.valueOf(mapSet.get(EmpPostChangeApplyConstant.OEPC_EMP)));
                    ormService.updateSelective(employeeEntity);
                }
            }
            // 更新单据的生效日期
            EmppostchangeapplyEntity entity = new EmppostchangeapplyEntity();
            entity.setId(id);
            entity.setOepc_beg(begDate);
            entity.setOrde_status(OrderConstants.ORDE_STATUS_5);
            //UPDATE emppostchangeapply SET oepc_beg = ? , modtime = ? WHERE id = ?
            ormService.updateSelective(entity);
        } else {
            return catchNoResult("未找到单据'" + id + "'的信息");
        }

        return result;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Result saveEditOrder(JSONObject empPostEditApplyDTO) throws Exception {
        Result result = new Result();
        String json = empPostEditApplyDTO.toJSONString();
        Object params = JSONObject.parse(json);
        // 驼峰转下划线
        JsonUtils.camel2UnderLine(params);
        // json转实体类
        EmppostchangeapplyEntity entity = JSONObject.parseObject(JSONObject.toJSONString(params), EmppostchangeapplyEntity.class);

        String errMsg = orderAddSessionInfo(entity);
        if (!StringUtil.isNullOrEmpty(errMsg)) {
            return catchNoResult(errMsg);
        }
        entity.setOepc_type(EmpPostChangeApplyConstant.EDIT_ORDER);
        JSONObject returnData = saveAddOrEditOrder(entity, NumberConstants.PREFIX_EMP_POST_CHANGE_APPLY_MODIFY);
        result.setData(returnData);
        result.setErrMsg("保存或更新人员调岗单信息成功");
        result.setRetCode(Result.RECODE_SUCCESS);

        return result;
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public Result loadEditOrder(String id) {
        Result result = new Result();
        try {
            // 待返回的数据
            JSONObject returnData = new JSONObject();
            JSONObject baseInfoData = loadOrderBaseInfo(id);
            if (null == baseInfoData) {
                return catchNoResult("未找到单据'" + id + "'的信息");
            }
            returnData.putAll(baseInfoData);
            List<Map<String, Object>> queryListSet = getEditOrderOepcChangSetMaps(id);
            // 属性集待返回数据
            JSONArray oepcChangSetData = new JSONArray();
            if (null != queryListSet && queryListSet.size() > 0) {
                for (Map<String, Object> mapSet : queryListSet) {
                    // 部门id
                    String deptId = NullUtils.valueOf(mapSet.get(EmpPostChangeApplyConstant.OEPC_DEPT));
                    // 岗位id
                    String postId = NullUtils.valueOf(mapSet.get(EmpPostChangeApplyConstant.OEPC_POST));
                    // 旧部门id
                    String oldDeptId = NullUtils.valueOf(mapSet.get(EmpPostChangeApplyConstant.OEPC_DEPT_OLD));
                    // 旧岗位id
                    String oldPostId = NullUtils.valueOf(mapSet.get(EmpPostChangeApplyConstant.OEPC_POST_OLD));
                    // 员工id
                    String empId = NullUtils.valueOf(mapSet.get(EmpPostChangeApplyConstant.OEPC_EMP));
                    // 部门名称
                    String deptName = loadEditOrderLoadDeptName(deptId);
                    // 旧部门名称
                    String oldDeptName = loadEditOrderLoadDeptName(oldDeptId);
                    // 岗位名称
                    String postName = loadEditOrderLoadPostName(postId);
                    // 旧岗位名称
                    String oldPostName = loadEditOrderLoadPostName(oldPostId);
                    // 员工姓名
                    String empName = loadEditOrderLoadEmpName(empId);
                    // 旧岗位岗级
                    String oldPGrade = loadEditOrderLoadPgrade(oldPostId);
                    // 新岗位岗级
                    String newPGrade = loadEditOrderLoadPgrade(postId);
                    // 员工工号
                    String empNo = loadEditOrderLoadEmpNo(empId);

                    JSONObject temp = new JSONObject();
                    temp.put(EmpPostChangeApplyConstant.OEPC_DEPT, deptId);
                    temp.put(EmpPostChangeApplyConstant.OEPC_POST, postId);
                    temp.put(EmpPostChangeApplyConstant.OEPC_DTYP_TYPE, NullUtils.valueOf(mapSet.get(EmpPostChangeApplyConstant.OEPC_DTYP_TYPE)));
                    temp.put(EmpPostChangeApplyConstant.OEPC_DEPT_OLD, oldDeptId);
                    temp.put(EmpPostChangeApplyConstant.OEPC_DTYP_OLD, NullUtils.valueOf(mapSet.get(EmpPostChangeApplyConstant.OEPC_DTYP_OLD)));
                    temp.put(EmpPostChangeApplyConstant.OEPC_POST_OLD, oldPostId);
                    temp.put(EmpPostChangeApplyConstant.OEPC_CHGR_TYPE, NullUtils.valueOf(mapSet.get(EmpPostChangeApplyConstant.OEPC_CHGR_TYPE)));
                    temp.put(EmpPostChangeApplyConstant.OEPC_IS_KEEP, NullUtils.valueOf(mapSet.get(EmpPostChangeApplyConstant.OEPC_IS_KEEP)));
                    temp.put(EmpPostChangeApplyConstant.OEPC_EMP, NullUtils.valueOf(mapSet.get(EmpPostChangeApplyConstant.OEPC_EMP)));
                    temp.put(EmployeeConstants.POST_GRADGE, newPGrade);
                    temp.put(EmployeeConstants.OLD_POST_GRADGE, oldPGrade);
                    temp.put(EmployeeConstants.REMP_NO, empNo);
                    temp.put(EmpPostChangeApplyConstant.PID, NullUtils.valueOf(mapSet.get(EmpPostChangeApplyConstant.PID)));
                    temp.put(EmpPostChangeApplyConstant.ID, NullUtils.valueOf(mapSet.get(EmpPostChangeApplyConstant.ID)));
                    temp.put(EmpPostChangeApplyConstant.EMP_NAME, empName);
                    temp.put(EmpPostChangeApplyConstant.DEPT_NAME, deptName);
                    temp.put(EmpPostChangeApplyConstant.POST_NAME, postName);
                    temp.put(EmpPostChangeApplyConstant.OLD_DEPT_NAME, oldDeptName);
                    temp.put(EmpPostChangeApplyConstant.OLD_POST_NAME, oldPostName);
                    oepcChangSetData.add(temp);
                }
            }
            returnData.put("oepcEditSet", oepcChangSetData);
            // 转驼峰
            JsonUtils.underLine2Camel(returnData);
            result.setData(returnData);
        } catch (Exception e) {
            return catchException(e);
        }
        return result;
    }

    @Override
    public Result submitEditOrder(JSONObject empPostEditApplyDTO) throws Exception {
        Result saveResult = saveEditOrder(empPostEditApplyDTO);
        if (saveResult.getRetCode().equals(Result.RECODE_SUCCESS)) {
            JSONObject saveData = (JSONObject) saveResult.getData();
            String orderId = saveData.getString(NodeConstant.ID);
            Result validResult = validEditOrder(orderId);
            if (validResult.getRetCode().equals(Result.RECODE_VALIDATE_ERROR)) {
                validResult.setErrMsg(validResult.getErrMsg() + ",当前临时单提交失败！");
                return validResult;
            }
        } else {
            return catchNoResult(saveResult.getErrMsg());
        }
        return submitUpdateStatus(saveResult);
    }


    @Transactional(readOnly = true, rollbackFor = Exception.class)
    protected Result validAddOrder(String ordeId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_VALIDATE_ERROR);
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(EmpPostChangeApplyConstant.OEPC_POST);
        ormParam.addColumn(EmpPostChangeApplyConstant.OEPC_EMP);
        ormParam.addColumn(EmpPostChangeApplyConstant.OEPC_DTYP_TYPE);
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.PID, ordeId)));
        try {
            List<Map<String, Object>> mapList = ormService.selectMapList(OepcOepcChangSetaEntity.class, ormParam);
            if (null != mapList && mapList.size() > 0) {
                for (Map<String, Object> map : mapList) {
                    String postId = NullUtils.valueOf(map.get(EmpPostChangeApplyConstant.OEPC_POST));
                    String orderEmpId = NullUtils.valueOf(map.get(EmpPostChangeApplyConstant.OEPC_EMP));
                    String orderDutyType = NullUtils.valueOf(map.get(EmpPostChangeApplyConstant.OEPC_DTYP_TYPE));

                    boolean isNewVaild = isPostVaild(postId);
                    if (!isNewVaild) {
                        String postName = loadEditOrderLoadPostName(postId);
                        String empName = loadEditOrderLoadEmpName(orderEmpId);
                        result.setErrMsg(empName + "新增的" + postName + "岗位已失效");
                        return result;
                    }
                    // 1. 校验是否和 入职单 冲突
                    if (!StringUtil.isNullOrEmpty(postId)) {
                        ormParam.reset();
                        ormParam.addColumn(OrderConstants.ORDE_NBR).addColumn(EmployeeEntryApplyConstant.OEEO_NAME);
                        ormParam.setWhereExp(OrmParam.and(ormParam.getInXML(OrderConstants.ORDE_STATUS, new Object[]{"2", "3", "4"}), ormParam.getEqualXML(EmployeeEntryApplyConstant.OEEO_POST, postId)));
                        List<Map<String, Object>> mapList2 = ormService.selectMapList(EmployeeentryapplyEntity.class, ormParam);
                        if (null != mapList2 && mapList2.size() > 0) {
                            Map<String, Object> map2 = mapList2.get(0);
                            String ordeNbr = NullUtils.valueOf(map2.get(OrderConstants.ORDE_NBR));
                            if(!StringUtil.isNullOrEmpty(ordeNbr)){
                                String oeeoName = NullUtils.valueOf(map2.get(EmployeeEntryApplyConstant.OEEO_NAME));
                                String postName = loadEditOrderLoadPostName(postId);
                                String empName = loadEditOrderLoadEmpName(orderEmpId);
                                result.setErrMsg(empName + " 提交的 " + postName + " 新增单与 " + oeeoName + " 提交的职员入职单任职岗位冲突，单号" + ordeNbr);
                                return result;
                            }
                        }
                    }
                    // 2. 校验是否 重复提交 一模一样的单子
                    if (!StringUtil.isNullOrEmpty(orderEmpId)) {
                        String sql = "SELECT a.orde_nbr,b.oepc_post FROM emppostchangeapply as a\n" +
                                "LEFT JOIN oepc_oepc_chang_seta  as b\n" +
                                "on b.pid = a.id\n" +
                                "WHERE b.oepc_emp = '" + orderEmpId + "'\n" +
                                "and a.oepc_type = '0'\n" +
                                "and a.orde_status in ('2','3','4')\n" +
                                " \n and b.oepc_post='" + postId + "'" +
                                " \n and b.oepc_dtyp_type='" + orderDutyType + "'" +
                                "\n and a.is_del = 0";
                        List<Map<String, Object>> countList = ormService.getDataBySql(sql);
                        if (null != countList && countList.size() > 0) {
                            String ordeNbr = NullUtils.valueOf(countList.get(0).get("orde_nbr"));
                            if(!StringUtil.isNullOrEmpty(ordeNbr)) {
                                orderDutyType = getOepcDtypType(orderDutyType);
                                String oepcPost = NullUtils.valueOf(countList.get(0).get("oepc_post"));
                                String postName = loadEditOrderLoadPostName(oepcPost);
                                String empName = loadEditOrderLoadEmpName(orderEmpId);
                                result.setErrMsg(empName + "已重复提交 " + orderDutyType + " " + postName + " 新增单，单号：" + ordeNbr);
                                return result;
                            }
                        }
                    }
                    //3. 校验是否 重复提交了任职岗位单子
                    if (!StringUtil.isNullOrEmpty(orderEmpId)) {
                        String sql = "SELECT a.orde_nbr,b.oepc_post FROM emppostchangeapply as a\n" +
                                "LEFT JOIN oepc_oepc_chang_seta  as b\n" +
                                "on b.pid = a.id\n" +
                                "WHERE b.oepc_emp = '" + orderEmpId + "'\n" +
                                "and a.oepc_type = '0'\n" +
                                "and a.orde_status in ('2','3','4')\n" +
                                " and b.oepc_dtyp_type='0' \n" +
                                "and a.is_del = 0";
                        List<Map<String, Object>> countList = ormService.getDataBySql(sql);
                        if (null != countList && countList.size() > 0) {
                            String ordeNbr = NullUtils.valueOf(countList.get(0).get("orde_nbr"));
                            if(!StringUtil.isNullOrEmpty(ordeNbr)) {
                                String oepcPost = NullUtils.valueOf(countList.get(0).get("oepc_post"));
                                String postName = loadEditOrderLoadPostName(oepcPost);
                                String empName = loadEditOrderLoadEmpName(orderEmpId);
                                result.setErrMsg(empName + "已提交 任职岗位（" + postName + "）新增单，单号：" + ordeNbr);
                                return result;
                            }
                        }
                    }

                    // 4. 校验岗位是不是空岗位
                    if (validEmptyPost(result, ormParam, postId)) {
                        return result;
                    }

                    // 5. 校验 职员岗位新增单’中不存在单据类型=‘新增’&新岗位=当前岗位
                    if (!StringUtil.isNullOrEmpty(postId)) {
                        String sql = "SELECT a.orde_nbr,b.oepc_emp FROM emppostchangeapply as a\n" +
                                "LEFT JOIN oepc_oepc_chang_seta  as b\n" +
                                "on b.pid = a.id\n" +
                                "WHERE b.oepc_post = '" + postId + "'\n" +
                                "and a.oepc_type = '0'\n" +
                                "and a.orde_status in ('2','3','4')\n" +
                                "and a.is_del = 0";
                        List<Map<String, Object>> countList = ormService.getDataBySql(sql);
                        if (null != countList && countList.size() > 0) {
                            String ordeNbr = NullUtils.valueOf(countList.get(0).get("orde_nbr"));
                            String empId = NullUtils.valueOf(countList.get(0).get("oepc_emp"));
                            if(!StringUtil.isNullOrEmpty(ordeNbr)) {
                                String postName = loadEditOrderLoadPostName(postId);
                                String empName = loadEditOrderLoadEmpName(empId);
                                result.setErrMsg(empName + " 已提交 " + postName + "岗位新增单，单号：" + ordeNbr);
                                return result;
                            }
                        }
                    }
                    // 6. 校验是否 于 部门岗位设置单 冲突
                    if (!StringUtil.isNullOrEmpty(postId)) {
                        if (validateDeptOrdeSet(result, postId)) {
                            return result;
                        }
                    }
                   // 7. 校验是否与 责任人任免单 冲突
                    if (!StringUtil.isNullOrEmpty(postId)) {
                        if (validateDeptchargerapplyorder(result, postId,orderEmpId)) {
                            return result;
                        }
                    }

                }
                result.setRetCode(Result.RECODE_SUCCESS);
                return result;
            }
        } catch (Exception e) {
            return catchException(e);
        }
        result.setErrMsg("未知原因");
        return result;
    }

    public boolean validateDeptchargerapplyorder(Result result, String postId,String empId) throws Exception{
        String sql="SELECT d1.orde_nbr from deptchargerapplyorder as d1\n" +
                "LEFT JOIN odcs_odcs_chrg_seta as d2\n" +
                "ON (d1.id=d2.pid and d2.is_del=0)\n" +
                "where odcs_post='"+postId+"'\n" +
                "and d1.orde_status in ('2','3','4')\n" +
                "and d1.is_del=0";
        List<Map<String, Object>> countList=ormService.getDataBySql(sql);
        if (null != countList && countList.size() > 0) {
            String ordeNbr = NullUtils.valueOf(countList.get(0).get("orde_nbr"));
            if (!StringUtil.isNullOrEmpty(ordeNbr)) {
                String postName = loadEditOrderLoadPostName(postId);
                String empName = loadEditOrderLoadEmpName(empId);
                result.setErrMsg(empName + "的岗位"+postName+"与部门责任人任免单冲突，单号：" + ordeNbr);
                return true;
            }
        }
        return false;
    }


    public boolean validateDeptOrdeSet(Result result, String postId) throws Exception {
        String sql;
        List<Map<String, Object>> countList;
        List<String> postIdList = new ArrayList<>();
        getAllPostIdAndPpostId(postId, postIdList);
        String postIdCondition = " b.odps_post in (";
        for (int i = 0; i < postIdList.size() - 1; i++) {
            postIdCondition += ("'" + postIdList.get(i) + "',");
        }
        postIdCondition += ("'" + postIdList.get(postIdList.size() - 1) + "') ");
        sql = "SELECT jobposition.id as postId, b.odps_post,a.orde_nbr,b.odps_sub FROM deptpostsetorder as a\n" +
                "LEFT JOIN odps_odps_dpost_seta  as b\n" +
                "on b.pid = a.id and b.is_del=0\n" +
                "LEFT JOIN jobposition \n" +
                "on jobposition.rpos_code = b.odps_post\n " +
                "WHERE " + postIdCondition + " \n" +
                "and a.odps_type ='3' \n" +
                "and a.orde_status in ('2','3','4')\n" +
                "and a.is_del = 0";
        countList = ormService.getDataBySql(sql);
        if (null != countList && countList.size() > 0) {
            String ordeNbr = NullUtils.valueOf(countList.get(0).get("orde_nbr"));
            if (!StringUtil.isNullOrEmpty(ordeNbr)) {
                String odpsPost = NullUtils.valueOf(countList.get(0).get("postId"));
                String postName = loadEditOrderLoadPostName(odpsPost);
                String odpsSub = NullUtils.valueOf(countList.get(0).get("odps_sub"));
                if (odpsPost.equals(postId)) {
                    result.setErrMsg(postName + " 已提交部门岗位注销单，单号：" + ordeNbr);
                    return true;
                } else {
                    // 含下级
                    if(odpsSub.equals("1")){
                        String postName2 = loadEditOrderLoadPostName(postId);
                        result.setErrMsg(postName2 + " 的上级岗位 " + postName + " 已提交部门岗位注销单，单号：" + ordeNbr);
                        return true;
                    }else{
                        return false;
                    }
                }
            }
        }
        return false;
    }


    public void getAllPostIdAndPpostId(String postId, List<String> postCodes) {
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(JobPositionConstants.RPOS_PPOST);
        ormParam.addColumn(JobPositionConstants.RPOS_CODE);
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.ID, postId)));
        try {
            List<Map<String, Object>> mapList = ormService.selectMapList(JobpositionEntity.class, ormParam);
            if (null != mapList && mapList.size() > 0) {
                String pPostId = NullUtils.valueOf(mapList.get(0).get(JobPositionConstants.RPOS_PPOST));
                String pPostCode = NullUtils.valueOf(mapList.get(0).get(JobPositionConstants.RPOS_CODE));
                if (!StringUtil.isNullOrEmpty(pPostCode)) {
                    postCodes.add(pPostCode);
                }
                if (!StringUtil.isNullOrEmpty(pPostId)) {
                    getAllPostIdAndPpostId(pPostId, postCodes);
                }
            }
        } catch (Exception e) {
            // e.printStackTrace();
        }
    }


    @Transactional(readOnly = true, rollbackFor = Exception.class)
    protected Result validEditOrder(String ordeId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_VALIDATE_ERROR);
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(EmpPostChangeApplyConstant.OEPC_POST);
        ormParam.addColumn(EmpPostChangeApplyConstant.OEPC_EMP);
        ormParam.addColumn(EmpPostChangeApplyConstant.OEPC_POST_OLD);
        ormParam.addColumn(EmpPostChangeApplyConstant.OEPC_IS_KEEP);
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.PID, ordeId)));
        try {
            List<Map<String, Object>> mapList = ormService.selectMapList(OepcOepcChangSetaEntity.class, ormParam);
            if (null != mapList && mapList.size() > 0) {
                for (Map<String, Object> map : mapList) {
                    String postId = NullUtils.valueOf(map.get(EmpPostChangeApplyConstant.OEPC_POST));
                    String postIdOld = NullUtils.valueOf(map.get(EmpPostChangeApplyConstant.OEPC_POST_OLD));
                    String isKeep = NullUtils.valueOf(map.get(EmpPostChangeApplyConstant.OEPC_IS_KEEP));
                    String orderEmpId = NullUtils.valueOf(map.get(EmpPostChangeApplyConstant.OEPC_EMP));

                    // 判断岗位有效性
                    if ("1".equals(isKeep)) {
                        boolean isOldVaild = isPostVaild(postIdOld);
                        if (!isOldVaild) {
                            String postName = loadEditOrderLoadPostName(postIdOld);
                            String empName = loadEditOrderLoadEmpName(orderEmpId);
                            result.setErrMsg(empName + " 原岗位 " + postName + " 已岗位失效，无法保留原岗位");
                            return result;
                        }
                        /**
                         * 员工岗位是否已经注销
                         */
                        if(isEmpPostCancel(postIdOld)){
                            String postName = loadEditOrderLoadPostName(postIdOld);
                            String empName = loadEditOrderLoadEmpName(orderEmpId);
                            result.setErrMsg(empName + " 原岗位 " + postName + " 已单据注销失效，无法保留原岗位");
                            return result;
                        }
                    }
                    boolean isNewVaild = isPostVaild(postId);
                    if (!isNewVaild) {
                        String postName = loadEditOrderLoadPostName(postId);
                        String empName = loadEditOrderLoadEmpName(orderEmpId);
                        result.setErrMsg(empName + " 新岗位 " + postName + "已岗位失效，无法调整新岗位");
                        return result;
                    }

                    // 1. 校验是否与 入职单 冲突
                    if (!StringUtil.isNullOrEmpty(postId)) {
                        ormParam.reset();
                        ormParam.addColumn(OrderConstants.ORDE_NBR).addColumn(EmployeeEntryApplyConstant.OEEO_NAME);
                        ormParam.setWhereExp(OrmParam.and(ormParam.getInXML(OrderConstants.ORDE_STATUS, new Object[]{"2", "3", "4"}), ormParam.getEqualXML(EmployeeEntryApplyConstant.OEEO_POST, postId)));
                        List<Map<String, Object>> mapList2 = ormService.selectMapList(EmployeeentryapplyEntity.class, ormParam);
                        if (null != mapList2 && mapList2.size() > 0) {
                            Map<String, Object> map2 = mapList2.get(0);
                            String ordeNbr = NullUtils.valueOf(map2.get(OrderConstants.ORDE_NBR));
                            if(!StringUtil.isNullOrEmpty(ordeNbr)) {
                                String oeeoName = NullUtils.valueOf(map2.get(EmployeeEntryApplyConstant.OEEO_NAME));
                                String postName = loadEditOrderLoadPostName(postId);
                                String empName = loadEditOrderLoadEmpName(orderEmpId);
                                result.setErrMsg(empName + " 调整的岗位" + postName + " 与 " + oeeoName + " 提交的职员入职单任职岗位冲突，单号" + ordeNbr);
                                return result;
                            }
                        }
                    }
                    //2. 校验是否重复提交过调整单
                    if (!StringUtil.isNullOrEmpty(orderEmpId)) {
                        String sql = "SELECT a.orde_nbr,b.oepc_post_old FROM emppostchangeapply as a\n" +
                                "LEFT JOIN oepc_oepc_chang_seta  as b\n" +
                                "on b.pid = a.id\n" +
                                "WHERE b.oepc_emp = '" + orderEmpId + "'\n" +
                                "and a.oepc_type = '1'\n" +
                                "and a.orde_status in ('2','3','4')\n" +
                                "and a.is_del = 0";
                        List<Map<String, Object>> countList = ormService.getDataBySql(sql);
                        if (null != countList && countList.size() > 0) {
                            String ordeNbr = NullUtils.valueOf(countList.get(0).get("orde_nbr"));
                            if(!StringUtil.isNullOrEmpty(ordeNbr)) {
                                String oepcPost = NullUtils.valueOf(countList.get(0).get("oepc_post_old"));
                                String postName = loadEditOrderLoadPostName(oepcPost);
                                String empName = loadEditOrderLoadEmpName(orderEmpId);
                                result.setErrMsg(empName + "已提交 " + postName + "  员工岗位调整单，单号：" + ordeNbr);
                                return result;
                            }
                        }
                    }
                    // 3. 校验是否 岗位是空岗
                    if (validEmptyPost(result, ormParam, postId)) {
                        return result;
                    }

                    // 4.   岗位在‘职员岗位调整单’中不存在单据类型=‘调整’&新岗位=当前岗位
                    if (!StringUtil.isNullOrEmpty(postId)) {
                        String sql = "SELECT a.orde_nbr,b.oepc_emp,b.oepc_dtyp_type FROM emppostchangeapply as a\n" +
                                "LEFT JOIN oepc_oepc_chang_seta  as b\n" +
                                "on b.pid = a.id\n" +
                                "WHERE b.oepc_post = '" + postId + "'\n" +
                                "and a.oepc_type = '1'\n" +
                                "and a.orde_status in ('2','3','4')\n" +
                                "and a.is_del = 0";
                        List<Map<String, Object>> countList = ormService.getDataBySql(sql);
                        if (null != countList && countList.size() > 0) {
                            String ordeNbr = NullUtils.valueOf(countList.get(0).get("orde_nbr"));
                            if(!StringUtil.isNullOrEmpty(ordeNbr)) {
                                String empId = NullUtils.valueOf(countList.get(0).get("oepc_emp"));
                                String oepcDtypType = NullUtils.valueOf(countList.get(0).get("oepc_dtyp_type"));
                                oepcDtypType = getOepcDtypType(oepcDtypType);
                                String postName = loadEditOrderLoadPostName(postId);
                                String empName = loadEditOrderLoadEmpName(empId);
                                result.setErrMsg(empName + oepcDtypType + postName + " 已提交员工岗位调整单，单号：" + ordeNbr);
                                return result;
                            }
                        }
                    }
                    // 5.  校验是否 于 部门岗位设置单 冲突
                    if (!StringUtil.isNullOrEmpty(postId)) {
                        if (validateDeptOrdeSet(result, postId)) {
                            return result;
                        }
                    }
                    // 6. 校验是否与 责任人任免单 冲突
                    if (!StringUtil.isNullOrEmpty(postId)) {
                        if (validateDeptchargerapplyorder(result, postId,orderEmpId)) {
                            return result;
                        }
                    }
                }

                result.setRetCode(Result.RECODE_SUCCESS);
                return result;
            }
        } catch (Exception e) {
            return catchException(e);
        }
        result.setErrMsg("未知原因");
        return result;
    }

    private boolean validEmptyPost(Result result, OrmParam ormParam, String postId) throws Exception {
        if (!StringUtil.isNullOrEmpty(postId)) {
            ormParam.reset();
            ormParam.addColumn(JobPositionConstants.RPOS_EMP);
            ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.ID, postId)));
            List<Map<String, Object>> empList = ormService.selectMapList(JobpositionEntity.class, ormParam);
            if (null != empList && empList.size() > 0) {
                String empId = NullUtils.valueOf(empList.get(0).get(JobPositionConstants.RPOS_EMP));
                if (!StringUtil.isNullOrEmpty(empId)) {
                    String postName = loadEditOrderLoadPostName(postId);
                    String empName = loadEditOrderLoadEmpName(empId);
                    result.setErrMsg(postName + "岗位不是空岗位，任职人：" + empName);
                    return true;
                }
            }
        }
        return false;
    }

    @NotNull
    private String getOepcDtypType(String oepcDtypType) {
        if (oepcDtypType.equals(EmpPostChangeApplyConstant.DUTY_TYPE_0)) {
            oepcDtypType = "任职";
        } else if (oepcDtypType.equals(EmpPostChangeApplyConstant.DUTY_TYPE_1)) {
            oepcDtypType = "兼职";
        } else if (oepcDtypType.equals(EmpPostChangeApplyConstant.DUTY_TYPE_2)) {
            oepcDtypType = "代职";
        }
        return oepcDtypType;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Result passEditOrder(String id) {
        Result result = new Result();
        try {
            List<Map<String, Object>> queryListSet = getEditOrderOepcChangSetMaps(id);
            if (null != queryListSet && queryListSet.size() > 0) {
                Date begDate = new Date();
                for (Map<String, Object> mapSet : queryListSet) {
                    // 调岗员工 id
                    String empId = NullUtils.valueOf(mapSet.get(EmpPostChangeApplyConstant.OEPC_EMP));
                    // 新部门id
                    String newDeptId = NullUtils.valueOf(mapSet.get(EmpPostChangeApplyConstant.OEPC_DEPT));
                    // 新岗位id
                    String newPostId = NullUtils.valueOf(mapSet.get(EmpPostChangeApplyConstant.OEPC_POST));
                    // 新岗位的岗级和汇报上级员工id
                    JSONObject jo = getPgradeAndPpostId(newPostId);
                    String newRposGrade = jo.getString(JobPositionConstants.RPOS_GRADE);
                    String newRposEmp = jo.getString(JobPositionConstants.RPOS_EMP);
                    // 旧岗位id
                    String oldPostId = NullUtils.valueOf(mapSet.get(EmpPostChangeApplyConstant.OEPC_POST_OLD));
                    // 新的任职方式
                    String newDutyType = NullUtils.valueOf(mapSet.get(EmpPostChangeApplyConstant.OEPC_DTYP_TYPE));
                    // 旧的任职方式
                    String oldDutyType = NullUtils.valueOf(mapSet.get(EmpPostChangeApplyConstant.OEPC_DTYP_OLD));
                    // 是否保留员工位：0不保留 1 保留
                    String isKeep = NullUtils.valueOf(mapSet.get(EmpPostChangeApplyConstant.OEPC_IS_KEEP));

                    // 查询新岗位是否有效（失效日期）
                    boolean isNewPostVaild = isPostVaild(newPostId);
                    if (!isNewPostVaild) {
                        return catchNoResult(loadEditOrderLoadPostName(newPostId) + "已失效");
                    }
                    // 查询旧岗位是否有效（失效日期）
                    boolean isOldPostVaild = isPostVaild(oldPostId);

                    JobpositionEntity oldPostEntity = new JobpositionEntity();
                    oldPostEntity.setRpos_emp(loadPostEmp(oldPostId));
                    oldPostEntity.setId(oldPostId);
                    JSONObject oldPostSetObj = loadRempRempPostSetIdByPostId(oldPostId);
                    /**
                     * 1.旧岗位=‘任职’，新=‘任职’，保留原岗位	1,2,3,4,5,6
                     */
                    if(oldDutyType.equals(EmpPostChangeApplyConstant.DUTY_TYPE_0)&&newDutyType.equals(EmpPostChangeApplyConstant.DUTY_TYPE_0)&&isKeep.equals("1")){
                        passEditStep1(newPostId,empId,newDutyType);
                        passEditStep2(oldPostEntity);
                        if(isOldPostVaild){
                            passEditStep3(oldPostSetObj,begDate);
                        }
                        passEditStep4(oldPostSetObj,begDate,empId);
                        passEditStep5(newPostId,newRposGrade,newRposEmp,newDutyType,empId,begDate);
                        passEditStep6(newRposGrade,newPostId,newDeptId,empId);
                    }
                    /**
                     * 2. 旧岗位=‘任职’，新=‘任职’，不保留原岗位 1,3,5,6,7
                     */
                    else if(oldDutyType.equals(EmpPostChangeApplyConstant.DUTY_TYPE_0)&&newDutyType.equals(EmpPostChangeApplyConstant.DUTY_TYPE_0)&&isKeep.equals("0")){
                        passEditStep1(newPostId,empId,newDutyType);
                        if(isOldPostVaild){
                            passEditStep3(oldPostSetObj,begDate);
                        }
                        passEditStep5(newPostId,newRposGrade,newRposEmp,newDutyType,empId,begDate);
                        passEditStep6(newRposGrade,newPostId,newDeptId,empId);
                        if(isOldPostVaild){
                            passEditStep7(oldPostEntity,empId);
                        }
                    }
                    /**
                     * 3. 旧岗位=‘兼职/代职’，新=‘兼职/代职’，保留原岗位 1，5，
                     */
                    else if(!oldDutyType.equals(EmpPostChangeApplyConstant.DUTY_TYPE_0)&&!newDutyType.equals(EmpPostChangeApplyConstant.DUTY_TYPE_0)&&isKeep.equals("1")){
                        passEditStep1(newPostId,empId,newDutyType);
                        passEditStep5(newPostId,newRposGrade,newRposEmp,newDutyType,empId,begDate);
                    }
                    /**
                     * 4. 旧岗位=‘兼职/代职’，新=‘兼职/代职’，不保留原岗位 1，3,5,7
                     */
                    else if(!oldDutyType.equals(EmpPostChangeApplyConstant.DUTY_TYPE_0)&&!newDutyType.equals(EmpPostChangeApplyConstant.DUTY_TYPE_0)&&isKeep.equals("0")){
                        passEditStep1(newPostId,empId,newDutyType);
                        if(isOldPostVaild){
                            passEditStep3(oldPostSetObj,begDate);
                        }
                        passEditStep5(newPostId,newRposGrade,newRposEmp,newDutyType,empId,begDate);
                        if(isOldPostVaild){
                            passEditStep7(oldPostEntity,empId);
                        }
                    }
                    /**
                     * 5. 旧岗位=‘兼职/代职’，新=‘任职’，保留原岗位 1，5，6,8，
                     */
                    else if(!oldDutyType.equals(EmpPostChangeApplyConstant.DUTY_TYPE_0)&&newDutyType.equals(EmpPostChangeApplyConstant.DUTY_TYPE_0)&&isKeep.equals("1")){
                        passEditStep1(newPostId,empId,newDutyType);
                        passEditStep5(newPostId,newRposGrade,newRposEmp,newDutyType,empId,begDate);
                        passEditStep6(newRposGrade,newPostId,newDeptId,empId);
                        passEditStep8(empId,begDate);
                    }
                    /**
                     * 6. 旧岗位=‘兼职/代职’，新=‘任职’，不保留原岗位 1，3,5，7,6，8
                     */
                    else if(!oldDutyType.equals(EmpPostChangeApplyConstant.DUTY_TYPE_0)&&newDutyType.equals(EmpPostChangeApplyConstant.DUTY_TYPE_0)&&isKeep.equals("0")){
                        passEditStep1(newPostId,empId,newDutyType);
                        if(isOldPostVaild){
                            passEditStep3(oldPostSetObj,begDate);
                        }
                        passEditStep5(newPostId,newRposGrade,newRposEmp,newDutyType,empId,begDate);
                        if(isOldPostVaild){
                            passEditStep7(oldPostEntity,empId);
                        }
                        passEditStep6(newRposGrade,newPostId,newDeptId,empId);
                        passEditStep8(empId,begDate);
                    }

                }
                // 更新单据的生效日期
                EmppostchangeapplyEntity entity = new EmppostchangeapplyEntity();
                entity.setId(id);
                entity.setOepc_beg(begDate);
                entity.setOrde_status(OrderConstants.ORDE_STATUS_5);
                ormService.updateSelective(entity);

            } else {
                return catchNoResult("未找到单据'" + id + "'的信息");
            }
        } catch (Exception e) {
            return catchException(e);
        }
        return result;
    }

    /**
     *   1.新岗位的岗位对象的任职人=‘被调整人’，任职方式=新岗位的任职方式；
      */
    private void passEditStep1(String newPostId,String empId,String newDutyType) throws Exception{
        JobpositionEntity newPostEntity = new JobpositionEntity();
        newPostEntity.setId(newPostId);
        newPostEntity.setRpos_emp(empId);
        newPostEntity.setRpos_duty_type(newDutyType);
        ormService.updateSelective(newPostEntity);
    }

    /**
     * //2.更新旧岗位岗位对象的任职方式=‘兼职’
     */
    private void passEditStep2(JobpositionEntity oldPostEntity) throws Exception{
        oldPostEntity.setRpos_duty_type(EmpPostChangeApplyConstant.DUTY_TYPE_1);
        ormService.updateSelective(oldPostEntity);
    }

    /**
     * // 3.被调整人员工对象任岗经历旧岗位的任岗经历失效，写入失效日期
     */
    private void passEditStep3(JSONObject oldPostSetObj,Date begDate) throws Exception{
        RempRempPostSetaEntity oldRempRempSet = new RempRempPostSetaEntity();
        oldRempRempSet.setId(NullUtils.valueOf(oldPostSetObj.get(NodeConstant.ID)));
        oldRempRempSet.setRemp_post_end(begDate);
        oldRempRempSet.setModtime(new Date());
        ormService.updateSelective(oldRempRempSet);
    }

    /**
     * // 4.被调整人员工对象任岗经历写入旧岗位的任岗经历，生效日期=单据批准日期，任职方式=‘兼职’
     */
    private void passEditStep4(JSONObject oldPostSetObj,Date begDate,String empId) throws Exception{
        RempRempPostSetaEntity oldRempRempSet2 = new RempRempPostSetaEntity();
        oldRempRempSet2.setRemp_post_his(NullUtils.valueOf(oldPostSetObj.get(EmployeeConstants.REMP_POST_HIS)));
        oldRempRempSet2.setRemp_pgrad_his(NullUtils.valueOf(oldPostSetObj.get(EmployeeConstants.REMP_PGRAD_HIS)));
        oldRempRempSet2.setRemp_pemp_his(NullUtils.valueOf(oldPostSetObj.get(EmployeeConstants.REMP_PEMP_HIS)));
        oldRempRempSet2.setRemp_dtyp_his(EmpPostChangeApplyConstant.DUTY_TYPE_1);
        oldRempRempSet2.setRemp_post_beg(begDate);
        oldRempRempSet2.setPid(empId);
        oldRempRempSet2.setClassName(EmployeeConstants.EDM_EMPLOYEE);
        CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
        oldRempRempSet2.setCreuser(sessionEntity.getEmployeeId());
        oldRempRempSet2.setCretime(new Date());
        ormService.insertSelective(oldRempRempSet2);
    }

    /**
     * 5. 被调整人员工对象任岗经历写入新岗位的任岗经历，生效日期=单据批准日期，任职方式=‘新的岗位的任职方式
     */
    private void passEditStep5(String newPostId,String newRposGrade,String newRposEmp,String newDutyType,String empId,Date begDate) throws Exception{
        RempRempPostSetaEntity newRempRempSet = new RempRempPostSetaEntity();
        newRempRempSet.setRemp_post_his(newPostId);
        newRempRempSet.setRemp_pgrad_his(newRposGrade);
        newRempRempSet.setRemp_pemp_his(newRposEmp);
        newRempRempSet.setRemp_dtyp_his(newDutyType);
        newRempRempSet.setRemp_post_beg(begDate);
        newRempRempSet.setPid(empId);
        newRempRempSet.setClassName(EmployeeConstants.EDM_EMPLOYEE);
        CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
        newRempRempSet.setCreuser(sessionEntity.getEmployeeId());
        newRempRempSet.setCretime(new Date());
        ormService.insertSelective(newRempRempSet);
    }

    /**
     * // 6. 更新员工对象的任职岗位、岗级、部门、法人
     */
    private void passEditStep6(String newRposGrade,String newPostId,String newDeptId,String empId)throws Exception{
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setRemp_pgrade(newRposGrade);
        employeeEntity.setRemp_post(newPostId);
        employeeEntity.setRemp_dept(newDeptId);
        employeeEntity.setRemp_mcop(loadEditOrderLoadDeptMCop(newDeptId));
        employeeEntity.setId(empId);
        ormService.updateSelective(employeeEntity);
    }

    /**
     * 7. 清空旧岗位对象的任职人和任职方式
     */
    private void passEditStep7(JobpositionEntity oldPostEntity,String empId)throws Exception{
        if(empId.equals(oldPostEntity.getRpos_emp())){
            oldPostEntity.setRpos_emp("");
            oldPostEntity.setRpos_duty_type("");
            ormService.updateSelective(oldPostEntity);
         }
    }

    /**
     * 8。 找到员工对象当前日期（生效日期<=当前日期<失效日期），任岗经历中的任职方式=“任职”的岗位P，P岗位的任岗经历写入失效期；员工对象新增一笔p岗位的“兼职”经历，生效日期=单据批准日期，更新P岗位的任职方式=‘兼职’
     * **/
    private void passEditStep8(String empId,Date begDate)throws Exception{
        Date now = new Date();
        OrmParam ormParam = new OrmParam();
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.PID,empId)
                ,ormParam.getLessThanAndEqualXML(RempRempPostSetaProperty.REMP_POST_BEG,now),
                ormParam.getGreaterThanXML(RempRempPostSetaProperty.REMP_POST_END,now),
                ormParam.getEqualXML(RempRempPostSetaProperty.REMP_DTYP_HIS,EmpPostChangeApplyConstant.DUTY_TYPE_0)
                ));
        List<RempRempPostSetaEntity> list = ormService.selectBeanList(RempRempPostSetaEntity.class,ormParam);
        if(null!=list&&list.size()>0){
            RempRempPostSetaEntity currPostSeta = list.get(0);
            currPostSeta.setRemp_post_end(begDate);
            ormService.updateSelective(currPostSeta);
            currPostSeta.setId(null);
            currPostSeta.setRemp_dtyp_his(EmpPostChangeApplyConstant.DUTY_TYPE_1);
            currPostSeta.setRemp_post_beg(begDate);
            Calendar calendar = Calendar.getInstance();
            calendar.set(9999,0,0);
            currPostSeta.setRemp_post_end(calendar.getTime());
            ormService.insertSelective(currPostSeta);

            JobpositionEntity job = new JobpositionEntity();
            job.setId(currPostSeta.getRemp_post_his());
            job.setRpos_duty_type(EmpPostChangeApplyConstant.DUTY_TYPE_1);
            ormService.updateSelective(job);
        }

    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Result saveDeleteOrder(JSONObject empPostChangeApplyDTO) throws Exception {
        Result result = new Result();
        String json = empPostChangeApplyDTO.toJSONString();
        Object params = JSONObject.parse(json);
        // 驼峰转下划线
        JsonUtils.camel2UnderLine(params);
        // json转实体类
        EmppostchangeapplyEntity entity = JSONObject.parseObject(JSONObject.toJSONString(params), EmppostchangeapplyEntity.class);

        String errMsg = orderAddSessionInfo(entity);
        if (!StringUtil.isNullOrEmpty(errMsg)) {
            return catchNoResult(errMsg);
        }
        entity.setOepc_type(EmpPostChangeApplyConstant.CANCEL_ORDER);
        JSONObject returnData = saveAddOrEditOrder(entity, NumberConstants.PREFIX_EMP_POST_CHANGE_APPLY_CANCEL);
        result.setData(returnData);
        result.setErrMsg("保存或更新人员离岗单信息成功");
        result.setRetCode(Result.RECODE_SUCCESS);

        return result;
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public Result loadDeleteOrder(String id) {
        Result result = new Result();
        try {
            // 待返回的数据
            JSONObject returnData = new JSONObject();
            JSONObject baseInfoData = loadOrderBaseInfo(id);
            if (null == baseInfoData) {
                return catchNoResult("未找到单据'" + id + "'的信息");
            }
            returnData.putAll(baseInfoData);
            // 获取属性集oepcChangSet
            List<Map<String, Object>> queryListSet = getAddOrderOepcChangSetMaps(id);
            // 属性集待返回数据
            JSONArray oepcChangSetData = new JSONArray();
            if (null != queryListSet && queryListSet.size() > 0) {
                for (Map<String, Object> mapSet : queryListSet) {
                    JSONObject temp = new JSONObject();
                    // 属性集
                    // 部门Id
                    String deptId = NullUtils.valueOf(mapSet.get(EmpPostChangeApplyConstant.OEPC_DEPT));
                    temp.put(EmpPostChangeApplyConstant.OEPC_DEPT, deptId);
                    // 岗位ID
                    String postId = NullUtils.valueOf(mapSet.get(EmpPostChangeApplyConstant.OEPC_POST));
                    temp.put(EmpPostChangeApplyConstant.OEPC_POST, postId);
                    // 任职方式
                    temp.put(EmpPostChangeApplyConstant.OEPC_DTYP_TYPE, NullUtils.valueOf(mapSet.get(EmpPostChangeApplyConstant.OEPC_DTYP_TYPE)));
                    // 员工ID
                    String oepcEmp = NullUtils.valueOf(mapSet.get(EdmPostchangeapplyConstants.OEPC_EMP));
                    temp.put(EmpPostChangeApplyConstant.OEPC_EMP, oepcEmp);
                    // 单据ID
                    temp.put(EmpPostChangeApplyConstant.PID, NullUtils.valueOf(mapSet.get(EmpPostChangeApplyConstant.PID)));
                    //属性集子元素ID
                    temp.put(EmpPostChangeApplyConstant.ID, NullUtils.valueOf(mapSet.get(EmpPostChangeApplyConstant.ID)));
                    // 部门名字
                    temp.put(DeptTreeConstants.MDEP_NAME, loadEditOrderLoadDeptName(deptId));
                    // 办公园区
                    temp.putAll(loadAddOrderPark(deptId));
                    //  员工工号，姓名，性别
                    OrmParam ormParam = getLoadEmployeeOrmParam(oepcEmp);
                    List<Map<String, Object>> queryList = ormService.selectMapList(EmployeeEntity.class, ormParam);
                    if (null != queryList && queryList.size() > 0) {
                        Map<String, Object> map = queryList.get(0);
                        getAllTimes(temp, map, postId);
                        temp.put(EmployeeConstants.REMP_NO, NullUtils.valueOf(map.get(EmployeeConstants.REMP_NO)));
                        temp.put(EmployeeConstants.REMP_NAME, NullUtils.valueOf(map.get(EmployeeConstants.REMP_NAME)));
                        temp.put(EmployeeConstants.REMP_GENDER, NullUtils.valueOf(map.get(EmployeeConstants.REMP_GENDER)));
                        // 法人公司名称
                        returnData.putAll(loadAddOrderLoadMCOP(NullUtils.valueOf(map.get(EmployeeConstants.REMP_MCOP))));
                    } else {
                        throw new Exception("未查到id为'" + oepcEmp + "'的员工信息");
                    }
                    // 岗位岗级
                    temp.put(EmployeeConstants.REMP_PGRADE, loadEditOrderLoadPgrade(postId));
                    JSONObject jobObj = loadAddOrderLoadJob(postId);
                    // 岗位上级任职人id
                    Object pPostEmpId = jobObj.get(JobPositionConstants.RPOS_PPOST);
                    if (null != pPostEmpId) {
                        String empName = loadEditOrderLoadEmpName(pPostEmpId.toString());
                        // 直属上级
                        jobObj.put(EmployeeConstants.UP_PERSON, empName);
                    } else {
                        jobObj.put(EmployeeConstants.UP_PERSON, "");
                    }
                    temp.putAll(jobObj);
                    // 教育经历
                    returnData.putAll(loadAddOrderLoadStudy(oepcEmp));
                    oepcChangSetData.add(temp);
                }
            }
            returnData.put(EmpPostChangeApplyConstant.OEPC_CHANG_SET, oepcChangSetData);
            // 转驼峰
            JsonUtils.underLine2Camel(returnData);
            result.setRetCode(Result.RECODE_SUCCESS);
            result.setData(returnData);
        } catch (Exception e) {
            return catchException(e);
        }

        return result;

    }

    @Override
    public Result submitDeleteOrder(JSONObject empPostChangeApplyDTO) throws Exception {
        Result saveResult = saveDeleteOrder(empPostChangeApplyDTO);
        if (saveResult.getRetCode().equals(Result.RECODE_SUCCESS)) {
            JSONObject saveData = (JSONObject) saveResult.getData();
            String orderId = saveData.getString(NodeConstant.ID);
            Result validResult = validDeleteOrder(orderId);
            if (validResult.getRetCode().equals(Result.RECODE_VALIDATE_ERROR)) {
                validResult.setErrMsg(validResult.getErrMsg() + ",当前临时单提交失败！");
                return validResult;
            }
        } else {
            return catchNoResult(saveResult.getErrMsg());
        }
        return submitUpdateStatus(saveResult);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    protected Result validDeleteOrder(String ordeId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_VALIDATE_ERROR);
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(EmpPostChangeApplyConstant.OEPC_POST);
        ormParam.addColumn(EmpPostChangeApplyConstant.OEPC_EMP);
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.PID, ordeId)));
        try {
            List<Map<String, Object>> mapList = ormService.selectMapList(OepcOepcChangSetaEntity.class, ormParam);
            if (null != mapList && mapList.size() > 0) {
                for (Map<String, Object> map : mapList) {
                    String postId = NullUtils.valueOf(map.get(EmpPostChangeApplyConstant.OEPC_POST));
                    String oemp = NullUtils.valueOf(map.get(EmpPostChangeApplyConstant.OEPC_EMP));
                    boolean isNewVaild = isPostVaild(postId);
                    if (!isNewVaild) {
                        String postName = loadEditOrderLoadPostName(postId);
                        String empName = loadEditOrderLoadEmpName(oemp);
                        result.setErrMsg(empName+" 岗位 " + postName + " 已岗位失效");
                        return result;
                    }
                    /**
                     * 员工岗位是否已经注销
                     */
                    if(isEmpPostCancel(postId)){
                        String postName = loadEditOrderLoadPostName(postId);
                        String empName = loadEditOrderLoadEmpName(oemp);
                        result.setErrMsg(empName + " 岗位 " + postName + "已单据注销失效");
                        return result;
                    }
                    // 4. 校验岗位是不是空岗位
                    ormParam.reset();
                    ormParam.addColumn(JobPositionConstants.RPOS_EMP);
                    ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.ID, postId)));
                    List<Map<String, Object>> empList1 = ormService.selectMapList(JobpositionEntity.class, ormParam);
                    if (null != empList1 && empList1.size() > 0) {
                        String empId1 = NullUtils.valueOf(empList1.get(0).get(JobPositionConstants.RPOS_EMP));
                        if (StringUtil.isNullOrEmpty(empId1)) {
                            String postName = loadEditOrderLoadPostName(postId);
                            String empName = loadEditOrderLoadEmpName(oemp);
                            result.setErrMsg(empName + " 岗位 " + postName + " 是空岗，无需注销！");
                            return result;
                        }
                    }

                    if (!StringUtil.isNullOrEmpty(postId)) {
                        //  1. 选择注销的岗位是是否是“兼职”或者“代职”岗位.即注销只能注销兼职岗位和代职岗位
                        ormParam.reset();
                        ormParam.addColumn(JobPositionConstants.RPOS_DUTY_TYPE);
                        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.ID, postId), ormParam.getEqualXML(JobPositionConstants.RPOS_DUTY_TYPE, "0")));
                        List<Map<String, Object>> empList = ormService.selectMapList(JobpositionEntity.class, ormParam);
                        if (null != empList && empList.size() > 0) {
                            String postName = loadEditOrderLoadPostName(postId);
                            result.setErrMsg("选择注销的岗位" + postName + "是任职的方式，不允许注销");
                            return result;
                        }
                    }
                    if (!StringUtil.isNullOrEmpty(postId)) {
                        // c. 岗位在‘职员岗位调整单’中不存在单据类型=‘注销’&新岗位=当前岗位
                        String sql = "SELECT a.orde_nbr FROM emppostchangeapply as a\n" +
                                "LEFT JOIN oepc_oepc_chang_seta  as b\n" +
                                "on b.pid = a.id\n" +
                                "WHERE b.oepc_post = '" + postId + "'\n" +
                                "and a.oepc_type = '3'\n" +
                                "and a.orde_status in ('2','3','4')\n" +
                                "and a.is_del = 0";
                        List<Map<String, Object>> countList = ormService.getDataBySql(sql);
                        if (null != countList && countList.size() > 0) {
                            String ordeNbr = NullUtils.valueOf(countList.get(0).get("orde_nbr"));
                            if(!StringUtil.isNullOrEmpty(ordeNbr)) {
                                String postName = loadEditOrderLoadPostName(postId);
                                result.setErrMsg(postName + "已提交员工岗位注销单，单号：" + ordeNbr);
                                return result;
                            }
                        }
                    }

                    if (!StringUtil.isNullOrEmpty(postId)) {
                        // d. 注销的岗位不是 负责人岗位
                        String sql = "SELECT d1.id,m1.mdep_chgr_post from depttree as d1\n " +
                                "LEFT JOIN mdep_mdep_chgr_seta as m1\n " +
                                "ON (d1.id=m1.pid and m1.is_del=0)\n " +
                                "where m1.mdep_chgr_post='" + postId + "'\n " +
                                "and m1.mdep_chgr_beg<=NOW()\n " +
                                "and m1.mdep_chgr_end>NOW()\n " +
                                "and d1.is_del=0 ";
                        List<Map<String, Object>> countList = ormService.getDataBySql(sql);
                        if (null != countList && countList.size() > 0) {
                            String id = NullUtils.valueOf(countList.get(0).get(NodeConstant.ID));
                            if(!StringUtil.isNullOrEmpty(id)){
                                String empName = loadEditOrderLoadEmpName(oemp);
                                String postName = loadEditOrderLoadPostName(postId);
                                result.setErrMsg(empName + "的" + postName + "是部门负责人岗位，不能被注销");
                                return result;
                            }
                        }
                    }
                    // 7. 校验是否与 责任人任免单 冲突
                    if (!StringUtil.isNullOrEmpty(postId)) {
                        if (validateDeptchargerapplyorder(result, postId,oemp)) {
                            return result;
                        }
                    }

                }
                result.setRetCode(Result.RECODE_SUCCESS);
                return result;
            }

        } catch (Exception e) {
            return catchException(e);
        }
        result.setErrMsg("未知原因");
        return result;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Result passDeleteOrder(String id) {
        Result result = new Result();
        try {
            List<Map<String, Object>> queryListSet = getDeleteOrderOepcChangSetMaps(id);
            if (null != queryListSet && queryListSet.size() > 0) {
                Date begDate = new Date();
                for (Map<String, Object> mapSet : queryListSet) {
                    String oepcPostOld = NullUtils.valueOf(mapSet.get(EdmPostchangeapplyConstants.OEPC_POST));
                    String oepcEmp = NullUtils.valueOf(mapSet.get(EdmPostchangeapplyConstants.OEPC_EMP));
                    if (!StringUtil.isNullOrEmpty(oepcPostOld)) {
                        String oempOld = loadPostEmp(oepcPostOld);
                        if(oempOld.equals(oepcEmp)){
                            JobpositionEntity entity = new JobpositionEntity();
                            entity.setId(oepcPostOld);
                            entity.setRpos_duty_type("");
                            entity.setRpos_emp("");
                            ormService.updateSelective(entity);
                        }

                        OrmParam ormParam = new OrmParam();
                        ormParam.addColumn(NodeConstant.ID);
                        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.PID, oepcEmp),
                                ormParam.getEqualXML(EmployeeConstants.REMP_POST_HIS, oepcPostOld)));
                        List<Map<String, Object>> list = ormService.selectMapList(RempRempPostSetaEntity.class, ormParam);
                        if (null != list && list.size() > 0) {
                            String setId = NullUtils.valueOf(list.get(0).get(NodeConstant.ID));
                            RempRempPostSetaEntity postSetaEntity = new RempRempPostSetaEntity();
                            postSetaEntity.setId(setId);
                            postSetaEntity.setPid(oepcEmp);
                            postSetaEntity.setRemp_post_his(oepcPostOld);
                            postSetaEntity.setRemp_post_end(begDate);
                            postSetaEntity.setModtime(new Date());
                            ormService.updateSelective(postSetaEntity);
                        }
                    }
                }
                // 更新单据的生效日期
                EmppostchangeapplyEntity entity = new EmppostchangeapplyEntity();
                entity.setId(id);
                entity.setOepc_beg(begDate);
                entity.setOrde_status(OrderConstants.ORDE_STATUS_5);
                //UPDATE emppostchangeapply SET oepc_beg = ? , modtime = ? WHERE id = ?
                ormService.updateSelective(entity);
            } else {
                return catchNoResult("未找到单据'" + id + "'的信息");
            }
        } catch (Exception e) {
            return catchException(e);
        }
        return result;
    }

    @TxTransaction(rollbackFor = Exception.class)
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public Result auditOrder(JSONObject auditSet) throws Exception {
        Result result = new Result();
        String auditKey = auditSet.getString(WorkFlowConstants.PARAM_AUDITKEY);
        String formState = auditSet.getString(WorkFlowConstants.PARAM_FORMSTATE);
        String actInstanceId = auditSet.getString(WorkFlowConstants.PARAM_ACT_INSTANCE_ID);
        String opinion = NullUtils.valueOf(auditSet.getString(WorkFlowConstants.PARAM_OPINION));
        if (StringUtil.isNullOrEmpty(auditKey)) {
            result.setErrMsg("请传入参数" + WorkFlowConstants.PARAM_AUDITKEY);
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            return result;
        } else if (StringUtil.isNullOrEmpty(formState)) {
            result.setErrMsg("请传入参数" + WorkFlowConstants.PARAM_FORMSTATE);
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            return result;
        } else if (StringUtil.isNullOrEmpty(actInstanceId)) {
            result.setErrMsg("请传入参数" + WorkFlowConstants.PARAM_ACT_INSTANCE_ID);
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            return result;
        }
        // 调用流程
        bizFormService.audit(actInstanceId, opinion, auditKey);
        result.setRetCode(Result.RECODE_SUCCESS);
        return result;
    }

    /**
     * load 加载 OepcChangSet
     * getAddOrderOepcChangSetMaps
     *
     * @param pid
     * @return
     * @throws Exception
     */
    private List<Map<String, Object>> getAddOrderOepcChangSetMaps(String pid) throws Exception {
        OrmParam ormParam = new OrmParam();
        // 部门id,岗位id,任职方式,员工id,pid,id
        ormParam.addColumn(EmpPostChangeApplyConstant.OEPC_DEPT)
                .addColumn(EmpPostChangeApplyConstant.OEPC_POST)
                .addColumn(EmpPostChangeApplyConstant.OEPC_DTYP_TYPE)
                .addColumn(EmpPostChangeApplyConstant.OEPC_EMP)
                .addColumn(EmpPostChangeApplyConstant.PID)
                .addColumn(EmpPostChangeApplyConstant.ID);
        // 条件: By PID
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.PID, pid)));
        ormParam.addOrderExpElement(SQLSortEnum.DESC, EmpPostChangeApplyConstant.OEPC_EMP);
        //SELECT oepc_dept , oepc_post , oepc_dtyp_type , oepc_emp , pid , id FROM oepc_oepc_chang_seta WHERE pid = ? AND is_del = 0
        return ormService.selectMapList(OepcOepcChangSetaEntity.class, ormParam);
    }

    private List<Map<String, Object>> getEditOrderOepcChangSetMaps(String pid) throws Exception {
        OrmParam ormParam = new OrmParam();
        // 部门id,岗位id,任职方式,旧部门id，旧任职方式，旧岗位id，是否保留旧岗位，员工id,pid,id
        ormParam.addColumn(EmpPostChangeApplyConstant.OEPC_DEPT)
                .addColumn(EmpPostChangeApplyConstant.OEPC_POST)
                .addColumn(EmpPostChangeApplyConstant.OEPC_DTYP_TYPE)
                .addColumn(EmpPostChangeApplyConstant.OEPC_DEPT_OLD)
                .addColumn(EmpPostChangeApplyConstant.OEPC_DTYP_OLD)
                .addColumn(EmpPostChangeApplyConstant.OEPC_POST_OLD)
                .addColumn(EmpPostChangeApplyConstant.OEPC_IS_KEEP)
                .addColumn(EmpPostChangeApplyConstant.OEPC_CHGR_TYPE)
                .addColumn(EmpPostChangeApplyConstant.OEPC_EMP)
                .addColumn(EmpPostChangeApplyConstant.PID)
                .addColumn(EmpPostChangeApplyConstant.ID);
        // 条件: By PID
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.PID, pid)));
        ormParam.addOrderExpElement(SQLSortEnum.DESC, EmpPostChangeApplyConstant.OEPC_EMP);
        //SELECT oepc_dept , oepc_post , oepc_dtyp_type , oepc_dept_old , oepc_dtyp_old , oepc_post_old , oepc_iskeep , oepc_emp , pid , id FROM oepc_oepc_chang_seta WHERE pid = ? AND is_del = 0
        return ormService.selectMapList(OepcOepcChangSetaEntity.class, ormParam);
    }

    private List<Map<String, Object>> getDeleteOrderOepcChangSetMaps(String pid) throws Exception {
        OrmParam ormParam = new OrmParam();
        // 岗位id，员工id,pid,id
        ormParam.addColumn(EmpPostChangeApplyConstant.OEPC_POST)
                .addColumn(EmpPostChangeApplyConstant.OEPC_EMP);
        // 条件: By PID
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.PID, pid)));
        //SELECT  oepc_post_old , oepc_emp  FROM oepc_oepc_chang_seta WHERE pid = ? AND is_del = 0
        return ormService.selectMapList(OepcOepcChangSetaEntity.class, ormParam);
    }

    private JSONObject saveAddOrEditOrder(EmppostchangeapplyEntity entity, String prefix) throws Exception {
        String orderId;
        String orderNbr="";
        JSONObject returnData = new JSONObject();
        orderId = entity.getId();

        if (StringUtil.isNullOrEmpty(orderId)) {
            // 生成单据单号
            orderNbr = getOrderNbr(prefix);
            //设置单据新的属性值
            entity.setOrde_nbr(orderNbr);
            entity.setOrde_status(OrderConstants.ORDE_STATUS_1);
            // 新增一条临时单记录
            orderId = (String) ormService.insertSelective(entity);
        } else {
            entity.setOrde_status(OrderConstants.ORDE_STATUS_1);
            // 更新一条临时单记录By Id
            ormService.updateSelective(entity);
            // 取出记录中的单据单号返回
            OrmParam ormParam = new OrmParam();
            ormParam.addColumn(NodeConstant.ID).addColumn(OrderProperty.ORDE_NBR);
            ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.ID,orderId)));
            List<EmppostchangeapplyEntity> queryList = ormService.selectBeanList(EmppostchangeapplyEntity.class,ormParam);
            if(null!=queryList&&queryList.size()>0){
                EmppostchangeapplyEntity e1 = queryList.get(0);
                orderNbr = e1.getOrde_nbr();
            }
        }
        // 保存和更新属性集 oepc_chang_set
        // 1.先删除
        OrmParam ormParam = new OrmParam();
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.PID, orderId)));
        ormService.delete(OepcOepcChangSetaEntity.class, ormParam);
        /**
         *orderAddSessionInfo方法中，entity设置了 creuser和cretime字段，
         * 所以 下面这段代码就是给属性集 也设置 creuser和cretime等字段。
         */
        EdmUtil.setPropertyBaseEntitiesSysColumns(OepcOepcChangSetaEntity.class, entity,
                entity.getOepc_chang_set(), SQLCurdEnum.INSERT);

        // 这里暂时不使用批量插入，是防止有什么字段不能为null的情况导致数据无法成功插入。
        for (OepcOepcChangSetaEntity e : entity.getOepc_chang_set()) {
            e.setId(null);
            e.setClassName(EmpPostChangeApplyConstant.EDM_NAME);
            ormService.insertSelective(e);
        }
        returnData.put(NodeConstant.ID, orderId);
        returnData.put(EmpPostChangeApplyConstant.ORDE_NBR, orderNbr);
        returnData.put(OrderConstants.ORDE_RODE_OBJ, entity.getOrde_rode_obj());
        return returnData;
    }

    @Override
    @TxTransaction(rollbackFor = Exception.class)
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Result passOrderById(String orderInstanceId,
                                String handlerType) throws Exception {
        Result result = new Result();
        switch (handlerType) {
            case WFHandlerTypeConstants.PASS: {
                //TODO 单据状态改为 完成；将单据数据写入资源表
                return passHandlerTypePass(orderInstanceId);
            }
            case WFHandlerTypeConstants.REVOKE: {
                //TODO 单据状态改为 待提
                return updateOrderStatus(orderInstanceId, OrderConstants.ORDE_STATUS_1);
            }
            case WFHandlerTypeConstants.RETURN_BACK: {
                //TODO 单据状态改为 退回
                return updateOrderStatus(orderInstanceId, OrderConstants.ORDE_STATUS_6);
            }
            default: {
                result.setRetCode(Result.RECODE_SUCCESS);
                result.setErrMsg("未找到" + handlerType + "处理方式");
                return result;
            }
        }
    }

    protected Result updateOrderStatus(String orderInstanceId, String status) throws Exception {
        Result result = new Result();

        EmppostchangeapplyEntity entity = new EmppostchangeapplyEntity();
        entity.setId(orderInstanceId);
        entity.setOrde_status(status);
        ormService.updateSelective(entity);
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setErrMsg("更新单据状态成功");

        return result;
    }


    protected Result passHandlerTypePass(String orderInstanceId) throws Exception {
        OrmParam ormParam = new OrmParam();
        //  查询单据类型
        ormParam.addColumn(EmpPostChangeApplyConstant.OEPC_TYPE);
        // 条件: By Id
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.ID, orderInstanceId)));

        List<Map<String, Object>> queryList = ormService.selectMapList(EmppostchangeapplyEntity.class, ormParam);
        if (null != queryList && queryList.size() > 0) {
            String orderType = NullUtils.valueOf(queryList.get(0).get(EmpPostChangeApplyConstant.OEPC_TYPE));
            Result result = new Result();
            result.setRetCode(Result.RECODE_SUCCESS);
            if (orderType.equals(EmpPostChangeApplyConstant.ADD_ORDER)) {
                result = passAddOrder(orderInstanceId);
            } else if (orderType.equals(EmpPostChangeApplyConstant.EDIT_ORDER)) {
                result = passEditOrder(orderInstanceId);
            } else if (orderType.equals(EmpPostChangeApplyConstant.CANCEL_ORDER)) {
                result = passDeleteOrder(orderInstanceId);
            } else {
                result.setRetCode(Result.RECODE_SUCCESS);
                result.setErrMsg("单据类型'" + orderType + "'未定义");
            }
            return result;
        } else {
            return catchNoResult("未找到单据'" + orderInstanceId + "'的信息");
        }

    }

    /**
     * CRUD操作出现异常捕获代码，属于重复代码，重构成方法
     *
     * @param e
     * @return
     */
    private Result catchException(Exception e) {
        Result result = new Result();
        e.printStackTrace();
        result.setData(null);
        result.setErrMsg(e.getMessage());
        result.setRetCode(Result.RECODE_ERROR);
        return result;
    }

    /**
     * 未查询到结果
     *
     * @param msg
     * @return
     */
    private Result catchNoResult(String msg) {
        Result result = new Result();
        result.setData(null);
        result.setErrMsg(msg);
        result.setRetCode(Result.RECODE_VALIDATE_ERROR);
        return result;
    }

    /**
     * loadAddOrder中 属性set中 load员工信息根据id
     *
     * @param oepcEmp
     * @return
     * @throws Exception
     */
    private JSONObject loadAddOrderLoadEmployee(String oepcEmp) throws Exception {
        OrmParam ormParam = getLoadEmployeeOrmParam(oepcEmp);
        JSONObject returnData = new JSONObject();
        try {
            //SELECT id , remp_post , remp_no , remp_name , remp_gender , remp_pgrade , remp_mcop , remp_in_date , remp_birth , remp_work_date FROM employee WHERE id = ? AND is_del = 0
            List<Map<String, Object>> queryList = ormService.selectMapList(EmployeeEntity.class, ormParam);

            if (null != queryList && queryList.size() > 0) {

                Map<String, Object> map = queryList.get(0);
                String postId = NullUtils.valueOf(map.get(EmployeeConstants.REMP_POST));
                // 得到工龄，年龄，在职月，在岗月
                getAllTimes(returnData, map, postId);

                returnData.put(NodeConstant.ID, NullUtils.valueOf(map.get(NodeConstant.ID)));
                returnData.put(EmployeeConstants.REMP_NO, NullUtils.valueOf(map.get(EmployeeConstants.REMP_NO)));
                returnData.put(EmployeeConstants.REMP_POST, postId);
                returnData.put(EmployeeConstants.REMP_NAME, NullUtils.valueOf(map.get(EmployeeConstants.REMP_NAME)));
                returnData.put(EmployeeConstants.REMP_GENDER, NullUtils.valueOf(map.get(EmployeeConstants.REMP_GENDER)));
                returnData.put(EmployeeConstants.REMP_PGRADE, NullUtils.valueOf(map.get(EmployeeConstants.REMP_PGRADE)));

                // 法人公司名称
                returnData.putAll(loadAddOrderLoadMCOP(NullUtils.valueOf(map.get(EmployeeConstants.REMP_MCOP))));

                // 教育经历
                returnData.putAll(loadAddOrderLoadStudy(oepcEmp));

            } else {
                throw new Exception("未查到id为'" + oepcEmp + "'的员工信息");
            }
        } catch (Exception e) {
            throw e;
        }
        return returnData;
    }

    private void getAllTimes(JSONObject returnData, Map<String, Object> map, String postId) throws Exception {
        double inPostMonths = 0.0;
        double onJobMonths = 0.0;
        double workYears = 0.0;
        int rempAge = 0;
        //获取当前时间
        Date nowTime = new Date();
        //计算在职月
        Object str = map.get(EmployeeConstants.REMP_IN_DATE);
        if (!StringUtil.isNullOrEmpty(str)) {
            java.sql.Timestamp date = (java.sql.Timestamp) str;
            onJobMonths = DateDiff.monthsDateDiff(date.getTime(), nowTime);
        }
        //计算工龄
        str = map.get(EmployeeConstants.REMP_WORK_DATE);
        if (!StringUtil.isNullOrEmpty(str)) {
            java.sql.Timestamp date = (java.sql.Timestamp) str;
            workYears = DateDiff.yearDateDiff(date.getTime(), nowTime);
        }
        //计算年龄
        str = map.get(EmployeeConstants.REMP_BIRTH);
        if (!StringUtil.isNullOrEmpty(str)) {
            java.sql.Timestamp date = (java.sql.Timestamp) str;
            BigDecimal b1 = new BigDecimal(DateDiff.yearDateDiff(date.getTime(), nowTime));
            rempAge = b1.intValue();
        }
        // 计算在岗月
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(EmployeeConstants.REMP_POST_BEG);
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.PID, NullUtils.valueOf(map.get(NodeConstant.ID)))
                , ormParam.getEqualXML(RempRempPostSetaProperty.REMP_POST_HIS, postId)));
        List<Map<String, Object>> list = ormService.selectMapList(RempRempPostSetaEntity.class, ormParam);
        if (null != list && list.size() > 0) {
            Object strp = map.get(EmployeeConstants.REMP_POST_BEG);
            if (!StringUtil.isNullOrEmpty(strp)) {
                java.sql.Timestamp date = (java.sql.Timestamp) strp;
                inPostMonths = DateDiff.monthsDateDiff(date.getTime(), nowTime);
            }
        }
        returnData.put(EmployeeConstants.WORK_MONTHS, inPostMonths);
        returnData.put(EmployeeConstants.ON_JOB_MONTHS, onJobMonths);
        returnData.put(EmployeeConstants.WORK_YEARS, workYears);
        returnData.put(EmployeeConstants.REMP_AGE, rempAge);
    }

    private OrmParam getLoadEmployeeOrmParam(String oepcEmp) {
        OrmParam ormParam = new OrmParam();
        // id,岗位id,工号，姓名，性别，法人公司，到职日，出生日期，首次工作日期
        ormParam.addColumn(NodeConstant.ID)
                .addColumn(EmployeeConstants.REMP_POST)
                .addColumn(EmployeeConstants.REMP_NO)
                .addColumn(EmployeeConstants.REMP_NAME)
                .addColumn(EmployeeConstants.REMP_GENDER)
                .addColumn(EmployeeConstants.REMP_PGRADE)
                .addColumn(EmployeeConstants.REMP_MCOP)
                .addColumn(EmployeeConstants.REMP_IN_DATE)
                .addColumn(EmployeeConstants.REMP_BIRTH)
                .addColumn(EmployeeConstants.REMP_WORK_DATE);
        // 条件: By Id
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.ID, oepcEmp)));
        return ormParam;
    }

    /**
     * loadAddOrder中 属性set中 load法人公司根据法人公司id
     *
     * @param mcop
     * @return
     * @throws Exception
     */
    private JSONObject loadAddOrderLoadMCOP(String mcop) throws Exception {
        OrmParam ormParam = new OrmParam();
        // 法人公司名称
        ormParam.addColumn(RelationConstants.RELA_NAME);
        ormParam.addColumn(RelationConstants.RELA_SHORT_NAME);
        // 条件: By Id
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.ID, mcop)));
        JSONObject returnData = new JSONObject();

        //SELECT rela_name FROM relation WHERE id = ? AND is_del = 0
        List<Map<String, Object>> queryList = ormService.selectMapList(RelationEntity.class, ormParam);
        if (null != queryList && queryList.size() > 0) {
            Map<String, Object> map = queryList.get(0);
            returnData.put(RelationConstants.RELA_NAME, NullUtils.valueOf(map.get(RelationConstants.RELA_NAME)));
            returnData.put(RelationConstants.RELA_SHORT_NAME, NullUtils.valueOf(map.get(RelationConstants.RELA_SHORT_NAME)));
        } else {
            returnData.put(RelationConstants.RELA_NAME, "");
            returnData.put(RelationConstants.RELA_SHORT_NAME, "");
            //throw  new Exception("未查到id为'"+mcop+"'的法人公司信息");
        }

        return returnData;
    }

    /**
     * loadAddOrder中 属性set中 load岗位
     *
     * @param oepcPost
     * @return
     * @throws Exception
     */
    private JSONObject loadAddOrderLoadJob(String oepcPost) throws Exception {
        OrmParam ormParam = new OrmParam();
        // 根据岗位id查询岗位名称、汇报岗位
        ormParam.addColumn(JobPositionConstants.RPOS_PPOST)
                .addColumn(JobPositionConstants.RPOS_NAME)
                .addColumn(JobPositionConstants.RPOS_GRADE);
        // 条件: By Id
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.ID, oepcPost)));
        JSONObject returnData = new JSONObject();

        //SELECT rpos_duty_type , rpos_ppost , rpos_name FROM jobposition WHERE id = ? AND is_del = 0
        List<Map<String, Object>> queryList = ormService.selectMapList(JobpositionEntity.class, ormParam);

        if (null != queryList && queryList.size() > 0) {
            Map<String, Object> map = queryList.get(0);
            returnData.put(JobPositionConstants.RPOS_NAME, NullUtils.valueOf(map.get(JobPositionConstants.RPOS_NAME)));
            returnData.put(JobPositionConstants.RPOS_GRADE, NullUtils.valueOf(map.get(JobPositionConstants.RPOS_GRADE)));
            returnData.putAll(loadAddOrderLoadPJob(NullUtils.valueOf(map.get(JobPositionConstants.RPOS_PPOST))));
        } else {
            returnData.put(JobPositionConstants.RPOS_NAME, "");
            returnData.put(JobPositionConstants.RPOS_GRADE, "");
            returnData.put(JobPositionConstants.RPOS_PPOST, "");
            //throw  new Exception("未查到id为'"+oepcPost+"'的岗位信息");
        }

        return returnData;
    }

    /**
     * loadAddOrder中 属性set中 load汇报岗位直属上级
     *
     * @param pPostId
     * @return
     * @throws Exception
     */
    private JSONObject loadAddOrderLoadPJob(String pPostId) throws Exception {
        OrmParam ormParam = new OrmParam();
        // 根据岗位id查询岗位名称
        ormParam.addColumn(JobPositionConstants.RPOS_EMP);
        // 条件: By Id
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.ID, pPostId)));
        JSONObject returnData = new JSONObject();

        //SELECT rpos_emp FROM jobposition WHERE id = ? AND is_del = 0
        List<Map<String, Object>> queryList = ormService.selectMapList(JobpositionEntity.class, ormParam);

        if (null != queryList && queryList.size() > 0) {
            Map<String, Object> map = queryList.get(0);
            if (null != map) {
                returnData.put(JobPositionConstants.RPOS_PPOST, NullUtils.valueOf(map.get(JobPositionConstants.RPOS_EMP)));
            } else {
                returnData.put(JobPositionConstants.RPOS_PPOST, "");
            }
        } else {
            returnData.put(JobPositionConstants.RPOS_PPOST, "");
            // throw  new Exception("未查到id为'"+pPostId+"'的汇报岗位直属上级岗位信息");
        }

        return returnData;
    }

    /**
     * loadAddOrder中 属性set中 load教育信息
     *
     * @param pid
     * @return
     * @throws Exception
     */
    private JSONObject loadAddOrderLoadStudy(String pid) throws Exception {
        OrmParam ormParam = new OrmParam();
        // 根据岗位id查询教育经历：学校，学历，毕业时间，专业
        ormParam.addColumn(EmployeeConstants.REMP_RSCH)
                .addColumn(EmployeeConstants.REMP_DEGREE)
                .addColumn(EmployeeConstants.REMP_STU_END)
                .addColumn(EmployeeConstants.REMP_MAJOR);
        // 条件: By Id
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.PID, pid)));
        ormParam.addOrderExpElement(SQLSortEnum.DESC, EmployeeConstants.REMP_STU_END);
        JSONObject returnData = new JSONObject();

        //SELECT remp_rsch , remp_degree , remp_stu_end , remp_major FROM remp_remp_study_seta WHERE pid = ? AND is_del = 0 ORDER BY remp_stu_end DESC
        List<Map<String, Object>> queryList = ormService.selectMapList(RempRempStudySetaEntity.class, ormParam);

        if (null != queryList && queryList.size() > 0) {
            Map<String, Object> map = queryList.get(0);
            Object rsch = map.get(EmployeeConstants.REMP_RSCH);
            if (null != rsch) {
                ormParam.reset();
                ormParam.addColumn(EmployeeConstants.RSCH_NAME);
                ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.ID, rsch.toString())));
                List<Map<String, Object>> slist = ormService.selectMapList(SchoolEntity.class, ormParam);
                if (null != slist && slist.size() > 0) {
                    returnData.put(EmployeeConstants.REMP_RSCH, rsch.toString());
                    returnData.put(EmployeeConstants.REMP_RSCH_NAME, NullUtils.valueOf(slist.get(0).get(EmployeeConstants.RSCH_NAME)));
                } else {
                    returnData.put(EmployeeConstants.REMP_RSCH, "");
                    returnData.put(EmployeeConstants.REMP_RSCH_NAME, "");
                }
            }
            returnData.put(EmployeeConstants.REMP_DEGREE, NullUtils.valueOf(map.get(EmployeeConstants.REMP_DEGREE)));
            returnData.put(EmployeeConstants.REMP_MAJOR, NullUtils.valueOf(map.get(EmployeeConstants.REMP_MAJOR)));

        } else {
            returnData.put(EmployeeConstants.REMP_RSCH, "");
            returnData.put(EmployeeConstants.REMP_RSCH_NAME, "");
            returnData.put(EmployeeConstants.REMP_DEGREE, "");
            returnData.put(EmployeeConstants.REMP_MAJOR, "");
            //throw  new Exception("未查到pid为'"+pid+"'员工的教育信息");
        }

        return returnData;
    }

    /**
     * loadAddOrder中 办公园区
     *
     * @param deptId
     * @return
     * @throws Exception
     */
    private JSONObject loadAddOrderPark(String deptId) throws Exception {
        JSONObject returnData = new JSONObject();
        OrmParam ormParam = new OrmParam();
        // 根据部门id查询办公园区id
        ormParam.addColumn(DeptTreeConstants.MDEP_RPAK);
        // 条件: By Id
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.ID, deptId)));
        List<Map<String, Object>> queryList = ormService.selectMapList(DepttreeEntity.class, ormParam);
        if (null != queryList && queryList.size() > 0) {
            Map<String, Object> map = queryList.get(0);
            ormParam.reset();
            ormParam.addColumn(ParkConstants.RPAK_NAME);
            // 条件: By Id
            ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.ID, NullUtils.valueOf(map.get(DeptTreeConstants.MDEP_RPAK)))));
            queryList = ormService.selectMapList(ParkEntity.class, ormParam);
            if (null != queryList && queryList.size() > 0) {
                returnData.put(DeptTreeConstants.MDEP_RPAK_NAME, NullUtils.valueOf(queryList.get(0).get(ParkConstants.RPAK_NAME)));
            } else {
                returnData.put(DeptTreeConstants.MDEP_RPAK_NAME, "");
            }
        } else {
            returnData.put(DeptTreeConstants.MDEP_RPAK_NAME, "");
        }

        return returnData;
    }

    /**
     * 获取单据的基本信息包括 单据id,生效日期,单据类型,单据备注信息,单据单号
     *
     * @param id
     * @return
     * @throws Exception
     */
    private JSONObject loadOrderBaseInfo(String id) throws Exception {
        OrmParam ormParam = new OrmParam();
        // 单据id,生效日期,单据类型,单据备注信息,单据单号
        ormParam.addColumn(NodeConstant.ID)
                .addColumn(EdmPostchangeapplyConstants.OEPC_BEG)
                .addColumn(EdmPostchangeapplyConstants.OEPC_TYPE)
                .addColumn(EdmProperty.EDMD_CLASS)
                .addColumn(EdmPostchangeapplyConstants.OEPC_REMARK)
                .addColumn(EdmPostchangeapplyConstants.ORDE_NBR)
                .addColumn(EdmPostchangeapplyConstants.OEPC_DEPT_BEG)
                .addColumn(OrderConstants.ORDE_RODE_OBJ)
                .addColumn(OrderConstants.ORDE_ADDUSER)
                .addColumn(OrderConstants.ORDE_DUTY)
                .addColumn(OrderConstants.ORDE_DATE)
                .addColumn(OrderConstants.ORDE_STATUS)
                .addColumn(OrderConstants.ORDE_DEPT);
        // 条件: By Id
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.ID, id)));
        //SELECT id , oepc_beg , oepc_type , oepc_remark , orde_nbr FROM emppostchangeapply WHERE id = ? AND is_del = 0
        List<Map<String, Object>> queryList = ormService.selectMapList(EmppostchangeapplyEntity.class, ormParam);
        // 待返回的数据
        JSONObject returnData = new JSONObject();
        if (null != queryList && queryList.size() > 0) {
            Map<String, Object> map = queryList.get(0);
            // 生效日期
            if (null != map.get(EdmPostchangeapplyConstants.OEPC_BEG)) {
                java.sql.Timestamp date = (java.sql.Timestamp) map.get(EdmPostchangeapplyConstants.OEPC_BEG);
                returnData.put(EdmPostchangeapplyConstants.OEPC_BEG, NullUtils.valueOf(date.getTime()));
            } else {
                returnData.put(EdmPostchangeapplyConstants.OEPC_BEG, "");
            }
            // 制单日期
            if (null != map.get(OrderConstants.ORDE_DATE)) {
                java.sql.Timestamp date = (java.sql.Timestamp) map.get(OrderConstants.ORDE_DATE);
                returnData.put(OrderConstants.ORDE_DATE, NullUtils.valueOf(date.getTime()));
            } else {
                returnData.put(OrderConstants.ORDE_DATE, "");
            }
            // 制单人id
            String empId = NullUtils.valueOf(map.get(OrderConstants.ORDE_ADDUSER));
            String deptId = NullUtils.valueOf(map.get(OrderConstants.ORDE_DEPT));
            String postId = NullUtils.valueOf(map.get(OrderConstants.ORDE_DUTY));
            // 所属部门id
            String oepcDeptBeg = NullUtils.valueOf(map.get(EdmPostchangeapplyConstants.OEPC_DEPT_BEG));

            returnData.put(OrderConstants.ORDE_ADDUSER, empId);
            // 制单部门id
            returnData.put(OrderConstants.ORDE_DEPT, deptId);
            // 制单岗位id
            returnData.put(OrderConstants.ORDE_DUTY, postId);
            // 单据定义类型id
            returnData.put(OrderConstants.ORDE_RODE_OBJ, NullUtils.valueOf(map.get(OrderConstants.ORDE_RODE_OBJ)));
            // 单据状态
            returnData.put(OrderConstants.ORDE_STATUS, NullUtils.valueOf(map.get(OrderConstants.ORDE_STATUS)));
            // 所属部门
            returnData.put(EdmPostchangeapplyConstants.OEPC_DEPT_BEG, NullUtils.valueOf(oepcDeptBeg));

            if (!StringUtil.isNullOrEmpty(empId)) {
                // 制单人名字
                returnData.put(OrderConstants.ORDE_ADDUSER_NAME, NullUtils.valueOf(loadEditOrderLoadEmpName(empId)));
                // 制单人工号
                returnData.put(OrderConstants.ORDE_ADDUSER_NO, NullUtils.valueOf(loadEditOrderLoadEmpNo(empId)));
            } else {
                returnData.put(OrderConstants.ORDE_ADDUSER_NAME, "");
                returnData.put(OrderConstants.ORDE_ADDUSER_NO, "");
            }
            if (!StringUtil.isNullOrEmpty(deptId)) {
                returnData.put(OrderConstants.ORDE_DEPT_NAME, NullUtils.valueOf(loadEditOrderLoadDeptName(deptId)));
            } else {
                returnData.put(OrderConstants.ORDE_DEPT_NAME, "");
            }
            if (!StringUtil.isNullOrEmpty(postId)) {
                returnData.put(OrderConstants.ORDE_DUTY_NAME, NullUtils.valueOf(loadEditOrderLoadPostName(postId)));
            } else {
                returnData.put(OrderConstants.ORDE_DUTY_NAME, "");
            }

            if (!StringUtil.isNullOrEmpty(oepcDeptBeg)) {
                returnData.put(EdmPostchangeapplyConstants.OEPC_DEPT_BEG_NAME, NullUtils.valueOf(loadEditOrderLoadDeptName(oepcDeptBeg)));
            } else {
                returnData.put(EdmPostchangeapplyConstants.OEPC_DEPT_BEG_NAME, "");
            }
            //EDM_Class
            returnData.put(EdmProperty.EDMD_CLASS, NullUtils.valueOf(map.get(EdmProperty.EDMD_CLASS)));
            // 单据类型
            returnData.put(EdmPostchangeapplyConstants.OEPC_TYPE, NullUtils.valueOf(map.get(EdmPostchangeapplyConstants.OEPC_TYPE)));
            // 备注信息
            returnData.put(EdmPostchangeapplyConstants.OEPC_REMARK, NullUtils.valueOf(map.get(EdmPostchangeapplyConstants.OEPC_REMARK)));
            // 单据单号
            returnData.put(EdmPostchangeapplyConstants.ORDE_NBR, NullUtils.valueOf(map.get(EdmPostchangeapplyConstants.ORDE_NBR)));
        } else {
            return null;
        }
        return returnData;
    }

    /**
     * 根据部门id查询部门名称
     *
     * @param deptId
     * @return
     * @throws Exception
     */
    private String loadEditOrderLoadDeptName(String deptId) throws Exception {
        OrmParam ormParam = new OrmParam();
        // 根据部门id查询部门名称
        ormParam.addColumn(DeptTreeConstants.MDEP_NAME);
        // 条件: By Id
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.ID, deptId)));
        //SELECT mdep_name FROM depttree WHERE id = ? AND is_del = 0
        List<Map<String, Object>> queryList = ormService.selectMapList(DepttreeEntity.class, ormParam);
        if (null != queryList && queryList.size() > 0) {
            Map<String, Object> map = queryList.get(0);
            return NullUtils.valueOf(map.get(DeptTreeConstants.MDEP_NAME));
        }
        return "";
    }

    private String loadEditOrderLoadDeptMCop(String deptId) throws Exception {
        OrmParam ormParam = new OrmParam();
        // 根据部门id查询部门名称
        ormParam.addColumn(DeptTreeConstants.MDEP_MCOP);
        // 条件: By Id
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.ID, deptId)));
        //SELECT mdep_name FROM depttree WHERE id = ? AND is_del = 0
        List<Map<String, Object>> queryList = ormService.selectMapList(DepttreeEntity.class, ormParam);
        if (null != queryList && queryList.size() > 0) {
            Map<String, Object> map = queryList.get(0);
            return NullUtils.valueOf(map.get(DeptTreeConstants.MDEP_MCOP));
        }
        return "";
    }

    /**
     * 根据岗位id查询岗位名称
     *
     * @param postId
     * @return
     * @throws Exception
     */
    private String loadEditOrderLoadPostName(String postId) throws Exception {
        OrmParam ormParam = new OrmParam();
        // 根据岗位id查询岗位名称
        ormParam.addColumn(JobPositionConstants.RPOS_NAME);
        // 条件: By Id
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.ID, postId)));
        //SELECT rpos_name FROM jobposition WHERE id = ? AND is_del = 0
        List<Map<String, Object>> queryList = ormService.selectMapList(JobpositionEntity.class, ormParam);
        if (null != queryList && queryList.size() > 0) {
            Map<String, Object> map = queryList.get(0);
            return NullUtils.valueOf(map.get(JobPositionConstants.RPOS_NAME));
        }
        return "";
    }

    private String loadPostEmp(String postId) throws Exception {
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(JobPositionConstants.RPOS_EMP);
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.ID, postId)));
        List<Map<String, Object>> queryList = ormService.selectMapList(JobpositionEntity.class, ormParam);
        if (null != queryList && queryList.size() > 0) {
            Map<String, Object> map = queryList.get(0);
            return NullUtils.valueOf(map.get(JobPositionConstants.RPOS_EMP));
        }
        return "";
    }

    /**
     * 根据员工id查询员工姓名
     *
     * @param empId
     * @return
     * @throws Exception
     */
    private String loadEditOrderLoadEmpName(String empId) throws Exception {
        OrmParam ormParam = new OrmParam();
        //  根据员工id查询员工姓名
        ormParam.addColumn(EmployeeConstants.REMP_NAME);
        // 条件: By Id
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.ID, empId)));
        // SELECT remp_name FROM employee WHERE id = ? AND is_del = 0
        List<Map<String, Object>> queryList = ormService.selectMapList(EmployeeEntity.class, ormParam);
        if (null != queryList && queryList.size() > 0) {
            Map<String, Object> map = queryList.get(0);
            return NullUtils.valueOf(map.get(EmployeeConstants.REMP_NAME));
        }
        return "";
    }

    /**
     * 查询岗级
     *
     * @param postId
     * @return
     * @throws Exception
     */
    private String loadEditOrderLoadPgrade(String postId) throws Exception {
        OrmParam ormParam = new OrmParam();
        //  根据员工id查询员工岗级
        ormParam.addColumn(JobPositionConstants.RPOS_GRADE);
        // 条件: By Id
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.ID, postId)));
        List<Map<String, Object>> queryList = ormService.selectMapList(JobpositionEntity.class, ormParam);
        if (null != queryList && queryList.size() > 0) {
            Map<String, Object> map = queryList.get(0);
            return NullUtils.valueOf(map.get(JobPositionConstants.RPOS_GRADE));
        }
        return "";
    }

    private String loadEditOrderLoadEmpNo(String empId) throws Exception {
        OrmParam ormParam = new OrmParam();
        //  根据员工id查询员工工号
        ormParam.addColumn(EmployeeConstants.REMP_NO);
        // 条件: By Id
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.ID, empId)));
        // SELECT remp_name FROM employee WHERE id = ? AND is_del = 0
        List<Map<String, Object>> queryList = ormService.selectMapList(EmployeeEntity.class, ormParam);
        if (null != queryList && queryList.size() > 0) {
            Map<String, Object> map = queryList.get(0);
            return NullUtils.valueOf(map.get(EmployeeConstants.REMP_NO));
        }
        return "";
    }

    /**
     * 根据岗位id获得岗级和汇报岗位的岗位id
     *
     * @param postId
     * @return
     * @throws Exception
     */
    private JSONObject getPgradeAndPpostId(String postId) throws Exception {
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(JobPositionConstants.RPOS_PPOST)
                .addColumn(JobPositionConstants.RPOS_GRADE);
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.ID, postId)));
        //员工类.任岗经历.岗级
        String rposGrade = "";
        //员工类.任岗经历.汇报上级
        String rposEmp = "";
        //SELECT rpos_ppost , rpos_grade FROM jobposition WHERE id = ? AND is_del = 0
        List<Map<String, Object>> postList = ormService.selectMapList(JobpositionEntity.class, ormParam);
        JSONObject jsonObject = new JSONObject();
        if (null != postList && postList.size() > 0) {
            Map<String, Object> rMap1 = postList.get(0);
            rposGrade = NullUtils.valueOf(rMap1.get(JobPositionConstants.RPOS_GRADE));
            ormParam.reset();
            ormParam.addColumn(JobPositionConstants.RPOS_EMP);
            ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.ID, NullUtils.valueOf(rMap1.get(JobPositionConstants.RPOS_PPOST)))));
            //SELECT rpos_emp FROM jobposition WHERE id = ? AND is_del = 0
            List<Map<String, Object>> pPostList = ormService.selectMapList(JobpositionEntity.class, ormParam);
            if (null != pPostList && pPostList.size() > 0) {
                Map<String, Object> rMap2 = pPostList.get(0);
                rposEmp = NullUtils.valueOf(rMap2.get(JobPositionConstants.RPOS_EMP));
            }
        }
        jsonObject.put(JobPositionConstants.RPOS_GRADE, rposGrade);
        jsonObject.put(JobPositionConstants.RPOS_EMP, rposEmp);
        return jsonObject;
    }

    /**
     * 根据岗位id，查询任岗记录
     *
     * @param postId
     * @return
     * @throws Exception
     */
    private JSONObject loadRempRempPostSetIdByPostId(String postId) throws Exception {
        OrmParam ormParam = new OrmParam();
        //  根据岗位id，查询任岗记录 id,岗位，岗级，汇报上级
        ormParam.addColumn(NodeConstant.ID)
                .addColumn(EmployeeConstants.REMP_POST_HIS)
                .addColumn(EmployeeConstants.REMP_PGRAD_HIS)
                .addColumn(EmployeeConstants.REMP_PEMP_HIS);
        // 条件: By Id
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(EmployeeConstants.REMP_POST_HIS, postId)));
        //SELECT id , remp_post_his , remp_pgrad_his , remp_pemp_his FROM remp_remp_post_seta WHERE remp_post_his = ? AND is_del = 0
        List<Map<String, Object>> queryList = ormService.selectMapList(RempRempPostSetaEntity.class, ormParam);
        if (null != queryList && queryList.size() > 0) {
            Map<String, Object> map = queryList.get(0);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(NodeConstant.ID, map.get(NodeConstant.ID));
            jsonObject.put(EmployeeConstants.REMP_POST_HIS, NullUtils.valueOf(map.get(EmployeeConstants.REMP_POST_HIS)));
            jsonObject.put(EmployeeConstants.REMP_PGRAD_HIS, NullUtils.valueOf(map.get(EmployeeConstants.REMP_PGRAD_HIS)));
            jsonObject.put(EmployeeConstants.REMP_PEMP_HIS, NullUtils.valueOf(map.get(EmployeeConstants.REMP_PEMP_HIS)));
            return jsonObject;
        }
        return null;
    }

    private boolean isPostVaild(String postId) throws Exception {
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(NodeConstant.ID)
                .addColumn(JobPositionConstants.RPOS_END);
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.ID, postId)));
        List<Map<String, Object>> queryList = ormService.selectMapList(JobpositionEntity.class, ormParam);
        if (null != queryList && queryList.size() > 0) {
            Map<String, Object> map = queryList.get(0);
            Object o = map.get(JobPositionConstants.RPOS_END);
            if (!StringUtil.isNullOrEmpty(o)) {
                java.sql.Timestamp date = (java.sql.Timestamp) o;
                return date.getTime() > System.currentTimeMillis();
            }
        }
        return true;
    }

    /**
     * 判断员工岗位是否已经注销
     * @param postId
     * @return
     * @throws Exception
     */
    private boolean isEmpPostCancel(String postId) throws Exception {
        String sql = "SELECT a.orde_nbr FROM emppostchangeapply as a\n" +
                "LEFT JOIN oepc_oepc_chang_seta  as b\n" +
                "on b.pid = a.id\n" +
                "WHERE b.oepc_post = '" + postId + "'\n" +
                "and a.oepc_type = '3'\n" +
                "and a.orde_status='5'\n" +
                "and a.is_del = 0";
        List<Map<String, Object>> countList = ormService.getDataBySql(sql);
        if (null != countList && countList.size() > 0) {
            String ordeNbr = NullUtils.valueOf(countList.get(0).get("orde_nbr"));
            if(!StringUtil.isNullOrEmpty(ordeNbr)){
                return true;
            }
        }
        return false;
    }


}
