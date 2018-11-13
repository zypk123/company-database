package com.huntkey.rx.hr.provider.controller;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.datetime.DateUtil;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.edm.entity.JobpositionEntity;
import com.huntkey.rx.hr.common.constants.MsgConstants;
import com.huntkey.rx.hr.common.constants.WFHandlerTypeConstants;
import com.huntkey.rx.hr.common.model.*;
import com.huntkey.rx.hr.provider.service.*;
import com.huntkey.rx.sceo.method.register.plugin.annotation.MethodRegister;
import com.huntkey.rx.sceo.method.register.plugin.entity.MethodExeFrequency;
import com.huntkey.rx.sceo.method.register.plugin.entity.ProgramCate;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@Validated
@RequestMapping("/hr/dept")
public class DeptTreeController {

    private static Logger logger = LoggerFactory.getLogger(DeptTreeController.class);

    @Autowired
    DeptTreeService deptTreeService;

    @Autowired
    PostService postSrv;

    @Autowired
    DeptStuChangeService deptStuChangeService;

    @Autowired
    BizFormService bizFormService;

    @Autowired
    DeptTransactionService deptTransactionService;

    private static final String YYYY_MM_DD = "yyyy-MM-dd";

    /**
     * 部门树形列表查询方法
     *
     * @param id         根部门id
     * @param beginDate  生效日期
     * @param level      展开层数
     * @param complement 是否补全关联属性名
     * @return
     */
    @MethodRegister(
            edmClass = DeptTreeConstants.EDM_DEPTTREE,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "查询部门树列表",
            getReqParamsNameNoPathVariable = {"id", "beginDate", "level", "complement"}
    )
    @RequestMapping(value = "/deptTreeList", method = RequestMethod.GET)
    public Result deptTreeList(@RequestParam String id,
                               @RequestParam @NotBlank(message = MsgConstants.MSG_HR_NOT_BLANK_DEPT_BEGIN_DATE)
                               @Pattern(regexp = "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$", message = MsgConstants.MSG_HR_PATTERN_DEPT_BEGIN_DATE)
                                       String beginDate,
                               @RequestParam @NotNull(message = MsgConstants.MSG_HR_NOT_NULL_DEPT_LEVEL) @Min(value = 1, message = MsgConstants.MSG_HR_MIN_DEPT_LEVEL) int level,
                               @RequestParam(defaultValue = "0") @Range(min = 0, max = 1, message = MsgConstants.MSG_HR_RANG_DEPT_COMPLEMENT) int complement) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        DeptTreeDTO data = deptTreeService.getDeptTreeList(id, DateUtil.parseFormatDate(beginDate, YYYY_MM_DD), level, complement);
        result.setData(data);
        return result;
    }

    /**
     * 查询部门列表
     *
     * @param deptQueryListDTO
     * @return
     */
    @MethodRegister(
            edmClass = DeptTreeConstants.EDM_DEPTTREE,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "查询部门列表"
    )
    @RequestMapping(value = "/deptList", method = RequestMethod.POST)
    public Result deptList(@RequestBody DeptQueryListDTO deptQueryListDTO) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        List<DeptTreeDTO> data = deptTreeService.getDeptList(deptQueryListDTO.getDeptIds(), DateUtil.parseFormatDate(deptQueryListDTO.getDate(), YYYY_MM_DD),
                deptQueryListDTO.getComplement());
        result.setData(data);
        return result;
    }

    /**
     * 删除部门
     *
     * @param deptIds
     * @return
     */
    @MethodRegister(
            edmClass = DeptTreeConstants.EDM_DEPTTREE,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "删除部门"
    )
    @RequestMapping(value = "/deptDelete", method = RequestMethod.DELETE)
    public Result deptDelete(@RequestBody @NotEmpty(message = "部门ID数组不能为空") String[] deptIds) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        deptTreeService.deleteDeptByIds(deptIds);
        return result;
    }

    /**
     * 部门可维护性校验
     *
     * @param deptIsEditableDTO
     * @return
     */
    @MethodRegister(
            edmClass = DeptTreeConstants.EDM_DEPTTREE,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "部门可维护性校验方法"
    )
    @RequestMapping(value = "/deptIsEditable", method = RequestMethod.POST)
    public Result deptIsEditable(@RequestBody DeptIsEditableDTO deptIsEditableDTO) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        List<DeptStuChangeOrderDTO> deptStuChangeOrderDTOS = null;
        if (deptIsEditableDTO.getType().equals(OrderConstants.ORDE_TYPE_ODSC_ADD)) {
            if (deptIsEditableDTO.getDeptCodes().length > 1) {
                result.setRetCode(Result.RECODE_ERROR);
                result.setErrMsg("order type is '" + OrderConstants.ORDE_TYPE_ODSC_ADD
                        + "', you can only select one department");
            } else {
                deptStuChangeOrderDTOS = deptTreeService.checkDeptChangeOrderByAdd(deptIsEditableDTO.getDeptCodes(), DateUtil.parseFormatDate(deptIsEditableDTO.getDate(), YYYY_MM_DD));
            }
        } else if (deptIsEditableDTO.getType().equals(OrderConstants.ORDE_TYPE_ODSC_MODIFY)) {
            deptStuChangeOrderDTOS = deptTreeService.checkDeptChangeOrderByModify(deptIsEditableDTO.getDeptCodes(), DateUtil.parseFormatDate(deptIsEditableDTO.getDate(), YYYY_MM_DD));
        } else if (deptIsEditableDTO.getType().equals(OrderConstants.ORDE_TYPE_ODSC_MOVE)) {
            deptStuChangeOrderDTOS = deptTreeService.checkDeptChangeOrderByMove(deptIsEditableDTO.getDeptCodes(), DateUtil.parseFormatDate(deptIsEditableDTO.getDate(), YYYY_MM_DD));
        } else if (deptIsEditableDTO.getType().equals(OrderConstants.ORDE_TYPE_ODSC_CANCEL)) {
            deptStuChangeOrderDTOS = deptTreeService.checkDeptChangeOrderByCancel(deptIsEditableDTO.getDeptCodes(), DateUtil.parseFormatDate(deptIsEditableDTO.getDate(), YYYY_MM_DD));
        } else if (deptIsEditableDTO.getType().equals(OrderConstants.ORDE_TYPE_ODCA)) {
            deptStuChangeOrderDTOS = deptTreeService.checkDeptChangeOrderByODCA(deptIsEditableDTO.getDeptCodes(), DateUtil.parseFormatDate(deptIsEditableDTO.getDate(), YYYY_MM_DD));
        } else {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg("wrong order type");
        }

        Set<String> resultSet = new HashSet();
        if (null != deptStuChangeOrderDTOS) {
            for (DeptStuChangeOrderDTO changeOrderDTO : deptStuChangeOrderDTOS) {
                //根据部门结构异动单据类型code，查询对应的枚举名
                String orderTypeName = deptTreeService.getOrderType(changeOrderDTO.getOdscType()).getString("word_name");
                for (OdscChagSetDTO chagSetDTO : changeOrderDTO.getOdscChagSet()) {
                    if (chagSetDTO.getOdscFlag().equals(OdscChagSetConstants.ODSC_FLAG_ADD)
                            && !StringUtil.isNullOrEmpty(chagSetDTO.getOdscPdept())) {
                        DeptTreeDTO deptTreeDTO = deptTreeService.getDept(chagSetDTO.getOdscPdept(), DateUtil.parseFormatDate(deptIsEditableDTO.getDate(), YYYY_MM_DD));
                        StringBuffer sb = new StringBuffer();
                        sb.append(chagSetDTO.getOdscDeptCode()).append("/").append(deptTreeDTO.getMdepName())
                                .append("存在").append(orderTypeName).append("类型的审核中单据：")
                                .append(changeOrderDTO.getOrdeNbr());
                        resultSet.add(sb.toString());
                    } else {
                        StringBuffer sb = new StringBuffer();
                        DeptTreeInfoDTO deptTreeInfoDTO = deptTreeService.getDeptByCode(chagSetDTO.getOdscDeptCode());
                        DeptTreeDTO deptTreeDTO = deptTreeService.getDept(deptTreeInfoDTO.getId(), DateUtil.parseFormatDate(deptIsEditableDTO.getDate(), YYYY_MM_DD));
                        sb.append(chagSetDTO.getOdscDeptCode()).append("/").append(deptTreeDTO.getMdepName())
                                .append("存在").append(orderTypeName).append("类型的审核中单据：")
                                .append(changeOrderDTO.getOrdeNbr());
                        resultSet.add(sb.toString());
                    }
                }
            }
        }
        if (resultSet.size() > 0) {
            result.setErrMsg(StringUtils.join(resultSet, "\n"));
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
        }
        return result;
    }

    /**
     * 部门新增单据保存方法
     *
     * @param deptStuChangeOrderDTO
     * @return
     */
    @MethodRegister(
            edmClass = DeptStuChangeOrderConstants.EDM_DEPT_STU_CHANGE_ORDER,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "部门新增单据保存方法"
    )
    @RequestMapping(value = "/saveDeptAddOrder", method = RequestMethod.POST)
    public Result saveDeptAddOrder(@RequestBody DeptStuChangeOrderDTO deptStuChangeOrderDTO) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        JSONObject resultObj = deptTreeService.saveDeptStuChangeOrder(deptStuChangeOrderDTO, DeptStuChangeOrderConstants.ODSC_TYPE_ADD_ID);
        result.setData(resultObj);
        return result;
    }

    /**
     * 部门新增单据加载方法
     *
     * @param orderId
     * @return
     */
    @MethodRegister(
            edmClass = DeptStuChangeOrderConstants.EDM_DEPT_STU_CHANGE_ORDER,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "部门新增单据加载方法",
            getReqParamsNameNoPathVariable = {"orderId"}
    )
    @RequestMapping(value = "/loadDeptAddOrder", method = RequestMethod.GET)
    public Result loadDeptAddOrder(@RequestParam @NotBlank(message = "部门结构异动单id不能为空") String orderId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        DeptStuChangeOrderDTO deptStuChangeOrderDTO = deptTreeService.getDeptChangeOrderAddById(orderId);
        result.setData(deptStuChangeOrderDTO);
        return result;
    }

    /**
     * 部门新增单据提交方法
     *
     * @param deptStuChangeOrderDTO
     * @return
     */
    @MethodRegister(
            edmClass = DeptStuChangeOrderConstants.EDM_DEPT_STU_CHANGE_ORDER,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "部门新增单据提交方法"
    )
    @RequestMapping(value = "/submitDeptAddOrder", method = RequestMethod.POST)
    public Result submitDeptAddOrder(@RequestBody DeptStuChangeOrderDTO deptStuChangeOrderDTO) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        JSONObject resultObj = deptTreeService.saveDeptStuChangeOrder(deptStuChangeOrderDTO, DeptStuChangeOrderConstants.ODSC_TYPE_ADD_ID);
        //提交流程
        String orderInstanceId = resultObj.getString("orderId");
        String orderDefId = deptStuChangeOrderDTO.getOrdeRodeObj();
        bizFormService.submitWorkFlow(orderDefId, orderInstanceId);
        deptStuChangeService.updateDeptOrderStatus(orderInstanceId, OrderConstants.ORDE_STATUS_2);
        result.setData(resultObj);
        return result;
    }

    /**
     * 部门新增单据流程审批通过
     * @param orderInstanceId
     * @param handlerType
     * @return
     */
    @MethodRegister(
            edmClass = DeptStuChangeOrderConstants.EDM_DEPT_STU_CHANGE_ORDER,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "部门单据流程审批通过",
            getReqParamsNameNoPathVariable = {"orderInstanceId", "handlerType"}
    )
    @RequestMapping(value = "/pass", method = RequestMethod.GET)
    public Result approve(@RequestParam(value = "orderInstanceId") String orderInstanceId,
                                   @RequestParam(value = "handlerType") String handlerType) {
        logger.info("部门异动单审批通过回调接口：orderInstanceId：" + orderInstanceId + ",handlerType:" + handlerType);
        Result result = new Result();
        switch (handlerType) {
            case WFHandlerTypeConstants.PASS: {
                DeptStuChangeOrderDTO orderDTO = deptTreeService.getDeptChangeOrderById(orderInstanceId);
                if (orderDTO.getOdscType().equals(DeptStuChangeOrderConstants.ODSC_TYPE_ADD_ID)){
                    result = deptStuChangeService.addDept(orderInstanceId);
                }else if (orderDTO.getOdscType().equals(DeptStuChangeOrderConstants.ODSC_TYPE_MODIFY_ID)){
                    result = deptStuChangeService.modifyDept(orderInstanceId);
                }else if (orderDTO.getOdscType().equals(DeptStuChangeOrderConstants.ODSC_TYPE_MOVE_ID)){
                    result = deptTransactionService.passMoveOrder(orderInstanceId);
                }else if (orderDTO.getOdscType().equals(DeptStuChangeOrderConstants.ODSC_TYPE_CANCEL_ID)){
                    result = deptTransactionService.passDeptDeleteOrder(orderInstanceId);
                }
                break;
            }
            case WFHandlerTypeConstants.REVOKE: {
                deptStuChangeService.updateDeptOrderStatus(orderInstanceId, OrderConstants.ORDE_STATUS_1);
                break;
            }
            case WFHandlerTypeConstants.RETURN_BACK: {
                deptStuChangeService.updateDeptOrderStatus(orderInstanceId, OrderConstants.ORDE_STATUS_6);
                break;
            }
            default: {
                break;
            }
        }
        return result;
    }

    /**
     * 部门修改单据保存方法
     *
     * @param deptStuChangeOrderDTO
     * @return
     */
    @MethodRegister(
            edmClass = DeptStuChangeOrderConstants.EDM_DEPT_STU_CHANGE_ORDER,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "部门修改单据保存方法"
    )
    @RequestMapping(value = "/saveDeptModifyOrder", method = RequestMethod.POST)
    public Result saveDeptModifyOrder(@RequestBody DeptStuChangeOrderDTO deptStuChangeOrderDTO) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        JSONObject resultObj = deptTreeService.saveDeptStuChangeOrder(deptStuChangeOrderDTO, DeptStuChangeOrderConstants.ODSC_TYPE_MODIFY_ID);
        result.setData(resultObj);
        return result;
    }

    /**
     * 部门修改单据加载方法
     *
     * @param orderId
     * @return
     */
    @MethodRegister(
            edmClass = DeptStuChangeOrderConstants.EDM_DEPT_STU_CHANGE_ORDER,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "部门修改单据加载方法",
            getReqParamsNameNoPathVariable = {"orderId"}
    )
    @RequestMapping(value = "/loadDeptModifyOrder", method = RequestMethod.GET)
    public Result loadDeptModifyOrder(@RequestParam @NotBlank(message = "部门结构异动单id不能为空") String orderId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        DeptStuChangeOrderDTO deptStuChangeOrderDTO = deptTreeService.getDeptChangeOrderEditById(orderId);
        result.setData(deptStuChangeOrderDTO);
        return result;
    }

    /**
     * 部门修改单据提交方法
     *
     * @param deptStuChangeOrderDTO
     * @return
     */
    @MethodRegister(
            edmClass = DeptStuChangeOrderConstants.EDM_DEPT_STU_CHANGE_ORDER,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "部门修改单据提交方法"
    )
    @RequestMapping(value = "/submitDeptModifyOrder", method = RequestMethod.POST)
    public Result submitDeptModifyOrder(@RequestBody DeptStuChangeOrderDTO deptStuChangeOrderDTO) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        JSONObject resultObj = deptTreeService.saveDeptStuChangeOrder(deptStuChangeOrderDTO, DeptStuChangeOrderConstants.ODSC_TYPE_MODIFY_ID);
        //提交流程
        String orderInstanceId = resultObj.getString("orderId");
        String orderDefId = deptStuChangeOrderDTO.getOrdeRodeObj();
        bizFormService.submitWorkFlow(orderDefId, orderInstanceId);
        deptStuChangeService.updateDeptOrderStatus(orderInstanceId, OrderConstants.ORDE_STATUS_2);
        result.setData(resultObj);
        return result;
    }

    /**
     * 查询岗位所在部门的上级部门树
     *
     * @param postId
     * @return
     */
    @MethodRegister(
            edmClass = DeptTreeConstants.EDM_DEPTTREE,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "查询岗位所在部门的上级部门树",
            getReqParamsNameNoPathVariable = {"postId"}
    )
    @RequestMapping(value = "/parentTree", method = RequestMethod.GET)
    public Result getParentTreeById(@RequestParam @NotBlank(message = "岗位id不能为空") String postId) throws Exception{
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        JobpositionEntity postTo = postSrv.queryPostById(postId);
        if (postTo != null){
            result.setData(deptTreeService.getParentTreeById(postTo.getRpos_dept(), DateUtil.parseFormatDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()), YYYY_MM_DD)));
        }
        return result;
    }

    /**
     * 查询顶级部门
     * @return
     */
    @MethodRegister(
            edmClass = DeptTreeConstants.EDM_DEPTTREE,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "查询顶级部门"
    )
    @RequestMapping(value = "/root", method = RequestMethod.GET)
    public Result rootDept(){
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        DeptTreeInfoDTO deptTreeInfoDTO = deptTreeService.getRootDept();
        result.setData(deptTreeInfoDTO);
        return result;
    }

    @MethodRegister(
            edmClass = DeptStuChangeOrderConstants.EDM_DEPT_STU_CHANGE_ORDER,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "部门单据填写审核意见方法")
    @RequestMapping(value = "/audit", method = RequestMethod.POST)
    public Result audit(@RequestBody JSONObject auditSet) {
        return deptTreeService.audit(auditSet);
    }

}
