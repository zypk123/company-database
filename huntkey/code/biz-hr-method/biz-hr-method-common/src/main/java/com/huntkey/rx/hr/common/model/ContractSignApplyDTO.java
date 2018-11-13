package com.huntkey.rx.hr.common.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouyou on 2017/11/18.
 */
public class ContractSignApplyDTO extends OrderDTO {

    @JSONField(name = NodeConstant.ID)
    private String id;

    //部门
    @JSONField(name = ContractSignApplyConstants.OCSO_DEPT)
    private String ocsoDept;
    //签约法人
    @JSONField(name = ContractSignApplyConstants.OCSO_MCOP)
    private String ocsoMcop;
    //签约类型
    @JSONField(name = ContractSignApplyConstants.OCSO_TYPE)
    private String ocsoType;
    //签订日期
    @JSONField(name = ContractSignApplyConstants.OCSO_SIGN_DATE)
    private String ocsoSignDate;

    /**
     * 签约人员
     */
    @JSONField(name = "ocso_emp_set")
    private List<OcsoEmpSetDTO> odscChagSet = new ArrayList<>();

    @JSONField(name = "creuser")
    private String creuser;

    @JSONField(name = "moduser")
    private String moduser;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOcsoDept() {
        return ocsoDept;
    }

    public void setOcsoDept(String ocsoDept) {
        this.ocsoDept = ocsoDept;
    }

    public String getOcsoMcop() {
        return ocsoMcop;
    }

    public void setOcsoMcop(String ocsoMcop) {
        this.ocsoMcop = ocsoMcop;
    }

    public String getOcsoType() {
        return ocsoType;
    }

    public void setOcsoType(String ocsoType) {
        this.ocsoType = ocsoType;
    }

    public String getOcsoSignDate() {
        return ocsoSignDate;
    }

    public void setOcsoSignDate(String ocsoSignDate) {
        this.ocsoSignDate = ocsoSignDate;
    }

    public List<OcsoEmpSetDTO> getOdscChagSet() {
        return odscChagSet;
    }

    public void setOdscChagSet(List<OcsoEmpSetDTO> odscChagSet) {
        this.odscChagSet = odscChagSet;
    }

    public String getCreuser() {
        return creuser;
    }

    public void setCreuser(String creuser) {
        this.creuser = creuser;
    }

    public String getModuser() {
        return moduser;
    }

    public void setModuser(String moduser) {
        this.moduser = moduser;
    }
}
