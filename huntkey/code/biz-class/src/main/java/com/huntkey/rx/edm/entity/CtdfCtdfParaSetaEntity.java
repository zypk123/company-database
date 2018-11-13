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
public class CtdfCtdfParaSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 9854398506218233L;
    
    /**参数名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ctdf_pname", className="")
    private String ctdf_pname;
    /**参数编码*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ctdf_pcode", className="")
    private String ctdf_pcode;
    /**参数类型*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ctdf_ptype", className="")
    private String ctdf_ptype;
    /**参数分类*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ctdf_pclass", className="WordlistEntity")
    private String ctdf_pclass;
    /**参数描述*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ctdf_pdesc", className="")
    private String ctdf_pdesc;
    /**默认值*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ctdf_pdvalue", className="")
    private String ctdf_pdvalue;
    /**参数相关配置*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ctdf_prefcon", className="")
    private String ctdf_prefcon;

    public String getCtdf_pname() {
        return ctdf_pname;
    }

    public void setCtdf_pname(String ctdf_pname) {
        this.ctdf_pname = ctdf_pname;
    }

    public String getCtdf_pcode() {
        return ctdf_pcode;
    }

    public void setCtdf_pcode(String ctdf_pcode) {
        this.ctdf_pcode = ctdf_pcode;
    }

    public String getCtdf_ptype() {
        return ctdf_ptype;
    }

    public void setCtdf_ptype(String ctdf_ptype) {
        this.ctdf_ptype = ctdf_ptype;
    }

    public String getCtdf_pclass() {
        return ctdf_pclass;
    }

    public void setCtdf_pclass(String ctdf_pclass) {
        this.ctdf_pclass = ctdf_pclass;
    }

    public WordlistEntity loadCtdf_pclass() {
        String propertyName = "ctdf_pclass";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getCtdf_pdesc() {
        return ctdf_pdesc;
    }

    public void setCtdf_pdesc(String ctdf_pdesc) {
        this.ctdf_pdesc = ctdf_pdesc;
    }

    public String getCtdf_pdvalue() {
        return ctdf_pdvalue;
    }

    public void setCtdf_pdvalue(String ctdf_pdvalue) {
        this.ctdf_pdvalue = ctdf_pdvalue;
    }

    public String getCtdf_prefcon() {
        return ctdf_prefcon;
    }

    public void setCtdf_prefcon(String ctdf_prefcon) {
        this.ctdf_prefcon = ctdf_prefcon;
    }



}