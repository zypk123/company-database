package com.huntkey.rx.hr.common.model;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
public class DeptEmployeeDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JSONField(name="id")
	private String deptEmpId;//岗位或者员工ID
	@JSONField(name="rpos_emp")
	private String rposEmp;//岗位任职人ID
	@JSONField(name="remp_post")
	private String rempPost;//员工岗位ID
	private String deptId;//部门ID
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	@JSONField(name="remp_no")
	private String empNo;//员工
	private String emp;//员工 "ID/姓名"格式
	@JSONField(name="remp_name")
	private String empName;
	@JSONField(name="rpos_name")
	private String rposName;//岗位名称
	@JSONField(name="remp_gender")
	private String rempGender;//性别
	@JSONField(name="rpos_duty_type")
	private String rposDutyType;//任职方式
	private String age;//年龄
	private String workYears;//工龄时间
	private String positionMonths;//在职时间
	private String postMonths;//在岗时间
	@JSONField(name="rpak_name")
	private String rpakName;//园区
	@JSONField(name="rela_name")
	private String relaName;//法人公司
	@JSONField(name="remp_rsch")
	private String rempRsch;//学校
	@JSONField(name="mdep_name")
	private String mdepName;//部门
	@JSONField(name="rpos_grade")
	private String rposGrade;//岗级
	@JSONField(name="remp_major")
	private String rempMajor;//专业
	@JSONField(name="remp_degree")
	private String rempDegree;//学历
	private String hasPostChange;//是否存在岗位新增 修改 注销的待审单
	private String hasEmpPostChange;//岗位是否有排岗的待审单
	private String rposPpostEmp;//直属上级名称
	private String hasRecordChange;//是否存在待审档案变更单据
	private String deptMajor;//部门主管人(1)还是协管人(2)
	
	public String getDeptMajor() {
		return deptMajor;
	}
	public void setDeptMajor(String deptMajor) {
		this.deptMajor = deptMajor;
	}
	public String getHasRecordChange() {
		return hasRecordChange;
	}
	public void setHasRecordChange(String hasRecordChange) {
		this.hasRecordChange = hasRecordChange;
	}
	public String getRposPpostEmp() {
		return rposPpostEmp;
	}
	public void setRposPpostEmp(String rposPpostEmp) {
		this.rposPpostEmp = rposPpostEmp;
	}
	public String getHasPostChange() {
		return hasPostChange;
	}
	public void setHasPostChange(String hasPostChange) {
		this.hasPostChange = hasPostChange;
	}
	public String getHasEmpPostChange() {
		return hasEmpPostChange;
	}
	public void setHasEmpPostChange(String hasEmpPostChange) {
		this.hasEmpPostChange = hasEmpPostChange;
	}
	public String getRposGrade() {
		return rposGrade;
	}
	public void setRposGrade(String rposGrade) {
		this.rposGrade = rposGrade;
	}

	public String getMdepName() {
		return mdepName;
	}
	public void setMdepName(String mdepName) {
		this.mdepName = mdepName;
	}
	public String getPostMonths() {
		return postMonths;
	}
	public void setPostMonths(String postMonths) {
		this.postMonths = postMonths;
	}
	public String getWorkYears() {
		return workYears;
	}
	public void setWorkYears(String workYears) {
		this.workYears = workYears;
	}
	public String getPositionMonths() {
		return positionMonths;
	}
	public void setPositionMonths(String positionMonths) {
		this.positionMonths = positionMonths;
	}
	public String getRpakName() {
		return rpakName;
	}
	public void setRpakName(String rpakName) {
		this.rpakName = rpakName;
	}
	public String getRelaName() {
		return relaName;
	}
	public void setRelaName(String relaName) {
		this.relaName = relaName;
	}
	public String getRempRsch() {
		return rempRsch;
	}
	public void setRempRsch(String rempRsch) {
		this.rempRsch = rempRsch;
	}
	public String getRempMajor() {
		return rempMajor;
	}
	public void setRempMajor(String rempMajor) {
		this.rempMajor = rempMajor;
	}
	public String getRempDegree() {
		return rempDegree;
	}
	public void setRempDegree(String rempDegree) {
		this.rempDegree = rempDegree;
	}
	public String getRposEmp() {
		return rposEmp;
	}
	public void setRposEmp(String rposEmp) {
		this.rposEmp = rposEmp;
	}
	public String getRempPost() {
		return rempPost;
	}
	public void setRempPost(String rempPost) {
		this.rempPost = rempPost;
	}
	public String getRposName() {
		return rposName;
	}
	public void setRposName(String rposName) {
		this.rposName = rposName;
	}
	public String getRempGender() {
		return rempGender;
	}
	public void setRempGender(String rempGender) {
		this.rempGender = rempGender;
	}
	public String getRposDutyType() {
		return rposDutyType;
	}
	public void setRposDutyType(String rposDutyType) {
		this.rposDutyType = rposDutyType;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getDeptEmpId() {
		return deptEmpId;
	}
	public void setDeptEmpId(String deptEmpId) {
		this.deptEmpId = deptEmpId;
	}
	public String getEmp() {
		return emp;
	}
	public void setEmp(String emp) {
		this.emp = emp;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
}
