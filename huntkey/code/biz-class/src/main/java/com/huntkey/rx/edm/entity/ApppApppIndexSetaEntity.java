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
public class ApppApppIndexSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 8867837801271474L;
    
    /**配置ppi集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="appp_ippi_set", className="ApppApppIppiSetbEntity")
    private List<ApppApppIppiSetbEntity> appp_ippi_set;
    /**配置kpi集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="appp_ikpi_set", className="ApppApppIkpiSetbEntity")
    private List<ApppApppIkpiSetbEntity> appp_ikpi_set;
    /**配置任务目标集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="appp_itarget_set", className="ApppApppItargetSetbEntity")
    private List<ApppApppItargetSetbEntity> appp_itarget_set;
    /**配置靶向集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="appp_idashboard_set", className="ApppApppIdashboardSetbEntity")
    private List<ApppApppIdashboardSetbEntity> appp_idashboard_set;

    public List<ApppApppIppiSetbEntity> loadAppp_ippi_set() {
        String propertyName = "appp_ippi_set";
        String rootClass = "ApplicableppiEntity";
        return (List<ApppApppIppiSetbEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setAppp_ippi_set(List<ApppApppIppiSetbEntity> appp_ippi_set) {
        this.appp_ippi_set = appp_ippi_set;
    }

    public List<ApppApppIppiSetbEntity> getAppp_ippi_set() {
        return appp_ippi_set;
    }

    public List<ApppApppIkpiSetbEntity> loadAppp_ikpi_set() {
        String propertyName = "appp_ikpi_set";
        String rootClass = "ApplicableppiEntity";
        return (List<ApppApppIkpiSetbEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setAppp_ikpi_set(List<ApppApppIkpiSetbEntity> appp_ikpi_set) {
        this.appp_ikpi_set = appp_ikpi_set;
    }

    public List<ApppApppIkpiSetbEntity> getAppp_ikpi_set() {
        return appp_ikpi_set;
    }

    public List<ApppApppItargetSetbEntity> loadAppp_itarget_set() {
        String propertyName = "appp_itarget_set";
        String rootClass = "ApplicableppiEntity";
        return (List<ApppApppItargetSetbEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setAppp_itarget_set(List<ApppApppItargetSetbEntity> appp_itarget_set) {
        this.appp_itarget_set = appp_itarget_set;
    }

    public List<ApppApppItargetSetbEntity> getAppp_itarget_set() {
        return appp_itarget_set;
    }

    public List<ApppApppIdashboardSetbEntity> loadAppp_idashboard_set() {
        String propertyName = "appp_idashboard_set";
        String rootClass = "ApplicableppiEntity";
        return (List<ApppApppIdashboardSetbEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setAppp_idashboard_set(List<ApppApppIdashboardSetbEntity> appp_idashboard_set) {
        this.appp_idashboard_set = appp_idashboard_set;
    }

    public List<ApppApppIdashboardSetbEntity> getAppp_idashboard_set() {
        return appp_idashboard_set;
    }



}