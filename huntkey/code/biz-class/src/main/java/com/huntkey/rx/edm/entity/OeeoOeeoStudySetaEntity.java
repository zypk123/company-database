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
public class OeeoOeeoStudySetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 9591053951463172L;
    
    /**起始时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_stu_beg", className="")
    private Date oeeo_stu_beg;
    /**结束时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_stu_end", className="")
    private Date oeeo_stu_end;
    /**学校*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_rsch", className="SchoolEntity")
    private String oeeo_rsch;
    /**专业*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_major", className="WordlistEntity")
    private String oeeo_major;
    /**学历*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_degree", className="WordlistEntity")
    private String oeeo_degree;
    /**培养类型*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_stu_type", className="WordlistEntity")
    private String oeeo_stu_type;
    /**培养模式*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_stu_mode", className="WordlistEntity")
    private String oeeo_stu_mode;
    /**所获证书*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_stu_cert", className="")
    private String oeeo_stu_cert;
    /**证书编号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_cert_no", className="")
    private String oeeo_cert_no;

    public Date getOeeo_stu_beg() {
        return oeeo_stu_beg;
    }

    public void setOeeo_stu_beg(Date oeeo_stu_beg) {
        this.oeeo_stu_beg = oeeo_stu_beg;
    }

    public Date getOeeo_stu_end() {
        return oeeo_stu_end;
    }

    public void setOeeo_stu_end(Date oeeo_stu_end) {
        this.oeeo_stu_end = oeeo_stu_end;
    }

    public String getOeeo_rsch() {
        return oeeo_rsch;
    }

    public void setOeeo_rsch(String oeeo_rsch) {
        this.oeeo_rsch = oeeo_rsch;
    }

    public SchoolEntity loadOeeo_rsch() {
        String propertyName = "oeeo_rsch";
        return (SchoolEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOeeo_major() {
        return oeeo_major;
    }

    public void setOeeo_major(String oeeo_major) {
        this.oeeo_major = oeeo_major;
    }

    public WordlistEntity loadOeeo_major() {
        String propertyName = "oeeo_major";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOeeo_degree() {
        return oeeo_degree;
    }

    public void setOeeo_degree(String oeeo_degree) {
        this.oeeo_degree = oeeo_degree;
    }

    public WordlistEntity loadOeeo_degree() {
        String propertyName = "oeeo_degree";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOeeo_stu_type() {
        return oeeo_stu_type;
    }

    public void setOeeo_stu_type(String oeeo_stu_type) {
        this.oeeo_stu_type = oeeo_stu_type;
    }

    public WordlistEntity loadOeeo_stu_type() {
        String propertyName = "oeeo_stu_type";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOeeo_stu_mode() {
        return oeeo_stu_mode;
    }

    public void setOeeo_stu_mode(String oeeo_stu_mode) {
        this.oeeo_stu_mode = oeeo_stu_mode;
    }

    public WordlistEntity loadOeeo_stu_mode() {
        String propertyName = "oeeo_stu_mode";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOeeo_stu_cert() {
        return oeeo_stu_cert;
    }

    public void setOeeo_stu_cert(String oeeo_stu_cert) {
        this.oeeo_stu_cert = oeeo_stu_cert;
    }

    public String getOeeo_cert_no() {
        return oeeo_cert_no;
    }

    public void setOeeo_cert_no(String oeeo_cert_no) {
        this.oeeo_cert_no = oeeo_cert_no;
    }



}