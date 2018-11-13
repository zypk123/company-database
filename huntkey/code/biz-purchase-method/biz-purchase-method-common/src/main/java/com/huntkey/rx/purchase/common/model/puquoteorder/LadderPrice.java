package com.huntkey.rx.purchase.common.model.puquoteorder;

/**
 * Created by zhoucj on 2018/1/22.
 */
public class LadderPrice {
    //单价
    private String puqoPrice;

    //旧单价
    private String puqoPriceOld;

    //返利额
    private String puqoRebate;

    //旧返利额
    private String puqoRebateOld;

    //生效日期
    private String puqoDateBeg;

    //旧生效日期
    private String puqoDateBegOld;

    //失效日期
    private String puqoDateEnd;

    //旧失效日期
    private String puqoDateEndOld;

    //阶梯订单量最小值
    private String puqoOqtyMin;

    //旧阶梯订单量最小值
    private String puqoOqtyMinOld;

    //阶梯订单量最大值
    private String puqoOqtyMax;

    //旧阶梯订单量最大值
    private String puqoOqtyMaxOld;

    //涨跌率
    private String puqoLupdownRate;

    public LadderPrice() {
        this.puqoPrice = "";
        this.puqoPriceOld = "";
        this.puqoRebate = "";
        this.puqoRebateOld = "";
        this.puqoDateBeg = "";
        this.puqoDateBegOld = "";
        this.puqoDateEnd = "";
        this.puqoDateEndOld = "";
        this.puqoOqtyMin = "";
        this.puqoOqtyMinOld = "";
        this.puqoOqtyMax = "";
        this.puqoOqtyMaxOld = "";
        this.puqoLupdownRate = "";
    }

    public String getPuqoPrice() {
        return puqoPrice;
    }

    public void setPuqoPrice(String puqoPrice) {
        this.puqoPrice = puqoPrice == null ? "" : puqoPrice;
    }

    public String getPuqoRebate() {
        return puqoRebate;
    }

    public void setPuqoRebate(String puqoRebate) {
        this.puqoRebate = puqoRebate == null ? "" : puqoRebate;
    }

    public String getPuqoDateBeg() {
        return puqoDateBeg;
    }

    public void setPuqoDateBeg(String puqoDateBeg) {
        this.puqoDateBeg = puqoDateBeg == null ? "" : puqoDateBeg;
    }

    public String getPuqoDateEnd() {
        return puqoDateEnd;
    }

    public void setPuqoDateEnd(String puqoDateEnd) {
        this.puqoDateEnd = puqoDateEnd == null ? "" : puqoDateEnd;
    }

    public String getPuqoOqtyMin() {
        return puqoOqtyMin;
    }

    public void setPuqoOqtyMin(String puqoOqtyMin) {
        this.puqoOqtyMin = puqoOqtyMin == null ? "" : puqoOqtyMin;
    }

    public String getPuqoOqtyMax() {
        return puqoOqtyMax;
    }

    public void setPuqoOqtyMax(String puqoOqtyMax) {
        this.puqoOqtyMax = puqoOqtyMax == null ? "" : puqoOqtyMax;
    }

    public String getPuqoPriceOld() {
        return puqoPriceOld;
    }

    public void setPuqoPriceOld(String puqoPriceOld) {
        this.puqoPriceOld = puqoPriceOld == null ? "" : puqoPriceOld;
    }

    public String getPuqoRebateOld() {
        return puqoRebateOld;
    }

    public void setPuqoRebateOld(String puqoRebateOld) {
        this.puqoRebateOld = puqoRebateOld == null ? "" : puqoRebateOld;
    }

    public String getPuqoDateBegOld() {
        return puqoDateBegOld;
    }

    public void setPuqoDateBegOld(String puqoDateBegOld) {
        this.puqoDateBegOld = puqoDateBegOld == null ? "" : puqoDateBegOld;
    }

    public String getPuqoDateEndOld() {
        return puqoDateEndOld;
    }

    public void setPuqoDateEndOld(String puqoDateEndOld) {
        this.puqoDateEndOld = puqoDateEndOld == null ? "" : puqoDateEndOld;
    }

    public String getPuqoOqtyMinOld() {
        return puqoOqtyMinOld;
    }

    public void setPuqoOqtyMinOld(String puqoOqtyMinOld) {
        this.puqoOqtyMinOld = puqoOqtyMinOld == null ? "" : puqoOqtyMinOld;
    }

    public String getPuqoOqtyMaxOld() {
        return puqoOqtyMaxOld;
    }

    public void setPuqoOqtyMaxOld(String puqoOqtyMaxOld) {
        this.puqoOqtyMaxOld = puqoOqtyMaxOld == null ? "" : puqoOqtyMaxOld;
    }

    public String getPuqoLupdownRate() {
        return puqoLupdownRate;
    }

    public void setPuqoLupdownRate(String puqoLupdownRate) {
        this.puqoLupdownRate = puqoLupdownRate == null ? "" : puqoLupdownRate;
    }
}
