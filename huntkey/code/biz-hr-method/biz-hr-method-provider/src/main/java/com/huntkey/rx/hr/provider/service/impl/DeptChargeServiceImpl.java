package com.huntkey.rx.hr.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.codingapi.tx.annotation.TxTransaction;
import com.huntkey.rx.commons.utils.datetime.DateUtil;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.edm.entity.*;
import com.huntkey.rx.hr.common.constants.NumberConstants;
import com.huntkey.rx.hr.common.constants.OrderStatusConstants;
import com.huntkey.rx.hr.common.constants.WFHandlerTypeConstants;
import com.huntkey.rx.hr.common.constants.WorkFlowConstants;
import com.huntkey.rx.hr.common.exception.ApplicationException;
import com.huntkey.rx.hr.common.model.*;
import com.huntkey.rx.hr.common.util.DateDiff;
import com.huntkey.rx.hr.common.util.DateUtils;
import com.huntkey.rx.hr.common.util.NullUtils;
import com.huntkey.rx.hr.provider.client.InformationClient;
import com.huntkey.rx.hr.provider.dao.DeptChargeDao;
import com.huntkey.rx.hr.provider.service.BizFormService;
import com.huntkey.rx.hr.provider.service.DeptChargeService;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;
import com.huntkey.rx.sceo.orm.service.OrmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by weijian on 2017/11/8.
 */
@Service
public class DeptChargeServiceImpl implements DeptChargeService {
    private static Logger logger = LoggerFactory.getLogger(PositionDefinitionServiceImpl.class);
    @Autowired
    private DeptChargeDao deptChargeDao;
    @Autowired
    OrmService ormService;
    @Autowired
    BizFormService bizFormService;
    @Autowired
    InformationClient informationClient;

    @Override
    public Result loadEmployeeInfo(String searchContent, String deptId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        //调用查询接口
        result = deptChargeDao.loadEmployeeInfo(searchContent, deptId);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result saveDeptCharge(DeptChargerApplyOrdeDTO deptChargerApplyOrdeDTO) throws Exception {
        Result result = new Result();
        //根据session中的当前员工和岗位赋值单据的制单人和制单岗位
        CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
        deptChargerApplyOrdeDTO.setOrdeAdduser(sessionEntity.getEmployeeId());
        deptChargerApplyOrdeDTO.setOrdeDuty(sessionEntity.getPositionId());
        deptChargerApplyOrdeDTO.setEdmdEnte(sessionEntity.getEnterpriseId());
        deptChargerApplyOrdeDTO.setOrdeStatus(OrderStatusConstants.ORDER_STATUS_1);//单据状态：临时单
        JSONObject checData = checkData(deptChargerApplyOrdeDTO);//数据校验
        if (checData.containsKey("message") && !StringUtil.isNullOrEmpty(checData.getString("message"))) {
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            result.setErrMsg(checData.getString("message"));
            return result;
        }
        if (StringUtil.isNullOrEmpty(deptChargerApplyOrdeDTO.getId())) {
            deptChargerApplyOrdeDTO.setOrdeDate(new Date());//制单日期
            String orderNum = getCode(NumberConstants.PREFIX_DEPT_CHARGER_APPLY_ORDER);// 部门责任人任免单编号生成
            deptChargerApplyOrdeDTO.setOrdeNbr(orderNum);
            result = deptChargeDao.insertDeptchargerapplyorder(deptChargerApplyOrdeDTO);
        } else {
            result = deptChargeDao.updateDeptchargerapplyorder(deptChargerApplyOrdeDTO);
        }
        return result;
    }

    @Override
    public Result submit(DeptChargerApplyOrdeDTO deptChargerApplyOrdeDTO) throws Exception {
        //根据session中的当前员工和岗位赋值单据的制单人和制单岗位
        CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
        deptChargerApplyOrdeDTO.setOrdeAdduser(sessionEntity.getEmployeeId());
        deptChargerApplyOrdeDTO.setOrdeDuty(sessionEntity.getPositionId());
        deptChargerApplyOrdeDTO.setEdmdEnte(sessionEntity.getEnterpriseId());
        deptChargerApplyOrdeDTO.setOrdeStatus(OrderStatusConstants.ORDER_STATUS_1);//单据状态：待提
        Result result = save(deptChargerApplyOrdeDTO);
        if (result.getRetCode().equals(Result.RECODE_SUCCESS) && result.getData() != null) {
            JSONObject jsonObject = (JSONObject) result.getData();
            String orderInstanceId = (String) jsonObject.get("id");//单据id
            String ordeNbr = (String) jsonObject.get("ordeNbr");//单据编号
            String orderDefId = deptChargerApplyOrdeDTO.getOrdeRodeObj();
            return submitUpdateStatus(orderDefId, orderInstanceId, ordeNbr);
        } else {
            result.setData(null);
            result.setErrMsg(result.getErrMsg());
            result.setRetCode(Result.RECODE_ERROR);
            return result;
        }

    }

    @TxTransaction(rollbackFor = Exception.class)
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Result submitUpdateStatus(String orderDefId, String orderInstanceId, String ordeNbr) throws Exception {
        Result result = new Result();
        bizFormService.submitWorkFlow(orderDefId, orderInstanceId);// 提交流程
        updateOrderStatus(orderInstanceId, OrderStatusConstants.ORDER_STATUS_2);// 更新状态
        // 待返回数据
        JSONObject returnData = new JSONObject();
        returnData.put("id", orderInstanceId);
        returnData.put("ordeNbr", ordeNbr);
        result.setData(returnData);
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setErrMsg("提交单据成功！");
        return result;
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Result save(DeptChargerApplyOrdeDTO deptChargerApplyOrdeDTO) throws Exception {
        Result result = new Result();
        JSONObject checData = checkData(deptChargerApplyOrdeDTO);//数据校验
        if (checData.containsKey("message") && !StringUtil.isNullOrEmpty(checData.getString("message"))) {
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            result.setErrMsg(checData.getString("message"));
            return result;
        }
        if (StringUtil.isNullOrEmpty(deptChargerApplyOrdeDTO.getId())) {
            deptChargerApplyOrdeDTO.setOrdeDate(new Date());//制单日期
            String orderNum = getCode(NumberConstants.PREFIX_DEPT_CHARGER_APPLY_ORDER);// 部门责任人任免单编号生成
            deptChargerApplyOrdeDTO.setOrdeNbr(orderNum);
            result = deptChargeDao.insertDeptchargerapplyorder(deptChargerApplyOrdeDTO);
        } else {
            result = deptChargeDao.updateDeptchargerapplyorder(deptChargerApplyOrdeDTO);
        }
        return result;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Result pass(String orderInstanceId, String handlerType) {
        Result result = new Result();
        logger.info("职位维护单审批通过回调接口：orderInstanceId：" + orderInstanceId + ",handlerType:" + handlerType);
        switch (handlerType) {
            case WFHandlerTypeConstants.PASS: {
                result = passMethod(orderInstanceId);//TODO 单据状态改为 完成；将单据数据写入资源表
                break;
            }
            case WFHandlerTypeConstants.REVOKE: {
                //TODO 单据状态改为 待提
                result = updateOrderStatus(orderInstanceId, OrderStatusConstants.ORDER_STATUS_1);
                break;
            }
            case WFHandlerTypeConstants.RETURN_BACK: {
                //TODO 单据状态改为 退回
                result = updateOrderStatus(orderInstanceId, OrderStatusConstants.ORDER_STATUS_6);
                break;
            }
            default: {
                break;
            }
        }
        result.setRetCode(Result.RECODE_SUCCESS);
        return result;
    }

    @Override
    public Result loadDeptChargerApplyOrder(String id, String bmid) {
        return deptChargeDao.loadDeptChargerApplyOrder(id, bmid);
    }

    @Override
    public Result loadFromOrder(String id) {
        Result result = new Result();
        try {
            DeptchargerapplyorderEntity deptchargerapplyorderEntity = ormService.load(DeptchargerapplyorderEntity.class, id);
            deptchargerapplyorderEntity.setOdcs_chrg_set(deptchargerapplyorderEntity.loadOdcs_chrg_set());
            DeptChargerApplyOrdeDTO deptChargerApplyOrdeDTO = JSONObject.parseObject(JSONObject.toJSONString(deptchargerapplyorderEntity), DeptChargerApplyOrdeDTO.class);//转Dto
            String ordeAddUserName = queryAddUserName(deptChargerApplyOrdeDTO.getOrdeAdduser());
            deptChargerApplyOrdeDTO.setOrdeAdduserName(ordeAddUserName);
            String ordeDutyName = queryOrdeDutyName(deptChargerApplyOrdeDTO.getOrdeDuty());
            deptChargerApplyOrdeDTO.setOrdeDutyName(ordeDutyName);
            String ordeDeptName = queryOrdeDeptName(deptChargerApplyOrdeDTO.getOrdeDept());
            deptChargerApplyOrdeDTO.setOrdeDeptName(ordeDeptName);
            result.setData(deptChargerApplyOrdeDTO);
            result.setRetCode(Result.RECODE_SUCCESS);
        } catch (Exception e) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(e.getMessage());
            logger.debug(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 查询人员所有任职岗位
     *
     * @param employeeId
     * @param deptId
     * @return
     */
    @Override
    public Result queryJobPosition(String employeeId, String deptId) {
        Result result = new Result();
        JSONArray jsonArray = new JSONArray();
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String nowDate = df.format(date);
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn("id").addColumn("rpos_emp").addColumn("rpos_code").addColumn("rpos_name").addColumn("rpos_grade").addColumn("rpos_dept").addColumn("rpos_duty_type");
        String whereCondition = OrmParam.and(ormParam.getEqualXML("rpos_emp", employeeId),
                ormParam.getEqualXML("rpos_dept", deptId),
                ormParam.getLessThanAndEqualXML("rpos_beg", new Date()));
        ormParam.setWhereExp(whereCondition);
        try {
            List<Map<String, Object>> list = ormService.selectMapList(JobpositionEntity.class, ormParam);
            if (list.size() > 0) {
                //在此部门有任职
                for (Map<String, Object> map : list) {
                    JSONObject jsonObject = (JSONObject) JSON.toJSON(map);
                    jsonObject.put("inThisDept", "1");
                    jsonArray.add(jsonObject);
                    List<QueryJobPositionDTO> datalist = jsonArray.stream().map(obj -> JSONObject.toJavaObject((JSONObject) JSON.toJSON(obj), QueryJobPositionDTO.class)).collect(Collectors.toList());
                    result.setRetCode(Result.RECODE_SUCCESS);
                    result.setData(datalist);
                }
            } else {
                OrmParam ormParam1 = new OrmParam();
                ormParam1.addColumn("id").addColumn("rpos_emp").addColumn("rpos_code").addColumn("rpos_name").addColumn("rpos_grade").addColumn("rpos_dept").addColumn("rpos_duty_type");
                String whereCondition1 = OrmParam.and(ormParam1.getEqualXML("rpos_emp", employeeId),
                        ormParam1.getLessThanAndEqualXML("rpos_beg", new Date()));
                ormParam1.setWhereExp(whereCondition1);
                List<Map<String, Object>> list1 = ormService.selectMapList(JobpositionEntity.class, ormParam1);
                for (Map<String, Object> map : list1) {
                    JSONObject jsonObject = (JSONObject) JSON.toJSON(map);
                    jsonObject.put("inThisDept", "0");
                    jsonArray.add(jsonObject);
                    List<QueryJobPositionDTO> datalist1 = jsonArray.stream().map(obj -> JSONObject.toJavaObject((JSONObject) JSON.toJSON(obj), QueryJobPositionDTO.class)).collect(Collectors.toList());
                    result.setRetCode(Result.RECODE_SUCCESS);
                    result.setData(datalist1);
                }
            }
        } catch (Exception e) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(e.getMessage());
            logger.debug(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    //
    private JSONArray queryDepCharge(String pid, String sxrq) throws Exception {
        JSONArray jsonArray = new JSONArray();
        OrmParam ormParam = new OrmParam();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date1 = sdf.format(new Date());
        String whereCondition = OrmParam.and(ormParam.getEqualXML("pid", pid)
                , ormParam.getGreaterThanAndEqualXML("mdep_chgr_beg", date1));
        ormParam.setWhereExp(whereCondition);
        List<MdepMdepChgrSetaEntity> list = ormService.selectBeanList(MdepMdepChgrSetaEntity.class, ormParam);//负责人集合
        for (MdepMdepChgrSetaEntity mdepMdepChgrSetaEntity : list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", mdepMdepChgrSetaEntity.getId());
            jsonObject.put("mdep_chgr_end", mdepMdepChgrSetaEntity.getMdep_chgr_end());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    private Result delOldDepCharge(JSONObject zgrObj, String sxrq) throws Exception {
        CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
        Result result = new Result();
        MdepMdepChgrSetaEntity mdepMdepChgrSetaEntity = new MdepMdepChgrSetaEntity();
        mdepMdepChgrSetaEntity.setId((String) zgrObj.get("id"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(sxrq);
        mdepMdepChgrSetaEntity.setMdep_chgr_end(date);
        mdepMdepChgrSetaEntity.setModtime(new Date());
        mdepMdepChgrSetaEntity.setModuser(sessionEntity.getEmployeeId());
        int val = ormService.updateSelective(mdepMdepChgrSetaEntity);
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(val);
        return result;
    }

    private Result insertOdcsChrgSet(OdcsChrgSetDTO odcsChrgSetDTO, String sxrq) throws Exception {
        CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
        Result result = new Result();
        String bmid = odcsChrgSetDTO.getOdcsDept();
        MdepMdepChgrSetaEntity mdepMdepChgrSetaEntity = new MdepMdepChgrSetaEntity();
        mdepMdepChgrSetaEntity.setPid(bmid);
        mdepMdepChgrSetaEntity.setClassName("depttree");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(sxrq);
        mdepMdepChgrSetaEntity.setMdep_chgr_beg(date);
        mdepMdepChgrSetaEntity.setMdep_chgr_end(DateUtil.parseFormatDate("9999-12-31 23:59:59", DateUtils.DATE_YYYY_MM_DD_HH_MM_SS));//失效日期设置
        mdepMdepChgrSetaEntity.setMdep_chgr_post(odcsChrgSetDTO.getOdcsPost());
        mdepMdepChgrSetaEntity.setMdep_chgr_type(odcsChrgSetDTO.getOdcsChrgType());
        mdepMdepChgrSetaEntity.setMdep_chgr_emp(odcsChrgSetDTO.getOdcsEmp());
        mdepMdepChgrSetaEntity.setCreuser(sessionEntity.getEmployeeId());//插入人
        mdepMdepChgrSetaEntity.setCretime(new Date());
        Serializable serializable = ormService.insert(mdepMdepChgrSetaEntity);
        result.setData(serializable.toString());
        return result;
    }

    private void updateChargeInfo(List<OdcsChrgSetDTO> list, String sxrq) throws Exception {
        //根据部门id对责任人信息分类
        Set<String> set = new HashSet<>();
        if (list.size() > 0) {
            for (OdcsChrgSetDTO odcsChrgSetDTO : list) {
                set.add(odcsChrgSetDTO.getOdcsDept());
            }
            if (set.size() > 0) {
                for (String str : set) {
                    List<OdcsChrgSetDTO> bmlist = new ArrayList<>();//同一部门责任人信息list
                    for (OdcsChrgSetDTO odcsChrgSetDTO : list) {
                        if (odcsChrgSetDTO.getOdcsDept().equals(str)) {
                            bmlist.add(odcsChrgSetDTO);
                        }
                    }
                    if (bmlist.size() > 0) {
                        updateDeptChargeInfo(bmlist, str, sxrq);
                    }
                }
            }
        }
    }

    //以部门为单位，做数据更新
    private void updateDeptChargeInfo(List<OdcsChrgSetDTO> bmlist, String bmid, String sxrq) throws Exception {
        //获取本部门所有人员
        Result bmzwResult = deptChargeDao.queryDeptEmployees(bmid);
        List<QueryDeptEmployeesDTO> bmzwList = (List<QueryDeptEmployeesDTO>) bmzwResult.getData();
        //删除所有部门原负责人
        JSONArray chargeArr = queryDepCharge(bmid, sxrq);//查询出部门原负责人
        if (chargeArr.size() > 0) {
            for (int j = 0; j < chargeArr.size(); j++) {
                JSONObject chargeObj = (JSONObject) chargeArr.get(j);
                delOldDepCharge(chargeObj, sxrq);
            }
        }
        //插入所有本部门的负责人信息
        List<OdcsChrgSetDTO> zgrList = new ArrayList<>();
        List<OdcsChrgSetDTO> xgrList = new ArrayList<>();
        for (OdcsChrgSetDTO addOdcsChrgSetDTO : bmlist) {
            if ("1".equals(addOdcsChrgSetDTO.getOdcsChrgType())) {
                zgrList.add(addOdcsChrgSetDTO);
            } else if ("2".equals(addOdcsChrgSetDTO.getOdcsChrgType())) {
                xgrList.add(addOdcsChrgSetDTO);
            }
            insertOdcsChrgSet(addOdcsChrgSetDTO, sxrq);
        }
        //主管人相关信息更新(一条数据)
        if (zgrList.size() > 0) {
            //更新depttree 主管人 主责岗位
            OdcsChrgSetDTO addOdcsChrgSetDTO = zgrList.get(0);
            updateDpetTree(addOdcsChrgSetDTO, bmid);
            //判断主管人在本部门是否有任职
            Boolean isInDeft = false;//任职&有岗
            for (QueryDeptEmployeesDTO queryDeptEmployeesDTO : bmzwList) {
                if (queryDeptEmployeesDTO.getRempId().equals(addOdcsChrgSetDTO.getOdcsEmp())) {
                    isInDeft = true;//在本部门任职
                }
            }
            if (!isInDeft) {//无岗
                if (addOdcsChrgSetDTO.getOdcsDutyType().equals("0")) {//任职
                    updateEmployee(addOdcsChrgSetDTO);//更新员工类岗位岗级
                }
                insertRempPostSet(addOdcsChrgSetDTO, sxrq);//插入任岗经历
                updateJobPosition(addOdcsChrgSetDTO, sxrq); //插入岗位任职人，变更历史
            }
        }
        //协管人相关信息更新
        if (xgrList.size() > 0) {
            //判断协管人在本部门是否有任职
            for (OdcsChrgSetDTO odcsChrgSetDTO : xgrList) {
                Boolean inDept = false;
                String employee = odcsChrgSetDTO.getOdcsEmp();//协管人ID
                for (QueryDeptEmployeesDTO queryDeptEmployeesDTO : bmzwList) {
                    if (employee.equals(queryDeptEmployeesDTO.getRempId())) {//
                        inDept = true;
                        break;
                    }
                }
                if (!inDept) {//其他部门
                    if (odcsChrgSetDTO.getOdcsDutyType().equals("0")) {//任职
                        updateEmployee(odcsChrgSetDTO);//更新员工类岗位岗级
                    }
                    insertRempPostSet(odcsChrgSetDTO, sxrq);//插入任岗经历
                    updateJobPosition(odcsChrgSetDTO, sxrq); //插入岗位任职人，变更历史
                }
            }
        }
    }

    //更新depttree类负责人(主管人)，主责岗位
    private void updateDpetTree(OdcsChrgSetDTO addOdcsChrgSetDTO, String bmid) throws Exception {
        CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
        DepttreeEntity depttreeEntity = new DepttreeEntity();
        depttreeEntity.setId(bmid);
        depttreeEntity.setMdep_leader(addOdcsChrgSetDTO.getOdcsEmp());
        depttreeEntity.setMdep_leader_post(addOdcsChrgSetDTO.getOdcsPost());
        depttreeEntity.setModtime(new Date());
        depttreeEntity.setModuser(sessionEntity.getEmployeeId());
        ormService.updateSelective(depttreeEntity);
    }

    //插入任岗经历
    private void insertRempPostSet(OdcsChrgSetDTO addOdcsChrgSetDTO, String sxrq) throws Exception {
        CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
        RempRempPostSetaEntity rempRempPostSetaEntity = new RempRempPostSetaEntity();
        rempRempPostSetaEntity.setPid(addOdcsChrgSetDTO.getOdcsEmp());
        rempRempPostSetaEntity.setClassName("employee");
        rempRempPostSetaEntity.setRemp_post_his(addOdcsChrgSetDTO.getOdcsPost());//岗位
        rempRempPostSetaEntity.setRemp_dtyp_his(addOdcsChrgSetDTO.getOdcsDutyType());//任职方式
        if (!StringUtil.isNullOrEmpty(addOdcsChrgSetDTO.getOdcsPost())) {
            JobpositionEntity jobpositionEntity = getJobpositionEntity(addOdcsChrgSetDTO.getOdcsPost());
            rempRempPostSetaEntity.setRemp_pgrad_his(jobpositionEntity.getRpos_grade());//岗级
            JobpositionEntity pemp = getJobpositionEntity(jobpositionEntity.getRpos_ppost());
            String employeeId = pemp.getRpos_emp();
            if (!StringUtil.isNullOrEmpty(employeeId)) {
                EmployeeEntity employeeEntity = loadEmployeeEntity(employeeId);
                rempRempPostSetaEntity.setRemp_pemp_his(employeeEntity.getId());//汇报上级
            }
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(sxrq);
        rempRempPostSetaEntity.setRemp_post_beg(date);
        rempRempPostSetaEntity.setCreuser(sessionEntity.getEmployeeId());//插入人
        rempRempPostSetaEntity.setCretime(new Date());
        Serializable serializable = ormService.insert(rempRempPostSetaEntity);
    }

    //更新jobposition任职人，任职方式，插入新变更历史
    private void updateJobPosition(OdcsChrgSetDTO addOdcsChrgSetDTO, String sxrq) throws Exception {
        CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
        //1.修改岗位
        JobpositionEntity jobpositionEntity = new JobpositionEntity();
        jobpositionEntity.setId(addOdcsChrgSetDTO.getOdcsPost());//岗位id
        jobpositionEntity.setRpos_emp(addOdcsChrgSetDTO.getOdcsEmp());//任职人
        jobpositionEntity.setRpos_duty_type(addOdcsChrgSetDTO.getOdcsDutyType());//任职方式
        jobpositionEntity.setModtime(new Date());
        jobpositionEntity.setModuser(sessionEntity.getEmployeeId());
        int val = ormService.updateSelective(jobpositionEntity);

        //2.修改岗位变更历史
        List<RposRposChagSetaEntity> hisList = jobpositionEntity.loadRpos_chag_set();
        for (RposRposChagSetaEntity rposRposChagSetaEntity : hisList) {
            ormService.delete(RposRposChagSetaEntity.class, rposRposChagSetaEntity.getId());//失效此岗位下原变更记录
        }
        //插入新变更历史
        RposRposChagSetaEntity rposRposChagSetaEntity = new RposRposChagSetaEntity();
        rposRposChagSetaEntity.setPid(addOdcsChrgSetDTO.getOdcsPost());
        rposRposChagSetaEntity.setClassName("jobposition");
        JSONObject gwInfo = getGwInfo(addOdcsChrgSetDTO.getOdcsPost());
        rposRposChagSetaEntity.setRpos_name_his((String) gwInfo.get("rpos_name"));
        rposRposChagSetaEntity.setRpos_grade_his((String) gwInfo.get("rpos_grade"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(sxrq);
        rposRposChagSetaEntity.setRpos_beg_his(date);//生效日期
        rposRposChagSetaEntity.setCreuser(sessionEntity.getEmployeeId());
        rposRposChagSetaEntity.setCretime(new Date());
        Serializable serializable = ormService.insert(rposRposChagSetaEntity);
    }

    //单据通过方法具体实现
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    private Result passMethod(String orderInstanceId) {
        Result result = new Result();
        try {
            //根据id获部门责任人任免单
            DeptchargerapplyorderEntity deptchargerapplyorderEntity = ormService.load(DeptchargerapplyorderEntity.class, orderInstanceId);
            List<OdcsOdcsChrgSetaEntity> chargelist = deptchargerapplyorderEntity.loadOdcs_chrg_set();
            deptchargerapplyorderEntity.setOdcs_chrg_set(chargelist);
            DeptChargerApplyOrdeDTO deptChargerApplyOrdeDTO = JSONObject.parseObject(JSONObject.toJSONString(deptchargerapplyorderEntity), DeptChargerApplyOrdeDTO.class);
            deptChargerApplyOrdeDTO.setOrdeStatus(OrderStatusConstants.ORDER_STATUS_5);//单据状态：通过
            //保存部门类数据
            //1.更新depttree类主管人协管人信息
            List<OdcsChrgSetDTO> list = deptChargerApplyOrdeDTO.getOdcsChrgSet();
            //更新主管人信息到depttree
            Date sxrq = deptChargerApplyOrdeDTO.getOdcsBeg();//生效日期
            updateChargeInfo(list, DateUtil.parseFormatDate(sxrq, "yyyy-MM-dd"));
            deptChargeDao.updateDeptchargerapplyorder(deptChargerApplyOrdeDTO);//保存表单类数据
            result.setRetCode(Result.RECODE_SUCCESS);
        } catch (Exception e) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    JSONObject getGwInfo(String gwid) throws Exception {
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn("id").addColumn("rpos_name").addColumn("rpos_grade");
        String whereCondition = ormParam.getEqualXML("id", gwid);
        ormParam.setWhereExp(whereCondition);
        List<Map<String, Object>> list = ormService.selectMapList(JobpositionEntity.class, ormParam);
        Map<String, Object> map = list.get(0);
        return (JSONObject) JSON.toJSON(map);
    }

    private JSONObject checkData(DeptChargerApplyOrdeDTO deptChargerApplyOrdeDTO) throws Exception {
        JSONObject jsonObject = new JSONObject();
        String ordeRodeObj = deptChargerApplyOrdeDTO.getOrdeRodeObj();// 单据定义类型id
        String ordeAddUser = deptChargerApplyOrdeDTO.getOrdeAdduser();// 制单人
        String ordeDuty = deptChargerApplyOrdeDTO.getOrdeDuty();// 制单岗位
        String ordeDept = deptChargerApplyOrdeDTO.getOrdeDept();// 制单部门
        if (StringUtil.isNullOrEmpty(ordeRodeObj)) {
            jsonObject.put("message", "必须传递参数ordeRodeObj的值作为单据类型定义id保存");
            return jsonObject;
        }
        if (StringUtil.isNullOrEmpty(ordeAddUser)) {
            jsonObject.put("message", "必须传递参数ordeAddUser的值作为制单人id保存");
            return jsonObject;
        }
        if (StringUtil.isNullOrEmpty(ordeDuty)) {
            jsonObject.put("message", "必须传递参数ordeDuty的值作为制单岗位id保存");
            return jsonObject;
        }
        if (StringUtil.isNullOrEmpty(ordeDept)) {
            jsonObject.put("message", "必须传递参数ordeDept的值作为制单部门id保存");
            return jsonObject;
        }
        List<OdcsChrgSetDTO> list = deptChargerApplyOrdeDTO.getOdcsChrgSet();
        for (OdcsChrgSetDTO odcsChrgSetDTO : list) {
            //1.责任人任免单校验
            String deptId = odcsChrgSetDTO.getOdcsDept();
            if (!StringUtil.isNullOrEmpty(deptId)) {
                OrmParam ormParam = new OrmParam();
                ormParam.setWhereExp(ormParam.getEqualXML("odcs_dept", deptId));
                List<OdcsOdcsChrgSetaEntity> beanlist = ormService.selectBeanList(OdcsOdcsChrgSetaEntity.class, ormParam);
                for (OdcsOdcsChrgSetaEntity odcsOdcsChrgSetaEntity : beanlist) {
                    String pid = odcsOdcsChrgSetaEntity.getPid();
                    OrmParam ormParam1 = new OrmParam();
                    ormParam1.setWhereExp(ormParam1.and(ormParam1.getEqualXML("id", pid), ormParam1.or(ormParam1.getEqualXML("orde_status", "2"), ormParam1.getEqualXML("orde_status", "3"), ormParam1.getEqualXML("orde_status", "4"))));
                    List<DeptchargerapplyorderEntity> ordeList = ormService.selectBeanList(DeptchargerapplyorderEntity.class, ormParam1);
                    if (ordeList.size() > 0) {
                        DeptchargerapplyorderEntity deptchargerapplyorderEntity = ordeList.get(0);
                        String ordeNbr = deptchargerapplyorderEntity.getOrde_nbr();
                        jsonObject.put("message", "新责任人所在部门存在未完成责任人任免单:" + ordeNbr + "，请重新选择");
                        return jsonObject;
                    }
                }
            }

            //2.1部门岗位设置单校验（上级岗位存在待审的含下级的部门岗位注销单）
            if (!StringUtil.isNullOrEmpty(odcsChrgSetDTO.getOdcsPost())) {
                OrmParam jobInfoParam = new OrmParam();
                //查询汇报岗位
                jobInfoParam.setWhereExp(jobInfoParam.getEqualXML("id", odcsChrgSetDTO.getOdcsPost()));
                jobInfoParam.addColumn("id").addColumn("rpos_dept").addColumn("rpos_code").addColumn("rpos_grade").addColumn("rpos_ppost");
                List<Map<String, Object>> jobList = ormService.selectMapList(JobpositionEntity.class, jobInfoParam);
                if (jobList.size() > 0) {
                    Map<String, Object> map = jobList.get(0);
                    //查询此岗位部门注销单
                    OrmParam deptJobParam = new OrmParam();
                    String dept = (String) map.get("rpos_dept");
                    String code = (String) map.get("rpos_code");
                    deptJobParam.setWhereExp(deptJobParam.and(deptJobParam.getEqualXML("odps_dept", dept),
                            deptJobParam.getEqualXML("odps_post", code)));
                    List<OdpsOdpsDpostSetaEntity> deptJobList = ormService.selectBeanList(OdpsOdpsDpostSetaEntity.class, deptJobParam);
                    for (OdpsOdpsDpostSetaEntity OdpsOdpsDpostSetaEntity : deptJobList) {
                        String pid = OdpsOdpsDpostSetaEntity.getPid();
                        OrmParam ormParam1 = new OrmParam();
                        ormParam1.setWhereExp(ormParam1.and(ormParam1.getEqualXML("id", pid), ormParam1.or(ormParam1.getEqualXML("orde_status", "2"), ormParam1.getEqualXML("orde_status", "3"), ormParam1.getEqualXML("orde_status", "4"))));
                        List<DeptpostsetorderEntity> ordeList = ormService.selectBeanList(DeptpostsetorderEntity.class, ormParam1);
                        if (ordeList.size() > 0) {
                            DeptpostsetorderEntity deptpostsetorderEntity = ordeList.get(0);
                            String ordeNbr = deptpostsetorderEntity.getOrde_nbr();
                            jsonObject.put("message", "所选岗位存在未完成部门岗位注销单:" + ordeNbr + "，请选择其它岗位");
                            return jsonObject;
                        }
                    }

                    String ppost = (String) map.get("rpos_ppost");//汇报岗位
                    if (!StringUtil.isNullOrEmpty(ppost)) {
                        //查询汇报岗位信息
                        OrmParam ppostParam = new OrmParam();
                        ppostParam.setWhereExp(ppostParam.getEqualXML("id", ppost));
                        ppostParam.addColumn("id").addColumn("rpos_dept").addColumn("rpos_code").addColumn("rpos_grade").addColumn("rpos_ppost");
                        List<Map<String, Object>> postList = ormService.selectMapList(JobpositionEntity.class, ppostParam);
                        if (postList.size() > 0) {
                            Map<String, Object> ppostMap = postList.get(0);
                            String pcode = (String) ppostMap.get("rpos_code");//岗位编号
                            String pdept = (String) ppostMap.get("rpos_dept");//岗位部门
                            OrmParam deptParam = new OrmParam();
                            deptParam.setWhereExp(deptParam.and(deptParam.getEqualXML("odps_dept", pdept), deptParam.getEqualXML("odps_post", pcode), deptParam.getEqualXML("odps_sub", "1")));
                            List<OdpsOdpsDpostSetaEntity> deptlist = ormService.selectBeanList(OdpsOdpsDpostSetaEntity.class, deptParam);
                            for (OdpsOdpsDpostSetaEntity odpsOdpsDpostSetaEntity : deptlist) {
                                String pid = odpsOdpsDpostSetaEntity.getPid();
                                OrmParam ormParam1 = new OrmParam();
                                ormParam1.setWhereExp(ormParam1.and(ormParam1.getEqualXML("id", pid), ormParam1.getEqualXML("odps_type", "3"),
                                        ormParam1.or(ormParam1.getEqualXML("orde_status", "2"), ormParam1.getEqualXML("orde_status", "3"), ormParam1.getEqualXML("orde_status", "4"))));
                                List<DeptpostsetorderEntity> ordeList = ormService.selectBeanList(DeptpostsetorderEntity.class, ormParam1);
                                if (ordeList.size() > 0) {
                                    DeptpostsetorderEntity deptpostsetorderEntity = ordeList.get(0);
                                    String orde_nbr = deptpostsetorderEntity.getOrde_nbr();
                                    jsonObject.put("message", "所选岗位存在未完成部门岗位注销单:" + orde_nbr + "，请选择其它岗位");
                                    return jsonObject;
                                }
                            }
                        }
                    }
                }

                //3.员工岗位调整单
                OrmParam jobParam = new OrmParam();
                jobParam.setWhereExp(jobParam.getEqualXML("oepc_post", odcsChrgSetDTO.getOdcsPost()));
                List<OepcOepcChangSetaEntity> joblist = ormService.selectBeanList(OepcOepcChangSetaEntity.class, jobParam);
                for (OepcOepcChangSetaEntity oepcOepcChangSetaEntity : joblist) {
                    String pid = oepcOepcChangSetaEntity.getPid();
                    OrmParam ormParam1 = new OrmParam();
                    ormParam1.setWhereExp(ormParam1.and(ormParam1.getEqualXML("id", pid), ormParam1.or(ormParam1.getEqualXML("orde_status", "2"), ormParam1.getEqualXML("orde_status", "3"), ormParam1.getEqualXML("orde_status", "4"))));
                    List<EmppostchangeapplyEntity> ordeList = ormService.selectBeanList(EmppostchangeapplyEntity.class, ormParam1);
                    if (ordeList.size() > 0) {
                        EmppostchangeapplyEntity emppostchangeapplyEntity = ordeList.get(0);
                        String ordeNbr = emppostchangeapplyEntity.getOrde_nbr();
                        jsonObject.put("message", "所选岗位存在未完成员工岗位调整单:" + ordeNbr + "，请选择其它岗位");
                        return jsonObject;
                    }
                }

                //4.员工入职单
                OrmParam entryParam = new OrmParam();
                entryParam.setWhereExp(entryParam.and(entryParam.getEqualXML("oeeo_post", odcsChrgSetDTO.getOdcsPost()),
                        entryParam.getEqualXML("oeeo_type", "1"),
                        entryParam.or(entryParam.getEqualXML("orde_status", "2"), entryParam.getEqualXML("orde_status", "3"), entryParam.getEqualXML("orde_status", "4"))));//1:入职单
                entryParam.addColumn("id").addColumn("orde_nbr");
                List<Map<String, Object>> entryList = ormService.selectMapList(EmployeeentryapplyEntity.class, entryParam);
                if (entryList.size() > 0) {
                    Map<String, Object> entryMap = entryList.get(0);
                    String ordeNbr = (String) entryMap.get("orde_nbr");
                    jsonObject.put("message", "所选岗位存在未完成员工入职单:" + ordeNbr + "，请选择其它岗位");
                    return jsonObject;
                }

                //5.判端岗位是否已有员工
                OrmParam jobOrm = new OrmParam();
                jobOrm.setWhereExp(jobOrm.getEqualXML("id", odcsChrgSetDTO.getOdcsPost()));
                List<JobpositionEntity> job = ormService.selectBeanList(JobpositionEntity.class, jobOrm);
                if (job.size() > 0) {
                    JobpositionEntity jobpositionEntity = job.get(0);
                    if (!StringUtil.isNullOrEmpty(jobpositionEntity.getRpos_emp()) && !jobpositionEntity.getRpos_emp().equals(odcsChrgSetDTO.getOdcsEmp())) {
                        jsonObject.put("message", "所选岗位:" + jobpositionEntity.getRpos_name() + "已安排员工，请选择其它岗位");
                        return jsonObject;
                    }
                }
            }

            if (!StringUtil.isNullOrEmpty(odcsChrgSetDTO.getOdcsDept())) {
                //6.被任免的部门存在未来生效的负责人记录
                OrmParam mdepParam = new OrmParam();
                mdepParam.setWhereExp(mdepParam.and(mdepParam.getEqualXML("pid", odcsChrgSetDTO.getOdcsDept()), mdepParam.getGreaterThanXML("mdep_chgr_beg", new Date())));
                List<MdepMdepChgrSetaEntity> mdepList = ormService.selectBeanList(MdepMdepChgrSetaEntity.class, mdepParam);//未来生效责任人
                if (mdepList.size() > 0) {
                    MdepMdepChgrSetaEntity mdepMdepChgrSetaEntity = mdepList.get(0);
                    Date date1 = mdepMdepChgrSetaEntity.getMdep_chgr_beg();//未来生效的日期
                    Date date2 = deptChargerApplyOrdeDTO.getOdcsBeg();
                    if ((date1.getTime() - date2.getTime()) > 0) {
                        String deptName = queryOrdeDeptName(odcsChrgSetDTO.getOdcsDept());
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        String date = sdf.format(mdepMdepChgrSetaEntity.getMdep_chgr_beg());
                        jsonObject.put("message", deptName + "存在" + date + "生效负责人，请调整生效日期");
                        return jsonObject;
                    }
                }
            }

        }
        return jsonObject;
    }

    /**
     * 查询单据制单岗位名称
     *
     * @return
     * @params ordeDuty
     */

    private String queryAddUserName(String userId) {
        String ordeAddUserName = "";
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn("remp_name").addColumn("remp_no");
        String whereCondition = ormParam.getEqualXML("id", userId);
        ormParam.setWhereExp(whereCondition);
        try {
            List<Map<String, Object>> list = ormService.selectMapList(EmployeeEntity.class, ormParam);
            JSONObject jsonObject = (JSONObject) JSON.toJSON(list.get(0));
            ordeAddUserName = (String) jsonObject.get("remp_name") + "/" + (String) jsonObject.get("remp_no");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ordeAddUserName;

    }

    /**
     * 查询单据制单岗位名称
     *
     * @return
     * @params ordeDuty
     */
    private String queryOrdeDutyName(String ordeDuty) {
        String ordeDutyName = "";
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn("rpos_name");
        String whereCondition = ormParam.getEqualXML("id", ordeDuty);
        ormParam.setWhereExp(whereCondition);
        try {
            List<Map<String, Object>> list = ormService.selectMapList(JobpositionEntity.class, ormParam);
            JSONObject jsonObject = (JSONObject) JSON.toJSON(list.get(0));
            ordeDutyName = (String) jsonObject.get("rpos_name");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ordeDutyName;
    }

    /**
     * 查询单据部门名称
     *
     * @return
     * @params ordeDept
     */
    private String queryOrdeDeptName(String ordeDept) {
        String ordeDeptName = "";
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn("mdep_name");
        String whereCondition = ormParam.getEqualXML("id", ordeDept);
        ormParam.setWhereExp(whereCondition);
        try {
            List<Map<String, Object>> list = ormService.selectMapList(DepttreeEntity.class, ormParam);
            JSONObject jsonObject = (JSONObject) JSON.toJSON(list.get(0));
            ordeDeptName = (String) jsonObject.get("mdep_name");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ordeDeptName;
    }

    /**
     * 单据状态修改方法
     *
     * @return
     * @params orderId
     * @params status
     */
    private Result updateOrderStatus(String orderId, String status) {
        Result result = new Result();
        try {
            DeptchargerapplyorderEntity entity = new DeptchargerapplyorderEntity();
            entity.setId(orderId);
            entity.setOrde_status(status);
            ormService.updateSelective(entity);
            result.setRetCode(Result.RECODE_SUCCESS);
        } catch (Exception e) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(e.getMessage());
            logger.debug(e.getMessage());
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    @Override
    @TxTransaction(rollbackFor = Exception.class)
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Result audit(JSONObject auditSet) {
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
     * 获取编号(职位编码，单据编号)
     *
     * @param nbrlCode
     * @return
     */
    public String getCode(String nbrlCode) {
        Result codeResult = informationClient.getNumbers(nbrlCode, null);
        if (codeResult.getData() == null) {
            throw new ApplicationException(Result.RECODE_ERROR, codeResult.getErrMsg());
        }
        return codeResult.getData().toString();
    }

    //更新员工类岗位岗级
    public void updateEmployee(OdcsChrgSetDTO addOdcsChrgSetDTO) throws Exception {
        CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(addOdcsChrgSetDTO.getOdcsEmp());//员工id
        if (!StringUtil.isNullOrEmpty(addOdcsChrgSetDTO.getOdcsPost())) {
            employeeEntity.setRemp_post(addOdcsChrgSetDTO.getOdcsPost());//任职岗位
            OrmParam ormParam = new OrmParam();
            ormParam.setWhereExp(ormParam.getEqualXML("id", addOdcsChrgSetDTO.getOdcsPost()));
            ormParam.addColumn("id").addColumn("rpos_grade");
            List<Map<String, Object>> list = ormService.selectMapList(JobpositionEntity.class, ormParam);
            if (list.size() > 0) {
                Map<String, Object> map = list.get(0);
                String pgrade = (String) map.get("rpos_grade");
                employeeEntity.setRemp_pgrade(pgrade);//岗级
            }
        }
        if (!StringUtil.isNullOrEmpty(addOdcsChrgSetDTO.getOdcsDept())) {
            employeeEntity.setRemp_dept(addOdcsChrgSetDTO.getOdcsDept());//部门
            OrmParam mcopParam = new OrmParam();
            mcopParam.addColumn("id").addColumn("mdep_mcop");
            mcopParam.setWhereExp(mcopParam.getEqualXML("id", addOdcsChrgSetDTO.getOdcsDept()));
            List<Map<String, Object>> mcopList = ormService.selectMapList(DepttreeEntity.class, mcopParam);
            if (mcopList.size() > 0) {
                Map<String, Object> map = mcopList.get(0);
                String mcop = (String) map.get("mdep_mcop");
                employeeEntity.setRemp_mcop(mcop);//法人公司
            }
        }
        employeeEntity.setModuser(sessionEntity.getEmployeeId());//更新人
        employeeEntity.setModtime(new Date());
        int val = ormService.updateSelective(employeeEntity);
    }

    //load岗位信息
    public JobpositionEntity getJobpositionEntity(String id) throws Exception {
        return ormService.load(JobpositionEntity.class, id);
    }

    //load员工信息
    public EmployeeEntity loadEmployeeEntity(String id) throws Exception {
        return ormService.load(EmployeeEntity.class, id);
    }
}
