package com.huntkey.rx.purchase.common.model.goodsmaintorder;

import com.alibaba.fastjson.annotation.JSONField;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by liangh on 2018/1/3 0003.
 */
public class GomoParkDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(name = NodeConstant.ID)
    private String id;

    @JSONField(name="gomo_park")
    private String gomoPark;//园区

    @JSONField(name="gomo_status")
    private String gomoStatus;//物品状态

    @JSONField(name="gomo_status_old")
    private String gomoStatusOld;//物品状态_旧
    private int gomoStatusFlag;//物品状态是否修改

    @JSONField(name="gomo_pm_code")
    private String gomoPmCode;//PM码

    @JSONField(name="gomo_pm_code_old")
    private String gomoPmCodeOld;//PM码_旧
    private int gomoPmCodeFlag;//PM码是否修改

    @JSONField(name="gomo_supplier")
    private String gomoSupplier;//供应商
    private String gomoSupplierName;//供应商名称

    @JSONField(name="gomo_supplier_old")
    private String gomoSupplierOld;//供应商_旧
    private String gomoSupplierNameOld;//供应商名称_旧
    private int gomoSupplierFlag;//供应商是否修改

    @JSONField(name="gomo_supplier_part")
    private String gomoSupplierPart;//供应商物品编号

    @JSONField(name="gomo_supplier_part_old")
    private String gomoSupplierPartOld;//供应商物品编号_旧
    private int gomoSupplierPartFlag;//供应商物品编号是否修改

    @JSONField(name="gomo_stock_sfty")
    private BigDecimal gomoStockSfty;//安全库存

    @JSONField(name="gomo_stock_sfty_old")
    private BigDecimal gomoStockSftyOld;//安全库存_旧
    private int gomoStockSftyFlag;//安全库存是否修改

    @JSONField(name="gomo_stock_min")
    private BigDecimal gomoStockMin;//最小库存

    @JSONField(name="gomo_stock_min_old")
    private BigDecimal gomoStockMinOld;//最小库存_旧
    private int gomoStockMinFlag;//最小库存是否修改

    @JSONField(name="gomo_stock_max")
    private BigDecimal gomoStockMax;//最大库存

    @JSONField(name="gomo_stock_max_old")
    private BigDecimal gomoStockMaxOld;//最大库存_旧
    private int gomoStockMaxFlag;//最大库存是否修改

    @JSONField(name="gomo_cost_type")
    private String gomoCostType;//成本计价方式

    @JSONField(name="gomo_cost_type_old")
    private String gomoCostTypeOld;//成本计价方式_旧
    private int gomoCostTypeFlag;//成本计价方式是否修改

    @JSONField(name="gomo_isbatchsplit")
    private BigDecimal gomoIsbatchsplit;//是否启用批序扫码

    @JSONField(name="gomo_isbatchsplit_old")
    private BigDecimal gomoIsbatchsplitOld;//是否启用批序扫码_旧
    private int gomoIsbatchsplitFlag;//是否启用批序扫码是否修改

    @JSONField(name="gomo_isininspect")
    private BigDecimal gomoIsininspect;//是否启用入库检验

    @JSONField(name="gomo_isininspect_old")
    private BigDecimal gomoIsininspectOld;//是否启用入库检验_旧
    private int gomoIsininspectFlag;//是否启用入库检验是否修改

    @JSONField(name="gomo_isoutinspect")
    private BigDecimal gomoIsoutinspect;//是否启用出库检验

    @JSONField(name="gomo_isoutinspect_old")
    private BigDecimal gomoIsoutinspectOld;//是否启用出库检验_旧
    private int gomoIsoutinspectFlag;//是否启用出库检验是否修改

    @JSONField(name="gomo_mod_type")
    private BigDecimal gomoModType;//修改类型

    private String rpakName;//园区名称

    private int rpak_isdefault;//是否是默认园区

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getGomoStatusOld() {
        return gomoStatusOld;
    }

    public void setGomoStatusOld(String gomoStatusOld) {
        this.gomoStatusOld = gomoStatusOld;
    }

    public int getGomoStatusFlag() {
        return gomoStatusFlag;
    }

    public void setGomoStatusFlag(int gomoStatusFlag) {
        this.gomoStatusFlag = gomoStatusFlag;
    }

    public String getGomoPmCode() {
        return gomoPmCode;
    }

    public void setGomoPmCode(String gomoPmCode) {
        this.gomoPmCode = gomoPmCode;
    }

    public String getGomoPmCodeOld() {
        return gomoPmCodeOld;
    }

    public void setGomoPmCodeOld(String gomoPmCodeOld) {
        this.gomoPmCodeOld = gomoPmCodeOld;
    }

    public int getGomoPmCodeFlag() {
        return gomoPmCodeFlag;
    }

    public void setGomoPmCodeFlag(int gomoPmCodeFlag) {
        this.gomoPmCodeFlag = gomoPmCodeFlag;
    }

    public String getGomoSupplier() {
        return gomoSupplier;
    }

    public void setGomoSupplier(String gomoSupplier) {
        this.gomoSupplier = gomoSupplier;
    }

    public String getGomoSupplierOld() {
        return gomoSupplierOld;
    }

    public void setGomoSupplierOld(String gomoSupplierOld) {
        this.gomoSupplierOld = gomoSupplierOld;
    }

    public int getGomoSupplierFlag() {
        return gomoSupplierFlag;
    }

    public void setGomoSupplierFlag(int gomoSupplierFlag) {
        this.gomoSupplierFlag = gomoSupplierFlag;
    }

    public String getGomoSupplierPart() {
        return gomoSupplierPart;
    }

    public void setGomoSupplierPart(String gomoSupplierPart) {
        this.gomoSupplierPart = gomoSupplierPart;
    }

    public String getGomoSupplierPartOld() {
        return gomoSupplierPartOld;
    }

    public void setGomoSupplierPartOld(String gomoSupplierPartOld) {
        this.gomoSupplierPartOld = gomoSupplierPartOld;
    }

    public int getGomoSupplierPartFlag() {
        return gomoSupplierPartFlag;
    }

    public void setGomoSupplierPartFlag(int gomoSupplierPartFlag) {
        this.gomoSupplierPartFlag = gomoSupplierPartFlag;
    }

    public BigDecimal getGomoStockSfty() {
        return gomoStockSfty;
    }

    public void setGomoStockSfty(BigDecimal gomoStockSfty) {
        this.gomoStockSfty = gomoStockSfty;
    }

    public BigDecimal getGomoStockSftyOld() {
        return gomoStockSftyOld;
    }

    public void setGomoStockSftyOld(BigDecimal gomoStockSftyOld) {
        this.gomoStockSftyOld = gomoStockSftyOld;
    }

    public int getGomoStockSftyFlag() {
        return gomoStockSftyFlag;
    }

    public void setGomoStockSftyFlag(int gomoStockSftyFlag) {
        this.gomoStockSftyFlag = gomoStockSftyFlag;
    }

    public BigDecimal getGomoStockMin() {
        return gomoStockMin;
    }

    public void setGomoStockMin(BigDecimal gomoStockMin) {
        this.gomoStockMin = gomoStockMin;
    }

    public BigDecimal getGomoStockMinOld() {
        return gomoStockMinOld;
    }

    public void setGomoStockMinOld(BigDecimal gomoStockMinOld) {
        this.gomoStockMinOld = gomoStockMinOld;
    }

    public int getGomoStockMinFlag() {
        return gomoStockMinFlag;
    }

    public void setGomoStockMinFlag(int gomoStockMinFlag) {
        this.gomoStockMinFlag = gomoStockMinFlag;
    }

    public BigDecimal getGomoStockMax() {
        return gomoStockMax;
    }

    public void setGomoStockMax(BigDecimal gomoStockMax) {
        this.gomoStockMax = gomoStockMax;
    }

    public BigDecimal getGomoStockMaxOld() {
        return gomoStockMaxOld;
    }

    public void setGomoStockMaxOld(BigDecimal gomoStockMaxOld) {
        this.gomoStockMaxOld = gomoStockMaxOld;
    }

    public int getGomoStockMaxFlag() {
        return gomoStockMaxFlag;
    }

    public void setGomoStockMaxFlag(int gomoStockMaxFlag) {
        this.gomoStockMaxFlag = gomoStockMaxFlag;
    }

    public String getGomoCostType() {
        return gomoCostType;
    }

    public void setGomoCostType(String gomoCostType) {
        this.gomoCostType = gomoCostType;
    }

    public String getGomoCostTypeOld() {
        return gomoCostTypeOld;
    }

    public void setGomoCostTypeOld(String gomoCostTypeOld) {
        this.gomoCostTypeOld = gomoCostTypeOld;
    }

    public int getGomoCostTypeFlag() {
        return gomoCostTypeFlag;
    }

    public void setGomoCostTypeFlag(int gomoCostTypeFlag) {
        this.gomoCostTypeFlag = gomoCostTypeFlag;
    }

    public BigDecimal getGomoIsbatchsplit() {
        return gomoIsbatchsplit;
    }

    public void setGomoIsbatchsplit(BigDecimal gomoIsbatchsplit) {
        this.gomoIsbatchsplit = gomoIsbatchsplit;
    }

    public BigDecimal getGomoIsbatchsplitOld() {
        return gomoIsbatchsplitOld;
    }

    public void setGomoIsbatchsplitOld(BigDecimal gomoIsbatchsplitOld) {
        this.gomoIsbatchsplitOld = gomoIsbatchsplitOld;
    }

    public int getGomoIsbatchsplitFlag() {
        return gomoIsbatchsplitFlag;
    }

    public void setGomoIsbatchsplitFlag(int gomoIsbatchsplitFlag) {
        this.gomoIsbatchsplitFlag = gomoIsbatchsplitFlag;
    }

    public BigDecimal getGomoIsininspect() {
        return gomoIsininspect;
    }

    public void setGomoIsininspect(BigDecimal gomoIsininspect) {
        this.gomoIsininspect = gomoIsininspect;
    }

    public BigDecimal getGomoIsininspectOld() {
        return gomoIsininspectOld;
    }

    public void setGomoIsininspectOld(BigDecimal gomoIsininspectOld) {
        this.gomoIsininspectOld = gomoIsininspectOld;
    }

    public int getGomoIsininspectFlag() {
        return gomoIsininspectFlag;
    }

    public void setGomoIsininspectFlag(int gomoIsininspectFlag) {
        this.gomoIsininspectFlag = gomoIsininspectFlag;
    }

    public BigDecimal getGomoIsoutinspect() {
        return gomoIsoutinspect;
    }

    public void setGomoIsoutinspect(BigDecimal gomoIsoutinspect) {
        this.gomoIsoutinspect = gomoIsoutinspect;
    }

    public BigDecimal getGomoIsoutinspectOld() {
        return gomoIsoutinspectOld;
    }

    public void setGomoIsoutinspectOld(BigDecimal gomoIsoutinspectOld) {
        this.gomoIsoutinspectOld = gomoIsoutinspectOld;
    }

    public int getGomoIsoutinspectFlag() {
        return gomoIsoutinspectFlag;
    }

    public void setGomoIsoutinspectFlag(int gomoIsoutinspectFlag) {
        this.gomoIsoutinspectFlag = gomoIsoutinspectFlag;
    }

    public BigDecimal getGomoModType() {
        return gomoModType;
    }

    public void setGomoModType(BigDecimal gomoModType) {
        this.gomoModType = gomoModType;
    }

    public int getRpak_isdefault() {
        return rpak_isdefault;
    }

    public void setRpak_isdefault(int rpak_isdefault) {
        this.rpak_isdefault = rpak_isdefault;
    }

    public String getGomoSupplierName() {
        return gomoSupplierName;
    }

    public void setGomoSupplierName(String gomoSupplierName) {
        this.gomoSupplierName = gomoSupplierName;
    }

    public String getGomoSupplierNameOld() {
        return gomoSupplierNameOld;
    }

    public void setGomoSupplierNameOld(String gomoSupplierNameOld) {
        this.gomoSupplierNameOld = gomoSupplierNameOld;
    }

    public String getRpakName() {
        return rpakName;
    }

    public void setRpakName(String rpakName) {
        this.rpakName = rpakName;
    }
}
