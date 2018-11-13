package com.huntkey.rx.hr.provider.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.hr.common.constants.MsgConstants;
import com.huntkey.rx.hr.common.model.*;
import com.huntkey.rx.hr.provider.service.EmpresignApplyService;
import com.huntkey.rx.sceo.method.register.plugin.annotation.MethodRegister;
import com.huntkey.rx.sceo.method.register.plugin.entity.MethodExeFrequency;
import com.huntkey.rx.sceo.method.register.plugin.entity.ProgramCate;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.Min;
import java.text.ParseException;

/**
 * 离职申请单Controller
 *
 * @author zhangyu
 */
@RestController
@RequestMapping("/hr/empresignApply")
public class EmpresignApplyController {

    @Autowired
    private EmpresignApplyService empresignApplyService;

    /**
     * 查询离职申请单列表
     *
     * @param deptId      部门
     * @param type        日期类型
     * @param startTime   开始日期
     * @param endTime     结束日期
     * @param auditStatus 审核状态
     * @param staffInfo   员工对象Id
     * @return
     */
    @MethodRegister(
            edmClass = EmpresignApplyConstants.EDM_EMPRESIGNAPPLY,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "查询离职申请单列表",
            getReqParamsNameNoPathVariable = {"deptId", "type", "startTime", "endTime", "auditStatus", "staffInfo", "pageNum", "pageSize"}
    )
    @RequestMapping(value = "/queryEmpresignApply", method = RequestMethod.GET)
    public Result queryEmpresignApply(@RequestParam(value = "deptId", required = false) String deptId,
                                      @RequestParam(value = "type", required = false) String type,
                                      @RequestParam(value = "startTime", required = false) String startTime,
                                      @RequestParam(value = "endTime", required = false) String endTime,
                                      @RequestParam(value = "auditStatus", required = false) String auditStatus,
                                      @RequestParam(value = "staffInfo", required = false) String staffInfo,
                                      @RequestParam(required = false, defaultValue = "1") @Min(value = 1, message = MsgConstants.MSG_HR_PAGE_START_MIN) int pageNum,
                                      @RequestParam(required = false, defaultValue = "10") @Min(value = 1, message = MsgConstants.MSG_HR_PAGE_ROWS_MIN) int pageSize) throws ParseException
    {
        return empresignApplyService.query(deptId, type, startTime, endTime, auditStatus, staffInfo, pageNum, pageSize);
    }

    /**
     * 离职申请单加载详情方法
     *
     * @param orderIdValue 单据号
     * @return
     */
    @MethodRegister(
            edmClass = EmpresignApplyConstants.EDM_EMPRESIGNAPPLY,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "离职申请单加载详情方法"
    )
    @RequestMapping(value = "/loadEmpresignApply/{orderId}", method = RequestMethod.GET)
    public Result loadEmpresignApply(@PathVariable(value = "orderId") @NotBlank(message = MsgConstants.MSG_HR_NOT_BLANK_OERA_ID) String orderIdValue) throws ParseException
    {
        return empresignApplyService.load(orderIdValue);
    }


    /**
     * 离职申请单保存方法（含校验）
     *
     * @param empresignApplyDTO
     * @return
     */
    @MethodRegister(
            edmClass = EmpresignApplyConstants.EDM_EMPRESIGNAPPLY,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "离职申请单保存方法"
    )
    @RequestMapping(value = "/saveEmpresignApply", method = RequestMethod.POST)
    public Result saveEmpresignApply(@RequestBody JSONObject empresignApplyDTO)
    {
        return empresignApplyService.save(empresignApplyDTO);
    }


    /**
     * 离职申请单提交方法（含校验）
     *
     * @param jsonObject
     * @return
     */
    @MethodRegister(
            edmClass = EmpresignApplyConstants.EDM_EMPRESIGNAPPLY,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "离职申请单提交方法"
    )
    @RequestMapping(value = "/submitEmpresignApply", method = RequestMethod.POST)
    public Result submitEmpresignApply(@RequestBody JSONObject jsonObject)
    {
        //走保存流程，如果存在单据Id，则修改，没有则保存
        Result result = empresignApplyService.save(jsonObject);
        EmpresignApplyDTO dto = new EmpresignApplyDTO();

        if (Result.RECODE_SUCCESS.equals(result.getRetCode()))
        {
            dto.setOrdeNbr(JSONObject.parseObject(JSON.toJSONString(result.getData())).getString("orderNbr"));
            dto.setId(JSONObject.parseObject(JSON.toJSONString(result.getData())).getString("orderId"));
            dto.setOrdeRodeObj((String) jsonObject.get("ordeRodeObj"));
            result = empresignApplyService.submit(dto);
        }
        else
        {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg("提交单据前执行保存失败，无法继续提交单据");
        }
        return result;
    }

    /**
     * 离职申请单批准通过方法
     * @param orderInstanceId
     * @param handlerType
     * @return
     */
    @MethodRegister(
            edmClass = EmpresignApplyConstants.EDM_EMPRESIGNAPPLY,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"orderInstanceId","handlerType"},
            methodDesc = "离职申请单批准通过方法"
    )
    @RequestMapping(value = "/passEmpresignApply", method = RequestMethod.GET)
    public Result approve(@RequestParam(value = "orderInstanceId") String orderInstanceId,@RequestParam(value = "handlerType") String handlerType)
    {
        return empresignApplyService.approve(orderInstanceId,handlerType);
    }

    /**
     * 离职申请单前端审核方法
     * @param jsonObject
     * @return
     */
    @MethodRegister(
            edmClass = EmpresignApplyConstants.EDM_EMPRESIGNAPPLY,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"auditSet"},
            methodDesc = "离职申请单填写审核意见方法"
    )
    @RequestMapping(value = "/audit", method = RequestMethod.POST)
    public Result audit(@RequestBody JSONObject jsonObject)
    {
        return empresignApplyService.audit(jsonObject);
    }

}
