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
public class OcsoOcsoEmpSetaEntity extends PropertyBaseEntity implements Serializable {
    private static final long serialVersionUID = 4011094219764452L;
    
    /**员工部门*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ocso_emp_dept", className="DepttreeEntity")
    private String ocso_emp_dept;
    /**员工*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ocso_emp", className="EmployeeEntity")
    private String ocso_emp;
    /**合同开始日期*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ocso_cont_beg", className="")
    private Date ocso_cont_beg;
    /**合同结束日期*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ocso_cont_end", className="")
    private Date ocso_cont_end;

    public String getOcso_emp_dept() {
        return ocso_emp_dept;
    }

    public void setOcso_emp_dept(String ocso_emp_dept) {
        this.ocso_emp_dept = ocso_emp_dept;
    }

    public DepttreeEntity loadOcso_emp_dept() {
        String propertyName = "ocso_emp_dept";
        return (DepttreeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOcso_emp() {
        return ocso_emp;
    }

    public void setOcso_emp(String ocso_emp) {
        this.ocso_emp = ocso_emp;
    }

    public EmployeeEntity loadOcso_emp() {
        String propertyName = "ocso_emp";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Date getOcso_cont_beg() {
        return ocso_cont_beg;
    }

    public void setOcso_cont_beg(Date ocso_cont_beg) {
        this.ocso_cont_beg = ocso_cont_beg;
    }

    public Date getOcso_cont_end() {
        return ocso_cont_end;
    }

    public void setOcso_cont_end(Date ocso_cont_end) {
        this.ocso_cont_end = ocso_cont_end;
    }



}