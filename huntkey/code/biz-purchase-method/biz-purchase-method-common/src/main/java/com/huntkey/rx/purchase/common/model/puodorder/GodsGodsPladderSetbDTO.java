package com.huntkey.rx.purchase.common.model.puodorder;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;

/**
 * GodsGodsPladderSetbDTO 关联属性集
 * @author zhouyou
 */
public class GodsGodsPladderSetbDTO {

    /**
     * 单价
     */
    @JSONField(name="gods_plprice")
    private BigDecimal godsPlprice;

    /**
     * 阶梯订单量小值
     */
    @JSONField(name="gods_poqty_min")
    private BigDecimal godsPoqtyMin;

    /**
     * 阶梯订单量大值
     */
    @JSONField(name = "gods_poqty_max")
    private BigDecimal godsPoqtyMax;

    /**
     * 涨跌率
     */
    @JSONField(name = "gods_lupdown_rate")
    private BigDecimal godsLupdownRate;

    public BigDecimal getGodsPlprice() {
        return godsPlprice;
    }

    public void setGodsPlprice(BigDecimal godsPlprice) {
        this.godsPlprice = godsPlprice;
    }

    public BigDecimal getGodsPoqtyMin() {
        return godsPoqtyMin;
    }

    public void setGodsPoqtyMin(BigDecimal godsPoqtyMin) {
        this.godsPoqtyMin = godsPoqtyMin;
    }

    public BigDecimal getGodsPoqtyMax() {
        return godsPoqtyMax;
    }

    public void setGodsPoqtyMax(BigDecimal godsPoqtyMax) {
        this.godsPoqtyMax = godsPoqtyMax;
    }

    public BigDecimal getGodsLupdownRate() {
        return godsLupdownRate;
    }

    public void setGodsLupdownRate(BigDecimal godsLupdownRate) {
        this.godsLupdownRate = godsLupdownRate;
    }
}
