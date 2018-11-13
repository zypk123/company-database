package com.huntkey.rx.modeler.common.model.vo;

import java.util.Date;

public class EdmConnectVO {

	private String id; // 主键

	private String edcnEdmpId;// 联动属性id

	private Byte edcnLinkPreservable;// 是否联动记录

	private String  edcnUpdateType;// 更新类型

	private String edcnUpdateTime;// 更新时间

	private String asyncTypePriority; //异步类型优先级

	private String edcnType;//联动方式

	private Byte isDel;// 是否已经删除

	private Date addtime; // 添加时间

	private String adduser;// 添加用户

	private Date modtime;// 修改时间

	private String moduser; // 修改用户

	private Byte acctid; // 账户标识

	/****************** 拓展字段 **************************/

	private String addtimeStr; // 添加时间字符串格式

	private String modtimeStr; // 修改时间字符串格式

	private String edmcName;// 所属类名

	private String edmpName;// 属性名称

	private String methodDesc; //方法描述

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getEdcnEdmpId() {
		return edcnEdmpId;
	}

	public void setEdcnEdmpId(String edcnEdmpId) {
		this.edcnEdmpId = edcnEdmpId == null ? null : edcnEdmpId.trim();
	}

	public Byte getEdcnLinkPreservable() {
		return edcnLinkPreservable;
	}

	public void setEdcnLinkPreservable(Byte edcnLinkPreservable) {
		this.edcnLinkPreservable = edcnLinkPreservable;
	}

	public String getEdcnUpdateType() {
		return edcnUpdateType;
	}

	public void setEdcnUpdateType(String edcnUpdateType) {
		this.edcnUpdateType = edcnUpdateType;
	}

	public String getEdcnType() {
		return edcnType;
	}

	public void setEdcnType(String edcnType) {
		this.edcnType = edcnType;
	}

	public String getEdcnUpdateTime() {
		return edcnUpdateTime;
	}

	public void setEdcnUpdateTime(String edcnUpdateTime) {
		this.edcnUpdateTime = edcnUpdateTime == null ? null : edcnUpdateTime.trim();
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

	public String getEdmcName() {
		return edmcName;
	}

	public void setEdmcName(String edmcName) {
		this.edmcName = edmcName;
	}

	public String getEdmpName() {
		return edmpName;
	}

	public void setEdmpName(String edmpName) {
		this.edmpName = edmpName;
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

	public String getAsyncTypePriority() {
		return asyncTypePriority;
	}

	public void setAsyncTypePriority(String asyncTypePriority) {
		this.asyncTypePriority = asyncTypePriority;
	}

	public String getMethodDesc() {
		return methodDesc;
	}

	public void setMethodDesc(String methodDesc) {
		this.methodDesc = methodDesc;
	}

	@Override
	public String toString() {
		return "EdmConnectVO{" +
				"id='" + id + '\'' +
				", edcnEdmpId='" + edcnEdmpId + '\'' +
				", edcnLinkPreservable=" + edcnLinkPreservable +
				", edcnUpdateType='" + edcnUpdateType + '\'' +
				", edcnUpdateTime='" + edcnUpdateTime + '\'' +
				", asyncTypePriority='" + asyncTypePriority + '\'' +
				", edcnType='" + edcnType + '\'' +
				", isDel=" + isDel +
				", addtime=" + addtime +
				", adduser='" + adduser + '\'' +
				", modtime=" + modtime +
				", moduser='" + moduser + '\'' +
				", acctid=" + acctid +
				", addtimeStr='" + addtimeStr + '\'' +
				", modtimeStr='" + modtimeStr + '\'' +
				", edmcName='" + edmcName + '\'' +
				", edmpName='" + edmpName + '\'' +
				'}';
	}
}