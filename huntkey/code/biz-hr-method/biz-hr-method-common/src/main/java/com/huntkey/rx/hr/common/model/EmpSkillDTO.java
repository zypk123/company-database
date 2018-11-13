package com.huntkey.rx.hr.common.model;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class EmpSkillDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;//员工个人技能类ID
	private String pid;//员工类外键
	@JSONField(name="remp_skill_field")
	private String rempSkillField;//技能领域
	@JSONField(name="remp_skill_pro")
	private String rempSkillPro;//技能专业
	@JSONField(name="remp_util_mon")
	private String rempUtilMon;//应用实践
	@JSONField(name="remp_mast_level")
	private String rempMastLevel;//技能级别
	@JSONField(name="remp_skill_seq")
	private String rempSkillSeq;//排序

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
	public String getRempSkillField() {
		return rempSkillField;
	}
	public void setRempSkillField(String rempSkillField) {
		this.rempSkillField = rempSkillField;
	}
	public String getRempSkillPro() {
		return rempSkillPro;
	}
	public void setRempSkillPro(String rempSkillPro) {
		this.rempSkillPro = rempSkillPro;
	}
	public String getRempUtilMon() {
		return rempUtilMon;
	}
	public void setRempUtilMon(String rempUtilMon) {
		this.rempUtilMon = rempUtilMon;
	}
	public String getRempMastLevel() {
		return rempMastLevel;
	}
	public void setRempMastLevel(String rempMastLevel) {
		this.rempMastLevel = rempMastLevel;
	}

	public String getRempSkillSeq() {
		return rempSkillSeq;
	}

	public void setRempSkillSeq(String rempSkillSeq) {
		this.rempSkillSeq = rempSkillSeq;
	}
}
