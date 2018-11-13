package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import java.math.BigDecimal;
import java.util.Date;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 统计类实体
 * 
 */
public class StatisticsEntity extends InformationEntity implements Serializable {
    private static final long serialVersionUID = 8922470522086640L;
    
    /**周期类对象*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="stat_pridobj", className="PeriodEntity")
    private String stat_pridobj;
    /**所属监管类*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="stat_moniclass", className="")
    private String stat_moniclass;
    /**所属监管类对象*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="stat_moniobj", className="MonitorEntity")
    private String stat_moniobj;
    /**属性名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="stat_moniattr", className="")
    private String stat_moniattr;
    /**是否为可比数据*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="stat_iscompare", className="")
    private String stat_iscompare;
    /**财年*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="stat_fyear", className="")
    private String stat_fyear;
    /**周期名*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="stat_periorname", className="")
    private String stat_periorname;
    /**期次*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="stat_period", className="")
    private Integer stat_period;
    /**起始时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="stat_beg", className="")
    private Date stat_beg;
    /**结束时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="stat_end", className="")
    private Date stat_end;
    /**期初余额*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="stat_peramt", className="")
    private BigDecimal stat_peramt;
    /**本期累计*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="stat_curamt", className="")
    private BigDecimal stat_curamt;
    /**本期余额*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="stat_cursum", className="")
    private BigDecimal stat_cursum;

    public String getStat_pridobj() {
        return stat_pridobj;
    }

    public void setStat_pridobj(String stat_pridobj) {
        this.stat_pridobj = stat_pridobj;
    }

    public PeriodEntity loadStat_pridobj() {
        String propertyName = "stat_pridobj";
        return (PeriodEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getStat_moniclass() {
        return stat_moniclass;
    }

    public void setStat_moniclass(String stat_moniclass) {
        this.stat_moniclass = stat_moniclass;
    }

    public String getStat_moniobj() {
        return stat_moniobj;
    }

    public void setStat_moniobj(String stat_moniobj) {
        this.stat_moniobj = stat_moniobj;
    }

    public MonitorEntity loadStat_moniobj() {
        String propertyName = "stat_moniobj";
        return (MonitorEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getStat_moniattr() {
        return stat_moniattr;
    }

    public void setStat_moniattr(String stat_moniattr) {
        this.stat_moniattr = stat_moniattr;
    }

    public String getStat_iscompare() {
        return stat_iscompare;
    }

    public void setStat_iscompare(String stat_iscompare) {
        this.stat_iscompare = stat_iscompare;
    }

    public String getStat_fyear() {
        return stat_fyear;
    }

    public void setStat_fyear(String stat_fyear) {
        this.stat_fyear = stat_fyear;
    }

    public String getStat_periorname() {
        return stat_periorname;
    }

    public void setStat_periorname(String stat_periorname) {
        this.stat_periorname = stat_periorname;
    }

    public Integer getStat_period() {
        return stat_period;
    }

    public void setStat_period(Integer stat_period) {
        this.stat_period = stat_period;
    }

    public Date getStat_beg() {
        return stat_beg;
    }

    public void setStat_beg(Date stat_beg) {
        this.stat_beg = stat_beg;
    }

    public Date getStat_end() {
        return stat_end;
    }

    public void setStat_end(Date stat_end) {
        this.stat_end = stat_end;
    }

    public BigDecimal getStat_peramt() {
        return stat_peramt;
    }

    public void setStat_peramt(BigDecimal stat_peramt) {
        this.stat_peramt = stat_peramt;
    }

    public BigDecimal getStat_curamt() {
        return stat_curamt;
    }

    public void setStat_curamt(BigDecimal stat_curamt) {
        this.stat_curamt = stat_curamt;
    }

    public BigDecimal getStat_cursum() {
        return stat_cursum;
    }

    public void setStat_cursum(BigDecimal stat_cursum) {
        this.stat_cursum = stat_cursum;
    }



}