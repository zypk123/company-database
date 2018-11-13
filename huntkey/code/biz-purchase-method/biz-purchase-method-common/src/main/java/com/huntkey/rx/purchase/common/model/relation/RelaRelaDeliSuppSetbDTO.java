package com.huntkey.rx.purchase.common.model.relation;

import com.alibaba.fastjson.annotation.JSONField;
import com.huntkey.rx.edm.constant.RelaRelaDeliSuppSetbProperty;
import com.huntkey.rx.sceo.orm.common.constant.EdmSysColumn;

/**
 * Created by xuyf on 2018/1/18 0018.
 */
public class RelaRelaDeliSuppSetbDTO {

    @JSONField(name = EdmSysColumn.ID)
    private String id;

    @JSONField(name = EdmSysColumn.PID)
    private String pid;

    @JSONField(name = RelaRelaDeliSuppSetbProperty.RELA_DANAME_SUPP)
    private String relaDanameSupp;

    @JSONField(name = RelaRelaDeliSuppSetbProperty.RELA_DADDRP_SUPP)
    private String relaDaddrpSupp;

    @JSONField(name = RelaRelaDeliSuppSetbProperty.RELA_DADDRC_SUPP)
    private String relaDaddrcSupp;

    @JSONField(name = RelaRelaDeliSuppSetbProperty.RELA_DADDRL_SUPP)
    private String relaDaddrlSupp;

    @JSONField(name = RelaRelaDeliSuppSetbProperty.RELA_DDADDR_SUPP)
    private String relaDdaddrSupp;

    @JSONField(name = RelaRelaDeliSuppSetbProperty.RELA_DCONTACT_SUPP)
    private String relaDcontactSupp;

    @JSONField(name = RelaRelaDeliSuppSetbProperty.RELA_DCWAY_SUPP)
    private String relaDcwaySupp;

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

    public String getRelaDanameSupp() {
        return relaDanameSupp;
    }

    public void setRelaDanameSupp(String relaDanameSupp) {
        this.relaDanameSupp = relaDanameSupp;
    }

    public String getRelaDaddrpSupp() {
        return relaDaddrpSupp;
    }

    public void setRelaDaddrpSupp(String relaDaddrpSupp) {
        this.relaDaddrpSupp = relaDaddrpSupp;
    }

    public String getRelaDaddrcSupp() {
        return relaDaddrcSupp;
    }

    public void setRelaDaddrcSupp(String relaDaddrcSupp) {
        this.relaDaddrcSupp = relaDaddrcSupp;
    }

    public String getRelaDaddrlSupp() {
        return relaDaddrlSupp;
    }

    public void setRelaDaddrlSupp(String relaDaddrlSupp) {
        this.relaDaddrlSupp = relaDaddrlSupp;
    }

    public String getRelaDdaddrSupp() {
        return relaDdaddrSupp;
    }

    public void setRelaDdaddrSupp(String relaDdaddrSupp) {
        this.relaDdaddrSupp = relaDdaddrSupp;
    }

    public String getRelaDcontactSupp() {
        return relaDcontactSupp;
    }

    public void setRelaDcontactSupp(String relaDcontactSupp) {
        this.relaDcontactSupp = relaDcontactSupp;
    }

    public String getRelaDcwaySupp() {
        return relaDcwaySupp;
    }

    public void setRelaDcwaySupp(String relaDcwaySupp) {
        this.relaDcwaySupp = relaDcwaySupp;
    }
}
