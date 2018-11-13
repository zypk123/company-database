package com.huntkey.rx.modeler.common.model;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class EdmConvolute {
    private String id;// 主键
    @NotNull
    private String edcoEdmpId;// 属性id

    private String edcoConvoluteFormula;// 卷积公式

    private String edcoFormulaDesc;// 卷积描述

    private Byte isDel;// 是否被删除

    private Date addtime;// 添加时间

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

    public String getEdcoEdmpId() {
        return edcoEdmpId;
    }

    public void setEdcoEdmpId(String edcoEdmpId) {
        this.edcoEdmpId = edcoEdmpId == null ? null : edcoEdmpId.trim();
    }

    public String getEdcoConvoluteFormula() {
        return edcoConvoluteFormula;
    }

    public void setEdcoConvoluteFormula(String edcoConvoluteFormula) {
        this.edcoConvoluteFormula = edcoConvoluteFormula == null ? null : edcoConvoluteFormula.trim();
    }

    public String getEdcoFormulaDesc() {
        return edcoFormulaDesc;
    }

    public void setEdcoFormulaDesc(String edcoFormulaDesc) {
        this.edcoFormulaDesc = edcoFormulaDesc == null ? null : edcoFormulaDesc.trim();
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