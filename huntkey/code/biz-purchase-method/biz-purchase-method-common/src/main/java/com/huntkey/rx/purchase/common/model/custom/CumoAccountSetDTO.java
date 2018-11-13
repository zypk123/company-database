package com.huntkey.rx.purchase.common.model.custom;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户维护单-客户属性集-账户管理DTO
 *
 * @author zhangyu
 * @create 2018-01-03 17:04
 **/
public class CumoAccountSetDTO implements Serializable {

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
    @JSONField(name = "cumo_acconame")
    private String cumoAcconame;

    /**
     * 开户名称(旧)
     */
    @JSONField(name = "cumo_acconame_old")
    private String cumoAcconameOld;

    /**
     * 开户银行
     */
    @JSONField(name = "cumo_accobank")
    private String cumoAccobank;

    /**
     * 开户银行(旧)
     */
    @JSONField(name = "cumo_accobank_old")
    private String cumoAccobankOld;

    /**
     * 开户账号
     */
    @JSONField(name = "cumo_acconum")
    private String cumoAcconum;

    /**
     * 开户账号(旧)
     */
    @JSONField(name = "cumo_acconum_old")
    private String cumoAcconumOld;

    /**
     * 币别
     */
    @JSONField(name = "cumo_accocurr")
    private String cumoAccocurr;

    /**
     * 币别(旧)
     */
    @JSONField(name = "cumo_accocurr_old")
    private String cumoAccocurrOld;

    /**
     * 生效日期
     */
    @JSONField(name = "cumo_accobeg")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date cumoAccobeg;

    /**
     * 生效日期(旧)
     */
    @JSONField(name = "cumo_accobeg_old")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date cumoAccobegOld;

    /**
     * 失效日期
     */
    @JSONField(name = "cumo_accoend")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date cumoAccoend;

    /**
     * 失效日期(旧)
     */
    @JSONField(name = "cumo_accoend_old")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date cumoAccoendOld;

    /**
     * 附件地址
     */
    @JSONField(name = "cumo_attaacco")
    private String cumoAttaacco;

    /**
     * 附件名称
     */
    @JSONField(name = "cumo_atta_name")
    private String cumoAttaName;

    /**
     * 附件名称(旧)
     */
    @JSONField(name = "cumo_atta_name_old")
    private String cumoAttaNameOld;

    /**
     * 附件地址(旧)
     */
    @JSONField(name = "cumo_attaacco_old")
    private String cumoAttaaccoOld;

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

    public String getCumoAcconame() {
        return cumoAcconame;
    }

    public void setCumoAcconame(String cumoAcconame) {
        this.cumoAcconame = cumoAcconame;
    }

    public String getCumoAcconameOld() {
        return cumoAcconameOld;
    }

    public void setCumoAcconameOld(String cumoAcconameOld) {
        this.cumoAcconameOld = cumoAcconameOld;
    }

    public String getCumoAccobank() {
        return cumoAccobank;
    }

    public void setCumoAccobank(String cumoAccobank) {
        this.cumoAccobank = cumoAccobank;
    }

    public String getCumoAccobankOld() {
        return cumoAccobankOld;
    }

    public void setCumoAccobankOld(String cumoAccobankOld) {
        this.cumoAccobankOld = cumoAccobankOld;
    }

    public String getCumoAcconum() {
        return cumoAcconum;
    }

    public void setCumoAcconum(String cumoAcconum) {
        this.cumoAcconum = cumoAcconum;
    }

    public String getCumoAcconumOld() {
        return cumoAcconumOld;
    }

    public void setCumoAcconumOld(String cumoAcconumOld) {
        this.cumoAcconumOld = cumoAcconumOld;
    }

    public String getCumoAccocurr() {
        return cumoAccocurr;
    }

    public void setCumoAccocurr(String cumoAccocurr) {
        this.cumoAccocurr = cumoAccocurr;
    }

    public String getCumoAccocurrOld() {
        return cumoAccocurrOld;
    }

    public void setCumoAccocurrOld(String cumoAccocurrOld) {
        this.cumoAccocurrOld = cumoAccocurrOld;
    }

    public Date getCumoAccobeg() {
        return cumoAccobeg;
    }

    public void setCumoAccobeg(Date cumoAccobeg) {
        this.cumoAccobeg = cumoAccobeg;
    }

    public Date getCumoAccobegOld() {
        return cumoAccobegOld;
    }

    public void setCumoAccobegOld(Date cumoAccobegOld) {
        this.cumoAccobegOld = cumoAccobegOld;
    }

    public Date getCumoAccoend() {
        return cumoAccoend;
    }

    public void setCumoAccoend(Date cumoAccoend) {
        this.cumoAccoend = cumoAccoend;
    }

    public Date getCumoAccoendOld() {
        return cumoAccoendOld;
    }

    public void setCumoAccoendOld(Date cumoAccoendOld) {
        this.cumoAccoendOld = cumoAccoendOld;
    }

    public String getCumoAttaacco() {
        return cumoAttaacco;
    }

    public void setCumoAttaacco(String cumoAttaacco) {
        this.cumoAttaacco = cumoAttaacco;
    }

    public String getCumoAttaName() {
        return cumoAttaName;
    }

    public void setCumoAttaName(String cumoAttaName) {
        this.cumoAttaName = cumoAttaName;
    }

    public String getCumoAttaNameOld() {
        return cumoAttaNameOld;
    }

    public void setCumoAttaNameOld(String cumoAttaNameOld) {
        this.cumoAttaNameOld = cumoAttaNameOld;
    }

    public String getCumoAttaaccoOld() {
        return cumoAttaaccoOld;
    }

    public void setCumoAttaaccoOld(String cumoAttaaccoOld) {
        this.cumoAttaaccoOld = cumoAttaaccoOld;
    }

    @Override
    public String toString() {
        return "CumoAccountSetDTO{" +
                "id='" + id + '\'' +
                ", pid='" + pid + '\'' +
                ", cumoAcconame='" + cumoAcconame + '\'' +
                ", cumoAcconameOld='" + cumoAcconameOld + '\'' +
                ", cumoAccobank='" + cumoAccobank + '\'' +
                ", cumoAccobankOld='" + cumoAccobankOld + '\'' +
                ", cumoAcconum='" + cumoAcconum + '\'' +
                ", cumoAcconumOld='" + cumoAcconumOld + '\'' +
                ", cumoAccocurr='" + cumoAccocurr + '\'' +
                ", cumoAccocurrOld='" + cumoAccocurrOld + '\'' +
                ", cumoAccobeg=" + cumoAccobeg +
                ", cumoAccobegOld=" + cumoAccobegOld +
                ", cumoAccoend=" + cumoAccoend +
                ", cumoAccoendOld=" + cumoAccoendOld +
                ", cumoAttaacco='" + cumoAttaacco + '\'' +
                ", cumoAttaName='" + cumoAttaName + '\'' +
                ", cumoAttaNameOld='" + cumoAttaNameOld + '\'' +
                ", cumoAttaaccoOld='" + cumoAttaaccoOld + '\'' +
                '}';
    }
}
