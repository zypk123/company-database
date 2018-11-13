package com.huntkey.rx.purchase.common.model.goods;

import com.alibaba.fastjson.annotation.JSONField;
import com.huntkey.rx.purchase.common.model.base.EdmDTO;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by liangh on 2017/12/27 0027.
 */
public class GoodsDTO extends EdmDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(name = NodeConstant.ID)
    private String id;

    @JSONField(name="gods_code")
    private String godsCode;//物品编码

    @JSONField(name="gods_name")
    private String godsName;//物品名称

    @JSONField(name="gods_model")
    private String godsModel;//规格型号

    @JSONField(name="gods_unit")
    private String godsUnit;//单位符号
    private String godsUnitName;//单位显示名称

    @JSONField(name="gods_unit_group")
    private String godsUnitGroup;//计量单位类id


    @JSONField(name="gods_class")
    private String godsClass;//物品分类
    private String godsClassName;//物品分类显示名称

    @JSONField(name="gods_park")
    private String godsPark;//园区

    private List<String> godsPmCodeList;//列表上用于显示的

    private List<String> godsStatusList;//列表上用于显示的

    @JSONField(name="gods_bar_code")
    private String godsBarCode;//物品条码

    @JSONField(name="gods_brand")
    private String godsBrand;//品牌

    @JSONField(name="gods_origin")
    private String godsOrigin;//原产地

    @JSONField(name="gods_weight_gross")
    private BigDecimal godsWeightGross;//单位毛重(kg)

    @JSONField(name="gods_weight_net")
    private BigDecimal godsWeightNet;//单位净重(kg)

    @JSONField(name="gods_volume_bulk")
    private BigDecimal godsVolumeBulk;//单位毛体积(m2)

    @JSONField(name="gods_volume_net")
    private BigDecimal godsVolumeNet;//单位净体积(m2)

    @JSONField(name="gods_desc")
    private String godsDesc;//物品介绍

    private List<GoodsFeaDTO> godsFeaList;//物品特征

    @JSONField(name="gods_check_cyc")
    private Integer godsCheckCyc;//盘点间隔(天)

    @JSONField(name="gods_shelflife")
    private Integer godsShelflife;//保存期限(天)

    @JSONField(name="gods_receipt_tol")
    private BigDecimal godsReceiptTol;//收货容差

    @JSONField(name="gods_ordmult_qty")
    private Integer godsOrdmultQty;//最小包装数量

    @JSONField(name="gods_pallet_qty")
    private Integer godsPalletQty;//栈板堆放数量

    @JSONField(name="gods_ordmin_qty")
    private Integer godsOrdminQty;//最小订单数量

    private List<GodsGodsParkSetaDTO> parkList;//园区信息列表

    private List<GoodsDocDTO> docList;//文档资料列表

    private String orderId;//维护单id

    private String orderNbr;//维护单号

    private String orderStatus;//维护单状态

    private String godsPcurr;//币别id

    private String godsPcurrName;//币别名称

    private String godsPprice;//单价

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGodsCode() {
        return godsCode;
    }

    public void setGodsCode(String godsCode) {
        this.godsCode = godsCode;
    }

    public String getGodsName() {
        return godsName;
    }

    public void setGodsName(String godsName) {
        this.godsName = godsName;
    }

    public String getGodsModel() {
        return godsModel;
    }

    public void setGodsModel(String godsModel) {
        this.godsModel = godsModel;
    }

    public String getGodsUnit() {
        return godsUnit;
    }

    public void setGodsUnit(String godsUnit) {
        this.godsUnit = godsUnit;
    }

    public String getGodsUnitName() {
        return godsUnitName;
    }

    public void setGodsUnitName(String godsUnitName) {
        this.godsUnitName = godsUnitName;
    }

    public String getGodsUnitGroup() {
        return godsUnitGroup;
    }

    public void setGodsUnitGroup(String godsUnitGroup) {
        this.godsUnitGroup = godsUnitGroup;
    }

    public String getGodsClass() {
        return godsClass;
    }

    public void setGodsClass(String godsClass) {
        this.godsClass = godsClass;
    }

    public String getGodsClassName() {
        return godsClassName;
    }

    public void setGodsClassName(String godsClassName) {
        this.godsClassName = godsClassName;
    }

    public String getGodsPark() {
        return godsPark;
    }

    public void setGodsPark(String godsPark) {
        this.godsPark = godsPark;
    }

    public List<String> getGodsPmCodeList() {
        return godsPmCodeList;
    }

    public void setGodsPmCodeList(List<String> godsPmCodeList) {
        this.godsPmCodeList = godsPmCodeList;
    }

    public List<String> getGodsStatusList() {
        return godsStatusList;
    }

    public void setGodsStatusList(List<String> godsStatusList) {
        this.godsStatusList = godsStatusList;
    }

    public String getGodsBarCode() {
        return godsBarCode;
    }

    public void setGodsBarCode(String godsBarCode) {
        this.godsBarCode = godsBarCode;
    }

    public String getGodsBrand() {
        return godsBrand;
    }

    public void setGodsBrand(String godsBrand) {
        this.godsBrand = godsBrand;
    }

    public String getGodsOrigin() {
        return godsOrigin;
    }

    public void setGodsOrigin(String godsOrigin) {
        this.godsOrigin = godsOrigin;
    }

    public BigDecimal getGodsWeightGross() {
        return godsWeightGross;
    }

    public void setGodsWeightGross(BigDecimal godsWeightGross) {
        this.godsWeightGross = godsWeightGross;
    }

    public BigDecimal getGodsWeightNet() {
        return godsWeightNet;
    }

    public void setGodsWeightNet(BigDecimal godsWeightNet) {
        this.godsWeightNet = godsWeightNet;
    }

    public BigDecimal getGodsVolumeBulk() {
        return godsVolumeBulk;
    }

    public void setGodsVolumeBulk(BigDecimal godsVolumeBulk) {
        this.godsVolumeBulk = godsVolumeBulk;
    }

    public BigDecimal getGodsVolumeNet() {
        return godsVolumeNet;
    }

    public void setGodsVolumeNet(BigDecimal godsVolumeNet) {
        this.godsVolumeNet = godsVolumeNet;
    }

    public String getGodsDesc() {
        return godsDesc;
    }

    public void setGodsDesc(String godsDesc) {
        this.godsDesc = godsDesc;
    }

    public List<GoodsFeaDTO> getGodsFeaList() {
        return godsFeaList;
    }

    public void setGodsFeaList(List<GoodsFeaDTO> godsFeaList) {
        this.godsFeaList = godsFeaList;
    }

    public Integer getGodsCheckCyc() {
        return godsCheckCyc;
    }

    public void setGodsCheckCyc(Integer godsCheckCyc) {
        this.godsCheckCyc = godsCheckCyc;
    }

    public Integer getGodsShelflife() {
        return godsShelflife;
    }

    public void setGodsShelflife(Integer godsShelflife) {
        this.godsShelflife = godsShelflife;
    }

    public BigDecimal getGodsReceiptTol() {
        return godsReceiptTol;
    }

    public void setGodsReceiptTol(BigDecimal godsReceiptTol) {
        this.godsReceiptTol = godsReceiptTol;
    }

    public Integer getGodsOrdmultQty() {
        return godsOrdmultQty;
    }

    public void setGodsOrdmultQty(Integer godsOrdmultQty) {
        this.godsOrdmultQty = godsOrdmultQty;
    }

    public Integer getGodsPalletQty() {
        return godsPalletQty;
    }

    public void setGodsPalletQty(Integer godsPalletQty) {
        this.godsPalletQty = godsPalletQty;
    }

    public Integer getGodsOrdminQty() {
        return godsOrdminQty;
    }

    public void setGodsOrdminQty(Integer godsOrdminQty) {
        this.godsOrdminQty = godsOrdminQty;
    }

    public List<GodsGodsParkSetaDTO> getParkList() {
        return parkList;
    }

    public void setParkList(List<GodsGodsParkSetaDTO> parkList) {
        this.parkList = parkList;
    }

    public List<GoodsDocDTO> getDocList() {
        return docList;
    }

    public void setDocList(List<GoodsDocDTO> docList) {
        this.docList = docList;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderNbr() {
        return orderNbr;
    }

    public void setOrderNbr(String orderNbr) {
        this.orderNbr = orderNbr;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getGodsPcurr() {
        return godsPcurr;
    }

    public void setGodsPcurr(String godsPcurr) {
        this.godsPcurr = godsPcurr;
    }

    public String getGodsPcurrName() {
        return godsPcurrName;
    }

    public void setGodsPcurrName(String godsPcurrName) {
        this.godsPcurrName = godsPcurrName;
    }

    public String getGodsPprice() {
        return godsPprice;
    }

    public void setGodsPprice(String godsPprice) {
        this.godsPprice = godsPprice;
    }
}
