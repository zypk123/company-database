package com.huntkey.rx.purchase.provider.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.purchase.common.model.balanceinito.BalanceinitoDTO;
import com.huntkey.rx.purchase.provider.service.ShouldPayOrReceiptOrderService;
import com.huntkey.rx.sceo.method.register.plugin.annotation.MethodRegister;
import com.huntkey.rx.sceo.method.register.plugin.entity.MethodExeFrequency;
import com.huntkey.rx.sceo.method.register.plugin.entity.ProgramCate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *  应收应付初始化单据管理
 * @author yaoss
 */
@RestController
@RequestMapping("/pu/payOrReceipt")
public class ShouldPayOrReceiptOrderController {

    @Autowired
    ShouldPayOrReceiptOrderService shouldPayOrReceiptOrderService;

    /**
     * 保存 应收应付初始化单 单据
     * @param params
     * @return
     * @author yaoss
     */
    @MethodRegister(
            edmClass = "balanceinito",
            methodCate = "采购系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "应收应付初始化单保存方法")
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Result save(@RequestBody BalanceinitoDTO params){
        return shouldPayOrReceiptOrderService.saveOrder(JSONObject.parseObject(JSON.toJSONString(params)));
    }

    /**
     * 提交 应收应付初始化单 单据
     * @param params
     * @return
     * @author yaoss
     */
    @MethodRegister(
            edmClass = "balanceinito",
            methodCate = "采购系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "应收应付初始化单提交方法")
    @RequestMapping(value = "/submit",method = RequestMethod.POST)
    public Result submit(@RequestBody BalanceinitoDTO params){
        return shouldPayOrReceiptOrderService.submitOrder(JSONObject.parseObject(JSON.toJSONString(params)));
    }

    /**
     * 加载 应收应付初始化单 单据
     * @param id
     * @return
     * @author yaoss
     */
    @MethodRegister(
            edmClass = "balanceinito",
            methodCate = "采购系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"id"},
            methodDesc = "应收应付初始化单 load")
    @RequestMapping(value = "/load",method = RequestMethod.GET)
    public Result load(@RequestParam String id){
        return shouldPayOrReceiptOrderService.loadOrder(id);
    }

    /**
     * 流程处 调用 审批方法
     * @param auditSet
     * @return
     * @throws Exception
     */
    @MethodRegister(
            edmClass ="balanceinito",
            methodCate = "采购系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"auditSet"},
            methodDesc = "应收应付初始化单填写审核意见方法")
    @RequestMapping(value = "/audit", method = RequestMethod.POST)
    public Result audit(@RequestBody JSONObject auditSet)throws Exception {
        return shouldPayOrReceiptOrderService.auditOrder(auditSet);
    }

    @MethodRegister(
            edmClass ="balanceinito",
            methodCate = "采购系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"orderInstanceId","handlerType"},
            methodDesc = "应收应付初始化单批准通过方法")
    @RequestMapping(value = "/passOrder", method = RequestMethod.GET)
    public Result approve(@RequestParam(value = "orderInstanceId") String orderInstanceId,
                          @RequestParam(value = "handlerType") String handlerType) throws Exception{
        return shouldPayOrReceiptOrderService.passOrder(orderInstanceId,handlerType);
    }




}
