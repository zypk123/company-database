package com.huntkey.rx.purchase.common.model.relation;

import com.alibaba.fastjson.annotation.JSONField;
import com.huntkey.rx.edm.constant.RelaRelaDeliCustSetbProperty;
import com.huntkey.rx.sceo.orm.common.constant.EdmSysColumn;

/**
 * Created by xuyf on 2018/1/18 0018.
 */
public class RelaRelaDeliCustSetbDTO {

    @JSONField(name = EdmSysColumn.ID)
    private String id;

    @JSONField(name = EdmSysColumn.PID)
    private String pid;

    @JSONField(name = RelaRelaDeliCustSetbProperty.RELA_DANAME_CUST)
    private String relaDanameCust;

    @JSONField(name = RelaRelaDeliCustSetbProperty.RELA_DADDRP_CUST)
    private String relaDaddrpCust;

    @JSONField(name = RelaRelaDeliCustSetbProperty.RELA_DADDRC_CUST)
    private String relaDaddrcCust;

    @JSONField(name = RelaRelaDeliCustSetbProperty.RELA_DADDRL_CUST)
    private String relaDaddrlCust;

    @JSONField(name = RelaRelaDeliCustSetbProperty.RELA_DDADDR_CUST)
    private String relaDdaddrCust;

    @JSONField(name = RelaRelaDeliCustSetbProperty.RELA_DCONTACT_CUST)
    private String relaDcontactCust;

    @JSONField(name = RelaRelaDeliCustSetbProperty.RELA_DCWAY_CUST)
    private String relaDcwayCust;

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

    public String getRelaDanameCust() {
        return relaDanameCust;
    }

    public void setRelaDanameCust(String relaDanameCust) {
        this.relaDanameCust = relaDanameCust;
    }

    public String getRelaDaddrpCust() {
        return relaDaddrpCust;
    }

    public void setRelaDaddrpCust(String relaDaddrpCust) {
        this.relaDaddrpCust = relaDaddrpCust;
    }

    public String getRelaDaddrcCust() {
        return relaDaddrcCust;
    }

    public void setRelaDaddrcCust(String relaDaddrcCust) {
        this.relaDaddrcCust = relaDaddrcCust;
    }

    public String getRelaDaddrlCust() {
        return relaDaddrlCust;
    }

    public void setRelaDaddrlCust(String relaDaddrlCust) {
        this.relaDaddrlCust = relaDaddrlCust;
    }

    public String getRelaDdaddrCust() {
        return relaDdaddrCust;
    }

    public void setRelaDdaddrCust(String relaDdaddrCust) {
        this.relaDdaddrCust = relaDdaddrCust;
    }

    public String getRelaDcontactCust() {
        return relaDcontactCust;
    }

    public void setRelaDcontactCust(String relaDcontactCust) {
        this.relaDcontactCust = relaDcontactCust;
    }

    public String getRelaDcwayCust() {
        return relaDcwayCust;
    }

    public void setRelaDcwayCust(String relaDcwayCust) {
        this.relaDcwayCust = relaDcwayCust;
    }
}
