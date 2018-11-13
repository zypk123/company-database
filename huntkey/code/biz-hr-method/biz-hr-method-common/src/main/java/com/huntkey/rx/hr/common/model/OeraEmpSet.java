package com.huntkey.rx.hr.common.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;

/**
 * 离职申请单-离职明细
 * @author liuwens
 * Created by liuwens on 2017/11/28.
 */
public class OeraEmpSet
{
    /**
     * 离职明细类对象Id
     */
    @JSONField(name =NodeConstant.ID)
    private String id;

    /**
     * 离职明细类关联离职申请单类对象Id
     */
    @JSONField(name =NodeConstant.PID)
    private String pId;

    /**
     * 离职人对象Id
     */
    @JSONField(name = EmpresignApplyConstants.OERA_EMP)
    private String oeraEmp;

    /**
     * 预离职日期
     */
    @JSONField(name = EmpresignApplyConstants.OERA_APP_DATE)
    private String oeraAppDate;

    /**
     * 批准离职日期
     */
    @JSONField(name = EmpresignApplyConstants.OERA_APPR_DATE)
    private String oeraApprDate;

    /**
     * 实际离职日期
     */
    @JSONField(name = EmpresignApplyConstants.OERA_REAL_DATE)
    private String oeraRealDate;

    /**
     * 离职审核意见
     */
    @JSONField(name = EmpresignApplyConstants.OERA_AUDIT_DESC)
    private String oeraAuditDesc;

    /**
     * 离职类型
     */
    @JSONField(name = EmpresignApplyConstants.OERA_TYPE)
    private String oeraType;

    /**
     * 离职原因
     */
    @JSONField(name = EmpresignApplyConstants.OERA_RESON)
    private String oeraReson;

    /**
     * 岗级
     */
    @JSONField(name = EmpresignApplyConstants.OERA_PGRADE)
    private String oeraPgrade;

    /**
     * 所属部门id
     */
    @JSONField(name = EmpresignApplyConstants.OERA_DEPT)
    private String oeraDept;

    /**
     * 离职申请数
     */
    @JSONField(name = EmpresignApplyConstants.OERA_NUM_AP)
    private Integer oeraNumAp;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id=id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId=pId;
    }

    public String getOeraEmp() {
        return oeraEmp;
    }

    public void setOeraEmp(String oeraEmp) {
        this.oeraEmp=oeraEmp;
    }

    public String getOeraAppDate() {
        return oeraAppDate;
    }

    public void setOeraAppDate(String oeraAppDate) {
        this.oeraAppDate=oeraAppDate;
    }

    public String getOeraApprDate() {
        return oeraApprDate;
    }

    public void setOeraApprDate(String oeraApprDate) {
        this.oeraApprDate=oeraApprDate;
    }

    public String getOeraRealDate() {
        return oeraRealDate;
    }

    public void setOeraRealDate(String oeraRealDate) {
        this.oeraRealDate=oeraRealDate;
    }

    public String getOeraAuditDesc() {
        return oeraAuditDesc;
    }

    public void setOeraAuditDesc(String oeraAuditDesc) {
        this.oeraAuditDesc=oeraAuditDesc;
    }

    public String getOeraType() {
        return oeraType;
    }

    public void setOeraType(String oeraType) {
        this.oeraType=oeraType;
    }

    public String getOeraReson() {
        return oeraReson;
    }

    public void setOeraReson(String oeraReson) {
        this.oeraReson=oeraReson;
    }

    public String getOeraPgrade() {
        return oeraPgrade;
    }

    public void setOeraPgrade(String oeraPgrade) {
        this.oeraPgrade=oeraPgrade;
    }

    public String getOeraDept() {
        return oeraDept;
    }

    public void setOeraDept(String oeraDept) {
        this.oeraDept=oeraDept;
    }

    public Integer getOeraNumAp() {
        return oeraNumAp;
    }

    public void setOeraNumAp(Integer oeraNumAp) {
        this.oeraNumAp=oeraNumAp;
    }
}
