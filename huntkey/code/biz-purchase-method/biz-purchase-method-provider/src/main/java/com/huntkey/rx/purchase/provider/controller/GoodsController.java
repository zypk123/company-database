package com.huntkey.rx.purchase.provider.controller;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.purchase.common.constants.GoodsConstants;
import com.huntkey.rx.purchase.common.constants.MsgConstants;
import com.huntkey.rx.purchase.provider.service.GoodsService;
import com.huntkey.rx.sceo.method.register.plugin.annotation.MethodRegister;
import com.huntkey.rx.sceo.method.register.plugin.entity.MethodExeFrequency;
import com.huntkey.rx.sceo.method.register.plugin.entity.ProgramCate;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Min;

/**
 * @author  liangh on 2017/12/27 0027.
 */
@RestController
@Validated
@RequestMapping("/pu/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 查询物品列表接口
     * @param godsCode 物品编号
     * @param godsName 物品名称
     * @param godsCodeName 物品编码名称
     * @param godsPmCode PM码
     * @param godsStatus 状态
     * @param godsPark 园区
     * @param godsClass 物品分类
     * @param godsUnit 单位
     * @param godsModel 规格型号
     * @param godsSupplier 供应商
     * @param pageNum 开始页
     * @param pageSize 每页大小
     */
    @MethodRegister(
            edmClass	= GoodsConstants.GOODS,
            methodCate	= "采购系统方法",
            methodDesc = "查询物品列表方法",
            getReqParamsNameNoPathVariable = {"godsCode","godsName","godsCodeName","godsPmCode","godsStatus","godsPark","godsClass","godsUnit","godsModel","godsSupplier","pageNum","pageSize"}
    )
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    Result query(@RequestParam(value = "godsCode", required = false) String godsCode,
                 @RequestParam(value = "godsName", required = false) String godsName,
                 @RequestParam(value = "godsCodeName", required = false) String godsCodeName,
                 @RequestParam(value = "godsPmCode", required = false) String godsPmCode,
                 @RequestParam(value = "godsStatus", required = false) String godsStatus,
                 @RequestParam(value = "godsPark", required = false) String godsPark,
                 @RequestParam(value = "godsClass", required = false) String godsClass,
                 @RequestParam(value = "godsUnit", required = false) String godsUnit,
                 @RequestParam(value = "godsModel", required = false) String godsModel,
                 @RequestParam(value = "godsSupplier", required = false) String godsSupplier,
                 @RequestParam(value = "startPage", required = false,defaultValue = "1") @Min(value = 1, message = MsgConstants.MSG_PU_PAGE_START_MIN) int pageNum,
                 @RequestParam(value = "rows", required = false,defaultValue = "10") @Min(value = 1, message = MsgConstants.MSG_PU_PAGE_ROWS_MIN) int pageSize) {
        return goodsService.query(godsCode,godsName,godsCodeName,godsPmCode,godsStatus,godsPark,godsClass,godsUnit,godsModel,godsSupplier,pageNum,pageSize);
    }

    /**
     * 根据物品类id加载物品信息接口
     * @param id 物品id
     */
    @MethodRegister(
            edmClass	= GoodsConstants.GOODS,
            methodCate	= "采购系统方法",
            methodDesc = "加载物品信息方法",
            getReqParamsNameNoPathVariable = {"id"}
    )
    @RequestMapping(value = "/load", method = RequestMethod.GET)
    Result load(@RequestParam(value = "id") @NotEmpty(message = "物品类ID不能为空") String id) {
        return goodsService.load(id);
    }

    /**
     * @ 根据物品id查询所有的园区，如果物品id为空，则查询所有园区信息
     *   否则根据物品id查询该物品下的园区信息，设置状态位，有则设置为true，否则设置为false
     * @param id 物品id
     */
    @MethodRegister(
            edmClass	= GoodsConstants.GOODS,
            methodCate	= "采购系统方法",
            methodDesc = "加载园区信息方法",
            getReqParamsNameNoPathVariable = {"id"}
    )
    @RequestMapping(value = "/loadParkList", method = RequestMethod.GET)
    Result loadParkList(@RequestParam(value = "id", required = false, defaultValue = "") String id) {
        return goodsService.loadParkList(id);
    }

    /**
     * 查询单位树
     *
     * @param
     * @return
     */
    @MethodRegister(
            edmClass	= GoodsConstants.GOODS,
            methodCate	= "采购系统方法",
            methodDesc = "加载园区信息方法",
            getReqParamsNameNoPathVariable = {"id"}
    )
    @RequestMapping(value = "/queryUnitTree", method = RequestMethod.GET)
    Result queryUnitTree() {
        return goodsService.queryUnitTree();
    }

    /**
     * 附件下载
     *
     * @param id
     * @param response
     * @return
     */
    @MethodRegister(
            edmClass = GoodsConstants.GOODS,
            methodCate = "采购系统方法",
            getReqParamsNameNoPathVariable = {"id", "response"},
            methodDesc = "物品文档资料下载")
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ResponseEntity<byte[]> download(@RequestParam("id") String id, HttpServletResponse response) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        ResponseEntity<byte[]> responseEntity = goodsService.fastDFSDownload(id, response);
        return responseEntity;
    }

    /**
     * 根据供应商id查询物品编码列表
     * @param supplierId
     * @return
     */
    @MethodRegister(
            edmClass	= GoodsConstants.GOODS,
            methodCate	= "采购系统方法",
            methodDesc = "根据供应商id查询物品编码列表",
            getReqParamsNameNoPathVariable = {"goodsCode"}
    )
    @RequestMapping(value = "/goodsCodeList/{supplierId}", method = RequestMethod.GET)
    public Result queryGoodsCode(@PathVariable(value = "supplierId") String supplierId,
                                 @RequestParam(value = "goodsCode", required = false) String goodsCode) {
        return goodsService.queryGoodsCode(supplierId, goodsCode);
    }

    /**
     * 查询采购报价单价格异动明细
     * @param supplierId
     * @param currencyId
     * @param taxrateId
     * @param goodsId
     * @return
     */
    @MethodRegister(
            edmClass	= GoodsConstants.GOODS,
            methodCate	= "采购系统方法",
            methodDesc = "查询采购报价单价格异动明细",
            getReqParamsNameNoPathVariable = {"orderId", "supplierId","currencyId","taxrateId","goodsId"}
    )
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public Result queryPriceChangeDetail(@RequestParam(value = "orderId") String orderId,
                                         @RequestParam(value = "supplierId") String supplierId,
                                         @RequestParam(value = "currencyId") String currencyId,
                                         @RequestParam(value = "taxrateId") String taxrateId,
                                         @RequestParam(value = "goodsId") String goodsId) {
        return goodsService.queryPriceChangeDetail(orderId, supplierId, currencyId, taxrateId, goodsId);
    }

    /**
     * 根据物品id查询物品维护单id
     * @param id
     * @return orderId
     */
    @MethodRegister(
            edmClass	= GoodsConstants.GOODS,
            methodCate	= "采购系统方法",
            methodDesc = "根据物品id查询物品维护单id",
            getReqParamsNameNoPathVariable = {"id"}
    )
    @RequestMapping(value = "/getOrderIdById", method = RequestMethod.GET)
    public Result getOrderIdById(@RequestParam(value = "id") String id) {
        return goodsService.getOrderIdById(id);
    }

}
