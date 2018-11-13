package com.huntkey.rx.modeler.common.model;

import java.util.Date;

public class EdmPropertyGroup {
    private String id;

    private String edpgPropertyGroup;

    private String edpgEdmcId;

    private String edpgLinkId;

    private String edpgEdmpId;

    private Byte isSource;

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

    public String getEdpgPropertyGroup() {
        return edpgPropertyGroup;
    }

    public void setEdpgPropertyGroup(String edpgPropertyGroup) {
        this.edpgPropertyGroup = edpgPropertyGroup == null ? null : edpgPropertyGroup.trim();
    }

    public String getEdpgEdmcId() {
        return edpgEdmcId;
    }

    public void setEdpgEdmcId(String edpgEdmcId) {
        this.edpgEdmcId = edpgEdmcId == null ? null : edpgEdmcId.trim();
    }

    public String getEdpgLinkId() {
        return edpgLinkId;
    }

    public void setEdpgLinkId(String edpgLinkId) {
        this.edpgLinkId = edpgLinkId == null ? null : edpgLinkId.trim();
    }

    public String getEdpgEdmpId() {
        return edpgEdmpId;
    }

    public void setEdpgEdmpId(String edpgEdmpId) {
        this.edpgEdmpId = edpgEdmpId == null ? null : edpgEdmpId.trim();
    }

    public Byte getIsSource() {
        return isSource;
    }

    public void setIsSource(Byte isSource) {
        this.isSource = isSource;
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