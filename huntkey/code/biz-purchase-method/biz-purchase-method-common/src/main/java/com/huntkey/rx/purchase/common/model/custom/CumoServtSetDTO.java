package com.huntkey.rx.purchase.common.model.custom;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 客户维护单-客户属性集-服务团队DTO
 *
 * @author zhangyu
 * @create 2018-01-03 17:02
 **/
public class CumoServtSetDTO implements Serializable {

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
     * 团队类型
     */
    @JSONField(name = "cumo_sttype")
    private String cumoSttype;

    /**
     * 团队类型(旧)
     */
    @JSONField(name = "cumo_sttype_old")
    private String cumoSttypeOld;

    /**
     * 人员
     */
    @JSONField(name = "cumo_stemp")
    private String cumoStemp;

    /**
     * 人员(旧)
     */
    @JSONField(name = "cumo_stemp_old")
    private String cumoStempOld;

    /**
     * 姓名
     */
    private String cumoStempName;

    /**
     * 部门
     */
    private String cumoDeptName;

    /**
     * 岗位
     */
    private String cumoPostName;

    /**
     * 手机号码
     */
    private String cumoTel;

    /**
     * 办公电话
     */
    private String cumoOfficTel;

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

    public String getCumoSttype() {
        return cumoSttype;
    }

    public void setCumoSttype(String cumoSttype) {
        this.cumoSttype = cumoSttype;
    }

    public String getCumoSttypeOld() {
        return cumoSttypeOld;
    }

    public void setCumoSttypeOld(String cumoSttypeOld) {
        this.cumoSttypeOld = cumoSttypeOld;
    }

    public String getCumoStemp() {
        return cumoStemp;
    }

    public void setCumoStemp(String cumoStemp) {
        this.cumoStemp = cumoStemp;
    }

    public String getCumoStempOld() {
        return cumoStempOld;
    }

    public void setCumoStempOld(String cumoStempOld) {
        this.cumoStempOld = cumoStempOld;
    }

    public String getCumoStempName() {
        return cumoStempName;
    }

    public void setCumoStempName(String cumoStempName) {
        this.cumoStempName = cumoStempName;
    }

    public String getCumoDeptName() {
        return cumoDeptName;
    }

    public void setCumoDeptName(String cumoDeptName) {
        this.cumoDeptName = cumoDeptName;
    }

    public String getCumoPostName() {
        return cumoPostName;
    }

    public void setCumoPostName(String cumoPostName) {
        this.cumoPostName = cumoPostName;
    }

    public String getCumoTel() {
        return cumoTel;
    }

    public void setCumoTel(String cumoTel) {
        this.cumoTel = cumoTel;
    }

    public String getCumoOfficTel() {
        return cumoOfficTel;
    }

    public void setCumoOfficTel(String cumoOfficTel) {
        this.cumoOfficTel = cumoOfficTel;
    }

    @Override
    public String toString() {
        return "CumoServtSetDTO{" +
                "id='" + id + '\'' +
                ", pid='" + pid + '\'' +
                ", cumoSttype='" + cumoSttype + '\'' +
                ", cumoSttypeOld='" + cumoSttypeOld + '\'' +
                ", cumoStemp='" + cumoStemp + '\'' +
                ", cumoStempOld='" + cumoStempOld + '\'' +
                ", cumoStempName='" + cumoStempName + '\'' +
                ", cumoDeptName='" + cumoDeptName + '\'' +
                ", cumoPostName='" + cumoPostName + '\'' +
                ", cumoTel='" + cumoTel + '\'' +
                ", cumoOfficTel='" + cumoOfficTel + '\'' +
                '}';
    }
}
