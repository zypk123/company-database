package com.huntkey.rx.purchase.common.model.custom;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;

/**
 * 客户维护单-股东认证DTO
 *
 * @author zhangyu
 * @create 2018-01-03 10:10
 **/
public class CumoHolderSetDTO {

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
     * 股东姓名
     */
    @JSONField(name = "cumo_hname")
    private String cumoHname;

    /**
     * 股东姓名(旧)
     */
    @JSONField(name = "cumo_hname_old")
    private String cumoHnameOld;

    /**
     * 持股比例
     */
    @JSONField(name = "cumo_hrate")
    private BigDecimal cumoHrate;

    /**
     * 持股比例(旧)
     */
    @JSONField(name = "cumo_hrate_old")
    private BigDecimal cumoHrateOld;

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

    public String getCumoHname() {
        return cumoHname;
    }

    public void setCumoHname(String cumoHname) {
        this.cumoHname = cumoHname;
    }

    public String getCumoHnameOld() {
        return cumoHnameOld;
    }

    public void setCumoHnameOld(String cumoHnameOld) {
        this.cumoHnameOld = cumoHnameOld;
    }

    public BigDecimal getCumoHrate() {
        return cumoHrate;
    }

    public void setCumoHrate(BigDecimal cumoHrate) {
        this.cumoHrate = cumoHrate;
    }

    public BigDecimal getCumoHrateOld() {
        return cumoHrateOld;
    }

    public void setCumoHrateOld(BigDecimal cumoHrateOld) {
        this.cumoHrateOld = cumoHrateOld;
    }

    @Override
    public String toString() {
        return "CumoHolderSetDTO{" +
                "id='" + id + '\'' +
                ", pid='" + pid + '\'' +
                ", cumoHname='" + cumoHname + '\'' +
                ", cumoHnameOld='" + cumoHnameOld + '\'' +
                ", cumoHrate=" + cumoHrate +
                ", cumoHrateOld=" + cumoHrateOld +
                '}';
    }
}
