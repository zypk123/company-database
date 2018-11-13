package com.huntkey.rx.hr.provider.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.codingapi.tx.annotation.TxTransaction;
import com.huntkey.rx.commons.utils.datetime.DateUtil;
import com.huntkey.rx.commons.utils.rest.Pagination;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.edm.entity.*;
import com.huntkey.rx.hr.common.constants.*;
import com.huntkey.rx.hr.common.exception.ApplicationException;
import com.huntkey.rx.hr.common.model.*;
import com.huntkey.rx.hr.common.util.DateDiff;
import com.huntkey.rx.hr.common.util.NullUtils;
import com.huntkey.rx.hr.provider.client.InformationClient;
import com.huntkey.rx.hr.provider.service.BaseService;
import com.huntkey.rx.hr.provider.service.BizFormService;
import com.huntkey.rx.hr.provider.service.ContractSignApplyService;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;
import com.huntkey.rx.sceo.orm.common.type.SQLCurdEnum;
import com.huntkey.rx.sceo.orm.common.type.SQLSortEnum;
import com.huntkey.rx.sceo.orm.common.util.EdmUtil;
import com.huntkey.rx.sceo.orm.service.OrmService;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 合同签订单类Service实现类
 *
 * @author zhangyu
 * @create 2017-11-13 17:40
 **/
@Service
public class ContractSignApplyServiceImpl extends BaseService implements ContractSignApplyService {

    private static Logger logger = LoggerFactory.getLogger(ContractSignApplyServiceImpl.class);

    @Autowired
    private OrmService ormService;

    @Autowired
    private BizFormService bizFormService;

    @Autowired
    InformationClient informationClient;

    /**
     * 匹配汉字
     */
    private Pattern p = Pattern.compile("[\u4e00-\u9fa5]");

    private static final String MALE = "1";
    private static final String FEMALE = "2";

    private static final String YUANGONG = "0";
    private static final String ZHIYUAN = "1";

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 合同签订单批准通过方法
     *
     * @return
     */
    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Result passContractSignOrder(String orderInstanceId, String handlerType) {

        logger.info("合同签订单批准通过回调接口：orderInstanceId：" + orderInstanceId + ",handlerType:" + handlerType);
        Result result = new Result();

        Result result1 = new Result();
        result1.setRetCode(Result.RECODE_ERROR);

        Result result2 = new Result();
        result2.setRetCode(Result.RECODE_ERROR);

        Result result3 = new Result();
        result3.setRetCode(Result.RECODE_ERROR);

        // 判断id是否为空
        if (StringUtil.isNullOrEmpty(orderInstanceId)) {
            result.setRetCode(0);
            result.setErrMsg(MsgConstants.MSG_HR_NOT_BLANK_CS_ID);
            result.setData(false);
            return result;
        }
        // 判断handlerType是否为空
        if (StringUtil.isNullOrEmpty(handlerType)) {
            result.setRetCode(0);
            result.setErrMsg(MsgConstants.MSG_HR_NOT_BLANK_HANDLER_TYPE);
            result.setData(false);
            return result;
        }

        switch (handlerType) {
            case WFHandlerTypeConstants.PASS: {
                // 单据状态改为 完成 "5"；将单据数据写入资源表
                result1 = pass(orderInstanceId);
                break;
            }
            case WFHandlerTypeConstants.REVOKE: {
                // 单据状态改为 待提 "2"
                result2 = updateOrderStatus(orderInstanceId, OrderStatusConstants.ORDER_STATUS_2);
                break;
            }
            case WFHandlerTypeConstants.RETURN_BACK: {
                // 单据状态改为 退回 "6"
                result3 = updateOrderStatus(orderInstanceId, OrderStatusConstants.ORDER_STATUS_6);
                break;
            }
            default: {
                break;
            }
        }

        if (result1.getRetCode() == 1 || result2.getRetCode() == 1 || result3.getRetCode() == 1) {
            result.setData(true);
        } else {
            result.setData(false);
        }
        return result;
    }

    /**
     * 人员合同单查询列表方法(带分页)
     *
     * @param deptId         起始部门ID
     * @param legalPerson    合同法人
     * @param contractStart  合同结束日期起
     * @param contractEnd    合同结束日期至
     * @param contractState  合同状态
     * @param employeeType   员工类型
     * @param officParks     办公园区
     * @param entryStart     入职时间起
     * @param entryEnd       入职时间至
     * @param staffIdAndName 姓名工号
     * @param startPage
     * @param rows
     * @return
     */
    @Override
    public Result query(String deptId, String legalPerson, String contractStart,
                        String contractEnd, String contractState, String employeeType,
                        String officParks, String entryStart, String entryEnd,
                        String staffIdAndName, int startPage, int rows) throws Exception {
        Result result = new Result();
        //组合查询条件
        OrmParam ormParam = getSearchParam(deptId, employeeType, officParks, entryStart, entryEnd, staffIdAndName);

        //根据条件查询员工类
        List<EmployeeDTO> employeeDTOS = getEmployees(ormParam);

        //设置员工的合同状态和合同记录
        List<EmployeeDTO> employeeDTOList = setContractRecords(legalPerson, contractStart, contractEnd, employeeDTOS);

        //根据合同状态过滤员工
        List<EmployeeDTO> filterdEmployeeDTOList = filterEmployeeDTO(contractState, employeeDTOList);

        //生成人员合同信息
        List<StaffContractDTO> staffContractDTOS = new ArrayList<>();
        if (filterdEmployeeDTOList == null || filterdEmployeeDTOList.size() == 0) {
            result.setErrMsg(String.format("没有查询到任何有效记录~"));
        } else {

            for (EmployeeDTO employeeDTO : filterdEmployeeDTOList) {
                List<StaffContractDTO> staffContractDTOList = createStaffContracts(employeeDTO);
                if (staffContractDTOList != null) {
                    staffContractDTOS.addAll(staffContractDTOList);
                }
            }
        }

        //分页
        int total = staffContractDTOS.size();
        int startIndex = (startPage - 1) * rows;
        int endIndex = startPage * rows;
        if (endIndex > total) {
            endIndex = total;
        }
        if (startIndex > endIndex) {
            staffContractDTOS.clear();
        } else {
            staffContractDTOS = staffContractDTOS.subList(startIndex, endIndex);
        }
        Pagination<StaffContractDTO> pagination = new Pagination<>(staffContractDTOS, startPage, rows, total);
        result.setData(pagination);
        return result;
    }

    @Override
    public Result load(String id) throws Exception {
        Result result = new Result();
        if (StringUtil.isNullOrEmpty(id)) {
            return result;
        }

        ContractsignapplyEntity contractsignapplyEntity = ormService.load(ContractsignapplyEntity.class, id);
        ContractSignApplyDTO contractSignApplyDTO = null;
        if (contractsignapplyEntity != null) {
            contractSignApplyDTO = JSONObject.parseObject(JSONObject.toJSONString(contractsignapplyEntity), ContractSignApplyDTO.class);
            logger.info("合同签订单信息" + contractSignApplyDTO.toString() + "================================");
        }
        ContractsignInfoDTO contractsignInfoDTO = new ContractsignInfoDTO();
        List<StaffContractDTO> contractDTOS = new ArrayList<>();
        if (contractSignApplyDTO != null) {
            //制单人id
            String applicant = contractSignApplyDTO.getOrdeAdduser();
            String applicantName = getApplicant(applicant);
            //制单岗位id
            String applicantOfPost = contractSignApplyDTO.getOrdeDuty();
            String applicantOfPostName = getApplicantOfPost(applicantOfPost);
            //制单部门id
            String applicantOfDept = contractSignApplyDTO.getOrdeDept();
            String applicantOfDeptName = getApplicantOfDept(applicantOfDept);

            //单据定义对象id
            contractsignInfoDTO.setOrdeObj(contractsignapplyEntity.getOrde_rode_obj());

            //申请部门id
            contractsignInfoDTO.setApplicantOfDepartmentId(applicantOfDept);
            contractsignInfoDTO.setId(id);
            //签约部门名称
            contractsignInfoDTO.setSignDepartment(applicantOfDeptName);
            //制单时间
            //时间类型做了修改
            contractsignInfoDTO.setApplicantDate(sdf.format(contractSignApplyDTO.getOrdeDate()));
            contractsignInfoDTO.setOrderNumber(contractSignApplyDTO.getOrdeNbr());
            contractsignInfoDTO.setApplicantOfDepartment(contractSignApplyDTO.getOcsoDept());
//            contractsignInfoDTO.setApplicant(contractSignApplyDTO.getOrdeAdduser());
            //制单人
            contractsignInfoDTO.setApplicant(applicantName);
            //制单岗位
            contractsignInfoDTO.setApplicantOfPost(applicantOfPostName);
            //制单部门
            contractsignInfoDTO.setApplicantOfDepartment(applicantOfDeptName);

            contractsignInfoDTO.setLegalPerson(contractSignApplyDTO.getOcsoMcop());
            //时间格式化
            contractsignInfoDTO.setSignDate(sdf.format(new Date(Long.parseLong(contractSignApplyDTO.getOcsoSignDate()))));
            //签订类型
            contractsignInfoDTO.setSignType(contractSignApplyDTO.getOcsoType());
            //所属类
            contractsignInfoDTO.setEdmdClass(contractSignApplyDTO.getEdmdClass());

            //根据单据id获取属性集list
            List<OcsoEmpSetDTO> ocsoEmpSetDTOS = getOcsoEmpSetDTO(id);
            if (ocsoEmpSetDTOS != null && ocsoEmpSetDTOS.size() > 0) {
                for (OcsoEmpSetDTO ocsoEmpSetDTO : ocsoEmpSetDTOS) {
                    //根据签约人员获取员工类id
                    EmployeeDTO employeeDTO = getEmployeeById(ocsoEmpSetDTO.getOcsoEmp());
                    //根据员工id查属性集的个数
                    List<ContractRecordDTO> empContSetDTOS = getEmpContSetDTO(ocsoEmpSetDTO.getOcsoEmp());

                    String contractNumbers = "0";
                    //计算合同年限
                    String contractYearLimite = "0.0";
                    //计算合同年限
                    if (empContSetDTOS != null && empContSetDTOS.size() > 0) {
                        contractNumbers = calculateRecordsNum(empContSetDTOS);
                        contractNumbers = String.valueOf((Integer.parseInt(contractNumbers) ));
                        //计算合同年限
                        contractYearLimite = calculateRecordTerm(empContSetDTOS);
                        //
                    }

                    //====
                    List<StaffContractDTO> temp = loadStaffContracts(employeeDTO);
                    StaffContractDTO staffContractDTO = null;
                    if (temp != null && temp.size() > 0) {
                        staffContractDTO = temp.get(0);
                    }

                    if (staffContractDTO != null) {
                        //如果签订类型为新签
                        if ("1".equals(contractsignInfoDTO.getSignType())) {
                            //合同法人信息为空
                            staffContractDTO.setLegalPerson("");
                            //合同记录数为空
                            staffContractDTO.setRecordsNum("0");
                            //合同年限为空
                            staffContractDTO.setRecordTerm("0.0");
                            //员工部门名称
                            staffContractDTO.setDepartmentName(staffContractDTO.getDepartmentName());
                            //时间格式化
                            if(!StringUtil.isNullOrEmpty(ocsoEmpSetDTO.getOcsoContBeg())){
                                staffContractDTO.setStartDate(sdf.format(new Date(Long.parseLong(ocsoEmpSetDTO.getOcsoContBeg()))));
                            }else{
                                staffContractDTO.setStartDate(null);
                            }
                            if(!StringUtil.isNullOrEmpty(ocsoEmpSetDTO.getOcsoContEnd())) {
                                staffContractDTO.setEndDate(sdf.format(new Date(Long.parseLong(ocsoEmpSetDTO.getOcsoContEnd()))));
                            }else{
                                staffContractDTO.setEndDate(null);
                            }
                            //当前合同结束日期
                            if (empContSetDTOS != null && empContSetDTOS.size() > 0) {
                                if (!StringUtil.isNullOrEmpty(empContSetDTOS.get(0).getRempContEnd())) {
                                    staffContractDTO.setRealEndTime(sdf.format(new Date(Long.parseLong(empContSetDTOS.get(0).getRempContEnd()))));
                                }
                            } else {
                                staffContractDTO.setRealEndTime(null);
                            }
                            //当前合同结束日期
                            contractDTOS.add(staffContractDTO);


                        } else if ("2".equals(contractsignInfoDTO.getSignType())) {

                            staffContractDTO.setLegalPerson(getLegalPerson(contractSignApplyDTO.getOcsoMcop()));

                            //员工部门名称
                            staffContractDTO.setDepartmentName(staffContractDTO.getDepartmentName());
                            //获取合同记录数
                            staffContractDTO.setRecordsNum(contractNumbers);
                            //计算合同年限
                            staffContractDTO.setRecordTerm(contractYearLimite);
                            //计算合同年限

                            //时间格式化

                            if(!StringUtil.isNullOrEmpty(ocsoEmpSetDTO.getOcsoContBeg())){
                                staffContractDTO.setStartDate(sdf.format(new Date(Long.parseLong(ocsoEmpSetDTO.getOcsoContBeg()))));
                            }else{
                                staffContractDTO.setStartDate(null);
                            }
                            if(!StringUtil.isNullOrEmpty(ocsoEmpSetDTO.getOcsoContEnd())) {
                                staffContractDTO.setEndDate(sdf.format(new Date(Long.parseLong(ocsoEmpSetDTO.getOcsoContEnd()))));
                            }else{
                                staffContractDTO.setEndDate(null);
                            }
                            //当前合同结束日期
                            if (empContSetDTOS != null && empContSetDTOS.size() > 0) {
                                if (!StringUtil.isNullOrEmpty(empContSetDTOS.get(0).getRempContEnd())) {
                                    staffContractDTO.setRealEndTime(sdf.format(new Date(Long.parseLong(empContSetDTOS.get(0).getRempContEnd()))));
                                }
                            } else {
                                staffContractDTO.setRealEndTime(null);
                            }

                            //当前合同结束日期

//                            String beg = ocsoEmpSetDTO.getOcsoContBeg();
//                            String end = ocsoEmpSetDTO.getOcsoContEnd();
//                            Long term = 0L;
//                            if (!StringUtil.isNullOrEmpty(beg) && !StringUtil.isNullOrEmpty(end)) {
//                                term = Long.parseLong(end) - Long.parseLong(beg);
//                            }
//                            double years = term / 1000.0;
//                            years = years / (365 * 24 * 60 * 60);
//                            staffContractDTO.setRecordTerm(String.valueOf(Math.floor(years)));

                            contractDTOS.add(staffContractDTO);
                        }
                    }
                }
            }
            contractsignInfoDTO.setDetails(contractDTOS);
            result.setData(contractsignInfoDTO);
        }

        return result;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Result save(ContractsignInfoDTO contractsignInfoDTO) throws Exception {
        Result checkResult = new Result();
        JSONObject dataObj = new JSONObject();
        ContractSignApplyDTO contractSignApplyDTO = new ContractSignApplyDTO();
        //session中仅获企业id
        CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
        contractSignApplyDTO.setEdmdEnte(sessionEntity.getEnterpriseId());
        //前端传入单据定义对象id
        contractSignApplyDTO.setOrdeRodeObj(contractsignInfoDTO.getOrdeObj());
        //制单人
        contractSignApplyDTO.setOrdeAdduser(contractsignInfoDTO.getApplicant());
        //制单部门
        contractSignApplyDTO.setOrdeDept(contractsignInfoDTO.getApplicantOfDepartment());
        //制单岗位
        contractSignApplyDTO.setOrdeDuty(contractsignInfoDTO.getApplicantOfPost());

        contractSignApplyDTO.setCreuser(sessionEntity.getEmployeeId());
        //制单时间
        //时间类型做了修改
        SimpleDateFormat sbf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sbf.format(new Date());
        contractSignApplyDTO.setOrdeDate(DateUtil.parseFormatDate(date, "yyyy-MM-dd HH:mm:ss"));
        //合同签订单类对象Id
        String orderIdValue = contractsignInfoDTO.getId();
        //合同签订单单号
        String ordeNbrValue = contractsignInfoDTO.getOrderNumber();

        //父类属性-单据号
        if (ordeNbrValue == null || "".equals(ordeNbrValue) || StringUtils.isBlank(ordeNbrValue)) {
            ordeNbrValue = getContractCode(NumberConstants.PREFIX_CONTRACT_SIGN_APPLY);
//            ordeNbrValue = getOrderNbrService(NumberConstants.PREFIX_CONTRACT_SIGN_APPLY, NumberConstants.TYPE_1);
            //只有新增临时单据时再生成单据编码
            contractSignApplyDTO.setOrdeNbr(ordeNbrValue);
        }

        //父类属性-单据状态
        contractSignApplyDTO.setOrdeStatus(OrderConstants.ORDE_STATUS_1);

        if (orderIdValue == null || "".equals(orderIdValue) || StringUtils.isBlank(orderIdValue)) {
            contractSignApplyDTO.setOcsoDept(contractsignInfoDTO.getSignDepartment());
            //保存校验本次签约法人不能为空
            if(StringUtil.isNullOrEmpty(contractsignInfoDTO.getLegalPerson()))
            {
                checkResult.setErrMsg("本次签约法人不能为空 保存失败");
                checkResult.setRetCode(Result.RECODE_ERROR);
                dataObj.put("id","");
                dataObj.put("msg","本次签约法人不能为空 保存失败" );
                checkResult.setData(dataObj);
                return checkResult;
            }
            contractSignApplyDTO.setOcsoMcop(contractsignInfoDTO.getLegalPerson());
            contractSignApplyDTO.setOcsoSignDate(contractsignInfoDTO.getSignDate());
            contractSignApplyDTO.setOcsoType(contractsignInfoDTO.getSignType());

            List<OcsoEmpSetDTO> ocsoEmpSetDTOS = new ArrayList<>();
            List<StaffContractDTO> staffContractDTOList = contractsignInfoDTO.getDetails();
            if (staffContractDTOList != null && staffContractDTOList.size() > 0) {
                for (StaffContractDTO staffContractDTO : staffContractDTOList) {
                    OcsoEmpSetDTO ocsoEmpSetDTO = new OcsoEmpSetDTO();
                    //保存校验合同开始时间不能为空
                    if(StringUtil.isNullOrEmpty(staffContractDTO.getStartDate()))
                    {
                        checkResult.setErrMsg("合同起始日期不能为空 保存失败");
                        checkResult.setRetCode(Result.RECODE_ERROR);
                        dataObj.put("id","");
                        dataObj.put("msg","合同起始日期不能为空 保存失败" );
                        checkResult.setData(dataObj);
                        return checkResult;
                    }
                    ocsoEmpSetDTO.setOcsoContBeg(staffContractDTO.getStartDate());
                    //保存校验合同结束时间不能为空
                    if(StringUtil.isNullOrEmpty(staffContractDTO.getEndDate()))
                    {
                        checkResult.setErrMsg("合同结束日期不能为空 保存失败");
                        checkResult.setRetCode(Result.RECODE_ERROR);
                        dataObj.put("id","");
                        dataObj.put("msg","合同结束日期不能为空 保存失败" );
                        checkResult.setData(dataObj);
                        return checkResult;
                    }
                    ocsoEmpSetDTO.setOcsoContEnd(staffContractDTO.getEndDate());
                    ocsoEmpSetDTO.setOcsoEmp(staffContractDTO.getEmployeeId());
                    ocsoEmpSetDTO.setOcsoEmpDept(contractsignInfoDTO.getSignDepartment());

                    ocsoEmpSetDTOS.add(ocsoEmpSetDTO);
                }
            }

            contractSignApplyDTO.setOdscChagSet(ocsoEmpSetDTOS);
            return insertObject(contractSignApplyDTO);


        } else {
            contractSignApplyDTO.setId(orderIdValue);
            contractSignApplyDTO.setOrdeNbr(ordeNbrValue);
            contractSignApplyDTO.setOcsoDept(contractsignInfoDTO.getSignDepartment());
            //保存校验本次签约法人不能为空
            if(StringUtil.isNullOrEmpty(contractsignInfoDTO.getLegalPerson()))
            {
                checkResult.setErrMsg("本次签约法人不能为空 保存失败");
                checkResult.setRetCode(Result.RECODE_ERROR);
                dataObj.put("id","");
                dataObj.put("msg","本次签约法人不能为空 保存失败 " );
                checkResult.setData(dataObj);
                return checkResult;
            }
            contractSignApplyDTO.setOcsoMcop(contractsignInfoDTO.getLegalPerson());
            contractSignApplyDTO.setOcsoSignDate(contractsignInfoDTO.getSignDate());
            contractSignApplyDTO.setOcsoType(contractsignInfoDTO.getSignType());
            List<OcsoEmpSetDTO> ocsoEmpSetDTOS = getOcsoEmpSetDTO(orderIdValue);
            List<StaffContractDTO> staffContractDTOList = contractsignInfoDTO.getDetails();
            Map<String, StaffContractDTO> map = null;
            if (staffContractDTOList != null && staffContractDTOList.size() > 0) {
                map = new HashMap<>(30);
                for (StaffContractDTO staffContractDTO : staffContractDTOList) {
                    map.put(staffContractDTO.getEmployeeId(), staffContractDTO);
                }
            }

            if (map != null && ocsoEmpSetDTOS != null && ocsoEmpSetDTOS.size() > 0) {
                for (OcsoEmpSetDTO ocsoEmpSetDTO : ocsoEmpSetDTOS) {
                    //保存校验合同开始时间不能为空
                    if(StringUtil.isNullOrEmpty(map.get(ocsoEmpSetDTO.getOcsoEmp()).getStartDate()))
                    {
                        checkResult.setErrMsg("合同起始日期不能为空 保存失败");
                        checkResult.setRetCode(Result.RECODE_ERROR);
                        dataObj.put("id","");
                        dataObj.put("msg","合同起始日期不能为空 保存失败" );
                        checkResult.setData(dataObj);
                        return checkResult;
                    }

                    ocsoEmpSetDTO.setOcsoContBeg(map.get(ocsoEmpSetDTO.getOcsoEmp()).getStartDate());
                    //保存校验合同结束时间不能为空
                    if(StringUtil.isNullOrEmpty(map.get(ocsoEmpSetDTO.getOcsoEmp()).getEndDate()))
                    {
                        checkResult.setErrMsg("合同结束日期不能为空 保存失败");
                        checkResult.setRetCode(Result.RECODE_ERROR);
                        dataObj.put("id","");
                        dataObj.put("msg","合同结束日期不能为空 保存失败" );
                        checkResult.setData(dataObj);
                        return checkResult;
                    }

                    ocsoEmpSetDTO.setOcsoContEnd(map.get(ocsoEmpSetDTO.getOcsoEmp()).getEndDate());
                    ocsoEmpSetDTO.setOcsoEmp(map.get(ocsoEmpSetDTO.getOcsoEmp()).getEmployeeId());
                    ocsoEmpSetDTO.setOcsoEmpDept(contractsignInfoDTO.getSignDepartment());
                    ocsoEmpSetDTO.setModuser(sessionEntity.getEmployeeId());
                }
            }

            contractSignApplyDTO.setOdscChagSet(ocsoEmpSetDTOS);
            return  updateObject(contractSignApplyDTO);

        }

    }


    @Override
    public Result submit(ContractsignInfoDTO contractsignInfoDTO) throws Exception {

        Result saveResult=  save(contractsignInfoDTO);

        if (saveResult.getRetCode().equals(Result.RECODE_SUCCESS)) {
            return submitFlow(saveResult);
        } else {
            Result result = new Result();
            result.setData(null);
            result.setErrMsg("保存临时单失败！");
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            return result;
        }

    }

    @TxTransaction(rollbackFor = Exception.class)
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Result submitFlow( Result saveResult) throws Exception{
        JSONObject saveData = (JSONObject) saveResult.getData();
        String orderId = saveData.getString(NodeConstant.ID);
        String orderNbr = saveData.getString("ordeNbr");
        String ordeRodeObj  =  saveData.getString("Orde_rode_obj");

        // 提交流程
        bizFormService.submitWorkFlow(ordeRodeObj, orderId);

        // 更新状态
        ContractsignapplyEntity updateEntity = new ContractsignapplyEntity();
        updateEntity.setOrde_status(OrderConstants.ORDE_STATUS_2);
        updateEntity.setOrde_date(new Date());
        updateEntity.setId(orderId);
        ormService.updateSelective(updateEntity);

        // 待返回数据
        JSONObject returnData = new JSONObject();
        // 插入返回的数据
        returnData.put(NodeConstant.ID, orderId);
        returnData.put("ordeNbr", orderNbr);

        Result result = new Result();
        result.setData(returnData);
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setErrMsg("提交单据成功！");
        return result;
    }



    @Override
    public Result sign(String deptName, String signType, String edmployeeIds, int startPage, int rows) throws Exception {
        String recordNumber = "";
        Result result = new Result();

        if (StringUtil.isNullOrEmpty(edmployeeIds)) {
            result.setRetCode(Result.RECODE_ERROR);
            return result;
        }
        String[] ids = edmployeeIds.split(",");

        if (ids.length < 0) {
            result.setRetCode(Result.RECODE_ERROR);
            return result;
        }
        List<StaffContractDTO> staffContractDTOList = new ArrayList<>();
        for (String edmployeeId : ids) {
            //根据id获取员工DTO
            EmployeeDTO employeeDTO = getEmployeeById(edmployeeId);
            //根据员工id查属性集的个数
            List<ContractRecordDTO> empContSetDTOS = getEmpContSetDTO(edmployeeId);
            employeeDTO.setRempContSet(empContSetDTOS);
            employeeDTO.setResultContractRecords(empContSetDTOS);
            String contractNumbers = "0";
            if (empContSetDTOS != null && empContSetDTOS.size() > 0) {
                contractNumbers = calculateRecordsNum(empContSetDTOS);
                contractNumbers = String.valueOf((Integer.parseInt(contractNumbers)));
            }
            //根据员工DTO组装显示的字段
            //调用creatStaffContract换位 loadStaffContracts
            List<StaffContractDTO> temp = signStaffContracts(employeeDTO);

            StaffContractDTO staffContractDTO = null;
            if (temp != null && temp.size() > 0) {
                staffContractDTO = temp.get(0);
            }
            //类型转换 字符转long  Long.parseLong("String")
            //类型转换 字符转int   Integer.parseInt(str);
            if (staffContractDTO != null) {
                if ("1".equals(signType)) {
                    staffContractDTO.setRecordState("未签");
                }
                if ("2".equals(signType)) {
                    staffContractDTO.setRecordState("已签");
                }
                staffContractDTO.setRecordsNum(contractNumbers);
                staffContractDTOList.add(staffContractDTO);
            }
        }

        if ("1".equals(signType)) {

            signType = "新签";
        }
        if ("2".equals(signType)) {

            signType = "续签";
        }

        //分页
        int total = staffContractDTOList.size();
        int startIndex = (startPage - 1) * rows;
        int endIndex = startPage * rows;
        if (endIndex > total) {
            endIndex = total;
        }
        staffContractDTOList = staffContractDTOList.subList(startIndex, endIndex);
        Pagination<StaffContractDTO> pagination = new Pagination<>(staffContractDTOList, startPage, rows, total);
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(pagination);
        return result;
    }


    /**
     * 组装人员合同信息
     *
     * @param employeeDTO 员工类信息和员工的合同信息
     * @return
     */
    private List<StaffContractDTO> signStaffContracts(EmployeeDTO employeeDTO) throws Exception {
        ContractsignapplyEntity contractsignapplyEntity = new ContractsignapplyEntity();
        if (employeeDTO == null) {
            System.out.println(employeeDTO.getId() + "/t" + employeeDTO.getRempName() + "员工信息");
            return null;
        }

        Date date = new Date();
        String entryDate = "";
        if (!StringUtil.isNullOrEmpty(employeeDTO.getRempInDate())) {
            date.setTime(Long.parseLong(employeeDTO.getRempInDate()));
            entryDate = DateUtil.formatDate(date);
        }

        String nameAndNumber = "";
        if (!StringUtil.isNullOrEmpty(employeeDTO.getRempNo()) && !StringUtil.isNullOrEmpty(employeeDTO.getRempName())) {
            nameAndNumber = employeeDTO.getRempName().concat("/").concat(employeeDTO.getRempNo());
        }
        //员工id
        String employeeId = employeeDTO.getId();
        //性别
        String gender = MALE.equals(employeeDTO.getRempGender()) ? "男" : "女";
        //年龄
        String age = calculateBirthYears(employeeDTO.getRempBirth());
        //身份证号码
        String cardId = employeeDTO.getRempCardId();
        //合同状态
        String recordState = employeeDTO.getRecondState();
        //计算工龄
        String workAge = calculateYears(employeeDTO.getRempWorkDate());
        //计算司龄
        String companyAge = calculateYears(employeeDTO.getRempInDate());
        //在岗(月)
        String onGuardTime = getOnGuardTime(employeeDTO.getId());
        //员工类型
        String name = getEmployeeType(employeeDTO.getRempType());
        //办公园区
        String parkName = getParkName(employeeDTO.getRempDept());
        //部门名称
        String deptName = getDeptName(employeeDTO.getRempDept());

        //员工合同记录List 组合DTO 合同状态是查询出来的结果 在数据库中是无存储的 然后构建在一个StaffContractDTO里面
        List<StaffContractDTO> staffContractDTOList = new ArrayList<>();
        //签约记录DTO 属性集DTO
        List<ContractRecordDTO> recordDTOS = employeeDTO.getResultContractRecords();
        if (recordDTOS != null && recordDTOS.size() > 0) {
            ContractRecordDTO contractRecordDTO = recordDTOS.get(0);
            //设置人员合同基础信息
            StaffContractDTO staffContractDTO = getStaffContractDTO(employeeId, nameAndNumber, gender,age, cardId, recordState, entryDate,
                    workAge, companyAge, onGuardTime, name, parkName, deptName);
            /**
             * 非空判断 合同状态 合同单据单号
             */
            if(!StringUtil.isNullOrEmpty(staffContractDTO.getEmployeeId())){
                contractsignapplyEntity = checkSignEmp(staffContractDTO.getEmployeeId());
                if( contractsignapplyEntity != null){
                    //合同状态数
                    staffContractDTO.setOrdeStatus(contractsignapplyEntity.getOrde_status());
                    //合同单据编号
                    staffContractDTO.setOrdeNbr(contractsignapplyEntity.getOrde_nbr());
                }
            }

            //合同纪录数
            //员工对象中合同签记录的数量，除去签订类型='解约'
            String recordsNum = calculateRecordsNum(employeeDTO.getRempContSet());
            if (StringUtil.isNullOrEmpty(recordsNum)) {
                recordsNum = "0";
            }
            staffContractDTO.setRecordsNum(recordsNum);
            //合同年限
            String term = calculateRecordTerm(employeeDTO.getRempContSet());
            if (StringUtil.isNullOrEmpty(recordsNum)) {
                term = "0";
            }
            staffContractDTO.setRecordTerm(term);
            //合同法人
            String legalPerson = getLegalPerson(contractRecordDTO.getRempContMcop());
            staffContractDTO.setLegalPerson(legalPerson);
            /**
             * 非空判断 合同开始时间
             */
            if (!StringUtil.isNullOrEmpty(contractRecordDTO.getRempContBeg())) {
                date.setTime(Long.parseLong(contractRecordDTO.getRempContBeg()));
                staffContractDTO.setStartDate(DateUtil.formatDate(date));
            }

            /**
             * 非空判断 合同结束时间
             */
            if (!StringUtil.isNullOrEmpty(contractRecordDTO.getRempContEnd())) {
                date.setTime(Long.parseLong(contractRecordDTO.getRempContEnd()));
                staffContractDTO.setEndDate(DateUtil.formatDate(date));
            }

            /**
             * 非空判断 合同实际结束时间
             */
            if (!StringUtil.isNullOrEmpty(contractRecordDTO.getRempContEnd())) {
                //====合同实际结束日期
                date.setTime(Long.parseLong(contractRecordDTO.getRempContEnd()));
                staffContractDTO.setRealEndTime(DateUtil.formatDate(date));
                //====
            }


            staffContractDTOList.add(staffContractDTO);

        } else {
            StaffContractDTO staffContractDTO = getStaffContractDTO(employeeId, nameAndNumber, gender,age, cardId, recordState, entryDate,
                    workAge, companyAge, onGuardTime, name, parkName, deptName);
            /**
             * 非空判断 合同状态 合同单据单号
             */
            if(!StringUtil.isNullOrEmpty(staffContractDTO.getEmployeeId())){
                contractsignapplyEntity = checkSignEmp(staffContractDTO.getEmployeeId());
                if( contractsignapplyEntity != null){
                    //合同状态数
                    staffContractDTO.setOrdeStatus(contractsignapplyEntity.getOrde_status());
                    //合同单据编号
                    staffContractDTO.setOrdeNbr(contractsignapplyEntity.getOrde_nbr());
                }
            }
            staffContractDTOList.add(staffContractDTO);
        }

        return staffContractDTOList;
    }





    private List<OcsoEmpSetDTO> getOcsoEmpSetDTO(String id) throws Exception {
        if (StringUtil.isNullOrEmpty(id)) {
            return null;
        }
        OrmParam ormParam = new OrmParam();
        ormParam.setWhereExp(ormParam.getEqualXML("pid", id));

        List<OcsoEmpSetDTO> ocsoEmpSetDTOS = null;
        List<OcsoOcsoEmpSetaEntity> ocsoOcsoEmpSetaEntities = ormService.selectBeanList(OcsoOcsoEmpSetaEntity.class, ormParam);
        if (ocsoOcsoEmpSetaEntities != null && ocsoOcsoEmpSetaEntities.size() > 0) {
            ocsoEmpSetDTOS = JSONArray.parseArray(JSONObject.toJSONString(ocsoOcsoEmpSetaEntities), OcsoEmpSetDTO.class);
        }

        return ocsoEmpSetDTOS;
    }


    private List<ContractRecordDTO> getEmpContSetDTO(String id) throws Exception {
        if (StringUtil.isNullOrEmpty(id)) {
            return null;
        }
        OrmParam ormParam = new OrmParam();
        ormParam.setWhereExp(ormParam.getEqualXML("pid", id).concat(" AND ").concat("remp_sign_type != 3"));
        ormParam.setOrderExp(SQLSortEnum.DESC, ContractRecordConsts.REMP_CONT_END);

        List<ContractRecordDTO> contractRecordDTO = null;
        List<RempRempContSetaEntity> rempContSetaEntities = ormService.selectBeanList(RempRempContSetaEntity.class, ormParam);
        if (rempContSetaEntities != null && rempContSetaEntities.size() > 0) {
            contractRecordDTO = JSONArray.parseArray(JSONObject.toJSONString(rempContSetaEntities), ContractRecordDTO.class);
        }

        return contractRecordDTO;
    }


    private EmployeeDTO getEmployeeById(String employeeId) throws Exception {
        if (StringUtil.isNullOrEmpty(employeeId)) {
            return null;
        }

        EmployeeDTO employeeDTO = null;
        EmployeeEntity employeeEntity = ormService.load(EmployeeEntity.class, employeeId);
        if (employeeEntity != null) {
            employeeDTO = JSONObject.parseObject(JSONObject.toJSONString(employeeEntity), EmployeeDTO.class);
        }

        return employeeDTO;
    }

    public Result insertObject(ContractSignApplyDTO contractSignApplyDTO) throws Exception {
        ContractsignapplyEntity contractsignapplyEntity = JSONObject.parseObject(JSONObject.toJSONString(contractSignApplyDTO),
                ContractsignapplyEntity.class);
        String id = ormService.insert(contractsignapplyEntity).toString();

        List<OcsoOcsoEmpSetaEntity> ocsoOcsoEmpSetaEntitys = contractsignapplyEntity.getOcso_emp_set();
        EdmUtil.setPropertyBaseEntitiesSysColumns(ContractsignapplyEntity.class, contractsignapplyEntity,
                ocsoOcsoEmpSetaEntitys, SQLCurdEnum.INSERT);

        if (ocsoOcsoEmpSetaEntitys != null && ocsoOcsoEmpSetaEntitys.size() > 0) {
            for (OcsoOcsoEmpSetaEntity empSetaEntity : ocsoOcsoEmpSetaEntitys) {
                empSetaEntity.setClassName(ContractSignApplyConstants.EDM_CTR_SIGN_APPLY_ORDER);
                ormService.insert(empSetaEntity);
            }
        }

        Result retResult = new Result();

        JSONObject dataObj = new JSONObject();
        dataObj.put("id", id);
        dataObj.put("ordeNbr", contractsignapplyEntity.getOrde_nbr());
        dataObj.put("Orde_rode_obj", contractsignapplyEntity.getOrde_rode_obj());
        retResult.setData(dataObj);

        return retResult;
    }

    public Result updateObject(ContractSignApplyDTO contractSignApplyDTO) throws Exception {
        ContractsignapplyEntity contractsignapplyEntity = JSONObject.parseObject(JSONObject.toJSONString(contractSignApplyDTO),
                ContractsignapplyEntity.class);

        ormService.updateSelective(contractsignapplyEntity);

        List<OcsoOcsoEmpSetaEntity> ocsoOcsoEmpSetaEntitys = contractsignapplyEntity.getOcso_emp_set();
        if (ocsoOcsoEmpSetaEntitys != null && ocsoOcsoEmpSetaEntitys.size() > 0) {
            for (OcsoOcsoEmpSetaEntity empSetaEntity : ocsoOcsoEmpSetaEntitys) {
                empSetaEntity.setClassName(ContractSignApplyConstants.EDM_CTR_SIGN_APPLY_ORDER);
                ormService.updateSelective(empSetaEntity);
            }
        }

        Result retResult = new Result();

        JSONObject dataObj = new JSONObject();
        dataObj.put("id", contractsignapplyEntity.getId());
        dataObj.put("ordeNbr", contractsignapplyEntity.getOrde_nbr());
        dataObj.put("Orde_rode_obj", contractsignapplyEntity.getOrde_rode_obj());
        retResult.setData(dataObj);

        return retResult;
    }

    /**
     * 根据合同状态过滤员工
     *
     * @param contractState   合同状态
     * @param employeeDTOList 员工集合
     * @return
     */
    private List<EmployeeDTO> filterEmployeeDTO(String contractState, List<EmployeeDTO> employeeDTOList) {
        if (StringUtil.isNullOrEmpty(contractState) || employeeDTOList == null || employeeDTOList.size() <= 0) {
            return null;
        }

        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (EmployeeDTO employeeDTO : employeeDTOList) {
            if (contractState.equals(employeeDTO.getRecondState())) {
                employeeDTOS.add(employeeDTO);
            }
        }

        return employeeDTOS;
    }

    /**
     * 组装人员合同信息
     *
     * @param employeeDTO 员工类信息和员工的合同信息
     * @return
     */
    private List<StaffContractDTO> createStaffContracts(EmployeeDTO employeeDTO) throws Exception {
        ContractsignapplyEntity contractsignapplyEntity = new ContractsignapplyEntity();
        if (employeeDTO == null) {
            System.out.println(employeeDTO.getId() + "/t" + employeeDTO.getRempName() + "员工信息");
            return null;
        }

        Date date = new Date();
        String entryDate = "";
        if (!StringUtil.isNullOrEmpty(employeeDTO.getRempInDate())) {
            date.setTime(Long.parseLong(employeeDTO.getRempInDate()));
            entryDate = DateUtil.formatDate(date);
        }

        String nameAndNumber = "";
        if (!StringUtil.isNullOrEmpty(employeeDTO.getRempNo()) && !StringUtil.isNullOrEmpty(employeeDTO.getRempName())) {
            nameAndNumber = employeeDTO.getRempName().concat("/").concat(employeeDTO.getRempNo());
        }
        //员工id
        String employeeId = employeeDTO.getId();
        //性别
        String gender = MALE.equals(employeeDTO.getRempGender()) ? "男" : "女";
        //计算年龄
        String age = calculateBirthYears(employeeDTO.getRempBirth());
        //身份证号码
        String cardId = employeeDTO.getRempCardId();
        //合同状态
        String recordState = employeeDTO.getRecondState();
        //计算工龄
        String workAge = calculateYears(employeeDTO.getRempWorkDate());
        //计算司龄
        String companyAge = calculateYears(employeeDTO.getRempInDate());
        //在岗(月)
        String onGuardTime = getOnGuardTime(employeeDTO.getId());
        //员工类型
        String name = getEmployeeType(employeeDTO.getRempType());
        //办公园区
        String parkName = getParkName(employeeDTO.getRempDept());
        //部门名称
        String deptName = getDeptName(employeeDTO.getRempDept());

        //员工合同记录List 组合DTO 合同状态是查询出来的结果 在数据库中是无存储的 然后构建在一个StaffContractDTO里面
        List<StaffContractDTO> staffContractDTOList = new ArrayList<>();
        if ("未签".equals(employeeDTO.getRecondState())) {
            StaffContractDTO staffContractDTO = getStaffContractDTO(employeeId, nameAndNumber, gender, age,  cardId, recordState, entryDate,
                    workAge, companyAge, onGuardTime, name, parkName, deptName);
            /**
             * 非空判断 合同状态 合同单据单号
             */
            if(!StringUtil.isNullOrEmpty(staffContractDTO.getEmployeeId())){
                contractsignapplyEntity = checkSignEmp(staffContractDTO.getEmployeeId());
                if( contractsignapplyEntity != null){
                    //合同状态数
                    staffContractDTO.setOrdeStatus(contractsignapplyEntity.getOrde_status());
                    //合同单据编号
                    staffContractDTO.setOrdeNbr(contractsignapplyEntity.getOrde_nbr());
                }
            }
            staffContractDTOList.add(staffContractDTO);
        } else {
            //签约记录DTO 属性集DTO
            List<ContractRecordDTO> recordDTOS = employeeDTO.getResultContractRecords();
            if (recordDTOS != null && recordDTOS.size() > 0) {
                ContractRecordDTO contractRecordDTO = recordDTOS.get(0);
                //设置人员合同基础信息
                StaffContractDTO staffContractDTO = getStaffContractDTO(employeeId, nameAndNumber, gender,age, cardId, recordState, entryDate,
                        workAge, companyAge, onGuardTime, name, parkName, deptName);
                /**
                 * 非空判断 合同状态 合同单据单号
                 */
                if(!StringUtil.isNullOrEmpty(staffContractDTO.getEmployeeId())){
                    contractsignapplyEntity = checkSignEmp(staffContractDTO.getEmployeeId());
                    if( contractsignapplyEntity != null){
                        //合同状态数
                        staffContractDTO.setOrdeStatus(contractsignapplyEntity.getOrde_status());
                        //合同单据编号
                        staffContractDTO.setOrdeNbr(contractsignapplyEntity.getOrde_nbr());
                    }
                }

                //合同纪录数
                //员工对象中合同签记录的数量，除去签订类型='解约'
                String recordsNum = calculateRecordsNum(employeeDTO.getRempContSet());
                if (StringUtil.isNullOrEmpty(recordsNum)) {
                    recordsNum = "0";
                }
                staffContractDTO.setRecordsNum(recordsNum);
                //合同年限
                String term = calculateRecordTerm(employeeDTO.getRempContSet());
                if (StringUtil.isNullOrEmpty(recordsNum)) {
                    term = "0";
                }
                staffContractDTO.setRecordTerm(term);
                //合同法人
                String legalPerson = getLegalPerson(contractRecordDTO.getRempContMcop());
                staffContractDTO.setLegalPerson(legalPerson);
                /**
                 * 非空判断 合同开始时间
                 */
                if (!StringUtil.isNullOrEmpty(contractRecordDTO.getRempContBeg())) {
                    date.setTime(Long.parseLong(contractRecordDTO.getRempContBeg()));
                    staffContractDTO.setStartDate(DateUtil.formatDate(date));
                }

                /**
                 * 非空判断 合同结束时间
                 */
                if (!StringUtil.isNullOrEmpty(contractRecordDTO.getRempContEnd())) {
                    date.setTime(Long.parseLong(contractRecordDTO.getRempContEnd()));
                    staffContractDTO.setEndDate(DateUtil.formatDate(date));
                }

                /**
                 * 非空判断 合同实际结束时间
                 */
                if (!StringUtil.isNullOrEmpty(contractRecordDTO.getRempContEnd())) {
                    //====合同实际结束日期
                    date.setTime(Long.parseLong(contractRecordDTO.getRempContEnd()));
                    staffContractDTO.setRealEndTime(DateUtil.formatDate(date));
                    //====
                }


                staffContractDTOList.add(staffContractDTO);

            }
        }

        return staffContractDTOList;
    }


    /**
     * 组装人员合同信息
     *
     * @param employeeDTO 员工类信息和员工的合同信息
     * @return
     */
    private List<StaffContractDTO> loadStaffContracts(EmployeeDTO employeeDTO) throws Exception {
        ContractsignapplyEntity contractsignapplyEntity = new ContractsignapplyEntity();
        if (employeeDTO == null) {
            System.out.println(employeeDTO.getId() + "/t" + employeeDTO.getRempName() + "员工信息");
            return null;
        }

        Date date = new Date();
        String entryDate = "";
        if (!StringUtil.isNullOrEmpty(employeeDTO.getRempInDate())) {
            date.setTime(Long.parseLong(employeeDTO.getRempInDate()));
            entryDate = DateUtil.formatDate(date);
        }

        String nameAndNumber = "";
        if (!StringUtil.isNullOrEmpty(employeeDTO.getRempNo()) && !StringUtil.isNullOrEmpty(employeeDTO.getRempName())) {
            nameAndNumber = employeeDTO.getRempName().concat("/").concat(employeeDTO.getRempNo());
        }
        //员工id
        String employeeId = employeeDTO.getId();
        //性别
        String gender = MALE.equals(employeeDTO.getRempGender()) ? "男" : "女";
        //计算年龄
        String age = calculateBirthYears(employeeDTO.getRempBirth());
        //身份证号码
        String cardId = employeeDTO.getRempCardId();
        //合同状态
        String recordState = employeeDTO.getRecondState();
        //计算工龄
        String workAge = calculateYears(employeeDTO.getRempWorkDate());
        //计算司龄
        String companyAge = calculateYears(employeeDTO.getRempInDate());
        //在岗(月)
        String onGuardTime = getOnGuardTime(employeeDTO.getId());
        //员工类型
        String name = getEmployeeType(employeeDTO.getRempType());
        //办公园区
        String parkName = getParkName(employeeDTO.getRempDept());
        //部门名称
        String deptName = getDeptName(employeeDTO.getRempDept());

        //员工合同记录List 组合DTO 合同状态是查询出来的结果 在数据库中是无存储的 然后构建在一个StaffContractDTO里面
        List<StaffContractDTO> staffContractDTOList = new ArrayList<>();
        StaffContractDTO staffContractDTO = getStaffContractDTO(employeeId, nameAndNumber, gender,age, cardId, recordState, entryDate,
                workAge, companyAge, onGuardTime, name, parkName, deptName);
        /**
         * 非空判断 合同状态 合同单据单号
         */
        if (!StringUtil.isNullOrEmpty(staffContractDTO.getEmployeeId())) {
            contractsignapplyEntity = checkSignEmp(staffContractDTO.getEmployeeId());
            if (contractsignapplyEntity != null) {
                //合同状态数
                staffContractDTO.setOrdeStatus(contractsignapplyEntity.getOrde_status());
                //合同单据编号
                staffContractDTO.setOrdeNbr(contractsignapplyEntity.getOrde_nbr());
            }
        }
        staffContractDTOList.add(staffContractDTO);


        return staffContractDTOList;
    }

    /**
     * 获取公司法人
     *
     * @param rempContMcop 伙伴类id
     * @return
     */
    private String getLegalPerson(String rempContMcop) throws Exception {
        if(StringUtil.isNullOrEmpty(rempContMcop)){return null;}
        RelationEntity relationEntity = ormService.load(RelationEntity.class,rempContMcop);
        String legalPerson = "";
        if(relationEntity!=null){legalPerson = relationEntity.getRela_short_name();}
        return legalPerson;
    }

    /**
     * 计算合同年限
     *
     * @param rempContSet 员工的所有合同记录
     * @return
     */
    private String calculateRecordTerm(List<ContractRecordDTO> rempContSet) {
        Long sum = 0L;
        for (ContractRecordDTO contractRecordDTO : rempContSet) {
            String beg = contractRecordDTO.getRempContBeg();
            String end = contractRecordDTO.getRempContEnd();
            if (!StringUtil.isNullOrEmpty(beg) && !StringUtil.isNullOrEmpty(end) && !"3".equals(contractRecordDTO.getRempSignType())) {
                sum += Long.parseLong(end) - Long.parseLong(beg);
            }
        }

        double years = sum / 1000.0;
        years = years / (365 * 24 * 60 * 60);
        BigDecimal bg = new BigDecimal(years);
        double f1 = bg.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
        return String.valueOf(f1);
    }

    /**
     * 计算合同次数
     *
     * @param rempContSet 员工的所有合同记录
     * @return
     */
    private String calculateRecordsNum(List<ContractRecordDTO> rempContSet) {
        int i = 0;
        for (ContractRecordDTO contractRecordDTO : rempContSet) {
            if (!"3".equals(contractRecordDTO.getRempSignType())) {
                i++;
            }
        }

        return String.valueOf(i);
    }

    /**
     * 获取部门名称
     *
     * @param rempDept 部门id
     * @return
     */
    private String getDeptName(String rempDept) throws Exception {
        if (StringUtil.isNullOrEmpty(rempDept)) {
            return null;
        }
        DepttreeEntity depttreeEntity = ormService.load(DepttreeEntity.class, rempDept);

        String deptName = "";
        if (depttreeEntity != null) {
            deptName = depttreeEntity.getMdep_name();
        }

        return deptName;
    }

    /**
     * 获取办公园区
     *
     * @param rempDept 部门id
     * @return
     */
    private String getParkName(String rempDept) throws Exception {
        if (StringUtil.isNullOrEmpty(rempDept)) {
            return null;
        }
        DepttreeEntity depttreeEntity = ormService.load(DepttreeEntity.class, rempDept);

        //获取办公园区id
        String parkId = "";
        if (depttreeEntity != null) {
            parkId = depttreeEntity.getMdep_rpak();
        }

        //获取园区名称
        String parkName = "";
        ParkEntity parkEntity = ormService.load(ParkEntity.class, parkId);
        if (parkEntity != null) {
            parkName = parkEntity.getRpak_name();
        }

        return parkName;
    }

    /**
     * 获取员工类型
     *
     * @param rempType 员工类型的枚举编码
     * @return
     */
    private String getEmployeeType(String rempType) {
        String employeeType = "";
        if ("1".equals(rempType)) {
            employeeType = "职员";
        }
        if ("0".equals(rempType)) {
            employeeType = "员工";
        }

        return employeeType;
    }

    /**
     * 计算在岗月
     *
     * @param id 员工id
     * @return
     */
    private String getOnGuardTime(String id) throws Exception {
        OrmParam ormParam = new OrmParam();
        ormParam.setOrderExp(SQLSortEnum.DESC, EmployeeConstants.REMP_POST_BEG);
        String exp = ormParam.getEqualXML("pid", id)
                .concat(" AND ")
                .concat(ormParam.getEqualXML(EmployeeConstants.REMP_DTYP_HIS, "0"));
        ormParam.setWhereExp(exp);

        List<RempRempPostSetaEntity> postSetaEntities = ormService.selectBeanList(RempRempPostSetaEntity.class, ormParam);

        //获取任职记录
        EmpPostSetDTO empPostSetDTO = null;
        List<EmpPostSetDTO> empPostSetDTOS = null;
        if (postSetaEntities != null && postSetaEntities.size() > 0) {
            empPostSetDTOS = JSONObject.parseArray(JSONObject.toJSONString(postSetaEntities), EmpPostSetDTO.class);
        }

        //记录是否找到最后一笔有效的任职记录
        int foundFlag = 0;
        if (empPostSetDTOS != null && empPostSetDTOS.size() > 0) {
            for (EmpPostSetDTO postSetDTO : empPostSetDTOS) {
                if (postSetDTO == null || postSetDTO.getRempPostEnd() == null) {
                    continue;
                }
                if (Long.parseLong(postSetDTO.getRempPostEnd()) > System.currentTimeMillis()) {
                    empPostSetDTO = postSetDTO;
                    foundFlag = 1;
                    break;
                }
            }

            if (foundFlag == 0) {
                empPostSetDTO = empPostSetDTOS.get(0);
            }
        }

        //计算在岗月
        String onGuardTime = "0.0";
        if (empPostSetDTO != null) {
            String beg = empPostSetDTO.getRempPostBeg();
            /* 注意点: 计算月份在相差66个月的时候不存在 直接从65跳到67 中间的66不存在,为此在大于67.0的时候做-1.0操作*/
            double d = DateDiff.monthsDateDiff(Long.parseLong(beg),new Date());
            if(d >= 67.0){

                d = d - 1.0;
            }
            onGuardTime = String.valueOf(d);
        }

        return onGuardTime;
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
        year = year / 365 / 24 / 60 / 60 ;
        //保留一位小数
        DecimalFormat df = new DecimalFormat("#0.0");
        return String.valueOf(df.format(year));
    }

    /**
     * 计算年龄
     *
     * @param startDate 起始日期
     * @return
     */
    private String calculateBirthYears(String startDate) {
        if (StringUtil.isNullOrEmpty(startDate)) {
            return null;
        }
        Long time = System.currentTimeMillis() - Long.parseLong(startDate);
        double year = time / 1000.0;
        year = year / 365 / 24 / 60 / 60 ;
        //取整数
        return String.valueOf((int)year);
    }

    /**
     * 设置人员合同基础信息
     *
     * @param nameAndNumber 姓名/工号
     * @param gender        性别
     * @param cardId        身份证号
     * @param recordState   合同状态
     * @param entryDate     入职日期
     * @param workAge       工龄
     * @param companyAge    司龄
     * @param onGuardTime   在岗月
     * @param employeeType  员工类型
     * @param parkName      办公园区
     * @param deptName      部门
     * @return
     */
    private StaffContractDTO getStaffContractDTO(String employeeId, String nameAndNumber, String gender, String age , String cardId, String recordState, String entryDate,
                                                 String workAge, String companyAge, String onGuardTime, String employeeType,
                                                 String parkName, String deptName) {
        StaffContractDTO staffContractDTO = new StaffContractDTO();
        staffContractDTO.setEmployeeId(employeeId);
        staffContractDTO.setNameAndNumber(nameAndNumber);
        staffContractDTO.setGender(gender);
        staffContractDTO.setAge(age);
        staffContractDTO.setIdCardNumber(cardId);
        staffContractDTO.setRecordState(recordState);
        staffContractDTO.setEntryDate(entryDate);
        staffContractDTO.setWorkAge(workAge);
        staffContractDTO.setCompanyAge(companyAge);
        staffContractDTO.setOnGuardTime(onGuardTime);
        staffContractDTO.setEmployeeType(employeeType);
        staffContractDTO.setParkName(parkName);
        staffContractDTO.setDepartmentName(deptName);
        return staffContractDTO;
    }

    /**
     * 设置员工合同状态、合同记录
     *
     * @param legalPerson   公司法人
     * @param contractStart 合同开始日期
     * @param contractEnd   合同结束日期
     * @param employeeDTOS  员工列表
     * @return
     */
    private List<EmployeeDTO> setContractRecords(String legalPerson, String contractStart, String contractEnd,
                                                 List<EmployeeDTO> employeeDTOS) throws Exception {
        if (employeeDTOS == null) {
            return null;
        }
        List<EmployeeDTO> resultList = new ArrayList<>();
        for (EmployeeDTO employeeDTO : employeeDTOS) {
            //根据员工id获取合同记录集合
            List<ContractRecordDTO> recordDTOS = getContractRecords(employeeDTO.getId());

            if (recordDTOS == null || recordDTOS.size() <= 0) {
                //未签：不包含合同记录
                employeeDTO.setRecondState("未签");
                if (StringUtil.isNullOrEmpty(legalPerson)
                        && StringUtil.isNullOrEmpty(contractStart)
                        && StringUtil.isNullOrEmpty(contractEnd)) {
                    resultList.add(employeeDTO);
                }
            } else {
                //已签：不包含解约合同
                //终止：包含解约合同
                if (!isTerminate(recordDTOS)) {
                    employeeDTO.setRecondState("已签");
                } else {
                    employeeDTO.setRecondState("终止");
                }
                //保存全部合同记录
                employeeDTO.setRempContSet(recordDTOS);
                //过滤、保存满足条件的合同记录
                List<ContractRecordDTO> temp = filterContractRecords(legalPerson, contractStart, contractEnd, recordDTOS);
                employeeDTO.setResultContractRecords(temp);
                resultList.add(employeeDTO);
            }

        }

        return resultList;
    }

    /**
     * 组合查询条件
     *
     * @param deptId
     * @param employeeType
     * @param officParks
     * @param entryStart
     * @param entryEnd
     * @param staffIdAndName
     * @return
     */
    private OrmParam getSearchParam(String deptId, String employeeType, String officParks, String entryStart, String entryEnd, String staffIdAndName) throws Exception {
        //查询员工表
        OrmParam ormParam = new OrmParam();
        StringBuilder exp = new StringBuilder();
        exp.append("1 = 1");
        //添加排除离职人员条件 就是员工状态为3
        exp.append(" AND ").append(ormParam.getInXML("remp_status",new String[]{"1","2"}));

        //添加条件-部门id非空、办公园区id为空时，根据部门id查找员工
        if (!StringUtil.isNullOrEmpty(deptId) && StringUtil.isNullOrEmpty(officParks)) {
            List<String> deptIds = getChildDept(deptId);
            deptIds.add(deptId);
            String idStr = getValue(deptIds);
            exp.append(" AND ").append(ormParam.getInXML(EmployeeConstants.REMP_DEPT, deptIds.toArray()));
        }

        //添加条件-部门id非空、办公园区id非空时，通过办公园区获得园区中部门id，然后取两个部门id集合的交集
        if (!StringUtil.isNullOrEmpty(deptId) && !StringUtil.isNullOrEmpty(officParks)) {
            //根据部门获取的部门id
            List<String> deptIds = getChildDept(deptId);
            deptIds.add(deptId);
            //根据园区获取的部门id
            List<String> deptIdsByPark = getDeptIdsOfPark(officParks);
            //取交集
            List<String> intersection = new ArrayList<>();

            if (deptIdsByPark != null && deptIdsByPark.size() > 0) {
                //在这里加了一个判断对于选择的上级部门字段id查询出对应的所有子部门id集合

                if(deptIds !=null && deptIds.size() > 0) {
                    for (String id : deptIds) {
                        if (deptIdsByPark.contains(id)) {
                            intersection.add(id);
                        }
                    }
                }
            }

            String idStr = getValue(intersection);
            if (StringUtil.isNullOrEmpty(idStr)) {
                exp.append(" AND ").append(ormParam.getInXML(EmployeeConstants.REMP_DEPT, new String[]{}));
            } else {
                exp.append(" AND ").append(ormParam.getInXML(EmployeeConstants.REMP_DEPT, intersection.toArray()));
            }
        }

        //添加条件-员工类型
        if (!StringUtil.isNullOrEmpty(employeeType)) {
            exp.append(" AND ").append(ormParam.getEqualXML(EmployeeConstants.REMP_TYPE, employeeType));
        }
        //添加条件-入职日起
        if (!StringUtil.isNullOrEmpty(entryStart)) {
            exp.append(" AND ").append(ormParam.getGreaterThanAndEqualXML(EmployeeConstants.REMP_IN_DATE, entryStart));
        }
        //添加条件-入职日至
        if (!StringUtil.isNullOrEmpty(entryEnd)) {
            exp.append(" AND ").append(ormParam.getLessThanAndEqualXML(EmployeeConstants.REMP_IN_DATE, entryEnd));
        }

        if (!StringUtil.isNullOrEmpty(staffIdAndName)) {
            //用来匹配中文
            Matcher m = p.matcher(staffIdAndName);

            //工号/姓名作为条件，模糊查询
            if (staffIdAndName.contains("/")) {
                String id = staffIdAndName.substring(0, staffIdAndName.indexOf("/"));
                String name = staffIdAndName.substring(staffIdAndName.indexOf("/") + 1, staffIdAndName.length());
                exp.append(" AND ").append(ormParam.getMatchMiddleXML(EmployeeConstants.REMP_NO, id));
                exp.append(" AND ").append(ormParam.getMatchMiddleXML(EmployeeConstants.EMP_NAME, name));
            } else if (m.find()) {
                exp.append(" AND ").append(ormParam.getMatchMiddleXML(EmployeeConstants.EMP_NAME, staffIdAndName));
            } else {
                exp.append(" AND ").append(ormParam.getMatchMiddleXML(EmployeeConstants.REMP_NO, staffIdAndName));
            }
        }
        ormParam.setWhereExp(exp.toString());
        System.out.println(exp.toString());
        return ormParam;
    }

    /**
     * 过滤出满足条件的合同记录
     *
     * @param legalPerson   法人公司id
     * @param contractStart 合同开始时间
     * @param contractEnd   合同结束时间
     * @param records       合同记录集合
     */
    private List<ContractRecordDTO> filterContractRecords(String legalPerson, String contractStart, String contractEnd, List<ContractRecordDTO> records) {
        if (records == null) {
            return null;
        }
        List<ContractRecordDTO> recordDTOS1 = new ArrayList<>();
        List<ContractRecordDTO> recordDTOS2 = new ArrayList<>();

        int flag = 0;
        for (ContractRecordDTO record : records) {
            //法人公司id非空时，根据法人公司id过滤，保存满足条件的合同记录
            if (!StringUtil.isNullOrEmpty(legalPerson)) {
                flag++;
                if (legalPerson.equals(record.getRempContMcop())) {
                    recordDTOS1.add(record);
                }
            } else {
                recordDTOS1.add(record);
            }

            //根据合同结束日期的区间过滤,保存满足条件的合同记录
            if (!StringUtil.isNullOrEmpty(contractStart) && !StringUtil.isNullOrEmpty(contractEnd)) {
                flag++;
                if (!StringUtil.isNullOrEmpty(record.getRempContEnd())
                        && Long.parseLong(record.getRempContEnd()) >= Long.parseLong(contractStart)
                        && Long.parseLong(record.getRempContEnd()) <= Long.parseLong(contractEnd)) {
                    recordDTOS2.add(record);
                }

            } else if (!StringUtil.isNullOrEmpty(contractStart)) {
                flag++;
                if (!StringUtil.isNullOrEmpty(record.getRempContEnd())
                        && Long.parseLong(record.getRempContEnd()) >= Long.parseLong(contractStart)) {
                    recordDTOS2.add(record);
                }
            } else if (!StringUtil.isNullOrEmpty(contractEnd)) {
                flag++;
                if (!StringUtil.isNullOrEmpty(record.getRempContEnd())
                        && Long.parseLong(record.getRempContEnd()) <= Long.parseLong(contractEnd)) {
                    recordDTOS2.add(record);
                }
            } else {
                recordDTOS2.add(record);
            }
        }

        List<ContractRecordDTO> recordDTOS = new ArrayList<>();
        if (flag > 0) {
            for (ContractRecordDTO recordDTO : recordDTOS1) {
                if (recordDTOS2.contains(recordDTO)) {
                    recordDTOS.add(recordDTO);
                }
            }
        } else {
            recordDTOS.addAll(records);
        }

        return recordDTOS;
    }

    /**
     * 判断合同记录中是否存在签订类型为解约的合同，有返回true，没有返回false
     *
     * @param recordDTOS
     * @return
     */
    private boolean isTerminate(List<ContractRecordDTO> recordDTOS) {
        Boolean ret = true;
        if (recordDTOS == null || recordDTOS.size() < 0) {
            ret = true;
        } else {
            for (ContractRecordDTO recordDTO : recordDTOS) {
                //3代表解约
                if ("3".equals(recordDTO.getRempSignType())) {
                    ret = true;
                    break;
                }

                ret = false;
            }
        }

        return ret;
    }

    /**
     * 获取满足条件的员工类
     *
     * @param ormParam
     * @return
     */
    private List<EmployeeDTO> getEmployees(OrmParam ormParam) throws Exception {
        if (ormParam == null) {
            return null;
        }
        List<EmployeeEntity> employeeEntities = ormService.selectBeanList(EmployeeEntity.class, ormParam);

        List<EmployeeDTO> list = null;
        if (employeeEntities != null && employeeEntities.size() > 0) {
            list = JSONObject.parseArray(JSONObject.toJSONString(employeeEntities), EmployeeDTO.class);
        }

        return list;
    }

    /**
     * 根据员工id获取员工合同记录
     *
     * @param employeeId
     * @return
     */
    private List<ContractRecordDTO> getContractRecords(String employeeId) throws Exception {
        if (StringUtil.isNullOrEmpty(employeeId)) { return null; }
        OrmParam ormParam = new OrmParam();
        ormParam.setWhereExp(ormParam.getEqualXML(ContractRecordConsts.PID, employeeId));
        ormParam.setOrderExp(SQLSortEnum.DESC, ContractRecordConsts.REMP_CONT_END);

        List<RempRempContSetaEntity> contracs = ormService.selectBeanList(RempRempContSetaEntity.class, ormParam);

        List<ContractRecordDTO> list = null;
        if (contracs != null && contracs.size() > 0) {
            list = JSONObject.parseArray(JSONObject.toJSONString(contracs), ContractRecordDTO.class);
        }

        return list;
    }

    /**
     * 查询部门下面的所有部门id
     *
     * @param deptId 部门id
     * @return
     */
    @Override
    public List<String> getChildDept(String deptId) throws Exception {
        if (StringUtil.isNullOrEmpty(deptId)) {
            return null;
        }
        OrmParam ormParam = new OrmParam();
        ormParam.setWhereExp(ormParam.getEqualXML(DeptTreeConstants.MDEP_PAR, deptId));

        List<DepttreeEntity> depttreeEntities = ormService.selectBeanList(DepttreeEntity.class, ormParam);

        List<String> list = new ArrayList<>();
        if (depttreeEntities != null && depttreeEntities.size() > 0) {
            for (DepttreeEntity depttreeEntity : depttreeEntities) {
                if (depttreeEntity != null) {
                    list.add(depttreeEntity.getId());
                    list.addAll(getChildDept(depttreeEntity.getId()));
                }
            }
        }

        return list;
    }

    /**
     * 根据园区id，获取园区中所有部门的id集合
     *
     * @param parkId
     * @return
     */
    private List<String> getDeptIdsOfPark(String parkId) throws Exception {
        if (StringUtil.isNullOrEmpty(parkId)) {
            return null;
        }
        OrmParam ormParam = new OrmParam();
        ormParam.setWhereExp(ormParam.getEqualXML(DeptTreeConstants.MDEP_RPAK, parkId));
        List<DepttreeEntity> depttreeEntities = ormService.selectBeanList(DepttreeEntity.class, ormParam);

        List<String> list = new ArrayList<>();
        if (depttreeEntities != null && depttreeEntities.size() > 0) {
            for (DepttreeEntity depttreeEntity : depttreeEntities) {
                if (depttreeEntity != null) {
                    list.add(depttreeEntity.getId());
                }
            }
        }

        return list;
    }

    /**
     * 将List中的元素拼接成字符串，中间用逗号隔开
     *
     * @param list
     * @return
     */
    private String getValue(List list) {
        StringBuilder sb = new StringBuilder();
        for (Object s : list) {
            sb.append(s).append(",");
        }
        if (!StringUtil.isNullOrEmpty(sb)) {
            sb.delete(sb.length() - 1, sb.length());
        }
        return sb.toString();
    }

    /**
     * 根据制单人id查询该制单人姓名
     */
    private String getApplicant(String id) throws Exception {
        if(StringUtil.isNullOrEmpty(id)){return null;}
        EmployeeEntity employeeEntity  = ormService.load(EmployeeEntity.class,id);
        String applicant = "";
        if(employeeEntity != null){ applicant =  employeeEntity.getRemp_name();}
        return applicant;

    }

    /**
     * 根据制单岗位id查询该对象的对应制单岗位名称
     */
    private String getApplicantOfPost(String id) throws Exception {
        if(StringUtil.isNullOrEmpty(id)){return null;}
        JobpositionEntity jobpositionEntity = ormService.load(JobpositionEntity.class,id);
        String applicantOfPost = "";
        if(jobpositionEntity!=null){applicantOfPost = jobpositionEntity.getRpos_name();}
        return applicantOfPost;

    }

    /**
     * 根据制单部门id查询该对象的对应制单部门名称
     */
    private String getApplicantOfDept(String id) throws Exception {
        if(StringUtil.isNullOrEmpty(id)){return null;}
        DepttreeEntity depttreeEntity = ormService.load(DepttreeEntity.class,id);
        String applicantDeptName = "";
        if(depttreeEntity!=null){applicantDeptName = depttreeEntity.getMdep_name();}
        return applicantDeptName;

    }

    /**
     * 批准单通过方法
     *
     * @param orderInstanceId
     * @return
     */
    private Result pass(String orderInstanceId) {

        Result result = new Result();

        int rent1 = -1;
        String rent2 = null;
        int rent3 = -1;

        try {
            // 查询合同签订类
            ContractsignapplyEntity contractsData = ormService.load(ContractsignapplyEntity.class, orderInstanceId);

            if (contractsData == null) {
                result.setRetCode(0);
                result.setErrMsg(MsgConstants.MSG_HR_NOT_EXIST_CS_DATA);
                result.setData(false);
                return result;
            }

            // 查询合同签订单类-签约人员属性集
            List<OcsoOcsoEmpSetaEntity> empSetArr = ormService.getAttrubuteSetByPID(orderInstanceId, OcsoOcsoEmpSetaEntity.class, ContractsignapplyEntity.class);

            if (empSetArr == null || empSetArr.size() == 0) {
                result.setRetCode(0);
                result.setErrMsg(MsgConstants.MSG_HR_NOT_EXIST_CS_OES);
                result.setData(false);
                return result;
            }

            // 遍历签约人员属性集
            for (OcsoOcsoEmpSetaEntity ocsoOcsoEmpSeta : empSetArr) {

                RempRempContSetaEntity rempRempContSeta = new RempRempContSetaEntity();
                rempRempContSeta.setClassName("employee");
                rempRempContSeta.setCreuser("");
                rempRempContSeta.setModuser("");

                // 合同记录的PID就是员工ID
                rempRempContSeta.setPid(ocsoOcsoEmpSeta.getOcso_emp());

                // 签订类型
                rempRempContSeta.setRemp_sign_type(contractsData.getOcso_type());

                if (!StringUtil.isNullOrEmpty(ocsoOcsoEmpSeta.getOcso_cont_beg())) {
                    // 合同开始日期
                    rempRempContSeta.setRemp_cont_beg(ocsoOcsoEmpSeta.getOcso_cont_beg());
                }
                if (!StringUtil.isNullOrEmpty(ocsoOcsoEmpSeta.getOcso_cont_end())) {
                    // 合同结束日期
                    rempRempContSeta.setRemp_cont_end(ocsoOcsoEmpSeta.getOcso_cont_end());
                }
                if (!StringUtil.isNullOrEmpty(contractsData.getOcso_sign_date())) {
                    // 签订日期
                    rempRempContSeta.setRemp_cont_sdate(contractsData.getOcso_sign_date());
                }
                // 法人公司
                rempRempContSeta.setRemp_cont_mcop(contractsData.getOcso_mcop());

                // 判断该员工是否存在合同记录,在员工类-合同记录表中查询
                List<RempRempContSetaEntity> contracts = ormService.getAttrubuteSetByPID(ocsoOcsoEmpSeta.getOcso_emp(), RempRempContSetaEntity.class, EmployeeEntity.class);
                // 添加排序字段 取最近的一条记录
                List<RempRempContSetaEntity> contractsOrdered = contracts.stream().sorted((h1, h2) -> {
                    if (h1.getCretime().getTime() > h2.getCretime().getTime()) {
                        return -1;
                    }
                    return 1;
                }).collect(Collectors.toList());

                // 存在 -> 更新上一笔合同记录的合同实际结束日期 = 本次合同的开始日期 - 1
                if (null != contractsOrdered && contractsOrdered.size() != 0) {

                    // 由于按照了创建时间排序，故第一条就是最新的数据
                    RempRempContSetaEntity latestData = contractsOrdered.get(0);

                    RempRempContSetaEntity contractRecordOldData = new RempRempContSetaEntity();
                    contractRecordOldData.setId(latestData.getId());
                    contractRecordOldData.setRemp_cont_rend(new Date((ocsoOcsoEmpSeta.getOcso_cont_beg().getTime()) - 86400000));

                    // 更新上一笔合同记录
                    rent1 = ormService.updateSelective(contractRecordOldData);
                }
                // 插入数据到合同记录表
                rent2 = (String) ormService.insertSelective(rempRempContSeta);

                // 修改合同签订单 状态orde_status为5
                ContractsignapplyEntity contractsignapplUpdate = new ContractsignapplyEntity();
                contractsignapplUpdate.setId(orderInstanceId);
                contractsignapplUpdate.setOrde_status(OrderStatusConstants.ORDER_STATUS_5);
                contractsignapplUpdate.setModuser("");

                rent3 = ormService.updateSelective(contractsignapplUpdate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (rent1 != -1 && !StringUtil.isNullOrEmpty(rent2) && rent3 != -1) {
            result.setData(true);
        } else {
            result.setData(false);
        }
        return result;
    }

    /**
     * 修改单据状态
     *
     * @param orderInstanceId
     * @param status
     */
    private Result updateOrderStatus(String orderInstanceId, String status) {
        Result result = new Result();
        int rent = -1;

        ContractsignapplyEntity contractsignapplUpdate = new ContractsignapplyEntity();
        contractsignapplUpdate.setId(orderInstanceId);
        contractsignapplUpdate.setOrde_status(status);
        contractsignapplUpdate.setModuser("");
        try {
            rent = ormService.updateSelective(contractsignapplUpdate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (rent != -1) {
            result.setData(true);
        } else {
            result.setRetCode(Result.RECODE_ERROR);
            result.setData(false);
        }
        return result;
    }

    /**
     * 根据员工id去签约人员表里查到合同签订单id
     * 根据合同签订单id获取单据状态
     * 如果单据状态为2 3 4 说明是待审单据
     * 返回单据id  员工姓名和工号 合同
     */
    private Result checkSign(String[] ids) {
        Result result = new Result();
        Map<String,String> map1 = new HashMap<>();
        String signEmp = "";
        for (String id : ids) {
            try {
                OrmParam ormParam = new OrmParam();
                ormParam.setWhereExp(ormParam.getEqualXML("ocso_emp", id));
                List<OcsoOcsoEmpSetaEntity> ocsoOcsoEmpSetaEntitys = ormService.selectBeanList(OcsoOcsoEmpSetaEntity.class, ormParam);

                if (ocsoOcsoEmpSetaEntitys != null && ocsoOcsoEmpSetaEntitys.size() > 0) {
                    for (OcsoOcsoEmpSetaEntity ocsoOcsoEmpSetaEntity : ocsoOcsoEmpSetaEntitys) {
                        if (ocsoOcsoEmpSetaEntity != null) {
                            ContractsignapplyEntity contractsignapplyEntity = ormService.load(ContractsignapplyEntity.class,ocsoOcsoEmpSetaEntity.getPid());
                            //合同状态为待审 2 3 4
                            if(contractsignapplyEntity != null && !StringUtil.isNullOrEmpty(contractsignapplyEntity.getOrde_status()) || "2".equals(contractsignapplyEntity.getOrde_status()) || "3".equals(contractsignapplyEntity.getOrde_status()) || "4".equals(contractsignapplyEntity.getOrde_status())){
                                //捕获待审单据的员工id
                                ocsoOcsoEmpSetaEntity.getOcso_emp();
                                //捕获待审单据的单号
                                contractsignapplyEntity.getOrde_nbr();
                                EmployeeEntity employeeEntity = ormService.load(EmployeeEntity.class,ocsoOcsoEmpSetaEntity.getOcso_emp());
                                if( employeeEntity != null){
                                    signEmp = employeeEntity.getRemp_name().concat("/").concat(employeeEntity.getRemp_no()).concat("已经存在劳动合同签订待审单,单号").concat(contractsignapplyEntity.getOrde_nbr());
                                    map1.put(ocsoOcsoEmpSetaEntity.getOcso_emp(),signEmp);
                                    result.setRetCode(Result.RECODE_ERROR);
                                }
                            }
                            //list预留位置
                            result.setData(map1);
                        }
                    }
                }

            } catch (Exception e) {
                if(logger.isDebugEnabled()) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * 根据员工id去签约人员表里查到合同签订单id
     * 根据合同签订单id获取单据状态
     * 如果单据状态为2 3 4 说明是待审单据
     * 返回单据id  员工姓名和工号 合同
     */
    private ContractsignapplyEntity checkSignEmp(String id) {
        ContractsignapplyEntity contractsignapplyEntity1 = new ContractsignapplyEntity();
        if(StringUtil.isNullOrEmpty(id)){return null;}
        if(!StringUtil.isNullOrEmpty(id)){
            OrmParam ormParam = new OrmParam();
            ormParam.setWhereExp(ormParam.getEqualXML("ocso_emp", id));
            try {
                List<OcsoOcsoEmpSetaEntity> ocsoOcsoEmpSetaEntitys = ormService.selectBeanList(OcsoOcsoEmpSetaEntity.class, ormParam);
                if (ocsoOcsoEmpSetaEntitys != null && ocsoOcsoEmpSetaEntitys.size() > 0) {
                    for (OcsoOcsoEmpSetaEntity ocsoOcsoEmpSetaEntity : ocsoOcsoEmpSetaEntitys) {
                        if (ocsoOcsoEmpSetaEntity != null) {
                            ContractsignapplyEntity contractsignapplyEntity = ormService.load(ContractsignapplyEntity.class,ocsoOcsoEmpSetaEntity.getPid());
                            //合同状态为待审 2 3 4
                            if(contractsignapplyEntity != null && !StringUtil.isNullOrEmpty(contractsignapplyEntity.getOrde_status()) && ("2".equals(contractsignapplyEntity.getOrde_status()) || "3".equals(contractsignapplyEntity.getOrde_status()) || "4".equals(contractsignapplyEntity.getOrde_status()))){
                                //捕获待审单据的员工id
                                ocsoOcsoEmpSetaEntity.getOcso_emp();
                                //捕获待审单据的单号
                                contractsignapplyEntity.getOrde_nbr();
                                //获取单据状态
                                contractsignapplyEntity.getOrde_status();

                                contractsignapplyEntity1 = contractsignapplyEntity;
                            }
                        }
                    }
                }
            } catch (Exception e) {
                if(logger.isDebugEnabled()) {
                    e.printStackTrace();
                }
            }
        }
        return contractsignapplyEntity1;
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
     * 获取合同签订单编号(参数"HR18")
     *
     * @param nbrlCode
     * @return
     */
    public String getContractCode(String nbrlCode) {
        Result codeResult = informationClient.getNumbers(nbrlCode, null);
        if (codeResult.getData() == null) {
            throw new ApplicationException(Result.RECODE_ERROR, codeResult.getErrMsg());
        }
        return codeResult.getData().toString();
    }



}
