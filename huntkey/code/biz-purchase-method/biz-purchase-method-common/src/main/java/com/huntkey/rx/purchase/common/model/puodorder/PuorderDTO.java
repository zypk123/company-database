package com.huntkey.rx.purchase.common.model.puodorder;

import com.alibaba.fastjson.annotation.JSONField;
import com.huntkey.rx.purchase.common.model.base.OrderDTO;
import com.huntkey.rx.sceo.orm.common.constant.EdmSysColumn;
import java.io.Serializable;
import java.util.List;

/**
 * Created by fangyou on 2018年1月19日 0019.
 */
public class PuorderDTO extends OrderDTO implements Serializable{
    @JSONField(name = EdmSysColumn.ID)
    private String id;

    /**
     * 供应商
     */
    @JSONField(name = "puod_code_supp")
    private String puodCodeSupp;

    /**
     * 供应商简称
     */
    @JSONField(name = "puod_sname_supp")
    private String puodSnameSupp;


    /**
     * 币别
     */
    @JSONField(name = "puod_curr")
    private String puodCurr;

    /**
     * 采购法人
     */
    @JSONField(name = "puod_corp")
    private String puodCorp;

    /**
     * 园区
     */
    @JSONField(name = "puod_park")
    private String puodPark;

    /**
     * 采购员
     */
    @JSONField(name = "puod_emp")
    private String puodEmp;

    /**
     * 采购部门
     */
    @JSONField(name = "puod_dept")
    private String puodDept;

    /**
     * 生效方式
     */
    @JSONField(name = "puod_priceff_type")
    private String puodPriceffType;

    /**
     * 交货地址
     */
    @JSONField(name = "puod_deli_addr")
    private String puodDeliAddr;

    /**
     * 交货联系人
     */
    @JSONField(name = "puod_deli_linkman")
    private String puodDeliLinkman;

    /**
     * 交货联系方式
     */
    @JSONField(name = "puod_deli_contway")
    private String puodDeliContway;

    /**
     *结算方式
     */
    @JSONField(name = "puod_settle_supp")
    private String puodSettleSupp;

    /**
     * 备注
     */
    @JSONField(name = "puod_remark")
    private String puodRemark;

    /**
     * 订单状态
     */
    @JSONField(name = "puod_status")
    private String puodStatus;

    /**
     * 订单明细集
     */
    @JSONField(name = "puod_order_set")
    private List<PuodOrderSetDTO> puodOrderSet;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPuodCodeSupp() {
        return puodCodeSupp;
    }

    public void setPuodCodeSupp(String puodCodeSupp) {
        this.puodCodeSupp = puodCodeSupp;
    }

    public String getPuodSnameSupp() {
        return puodSnameSupp;
    }

    public void setPuodSnameSupp(String puodSnameSupp) {
        this.puodSnameSupp = puodSnameSupp;
    }

    public String getPuodCurr() {
        return puodCurr;
    }

    public void setPuodCurr(String puodCurr) {
        this.puodCurr = puodCurr;
    }

    public String getPuodCorp() {
        return puodCorp;
    }

    public void setPuodCorp(String puodCorp) {
        this.puodCorp = puodCorp;
    }

    public String getPuodPark() {
        return puodPark;
    }

    public void setPuodPark(String puodPark) {
        this.puodPark = puodPark;
    }

    public String getPuodEmp() {
        return puodEmp;
    }

    public void setPuodEmp(String puodEmp) {
        this.puodEmp = puodEmp;
    }

    public String getPuodDept() {
        return puodDept;
    }

    public void setPuodDept(String puodDept) {
        this.puodDept = puodDept;
    }

    public String getPuodPriceffType() {
        return puodPriceffType;
    }

    public void setPuodPriceffType(String puodPriceffType) {
        this.puodPriceffType = puodPriceffType;
    }

    public String getPuodDeliAddr() {
        return puodDeliAddr;
    }

    public void setPuodDeliAddr(String puodDeliAddr) {
        this.puodDeliAddr = puodDeliAddr;
    }

    public String getPuodDeliLinkman() {
        return puodDeliLinkman;
    }

    public void setPuodDeliLinkman(String puodDeliLinkman) {
        this.puodDeliLinkman = puodDeliLinkman;
    }

    public String getPuodDeliContway() {
        return puodDeliContway;
    }

    public void setPuodDeliContway(String puodDeliContway) {
        this.puodDeliContway = puodDeliContway;
    }

    public String getPuodSettleSupp() {
        return puodSettleSupp;
    }

    public void setPuodSettleSupp(String puodSettleSupp) {
        this.puodSettleSupp = puodSettleSupp;
    }

    public String getPuodRemark() {
        return puodRemark;
    }

    public void setPuodRemark(String puodRemark) {
        this.puodRemark = puodRemark;
    }

    public String getPuodStatus() {
        return puodStatus;
    }

    public void setPuodStatus(String puodStatus) {
        this.puodStatus = puodStatus;
    }

    public List<PuodOrderSetDTO> getPuodOrderSet() {
        return puodOrderSet;
    }

    public void setPuodOrderSet(List<PuodOrderSetDTO> puodOrderSet) {
        this.puodOrderSet = puodOrderSet;
    }
}
