package com.huntkey.rx.modeler.common.model.vo;

import java.util.Date;

public class EdmMethodArgVO {

	// 主键id
	private String id;

	// 方法id
	private String edmaEdmmId;

	// 输入/返回
	private Byte edmaType;

	// 参数数据类型
	private String edmaDataType;

	// 参数名称
	private String edmaName;

	// 参数描述
	private String edmaDesc;

	// 排序
	private Integer edmaSeq;

	// 是否删除状态位
	private Byte isDel;

	// 创建时间
	private Date addtime;

	// 创建人
	private String adduser;

	// 修改时间
	private Date modtime;

	// 修改人
	private String moduser;

	// 账号标示
	private Byte acctid;

	/****************** 拓展字段 **************************/

	private String addtimeStr; // 添加时间字符串格式

	private String modtimeStr; // 修改时间字符串格式

	// 是否编辑
	private int isEdit;

	// 数据类型名称
	private String edmaDataTypeName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
		this.edmaName = edmaName;
	}

	public String getEdmaDesc() {
		return edmaDesc;
	}

	public void setEdmaDesc(String edmaDesc) {
		this.edmaDesc = edmaDesc;
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

	public String getModtimeStr() {
		return modtimeStr;
	}

	public void setModtimeStr(String modtimeStr) {
		this.modtimeStr = modtimeStr;
	}

	public String getEdmaDataType() {
		return edmaDataType;
	}

	public void setEdmaDataType(String edmaDataType) {
		this.edmaDataType = edmaDataType;
	}

	public int getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(int isEdit) {
		this.isEdit = isEdit;
	}

	public Integer getEdmaSeq() {
		return edmaSeq;
	}

	public void setEdmaSeq(Integer edmaSeq) {
		this.edmaSeq = edmaSeq;
	}

	public String getEdmaDataTypeName() {
		return edmaDataTypeName;
	}

	public void setEdmaDataTypeName(String edmaDataTypeName) {
		this.edmaDataTypeName = edmaDataTypeName;
	}

	public String getEdmaEdmmId() {
		return edmaEdmmId;
	}

	public void setEdmaEdmmId(String edmaEdmmId) {
		this.edmaEdmmId = edmaEdmmId;
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

	public Byte getAcctid() {
		return acctid;
	}

	public void setAcctid(Byte acctid) {
		this.acctid = acctid;
	}

	public String getAddtimeStr() {
		return addtimeStr;
	}

	public void setAddtimeStr(String addtimeStr) {
		this.addtimeStr = addtimeStr;
	}

	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"edmaEdmmId\":\"" + edmaEdmmId + "\", \"edmaType\":\"" + edmaType
				+ "\", \"edmaDataType\":\"" + edmaDataType + "\", \"edmaName\":\"" + edmaName + "\", \"edmaDesc\":\""
				+ edmaDesc + "\", \"edmaSeq\":\"" + edmaSeq + "\", \"isDel\":\"" + isDel + "\", \"addtime\":\""
				+ addtime + "\", \"adduser\":\"" + adduser + "\", \"modtime\":\"" + modtime + "\", \"moduser\":\""
				+ moduser + "\", \"acctid\":\"" + acctid + "\", \"addtimeStr\":\"" + addtimeStr
				+ "\", \"modtimeStr\":\"" + modtimeStr + "\", \"isEdit\":\"" + isEdit + "\", \"edmaDataTypeName\":\""
				+ edmaDataTypeName + "\"} ";
	}

}
