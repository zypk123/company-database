package com.huntkey.rx.hr.common.model;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class EmpSkillToOrderDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;//id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@JSONField(name="remp_skill_field")
	private String oeeoSkillField;//技能领域
	@JSONField(name="remp_skill_pro")
	private String oeeoSkillPro;//技能专业
	@JSONField(name="remp_util_mon")
	private String oeeoUtilMon;//应用实践
	@JSONField(name="remp_mast_level")
	private String oeeoMastLevel;//技能级别
	public String getOeeoSkillField() {
		return oeeoSkillField;
	}
	public void setOeeoSkillField(String oeeoSkillField) {
		this.oeeoSkillField = oeeoSkillField;
	}
	public String getOeeoSkillPro() {
		return oeeoSkillPro;
	}
	public void setOeeoSkillPro(String oeeoSkillPro) {
		this.oeeoSkillPro = oeeoSkillPro;
	}
	public String getOeeoUtilMon() {
		return oeeoUtilMon;
	}
	public void setOeeoUtilMon(String oeeoUtilMon) {
		this.oeeoUtilMon = oeeoUtilMon;
	}
	public String getOeeoMastLevel() {
		return oeeoMastLevel;
	}
	public void setOeeoMastLevel(String oeeoMastLevel) {
		this.oeeoMastLevel = oeeoMastLevel;
	}
}
