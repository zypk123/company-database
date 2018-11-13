package com.huntkey.rx.modeler.common.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

import java.util.Date;

public class EdmModeler {
    //主键id
    private String id;

    //标准模型id
    private String edmdParentId;

    //模型编码
    private String edmdCode;

    //模型版本
    @NotNull(message = "模型版本不能为空")
    private String edmdVer;

    //模型更新说明
    @Length(max = 800)
    private String edmdUpdateDesc;

    //模型状态
    @NotNull
    private Byte edmdStatus;

    //模型发布时间
    private Date edmdReleaseTime;

    //模型失效时间
    private Date edmdExpireTime;

    //是否删除状态
    private Byte isDel;

    //创建时间
    private Date addtime;

    //创建人
    private String adduser;

    //修改时间
    private Date modtime;

    //修改人
    private String moduser;

    //账户标识
    private Byte acctid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getEdmdParentId() {
        return edmdParentId;
    }

    public void setEdmdParentId(String edmdParentId) {
        this.edmdParentId = edmdParentId == null ? null : edmdParentId.trim();
    }

    public String getEdmdCode() {
        return edmdCode;
    }

    public void setEdmdCode(String edmdCode) {
        this.edmdCode = edmdCode == null ? null : edmdCode.trim();
    }

    public String getEdmdVer() {
        return edmdVer;
    }

    public void setEdmdVer(String edmdVer) {
        this.edmdVer = edmdVer == null ? null : edmdVer.trim();
    }

    public String getEdmdUpdateDesc() {
        return edmdUpdateDesc;
    }

    public void setEdmdUpdateDesc(String edmdUpdateDesc) {
        this.edmdUpdateDesc = edmdUpdateDesc == null ? null : edmdUpdateDesc.trim();
    }

    public Byte getEdmdStatus() {
        return edmdStatus;
    }

    public void setEdmdStatus(Byte edmdStatus) {
        this.edmdStatus = edmdStatus;
    }

    public Date getEdmdReleaseTime() {
        if(this.edmdReleaseTime!=null){
            return (Date)this.edmdReleaseTime.clone();
        }else{
            return null;
        }
    }

    public void setEdmdReleaseTime(Date edmdReleaseTime) {
        if(edmdReleaseTime!=null){
            this.edmdReleaseTime = (Date)edmdReleaseTime.clone();
        }else{
            this.edmdReleaseTime = null;
        }
    }

    public Date getEdmdExpireTime() {
        if(this.edmdExpireTime!=null){
            return (Date)this.edmdExpireTime.clone();
        }else{
            return null;
        }
    }

    public void setEdmdExpireTime(Date edmdExpireTime) {
        if(edmdExpireTime!=null){
            this.edmdExpireTime = (Date)edmdExpireTime.clone();
        }else{
            this.edmdExpireTime = null;
        }
    }

    public Byte getIsDel() {
        return isDel;
    }

    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }

    public Date getAddtime() {
        if(this.addtime!=null){
            return (Date)this.addtime.clone();
        }else{
            return null;
        }
    }

    public void setAddtime(Date addtime) {
        if(addtime!=null){
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
        if(this.modtime!=null){
            return (Date) this.modtime.clone();
        }else{
            return null;
        }
    }

    public void setModtime(Date modtime) {
        if(modtime!=null){
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
                ", edmdParentId:'" + edmdParentId + '\'' +
                ", edmdCode:'" + edmdCode + '\'' +
                ", edmdVer:'" + edmdVer + '\'' +
                ", edmdUpdateDesc:'" + edmdUpdateDesc + '\'' +
                ", edmdStatus:" + edmdStatus +
                ", edmdReleaseTime:" + edmdReleaseTime +
                ", edmdExpireTime:" + edmdExpireTime +
                ", isDel:" + isDel +
                ", addtime:" + addtime +
                ", adduser:'" + adduser + '\'' +
                ", modtime:" + modtime +
                ", moduser:'" + moduser + '\'' +
                ", acctid:" + acctid +
                '}';
    }
}