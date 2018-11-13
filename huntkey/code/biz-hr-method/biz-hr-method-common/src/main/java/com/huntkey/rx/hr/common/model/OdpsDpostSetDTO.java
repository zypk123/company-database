package com.huntkey.rx.hr.common.model;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class OdpsDpostSetDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	@JSONField(name="id")
	private String id;//ID
	@JSONField(name="pid")
	private String pid;//外键
	@JSONField(name="odps_name")
	private String odpsName;//岗位名称
	@JSONField(name="odps_dept")
	private String odpsDept;//部门
	@JSONField(name="mdep_name")
	private String mdepName;//部门名称
	@JSONField(name="odps_rpof")
	private String odpsRpof;//职位
	@JSONField(name="rpof_name")
	private String rpofName;//职位名称
	@JSONField(name="odps_pgrade")
	private String odpsPgrade;//岗级
	@JSONField(name="odps_post")
	private String odpsPost;//岗位编码
	@JSONField(name="odps_ppost")
	private String odpsPpost;//汇报岗位
	@JSONField(name="rpos_name")
	private String rposName;//汇报岗位名称
	@JSONField(name="odps_duty")
	private String odpsDuty;//岗位职责
	@JSONField(name="odps_qual")
	private String odpsQual;//任职资格	
	@JSONField(name="odps_sub")
	private String odpsSub;//含下级
	@JSONField(name="odps_name_old")
	private String odpsNameOld;//岗位名称_旧
	@JSONField(name="odps_pgrade_old")
	private String odpsPgradeOld;//岗级_旧
	@JSONField(name="odps_ppost_old")
	private String odpsPpostOld;//汇报岗位_旧
	@JSONField(name="odps_duty_old")
	private String odpsDutyOld;//岗位职责_旧
	@JSONField(name="odps_qual_old")
	private String odpsQualOld;//任职资格_旧
	@JSONField(name="odps_lvl")
	private String odpsLvl;//岗位层级码
	@JSONField(name="odps_flag")
	private String odpsFlag;//标识
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getOdpsName() {
		return odpsName;
	}
	public void setOdpsName(String odpsName) {
		this.odpsName = odpsName;
	}
	public String getOdpsDept() {
		return odpsDept;
	}
	public void setOdpsDept(String odpsDept) {
		this.odpsDept = odpsDept;
	}
	public String getMdepName() {
		return mdepName;
	}
	public void setMdepName(String mdepName) {
		this.mdepName = mdepName;
	}
	public String getOdpsRpof() {
		return odpsRpof;
	}
	public void setOdpsRpof(String odpsRpof) {
		this.odpsRpof = odpsRpof;
	}
	public String getRpofName() {
		return rpofName;
	}
	public void setRpofName(String rpofName) {
		this.rpofName = rpofName;
	}
	public String getOdpsPgrade() {
		return odpsPgrade;
	}
	public void setOdpsPgrade(String odpsPgrade) {
		this.odpsPgrade = odpsPgrade;
	}
	public String getOdpsPost() {
		return odpsPost;
	}
	public void setOdpsPost(String odpsPost) {
		this.odpsPost = odpsPost;
	}
	public String getOdpsPpost() {
		return odpsPpost;
	}
	public void setOdpsPpost(String odpsPpost) {
		this.odpsPpost = odpsPpost;
	}
	public String getRposName() {
		return rposName;
	}
	public void setRposName(String rposName) {
		this.rposName = rposName;
	}
	public String getOdpsDuty() {
		return odpsDuty;
	}
	public void setOdpsDuty(String odpsDuty) {
		this.odpsDuty = odpsDuty;
	}
	public String getOdpsQual() {
		return odpsQual;
	}
	public void setOdpsQual(String odpsQual) {
		this.odpsQual = odpsQual;
	}
	public String getOdpsSub() {
		return odpsSub;
	}
	public void setOdpsSub(String odpsSub) {
		this.odpsSub = odpsSub;
	}
	public String getOdpsNameOld() {
		return odpsNameOld;
	}
	public void setOdpsNameOld(String odpsNameOld) {
		this.odpsNameOld = odpsNameOld;
	}
	public String getOdpsPgradeOld() {
		return odpsPgradeOld;
	}
	public void setOdpsPgradeOld(String odpsPgradeOld) {
		this.odpsPgradeOld = odpsPgradeOld;
	}
	public String getOdpsPpostOld() {
		return odpsPpostOld;
	}
	public void setOdpsPpostOld(String odpsPpostOld) {
		this.odpsPpostOld = odpsPpostOld;
	}
	public String getOdpsDutyOld() {
		return odpsDutyOld;
	}
	public void setOdpsDutyOld(String odpsDutyOld) {
		this.odpsDutyOld = odpsDutyOld;
	}
	public String getOdpsQualOld() {
		return odpsQualOld;
	}
	public void setOdpsQualOld(String odpsQualOld) {
		this.odpsQualOld = odpsQualOld;
	}
	public String getOdpsLvl() {
		return odpsLvl;
	}
	public void setOdpsLvl(String odpsLvl) {
		this.odpsLvl = odpsLvl;
	}
	public String getOdpsFlag() {
		return odpsFlag;
	}
	public void setOdpsFlag(String odpsFlag) {
		this.odpsFlag = odpsFlag;
	}

}
