package com.huntkey.rx.hr.common.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by chengchen on 2017/11/22.
 */
public class OepcEditSetDTO {


    /**
     * 属性集id
     */
    @JSONField(name = "id")
    String id;

    /**
     * 单据对象id
     */
    @JSONField(name = "pid")
    String pid;

    /**
     * 部门id
     */
    @JSONField(name = "oepc_dept")
    String oepcDept;

    /**
     * 岗位id
     */
    @JSONField(name  = "oepc_post")
    String oepcPost;

    /**
     * 员工id
     */
    @JSONField(name = "oepc_emp")
    String oepcEmp;


    /**
     * 任职方式
     */
    @JSONField(name = "oepc_dtyp_type")
    String oepcDtypType;

    /**
     * 旧部门id
     */
    @JSONField(name = "oepc_dept_old")
    String oepcDeptOld;

    /**
     * 旧岗位id
     */
    @JSONField(name = "oepc_post_old")
    String oepcPostOld;


    /**
     * 旧任职方式
     */
    @JSONField(name = "oepc_dtyp_old")
    String oepcDtypOld;
    /**
     * 部门名称
     */
    @JSONField(name = "dept_name")
    String deptName;

    /**
     * 岗位名称
     */
    @JSONField(name = "post_name" )
    String postName;

    /**
     * 员工名称
     */
    @JSONField(name = "emp_name")
    String empName;

    /**
     * 旧部门名称
     */
    @JSONField(name = "old_dept_name")
    String oldDeptName;

    /**
     * 旧岗位名称
     */
    @JSONField(name = "old_post_name")
    String oldPostName;

    /**
     * 是否保留原岗位 "0" 保留 "1" 不保留
     * @return
     */
    @JSONField(name = "oepc_iskeep")
    String oepcIskeep;

    public String getOepcIskeep() {
        return oepcIskeep;
    }

    public void setOepcIskeep(String oepcIskeep) {
        this.oepcIskeep = oepcIskeep;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getOepcDept() {
        return oepcDept;
    }

    public void setOepcDept(String oepcDept) {
        this.oepcDept = oepcDept;
    }

    public String getOepcPost() {
        return oepcPost;
    }

    public void setOepcPost(String oepcPost) {
        this.oepcPost = oepcPost;
    }

    public String getOepcEmp() {
        return oepcEmp;
    }

    public void setOepcEmp(String oepcEmp) {
        this.oepcEmp = oepcEmp;
    }

    public String getOepcDtypType() {
        return oepcDtypType;
    }

    public void setOepcDtypType(String oepcDtypType) {
        this.oepcDtypType = oepcDtypType;
    }

    public String getOepcDeptOld() {
        return oepcDeptOld;
    }

    public void setOepcDeptOld(String oepcDeptOld) {
        this.oepcDeptOld = oepcDeptOld;
    }

    public String getOepcPostOld() {
        return oepcPostOld;
    }

    public void setOepcPostOld(String oepcPostOld) {
        this.oepcPostOld = oepcPostOld;
    }

    public String getOepcDtypOld() {
        return oepcDtypOld;
    }

    public void setOepcDtypOld(String oepcDtypOld) {
        this.oepcDtypOld = oepcDtypOld;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getOldDeptName() {
        return oldDeptName;
    }

    public void setOldDeptName(String oldDeptName) {
        this.oldDeptName = oldDeptName;
    }

    public String getOldPostName() {
        return oldPostName;
    }

    public void setOldPostName(String oldPostName) {
        this.oldPostName = oldPostName;
    }
}


