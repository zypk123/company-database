package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import java.math.BigDecimal;
import com.huntkey.rx.base.PropertyBaseEntity;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 实体
 * 
 */
public class MeasMeasDefineSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 740135520425816L;
    
    /**单位符号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="meas_dsymbol", className="")
    private String meas_dsymbol;
    /**是否基准单位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="meas_dbase", className="")
    private Integer meas_dbase;
    /**状态*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="meas_denable", className="")
    private Integer meas_denable;
    /**计量名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="meas_dname", className="")
    private String meas_dname;
    /**制式*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="meas_dtype", className="")
    private Integer meas_dtype;
    /**小数点位数*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="meas_dradix_num", className="")
    private Integer meas_dradix_num;
    /**小数点归整方法*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="meas_dradix_put", className="")
    private String meas_dradix_put;
    /**与基准计量级差*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="meas_dstages", className="")
    private BigDecimal meas_dstages;
    /**换算率*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="meas_drate", className="")
    private BigDecimal meas_drate;

    public String getMeas_dsymbol() {
        return meas_dsymbol;
    }

    public void setMeas_dsymbol(String meas_dsymbol) {
        this.meas_dsymbol = meas_dsymbol;
    }

    public Integer getMeas_dbase() {
        return meas_dbase;
    }

    public void setMeas_dbase(Integer meas_dbase) {
        this.meas_dbase = meas_dbase;
    }

    public Integer getMeas_denable() {
        return meas_denable;
    }

    public void setMeas_denable(Integer meas_denable) {
        this.meas_denable = meas_denable;
    }

    public String getMeas_dname() {
        return meas_dname;
    }

    public void setMeas_dname(String meas_dname) {
        this.meas_dname = meas_dname;
    }

    public Integer getMeas_dtype() {
        return meas_dtype;
    }

    public void setMeas_dtype(Integer meas_dtype) {
        this.meas_dtype = meas_dtype;
    }

    public Integer getMeas_dradix_num() {
        return meas_dradix_num;
    }

    public void setMeas_dradix_num(Integer meas_dradix_num) {
        this.meas_dradix_num = meas_dradix_num;
    }

    public String getMeas_dradix_put() {
        return meas_dradix_put;
    }

    public void setMeas_dradix_put(String meas_dradix_put) {
        this.meas_dradix_put = meas_dradix_put;
    }

    public BigDecimal getMeas_dstages() {
        return meas_dstages;
    }

    public void setMeas_dstages(BigDecimal meas_dstages) {
        this.meas_dstages = meas_dstages;
    }

    public BigDecimal getMeas_drate() {
        return meas_drate;
    }

    public void setMeas_drate(BigDecimal meas_drate) {
        this.meas_drate = meas_drate;
    }



}