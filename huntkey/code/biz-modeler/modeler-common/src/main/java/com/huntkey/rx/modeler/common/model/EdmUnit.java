package com.huntkey.rx.modeler.common.model;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

public class EdmUnit {
    private String id;//主键id
    @NotEmpty
    private String edunEdmpId;//单位属性id
    @NotEmpty
    private String edunQtyEdmpId;//数值属性id

    private Byte isDel;//是否删除状态位

    private Date addtime;//创建时间

    private String adduser;//创建人

    private Date modtime;//修改时间

    private String moduser; //修改人

    private Byte acctid;//账户标识

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getEdunEdmpId() {
        return edunEdmpId;
    }

    public void setEdunEdmpId(String edunEdmpId) {
        this.edunEdmpId = edunEdmpId == null ? null : edunEdmpId.trim();
    }

    public String getEdunQtyEdmpId() {
        return edunQtyEdmpId;
    }

    public void setEdunQtyEdmpId(String edunQtyEdmpId) {
        this.edunQtyEdmpId = edunQtyEdmpId == null ? null : edunQtyEdmpId.trim();
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