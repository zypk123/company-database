package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import java.util.Date;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 流程实例类实体
 * 
 */
public class ProcinstanceEntity extends ProcessEntity implements Serializable {
    private static final long serialVersionUID = 6941732073506850L;
    
    /**流程定义*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="prit_procpattern", className="ProcesspatternEntity")
    private String prit_procpattern;
    /**单据定义*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="prit_order_def", className="OperationdocumentdefinitionEntity")
    private String prit_order_def;
    /**关联表单*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="prit_form", className="FormEntity")
    private String prit_form;
    /**单据实例*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="prit_order", className="OrderEntity")
    private String prit_order;
    /**流程状态*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="prit_status", className="WordlistEntity")
    private String prit_status;
    /**开始时间*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="prit_starttime", className="")
    private Date prit_starttime;
    /**结束时间*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="pirt_endtime", className="")
    private Date pirt_endtime;

    public String getPrit_procpattern() {
        return prit_procpattern;
    }

    public void setPrit_procpattern(String prit_procpattern) {
        this.prit_procpattern = prit_procpattern;
    }

    public ProcesspatternEntity loadPrit_procpattern() {
        String propertyName = "prit_procpattern";
        return (ProcesspatternEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getPrit_order_def() {
        return prit_order_def;
    }

    public void setPrit_order_def(String prit_order_def) {
        this.prit_order_def = prit_order_def;
    }

    public OperationdocumentdefinitionEntity loadPrit_order_def() {
        String propertyName = "prit_order_def";
        return (OperationdocumentdefinitionEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getPrit_form() {
        return prit_form;
    }

    public void setPrit_form(String prit_form) {
        this.prit_form = prit_form;
    }

    public FormEntity loadPrit_form() {
        String propertyName = "prit_form";
        return (FormEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getPrit_order() {
        return prit_order;
    }

    public void setPrit_order(String prit_order) {
        this.prit_order = prit_order;
    }

    public OrderEntity loadPrit_order() {
        String propertyName = "prit_order";
        return (OrderEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getPrit_status() {
        return prit_status;
    }

    public void setPrit_status(String prit_status) {
        this.prit_status = prit_status;
    }

    public WordlistEntity loadPrit_status() {
        String propertyName = "prit_status";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Date getPrit_starttime() {
        return prit_starttime;
    }

    public void setPrit_starttime(Date prit_starttime) {
        this.prit_starttime = prit_starttime;
    }

    public Date getPirt_endtime() {
        return pirt_endtime;
    }

    public void setPirt_endtime(Date pirt_endtime) {
        this.pirt_endtime = pirt_endtime;
    }



}