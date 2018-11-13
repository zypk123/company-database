package com.huntkey.rx.hr.common.model;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/***
 * 任岗经历
 * @author fangkun
 *
 */
public class EmpPostSetDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;//员工任岗经历主键
	private String pid;//员工类外键
	private String classname;//类名
	@JSONField(name="remp_post_his")
	private String rempPostHis;//岗位类对象
	@JSONField(name="rpos_name")
	private String rempPostName;//岗位名称
	@JSONField(name="remp_pgrad_his")
	private String rempPgradHis;//岗级
	@JSONField(name="remp_dtyp_his")
	private String rempDtypHis;//任职方式
	@JSONField(name="remp_pemp_his")
	private String rempPempHis;//汇报上级
	@JSONField(name="remp_name")
	private String rempPempName;//汇报上级姓名
	@JSONField(name="remp_post_beg")
	private String rempPostBeg;//生效日期
	@JSONField(name="remp_post_end")
	private String rempPostEnd;//生效日期
	@JSONField(name="mdep_name_his")
	private String rempDeptName;//部门名称
	private String isMajor;//是否主管人
	private String postDays;//在岗天数
	public String getPostDays() {
		return postDays;
	}
	public void setPostDays(String postDays) {
		this.postDays = postDays;
	}
	public String getRempDeptName() {
		return rempDeptName;
	}
	public void setRempDeptName(String rempDeptName) {
		this.rempDeptName = rempDeptName;
	}

	public String getRempPostName() {
		return rempPostName;
	}
	public void setRempPostName(String rempPostName) {
		this.rempPostName = rempPostName;
	}
	public String getRempPempName() {
		return rempPempName;
	}
	public void setRempPempName(String rempPempName) {
		this.rempPempName = rempPempName;
	}
	public String getIsMajor() {
		return isMajor;
	}
	public void setIsMajor(String isMajor) {
		this.isMajor = isMajor;
	}
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

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getRempPostHis() {
		return rempPostHis;
	}
	public void setRempPostHis(String rempPostHis) {
		this.rempPostHis = rempPostHis;
	}
	public String getRempPgradHis() {
		return rempPgradHis;
	}
	public void setRempPgradHis(String rempPgradHis) {
		this.rempPgradHis = rempPgradHis;
	}
	public String getRempDtypHis() {
		return rempDtypHis;
	}
	public void setRempDtypHis(String rempDtypHis) {
		this.rempDtypHis = rempDtypHis;
	}
	public String getRempPempHis() {
		return rempPempHis;
	}
	public void setRempPempHis(String rempPempHis) {
		this.rempPempHis = rempPempHis;
	}
	public String getRempPostBeg() {
		return rempPostBeg;
	}
	public void setRempPostBeg(String rempPostBeg) {
		this.rempPostBeg = rempPostBeg;
	}
	public String getRempPostEnd() {
		return rempPostEnd;
	}
	public void setRempPostEnd(String rempPostEnd) {
		this.rempPostEnd = rempPostEnd;
	}
}
