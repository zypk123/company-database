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
public class FormFormCustomSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 9098211748569408L;
    
    /**定制表单对象*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="form_csobj", className="CustomizedformEntity")
    private String form_csobj;

    public String getForm_csobj() {
        return form_csobj;
    }

    public void setForm_csobj(String form_csobj) {
        this.form_csobj = form_csobj;
    }

    public CustomizedformEntity loadForm_csobj() {
        String propertyName = "form_csobj";
        return (CustomizedformEntity)EntityUtils.getPropertyObj(this, propertyName);
    }



}