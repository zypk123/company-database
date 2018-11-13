package com.huntkey.rx.hr.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.codingapi.tx.annotation.TxTransaction;
import com.github.tobato.fastdfs.domain.MateData;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.DefaultFastFileStorageClient;
import com.huntkey.rx.commons.utils.datetime.DateUtil;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.edm.entity.*;
import com.huntkey.rx.hr.common.constants.OrderStatusConstants;
import com.huntkey.rx.hr.common.constants.WFHandlerTypeConstants;
import com.huntkey.rx.hr.common.constants.WorkFlowConstants;
import com.huntkey.rx.hr.common.exception.ApplicationException;
import com.huntkey.rx.hr.common.model.*;
import com.huntkey.rx.hr.common.util.DateDiff;
import com.huntkey.rx.hr.common.util.NullUtils;
import com.huntkey.rx.hr.provider.dao.EmployeeEntryApplyDao;
import com.huntkey.rx.hr.provider.service.BizFormService;
import com.huntkey.rx.hr.provider.service.EmployeeEntryApplyService;
import com.huntkey.rx.hr.provider.service.EmployeeService;
import com.huntkey.rx.sceo.method.register.plugin.entity.ParamsVo;
import com.huntkey.rx.sceo.method.register.plugin.util.ExecUtil;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;
import com.huntkey.rx.sceo.orm.service.OrmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by weijian on 2017/11/16.
 */
@Service
public class EmployeeEntryApplyServiceImpl implements EmployeeEntryApplyService {
    private static Logger logger = LoggerFactory.getLogger(EmployeeEntryApplyServiceImpl.class);
    @Autowired
    private HttpServletRequest request;
    @Autowired
    BizFormService bizFormService;
    @Autowired
    OrmService ormService;
    @Autowired
    private EmployeeEntryApplyDao employeeEntryApplyDao;
    @Autowired
    EmployeeService employee;
    @Autowired
    private DefaultFastFileStorageClient defaultFastFileStorageClient;

    @Value("${nginxIp}")
    String nginxIp;

    /**
     * 职员入职单类加载
     */
    @Override
    public Result load(String id) {
        Result result = new Result();
        try {
            JSONObject jsonObject = employeeEntryApplyDao.load(id);
            EmployeeEntryApplyDTO employeeEntryApplyDTO = JSONObject.parseObject(JSONObject.toJSONString(jsonObject), EmployeeEntryApplyDTO.class);//转Dto
            String ordeAddUserName = queryAddUserName(employeeEntryApplyDTO.getOrdeAdduser());
            employeeEntryApplyDTO.setOrdeAdduserName(ordeAddUserName);
            String ordeDutyName = queryOrdeDutyName(employeeEntryApplyDTO.getOrdeDuty());
            employeeEntryApplyDTO.setOrdeDutyName(ordeDutyName);
            String ordeDeptName = queryOrdeDeptName(employeeEntryApplyDTO.getOrdeDept());
            employeeEntryApplyDTO.setOrdeDeptName(ordeDeptName);
            String postId = employeeEntryApplyDTO.getOeeoPost();
            if (!StringUtil.isNullOrEmpty(postId)) {
                String postName = queryOrdeDutyName(postId);
                employeeEntryApplyDTO.setOeeoPostName(postName);//oeeoPostName 岗位名称
            }
            String deptId = employeeEntryApplyDTO.getOeeoDept();
            if (!StringUtil.isNullOrEmpty(deptId)) {
                String deptName = queryOrdeDeptName(deptId);
                employeeEntryApplyDTO.setOeeoDeptName(deptName);//oeeoDeptName 部门名称
            }
            if (!StringUtil.isNullOrEmpty(employeeEntryApplyDTO.getOeeoIntr())) {
                String oeeoIntrName = queryEmployee(employeeEntryApplyDTO.getOeeoIntr());//介绍人
                employeeEntryApplyDTO.setOeeoIntrName(oeeoIntrName);//介绍人
            }
            //指引人oeeo_guide
            if (!StringUtil.isNullOrEmpty(employeeEntryApplyDTO.getOeeoGuide())) {
                String oeeoGuideName = queryEmployee(employeeEntryApplyDTO.getOeeoGuide());//指引人oeeo_guide
                employeeEntryApplyDTO.setOeeoGuideName(oeeoGuideName);//指引人oeeo_guide
            }
            if ("0".equals(employeeEntryApplyDTO.getOeeoType())) {//档案变更单
                //String deptId = employeeEntryApplyDTO.getOeeoDept();
                if (!StringUtil.isNullOrEmpty(deptId)) {
                    String rpakName = queryRpakName(deptId);
                    employeeEntryApplyDTO.setRpakName(rpakName);//rpakName 办公园区
                }
                String rempNo = employeeEntryApplyDTO.getOeeoCode();//工号
                String rempMcop = queryRempMcop(rempNo);//查询员工法人
                if (!StringUtil.isNullOrEmpty(rempMcop)) {
                    String mcopName = queryRelation(rempMcop);
                    employeeEntryApplyDTO.setOeeoMcopName(mcopName);//oeeoMcopName 法人公司
                }
                String oeeoEnDate = employeeEntryApplyDTO.getOeeoEnDate();//入职日期
                if (!StringUtil.isNullOrEmpty(oeeoEnDate)) {
                    String campanyAge = String.valueOf(DateDiff.yearDateDiff(Long.parseLong(oeeoEnDate), new Date()));
                    //String campanyAge = calculateYears(oeeoEnDate);//司龄计算
                    employeeEntryApplyDTO.setCampanyAge(campanyAge);//oeeoCampanyAge 司龄
                }

                if (!StringUtil.isNullOrEmpty(postId)) {
                    JobpositionEntity jobpositionEntity = getJobpositionEntity(postId);
                    String rposPgrade = jobpositionEntity.getRpos_grade();
                    employeeEntryApplyDTO.setOeeoPgrade(rposPgrade);//岗级
                    String rposPpost = jobpositionEntity.getRpos_ppost();//汇报岗位
                    if (!StringUtil.isNullOrEmpty(rposPpost)) {
                        JobpositionEntity jobpositionEntity1 = getJobpositionEntity(rposPpost);
                        String rposEmp = jobpositionEntity1.getRpos_emp();//汇报岗位任职人
                        if (!StringUtil.isNullOrEmpty(rposEmp)) {
                            EmployeeEntity postEmployee = loadEmployeeEntity(rposEmp);
                            String rempName = postEmployee.getRemp_name();
                            employeeEntryApplyDTO.setOeeoPpostName(rempName);//直属上级
                        }
                    }
                }
            }
            List<OeeoStudySetDTO> studylist = employeeEntryApplyDTO.getOeeoStudySet();
            if (null != studylist) {
                for (OeeoStudySetDTO oeeoStudySetDTO : studylist) {
                    JSONObject object = JSONObject.parseObject(JSON.toJSONString(oeeoStudySetDTO));//驼峰格式转下划线
                    String oeeoRsch = (String) object.get("oeeo_rsch");
                    String oeeoRschName = getSchoolName(oeeoRsch);
                    oeeoStudySetDTO.setOeeoRschName(oeeoRschName);
                }
                employeeEntryApplyDTO.setOeeoStudySet(studylist);
            }
            if (!StringUtil.isNullOrEmpty(employeeEntryApplyDTO.getOeeoPicUrl())) {
                String picUrl = nginxIp + employeeEntryApplyDTO.getOeeoPicUrl();//图片地址增加服务器地址
                employeeEntryApplyDTO.setOeeoPicUrlNew(picUrl);
            }
            result.setData(employeeEntryApplyDTO);
            result.setRetCode(Result.RECODE_SUCCESS);
        } catch (Exception e) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    //load员工信息
    public EmployeeEntity loadEmployeeEntity(String id) throws Exception {
        return ormService.load(EmployeeEntity.class, id);
    }

    //load岗位信息
    public JobpositionEntity getJobpositionEntity(String id) throws Exception {
        return ormService.load(JobpositionEntity.class, id);
    }

    //根据员工号查询员工信息
    public EmployeeEntity getEmployee(String rempNo) throws Exception {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        OrmParam ormParam = new OrmParam();
        ormParam.setWhereExp(ormParam.getEqualXML("remp_no", rempNo));
        List<EmployeeEntity> list = ormService.selectBeanList(EmployeeEntity.class, ormParam);
        if (list.size() > 0) {
            employeeEntity = list.get(0);
        }
        return employeeEntity;
    }

    /**
     * 职员入职单保存方法
     *
     * @param employeeEntryApplyDTO
     * @return
     */
    @Override
    @Transactional
    public Result saveAddOrder(EmployeeEntryApplyDTO employeeEntryApplyDTO) throws Exception {
        Result result = new Result();
        //根据session中的当前员工和岗位赋值单据的制单人和制单岗位
        CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
        employeeEntryApplyDTO.setOrdeAdduser(sessionEntity.getEmployeeId());
        employeeEntryApplyDTO.setOrdeDuty(sessionEntity.getPositionId());
        employeeEntryApplyDTO.setEdmdEnte(sessionEntity.getEnterpriseId());
        //设置岗位岗级
        if (!StringUtil.isNullOrEmpty(employeeEntryApplyDTO.getOeeoPost())) {
            String pgrade = queryPostGrade(employeeEntryApplyDTO.getOeeoPost());
            employeeEntryApplyDTO.setOeeoPgrade(pgrade);
        }
        JSONObject checkDataObj = checkData(employeeEntryApplyDTO);
        if (!checkDataObj.isEmpty()) {
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            result.setErrMsg((String) checkDataObj.get("message"));
            return result;
        }
        employeeEntryApplyDTO.setOeeoType("1");
        //图片路径处理
        String picUrl = employeeEntryApplyDTO.getOeeoPicUrl();
        if (!StringUtil.isNullOrEmpty(picUrl)) {
            picUrl = picUrl.replace(nginxIp, "");
            employeeEntryApplyDTO.setOeeoPicUrl(picUrl);
        }
        //判断单据保存、修改
        if (employeeEntryApplyDTO.getId() != null) {
            result = employeeEntryApplyDao.updateEmployeeEntryApply(employeeEntryApplyDTO);//update
        } else {
            result = employeeEntryApplyDao.insertEmployeeEntryApply(employeeEntryApplyDTO);//insert
        }
        return result;
    }

    /**
     * 职员入职单保存方法必填校验
     *
     * @param employeeEntryApplyDTO
     * @return
     */
    private JSONObject checkData(EmployeeEntryApplyDTO employeeEntryApplyDTO) throws Exception {
        JSONObject jsonObject = new JSONObject();
        if (StringUtil.isNullOrEmpty(employeeEntryApplyDTO.getOeeoName())) {
            jsonObject.put("message", "姓名不可为空！");
            return jsonObject;
        }
        if (StringUtil.isNullOrEmpty(employeeEntryApplyDTO.getOeeoCardNo())) {
            jsonObject.put("message", "证件号码不可为空！");
            return jsonObject;
        }
        if (StringUtil.isNullOrEmpty(employeeEntryApplyDTO.getOeeoTel())) {
            jsonObject.put("message", "手机号码不可为空！");
            return jsonObject;
        }
        String ordeRodeObj = employeeEntryApplyDTO.getOrdeRodeObj();// 单据定义类型id
        String ordeAddUser = employeeEntryApplyDTO.getOrdeAdduser();// 制单人
        String ordeDuty = employeeEntryApplyDTO.getOrdeDuty();// 制单岗位
        String ordeDept = employeeEntryApplyDTO.getOrdeDept();// 制单部门
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
        if (!StringUtil.isNullOrEmpty(employeeEntryApplyDTO.getOeeoPost()) && employeeEntryApplyDTO.getOeeoType().equals("1")) {
            //判断所选择岗位是否存在入职单
            OrmParam ormParam = new OrmParam();
            ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML("oeeo_post", employeeEntryApplyDTO.getOeeoPost()), OrmParam.or(ormParam.getEqualXML("orde_status", "2"), ormParam.getEqualXML("orde_status", "3"), ormParam.getEqualXML("orde_status", "4"))));
            try {
                List<EmployeeentryapplyEntity> list = ormService.selectBeanList(EmployeeentryapplyEntity.class, ormParam);
                if (list.size() > 0) {
                    jsonObject.put("message", "任职岗位已被占用，请选择其他岗位");
                    return jsonObject;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //1.判断岗位是否存在排岗的待审单
        if (!StringUtil.isNullOrEmpty(employeeEntryApplyDTO.getOeeoPost()) && employeeEntryApplyDTO.getOeeoType().equals("1")) {
            OrmParam ormParam = new OrmParam();
            ormParam.setWhereExp(ormParam.getEqualXML("oepc_post", employeeEntryApplyDTO.getOeeoPost()));
            List<OepcOepcChangSetaEntity> list = ormService.selectBeanList(OepcOepcChangSetaEntity.class, ormParam);
            for (OepcOepcChangSetaEntity oepcOepcChangSetaEntity : list) {
                String pid = oepcOepcChangSetaEntity.getPid();
                OrmParam ormParam1 = new OrmParam();
                ormParam1.setWhereExp(ormParam1.and(ormParam1.getEqualXML("id", pid), ormParam1.or(ormParam1.getEqualXML("orde_status", "2"), ormParam1.getEqualXML("orde_status", "3"), ormParam1.getEqualXML("orde_status", "4"))));
                List<EmppostchangeapplyEntity> ordeList = ormService.selectBeanList(EmppostchangeapplyEntity.class, ormParam1);
                if (ordeList.size() > 0) {
                    EmppostchangeapplyEntity emppostchangeapplyEntity = ordeList.get(0);
                    String ordeNbr = emppostchangeapplyEntity.getOrde_nbr();
                    jsonObject.put("message", "所选岗位存在未完成岗位调整单:" + ordeNbr + "，请选择其它岗位");
                    return jsonObject;
                }
            }
        }
        //2.判断身份证号是否被占用
        if (!StringUtil.isNullOrEmpty(employeeEntryApplyDTO.getOeeoCardNo()) && employeeEntryApplyDTO.getOeeoType().equals("1")) {
            OrmParam ormParam = new OrmParam();
            ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML("oeeo_card_no", employeeEntryApplyDTO.getOeeoCardNo()),
                    ormParam.getEqualXML("oeeo_type", "1"),
                    OrmParam.or(ormParam.getEqualXML("orde_status", "2"), ormParam.getEqualXML("orde_status", "3"), ormParam.getEqualXML("orde_status", "4"))));
            List<EmployeeentryapplyEntity> list = ormService.selectBeanList(EmployeeentryapplyEntity.class, ormParam);
            if (list.size() > 0) {
                EmployeeentryapplyEntity employeeentryapplyEntity = list.get(0);
                String ordeNbr = employeeentryapplyEntity.getOrde_nbr();
                jsonObject.put("message", "此证件号码存在待审入职单，单据编号：" + ordeNbr + ",请重新填写");
                return jsonObject;
            }
        }
        //3.任免岗位为所选岗位的责任人任免单校验
        if (!StringUtil.isNullOrEmpty(employeeEntryApplyDTO.getOeeoPost()) && employeeEntryApplyDTO.getOeeoType().equals("1")) {
            //判断岗位是否存在排岗的待审单
            OrmParam ormParam = new OrmParam();
            ormParam.setWhereExp(ormParam.getEqualXML("odcs_post", employeeEntryApplyDTO.getOeeoPost()));
            List<OdcsOdcsChrgSetaEntity> list = ormService.selectBeanList(OdcsOdcsChrgSetaEntity.class, ormParam);
            for (OdcsOdcsChrgSetaEntity odcsOdcsChrgSetaEntity : list) {
                String pid = odcsOdcsChrgSetaEntity.getPid();
                OrmParam ormParam1 = new OrmParam();
                ormParam1.setWhereExp(ormParam1.and(ormParam1.getEqualXML("id", pid), ormParam1.or(ormParam1.getEqualXML("orde_status", "2"), ormParam1.getEqualXML("orde_status", "3"), ormParam1.getEqualXML("orde_status", "4"))));
                List<DeptchargerapplyorderEntity> ordeList = ormService.selectBeanList(DeptchargerapplyorderEntity.class, ormParam1);
                if (ordeList.size() > 0) {
                    DeptchargerapplyorderEntity deptchargerapplyorderEntity = ordeList.get(0);
                    String ordeNbr = deptchargerapplyorderEntity.getOrde_nbr();
                    jsonObject.put("message", "所选岗位存在未完成的部门责任人任免单:" + ordeNbr + "，请选择其它岗位");
                    return jsonObject;
                }
            }
        }

        //4.手机号码待审单校验
        if (!StringUtil.isNullOrEmpty(employeeEntryApplyDTO.getOeeoTel()) && employeeEntryApplyDTO.getOeeoType().equals("1")) {
            OrmParam ormParam = new OrmParam();
            ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML("oeeo_tel", employeeEntryApplyDTO.getOeeoTel()),
                    ormParam.getEqualXML("oeeo_type", "1"),
                    OrmParam.or(ormParam.getEqualXML("orde_status", "2"), ormParam.getEqualXML("orde_status", "3"), ormParam.getEqualXML("orde_status", "4"))));
            List<EmployeeentryapplyEntity> list = ormService.selectBeanList(EmployeeentryapplyEntity.class, ormParam);
            if (list.size() > 0) {
                EmployeeentryapplyEntity employeeentryapplyEntity = list.get(0);
                String ordeNbr = employeeentryapplyEntity.getOrde_nbr();
                jsonObject.put("message", "此手机号码存在待审入职单，单据编号：" + ordeNbr + ",请重新填写");
                return jsonObject;
            }
        }

        //5.入职单判端岗位是否已有员工
        if (!StringUtil.isNullOrEmpty(employeeEntryApplyDTO.getOeeoPost()) && employeeEntryApplyDTO.getOeeoType().equals("1")) {
            OrmParam jobOrm = new OrmParam();
            jobOrm.setWhereExp(jobOrm.getEqualXML("id", employeeEntryApplyDTO.getOeeoPost()));
            List<JobpositionEntity> job = ormService.selectBeanList(JobpositionEntity.class, jobOrm);
            if (job.size() > 0) {
                JobpositionEntity jobpositionEntity = job.get(0);
                if (!StringUtil.isNullOrEmpty(jobpositionEntity.getRpos_emp())) {
                    jsonObject.put("message", "所选岗位:" + jobpositionEntity.getRpos_name() + "已安排员工，请选择其它岗位");
                    return jsonObject;
                }
            }
        }
        return jsonObject;
    }

    /**
     * 职员入职单提交方法
     *
     * @param employeeEntryApplyDTO
     * @return
     */
    @Override
    public Result submitAddOrder(EmployeeEntryApplyDTO employeeEntryApplyDTO) throws Exception {
        Result result = new Result();
        //根据session中的当前员工和岗位赋值单据的制单人和制单岗位
        CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
        employeeEntryApplyDTO.setOrdeAdduser(sessionEntity.getEmployeeId());
        employeeEntryApplyDTO.setOrdeDuty(sessionEntity.getPositionId());
        employeeEntryApplyDTO.setEdmdEnte(sessionEntity.getEnterpriseId());
        employeeEntryApplyDTO.setOeeoType("1");
        employeeEntryApplyDTO.setOrdeStatus(OrderStatusConstants.ORDER_STATUS_1);//单据装态:待提
        //设置岗位岗级
        if (!StringUtil.isNullOrEmpty(employeeEntryApplyDTO.getOeeoPost())) {
            String pgrade = queryPostGrade(employeeEntryApplyDTO.getOeeoPost());
            employeeEntryApplyDTO.setOeeoPgrade(pgrade);
        }
        result = saveSubmit(employeeEntryApplyDTO);
        if (result.getRetCode().equals(Result.RECODE_SUCCESS) && result.getData() != null) {
            JSONObject jsonObject = (JSONObject) result.getData();
            String orderInstanceId = (String) jsonObject.get("id");
            String ordeNbr = (String) jsonObject.get("ordeNbr");//单据编号
            //提交流程方法
            String orderDefId = employeeEntryApplyDTO.getOrdeRodeObj();
            return submitUpdateStatus(orderDefId, orderInstanceId, ordeNbr);
        } else {
            return result;
        }
    }

    @TxTransaction(rollbackFor = Exception.class)
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Result submitUpdateStatus(String orderDefId, String orderInstanceId, String ordeNbr) throws Exception {
        Result result = new Result();
        bizFormService.submitWorkFlow(orderDefId, orderInstanceId);// 提交流程
        updateOrderStatus(orderInstanceId, OrderStatusConstants.ORDER_STATUS_2);// 更新状态：待审
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
    public Result saveSubmit(EmployeeEntryApplyDTO employeeEntryApplyDTO) throws Exception {
        Result result = new Result();
        JSONObject checkDataObj = checkData(employeeEntryApplyDTO);
        if (!checkDataObj.isEmpty()) {
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            result.setErrMsg((String) checkDataObj.get("message"));
            return result;
        }
        if (employeeEntryApplyDTO.getId() != null) {
            result = employeeEntryApplyDao.updateEmployeeEntryApply(employeeEntryApplyDTO);//update
        } else {
            result = employeeEntryApplyDao.insertEmployeeEntryApply(employeeEntryApplyDTO);//insert保存加提交
        }
        return result;
    }

    /**
     * 职员入职单通过方法
     *
     * @param orderInstanceId
     * @param handlerType
     * @return
     */
    @Override
    public Result passAddOrder(String orderInstanceId, String handlerType) {
        Result result = new Result();
        logger.info("职位维护单审批通过回调接口：orderInstanceId：" + orderInstanceId + ",handlerType:" + handlerType);
        switch (handlerType) {
            case WFHandlerTypeConstants.PASS: {
                //TODO 单据状态改为 完成；将单据数据写入资源表
                result = passMethod(orderInstanceId);
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
        return result;
    }

    /**
     * 加载职员档案变更
     *
     * @param employeeId 员工ID
     * @param oeeoCode   员工工号
     * @return
     */
    @Override
    public Result loadEditOrder(String employeeId, String oeeoCode) throws Exception {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        OrmParam ormParam = new OrmParam();
        String whereExp = OrmParam.and(ormParam.getEqualXML("oeeo_code", oeeoCode), ormParam.getEqualXML("oeeo_type", "0"), ormParam.getEqualXML("orde_status", "1"));
        ormParam.setWhereExp(whereExp);
        ormParam.addColumn("id");
        List<Map<String, Object>> list = ormService.selectMapList(EmployeeentryapplyEntity.class, ormParam);
        String id = "";
        JSONObject dataObj = new JSONObject();
        if (list.size() > 0) {
            Map<String, Object> map = list.get(0);
            id = (String) map.get("id");
        }
        if (!StringUtil.isNullOrEmpty(id)) {
            dataObj = employeeEntryApplyDao.load(id);
        }
        if (!dataObj.isEmpty()) {
            //返回档案变更单
            EmployeeEntryApplyDTO employeeEntryApplyDTO = JSONObject.parseObject(JSONObject.toJSONString(dataObj), EmployeeEntryApplyDTO.class);
            if ("0".equals(employeeEntryApplyDTO.getOeeoType())) {//档案变更单
                String deptId = employeeEntryApplyDTO.getOeeoDept();
                if (!StringUtil.isNullOrEmpty(deptId)) {
                    String deptName = queryOrdeDeptName(deptId);
                    employeeEntryApplyDTO.setOeeoDeptName(deptName);//oeeoDeptName 部门名称
                    String rpakName = queryRpakName(deptId);
                    employeeEntryApplyDTO.setRpakName(rpakName);//rpakName 办公园区
                }
                String postId = employeeEntryApplyDTO.getOeeoPost();
                String postName = queryOrdeDutyName(postId);
                employeeEntryApplyDTO.setOeeoPostName(postName);//oeeoPostName 岗位名称
                if (!StringUtil.isNullOrEmpty(postId)) {
                    JobpositionEntity jobpositionEntity = getJobpositionEntity(postId);
                    String rposPgrade = jobpositionEntity.getRpos_grade();
                    employeeEntryApplyDTO.setOeeoPgrade(rposPgrade);//岗级
                    String rposPpost = jobpositionEntity.getRpos_ppost();//汇报岗位
                    if (!StringUtil.isNullOrEmpty(rposPpost)) {
                        JobpositionEntity jobpositionEntity1 = getJobpositionEntity(rposPpost);
                        String rposEmp = jobpositionEntity1.getRpos_emp();//汇报岗位任职人
                        if (!StringUtil.isNullOrEmpty(rposEmp)) {
                            EmployeeEntity postEmployee = loadEmployeeEntity(rposEmp);
                            String rempName = postEmployee.getRemp_name();
                            employeeEntryApplyDTO.setOeeoPpostName(rempName);//直属上级
                        }
                    }
                }
                String rempNo = employeeEntryApplyDTO.getOeeoCode();//工号
                String rempMcop = queryRempMcop(rempNo);//查询员工法人
                if (!StringUtil.isNullOrEmpty(rempMcop)) {
                    String mcopName = queryRelation(rempMcop);
                    employeeEntryApplyDTO.setOeeoMcopName(mcopName);//oeeoMcopName 法人公司
                }
                String oeeoEnDate = employeeEntryApplyDTO.getOeeoEnDate();//入职日期
                if (!StringUtil.isNullOrEmpty(rempMcop)) {
                    String campanyAge = String.valueOf(DateDiff.yearDateDiff(Long.parseLong(oeeoEnDate), new Date()));
                    //String campanyAge = calculateYears(oeeoEnDate);//司龄计算
                    employeeEntryApplyDTO.setCampanyAge(campanyAge);//oeeoCampanyAge 司龄
                }
                if (!StringUtil.isNullOrEmpty(employeeEntryApplyDTO.getOeeoIntr())) {
                    String oeeoIntrName = queryEmployee(employeeEntryApplyDTO.getOeeoIntr());//介绍人
                    employeeEntryApplyDTO.setOeeoIntrName(oeeoIntrName);//介绍人
                }
                List<OeeoStudySetDTO> studylist = employeeEntryApplyDTO.getOeeoStudySet();
                if (null != studylist) {
                    for (OeeoStudySetDTO oeeoStudySetDTO : studylist) {
                        JSONObject object = JSONObject.parseObject(JSON.toJSONString(oeeoStudySetDTO));//驼峰格式转下划线
                        String oeeoRsch = (String) object.get("oeeo_rsch");
                        String oeeoRschName = getSchoolName(oeeoRsch);
                        oeeoStudySetDTO.setOeeoRschName(oeeoRschName);
                    }
                    employeeEntryApplyDTO.setOeeoStudySet(studylist);
                }
                if (!StringUtil.isNullOrEmpty(employeeEntryApplyDTO.getOeeoPicUrl())) {
                    String picUrl = nginxIp + employeeEntryApplyDTO.getOeeoPicUrl();//图片地址增加服务器地址
                    employeeEntryApplyDTO.setOeeoPicUrlNew(picUrl);
                }
            }
            result.setData(employeeEntryApplyDTO);
        } else {
            //调用员工类方法查询
            JSONObject employeeObj = employee.queryEmployeeById(employeeId, true, true);
            //查询档案变更需要的结果集
            EmpToOrderDTO empToOrderDTO = JSONObject.parseObject(JSONObject.toJSONString(employeeObj), EmpToOrderDTO.class);
            List<EmpStudyToOrderDTO> studylist = empToOrderDTO.getOeeoStudySet();
            if (null != studylist) {
                for (EmpStudyToOrderDTO empStudyToOrderDTO : studylist) {
                    JSONObject object = JSONObject.parseObject(JSON.toJSONString(empStudyToOrderDTO));//驼峰格式转下划线
                    String oeeoRsch = (String) object.get("remp_rsch");
                    String oeeoRschName = getSchoolName(oeeoRsch);
                    empStudyToOrderDTO.setOeeoRschName(oeeoRschName);
                }
                empToOrderDTO.setOeeoStudySet(studylist);
            }
            //根据岗位id查询岗位名称，岗级，直属上级
            String postId = empToOrderDTO.getOeeoPost();//岗位id
            if (!StringUtil.isNullOrEmpty(postId)) {
                String postName = queryOrdeDutyName(postId);
                empToOrderDTO.setOeeoPostName(postName);//oeeoPostName 岗位名称
                JobpositionEntity jobpositionEntity = getJobpositionEntity(postId);
                String rposPgrade = jobpositionEntity.getRpos_grade();
                empToOrderDTO.setOeeoPgrade(rposPgrade);//岗级
                String rposPpost = jobpositionEntity.getRpos_ppost();//汇报岗位
                if (!StringUtil.isNullOrEmpty(rposPpost)) {
                    JobpositionEntity jobpositionEntity1 = getJobpositionEntity(rposPpost);
                    String rposEmp = jobpositionEntity1.getRpos_emp();//汇报岗位任职人
                    if (!StringUtil.isNullOrEmpty(rposEmp)) {
                        EmployeeEntity postEmployee = loadEmployeeEntity(rposEmp);
                        String rempName = postEmployee.getRemp_name();
                        empToOrderDTO.setOeeoPpostName(rempName);//直属上级
                    }
                }
            }
            if (!StringUtil.isNullOrEmpty(empToOrderDTO.getOeeoPicUrl())) {
                String picUrl = nginxIp + empToOrderDTO.getOeeoPicUrl();
                empToOrderDTO.setOeeoPicUrlNew(picUrl);
            }
            result.setData(empToOrderDTO);
        }
        return result;
    }

    /**
     * 保存职员档案变更
     *
     * @param employeeEntryApplyDTO
     * @return
     */
    @Override
    @Transactional
    public Result saveEditOrder(EmployeeEntryApplyDTO employeeEntryApplyDTO) throws Exception {
        Result result = new Result();
        //根据session中的当前员工和岗位赋值单据的制单人和制单岗位
        CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
        employeeEntryApplyDTO.setOrdeAdduser(sessionEntity.getEmployeeId());
        employeeEntryApplyDTO.setOrdeDuty(sessionEntity.getPositionId());
        employeeEntryApplyDTO.setEdmdEnte(sessionEntity.getEnterpriseId());
        employeeEntryApplyDTO.setOeeoType("0");//单据类型：档案变更单
        employeeEntryApplyDTO.setOrdeStatus(OrderStatusConstants.ORDER_STATUS_1);//单据状态：临时单
        //图片路径处理
        String picUrl = employeeEntryApplyDTO.getOeeoPicUrl();
        if (!StringUtil.isNullOrEmpty(picUrl)) {
            picUrl = picUrl.replace(nginxIp, "");
            employeeEntryApplyDTO.setOeeoPicUrl(picUrl);
        }
        JSONObject checkDataObj = checkData(employeeEntryApplyDTO);
        if (!checkDataObj.isEmpty()) {
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            result.setErrMsg((String) checkDataObj.get("message"));
            return result;
        }
        if (StringUtil.isNullOrEmpty(employeeEntryApplyDTO.getId())) {
            result = employeeEntryApplyDao.insertEmployeeEntryApply(employeeEntryApplyDTO);
        } else {
            result = employeeEntryApplyDao.updateEmployeeEntryApply(employeeEntryApplyDTO);
        }
        return result;
    }

    /**
     * 档案变更单据提交方法
     *
     * @param employeeEntryApplyDTO
     * @return
     */
    @Override
    public Result submitEditOrder(EmployeeEntryApplyDTO employeeEntryApplyDTO) throws Exception {
        Result result = new Result();
        //根据session中的当前员工和岗位赋值单据的制单人和制单岗位
        CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
        employeeEntryApplyDTO.setOrdeAdduser(sessionEntity.getEmployeeId());
        employeeEntryApplyDTO.setOrdeDuty(sessionEntity.getPositionId());
        employeeEntryApplyDTO.setEdmdEnte(sessionEntity.getEnterpriseId());
        employeeEntryApplyDTO.setOeeoType("0");//单据类型：档案变更单
        employeeEntryApplyDTO.setOrdeStatus(OrderStatusConstants.ORDER_STATUS_1);//单据状态：待提
        result = saveEdit(employeeEntryApplyDTO);
        if (result.getRetCode().equals(Result.RECODE_SUCCESS) && result.getData() != null) {
            JSONObject jsonObject = (JSONObject) result.getData();
            String orderInstanceId = (String) jsonObject.get("id");
            String ordeNbr = (String) jsonObject.get("ordeNbr");//单据编号
            //提交流程方法,部门责任人任免单
            String orderDefId = employeeEntryApplyDTO.getOrdeRodeObj();
            return submitUpdateStatus(orderDefId, orderInstanceId, ordeNbr);
        } else {
            result.setData(null);
            result.setErrMsg(result.getErrMsg());
            result.setRetCode(Result.RECODE_ERROR);
            return result;
        }
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Result saveEdit(EmployeeEntryApplyDTO employeeEntryApplyDTO) throws Exception {
        Result result = new Result();
        JSONObject checkDataObj = checkData(employeeEntryApplyDTO);
        if (!checkDataObj.isEmpty()) {
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            result.setErrMsg((String) checkDataObj.get("message"));
            return result;
        }
        if (StringUtil.isNullOrEmpty(employeeEntryApplyDTO.getId())) {
            result = employeeEntryApplyDao.insertEmployeeEntryApply(employeeEntryApplyDTO);
        } else {
            result = employeeEntryApplyDao.updateEmployeeEntryApply(employeeEntryApplyDTO);
        }
        return result;
    }

    @Override
    public String uploadImage(MultipartFile upLoadFile) {
        //上传图片
        StorePath path = upload(upLoadFile);
        //更新imageUrl入库
        try {
            String abpath = path.getFullPath();
            logger.info("上传图片成功！" + "abpath：" + abpath);
            upLoadFile.getInputStream().close();
            return abpath;
        } catch (Exception e) {
            logger.error("更新图片失败！ " + e);
            throw new RuntimeException(e);
        }
    }

    private StorePath upload(MultipartFile upLoadFile) {
        InputStream inputStream = null;
        try {
            inputStream = upLoadFile.getInputStream();
            long fileSize = inputStream.available();
            String fileExtName = "jpg";
            Set<MateData> metaDataSet = new HashSet<MateData>();
            metaDataSet.add(new MateData("width", "800"));
            metaDataSet.add(new MateData("bgcolor", "FFFFFF"));
            metaDataSet.add(new MateData("author", "FirstMateData"));
            StorePath path = defaultFastFileStorageClient.uploadImageAndCrtThumbImage(inputStream, fileSize, fileExtName, metaDataSet);
            return path;
        } catch (IOException e) {
            logger.error("上传图片失败！ " + e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 档案变更单据批准通过方法
     *
     * @param employeeEntryApplyDTO
     * @return
     */
    private EmployeeDTO setEmployeeEntryApplyDTOtoEmployee(EmployeeDTO employeeDTO, EmployeeEntryApplyDTO employeeEntryApplyDTO) throws Exception {
        if (!StringUtil.isNullOrEmpty(employeeEntryApplyDTO.getOeeoDept())) {
            String rempMcop = queryMcop(employeeEntryApplyDTO.getOeeoDept());//根据部门ID查询法人公司
            employeeDTO.setRempMcop(rempMcop);//法人公司
        }
        employeeDTO.setEdmdEnte(employeeEntryApplyDTO.getEdmdEnte());//企业对象
        employeeDTO.setRempEpeoObj(employeeEntryApplyDTO.getOeeoEpeo());//自然人
        employeeDTO.setRempAddr(employeeEntryApplyDTO.getOeeoAddr());//现住址
        employeeDTO.setRempAssure(employeeEntryApplyDTO.getOeeoAssure());//担保人
        employeeDTO.setRempAssuRela(employeeEntryApplyDTO.getOeeoAssuRela());//担保人关系
        employeeDTO.setRempAssuTel(employeeEntryApplyDTO.getOeeoAssuTel());//担保人电话
        employeeDTO.setRempBirth(employeeEntryApplyDTO.getOeeoBirth());//出生日期
        employeeDTO.setRempBlood(employeeEntryApplyDTO.getOeeoBlood());//血型
        employeeDTO.setRempCardBeg(employeeEntryApplyDTO.getOeeoCardBeg());//证件生效日期
        employeeDTO.setRempCardEnd(employeeEntryApplyDTO.getOeeoCardEnd());//证件失效日期
        employeeDTO.setRempCardType(employeeEntryApplyDTO.getOeeoCardType());//证件类型
        employeeDTO.setRempCardId(employeeEntryApplyDTO.getOeeoCardNo());//身份证号
        employeeDTO.setRempCardOrgan(employeeEntryApplyDTO.getOeeoCardOrg());//发证机关
        employeeDTO.setRempDept(employeeEntryApplyDTO.getOeeoDept());//所在部门
        employeeDTO.setRempGender(employeeEntryApplyDTO.getOeeoGender());//性别
        //String height = employeeEntryApplyDTO.getOeeoHeight().substring(0, employeeEntryApplyDTO.getOeeoHeight().indexOf("."));
        employeeDTO.setRempHeight(employeeEntryApplyDTO.getOeeoHeight());//身高
        employeeDTO.setRempHomeAddr(employeeEntryApplyDTO.getOeeoHomeAddr());//家庭住址
        employeeDTO.setRempHtel(employeeEntryApplyDTO.getOeeoHtel());//家庭电话
        employeeDTO.setRempInDate(employeeEntryApplyDTO.getOeeoEnDate());//到职日期
        employeeDTO.setRempMail(employeeEntryApplyDTO.getOeeoMail());//电子邮件
        employeeDTO.setRempMaried(employeeEntryApplyDTO.getOeeoMaried());//婚否
        employeeDTO.setRempName(employeeEntryApplyDTO.getOeeoName());//姓名
        employeeDTO.setRempNameCn(employeeEntryApplyDTO.getOeeoNameCn());//姓名拼音
        employeeDTO.setRempNameEn(employeeEntryApplyDTO.getOeeoNameEn());//英文姓名
        employeeDTO.setRempNation(employeeEntryApplyDTO.getOeeoNation());//名族
        employeeDTO.setRempNo(employeeEntryApplyDTO.getOeeoCode());//工号
        employeeDTO.setRempPicUrl(employeeEntryApplyDTO.getOeeoPicUrl());//工作证照片
        employeeDTO.setRempOrigin(employeeEntryApplyDTO.getOeeoOrigin());//籍贯
        employeeDTO.setRempParty(employeeEntryApplyDTO.getOeeoParty());//政治面貌
        employeeDTO.setRempPgrade(employeeEntryApplyDTO.getOeeoPgrade());//岗级
        employeeDTO.setRempPost(employeeEntryApplyDTO.getOeeoPost());//岗位
        employeeDTO.setRempPostDate(employeeEntryApplyDTO.getOeeoPostDate());//转正日级
        employeeDTO.setRempRefEmp(employeeEntryApplyDTO.getOeeoIntr());//推荐人
        employeeDTO.setRempResidence(employeeEntryApplyDTO.getOeeoResidence());//户籍地址
        employeeDTO.setRempRgtPro(employeeEntryApplyDTO.getOeeoRgtPro());//户籍所在地
        employeeDTO.setRempRgtType(employeeEntryApplyDTO.getOeeoRgtType());//户籍类型
        employeeDTO.setRempSecuNo(employeeEntryApplyDTO.getOeeoSecuNo());//社保号
        employeeDTO.setRempSecuType(employeeEntryApplyDTO.getOeeoSecuType());//社保类型
        employeeDTO.setRempSelfEval(employeeEntryApplyDTO.getOeeoSelfEval());//自我评价
        employeeDTO.setRempSgrade(employeeEntryApplyDTO.getOeeoTrySgrade());//薪级
        employeeDTO.setRempTel(employeeEntryApplyDTO.getOeeoTel());//联系电话
        employeeDTO.setRempType(employeeEntryApplyDTO.getOeeoEmpType());//员工类型
        //String weight = employeeEntryApplyDTO.getOeeoWeight().substring(0, employeeEntryApplyDTO.getOeeoWeight().indexOf("."));
        employeeDTO.setRempWeight(String.valueOf(employeeEntryApplyDTO.getOeeoWeight()));//体重
        employeeDTO.setRempWorkDate(employeeEntryApplyDTO.getOeeoWorkDate());//首次工作日
        employeeDTO.setRempCont(employeeEntryApplyDTO.getOeeoCont());//紧急联系人
        employeeDTO.setRempContTel(employeeEntryApplyDTO.getOeeoContTel());//紧急联系人电话
        employeeDTO.setRempFirstName(employeeEntryApplyDTO.getOeeoFirstName());//姓
        employeeDTO.setRempLastName(employeeEntryApplyDTO.getOeeoLastName());//名


        if(employeeEntryApplyDTO.getOeeoType().equals("1")){//入职单插入一条新的认岗经历
            employeeDTO.setRempStatus("2");//入职单设置职员状态试用
            List<EmpPostSetDTO> rempPostSet = new ArrayList<EmpPostSetDTO>();//任岗经历
            EmpPostSetDTO empPostSetDTO = new EmpPostSetDTO();
            if (!StringUtil.isNullOrEmpty(employeeEntryApplyDTO.getOeeoPost())) {//岗位不为空时设置认岗经历
                empPostSetDTO.setRempPostHis(employeeEntryApplyDTO.getOeeoPost());//岗位
                empPostSetDTO.setRempPgradHis(employeeEntryApplyDTO.getOeeoPgrade());//岗级
                empPostSetDTO.setRempPostBeg(employeeEntryApplyDTO.getOeeoEnDate());//生效日期
                empPostSetDTO.setRempDtypHis("0");//任职方式默认为任职
                rempPostSet.add(empPostSetDTO);
                employeeDTO.setRempPostSet(rempPostSet);
            }
        }

        List<EmpStudyDTO> rempStudySet = new ArrayList<EmpStudyDTO>();
        List<OeeoStudySetDTO> oeeoStudySet = employeeEntryApplyDTO.getOeeoStudySet();//教育经历
        if (oeeoStudySet != null && oeeoStudySet.size() > 0) {
            for (OeeoStudySetDTO oeeoStudySetDTO : oeeoStudySet) {
                EmpStudyDTO empStudyDTO = new EmpStudyDTO();
                empStudyDTO.setRempStuBeg(oeeoStudySetDTO.getOeeoStuBeg());//起始时间
                empStudyDTO.setRempStuEnd(oeeoStudySetDTO.getOeeoStuEnd());//结束时间
                empStudyDTO.setRempRsch(oeeoStudySetDTO.getOeeoRsch());//学校
                empStudyDTO.setRempMajor(oeeoStudySetDTO.getOeeoMajor());//专业
                empStudyDTO.setRempDegree(oeeoStudySetDTO.getOeeoDegree());//学位
                empStudyDTO.setRempStuType(oeeoStudySetDTO.getOeeoStuType());//学历类型
                empStudyDTO.setRempStuMode(oeeoStudySetDTO.getOeeoStuMode());//培养方式
                empStudyDTO.setRempStuCert(oeeoStudySetDTO.getOeeoStuCert());//所获证书
                empStudyDTO.setRempCertNo(oeeoStudySetDTO.getOeeoCertNo());//证书编号
                rempStudySet.add(empStudyDTO);
            }
            employeeDTO.setRempStudySet(rempStudySet);
        }

        List<EmpSkillDTO> rempSkillSet = new ArrayList<EmpSkillDTO>();//专业技能
        List<OeeoSkillSetDTO> oeeoSkillSet = employeeEntryApplyDTO.getOeeoSkillSet();//专业技能
        if (oeeoSkillSet != null && oeeoSkillSet.size() > 0) {
            for (OeeoSkillSetDTO oeeoSkillSetDTO : oeeoSkillSet) {
                EmpSkillDTO empSkillDTO = new EmpSkillDTO();
                empSkillDTO.setRempSkillField(oeeoSkillSetDTO.getOeeoSkillField());//技能领域
                empSkillDTO.setRempSkillPro(oeeoSkillSetDTO.getOeeoSkillPro());//技能专业
                empSkillDTO.setRempUtilMon(oeeoSkillSetDTO.getOeeoUtilMon());//应用实践
                empSkillDTO.setRempMastLevel(oeeoSkillSetDTO.getOeeoMastLevel());//技能级别
                empSkillDTO.setRempSkillSeq(oeeoSkillSetDTO.getOeeoSkillSeq());//排序
                rempSkillSet.add(empSkillDTO);
            }
            employeeDTO.setRempSkillSet(rempSkillSet);
        }


        List<EmpWorkExpDTO> rempWorkSet = new ArrayList<EmpWorkExpDTO>();//工作经历
        List<OeeoWorkSetDTO> oeeoWorkSet = employeeEntryApplyDTO.getOeeoWorkSet();//工作经历
        if (oeeoWorkSet != null && oeeoWorkSet.size() > 0) {
            for (OeeoWorkSetDTO oeeoWorkSetDTO : oeeoWorkSet) {
                EmpWorkExpDTO empWorkExpDTO = new EmpWorkExpDTO();
                empWorkExpDTO.setRempWorkComp(oeeoWorkSetDTO.getOeeoWorkComp());//公司名称
                empWorkExpDTO.setRempWorkPost(oeeoWorkSetDTO.getOeeoWorkPost());//职位
                empWorkExpDTO.setRempWorkBeg(oeeoWorkSetDTO.getOeeoWorkBeg());//起始年月
                empWorkExpDTO.setRempWorkEnd(oeeoWorkSetDTO.getOeeoWorkEnd());////结束年月
                empWorkExpDTO.setRempWorkDesc(oeeoWorkSetDTO.getOeeoWorkDesc());//工作描述
                empWorkExpDTO.setRempCons(oeeoWorkSetDTO.getOeeoWorkCons());//咨询人
                empWorkExpDTO.setRempConsRela(oeeoWorkSetDTO.getOeeoConsRela());//咨询人关系
                empWorkExpDTO.setRempConsPost(oeeoWorkSetDTO.getOeeoConsPost());//咨询人职位
                empWorkExpDTO.setRempConsTel(oeeoWorkSetDTO.getOeeoConsTel());//咨询人电话
                rempWorkSet.add(empWorkExpDTO);
            }
            employeeDTO.setRempWorkSet(rempWorkSet);
        }

        List<EmpFamilyDTO> rempFamSet = new ArrayList<EmpFamilyDTO>();//家庭成员
        List<OeeoFamSetDTO> oeeoFamSet = employeeEntryApplyDTO.getOeeoFamSet();//家庭成员
        if (oeeoFamSet != null && oeeoFamSet.size() > 0) {
            for (OeeoFamSetDTO oeeoFamSetDTO : oeeoFamSet) {
                EmpFamilyDTO empFamilyDTO = new EmpFamilyDTO();
                empFamilyDTO.setRempFamName(oeeoFamSetDTO.getOeeoFamName());//姓名
                empFamilyDTO.setRempFamRela(oeeoFamSetDTO.getOeeoFamRela());//关系
                empFamilyDTO.setRempFamCom(oeeoFamSetDTO.getOeeoFamComp());//工作单位
                empFamilyDTO.setRempFamPost(oeeoFamSetDTO.getOeeoFamPost());//职位
                empFamilyDTO.setRempFamTel(oeeoFamSetDTO.getOeeoFamTel());//联系电话
                empFamilyDTO.setRempFamTel(oeeoFamSetDTO.getOeeoFamTel());//联系电话
                empFamilyDTO.setRempFamAddr(oeeoFamSetDTO.getOeeoFamAddr());//家庭地址
                empFamilyDTO.setRempFamSeq(oeeoFamSetDTO.getOeeoFamSeq());//排序
                rempFamSet.add(empFamilyDTO);
            }
            employeeDTO.setRempFamSet(rempFamSet);
        }
        return employeeDTO;
    }


    //插入新自然人
    private Result addPeopleToECO(EmployeeEntryApplyDTO employeeEntryApplyDTO) {
        String token = request.getHeader("Authorization");
        //根据session中的当前员工和岗位赋值单据的制单人和制单岗位
        //CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
        JSONObject insertObj = setEmpToPeople(employeeEntryApplyDTO);
        ParamsVo params = new ParamsVo();
        params.setClassName("people");
        params.setMethodName("addPeople");
        params.setAuthorization(token);// 可选
        params.setParamObj(insertObj);
        System.out.println(params.toString());
        Result exec = ExecUtil.exec(params);
        logger.info(exec.toString());
        if (null == exec || !Result.RECODE_SUCCESS.equals(exec.getRetCode())) {
            throw new ApplicationException(0, "新增自然人失败" + exec.getErrMsg());
        }
        return exec;
    }

    //更新用户企业圈
    private void addPeopleEnteSet(EmployeeEntryApplyDTO employeeEntryApplyDTO) {
        String token = request.getHeader("Authorization");
        JSONObject insertObj = new JSONObject();
        if (!StringUtil.isNullOrEmpty(employeeEntryApplyDTO.getEdmdEnte())) {
            insertObj.put("enterpriseId", employeeEntryApplyDTO.getEdmdEnte());
            insertObj.put("peopleId", employeeEntryApplyDTO.getOeeoEpeo());
            ParamsVo params = new ParamsVo();
            params.setClassName("people");
            params.setMethodName("addPeopleEnteSet");
            params.setAuthorization(token);
            params.setParamObj(insertObj);
            System.out.println(params.toString());
            Result exec = ExecUtil.exec(params);
            logger.info(exec.toString());
            if (null == exec || !Result.RECODE_SUCCESS.equals(exec.getRetCode())) {
                throw new ApplicationException(0, "更新自然人企业圈失败" + exec.getErrMsg());
            }
        }
    }

    private JSONObject setEmpToPeople(EmployeeEntryApplyDTO employeeEntryApplyDTO) {
        CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
        JSONObject peopleObj = new JSONObject();
        peopleObj.put("epeo_photourl", employeeEntryApplyDTO.getOeeoPicUrl());
        peopleObj.put("epeo_name_en", employeeEntryApplyDTO.getOeeoNameEn());
        peopleObj.put("epeo_name_cn", employeeEntryApplyDTO.getOeeoNameCn());
        peopleObj.put("epeo_name_ni", null);
        peopleObj.put("epeo_fist_name", employeeEntryApplyDTO.getOeeoFirstName());
        peopleObj.put("epeo_last_name", employeeEntryApplyDTO.getOeeoLastName());
        peopleObj.put("epeo_gender", employeeEntryApplyDTO.getOeeoGender());
        peopleObj.put("epeo_card_no", employeeEntryApplyDTO.getOeeoCardNo());
        peopleObj.put("epeo_card_set", null);
        peopleObj.put("epeo_birth", employeeEntryApplyDTO.getOeeoBirth());
        peopleObj.put("epeo_party", employeeEntryApplyDTO.getOeeoParty());
        peopleObj.put("epeo_nation", employeeEntryApplyDTO.getOeeoNation());
        peopleObj.put("epeo_maried", employeeEntryApplyDTO.getOeeoMaried());
        peopleObj.put("epeo_height", employeeEntryApplyDTO.getOeeoHeight());
        peopleObj.put("epeo_weight", employeeEntryApplyDTO.getOeeoWeight());
        peopleObj.put("epeo_blood", employeeEntryApplyDTO.getOeeoBlood());
        peopleObj.put("epeo_rgt_pro", null);
        peopleObj.put("epeo_origin", employeeEntryApplyDTO.getOeeoOrigin());
        peopleObj.put("epeo_residence", employeeEntryApplyDTO.getOeeoResidence());
        peopleObj.put("epeo_home_addr", employeeEntryApplyDTO.getOeeoHomeAddr());
        //peopleObj.put("epeo_addr", employeeEntryApplyDTO.getOeeoAddr());
        peopleObj.put("epeo_secu_type", employeeEntryApplyDTO.getOeeoSecuType());
        peopleObj.put("epeo_secu_no", employeeEntryApplyDTO.getOeeoSecuNo());
        peopleObj.put("epeo_tel", employeeEntryApplyDTO.getOeeoTel());
        peopleObj.put("epeo_htel", employeeEntryApplyDTO.getOeeoHtel());
        //peopleObj.put("epeo_mail", employeeEntryApplyDTO.getOeeoMail());
        peopleObj.put("epeo_self_eval", employeeEntryApplyDTO.getOeeoSelfEval());
        peopleObj.put("oeeo_work_date", employeeEntryApplyDTO.getOeeoWorkDate());
        peopleObj.put("enterpriseId", employeeEntryApplyDTO.getEdmdEnte());
        //peopleObj.put("epeo_nationality", "86");//国籍默认中国
        peopleObj.put("creuser", sessionEntity.getEmployeeId());
        peopleObj.put("cretime", new Date());
        return peopleObj;
    }

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

    private Result updateOrderStatus(String orderId, String status) {
        Result result = new Result();
        try {
            EmployeeentryapplyEntity entity = new EmployeeentryapplyEntity();
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

    //单据通过方法具体实现
    private Result passMethod(String orderInstanceId) {
        Result result = new Result();
        try {
            //获取入职单
            EmployeeentryapplyEntity employeeentryapplyEntity = ormService.load(EmployeeentryapplyEntity.class, orderInstanceId);
            List<OeeoOeeoStudySetaEntity> list = employeeentryapplyEntity.loadOeeo_study_set();
            employeeentryapplyEntity.setOeeo_study_set(list);
            List<OeeoOeeoSkillSetaEntity> list2 = employeeentryapplyEntity.loadOeeo_skill_set();
            employeeentryapplyEntity.setOeeo_skill_set(list2);
            List<OeeoOeeoWorkSetaEntity> list3 = employeeentryapplyEntity.loadOeeo_work_set();
            employeeentryapplyEntity.setOeeo_work_set(list3);
            List<OeeoOeeoFamSetaEntity> list4 = employeeentryapplyEntity.loadOeeo_fam_set();
            employeeentryapplyEntity.setOeeo_fam_set(list4);
            EmployeeEntryApplyDTO employeeEntryApplyDTO = JSONObject.parseObject(JSONObject.toJSONString(employeeentryapplyEntity), EmployeeEntryApplyDTO.class);
            //时间格式调整long——>string
            if (!StringUtil.isNullOrEmpty(employeeEntryApplyDTO.getOeeoCardBeg())) {
                employeeEntryApplyDTO.setOeeoCardBeg(DateUtil.transferDate(Long.valueOf(employeeEntryApplyDTO.getOeeoCardBeg()), "yyyy-MM-dd"));
            }
            if (!StringUtil.isNullOrEmpty(employeeEntryApplyDTO.getOeeoCardEnd())) {
                employeeEntryApplyDTO.setOeeoCardEnd(DateUtil.transferDate(Long.valueOf(employeeEntryApplyDTO.getOeeoCardEnd()), "yyyy-MM-dd"));
            }
            if (!StringUtil.isNullOrEmpty(employeeEntryApplyDTO.getOeeoBirth())) {
                employeeEntryApplyDTO.setOeeoBirth(DateUtil.transferDate(Long.valueOf(employeeEntryApplyDTO.getOeeoBirth()), "yyyy-MM-dd"));
            }
            if (!StringUtil.isNullOrEmpty(employeeEntryApplyDTO.getOeeoWorkDate())) {
                employeeEntryApplyDTO.setOeeoWorkDate(DateUtil.transferDate(Long.valueOf(employeeEntryApplyDTO.getOeeoWorkDate()), "yyyy-MM-dd"));
            }
            if (!StringUtil.isNullOrEmpty(employeeEntryApplyDTO.getOeeoEnDate())) {
                employeeEntryApplyDTO.setOeeoEnDate(DateUtil.transferDate(Long.valueOf(employeeEntryApplyDTO.getOeeoEnDate()), "yyyy-MM-dd"));
            }
            if (!StringUtil.isNullOrEmpty(employeeEntryApplyDTO.getOeeoTryBeg())) {
                employeeEntryApplyDTO.setOeeoTryBeg(DateUtil.transferDate(Long.valueOf(employeeEntryApplyDTO.getOeeoTryBeg()), "yyyy-MM-dd"));
            }
            if (!StringUtil.isNullOrEmpty(employeeEntryApplyDTO.getOeeoTryEnd())) {
                employeeEntryApplyDTO.setOeeoTryEnd(DateUtil.transferDate(Long.valueOf(employeeEntryApplyDTO.getOeeoTryEnd()), "yyyy-MM-dd"));
            }
            if (!StringUtil.isNullOrEmpty(employeeEntryApplyDTO.getOeeoPostDate())) {
                employeeEntryApplyDTO.setOeeoPostDate(DateUtil.transferDate(Long.valueOf(employeeEntryApplyDTO.getOeeoPostDate()), "yyyy-MM-dd"));
            }
            List<OeeoStudySetDTO> study = employeeEntryApplyDTO.getOeeoStudySet();//教育经历
            for (OeeoStudySetDTO oeeoStudySetDTO : study) {
                if (!StringUtil.isNullOrEmpty(oeeoStudySetDTO.getOeeoStuBeg())) {
                    oeeoStudySetDTO.setOeeoStuBeg(DateUtil.transferDate(Long.valueOf(oeeoStudySetDTO.getOeeoStuBeg()), "yyyy-MM-dd"));
                }
                if (!StringUtil.isNullOrEmpty(oeeoStudySetDTO.getOeeoStuEnd())) {
                    oeeoStudySetDTO.setOeeoStuEnd(DateUtil.transferDate(Long.valueOf(oeeoStudySetDTO.getOeeoStuEnd()), "yyyy-MM-dd"));
                }
            }
            employeeEntryApplyDTO.setOeeoStudySet(study);
            List<OeeoWorkSetDTO> work = employeeEntryApplyDTO.getOeeoWorkSet();//工作经历
            for (OeeoWorkSetDTO oeeoWorkSetDTO : work) {
                if (!StringUtil.isNullOrEmpty(oeeoWorkSetDTO.getOeeoWorkBeg())) {
                    oeeoWorkSetDTO.setOeeoWorkBeg(DateUtil.transferDate(Long.valueOf(oeeoWorkSetDTO.getOeeoWorkBeg()), "yyyy-MM-dd"));
                }
                if (!StringUtil.isNullOrEmpty(oeeoWorkSetDTO.getOeeoWorkEnd())) {
                    oeeoWorkSetDTO.setOeeoWorkEnd(DateUtil.transferDate(Long.valueOf(oeeoWorkSetDTO.getOeeoWorkEnd()), "yyyy-MM-dd"));
                }
            }
            employeeEntryApplyDTO.setOeeoWorkSet(work);
            employeeEntryApplyDTO.setOrdeStatus(OrderStatusConstants.ORDER_STATUS_5);//单据装态:通过
            if ("1".equals(employeeEntryApplyDTO.getOeeoType())) {
                saveRzd(employeeEntryApplyDTO);//入职单pass
            } else if ("0".equals(employeeEntryApplyDTO.getOeeoType())) {
                savrDabg(employeeEntryApplyDTO);//档案变更单pass
            }
            result.setRetCode(Result.RECODE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(e.getMessage());
            logger.debug(e.getMessage());
        }
        return result;
    }

    //入职单通过方法
    @TxTransaction(rollbackFor = Exception.class)
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    private void saveRzd(EmployeeEntryApplyDTO employeeEntryApplyDTO) throws Exception {
        if (StringUtil.isNullOrEmpty(employeeEntryApplyDTO.getOeeoEpeo())) {
            Result exec = addPeopleToECO(employeeEntryApplyDTO);//插入新自然人
            String oeeoEpeo = exec.getData().toString();
            employeeEntryApplyDTO.setOeeoEpeo(oeeoEpeo);
        }
        if (!StringUtil.isNullOrEmpty(employeeEntryApplyDTO.getEdmdEnte())) {
            addPeopleEnteSet(employeeEntryApplyDTO);//更新自然人企业圈
        }
        EmployeeDTO employeeNew = new EmployeeDTO();
        EmployeeDTO employeeDTO = setEmployeeEntryApplyDTOtoEmployee(employeeNew, employeeEntryApplyDTO);
        Result saveEmployeeResult = employee.saveEmployee(JSONObject.parseObject(JSONObject.toJSONString(employeeDTO), EmployeeEntity.class));//保存信息到employee
        String rempId = saveEmployeeResult.getData().toString();
        if (!StringUtil.isNullOrEmpty(rempId) && !StringUtil.isNullOrEmpty(employeeEntryApplyDTO.getOeeoPost())) {
            updateJobEmp(rempId, employeeEntryApplyDTO.getOeeoPost());//更新岗位任职人
        }
        employeeEntryApplyDao.updateEmployeeEntryApply(employeeEntryApplyDTO);//职员入职单更新
    }

    //档案变更单通过方法
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    private void savrDabg(EmployeeEntryApplyDTO employeeEntryApplyDTO) throws Exception {
        //根据工号查询到员工信息
        String rmpNo = employeeEntryApplyDTO.getOeeoCode();
        OrmParam ormParam = new OrmParam();
        ormParam.setWhereExp(ormParam.getEqualXML("remp_no", rmpNo));
        List<EmployeeEntity> list = ormService.selectBeanList(EmployeeEntity.class, ormParam);
        if (list.size() > 0) {
            EmployeeEntity employeeEntity = list.get(0);
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(employeeEntity);//转object
            EmployeeDTO employeeNew = JSONObject.parseObject(JSONObject.toJSONString(jsonObject), EmployeeDTO.class);
            //保存数据到Employee（插入新员工）需修改
            EmployeeDTO employeeDTO = setEmployeeEntryApplyDTOtoEmployee(employeeNew, employeeEntryApplyDTO);
            employee.saveEmployee(JSONObject.parseObject(JSONObject.toJSONString(employeeDTO), EmployeeEntity.class));
            employeeEntryApplyDao.updateEmployeeEntryApply(employeeEntryApplyDTO);//更新档案变更单
        }

    }

    //根据员工工号查询法人
    private String queryRempMcop(String rempNo) throws Exception {
        String rempMcop = "";
        OrmParam ormParam = new OrmParam();
        ormParam.setWhereExp(ormParam.getEqualXML("remp_no", rempNo));
        List<EmployeeEntity> list = ormService.selectBeanList(EmployeeEntity.class, ormParam);
        if (list.size() > 0) {
            EmployeeEntity employeeEntity = list.get(0);
            rempMcop = employeeEntity.getRemp_mcop();//法人公司ID
        }
        return rempMcop;
    }

    //查询办公园区
    private String queryRpakName(String deptId) throws Exception {
        String rpakName = "";
        DepttreeEntity depttreeEntity = ormService.load(DepttreeEntity.class, deptId);
        if (!StringUtil.isNullOrEmpty(depttreeEntity.getMdep_rpak())) {
            ParkEntity parkEntity = ormService.load(ParkEntity.class, depttreeEntity.getMdep_rpak());
            rpakName = parkEntity.getRpak_name();
        }
        return rpakName;
    }

    /**
     * 计算工龄、司龄
     *
     * @param startDate 起始日期
     * @return
     */
    private String calculateYears(String startDate) {
        if (StringUtil.isNullOrEmpty(startDate)) {
            return null;
        }
        Long time = System.currentTimeMillis() - Long.parseLong(startDate);
        double year = time / 1000.0;
        year = year / (365 * 24 * 60 * 60);
        //保留一位小数
        DecimalFormat df = new DecimalFormat("#0.0");
        return String.valueOf(df.format(year));
    }

    //获取伙伴公司名称
    private String queryRelation(String rempMcop) throws Exception {
        String rempMcopName = "";
        RelationEntity relationEntity = ormService.load(RelationEntity.class, rempMcop);
        rempMcopName = relationEntity.getRela_name();
        return rempMcopName;
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

    //更新岗位任职人
    private Result updateJobEmp(String rempId, String postId) throws Exception {
        Result result = new Result();
        CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
        JobpositionEntity jobpositionEntity = new JobpositionEntity();
        jobpositionEntity.setId(postId);
        jobpositionEntity.setRpos_emp(rempId);
        jobpositionEntity.setRpos_duty_type("0");//任职方式：0 任职 1兼职 2代职
        jobpositionEntity.setModuser(sessionEntity.getEmployeeId());
        jobpositionEntity.setModtime(new Date());
        int val = ormService.updateSelective(jobpositionEntity);
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(val);
        return result;
    }

    //根据部门ID查询法人公司
    private String queryMcop(String deptId) throws Exception {
        OrmParam ormParam = new OrmParam();
        ormParam.setWhereExp(ormParam.getEqualXML("id", deptId));
        List<DepttreeEntity> list = ormService.selectBeanList(DepttreeEntity.class, ormParam);
        DepttreeEntity depttreeEntity = list.get(0);
        return depttreeEntity.getMdep_mcop();//法人公司ID
    }

    //根据人员ID查询人员姓名工号
    private String queryEmployee(String rempId) throws Exception {
        String name = "";
        OrmParam ormParam = new OrmParam();
        ormParam.setWhereExp(ormParam.getEqualXML("id", rempId));
        List<EmployeeEntity> list = ormService.selectBeanList(EmployeeEntity.class, ormParam);
        EmployeeEntity employeeEntity = list.get(0);
        String rempName = employeeEntity.getRemp_name();
        String rempNo = employeeEntity.getRemp_no();
        name = rempName + "/" + rempNo;
        return name;
    }

    private String getSchoolName(String oeeoRsch) {
        String oeeoRschName = "";
        try {
            OrmParam ormParam = new OrmParam();
            ormParam.setWhereExp(ormParam.getEqualXML("id", oeeoRsch));
            List<SchoolEntity> list = ormService.selectBeanList(SchoolEntity.class, ormParam);
            SchoolEntity schoolEntity = list.get(0);
            oeeoRschName = schoolEntity.getRsch_name();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return oeeoRschName;
    }

    private String queryPostGrade(String pistId) {
        String pgrade = "";
        try {
            OrmParam ormParam = new OrmParam();
            ormParam.setWhereExp(ormParam.getEqualXML("id", pistId));
            List<JobpositionEntity> list = ormService.selectBeanList(JobpositionEntity.class, ormParam);
            JobpositionEntity jobpositionEntity = list.get(0);
            pgrade = jobpositionEntity.getRpos_grade();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pgrade;
    }
}
