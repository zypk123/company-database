package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyBaseEntity;
import java.util.Date;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 实体
 * 
 */
public class RempRempStudySetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 2163119224155042L;
    
    /**起始时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_stu_beg", className="")
    private Date remp_stu_beg;
    /**结束时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_stu_end", className="")
    private Date remp_stu_end;
    /**学校*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_rsch", className="SchoolEntity")
    private String remp_rsch;
    /**专业*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_major", className="WordlistEntity")
    private String remp_major;
    /**学历*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_degree", className="WordlistEntity")
    private String remp_degree;
    /**学历类型*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_stu_type", className="WordlistEntity")
    private String remp_stu_type;
    /**培养方式*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_stu_mode", className="WordlistEntity")
    private String remp_stu_mode;
    /**所获证书*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_stu_cert", className="")
    private String remp_stu_cert;
    /**证书编号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_cert_no", className="")
    private String remp_cert_no;

    public Date getRemp_stu_beg() {
        return remp_stu_beg;
    }

    public void setRemp_stu_beg(Date remp_stu_beg) {
        this.remp_stu_beg = remp_stu_beg;
    }

    public Date getRemp_stu_end() {
        return remp_stu_end;
    }

    public void setRemp_stu_end(Date remp_stu_end) {
        this.remp_stu_end = remp_stu_end;
    }

    public String getRemp_rsch() {
        return remp_rsch;
    }

    public void setRemp_rsch(String remp_rsch) {
        this.remp_rsch = remp_rsch;
    }

    public SchoolEntity loadRemp_rsch() {
        String propertyName = "remp_rsch";
        return (SchoolEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getRemp_major() {
        return remp_major;
    }

    public void setRemp_major(String remp_major) {
        this.remp_major = remp_major;
    }

    public WordlistEntity loadRemp_major() {
        String propertyName = "remp_major";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getRemp_degree() {
        return remp_degree;
    }

    public void setRemp_degree(String remp_degree) {
        this.remp_degree = remp_degree;
    }

    public WordlistEntity loadRemp_degree() {
        String propertyName = "remp_degree";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getRemp_stu_type() {
        return remp_stu_type;
    }

    public void setRemp_stu_type(String remp_stu_type) {
        this.remp_stu_type = remp_stu_type;
    }

    public WordlistEntity loadRemp_stu_type() {
        String propertyName = "remp_stu_type";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getRemp_stu_mode() {
        return remp_stu_mode;
    }

    public void setRemp_stu_mode(String remp_stu_mode) {
        this.remp_stu_mode = remp_stu_mode;
    }

    public WordlistEntity loadRemp_stu_mode() {
        String propertyName = "remp_stu_mode";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getRemp_stu_cert() {
        return remp_stu_cert;
    }

    public void setRemp_stu_cert(String remp_stu_cert) {
        this.remp_stu_cert = remp_stu_cert;
    }

    public String getRemp_cert_no() {
        return remp_cert_no;
    }

    public void setRemp_cert_no(String remp_cert_no) {
        this.remp_cert_no = remp_cert_no;
    }



}