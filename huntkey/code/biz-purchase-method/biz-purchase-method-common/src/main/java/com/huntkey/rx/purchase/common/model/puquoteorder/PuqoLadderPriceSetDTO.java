package com.huntkey.rx.purchase.common.model.puquoteorder;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;

/**
 * PuqoPriceSetDTO 关联属性集
 * @author yaoss
 */
public class PuqoLadderPriceSetDTO {

    /**
     * 单价
     */
    @JSONField(name="puqo_lprice")
    private BigDecimal puqoLprice;

    /**
     * 阶梯订单量小值
     */
    @JSONField(name="puqo_oqty_min")
    private BigDecimal puqoOqtyMin;

    /**
     * 阶梯订单量大值
     */
    @JSONField(name = "puqo_oqty_max")
    private BigDecimal puqoOqtyMax;

    /**
     * 涨跌率
     */
    @JSONField(name = "puqo_lupdown_rate")
    private BigDecimal puqoLupdownRate;

    public BigDecimal getPuqoLprice() {
        return puqoLprice;
    }

    public void setPuqoLprice(BigDecimal puqoLprice) {
        this.puqoLprice = puqoLprice;
    }

    public BigDecimal getPuqoOqtyMin() {
        return puqoOqtyMin;
    }

    public void setPuqoOqtyMin(BigDecimal puqoOqtyMin) {
        this.puqoOqtyMin = puqoOqtyMin;
    }

    public BigDecimal getPuqoOqtyMax() {
        return puqoOqtyMax;
    }

    public void setPuqoOqtyMax(BigDecimal puqoOqtyMax) {
        this.puqoOqtyMax = puqoOqtyMax;
    }

    public BigDecimal getPuqoLupdownRate() {
        return puqoLupdownRate;
    }

    public void setPuqoLupdownRate(BigDecimal puqoLupdownRate) {
        this.puqoLupdownRate = puqoLupdownRate;
    }
}
