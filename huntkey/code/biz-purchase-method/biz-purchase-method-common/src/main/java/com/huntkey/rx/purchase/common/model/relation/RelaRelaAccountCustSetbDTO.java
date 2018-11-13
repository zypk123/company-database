package com.huntkey.rx.purchase.common.model.relation;

import com.alibaba.fastjson.annotation.JSONField;
import com.huntkey.rx.edm.constant.RelaRelaAccountCustSetbProperty;
import com.huntkey.rx.sceo.orm.common.constant.EdmSysColumn;

import java.util.Date;

/**
 * Created by xuyf on 2018/1/18 0018.
 */
public class RelaRelaAccountCustSetbDTO {

    @JSONField(name = EdmSysColumn.ID)
    private String id;

    @JSONField(name = EdmSysColumn.PID)
    private String pid;

    @JSONField(name = RelaRelaAccountCustSetbProperty.RELA_ACCONAME_CUST)
    private String relaAcconameCust;

    @JSONField(name = RelaRelaAccountCustSetbProperty.RELA_ACCOBANK_CUST)
    private String relaAccobankCust;

    @JSONField(name = RelaRelaAccountCustSetbProperty.RELA_ACCONUM_CUST)
    private String relaAcconumCust;

    @JSONField(name = RelaRelaAccountCustSetbProperty.RELA_ACCOCURR_CUST)
    private String relaAccocurrCust;

    @JSONField(name = RelaRelaAccountCustSetbProperty.RELA_ACCOBEG_CUST)
    private Date relaAccobegCust;

    @JSONField(name = RelaRelaAccountCustSetbProperty.RELA_ACCOEND_CUST)
    private Date relaAccoendCust;

    @JSONField(name = RelaRelaAccountCustSetbProperty.RELA_ATTAACCO_CUST)
    private String relaAttaaccoCust;

    @JSONField(name = RelaRelaAccountCustSetbProperty.RELA_ATTAN_CUST)
    private String relaAttanCust;

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

    public String getRelaAcconameCust() {
        return relaAcconameCust;
    }

    public void setRelaAcconameCust(String relaAcconameCust) {
        this.relaAcconameCust = relaAcconameCust;
    }

    public String getRelaAccobankCust() {
        return relaAccobankCust;
    }

    public void setRelaAccobankCust(String relaAccobankCust) {
        this.relaAccobankCust = relaAccobankCust;
    }

    public String getRelaAcconumCust() {
        return relaAcconumCust;
    }

    public void setRelaAcconumCust(String relaAcconumCust) {
        this.relaAcconumCust = relaAcconumCust;
    }

    public String getRelaAccocurrCust() {
        return relaAccocurrCust;
    }

    public void setRelaAccocurrCust(String relaAccocurrCust) {
        this.relaAccocurrCust = relaAccocurrCust;
    }

    public Date getRelaAccobegCust() {
        return relaAccobegCust;
    }

    public void setRelaAccobegCust(Date relaAccobegCust) {
        this.relaAccobegCust = relaAccobegCust;
    }

    public Date getRelaAccoendCust() {
        return relaAccoendCust;
    }

    public void setRelaAccoendCust(Date relaAccoendCust) {
        this.relaAccoendCust = relaAccoendCust;
    }

    public String getRelaAttaaccoCust() {
        return relaAttaaccoCust;
    }

    public void setRelaAttaaccoCust(String relaAttaaccoCust) {
        this.relaAttaaccoCust = relaAttaaccoCust;
    }

    public String getRelaAttanCust() {
        return relaAttanCust;
    }

    public void setRelaAttanCust(String relaAttanCust) {
        this.relaAttanCust = relaAttanCust;
    }
}
