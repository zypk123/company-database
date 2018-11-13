package com.huntkey.rx.purchase.provider.controller;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.purchase.common.constants.*;
import com.huntkey.rx.purchase.common.model.goodsmaintorder.GoodsMaintOrderDTO;
import com.huntkey.rx.purchase.provider.service.GoodsMaintOrderService;
import com.huntkey.rx.sceo.method.register.plugin.annotation.MethodRegister;
import com.huntkey.rx.sceo.method.register.plugin.entity.MethodExeFrequency;
import com.huntkey.rx.sceo.method.register.plugin.entity.ProgramCate;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author  liangh on 2017/12/27 0027.
 */
@RestController
@Validated
@RequestMapping("/pu/goodsMaintOrder")
public class GoodsMaintOrderController {

    private static Logger logger = LoggerFactory.getLogger(GoodsMaintOrderController.class);

    @Autowired
    private GoodsMaintOrderService goodsMaintOrderService;

    /**
     * 查询物品维护单方法
     * @Param id
     */
    @MethodRegister(
            edmClass	= GoodsMaintOrderConstants.GOODSMAINTORDER,
            methodCate	= "采购系统方法",
            methodDesc = "物品维护单加载方法",
            getReqParamsNameNoPathVariable = {"id"}
    )
    @RequestMapping(value = "/load", method = RequestMethod.GET)
    Result load(@RequestParam(value = "id") @NotEmpty(message = "物品特征类ID不能为空") String id) {
        return goodsMaintOrderService.load(id);
    }
    /**
     * 新增、更新物品维护单方法
     * 注意更新物品维护单时，存old数据
     * @Param
     */
    @MethodRegister(
            edmClass = GoodsMaintOrderConstants.GOODSMAINTORDER,
            methodCate = "采购系统方法",
            methodDesc = "物品维护单保存方法"
    )
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    Result save(@RequestBody GoodsMaintOrderDTO goodsMaintOrderDTO) {
        return goodsMaintOrderService.saveGoodsMaintOrder(goodsMaintOrderDTO);
    }

    @RequestMapping(value = "/saveGoods", method = RequestMethod.POST)
    Result saveGoods(@RequestParam(value = "id") String id) {
        goodsMaintOrderService.saveGoods(id);
        return new Result();
    }

    /**
     * 删除物品维护单方法
     * 注意这个方法只是删除维护单，不是删除物品信息
     * @Param
     */
    @MethodRegister(
            edmClass = GoodsMaintOrderConstants.GOODSMAINTORDER,
            methodCate = "采购系统方法",
            methodDesc = "物品维护单删除方法"
    )
    @RequestMapping(value="/delete", method = RequestMethod.DELETE)
    Result delete(@RequestBody @NotEmpty(message = "物品类ID不能为空") String id) {
        return goodsMaintOrderService.deleteGoodsMaintOrder(id);
    }

    /**
     * 提交物品维护单方法
     * @Param
     */
    @MethodRegister(
            edmClass = GoodsMaintOrderConstants.GOODSMAINTORDER,
            methodCate = "采购系统方法",
            methodDesc = "物品维护单提交方法"
    )
    @RequestMapping(value = "/submit", method = RequestMethod.PUT)
    Result submit(@RequestBody GoodsMaintOrderDTO goodsMaintOrderDTO) {
        return goodsMaintOrderService.submitGoodsMaintOrder(goodsMaintOrderDTO);
    }

    /**
     * 物品维护单新增、修改批准通过方法
     * @Param
     */
    @MethodRegister(
            edmClass = GoodsMaintOrderConstants.GOODSMAINTORDER,
            methodCate = "采购系统方法",
            getReqParamsNameNoPathVariable = {"orderInstanceId","handlerType"},
            methodDesc = "物品维护单批准方法"
    )
    @RequestMapping(value = "/approve", method = RequestMethod.GET)
    public Result approve(@RequestParam(value = "orderInstanceId") String orderInstanceId,
                                      @RequestParam(value = "handlerType") String handlerType) {
        return goodsMaintOrderService.approveGoodsMaintOrder(orderInstanceId, handlerType);
    }

    /**
     * 伙伴维护单填写审核意见方法
     * @param auditSet
     * @return
     */
    @MethodRegister(
            edmClass = GoodsMaintOrderConstants.GOODSMAINTORDER,
            methodCate = "采购系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "物品维护单填写审核意见方法")
    @RequestMapping(value = "/audit", method = RequestMethod.POST)
    public Result audit(@RequestBody JSONObject auditSet) {
        return goodsMaintOrderService.audit(auditSet);
    }

    /**
     * 查物品表和物品维护单表，物品编码是否有重复
     * 如果有，则返回状态码为0,给出错误信息
     * 如果没有，则返回状态码为1
     * @param godsCode 物品编码godsCode
     */
    @MethodRegister(
            edmClass = GoodsMaintOrderConstants.GOODSMAINTORDER,
            methodCate = "采购系统方法",
            getReqParamsNameNoPathVariable = {"godsCode"},
            methodDesc = "物品编码验证方法"
    )
    @RequestMapping(value = "/check", method = RequestMethod.GET)
    Result check(@RequestParam(value = "godsCode") @NotEmpty(message = "物品编码不能为空") String godsCode) {
        return goodsMaintOrderService.check(godsCode);
    }

}
