package com.huntkey.rx.purchase.common.model.relation;

import com.alibaba.fastjson.annotation.JSONField;
import com.huntkey.rx.edm.constant.RelaRelaAttachCustSetbProperty;
import com.huntkey.rx.sceo.orm.common.constant.EdmSysColumn;

import java.util.Date;

/**
 * Created by xuyf on 2018/1/18 0018.
 */
public class RelaRelaAttachCustSetbDTO {

    @JSONField(name = EdmSysColumn.ID)
    private String id;

    @JSONField(name = EdmSysColumn.PID)
    private String pid;

    @JSONField(name = RelaRelaAttachCustSetbProperty.RELA_ATTATYPE_CUST)
    private String relaAttatypeCust;

    @JSONField(name = RelaRelaAttachCustSetbProperty.RELA_ATTAADDR_CUST)
    private String relaAttaaddrCust;

    @JSONField(name = RelaRelaAttachCustSetbProperty.RELA_ATTAVALID_CUST)
    private Date relaAttavalidCust;

    @JSONField(name = RelaRelaAttachCustSetbProperty.RELA_ATTANAME_CUST)
    private String relaAttanameSupp;

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

    public String getRelaAttatypeCust() {
        return relaAttatypeCust;
    }

    public void setRelaAttatypeCust(String relaAttatypeCust) {
        this.relaAttatypeCust = relaAttatypeCust;
    }

    public String getRelaAttaaddrCust() {
        return relaAttaaddrCust;
    }

    public void setRelaAttaaddrCust(String relaAttaaddrCust) {
        this.relaAttaaddrCust = relaAttaaddrCust;
    }

    public Date getRelaAttavalidCust() {
        return relaAttavalidCust;
    }

    public void setRelaAttavalidCust(Date relaAttavalidCust) {
        this.relaAttavalidCust = relaAttavalidCust;
    }

    public String getRelaAttanameSupp() {
        return relaAttanameSupp;
    }

    public void setRelaAttanameSupp(String relaAttanameSupp) {
        this.relaAttanameSupp = relaAttanameSupp;
    }
}
