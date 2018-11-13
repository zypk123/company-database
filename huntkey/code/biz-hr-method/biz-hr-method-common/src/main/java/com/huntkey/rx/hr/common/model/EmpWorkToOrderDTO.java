package com.huntkey.rx.hr.common.model;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/***
 * 员工工作经历
 * @author fangkun
 *
 */
public class EmpWorkToOrderDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;//id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@JSONField(name="remp_work_comp")
	private String oeeoWorkComp;//公司名称
	@JSONField(name="remp_work_post")
	private String oeeoWorkPost;//职位
	@JSONField(name="remp_work_beg")
	private String oeeoWorkBeg;//起始年月
	@JSONField(name="remp_work_end")
	private String oeeoWorkEnd;//结束年月
	@JSONField(name="remp_work_desc")
	private String oeeoWorkDesc;//工作描述
	@JSONField(name="remp_cons")
	private String oeeoWorkCons;//咨询人
	@JSONField(name="remp_cons_rela")
	private String oeeoConsRela;//咨询人关系
	@JSONField(name="remp_cons_post")
	private String oeeoConsPost;//咨询人职位
	@JSONField(name="remp_cons_tel")
	private String oeeoConsTel;//咨询人电话
	public String getOeeoWorkComp() {
		return oeeoWorkComp;
	}
	public void setOeeoWorkComp(String oeeoWorkComp) {
		this.oeeoWorkComp = oeeoWorkComp;
	}
	public String getOeeoWorkPost() {
		return oeeoWorkPost;
	}
	public void setOeeoWorkPost(String oeeoWorkPost) {
		this.oeeoWorkPost = oeeoWorkPost;
	}
	public String getOeeoWorkBeg() {
		return oeeoWorkBeg;
	}
	public void setOeeoWorkBeg(String oeeoWorkBeg) {
		this.oeeoWorkBeg = oeeoWorkBeg;
	}
	public String getOeeoWorkEnd() {
		return oeeoWorkEnd;
	}
	public void setOeeoWorkEnd(String oeeoWorkEnd) {
		this.oeeoWorkEnd = oeeoWorkEnd;
	}
	public String getOeeoWorkDesc() {
		return oeeoWorkDesc;
	}
	public void setOeeoWorkDesc(String oeeoWorkDesc) {
		this.oeeoWorkDesc = oeeoWorkDesc;
	}
	public String getOeeoWorkCons() {
		return oeeoWorkCons;
	}
	public void setOeeoWorkCons(String oeeoWorkCons) {
		this.oeeoWorkCons = oeeoWorkCons;
	}
	public String getOeeoConsRela() {
		return oeeoConsRela;
	}
	public void setOeeoConsRela(String oeeoConsRela) {
		this.oeeoConsRela = oeeoConsRela;
	}
	public String getOeeoConsPost() {
		return oeeoConsPost;
	}
	public void setOeeoConsPost(String oeeoConsPost) {
		this.oeeoConsPost = oeeoConsPost;
	}
	public String getOeeoConsTel() {
		return oeeoConsTel;
	}
	public void setOeeoConsTel(String oeeoConsTel) {
		this.oeeoConsTel = oeeoConsTel;
	}
}
