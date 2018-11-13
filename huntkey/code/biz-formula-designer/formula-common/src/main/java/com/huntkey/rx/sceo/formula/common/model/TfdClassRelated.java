package com.huntkey.rx.sceo.formula.common.model;

import com.huntkey.rx.sceo.formula.common.base.BaseEntity;

import java.util.Date;

/**
 * @author chenfei on 2017/5/15.
 */
public class TfdClassRelated extends BaseEntity {
    /** 编号 */
    private String clssId;

    /** 类id */
    private String clssClassId;

    /** 关联公式编号 */
    private String clssFormulaId;

    /** 顺序 */
    private Integer clssSeq;

    /** 相关类名称 */
    private String clssClassRelatedName;

    /** 相关类别名 */
    private String clssAliasName;

    /** edm类英文名称 */
    private String clssEdmcNameEn;

    /** 条件名称 */
    private String clssConditionName;

    /** 条件公式 */
    private String clssConditionFormula;

    /** 条件描述 */
    private String clssConditionDesc;

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

    /** type用来区分是class过来的还是formula  2class */
    private Integer type;

    /** 相关类id */
    private String clssLinkClassId;

    public String getClssId() {
        return clssId;
    }

    public void setClssId(String clssId) {
        this.clssId = clssId == null ? null : clssId.trim();
    }

    public String getClssClassId() {
        return clssClassId;
    }

    public void setClssClassId(String clssClassId) {
        this.clssClassId = clssClassId == null ? null : clssClassId.trim();
    }

    public String getClssFormulaId() {
        return clssFormulaId;
    }

    public void setClssFormulaId(String clssFormulaId) {
        this.clssFormulaId = clssFormulaId == null ? null : clssFormulaId.trim();
    }

    public Integer getClssSeq() {
        return clssSeq;
    }

    public void setClssSeq(Integer clssSeq) {
        this.clssSeq = clssSeq;
    }

    public String getClssClassRelatedName() {
        return clssClassRelatedName;
    }

    public void setClssClassRelatedName(String clssClassRelatedName) {
        this.clssClassRelatedName = clssClassRelatedName == null ? null : clssClassRelatedName.trim();
    }

    public String getClssAliasName() {
        return clssAliasName;
    }

    public void setClssAliasName(String clssAliasName) {
        this.clssAliasName = clssAliasName == null ? null : clssAliasName.trim();
    }

    public String getClssEdmcNameEn() {
        return clssEdmcNameEn;
    }

    public void setClssEdmcNameEn(String clssEdmcNameEn) {
        this.clssEdmcNameEn = clssEdmcNameEn == null ? null : clssEdmcNameEn.trim();
    }

    public String getClssConditionName() {
        return clssConditionName;
    }

    public void setClssConditionName(String clssConditionName) {
        this.clssConditionName = clssConditionName == null ? null : clssConditionName.trim();
    }

    public String getClssConditionFormula() {
        return clssConditionFormula;
    }

    public void setClssConditionFormula(String clssConditionFormula) {
        this.clssConditionFormula = clssConditionFormula == null ? null : clssConditionFormula.trim();
    }

    public String getClssConditionDesc() {
        return clssConditionDesc;
    }

    public void setClssConditionDesc(String clssConditionDesc) {
        this.clssConditionDesc = clssConditionDesc == null ? null : clssConditionDesc.trim();
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getClssLinkClassId() {
        return clssLinkClassId;
    }

    public void setClssLinkClassId(String clssLinkClassId) {
        this.clssLinkClassId = clssLinkClassId == null ? null : clssLinkClassId.trim();
    }
}