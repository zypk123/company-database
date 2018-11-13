package com.huntkey.rx.modeler.common.model;

import java.util.Date;

public class EdmIndex {
    private String id;

    private String edmcId;

    private String edmpAssId;

    private String indexName;

    private String indexType;

    private Byte isDel;

    private Date addtime;

    private String adduser;

    private Date modtime;

    private String moduser;

    private Byte acctid;

    private String indexVer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getEdmcId() {
        return edmcId;
    }

    public void setEdmcId(String edmcId) {
        this.edmcId = edmcId == null ? null : edmcId.trim();
    }

    public String getEdmpAssId() {
        return edmpAssId;
    }

    public void setEdmpAssId(String edmpAssId) {
        this.edmpAssId = edmpAssId == null ? null : edmpAssId.trim();
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName == null ? null : indexName.trim();
    }

    public String getIndexType() {
        return indexType;
    }

    public void setIndexType(String indexType) {
        this.indexType = indexType == null ? null : indexType.trim();
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

    public String getIndexVer() {
        return indexVer;
    }

    public void setIndexVer(String indexVer) {
        this.indexVer = indexVer == null ? null : indexVer.trim();
    }
}