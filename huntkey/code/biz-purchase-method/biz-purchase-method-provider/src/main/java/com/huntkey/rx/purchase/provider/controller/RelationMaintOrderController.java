package com.huntkey.rx.purchase.provider.controller;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.purchase.common.constants.RelationConstants;
import com.huntkey.rx.purchase.common.constants.RelationMaintOrderConstants;
import com.huntkey.rx.purchase.common.constants.SupplierMaintOrderConstants;
import com.huntkey.rx.purchase.common.model.relation.RelationMaintOrderDTO;
import com.huntkey.rx.purchase.common.model.supplier.SupplierMaintOrderDTO;
import com.huntkey.rx.purchase.provider.service.RelationMaintOrderService;
import com.huntkey.rx.purchase.provider.service.RelationService;
import com.huntkey.rx.sceo.method.register.plugin.annotation.MethodRegister;
import com.huntkey.rx.sceo.method.register.plugin.entity.MethodExeFrequency;
import com.huntkey.rx.sceo.method.register.plugin.entity.ProgramCate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author by xuyf on 2018/1/22 0022.
 */
@RestController
@RequestMapping("/pu/relationMaintOrder")
public class RelationMaintOrderController {
    private static final Logger logger = LoggerFactory.getLogger(RelationMaintOrderController.class);

    @Autowired
    private RelationMaintOrderService relationMaintOrderService;

    /**
     * 伙伴维护单载入方法
     * @param orderId
     * @return
     */
    @MethodRegister(
            edmClass = RelationMaintOrderConstants.RELATIONMAINTORDER,
            methodCate = "采购系统方法",
            getReqParamsNameNoPathVariable = {"orderId"},
            methodDesc = "伙伴维护单载入方法"
    )
    @RequestMapping(value = "/load", method = RequestMethod.GET)
    public Result load(@RequestParam(value = "orderId") String orderId){
        Result result = new Result();
        RelationMaintOrderDTO relationMaintOrderDTO = relationMaintOrderService.getRelationMaintOrderByOrderId(orderId);
        result.setData(relationMaintOrderDTO);
        return result;
    }

    /**
     * 伙伴维护单保存方法
     * @param relationMaintOrderDTO
     * @return
     */
    @MethodRegister(
            edmClass = RelationMaintOrderConstants.RELATIONMAINTORDER,
            methodCate = "采购系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "伙伴维护单保存方法"
    )
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result save(@RequestBody RelationMaintOrderDTO relationMaintOrderDTO){
        Result result = new Result();
        JSONObject obj = relationMaintOrderService.saveRelationMaintOrder(relationMaintOrderDTO);
        result.setData(obj);
        return result;
    }


    /**
     * 伙伴维护单提交方法
     * @param relationMaintOrderDTO
     * @return
     */
    @MethodRegister(
            edmClass = RelationMaintOrderConstants.RELATIONMAINTORDER,
            methodCate = "采购系统方法",
            methodDesc = "伙伴维护单提交方法"
    )
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public Result submit(@RequestBody RelationMaintOrderDTO relationMaintOrderDTO) {
        logger.info("伙伴维护单 提交接口服务启动....");
        Result result = new Result();
        JSONObject obj = relationMaintOrderService.submitRelationMaintOrder(relationMaintOrderDTO);
        result.setData(obj);
        return result;
    }

    /**
     * 伙伴维护单唯一性校验方法
     * @param exceptRelaId
     * @param exceptRemoId
     * @param fieldName
     * @param fieldValue
     * @return
     */
    @MethodRegister(
            edmClass = RelationMaintOrderConstants.RELATIONMAINTORDER,
            methodCate = "采购系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "伙伴维护单唯一性校验方法",
            getReqParamsNameNoPathVariable = {"exceptRelaId", "exceptRemoId", "fieldName", "fieldValue"}
    )
    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public Result checkRela(@RequestParam(value = "exceptRelaId") String exceptRelaId,
                            @RequestParam(value = "exceptRemoId") String exceptRemoId,
                            @RequestParam(value = "fieldName") String fieldName,
                            @RequestParam(value = "fieldValue") String fieldValue){
        Result result = new Result();
        JSONObject resultObj = null;
        if (fieldName.equals(RelationMaintOrderConstants.REMO_USCC)){
            resultObj  = relationMaintOrderService.checkRelationMaintOrderUnique(exceptRelaId, exceptRemoId, fieldValue, null, null);
        } else if (fieldName.equals(RelationMaintOrderConstants.REMO_CODE)){
            resultObj  = relationMaintOrderService.checkRelationMaintOrderUnique(exceptRelaId, exceptRemoId, null, fieldValue, null);
        } else if (fieldName.equals(RelationMaintOrderConstants.REMO_SHORT_NAME)){
            resultObj  = relationMaintOrderService.checkRelationMaintOrderUnique(exceptRelaId, exceptRemoId, null, null, fieldValue);
        } else {
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            result.setErrMsg("参数fieldName无效");
        }
        result.setData(resultObj);
        return result;
    }

    /**
     * 伙伴维护单 删除
     *
     * @param id
     * @return
     */
    @MethodRegister(
            edmClass = RelationMaintOrderConstants.RELATIONMAINTORDER,
            methodCate = "采购系统方法",
            methodDesc = "伙伴维护单删除方法"
    )
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        logger.info("伙伴维护单 删除接口服务启动....");
        Result result = new Result();
        int data = relationMaintOrderService.deleteRelation(id);
        result.setData(data);
        return result;
    }

    /**
     * 伙伴维护单填写审核意见方法
     * @param auditSet
     * @return
     */
    @MethodRegister(
            edmClass = RelationMaintOrderConstants.RELATIONMAINTORDER,
            methodCate = "采购系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "伙伴维护单填写审核意见方法")
    @RequestMapping(value = "/audit", method = RequestMethod.POST)
    public Result audit(@RequestBody JSONObject auditSet) {
        return relationMaintOrderService.audit(auditSet);
    }

    /**
     * 伙伴维护单 批准通过
     *
     * @param orderInstanceId
     * @param handlerType
     * @return
     */
    @MethodRegister(
            edmClass = RelationMaintOrderConstants.RELATIONMAINTORDER,
            methodCate = "采购系统方法",
            getReqParamsNameNoPathVariable = {"orderInstanceId", "handlerType"},
            methodDesc = "伙伴维护单批准通过方法"
    )
    @RequestMapping(value = "/approve", method = RequestMethod.GET)
    public Result approve(@RequestParam(value = "orderInstanceId") String orderInstanceId, @RequestParam(value = "handlerType") String handlerType) {
        logger.info("伙伴维护单 伙伴维护单批准通过方法启动....");
        Result result = new Result();
        relationMaintOrderService.approve(orderInstanceId, handlerType);
        return result;
    }

}
