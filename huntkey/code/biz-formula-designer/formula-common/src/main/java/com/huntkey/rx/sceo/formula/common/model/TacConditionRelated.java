package com.huntkey.rx.sceo.formula.common.model;

import com.huntkey.rx.sceo.formula.common.base.BaseEntity;

import java.util.Date;

/**
 * @author chenfei on 2017/5/15.
 */
public class TacConditionRelated extends BaseEntity{
    /** 编号 */
    private String cndrId;

    /** 属性关联编号 */
    private String cndrPropRelatedId;

    /** 关联属性 */
    private String cndrPropRelated;

    /** 序号 */
    private Integer cndrSeq;

    /** 属性1 */
    private String cndrProp;

    /** 属性2 */
    private String cndrProp2;

    /** 操作符 */
    private String cndrOperate;

    /** 值 */
    private String cndrValue;

    /** 值类型 enum[class,constant, variant] */
    private String cndrValueType;

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

    /** 属性1编码 */
    private String cndrPropOriginCode;

    /** 属性2编码 */
    private String cndrProp2OriginCode;

    /** 类1英文名称 */
    private String cndrClass1NameEn;

    /** 属性1的code */
    private String cndrProp1Code;

    /** 类2英文名称 */
    private String cndrClass2NameEn;

    /** 属性2的code */
    private String cndrProp2Code;

    /** 流程函数类型 1属性 2 函数 */
    private Integer cndrTypeFlag;

    /** 属性类类型 1 类 2 相关类 */
    private Integer classTypeFlag;

    /** 类或者关联类id */
    private String linkClassOrClassId;

    /** 相关类主键id 便于查询 */
    private String linkClssId;
    /**常量类型 */
    private Integer constType;
    /**对象编号*/
    private String cndtObjectNumber;

    public String getCndrId() {
        return cndrId;
    }

    public void setCndrId(String cndrId) {
        this.cndrId = cndrId == null ? null : cndrId.trim();
    }

    public String getCndrPropRelatedId() {
        return cndrPropRelatedId;
    }

    public void setCndrPropRelatedId(String cndrPropRelatedId) {
        this.cndrPropRelatedId = cndrPropRelatedId == null ? null : cndrPropRelatedId.trim();
    }

    public String getCndrPropRelated() {
        return cndrPropRelated;
    }

    public void setCndrPropRelated(String cndrPropRelated) {
        this.cndrPropRelated = cndrPropRelated == null ? null : cndrPropRelated.trim();
    }

    public Integer getCndrSeq() {
        return cndrSeq;
    }

    public void setCndrSeq(Integer cndrSeq) {
        this.cndrSeq = cndrSeq;
    }

    public String getCndrProp() {
        return cndrProp;
    }

    public void setCndrProp(String cndrProp) {
        this.cndrProp = cndrProp == null ? null : cndrProp.trim();
    }

    public String getCndrProp2() {
        return cndrProp2;
    }

    public void setCndrProp2(String cndrProp2) {
        this.cndrProp2 = cndrProp2 == null ? null : cndrProp2.trim();
    }

    public String getCndrOperate() {
        return cndrOperate;
    }

    public void setCndrOperate(String cndrOperate) {
        this.cndrOperate = cndrOperate == null ? null : cndrOperate.trim();
    }

    public String getCndrValue() {
        return cndrValue;
    }

    public void setCndrValue(String cndrValue) {
        this.cndrValue = cndrValue == null ? null : cndrValue.trim();
    }

    public String getCndrValueType() {
        return cndrValueType;
    }

    public void setCndrValueType(String cndrValueType) {
        this.cndrValueType = cndrValueType == null ? null : cndrValueType.trim();
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

    public String getCndrPropOriginCode() {
        return cndrPropOriginCode;
    }

    public void setCndrPropOriginCode(String cndrPropOriginCode) {
        this.cndrPropOriginCode = cndrPropOriginCode == null ? null : cndrPropOriginCode.trim();
    }

    public String getCndrProp2OriginCode() {
        return cndrProp2OriginCode;
    }

    public void setCndrProp2OriginCode(String cndrProp2OriginCode) {
        this.cndrProp2OriginCode = cndrProp2OriginCode == null ? null : cndrProp2OriginCode.trim();
    }

    public String getCndrClass1NameEn() {
        return cndrClass1NameEn;
    }

    public void setCndrClass1NameEn(String cndrClass1NameEn) {
        this.cndrClass1NameEn = cndrClass1NameEn == null ? null : cndrClass1NameEn.trim();
    }

    public String getCndrProp1Code() {
        return cndrProp1Code;
    }

    public void setCndrProp1Code(String cndrProp1Code) {
        this.cndrProp1Code = cndrProp1Code == null ? null : cndrProp1Code.trim();
    }

    public String getCndrClass2NameEn() {
        return cndrClass2NameEn;
    }

    public void setCndrClass2NameEn(String cndrClass2NameEn) {
        this.cndrClass2NameEn = cndrClass2NameEn == null ? null : cndrClass2NameEn.trim();
    }

    public String getCndrProp2Code() {
        return cndrProp2Code;
    }

    public void setCndrProp2Code(String cndrProp2Code) {
        this.cndrProp2Code = cndrProp2Code == null ? null : cndrProp2Code.trim();
    }

    public Integer getCndrTypeFlag() {
        return cndrTypeFlag;
    }

    public void setCndrTypeFlag(Integer cndrTypeFlag) {
        this.cndrTypeFlag = cndrTypeFlag;
    }

    public Integer getClassTypeFlag() {
        return classTypeFlag;
    }

    public void setClassTypeFlag(Integer classTypeFlag) {
        this.classTypeFlag = classTypeFlag;
    }

    public String getLinkClassOrClassId() {
        return linkClassOrClassId;
    }

    public void setLinkClassOrClassId(String linkClassOrClassId) {
        this.linkClassOrClassId = linkClassOrClassId == null ? null : linkClassOrClassId.trim();
    }

    public String getLinkClssId() {
        return linkClssId;
    }

    public void setLinkClssId(String linkClssId) {
        this.linkClssId = linkClssId == null ? null : linkClssId.trim();
    }

    public Integer getConstType() {
        return constType;
    }

    public void setConstType(Integer constType) {
        this.constType = constType;
    }

    public String getCndtObjectNumber() {
        return cndtObjectNumber;
    }

    public void setCndtObjectNumber(String cndtObjectNumber) {
        this.cndtObjectNumber = cndtObjectNumber;
    }
}