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
public class ViinViinVisibleSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 6868448449877314L;
    
    /**可见ppi集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="viin_vppi_set", className="ViinViinVppiSetbEntity")
    private List<ViinViinVppiSetbEntity> viin_vppi_set;
    /**可见kpi集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="viin_vkpi_set", className="ViinViinVkpiSetbEntity")
    private List<ViinViinVkpiSetbEntity> viin_vkpi_set;
    /**可见任务目标集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="viin_vtarget_set", className="ViinViinVtargetSetbEntity")
    private List<ViinViinVtargetSetbEntity> viin_vtarget_set;
    /**可见靶向集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="viin_vdashboard_set", className="ViinViinVdashboardSetbEntity")
    private List<ViinViinVdashboardSetbEntity> viin_vdashboard_set;

    public List<ViinViinVppiSetbEntity> loadViin_vppi_set() {
        String propertyName = "viin_vppi_set";
        String rootClass = "VisibleindexEntity";
        return (List<ViinViinVppiSetbEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setViin_vppi_set(List<ViinViinVppiSetbEntity> viin_vppi_set) {
        this.viin_vppi_set = viin_vppi_set;
    }

    public List<ViinViinVppiSetbEntity> getViin_vppi_set() {
        return viin_vppi_set;
    }

    public List<ViinViinVkpiSetbEntity> loadViin_vkpi_set() {
        String propertyName = "viin_vkpi_set";
        String rootClass = "VisibleindexEntity";
        return (List<ViinViinVkpiSetbEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setViin_vkpi_set(List<ViinViinVkpiSetbEntity> viin_vkpi_set) {
        this.viin_vkpi_set = viin_vkpi_set;
    }

    public List<ViinViinVkpiSetbEntity> getViin_vkpi_set() {
        return viin_vkpi_set;
    }

    public List<ViinViinVtargetSetbEntity> loadViin_vtarget_set() {
        String propertyName = "viin_vtarget_set";
        String rootClass = "VisibleindexEntity";
        return (List<ViinViinVtargetSetbEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setViin_vtarget_set(List<ViinViinVtargetSetbEntity> viin_vtarget_set) {
        this.viin_vtarget_set = viin_vtarget_set;
    }

    public List<ViinViinVtargetSetbEntity> getViin_vtarget_set() {
        return viin_vtarget_set;
    }

    public List<ViinViinVdashboardSetbEntity> loadViin_vdashboard_set() {
        String propertyName = "viin_vdashboard_set";
        String rootClass = "VisibleindexEntity";
        return (List<ViinViinVdashboardSetbEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setViin_vdashboard_set(List<ViinViinVdashboardSetbEntity> viin_vdashboard_set) {
        this.viin_vdashboard_set = viin_vdashboard_set;
    }

    public List<ViinViinVdashboardSetbEntity> getViin_vdashboard_set() {
        return viin_vdashboard_set;
    }



}