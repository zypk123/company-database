package com.huntkey.rx.purchase.common.model.settlemenet;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 结算方式DTO
 *
 * @author zhangyu
 * @create 2018-01-10 17:11
 **/
public class SettleMenetDTO implements Serializable {

    /**
     * id
     */
    @JSONField(name = "id")
    private String id;

    /**
     * 结算类型
     */
    @JSONField(name = "sett_type")
    private String settType;

    /**
     * 月结天数
     */
    @JSONField(name = "sett_mdays")
    private Integer settMdays;

    /**
     * 月结日
     */
    @JSONField(name = "sett_mdate")
    private Integer settMdate;

    /**
     * 货到天数
     */
    @JSONField(name = "sett_idays")
    private Integer settIdays;

    /**
     * 提前天数
     */
    @JSONField(name = "sett_adays")
    private Integer settAdays;

    /**
     * 比例
     */
    @JSONField(name = "sett_rate")
    private BigDecimal settRate;

    /**
     * 出货天数
     */
    @JSONField(name = "sett_odays")
    private Integer settOdays;

    /**
     * 结算方式说明
     */
    @JSONField(name = "sett_desc")
    private String settDesc;

    /**
     * 结算方式
     */
    @JSONField(name = "sett_way")
    private String settWay;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSettType() {
        return settType;
    }

    public void setSettType(String settType) {
        this.settType = settType;
    }

    public Integer getSettMdays() {
        return settMdays;
    }

    public void setSettMdays(Integer settMdays) {
        this.settMdays = settMdays;
    }

    public Integer getSettMdate() {
        return settMdate;
    }

    public void setSettMdate(Integer settMdate) {
        this.settMdate = settMdate;
    }

    public Integer getSettIdays() {
        return settIdays;
    }

    public void setSettIdays(Integer settIdays) {
        this.settIdays = settIdays;
    }

    public Integer getSettAdays() {
        return settAdays;
    }

    public void setSettAdays(Integer settAdays) {
        this.settAdays = settAdays;
    }

    public BigDecimal getSettRate() {
        return settRate;
    }

    public void setSettRate(BigDecimal settRate) {
        this.settRate = settRate;
    }

    public Integer getSettOdays() {
        return settOdays;
    }

    public void setSettOdays(Integer settOdays) {
        this.settOdays = settOdays;
    }

    public String getSettDesc() {
        return settDesc;
    }

    public void setSettDesc(String settDesc) {
        this.settDesc = settDesc;
    }

    public String getSettWay() {
        return settWay;
    }

    public void setSettWay(String settWay) {
        this.settWay = settWay;
    }

    @Override
    public String toString() {
        return "SettleMenetDTO{" +
                "id='" + id + '\'' +
                ", settType='" + settType + '\'' +
                ", settMdays=" + settMdays +
                ", settMdate=" + settMdate +
                ", settIdays=" + settIdays +
                ", settAdays=" + settAdays +
                ", settRate=" + settRate +
                ", settOdays=" + settOdays +
                ", settDesc='" + settDesc + '\'' +
                ", settWay='" + settWay + '\'' +
                '}';
    }
}
