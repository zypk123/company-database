package com.huntkey.rx.hr.provider.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.codingapi.tx.annotation.TxTransaction;
import com.github.pagehelper.PageHelper;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.edm.constant.*;
import com.huntkey.rx.edm.entity.*;
import com.huntkey.rx.hr.common.constants.NumberConstants;
import com.huntkey.rx.hr.common.constants.WFHandlerTypeConstants;
import com.huntkey.rx.hr.common.constants.WorkFlowConstants;
import com.huntkey.rx.hr.common.exception.ApplicationException;
import com.huntkey.rx.hr.common.model.*;
import com.huntkey.rx.hr.common.util.DateDiff;
import com.huntkey.rx.hr.common.util.DateUtils;
import com.huntkey.rx.hr.common.util.JsonUtils;
import com.huntkey.rx.hr.common.util.NullUtils;
import com.huntkey.rx.hr.provider.service.BaseService;
import com.huntkey.rx.hr.provider.service.BizFormService;
import com.huntkey.rx.hr.provider.service.PositiveApplyService;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;
import com.huntkey.rx.sceo.orm.common.type.SQLCurdEnum;
import com.huntkey.rx.sceo.orm.common.type.SQLSortEnum;
import com.huntkey.rx.sceo.orm.common.util.EdmUtil;
import com.huntkey.rx.sceo.orm.common.util.PersistentUtil;
import com.huntkey.rx.sceo.orm.service.OrmService;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;
import kafka.utils.Json;
import org.aspectj.weaver.ast.Or;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @author yaoss
 */
@Service
public class PositiveApplyServiceImpl extends BaseService implements PositiveApplyService {
    /**
     * 试用结果
     * 1 按期转正  2 延期转正  3. 不予转正 4. 提前转正
     */
    private final static String TURN_POSITIVE_ON_SCHEDULE = "1";
    private final static String POSTPONED_TO_POSITIVE = "2";
    private final static String NOT_CORRECT = "3";
    private final static String ADVANCE_TRANDFER = "4";
    /**
     * 员工状态 1 在职  2 试用  3. 离职
     */
    private final static String IN_SERVICE = "1";
    private final static String TRY_OUT = "2";
    private final static String LEAVE = "3";

    private final static String ORDE_NBR = OrderConstants.ORDE_NBR;

    @Autowired
    OrmService ormService;
    @Autowired
    BizFormService bizFormService;

    @Value("${nginxIp}")
    String nginxIp;

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public Result queryPositive(String deptCode, String starTime, String endTime, String no, String ordeStatus, String oepaAuditIdea, int startPage, int rows) {
        Result result = new Result();

        try {
            String depts = "";
            List<Object> allDept;
            if (!StringUtil.isNullOrEmpty(deptCode)) {
                allDept = getAllDept(deptCode);
                if (allDept.size() > 0) {
                    Object[] objects = allDept.toArray();
                    depts = " and remp_dept IN ( ";
                    if (objects.length == 1) {
                        depts += "'" + objects[0] + "')";
                    } else {
                        for (int i = 0; i < objects.length - 1; i++) {
                            depts += "'" + objects[i] + "',";
                        }
                        depts += "'" + objects[objects.length - 1] + "')";
                    }
                }
            }

            String baseSql = "SELECT emp.id , remp_name ,pos.id as emppostiveapplyId, audit.id as auditId, pos.orde_status,audit.oepa_audit_idea, pos.oepa_emp,remp_no , remp_gender , remp_post , remp_pgrade , remp_in_date , remp_post_date , remp_dept , remp_birth,remp_status \n" +
                    "FROM employee as emp\n " +
                    "LEFT JOIN emppostiveapply as pos ON  (emp.id=pos.oepa_emp and pos.is_del=0) \n " +
                    "LEFT JOIN oepa_oepa_audit_seta as audit ON pos.id=audit.pid " +
                    "where emp.is_del=0 and remp_status='2' ";

            String countSQL = "SELECT COUNT(emp.id) as count\n" +
                    "FROM employee as emp\n" +
                    "LEFT JOIN emppostiveapply as pos ON  (emp.id=pos.oepa_emp and pos.is_del=0) \n" +
                    "LEFT JOIN oepa_oepa_audit_seta as audit ON pos.id=audit.pid\n" +
                    "where emp.is_del=0 and remp_status='2'  ";

            String whereSql = "";
            if (!StringUtil.isNullOrEmpty(no)) {
                whereSql += " and emp.id = '" + no + "'";
            }
            if (!StringUtil.isNullOrEmpty(depts)) {
                whereSql += depts;
            }
            if (!StringUtil.isNullOrEmpty(starTime)) {
                whereSql += " and remp_post_date >= '" + starTime + "'";
            }
            if (!StringUtil.isNullOrEmpty(endTime)) {
                whereSql += " and remp_post_date <= '" + endTime + "'";
            }
            if (!StringUtil.isNullOrEmpty(ordeStatus)) {
                whereSql += " and pos.orde_status = '" + ordeStatus + "'";
            }
            if (!StringUtil.isNullOrEmpty(oepaAuditIdea)) {
                whereSql += " and audit.oepa_audit_idea = '" + oepaAuditIdea + "'";
            }
            if (startPage < 1) {
                startPage = 1;
            }
            String groupBy = "  GROUP BY remp_no  ";
            String limit = " ORDER BY audit.cretime desc  LIMIT " + (startPage - 1) * rows + "," + rows;

            String querySql = baseSql + whereSql + groupBy + limit;
            String countQuerySQL = countSQL + whereSql + groupBy;
            List<Map<String, Object>> list = ormService.getDataBySql(querySql);
            List<Map<String, Object>> count = ormService.getDataBySql(countQuerySQL);
            int totalSize = count.size();
            JSONObject returnData = new JSONObject();
            JSONArray listArray = new JSONArray();
            if (null != list && list.size() > 0) {
                for (Map<String, Object> map : list) {
                    JSONObject temp = new JSONObject();
                    String empId = NullUtils.valueOf(map.get(NodeConstant.ID));
                    String empDept = NullUtils.valueOf(map.get(EmployeeConstants.REMP_DEPT));
                    String rempPost = NullUtils.valueOf(map.get(EmployeeConstants.REMP_POST));
                    temp.put(NodeConstant.ID, empId);
                    temp.put(EmployeeConstants.REMP_NAME, NullUtils.valueOf(map.get(EmployeeConstants.REMP_NAME)));
                    temp.put(EmployeeConstants.REMP_NO, NullUtils.valueOf(map.get(EmployeeConstants.REMP_NO)));
                    temp.put(EmployeeConstants.REMP_GENDER, NullUtils.valueOf(map.get(EmployeeConstants.REMP_GENDER)));
                    temp.put(EmployeeConstants.REMP_POST, rempPost);
                    temp.put(EmployeeConstants.REMP_PGRADE, NullUtils.valueOf(map.get(EmployeeConstants.REMP_PGRADE)));
                    temp.put(EmployeeConstants.REMP_DEPT, empDept);
                    temp.put(EmployeeConstants.REMP_STATUS, NullUtils.valueOf(map.get(EmployeeConstants.REMP_STATUS)));
                    temp.put(OepaOepaAuditSetaProperty.OEPA_AUDIT_IDEA, NullUtils.valueOf(map.get(OepaOepaAuditSetaProperty.OEPA_AUDIT_IDEA)));

                    //计算年龄
                    Object str = map.get(EmployeeConstants.REMP_BIRTH);
                    if (!StringUtil.isNullOrEmpty(str)) {
                        java.sql.Timestamp date = (java.sql.Timestamp) str;
                        BigDecimal b1 = new BigDecimal(DateDiff.yearDateDiff(date.getTime(), new Date()));
                        temp.put(EmployeeConstants.AGE, b1.intValue());
                        temp.put(EmployeeConstants.REMP_BIRTH, NullUtils.valueOf(date.getTime()));
                    } else {
                        temp.put(EmployeeConstants.AGE, 0);
                        temp.put(EmployeeConstants.REMP_BIRTH, "");
                    }
                    str = map.get(EmployeeConstants.REMP_IN_DATE);
                    if (!StringUtil.isNullOrEmpty(str)) {
                        java.sql.Timestamp date = (java.sql.Timestamp) str;
                        temp.put(EmployeeConstants.REMP_IN_DATE, NullUtils.valueOf(date.getTime()));
                    } else {
                        temp.put(EmployeeConstants.REMP_IN_DATE, "");
                    }
                    str = map.get(EmployeeConstants.REMP_PSOT_DATE);
                    if (!StringUtil.isNullOrEmpty(str)) {
                        java.sql.Timestamp date = (java.sql.Timestamp) str;
                        temp.put(EmployeeConstants.REMP_PSOT_DATE, NullUtils.valueOf(date.getTime()));
                    } else {
                        temp.put(EmployeeConstants.REMP_PSOT_DATE, "");
                    }

                    temp.put(EdmApplyPositiveConstants.OEPA_ID_RETURN, NullUtils.valueOf(map.get(EdmApplyPositiveConstants.OEPA_EMPPOSITIVEAPPLY_ID)));
                    temp.put(OrderConstants.ORDE_STATUS, NullUtils.valueOf(map.get(OrderConstants.ORDE_STATUS)));

                    temp.putAll(loadDeptNameById(empDept));
                    temp.putAll(loadPostNameById(rempPost));
                    listArray.add(temp);
                }
            }
            returnData.put(EdmApplyPositiveConstants.PAGE_PAGENUM, startPage);
            returnData.put(EdmApplyPositiveConstants.PAGE_PAGESIZE, rows);
            returnData.put(EdmApplyPositiveConstants.PAGE_TOTAL, totalSize);
            returnData.put(EdmApplyPositiveConstants.PAGE_LIST, listArray);

            JsonUtils.underLine2Camel(returnData);
            result.setRetCode(Result.RECODE_SUCCESS);
            result.setData(returnData);

        } catch (Exception e) {
            return catchException(e);
        }

        return result;
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public Result loadEmpData(String id) {
        Result result = new Result();
        OrmParam ormParam = getEmpPositiveApplyOrmParam();
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(EmppostiveapplyProperty.OEPA_EMP, id)));
        JSONObject returnData = new JSONObject();
        try{
            List<Map<String, Object>> queryList = ormService.selectMapList(EmppostiveapplyEntity.class, ormParam);
            if(null!=queryList&&queryList.size()>0){
                returnData.putAll(getEmpPositiveOrdeData(queryList.get(0)));
            }else{
                returnData.putAll(getEmpPositiveOrdeData(null));
                returnData.put(EdmApplyPositiveConstants.OEPA_EMP, id);
            }
            JSONObject empData = getEmpData(id);
            if(null!=empData){
                returnData.putAll(empData);
            }else{
                return catchNoResult("没有找到id为'" + id + "'的员工信息");
            }
            /**
             * 未查到入职指引人
             */
            if("".equals(returnData.getString(EdmApplyPositiveConstants.OEPA_GUILD))){
                returnData.putAll(getEmpGuild(empData.getString(EmployeeConstants.REMP_NO)));
            }

        }catch (Exception e){
            return catchException(e);
        }
        JsonUtils.underLine2Camel(returnData);
        result.setData(returnData);
        return  result;
    }

    private JSONObject getEmpGuild(String empNo) throws Exception{
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(EmployeeentryapplyProperty.OEEO_CODE)
                .addColumn(EmployeeentryapplyProperty.OEEO_GUIDE);
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(EmployeeentryapplyProperty.OEEO_CODE,empNo)));
        List<Map<String, Object>> queryList = ormService.selectMapList(EmployeeentryapplyEntity.class, ormParam);
        JSONObject jsonObject = new JSONObject();
        if(null!=queryList&&queryList.size()>0){
            Map<String, Object> map = queryList.get(0);
            String guildId = NullUtils.valueOf(map.get(EmployeeentryapplyProperty.OEEO_GUIDE));
            jsonObject.put(EdmApplyPositiveConstants.OEPA_GUILD,guildId);
            if("".equals(guildId)){
                jsonObject.put(EdmApplyPositiveConstants.OEPA_GUILD_NAME, "");
                jsonObject.put(EdmApplyPositiveConstants.OEPA_GUILD_NO, "");
            }else{
                JSONObject guildObj = loadEmpNameById(guildId);
                jsonObject.put(EdmApplyPositiveConstants.OEPA_GUILD_NAME, guildObj.getString(EmployeeConstants.REMP_NAME));
                jsonObject.put(EdmApplyPositiveConstants.OEPA_GUILD_NO, guildObj.getString(EmployeeConstants.REMP_NO));
            }
        }else{
            jsonObject.put(EdmApplyPositiveConstants.OEPA_GUILD,"");
            jsonObject.put(EdmApplyPositiveConstants.OEPA_GUILD_NAME, "");
            jsonObject.put(EdmApplyPositiveConstants.OEPA_GUILD_NO, "");
        }
        return jsonObject;
    }

    @NotNull
    private OrmParam getEmpPositiveApplyOrmParam() {
        OrmParam ormParam = new OrmParam();
        //返回工号，姓名，部门，岗位，职级，入职日，转正日
        //从转正申请表获取指引人，报告，备注
        ormParam.addColumn(NodeConstant.ID)
                .addColumn(EdmApplyPositiveConstants.OEPA_EMP)
                .addColumn(EdmApplyPositiveConstants.OEPA_REMARK)
                .addColumn(EdmApplyPositiveConstants.OEPA_GUILD)
                .addColumn(EdmApplyPositiveConstants.OEPA_REPORT)
                .addColumn(EdmApplyPositiveConstants.OEPA_REPORT_URL)
                .addColumn(EdmApplyPositiveConstants.OEPA_FILE_NAME)
                .addColumn(EdmApplyPositiveConstants.ORDE_STATUS)
                .addColumn(OrderConstants.ORDE_RODE_OBJ)
                .addColumn(OrderConstants.ORDE_NBR)
                .addColumn(OrderConstants.ORDE_ADDUSER)
                .addColumn(EdmProperty.EDMD_CLASS)
                .addColumn(OrderConstants.ORDE_DUTY)
                .addColumn(OrderConstants.ORDE_DATE)
                .addColumn(OrderConstants.ORDE_DEPT);
        return ormParam;
    }

    public JSONObject getEmpData(String empId) throws Exception {
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(EmployeeConstants.REMP_IN_DATE)
                .addColumn(EmployeeConstants.REMP_PGRADE)
                .addColumn(EmployeeConstants.REMP_DEPT)
                .addColumn(EmployeeConstants.REMP_NAME)
                .addColumn(EmployeeConstants.REMP_NO)
                .addColumn(EmployeeConstants.REMP_PSOT_DATE)
                .addColumn(EmployeeConstants.REMP_STATUS)
                .addColumn(EmployeeConstants.REMP_POST);
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.ID, empId)));

        List<Map<String, Object>> employeeList = ormService.selectMapList(EmployeeEntity.class, ormParam);
        JSONObject returnData = new JSONObject();
        if (null != employeeList && employeeList.size() > 0) {
            Map<String, Object> employeeMap = employeeList.get(0);
            String rempPost = NullUtils.valueOf(employeeMap.get(EmployeeConstants.REMP_POST));
            String empDept = NullUtils.valueOf(employeeMap.get(EmployeeConstants.REMP_DEPT));
            Object rempInDate = employeeMap.get(EmployeeConstants.REMP_IN_DATE);
            if (!StringUtil.isNullOrEmpty(rempInDate)) {
                java.sql.Timestamp date = (java.sql.Timestamp) rempInDate;
                returnData.put(EmployeeConstants.REMP_IN_DATE, NullUtils.valueOf(date.getTime()));
            } else {
                returnData.put(EmployeeConstants.REMP_IN_DATE, "");
            }
            Object str = employeeMap.get(EmployeeConstants.REMP_PSOT_DATE);
            if (!StringUtil.isNullOrEmpty(str)) {
                java.sql.Timestamp date = (java.sql.Timestamp) str;
                returnData.put(EmployeeConstants.REMP_PSOT_DATE, NullUtils.valueOf(date.getTime()));
            } else {
                returnData.put(EmployeeConstants.REMP_PSOT_DATE, "");
            }
            returnData.put(EmployeeConstants.REMP_PGRADE, NullUtils.valueOf(employeeMap.get(EmployeeConstants.REMP_PGRADE)));
            returnData.put(EmployeeConstants.REMP_DEPT, empDept);
            returnData.put(EmployeeConstants.REMP_NAME, NullUtils.valueOf(employeeMap.get(EmployeeConstants.REMP_NAME)));
            returnData.put(EmployeeConstants.REMP_NO, NullUtils.valueOf(employeeMap.get(EmployeeConstants.REMP_NO)));
            returnData.put(EmployeeConstants.REMP_STATUS, NullUtils.valueOf(employeeMap.get(EmployeeConstants.REMP_STATUS)));
            returnData.put(EmployeeConstants.REMP_POST, rempPost);
            returnData.putAll(loadDeptNameById(empDept));
            returnData.putAll(loadPostNameById(rempPost));
            return returnData;
        }
        return null;
    }

    public JSONObject getEmpPositiveOrdeData( Map<String, Object> postiveApplyMap) throws Exception {
        JSONObject returnData = new JSONObject();
        if(null==postiveApplyMap){
            returnData.put(OrderConstants.ORDE_DATE, "");
            returnData.put(EdmApplyPositiveConstants.OEPA_DATE, "");
            returnData.put(OrderConstants.ORDE_ADDUSER, "");
            returnData.put(EdmProperty.EDMD_CLASS, "");
            returnData.put(OrderConstants.ORDE_DEPT, "");
            returnData.put(OrderConstants.ORDE_DUTY, "");
            returnData.put(OrderConstants.ORDE_RODE_OBJ, "");
            returnData.put(OrderConstants.ORDE_NBR, "");
            returnData.put(OrderConstants.ORDE_ADDUSER_NAME, "");
            returnData.put(OrderConstants.ORDE_ADDUSER_NO, "");
            returnData.put(OrderConstants.ORDE_DEPT_NAME, "");
            returnData.put(OrderConstants.ORDE_DUTY_NAME, "");
            returnData.put(NodeConstant.ID, "");
            returnData.put(EdmApplyPositiveConstants.OEPA_REMARK, "");
            returnData.put(EdmApplyPositiveConstants.OEPA_GUILD, "");
            returnData.put(EdmApplyPositiveConstants.OEPA_REPORT, "");
            returnData.put(EdmApplyPositiveConstants.OEPA_REPORT_URL, "");
            returnData.put(EdmApplyPositiveConstants.OEPA_FILE_NAME, "");
            returnData.put(EdmApplyPositiveConstants.ORDE_STATUS, "");
            returnData.put(EdmApplyPositiveConstants.CREATE_TIME, "");
            returnData.put(EdmApplyPositiveConstants.OEPA_EVALUATION, "");
            returnData.put(EdmApplyPositiveConstants.OEPA_EXT_MONTH, "");
            returnData.put(EdmApplyPositiveConstants.OEPA_OEPA_EXT_DATE, "");
            returnData.put(EdmApplyPositiveConstants.OEPA_GUILD_NAME, "");
            returnData.put(EdmApplyPositiveConstants.OEPA_GUILD_NO, "");
            returnData.put(EdmApplyPositiveConstants.OEPA_EMP, "");
            return returnData;
        }
        // 制单日期
        if (null != postiveApplyMap.get(OrderConstants.ORDE_DATE)) {
            java.sql.Timestamp date = (java.sql.Timestamp) postiveApplyMap.get(OrderConstants.ORDE_DATE);
            returnData.put(OrderConstants.ORDE_DATE, NullUtils.valueOf(date.getTime()));
        } else {
            returnData.put(OrderConstants.ORDE_DATE, "");
        }
        // 转正日期
        if (null != postiveApplyMap.get(EdmApplyPositiveConstants.OEPA_DATE)) {
            java.sql.Timestamp date = (java.sql.Timestamp) postiveApplyMap.get(EdmApplyPositiveConstants.OEPA_DATE);
            returnData.put(EdmApplyPositiveConstants.OEPA_DATE, NullUtils.valueOf(date.getTime()));
        } else {
            returnData.put(EdmApplyPositiveConstants.OEPA_DATE, "");
        }
        // 制单人id
        String empId = NullUtils.valueOf(postiveApplyMap.get(OrderConstants.ORDE_ADDUSER));
        String deptId = NullUtils.valueOf(postiveApplyMap.get(OrderConstants.ORDE_DEPT));
        String postId = NullUtils.valueOf(postiveApplyMap.get(OrderConstants.ORDE_DUTY));
        returnData.put(OrderConstants.ORDE_ADDUSER, empId);
        //EDM_Class
        returnData.put(EdmProperty.EDMD_CLASS, NullUtils.valueOf(postiveApplyMap.get(EdmProperty.EDMD_CLASS)));
        // 制单部门id
        returnData.put(OrderConstants.ORDE_DEPT, deptId);
        // 制单岗位id
        returnData.put(OrderConstants.ORDE_DUTY, postId);
        // 单据定义类型id
        returnData.put(OrderConstants.ORDE_RODE_OBJ, NullUtils.valueOf(postiveApplyMap.get(OrderConstants.ORDE_RODE_OBJ)));
        // 单据单号
        returnData.put(OrderConstants.ORDE_NBR, NullUtils.valueOf(postiveApplyMap.get(OrderConstants.ORDE_NBR)));

        JSONObject adduser = loadEmpNameById(empId);
        // 制单人名字
        returnData.put(OrderConstants.ORDE_ADDUSER_NAME, adduser.getString(EmployeeConstants.REMP_NAME));
        // 制单人工号
        returnData.put(OrderConstants.ORDE_ADDUSER_NO, adduser.getString(EmployeeConstants.REMP_NO));
        returnData.put(OrderConstants.ORDE_DEPT_NAME, loadDeptNameById(deptId).getString(DeptTreeConstants.MDEP_NAME));
        returnData.put(OrderConstants.ORDE_DUTY_NAME, loadPostNameById(postId).getString(JobPositionConstants.RPOS_NAME));

        String postiveApplyId = NullUtils.valueOf(postiveApplyMap.get(NodeConstant.ID));
        String oepaGuild = NullUtils.valueOf(postiveApplyMap.get(EdmApplyPositiveConstants.OEPA_GUILD));
        returnData.put(NodeConstant.ID, postiveApplyId);
        returnData.put(EdmApplyPositiveConstants.OEPA_REMARK, NullUtils.valueOf(postiveApplyMap.get(EdmApplyPositiveConstants.OEPA_REMARK)));
        returnData.put(EdmApplyPositiveConstants.OEPA_GUILD, oepaGuild);
        returnData.put(EdmApplyPositiveConstants.OEPA_REPORT, NullUtils.valueOf(postiveApplyMap.get(EdmApplyPositiveConstants.OEPA_REPORT)));
        String url=NullUtils.valueOf(postiveApplyMap.get(EdmApplyPositiveConstants.OEPA_REPORT_URL));
        String downloadUrl="";
        if(!StringUtil.isNullOrEmpty(url)){
            downloadUrl=nginxIp+url;
        }
        returnData.put("downloadUrl", downloadUrl);
        returnData.put(EdmApplyPositiveConstants.OEPA_REPORT_URL, url);
        returnData.put(EdmApplyPositiveConstants.OEPA_FILE_NAME, NullUtils.valueOf(postiveApplyMap.get(EdmApplyPositiveConstants.OEPA_FILE_NAME)));
        returnData.put(EdmApplyPositiveConstants.ORDE_STATUS, NullUtils.valueOf(postiveApplyMap.get(EdmApplyPositiveConstants.ORDE_STATUS)));

        if (!StringUtil.isNullOrEmpty(postiveApplyId)) {
           OrmParam ormParam = new OrmParam();
            ormParam.addColumn(EdmApplyPositiveConstants.OEPA_AUDIT_IDEA)
                    .addColumn(EdmApplyPositiveConstants.OEPA_OEPA_EXT_DATE)
                    .addColumn(EdmApplyPositiveConstants.OEPA_EVALUATION)
                    .addColumn(EdmApplyPositiveConstants.OEPA_EXT_MONTH)
                    .addColumn(EdmApplyPositiveConstants.CREATE_TIME);
            ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.PID, postiveApplyId)));
            ormParam.setOrderExp(SQLSortEnum.DESC, EdmApplyPositiveConstants.CREATE_TIME, EdmApplyPositiveConstants.MOD_TIME);
            List<Map<String, Object>> auditSetList = ormService.selectMapList(OepaOepaAuditSetaEntity.class, ormParam);
            if (null != auditSetList && auditSetList.size() > 0) {
                Map<String, Object> auditSetMap = auditSetList.get(0);
                returnData.put(EdmApplyPositiveConstants.OEPA_AUDIT_IDEA, NullUtils.valueOf(auditSetMap.get(EdmApplyPositiveConstants.OEPA_AUDIT_IDEA)));
                Object cretime = auditSetMap.get(EdmApplyPositiveConstants.CREATE_TIME);
                if (!StringUtil.isNullOrEmpty(cretime)) {
                    java.sql.Timestamp date = (java.sql.Timestamp) cretime;
                    returnData.put(EdmApplyPositiveConstants.CREATE_TIME, NullUtils.valueOf(date.getTime()));
                } else {
                    returnData.put(EdmApplyPositiveConstants.CREATE_TIME, "");
                }
                returnData.put(EdmApplyPositiveConstants.OEPA_EVALUATION, NullUtils.valueOf(auditSetMap.get(EdmApplyPositiveConstants.OEPA_EVALUATION)));
                returnData.put(EdmApplyPositiveConstants.OEPA_EXT_MONTH, NullUtils.valueOf(auditSetMap.get(EdmApplyPositiveConstants.OEPA_EXT_MONTH)));
                returnData.put(EdmApplyPositiveConstants.OEPA_OEPA_EXT_DATE, NullUtils.valueOf(auditSetMap.get(EdmApplyPositiveConstants.OEPA_OEPA_EXT_DATE)));
            } else {
                returnData.put(EdmApplyPositiveConstants.CREATE_TIME, "");
                returnData.put(EdmApplyPositiveConstants.OEPA_EVALUATION, "");
                returnData.put(EdmApplyPositiveConstants.OEPA_EXT_MONTH, "");
                returnData.put(EdmApplyPositiveConstants.OEPA_OEPA_EXT_DATE, "");
            }
        } else {
            returnData.put(EdmApplyPositiveConstants.CREATE_TIME, "");
            returnData.put(EdmApplyPositiveConstants.OEPA_EVALUATION, "");
            returnData.put(EdmApplyPositiveConstants.OEPA_EXT_MONTH, "");
            returnData.put(EdmApplyPositiveConstants.OEPA_OEPA_EXT_DATE, "");
        }

        JSONObject guildObj = loadEmpNameById(oepaGuild);
        returnData.put(EdmApplyPositiveConstants.OEPA_GUILD_NAME, guildObj.getString(EmployeeConstants.REMP_NAME));
        returnData.put(EdmApplyPositiveConstants.OEPA_GUILD_NO, guildObj.getString(EmployeeConstants.REMP_NO));
        String oepaEmp = NullUtils.valueOf(postiveApplyMap.get(EdmApplyPositiveConstants.OEPA_EMP));
        returnData.put(EdmApplyPositiveConstants.OEPA_EMP, oepaEmp);
        return returnData;
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public Result loadPositiveByOrdeId(String id) {
        Result result = new Result();
        OrmParam ormParam = getEmpPositiveApplyOrmParam();
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.ID, id)));
        JSONObject returnData = new JSONObject();
        try{
            List<Map<String, Object>> queryList = ormService.selectMapList(EmppostiveapplyEntity.class, ormParam);
            if(null!=queryList&&queryList.size()>0){
                returnData.putAll(getEmpPositiveOrdeData(queryList.get(0)));
            }else{
                returnData.putAll(getEmpPositiveOrdeData(null));
            }
            String oempId = returnData.getString(EdmApplyPositiveConstants.OEPA_EMP);
            JSONObject empData = getEmpData(oempId);
            if(null!=empData){
                returnData.putAll(empData);
            }else{
                return catchNoResult("没有找到id为'" + oempId + "'的员工信息");
            }
            /**
             * 未查到入职指引人
             */
            if("".equals(returnData.getString(EdmApplyPositiveConstants.OEPA_GUILD))){
                returnData.putAll(getEmpGuild(empData.getString(EmployeeConstants.REMP_NO)));
            }
        }catch (Exception e){
            return catchException(e);
        }
        JsonUtils.underLine2Camel(returnData);
        result.setData(returnData);
        return  result;

    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public Result savePositive(JSONObject applyPositiveDTO) {
        Result result = new Result();
        String json = applyPositiveDTO.toJSONString();
        Object params = JSONObject.parse(json);
        // 驼峰转下划线
        JsonUtils.camel2UnderLine(params);
        EmppostiveapplyEntity entity = JSONObject.parseObject(JSONObject.toJSONString(params), EmppostiveapplyEntity.class);
        String orderId;
        String orderNbr="";
        // 待返回数据
        JSONObject returnData = new JSONObject();
        orderId = entity.getId();
        try {
            // 单据定义类型id
            String ordeRodeObj = entity.getOrde_rode_obj();
            // 制单人
            String ordeAddUser = entity.getOrde_adduser();
            // 制单岗位
            String ordeDuty = entity.getOrde_duty();
            // 制单部门
            String ordeDept = entity.getOrde_dept();
            if (StringUtil.isNullOrEmpty(ordeRodeObj)) {
                return catchNoResult("必须传递参数ordeRodeObj的值作为单据类型定义id保存");
            }
            if (StringUtil.isNullOrEmpty(ordeAddUser)) {
                return catchNoResult("必须传递参数ordeAdduser的值作为制单人id保存");
            }
            if (StringUtil.isNullOrEmpty(ordeDuty)) {
                return catchNoResult("必须传递参数ordeDuty的值作为制单岗位id保存");
            }
            if (StringUtil.isNullOrEmpty(ordeDept)) {
                return catchNoResult("必须传递参数ordeDept的值作为制单部门id保存");
            }
            //根据session中的当前员工和岗位赋值单据的制单人和制单岗位
            CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
            entity.setOrde_adduser(ordeAddUser);
            entity.setOrde_duty(ordeDuty);
            entity.setOrde_dept(ordeDept);
            entity.setEdmd_ente(sessionEntity.getEnterpriseId());
            if (StringUtil.isNullOrEmpty(orderId)) {
                entity.setOrde_date(new Date());
                entity.setCretime(entity.getOrde_date());
                entity.setCreuser(sessionEntity.getEmployeeId());
                // 生成单据单号
                orderNbr = getOrderNbr(NumberConstants.PREFIX_EMP_POSTIVE_APPLY);
                entity.setOrde_nbr(orderNbr);
                entity.setOrde_status(OrderConstants.ORDE_STATUS_1);
                orderId = (String) ormService.insertSelective(entity);
            } else {
                entity.setModtime(new Date());
                entity.setOrde_date(entity.getModtime());
                entity.setModuser(sessionEntity.getEmployeeId());
                entity.setOrde_status(OrderConstants.ORDE_STATUS_1);
                // 更新一条临时单记录By Id
                ormService.updateSelective(entity);
                // 取出记录中的单据单号返回
                OrmParam ormParam = new OrmParam();
                ormParam.addColumn(NodeConstant.ID).addColumn(OrderProperty.ORDE_NBR);
                ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.ID,orderId)));
                List<EmppostiveapplyEntity> queryList = ormService.selectBeanList(EmppostiveapplyEntity.class,ormParam);
                if(null!=queryList&&queryList.size()>0){
                    EmppostiveapplyEntity e1 = queryList.get(0);
                    orderNbr = e1.getOrde_nbr();
                }
            }

            returnData.put(NodeConstant.ID, orderId);
            returnData.put(ORDE_NBR, orderNbr);
            returnData.put(OrderConstants.ORDE_RODE_OBJ, ordeRodeObj);
            result.setData(returnData);
            result.setErrMsg("保存或更新人员转正单信息成功");
            result.setRetCode(Result.RECODE_SUCCESS);
        } catch (Exception e) {
            return catchException(e);
        }
        // json转实体类
        return result;
    }

    @Override
    public Result empPositive(String orderInstanceId, String handlerType) throws Exception {
        Result result = new Result();
        switch (handlerType) {
            case WFHandlerTypeConstants.PASS: {
                //TODO 单据状态改为 完成；将单据数据写入资源表
                return empPositivePass(orderInstanceId);
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

    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public Result updateOrderStatus(String orderInstanceId, String status) throws Exception {
        Result result = new Result();

        EmppostiveapplyEntity emppostiveapplyEntity = new EmppostiveapplyEntity();
        emppostiveapplyEntity.setId(orderInstanceId);
        emppostiveapplyEntity.setOrde_status(status);
        ormService.updateSelective(emppostiveapplyEntity);
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setErrMsg("更新单据状态成功");

        return result;
    }

    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public Result empPositivePass(String id) throws Exception {
        Result result = new Result();

        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(EdmApplyPositiveConstants.OEPA_EMP).addColumn(EdmApplyPositiveConstants.OEPA_DATE);
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.ID, id)));

        List<Map<String, Object>> queryList = ormService.selectMapList(EmppostiveapplyEntity.class, ormParam);
        if (null != queryList && queryList.size() > 0) {
            Map<String, Object> map1 = queryList.get(0);
            //获取转正人与转正日期
            Date oepaDate = null;
            String oepaEmp = NullUtils.valueOf(map1.get(EdmApplyPositiveConstants.OEPA_EMP));
            Object oepaDate1 = map1.get(EdmApplyPositiveConstants.OEPA_DATE);
            if (!StringUtil.isNullOrEmpty(oepaDate1)) {
                java.sql.Timestamp date = (java.sql.Timestamp) oepaDate1;
                oepaDate = new Date(date.getTime());
            }
            ormParam.reset();
            ormParam.addColumn(EdmApplyPositiveConstants.OEPA_AUDIT_IDEA).addColumn(EdmApplyPositiveConstants.OEPA_OEPA_EXT_DATE);
            ormParam.setOrderExp(SQLSortEnum.DESC, EdmApplyPositiveConstants.CREATE_TIME);

            List<Map<String, Object>> setList = ormService.selectMapList(OepaOepaAuditSetaEntity.class, ormParam);
            if (null != setList && setList.size() > 0) {
                Map<String, Object> map2 = setList.get(0);
                String emppostiveApply = NullUtils.valueOf(map2.get(EdmApplyPositiveConstants.OEPA_AUDIT_IDEA));
                Date oepaExtDate = null;
                Object oepaDate2 = map2.get(EdmApplyPositiveConstants.OEPA_OEPA_EXT_DATE);
                if (!StringUtil.isNullOrEmpty(oepaDate2)) {
                    java.sql.Timestamp date = (java.sql.Timestamp) oepaDate2;
                    oepaExtDate = new Date(date.getTime());
                }

                EmployeeEntity employeeEntity = new EmployeeEntity();
                employeeEntity.setId(oepaEmp);
                //更新员工类.转正日 = 转正日期，员工类.试用结果 = 按期转正，员工状态 = 在职
                if (TURN_POSITIVE_ON_SCHEDULE.equals(emppostiveApply)) {
                    employeeEntity.setRemp_post_date(oepaDate);
                    employeeEntity.setRemp_try_result(TURN_POSITIVE_ON_SCHEDULE);

                    if(DateDiff.canPositive(oepaDate)){
                        employeeEntity.setRemp_status(IN_SERVICE);
                    }/**否则执行定时任务 **/
                    //更新员工类.试用结果 = 延期转正，更新员工类.转正日 = 延期转正日期
                } else if (POSTPONED_TO_POSITIVE.equals(emppostiveApply)) {
                    employeeEntity.setRemp_post_date(oepaExtDate);
                    employeeEntity.setRemp_try_result(POSTPONED_TO_POSITIVE);
                    // 更新员工类.试用结果 = 不与转正，更新员工类.转正日 = "";
                } else if (NOT_CORRECT.equals(emppostiveApply)) {
                    employeeEntity.setRemp_post_date(new Date(0));
                    employeeEntity.setRemp_try_result(NOT_CORRECT);
                } else if (ADVANCE_TRANDFER.equals(emppostiveApply)) {
                    employeeEntity.setRemp_post_date(new Date());
                    employeeEntity.setRemp_try_result(ADVANCE_TRANDFER);
                    employeeEntity.setRemp_status(IN_SERVICE);
                }
                ormService.updateSelective(employeeEntity);
                updateOrderStatus(id, OrderConstants.ORDE_STATUS_5);
                result.setRetCode(Result.RECODE_SUCCESS);
                result.setErrMsg("批准转正操作成功");
            } else {
                return catchNoResult("没有找到pid为'" + id + "'的转正审核意见信息");
            }
        } else {
            return catchNoResult("没有找到id为'" + id + "'的相关转正信息");
        }

        return result;
    }

    @Override
    public List<Object> getAllDept(String deptId) {
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(DeptTreeConstants.MDEP_LVL_CODE);
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.ID, deptId)));
        List<Object> returnList = new ArrayList<>();
        try {
            List<Map<String, Object>> list1 = ormService.selectMapList(DepttreeEntity.class, ormParam);
            if (null != list1 && list1.size() > 0) {
                Map<String, Object> map1 = list1.get(0);
                String mdepLelCode = NullUtils.valueOf(map1.get(DeptTreeConstants.MDEP_LVL_CODE));
                if (!StringUtil.isNullOrEmpty(mdepLelCode)) {
                    ormParam.reset();
                    ormParam.addColumn(NodeConstant.ID);
                    ormParam.setWhereExp(OrmParam.and(ormParam.getMatchMiddleXML(DeptTreeConstants.MDEP_LVL_CODE, mdepLelCode)));
                    List<Map<String, Object>> list2 = ormService.selectMapList(DepttreeEntity.class, ormParam);
                    if (null != list2 && list2.size() > 0) {
                        for (Map<String, Object> map2 : list2) {
                            returnList.add(map2.get(NodeConstant.ID));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return returnList;
    }

    @Override
    public Result submitAddOrder(JSONObject applyPositiveDTO) throws Exception {
        Result saveResult = savePositive(applyPositiveDTO);
        if (saveResult.getRetCode().equals(Result.RECODE_SUCCESS)) {
            return submitUpdateStatus(saveResult);
        } else {
            Result result = new Result();
            result.setData(null);
            result.setErrMsg(saveResult.getErrMsg());
            result.setRetCode(Result.RECODE_ERROR);
            return result;
        }
    }

    @TxTransaction(rollbackFor = Exception.class)
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Result submitUpdateStatus(Result saveResult) throws Exception {
        Result result = new Result();
        // 参见saveAddOrder中的returnData，所以强制类型转换
        JSONObject saveData = (JSONObject) saveResult.getData();
        String orderId = saveData.getString(NodeConstant.ID);
        String orderNbr = saveData.getString(ORDE_NBR);

        // 提交流程
        String orderDefId = saveData.getString(OrderConstants.ORDE_RODE_OBJ);
        bizFormService.submitWorkFlow(orderDefId, orderId);

        // 更新状态
        EmppostiveapplyEntity updateEntity = new EmppostiveapplyEntity();
        updateEntity.setOrde_status(OrderConstants.ORDE_STATUS_2);
        updateEntity.setOrde_date(new Date());
        updateEntity.setId(orderId);
        ormService.updateSelective(updateEntity);

        // 待返回数据
        JSONObject returnData = new JSONObject();
        // 插入返回的数据
        returnData.put(NodeConstant.ID, orderId);
        returnData.put(ORDE_NBR, orderNbr);
        result.setData(returnData);
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setErrMsg("提交单据成功！");

        return result;
    }


    @Override
    public Result auditPostivieOrder(JSONObject auditSet) throws Exception {
        Result result = new Result();
        String auditKey = NullUtils.valueOf(auditSet.getString(WorkFlowConstants.PARAM_AUDITKEY));
        String formState = NullUtils.valueOf(auditSet.getString(WorkFlowConstants.PARAM_FORMSTATE));
        JSONObject ordeParamObj = auditSet.getJSONObject(WorkFlowConstants.PARAM_ORDER_OBJ);
        String actInstanceId = NullUtils.valueOf(auditSet.getString(WorkFlowConstants.PARAM_ACT_INSTANCE_ID));
        String opinion = NullUtils.valueOf(auditSet.getString(WorkFlowConstants.PARAM_OPINION));

        JSONObject returnData = new JSONObject();
        if (WorkFlowConstants.FormState.EDITABLE.equals(formState) && WorkFlowConstants.AuditKey.PASS.equals(auditKey)) {
            JSONObject jsonObject = auditUpdateOrder(ordeParamObj);
            Result res = (Result) jsonObject.get("error");
            if(res.getRetCode().equals(Result.RECODE_SUCCESS)){
                jsonObject.remove("error");
                returnData.putAll(jsonObject);
            }else{
                return  res;
            }
        }

        audiSubmitFlowOrder(actInstanceId, opinion, auditKey);

        result.setData(returnData);
        result.setErrMsg("提交转正单审核意见成功");
        result.setRetCode(Result.RECODE_SUCCESS);

        return result;
    }

    @TxTransaction(rollbackFor = Exception.class)
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void audiSubmitFlowOrder(String actInstanceId, String opinion, String auditKey) throws Exception {
        // 调用流程
        bizFormService.audit(actInstanceId, opinion, auditKey);
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public JSONObject auditUpdateOrder(JSONObject ordeParamObj) throws Exception {
       JSONObject returnData = new JSONObject();
        String orderId = "";
        if (null != ordeParamObj) {
            orderId = NullUtils.valueOf(ordeParamObj.getString(NodeConstant.ID));
            if (StringUtil.isNullOrEmpty(orderId)) {
                Result result = new Result();
                result.setRetCode(Result.RECODE_VALIDATE_ERROR);
                result.setErrMsg("未找到单据id参数" + NodeConstant.ID);
                returnData.put("error",result);
                return returnData;
            }
        } else {
            Result result = new Result();
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            result.setErrMsg("未找到单据对象参数" + EdmApplyPositiveConstants.PARAM_ORDER_OBJ);
            returnData.put("error",result);
            return returnData;
        }
        // 查询单据
        EmppostiveapplyEntity entity = ormService.load(EmppostiveapplyEntity.class, orderId);
        if (null == entity) {
            Result result= catchNoResult("没有找到id为'" + orderId + "'的单据信息");
            returnData.put("error",result);
            return returnData;
        }

        // 参数OepaOepaAuditSetaEntity
        List<OepaOepaAuditSetaEntity> list = new ArrayList<>();
        OepaOepaAuditSetaEntity es = new OepaOepaAuditSetaEntity();
        es.setOepa_auditor(NullUtils.valueOf(ordeParamObj.getString(EdmApplyPositiveConstants.PARAM_OEPA_AUDITOR)));
        es.setOepa_audit_idea(NullUtils.valueOf(ordeParamObj.getString(EdmApplyPositiveConstants.PARAM_OEPA_AUDIT_IDEA)));
        es.setOepa_evaluation(NullUtils.valueOf(ordeParamObj.getString(EdmApplyPositiveConstants.PARAM_OEPA_EVALUATION)));
        if (null != ordeParamObj.getInteger(EdmApplyPositiveConstants.PARAM_OEPA_EXT_MONTH)) {
            es.setOepa_ext_month(ordeParamObj.getInteger(EdmApplyPositiveConstants.PARAM_OEPA_EXT_MONTH));
        } else {
            es.setOepa_ext_month(0);
        }
        es.setOepa_ext_date(ordeParamObj.getDate(EdmApplyPositiveConstants.PARAM_OEPA_EXT_DATE));
        list.add(es);

        // 插入一条记录，作为Orde_last_version_obj历史记录
        entity.setId(null);
        String oldId = (String) ormService.insertSelective(entity);
        // 更新原有的记录entity中Orde_last_version_obj
        entity.setId(orderId);
        entity.setOrde_last_version_obj(oldId);
        ormService.updateSelective(entity);

        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(NodeConstant.ID);
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.PID, orderId)));
        List<Map<String, Object>> mapList = ormService.selectMapList(OepaOepaAuditSetaEntity.class, ormParam);
        if (null != mapList && mapList.size() > 0) {
            for (Map<String, Object> map : mapList) {
                OepaOepaAuditSetaEntity e11 = new OepaOepaAuditSetaEntity();
                e11.setId(NullUtils.valueOf(map.get(NodeConstant.ID)));
                e11.setPid(oldId);
                ormService.updateSelective(e11);
            }
        }

        entity.setOepa_audit_set(list);
        /**
         *因为save方法给entity设置了 creuser和cretime字段，然后我通过load方法加载的entity。
         * 所以 下面这段代码就是给属性集 也设置 creuser和cretime等字段。
         */
        EdmUtil.setPropertyBaseEntitiesSysColumns(OepcOepcChangSetaEntity.class, entity,
                entity.getOepa_audit_set(), SQLCurdEnum.INSERT);

        // 这里暂时不使用批量插入，是防止有什么字段不能为null的情况导致数据无法成功插入。
        for (OepaOepaAuditSetaEntity e : entity.getOepa_audit_set()) {
            e.setClassName(EdmApplyPositiveConstants.OEPA_EMPPOSITIVEAPPLY);
            e.setId(null);
            e.setPid(orderId);
            ormService.insertSelective(e);
        }

        returnData.put("error", new Result());
        returnData.put(NodeConstant.ID, orderId);
        returnData.put(ORDE_NBR, entity.getOrde_nbr());
        return returnData;
    }


    /**
     * 定时任务修改转正状态
     *
     * @return
     * @throws Exception
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public Result updateEmpPostive() throws Exception {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setErrMsg("定时任务修改转正状态");

        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(NodeConstant.ID)
                .addColumn(EmployeeProperty.REMP_POST_DATE);

        ormParam.setWhereExp(OrmParam.and(
                ormParam.getInXML(EmployeeProperty.REMP_TRY_RESULT, new String[]{TURN_POSITIVE_ON_SCHEDULE,POSTPONED_TO_POSITIVE}),
                ormParam.getEqualXML(EmployeeProperty.REMP_STATUS, TRY_OUT))
        );

        List<Map<String, Object>> list = ormService.selectMapList(EmployeeEntity.class, ormParam);

        if (null != list && list.size() > 0) {
            for (Map<String, Object> map : list) {
                Object o = map.get(EmployeeProperty.REMP_POST_DATE);
                if (!StringUtil.isNullOrEmpty(o)) {
                    java.sql.Timestamp date = (java.sql.Timestamp) o;
                    if(DateDiff.canPositive(date)){
                        EmployeeEntity employeeEntity = new EmployeeEntity();
                        employeeEntity.setId(NullUtils.valueOf(map.get(NodeConstant.ID)));
                        // 修改成在职状态
                        employeeEntity.setRemp_status(IN_SERVICE);
                        ormService.updateSelective(employeeEntity);
                    }
                }
            }
        }

        return result;
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

    private JSONObject loadPostNameById(String postId) throws Exception {
        JSONObject object = new JSONObject();
        if (StringUtil.isNullOrEmpty(postId)) {
            object.put(JobPositionConstants.RPOS_NAME, "");
            return object;
        }
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(JobPositionConstants.RPOS_NAME);
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.ID, postId)));
        List<Map<String, Object>> queryList = ormService.selectMapList(JobpositionEntity.class, ormParam);
        if (null != queryList && queryList.size() > 0) {
            Map<String, Object> map = queryList.get(0);
            object.put(JobPositionConstants.RPOS_NAME, NullUtils.valueOf(map.get(JobPositionConstants.RPOS_NAME)));
        } else {
            object.put(JobPositionConstants.RPOS_NAME, "");
        }
        return object;
    }

    private JSONObject loadDeptNameById(String deptId) throws Exception {
        JSONObject object = new JSONObject();
        if (StringUtil.isNullOrEmpty(deptId)) {
            object.put(DeptTreeConstants.MDEP_NAME, "");
            return object;
        }
        OrmParam ormParam = new OrmParam();
        // 根据部门id查询部门名称
        ormParam.addColumn(DeptTreeConstants.MDEP_NAME);
        // 条件: By Id
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.ID, deptId)));
        //SELECT mdep_name FROM depttree WHERE id = ? AND is_del = 0
        List<Map<String, Object>> queryList = ormService.selectMapList(DepttreeEntity.class, ormParam);
        if (null != queryList && queryList.size() > 0) {
            Map<String, Object> map = queryList.get(0);
            object.put(DeptTreeConstants.MDEP_NAME, NullUtils.valueOf(map.get(DeptTreeConstants.MDEP_NAME)));
        } else {
            object.put(DeptTreeConstants.MDEP_NAME, "");
        }
        return object;
    }

    private JSONObject loadEmpNameById(String empId) throws Exception {
        JSONObject object = new JSONObject();
        if (StringUtil.isNullOrEmpty(empId)) {
            object.put(EmployeeConstants.REMP_NAME, "");
            object.put(EmployeeConstants.REMP_NO, "");
            return object;
        }
        OrmParam ormParam = new OrmParam();
        //  根据员工id查询员工姓名
        ormParam.addColumn(EmployeeConstants.REMP_NAME).addColumn(EmployeeConstants.REMP_NO);
        // 条件: By Id
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.ID, empId)));
        // SELECT remp_name FROM employee WHERE id = ? AND is_del = 0
        List<Map<String, Object>> queryList = ormService.selectMapList(EmployeeEntity.class, ormParam);
        if (null != queryList && queryList.size() > 0) {
            Map<String, Object> map = queryList.get(0);
            object.put(EmployeeConstants.REMP_NAME, NullUtils.valueOf(map.get(EmployeeConstants.REMP_NAME)));
            object.put(EmployeeConstants.REMP_NO, NullUtils.valueOf(map.get(EmployeeConstants.REMP_NO)));
        } else {
            object.put(EmployeeConstants.REMP_NAME, "");
            object.put(EmployeeConstants.REMP_NO, "");
        }
        return object;
    }

}

