package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 指标类实体
 * 
 */
public class IndexEntity extends EdmEntity implements Serializable {
    private static final long serialVersionUID = 2156598174304827L;
    
    /**指标编码*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="inde_code", className="")
    private String inde_code;
    /**指标名称 */
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="inde_name", className="")
    private String inde_name;
    /**指标计量单位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="inde_punit", className="")
    private Object inde_punit;
    /**监管对象*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="inde_moin_id", className="MonitorEntity")
    private String inde_moin_id;
    /**监管类版本*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="inde_moin_ver", className="")
    private String inde_moin_ver;

    public String getInde_code() {
        return inde_code;
    }

    public void setInde_code(String inde_code) {
        this.inde_code = inde_code;
    }

    public String getInde_name() {
        return inde_name;
    }

    public void setInde_name(String inde_name) {
        this.inde_name = inde_name;
    }

    public Object getInde_punit() {
        return inde_punit;
    }

    public void setInde_punit(Object inde_punit) {
        this.inde_punit = inde_punit;
    }

    public String getInde_moin_id() {
        return inde_moin_id;
    }

    public void setInde_moin_id(String inde_moin_id) {
        this.inde_moin_id = inde_moin_id;
    }

    public MonitorEntity loadInde_moin_id() {
        String propertyName = "inde_moin_id";
        return (MonitorEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getInde_moin_ver() {
        return inde_moin_ver;
    }

    public void setInde_moin_ver(String inde_moin_ver) {
        this.inde_moin_ver = inde_moin_ver;
    }



}