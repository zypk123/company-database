package com.huntkey.rx.hr.common.model;

import java.io.Serializable;

public class PostExpDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String pid;
	private String rempPostHis;//岗位
	private String rempPgradHis;//岗级
	private String rempDtypHis;//任职方式
	private String rempPempHis;//汇报上级
	private String rempPostBeg;//生效日期
	private String rempPostEnd;//失效日期
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
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
