package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * PPI配置单类实体
 * 
 */
public class PpiassignmentEntity extends OrderEntity implements Serializable {
    private static final long serialVersionUID = 7779706469083514L;
    
    /**单号类型*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ppia_nbr_type", className="WordlistEntity")
    private String ppia_nbr_type;
    /**指标定义对象*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ppia_rpif", className="")
    private String ppia_rpif;
    /**统计范围集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ppia_rang_set", className="PpiaPpiaRangSetaEntity")
    private List<PpiaPpiaRangSetaEntity> ppia_rang_set;
    /**财年*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="ppia_fyear", className="")
    private String ppia_fyear;

    public String getPpia_nbr_type() {
        return ppia_nbr_type;
    }

    public void setPpia_nbr_type(String ppia_nbr_type) {
        this.ppia_nbr_type = ppia_nbr_type;
    }

    public WordlistEntity loadPpia_nbr_type() {
        String propertyName = "ppia_nbr_type";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getPpia_rpif() {
        return ppia_rpif;
    }

    public void setPpia_rpif(String ppia_rpif) {
        this.ppia_rpif = ppia_rpif;
    }

    public List<PpiaPpiaRangSetaEntity> loadPpia_rang_set() {
        String propertyName = "ppia_rang_set";
        String rootClass = "";
        return (List<PpiaPpiaRangSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setPpia_rang_set(List<PpiaPpiaRangSetaEntity> ppia_rang_set) {
        this.ppia_rang_set = ppia_rang_set;
    }

    public List<PpiaPpiaRangSetaEntity> getPpia_rang_set() {
        return ppia_rang_set;
    }

    public String getPpia_fyear() {
        return ppia_fyear;
    }

    public void setPpia_fyear(String ppia_fyear) {
        this.ppia_fyear = ppia_fyear;
    }



}