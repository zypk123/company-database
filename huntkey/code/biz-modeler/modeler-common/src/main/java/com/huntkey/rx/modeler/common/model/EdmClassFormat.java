package com.huntkey.rx.modeler.common.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class EdmClassFormat {

    private String id;

    @NotNull(message = "类id不能为空")
    private String edmfEdmcId;

    private String edmfEdmpId;

    @Length(min = 1,max = 8,message = "连接符长度不能大于8")
    private String edmfConnector;

    private Integer edmfSeq;

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
        this.id = id;
    }

    public String getEdmfEdmcId() {
        return edmfEdmcId;
    }

    public void setEdmfEdmcId(String edmfEdmcId) {
        this.edmfEdmcId = edmfEdmcId;
    }

    public String getEdmfEdmpId() {
        return edmfEdmpId;
    }

    public void setEdmfEdmpId(String edmfEdmpId) {
        this.edmfEdmpId = edmfEdmpId;
    }

    public String getEdmfConnector() {
        return edmfConnector;
    }

    public void setEdmfConnector(String edmfConnector) {
        this.edmfConnector = edmfConnector;
    }

    public Integer getEdmfSeq() {
        return edmfSeq;
    }

    public void setEdmfSeq(Integer edmfSeq) {
        this.edmfSeq = edmfSeq;
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
        this.adduser = adduser;
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
        this.moduser = moduser;
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
                ", edmfEdmcId:'" + edmfEdmcId + '\'' +
                ", edmfEdmpId:'" + edmfEdmpId + '\'' +
                ", edmfConnector:'" + edmfConnector + '\'' +
                ", edmfSeq:" + edmfSeq +
                ", isDel:" + isDel +
                ", addtime:" + addtime +
                ", adduser:'" + adduser + '\'' +
                ", modtime:" + modtime +
                ", moduser:'" + moduser + '\'' +
                ", acctid:" + acctid +
                '}';
    }
}