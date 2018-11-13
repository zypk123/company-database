package com.huntkey.rx.modeler.common.model.vo;

import com.huntkey.rx.modeler.common.model.EdmAttachment;
import com.huntkey.rx.modeler.common.model.EdmProperty;

import java.util.Date;
import java.util.List;

/**
 * Created by liangh on 2017/4/14.
 */
public class EdmClassVO {

	private String id;// 类ID

	private String edmcEdmdId;// 模型id

	private String edmcCode;// 类编码

	private String edmcName;// 类名称

	private String edmcNameEn;// 类英文名称

	private String edmcShortName;//类简称

	private String edmcParentId;// 父类id

	private String edmcVer;// 版本号

	private String edmcPrincipal;// 负责人

	private String edmcUseDesc;// 描述

	private String edmcLevel;// 层级

	private String edmcIndustryCode;// 行业编码

	private Integer edmcSeq;// 排序字段

	private Byte isDefined;// 是否自定义

	private Byte isDel; // 是否删除

	private Date addtime;// 添加时间

	private String adduser;// 添加用户

	private Date modtime;// 修改时间

	private String moduser;// 修改用户

	private Byte acctid;// 账户标识

	private String edmcDesc; // 类描述

	private String databaseType; // 数据库类型

	private Byte isEntity; //是否实体类

	private String edmcShowId;//呈现类id

	private String normalPresent; //标准的呈现

	private String objectOnCloud; //对象所在云

	private String tablename; //表名
	
	/******************拓展字段**************************/
	
	// 父类中文名称
	private String parentName;

	// 父类英文名称
	private String parentEnName;

	// 类的所有附件
	private List<EdmAttachment> attachments;

	// 类的子类
	private List<EdmClassVO> children;

	//类下属性
	private List<EdmProperty> classProperties;

	// 是否叶子节点
	private boolean isLeaf = true;

	// 行业名
	private String industry;

	// 行业名拼接
	private String industryNames;

	// 行业值拼接
	private String industryValues;

	// 对象呈现特征
	private String formatValue;
	
	private String addtimeStr; // 添加时间字符串格式
	
	private String modtimeStr; // 修改时间字符串格式

	private String spell; // 汉语拼音

	//标准呈现对象名称
	private String showName;

	//类下属性VO
	private List<EdmPropertyVO> edmPropertyVOS;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEdmcEdmdId() {
		return edmcEdmdId;
	}

	public void setEdmcEdmdId(String edmcEdmdId) {
		this.edmcEdmdId = edmcEdmdId;
	}

	public void setEdmcParentId(String edmcParentId) {
		this.edmcParentId = edmcParentId;
	}

	public String getEdmcCode() {
		return edmcCode;
	}

	public void setEdmcCode(String edmcCode) {
		this.edmcCode = edmcCode;
	}

	public String getEdmcName() {
		return edmcName;
	}

	public void setEdmcName(String edmcName) {
		this.edmcName = edmcName;
	}

	public String getEdmcNameEn() {
		return edmcNameEn;
	}

	public void setEdmcNameEn(String edmcNameEn) {
		this.edmcNameEn = edmcNameEn;
	}

	public String getEdmcParentId() {
		return edmcParentId;
	}

	public String getEdmcLevel() {
		return edmcLevel;
	}

	public void setEdmcLevel(String edmcLevel) {
		this.edmcLevel = edmcLevel;
	}

	public String getEdmcDesc() {
		return edmcDesc;
	}

	public void setEdmcDesc(String edmcDesc) {
		this.edmcDesc = edmcDesc;
	}

	public Integer getEdmcSeq() {
		return edmcSeq;
	}

	public void setEdmcSeq(Integer edmcSeq) {
		this.edmcSeq = edmcSeq;
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
		if(this.addtime != null){
			return new Date(this.addtime.getTime());
		}else{
			return null;
		}
	}
	public void setAddtime(Date addtime) {
		if(addtime != null){
			this.addtime = (Date)addtime.clone();
		}else{
			this.addtime = null;
		}
	}

	public String getAdduser() {
		return adduser;
	}

	public void setAdduser(String adduser) {
		this.adduser = adduser;
	}

	public Date getModtime() {
		if(this.modtime != null){
			return new Date(this.modtime.getTime());
		}else{
			return null;
		}
	}
	public void setModtime(Date modtime) {
		if(modtime != null){
			this.modtime = (Date)modtime.clone();
		}else{
			this.modtime = null;
		}
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

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getParentEnName() {
		return parentEnName;
	}

	public void setParentEnName(String parentEnName) {
		this.parentEnName = parentEnName;
	}

	public List<EdmAttachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<EdmAttachment> attachments) {
		this.attachments = attachments;
	}

	public String getEdmcVer() {
		return edmcVer;
	}

	public void setEdmcVer(String edmcVer) {
		this.edmcVer = edmcVer;
	}

	public String getEdmcPrincipal() {
		return edmcPrincipal;
	}

	public void setEdmcPrincipal(String edmcPrincipal) {
		this.edmcPrincipal = edmcPrincipal;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public List<EdmClassVO> getChildren() {
		return children;
	}

	public void setChildren(List<EdmClassVO> children) {
		this.children = children;
	}

	public boolean isLeaf() {
		return isLeaf;
	}

	public void setLeaf(boolean leaf) {
		isLeaf = leaf;
	}

	public String getEdmcUseDesc() {
		return edmcUseDesc;
	}

	public void setEdmcUseDesc(String edmcUseDesc) {
		this.edmcUseDesc = edmcUseDesc;
	}

	public String getEdmcIndustryCode() {
		return edmcIndustryCode;
	}

	public void setEdmcIndustryCode(String edmcIndustryCode) {
		this.edmcIndustryCode = edmcIndustryCode;
	}

	public String getIndustryNames() {
		return industryNames;
	}

	public void setIndustryNames(String industryNames) {
		this.industryNames = industryNames;
	}

	public String getIndustryValues() {
		return industryValues;
	}

	public void setIndustryValues(String industryValues) {
		this.industryValues = industryValues;
	}
	
	public String getAddtimeStr() {
		return addtimeStr;
	}

	public void setAddtimeStr(String addtimeStr) {
		this.addtimeStr = addtimeStr;
	}

	public String getModtimeStr() {
		return modtimeStr;
	}

	public void setModtimeStr(String modtimeStr) {
		this.modtimeStr = modtimeStr;
	}

	public String getEdmcShortName() {
		return edmcShortName;
	}

	public void setEdmcShortName(String edmcShortName) {
		this.edmcShortName = edmcShortName;
	}

	public String getDatabaseType() {
		return databaseType;
	}

	public void setDatabaseType(String databaseType) {
		this.databaseType = databaseType;
	}

	public Byte getIsEntity() {
		return isEntity;
	}

	public void setIsEntity(Byte isEntity) {
		this.isEntity = isEntity;
	}

	public String getNormalPresent() {
		return normalPresent;
	}

	public void setNormalPresent(String normalPresent) {
		this.normalPresent = normalPresent;
	}

	public String getObjectOnCloud() {
		return objectOnCloud;
	}

	public void setObjectOnCloud(String objectOnCloud) {
		this.objectOnCloud = objectOnCloud;
	}

	public String getSpell() {
		return spell;
	}

	public void setSpell(String spell) {
		this.spell = spell;
	}

	public List<EdmProperty> getClassProperties() {
		return classProperties;
	}

	public void setClassProperties(List<EdmProperty> classProperties) {
		this.classProperties = classProperties;
	}

	public String getFormatValue() {
		return formatValue;
	}

	public void setFormatValue(String formatValue) {
		this.formatValue = formatValue;
	}

	public String getEdmcShowId() {
		return edmcShowId;
	}

	public void setEdmcShowId(String edmcShowId) {
		this.edmcShowId = edmcShowId;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename == null ? null : tablename.trim();
	}

	public List<EdmPropertyVO> getEdmPropertyVOS() {
		return edmPropertyVOS;
	}

	public void setEdmPropertyVOS(List<EdmPropertyVO> edmPropertyVOS) {
		this.edmPropertyVOS = edmPropertyVOS;
	}

	@Override
	public String toString() {
		return "EdmClassVO{" +
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
				", isDefined=" + isDefined +
				", isDel=" + isDel +
				", addtime=" + addtime +
				", adduser='" + adduser + '\'' +
				", modtime=" + modtime +
				", moduser='" + moduser + '\'' +
				", acctid=" + acctid +
				", edmcDesc='" + edmcDesc + '\'' +
				", databaseType='" + databaseType + '\'' +
				", isEntity=" + isEntity +
				", edmcShowId='" + edmcShowId + '\'' +
				", normalPresent='" + normalPresent + '\'' +
				", objectOnCloud='" + objectOnCloud + '\'' +
				", parentName='" + parentName + '\'' +
				", parentEnName='" + parentEnName + '\'' +
				", attachments=" + attachments +
				", children=" + children +
				", classProperties=" + classProperties +
				", isLeaf=" + isLeaf +
				", industry='" + industry + '\'' +
				", industryNames='" + industryNames + '\'' +
				", industryValues='" + industryValues + '\'' +
				", formatValue='" + formatValue + '\'' +
				", addtimeStr='" + addtimeStr + '\'' +
				", modtimeStr='" + modtimeStr + '\'' +
				", spell='" + spell + '\'' +
				", showName='" + showName + '\'' +
				", tablename=" + tablename +
				'}';
	}
}
