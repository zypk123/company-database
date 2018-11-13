package com.huntkey.rx.hr.common.model;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/***
 * 员工工作经历
 * @author fangkun
 *
 */
public class EmpWorkExpDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;//员工工作经历ID
	private String pid;
	@JSONField(name="remp_work_comp")
	private String rempWorkComp;//公司名称
	@JSONField(name="remp_work_post")
	private String rempWorkPost;//职位
	@JSONField(name="remp_work_beg")
	private String rempWorkBeg;//起始年月
	@JSONField(name="remp_work_end")
	private String rempWorkEnd;//结束年月
	@JSONField(name="remp_work_desc")
	private String rempWorkDesc;//工作描述
	@JSONField(name="remp_cons")
	private String rempCons;//咨询人
	@JSONField(name="remp_cons_rela")
	private String rempConsRela;//咨询人关系
	@JSONField(name="remp_cons_post")
	private String rempConsPost;//咨询人职位
	@JSONField(name="remp_cons_tel")
	private String rempConsTel;//咨询人电话
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
	public String getRempWorkComp() {
		return rempWorkComp;
	}
	public void setRempWorkComp(String rempWorkComp) {
		this.rempWorkComp = rempWorkComp;
	}
	public String getRempWorkPost() {
		return rempWorkPost;
	}
	public void setRempWorkPost(String rempWorkPost) {
		this.rempWorkPost = rempWorkPost;
	}
	public String getRempWorkBeg() {
		return rempWorkBeg;
	}
	public void setRempWorkBeg(String rempWorkBeg) {
		this.rempWorkBeg = rempWorkBeg;
	}
	public String getRempWorkEnd() {
		return rempWorkEnd;
	}
	public void setRempWorkEnd(String rempWorkEnd) {
		this.rempWorkEnd = rempWorkEnd;
	}
	public String getRempWorkDesc() {
		return rempWorkDesc;
	}
	public void setRempWorkDesc(String rempWorkDesc) {
		this.rempWorkDesc = rempWorkDesc;
	}
	public String getRempCons() {
		return rempCons;
	}
	public void setRempCons(String rempCons) {
		this.rempCons = rempCons;
	}
	public String getRempConsRela() {
		return rempConsRela;
	}
	public void setRempConsRela(String rempConsRela) {
		this.rempConsRela = rempConsRela;
	}
	public String getRempConsPost() {
		return rempConsPost;
	}
	public void setRempConsPost(String rempConsPost) {
		this.rempConsPost = rempConsPost;
	}
	public String getRempConsTel() {
		return rempConsTel;
	}
	public void setRempConsTel(String rempConsTel) {
		this.rempConsTel = rempConsTel;
	}
}
