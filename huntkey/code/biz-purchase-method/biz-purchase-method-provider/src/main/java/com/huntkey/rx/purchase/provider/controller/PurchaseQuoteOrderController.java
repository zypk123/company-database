package com.huntkey.rx.purchase.provider.controller;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.purchase.common.model.puquoteorder.PuqoOrderQueryDTO;
import com.huntkey.rx.purchase.common.model.puquoteorder.PuquGodsQueryDTO;
import com.huntkey.rx.purchase.common.model.puquoteorder.PuquoteOrderDTO;
import com.huntkey.rx.purchase.provider.service.PurchaseQuoteOrderService;
import com.huntkey.rx.sceo.method.register.plugin.annotation.MethodRegister;
import com.huntkey.rx.sceo.method.register.plugin.entity.MethodExeFrequency;
import com.huntkey.rx.sceo.method.register.plugin.entity.ProgramCate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 采购报价单类
 *
 * @author zhoucj
 * @date 2018/1/19
 */
@RestController
@RequestMapping("/pu/puquoteorder")
public class PurchaseQuoteOrderController {

    @Autowired
    PurchaseQuoteOrderService purchaseQuoteOrderService;

    /**
     * 保存 采购报价单 单据
     * @param params
     * @return
     * @author yaoss
     */
    @MethodRegister(
            edmClass = "puquoteorder",
            methodCate = "采购系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "采购报价单保存方法")
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Result save(@RequestBody PuquoteOrderDTO params){
        return purchaseQuoteOrderService.savePuquoteOrder(params);
    }

    /**
     * 提交 采购报价单 单据
     * @param params
     * @return
     * @author yaoss
     */
    @MethodRegister(
            edmClass = "puquoteorder",
            methodCate = "采购系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "采购报价单提交方法")
    @RequestMapping(value = "/submit",method = RequestMethod.POST)
    public Result submit(@RequestBody PuquoteOrderDTO params){
        return purchaseQuoteOrderService.submitPuquoteOrder(params);
    }

    /**
     * 加载 采购报价单 单据 基本信息
     * @param id
     * @param isLoadParks: "0": 不加载园区列表数据 "1": 加载园区列表数据； 默认加载
     * @return
     * @author yaoss
     */
    @MethodRegister(
            edmClass = "puquoteorder",
            methodCate = "采购系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "采购报价单加载方法",
            getReqParamsNameNoPathVariable = {"id", "isLoadParks"}
    )
    @RequestMapping(value = "/load",method = RequestMethod.GET)
    public Result load(@RequestParam String id, @RequestParam(required = false)String isLoadParks){
        final String loadParks="1";
        if(loadParks.equals(isLoadParks)){
            return purchaseQuoteOrderService.loadOrder(id,true);
        }else{
            return purchaseQuoteOrderService.loadOrder(id,false);
        }
    }

    /**
     * 查询 采购报价单
     * @param params
     * @return
     * @author yaoss
     */
    @MethodRegister(
            edmClass = "puquoteorder",
            methodCate = "采购系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "查询 采购报价单")
    @RequestMapping(value = "/query",method = RequestMethod.POST)
    public Result query(@RequestBody PuqoOrderQueryDTO params){
        return purchaseQuoteOrderService.queryPuquOrders(params);
    }

    /**
     * 查询 采购价格管理列表
     * @param params
     * @return
     * @author yaoss
     */
    @MethodRegister(
            edmClass = "puquoteorder",
            methodCate = "采购系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "查询 采购价格管理列表")
    @RequestMapping(value = "/queryPuPrice",method = RequestMethod.POST)
    public Result queryPuPrice(@RequestBody PuquGodsQueryDTO params){
        return purchaseQuoteOrderService.queryPuquPriceByGoods(params);
    }


    /**
     * 流程处 调用 审批方法
     * @param auditSet
     * @return
     * @throws Exception
     */
    @MethodRegister(
            edmClass ="puquoteorder",
            methodCate = "采购系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"auditSet"},
            methodDesc = "采购报价单填写审核意见方法")
    @RequestMapping(value = "/audit", method = RequestMethod.POST)
    public Result audit(@RequestBody JSONObject auditSet)throws Exception {
        return purchaseQuoteOrderService.auditOrder(auditSet);
    }

    @MethodRegister(
            edmClass ="puquoteorder",
            methodCate = "采购系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"orderInstanceId","handlerType"},
            methodDesc = "采购报价单批准通过方法")
    @RequestMapping(value = "/passOrder", method = RequestMethod.GET)
    public Result approve(@RequestParam(value = "orderInstanceId") String orderInstanceId,
                          @RequestParam(value = "handlerType") String handlerType) throws Exception{
        return purchaseQuoteOrderService.passOrder(orderInstanceId,handlerType);
    }



}
