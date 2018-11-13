package com.huntkey.rx.purchase.common.model.supplier;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;

/**
 * 供应商维护单-股东认证DTO
 *
 * @author zhangyu
 * @create 2018-01-03 10:10
 **/
public class SumoHolderSetDTO {

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
    @JSONField(name = "sumo_hname")
    private String sumoHname;

    /**
     * 股东姓名(旧)
     */
    @JSONField(name = "sumo_hname_old")
    private String sumoHnameOld;

    /**
     * 持股比例
     */
    @JSONField(name = "sumo_hrate")
    private BigDecimal sumoHrate;

    /**
     * 持股比例(旧)
     */
    @JSONField(name = "sumo_hrate_old")
    private BigDecimal sumoHrateOld;

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

    public String getSumoHname() {
        return sumoHname;
    }

    public void setSumoHname(String sumoHname) {
        this.sumoHname = sumoHname;
    }

    public String getSumoHnameOld() {
        return sumoHnameOld;
    }

    public void setSumoHnameOld(String sumoHnameOld) {
        this.sumoHnameOld = sumoHnameOld;
    }

    public BigDecimal getSumoHrate() {
        return sumoHrate;
    }

    public void setSumoHrate(BigDecimal sumoHrate) {
        this.sumoHrate = sumoHrate;
    }

    public BigDecimal getSumoHrateOld() {
        return sumoHrateOld;
    }

    public void setSumoHrateOld(BigDecimal sumoHrateOld) {
        this.sumoHrateOld = sumoHrateOld;
    }

    @Override
    public String toString() {
        return "SumoHolderSetDTO{" +
                "id='" + id + '\'' +
                ", pid='" + pid + '\'' +
                ", sumoHname='" + sumoHname + '\'' +
                ", sumoHnameOld='" + sumoHnameOld + '\'' +
                ", sumoHrate=" + sumoHrate +
                ", sumoHrateOld=" + sumoHrateOld +
                '}';
    }
}
