package com.huntkey.rx.purchase.common.model.puquoteorder;

import com.alibaba.fastjson.annotation.JSONField;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zhouyou on 2018/1/22
 */
public class GodsGodsPupricSetaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(name = NodeConstant.ID)
    private String id;

    @JSONField(name = NodeConstant.PID)
    private String pid;

    @JSONField(name="gods_pprice_ord")
    private String godsPpriceOrd;//采购报价单

    @JSONField(name="gods_supp")
    private String godsSupp;//供应商

    @JSONField(name="gods_pcurr")
    private String godsPcurr;//币别

    @JSONField(name="gods_ptax_rate")
    private String godsPtaxRate;//税率

    @JSONField(name="gods_ppark")
    private String godsPpark;//园区

    @JSONField(name="gods_pum_group")
    private String godsPumGroup;//单位组

    @JSONField(name="gods_pum")
    private String godsPum;//单位

    @JSONField(name="godsPprice")
    private BigDecimal godsPprice;//单价

    @JSONField(name="gods_prebate")
    private BigDecimal godsPrebate;//返利额

    @JSONField(name="gods_pdate_beg")
    private Date godsPdateBeg;//生效日期

    @JSONField(name="gods_pdate_end")
    private Date godsPdateEnd;//失效日期

    @JSONField(name="gods_pisladder")
    private int godsPisladder;//是否阶梯报价

    @JSONField(name="gods_poqty_min")
    private BigDecimal godsPoqtyMin;//阶梯订单量小值

    @JSONField(name="gods_poqty_max")
    private BigDecimal godsPoqtyMax;//阶梯订单量大值

    @JSONField(name="gods_updown_rate")
    private BigDecimal godsUpdownRate;//涨跌率

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public String getGodsPpriceOrd() {
        return godsPpriceOrd;
    }

    public void setGodsPpriceOrd(String godsPpriceOrd) {
        this.godsPpriceOrd = godsPpriceOrd;
    }

    public String getGodsSupp() {
        return godsSupp;
    }

    public void setGodsSupp(String godsSupp) {
        this.godsSupp = godsSupp;
    }

    public String getGodsPcurr() {
        return godsPcurr;
    }

    public void setGodsPcurr(String godsPcurr) {
        this.godsPcurr = godsPcurr;
    }

    public String getGodsPtaxRate() {
        return godsPtaxRate;
    }

    public void setGodsPtaxRate(String godsPtaxRate) {
        this.godsPtaxRate = godsPtaxRate;
    }

    public String getGodsPpark() {
        return godsPpark;
    }

    public void setGodsPpark(String godsPpark) {
        this.godsPpark = godsPpark;
    }

    public String getGodsPumGroup() {
        return godsPumGroup;
    }

    public void setGodsPumGroup(String godsPumGroup) {
        this.godsPumGroup = godsPumGroup;
    }

    public String getGodsPum() {
        return godsPum;
    }

    public void setGodsPum(String godsPum) {
        this.godsPum = godsPum;
    }

    public BigDecimal getGodsPprice() {
        return godsPprice;
    }

    public void setGodsPprice(BigDecimal godsPprice) {
        this.godsPprice = godsPprice;
    }

    public BigDecimal getGodsPrebate() {
        return godsPrebate;
    }

    public void setGodsPrebate(BigDecimal godsPrebate) {
        this.godsPrebate = godsPrebate;
    }

    public Date getGodsPdateBeg() {
        return godsPdateBeg;
    }

    public void setGodsPdateBeg(Date godsPdateBeg) {
        this.godsPdateBeg = godsPdateBeg;
    }

    public Date getGodsPdateEnd() {
        return godsPdateEnd;
    }

    public void setGodsPdateEnd(Date godsPdateEnd) {
        this.godsPdateEnd = godsPdateEnd;
    }

    public int getGodsPisladder() {
        return godsPisladder;
    }

    public void setGodsPisladder(int godsPisladder) {
        this.godsPisladder = godsPisladder;
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

    public BigDecimal getGodsUpdownRate() {
        return godsUpdownRate;
    }

    public void setGodsUpdownRate(BigDecimal godsUpdownRate) {
        this.godsUpdownRate = godsUpdownRate;
    }
}
