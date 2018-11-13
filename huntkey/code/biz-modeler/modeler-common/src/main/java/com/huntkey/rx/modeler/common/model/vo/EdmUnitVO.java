package com.huntkey.rx.modeler.common.model.vo;

import java.util.Date;

public class EdmUnitVO {
	private String id;// 主键id
	private String edunEdmpId;// 单位属性id
	private String edunQtyEdmpId;	// 数值属性id
	private Byte isDel;// 是否删除状态位
	private Date addtime;// 创建时间
	private String adduser;// 创建人
	private Date modtime;// 修改时间
	private String moduser;// 修改人
	private Byte acctid;// 账户标识
	// --------------------------------------------------------------------
	private String modtimeStr; // 修改时间str
	private String addtimeStr;// 创建时间str
	private String mClassId;// 类id

	public String getClassId() {
		return mClassId;
	}

	public String setClassId(String strClassId) {
		return this.mClassId = strClassId == null ? null : strClassId.trim();
	}

	private String mClassName; // 类名

	public String getClassName() {
		return mClassName;
	}

	public String setClassName(String strClassName) {
		return this.mClassName = strClassName == null ? null : strClassName.trim();
	}

	private String mPropertyName;// 属性名

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

	public String getEdunEdmpId() {
		return edunEdmpId;
	}

	public void setEdunEdmpId(String edunEdmpId) {
		this.edunEdmpId = edunEdmpId == null ? null : edunEdmpId.trim();
	}

	public String getEdunQtyEdmpId() {
		return edunQtyEdmpId;
	}

	public void setEdunQtyEdmpId(String edunQtyEdmpId) {
		this.edunQtyEdmpId = edunQtyEdmpId == null ? null : edunQtyEdmpId.trim();
	}

	public Byte getIsDel() {
		return isDel;
	}

	public void setIsDel(Byte isDel) {
		this.isDel = isDel;
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
	public Date getAddtime() {
		if(this.addtime != null){
			return new Date(this.addtime.getTime());
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

	// 所属类名
	private String edmcName;
	// 属性名称
	private String edmpName;
	// 对应数值类名
	private String edmcQtyName;
	// 对应数值属性
	private String edmpQtyName;

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

	public String getEdmcQtyName() {
		return edmcQtyName;
	}

	public void setEdmcQtyName(String edmcQtyName) {
		this.edmcQtyName = edmcQtyName;
	}

	public String getEdmpQtyName() {
		return edmpQtyName;
	}

	public void setEdmpQtyName(String edmpQtyName) {
		this.edmpQtyName = edmpQtyName;
	}

	public String getModtimeStr() {
		return modtimeStr;
	}

	public void setModtimeStr(String modtimeStr) {
		this.modtimeStr = modtimeStr;
	}

	public String getAddtimeStr() {
		return addtimeStr;
	}

	public void setAddtimeStr(String addtimeStr) {
		this.addtimeStr = addtimeStr;
	}

	@Override
	public String toString() {
		return "${" +
				"id='" + id + '\'' +
				", edunEdmpId='" + edunEdmpId + '\'' +
				", edunQtyEdmpId='" + edunQtyEdmpId + '\'' +
				", isDel=" + isDel +
				", addtime=" + addtime +
				", adduser='" + adduser + '\'' +
				", modtime=" + modtime +
				", moduser='" + moduser + '\'' +
				", acctid=" + acctid +
				", modtimeStr='" + modtimeStr + '\'' +
				", addtimeStr='" + addtimeStr + '\'' +
				", mClassId='" + mClassId + '\'' +
				", mClassName='" + mClassName + '\'' +
				", mPropertyName='" + mPropertyName + '\'' +
				", edmcName='" + edmcName + '\'' +
				", edmpName='" + edmpName + '\'' +
				", edmcQtyName='" + edmcQtyName + '\'' +
				", edmpQtyName='" + edmpQtyName + '\'' +
				'}';
	}
}