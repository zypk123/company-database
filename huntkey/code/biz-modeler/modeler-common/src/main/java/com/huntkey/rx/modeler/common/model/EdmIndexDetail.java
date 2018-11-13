package com.huntkey.rx.modeler.common.model;

import java.util.Date;

public class EdmIndexDetail {
    private String id;

    private String indexId;

    private String edmpId;

    private Byte indexSeq;

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

    public String getIndexId() {
        return indexId;
    }

    public void setIndexId(String indexId) {
        this.indexId = indexId == null ? null : indexId.trim();
    }

    public String getEdmpId() {
        return edmpId;
    }

    public void setEdmpId(String edmpId) {
        this.edmpId = edmpId == null ? null : edmpId.trim();
    }

    public Byte getIndexSeq() {
        return indexSeq;
    }

    public void setIndexSeq(Byte indexSeq) {
        this.indexSeq = indexSeq;
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