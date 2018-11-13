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
public class PrtkPrtkOutputSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 212049270889938L;
    
    /**序号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prtk_osort", className="")
    private Integer prtk_osort;
    /**内容*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prtk_ocont", className="")
    private String prtk_ocont;
    /**产出物*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prtk_ooutput", className="")
    private String prtk_ooutput;
    /**时长*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prtk_otime", className="")
    private BigDecimal prtk_otime;
    /**数量*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prtk_oqty", className="")
    private BigDecimal prtk_oqty;

    public Integer getPrtk_osort() {
        return prtk_osort;
    }

    public void setPrtk_osort(Integer prtk_osort) {
        this.prtk_osort = prtk_osort;
    }

    public String getPrtk_ocont() {
        return prtk_ocont;
    }

    public void setPrtk_ocont(String prtk_ocont) {
        this.prtk_ocont = prtk_ocont;
    }

    public String getPrtk_ooutput() {
        return prtk_ooutput;
    }

    public void setPrtk_ooutput(String prtk_ooutput) {
        this.prtk_ooutput = prtk_ooutput;
    }

    public BigDecimal getPrtk_otime() {
        return prtk_otime;
    }

    public void setPrtk_otime(BigDecimal prtk_otime) {
        this.prtk_otime = prtk_otime;
    }

    public BigDecimal getPrtk_oqty() {
        return prtk_oqty;
    }

    public void setPrtk_oqty(BigDecimal prtk_oqty) {
        this.prtk_oqty = prtk_oqty;
    }



}