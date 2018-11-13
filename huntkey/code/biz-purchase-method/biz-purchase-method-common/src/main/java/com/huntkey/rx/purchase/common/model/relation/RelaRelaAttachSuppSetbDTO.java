package com.huntkey.rx.purchase.common.model.relation;

import com.alibaba.fastjson.annotation.JSONField;
import com.huntkey.rx.edm.constant.RelaRelaAttachSuppSetbProperty;
import com.huntkey.rx.sceo.orm.common.constant.EdmSysColumn;

import java.util.Date;

/**
 * Created by xuyf on 2018/1/18 0018.
 */
public class RelaRelaAttachSuppSetbDTO {

    @JSONField(name = EdmSysColumn.ID)
    private String id;

    @JSONField(name = EdmSysColumn.PID)
    private String pid;

    @JSONField(name = RelaRelaAttachSuppSetbProperty.RELA_ATTATYPE_SUPP)
    private String relaAttatypeSupp;

    @JSONField(name = RelaRelaAttachSuppSetbProperty.RELA_ATTAADDR_SUPP)
    private String relaAttaaddrSupp;

    @JSONField(name = RelaRelaAttachSuppSetbProperty.RELA_ATTAVALID_SUPP)
    private Date relaAttavalidSupp;

    @JSONField(name = RelaRelaAttachSuppSetbProperty.RELA_ATTANAME_SUPP)
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

    public String getRelaAttatypeSupp() {
        return relaAttatypeSupp;
    }

    public void setRelaAttatypeSupp(String relaAttatypeSupp) {
        this.relaAttatypeSupp = relaAttatypeSupp;
    }

    public String getRelaAttaaddrSupp() {
        return relaAttaaddrSupp;
    }

    public void setRelaAttaaddrSupp(String relaAttaaddrSupp) {
        this.relaAttaaddrSupp = relaAttaaddrSupp;
    }

    public Date getRelaAttavalidSupp() {
        return relaAttavalidSupp;
    }

    public void setRelaAttavalidSupp(Date relaAttavalidSupp) {
        this.relaAttavalidSupp = relaAttavalidSupp;
    }

    public String getRelaAttanameSupp() {
        return relaAttanameSupp;
    }

    public void setRelaAttanameSupp(String relaAttanameSupp) {
        this.relaAttanameSupp = relaAttanameSupp;
    }
}
