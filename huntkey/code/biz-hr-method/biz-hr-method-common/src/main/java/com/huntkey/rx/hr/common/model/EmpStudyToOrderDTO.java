package com.huntkey.rx.hr.common.model;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/***
 * 教育经历
 * @author fangkun
 *
 */
public class EmpStudyToOrderDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;//id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@JSONField(name="remp_stu_beg")
	private String oeeoStuBeg;//起始时间
	@JSONField(name="remp_stu_end")
	private String oeeoStuEnd;//结束时间
	@JSONField(name="remp_rsch")
	private String oeeoRsch;//学校
	private String oeeoRschName;//学校名称
	@JSONField(name="remp_major")
	private String oeeoMajor;//专业
	@JSONField(name="remp_degree")
	private String oeeoDegree;//学位
	@JSONField(name="remp_stu_type")
	private String oeeoStuType;//学历类型
	@JSONField(name="remp_stu_mode")
	private String oeeoStuMode;//培养方式
	@JSONField(name="remp_stu_cert")
	private String oeeoStuCert;//所获证书
	@JSONField(name="remp_cert_no")
	private String oeeoCertNo;//证书编号
	public String getOeeoStuBeg() {
		return oeeoStuBeg;
	}
	public void setOeeoStuBeg(String oeeoStuBeg) {
		this.oeeoStuBeg = oeeoStuBeg;
	}
	public String getOeeoStuEnd() {
		return oeeoStuEnd;
	}
	public void setOeeoStuEnd(String oeeoStuEnd) {
		this.oeeoStuEnd = oeeoStuEnd;
	}
	public String getOeeoRsch() {
		return oeeoRsch;
	}
	public void setOeeoRsch(String oeeoRsch) {
		this.oeeoRsch = oeeoRsch;
	}

	public String getOeeoRschName() {
		return oeeoRschName;
	}

	public void setOeeoRschName(String oeeoRschName) {
		this.oeeoRschName = oeeoRschName;
	}

	public String getOeeoMajor() {
		return oeeoMajor;
	}
	public void setOeeoMajor(String oeeoMajor) {
		this.oeeoMajor = oeeoMajor;
	}
	public String getOeeoDegree() {
		return oeeoDegree;
	}
	public void setOeeoDegree(String oeeoDegree) {
		this.oeeoDegree = oeeoDegree;
	}
	public String getOeeoStuType() {
		return oeeoStuType;
	}
	public void setOeeoStuType(String oeeoStuType) {
		this.oeeoStuType = oeeoStuType;
	}
	public String getOeeoStuMode() {
		return oeeoStuMode;
	}
	public void setOeeoStuMode(String oeeoStuMode) {
		this.oeeoStuMode = oeeoStuMode;
	}
	public String getOeeoStuCert() {
		return oeeoStuCert;
	}
	public void setOeeoStuCert(String oeeoStuCert) {
		this.oeeoStuCert = oeeoStuCert;
	}
	public String getOeeoCertNo() {
		return oeeoCertNo;
	}
	public void setOeeoCertNo(String oeeoCertNo) {
		this.oeeoCertNo = oeeoCertNo;
	}
}
