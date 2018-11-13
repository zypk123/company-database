package com.huntkey.rx.modeler.common.model.vo;

import java.util.Date;
public class EdmMethodVO {

	private String id; // 方法ID

	private String edmmEdmcId;// 所属类ID

	private String edmmType; // 方法分类

	private Byte isCover; // 是否可覆盖

	private String edmmName;// 方法名称

	private String edmmDesc;// 方法描述

	private String edmmProgramType;// 程序类型 1.java程序 2.SQL

	private String edmmProgramStorage;// 存储位置

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

	private Byte acctid;// 账号标示

	private String edmmArithmeticDesc; // 算法描述

	private String edmmArithmeticStorage; // 算法存储位置

	private String edmmArithmeticSourceName; // 算法名称

	private String edmmRequestType; // 请求方式  0.GET 1.POST 2.PUT 3.PATCH 4.DELETE

	private String edmmExecuteType; // 方法类型 0.同步 1.异步 2.自动 3.定时

	private String edmmExecRate; //执行频率 0.一次 1.反复

	private String edmmPlanId;//计划id

	private String edmmJobId;//计划id

	private String edmmMethodType; //方法类型 0一般方法 1关联方法 2卷积方法

	private Integer timeout;

	/********* 拓展字段 **********/

	private String edmmTypeName;// 方法分类名称
	private String edmtName; // 方法类型名
	private String edmProgramTypeName;// 程序类型名
	private String edmEdmdStatusName;// 方法状态名
	private String edmcName;// 所属类名
	private String edmcNameEn;//所属类英文名

	private String edmEdmdInsertArgType;// 方法输入参数类型
	private String edmEdmdInsertArgName;// 方法输入参数名称
	private String edmInsertArgDesc;// 输入参数描述

	private String edmEdmdReturnArgType;// 方法返回值类型
	private String edmEdmdReturnName;// 方法返回值名称
	private String edmReturnDesc;// 返回参数描述

	private String addtimeStr; // 添加时间String格式
	private String modtimeStr; // 修改时间String格式

	private String edmdVersion; // 版本号
	private String edmdId; // 版本ID
	private String edmdStatus; // 版本状态

	private String edmmProgramSourceName;// 方法体的原名称

	private String edmTriggerCondName; // 触发条件名
	private String edmTriggerCondNameDesc; // 触发条件描述

	public String getEdmTriggerCondNameDesc() {
		return edmTriggerCondNameDesc;
	}

	public void setEdmTriggerCondNameDesc(String edmTriggerCondNameDesc) {
		this.edmTriggerCondNameDesc = edmTriggerCondNameDesc;
	}

	public String getEdmTriggerCondName() {
		return edmTriggerCondName;
	}

	public void setEdmTriggerCondName(String edmTriggerCondName) {
		this.edmTriggerCondName = edmTriggerCondName;
	}

	public String getEdmmRequestType() {
		return edmmRequestType;
	}

	public void setEdmmRequestType(String edmmRequestType) {
		this.edmmRequestType = edmmRequestType;
	}

	public String getEdmmMethodType() {
		return edmmMethodType;
	}

	public void setEdmmMethodType(String edmmMethodType) {
		this.edmmMethodType = edmmMethodType;
	}

	public String getEdmmExecRate() {
		return edmmExecRate;
	}

	public void setEdmmExecRate(String edmmExecRate) {
		this.edmmExecRate = edmmExecRate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEdmmType() {
		return edmmType;
	}

	public void setEdmmType(String edmmType) {
		this.edmmType = edmmType;
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
		this.edmmName = edmmName;
	}

	public String getEdmmDesc() {
		return edmmDesc;
	}

	public void setEdmmDesc(String edmmDesc) {
		this.edmmDesc = edmmDesc;
	}

	public String getEdmmProgramType() {
		return edmmProgramType;
	}

	public void setEdmmProgramType(String edmmProgramType) {
		this.edmmProgramType = edmmProgramType;
	}

	public String getEdmmProgramStorage() {
		return edmmProgramStorage;
	}

	public void setEdmmProgramStorage(String edmmProgramStorage) {
		this.edmmProgramStorage = edmmProgramStorage;
	}

	public String getEdmmTriggerCond() {
		return edmmTriggerCond;
	}

	public void setEdmmTriggerCond(String edmmTriggerCond) {
		this.edmmTriggerCond = edmmTriggerCond;
	}

	public String getEdmmVer() {
		return edmmVer;
	}

	public void setEdmmVer(String edmmVer) {
		this.edmmVer = edmmVer;
	}

	public String getEdmmDevelopDept() {
		return edmmDevelopDept;
	}

	public void setEdmmDevelopDept(String edmmDevelopDept) {
		this.edmmDevelopDept = edmmDevelopDept;
	}

	public String getEdmmProgrammer() {
		return edmmProgrammer;
	}

	public void setEdmmProgrammer(String edmmProgrammer) {
		this.edmmProgrammer = edmmProgrammer;
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
		this.edmmStatus = edmmStatus;
	}

	public String getEdmmUpdateDesc() {
		return edmmUpdateDesc;
	}

	public void setEdmmUpdateDesc(String edmmUpdateDesc) {
		this.edmmUpdateDesc = edmmUpdateDesc;
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
		if (null != this.addtime) {
			return new Date(this.addtime.getTime());
		} else {
			return null;
		}
	}

	public void setAddtime(Date addtime) {
		if (null != addtime) {
			this.addtime = (Date) addtime.clone();
		} else {
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
		if (null != this.modtime) {
			return new Date(this.modtime.getTime());
		} else {
			return null;
		}
	}

	public void setModtime(Date modtime) {
		if (null != modtime) {
			this.modtime = (Date) modtime.clone();
		} else {
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

	public String getEdmtName() {
		return edmtName;
	}

	public void setEdmtName(String edmtName) {
		this.edmtName = edmtName;
	}

	public String getEdmProgramTypeName() {
		return edmProgramTypeName;
	}

	public void setEdmProgramTypeName(String edmProgramTypeName) {
		this.edmProgramTypeName = edmProgramTypeName;
	}

	public String getEdmEdmdStatusName() {
		return edmEdmdStatusName;
	}

	public void setEdmEdmdStatusName(String edmEdmdStatusName) {
		this.edmEdmdStatusName = edmEdmdStatusName;
	}

	public String getEdmcName() {
		return edmcName;
	}

	public void setEdmcName(String edmcName) {
		this.edmcName = edmcName;
	}

	public String getEdmEdmdInsertArgName() {
		return edmEdmdInsertArgName;
	}

	public void setEdmEdmdInsertArgName(String edmEdmdInsertArgName) {
		this.edmEdmdInsertArgName = edmEdmdInsertArgName;
	}

	public String getEdmEdmdReturnName() {
		return edmEdmdReturnName;
	}

	public void setEdmEdmdReturnName(String edmEdmdReturnName) {
		this.edmEdmdReturnName = edmEdmdReturnName;
	}

	public String getEdmInsertArgDesc() {
		return edmInsertArgDesc;
	}

	public void setEdmInsertArgDesc(String edmInsertArgDesc) {
		this.edmInsertArgDesc = edmInsertArgDesc;
	}

	public String getEdmReturnDesc() {
		return edmReturnDesc;
	}

	public void setEdmReturnDesc(String edmReturnDesc) {
		this.edmReturnDesc = edmReturnDesc;
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

	public String getEdmEdmdInsertArgType() {
		return edmEdmdInsertArgType;
	}

	public void setEdmEdmdInsertArgType(String edmEdmdInsertArgType) {
		this.edmEdmdInsertArgType = edmEdmdInsertArgType;
	}

	public String getEdmEdmdReturnArgType() {
		return edmEdmdReturnArgType;
	}

	public void setEdmEdmdReturnArgType(String edmEdmdReturnArgType) {
		this.edmEdmdReturnArgType = edmEdmdReturnArgType;
	}

	public String getEdmdVersion() {
		return edmdVersion;
	}

	public void setEdmdVersion(String edmdVersion) {
		this.edmdVersion = edmdVersion;
	}

	public String getEdmdStatus() {
		return edmdStatus;
	}

	public void setEdmdStatus(String edmdStatus) {
		this.edmdStatus = edmdStatus;
	}

	public String getEdmdId() {
		return edmdId;
	}

	public void setEdmdId(String edmdId) {
		this.edmdId = edmdId;
	}

	public String getEdmmTypeName() {
		return edmmTypeName;
	}

	public void setEdmmTypeName(String edmmTypeName) {
		this.edmmTypeName = edmmTypeName;
	}

	public String getEdmmProgramSourceName() {
		return edmmProgramSourceName;
	}

	public void setEdmmProgramSourceName(String edmmProgramSourceName) {
		this.edmmProgramSourceName = edmmProgramSourceName;
	}

	public String getEdmmArithmeticDesc() {
		return edmmArithmeticDesc;
	}

	public void setEdmmArithmeticDesc(String edmmArithmeticDesc) {
		this.edmmArithmeticDesc = edmmArithmeticDesc;
	}

	public String getEdmmArithmeticStorage() {
		return edmmArithmeticStorage;
	}

	public void setEdmmArithmeticStorage(String edmmArithmeticStorage) {
		this.edmmArithmeticStorage = edmmArithmeticStorage;
	}
	public String getEdmmArithmeticSourceName() {
		return edmmArithmeticSourceName;
	}

	public void setEdmmArithmeticSourceName(String edmmArithmeticSourceName) {
		this.edmmArithmeticSourceName = edmmArithmeticSourceName;
	}

	public String getEdmmEdmcId() {
		return edmmEdmcId;
	}

	public void setEdmmEdmcId(String edmmEdmcId) {
		this.edmmEdmcId = edmmEdmcId;
	}

	public String getEdmmPlanId() {
		return edmmPlanId;
	}

	public void setEdmmPlanId(String edmmPlanId) {
		this.edmmPlanId = edmmPlanId;
	}

	public String getEdmmJobId() {
		return edmmJobId;
	}

	public void setEdmmJobId(String edmmJobId) {
		this.edmmJobId = edmmJobId;
	}

	public String getEdmcNameEn() {
		return edmcNameEn;
	}

	public void setEdmcNameEn(String edmcNameEn) {
		this.edmcNameEn = edmcNameEn;
	}

	public String getEdmmExecuteType() {
		return edmmExecuteType;
	}

	public void setEdmmExecuteType(String edmmExecuteType) {
		this.edmmExecuteType = edmmExecuteType;
	}

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	@Override
	public String toString() {
		return "EdmMethodVO{" +
				"id='" + id + '\'' +
				", edmmEdmcId='" + edmmEdmcId + '\'' +
				", edmmType='" + edmmType + '\'' +
				", isCover=" + isCover +
				", edmmName='" + edmmName + '\'' +
				", edmmDesc='" + edmmDesc + '\'' +
				", edmmProgramType='" + edmmProgramType + '\'' +
				", edmmProgramStorage='" + edmmProgramStorage + '\'' +
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
				", edmmTypeName='" + edmmTypeName + '\'' +
				", timeout='" + timeout + '\'' +
				", edmtName='" + edmtName + '\'' +
				", edmProgramTypeName='" + edmProgramTypeName + '\'' +
				", edmEdmdStatusName='" + edmEdmdStatusName + '\'' +
				", edmcName='" + edmcName + '\'' +
				", edmcNameEn='" + edmcNameEn + '\'' +
				", edmEdmdInsertArgType='" + edmEdmdInsertArgType + '\'' +
				", edmEdmdInsertArgName='" + edmEdmdInsertArgName + '\'' +
				", edmInsertArgDesc='" + edmInsertArgDesc + '\'' +
				", edmEdmdReturnArgType='" + edmEdmdReturnArgType + '\'' +
				", edmEdmdReturnName='" + edmEdmdReturnName + '\'' +
				", edmReturnDesc='" + edmReturnDesc + '\'' +
				", addtimeStr='" + addtimeStr + '\'' +
				", modtimeStr='" + modtimeStr + '\'' +
				", edmdVersion='" + edmdVersion + '\'' +
				", edmdId='" + edmdId + '\'' +
				", edmdStatus='" + edmdStatus + '\'' +
				", edmmProgramSourceName='" + edmmProgramSourceName + '\'' +
				", edmTriggerCondName='" + edmTriggerCondName + '\'' +
				", edmTriggerCondNameDesc='" + edmTriggerCondNameDesc + '\'' +
				'}';
	}
}
