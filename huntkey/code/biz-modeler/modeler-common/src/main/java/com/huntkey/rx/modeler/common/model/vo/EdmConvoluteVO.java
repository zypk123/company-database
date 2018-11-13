package com.huntkey.rx.modeler.common.model.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EdmConvoluteVO {

	private String id;// 主键
	private String edcoEdmpId;// 属性id
	private String edcoConvoluteFormula;// 卷积公式
	private String edcoFormulaDesc;// 卷积描述
	private Byte isDel;// 是否被删除
	private Date addtime;// 添加时间
	private String adduser;// 添加用户
	private Date modtime;// 修改时间
	private String moduser;// 修改用户
	private Byte acctid;// 账户标识

	// --------------------------------------------------------------------

	private String mClassId;// 类id

	private String edcoUpdateTypeName;

	private String edmcId; // 所属类ID

	private String edmcName;// 所属类名

	private String edmpName;// 属性名称

	private String edmdCode;// 模型名;

	private Byte edmdStatus;// 状态描述;

	private String edmdStatusDesc;// 状态描述;

	private String edmdVer;// 模型版本;

	private String edmdId; // 模型ID

	private String edcoConvoluteFormulaName; // 卷积公式名称

	private String addtimeStr; // 添加时间字符串格式

	private String modtimeStr; // 修改时间字符串格式

	private String mClassName; // 类名

	private String mPropertyName;// 属性名

	private String methodDesc; //方法描述

	public String getClassId() {
		return mClassId;
	}

	public String setClassId(String strClassId) {
		return this.mClassId = strClassId == null ? null : strClassId.trim();
	}

	public String getClassName() {
		return mClassName;
	}

	public String setClassName(String strClassName) {
		return this.mClassName = strClassName == null ? null : strClassName.trim();
	}

	public String getPropertyName() {
		return mPropertyName;
	}

	public String setPropertyName(String strPropertyName) {
		return this.mPropertyName = strPropertyName == null ? null : strPropertyName.trim();
	}
	// ------------------------------------------------------------------------

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getEdcoEdmpId() {
		return edcoEdmpId;
	}

	public void setEdcoEdmpId(String edcoEdmpId) {
		this.edcoEdmpId = edcoEdmpId == null ? null : edcoEdmpId.trim();
	}

	public String getEdcoConvoluteFormula() {
		return edcoConvoluteFormula;
	}

	public void setEdcoConvoluteFormula(String edcoConvoluteFormula) {
		this.edcoConvoluteFormula = edcoConvoluteFormula == null ? null : edcoConvoluteFormula.trim();
	}

	public String getEdcoFormulaDesc() {
		return edcoFormulaDesc;
	}

	public void setEdcoFormulaDesc(String edcoFormulaDesc) {
		this.edcoFormulaDesc = edcoFormulaDesc == null ? null : edcoFormulaDesc.trim();
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

	public String getAdduser() {
		return adduser;
	}

	public void setAdduser(String adduser) {
		this.adduser = adduser == null ? null : adduser.trim();
	}

	public Date getModtime() {
		if(this.modtime != null){
			return new Date(this.modtime.getTime());
		}else{
			return null;
		}
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

	public String getEdcoUpdateTypeName() {
		return edcoUpdateTypeName;
	}

	public void setEdcoUpdateTypeName(String edcoUpdateTypeName) {
		this.edcoUpdateTypeName = edcoUpdateTypeName;
	}

	public String getEdmcName() {
		return edmcName;
	}

	public void setEdmcName(String edmcName) {
		this.edmcName = edmcName;
	}

	public String getEdmcId() {
		return edmcId;
	}

	public void setEdmcId(String edmcId) {
		this.edmcId = edmcId;
	}

	public String getEdmpName() {
		return edmpName;
	}

	public void setEdmpName(String edmpName) {
		this.edmpName = edmpName;
	}

	public String getEdmdCode() {
		return edmdCode;
	}

	public void setEdmdCode(String edmdCode) {
		this.edmdCode = edmdCode;
	}

	public Byte getEdmdStatus() {
		return edmdStatus;
	}

	public String getEdmdStatusDesc() {
		return edmdStatusDesc;
	}

	public void setEdmdStatus(Byte edmdStatus) {
		this.edmdStatus = edmdStatus;
	}

	public void setEdmdStatusDesc(String edmdStatusDesc) {
		this.edmdStatusDesc = edmdStatusDesc;
	}

	public String getEdmdVer() {
		return edmdVer;
	}

	public void setEdmdVer(String edmdVer) {
		this.edmdVer = edmdVer;
	}

	public String getEdmdId() {
		return edmdId;
	}

	public void setEdmdId(String edmdId) {
		this.edmdId = edmdId;
	}

	public String getEdcoConvoluteFormulaName() {
		return edcoConvoluteFormulaName;
	}

	public void setEdcoConvoluteFormulaName(String edcoConvoluteFormulaName) {
		this.edcoConvoluteFormulaName = edcoConvoluteFormulaName;
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

	public void setAddtime(Date addtime) {
		if(addtime != null){
			this.addtime = (Date)addtime.clone();
		}else{
			this.addtime = null;
		}
	}

	public void setModtime(Date modtime) {
		if(modtime != null){
			this.modtime = (Date)modtime.clone();
		}else{
			this.modtime = null;
		}
	}

	public String getMethodDesc() {
		return methodDesc;
	}

	public void setMethodDesc(String methodDesc) {
		this.methodDesc = methodDesc;
	}

	@Override
	public String toString() {
		return "EdmConvoluteVO{" +
				"id='" + id + '\'' +
				", edcoEdmpId='" + edcoEdmpId + '\'' +
				", edcoConvoluteFormula='" + edcoConvoluteFormula + '\'' +
				", edcoFormulaDesc='" + edcoFormulaDesc + '\'' +
				", isDel=" + isDel +
				", addtime=" + addtime +
				", adduser='" + adduser + '\'' +
				", modtime=" + modtime +
				", moduser='" + moduser + '\'' +
				", acctid=" + acctid +
				", mClassId='" + mClassId + '\'' +
				", edcoUpdateTypeName='" + edcoUpdateTypeName + '\'' +
				", edmcId='" + edmcId + '\'' +
				", edmcName='" + edmcName + '\'' +
				", edmpName='" + edmpName + '\'' +
				", edmdCode='" + edmdCode + '\'' +
				", edmdStatus=" + edmdStatus +
				", edmdStatusDesc='" + edmdStatusDesc + '\'' +
				", edmdVer='" + edmdVer + '\'' +
				", edmdId='" + edmdId + '\'' +
				", edcoConvoluteFormulaName='" + edcoConvoluteFormulaName + '\'' +
				", addtimeStr='" + addtimeStr + '\'' +
				", modtimeStr='" + modtimeStr + '\'' +
				", mClassName='" + mClassName + '\'' +
				", mPropertyName='" + mPropertyName + '\'' +
				'}';
	}
}