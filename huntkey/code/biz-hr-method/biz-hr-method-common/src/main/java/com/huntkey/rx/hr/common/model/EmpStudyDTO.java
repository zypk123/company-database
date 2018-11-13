package com.huntkey.rx.hr.common.model;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/***
 * 教育经历
 * @author fangkun
 *
 */
public class EmpStudyDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;//员工类外键
	private String pid;//员工类外键
	@JSONField(name="remp_stu_beg")
	private String rempStuBeg;//起始时间
	@JSONField(name="remp_stu_end")
	private String rempStuEnd;//结束时间
	@JSONField(name="remp_rsch")
	private String rempRsch;//学校
	@JSONField(name="remp_major")
	private String rempMajor;//专业
	@JSONField(name="remp_degree")
	private String rempDegree;//学历
	@JSONField(name="remp_stu_type")
	private String rempStuType;//学历类型
	@JSONField(name="remp_stu_mode")
	private String rempStuMode;//培养方式
	@JSONField(name="remp_stu_cert")
	private String rempStuCert;//所获证书
	
	@JSONField(name="remp_cert_no")
	private String rempCertNo;//证书编号
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
	public String getRempStuBeg() {
		return rempStuBeg;
	}
	public void setRempStuBeg(String rempStuBeg) {
		this.rempStuBeg = rempStuBeg;
	}
	public String getRempStuEnd() {
		return rempStuEnd;
	}
	public void setRempStuEnd(String rempStuEnd) {
		this.rempStuEnd = rempStuEnd;
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
	public String getRempStuType() {
		return rempStuType;
	}
	public void setRempStuType(String rempStuType) {
		this.rempStuType = rempStuType;
	}
	public String getRempStuMode() {
		return rempStuMode;
	}
	public void setRempStuMode(String rempStuMode) {
		this.rempStuMode = rempStuMode;
	}
	public String getRempStuCert() {
		return rempStuCert;
	}
	public void setRempStuCert(String rempStuCert) {
		this.rempStuCert = rempStuCert;
	}
	public String getRempCertNo() {
		return rempCertNo;
	}
	public void setRempCertNo(String rempCertNo) {
		this.rempCertNo = rempCertNo;
	}
}
