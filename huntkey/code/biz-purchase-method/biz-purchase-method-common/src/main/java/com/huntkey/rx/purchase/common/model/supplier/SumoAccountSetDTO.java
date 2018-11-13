package com.huntkey.rx.purchase.common.model.supplier;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 供应商维护单-供应商属性集-账户管理DTO
 *
 * @author zhangyu
 * @create 2018-01-03 17:04
 **/
public class SumoAccountSetDTO implements Serializable {

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
     * 开户名称
     */
    @JSONField(name = "sumo_acconame")
    private String sumoAcconame;

    /**
     * 开户名称(旧)
     */
    @JSONField(name = "sumo_acconame_old")
    private String sumoAcconameOld;

    /**
     * 开户银行
     */
    @JSONField(name = "sumo_accobank")
    private String sumoAccobank;

    /**
     * 开户银行(旧)
     */
    @JSONField(name = "sumo_accobank_old")
    private String sumoAccobankOld;

    /**
     * 开户账号
     */
    @JSONField(name = "sumo_acconum")
    private String sumoAcconum;

    /**
     * 开户账号(旧)
     */
    @JSONField(name = "sumo_acconum_old")
    private String sumoAcconumOld;

    /**
     * 币别
     */
    @JSONField(name = "sumo_accocurr")
    private String sumoAccocurr;

    /**
     * 币别(旧)
     */
    @JSONField(name = "sumo_accocurr_old")
    private String sumoAccocurrOld;

    /**
     * 生效日期
     */
    @JSONField(name = "sumo_accobeg")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date sumoAccobeg;

    /**
     * 生效日期(旧)
     */
    @JSONField(name = "sumo_accobeg_old")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date sumoAccobegOld;

    /**
     * 失效日期
     */
    @JSONField(name = "sumo_accoend")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date sumoAccoend;

    /**
     * 失效日期(旧)
     */
    @JSONField(name = "sumo_accoend_old")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date sumoAccoendOld;

    /**
     * 附件名称
     */
    @JSONField(name = "sumo_atta_name")
    private String sumoAttaName;

    /**
     * 附件名称(旧)
     */
    @JSONField(name = "sumo_atta_name_old")
    private String sumoAttaNameOld;

    /**
     * 附件地址
     */
    @JSONField(name = "sumo_attaacco")
    private String sumoAttaacco;

    /**
     * 附件地址(旧)
     */
    @JSONField(name = "sumo_attaacco_old")
    private String sumoAttaaccoOld;

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

    public String getSumoAcconame() {
        return sumoAcconame;
    }

    public void setSumoAcconame(String sumoAcconame) {
        this.sumoAcconame = sumoAcconame;
    }

    public String getSumoAcconameOld() {
        return sumoAcconameOld;
    }

    public void setSumoAcconameOld(String sumoAcconameOld) {
        this.sumoAcconameOld = sumoAcconameOld;
    }

    public String getSumoAccobank() {
        return sumoAccobank;
    }

    public void setSumoAccobank(String sumoAccobank) {
        this.sumoAccobank = sumoAccobank;
    }

    public String getSumoAccobankOld() {
        return sumoAccobankOld;
    }

    public void setSumoAccobankOld(String sumoAccobankOld) {
        this.sumoAccobankOld = sumoAccobankOld;
    }

    public String getSumoAcconum() {
        return sumoAcconum;
    }

    public void setSumoAcconum(String sumoAcconum) {
        this.sumoAcconum = sumoAcconum;
    }

    public String getSumoAcconumOld() {
        return sumoAcconumOld;
    }

    public void setSumoAcconumOld(String sumoAcconumOld) {
        this.sumoAcconumOld = sumoAcconumOld;
    }

    public String getSumoAccocurr() {
        return sumoAccocurr;
    }

    public void setSumoAccocurr(String sumoAccocurr) {
        this.sumoAccocurr = sumoAccocurr;
    }

    public String getSumoAccocurrOld() {
        return sumoAccocurrOld;
    }

    public void setSumoAccocurrOld(String sumoAccocurrOld) {
        this.sumoAccocurrOld = sumoAccocurrOld;
    }

    public Date getSumoAccobeg() {
        return sumoAccobeg;
    }

    public void setSumoAccobeg(Date sumoAccobeg) {
        this.sumoAccobeg = sumoAccobeg;
    }

    public Date getSumoAccobegOld() {
        return sumoAccobegOld;
    }

    public void setSumoAccobegOld(Date sumoAccobegOld) {
        this.sumoAccobegOld = sumoAccobegOld;
    }

    public Date getSumoAccoend() {
        return sumoAccoend;
    }

    public void setSumoAccoend(Date sumoAccoend) {
        this.sumoAccoend = sumoAccoend;
    }

    public Date getSumoAccoendOld() {
        return sumoAccoendOld;
    }

    public void setSumoAccoendOld(Date sumoAccoendOld) {
        this.sumoAccoendOld = sumoAccoendOld;
    }

    public String getSumoAttaName() {
        return sumoAttaName;
    }

    public void setSumoAttaName(String sumoAttaName) {
        this.sumoAttaName = sumoAttaName;
    }

    public String getSumoAttaNameOld() {
        return sumoAttaNameOld;
    }

    public void setSumoAttaNameOld(String sumoAttaNameOld) {
        this.sumoAttaNameOld = sumoAttaNameOld;
    }

    public String getSumoAttaacco() {
        return sumoAttaacco;
    }

    public void setSumoAttaacco(String sumoAttaacco) {
        this.sumoAttaacco = sumoAttaacco;
    }

    public String getSumoAttaaccoOld() {
        return sumoAttaaccoOld;
    }

    public void setSumoAttaaccoOld(String sumoAttaaccoOld) {
        this.sumoAttaaccoOld = sumoAttaaccoOld;
    }

    @Override
    public String toString() {
        return "SumoAccountSetDTO{" +
                "id='" + id + '\'' +
                ", pid='" + pid + '\'' +
                ", sumoAcconame='" + sumoAcconame + '\'' +
                ", sumoAcconameOld='" + sumoAcconameOld + '\'' +
                ", sumoAccobank='" + sumoAccobank + '\'' +
                ", sumoAccobankOld='" + sumoAccobankOld + '\'' +
                ", sumoAcconum='" + sumoAcconum + '\'' +
                ", sumoAcconumOld='" + sumoAcconumOld + '\'' +
                ", sumoAccocurr='" + sumoAccocurr + '\'' +
                ", sumoAccocurrOld='" + sumoAccocurrOld + '\'' +
                ", sumoAccobeg=" + sumoAccobeg +
                ", sumoAccobegOld=" + sumoAccobegOld +
                ", sumoAccoend=" + sumoAccoend +
                ", sumoAccoendOld=" + sumoAccoendOld +
                ", sumoAttaName='" + sumoAttaName + '\'' +
                ", sumoAttaNameOld='" + sumoAttaNameOld + '\'' +
                ", sumoAttaacco='" + sumoAttaacco + '\'' +
                ", sumoAttaaccoOld='" + sumoAttaaccoOld + '\'' +
                '}';
    }
}
