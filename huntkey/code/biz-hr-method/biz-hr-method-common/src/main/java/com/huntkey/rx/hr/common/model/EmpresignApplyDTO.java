package com.huntkey.rx.hr.common.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 离职申请单DTO
 *
 * @author zhangyu
 * @create 2017-11-17 9:29
 **/
public class EmpresignApplyDTO extends OrderDTO {

    /**
     * 离职申请单对象id
     */
    @JSONField(name = "id")
    private String id;

    /**
     * pid
     */
    @JSONField(name = EmpresignApplyConstants.PID)
    private String pid;

    /**
     * 离职人对象Id
     */
    @JSONField(name = EmpresignApplyConstants.OERA_EMP)
    private String oeraEmp;

    /**
     * 姓名
     */
    @JSONField(name = EmpresignApplyConstants.REMP_NAME)
    private String rempName;

    /**
     * 工号
     */
    @JSONField(name = EmpresignApplyConstants.REMP_NO)
    private String rempNo;

    /**
     * 申请离职日期
     */
    @JSONField(name = EmpresignApplyConstants.CRETIME)
    private String creTime;

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
     * 性别
     */
    @JSONField(name = EmpresignApplyConstants.REMP_GENDER)
    private String rempGender;

    /**
     * 出生日期 // TODO 为了计算年龄
     */
    @JSONField(name = EmpresignApplyConstants.REMP_BIRTH)
    private String rempBirth;

    /**
     * 年龄
     */
    @JSONField(name = EmpresignApplyConstants.REMP_AGE)
    private String rempAge;

    /**
     * 工龄
     */
    @JSONField(name = EmpresignApplyConstants.REMP_WORK_AGE)
    private String rempWorkAge;

    /**
     * 岗级
     */
    @JSONField(name = EmpresignApplyConstants.OERA_PGRADE)
    private String oeraPgrade;

    /**
     * 首次工作时间 // TODO 这里是为了计算工龄(年)
     */
    @JSONField(name = EmpresignApplyConstants.REMP_WORK_DATE)
    private String rempWorkDate;

    /**
     * 到职日 // TODO 这里是为了计算在职(月)
     */
    @JSONField(name = EmpresignApplyConstants.REMP_IN_DATE)
    private String rempInDate;

    /**
     * 离职日 // TODO 这里是为了计算在职(月)
     */
    @JSONField(name = EmpresignApplyConstants.REMP_LEA_DATE)
    private String rempLeaDate;

    /**
     * 在职(月)
     */
    @JSONField(name = EmpresignApplyConstants.REMP_IN_WORK)
    private String rempInWork;

    /**
     * 在岗(月)
     */
    @JSONField(name = EmpresignApplyConstants.REMP_IN_JOB)
    private String rempInJob;

    /**
     * 部门id
     */
    @JSONField(name = EmpresignApplyConstants.OERA_DEPT)
    private String oeraDept;

    /**
     * 上级部门
     */
    @JSONField(name = EmpresignApplyConstants.MDEP_PAR)
    private String mdepPar;

    /**
     * 部门名称
     */
    @JSONField(name = EmpresignApplyConstants.MDEP_NAME)
    private String mdepName;

    /**
     * 岗位
     */
    @JSONField(name = EmpresignApplyConstants.RPOS_NAME)
    private String rposName;

    /**
     * 直属上级
     */
    @JSONField(name = EmpresignApplyConstants.MDEP_LEADER)
    private String mdepLeader;

    /**
     * 直属上级名称
     */
    @JSONField(name = EmpresignApplyConstants.MDEP_LEADER_NAME)
    private String mdepLeaderName;

    /**
     * 备注
     */
    @JSONField(name = EmpresignApplyConstants.OERA_REMARK)
    private String oeraRemark;

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
     * 离职申请数
     */
    @JSONField(name = EmpresignApplyConstants.OERA_NUM_AP)
    private Integer oeraNumAp;


    /**
     * 离职原因
     */
    @JSONField(name = EmpresignApplyConstants.OERA_RESON)
    private String oeraReson;


    /**
     * 岗级
     */
    @JSONField(name = EmpresignApplyConstants.REMP_PGRADE)
    private String rempPgrade;

    /**
     * 毕业院校对象Id
     */
    @JSONField(name = EmployeeConstants.REMP_RSCH)
    private String rempRsch;

    /**
     * 教育背景：学历
     */
    @JSONField(name = EmployeeConstants.REMP_DEGREE)
    private String rempDegree;

    /**
     * 专业
     */
    @JSONField(name = EmployeeConstants.REMP_MAJOR)
    private String rempMajor;

    /**
     * 毕业院校名称
     */
    @JSONField(name = SchoolConstants.RSCH_NAME)
    private String rschName;

    /**
     * 离职明细实体类
     */
    private OeraEmpSet oeraEmpSet;

    // ............................ 待完善


    public String getRempDegree() {
        return rempDegree;
    }

    public void setRempDegree(String rempDegree) {
        this.rempDegree=rempDegree;
    }

    public String getRempBirth() {
        return rempBirth;
    }

    public void setRempBirth(String rempBirth) {
        this.rempBirth=rempBirth;
    }

    public OeraEmpSet getOeraEmpSet() {
        return oeraEmpSet;
    }

    public void setOeraEmpSet(OeraEmpSet oeraEmpSet) {
        this.oeraEmpSet=oeraEmpSet;
    }

    public Integer getOeraNumAp() {
        return oeraNumAp;
    }

    public void setOeraNumAp(Integer oeraNumAp) {
        this.oeraNumAp=oeraNumAp;
    }

    public String getOeraAuditDesc() {
        return oeraAuditDesc;
    }

    public void setOeraAuditDesc(String oeraAuditDesc) {
        this.oeraAuditDesc=oeraAuditDesc;
    }

    public String getOeraEmp() {
        return oeraEmp;
    }

    public void setOeraEmp(String oeraEmp) {
        this.oeraEmp=oeraEmp;
    }

    public String getMdepLeaderName() {
        return mdepLeaderName;
    }

    public void setMdepLeaderName(String mdepLeaderName) {
        this.mdepLeaderName=mdepLeaderName;
    }

    public String getRempName() {
        return rempName;
    }

    public void setRempName(String rempName) {
        this.rempName = rempName;
    }

    public String getRempNo() {
        return rempNo;
    }

    public void setRempNo(String rempNo) {
        this.rempNo = rempNo;
    }

    public String getOeraApprDate() {
        return oeraApprDate;
    }

    public void setOeraApprDate(String oeraApprDate) {
        this.oeraApprDate = oeraApprDate;
    }

    public String getOeraRealDate() {
        return oeraRealDate;
    }

    public void setOeraRealDate(String oeraRealDate) {
        this.oeraRealDate = oeraRealDate;
    }

    public String getRempGender() {
        return rempGender;
    }

    public void setRempGender(String rempGender) {
        this.rempGender = rempGender;
    }


    public String getOeraPgrade() {
        return oeraPgrade;
    }

    public void setOeraPgrade(String oeraPgrade) {
        this.oeraPgrade = oeraPgrade;
    }

    public String getRempWorkDate() {
        return rempWorkDate;
    }

    public void setRempWorkDate(String rempWorkDate) {
        this.rempWorkDate = rempWorkDate;
    }

    public String getRempInDate() {
        return rempInDate;
    }

    public void setRempInDate(String rempInDate) {
        this.rempInDate = rempInDate;
    }

    public String getRempLeaDate() {
        return rempLeaDate;
    }

    public void setRempLeaDate(String rempLeaDate) {
        this.rempLeaDate = rempLeaDate;
    }

    public String getMdepPar() {
        return mdepPar;
    }

    public void setMdepPar(String mdepPar) {
        this.mdepPar = mdepPar;
    }

    public String getMdepName() {
        return mdepName;
    }

    public void setMdepName(String mdepName) {
        this.mdepName = mdepName;
    }

    public String getRposName() {
        return rposName;
    }

    public void setRposName(String rposName) {
        this.rposName = rposName;
    }

    public String getOeraRemark() {
        return oeraRemark;
    }

    public void setOeraRemark(String oeraRemark) {
        this.oeraRemark = oeraRemark;
    }

    public String getOeraType() {
        return oeraType;
    }

    public void setOeraType(String oeraType) {
        this.oeraType = oeraType;
    }

    public String getOeraReson() {
        return oeraReson;
    }

    public void setOeraReson(String oeraReson) {
        this.oeraReson = oeraReson;
    }

    public String getRempPgrade() {
        return rempPgrade;
    }

    public void setRempPgrade(String rempPgrade) {
        this.rempPgrade = rempPgrade;
    }

    public String getRempRsch() {
        return rempRsch;
    }

    public void setRempRsch(String rempRsch) {
        this.rempRsch = rempRsch;
    }

    public String getRempMajor() {
        return rempMajor;
    }

    public void setRempMajor(String rempMajor) {
        this.rempMajor = rempMajor;
    }

    public String getRschName() {
        return rschName;
    }

    public void setRschName(String rschName) {
        this.rschName = rschName;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCreTime() {
        return creTime;
    }

    public void setCreTime(String creTime) {
        this.creTime = creTime;
    }

    public String getOeraAppDate() {
        return oeraAppDate;
    }

    public void setOeraAppDate(String oeraAppDate) {
        this.oeraAppDate = oeraAppDate;
    }

    public String getRempAge() {
        return rempAge;
    }

    public void setRempAge(String rempAge) {
        this.rempAge = rempAge;
    }

    public String getRempWorkAge() {
        return rempWorkAge;
    }

    public void setRempWorkAge(String rempWorkAge) {
        this.rempWorkAge = rempWorkAge;
    }

    public String getRempInWork() {
        return rempInWork;
    }

    public void setRempInWork(String rempInWork) {
        this.rempInWork = rempInWork;
    }

    public String getRempInJob() {
        return rempInJob;
    }

    public void setRempInJob(String rempInJob) {
        this.rempInJob = rempInJob;
    }


    public String getMdepLeader() {
        return mdepLeader;
    }

    public void setMdepLeader(String mdepLeader) {
        this.mdepLeader = mdepLeader;
    }

    public String getOeraDept() {
        return oeraDept;
    }

    public void setOeraDept(String oeraDept) {
        this.oeraDept = oeraDept;
    }

    @Override
    public String toString() {
        return "EmpresignApplyDTO{" +
                ", id='" + id + '\'' +
                ", pid='" + pid + '\'' +
                ", rempName='" + rempName + '\'' +
                ", rempNo='" + rempNo + '\'' +
                ", creTime='" + creTime + '\'' +
                ", oeraAppDate='" + oeraAppDate + '\'' +
                ", oeraApprDate='" + oeraApprDate + '\'' +
                ", oeraRealDate='" + oeraRealDate + '\'' +
                ", rempGender='" + rempGender + '\'' +
                ", rempBitrh='" + rempBirth + '\'' +
                ", rempAge='" + rempAge + '\'' +
                ", rempWorkAge='" + rempWorkAge + '\'' +
                ", oeraPgrade='" + oeraPgrade + '\'' +
                ", rempWorkDate='" + rempWorkDate + '\'' +
                ", rempInDate='" + rempInDate + '\'' +
                ", rempLeaDate='" + rempLeaDate + '\'' +
                ", rempInWork='" + rempInWork + '\'' +
                ", rempInJob='" + rempInJob + '\'' +
                ", oeraDept='" + oeraDept + '\'' +
                ", mdepPar='" + mdepPar + '\'' +
                ", mdepName='" + mdepName + '\'' +
                ", rposName='" + rposName + '\'' +
                ", mdepLeader='" + mdepLeader + '\'' +
                ", oeraRemark='" + oeraRemark + '\'' +
                ", oeraType='" + oeraType + '\'' +
                ", oeraReson='" + oeraReson + '\'' +
                ", rempPgrade='" + rempPgrade + '\'' +
                ", rempRsch='" + rempRsch + '\'' +
                ", rempMajor='" + rempMajor + '\'' +
                ", rschName='" + rschName + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
