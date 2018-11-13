package com.huntkey.rx.purchase.common.model.relation;

import com.alibaba.fastjson.annotation.JSONField;
import com.huntkey.rx.edm.constant.RelaRelaServtCustSetbProperty;
import com.huntkey.rx.sceo.orm.common.constant.EdmSysColumn;

/**
 * Created by xuyf on 2018/1/18 0018.
 */
public class RelaRelaServtCustSetbDTO {

    @JSONField(name = EdmSysColumn.ID)
    private String id;

    @JSONField(name = EdmSysColumn.PID)
    private String pid;

    @JSONField(name = RelaRelaServtCustSetbProperty.RELA_STTYPE_CUST)
    private String relaSttypeCust;

    @JSONField(name = RelaRelaServtCustSetbProperty.RELA_STEMP_CUST)
    private String relaStempCust;

    private String relaStempNo;

    private String relaStempName;

    private String relaStempTel;

    private String relaStempPost;

    private String relaStempPostCode;

    private String relaStempPostName;

    private String relaStempDept;

    private String relaStempDeptCode;

    private String relaStempDeptName;

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

    public String getRelaSttypeCust() {
        return relaSttypeCust;
    }

    public void setRelaSttypeCust(String relaSttypeCust) {
        this.relaSttypeCust = relaSttypeCust;
    }

    public String getRelaStempCust() {
        return relaStempCust;
    }

    public void setRelaStempCust(String relaStempCust) {
        this.relaStempCust = relaStempCust;
    }

    public String getRelaStempNo() {
        return relaStempNo;
    }

    public void setRelaStempNo(String relaStempNo) {
        this.relaStempNo = relaStempNo;
    }

    public String getRelaStempName() {
        return relaStempName;
    }

    public void setRelaStempName(String relaStempName) {
        this.relaStempName = relaStempName;
    }

    public String getRelaStempTel() {
        return relaStempTel;
    }

    public void setRelaStempTel(String relaStempTel) {
        this.relaStempTel = relaStempTel;
    }

    public String getRelaStempPost() {
        return relaStempPost;
    }

    public void setRelaStempPost(String relaStempPost) {
        this.relaStempPost = relaStempPost;
    }

    public String getRelaStempPostCode() {
        return relaStempPostCode;
    }

    public void setRelaStempPostCode(String relaStempPostCode) {
        this.relaStempPostCode = relaStempPostCode;
    }

    public String getRelaStempPostName() {
        return relaStempPostName;
    }

    public void setRelaStempPostName(String relaStempPostName) {
        this.relaStempPostName = relaStempPostName;
    }

    public String getRelaStempDept() {
        return relaStempDept;
    }

    public void setRelaStempDept(String relaStempDept) {
        this.relaStempDept = relaStempDept;
    }

    public String getRelaStempDeptCode() {
        return relaStempDeptCode;
    }

    public void setRelaStempDeptCode(String relaStempDeptCode) {
        this.relaStempDeptCode = relaStempDeptCode;
    }

    public String getRelaStempDeptName() {
        return relaStempDeptName;
    }

    public void setRelaStempDeptName(String relaStempDeptName) {
        this.relaStempDeptName = relaStempDeptName;
    }
}
