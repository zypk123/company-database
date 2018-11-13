package com.huntkey.rx.sceo.formula.common.model;

import com.huntkey.rx.sceo.formula.common.base.BaseEntity;

import java.util.Date;

/**
 * @author chenfei on 2017/8/12
 */
public class AuditRoles extends BaseEntity {
    /** 审核角色编码 */
    private String aditId;
    /** 流程单据对象编码 */
    private String processDocuObjId;
    /** 序号 */
    private Integer auditSeq;
    /** 指定方式 */
    private String auditPattern;
    /** 审核角色人员 */
    private String auditRolesStaff;
    /** 条件 */
    private String auditCondition;
    /** 是否可用: 1 可用， 0 不可用 */
    private Byte isenable;
    /** 创建时间 */
    private Date addtime;
    /** 创建人 */
    private String adduser;
    /** 更新时间 */
    private Date modtime;
    /** 修改人 */
    private String moduser;
    /** 指定方式code */
    private String alternateField1;
    /** 审核角色code 根据审核方式 对应审核方式的code 比如是指定部门的话 对应就是指定部门的code*/
    private String alternateField2;
    /** 本级岗位 上级岗位 上上级岗位  主管人 上级主管人 上上级主管人 */
    private String alternateField3;
    /** 公式id */
    private String alternateField4;
    /** 公式名称 */
    private String alternateField5;
    /** 标记位 */
    private String mark;
    /** 岗位或部门对应的code */
    private String chooseCode;
    /** 备用1 */
    private String beiyong1;
    /** 备用2 */
    private String beiyong2;
    /** 备用3 */
    private String beiyong3;
    /** 岗位操作符 */
    private String postOperate;
    /** 岗位等级值 */
    private String postValue;
    /** 部门操作符 */
    private String deptOperate;
    /** 部门等级值 */
    private String deptValue;
    /** 备用4 */
    private String beiyong4;
    /** 备用5 */
    private String beiyong5;
    /** 备用6 */
    private String beiyong6;
    /** 备用7 */
    private String beiyong7;
    /** 备用8 */
    private String beiyong8;
    /** 备用9 */
    private String beiyong9;

    public String getAditId() {
        return aditId;
    }

    public void setAditId(String aditId) {
        this.aditId = aditId == null ? null : aditId.trim();
    }

    public String getProcessDocuObjId() {
        return processDocuObjId;
    }

    public void setProcessDocuObjId(String processDocuObjId) {
        this.processDocuObjId = processDocuObjId == null ? null : processDocuObjId.trim();
    }

    public Integer getAuditSeq() {
        return auditSeq;
    }

    public void setAuditSeq(Integer auditSeq) {
        this.auditSeq = auditSeq;
    }

    public String getAuditPattern() {
        return auditPattern;
    }

    public void setAuditPattern(String auditPattern) {
        this.auditPattern = auditPattern == null ? null : auditPattern.trim();
    }

    public String getAuditRolesStaff() {
        return auditRolesStaff;
    }

    public void setAuditRolesStaff(String auditRolesStaff) {
        this.auditRolesStaff = auditRolesStaff == null ? null : auditRolesStaff.trim();
    }

    public String getAuditCondition() {
        return auditCondition;
    }

    public void setAuditCondition(String auditCondition) {
        this.auditCondition = auditCondition == null ? null : auditCondition.trim();
    }
    @Override
    public Byte getIsenable() {
        return isenable;
    }
    @Override
    public void setIsenable(Byte isenable) {
        this.isenable = isenable;
    }
    @Override
    public Date getAddtime() {
        return addtime;
    }
    @Override
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
    @Override
    public String getAdduser() {
        return adduser;
    }
    @Override
    public void setAdduser(String adduser) {
        this.adduser = adduser == null ? null : adduser.trim();
    }
    @Override
    public Date getModtime() {
        return modtime;
    }
    @Override
    public void setModtime(Date modtime) {
        this.modtime = modtime;
    }
    @Override
    public String getModuser() {
        return moduser;
    }
    @Override
    public void setModuser(String moduser) {
        this.moduser = moduser == null ? null : moduser.trim();
    }

    public String getAlternateField1() {
        return alternateField1;
    }

    public void setAlternateField1(String alternateField1) {
        this.alternateField1 = alternateField1 == null ? null : alternateField1.trim();
    }

    public String getAlternateField2() {
        return alternateField2;
    }

    public void setAlternateField2(String alternateField2) {
        this.alternateField2 = alternateField2 == null ? null : alternateField2.trim();
    }

    public String getAlternateField3() {
        return alternateField3;
    }

    public void setAlternateField3(String alternateField3) {
        this.alternateField3 = alternateField3 == null ? null : alternateField3.trim();
    }

    public String getAlternateField4() {
        return alternateField4;
    }

    public void setAlternateField4(String alternateField4) {
        this.alternateField4 = alternateField4 == null ? null : alternateField4.trim();
    }

    public String getAlternateField5() {
        return alternateField5;
    }

    public void setAlternateField5(String alternateField5) {
        this.alternateField5 = alternateField5 == null ? null : alternateField5.trim();
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark == null ? null : mark.trim();
    }

    public String getChooseCode() {
        return chooseCode;
    }

    public void setChooseCode(String chooseCode) {
        this.chooseCode = chooseCode == null ? null : chooseCode.trim();
    }

    public String getBeiyong1() {
        return beiyong1;
    }

    public void setBeiyong1(String beiyong1) {
        this.beiyong1 = beiyong1 == null ? null : beiyong1.trim();
    }

    public String getBeiyong2() {
        return beiyong2;
    }

    public void setBeiyong2(String beiyong2) {
        this.beiyong2 = beiyong2 == null ? null : beiyong2.trim();
    }

    public String getBeiyong3() {
        return beiyong3;
    }

    public void setBeiyong3(String beiyong3) {
        this.beiyong3 = beiyong3 == null ? null : beiyong3.trim();
    }

    public String getPostOperate() {
        return postOperate;
    }

    public void setPostOperate(String postOperate) {
        this.postOperate = postOperate == null ? null : postOperate.trim();
    }

    public String getPostValue() {
        return postValue;
    }

    public void setPostValue(String postValue) {
        this.postValue = postValue == null ? null : postValue.trim();
    }

    public String getDeptOperate() {
        return deptOperate;
    }

    public void setDeptOperate(String deptOperate) {
        this.deptOperate = deptOperate == null ? null : deptOperate.trim();
    }

    public String getDeptValue() {
        return deptValue;
    }

    public void setDeptValue(String deptValue) {
        this.deptValue = deptValue == null ? null : deptValue.trim();
    }

    public String getBeiyong4() {
        return beiyong4;
    }

    public void setBeiyong4(String beiyong4) {
        this.beiyong4 = beiyong4 == null ? null : beiyong4.trim();
    }

    public String getBeiyong5() {
        return beiyong5;
    }

    public void setBeiyong5(String beiyong5) {
        this.beiyong5 = beiyong5 == null ? null : beiyong5.trim();
    }

    public String getBeiyong6() {
        return beiyong6;
    }

    public void setBeiyong6(String beiyong6) {
        this.beiyong6 = beiyong6 == null ? null : beiyong6.trim();
    }

    public String getBeiyong7() {
        return beiyong7;
    }

    public void setBeiyong7(String beiyong7) {
        this.beiyong7 = beiyong7 == null ? null : beiyong7.trim();
    }

    public String getBeiyong8() {
        return beiyong8;
    }

    public void setBeiyong8(String beiyong8) {
        this.beiyong8 = beiyong8 == null ? null : beiyong8.trim();
    }

    public String getBeiyong9() {
        return beiyong9;
    }

    public void setBeiyong9(String beiyong9) {
        this.beiyong9 = beiyong9 == null ? null : beiyong9.trim();
    }
}