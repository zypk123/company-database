package com.huntkey.rx.hr.common.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by weijian on 2017/11/18.
 */
public class OeeoStudySetDTO {
    String id;
    String pid;
    String classname;
    @JSONField(name="oeeo_cert_no")
    String oeeoCertNo;//证书编码
    @JSONField(name="oeeo_degree")
    String oeeoDegree;//学历
    @JSONField(name="oeeo_major")
    String oeeoMajor;//专业
    @JSONField(name="oeeo_rsch")
    String oeeoRsch;//学校
    @JSONField(name="oeeo_stu_beg")
    String oeeoStuBeg;//起始时间
    @JSONField(name="oeeo_stu_cert")
    String oeeoStuCert;//所获证书
    @JSONField(name="oeeo_stu_end")
    String oeeoStuEnd;//结束时间
    @JSONField(name="oeeo_stu_mode")
    String oeeoStuMode;//培养方式
    @JSONField(name="oeeo_stu_type")
    String oeeoStuType;//学历类型
    String oeeoRschName;//学校名称
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

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getOeeoCertNo() {
        return oeeoCertNo;
    }

    public void setOeeoCertNo(String oeeoCertNo) {
        this.oeeoCertNo = oeeoCertNo;
    }

    public String getOeeoDegree() {
        return oeeoDegree;
    }

    public void setOeeoDegree(String oeeoDegree) {
        this.oeeoDegree = oeeoDegree;
    }

    public String getOeeoMajor() {
        return oeeoMajor;
    }

    public void setOeeoMajor(String oeeoMajor) {
        this.oeeoMajor = oeeoMajor;
    }

    public String getOeeoRsch() {
        return oeeoRsch;
    }

    public void setOeeoRsch(String oeeoRsch) {
        this.oeeoRsch = oeeoRsch;
    }

    public String getOeeoStuBeg() {
        return oeeoStuBeg;
    }

    public void setOeeoStuBeg(String oeeoStuBeg) {
        this.oeeoStuBeg = oeeoStuBeg;
    }

    public String getOeeoStuCert() {
        return oeeoStuCert;
    }

    public void setOeeoStuCert(String oeeoStuCert) {
        this.oeeoStuCert = oeeoStuCert;
    }

    public String getOeeoStuEnd() {
        return oeeoStuEnd;
    }

    public void setOeeoStuEnd(String oeeoStuEnd) {
        this.oeeoStuEnd = oeeoStuEnd;
    }

    public String getOeeoStuMode() {
        return oeeoStuMode;
    }

    public void setOeeoStuMode(String oeeoStuMode) {
        this.oeeoStuMode = oeeoStuMode;
    }

    public String getOeeoStuType() {
        return oeeoStuType;
    }

    public void setOeeoStuType(String oeeoStuType) {
        this.oeeoStuType = oeeoStuType;
    }

    public String getOeeoRschName() {
        return oeeoRschName;
    }

    public void setOeeoRschName(String oeeoRschName) {
        this.oeeoRschName = oeeoRschName;
    }
}
