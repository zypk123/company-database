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
public class ProcProcChildSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 8391447120617251L;
    
    /**起始活动子流程*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="proc_cbegflow", className="ProcessEntity")
    private String proc_cbegflow;
    /**结束活动子流程*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="proc_cendflow", className="ProcessEntity")
    private String proc_cendflow;

    public String getProc_cbegflow() {
        return proc_cbegflow;
    }

    public void setProc_cbegflow(String proc_cbegflow) {
        this.proc_cbegflow = proc_cbegflow;
    }

    public ProcessEntity loadProc_cbegflow() {
        String propertyName = "proc_cbegflow";
        return (ProcessEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getProc_cendflow() {
        return proc_cendflow;
    }

    public void setProc_cendflow(String proc_cendflow) {
        this.proc_cendflow = proc_cendflow;
    }

    public ProcessEntity loadProc_cendflow() {
        String propertyName = "proc_cendflow";
        return (ProcessEntity)EntityUtils.getPropertyObj(this, propertyName);
    }



}