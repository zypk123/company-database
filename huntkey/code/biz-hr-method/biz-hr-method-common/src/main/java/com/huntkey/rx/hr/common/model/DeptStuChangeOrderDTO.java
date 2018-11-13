package com.huntkey.rx.hr.common.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;

/**
 * Created by xuyf on 2017/11/15 0015.
 */
public class DeptStuChangeOrderDTO extends OrderDTO {

    @JSONField(name = NodeConstant.ID)
    private String id;
    
    @JSONField(name = DeptStuChangeOrderConstants.ODSC_DEPT_BEG)
    private String odscDeptBeg;

    @JSONField(name = DeptStuChangeOrderConstants.ODSC_TYPE)
    private String odscType;

    @JSONField(name = DeptStuChangeOrderConstants.ODSC_BEG)
    private Date odscBeg;

    @JSONField(name = DeptStuChangeOrderConstants.ODSC_REMARK)
    private String odscRemark;

    /**
     * 异动列表
     */
    @JSONField(name = "odsc_chag_set")
    private List<OdscChagSetDTO> odscChagSet = new ArrayList<>();

    private OdscChagSetTreeDTO odscChagSetTree;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getOdscDeptBeg() {
        return odscDeptBeg;
    }
    
    public void setOdscDeptBeg(String odscDeptBeg) {
        this.odscDeptBeg = odscDeptBeg;
    }

    public String getOdscType() {
        return odscType;
    }

    public void setOdscType(String odscType) {
        this.odscType = odscType;
    }

    public String getOdscRemark() {
        return odscRemark;
    }

    public void setOdscRemark(String odscRemark) {
        this.odscRemark = odscRemark;
    }

    public List<OdscChagSetDTO> getOdscChagSet() {
        return odscChagSet;
    }

    public void setOdscChagSet(List<OdscChagSetDTO> odscChagSet) {
        this.odscChagSet = odscChagSet;
    }

    public Date getOdscBeg() {
        return odscBeg;
    }

    public void setOdscBeg(Date odscBeg) {
        this.odscBeg = odscBeg;
    }

    public OdscChagSetTreeDTO getOdscChagSetTree() {
        return odscChagSetTree;
    }

    public void setOdscChagSetTree(OdscChagSetTreeDTO odscChagSetTree) {
        this.odscChagSetTree = odscChagSetTree;
    }
}
