package com.huntkey.rx.sceo.formula.common.model;

import com.huntkey.rx.sceo.formula.common.base.BaseEntity;

import java.util.Date;

/**
 * @author chenfei on 2017/5/15.
 */
public class TplCondition extends BaseEntity {
    /**
     * 编号
     */
    private String cndtId;

    /**
     * 属性或者相关类编号
     */
    private String cndtPropClssId;

    /**
     * 序号
     */
    private Integer cndtSeq;

    /**
     * 属性
     */
    private String cndtProp;

    /**
     * 操作符
     */
    private String cndtOperate;

    /**
     * 值
     */
    private String cndtValue;

    /**
     * 值类型 enum[constant, variant]
     */
    private String cndtValueType;

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

    /**
     * 如果cndt的类型为class, 此列存该class的id
     */
    private String cndtValueClassId;

    /**
     * 属性编码
     */
    private String cndtPropOriginCode;

    /**
     * 值编码
     */
    private String cndtValueOriginCode;

    /**
     * 值编码左边部分
     */
    @Deprecated
    private String cndtValueOriginCodeLeft;

    /**
     * 值编码右边部分
     */
    @Deprecated
    private String cndtValueOriginCodeRight;

    /** 辅助字段 */
    private String preVarchar;

    /**常量类型 */
    private Integer constType;
    /**对象编号*/
    private String cndtObjectNumber;


    public String getCndtId() {
        return cndtId;
    }

    public void setCndtId(String cndtId) {
        this.cndtId = cndtId == null ? null : cndtId.trim();
    }

    public String getCndtPropClssId() {
        return cndtPropClssId;
    }

    public void setCndtPropClssId(String cndtPropClssId) {
        this.cndtPropClssId = cndtPropClssId == null ? null : cndtPropClssId.trim();
    }

    public Integer getCndtSeq() {
        return cndtSeq;
    }

    public void setCndtSeq(Integer cndtSeq) {
        this.cndtSeq = cndtSeq;
    }

    public String getCndtProp() {
        return cndtProp;
    }

    public void setCndtProp(String cndtProp) {
        this.cndtProp = cndtProp == null ? null : cndtProp.trim();
    }

    public String getCndtOperate() {
        return cndtOperate;
    }

    public void setCndtOperate(String cndtOperate) {
        this.cndtOperate = cndtOperate == null ? null : cndtOperate.trim();
    }

    public String getCndtValue() {
        return cndtValue;
    }

    public void setCndtValue(String cndtValue) {
        this.cndtValue = cndtValue == null ? null : cndtValue.trim();
    }

    public String getCndtValueType() {
        return cndtValueType;
    }

    public void setCndtValueType(String cndtValueType) {
        this.cndtValueType = cndtValueType == null ? null : cndtValueType.trim();
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

    public String getCndtValueClassId() {
        return cndtValueClassId;
    }

    public void setCndtValueClassId(String cndtValueClassId) {
        this.cndtValueClassId = cndtValueClassId == null ? null : cndtValueClassId.trim();
    }

    public String getCndtPropOriginCode() {
        return cndtPropOriginCode;
    }

    public void setCndtPropOriginCode(String cndtPropOriginCode) {
        this.cndtPropOriginCode = cndtPropOriginCode == null ? null : cndtPropOriginCode.trim();
    }

    public String getCndtValueOriginCode() {
        return cndtValueOriginCode;
    }

    public void setCndtValueOriginCode(String cndtValueOriginCode) {
        this.cndtValueOriginCode = cndtValueOriginCode == null ? null : cndtValueOriginCode.trim();
    }

    public String getCndtValueOriginCodeLeft() {
        return cndtValueOriginCodeLeft;
    }

    public void setCndtValueOriginCodeLeft(String cndtValueOriginCodeLeft) {
        this.cndtValueOriginCodeLeft = cndtValueOriginCodeLeft;
    }

    public String getCndtValueOriginCodeRight() {
        return cndtValueOriginCodeRight;
    }

    public void setCndtValueOriginCodeRight(String cndtValueOriginCodeRight) {
        this.cndtValueOriginCodeRight = cndtValueOriginCodeRight;
    }

    public String getPreVarchar() {
        return preVarchar;
    }

    public void setPreVarchar(String preVarchar) {
        this.preVarchar = preVarchar == null ? null : preVarchar.trim();
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

    @Override
    public String toString() {
        return "TplCondition{" +
                "cndtId='" + cndtId + '\'' +
                ", cndtPropClssId='" + cndtPropClssId + '\'' +
                ", cndtSeq=" + cndtSeq +
                ", cndtProp='" + cndtProp + '\'' +
                ", cndtOperate='" + cndtOperate + '\'' +
                ", cndtValue='" + cndtValue + '\'' +
                ", cndtValueType='" + cndtValueType + '\'' +
                ", isenable=" + isenable +
                ", addtime=" + addtime +
                ", adduser='" + adduser + '\'' +
                ", modtime=" + modtime +
                ", moduser='" + moduser + '\'' +
                ", acctid=" + acctid +
                ", cndtValueClassId='" + cndtValueClassId + '\'' +
                ", cndtPropOriginCode='" + cndtPropOriginCode + '\'' +
                ", cndtValueOriginCode='" + cndtValueOriginCode + '\'' +
                ", cndtValueOriginCodeLeft='" + cndtValueOriginCodeLeft + '\'' +
                ", cndtValueOriginCodeRight='" + cndtValueOriginCodeRight + '\'' +
                '}';
    }
}