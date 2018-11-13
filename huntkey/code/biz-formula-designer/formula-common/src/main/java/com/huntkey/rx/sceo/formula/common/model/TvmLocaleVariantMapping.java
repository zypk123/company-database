package com.huntkey.rx.sceo.formula.common.model;

import java.util.Date;

/**
 * @author chenfei on 2017/5/15.
 */
public class TvmLocaleVariantMapping {
    /** 编号 */
    private String lclvId;

    /** 公式编号 */
    private String lclvFormulaId;

    /** 变量编号 */
    private String lclvVarId;

    /** 是否可用: 1 可用， 0 不可用 */
    private Byte isenable;

    /** 创建时间 */
    private Date addtime;

    /** 创建人 */
    private String adduser;

    /** 更新时间 */
    private Date modtime;

    /** 修改人 */
    private String moduser;

    /** 区域标识 */
    private Integer acctid;

    public String getLclvId() {
        return lclvId;
    }

    public void setLclvId(String lclvId) {
        this.lclvId = lclvId == null ? null : lclvId.trim();
    }

    public String getLclvFormulaId() {
        return lclvFormulaId;
    }

    public void setLclvFormulaId(String lclvFormulaId) {
        this.lclvFormulaId = lclvFormulaId == null ? null : lclvFormulaId.trim();
    }

    public String getLclvVarId() {
        return lclvVarId;
    }

    public void setLclvVarId(String lclvVarId) {
        this.lclvVarId = lclvVarId == null ? null : lclvVarId.trim();
    }

    public Byte getIsenable() {
        return isenable;
    }

    public void setIsenable(Byte isenable) {
        this.isenable = isenable;
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

    public Integer getAcctid() {
        return acctid;
    }

    public void setAcctid(Integer acctid) {
        this.acctid = acctid;
    }
}