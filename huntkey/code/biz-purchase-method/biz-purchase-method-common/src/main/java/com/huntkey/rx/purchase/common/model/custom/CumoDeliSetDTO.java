package com.huntkey.rx.purchase.common.model.custom;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 客户维护单-客户属性集-交货管理DTO
 *
 * @author zhangyu
 * @create 2018-01-03 17:07
 **/
public class CumoDeliSetDTO implements Serializable {

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
     * 地址简称
     */
    @JSONField(name = "cumo_daname")
    private String cumoDaname;

    /**
     * 地址简称(旧)
     */
    @JSONField(name = "cumo_daname_old")
    private String cumoDanameOld;

    /**
     * 交货省地址
     */
    @JSONField(name = "cumo_daddrp")
    private String cumoDaddrp;

    /**
     * 交货省地址(旧)
     */
    @JSONField(name = "cumo_daddrp_old")
    private String cumoDaddrpOld;

    /**
     * 交货市地址
     */
    @JSONField(name = "cumo_daddrc")
    private String cumoDaddrc;

    /**
     * 交货市地址(旧)
     */
    @JSONField(name = "cumo_daddrc_old")
    private String cumoDaddrcOld;

    /**
     * 交货区地址
     */
    @JSONField(name = "cumo_daddrl")
    private String cumoDaddrl;

    /**
     * 交货区地址(旧)
     */
    @JSONField(name = "cumo_daddrl_old")
    private String cumoDaddrlOld;

    /**
     * 详细地址
     */
    @JSONField(name = "cumo_ddaddr")
    private String cumoDdaddr;

    /**
     * 详细地址(旧)
     */
    @JSONField(name = "cumo_ddaddr_old")
    private String cumoDdaddrOld;

    /**
     * 联系人
     */
    @JSONField(name = "cumo_dcontact")
    private String cumoDcontact;

    /**
     * 联系人(旧)
     */
    @JSONField(name = "cumo_dcontact_old")
    private String cumoDcontactOld;

    /**
     * 联系方式
     */
    @JSONField(name = "cumo_dcway")
    private String cumoDcway;

    /**
     * 联系方式(旧)
     */
    @JSONField(name = "cumo_dcway_old")
    private String cumoDcwayOld;

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

    public String getCumoDaname() {
        return cumoDaname;
    }

    public void setCumoDaname(String cumoDaname) {
        this.cumoDaname = cumoDaname;
    }

    public String getCumoDanameOld() {
        return cumoDanameOld;
    }

    public void setCumoDanameOld(String cumoDanameOld) {
        this.cumoDanameOld = cumoDanameOld;
    }

    public String getCumoDaddrp() {
        return cumoDaddrp;
    }

    public void setCumoDaddrp(String cumoDaddrp) {
        this.cumoDaddrp = cumoDaddrp;
    }

    public String getCumoDaddrpOld() {
        return cumoDaddrpOld;
    }

    public void setCumoDaddrpOld(String cumoDaddrpOld) {
        this.cumoDaddrpOld = cumoDaddrpOld;
    }

    public String getCumoDaddrc() {
        return cumoDaddrc;
    }

    public void setCumoDaddrc(String cumoDaddrc) {
        this.cumoDaddrc = cumoDaddrc;
    }

    public String getCumoDaddrcOld() {
        return cumoDaddrcOld;
    }

    public void setCumoDaddrcOld(String cumoDaddrcOld) {
        this.cumoDaddrcOld = cumoDaddrcOld;
    }

    public String getCumoDaddrl() {
        return cumoDaddrl;
    }

    public void setCumoDaddrl(String cumoDaddrl) {
        this.cumoDaddrl = cumoDaddrl;
    }

    public String getCumoDaddrlOld() {
        return cumoDaddrlOld;
    }

    public void setCumoDaddrlOld(String cumoDaddrlOld) {
        this.cumoDaddrlOld = cumoDaddrlOld;
    }

    public String getCumoDdaddr() {
        return cumoDdaddr;
    }

    public void setCumoDdaddr(String cumoDdaddr) {
        this.cumoDdaddr = cumoDdaddr;
    }

    public String getCumoDdaddrOld() {
        return cumoDdaddrOld;
    }

    public void setCumoDdaddrOld(String cumoDdaddrOld) {
        this.cumoDdaddrOld = cumoDdaddrOld;
    }

    public String getCumoDcontact() {
        return cumoDcontact;
    }

    public void setCumoDcontact(String cumoDcontact) {
        this.cumoDcontact = cumoDcontact;
    }

    public String getCumoDcontactOld() {
        return cumoDcontactOld;
    }

    public void setCumoDcontactOld(String cumoDcontactOld) {
        this.cumoDcontactOld = cumoDcontactOld;
    }

    public String getCumoDcway() {
        return cumoDcway;
    }

    public void setCumoDcway(String cumoDcway) {
        this.cumoDcway = cumoDcway;
    }

    public String getCumoDcwayOld() {
        return cumoDcwayOld;
    }

    public void setCumoDcwayOld(String cumoDcwayOld) {
        this.cumoDcwayOld = cumoDcwayOld;
    }

    @Override
    public String toString() {
        return "CumoDeliSetDTO{" +
                "id='" + id + '\'' +
                ", pid='" + pid + '\'' +
                ", cumoDaname='" + cumoDaname + '\'' +
                ", cumoDanameOld='" + cumoDanameOld + '\'' +
                ", cumoDaddrp='" + cumoDaddrp + '\'' +
                ", cumoDaddrpOld='" + cumoDaddrpOld + '\'' +
                ", cumoDaddrc='" + cumoDaddrc + '\'' +
                ", cumoDaddrcOld='" + cumoDaddrcOld + '\'' +
                ", cumoDaddrl='" + cumoDaddrl + '\'' +
                ", cumoDaddrlOld='" + cumoDaddrlOld + '\'' +
                ", cumoDdaddr='" + cumoDdaddr + '\'' +
                ", cumoDdaddrOld='" + cumoDdaddrOld + '\'' +
                ", cumoDcontact='" + cumoDcontact + '\'' +
                ", cumoDcontactOld='" + cumoDcontactOld + '\'' +
                ", cumoDcway='" + cumoDcway + '\'' +
                ", cumoDcwayOld='" + cumoDcwayOld + '\'' +
                '}';
    }
}
