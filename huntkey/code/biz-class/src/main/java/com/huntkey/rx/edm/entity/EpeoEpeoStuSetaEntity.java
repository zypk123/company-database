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
public class EpeoEpeoStuSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 380681010776968L;
    
    /**起始时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_stu_beg", className="")
    private Date epeo_stu_beg;
    /**结束时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_stu_end", className="")
    private Date epeo_stu_end;
    /**学校*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_rsch", className="SchoolEntity")
    private String epeo_rsch;
    /**专业*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_major", className="WordlistEntity")
    private String epeo_major;
    /**学历*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_degree", className="WordlistEntity")
    private String epeo_degree;
    /**学历类型*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_stu_type", className="WordlistEntity")
    private String epeo_stu_type;
    /**培养方式*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_stu_mode", className="WordlistEntity")
    private String epeo_stu_mode;
    /**所获证书*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_stu_cert", className="")
    private String epeo_stu_cert;
    /**证书编号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_cert_no", className="")
    private String epeo_cert_no;

    public Date getEpeo_stu_beg() {
        return epeo_stu_beg;
    }

    public void setEpeo_stu_beg(Date epeo_stu_beg) {
        this.epeo_stu_beg = epeo_stu_beg;
    }

    public Date getEpeo_stu_end() {
        return epeo_stu_end;
    }

    public void setEpeo_stu_end(Date epeo_stu_end) {
        this.epeo_stu_end = epeo_stu_end;
    }

    public String getEpeo_rsch() {
        return epeo_rsch;
    }

    public void setEpeo_rsch(String epeo_rsch) {
        this.epeo_rsch = epeo_rsch;
    }

    public SchoolEntity loadEpeo_rsch() {
        String propertyName = "epeo_rsch";
        return (SchoolEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getEpeo_major() {
        return epeo_major;
    }

    public void setEpeo_major(String epeo_major) {
        this.epeo_major = epeo_major;
    }

    public WordlistEntity loadEpeo_major() {
        String propertyName = "epeo_major";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getEpeo_degree() {
        return epeo_degree;
    }

    public void setEpeo_degree(String epeo_degree) {
        this.epeo_degree = epeo_degree;
    }

    public WordlistEntity loadEpeo_degree() {
        String propertyName = "epeo_degree";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getEpeo_stu_type() {
        return epeo_stu_type;
    }

    public void setEpeo_stu_type(String epeo_stu_type) {
        this.epeo_stu_type = epeo_stu_type;
    }

    public WordlistEntity loadEpeo_stu_type() {
        String propertyName = "epeo_stu_type";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getEpeo_stu_mode() {
        return epeo_stu_mode;
    }

    public void setEpeo_stu_mode(String epeo_stu_mode) {
        this.epeo_stu_mode = epeo_stu_mode;
    }

    public WordlistEntity loadEpeo_stu_mode() {
        String propertyName = "epeo_stu_mode";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getEpeo_stu_cert() {
        return epeo_stu_cert;
    }

    public void setEpeo_stu_cert(String epeo_stu_cert) {
        this.epeo_stu_cert = epeo_stu_cert;
    }

    public String getEpeo_cert_no() {
        return epeo_cert_no;
    }

    public void setEpeo_cert_no(String epeo_cert_no) {
        this.epeo_cert_no = epeo_cert_no;
    }



}