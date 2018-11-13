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
public class OpfmOpfmTimeSetbEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 9095747108517965L;
    
    /**公式样式*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="opfm_fmul_style", className="")
    private String opfm_fmul_style;
    /**周期名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opfm_time_name", className="")
    private String opfm_time_name;
    /**计算公式*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opfm_cal_fmul", className="")
    private String opfm_cal_fmul;
    /**数据状态*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="opfm_data_stat", className="")
    private Integer opfm_data_stat;

    public String getOpfm_fmul_style() {
        return opfm_fmul_style;
    }

    public void setOpfm_fmul_style(String opfm_fmul_style) {
        this.opfm_fmul_style = opfm_fmul_style;
    }

    public String getOpfm_time_name() {
        return opfm_time_name;
    }

    public void setOpfm_time_name(String opfm_time_name) {
        this.opfm_time_name = opfm_time_name;
    }

    public String getOpfm_cal_fmul() {
        return opfm_cal_fmul;
    }

    public void setOpfm_cal_fmul(String opfm_cal_fmul) {
        this.opfm_cal_fmul = opfm_cal_fmul;
    }

    public Integer getOpfm_data_stat() {
        return opfm_data_stat;
    }

    public void setOpfm_data_stat(Integer opfm_data_stat) {
        this.opfm_data_stat = opfm_data_stat;
    }



}