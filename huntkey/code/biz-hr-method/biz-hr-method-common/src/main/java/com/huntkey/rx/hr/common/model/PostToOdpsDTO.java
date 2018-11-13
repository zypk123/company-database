package com.huntkey.rx.hr.common.model;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class PostToOdpsDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@JSONField(name="id")
	private String id;//岗位Id
	@JSONField(name="rpos_name")
	private String odps_name;//岗位名称
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@JSONField(name="rpos_dept")
	private String odps_dept;//部门
	@JSONField(name="rpos_rpof")
	private String odps_rpof;//职位
	private String rpof_type;//职位类别
	@JSONField(name="rpos_grade")
	private String odps_pgrade;//岗级
	@JSONField(name="rpos_code")
	private String odps_post;//岗位编码
	@JSONField(name="rpos_ppost")
	private String odps_ppost;//汇报岗位
	private String rpos_sup_name;//汇报岗位名称
	@JSONField(name="rpos_duty")
	private String odps_duty;//岗位职责
	@JSONField(name="rpos_qual")
	private String odps_qual;//任职资格	
	private String rpos_pos_name;//岗位编码/名称
	private String rpos_emp_name;//在编职员
	private String rpos_dept_name;//岗位所属部门名称
	public String getRpos_dept_name() {
		return rpos_dept_name;
	}
	public void setRpos_dept_name(String rpos_dept_name) {
		this.rpos_dept_name = rpos_dept_name;
	}
	public String getRpos_pos_name() {
		return rpos_pos_name;
	}
	public void setRpos_pos_name(String rpos_pos_name) {
		this.rpos_pos_name = rpos_pos_name;
	}
	public String getRpos_emp_name() {
		return rpos_emp_name;
	}
	public void setRpos_emp_name(String rpos_emp_name) {
		this.rpos_emp_name = rpos_emp_name;
	}
	
	public String getOdps_name() {
		return odps_name;
	}
	public void setOdps_name(String odps_name) {
		this.odps_name = odps_name;
	}
	public String getOdps_dept() {
		return odps_dept;
	}
	public void setOdps_dept(String odps_dept) {
		this.odps_dept = odps_dept;
	}
	public String getOdps_rpof() {
		return odps_rpof;
	}
	public void setOdps_rpof(String odps_rpof) {
		this.odps_rpof = odps_rpof;
	}
	public String getRpof_type() {
		return rpof_type;
	}
	public void setRpof_type(String rpof_type) {
		this.rpof_type = rpof_type;
	}
	public String getOdps_pgrade() {
		return odps_pgrade;
	}
	public void setOdps_pgrade(String odps_pgrade) {
		this.odps_pgrade = odps_pgrade;
	}
	public String getOdps_post() {
		return odps_post;
	}
	public void setOdps_post(String odps_post) {
		this.odps_post = odps_post;
	}
	public String getOdps_ppost() {
		return odps_ppost;
	}
	public void setOdps_ppost(String odps_ppost) {
		this.odps_ppost = odps_ppost;
	}
	public String getRpos_sup_name() {
		return rpos_sup_name;
	}
	public void setRpos_sup_name(String rpos_sup_name) {
		this.rpos_sup_name = rpos_sup_name;
	}
	public String getOdps_duty() {
		return odps_duty;
	}
	public void setOdps_duty(String odps_duty) {
		this.odps_duty = odps_duty;
	}
	public String getOdps_qual() {
		return odps_qual;
	}
	public void setOdps_qual(String odps_qual) {
		this.odps_qual = odps_qual;
	}
}
