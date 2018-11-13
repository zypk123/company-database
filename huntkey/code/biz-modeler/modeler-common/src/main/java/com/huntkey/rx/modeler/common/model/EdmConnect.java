package com.huntkey.rx.modeler.common.model;

import java.util.Date;

public class EdmConnect {
    private String id;// 主键

    private String edcnEdmpId;// 属性id

    private Byte edcnLinkPreservable;//是否联动记录

    private String edcnUpdateType;//更新类型

    private String edcnUpdateTime;// 更新时间

    private String edcnType;//联动方式

    private String asyncTypePriority;//异步类型优先级

    private Byte isDel;// 是否已经删除

    private Date addtime; // 添加时间

    private String adduser;// 添加用户

    private Date modtime;// 修改时间

    private String moduser;// 修改用户

    private Byte acctid;// 账户标识

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getEdcnEdmpId() {
        return edcnEdmpId;
    }

    public void setEdcnEdmpId(String edcnEdmpId) {
        this.edcnEdmpId = edcnEdmpId == null ? null : edcnEdmpId.trim();
    }

    public Byte getEdcnLinkPreservable() {
        return edcnLinkPreservable;
    }

    public void setEdcnLinkPreservable(Byte edcnLinkPreservable) {
        this.edcnLinkPreservable = edcnLinkPreservable;
    }

    public String getEdcnUpdateType() {
        return edcnUpdateType;
    }

    public void setEdcnUpdateType(String edcnUpdateType) {
        this.edcnUpdateType = edcnUpdateType == null ? null : edcnUpdateType.trim();
    }

    public String getEdcnUpdateTime() {
        return edcnUpdateTime;
    }

    public void setEdcnUpdateTime(String edcnUpdateTime) {
        this.edcnUpdateTime = edcnUpdateTime == null ? null : edcnUpdateTime.trim();
    }

    public String getEdcnType() {
        return edcnType;
    }

    public void setEdcnType(String edcnType) {
        this.edcnType = edcnType == null ? null : edcnType.trim();
    }

    public String getAsyncTypePriority() {
        return asyncTypePriority;
    }

    public void setAsyncTypePriority(String asyncTypePriority) {
        this.asyncTypePriority = asyncTypePriority == null ? null : asyncTypePriority.trim();
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