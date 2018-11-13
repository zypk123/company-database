package com.huntkey.rx.purchase.common.model.puquoteorder;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;

/**
 * PuqoPriceSetDTO 关联属性集
 * @author yaoss
 */
public class PuqoLadderPriceSetOldDTO {

    /**
     * 单价old
     */
    @JSONField(name="puqo_lprice_old")
    private BigDecimal puqoLpriceOld;

    /**
     * 阶梯订单量小值_旧
     */
    @JSONField(name="puqo_oqty_min_old")
    private BigDecimal puqoOqtyMinOld;

    /**
     * 阶梯订单量大值_旧
     */
    @JSONField(name="puqo_oqty_max_old")
    private BigDecimal puqoOqtyMaxOld;

    public BigDecimal getPuqoLpriceOld() {
        return puqoLpriceOld;
    }

    public void setPuqoLpriceOld(BigDecimal puqoLpriceOld) {
        this.puqoLpriceOld = puqoLpriceOld;
    }

    public BigDecimal getPuqoOqtyMinOld() {
        return puqoOqtyMinOld;
    }

    public void setPuqoOqtyMinOld(BigDecimal puqoOqtyMinOld) {
        this.puqoOqtyMinOld = puqoOqtyMinOld;
    }

    public BigDecimal getPuqoOqtyMaxOld() {
        return puqoOqtyMaxOld;
    }

    public void setPuqoOqtyMaxOld(BigDecimal puqoOqtyMaxOld) {
        this.puqoOqtyMaxOld = puqoOqtyMaxOld;
    }
}
