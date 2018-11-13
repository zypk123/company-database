package com.huntkey.rx.purchase.common.model.relation;

import com.alibaba.fastjson.annotation.JSONField;
import com.huntkey.rx.edm.constant.RelaRelaContactSuppSetbProperty;
import com.huntkey.rx.sceo.orm.common.constant.EdmSysColumn;

/**
 * Created by xuyf on 2018/1/18 0018.
 */
public class RelaRelaContactSuppSetbDTO {

    @JSONField(name = EdmSysColumn.ID)
    private String id;

    @JSONField(name = EdmSysColumn.PID)
    private String pid;

    @JSONField(name = RelaRelaContactSuppSetbProperty.RELA_CONTTYPE_SUPP)
    private String relaConttypeSupp;

    @JSONField(name = RelaRelaContactSuppSetbProperty.RELA_CONTNAME_SUPP)
    private String relaContnameSupp;

    @JSONField(name = RelaRelaContactSuppSetbProperty.RELA_CONTSEX_SUPP)
    private String relaContsexSupp;

    @JSONField(name = RelaRelaContactSuppSetbProperty.RELA_CONTPOST_SUPP)
    private String relaContpostSupp;

    @JSONField(name = RelaRelaContactSuppSetbProperty.RELA_CONTTEL_SUPP)
    private String relaConttelSupp;

    @JSONField(name = RelaRelaContactSuppSetbProperty.RELA_CONTEMAIL_SUPP)
    private String relaContemailSupp;

    @JSONField(name = RelaRelaContactSuppSetbProperty.RELA_CONTOTHER_SUPP)
    private String relaContotherSupp;

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

    public String getRelaConttypeSupp() {
        return relaConttypeSupp;
    }

    public void setRelaConttypeSupp(String relaConttypeSupp) {
        this.relaConttypeSupp = relaConttypeSupp;
    }

    public String getRelaContnameSupp() {
        return relaContnameSupp;
    }

    public void setRelaContnameSupp(String relaContnameSupp) {
        this.relaContnameSupp = relaContnameSupp;
    }

    public String getRelaContsexSupp() {
        return relaContsexSupp;
    }

    public void setRelaContsexSupp(String relaContsexSupp) {
        this.relaContsexSupp = relaContsexSupp;
    }

    public String getRelaContpostSupp() {
        return relaContpostSupp;
    }

    public void setRelaContpostSupp(String relaContpostSupp) {
        this.relaContpostSupp = relaContpostSupp;
    }

    public String getRelaConttelSupp() {
        return relaConttelSupp;
    }

    public void setRelaConttelSupp(String relaConttelSupp) {
        this.relaConttelSupp = relaConttelSupp;
    }

    public String getRelaContemailSupp() {
        return relaContemailSupp;
    }

    public void setRelaContemailSupp(String relaContemailSupp) {
        this.relaContemailSupp = relaContemailSupp;
    }

    public String getRelaContotherSupp() {
        return relaContotherSupp;
    }

    public void setRelaContotherSupp(String relaContotherSupp) {
        this.relaContotherSupp = relaContotherSupp;
    }
}
