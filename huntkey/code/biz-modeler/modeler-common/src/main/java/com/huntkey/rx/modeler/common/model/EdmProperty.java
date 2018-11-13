package com.huntkey.rx.modeler.common.model;

import javax.validation.constraints.NotNull;
import java.util.Date;
public class EdmProperty {

    //主键id
    private String id;

    //类id
    private String edmpEdmcId;

    //父属性id
    private String edmpParentId;

    //属性编码
    @NotNull(message = "属性编码不能为空")
    private String edmpCode;

    //属性名称
    @NotNull(message = "属性名不能为空")
    private String edmpName;

    //属性描述
    private String edmpDesc;

    //属性值类型
    private String edmpValueType;

    //数据类型
    private String edmpDataType;

    //属性值长度
    private String edmpValueSize;

    //属性限值
    private String edmpValueLimit;

    //属性公式
    private String edmpFormula;

    //属性值
    private String edmpValue;

    //属性修改方法id
    private String edmpEdmmId;

    //对象类id
    private String edmpObjEdmcId;

    //排序
    private Integer edmpSeq;

    //触发条件
    private String triggerCond;

    //是否自定义
    private Byte isDefined;

    //是否可见
    private Byte isVisible;

    // 是否索引
    private Byte isIndex;

    //是否删除状态位
    private Byte isDel;

    //是否特征值
    private Byte isCharacter;

    //创建时间
    private Date addtime;

    //创建人
    private String adduser;

    //修改时间
    private Date modtime;

    //修改人
    private String moduser;

    //账户标识
    private Byte acctid;

    //表名
    private String tablename;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEdmpEdmcId() {
        return edmpEdmcId;
    }

    public void setEdmpEdmcId(String edmpEdmcId) {
        this.edmpEdmcId = edmpEdmcId;
    }

    public String getEdmpParentId() {
        return edmpParentId;
    }

    public void setEdmpParentId(String edmpParentId) {
        this.edmpParentId = edmpParentId;
    }

    public String getEdmpCode() {
        return edmpCode;
    }

    public void setEdmpCode(String edmpCode) {
        this.edmpCode = edmpCode;
    }

    public String getEdmpName() {
        return edmpName;
    }

    public void setEdmpName(String edmpName) {
        this.edmpName = edmpName;
    }

    public String getEdmpDesc() {
        return edmpDesc;
    }

    public void setEdmpDesc(String edmpDesc) {
        this.edmpDesc = edmpDesc;
    }

    public String getEdmpValueType() {
        return edmpValueType;
    }

    public void setEdmpValueType(String edmpValueType) {
        this.edmpValueType = edmpValueType;
    }

    public String getEdmpDataType() {
        return edmpDataType;
    }

    public void setEdmpDataType(String edmpDataType) {
        this.edmpDataType = edmpDataType;
    }

    public String getEdmpValueSize() {
        return edmpValueSize;
    }

    public void setEdmpValueSize(String edmpValueSize) {
        this.edmpValueSize = edmpValueSize;
    }

    public String getEdmpValueLimit() {
        return edmpValueLimit;
    }

    public void setEdmpValueLimit(String edmpValueLimit) {
        this.edmpValueLimit = edmpValueLimit;
    }

    public String getEdmpFormula() {
        return edmpFormula;
    }

    public void setEdmpFormula(String edmpFormula) {
        this.edmpFormula = edmpFormula;
    }

    public String getEdmpValue() {
        return edmpValue;
    }

    public void setEdmpValue(String edmpValue) {
        this.edmpValue = edmpValue;
    }

    public String getEdmpEdmmId() {
        return edmpEdmmId;
    }

    public void setEdmpEdmmId(String edmpEdmmId) {
        this.edmpEdmmId = edmpEdmmId;
    }

    public String getEdmpObjEdmcId() {
        return edmpObjEdmcId;
    }

    public void setEdmpObjEdmcId(String edmpObjEdmcId) {
        this.edmpObjEdmcId = edmpObjEdmcId;
    }

    public Integer getEdmpSeq() {
        return edmpSeq;
    }

    public void setEdmpSeq(Integer edmpSeq) {
        this.edmpSeq = edmpSeq;
    }

    public String getTriggerCond() {
        return triggerCond;
    }

    public void setTriggerCond(String triggerCond) {
        this.triggerCond = triggerCond;
    }

    public Byte getIsDefined() {
        return isDefined;
    }

    public void setIsDefined(Byte isDefined) {
        this.isDefined = isDefined;
    }

    public Byte getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(Byte isVisible) {
        this.isVisible = isVisible;
    }

    public Byte getIsIndex() {
        return isIndex;
    }

    public void setIsIndex(Byte isIndex) {
        this.isIndex = isIndex;
    }

    public Byte getIsDel() {
        return isDel;
    }

    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }

    public Byte getIsCharacter() {
        return isCharacter;
    }

    public void setIsCharacter(Byte isCharacter) {
        this.isCharacter = isCharacter;
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

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename == null ? null : tablename.trim();
    }


    @Override
    public String toString() {
        return "${" +
                "id:'" + id + '\'' +
                ", edmpEdmcId:'" + edmpEdmcId + '\'' +
                ", edmpParentId:'" + edmpParentId + '\'' +
                ", edmpCode:'" + edmpCode + '\'' +
                ", edmpName:'" + edmpName + '\'' +
                ", edmpDesc:'" + edmpDesc + '\'' +
                ", edmpValueType:'" + edmpValueType + '\'' +
                ", edmpDataType:'" + edmpDataType + '\'' +
                ", edmpValueSize:'" + edmpValueSize + '\'' +
                ", edmpValueLimit:'" + edmpValueLimit + '\'' +
                ", edmpFormula:'" + edmpFormula + '\'' +
                ", edmpValue:'" + edmpValue + '\'' +
                ", edmpEdmmId:'" + edmpEdmmId + '\'' +
                ", edmpObjEdmcId:'" + edmpObjEdmcId + '\'' +
                ", edmpSeq:" + edmpSeq +
                ", triggerCond:'" + triggerCond + '\'' +
                ", isDefined:" + isDefined +
                ", isVisible:" + isVisible +
                ", isIndex:" + isIndex +
                ", isDel:" + isDel +
                ", isCharacter:" + isCharacter +
                ", addtime:" + addtime +
                ", adduser:'" + adduser + '\'' +
                ", modtime:" + modtime +
                ", moduser:'" + moduser + '\'' +
                ", acctid:" + acctid +
                ", tablename:" + tablename +
                '}';
    }
}