package com.huntkey.rx.purchase.common.model.supplier;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.List;

/**
 * 供应商维护单-供应商属性集DTO
 *
 * @author zhangyu
 * @create 2018-01-02 19:46
 **/
public class SumoSupplierSetDTO implements Serializable {

    /**
     * id
     */
    @JSONField(name = "id")
    private String id;

    /**
     * pid
     */
    @JSONField(name = "pid")
    private String pid;

    /**
     * 供应商类别
     */
    @JSONField(name = "sumo_supptype")
    private String sumoSupptype;

    /**
     * 供应商类别(旧)
     */
    @JSONField(name = "sumo_supptype_old")
    private String sumoSupptypeOld;

    /**
     * 状态
     */
    @JSONField(name = "sumo_stat")
    private String sumoStat;

    /**
     * 状态(旧)
     */
    @JSONField(name = "sumo_stat_old")
    private String sumoStatOld;

    /**
     * 服务部门
     */
    @JSONField(name = "sumo_servdept")
    private String sumoServdept;

    /**
     * 服务部门名称
     */
    private String sumoServdeptName;

    /**
     * 服务部门(旧)
     */
    @JSONField(name = "sumo_servdept_old")
    private String sumoServdeptOld;

    /**
     * 税率
     */
    @JSONField(name = "sumo_taxrate")
    private String sumoTaxrate;

    /**
     * 税率(旧)
     */
    @JSONField(name = "sumo_taxrate_old")
    private String sumoTaxrateOld;

    /**
     * 结算方式
     */
    @JSONField(name = "sumo_sett_way")
    private String sumoSettWay;

    /**
     * 结算方式
     */
    private String sumoSettWayDesc;

    /**
     * 结算方式(旧)
     */
    @JSONField(name = "sumo_sett_way_old")
    private String sumoSettWayOld;

    /**
     * 联系人
     */
    @JSONField(name = "sumo_contact_set")
    private List<SumoContactSetDTO> sumoContactSet;

    /**
     * 附件资料
     */
    @JSONField(name = "sumo_attach_set")
    private List<SumoAttachSetDTO> sumoAttachSet;

    /**
     * 服务团队
     */
    @JSONField(name = "sumo_servt_set")
    private List<SumoServtSetDTO> sumoServtSet;

    /**
     * 账户管理
     */
    @JSONField(name = "sumo_account_set")
    private List<SumoAccountSetDTO> sumoAccountSet;

    /**
     * 交货管理
     */
    @JSONField(name = "sumo_deli_set")
    private List<SumoDeliSetDTO> sumoDeliSet;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getSumoSupptype() {
        return sumoSupptype;
    }

    public void setSumoSupptype(String sumoSupptype) {
        this.sumoSupptype = sumoSupptype;
    }

    public String getSumoSupptypeOld() {
        return sumoSupptypeOld;
    }

    public void setSumoSupptypeOld(String sumoSupptypeOld) {
        this.sumoSupptypeOld = sumoSupptypeOld;
    }

    public String getSumoStat() {
        return sumoStat;
    }

    public void setSumoStat(String sumoStat) {
        this.sumoStat = sumoStat;
    }

    public String getSumoStatOld() {
        return sumoStatOld;
    }

    public void setSumoStatOld(String sumoStatOld) {
        this.sumoStatOld = sumoStatOld;
    }

    public String getSumoServdept() {
        return sumoServdept;
    }

    public void setSumoServdept(String sumoServdept) {
        this.sumoServdept = sumoServdept;
    }

    public String getSumoServdeptOld() {
        return sumoServdeptOld;
    }

    public void setSumoServdeptOld(String sumoServdeptOld) {
        this.sumoServdeptOld = sumoServdeptOld;
    }

    public String getSumoTaxrate() {
        return sumoTaxrate;
    }

    public void setSumoTaxrate(String sumoTaxrate) {
        this.sumoTaxrate = sumoTaxrate;
    }

    public String getSumoTaxrateOld() {
        return sumoTaxrateOld;
    }

    public void setSumoTaxrateOld(String sumoTaxrateOld) {
        this.sumoTaxrateOld = sumoTaxrateOld;
    }

    public String getSumoSettWay() {
        return sumoSettWay;
    }

    public void setSumoSettWay(String sumoSettWay) {
        this.sumoSettWay = sumoSettWay;
    }

    public String getSumoSettWayOld() {
        return sumoSettWayOld;
    }

    public void setSumoSettWayOld(String sumoSettWayOld) {
        this.sumoSettWayOld = sumoSettWayOld;
    }

    public List<SumoContactSetDTO> getSumoContactSet() {
        return sumoContactSet;
    }

    public void setSumoContactSet(List<SumoContactSetDTO> sumoContactSet) {
        this.sumoContactSet = sumoContactSet;
    }

    public List<SumoAttachSetDTO> getSumoAttachSet() {
        return sumoAttachSet;
    }

    public void setSumoAttachSet(List<SumoAttachSetDTO> sumoAttachSet) {
        this.sumoAttachSet = sumoAttachSet;
    }

    public List<SumoServtSetDTO> getSumoServtSet() {
        return sumoServtSet;
    }

    public void setSumoServtSet(List<SumoServtSetDTO> sumoServtSet) {
        this.sumoServtSet = sumoServtSet;
    }

    public List<SumoAccountSetDTO> getSumoAccountSet() {
        return sumoAccountSet;
    }

    public void setSumoAccountSet(List<SumoAccountSetDTO> sumoAccountSet) {
        this.sumoAccountSet = sumoAccountSet;
    }

    public List<SumoDeliSetDTO> getSumoDeliSet() {
        return sumoDeliSet;
    }

    public void setSumoDeliSet(List<SumoDeliSetDTO> sumoDeliSet) {
        this.sumoDeliSet = sumoDeliSet;
    }

    public String getSumoServdeptName() {
        return sumoServdeptName;
    }

    public void setSumoServdeptName(String sumoServdeptName) {
        this.sumoServdeptName = sumoServdeptName;
    }

    public String getSumoSettWayDesc() {
        return sumoSettWayDesc;
    }

    public void setSumoSettWayDesc(String sumoSettWayDesc) {
        this.sumoSettWayDesc = sumoSettWayDesc;
    }

    @Override
    public String toString() {
        return "SumoSupplierSetDTO{" +
                "id='" + id + '\'' +
                ", pid='" + pid + '\'' +
                ", sumoSupptype='" + sumoSupptype + '\'' +
                ", sumoSupptypeOld='" + sumoSupptypeOld + '\'' +
                ", sumoStat='" + sumoStat + '\'' +
                ", sumoStatOld='" + sumoStatOld + '\'' +
                ", sumoServdept='" + sumoServdept + '\'' +
                ", sumoServdeptName='" + sumoServdeptName + '\'' +
                ", sumoServdeptOld='" + sumoServdeptOld + '\'' +
                ", sumoTaxrate='" + sumoTaxrate + '\'' +
                ", sumoTaxrateOld='" + sumoTaxrateOld + '\'' +
                ", sumoSettWay='" + sumoSettWay + '\'' +
                ", sumoSettWayDesc='" + sumoSettWayDesc + '\'' +
                ", sumoSettWayOld='" + sumoSettWayOld + '\'' +
                ", sumoContactSet=" + sumoContactSet +
                ", sumoAttachSet=" + sumoAttachSet +
                ", sumoServtSet=" + sumoServtSet +
                ", sumoAccountSet=" + sumoAccountSet +
                ", sumoDeliSet=" + sumoDeliSet +
                '}';
    }
}