package com.huntkey.rx.modeler.common.model;

import java.util.Date;

public class EdmNumber {
    private String id;

    private Integer edmnType;

    private String edmnEncode;

    private Integer edmnSerialNumber;

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

    public Integer getEdmnType() {
        return edmnType;
    }

    public void setEdmnType(Integer edmnType) {
        this.edmnType = edmnType;
    }

    public String getEdmnEncode() {
        return edmnEncode;
    }

    public void setEdmnEncode(String edmnEncode) {
        this.edmnEncode = edmnEncode == null ? null : edmnEncode.trim();
    }

    public Integer getEdmnSerialNumber() {
        return edmnSerialNumber;
    }

    public void setEdmnSerialNumber(Integer edmnSerialNumber) {
        this.edmnSerialNumber = edmnSerialNumber;
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