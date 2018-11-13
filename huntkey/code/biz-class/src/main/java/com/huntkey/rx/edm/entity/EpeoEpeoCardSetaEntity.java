package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyBaseEntity;
import java.util.Date;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 实体
 * 
 */
public class EpeoEpeoCardSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 2957869804210398L;
    
    /**证件类别*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_card_type", className="WordlistEntity")
    private String epeo_card_type;
    /**证件号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_card_no", className="")
    private String epeo_card_no;
    /**发证机关*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_card_organ", className="")
    private String epeo_card_organ;
    /**证件生效日期*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_card_beg", className="")
    private Date epeo_card_beg;
    /**证件失效日期*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_card_end", className="")
    private Date epeo_card_end;

    public String getEpeo_card_type() {
        return epeo_card_type;
    }

    public void setEpeo_card_type(String epeo_card_type) {
        this.epeo_card_type = epeo_card_type;
    }

    public WordlistEntity loadEpeo_card_type() {
        String propertyName = "epeo_card_type";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getEpeo_card_no() {
        return epeo_card_no;
    }

    public void setEpeo_card_no(String epeo_card_no) {
        this.epeo_card_no = epeo_card_no;
    }

    public String getEpeo_card_organ() {
        return epeo_card_organ;
    }

    public void setEpeo_card_organ(String epeo_card_organ) {
        this.epeo_card_organ = epeo_card_organ;
    }

    public Date getEpeo_card_beg() {
        return epeo_card_beg;
    }

    public void setEpeo_card_beg(Date epeo_card_beg) {
        this.epeo_card_beg = epeo_card_beg;
    }

    public Date getEpeo_card_end() {
        return epeo_card_end;
    }

    public void setEpeo_card_end(Date epeo_card_end) {
        this.epeo_card_end = epeo_card_end;
    }



}