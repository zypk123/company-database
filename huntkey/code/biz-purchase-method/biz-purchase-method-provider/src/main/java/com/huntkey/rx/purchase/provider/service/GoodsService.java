package com.huntkey.rx.purchase.provider.service;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.purchase.common.model.park.SimpleParkDTO;
import com.huntkey.rx.purchase.common.model.puquoteorder.SimplePuqoPriceSetDTO;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * ClassName:GoodsService 物品操作
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 * Date:     2017年12月27日 上午11:55:29
 * @author   liangh
 * @version
 * @see
 */
public interface GoodsService {

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
    Result query(String godsCode, String godsName, String godsCodeName, String godsPmCode, String godsStatus, String godsPark,
                 String godsClass, String godsUnit, String godsModel, String godsSupplier, int pageNum, int pageSize);

    /**
     * 根据物品类id加载物品信息接口
     * @param id 物品id
     */
    Result load(String id);

    /**
     * @ 查询所有的园区，如果该园区已经有物品信息，则id即为该物品信息的id
     * @               如果该园区没有物品信息，则id为空
     * @param id 物品id
     */
    Result loadParkList(String id);

    /**
     * 查询单位树
     *
     * @param
     * @return
     */
    Result queryUnitTree();

    /**
     * 附件下载
     *
     * @param id
     * @param response
     * @return
     */
    ResponseEntity<byte[]> fastDFSDownload(String id, HttpServletResponse response);

    /**
     * 根据供应商ID和物品编码模糊查询匹配的物品编码列表
     * @param supplierId
     * @param goodsCode
     * @return
     */
    Result queryGoodsCode(String supplierId, String goodsCode);

    /**
     * 查询价格异动
     * @param orderId    单据ID
     * @param supplierId 供应商ID
     * @param currencyId 币别ID
     * @param taxrateId  税率ID
     * @param goodsId    物品ID
     * @return
     */
    Result queryPriceChangeDetail(String orderId, String supplierId, String currencyId, String taxrateId, String goodsId);

    /**
     * 查询可选园区和对应的价格
     * @param orderId    单据ID
     * @param supplierId 供应商ID
     * @param currencyId 币别ID
     * @param taxrateId  税率ID
     * @param goodsId    物品ID
     * @return
     * @throws Exception
     */
    List<SimplePuqoPriceSetDTO> getParkAndPrice(String orderId, String supplierId, String currencyId, String taxrateId, String goodsId) throws Exception;

    /**
     * 查询可选园区
     * @param supplierId 供应商ID
     * @param goodsId    物品ID
     * @return
     */
    List<SimpleParkDTO> getSimpleParkDTOS(String supplierId, String goodsId) throws Exception;

    /**
     * 根据物品id查询物品维护单id
     * @param id
     * @return
     */
    Result getOrderIdById(String id);
}
