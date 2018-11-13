package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import java.util.Date;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 周期类实体
 * 
 */
public class PeriodEntity extends InformationEntity implements Serializable {
    private static final long serialVersionUID = 3959454021482274L;
    
    /**财年*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="peid_fyr", className="")
    private String peid_fyr;
    /**周期名*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="peid_name", className="WordlistEntity")
    private String peid_name;
    /**开始时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="peid_sdate", className="")
    private Date peid_sdate;
    /**结束时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="peid_edate", className="")
    private Date peid_edate;
    /**期次*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="peid_proid", className="")
    private Integer peid_proid;

    public String getPeid_fyr() {
        return peid_fyr;
    }

    public void setPeid_fyr(String peid_fyr) {
        this.peid_fyr = peid_fyr;
    }

    public String getPeid_name() {
        return peid_name;
    }

    public void setPeid_name(String peid_name) {
        this.peid_name = peid_name;
    }

    public WordlistEntity loadPeid_name() {
        String propertyName = "peid_name";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Date getPeid_sdate() {
        return peid_sdate;
    }

    public void setPeid_sdate(Date peid_sdate) {
        this.peid_sdate = peid_sdate;
    }

    public Date getPeid_edate() {
        return peid_edate;
    }

    public void setPeid_edate(Date peid_edate) {
        this.peid_edate = peid_edate;
    }

    public Integer getPeid_proid() {
        return peid_proid;
    }

    public void setPeid_proid(Integer peid_proid) {
        this.peid_proid = peid_proid;
    }



}