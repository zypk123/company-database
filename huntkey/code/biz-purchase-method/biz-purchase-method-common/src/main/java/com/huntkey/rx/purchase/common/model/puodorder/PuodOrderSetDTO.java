package com.huntkey.rx.purchase.common.model.puodorder;

import com.alibaba.fastjson.annotation.JSONField;
import com.huntkey.rx.purchase.common.model.puquoteorder.GodsGodsPupricSetaDTO;
import com.huntkey.rx.purchase.common.model.taxrate.TaxrateDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by fangyou on 2018年1月19日 0019.
 */
public class PuodOrderSetDTO implements Serializable {


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
     * 物品
     */
    @JSONField(name = "puod_gods")
    private String puodGods;

    /**
     *物品名称
     */
    @JSONField(name = "puod_gods_name")
    private String puodGodsName;

    /**
     *规格型号
     */
    @JSONField(name = "puod_gods_model")
    private String puodGodsModel;

    /**
     *单位组
     */
    @JSONField(name = "puod_um_group")
    private String puodUmGroup;

    /**
     *单位
     */
    @JSONField(name = "puod_um")
    private String puodUm;

    /**
     *单价
     */
    @JSONField(name = "puod_price")
    private BigDecimal puodPrice;

    /**
     *原单价
     */
    @JSONField(name = "puod_price_src")
    private BigDecimal puodPriceSrc;

    /**
     *价格类型
     */
    @JSONField(name = "puod_price_type")
    private int puodPriceType;

    /**
     *订单数量
     */
    @JSONField(name = "puod_qty")
    private BigDecimal puodQty;

    /**
     *交货日期
     */
    @JSONField(name = "puod_date")
    private Date puodDate;

    /**
     *最小订单量
     */
    @JSONField(name = "puod_qty_min")
    private BigDecimal puodQtyMin;

    /**
     *税率
     */
    @JSONField(name = "puod_rate")
    private String puodRate;

    /**
     *状态
     */
    @JSONField(name = "puod_istatus")
    private String puodIstatus;

    /**
     *是否补货项
     */
    @JSONField(name = "puod_isrepl")
    private int puodIsrepl;

    /**
     *已收货数量
     */
    @JSONField(name = "puod_dqty")
    private BigDecimal puodDqty;

    /**
     *待收货数量
     */
    @JSONField(name = "puod_dwqty")
    private BigDecimal puodDwqty;

    /**
     *已退货数量
     */
    @JSONField(name = "puod_rqty")
    private BigDecimal puodRqty;

    /**
     *待退货数量
     */
    @JSONField(name = "puod_rwqty")
    private BigDecimal puodRwqty;

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

    public String getPuodGods() {
        return puodGods;
    }

    public void setPuodGods(String puodGods) {
        this.puodGods = puodGods;
    }

    public String getPuodGodsName() {
        return puodGodsName;
    }

    public void setPuodGodsName(String puodGodsName) {
        this.puodGodsName = puodGodsName;
    }

    public String getPuodGodsModel() {
        return puodGodsModel;
    }

    public void setPuodGodsModel(String puodGodsModel) {
        this.puodGodsModel = puodGodsModel;
    }

    public String getPuodUmGroup() {
        return puodUmGroup;
    }

    public void setPuodUmGroup(String puodUmGroup) {
        this.puodUmGroup = puodUmGroup;
    }

    public String getPuodUm() {
        return puodUm;
    }

    public void setPuodUm(String puodUm) {
        this.puodUm = puodUm;
    }

    public BigDecimal getPuodPrice() {
        return puodPrice;
    }

    public void setPuodPrice(BigDecimal puodPrice) {
        this.puodPrice = puodPrice;
    }

    public BigDecimal getPuodPriceSrc() {
        return puodPriceSrc;
    }

    public void setPuodPriceSrc(BigDecimal puodPriceSrc) {
        this.puodPriceSrc = puodPriceSrc;
    }

    public int getPuodPriceType() {
        return puodPriceType;
    }

    public void setPuodPriceType(int puodPriceType) {
        this.puodPriceType = puodPriceType;
    }

    public BigDecimal getPuodQty() {
        return puodQty;
    }

    public void setPuodQty(BigDecimal puodQty) {
        this.puodQty = puodQty;
    }

    public Date getPuodDate() {
        return puodDate;
    }

    public void setPuodDate(Date puodDate) {
        this.puodDate = puodDate;
    }

    public BigDecimal getPuodQtyMin() {
        return puodQtyMin;
    }

    public void setPuodQtyMin(BigDecimal puodQtyMin) {
        this.puodQtyMin = puodQtyMin;
    }

    public String getPuodRate() {
        return puodRate;
    }

    public void setPuodRate(String puodRate) {
        this.puodRate = puodRate;
    }

    public String getPuodIstatus() {
        return puodIstatus;
    }

    public void setPuodIstatus(String puodIstatus) {
        this.puodIstatus = puodIstatus;
    }

    public int getPuodIsrepl() {
        return puodIsrepl;
    }

    public void setPuodIsrepl(int puodIsrepl) {
        this.puodIsrepl = puodIsrepl;
    }

    public BigDecimal getPuodDqty() {
        return puodDqty;
    }

    public void setPuodDqty(BigDecimal puodDqty) {
        this.puodDqty = puodDqty;
    }

    public BigDecimal getPuodDwqty() {
        return puodDwqty;
    }

    public void setPuodDwqty(BigDecimal puodDwqty) {
        this.puodDwqty = puodDwqty;
    }

    public BigDecimal getPuodRqty() {
        return puodRqty;
    }

    public void setPuodRqty(BigDecimal puodRqty) {
        this.puodRqty = puodRqty;
    }

    public BigDecimal getPuodRwqty() {
        return puodRwqty;
    }

    public void setPuodRwqty(BigDecimal puodRwqty) {
        this.puodRwqty = puodRwqty;
    }

    /**
     * 物品编号
     */
    private String puodGodsCode;

    public String getPuodGodsCode() {
        return puodGodsCode;
    }

    public void setPuodGodsCode(String puodGodsCode) {
        this.puodGodsCode = puodGodsCode;
    }

    /**
     * 总金额
     */
    private BigDecimal tMoney;

    public BigDecimal gettMoney() {
        return tMoney;
    }

    public void settMoney(BigDecimal tMoney) {
        this.tMoney = tMoney;
    }

    /**
     * 税率名称
     */
    private String puodRateName;

    public String getPuodRateName() {
        return puodRateName;
    }

    public void setPuodRateName(String puodRateName) {
        this.puodRateName = puodRateName;
    }

    /**
     * 税率值
     */
    private BigDecimal puodRateValue;

    public BigDecimal getPuodRateValue() {
        return puodRateValue;
    }

    public void setPuodRateValue(BigDecimal puodRateValue) {
        this.puodRateValue = puodRateValue;
    }

    /**
     * 税额
     */
    private BigDecimal tTax;

    public BigDecimal gettTax() {
        return tTax;
    }

    public void settTax(BigDecimal tTax) {
        this.tTax = tTax;
    }

    /**
     * 项次
     */
    private int puodLine;

    public int getPuodLine() {
        return puodLine;
    }

    public void setPuodLine(int puodLine) {
        this.puodLine = puodLine;
    }

    /**
     * 采购报价集
     */
    @JSONField(name = "gods_gods_pupric_seta")
    public List<GodsGodsPupricSetaDTO> godsGodsPupricSetaDTOS;

    public List<GodsGodsPupricSetaDTO> getGodsGodsPupricSetaDTOS() {
        return godsGodsPupricSetaDTOS;
    }

    public void setGodsGodsPupricSetaDTOS(List<GodsGodsPupricSetaDTO> godsGodsPupricSetaDTOS) {
        this.godsGodsPupricSetaDTOS = godsGodsPupricSetaDTOS;
    }

    /**
     * 阶梯报价明细
     */
    @JSONField(name="gods_gods_pladder_setb")
    private List<GodsGodsPladderSetbDTO> godsGodsPladderSetbDTOS;

    public List<GodsGodsPladderSetbDTO> getGodsGodsPladderSetbDTOS() {
        return godsGodsPladderSetbDTOS;
    }

    public void setGodsGodsPladderSetbDTOS(List<GodsGodsPladderSetbDTO> godsGodsPladderSetbDTOS) {
        this.godsGodsPladderSetbDTOS = godsGodsPladderSetbDTOS;
    }

    /**
     * 税率类
     */
    @JSONField(name = "taxrates")
    private List<TaxrateDTO> taxrateDTOs;

    public List<TaxrateDTO> getTaxrateDTOs() {
        return taxrateDTOs;
    }

    public void setTaxrateDTOs(List<TaxrateDTO> taxrateDTOs) {
        this.taxrateDTOs = taxrateDTOs;
    }

    /**
     *小数点位数
     */
    @JSONField(name = "meas_dradix_num")
    private int measDradixNum;

    public int getMeasDradixNum() {
        return measDradixNum;
    }

    public void setMeasDradixNum(int measDradixNum) {
        this.measDradixNum = measDradixNum;
    }

    /**
     * 小数点归整方法
     */
    @JSONField(name="meas_dradix_put")
    private String measDradixPut;

    public String getMeasDradixPut() {
        return measDradixPut;
    }

    public void setMeasDradixPut(String measDradixPut) {
        this.measDradixPut = measDradixPut;
    }

}
