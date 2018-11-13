package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import java.math.BigDecimal;
import com.huntkey.rx.base.PropertyBaseEntity;
import java.util.Date;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 实体
 * 
 */
public class TaexTaexResourceSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 3763433285322691L;
    
    /**资源对象*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="taex_robj", className="ResourceEntity")
    private String taex_robj;
    /**实际开始时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="taex_rrbeg", className="")
    private Date taex_rrbeg;
    /**实际结束时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="taex_rrend", className="")
    private Date taex_rrend;
    /**内容*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="taex_rcont", className="")
    private String taex_rcont;
    /**产出物*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="taex_routput", className="")
    private String taex_routput;
    /**时长*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="taex_rtime", className="")
    private BigDecimal taex_rtime;
    /**数量*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="taex_rqty", className="")
    private BigDecimal taex_rqty;

    public String getTaex_robj() {
        return taex_robj;
    }

    public void setTaex_robj(String taex_robj) {
        this.taex_robj = taex_robj;
    }

    public ResourceEntity loadTaex_robj() {
        String propertyName = "taex_robj";
        return (ResourceEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Date getTaex_rrbeg() {
        return taex_rrbeg;
    }

    public void setTaex_rrbeg(Date taex_rrbeg) {
        this.taex_rrbeg = taex_rrbeg;
    }

    public Date getTaex_rrend() {
        return taex_rrend;
    }

    public void setTaex_rrend(Date taex_rrend) {
        this.taex_rrend = taex_rrend;
    }

    public String getTaex_rcont() {
        return taex_rcont;
    }

    public void setTaex_rcont(String taex_rcont) {
        this.taex_rcont = taex_rcont;
    }

    public String getTaex_routput() {
        return taex_routput;
    }

    public void setTaex_routput(String taex_routput) {
        this.taex_routput = taex_routput;
    }

    public BigDecimal getTaex_rtime() {
        return taex_rtime;
    }

    public void setTaex_rtime(BigDecimal taex_rtime) {
        this.taex_rtime = taex_rtime;
    }

    public BigDecimal getTaex_rqty() {
        return taex_rqty;
    }

    public void setTaex_rqty(BigDecimal taex_rqty) {
        this.taex_rqty = taex_rqty;
    }



}