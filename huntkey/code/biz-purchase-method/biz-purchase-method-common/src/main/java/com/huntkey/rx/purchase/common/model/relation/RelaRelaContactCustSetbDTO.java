package com.huntkey.rx.purchase.common.model.relation;

import com.alibaba.fastjson.annotation.JSONField;
import com.huntkey.rx.edm.constant.RelaRelaContactCustSetbProperty;
import com.huntkey.rx.sceo.orm.common.constant.EdmSysColumn;

/**
 * Created by xuyf on 2018/1/18 0018.
 */
public class RelaRelaContactCustSetbDTO {

    @JSONField(name = EdmSysColumn.ID)
    private String id;

    @JSONField(name = EdmSysColumn.PID)
    private String pid;

    @JSONField(name = RelaRelaContactCustSetbProperty.RELA_CONTTYPE_CUST)
    private String relaConttypeCust;

    @JSONField(name = RelaRelaContactCustSetbProperty.RELA_CONTNAME_CUST)
    private String relaContnameCust;

    @JSONField(name = RelaRelaContactCustSetbProperty.RELA_CONTSEX_CUST)
    private String relaContsexCust;

    @JSONField(name = RelaRelaContactCustSetbProperty.RELA_CONTPOST_CUST)
    private String relaContpostCust;

    @JSONField(name = RelaRelaContactCustSetbProperty.RELA_CONTTEL_CUST)
    private String relaConttelCust;

    @JSONField(name = RelaRelaContactCustSetbProperty.RELA_CONTEMAIL_CUST)
    private String relaContemailCust;

    @JSONField(name = RelaRelaContactCustSetbProperty.RELA_CONTOTHER_CUST)
    private String relaContotherCust;

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

    public String getRelaConttypeCust() {
        return relaConttypeCust;
    }

    public void setRelaConttypeCust(String relaConttypeCust) {
        this.relaConttypeCust = relaConttypeCust;
    }

    public String getRelaContnameCust() {
        return relaContnameCust;
    }

    public void setRelaContnameCust(String relaContnameCust) {
        this.relaContnameCust = relaContnameCust;
    }

    public String getRelaContsexCust() {
        return relaContsexCust;
    }

    public void setRelaContsexCust(String relaContsexCust) {
        this.relaContsexCust = relaContsexCust;
    }

    public String getRelaContpostCust() {
        return relaContpostCust;
    }

    public void setRelaContpostCust(String relaContpostCust) {
        this.relaContpostCust = relaContpostCust;
    }

    public String getRelaConttelCust() {
        return relaConttelCust;
    }

    public void setRelaConttelCust(String relaConttelCust) {
        this.relaConttelCust = relaConttelCust;
    }

    public String getRelaContemailCust() {
        return relaContemailCust;
    }

    public void setRelaContemailCust(String relaContemailCust) {
        this.relaContemailCust = relaContemailCust;
    }

    public String getRelaContotherCust() {
        return relaContotherCust;
    }

    public void setRelaContotherCust(String relaContotherCust) {
        this.relaContotherCust = relaContotherCust;
    }
}
