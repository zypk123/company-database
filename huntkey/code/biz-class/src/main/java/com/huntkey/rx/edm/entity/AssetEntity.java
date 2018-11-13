package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 财务类实体
 * 
 */
public class AssetEntity extends ResourceEntity implements Serializable {
    private static final long serialVersionUID = 6960657039589298L;
    
    /**资产类别*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="asse_type", className="")
    private String asse_type;
    /**名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="asse_name", className="")
    private String asse_name;
    /**编号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="asse_code", className="")
    private String asse_code;
    /**原值*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="asse_orig_value", className="")
    private String asse_orig_value;
    /**分摊办法*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="asse_appo_measure", className="")
    private String asse_appo_measure;

    public String getAsse_type() {
        return asse_type;
    }

    public void setAsse_type(String asse_type) {
        this.asse_type = asse_type;
    }

    public String getAsse_name() {
        return asse_name;
    }

    public void setAsse_name(String asse_name) {
        this.asse_name = asse_name;
    }

    public String getAsse_code() {
        return asse_code;
    }

    public void setAsse_code(String asse_code) {
        this.asse_code = asse_code;
    }

    public String getAsse_orig_value() {
        return asse_orig_value;
    }

    public void setAsse_orig_value(String asse_orig_value) {
        this.asse_orig_value = asse_orig_value;
    }

    public String getAsse_appo_measure() {
        return asse_appo_measure;
    }

    public void setAsse_appo_measure(String asse_appo_measure) {
        this.asse_appo_measure = asse_appo_measure;
    }



}