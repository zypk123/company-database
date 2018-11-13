package com.huntkey.rx.purchase.common.model.custom;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.List;

/**
 * 客户维护单-客户属性集DTO
 *
 * @author zhangyu
 * @create 2018-01-02 19:46
 **/
public class CumoCustSetDTO implements Serializable {

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
     * 状态
     */
    @JSONField(name = "cumo_stat")
    private String cumoStat;

    /**
     * 状态(旧)
     */
    @JSONField(name = "cumo_stat_old")
    private String cumoStatOld;

    /**
     * 服务部门
     */
    @JSONField(name = "cumo_servdept")
    private String cumoServdept;

    /**
     * 服务部门名称
     */
    private String cumoServdeptName;

    /**
     * 服务部门(旧)
     */
    @JSONField(name = "cumo_servdept_old")
    private String cumoServdeptOld;

    /**
     * 信用额度
     */
    @JSONField(name = "cumo_credlimit")
    private String cumoCredlimit;

    /**
     * 信用额度(旧)
     */
    @JSONField(name = "cumo_credlimit_old")
    private String cumoCredlimitOld;

    /**
     * 结算方式
     */
    @JSONField(name = "cumo_sett_way")
    private String cumoSettWay;

    /**
     * 结算方式描述
     */
    private String cumoSettWayDesc;

    /**
     * 结算方式(旧)
     */
    @JSONField(name = "cumo_sett_way_old")
    private String cumoSettWayOld;

    /**
     * 联系人
     */
    @JSONField(name = "cumo_contact_set")
    private List<CumoContactSetDTO> cumoContactSet;

    /**
     * 附件资料
     */
    @JSONField(name = "cumo_attach_set")
    private List<CumoAttachSetDTO> cumoAttachSet;

    /**
     * 服务团队
     */
    @JSONField(name = "cumo_servt_set")
    private List<CumoServtSetDTO> cumoServtSet;

    /**
     * 账户管理
     */
    @JSONField(name = "cumo_account_set")
    private List<CumoAccountSetDTO> cumoAccountSet;

    /**
     * 交货管理
     */
    @JSONField(name = "cumo_deli_set")
    private List<CumoDeliSetDTO> cumoDeliSet;

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

    public String getCumoStat() {
        return cumoStat;
    }

    public void setCumoStat(String cumoStat) {
        this.cumoStat = cumoStat;
    }

    public String getCumoStatOld() {
        return cumoStatOld;
    }

    public void setCumoStatOld(String cumoStatOld) {
        this.cumoStatOld = cumoStatOld;
    }

    public String getCumoServdept() {
        return cumoServdept;
    }

    public void setCumoServdept(String cumoServdept) {
        this.cumoServdept = cumoServdept;
    }

    public String getCumoServdeptOld() {
        return cumoServdeptOld;
    }

    public void setCumoServdeptOld(String cumoServdeptOld) {
        this.cumoServdeptOld = cumoServdeptOld;
    }

    public String getCumoCredlimit() {
        return cumoCredlimit;
    }

    public void setCumoCredlimit(String cumoCredlimit) {
        this.cumoCredlimit = cumoCredlimit;
    }

    public String getCumoCredlimitOld() {
        return cumoCredlimitOld;
    }

    public void setCumoCredlimitOld(String cumoCredlimitOld) {
        this.cumoCredlimitOld = cumoCredlimitOld;
    }

    public String getCumoSettWay() {
        return cumoSettWay;
    }

    public void setCumoSettWay(String cumoSettWay) {
        this.cumoSettWay = cumoSettWay;
    }

    public String getCumoSettWayOld() {
        return cumoSettWayOld;
    }

    public void setCumoSettWayOld(String cumoSettWayOld) {
        this.cumoSettWayOld = cumoSettWayOld;
    }

    public List<CumoContactSetDTO> getCumoContactSet() {
        return cumoContactSet;
    }

    public void setCumoContactSet(List<CumoContactSetDTO> cumoContactSet) {
        this.cumoContactSet = cumoContactSet;
    }

    public List<CumoAttachSetDTO> getCumoAttachSet() {
        return cumoAttachSet;
    }

    public void setCumoAttachSet(List<CumoAttachSetDTO> cumoAttachSet) {
        this.cumoAttachSet = cumoAttachSet;
    }

    public List<CumoServtSetDTO> getCumoServtSet() {
        return cumoServtSet;
    }

    public void setCumoServtSet(List<CumoServtSetDTO> cumoServtSet) {
        this.cumoServtSet = cumoServtSet;
    }

    public List<CumoAccountSetDTO> getCumoAccountSet() {
        return cumoAccountSet;
    }

    public void setCumoAccountSet(List<CumoAccountSetDTO> cumoAccountSet) {
        this.cumoAccountSet = cumoAccountSet;
    }

    public List<CumoDeliSetDTO> getCumoDeliSet() {
        return cumoDeliSet;
    }

    public void setCumoDeliSet(List<CumoDeliSetDTO> cumoDeliSet) {
        this.cumoDeliSet = cumoDeliSet;
    }

    public String getCumoServdeptName() {
        return cumoServdeptName;
    }

    public void setCumoServdeptName(String cumoServdeptName) {
        this.cumoServdeptName = cumoServdeptName;
    }

    public String getCumoSettWayDesc() {
        return cumoSettWayDesc;
    }

    public void setCumoSettWayDesc(String cumoSettWayDesc) {
        this.cumoSettWayDesc = cumoSettWayDesc;
    }

    @Override
    public String toString() {
        return "CumoCustSetDTO{" +
                "id='" + id + '\'' +
                ", pid='" + pid + '\'' +
                ", cumoStat='" + cumoStat + '\'' +
                ", cumoStatOld='" + cumoStatOld + '\'' +
                ", cumoServdept='" + cumoServdept + '\'' +
                ", cumoServdeptName='" + cumoServdeptName + '\'' +
                ", cumoServdeptOld='" + cumoServdeptOld + '\'' +
                ", cumoCredlimit='" + cumoCredlimit + '\'' +
                ", cumoCredlimitOld='" + cumoCredlimitOld + '\'' +
                ", cumoSettWay='" + cumoSettWay + '\'' +
                ", cumoSettWayDesc='" + cumoSettWayDesc + '\'' +
                ", cumoSettWayOld='" + cumoSettWayOld + '\'' +
                ", cumoContactSet=" + cumoContactSet +
                ", cumoAttachSet=" + cumoAttachSet +
                ", cumoServtSet=" + cumoServtSet +
                ", cumoAccountSet=" + cumoAccountSet +
                ", cumoDeliSet=" + cumoDeliSet +
                '}';
    }
}