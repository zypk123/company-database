package com.huntkey.rx.modeler.common.model.vo;

import java.util.Date;

public class EdmIndexVO {
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

    //============扩展字段================
    private String indexProppertyIds;

    private String edmcName;

    private String edmpName;

    private String indexPropertyNames;

    private String addtimeStr; // 添加时间字符串格式

    private String modtimeStr; // 修改时间字符串格式

    public String getAddtimeStr() {
        return addtimeStr;
    }

    public void setAddtimeStr(String addtimeStr) {
        this.addtimeStr = addtimeStr;
    }

    public String getModtimeStr() {
        return modtimeStr;
    }

    public void setModtimeStr(String modtimeStr) {
        this.modtimeStr = modtimeStr;
    }

    public String getEdmcName() {
        return edmcName;
    }

    public void setEdmcName(String edmcName) {
        this.edmcName = edmcName;
    }

    public String getEdmpName() {
        return edmpName;
    }

    public void setEdmpName(String edmpName) {
        this.edmpName = edmpName;
    }

    public String getIndexPropertyNames() {
        return indexPropertyNames;
    }

    public void setIndexPropertyNames(String indexPropertyNames) {
        this.indexPropertyNames = indexPropertyNames;
    }

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

    public String getIndexProppertyIds() {
        return indexProppertyIds;
    }

    public void setIndexProppertyIds(String indexProppertyIds) {
        this.indexProppertyIds = indexProppertyIds;
    }
}