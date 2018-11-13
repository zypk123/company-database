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
public class GoimGoimCharSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 1335647593270546L;
    
    /**特性表编号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="goim_char_num", className="")
    private String goim_char_num;
    /**字母代码*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="goim_char_code", className="")
    private String goim_char_code;
    /**特性名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="goim_char_name", className="")
    private String goim_char_name;
    /**有关说明*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="goim_char_desc", className="")
    private String goim_char_desc;
    /**计量单位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="goim_char_meas", className="")
    private String goim_char_meas;

    public String getGoim_char_num() {
        return goim_char_num;
    }

    public void setGoim_char_num(String goim_char_num) {
        this.goim_char_num = goim_char_num;
    }

    public String getGoim_char_code() {
        return goim_char_code;
    }

    public void setGoim_char_code(String goim_char_code) {
        this.goim_char_code = goim_char_code;
    }

    public String getGoim_char_name() {
        return goim_char_name;
    }

    public void setGoim_char_name(String goim_char_name) {
        this.goim_char_name = goim_char_name;
    }

    public String getGoim_char_desc() {
        return goim_char_desc;
    }

    public void setGoim_char_desc(String goim_char_desc) {
        this.goim_char_desc = goim_char_desc;
    }

    public String getGoim_char_meas() {
        return goim_char_meas;
    }

    public void setGoim_char_meas(String goim_char_meas) {
        this.goim_char_meas = goim_char_meas;
    }



}