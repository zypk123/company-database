package com.huntkey.rx.purchase.common.model.goods;

import com.alibaba.fastjson.annotation.JSONField;
import com.huntkey.rx.edm.entity.GodsGodsParkSetaEntity;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by liangh on 2018/1/4 0004.
 */
public class GodsGodsParkSetaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(name = NodeConstant.ID)
    private String id;

    @JSONField(name="gods_park")
    private String godsPark;//园区

    @JSONField(name="gods_status")
    private String godsStatus;//物品状态

    @JSONField(name="gods_pm_code")
    private String godsPmCode;//PM码

    @JSONField(name="gods_supplier")
    private String godsSupplier;//供应商
    private String godsSupplierName;//供应商名称

    @JSONField(name="gods_supplier_part")
    private String godsSupplierPart;//供应商物品编号

    @JSONField(name="gods_stock_sfty")
    private BigDecimal godsStockSfty;//安全库存

    @JSONField(name="gods_stock_min")
    private BigDecimal godsStockMin;//最小库存

    @JSONField(name="gods_stock_max")
    private BigDecimal godsStockMax;//最大库存

    @JSONField(name="gods_cost_type")
    private String godsCostType;//成本计价方式

    @JSONField(name="gods_isbatchsplit")
    private Integer godsIsbatchsplit;//是否启用批序扫码

    @JSONField(name="gods_isininspect")
    private Integer godsIsininspect;//是否启用入库检验

    @JSONField(name="gods_isoutinspect")
    private Integer godsIsoutinspect;//是否启用出库检验

    private Integer rpak_isdefault;//是否是默认园区

    private String rpakName;//园区名称

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGodsPark() {
        return godsPark;
    }

    public void setGodsPark(String godsPark) {
        this.godsPark = godsPark;
    }

    public String getGodsStatus() {
        return godsStatus;
    }

    public void setGodsStatus(String godsStatus) {
        this.godsStatus = godsStatus;
    }

    public String getGodsPmCode() {
        return godsPmCode;
    }

    public void setGodsPmCode(String godsPmCode) {
        this.godsPmCode = godsPmCode;
    }

    public String getGodsSupplier() {
        return godsSupplier;
    }

    public void setGodsSupplier(String godsSupplier) {
        this.godsSupplier = godsSupplier;
    }

    public String getGodsSupplierName() {
        return godsSupplierName;
    }

    public void setGodsSupplierName(String godsSupplierName) {
        this.godsSupplierName = godsSupplierName;
    }

    public String getGodsSupplierPart() {
        return godsSupplierPart;
    }

    public void setGodsSupplierPart(String godsSupplierPart) {
        this.godsSupplierPart = godsSupplierPart;
    }

    public BigDecimal getGodsStockSfty() {
        return godsStockSfty;
    }

    public void setGodsStockSfty(BigDecimal godsStockSfty) {
        this.godsStockSfty = godsStockSfty;
    }

    public BigDecimal getGodsStockMin() {
        return godsStockMin;
    }

    public void setGodsStockMin(BigDecimal godsStockMin) {
        this.godsStockMin = godsStockMin;
    }

    public BigDecimal getGodsStockMax() {
        return godsStockMax;
    }

    public void setGodsStockMax(BigDecimal godsStockMax) {
        this.godsStockMax = godsStockMax;
    }

    public String getGodsCostType() {
        return godsCostType;
    }

    public void setGodsCostType(String godsCostType) {
        this.godsCostType = godsCostType;
    }

    public Integer getGodsIsbatchsplit() {
        return godsIsbatchsplit;
    }

    public void setGodsIsbatchsplit(Integer godsIsbatchsplit) {
        this.godsIsbatchsplit = godsIsbatchsplit;
    }

    public Integer getGodsIsininspect() {
        return godsIsininspect;
    }

    public void setGodsIsininspect(Integer godsIsininspect) {
        this.godsIsininspect = godsIsininspect;
    }

    public Integer getGodsIsoutinspect() {
        return godsIsoutinspect;
    }

    public void setGodsIsoutinspect(Integer godsIsoutinspect) {
        this.godsIsoutinspect = godsIsoutinspect;
    }

    public Integer getRpak_isdefault() {
        return rpak_isdefault;
    }

    public void setRpak_isdefault(Integer rpak_isdefault) {
        this.rpak_isdefault = rpak_isdefault;
    }

    public String getRpakName() {
        return rpakName;
    }

    public void setRpakName(String rpakName) {
        this.rpakName = rpakName;
    }
}
