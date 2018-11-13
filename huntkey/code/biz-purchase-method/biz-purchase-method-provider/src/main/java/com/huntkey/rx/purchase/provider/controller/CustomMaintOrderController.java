package com.huntkey.rx.purchase.provider.controller;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.purchase.common.constants.CustomMaintOrderConstants;
import com.huntkey.rx.purchase.common.constants.MsgConstants;
import com.huntkey.rx.purchase.common.model.custom.CustomMaintOrderDTO;
import com.huntkey.rx.purchase.provider.service.CustomMaintOrderService;
import com.huntkey.rx.sceo.method.register.plugin.annotation.MethodRegister;
import com.huntkey.rx.sceo.method.register.plugin.entity.MethodExeFrequency;
import com.huntkey.rx.sceo.method.register.plugin.entity.ProgramCate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.Min;

/**
 * 客户维护单Controller
 *
 * @author zhangyu
 * @create 2018-01-02 17:47
 **/
@RestController
@RequestMapping("/pu/customMaintOrder")
@Api("客户维护单相关API")
public class CustomMaintOrderController {

    Logger logger = LoggerFactory.getLogger(CustomMaintOrderController.class);

    @Autowired
    private CustomMaintOrderService customMaintOrderService;

    /**
     * 客户列表查询
     *
     * @param relaCode  伙伴代码
     * @param relaShortName 伙伴简称
     * @param relaStatus    状态
     * @param pageNum       当前页码
     * @param pageSize      每页最大记录数
     * @return
     */
    @MethodRegister(
            edmClass = CustomMaintOrderConstants.CUSTOMMAINTORDER,
            methodCate = "采购系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "客户列表查询",
            getReqParamsNameNoPathVariable = {"relaCode", "sumoShortName", "relaStatus", "pageNum", "pageSize"}
    )
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public Result query(@RequestParam(value = "relaCode", required = false) String relaCode,
                        @RequestParam(value = "relaShortName", required = false) String relaShortName,
                        @RequestParam(value = "relaStatus", required = false) String relaStatus,
                        @RequestParam(required = false, defaultValue = "1") @Min(value = 1, message = MsgConstants.MSG_PU_PAGE_START_MIN) int pageNum,
                        @RequestParam(required = false, defaultValue = "10") @Min(value = 1, message = MsgConstants.MSG_PU_PAGE_ROWS_MIN) int pageSize)
    {
        return customMaintOrderService.query(relaCode, relaShortName, relaStatus, pageNum, pageSize);
    }

    /**
     * 客户维护单 加载
     *
     * @param id
     * @return
     */
    @MethodRegister(
            edmClass = CustomMaintOrderConstants.CUSTOMMAINTORDER,
            methodCate = "采购系统方法",
            methodDesc = "客户维护单加载方法"
    )
    @RequestMapping(value = "/load/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "客户维护单加载方法", notes = "查询客户维护单单条记录")
    @ApiImplicitParam(name = "id", value = "客户维护单ID", paramType = "path", required = true, dataType = "String")
    public Result load(@PathVariable String id) {
        logger.info("客户维护单 加载接口服务启动....");
        return customMaintOrderService.load(id);
    }

    /**
     * 客户维护单 保存
     *
     * @param customMaintOrderDTO
     * @return
     */
    @MethodRegister(
            edmClass = CustomMaintOrderConstants.CUSTOMMAINTORDER,
            methodCate = "采购系统方法",
            methodDesc = "客户维护单保存方法"
    )
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result save(@RequestBody CustomMaintOrderDTO customMaintOrderDTO) {
        logger.info("客户维护单 保存接口服务启动....");
        return customMaintOrderService.save(customMaintOrderDTO);
    }

    /**
     * 客户维护单 删除
     *
     * @param id
     * @return
     */
    @MethodRegister(
            edmClass = CustomMaintOrderConstants.CUSTOMMAINTORDER,
            methodCate = "采购系统方法",
            methodDesc = "客户维护单删除方法"
    )
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        logger.info("客户维护单 删除接口服务启动....");
        return customMaintOrderService.delete(id);
    }

    /**
     * 客户维护单 提交
     *
     * @param customMaintOrderDTO
     * @return
     */
    @MethodRegister(
            edmClass = CustomMaintOrderConstants.CUSTOMMAINTORDER,
            methodCate = "采购系统方法",
            methodDesc = "客户维护单提交方法"
    )
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public Result submit(@RequestBody CustomMaintOrderDTO customMaintOrderDTO) {
        logger.info("客户维护单 提交接口服务启动....");
        return customMaintOrderService.submit(customMaintOrderDTO);
    }


    /**
     * 客户维护单 批准通过
     *
     * @param orderInstanceId
     * @param handlerType
     * @return
     */
    @MethodRegister(
            edmClass = CustomMaintOrderConstants.CUSTOMMAINTORDER,
            methodCate = "采购系统方法",
            getReqParamsNameNoPathVariable = {"orderInstanceId", "handlerType"},
            methodDesc = "客户维护单批准通过方法"
    )
    @RequestMapping(value = "/approve", method = RequestMethod.GET)
    public Result approve(@RequestParam(value = "orderInstanceId") String orderInstanceId, @RequestParam(value = "handlerType") String handlerType) {
        return customMaintOrderService.approve(orderInstanceId, handlerType);
    }

    /**
     * 客户维护单填写审核意见方法
     *
     * @param auditSet
     * @return
     */
    @MethodRegister(
            edmClass = CustomMaintOrderConstants.CUSTOMMAINTORDER,
            methodCate = "采购系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "客户维护单填写审核意见方法")
    @RequestMapping(value = "/audit", method = RequestMethod.POST)
    public Result audit(@RequestBody JSONObject auditSet) {
        return customMaintOrderService.audit(auditSet);
    }

    /**
     * 客户维护单 唯一性校验
     *
     * @param checkField
     * @param value
     * @param relaId
     * @param customMaintOrderId
     * @return
     */
    @MethodRegister(
            edmClass = CustomMaintOrderConstants.CUSTOMMAINTORDER,
            methodCate = "采购系统方法",
            getReqParamsNameNoPathVariable = {"checkField", "value", "relaId", "customMaintOrderId"},
            methodDesc = "客户维护单唯一性校验方法"
    )
    @RequestMapping(value = "/checkCustomUnique", method = RequestMethod.GET)
    public Result checkCustomUnique(@RequestParam(value = "checkField") String checkField, @RequestParam(value = "value") String value,
                                    @RequestParam(value = "relaId", required = false) String relaId,
                                    @RequestParam(value = "customMaintOrderId", required = false) String customMaintOrderId) {
        return customMaintOrderService.checkCustomUnique(checkField, value, relaId, customMaintOrderId);
    }

    /**
     * 根据伙伴编码查询客户维护单方法
     *
     * @param cumoCode
     * @return
     */
    @MethodRegister(
            edmClass = CustomMaintOrderConstants.CUSTOMMAINTORDER,
            methodCate = "采购系统方法",
            methodDesc = "根据伙伴编码查询客户维护单方法"
    )
    @RequestMapping(value = "/getCustomMaintOrderByCode/{cumoCode}", method = RequestMethod.GET)
    public Result getCustomMaintOrderByCode(@PathVariable String cumoCode) {
        return customMaintOrderService.getCustomMaintOrderByCode(cumoCode);
    }

    /**
     * 根据伙伴编码查询伙伴类方法
     *
     * @param cumoCode
     * @return
     */
    @MethodRegister(
            edmClass = CustomMaintOrderConstants.CUSTOMMAINTORDER,
            methodCate = "采购系统方法",
            methodDesc = "根据伙伴编码查询伙伴类方法"
    )
    @RequestMapping(value = "/getRelationByCode/{cumoCode}", method = RequestMethod.GET)
    public Result getRelationByCode(@PathVariable String cumoCode) {
        return customMaintOrderService.getRelationByCode(cumoCode);
    }

}
