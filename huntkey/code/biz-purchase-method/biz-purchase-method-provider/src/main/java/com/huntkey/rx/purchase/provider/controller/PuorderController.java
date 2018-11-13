package com.huntkey.rx.purchase.provider.controller;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.purchase.common.constants.MsgConstants;
import com.huntkey.rx.purchase.common.constants.PuorderConstants;
import com.huntkey.rx.purchase.common.model.puodorder.PuorderDTO;
import com.huntkey.rx.purchase.provider.service.PuorderService;
import com.huntkey.rx.sceo.method.register.plugin.annotation.MethodRegister;
import com.huntkey.rx.sceo.method.register.plugin.entity.MethodExeFrequency;
import com.huntkey.rx.sceo.method.register.plugin.entity.ProgramCate;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.Min;

/**
 * 采购订单Controller
 *
 * Created by fangyou on 2018年1月18日 0018.
 */
@RestController
@RequestMapping("/pu/puorder")
public class PuorderController {

    @Autowired
    PuorderService puorderService;

    /**
     * 采购订单保存方法
     *
     * @param puorderDTO
     * @return
     */
    @MethodRegister(
            edmClass = PuorderConstants.PUORDER,
            methodCate = "采购系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "采购订单保存方法")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result save(@RequestBody PuorderDTO puorderDTO)
    {
        return puorderService.saveOrderService(puorderDTO , false);
    }

    /**
     * 采购订单加载方法
     *
     * @param id
     * @return
     */
    @MethodRegister(
            edmClass = PuorderConstants.PUORDER,
            methodCate = "采购系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "采购订单加载方法",
            getReqParamsNameNoPathVariable = {"id"})
    @RequestMapping(value = "/load", method = RequestMethod.GET)
    public Result load(@RequestParam(value = "id") @NotEmpty(message = "采购订单ID不能为空") String id)
    {
        return puorderService.loadOrder(id);
    }

    /**
     * 采购订单管理列表查询方法
     *
     * @param ordeNbr  订单编号
     * @param sumoName        供应商名称
     * @param startTime     收货日期：开始时间
     * @param endTime       收货日期：结束时间
     * @param puodStatus    订单状态
     * @param parkId        园区Id
     * @param currId        币别Id
     * @param deptName       采购部门名称
     * @param empName        采购员名称
     * @param pageNum       当前页码
     * @param pageSize      每页最大记录数
     * @return
     */
    @MethodRegister(
            edmClass = PuorderConstants.PUORDER,
            methodCate = "采购系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "采购订单管理列表查询方法-前端界面使用",
            getReqParamsNameNoPathVariable = {"ordeNbr", "sumoName", "startTime", "endTime", "puodStatus", "parkId","currId","deptName","empName", "pageNum", "pageSize"}
    )
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public Result query(@RequestParam(value = "ordeNbr", required = false) String ordeNbr,
                                 @RequestParam(value = "sumoName", required = false) String sumoName,
                                 @RequestParam(value = "startTime", required = false) String startTime,
                                 @RequestParam(value = "endTime", required = false) String endTime,
                                 @RequestParam(value = "puodStatus", required = false) String puodStatus,
                                 @RequestParam(value = "parkId", required = false) String parkId,
                                 @RequestParam(value = "currId", required = false) String currId,
                                 @RequestParam(value = "deptName", required = false) String deptName,
                                 @RequestParam(value = "empName", required = false) String empName,
                                 @RequestParam(required = false, defaultValue = "1") @Min(value = 1, message = MsgConstants.MSG_PU_PAGE_START_MIN) int pageNum,
                                 @RequestParam(required = false, defaultValue = "10") @Min(value = 1, message = MsgConstants.MSG_PU_PAGE_ROWS_MIN) int pageSize)
    {
        return puorderService.queryOrderListService(ordeNbr, sumoName, startTime, endTime, puodStatus, parkId,currId,deptName,empName, pageNum, pageSize);
    }

    /**
     * 采购订单提交方法
     *
     * @param puorderDTO
     * @return
     */
    @MethodRegister(
            edmClass = PuorderConstants.PUORDER,
            methodCate = "采购系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "采购订单提交方法"
    )
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public Result submit(@RequestBody PuorderDTO puorderDTO)
    {
        return puorderService.submitOrder(puorderDTO);
    }

    /**
     * 采购订单删除方法
     *
     * @param id
     * @return
     */
    @MethodRegister(
            edmClass = PuorderConstants.PUORDER,
            methodCate = "采购系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "采购订单删除方法",
            getReqParamsNameNoPathVariable = {"id"})
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Result delete(@RequestParam(value = "id") @NotEmpty(message = "采购订单ID不能为空") String id)
    {
        return puorderService.deleteOrderService(id);
    }

    /**
     * 采购订单批准方法
     * @param orderInstanceId
     * @param handlerType
     * @return
     */
    @MethodRegister(
            edmClass = PuorderConstants.PUORDER,
            methodCate = "采购系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"orderInstanceId","handlerType"},
            methodDesc = "采购订单批准方法"
    )
    @RequestMapping(value = "/approve", method = RequestMethod.POST)
    public Result approve(@RequestParam(value = "orderInstanceId") @NotEmpty(message = "orderInstanceId不能为空") String orderInstanceId ,
                          @RequestParam(value = "handlerType") @NotEmpty(message = "handlerType不能为空") String handlerType)
    {
        return puorderService.approve(orderInstanceId,handlerType);
    }

    /**
     * 采购订单审核方法—前端使用
     * @param jsonObject
     * @return
     */
    @MethodRegister(
            edmClass = PuorderConstants.PUORDER,
            methodCate = "采购系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"auditSet"},
            methodDesc = "采购订单审核方法"
    )
    @RequestMapping(value = "/audit", method = RequestMethod.POST)
    public Result audit(@RequestBody JSONObject jsonObject)
    {
        return puorderService.auditOrder(jsonObject);
    }

    /**
     * 采购订单关闭方法
     * @param puorderDTO
     * @return
     */
    @MethodRegister(
            edmClass = PuorderConstants.PUORDER,
            methodCate = "采购系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "采购订单关闭方法")
    @RequestMapping(value = "/close", method = RequestMethod.POST)
    public Result close(@RequestBody PuorderDTO puorderDTO)
    {
        return puorderService.closeOrder(puorderDTO);
    }


    /**
     * 根据供应商id,园区id,物品编码 模糊查询物品编号接口
     * @param supplierId
     * @param parkId
     * @param godsCode
     * @return
     */
    @MethodRegister(
            edmClass	= PuorderConstants.PUORDER,
            methodCate	= "采购系统方法",
            methodDesc = "根据供应商id,园区id,查询物品编码列表"
    )
    @RequestMapping(value = "/goodsCodeList/{supplierId}/{parkId}/{godsCode}", method = RequestMethod.GET)
    public Result queryGoodsCode(@PathVariable(value = "supplierId") String supplierId,
                                 @PathVariable(value = "parkId") String parkId,
                                 @PathVariable(value = "godsCode") String godsCode) {
        return puorderService.queryGoodsCode( supplierId, parkId, godsCode);
    }

    /**
     * 根据物品编号、供应商、园区、币别 获取采购订单明细
     * @param godsCode     物品编号
     * @param supplierId   供应商
     * @param parkId       园区
     * @param currId       币别
     * @param goodsCodes   物品编号集[,,]
     * @return
     */
    @MethodRegister(
            edmClass	= PuorderConstants.PUORDER,
            methodCate	= "采购系统方法",
            methodDesc = "查询采购订单明细",
            getReqParamsNameNoPathVariable = {"godsCode","supplierId","parkId","currId","goodsCodes"}
    )
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public Result queryOrderDetail( @RequestParam(value = "godsCode") String godsCode,
                                    @RequestParam(value = "supplierId") String supplierId,
                                    @RequestParam(value = "parkId") String parkId,
                                    @RequestParam(value = "currId") String currId,
                                    @RequestParam(value = "goodsCodes") String goodsCodes) {
        return puorderService.getPurchaseOrderDetails(godsCode,supplierId, parkId, currId,goodsCodes);
    }

    /**
     * 根据供应商ID获取币别 税率 结算方式集合
     * @param supplierId
     * @return
     */

    @MethodRegister(
            edmClass = PuorderConstants.PUORDER,
            methodCate = "采购系统方法",
            methodDesc = "根据供应商ID获取币别 税率 结算方式集合"
    )
    @RequestMapping(value = "/{supplierId}/currency",  method = RequestMethod.GET)
    public Result queryBySupplier(@PathVariable(value = "supplierId") String supplierId) {
        return puorderService.queryBySupplier(supplierId);
    }

    /**
     * 根据园区载入对应交货地址列表
     *
     */
    @MethodRegister(
            edmClass	= PuorderConstants.PUORDER,
            methodCate	= "采购系统方法",
            methodDesc = "根据园区Id载入对应交货地址列表",
            getReqParamsNameNoPathVariable = {"parkId"}
    )
    @RequestMapping(value = "/address",method = RequestMethod.GET)
    public Result queryByPark( @RequestParam(value = "parkId") String parkId){
        return puorderService.queryByPark(parkId);
    }

    /**
     * 获取采购法人列表接口
     *
     */
    @MethodRegister(
            edmClass	= PuorderConstants.PUORDER,
            methodCate	= "采购系统方法",
            methodDesc = "获取采购法人列表"
    )
    @RequestMapping(value = "/purchaseLegalPerson",method = RequestMethod.GET)
    public Result getPuorderLeagerPerson(){
        return puorderService.getPuorderLeagerPerson();
    }


}
