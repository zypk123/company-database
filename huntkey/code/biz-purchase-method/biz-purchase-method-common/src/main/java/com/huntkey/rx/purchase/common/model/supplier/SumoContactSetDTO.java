package com.huntkey.rx.purchase.common.model.supplier;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 供应商维护单-供应商属性集-联系人DTO
 *
 * @author zhangyu
 * @create 2018-01-03 16:54
 **/
public class SumoContactSetDTO implements Serializable {

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
     * 联系人类型
     */
    @JSONField(name = "sumo_conttype")
    private String sumoConttype;

    /**
     * 联系人类型(旧)
     */
    @JSONField(name = "sumo_conttype_old")
    private String sumoConttypeOld;

    /**
     * 姓名
     */
    @JSONField(name = "sumo_contname")
    private String sumoContname;

    /**
     * 姓名(旧)
     */
    @JSONField(name = "sumo_contname_old")
    private String sumoContnameOld;

    /**
     * 性别
     */
    @JSONField(name = "sumo_contsex")
    private String sumoContsex;

    /**
     * 性别(旧)
     */
    @JSONField(name = "sumo_contsex_old")
    private String sumoContsexOld;

    /**
     * 职务
     */
    @JSONField(name = "sumo_contpost")
    private String sumoContpost;

    /**
     * 职务(旧)
     */
    @JSONField(name = "sumo_contpost_old")
    private String sumoContpostOld;

    /**
     * 电话
     */
    @JSONField(name = "sumo_conttel")
    private String sumoConttel;

    /**
     * 电话(旧)
     */
    @JSONField(name = "sumo_conttel_old")
    private String sumoConttelOld;

    /**
     * 邮箱
     */
    @JSONField(name = "sumo_contemail")
    private String sumoContemail;

    /**
     * 邮箱(旧)
     */
    @JSONField(name = "sumo_contemail_old")
    private String sumoContemailOld;

    /**
     * QQ/微信/其它
     */
    @JSONField(name = "sumo_contother")
    private String sumoContother;

    /**
     * QQ/微信/其它(旧)
     */
    @JSONField(name = "sumo_contother_old")
    private String sumoContotherOld;

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

    public String getSumoConttype() {
        return sumoConttype;
    }

    public void setSumoConttype(String sumoConttype) {
        this.sumoConttype = sumoConttype;
    }

    public String getSumoConttypeOld() {
        return sumoConttypeOld;
    }

    public void setSumoConttypeOld(String sumoConttypeOld) {
        this.sumoConttypeOld = sumoConttypeOld;
    }

    public String getSumoContname() {
        return sumoContname;
    }

    public void setSumoContname(String sumoContname) {
        this.sumoContname = sumoContname;
    }

    public String getSumoContnameOld() {
        return sumoContnameOld;
    }

    public void setSumoContnameOld(String sumoContnameOld) {
        this.sumoContnameOld = sumoContnameOld;
    }

    public String getSumoContsex() {
        return sumoContsex;
    }

    public void setSumoContsex(String sumoContsex) {
        this.sumoContsex = sumoContsex;
    }

    public String getSumoContsexOld() {
        return sumoContsexOld;
    }

    public void setSumoContsexOld(String sumoContsexOld) {
        this.sumoContsexOld = sumoContsexOld;
    }

    public String getSumoContpost() {
        return sumoContpost;
    }

    public void setSumoContpost(String sumoContpost) {
        this.sumoContpost = sumoContpost;
    }

    public String getSumoContpostOld() {
        return sumoContpostOld;
    }

    public void setSumoContpostOld(String sumoContpostOld) {
        this.sumoContpostOld = sumoContpostOld;
    }

    public String getSumoConttel() {
        return sumoConttel;
    }

    public void setSumoConttel(String sumoConttel) {
        this.sumoConttel = sumoConttel;
    }

    public String getSumoConttelOld() {
        return sumoConttelOld;
    }

    public void setSumoConttelOld(String sumoConttelOld) {
        this.sumoConttelOld = sumoConttelOld;
    }

    public String getSumoContemail() {
        return sumoContemail;
    }

    public void setSumoContemail(String sumoContemail) {
        this.sumoContemail = sumoContemail;
    }

    public String getSumoContemailOld() {
        return sumoContemailOld;
    }

    public void setSumoContemailOld(String sumoContemailOld) {
        this.sumoContemailOld = sumoContemailOld;
    }

    public String getSumoContother() {
        return sumoContother;
    }

    public void setSumoContother(String sumoContother) {
        this.sumoContother = sumoContother;
    }

    public String getSumoContotherOld() {
        return sumoContotherOld;
    }

    public void setSumoContotherOld(String sumoContotherOld) {
        this.sumoContotherOld = sumoContotherOld;
    }

    @Override
    public String toString() {
        return "SumoContactSetDTO{" +
                "id='" + id + '\'' +
                ", pid='" + pid + '\'' +
                ", sumoConttype='" + sumoConttype + '\'' +
                ", sumoConttypeOld='" + sumoConttypeOld + '\'' +
                ", sumoContname='" + sumoContname + '\'' +
                ", sumoContnameOld='" + sumoContnameOld + '\'' +
                ", sumoContsex='" + sumoContsex + '\'' +
                ", sumoContsexOld='" + sumoContsexOld + '\'' +
                ", sumoContpost='" + sumoContpost + '\'' +
                ", sumoContpostOld='" + sumoContpostOld + '\'' +
                ", sumoConttel='" + sumoConttel + '\'' +
                ", sumoConttelOld='" + sumoConttelOld + '\'' +
                ", sumoContemail='" + sumoContemail + '\'' +
                ", sumoContemailOld='" + sumoContemailOld + '\'' +
                ", sumoContother='" + sumoContother + '\'' +
                ", sumoContotherOld='" + sumoContotherOld + '\'' +
                '}';
    }
}
