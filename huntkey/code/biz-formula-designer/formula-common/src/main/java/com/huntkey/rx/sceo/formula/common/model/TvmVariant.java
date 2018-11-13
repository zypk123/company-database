package com.huntkey.rx.sceo.formula.common.model;

import com.huntkey.rx.sceo.formula.common.base.BaseEntity;

import java.util.Date;

/**
 * @author chenfei on 2017/5/15.
 */
public class TvmVariant extends BaseEntity {
    /**
     * 编号
     */
    private String vrntId;

    /**
     * 变量名
     */
    private String vrntVarName;

    /**
     * 变量描述
     */
    private String vrntVarDesc;

    /**  */
    private String formulaId;

    /**
     * 变量类型 enum[math, text, date, set, other]
     */
    private String vrntVarType;

    /**
     * 变量范围 enum[system, local]
     */
    private String vrntVarScope;

    /**
     * 变量公式
     */
    private String vrntFormulaId;

    /**
     * 变量状态 enum[prohibit, inusing]
     */
    private String vrntState;

    /**
     * 变更摘要
     */
    private String vrntModifyRemarks;

    /**
     * 是否可用: 1 可用， 0 不可用
     */
    private Byte isenable;

    /**
     * 创建时间
     */
    private Date addtime;

    /**
     * 创建人
     */
    private String adduser;

    /**
     * 更新时间
     */
    private Date modtime;

    /**
     * 修改人
     */
    private String moduser;

    /**
     * 区域标识
     */
    private Integer acctid;

    public String getVrntId() {
        return vrntId;
    }

    public void setVrntId(String vrntId) {
        this.vrntId = vrntId == null ? null : vrntId.trim();
    }

    public String getVrntVarName() {
        return vrntVarName;
    }

    public void setVrntVarName(String vrntVarName) {
        this.vrntVarName = vrntVarName == null ? null : vrntVarName.trim();
    }

    public String getVrntVarDesc() {
        return vrntVarDesc;
    }

    public void setVrntVarDesc(String vrntVarDesc) {
        this.vrntVarDesc = vrntVarDesc == null ? null : vrntVarDesc.trim();
    }

    /**
     *  冗余字段，请使用getVrntFormulaId方法
     * @deprecated
     */
    public String getFormulaId() {
        return formulaId;
    }

    /**
     *  冗余字段，请使用setVrntFormulaId方法
     * @deprecated
     */
    public void setFormulaId(String formulaId) {
        this.formulaId = formulaId == null ? null : formulaId.trim();
    }

    public String getVrntVarType() {
        return vrntVarType;
    }

    public void setVrntVarType(String vrntVarType) {
        this.vrntVarType = vrntVarType == null ? null : vrntVarType.trim();
    }

    public String getVrntVarScope() {
        return vrntVarScope;
    }

    public void setVrntVarScope(String vrntVarScope) {
        this.vrntVarScope = vrntVarScope == null ? null : vrntVarScope.trim();
    }

    public String getVrntFormulaId() {
        return vrntFormulaId;
    }

    public void setVrntFormulaId(String vrntFormulaId) {
        this.vrntFormulaId = vrntFormulaId == null ? null : vrntFormulaId.trim();
    }

    public String getVrntState() {
        return vrntState;
    }

    public void setVrntState(String vrntState) {
        this.vrntState = vrntState == null ? null : vrntState.trim();
    }

    public String getVrntModifyRemarks() {
        return vrntModifyRemarks;
    }

    public void setVrntModifyRemarks(String vrntModifyRemarks) {
        this.vrntModifyRemarks = vrntModifyRemarks == null ? null : vrntModifyRemarks.trim();
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