package com.huntkey.rx.modeler.common.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class EdmMethodType {

    //主键id
    private String id;

    //方法分类父id
    private String edmtParentId;

    //方法分类编码
    @NotNull(message = "分类编码不能为空")
    private String edmtCode;

    //方法分类名称
    @NotNull(message = "分类名称不能为空")
    private String edmtName;

    //方法分类描述
    private String edmtDesc;

    //方法分类排序
    private Integer edmtSeq;

    //是否删除状态位
    private Byte isDel;

    //创建时间
    private Date addtime;

    //创建人
    private String adduser;

    //修改时间
    private Date modtime;

    //修改人
    private String moduser;

    //账号标示
    private Byte acctid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getEdmtParentId() {
        return edmtParentId;
    }

    public void setEdmtParentId(String edmtParentId) {
        this.edmtParentId = edmtParentId == null ? null : edmtParentId.trim();
    }

    public String getEdmtCode() {
        return edmtCode;
    }

    public void setEdmtCode(String edmtCode) {
        this.edmtCode = edmtCode == null ? null : edmtCode.trim();
    }

    public String getEdmtName() {
        return edmtName;
    }

    public void setEdmtName(String edmtName) {
        this.edmtName = edmtName == null ? null : edmtName.trim();
    }

    public String getEdmtDesc() {
        return edmtDesc;
    }

    public void setEdmtDesc(String edmtDesc) {
        this.edmtDesc = edmtDesc == null ? null : edmtDesc.trim();
    }

    public Integer getEdmtSeq() {
        return edmtSeq;
    }

    public void setEdmtSeq(Integer edmtSeq) {
        this.edmtSeq = edmtSeq;
    }

    public Byte getIsDel() {
        return isDel;
    }

    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }

    public Date getAddtime() {
        if (this.addtime != null) {
            return (Date)this.addtime.clone();
        } else {
            return null;
        }
    }

    public void setAddtime(Date addtime) {
        if (addtime != null) {
            this.addtime = (Date) addtime.clone();
        } else {
            this.addtime = null;
        }

    }

    public String getAdduser() {
        return adduser;
    }

    public void setAdduser(String adduser) {
        this.adduser = adduser == null ? null : adduser.trim();
    }

    public Date getModtime() {
        if (this.modtime != null) {
            return (Date) this.modtime.clone();
        } else {
            return null;
        }

    }

    public void setModtime(Date modtime) {
        if (modtime != null) {
            this.modtime = (Date) modtime.clone();
        } else {
            this.modtime = null;
        }
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

    @Override
    public String toString() {
        return "${" +
                "id:'" + id + '\'' +
                ", edmtParentId:'" + edmtParentId + '\'' +
                ", edmtCode:'" + edmtCode + '\'' +
                ", edmtName:'" + edmtName + '\'' +
                ", edmtDesc:'" + edmtDesc + '\'' +
                ", edmtSeq:" + edmtSeq +
                ", isDel:" + isDel +
                ", addtime:" + addtime +
                ", adduser:'" + adduser + '\'' +
                ", modtime:" + modtime +
                ", moduser:'" + moduser + '\'' +
                ", acctid:" + acctid +
                '}';
    }
}