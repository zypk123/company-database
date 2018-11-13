package com.huntkey.rx.sceo.formula.common.model;

import java.util.Date;

/**
 * @author chenfei on 2017/5/15.
 */
public class TvmSystemVariantMapping {
    /** 编号 */
    private String systId;

    /** 公式编号 */
    private String systFormulaId;

    /** 变量编号 */
    private String systVarId;

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

    public String getSystId() {
        return systId;
    }

    public void setSystId(String systId) {
        this.systId = systId == null ? null : systId.trim();
    }

    public String getSystFormulaId() {
        return systFormulaId;
    }

    public void setSystFormulaId(String systFormulaId) {
        this.systFormulaId = systFormulaId == null ? null : systFormulaId.trim();
    }

    public String getSystVarId() {
        return systVarId;
    }

    public void setSystVarId(String systVarId) {
        this.systVarId = systVarId == null ? null : systVarId.trim();
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