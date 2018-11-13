package com.huntkey.rx.modeler.common.model;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

public class EdmLink {

    private String id;//主键id

    @NotEmpty
    private String edmlEdmpId;  //关联属性id

    @NotEmpty
    private String edmlEdmpLinkId; //被关联的属性id

    private String edmlCond; //关联的条件

    private String edmlFormula; //对象定位公式

    private Byte isDel;  //是否被删除

    private Date addtime; // 添加时间

    private String adduser;   // 添加用户

    private Date modtime;  // 修改时间

    private String moduser;// 修改用户

    private Byte acctid; // 账户标识

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }
    public String getEdmlEdmpId() {
        return edmlEdmpId;
    }
    public void setEdmlEdmpId(String edmlEdmpId) {
        this.edmlEdmpId = edmlEdmpId == null ? null : edmlEdmpId.trim();
    }
    public String getEdmlEdmpLinkId() {
        return edmlEdmpLinkId;
    }
    public void setEdmlEdmpLinkId(String edmlEdmpLinkId) {
        this.edmlEdmpLinkId = edmlEdmpLinkId == null ? null : edmlEdmpLinkId.trim();
    }

    public String getEdmlCond() {
        return edmlCond;
    }

    public void setEdmlCond(String edmlCond) {
        this.edmlCond = edmlCond == null ? null : edmlCond.trim();
    }

    public String getEdmlFormula() {
        return edmlFormula;
    }

    public void setEdmlFormula(String edmlFormula) {
        this.edmlFormula = edmlFormula == null ? null : edmlFormula.trim();
    }

    public Byte getIsDel() {
        return isDel;
    }

    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }

    public Date getAddtime() {
        if(this.addtime != null){
            return (Date)this.addtime.clone();
        }else{
            return null;
        }
    }

    public void setAddtime(Date addtime) {
        if(addtime != null){
            this.addtime = (Date)addtime.clone();
        }else{
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
        if(this.modtime != null){
            return (Date) this.modtime.clone();
        }else{
            return null;
        }
    }

    public void setModtime(Date modtime) {
        if(modtime != null){
            this.modtime = (Date)modtime.clone();
        }else{
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
                ", edmlEdmpId:'" + edmlEdmpId + '\'' +
                ", edmlEdmpLinkId:'" + edmlEdmpLinkId + '\'' +
                ", edmlCond:'" + edmlCond + '\'' +
                ", edmlFormula:'" + edmlFormula + '\'' +
                ", isDel:" + isDel +
                ", addtime:" + addtime +
                ", adduser:'" + adduser + '\'' +
                ", modtime:" + modtime +
                ", moduser:'" + moduser + '\'' +
                ", acctid:" + acctid +
                '}';
    }
}