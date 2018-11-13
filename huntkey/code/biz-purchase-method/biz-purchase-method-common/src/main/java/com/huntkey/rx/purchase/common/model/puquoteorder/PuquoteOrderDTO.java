package com.huntkey.rx.purchase.common.model.puquoteorder;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * 采购报价单 dto
 *
 * @author yaoss
 */
public class PuquoteOrderDTO {

    /**
     * 单据id
     */
    @JSONField(name = "id")
    private String id;

    /**
     * 单据定义id
     */
    @JSONField(name="orde_rode_obj")
    private String ordeRodeObj;

    /**
     *制单人id
     */
    @JSONField(name="orde_adduser")
    private String ordeAdduser;

    /**
     * 制单岗位id
     */
    @JSONField(name="orde_duty")
    private String ordeDuty;

    /**
     * 制单部门id
     */
    @JSONField(name="orde_dept")
    private String ordeDept;

    /**
     * 供应商id
     */
    @JSONField(name="puqo_code_supp")
    private String puqoCodeSupp;

    /**
     * 供应商简称
     */
    @JSONField(name="puqo_sname_supp")
    private String puqoSnameSupp;

    /**
     * 币别id
     */
    @JSONField(name="puqo_curr")
    private String puqoCurr;

    /**
     * 税率类id
     */
    @JSONField(name="puqo_tax")
    private String puqoTax;

    /**
     * 备注
     */
    @JSONField(name="puqo_remark")
    private String puqoRemark;

    /**
     * 报价明细集合
     */
    @JSONField(name="puqo_price_set")
    private List<PuqoPriceSetDTO> puqoPriceSet;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrdeRodeObj() {
        return ordeRodeObj;
    }

    public void setOrdeRodeObj(String ordeRodeObj) {
        this.ordeRodeObj = ordeRodeObj;
    }

    public String getOrdeAdduser() {
        return ordeAdduser;
    }

    public void setOrdeAdduser(String ordeAdduser) {
        this.ordeAdduser = ordeAdduser;
    }

    public String getOrdeDuty() {
        return ordeDuty;
    }

    public void setOrdeDuty(String ordeDuty) {
        this.ordeDuty = ordeDuty;
    }

    public String getOrdeDept() {
        return ordeDept;
    }

    public void setOrdeDept(String ordeDept) {
        this.ordeDept = ordeDept;
    }

    public String getPuqoCodeSupp() {
        return puqoCodeSupp;
    }

    public void setPuqoCodeSupp(String puqoCodeSupp) {
        this.puqoCodeSupp = puqoCodeSupp;
    }

    public String getPuqoSnameSupp() {
        return puqoSnameSupp;
    }

    public void setPuqoSnameSupp(String puqoSnameSupp) {
        this.puqoSnameSupp = puqoSnameSupp;
    }

    public String getPuqoCurr() {
        return puqoCurr;
    }

    public void setPuqoCurr(String puqoCurr) {
        this.puqoCurr = puqoCurr;
    }

    public String getPuqoTax() {
        return puqoTax;
    }

    public void setPuqoTax(String puqoTax) {
        this.puqoTax = puqoTax;
    }

    public String getPuqoRemark() {
        return puqoRemark;
    }

    public void setPuqoRemark(String puqoRemark) {
        this.puqoRemark = puqoRemark;
    }

    public List<PuqoPriceSetDTO> getPuqoPriceSet() {
        return puqoPriceSet;
    }

    public void setPuqoPriceSet(List<PuqoPriceSetDTO> puqoPriceSet) {
        this.puqoPriceSet = puqoPriceSet;
    }
}
