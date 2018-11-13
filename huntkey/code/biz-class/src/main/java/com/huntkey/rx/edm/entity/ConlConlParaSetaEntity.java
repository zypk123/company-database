package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyBaseEntity;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 实体
 * 
 */
public class ConlConlParaSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 4995035561102491L;
    
    /**参数名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="conl_pname", className="")
    private String conl_pname;
    /**参数分类*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="conl_pclass", className="")
    private String conl_pclass;
    /**参数描述*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="conl_pdesc", className="")
    private String conl_pdesc;
    /**参数编码*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="conl_pcode", className="")
    private String conl_pcode;
    /**参数数据类型*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="conl_pdatatype", className="")
    private String conl_pdatatype;
    /**参数值*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="conl_pvalue", className="")
    private String conl_pvalue;
    /**默认值*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="conl_pdefault", className="")
    private String conl_pdefault;

    public String getConl_pname() {
        return conl_pname;
    }

    public void setConl_pname(String conl_pname) {
        this.conl_pname = conl_pname;
    }

    public String getConl_pclass() {
        return conl_pclass;
    }

    public void setConl_pclass(String conl_pclass) {
        this.conl_pclass = conl_pclass;
    }

    public String getConl_pdesc() {
        return conl_pdesc;
    }

    public void setConl_pdesc(String conl_pdesc) {
        this.conl_pdesc = conl_pdesc;
    }

    public String getConl_pcode() {
        return conl_pcode;
    }

    public void setConl_pcode(String conl_pcode) {
        this.conl_pcode = conl_pcode;
    }

    public String getConl_pdatatype() {
        return conl_pdatatype;
    }

    public void setConl_pdatatype(String conl_pdatatype) {
        this.conl_pdatatype = conl_pdatatype;
    }

    public String getConl_pvalue() {
        return conl_pvalue;
    }

    public void setConl_pvalue(String conl_pvalue) {
        this.conl_pvalue = conl_pvalue;
    }

    public String getConl_pdefault() {
        return conl_pdefault;
    }

    public void setConl_pdefault(String conl_pdefault) {
        this.conl_pdefault = conl_pdefault;
    }



}