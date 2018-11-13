package com.huntkey.rx.purchase.common.model.puquoteorder;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * PuquoteOrderDTO 关联属性集
 * @author yaoss
 */
public class PuqoPriceSetDTO {

    /**
     * 物品id
     */
    @JSONField(name="puqo_goods")
    private String puqoGoods;
    /**
     * 园区id
     */
    @JSONField(name="puqo_park")
    private String puqoPark;
    /**
     * 单位
     */
    @JSONField(name="puqo_um")
    private String puqoUm;
    /**
     * 涨跌率
     */
    @JSONField(name="puqo_updown_rate")
    private BigDecimal puqoUpdownRate;
    /**
     * 单价
     */
    @JSONField(name="puqo_price")
    private BigDecimal puqoPrice;
    /**
     * 返利额
     */
    @JSONField(name="puqo_rebate")
    private BigDecimal puqoRebate;
    /**
     * 生效日期
     */
    @JSONField(name="puqo_date_beg")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date puqoDateBeg;
    /**
     * 失效日期
     */
    @JSONField(name="puqo_date_end")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date puqoDateEnd;
    /**
     * 是否是阶梯报价
     */
    @JSONField(name="puqo_isladder")
    private Integer puqoIsladder;
    /**
     * 单价old
     */
    @JSONField(name="puqo_price_old")
    private BigDecimal puqoPriceOld;
    /**
     * 返利额old
     */
    @JSONField(name="puqo_rebate_old")
    private BigDecimal puqoRebateOld;
    /**
     * 生效日期old
     */
    @JSONField(name="puqo_date_beg_old")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date puqoDateBegOld;
    /**
     * 失效日期old
     */
    @JSONField(name="puqo_date_end_old")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date puqoDateEndOld;
    /**
     * 是否是阶梯报价old
     */
    @JSONField(name="puqo_isladder_old")
    private Integer puqoIsladderOld;

    /**
     * 阶梯报价明细
     */
    @JSONField(name="puqo_ladder_set")
    private List<PuqoLadderPriceSetDTO> puqoLadderSet;

    /**
     * 阶梯报价明细old
     */
    @JSONField(name="puqo_ladder_set_old")
    private List<PuqoLadderPriceSetOldDTO> puqoLadderSetOld;

    public List<PuqoLadderPriceSetOldDTO> getPuqoLadderSetOld() {
        return puqoLadderSetOld;
    }

    public void setPuqoLadderSetOld(List<PuqoLadderPriceSetOldDTO> puqoLadderSetOld) {
        this.puqoLadderSetOld = puqoLadderSetOld;
    }

    public String getPuqoGoods() {
        return puqoGoods;
    }

    public void setPuqoGoods(String puqoGoods) {
        this.puqoGoods = puqoGoods;
    }

    public String getPuqoPark() {
        return puqoPark;
    }

    public void setPuqoPark(String puqoPark) {
        this.puqoPark = puqoPark;
    }

    public String getPuqoUm() {
        return puqoUm;
    }

    public void setPuqoUm(String puqoUm) {
        this.puqoUm = puqoUm;
    }

    public BigDecimal getPuqoUpdownRate() {
        return puqoUpdownRate;
    }

    public void setPuqoUpdownRate(BigDecimal puqoUpdownRate) {
        this.puqoUpdownRate = puqoUpdownRate;
    }

    public BigDecimal getPuqoPrice() {
        return puqoPrice;
    }

    public void setPuqoPrice(BigDecimal puqoPrice) {
        this.puqoPrice = puqoPrice;
    }

    public BigDecimal getPuqoRebate() {
        return puqoRebate;
    }

    public void setPuqoRebate(BigDecimal puqoRebate) {
        this.puqoRebate = puqoRebate;
    }

    public Date getPuqoDateBeg() {
        return puqoDateBeg;
    }

    public void setPuqoDateBeg(Date puqoDateBeg) {
        this.puqoDateBeg = puqoDateBeg;
    }

    public Date getPuqoDateEnd() {
        return puqoDateEnd;
    }

    public void setPuqoDateEnd(Date puqoDateEnd) {
        this.puqoDateEnd = puqoDateEnd;
    }

    public Integer getPuqoIsladder() {
        return puqoIsladder;
    }

    public void setPuqoIsladder(Integer puqoIsladder) {
        this.puqoIsladder = puqoIsladder;
    }

    public BigDecimal getPuqoPriceOld() {
        return puqoPriceOld;
    }

    public void setPuqoPriceOld(BigDecimal puqoPriceOld) {
        this.puqoPriceOld = puqoPriceOld;
    }

    public BigDecimal getPuqoRebateOld() {
        return puqoRebateOld;
    }

    public void setPuqoRebateOld(BigDecimal puqoRebateOld) {
        this.puqoRebateOld = puqoRebateOld;
    }

    public Date getPuqoDateBegOld() {
        return puqoDateBegOld;
    }

    public void setPuqoDateBegOld(Date puqoDateBegOld) {
        this.puqoDateBegOld = puqoDateBegOld;
    }

    public Date getPuqoDateEndOld() {
        return puqoDateEndOld;
    }

    public void setPuqoDateEndOld(Date puqoDateEndOld) {
        this.puqoDateEndOld = puqoDateEndOld;
    }

    public Integer getPuqoIsladderOld() {
        return puqoIsladderOld;
    }

    public void setPuqoIsladderOld(Integer puqoIsladderOld) {
        this.puqoIsladderOld = puqoIsladderOld;
    }

    public List<PuqoLadderPriceSetDTO> getPuqoLadderSet() {
        return puqoLadderSet;
    }

    public void setPuqoLadderSet(List<PuqoLadderPriceSetDTO> puqoLadderSet) {
        this.puqoLadderSet = puqoLadderSet;
    }
}
