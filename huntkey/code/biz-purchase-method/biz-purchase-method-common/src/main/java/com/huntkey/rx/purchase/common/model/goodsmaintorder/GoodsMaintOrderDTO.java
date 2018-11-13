package com.huntkey.rx.purchase.common.model.goodsmaintorder;

import com.alibaba.fastjson.annotation.JSONField;
import com.huntkey.rx.purchase.common.model.base.OrderDTO;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by liangh on 2018/1/3 0003.
 */
public class GoodsMaintOrderDTO extends OrderDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(name = NodeConstant.ID)
    private String id;

    @JSONField(name="gomo_code")
    private String gomoCode;//物品编码

    @JSONField(name="gomo_name")
    private String gomoName;//物品名称

    @JSONField(name="gomo_name_old")
    private String gomoNameOld;//物品名称_旧
    private Integer gomoNameFlag;//物品名称是否修改

    @JSONField(name="gomo_model")
    private String gomoModel;//规格型号

    @JSONField(name="gomo_model_old")
    private String gomoModelOld;//规格型号_旧
    private Integer gomoModelFlag;//规格型号是否修改

    @JSONField(name="gomo_class")
    private String gomoClass;//物品分类
    private String gomoClassName;//物品分类名称

    @JSONField(name="gomo_class_old")
    private String gomoClassOld;//物品分类_旧
    private Integer gomoClassFlag;//物品分类是否修改

    @JSONField(name="gomo_unit")
    private String gomoUnit;//单位符号

    @JSONField(name="gomo_unit_old")
    private String gomoUnitOld;//单位符号_旧
    private String gomoUnitName;//单位显示名称
    private Integer gomoUnitFlag;//单位符号是否修改

    @JSONField(name="gomo_unit_group")
    private String gomoUnitGroup;//计量单位类id
    @JSONField(name="gomo_unit_group_old")
    private String gomoUnitGroupOld;//计量单位类id_旧
    private Integer gomoUnitGroupFlag;//计量单位类id是否修改

    @JSONField(name="gomo_park")
    private String gomoPark;//园区

    @JSONField(name="gomo_status")
    private String gomoStatus;//物品状态

    @JSONField(name="gomo_pm_code")
    private String gomoPmCode;//PM码

    @JSONField(name="gomo_supplier")
    private String gomoSupplier;//供应商

    @JSONField(name="gomo_supplier_part")
    private String gomoSupplierPart;//供应商物品编号

    @JSONField(name="gomo_bar_code")
    private String gomoBarCode;//物品条码

    @JSONField(name="gomo_bar_code_old")
    private String gomoBarCodeOld;//物品条码_旧
    private Integer gomoBarCodeFlag;//物品条码是否修改

    @JSONField(name="gomo_brand")
    private String gomoBrand;//品牌

    @JSONField(name="gomo_brand_old")
    private String gomoBrandOld;//品牌_旧
    private Integer gomoBrandFlag;//品牌是否修改

    @JSONField(name="gomo_origin")
    private String gomoOrigin;//原产地

    @JSONField(name="gomo_origin_old")
    private String gomoOriginOld;//原产地_旧
    private Integer gomoOriginFlag;//原产地是否修改

    @JSONField(name="gomo_weight_gross")
    private BigDecimal gomoWeightGross;//单位毛重(kg)

    @JSONField(name="gomo_weight_gross_old")
    private BigDecimal gomoWeightGrossOld;//单位毛重(kg)_旧
    private Integer gomoWeightGrossFlag;//单位毛重(kg)是否修改

    @JSONField(name="gomo_weight_net")
    private BigDecimal gomoWeightNet;//单位净重(kg)

    @JSONField(name="gomo_weight_net_old")
    private BigDecimal gomoWeightNetOld;//单位净重(kg)_旧
    private Integer gomoWeightNetFlag;//单位净重(kg)是否修改

    @JSONField(name="gomo_volume_bulk")
    private BigDecimal gomoVolumeBulk;//单位毛体积(m2)

    @JSONField(name="gomo_volume_bulk_old")
    private BigDecimal gomoVolumeBulkOld;//单位毛体积(m2)_旧
    private Integer gomoVolumeBulkFlag;//单位毛体积(m2)是否修改

    @JSONField(name="gomo_volume_net")
    private BigDecimal gomoVolumeNet;//单位净体积(m2)

    @JSONField(name="gomo_volume_net_old")
    private BigDecimal gomoVolumeNetOld;//单位净体积(m2)_旧
    private Integer gomoVolumeNetFlag;//单位净体积(m2)是否修改

    @JSONField(name="gomo_desc")
    private String gomoDesc;//物品介绍

    @JSONField(name="gomo_desc_old")
    private String gomoDescOld;//物品介绍_旧
    private Integer gomoDescFlag;//物品介绍是否修改

    @JSONField(name="gomo_cost_type")
    private String gomoCostType;//成本计价方式

    @JSONField(name="gomo_stock_sfty")
    private BigDecimal gomoStockSfty;//安全库存

    @JSONField(name="gomo_stock_min")
    private BigDecimal gomoStockMin;//最小库存

    @JSONField(name="gomo_stock_max")
    private BigDecimal gomoStockMax;//最大库存

    @JSONField(name="gomo_isbatchsplit")
    private Integer gomoIsbatchsplit;//启用批序扫码

    @JSONField(name="gomo_check_cyc")
    private Integer gomoCheckCyc;//盘点间隔(天)

    @JSONField(name="gomo_check_cyc_old")
    private Integer gomoCheckCycOld;//盘点间隔(天)_旧
    private Integer gomoCheckCycFlag;//盘点间隔(天)是否修改

    @JSONField(name="gomo_shelflife")
    private Integer gomoShelflife;//保存期限(天)

    @JSONField(name="gomo_shelflife_old")
    private Integer gomoShelflifeOld;//保存期限(天)_旧
    private Integer gomoShelflifeFlag;//保存期限(天)是否修改

    @JSONField(name="gomo_receipt_tol")
    private BigDecimal gomoReceiptTol;//收货容差

    @JSONField(name="gomo_receipt_tol_old")
    private BigDecimal gomoReceiptTolOld;//收货容差_旧
    private Integer gomoReceiptTolFlag;//收货容差是否修改

    @JSONField(name="gomo_ordmult_qty")
    private Integer gomoOrdmultQty;//最小包装数量

    @JSONField(name="gomo_ordmult_qty_old")
    private Integer gomoOrdmultQtyOld;//最小包装数量_旧
    private Integer gomoOrdmultQtyFlag;//最小包装数量是否修改

    @JSONField(name="gomo_pallet_qty")
    private Integer gomoPalletQty;//栈板堆放数量

    @JSONField(name="gomo_pallet_qty_old")
    private Integer gomoPalletQtyOld;//栈板堆放数量_旧
    private Integer gomoPalletQtyFlag;//栈板堆放数量是否修改

    @JSONField(name="gomo_isininspect")
    private Integer gomoIsininspect;//启用入库检验

    @JSONField(name="gomo_isoutinspect")
    private Integer gomoIsoutinspect;//启用出库检验

    @JSONField(name="gomo_ordmin_qty")
    private Integer gomoOrdminQty;//最小订单数量
    @JSONField(name="gomo_ordmin_qty_old")
    private Integer gomoOrdminQtyOld;//最小订单数量_旧
    private Integer gomoOrdminQtyFlag;//最小订单数量是否修改

    private List<GomoParkDTO> parkList;//园区信息列表

    private List<GomoDocDTO> docList;//文档资料列表

    private List<GomoFeaDTO> feaList;//物品特征

    private Integer baseInfoTotalNum;//基础信息被修改的地方总数

    private Integer busiPropTotalNum;//业务属性被修改的地方总数

    private Integer documentTotalNum;//文档资料被修改的地方总数

    private boolean isSubmit;//是否是提交情况

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGomoCode() {
        return gomoCode;
    }

    public void setGomoCode(String gomoCode) {
        this.gomoCode = gomoCode;
    }

    public String getGomoName() {
        return gomoName;
    }

    public void setGomoName(String gomoName) {
        this.gomoName = gomoName;
    }

    public String getGomoNameOld() {
        return gomoNameOld;
    }

    public void setGomoNameOld(String gomoNameOld) {
        this.gomoNameOld = gomoNameOld;
    }

    public Integer getGomoNameFlag() {
        return gomoNameFlag;
    }

    public void setGomoNameFlag(Integer gomoNameFlag) {
        this.gomoNameFlag = gomoNameFlag;
    }

    public String getGomoModel() {
        return gomoModel;
    }

    public void setGomoModel(String gomoModel) {
        this.gomoModel = gomoModel;
    }

    public String getGomoModelOld() {
        return gomoModelOld;
    }

    public void setGomoModelOld(String gomoModelOld) {
        this.gomoModelOld = gomoModelOld;
    }

    public Integer getGomoModelFlag() {
        return gomoModelFlag;
    }

    public void setGomoModelFlag(Integer gomoModelFlag) {
        this.gomoModelFlag = gomoModelFlag;
    }

    public String getGomoClass() {
        return gomoClass;
    }

    public void setGomoClass(String gomoClass) {
        this.gomoClass = gomoClass;
    }

    public String getGomoClassName() {
        return gomoClassName;
    }

    public void setGomoClassName(String gomoClassName) {
        this.gomoClassName = gomoClassName;
    }

    public String getGomoClassOld() {
        return gomoClassOld;
    }

    public void setGomoClassOld(String gomoClassOld) {
        this.gomoClassOld = gomoClassOld;
    }

    public Integer getGomoClassFlag() {
        return gomoClassFlag;
    }

    public void setGomoClassFlag(Integer gomoClassFlag) {
        this.gomoClassFlag = gomoClassFlag;
    }

    public String getGomoUnit() {
        return gomoUnit;
    }

    public void setGomoUnit(String gomoUnit) {
        this.gomoUnit = gomoUnit;
    }

    public String getGomoUnitOld() {
        return gomoUnitOld;
    }

    public void setGomoUnitOld(String gomoUnitOld) {
        this.gomoUnitOld = gomoUnitOld;
    }

    public String getGomoUnitName() {
        return gomoUnitName;
    }

    public void setGomoUnitName(String gomoUnitName) {
        this.gomoUnitName = gomoUnitName;
    }

    public Integer getGomoUnitFlag() {
        return gomoUnitFlag;
    }

    public void setGomoUnitFlag(Integer gomoUnitFlag) {
        this.gomoUnitFlag = gomoUnitFlag;
    }

    public String getGomoUnitGroup() {
        return gomoUnitGroup;
    }

    public void setGomoUnitGroup(String gomoUnitGroup) {
        this.gomoUnitGroup = gomoUnitGroup;
    }

    public String getGomoUnitGroupOld() {
        return gomoUnitGroupOld;
    }

    public void setGomoUnitGroupOld(String gomoUnitGroupOld) {
        this.gomoUnitGroupOld = gomoUnitGroupOld;
    }

    public Integer getGomoUnitGroupFlag() {
        return gomoUnitGroupFlag;
    }

    public void setGomoUnitGroupFlag(Integer gomoUnitGroupFlag) {
        this.gomoUnitGroupFlag = gomoUnitGroupFlag;
    }

    public String getGomoPark() {
        return gomoPark;
    }

    public void setGomoPark(String gomoPark) {
        this.gomoPark = gomoPark;
    }

    public String getGomoStatus() {
        return gomoStatus;
    }

    public void setGomoStatus(String gomoStatus) {
        this.gomoStatus = gomoStatus;
    }

    public String getGomoPmCode() {
        return gomoPmCode;
    }

    public void setGomoPmCode(String gomoPmCode) {
        this.gomoPmCode = gomoPmCode;
    }

    public String getGomoSupplier() {
        return gomoSupplier;
    }

    public void setGomoSupplier(String gomoSupplier) {
        this.gomoSupplier = gomoSupplier;
    }

    public String getGomoSupplierPart() {
        return gomoSupplierPart;
    }

    public void setGomoSupplierPart(String gomoSupplierPart) {
        this.gomoSupplierPart = gomoSupplierPart;
    }

    public String getGomoBarCode() {
        return gomoBarCode;
    }

    public void setGomoBarCode(String gomoBarCode) {
        this.gomoBarCode = gomoBarCode;
    }

    public String getGomoBarCodeOld() {
        return gomoBarCodeOld;
    }

    public void setGomoBarCodeOld(String gomoBarCodeOld) {
        this.gomoBarCodeOld = gomoBarCodeOld;
    }

    public Integer getGomoBarCodeFlag() {
        return gomoBarCodeFlag;
    }

    public void setGomoBarCodeFlag(Integer gomoBarCodeFlag) {
        this.gomoBarCodeFlag = gomoBarCodeFlag;
    }

    public String getGomoBrand() {
        return gomoBrand;
    }

    public void setGomoBrand(String gomoBrand) {
        this.gomoBrand = gomoBrand;
    }

    public String getGomoBrandOld() {
        return gomoBrandOld;
    }

    public void setGomoBrandOld(String gomoBrandOld) {
        this.gomoBrandOld = gomoBrandOld;
    }

    public Integer getGomoBrandFlag() {
        return gomoBrandFlag;
    }

    public void setGomoBrandFlag(Integer gomoBrandFlag) {
        this.gomoBrandFlag = gomoBrandFlag;
    }

    public String getGomoOrigin() {
        return gomoOrigin;
    }

    public void setGomoOrigin(String gomoOrigin) {
        this.gomoOrigin = gomoOrigin;
    }

    public String getGomoOriginOld() {
        return gomoOriginOld;
    }

    public void setGomoOriginOld(String gomoOriginOld) {
        this.gomoOriginOld = gomoOriginOld;
    }

    public Integer getGomoOriginFlag() {
        return gomoOriginFlag;
    }

    public void setGomoOriginFlag(Integer gomoOriginFlag) {
        this.gomoOriginFlag = gomoOriginFlag;
    }

    public BigDecimal getGomoWeightGross() {
        return gomoWeightGross;
    }

    public void setGomoWeightGross(BigDecimal gomoWeightGross) {
        this.gomoWeightGross = gomoWeightGross;
    }

    public BigDecimal getGomoWeightGrossOld() {
        return gomoWeightGrossOld;
    }

    public void setGomoWeightGrossOld(BigDecimal gomoWeightGrossOld) {
        this.gomoWeightGrossOld = gomoWeightGrossOld;
    }

    public Integer getGomoWeightGrossFlag() {
        return gomoWeightGrossFlag;
    }

    public void setGomoWeightGrossFlag(Integer gomoWeightGrossFlag) {
        this.gomoWeightGrossFlag = gomoWeightGrossFlag;
    }

    public BigDecimal getGomoWeightNet() {
        return gomoWeightNet;
    }

    public void setGomoWeightNet(BigDecimal gomoWeightNet) {
        this.gomoWeightNet = gomoWeightNet;
    }

    public BigDecimal getGomoWeightNetOld() {
        return gomoWeightNetOld;
    }

    public void setGomoWeightNetOld(BigDecimal gomoWeightNetOld) {
        this.gomoWeightNetOld = gomoWeightNetOld;
    }

    public Integer getGomoWeightNetFlag() {
        return gomoWeightNetFlag;
    }

    public void setGomoWeightNetFlag(Integer gomoWeightNetFlag) {
        this.gomoWeightNetFlag = gomoWeightNetFlag;
    }

    public BigDecimal getGomoVolumeBulk() {
        return gomoVolumeBulk;
    }

    public void setGomoVolumeBulk(BigDecimal gomoVolumeBulk) {
        this.gomoVolumeBulk = gomoVolumeBulk;
    }

    public BigDecimal getGomoVolumeBulkOld() {
        return gomoVolumeBulkOld;
    }

    public void setGomoVolumeBulkOld(BigDecimal gomoVolumeBulkOld) {
        this.gomoVolumeBulkOld = gomoVolumeBulkOld;
    }

    public Integer getGomoVolumeBulkFlag() {
        return gomoVolumeBulkFlag;
    }

    public void setGomoVolumeBulkFlag(Integer gomoVolumeBulkFlag) {
        this.gomoVolumeBulkFlag = gomoVolumeBulkFlag;
    }

    public BigDecimal getGomoVolumeNet() {
        return gomoVolumeNet;
    }

    public void setGomoVolumeNet(BigDecimal gomoVolumeNet) {
        this.gomoVolumeNet = gomoVolumeNet;
    }

    public BigDecimal getGomoVolumeNetOld() {
        return gomoVolumeNetOld;
    }

    public void setGomoVolumeNetOld(BigDecimal gomoVolumeNetOld) {
        this.gomoVolumeNetOld = gomoVolumeNetOld;
    }

    public Integer getGomoVolumeNetFlag() {
        return gomoVolumeNetFlag;
    }

    public void setGomoVolumeNetFlag(Integer gomoVolumeNetFlag) {
        this.gomoVolumeNetFlag = gomoVolumeNetFlag;
    }

    public String getGomoDesc() {
        return gomoDesc;
    }

    public void setGomoDesc(String gomoDesc) {
        this.gomoDesc = gomoDesc;
    }

    public String getGomoDescOld() {
        return gomoDescOld;
    }

    public void setGomoDescOld(String gomoDescOld) {
        this.gomoDescOld = gomoDescOld;
    }

    public Integer getGomoDescFlag() {
        return gomoDescFlag;
    }

    public void setGomoDescFlag(Integer gomoDescFlag) {
        this.gomoDescFlag = gomoDescFlag;
    }

    public String getGomoCostType() {
        return gomoCostType;
    }

    public void setGomoCostType(String gomoCostType) {
        this.gomoCostType = gomoCostType;
    }

    public BigDecimal getGomoStockSfty() {
        return gomoStockSfty;
    }

    public void setGomoStockSfty(BigDecimal gomoStockSfty) {
        this.gomoStockSfty = gomoStockSfty;
    }

    public BigDecimal getGomoStockMin() {
        return gomoStockMin;
    }

    public void setGomoStockMin(BigDecimal gomoStockMin) {
        this.gomoStockMin = gomoStockMin;
    }

    public BigDecimal getGomoStockMax() {
        return gomoStockMax;
    }

    public void setGomoStockMax(BigDecimal gomoStockMax) {
        this.gomoStockMax = gomoStockMax;
    }

    public Integer getGomoIsbatchsplit() {
        return gomoIsbatchsplit;
    }

    public void setGomoIsbatchsplit(Integer gomoIsbatchsplit) {
        this.gomoIsbatchsplit = gomoIsbatchsplit;
    }

    public Integer getGomoCheckCyc() {
        return gomoCheckCyc;
    }

    public void setGomoCheckCyc(Integer gomoCheckCyc) {
        this.gomoCheckCyc = gomoCheckCyc;
    }

    public Integer getGomoCheckCycOld() {
        return gomoCheckCycOld;
    }

    public void setGomoCheckCycOld(Integer gomoCheckCycOld) {
        this.gomoCheckCycOld = gomoCheckCycOld;
    }

    public Integer getGomoCheckCycFlag() {
        return gomoCheckCycFlag;
    }

    public void setGomoCheckCycFlag(Integer gomoCheckCycFlag) {
        this.gomoCheckCycFlag = gomoCheckCycFlag;
    }

    public Integer getGomoShelflife() {
        return gomoShelflife;
    }

    public void setGomoShelflife(Integer gomoShelflife) {
        this.gomoShelflife = gomoShelflife;
    }

    public Integer getGomoShelflifeOld() {
        return gomoShelflifeOld;
    }

    public void setGomoShelflifeOld(Integer gomoShelflifeOld) {
        this.gomoShelflifeOld = gomoShelflifeOld;
    }

    public Integer getGomoShelflifeFlag() {
        return gomoShelflifeFlag;
    }

    public void setGomoShelflifeFlag(Integer gomoShelflifeFlag) {
        this.gomoShelflifeFlag = gomoShelflifeFlag;
    }

    public BigDecimal getGomoReceiptTol() {
        return gomoReceiptTol;
    }

    public void setGomoReceiptTol(BigDecimal gomoReceiptTol) {
        this.gomoReceiptTol = gomoReceiptTol;
    }

    public BigDecimal getGomoReceiptTolOld() {
        return gomoReceiptTolOld;
    }

    public void setGomoReceiptTolOld(BigDecimal gomoReceiptTolOld) {
        this.gomoReceiptTolOld = gomoReceiptTolOld;
    }

    public Integer getGomoReceiptTolFlag() {
        return gomoReceiptTolFlag;
    }

    public void setGomoReceiptTolFlag(Integer gomoReceiptTolFlag) {
        this.gomoReceiptTolFlag = gomoReceiptTolFlag;
    }

    public Integer getGomoOrdmultQty() {
        return gomoOrdmultQty;
    }

    public void setGomoOrdmultQty(Integer gomoOrdmultQty) {
        this.gomoOrdmultQty = gomoOrdmultQty;
    }

    public Integer getGomoOrdmultQtyOld() {
        return gomoOrdmultQtyOld;
    }

    public void setGomoOrdmultQtyOld(Integer gomoOrdmultQtyOld) {
        this.gomoOrdmultQtyOld = gomoOrdmultQtyOld;
    }

    public Integer getGomoOrdmultQtyFlag() {
        return gomoOrdmultQtyFlag;
    }

    public void setGomoOrdmultQtyFlag(Integer gomoOrdmultQtyFlag) {
        this.gomoOrdmultQtyFlag = gomoOrdmultQtyFlag;
    }

    public Integer getGomoPalletQty() {
        return gomoPalletQty;
    }

    public void setGomoPalletQty(Integer gomoPalletQty) {
        this.gomoPalletQty = gomoPalletQty;
    }

    public Integer getGomoPalletQtyOld() {
        return gomoPalletQtyOld;
    }

    public void setGomoPalletQtyOld(Integer gomoPalletQtyOld) {
        this.gomoPalletQtyOld = gomoPalletQtyOld;
    }

    public Integer getGomoPalletQtyFlag() {
        return gomoPalletQtyFlag;
    }

    public void setGomoPalletQtyFlag(Integer gomoPalletQtyFlag) {
        this.gomoPalletQtyFlag = gomoPalletQtyFlag;
    }

    public Integer getGomoIsininspect() {
        return gomoIsininspect;
    }

    public void setGomoIsininspect(Integer gomoIsininspect) {
        this.gomoIsininspect = gomoIsininspect;
    }

    public Integer getGomoIsoutinspect() {
        return gomoIsoutinspect;
    }

    public void setGomoIsoutinspect(Integer gomoIsoutinspect) {
        this.gomoIsoutinspect = gomoIsoutinspect;
    }

    public Integer getGomoOrdminQty() {
        return gomoOrdminQty;
    }

    public void setGomoOrdminQty(Integer gomoOrdminQty) {
        this.gomoOrdminQty = gomoOrdminQty;
    }

    public Integer getGomoOrdminQtyOld() {
        return gomoOrdminQtyOld;
    }

    public void setGomoOrdminQtyOld(Integer gomoOrdminQtyOld) {
        this.gomoOrdminQtyOld = gomoOrdminQtyOld;
    }

    public Integer getGomoOrdminQtyFlag() {
        return gomoOrdminQtyFlag;
    }

    public void setGomoOrdminQtyFlag(Integer gomoOrdminQtyFlag) {
        this.gomoOrdminQtyFlag = gomoOrdminQtyFlag;
    }

    public List<GomoParkDTO> getParkList() {
        return parkList;
    }

    public void setParkList(List<GomoParkDTO> parkList) {
        this.parkList = parkList;
    }

    public List<GomoDocDTO> getDocList() {
        return docList;
    }

    public void setDocList(List<GomoDocDTO> docList) {
        this.docList = docList;
    }

    public List<GomoFeaDTO> getFeaList() {
        return feaList;
    }

    public void setFeaList(List<GomoFeaDTO> feaList) {
        this.feaList = feaList;
    }

    public Integer getBaseInfoTotalNum() {
        return baseInfoTotalNum;
    }

    public void setBaseInfoTotalNum(Integer baseInfoTotalNum) {
        this.baseInfoTotalNum = baseInfoTotalNum;
    }

    public Integer getBusiPropTotalNum() {
        return busiPropTotalNum;
    }

    public void setBusiPropTotalNum(Integer busiPropTotalNum) {
        this.busiPropTotalNum = busiPropTotalNum;
    }

    public Integer getDocumentTotalNum() {
        return documentTotalNum;
    }

    public void setDocumentTotalNum(Integer documentTotalNum) {
        this.documentTotalNum = documentTotalNum;
    }

    public boolean isSubmit() {
        return isSubmit;
    }

    public void setSubmit(boolean submit) {
        isSubmit = submit;
    }
}
