package com.huntkey.rx.hr.common.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;

/**
 * Created by zhouyou on 2017/11/18.
 */
public class OcsoEmpSetDTO {

    @JSONField(name = NodeConstant.ID)
    private String id;

    @JSONField(name = NodeConstant.PID)
    private String pid;

    //员工部门
    @JSONField(name = OcsoEmpSetConstants.OCSO_EMP_DEPT)
    private String ocsoEmpDept;

    //员工
    @JSONField(name = OcsoEmpSetConstants.OCSO_EMP)
    private String ocsoEmp;

    //合同开始日期
    @JSONField(name = OcsoEmpSetConstants.OCSO_CONT_BEG)
    private String ocsoContBeg;

    //合同结束日期
    @JSONField(name = OcsoEmpSetConstants.OCSO_CONT_END)
    private String ocsoContEnd;

    //修改人
    @JSONField(name = OcsoEmpSetConstants.MODUSER)
    private String moduser;

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

    public String getOcsoEmpDept() {
        return ocsoEmpDept;
    }

    public void setOcsoEmpDept(String ocsoEmpDept) {
        this.ocsoEmpDept = ocsoEmpDept;
    }

    public String getOcsoEmp() {
        return ocsoEmp;
    }

    public void setOcsoEmp(String ocsoEmp) {
        this.ocsoEmp = ocsoEmp;
    }

    public String getOcsoContBeg() {
        return ocsoContBeg;
    }

    public void setOcsoContBeg(String ocsoContBeg) {
        this.ocsoContBeg = ocsoContBeg;
    }

    public String getOcsoContEnd() {
        return ocsoContEnd;
    }

    public void setOcsoContEnd(String ocsoContEnd) {
        this.ocsoContEnd = ocsoContEnd;
    }

    public String getModuser() {
        return moduser;
    }

    public void setModuser(String moduser) {
        this.moduser = moduser;
    }
}
