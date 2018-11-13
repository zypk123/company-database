package com.huntkey.rx.purchase.common.model.supplier;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 供应商维护单-供应商属性集-服务团队DTO
 *
 * @author zhangyu
 * @create 2018-01-03 17:02
 **/
public class SumoServtSetDTO implements Serializable {

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
    @JSONField(name = "sumo_sttype")
    private String sumoSttype;

    /**
     * 团队类型(旧)
     */
    @JSONField(name = "sumo_sttype_old")
    private String sumoSttypeOld;

    /**
     * 人员
     */
    @JSONField(name = "sumo_stemp")
    private String sumoStemp;

    /**
     * 人员(旧)
     */
    @JSONField(name = "sumo_stemp_old")
    private String sumoStempOld;

    /**
     * 姓名
     */
    private String sumoStempName;

    /**
     * 部门
     */
    private String sumoDeptName;

    /**
     * 岗位
     */
    private String sumoPostName;

    /**
     * 手机号码
     */
    private String sumoTel;

    /**
     * 办公电话
     */
    private String sumoOfficTel;

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

    public String getSumoSttype() {
        return sumoSttype;
    }

    public void setSumoSttype(String sumoSttype) {
        this.sumoSttype = sumoSttype;
    }

    public String getSumoSttypeOld() {
        return sumoSttypeOld;
    }

    public void setSumoSttypeOld(String sumoSttypeOld) {
        this.sumoSttypeOld = sumoSttypeOld;
    }

    public String getSumoStemp() {
        return sumoStemp;
    }

    public void setSumoStemp(String sumoStemp) {
        this.sumoStemp = sumoStemp;
    }

    public String getSumoStempOld() {
        return sumoStempOld;
    }

    public void setSumoStempOld(String sumoStempOld) {
        this.sumoStempOld = sumoStempOld;
    }

    public String getSumoStempName() {
        return sumoStempName;
    }

    public void setSumoStempName(String sumoStempName) {
        this.sumoStempName = sumoStempName;
    }

    public String getSumoDeptName() {
        return sumoDeptName;
    }

    public void setSumoDeptName(String sumoDeptName) {
        this.sumoDeptName = sumoDeptName;
    }

    public String getSumoPostName() {
        return sumoPostName;
    }

    public void setSumoPostName(String sumoPostName) {
        this.sumoPostName = sumoPostName;
    }

    public String getSumoTel() {
        return sumoTel;
    }

    public void setSumoTel(String sumoTel) {
        this.sumoTel = sumoTel;
    }

    public String getSumoOfficTel() {
        return sumoOfficTel;
    }

    public void setSumoOfficTel(String sumoOfficTel) {
        this.sumoOfficTel = sumoOfficTel;
    }

    @Override
    public String toString() {
        return "SumoServtSetDTO{" +
                "id='" + id + '\'' +
                ", pid='" + pid + '\'' +
                ", sumoSttype='" + sumoSttype + '\'' +
                ", sumoSttypeOld='" + sumoSttypeOld + '\'' +
                ", sumoStemp='" + sumoStemp + '\'' +
                ", sumoStempOld='" + sumoStempOld + '\'' +
                ", sumoStempName='" + sumoStempName + '\'' +
                ", sumoDeptName='" + sumoDeptName + '\'' +
                ", sumoPostName='" + sumoPostName + '\'' +
                ", sumoTel='" + sumoTel + '\'' +
                ", sumoOfficTel='" + sumoOfficTel + '\'' +
                '}';
    }
}
