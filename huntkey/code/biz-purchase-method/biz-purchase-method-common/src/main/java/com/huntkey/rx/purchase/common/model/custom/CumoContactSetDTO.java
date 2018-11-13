package com.huntkey.rx.purchase.common.model.custom;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 客户维护单-客户属性集-联系人DTO
 *
 * @author zhangyu
 * @create 2018-01-03 16:54
 **/
public class CumoContactSetDTO implements Serializable {

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
    @JSONField(name = "cumo_conttype")
    private String cumoConttype;

    /**
     * 联系人类型(旧)
     */
    @JSONField(name = "cumo_conttype_old")
    private String cumoConttypeOld;

    /**
     * 姓名
     */
    @JSONField(name = "cumo_contname")
    private String cumoContname;

    /**
     * 姓名(旧)
     */
    @JSONField(name = "cumo_contname_old")
    private String cumoContnameOld;

    /**
     * 性别
     */
    @JSONField(name = "cumo_contsex")
    private String cumoContsex;

    /**
     * 性别(旧)
     */
    @JSONField(name = "cumo_contsex_old")
    private String cumoContsexOld;

    /**
     * 职务
     */
    @JSONField(name = "cumo_contpost")
    private String cumoContpost;

    /**
     * 职务(旧)
     */
    @JSONField(name = "cumo_contpost_old")
    private String cumoContpostOld;

    /**
     * 电话
     */
    @JSONField(name = "cumo_conttel")
    private String cumoConttel;

    /**
     * 电话(旧)
     */
    @JSONField(name = "cumo_conttel_old")
    private String cumoConttelOld;

    /**
     * 邮箱
     */
    @JSONField(name = "cumo_contemail")
    private String cumoContemail;

    /**
     * 邮箱(旧)
     */
    @JSONField(name = "cumo_contemail_old")
    private String cumoContemailOld;

    /**
     * QQ/微信/其它
     */
    @JSONField(name = "cumo_contother")
    private String cumoContother;

    /**
     * QQ/微信/其它(旧)
     */
    @JSONField(name = "cumo_contother_old")
    private String cumoContotherOld;

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

    public String getCumoConttype() {
        return cumoConttype;
    }

    public void setCumoConttype(String cumoConttype) {
        this.cumoConttype = cumoConttype;
    }

    public String getCumoConttypeOld() {
        return cumoConttypeOld;
    }

    public void setCumoConttypeOld(String cumoConttypeOld) {
        this.cumoConttypeOld = cumoConttypeOld;
    }

    public String getCumoContname() {
        return cumoContname;
    }

    public void setCumoContname(String cumoContname) {
        this.cumoContname = cumoContname;
    }

    public String getCumoContnameOld() {
        return cumoContnameOld;
    }

    public void setCumoContnameOld(String cumoContnameOld) {
        this.cumoContnameOld = cumoContnameOld;
    }

    public String getCumoContsex() {
        return cumoContsex;
    }

    public void setCumoContsex(String cumoContsex) {
        this.cumoContsex = cumoContsex;
    }

    public String getCumoContsexOld() {
        return cumoContsexOld;
    }

    public void setCumoContsexOld(String cumoContsexOld) {
        this.cumoContsexOld = cumoContsexOld;
    }

    public String getCumoContpost() {
        return cumoContpost;
    }

    public void setCumoContpost(String cumoContpost) {
        this.cumoContpost = cumoContpost;
    }

    public String getCumoContpostOld() {
        return cumoContpostOld;
    }

    public void setCumoContpostOld(String cumoContpostOld) {
        this.cumoContpostOld = cumoContpostOld;
    }

    public String getCumoConttel() {
        return cumoConttel;
    }

    public void setCumoConttel(String cumoConttel) {
        this.cumoConttel = cumoConttel;
    }

    public String getCumoConttelOld() {
        return cumoConttelOld;
    }

    public void setCumoConttelOld(String cumoConttelOld) {
        this.cumoConttelOld = cumoConttelOld;
    }

    public String getCumoContemail() {
        return cumoContemail;
    }

    public void setCumoContemail(String cumoContemail) {
        this.cumoContemail = cumoContemail;
    }

    public String getCumoContemailOld() {
        return cumoContemailOld;
    }

    public void setCumoContemailOld(String cumoContemailOld) {
        this.cumoContemailOld = cumoContemailOld;
    }

    public String getCumoContother() {
        return cumoContother;
    }

    public void setCumoContother(String cumoContother) {
        this.cumoContother = cumoContother;
    }

    public String getCumoContotherOld() {
        return cumoContotherOld;
    }

    public void setCumoContotherOld(String cumoContotherOld) {
        this.cumoContotherOld = cumoContotherOld;
    }

    @Override
    public String toString() {
        return "CumoContactSetDTO{" +
                "id='" + id + '\'' +
                ", pid='" + pid + '\'' +
                ", cumoConttype='" + cumoConttype + '\'' +
                ", cumoConttypeOld='" + cumoConttypeOld + '\'' +
                ", cumoContname='" + cumoContname + '\'' +
                ", cumoContnameOld='" + cumoContnameOld + '\'' +
                ", cumoContsex='" + cumoContsex + '\'' +
                ", cumoContsexOld='" + cumoContsexOld + '\'' +
                ", cumoContpost='" + cumoContpost + '\'' +
                ", cumoContpostOld='" + cumoContpostOld + '\'' +
                ", cumoConttel='" + cumoConttel + '\'' +
                ", cumoConttelOld='" + cumoConttelOld + '\'' +
                ", cumoContemail='" + cumoContemail + '\'' +
                ", cumoContemailOld='" + cumoContemailOld + '\'' +
                ", cumoContother='" + cumoContother + '\'' +
                ", cumoContotherOld='" + cumoContotherOld + '\'' +
                '}';
    }
}
