package com.huntkey.rx.modeler.common.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class EdmClass {
    // 类ID
    private String id;

    // 模型id
    private String edmcEdmdId;

    // 类编码
    @NotNull(message = "类编码不能为空")
    private String edmcCode;

    // 类名称
    @NotNull(message = "类名称不能为空")
    private String edmcName;

    // 类英文名称
    @NotNull(message = "类英文名称不能为空")
    private String edmcNameEn;

    //类简称
    @NotNull(message = "类简称不能为空")
    @Length(min = 1,max = 4,message = "类简称长度不能大于4")
    private String edmcShortName;

    // 父类id
    private String edmcParentId;

    // 版本号
    private String edmcVer;

    // 负责人
    private String edmcPrincipal;

    // 描述
    private String edmcUseDesc;

    // 层级
    private String edmcLevel;

    // 行业编码
    private String edmcIndustryCode;

    // 排序字段
    private Integer edmcSeq;

    // 数据库类型
    private String databaseType;

    //标准呈现类id
    private String edmcShowId;

    //标准的呈现
    private String normalPresent;

    //对象所在云
    private String objectOnCloud;

    // 是否自定义
    private Byte isDefined;

    //是否实体类
    private Byte isEntity;

    // 是否删除
    private Byte isDel;

    // 添加时间
    private Date addtime;

    // 添加用户
    private String adduser;

    // 修改时间
    private Date modtime;

    // 修改用户
    private String moduser;

    // 账户标识
    private Byte acctid;

    //表名
    private String tablename;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getEdmcEdmdId() {
        return edmcEdmdId;
    }

    public void setEdmcEdmdId(String edmcEdmdId) {
        this.edmcEdmdId = edmcEdmdId == null ? null : edmcEdmdId.trim();
    }

    public String getEdmcCode() {
        return edmcCode;
    }

    public void setEdmcCode(String edmcCode) {
        this.edmcCode = edmcCode == null ? null : edmcCode.trim();
    }

    public String getEdmcName() {
        return edmcName;
    }

    public void setEdmcName(String edmcName) {
        this.edmcName = edmcName == null ? null : edmcName.trim();
    }

    public String getEdmcNameEn() {
        return edmcNameEn;
    }

    public void setEdmcNameEn(String edmcNameEn) {
        this.edmcNameEn = edmcNameEn == null ? null : edmcNameEn.trim();
    }

    public String getEdmcShortName() {
        return edmcShortName;
    }

    public void setEdmcShortName(String edmcShortName) {
        this.edmcShortName = edmcShortName == null ? null : edmcShortName.trim();
    }

    public String getEdmcParentId() {
        return edmcParentId;
    }

    public void setEdmcParentId(String edmcParentId) {
        this.edmcParentId = edmcParentId == null ? null : edmcParentId.trim();
    }

    public String getEdmcVer() {
        return edmcVer;
    }

    public void setEdmcVer(String edmcVer) {
        this.edmcVer = edmcVer == null ? null : edmcVer.trim();
    }

    public String getEdmcPrincipal() {
        return edmcPrincipal;
    }

    public void setEdmcPrincipal(String edmcPrincipal) {
        this.edmcPrincipal = edmcPrincipal == null ? null : edmcPrincipal.trim();
    }

    public String getEdmcUseDesc() {
        return edmcUseDesc;
    }

    public void setEdmcUseDesc(String edmcUseDesc) {
        this.edmcUseDesc = edmcUseDesc == null ? null : edmcUseDesc.trim();
    }

    public String getEdmcLevel() {
        return edmcLevel;
    }

    public void setEdmcLevel(String edmcLevel) {
        this.edmcLevel = edmcLevel == null ? null : edmcLevel.trim();
    }

    public String getEdmcIndustryCode() {
        return edmcIndustryCode;
    }

    public void setEdmcIndustryCode(String edmcIndustryCode) {
        this.edmcIndustryCode = edmcIndustryCode == null ? null : edmcIndustryCode.trim();
    }

    public String getEdmcShowId() {
        return edmcShowId;
    }

    public void setEdmcShowId(String edmcShowId) {
        this.edmcShowId = edmcShowId == null ? null : edmcShowId.trim();
    }

    public String getNormalPresent() {
        return normalPresent;
    }

    public void setNormalPresent(String normalPresent) {
        this.normalPresent = normalPresent == null ? null : normalPresent.trim();
    }

    public String getObjectOnCloud() {
        return objectOnCloud;
    }

    public void setObjectOnCloud(String objectOnCloud) {
        this.objectOnCloud = objectOnCloud == null ? null : objectOnCloud.trim();
    }

    public Integer getEdmcSeq() {
        return edmcSeq;
    }

    public void setEdmcSeq(Integer edmcSeq) {
        this.edmcSeq = edmcSeq;
    }

    public String getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType == null ? null : databaseType.trim();
    }

    public Byte getIsDefined() {
        return isDefined;
    }

    public void setIsDefined(Byte isDefined) {
        this.isDefined = isDefined;
    }

    public Byte getIsEntity() {
        return isEntity;
    }

    public void setIsEntity(Byte isEntity) {
        this.isEntity = isEntity;
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

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename == null ? null : tablename.trim();
    }


    @Override
    public String toString() {
        return "EdmClass{" +
                "id='" + id + '\'' +
                ", edmcEdmdId='" + edmcEdmdId + '\'' +
                ", edmcCode='" + edmcCode + '\'' +
                ", edmcName='" + edmcName + '\'' +
                ", edmcNameEn='" + edmcNameEn + '\'' +
                ", edmcShortName='" + edmcShortName + '\'' +
                ", edmcParentId='" + edmcParentId + '\'' +
                ", edmcVer='" + edmcVer + '\'' +
                ", edmcPrincipal='" + edmcPrincipal + '\'' +
                ", edmcUseDesc='" + edmcUseDesc + '\'' +
                ", edmcLevel='" + edmcLevel + '\'' +
                ", edmcIndustryCode='" + edmcIndustryCode + '\'' +
                ", edmcSeq=" + edmcSeq +
                ", databaseType='" + databaseType + '\'' +
                ", edmcShowId='" + edmcShowId + '\'' +
                ", normalPresent='" + normalPresent + '\'' +
                ", objectOnCloud='" + objectOnCloud + '\'' +
                ", isDefined=" + isDefined +
                ", isEntity=" + isEntity +
                ", isDel=" + isDel +
                ", addtime=" + addtime +
                ", adduser='" + adduser + '\'' +
                ", modtime=" + modtime +
                ", moduser='" + moduser + '\'' +
                ", acctid=" + acctid +
                ", tablename=" + tablename +
                '}';
    }

    public EdmClass(EdmClass edmClass) {
        this.id = edmClass.getId();
        this.edmcEdmdId = edmClass.getEdmcEdmdId();
        this.edmcCode = edmClass.getEdmcCode();
        this.edmcName = edmClass.getEdmcName();
        this.edmcNameEn = edmClass.getEdmcNameEn();
        this.edmcShortName = edmClass.getEdmcShortName();
        this.edmcParentId = edmClass.getEdmcParentId();
        this.edmcVer = edmClass.getEdmcVer();
        this.edmcPrincipal = edmClass.getEdmcPrincipal();
        this.edmcUseDesc = edmClass.getEdmcUseDesc();
        this.edmcLevel = edmClass.getEdmcLevel();
        this.edmcIndustryCode = edmClass.getEdmcIndustryCode();
        this.edmcSeq = edmClass.getEdmcSeq();
        this.databaseType = edmClass.getDatabaseType();
        this.edmcShowId = edmClass.getEdmcShowId();
        this.normalPresent = edmClass.getNormalPresent();
        this.objectOnCloud = edmClass.getObjectOnCloud();
        this.isDefined = edmClass.getIsDefined();
        this.isEntity = edmClass.getIsEntity();
        this.isDel = edmClass.getIsDel();
        this.addtime = edmClass.getAddtime();
        this.adduser = edmClass.getAdduser();
        this.modtime = edmClass.getModtime();
        this.moduser = edmClass.getModuser();
        this.acctid = edmClass.getAcctid();
        this.tablename = edmClass.getTablename();
    }

    public EdmClass() {
    }
}