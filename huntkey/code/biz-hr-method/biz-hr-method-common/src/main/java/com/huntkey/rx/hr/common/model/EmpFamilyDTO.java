package com.huntkey.rx.hr.common.model;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/***
 * 家庭成员类
 * @author fangkun
 *
 */
public class EmpFamilyDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;//员工家庭成员主键
	private String pid;//员工类外键
	@JSONField(name="remp_fam_name")
	private String rempFamName;//姓名
	@JSONField(name="remp_fam_rela")
	private String rempFamRela;//关系
	@JSONField(name="remp_fam_com")
	private String rempFamCom;//工作单位
	@JSONField(name="remp_fam_post")
	private String rempFamPost;//职位
	@JSONField(name="remp_fam_tel")
	private String rempFamTel;//联系电话
	@JSONField(name="remp_fam_addr")
	private String rempFamAddr;//家庭地址
	@JSONField(name="remp_fam_seq")
	private String rempFamSeq;//排序
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
	public String getRempFamName() {
		return rempFamName;
	}
	public void setRempFamName(String rempFamName) {
		this.rempFamName = rempFamName;
	}
	public String getRempFamRela() {
		return rempFamRela;
	}
	public void setRempFamRela(String rempFamRela) {
		this.rempFamRela = rempFamRela;
	}
	public String getRempFamCom() {
		return rempFamCom;
	}
	public void setRempFamCom(String rempFamCom) {
		this.rempFamCom = rempFamCom;
	}
	public String getRempFamPost() {
		return rempFamPost;
	}
	public void setRempFamPost(String rempFamPost) {
		this.rempFamPost = rempFamPost;
	}
	public String getRempFamTel() {
		return rempFamTel;
	}
	public void setRempFamTel(String rempFamTel) {
		this.rempFamTel = rempFamTel;
	}
	public String getRempFamAddr() {
		return rempFamAddr;
	}
	public void setRempFamAddr(String rempFamAddr) {
		this.rempFamAddr = rempFamAddr;
	}

	public String getRempFamSeq() {
		return rempFamSeq;
	}

	public void setRempFamSeq(String rempFamSeq) {
		this.rempFamSeq = rempFamSeq;
	}
}
