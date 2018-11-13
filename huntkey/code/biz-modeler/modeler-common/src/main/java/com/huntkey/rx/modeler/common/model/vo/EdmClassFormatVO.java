package com.huntkey.rx.modeler.common.model.vo;

import java.util.Date;

public class EdmClassFormatVO {

	private String id; // 对象呈现格式

	private String edmfEdmcId; // 类ID

	private String edmfEdmpId; // 属性ID

	private String edmfConnector; // 连接符

	private Integer edmfSeq; //排序

	private Byte isDel; // 是否删除

	private Date addtime; // 添加时间

	private String adduser; // 添加人

	private Date modtime; // 修改时间

	private String moduser; // 修改人

	private Byte acctid; // 账户标识

	/******************拓展字段**************************/

	//属性名称
	private String edmpName;

	//维护时间
	private String addTimeStr;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEdmfEdmcId() {
		return edmfEdmcId;
	}

	public void setEdmfEdmcId(String edmfEdmcId) {
		this.edmfEdmcId = edmfEdmcId;
	}

	public String getEdmfEdmpId() {
		return edmfEdmpId;
	}

	public void setEdmfEdmpId(String edmfEdmpId) {
		this.edmfEdmpId = edmfEdmpId;
	}

	public String getEdmfConnector() {
		return edmfConnector;
	}

	public void setEdmfConnector(String edmfConnector) {
		this.edmfConnector = edmfConnector;
	}


	public String getEdmpName() {
		return edmpName;
	}

	public void setEdmpName(String edmpName) {
		this.edmpName = edmpName;
	}

	public String getAddTimeStr() {
		return addTimeStr;
	}

	public void setAddTimeStr(String addTimeStr) {
		this.addTimeStr = addTimeStr;
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

	public Integer getEdmfSeq() {
		return edmfSeq;
	}

	public void setEdmfSeq(Integer edmfSeq) {
		this.edmfSeq = edmfSeq;
	}

	@Override
	public String toString() {
		return "${" +
				"id:'" + id + '\'' +
				", edmfEdmcId:'" + edmfEdmcId + '\'' +
				", edmfEdmpId:'" + edmfEdmpId + '\'' +
				", edmfConnector:'" + edmfConnector + '\'' +
				", edmfSeq:" + edmfSeq +
				", isDel:" + isDel +
				", addtime:" + addtime +
				", adduser:'" + adduser + '\'' +
				", modtime:" + modtime +
				", moduser:'" + moduser + '\'' +
				", acctid:" + acctid +
				", edmpName:'" + edmpName + '\'' +
				", addTimeStr:'" + addTimeStr + '\'' +
				'}';
	}
}
