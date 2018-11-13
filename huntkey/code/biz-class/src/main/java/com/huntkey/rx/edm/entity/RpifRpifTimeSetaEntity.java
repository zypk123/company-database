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
public class RpifRpifTimeSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 7351467691026997L;
    
    /**周期名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpif_time_name", className="")
    private String rpif_time_name;
    /**计算公式*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rpif_cal_fmul", className="")
    private String rpif_cal_fmul;
    /**公式样式*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="rpif_fmul_style", className="")
    private String rpif_fmul_style;

    public String getRpif_time_name() {
        return rpif_time_name;
    }

    public void setRpif_time_name(String rpif_time_name) {
        this.rpif_time_name = rpif_time_name;
    }

    public String getRpif_cal_fmul() {
        return rpif_cal_fmul;
    }

    public void setRpif_cal_fmul(String rpif_cal_fmul) {
        this.rpif_cal_fmul = rpif_cal_fmul;
    }

    public String getRpif_fmul_style() {
        return rpif_fmul_style;
    }

    public void setRpif_fmul_style(String rpif_fmul_style) {
        this.rpif_fmul_style = rpif_fmul_style;
    }



}