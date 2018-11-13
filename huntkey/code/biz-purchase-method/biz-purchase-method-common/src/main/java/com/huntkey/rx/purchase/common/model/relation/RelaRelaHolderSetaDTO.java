package com.huntkey.rx.purchase.common.model.relation;

import com.alibaba.fastjson.annotation.JSONField;
import com.huntkey.rx.edm.constant.RelaRelaHolderSetaProperty;
import com.huntkey.rx.sceo.orm.common.constant.EdmSysColumn;

import java.math.BigDecimal;

/**
 * Created by xuyf on 2018/1/17 0017.
 */
public class RelaRelaHolderSetaDTO {

    @JSONField(name = EdmSysColumn.ID)
    private String id;

    @JSONField(name = EdmSysColumn.PID)
    private String pid;

    @JSONField(name = RelaRelaHolderSetaProperty.RELA_HNAME)
    private String relaHname;

    @JSONField(name = RelaRelaHolderSetaProperty.RELA_HRATE)
    private BigDecimal relaHrate;

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

    public String getRelaHname() {
        return relaHname;
    }

    public void setRelaHname(String relaHname) {
        this.relaHname = relaHname;
    }

    public BigDecimal getRelaHrate() {
        return relaHrate;
    }

    public void setRelaHrate(BigDecimal relaHrate) {
        this.relaHrate = relaHrate;
    }
}
