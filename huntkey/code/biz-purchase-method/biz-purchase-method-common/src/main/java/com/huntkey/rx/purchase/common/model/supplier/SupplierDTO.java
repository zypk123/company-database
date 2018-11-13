package com.huntkey.rx.purchase.common.model.supplier;

import java.io.Serializable;
import java.util.Date;

/**
 * 供应商DTO
 *
 * @author zhangyu
 * @create 2017-12-29 15:38
 **/
public class SupplierDTO implements Serializable {

    /**
     * ID
     */
    private String id;

    /**
     * 伙伴编号
     */
    private String relaCode;

    /**
     * 伙伴简称
     */
    private String relaShortName;

    /**
     * 状态
     */
    private String relaStatus;

    /**
     * 备注
     */
    private String relaRemark;

    /**
     * 服务部门
     */
    private String relaServdeptSupp;

    /**
     * 服务人员
     */
    private String relaStempSupp;

    /**
     * 维护人
     */
    private String moduser;

    /**
     * 维护时间
     */
    private Date modtime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRelaCode() {
        return relaCode;
    }

    public void setRelaCode(String relaCode) {
        this.relaCode = relaCode;
    }

    public String getRelaShortName() {
        return relaShortName;
    }

    public void setRelaShortName(String relaShortName) {
        this.relaShortName = relaShortName;
    }

    public String getRelaStatus() {
        return relaStatus;
    }

    public void setRelaStatus(String relaStatus) {
        this.relaStatus = relaStatus;
    }

    public String getRelaRemark() {
        return relaRemark;
    }

    public void setRelaRemark(String relaRemark) {
        this.relaRemark = relaRemark;
    }

    public String getRelaServdeptSupp() {
        return relaServdeptSupp;
    }

    public void setRelaServdeptSupp(String relaServdeptSupp) {
        this.relaServdeptSupp = relaServdeptSupp;
    }

    public String getRelaStempSupp() {
        return relaStempSupp;
    }

    public void setRelaStempSupp(String relaStempSupp) {
        this.relaStempSupp = relaStempSupp;
    }

    public String getModuser() {
        return moduser;
    }

    public void setModuser(String moduser) {
        this.moduser = moduser;
    }

    public Date getModtime() {
        return modtime;
    }

    public void setModtime(Date modtime) {
        this.modtime = modtime;
    }

    @Override
    public String toString() {
        return "SupplierDTO{" +
                "id='" + id + '\'' +
                ", relaCode='" + relaCode + '\'' +
                ", relaShortName='" + relaShortName + '\'' +
                ", relaStatus='" + relaStatus + '\'' +
                ", relaRemark='" + relaRemark + '\'' +
                ", relaServdeptSupp='" + relaServdeptSupp + '\'' +
                ", relaStempSupp='" + relaStempSupp + '\'' +
                ", moduser='" + moduser + '\'' +
                ", modtime=" + modtime +
                '}';
    }
}
