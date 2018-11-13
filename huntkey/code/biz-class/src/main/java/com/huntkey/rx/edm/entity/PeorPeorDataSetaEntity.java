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
public class PeorPeorDataSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 735070425893197L;
    
    /**可见数据类*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="peor_dclass", className="ResourceEntity")
    private String peor_dclass;
    /**可否再授权*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="peor_disempower", className="")
    private Integer peor_disempower;
    /**生效时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="peor_dbeg", className="")
    private Date peor_dbeg;
    /**结束时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="peor_dend", className="")
    private Date peor_dend;
    /**检索限定条件*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="peor_drow_cond", className="")
    private String peor_drow_cond;
    /**授权属性可阅性控制集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="peor_dattr_set", className="PeorPeorDattrSetbEntity")
    private List<PeorPeorDattrSetbEntity> peor_dattr_set;

    public String getPeor_dclass() {
        return peor_dclass;
    }

    public void setPeor_dclass(String peor_dclass) {
        this.peor_dclass = peor_dclass;
    }

    public ResourceEntity loadPeor_dclass() {
        String propertyName = "peor_dclass";
        return (ResourceEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Integer getPeor_disempower() {
        return peor_disempower;
    }

    public void setPeor_disempower(Integer peor_disempower) {
        this.peor_disempower = peor_disempower;
    }

    public Date getPeor_dbeg() {
        return peor_dbeg;
    }

    public void setPeor_dbeg(Date peor_dbeg) {
        this.peor_dbeg = peor_dbeg;
    }

    public Date getPeor_dend() {
        return peor_dend;
    }

    public void setPeor_dend(Date peor_dend) {
        this.peor_dend = peor_dend;
    }

    public String getPeor_drow_cond() {
        return peor_drow_cond;
    }

    public void setPeor_drow_cond(String peor_drow_cond) {
        this.peor_drow_cond = peor_drow_cond;
    }

    public List<PeorPeorDattrSetbEntity> loadPeor_dattr_set() {
        String propertyName = "peor_dattr_set";
        String rootClass = "PositionempowerorderEntity";
        return (List<PeorPeorDattrSetbEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setPeor_dattr_set(List<PeorPeorDattrSetbEntity> peor_dattr_set) {
        this.peor_dattr_set = peor_dattr_set;
    }

    public List<PeorPeorDattrSetbEntity> getPeor_dattr_set() {
        return peor_dattr_set;
    }



}