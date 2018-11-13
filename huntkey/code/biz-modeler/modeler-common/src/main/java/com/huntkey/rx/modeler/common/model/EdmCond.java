package com.huntkey.rx.modeler.common.model;

import java.util.Date;

public class EdmCond {
    private String id;

    private String edcoEdmpId;//触发条件属性id

    private String edcoCond;//触发条件

    private Byte isDel;

    private Date addtime;

    private String adduser;

    private Date modtime;

    private String moduser;

    private Byte acctid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getEdcoEdmpId() {
        return edcoEdmpId;
    }

    public void setEdcoEdmpId(String edcoEdmpId) {
        this.edcoEdmpId = edcoEdmpId == null ? null : edcoEdmpId.trim();
    }

    public String getEdcoCond() {
        return edcoCond;
    }

    public void setEdcoCond(String edcoCond) {
        this.edcoCond = edcoCond == null ? null : edcoCond.trim();
    }

    public Byte getIsDel() {
        return isDel;
    }

    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getAdduser() {
        return adduser;
    }

    public void setAdduser(String adduser) {
        this.adduser = adduser == null ? null : adduser.trim();
    }

    public Date getModtime() {
        return modtime;
    }

    public void setModtime(Date modtime) {
        this.modtime = modtime;
    }

    public String getModuser() {
        return moduser;
    }

    public void setModuser(String moduser) {
        this.moduser = moduser == null ? null : moduser.trim();
    }

    public Byte getAcctid() {
        return acctid;
    }

    public void setAcctid(Byte acctid) {
        this.acctid = acctid;
    }
}