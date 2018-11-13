package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 项目计划单类实体
 * 
 */
public class ProjectplanorderEntity extends OrderEntity implements Serializable {
    private static final long serialVersionUID = 1184637766943402L;
    
    /**项目对象*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prpl_proj", className="ProjectEntity")
    private String prpl_proj;
    /**计划任务集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prpl_plantask_set", className="PrplPrplPlantaskSetaEntity")
    private List<PrplPrplPlantaskSetaEntity> prpl_plantask_set;
    /**项目成员集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="prpl_team_set", className="PrplPrplTeamSetaEntity")
    private List<PrplPrplTeamSetaEntity> prpl_team_set;

    public String getPrpl_proj() {
        return prpl_proj;
    }

    public void setPrpl_proj(String prpl_proj) {
        this.prpl_proj = prpl_proj;
    }

    public ProjectEntity loadPrpl_proj() {
        String propertyName = "prpl_proj";
        return (ProjectEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public List<PrplPrplPlantaskSetaEntity> loadPrpl_plantask_set() {
        String propertyName = "prpl_plantask_set";
        String rootClass = "";
        return (List<PrplPrplPlantaskSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setPrpl_plantask_set(List<PrplPrplPlantaskSetaEntity> prpl_plantask_set) {
        this.prpl_plantask_set = prpl_plantask_set;
    }

    public List<PrplPrplPlantaskSetaEntity> getPrpl_plantask_set() {
        return prpl_plantask_set;
    }

    public List<PrplPrplTeamSetaEntity> loadPrpl_team_set() {
        String propertyName = "prpl_team_set";
        String rootClass = "";
        return (List<PrplPrplTeamSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setPrpl_team_set(List<PrplPrplTeamSetaEntity> prpl_team_set) {
        this.prpl_team_set = prpl_team_set;
    }

    public List<PrplPrplTeamSetaEntity> getPrpl_team_set() {
        return prpl_team_set;
    }



}