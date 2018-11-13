package com.huntkey.rx.sceo.formula.common.model;

import com.huntkey.rx.sceo.formula.common.base.BaseEntity;

import java.util.Date;

/**
 * @author chenfei on 2017/5/15.
 */
public class TplPropertyLimit extends BaseEntity {
    /** 编号 */
    private String prprId;

    /** 属性元数据 */
    private String prprPropMata;

    /** 属性表现值 */
    private String prprPropDisplay;

    /** 条件公式 */
    private String prprConditionFormula;

    /** 条件名称 */
    private String prprConditionName;

    /** 条件描述 */
    private String prprConditionDesc;

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

    public String getPrprId() {
        return prprId;
    }

    public void setPrprId(String prprId) {
        this.prprId = prprId == null ? null : prprId.trim();
    }

    public String getPrprPropMata() {
        return prprPropMata;
    }

    public void setPrprPropMata(String prprPropMata) {
        this.prprPropMata = prprPropMata == null ? null : prprPropMata.trim();
    }

    public String getPrprPropDisplay() {
        return prprPropDisplay;
    }

    public void setPrprPropDisplay(String prprPropDisplay) {
        this.prprPropDisplay = prprPropDisplay == null ? null : prprPropDisplay.trim();
    }

    public String getPrprConditionFormula() {
        return prprConditionFormula;
    }

    public void setPrprConditionFormula(String prprConditionFormula) {
        this.prprConditionFormula = prprConditionFormula == null ? null : prprConditionFormula.trim();
    }

    public String getPrprConditionName() {
        return prprConditionName;
    }

    public void setPrprConditionName(String prprConditionName) {
        this.prprConditionName = prprConditionName == null ? null : prprConditionName.trim();
    }

    public String getPrprConditionDesc() {
        return prprConditionDesc;
    }

    public void setPrprConditionDesc(String prprConditionDesc) {
        this.prprConditionDesc = prprConditionDesc == null ? null : prprConditionDesc.trim();
    }
    @Override
    public Byte getIsenable() {
        return isenable;
    }
    @Override
    public void setIsenable(Byte isenable) {
        this.isenable = isenable;
    }
    @Override
    public Date getAddtime() {
        return addtime;
    }
    @Override
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
    @Override
    public String getAdduser() {
        return adduser;
    }
    @Override
    public void setAdduser(String adduser) {
        this.adduser = adduser == null ? null : adduser.trim();
    }
    @Override
    public Date getModtime() {
        return modtime;
    }
    @Override
    public void setModtime(Date modtime) {
        this.modtime = modtime;
    }
    @Override
    public String getModuser() {
        return moduser;
    }
    @Override
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