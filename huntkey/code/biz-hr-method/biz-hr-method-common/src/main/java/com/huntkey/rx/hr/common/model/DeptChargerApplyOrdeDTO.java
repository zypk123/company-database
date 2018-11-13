package com.huntkey.rx.hr.common.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by weijian on 2017/12/2.
 */
public class DeptChargerApplyOrdeDTO extends OrderDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @JSONField(name = "id")
    private String id;
    @JSONField(name = "odcs_dept_beg")
    private String odcsDeptBeg;//起始部门
    @JSONField(name = "odcs_beg")
    private Date odcsBeg;//生效时间
    @JSONField(name = "odcs_remark")
    private String odcsRemark;//备注
    @JSONField(name = "odcs_chrg_set")
    List<OdcsChrgSetDTO> odcsChrgSet;//责任人任免

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOdcsDeptBeg() {
        return odcsDeptBeg;
    }

    public void setOdcsDeptBeg(String odcsDeptBeg) {
        this.odcsDeptBeg = odcsDeptBeg;
    }

    public Date getOdcsBeg() {
        return odcsBeg;
    }

    public void setOdcsBeg(Date odcsBeg) {
        this.odcsBeg = odcsBeg;
    }

    public String getOdcsRemark() {
        return odcsRemark;
    }

    public void setOdcsRemark(String odcsRemark) {
        this.odcsRemark = odcsRemark;
    }

    public List<OdcsChrgSetDTO> getOdcsChrgSet() {
        return odcsChrgSet;
    }

    public void setOdcsChrgSet(List<OdcsChrgSetDTO> odcsChrgSet) {
        this.odcsChrgSet = odcsChrgSet;
    }
}
