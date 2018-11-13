package com.huntkey.rx.purchase.provider.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.purchase.common.model.puquoteorder.PuqoOrderQueryDTO;
import com.huntkey.rx.purchase.common.model.puquoteorder.PuquGodsQueryDTO;
import com.huntkey.rx.purchase.common.model.puquoteorder.PuquoteOrderDTO;
import com.huntkey.rx.purchase.provider.service.PuquTestService;
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
// @RestController
// @RequestMapping("/pu/test/puquoteorder")
public class PurchaseQuoteOrderTestController {

    @Autowired
    PuquTestService puquTestService;

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
    @RequestMapping(value = "/saveTest",method = RequestMethod.POST)
    public Result save(@RequestBody PuquoteOrderDTO params){
        return puquTestService.saveOrder(JSONObject.parseObject(JSON.toJSONString(params)));
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
    @RequestMapping(value = "/submitTest",method = RequestMethod.POST)
    public Result submit(@RequestBody PuquoteOrderDTO params){
        return puquTestService.submitOrder(JSONObject.parseObject(JSON.toJSONString(params)));
    }





}
