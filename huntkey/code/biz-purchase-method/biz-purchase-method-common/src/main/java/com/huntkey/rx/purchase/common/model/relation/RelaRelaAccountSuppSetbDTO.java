package com.huntkey.rx.purchase.common.model.relation;

import com.alibaba.fastjson.annotation.JSONField;
import com.huntkey.rx.edm.constant.RelaRelaAccountSuppSetbProperty;
import com.huntkey.rx.sceo.orm.common.constant.EdmSysColumn;

import java.util.Date;

/**
 * Created by xuyf on 2018/1/18 0018.
 */
public class RelaRelaAccountSuppSetbDTO {

    @JSONField(name = EdmSysColumn.ID)
    private String id;

    @JSONField(name = EdmSysColumn.PID)
    private String pid;

    @JSONField(name = RelaRelaAccountSuppSetbProperty.RELA_ACCONAME_SUPP)
    private String relaAcconameSupp;

    @JSONField(name = RelaRelaAccountSuppSetbProperty.RELA_ACCOBANK_SUPP)
    private String relaAccobankSupp;

    @JSONField(name = RelaRelaAccountSuppSetbProperty.RELA_ACCONUM_SUPP)
    private String relaAcconumSupp;

    @JSONField(name = RelaRelaAccountSuppSetbProperty.RELA_ACCOCURR_SUPP)
    private String relaAccocurrSupp;

    @JSONField(name = RelaRelaAccountSuppSetbProperty.RELA_ACCOBEG_SUPP)
    private Date relaAccobegSupp;

    @JSONField(name = RelaRelaAccountSuppSetbProperty.RELA_ACCOEND_SUPP)
    private Date relaAccoendSupp;

    @JSONField(name = RelaRelaAccountSuppSetbProperty.RELA_ATTAACCO_SUPP)
    private String relaAttaaccoSupp;

    @JSONField(name = RelaRelaAccountSuppSetbProperty.RELA_ATTAN_SUPP)
    private String relaAttanSupp;

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

    public String getRelaAcconameSupp() {
        return relaAcconameSupp;
    }

    public void setRelaAcconameSupp(String relaAcconameSupp) {
        this.relaAcconameSupp = relaAcconameSupp;
    }

    public String getRelaAccobankSupp() {
        return relaAccobankSupp;
    }

    public void setRelaAccobankSupp(String relaAccobankSupp) {
        this.relaAccobankSupp = relaAccobankSupp;
    }

    public String getRelaAcconumSupp() {
        return relaAcconumSupp;
    }

    public void setRelaAcconumSupp(String relaAcconumSupp) {
        this.relaAcconumSupp = relaAcconumSupp;
    }

    public String getRelaAccocurrSupp() {
        return relaAccocurrSupp;
    }

    public void setRelaAccocurrSupp(String relaAccocurrSupp) {
        this.relaAccocurrSupp = relaAccocurrSupp;
    }

    public Date getRelaAccobegSupp() {
        return relaAccobegSupp;
    }

    public void setRelaAccobegSupp(Date relaAccobegSupp) {
        this.relaAccobegSupp = relaAccobegSupp;
    }

    public Date getRelaAccoendSupp() {
        return relaAccoendSupp;
    }

    public void setRelaAccoendSupp(Date relaAccoendSupp) {
        this.relaAccoendSupp = relaAccoendSupp;
    }

    public String getRelaAttaaccoSupp() {
        return relaAttaaccoSupp;
    }

    public void setRelaAttaaccoSupp(String relaAttaaccoSupp) {
        this.relaAttaaccoSupp = relaAttaaccoSupp;
    }

    public String getRelaAttanSupp() {
        return relaAttanSupp;
    }

    public void setRelaAttanSupp(String relaAttanSupp) {
        this.relaAttanSupp = relaAttanSupp;
    }
}
