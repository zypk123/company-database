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
public class OrdeOrdeWorkflowSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 6638865753932439L;
    
    /**顺序号*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="orde_wsort", className="")
    private Integer orde_wsort;
    /**业务流程对象*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="orde_wflowobj", className="ProcessEntity")
    private String orde_wflowobj;
    /**激活条件*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="orde_wactive_con", className="")
    private String orde_wactive_con;

    public Integer getOrde_wsort() {
        return orde_wsort;
    }

    public void setOrde_wsort(Integer orde_wsort) {
        this.orde_wsort = orde_wsort;
    }

    public String getOrde_wflowobj() {
        return orde_wflowobj;
    }

    public void setOrde_wflowobj(String orde_wflowobj) {
        this.orde_wflowobj = orde_wflowobj;
    }

    public ProcessEntity loadOrde_wflowobj() {
        String propertyName = "orde_wflowobj";
        return (ProcessEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOrde_wactive_con() {
        return orde_wactive_con;
    }

    public void setOrde_wactive_con(String orde_wactive_con) {
        this.orde_wactive_con = orde_wactive_con;
    }



}