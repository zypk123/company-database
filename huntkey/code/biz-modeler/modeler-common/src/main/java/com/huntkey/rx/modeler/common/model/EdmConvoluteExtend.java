package com.huntkey.rx.modeler.common.model;

import java.util.Date;

public class EdmConvoluteExtend {
    private String id;

    private String edceEdmpId;

    private String edceEnumValue;

    private Integer edceSeq;

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

    public String getEdceEdmpId() {
        return edceEdmpId;
    }

    public void setEdceEdmpId(String edceEdmpId) {
        this.edceEdmpId = edceEdmpId == null ? null : edceEdmpId.trim();
    }

    public String getEdceEnumValue() {
        return edceEnumValue;
    }

    public void setEdceEnumValue(String edceEnumValue) {
        this.edceEnumValue = edceEnumValue == null ? null : edceEnumValue.trim();
    }

    public Integer getEdceSeq() {
        return edceSeq;
    }

    public void setEdceSeq(Integer edceSeq) {
        this.edceSeq = edceSeq;
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