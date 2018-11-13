package com.huntkey.rx.hr.provider.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.hr.common.model.DeptStuChangeOrderConstants;
import com.huntkey.rx.hr.common.model.DeptStuChangeOrderDTO;
import com.huntkey.rx.hr.common.model.DeptTreeConstants;
import com.huntkey.rx.hr.common.util.DateUtils;
import com.huntkey.rx.hr.provider.service.DeptTransactionService;
import com.huntkey.rx.sceo.method.register.plugin.annotation.MethodRegister;
import com.huntkey.rx.sceo.method.register.plugin.entity.MethodExeFrequency;
import com.huntkey.rx.sceo.method.register.plugin.entity.ProgramCate;
import com.huntkey.rx.sceo.orm.common.constant.EdmSysColumn;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 部门异动单控制类，包括部门调动和部门注销主要操作接口
 *
 * @author Created by liuwens on 2017/11/10.
 */
@RestController
@RequestMapping("/hr/dept")
public class DeptTransactionController {
    Logger LOGGER = LoggerFactory.getLogger(DeptTransactionController.class);

    @Autowired
    DeptTransactionService deptTransactionService;

    static final String NEW_P_DEPT_ID = "newPDeptId";

    static final String DELAY_MOVE_DEPT_ID_LIST = "delayMoveDeptIdList";

    static final String ORDER_TYPE = "orderType";

    /**
     * 有效时间
     */
    static final String ODSC_BEG = "odscBeg";

    /**
     * 【调动】
     * 查询部门调动列表-前端界面使用
     *
     * @param param 待调动部门的id列表
     * @return
     */
    @MethodRegister(
            edmClass = DeptTreeConstants.EDM_DEPTTREE,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "查询部门调动列表-前端界面使用")
    @RequestMapping(value = "/moveDeptList", method = RequestMethod.POST)
    public Result queryMoveDeptList(@RequestBody @NotNull JSONObject param)
    {
        Result result = new Result();

        if (null == param || !param.containsKey(DELAY_MOVE_DEPT_ID_LIST) || param.getJSONArray(DELAY_MOVE_DEPT_ID_LIST).isEmpty())
        {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg("输入校验的参数不合法, 缺少待调动部门对象Id列表字段");
            return result;
        }

        if (!param.containsKey(ODSC_BEG) || StringUtil.isNullOrEmpty(param.getString(ODSC_BEG)))
        {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(String.format("输入校验的参数不合法, 缺少生效日期%s字段" , ODSC_BEG));
            return result;
        }

        JSONArray idJSONArray = param.getJSONArray(DELAY_MOVE_DEPT_ID_LIST);

        String odscBeg = param.getString(ODSC_BEG);

        List<String> deptIdList = JSONArray.parseArray(idJSONArray.toJSONString(), String.class);

        deptIdList.forEach(t -> LOGGER.info("--> 部门ID:" + t));

        return deptTransactionService.queryDeptListService(odscBeg , deptIdList);
    }

    /**
     * 【调动】
     * 部门结构异动单据加载方法-流程加载单据时使用
     *
     * @param orderIdValue   单据对象Id
     * @param orderTypeValue 单据类型, 部门调动2 部门注销3
     * @return
     */
    @MethodRegister(
            edmClass = DeptStuChangeOrderConstants.EDM_DEPT_STU_CHANGE_ORDER,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "部门结构异动单据加载方法-流程加载单据时使用")
    @RequestMapping(value = "/orders/{orderId}/{orderType}", method = RequestMethod.GET)
    public Result loadDeptMoveOrder(@PathVariable(value = "orderId") @NotEmpty String orderIdValue, @PathVariable(value = "orderType") @NotEmpty String orderTypeValue)
    {
        return deptTransactionService.loadMoveDeptOrderService(orderIdValue, orderTypeValue);
    }


    /**
     * 【调动】
     * 部门调动单保存方法
     *
     * @param jsonObject 部门结构异动单类
     * @return
     */
    @MethodRegister(
            edmClass = DeptStuChangeOrderConstants.EDM_DEPT_STU_CHANGE_ORDER,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "部门调动单保存方法")
    @RequestMapping(value = "/saveMoveOrder", method = RequestMethod.POST)
    public Result saveDeptMoveOrder(@RequestBody JSONObject jsonObject)
    {
        return deptTransactionService.saveDeptTransactionOrderService(jsonObject);
    }

    /**
     * 【调动】
     * 部门调动单提交方法
     * @param jsonObject
     * @return
     */
    @MethodRegister(
            edmClass = DeptStuChangeOrderConstants.EDM_DEPT_STU_CHANGE_ORDER,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "部门调动单提交方法")
    @RequestMapping(value = "/submitMoveOrder", method = RequestMethod.POST)
    public Result submitDeptMoveOrder(@RequestBody @NotEmpty JSONObject jsonObject)
    {
        return submitOrder(jsonObject);
    }


    /**
     * 【注销】
     * 查询注销部门列表
     *
     * @param param
     * @return
     */
    @MethodRegister(
            edmClass = DeptStuChangeOrderConstants.EDM_DEPT_STU_CHANGE_ORDER,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "查询注销部门列表")
    @RequestMapping(value = "/deptDelList", method = RequestMethod.POST)
    public Result loadDeptDeleteList(@RequestBody @NotNull JSONObject param)
    {
        Result result = new Result();

        if (null == param || !param.containsKey(DELAY_MOVE_DEPT_ID_LIST) || param.getJSONArray(DELAY_MOVE_DEPT_ID_LIST).isEmpty())
        {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg("输入校验的参数不合法, 缺少待调动部门对象Id列表字段");
            return result;
        }

        if (!param.containsKey(ODSC_BEG) || StringUtil.isNullOrEmpty(param.getString(ODSC_BEG)) )
        {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(String.format("输入校验的参数不合法, 缺少生效日期%s字段" , ODSC_BEG));
            return result;
        }

        JSONArray idJSONArray = param.getJSONArray(DELAY_MOVE_DEPT_ID_LIST);

        String odscBeg = param.getString(ODSC_BEG);

        List<String> deptIdList = JSONArray.parseArray(idJSONArray.toJSONString(), String.class);

        deptIdList.forEach(t -> LOGGER.info("-->  待注销的部门ID:" + t));

        return deptTransactionService.queryDeptListService(odscBeg , deptIdList);
    }

    /**
     * 【注销】
     * 部门注销单保存方法
     *
     * @param jsonObject
     * @return
     */
    @MethodRegister(
            edmClass = DeptStuChangeOrderConstants.EDM_DEPT_STU_CHANGE_ORDER,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "部门注销单保存方法")
    @RequestMapping(value = "/saveDeleteOrder", method = RequestMethod.POST)
    public Result saveDeptDeleteOrder(@RequestBody JSONObject jsonObject)
    {
        //单据类型为：部门注销
        return deptTransactionService.saveDeptTransactionOrderService(jsonObject);
    }

    /**
     * 【注销】
     * 部门注销单据提交方法
     * @param jsonObject
     * @return
     */
    @MethodRegister(
            edmClass = DeptStuChangeOrderConstants.EDM_DEPT_STU_CHANGE_ORDER,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "部门注销单据提交方法")
    @RequestMapping(value = "/submitDeptDeleteOrder", method = RequestMethod.POST)
    public Result submitDeptDeleteOrder(@RequestBody @NotEmpty JSONObject jsonObject)
    {
        return submitOrder(jsonObject);
    }

    /**
     * 【注销】
     * 查询部门下的待审单据列表
     *
     * @param deptIdValue
     * @return
     */
    @RequestMapping(value = "/deptDelOrder/{deptId}", method = RequestMethod.GET)
    public Result queryDeptPendingTrialOrderList(@PathVariable(value = "deptId") String deptIdValue)
    {
        return null;
    }

    /**
     * 部门结构异动单校验方法
     *
     * @param removeableParam 检验参数{newPDeptId:新上级部门（调入部门）, orderType: 调动2/注销3 delayMoveDeptIdList:待调动部门对象列表, odscBeg:yyyy-MM-dd hh:mm:ss}
     * @return
     */
    @MethodRegister(
            edmClass = DeptStuChangeOrderConstants.EDM_DEPT_STU_CHANGE_ORDER,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "部门结构异动单校验方法(包括部门调动和部门注销的校验)")
    @RequestMapping(value = "/deptRemoveable", method = RequestMethod.POST)
    public Result deptRemovable(@RequestBody @NotEmpty JSONObject removeableParam)
    {
        Result result = new Result();

        if (null == removeableParam)
        {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg("未获得校验的参数removeableParam");
            return result;
        }

        if (!removeableParam.containsKey(DELAY_MOVE_DEPT_ID_LIST))
        {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg("输入校验的参数不合法，缺少部门对象列表");
            return result;
        }

        if (!removeableParam.containsKey(ODSC_BEG))
        {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg("输入校验的参数不合法, 缺少有效时间字段");
            return result;
        }

        if (!removeableParam.containsKey(ORDER_TYPE))
        {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg("输入校验的参数不合法, 缺少操作类型[调动 2/注销 3]字段");
            return result;
        }

        //待调动部门的新上级部门对象Id
        String newPdeptIdValue = removeableParam.getString(NEW_P_DEPT_ID);

        //等待调动的部门列表（下级部门不显示）
        JSONArray jsonArray = removeableParam.getJSONArray(DELAY_MOVE_DEPT_ID_LIST);

        if (jsonArray.isEmpty())
        {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg("部门对象列表为空");
            return result;
        }

        List<String> delayMoveDeptIdList = JSONArray.parseArray(jsonArray.toJSONString(), String.class);

        //单据操作类型
        String orderType = removeableParam.getString(ORDER_TYPE);

        if(!DeptStuChangeOrderConstants.ODSC_TYPE_MOVE_ID.equals(orderType) && !DeptStuChangeOrderConstants.ODSC_TYPE_CANCEL_ID.equals(orderType))
        {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg("单据类型字段的值应该为[调动 2/注销 3]");
            return result;
        }

        //单据有效时间
        String odscBeg = removeableParam.getString(ODSC_BEG);
        if(StringUtil.isNullOrEmpty(odscBeg))
        {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg("生效日期为空");
            return result;
        }

        Date odscBegDate = null;
        try
        {
            odscBegDate = DateUtils.parseStrToDate(odscBeg, DateUtils.DATE_YYYY_MM_DD);
        }
        catch (Exception e)
        {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg("生效日期格式错误，格式应该为2018-01-01");
            return result;
        }

        if (DeptStuChangeOrderConstants.ODSC_TYPE_CANCEL_ID.equals(orderType))
        {
            //部门注销校验
            result = deptTransactionService.deptDeleteValidateService(delayMoveDeptIdList,odscBegDate);
        }
        else
        {
            if(StringUtil.isNullOrEmpty(newPdeptIdValue))
            {
                result.setRetCode(Result.RECODE_ERROR);
                result.setErrMsg("待调动部门的新上级部门对象Id为空");
                return result;
            }
            //默认走部门调动单
            result = deptTransactionService.deptMoveValidateService(newPdeptIdValue, delayMoveDeptIdList,odscBegDate);
        }

        return result;
    }

    private Result submitOrder(JSONObject jsonObject)
    {
        //返回结果
        Result result = new Result();
        String orderIdValue = jsonObject.getString(EdmSysColumn.ID);
        //提交之前，先要判断该单据是否已经提交过了，如果已经提交过了，不给提交，以防止重复提交
        if(!StringUtil.isNullOrEmpty(orderIdValue) && !StringUtils.isBlank(orderIdValue))
        {
            result = deptTransactionService.checkIsSubmit(orderIdValue);
            if(Result.RECODE_ERROR.equals(result.getRetCode()))
            {
                return result;
            }
        }

        //先保存
        result = deptTransactionService.saveDeptTransactionOrderService(jsonObject);

        //调用流程提交接口
        DeptStuChangeOrderDTO dto = new DeptStuChangeOrderDTO();

        if(Result.RECODE_SUCCESS.equals(result.getRetCode()))
        {
            String orderId = JSONObject.parseObject(JSON.toJSONString(result.getData())).getString("deptStuChangeOrderId");
            String orderNbr = JSONObject.parseObject(JSON.toJSONString(result.getData())).getString("deptStuChangeOrderNbr");
            dto.setId(orderId);
            dto.setOrdeRodeObj(JSONObject.parseObject(JSON.toJSONString(jsonObject)).getString("ordeRodeObj"));
            result = deptTransactionService.submit(dto);

            if(Result.RECODE_SUCCESS.equals(result.getRetCode()))
            {
                JSONObject jo = new JSONObject();
                jo.put("deptStuChangeOrderId",orderId);
                jo.put("deptStuChangeOrderNbr",orderNbr);
                result.setData(jo);
            }
        }
        return result;
    }

}
