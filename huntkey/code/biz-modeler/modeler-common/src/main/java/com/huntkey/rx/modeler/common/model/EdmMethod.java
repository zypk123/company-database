package com.huntkey.rx.modeler.common.model;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class EdmMethod {
    private String id;// 方法ID

    private String edmmEdmcId;//所属类ID

    private String edmmType;// 方法分类

    private Byte isCover;// 是否可覆盖

    @NotNull(message = "方法名不能为空")
    private String edmmName;// 方法名称

    private String edmmDesc;// 方法描述

    @NotNull(message = "程序类型不能为空")
    private String edmmProgramType;// 程序类型 1.java程序 2.SQL

    private String edmmProgramStorage;// 存储位置

    private String edmmProgramSourceName;// 方法体的原名称

    private String edmmTriggerCond;// 触发条件

    private String edmmVer;// 版本号

    private String edmmDevelopDept;// 研发部门

    private String edmmProgrammer;// 程序员

    private Integer edmmSeq;// 排序

    private String edmmStatus;// 方法状态：0、禁用 1、启用

    private String edmmUpdateDesc;// 方法更新说明

    private Byte isDefined; // 是否用户自定义 0.否 1.是，非自定义用户不可编辑

    private Byte isDel;// 是否被删除 0.否 1.是

    private Date addtime;// 添加时间

    private String adduser;// 添加用户

    private Date modtime; // 修改时间

    private String moduser; // 修改用户

    private Byte acctid;

    private String edmmArithmeticDesc; // 算法描述

    private String edmmArithmeticStorage; // 算法存储位置

    private String edmmArithmeticSourceName; // 算法名称

    private String edmmRequestType; // 请求方式  0.GET 1.POST 2.PUT 3.PATCH 4.DELETE

    private String edmmExecuteType; // 方法类型 0.同步 1.异步 2.自动 3.定时

    private String edmmExecRate; //执行频率 0.一次 1.反复

    private String edmmPlanId;//执行任务id

    private String edmmJobId;

    private String edmmMethodType; //方法类型 0一般方法 1关联方法 2卷积方法

    private Integer timeout;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getEdmmEdmcId() {
        return edmmEdmcId;
    }

    public void setEdmmEdmcId(String edmmEdmcId) {
        this.edmmEdmcId = edmmEdmcId == null ? null : edmmEdmcId.trim();
    }

    public String getEdmmType() {
        return edmmType;
    }

    public void setEdmmType(String edmmType) {
        this.edmmType = edmmType == null ? null : edmmType.trim();
    }

    public Byte getIsCover() {
        return isCover;
    }

    public void setIsCover(Byte isCover) {
        this.isCover = isCover;
    }

    public String getEdmmName() {
        return edmmName;
    }

    public void setEdmmName(String edmmName) {
        this.edmmName = edmmName == null ? null : edmmName.trim();
    }

    public String getEdmmDesc() {
        return edmmDesc;
    }

    public void setEdmmDesc(String edmmDesc) {
        this.edmmDesc = edmmDesc == null ? null : edmmDesc.trim();
    }

    public String getEdmmProgramType() {
        return edmmProgramType;
    }

    public void setEdmmProgramType(String edmmProgramType) {
        this.edmmProgramType = edmmProgramType == null ? null : edmmProgramType.trim();
    }

    public String getEdmmProgramStorage() {
        return edmmProgramStorage;
    }

    public void setEdmmProgramStorage(String edmmProgramStorage) {
        this.edmmProgramStorage = edmmProgramStorage == null ? null : edmmProgramStorage.trim();
    }

    public String getEdmmProgramSourceName() {
        return edmmProgramSourceName;
    }

    public void setEdmmProgramSourceName(String edmmProgramSourceName) {
        this.edmmProgramSourceName = edmmProgramSourceName == null ? null : edmmProgramSourceName.trim();
    }

    public String getEdmmTriggerCond() {
        return edmmTriggerCond;
    }

    public void setEdmmTriggerCond(String edmmTriggerCond) {
        this.edmmTriggerCond = edmmTriggerCond == null ? null : edmmTriggerCond.trim();
    }

    public String getEdmmVer() {
        return edmmVer;
    }

    public void setEdmmVer(String edmmVer) {
        this.edmmVer = edmmVer == null ? null : edmmVer.trim();
    }

    public String getEdmmDevelopDept() {
        return edmmDevelopDept;
    }

    public void setEdmmDevelopDept(String edmmDevelopDept) {
        this.edmmDevelopDept = edmmDevelopDept == null ? null : edmmDevelopDept.trim();
    }

    public String getEdmmProgrammer() {
        return edmmProgrammer;
    }

    public void setEdmmProgrammer(String edmmProgrammer) {
        this.edmmProgrammer = edmmProgrammer == null ? null : edmmProgrammer.trim();
    }

    public Integer getEdmmSeq() {
        return edmmSeq;
    }

    public void setEdmmSeq(Integer edmmSeq) {
        this.edmmSeq = edmmSeq;
    }

    public String getEdmmStatus() {
        return edmmStatus;
    }

    public void setEdmmStatus(String edmmStatus) {
        this.edmmStatus = edmmStatus == null ? null : edmmStatus.trim();
    }

    public String getEdmmUpdateDesc() {
        return edmmUpdateDesc;
    }

    public void setEdmmUpdateDesc(String edmmUpdateDesc) {
        this.edmmUpdateDesc = edmmUpdateDesc == null ? null : edmmUpdateDesc.trim();
    }

    public Byte getIsDefined() {
        return isDefined;
    }

    public void setIsDefined(Byte isDefined) {
        this.isDefined = isDefined;
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

    public String getEdmmArithmeticDesc() {
        return edmmArithmeticDesc;
    }

    public void setEdmmArithmeticDesc(String edmmArithmeticDesc) {
        this.edmmArithmeticDesc = edmmArithmeticDesc == null ? null : edmmArithmeticDesc.trim();
    }

    public String getEdmmArithmeticStorage() {
        return edmmArithmeticStorage;
    }

    public void setEdmmArithmeticStorage(String edmmArithmeticStorage) {
        this.edmmArithmeticStorage = edmmArithmeticStorage == null ? null : edmmArithmeticStorage.trim();
    }

    public String getEdmmArithmeticSourceName() {
        return edmmArithmeticSourceName;
    }

    public void setEdmmArithmeticSourceName(String edmmArithmeticSourceName) {
        this.edmmArithmeticSourceName = edmmArithmeticSourceName == null ? null : edmmArithmeticSourceName.trim();
    }

    public String getEdmmRequestType() {
        return edmmRequestType;
    }

    public void setEdmmRequestType(String edmmRequestType) {
        this.edmmRequestType = edmmRequestType == null ? null : edmmRequestType.trim();
    }

    public String getEdmmMethodType() {
        return edmmMethodType;
    }

    public void setEdmmMethodType(String edmmMethodType) {
        this.edmmMethodType = edmmMethodType == null ? null : edmmMethodType.trim();
    }

    public String getEdmmExecRate() {
        return edmmExecRate;
    }

    public void setEdmmExecRate(String edmmExecRate) {
        this.edmmExecRate = edmmExecRate == null ? null : edmmExecRate.trim();
    }

    public String getEdmmPlanId() {
        return edmmPlanId;
    }

    public void setEdmmPlanId(String edmmPlanId) {
        this.edmmPlanId = edmmPlanId == null ? null : edmmPlanId.trim();
    }

    public String getEdmmJobId() {
        return edmmJobId;
    }

    public void setEdmmJobId(String edmmJobId) {
        this.edmmJobId = edmmJobId == null ? null : edmmJobId.trim();
    }

    public String getEdmmExecuteType() {
        return edmmExecuteType;
    }

    public void setEdmmExecuteType(String edmmExecuteType) {
        this.edmmExecuteType = edmmExecuteType == null ? null : edmmExecuteType.trim();
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    @Override
    public String toString() {
        return "EdmMethod{" +
                "id='" + id + '\'' +
                ", edmmEdmcId='" + edmmEdmcId + '\'' +
                ", edmmType='" + edmmType + '\'' +
                ", isCover=" + isCover +
                ", edmmName='" + edmmName + '\'' +
                ", edmmDesc='" + edmmDesc + '\'' +
                ", edmmProgramType='" + edmmProgramType + '\'' +
                ", edmmProgramStorage='" + edmmProgramStorage + '\'' +
                ", edmmProgramSourceName='" + edmmProgramSourceName + '\'' +
                ", edmmTriggerCond='" + edmmTriggerCond + '\'' +
                ", edmmVer='" + edmmVer + '\'' +
                ", edmmDevelopDept='" + edmmDevelopDept + '\'' +
                ", edmmProgrammer='" + edmmProgrammer + '\'' +
                ", edmmSeq=" + edmmSeq +
                ", edmmStatus='" + edmmStatus + '\'' +
                ", edmmUpdateDesc='" + edmmUpdateDesc + '\'' +
                ", isDefined=" + isDefined +
                ", isDel=" + isDel +
                ", addtime=" + addtime +
                ", adduser='" + adduser + '\'' +
                ", modtime=" + modtime +
                ", moduser='" + moduser + '\'' +
                ", acctid=" + acctid +
                ", edmmArithmeticDesc='" + edmmArithmeticDesc + '\'' +
                ", edmmArithmeticStorage='" + edmmArithmeticStorage + '\'' +
                ", edmmArithmeticSourceName='" + edmmArithmeticSourceName + '\'' +
                ", edmmRequestType='" + edmmRequestType + '\'' +
                ", edmmExecuteType='" + edmmExecuteType + '\'' +
                ", edmmExecRate='" + edmmExecRate + '\'' +
                ", edmmPlanId='" + edmmPlanId + '\'' +
                ", edmmJobId='" + edmmJobId + '\'' +
                ", edmmMethodType='" + edmmMethodType + '\'' +
                ", timeout='" + timeout + '\'' +
                '}';
    }
}