package com.huntkey.rx.modeler.common.model.vo;

import com.huntkey.rx.modeler.common.model.EdmCond;
import com.huntkey.rx.modeler.common.model.EdmLink;

import java.util.Date;
import java.util.List;

public class EdmCondVO {
	private String id;

	private String edcoEdmpId;

	private String edcoCond;

	private Byte isDel;

	private Date addtime;

	private String adduser;

	private Date modtime;

	private String moduser;

	private Byte acctid;

	private String addtimeStr; // 添加时间字符串格式

	private String modtimeStr; // 修改时间字符串格式

	//-----------------扩展---------------

	private String condFormula;

	private String condFormulaDesc;

	private List<EdmLinkVO> edmLinks;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEdcoEdmpId() {
		return edcoEdmpId;
	}

	public void setEdcoEdmpId(String edcoEdmpId) {
		this.edcoEdmpId = edcoEdmpId;
	}

	public String getEdcoCond() {
		return edcoCond;
	}

	public void setEdcoCond(String edcoCond) {
		this.edcoCond = edcoCond;
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

	public String getCondFormula() {
		return condFormula;
	}

	public void setCondFormula(String condFormula) {
		this.condFormula = condFormula;
	}

	public String getCondFormulaDesc() {
		return condFormulaDesc;
	}

	public void setCondFormulaDesc(String condFormulaDesc) {
		this.condFormulaDesc = condFormulaDesc;
	}

	@Override
	public String toString() {
		return "EdmCondVO{" +
				"id='" + id + '\'' +
				", edcoEdmpId='" + edcoEdmpId + '\'' +
				", edcoCond='" + edcoCond + '\'' +
				", isDel=" + isDel +
				", addtime=" + addtime +
				", adduser='" + adduser + '\'' +
				", modtime=" + modtime +
				", moduser='" + moduser + '\'' +
				", acctid=" + acctid +
				", addtimeStr='" + addtimeStr + '\'' +
				", modtimeStr='" + modtimeStr + '\'' +
				", condFormula='" + condFormula + '\'' +
				", condFormulaDesc='" + condFormulaDesc + '\'' +
				'}';
	}

	public List<EdmLinkVO> getEdmLinks() {
		return edmLinks;
	}

	public void setEdmLinks(List<EdmLinkVO> edmLinks) {
		this.edmLinks = edmLinks;
	}
}