package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 指标配置类实体
 * 
 */
public class ApplicableppiEntity extends EmpowerEntity implements Serializable {
    private static final long serialVersionUID = 6731423810972169L;
    
    /**指标配置集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="appp_index_set", className="ApppApppIndexSetaEntity")
    private List<ApppApppIndexSetaEntity> appp_index_set;

    public List<ApppApppIndexSetaEntity> loadAppp_index_set() {
        String propertyName = "appp_index_set";
        String rootClass = "";
        return (List<ApppApppIndexSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setAppp_index_set(List<ApppApppIndexSetaEntity> appp_index_set) {
        this.appp_index_set = appp_index_set;
    }

    public List<ApppApppIndexSetaEntity> getAppp_index_set() {
        return appp_index_set;
    }



}