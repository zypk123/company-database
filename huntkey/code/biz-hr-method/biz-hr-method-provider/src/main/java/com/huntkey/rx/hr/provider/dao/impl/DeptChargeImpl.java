package com.huntkey.rx.hr.provider.dao.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.datetime.DateUtil;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.edm.entity.DeptchargerapplyorderEntity;
import com.huntkey.rx.edm.entity.EmployeeEntity;
import com.huntkey.rx.edm.entity.JobpositionEntity;
import com.huntkey.rx.edm.entity.OdcsOdcsChrgSetaEntity;
import com.huntkey.rx.hr.common.model.*;
import com.huntkey.rx.hr.provider.dao.DeptChargeDao;
import com.huntkey.rx.hr.provider.service.BizFormService;
import com.huntkey.rx.hr.provider.service.impl.DeptTreeServiceImpl;
import com.huntkey.rx.hr.provider.service.impl.PositionDefinitionServiceImpl;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;
import com.huntkey.rx.sceo.orm.service.OrmService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by weijian on 2017/11/8.
 */
@Component
public class DeptChargeImpl implements DeptChargeDao {
    private static Logger logger = LoggerFactory.getLogger(PositionDefinitionServiceImpl.class);
    @Autowired
    OrmService ormService;
    @Autowired
    DeptTreeServiceImpl deptTreeService;
    @Autowired
    BizFormService bizFormService;

    @Override
    public Result loadEmployeeInfo(String searchContent, String deptId) {
        Result result = new Result();
        JSONArray jsonArray = new JSONArray();
        //1.数据查询
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(EmployeeConstants.REMP_ID).addColumn(EmployeeConstants.REMP_NO).addColumn(EmployeeConstants.REMP_NAME).addColumn(EmployeeConstants.REMP_DEPT);
        String whereCondition = OrmParam.and(ormParam.getUnequalXML("remp_status","3"),OrmParam.or(ormParam.getMatchMiddleXML(EmployeeConstants.REMP_NAME, searchContent), ormParam.getMatchMiddleXML(EmployeeConstants.REMP_NO, searchContent)));
        ormParam.setWhereExp(whereCondition);
        try {
            List<Map<String, Object>> list = ormService.selectMapList(EmployeeEntity.class, ormParam);
            for (Map<String, Object> map : list) {//员工类集合
                JSONObject jsonObject = (JSONObject) JSON.toJSON(map);
                Date date = new Date();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
                String nowDate = df.format(date);
                OrmParam params = new OrmParam();
                params.addColumn("id").addColumn("rpos_emp").addColumn("rpos_code").addColumn("rpos_name").addColumn("rpos_grade").addColumn("rpos_dept").addColumn("rpos_duty_type");
                String whereCondition1 = params.and(params.getEqualXML("rpos_emp", jsonObject.get("id")),
                        params.getEqualXML("rpos_dept", deptId),
                        params.getLessThanAndEqualXML("rpos_beg", nowDate));
                params.setWhereExp(whereCondition1);
                List<Map<String, Object>> joblist = ormService.selectMapList(JobpositionEntity.class, params);//岗位集合
                if (joblist.size() > 0) {
                    //在此部门有任职
                    for (Map<String, Object> jobmap : joblist) {
                        JSONObject jobObject = (JSONObject) JSON.toJSON(jobmap);
                        String rposId = (String) jobObject.get("id");
                        jobObject.remove("id");
                        jobObject.put("rposId", rposId);
                        jobObject.put("inThisDept", "1");
                        jsonObject.putAll(jobObject);
                    }
                } else {
                    OrmParam ormParam1 = new OrmParam();
                    ormParam1.addColumn("id").addColumn("rpos_emp").addColumn("rpos_code").addColumn("rpos_name").addColumn("rpos_grade").addColumn("rpos_dept").addColumn("rpos_duty_type");
                    String where = ormParam1.and(ormParam1.getEqualXML("rpos_emp", jsonObject.get("id")),
                            ormParam1.getLessThanAndEqualXML("rpos_beg", nowDate));
                    ormParam1.setWhereExp(where);
                    List<Map<String, Object>> list1 = ormService.selectMapList(JobpositionEntity.class, ormParam1);
                    for (Map<String, Object> newmap : list1) {
                        JSONObject newObject = (JSONObject) JSON.toJSON(newmap);
                        String rposId = (String) newObject.get("id");
                        newObject.remove("id");
                        newObject.put("rposId", rposId);
                        newObject.put("inThisDept", "0");
                        jsonObject.putAll(newObject);
                    }
                }
                jsonArray.add(jsonObject);
            }
            List<EmpSearchDTO> datalist = jsonArray.stream().map(obj -> JSONObject.toJavaObject((JSONObject) JSON.toJSON(obj), EmpSearchDTO.class)).collect(Collectors.toList());
            result.setRetCode(Result.RECODE_SUCCESS);
            result.setData(datalist);
        } catch (Exception e) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(e.getMessage());
            logger.debug(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Result insertDeptchargerapplyorder(DeptChargerApplyOrdeDTO deptChargerApplyOrdeDTO) throws Exception {
        //根据session中的当前员工和岗位赋值单据的制单人和制单岗位
        CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
        Result result = new Result();
        List<OdcsChrgSetDTO> list = deptChargerApplyOrdeDTO.getOdcsChrgSet();//责任人信息
        deptChargerApplyOrdeDTO.setOdcsChrgSet(null);
        JSONObject insertObject = JSONObject.parseObject(JSON.toJSONString(deptChargerApplyOrdeDTO));//驼峰格式转下划线
        DeptchargerapplyorderEntity deptchargerapplyorderEntity = JSONObject.parseObject(JSONObject.toJSONString(insertObject), DeptchargerapplyorderEntity.class);
        deptchargerapplyorderEntity.setCretime(new Date());
        deptchargerapplyorderEntity.setCreuser(sessionEntity.getEmployeeId());
        String orderId = (String) ormService.insertSelective(deptchargerapplyorderEntity);//插入主表信息
        for (OdcsChrgSetDTO odcsChrgSetDTO : list) {
            odcsChrgSetDTO.setPid(orderId);
            odcsChrgSetDTO.setClassname("deptchargerapplyorder");
            JSONObject param = JSONObject.parseObject(JSON.toJSONString(odcsChrgSetDTO));//驼峰格式转下划线
            OdcsOdcsChrgSetaEntity odcsOdcsChrgSetaEntity = JSONObject.parseObject(JSONObject.toJSONString(param), OdcsOdcsChrgSetaEntity.class);
            odcsOdcsChrgSetaEntity.setCretime(new Date());
            odcsOdcsChrgSetaEntity.setCreuser(sessionEntity.getEmployeeId());
            ormService.insertSelective(odcsOdcsChrgSetaEntity);//插入结果集信息
        }
        JSONObject dataObj = new JSONObject();
        dataObj.put("id", orderId);
        dataObj.put("ordeNbr", deptChargerApplyOrdeDTO.getOrdeNbr());
        result.setData(dataObj);
        return result;
    }

    @Override
    public Result updateDeptchargerapplyorder(DeptChargerApplyOrdeDTO deptChargerApplyOrdeDTO) throws Exception {
        Result result = new Result();
        CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
        List<OdcsChrgSetDTO> list = deptChargerApplyOrdeDTO.getOdcsChrgSet();//责任人信息
        deptChargerApplyOrdeDTO.setOdcsChrgSet(null);
        JSONObject insertObject = JSONObject.parseObject(JSON.toJSONString(deptChargerApplyOrdeDTO));//驼峰格式转下划线
        DeptchargerapplyorderEntity deptchargerapplyorderEntity = JSONObject.parseObject(JSONObject.toJSONString(insertObject), DeptchargerapplyorderEntity.class);
        deptchargerapplyorderEntity.setModtime(new Date());
        deptchargerapplyorderEntity.setModuser(sessionEntity.getEmployeeId());
        //更新主表信息
        int val = ormService.updateSelective(deptchargerapplyorderEntity);
        //删除结果集信息
        String pid = deptChargerApplyOrdeDTO.getId();
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn("id");
        String whereExp = ormParam.getEqualXML("pid", pid);
        ormParam.setWhereExp(whereExp);
        ormService.delete(OdcsOdcsChrgSetaEntity.class, ormParam);

        //插入新结果集信息
        for (OdcsChrgSetDTO odcsChrgSetDTO : list) {
            odcsChrgSetDTO.setId(null);
            odcsChrgSetDTO.setPid(deptChargerApplyOrdeDTO.getId());
            odcsChrgSetDTO.setClassname("deptchargerapplyorder");
            JSONObject param = JSONObject.parseObject(JSON.toJSONString(odcsChrgSetDTO));//驼峰格式转下划线
            OdcsOdcsChrgSetaEntity odcsOdcsChrgSetaEntity = JSONObject.parseObject(JSONObject.toJSONString(param), OdcsOdcsChrgSetaEntity.class);
            odcsOdcsChrgSetaEntity.setCretime(new Date());
            odcsOdcsChrgSetaEntity.setCreuser(sessionEntity.getEmployeeId());
            ormService.insertSelective(odcsOdcsChrgSetaEntity);//插入结果集信息
        }
        JSONObject dataObj = new JSONObject();
        dataObj.put("id", deptChargerApplyOrdeDTO.getId());
        dataObj.put("ordeNbr", deptChargerApplyOrdeDTO.getOrdeNbr());
        result.setData(dataObj);
        return result;
    }

    @Override
    public Result loadDeptChargerApplyOrder(String id, String bmid) {
        //bmid 不为空时为子部门调用
        Result result = new Result();
        DeptTreeDTO deptTreeDTO = new DeptTreeDTO();
        //获取表单数据
        try {
            DeptchargerapplyorderEntity deptchargerapplyorderEntity = ormService.load(DeptchargerapplyorderEntity.class, id);
            if (deptchargerapplyorderEntity != null) {
                List<OdcsOdcsChrgSetaEntity> list = deptchargerapplyorderEntity.loadOdcs_chrg_set();//责任人信息结果集
                //遍历结果集
                JSONArray chargeArr = new JSONArray();
                for (OdcsOdcsChrgSetaEntity odcsOdcsChrgSetaEntity : list) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("deptId", odcsOdcsChrgSetaEntity.getOdcs_dept());//部门ID
                    jsonObject.put("chrgType", odcsOdcsChrgSetaEntity.getOdcs_chrg_type());//责任人类型1 第一负责人/主管 2	第二负责人/协同
                    jsonObject.put("dutyType", odcsOdcsChrgSetaEntity.getOdcs_duty_type());//任职方式0	任职 1	兼职 2	代职
                    String newChargeEmpCode = odcsOdcsChrgSetaEntity.getOdcs_emp();//责任人ID
                    jsonObject.put("empCode", newChargeEmpCode);
                    JSONObject employee = getEmployee(newChargeEmpCode);
                    String newChargeEmpName = (String) employee.get("remp_name");//获取人员姓名
                    jsonObject.put("empName", newChargeEmpName);
                    String newChargeEmpNo = (String) employee.get("remp_no");//获取人员工号
                    jsonObject.put("empNo", newChargeEmpNo);
                    String newChargePostCode = odcsOdcsChrgSetaEntity.getOdcs_post();//岗位
                    jsonObject.put("postCode", newChargePostCode);
                    String newChargePostName = getName(newChargePostCode);//获取岗位名称
                    jsonObject.put("postName", newChargePostName);
                    //原值
                    if (!StringUtil.isNullOrEmpty(odcsOdcsChrgSetaEntity.getOdcs_emp_old())) {
                        jsonObject.put("oldEmp", odcsOdcsChrgSetaEntity.getOdcs_emp_old());
                        JSONObject oldEmployee = getEmployee(odcsOdcsChrgSetaEntity.getOdcs_emp_old());
                        String oldEmpName = (String) oldEmployee.get("remp_name");//获取人员姓名
                        jsonObject.put("oldEmpName", oldEmpName);
                        String oldEmpNo = (String) oldEmployee.get("remp_no");//获取人员工号
                        jsonObject.put("oldEmpNo", oldEmpNo);
                    }
                    chargeArr.add(jsonObject);
                }
                Set<String> set = new HashSet<>();
                for (int i = 0; i < chargeArr.size(); i++) {
                    JSONObject jsonObject = (JSONObject) chargeArr.get(i);
                    String deptId = (String) jsonObject.get("deptId");
                    set.add(deptId);
                }
                JSONArray dataArr = new JSONArray();
                for (String str : set) {
                    JSONObject object = new JSONObject();
                    object.put("deptId", str);
                    JSONArray newSubCooperateCodes = new JSONArray();// 协管-新值-人-id（新值默认为旧值）["113","123123"]
                    JSONArray newSubCooperatePostCode = new JSONArray();// 协管-新值-岗位-id  arr
                    JSONArray newSubCooperateDutyType = new JSONArray();// 协管-新值-任职方式 arr
                    JSONArray newSubCooperateDeptCode = new JSONArray();// 协管-新值-部门-id  arr
                    JSONArray newSubCooperateNames = new JSONArray();// 协管-新值-人-名称（新值默认为旧值）张三;李四
                    JSONArray newSubCooperateNos = new JSONArray();//协管人工号
                    Set<String> oldSubCooperateCodes = new HashSet<>();//旧协管人id
                    Set<String> oldSubCooperateNames = new HashSet<>();//旧协管人名称
                    /*JSONArray oldSubCooperateCodes = new JSONArray();
                    JSONArray oldSubCooperateNames = new JSONArray();*/
                    for (int j = 0; j < chargeArr.size(); j++) {
                        JSONObject jsonObject = (JSONObject) chargeArr.get(j);
                        if (jsonObject.get("deptId").equals(str)) {
                            if ("1".equals(jsonObject.get("chrgType"))) {//1 第一负责人/主管
                                String newChargeEmpName = (String) jsonObject.get("empName") + "/" + (String) jsonObject.get("empNo");
                                object.put("newChargeDeptCode", jsonObject.get("deptId"));
                                object.put("newChargeDutyType", jsonObject.get("dutyType"));
                                object.put("newChargeEmpCode", jsonObject.get("empCode"));
                                object.put("newChargeEmpName", newChargeEmpName);
                                object.put("newChargePostCode", jsonObject.get("postCode"));
                                object.put("newChargePostName", jsonObject.get("postName"));
                                if (jsonObject.containsKey("oldEmp")) {
                                    object.put("oldChargeEmpCode", jsonObject.get("oldEmp"));
                                    String oldChargeEmpName = (String) jsonObject.get("oldEmpName") + "/" + (String) jsonObject.get("oldEmpNo");
                                    object.put("oldChargeEmpName", oldChargeEmpName);
                                }
                            } else if ("2".equals(jsonObject.get("chrgType"))) {//2	第二负责人/协同
                                newSubCooperateDeptCode.add(jsonObject.get("deptId"));// 协管-新值-部门-id  arr
                                newSubCooperateCodes.add(jsonObject.get("empCode"));// 协管-新值-人-id（新值默认为旧值）["113","123123"]
                                newSubCooperateNames.add(jsonObject.get("empName"));// 协管-新值-人-名称（新值默认为旧值）张三;李四
                                newSubCooperatePostCode.add(jsonObject.get("postCode"));// 协管-新值-岗位-id  arr
                                newSubCooperateDutyType.add(jsonObject.get("dutyType"));// 协管-新值-任职方式 arr
                                newSubCooperateNos.add(jsonObject.get("empNo"));
                                if (jsonObject.containsKey("oldEmp")) {
                                    oldSubCooperateCodes.add((String) jsonObject.get("oldEmp"));// 协管人旧值
                                    //oldSubCooperateCodes.add(jsonObject.get("oldEmp"));
                                    String oldSubCooperateName = (String) jsonObject.get("oldEmpName") + "/" + (String) jsonObject.get("oldEmpNo");
                                    oldSubCooperateNames.add(oldSubCooperateName);//协管人姓名
                                }
                            }
                        }
                    }
                    if (newSubCooperateDeptCode.size() > 0) {
                        object.put("newSubCooperateDeptCode", newSubCooperateDeptCode);
                    }
                    if (newSubCooperateCodes.size() > 0) {
                        object.put("newSubCooperateCodes", newSubCooperateCodes);
                    }
                    if (newSubCooperateNames.size() > 0) {
                        object.put("newSubCooperateNames", StringUtils.join(newSubCooperateNames, ","));
                    }
                    if (newSubCooperatePostCode.size() > 0) {
                        object.put("newSubCooperatePostCode", newSubCooperatePostCode);
                    }
                    if (newSubCooperateDutyType.size() > 0) {
                        object.put("newSubCooperateDutyType", newSubCooperateDutyType);
                    }
                    if (newSubCooperateNos.size() > 0) {
                        object.put("newSubCooperateNos", newSubCooperateNos);
                    }
                    if (oldSubCooperateCodes.size() > 0) {
                        object.put("oldSubCooperateCodes", oldSubCooperateCodes);
                    }
                    if (oldSubCooperateNames.size() > 0) {
                        object.put("oldSubCooperateNames", StringUtils.join(oldSubCooperateNames, ","));
                    }
                    object.put("isEdit","1");//判断是否修改1：已修改
                    dataArr.add(object);
                }
                JSONObject deptObj = new JSONObject();
                if (!StringUtil.isNullOrEmpty(bmid)) {
                    deptObj = getDepttree(bmid, deptchargerapplyorderEntity.getOdcs_beg());//获取部门树 起始部门ID——下划线格式
                } else {
                    deptObj = getDepttree(deptchargerapplyorderEntity.getOdcs_dept_beg(), deptchargerapplyorderEntity.getOdcs_beg());//获取部门树 起始部门ID——下划线格式
                }
                //System.out.println("deptObj+++++++++++++++++++++" + deptObj.toString());
                //根据起始部门ID获取部门树数据遍历添加数据
                JSONObject newTree = getNewTree(deptObj, dataArr);
                //System.out.println("newTree+++++++++++++++++++++" + newTree.toString());
                deptTreeDTO = JSONObject.parseObject(JSONObject.toJSONString(newTree), DeptTreeDTO.class);//下划线转驼峰格式
                //System.out.println("resultData+++++++++++++++++++++"+deptTreeDTO.toString());
                result.setData(deptTreeDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setRetCode(Result.RECODE_SUCCESS);
        return result;
    }

    //根据ID获取对应名称
    private String getName(String value) {
        OrmParam ormParam = new OrmParam();
        String name = "";
        String whereXml = ormParam.getEqualXML("id", value);
        ormParam.setWhereExp(whereXml);
        try {
            List<Map<String, Object>> list = ormService.selectMapList(JobpositionEntity.class, ormParam);
            if (list.size() > 0) {
                Map<String, Object> map = list.get(0);
                name = (String) map.get("rpos_name");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }

    private JSONObject getEmployee(String rempCode) {
        JSONObject resultObj = new JSONObject();
        OrmParam ormParam = new OrmParam();
        String name = "";
        String whereXml = ormParam.getEqualXML("id", rempCode);
        ormParam.setWhereExp(whereXml);
        try {
            List<Map<String, Object>> list = ormService.selectMapList(EmployeeEntity.class, ormParam);
            if (list.size() > 0) {
                Map<String, Object> map = list.get(0);
                resultObj.put("remp_code", map.get("remp_code"));
                resultObj.put("remp_name", map.get("remp_name"));
                resultObj.put("remp_no", map.get("remp_no"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultObj;
    }

    //获取员工工号，姓名
    private JSONArray getEmployeeInfo(JSONArray arr) throws Exception {
        JSONArray resultArr = new JSONArray();
        for (int i = 0; i < arr.size(); i++) {
            String id = (String) arr.get(i);
            EmployeeEntity employeeEntity = ormService.load(EmployeeEntity.class, id);
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(employeeEntity);//转object
            resultArr.add(jsonObject);
        }
        return resultArr;
    }

    //获取部门树数据
    private JSONObject getDepttree(String bmid, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (date == null) {
            date = new Date();
        }
        String nowDate = sdf.format(date);
        DeptTreeDTO deptTreeDTO = deptTreeService.getDeptTreeList(bmid, DateUtil.parseFormatDate(nowDate, "yyyy-MM-dd"), 1, 1);
        JSONObject resultObj = JSONObject.parseObject(JSON.toJSONString(deptTreeDTO));//驼峰格式转下划线
        return resultObj;
    }

    //遍历树结构
    private JSONObject getNewTree(JSONObject deptObj, JSONArray dataArr) {
        String bmbm = (String) deptObj.get("pid");//部门id
        for (int i = 0; i < dataArr.size(); i++) {
            JSONObject Object = (JSONObject) dataArr.get(i);
            String bmid = (String) Object.get("deptId");
            if (bmbm.equals(bmid)) {
                Object.remove("deptId");
                deptObj.putAll(Object);//合并
            }
        }
        JSONArray child = new JSONArray();
        if (deptObj.get("childDeptList") != null) {
            JSONArray treeArr = (JSONArray) deptObj.get("childDeptList");
            for (int j = 0; j < treeArr.size(); j++) {
                JSONObject jsonObject = (JSONObject) treeArr.get(j);
                String bm = (String) jsonObject.get("pid");//部门编码
                JSONArray newArr = new JSONArray();
                for (int k = 0; k < dataArr.size(); k++) {
                    JSONObject charge = (JSONObject) dataArr.get(k);
                    String chargeBm = (String) charge.get("deptId");
                    if (bm.equals(chargeBm)) {
                        charge.remove("deptId");
                        jsonObject.putAll(charge);//合并
                    }
                }
                child.add(jsonObject);
            }
        }
        deptObj.put("childDeptList", child);
        return deptObj;
    }


    @Override
    public Result queryDeptEmployees(String deptId) throws Exception {
        Result result = new Result();
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(JobPositionConstants.RPOS_ID).addColumn(JobPositionConstants.RPOS_CODE).addColumn(JobPositionConstants.RPOS_NAME).addColumn(JobPositionConstants.RPOS_EMP).addColumn(JobPositionConstants.RPOS_DUTY_TYPE);
        String whereCondition = ormParam.getEqualXML("rpos_dept", deptId);
        ormParam.setWhereExp(whereCondition);
        List<Map<String, Object>> empList = ormService.selectMapList(JobpositionEntity.class, ormParam);
        JSONArray arr = new JSONArray();
        for (Map<String, Object> map : empList) {
            if (!StringUtil.isNullOrEmpty(map.get(JobPositionConstants.RPOS_EMP))) {
                arr.add(map.get(JobPositionConstants.RPOS_EMP));
            }
        }
        JSONArray employeeInfo = getEmployeeInfo(arr);
        JSONArray dataArr = new JSONArray();
        for (Map<String, Object> map : empList) {
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(map);
            for (int j = 0; j < employeeInfo.size(); j++) {
                JSONObject employObject = (JSONObject) employeeInfo.get(j);
                if (jsonObject.containsKey(JobPositionConstants.RPOS_EMP) && jsonObject.get(JobPositionConstants.RPOS_EMP).toString().equals(employObject.get("id").toString())) {
                    jsonObject.put("remp_id", employObject.get("id"));
                    jsonObject.put("remp_no", employObject.get("remp_no"));
                    jsonObject.put("remp_name", employObject.get("remp_name"));
                    dataArr.add(jsonObject);
                }
            }
        }
        List<QueryDeptEmployeesDTO> list = dataArr.stream().map(obj -> JSONObject.toJavaObject((JSONObject) JSON.toJSON(obj), QueryDeptEmployeesDTO.class)).collect(Collectors.toList());
        result.setData(list);
        result.setRetCode(Result.RECODE_SUCCESS);
        return result;
    }
}
