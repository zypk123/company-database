package com.huntkey.rx.modeler.common.model.vo;

import java.util.Date;

public class EdmAttachmentVO {
	private String id; // 主键id

	private String edmaEdmcId; // 类id

	private Byte edmaType; // 附件类型

	private String edmaName;// 附件名称

	private String edmaSourceName;//附件原名称

	private String edmaPath;// 附件路径

	private Integer edmaSeq;// 排序字段

	private Byte isDel;// 是否被删除

	private Date addtime;// 添加时间

	private String adduser; // 添加用户

	private Date modtime; // 修改时间

	private String moduser;// 修改用户

	private Byte acctid;// 账户标识
	
/******************拓展字段**************************/

	private String typeValue; // 类型值
	
	private String addtimeStr; // 添加时间字符串格式
	
	private String modtimeStr; // 修改时间字符串格式
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getEdmaEdmcId() {
		return edmaEdmcId;
	}

	public void setEdmaEdmcId(String edmaEdmcId) {
		this.edmaEdmcId = edmaEdmcId == null ? null : edmaEdmcId.trim();
	}

	public Byte getEdmaType() {
		return edmaType;
	}

	public void setEdmaType(Byte edmaType) {
		this.edmaType = edmaType;
	}

	public String getEdmaName() {
		return edmaName;
	}

	public void setEdmaName(String edmaName) {
		this.edmaName = edmaName == null ? null : edmaName.trim();
	}

	public String getEdmaPath() {
		return edmaPath;
	}

	public void setEdmaPath(String edmaPath) {
		this.edmaPath = edmaPath == null ? null : edmaPath.trim();
	}

	public Integer getEdmaSeq() {
		return edmaSeq;
	}

	public void setEdmaSeq(Integer edmaSeq) {
		this.edmaSeq = edmaSeq;
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
		this.adduser = adduser == null ? null : adduser.trim();
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
		this.moduser = moduser == null ? null : moduser.trim();
	}

	public Byte getAcctid() {
		return acctid;
	}

	public void setAcctid(Byte acctid) {
		this.acctid = acctid;
	}

	private String edmcName;// 所属类名

	public String getEdmcName() {
		return edmcName;
	}

	public void setEdmcName(String edmcName) {
		this.edmcName = edmcName;
	}

	public String getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
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

	public String getEdmaSourceName() {
		return edmaSourceName;
	}

	public void setEdmaSourceName(String edmaSourceName) {
		this.edmaSourceName = edmaSourceName;
	}

	@Override
	public String toString() {
		return "${" +
				"id:'" + id + '\'' +
				", edmaEdmcId:'" + edmaEdmcId + '\'' +
				", edmaType:" + edmaType +
				", edmaName:'" + edmaName + '\'' +
				", edmaSourceName:'" + edmaSourceName + '\'' +
				", edmaPath:'" + edmaPath + '\'' +
				", edmaSeq:" + edmaSeq +
				", isDel:" + isDel +
				", addtime:" + addtime +
				", adduser:'" + adduser + '\'' +
				", modtime:" + modtime +
				", moduser:'" + moduser + '\'' +
				", acctid:" + acctid +
				", typeValue:'" + typeValue + '\'' +
				", addtimeStr:'" + addtimeStr + '\'' +
				", modtimeStr:'" + modtimeStr + '\'' +
				", edmcName:'" + edmcName + '\'' +
				'}';
	}
}