package com.huntkey.rx.modeler.common.model.vo;

import java.util.Date;
import java.util.List;

public class EdmLinkVO {
	// 主键
	private String id;
	// 关联属性id
	private String edmlEdmpId;
	// 被关联的属性id
	private String edmlEdmpLinkId;
	// 联动的条件
	private String edmlCond;
	// 对象定位公式id
	private String edmlFormula;

	// 是否被删除
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
	// -----------------------------------------------------------------------

	private String addtimeStr; // 添加时间字符串格式

	private String modtimeStr; // 修改时间字符串格式

	private String edmConnectId;// edm_connnect 中的id
	// 是否联动记录(存放connect表的 edcn_link_preservable)
	private Byte edcnLinkPreservable;

	// 更新类型
	private String edclUpdateType;
	// 更新时间
	private String edclUpdateTime;

	private String asyncTypePriority;

	private String edclType;// 联动方式

	private String edmcId; //所属类id
	private String edmcName;// 所属类名
	private String edmpName;// 属性名称

	private String edmlEdmcIdLink;//关联类ID
	private String edmlEdmcNameLink;// 关联类名
	private String edmlEdmpNameLink;// 关联属性名称

	//对象定位公式
	private String formula;

	private String formulaDesc;

	private List<EdmLinkVO> children;

	private String condName;

	private String condDesc;

	//联动方法所属类名
	private String className;


	public String getEdmcId() {
		return edmcId;
	}

	public void setEdmcId(String edmcId) {
		this.edmcId = edmcId == null ? null : edmcId.trim();
	}

	public String getEdmConnectId() {
		return edmConnectId;
	}

	public String setEdmConnectId(String strEdmConnectId) {
		return this.edmConnectId = strEdmConnectId == null ? null : strEdmConnectId.trim();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getEdmlEdmpId() {
		return edmlEdmpId;
	}

	public void setEdmlEdmpId(String edmlEdmpId) {
		this.edmlEdmpId = edmlEdmpId == null ? null : edmlEdmpId.trim();
	}

	public String getEdmlEdmpLinkId() {
		return edmlEdmpLinkId;
	}

	public void setEdmlEdmpLinkId(String edmlEdmpLinkId) {
		this.edmlEdmpLinkId = edmlEdmpLinkId == null ? null : edmlEdmpLinkId.trim();
	}

	public String getEdmlCond() {
		return edmlCond;
	}

	public void setEdmlCond(String edmlCond) {
		this.edmlCond = edmlCond == null ? null : edmlCond.trim();
	}

	public String getEdmlFormula() {
		return edmlFormula;
	}

	public void setEdmlFormula(String edmlFormula) {
		this.edmlFormula = edmlFormula == null ? null : edmlFormula.trim();
	}

	public Byte getEdcnLinkPreservable() {
		return edcnLinkPreservable;
	}

	public void setEdcnLinkPreservable(Byte edcnLinkPreservable) {
		this.edcnLinkPreservable = edcnLinkPreservable;
	}

	public String getEdclUpdateType() {
		return edclUpdateType;
	}

	public void setEdclUpdateType(String edclUpdateType) {
		this.edclUpdateType = edclUpdateType;
	}

	public String getEdclUpdateTime() {
		return edclUpdateTime;
	}

	public void setEdclUpdateTime(String edclUpdateTime) {
		this.edclUpdateTime = edclUpdateTime == null ? null : edclUpdateTime.trim();
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

	public String getEdmlEdmcNameLink() {
		return edmlEdmcNameLink;
	}

	public void setEdmlEdmcNameLink(String edmlEdmcNameLink) {
		this.edmlEdmcNameLink = edmlEdmcNameLink;
	}

	public String getEdmlEdmpNameLink() {
		return edmlEdmpNameLink;
	}

	public void setEdmlEdmpNameLink(String edmlEdmpNameLink) {
		this.edmlEdmpNameLink = edmlEdmpNameLink;
	}

	public String getEdmlEdmcIdLink() {
		return edmlEdmcIdLink;
	}

	public void setEdmlEdmcIdLink(String edmlEdmcIdLink) {
		this.edmlEdmcIdLink = edmlEdmcIdLink == null ? null : edmlEdmcIdLink.trim();
	}
	public String getAsyncTypePriority() {
		return asyncTypePriority;
	}

	public void setAsyncTypePriority(String asyncTypePriority) {
		this.asyncTypePriority = asyncTypePriority;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public String getEdclType() {
		return edclType;
	}

	public void setEdclType(String edclType) {
		this.edclType = edclType;
	}

	public String getFormulaDesc() {
		return formulaDesc;
	}

	public void setFormulaDesc(String formulaDesc) {
		this.formulaDesc = formulaDesc;
	}

	public List<EdmLinkVO> getChildren() {
		return children;
	}

	public void setChildren(List<EdmLinkVO> children) {
		this.children = children;
	}

	public String getCondName() {
		return condName;
	}

	public void setCondName(String condName) {
		this.condName = condName;
	}

	public String getCondDesc() {
		return condDesc;
	}

	public void setCondDesc(String condDesc) {
		this.condDesc = condDesc;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Override
	public String toString() {
		return "EdmLinkVO{" +
				"id='" + id + '\'' +
				", edmlEdmpId='" + edmlEdmpId + '\'' +
				", edmlEdmpLinkId='" + edmlEdmpLinkId + '\'' +
				", edmlCond='" + edmlCond + '\'' +
				", edmlFormula='" + edmlFormula + '\'' +
				", isDel=" + isDel +
				", addtime=" + addtime +
				", adduser='" + adduser + '\'' +
				", modtime=" + modtime +
				", moduser='" + moduser + '\'' +
				", acctid=" + acctid +
				", addtimeStr='" + addtimeStr + '\'' +
				", modtimeStr='" + modtimeStr + '\'' +
				", edmConnectId='" + edmConnectId + '\'' +
				", edcnLinkPreservable=" + edcnLinkPreservable +
				", edclUpdateType='" + edclUpdateType + '\'' +
				", edclUpdateTime='" + edclUpdateTime + '\'' +
				", asyncTypePriority='" + asyncTypePriority + '\'' +
				", edclType='" + edclType + '\'' +
				", edmcId='" + edmcId + '\'' +
				", edmcName='" + edmcName + '\'' +
				", edmpName='" + edmpName + '\'' +
				", edmlEdmcIdLink='" + edmlEdmcIdLink + '\'' +
				", edmlEdmcNameLink='" + edmlEdmcNameLink + '\'' +
				", edmlEdmpNameLink='" + edmlEdmpNameLink + '\'' +
				", formula='" + formula + '\'' +
				", formulaDesc='" + formulaDesc + '\'' +
				", children=" + children +
				", condName='" + condName + '\'' +
				", condDesc='" + condDesc + '\'' +
				'}';
	}
}
