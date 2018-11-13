package com.huntkey.rx.hr.common.model;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/***
 * 家庭成员类
 * @author fangkun
 *
 */
public class EmpFamToOrderDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;//id
	@JSONField(name="remp_fam_name")
	private String oeeoFamName;//姓名
	@JSONField(name="remp_fam_rela")
	private String oeeoFamRela;//关系
	@JSONField(name="remp_fam_com")
	private String oeeoFamComp;//工作单位
	@JSONField(name="remp_fam_post")
	private String oeeoFamPost;//职位
	@JSONField(name="remp_fam_tel")
	private String oeeoFamTel;//联系电话
	@JSONField(name="remp_fam_addr")
	private String oeeoFamAddr;//家庭地址
	public String getOeeoFamName() {
		return oeeoFamName;
	}
	public void setOeeoFamName(String oeeoFamName) {
		this.oeeoFamName = oeeoFamName;
	}
	public String getOeeoFamRela() {
		return oeeoFamRela;
	}
	public void setOeeoFamRela(String oeeoFamRela) {
		this.oeeoFamRela = oeeoFamRela;
	}
	public String getOeeoFamComp() {
		return oeeoFamComp;
	}
	public void setOeeoFamComp(String oeeoFamComp) {
		this.oeeoFamComp = oeeoFamComp;
	}
	public String getOeeoFamPost() {
		return oeeoFamPost;
	}
	public void setOeeoFamPost(String oeeoFamPost) {
		this.oeeoFamPost = oeeoFamPost;
	}
	public String getOeeoFamTel() {
		return oeeoFamTel;
	}
	public void setOeeoFamTel(String oeeoFamTel) {
		this.oeeoFamTel = oeeoFamTel;
	}
	public String getOeeoFamAddr() {
		return oeeoFamAddr;
	}
	public void setOeeoFamAddr(String oeeoFamAddr) {
		this.oeeoFamAddr = oeeoFamAddr;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
