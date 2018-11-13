package com.huntkey.rx.sceo.formula.common.model;

import com.huntkey.rx.sceo.formula.common.base.BaseEntity;

import java.util.Date;

/**
 * @author chenfei on 2017/5/15.
 */
public class TacPropertyRelated extends BaseEntity {
    /** 编号 */
    private String prplId;

    /** 关联开始属性 */
    private String prplClassRelatedFrom;

    /** 关联结束属性 */
    private String prplClassRelatedTo;

    /** 类1英文名称 */
    private String prplClass1NameEn;

    /** 属性1的code */
    private String prplProp1Code;

    /** 类2英文名称 */
    private String prplClass2NameEn;

    /** 属性2的code */
    private String prplProp2Code;

    /** 条件名称 */
    private String prplConditionName;

    /** 条件公式 */
    private String prplConditionFormula;

    /** 条件描述 */
    private String prplConditionDesc;

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

    public String getPrplId() {
        return prplId;
    }

    public void setPrplId(String prplId) {
        this.prplId = prplId == null ? null : prplId.trim();
    }

    public String getPrplClassRelatedFrom() {
        return prplClassRelatedFrom;
    }

    public void setPrplClassRelatedFrom(String prplClassRelatedFrom) {
        this.prplClassRelatedFrom = prplClassRelatedFrom == null ? null : prplClassRelatedFrom.trim();
    }

    public String getPrplClassRelatedTo() {
        return prplClassRelatedTo;
    }

    public void setPrplClassRelatedTo(String prplClassRelatedTo) {
        this.prplClassRelatedTo = prplClassRelatedTo == null ? null : prplClassRelatedTo.trim();
    }

    public String getPrplClass1NameEn() {
        return prplClass1NameEn;
    }

    public void setPrplClass1NameEn(String prplClass1NameEn) {
        this.prplClass1NameEn = prplClass1NameEn == null ? null : prplClass1NameEn.trim();
    }

    public String getPrplProp1Code() {
        return prplProp1Code;
    }

    public void setPrplProp1Code(String prplProp1Code) {
        this.prplProp1Code = prplProp1Code == null ? null : prplProp1Code.trim();
    }

    public String getPrplClass2NameEn() {
        return prplClass2NameEn;
    }

    public void setPrplClass2NameEn(String prplClass2NameEn) {
        this.prplClass2NameEn = prplClass2NameEn == null ? null : prplClass2NameEn.trim();
    }

    public String getPrplProp2Code() {
        return prplProp2Code;
    }

    public void setPrplProp2Code(String prplProp2Code) {
        this.prplProp2Code = prplProp2Code == null ? null : prplProp2Code.trim();
    }

    public String getPrplConditionName() {
        return prplConditionName;
    }

    public void setPrplConditionName(String prplConditionName) {
        this.prplConditionName = prplConditionName == null ? null : prplConditionName.trim();
    }

    public String getPrplConditionFormula() {
        return prplConditionFormula;
    }

    public void setPrplConditionFormula(String prplConditionFormula) {
        this.prplConditionFormula = prplConditionFormula == null ? null : prplConditionFormula.trim();
    }

    public String getPrplConditionDesc() {
        return prplConditionDesc;
    }

    public void setPrplConditionDesc(String prplConditionDesc) {
        this.prplConditionDesc = prplConditionDesc == null ? null : prplConditionDesc.trim();
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