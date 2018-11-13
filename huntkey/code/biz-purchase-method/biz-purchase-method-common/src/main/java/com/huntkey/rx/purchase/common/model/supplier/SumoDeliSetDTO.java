package com.huntkey.rx.purchase.common.model.supplier;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 供应商维护单-供应商属性集-交货管理DTO
 *
 * @author zhangyu
 * @create 2018-01-03 17:07
 **/
public class SumoDeliSetDTO implements Serializable {

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
    @JSONField(name = "sumo_daname")
    private String sumoDaname;

    /**
     * 地址简称(旧)
     */
    @JSONField(name = "sumo_daname_old")
    private String sumoDanameOld;

    /**
     * 交货省地址
     */
    @JSONField(name = "sumo_daddrp")
    private String sumoDaddrp;

    /**
     * 交货省地址(旧)
     */
    @JSONField(name = "sumo_daddrp_old")
    private String sumoDaddrpOld;

    /**
     * 交货市地址
     */
    @JSONField(name = "sumo_daddrc")
    private String sumoDaddrc;

    /**
     * 交货市地址(旧)
     */
    @JSONField(name = "sumo_daddrc_old")
    private String sumoDaddrcOld;

    /**
     * 交货区地址
     */
    @JSONField(name = "sumo_daddrl")
    private String sumoDaddrl;

    /**
     * 交货区地址(旧)
     */
    @JSONField(name = "sumo_daddrl_old")
    private String sumoDaddrlOld;

    /**
     * 详细地址
     */
    @JSONField(name = "sumo_ddaddr")
    private String sumoDdaddr;

    /**
     * 详细地址(旧)
     */
    @JSONField(name = "sumo_ddaddr_old")
    private String sumoDdaddrOld;

    /**
     * 联系人
     */
    @JSONField(name = "sumo_dcontact")
    private String sumoDcontact;

    /**
     * 联系人(旧)
     */
    @JSONField(name = "sumo_dcontact_old")
    private String sumoDcontactOld;

    /**
     * 联系方式
     */
    @JSONField(name = "sumo_dcway")
    private String sumoDcway;

    /**
     * 联系方式(旧)
     */
    @JSONField(name = "sumo_dcway_old")
    private String sumoDcwayOld;

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

    public String getSumoDaname() {
        return sumoDaname;
    }

    public void setSumoDaname(String sumoDaname) {
        this.sumoDaname = sumoDaname;
    }

    public String getSumoDanameOld() {
        return sumoDanameOld;
    }

    public void setSumoDanameOld(String sumoDanameOld) {
        this.sumoDanameOld = sumoDanameOld;
    }

    public String getSumoDaddrp() {
        return sumoDaddrp;
    }

    public void setSumoDaddrp(String sumoDaddrp) {
        this.sumoDaddrp = sumoDaddrp;
    }

    public String getSumoDaddrpOld() {
        return sumoDaddrpOld;
    }

    public void setSumoDaddrpOld(String sumoDaddrpOld) {
        this.sumoDaddrpOld = sumoDaddrpOld;
    }

    public String getSumoDaddrc() {
        return sumoDaddrc;
    }

    public void setSumoDaddrc(String sumoDaddrc) {
        this.sumoDaddrc = sumoDaddrc;
    }

    public String getSumoDaddrcOld() {
        return sumoDaddrcOld;
    }

    public void setSumoDaddrcOld(String sumoDaddrcOld) {
        this.sumoDaddrcOld = sumoDaddrcOld;
    }

    public String getSumoDaddrl() {
        return sumoDaddrl;
    }

    public void setSumoDaddrl(String sumoDaddrl) {
        this.sumoDaddrl = sumoDaddrl;
    }

    public String getSumoDaddrlOld() {
        return sumoDaddrlOld;
    }

    public void setSumoDaddrlOld(String sumoDaddrlOld) {
        this.sumoDaddrlOld = sumoDaddrlOld;
    }

    public String getSumoDdaddr() {
        return sumoDdaddr;
    }

    public void setSumoDdaddr(String sumoDdaddr) {
        this.sumoDdaddr = sumoDdaddr;
    }

    public String getSumoDdaddrOld() {
        return sumoDdaddrOld;
    }

    public void setSumoDdaddrOld(String sumoDdaddrOld) {
        this.sumoDdaddrOld = sumoDdaddrOld;
    }

    public String getSumoDcontact() {
        return sumoDcontact;
    }

    public void setSumoDcontact(String sumoDcontact) {
        this.sumoDcontact = sumoDcontact;
    }

    public String getSumoDcontactOld() {
        return sumoDcontactOld;
    }

    public void setSumoDcontactOld(String sumoDcontactOld) {
        this.sumoDcontactOld = sumoDcontactOld;
    }

    public String getSumoDcway() {
        return sumoDcway;
    }

    public void setSumoDcway(String sumoDcway) {
        this.sumoDcway = sumoDcway;
    }

    public String getSumoDcwayOld() {
        return sumoDcwayOld;
    }

    public void setSumoDcwayOld(String sumoDcwayOld) {
        this.sumoDcwayOld = sumoDcwayOld;
    }

    @Override
    public String toString() {
        return "SumoDeliSetDTO{" +
                "id='" + id + '\'' +
                ", pid='" + pid + '\'' +
                ", sumoDaname='" + sumoDaname + '\'' +
                ", sumoDanameOld='" + sumoDanameOld + '\'' +
                ", sumoDaddrp='" + sumoDaddrp + '\'' +
                ", sumoDaddrpOld='" + sumoDaddrpOld + '\'' +
                ", sumoDaddrc='" + sumoDaddrc + '\'' +
                ", sumoDaddrcOld='" + sumoDaddrcOld + '\'' +
                ", sumoDaddrl='" + sumoDaddrl + '\'' +
                ", sumoDaddrlOld='" + sumoDaddrlOld + '\'' +
                ", sumoDdaddr='" + sumoDdaddr + '\'' +
                ", sumoDdaddrOld='" + sumoDdaddrOld + '\'' +
                ", sumoDcontact='" + sumoDcontact + '\'' +
                ", sumoDcontactOld='" + sumoDcontactOld + '\'' +
                ", sumoDcway='" + sumoDcway + '\'' +
                ", sumoDcwayOld='" + sumoDcwayOld + '\'' +
                '}';
    }
}
