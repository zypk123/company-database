package com.huntkey.rx.purchase.provider.controller;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.purchase.common.constants.CustomMaintOrderConstants;
import com.huntkey.rx.purchase.common.constants.MsgConstants;
import com.huntkey.rx.purchase.common.constants.SupplierMaintOrderConstants;
import com.huntkey.rx.purchase.common.model.supplier.SupplierMaintOrderDTO;
import com.huntkey.rx.purchase.provider.service.SupplierMaintOrderService;
import com.huntkey.rx.sceo.method.register.plugin.annotation.MethodRegister;
import com.huntkey.rx.sceo.method.register.plugin.entity.MethodExeFrequency;
import com.huntkey.rx.sceo.method.register.plugin.entity.ProgramCate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

/**
 * 供应商维护单Controller
 *
 * @author zhangyu
 * @create 2018-01-02 17:47
 **/
@RestController
@RequestMapping("/pu/supplierMaintOrder")
public class SupplierMaintOrderController {

    Logger logger = LoggerFactory.getLogger(SupplierMaintOrderController.class);

    @Autowired
    private SupplierMaintOrderService supplierMaintOrderService;

    /**
     * 供应商列表查询
     *
     * @param relaCode  伙伴代码
     * @param relaShortName 伙伴简称
     * @param relaStatus    状态
     * @param pageNum       当前页码
     * @param pageSize      每页最大记录数
     * @return
     */
    @MethodRegister(
            edmClass = SupplierMaintOrderConstants.SUPPLIERMAINTORDER,
            methodCate = "采购系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "供应商列表查询",
            getReqParamsNameNoPathVariable = {"relaCode", "relaShortName", "relaStatus", "pageNum", "pageSize"}
    )
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public Result query(@RequestParam(value = "relaCode", required = false) String relaCode,
                        @RequestParam(value = "relaShortName", required = false) String relaShortName,
                        @RequestParam(value = "relaStatus", required = false) String relaStatus,
                        @RequestParam(required = false, defaultValue = "1") @Min(value = 1, message = MsgConstants.MSG_PU_PAGE_START_MIN) int pageNum,
                        @RequestParam(required = false, defaultValue = "10") @Min(value = 1, message = MsgConstants.MSG_PU_PAGE_ROWS_MIN) int pageSize)
    {
        return supplierMaintOrderService.query(relaCode, relaShortName, relaStatus, pageNum, pageSize);
    }

    /**
     * 供应商维护单 加载
     *
     * @param id
     * @return
     */
    @MethodRegister(
            edmClass = SupplierMaintOrderConstants.SUPPLIERMAINTORDER,
            methodCate = "采购系统方法",
            methodDesc = "供应商维护单加载方法"
    )
    @RequestMapping(value = "/load/{id}", method = RequestMethod.GET)
    public Result load(@PathVariable String id) {
        logger.info("供应商维护单 加载接口服务启动....");
        return supplierMaintOrderService.load(id);
    }

    /**
     * 供应商维护单 保存
     *
     * @param supplierMaintOrderDTO
     * @return
     */
    @MethodRegister(
            edmClass = SupplierMaintOrderConstants.SUPPLIERMAINTORDER,
            methodCate = "采购系统方法",
            methodDesc = "供应商维护单保存方法"
    )
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result save(@RequestBody SupplierMaintOrderDTO supplierMaintOrderDTO) {
        logger.info("供应商维护单 保存接口服务启动....");
        return supplierMaintOrderService.save(supplierMaintOrderDTO);
    }

    /**
     * 供应商维护单 删除
     *
     * @param id
     * @return
     */
    @MethodRegister(
            edmClass = SupplierMaintOrderConstants.SUPPLIERMAINTORDER,
            methodCate = "采购系统方法",
            methodDesc = "供应商维护单删除方法"
    )
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        logger.info("供应商维护单 删除接口服务启动....");
        return supplierMaintOrderService.delete(id);
    }

    /**
     * 供应商维护单 提交
     *
     * @param supplierMaintOrderDTO
     * @return
     */
    @MethodRegister(
            edmClass = SupplierMaintOrderConstants.SUPPLIERMAINTORDER,
            methodCate = "采购系统方法",
            methodDesc = "供应商维护单提交方法"
    )
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public Result submit(@RequestBody SupplierMaintOrderDTO supplierMaintOrderDTO) {
        logger.info("供应商维护单 提交接口服务启动....");
        return supplierMaintOrderService.submit(supplierMaintOrderDTO);
    }

    /**
     * 供应商维护单 批准通过
     *
     * @param orderInstanceId
     * @param handlerType
     * @return
     */
    @MethodRegister(
            edmClass = SupplierMaintOrderConstants.SUPPLIERMAINTORDER,
            methodCate = "采购系统方法",
            getReqParamsNameNoPathVariable = {"orderInstanceId", "handlerType"},
            methodDesc = "供应商维护单批准通过方法"
    )
    @RequestMapping(value = "/approve", method = RequestMethod.GET)
    public Result approve(@RequestParam(value = "orderInstanceId") String orderInstanceId, @RequestParam(value = "handlerType") String handlerType) {
        return supplierMaintOrderService.approve(orderInstanceId, handlerType);
    }

    /**
     * 供应商维护单填写审核意见方法
     *
     * @param auditSet
     * @return
     */
    @MethodRegister(
            edmClass = SupplierMaintOrderConstants.SUPPLIERMAINTORDER,
            methodCate = "采购系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "供应商维护单填写审核意见方法")
    @RequestMapping(value = "/audit", method = RequestMethod.POST)
    public Result audit(@RequestBody JSONObject auditSet) {
        return supplierMaintOrderService.audit(auditSet);
    }

    /**
     * 供应商维护单 唯一性校验
     *
     * @param checkField
     * @param value
     * @param relaId
     * @param supplierMaintOrderId
     * @return
     */
    @MethodRegister(
            edmClass = SupplierMaintOrderConstants.SUPPLIERMAINTORDER,
            methodCate = "采购系统方法",
            getReqParamsNameNoPathVariable = {"checkField", "value", "relaId", "supplierMaintOrderId"},
            methodDesc = "供应商维护单唯一性校验方法"
    )
    @RequestMapping(value = "/checkSupplierUnique", method = RequestMethod.GET)
    public Result checkSupplierUnique(@RequestParam(value = "checkField") String checkField, @RequestParam(value = "value") String value,
                                      @RequestParam(value = "relaId", required = false) String relaId,
                                      @RequestParam(value = "supplierMaintOrderId", required = false) String supplierMaintOrderId) {
        return supplierMaintOrderService.checkSupplierUnique(checkField, value, relaId, supplierMaintOrderId);
    }

    /**
     * 根据伙伴编码查询供应商维护单方法
     *
     * @param sumoCode
     * @return
     */
    @MethodRegister(
            edmClass = SupplierMaintOrderConstants.SUPPLIERMAINTORDER,
            methodCate = "采购系统方法",
            methodDesc = "根据伙伴编码查询供应商维护单方法"
    )
    @RequestMapping(value = "/getSupplierMaintOrderByCode/{sumoCode}", method = RequestMethod.GET)
    public Result getSupplierMaintOrderByCode(@PathVariable String sumoCode) {
        return supplierMaintOrderService.getSupplierMaintOrderByCode(sumoCode);
    }

    /**
     * 根据伙伴编码查询伙伴类方法
     *
     * @param sumoCode
     * @return
     */
    @MethodRegister(
            edmClass = SupplierMaintOrderConstants.SUPPLIERMAINTORDER,
            methodCate = "采购系统方法",
            methodDesc = "根据伙伴编码查询伙伴类方法"
    )
    @RequestMapping(value = "/getRelationByCode/{sumoCode}", method = RequestMethod.GET)
    public Result getRelationByCode(@PathVariable String sumoCode) {
        return supplierMaintOrderService.getRelationByCode(sumoCode);
    }

}
