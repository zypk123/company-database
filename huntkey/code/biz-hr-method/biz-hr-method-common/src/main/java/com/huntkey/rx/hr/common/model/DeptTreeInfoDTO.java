package com.huntkey.rx.hr.common.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;

import java.util.Date;

/**
 * 部门信息类
 * @author Created by liuwens on 2017/11/15.
 */
public class DeptTreeInfoDTO
{
    @JSONField(name = NodeConstant.ID)
    private String id;

    /**
     * 部门编码
     */
    @JSONField(name = DeptTreeConstants.MDEP_CODE)
    private String mdepCode;

    /**
     * 部门类属性：名称
     */
    @JSONField(name = DeptTreeConstants.MDEP_NAME)
    private String mdepName;

    /**
     * 上级部门
     */
    @JSONField(name = DeptTreeConstants.MDEP_PAR)
    private String mdepPar;

    /**
     * 上级部门名称
     */
    @JSONField(name = DeptTreeConstants.MDEP_PAR_NAME)
    private String mdepParName;

    /**
     * 部门类属性：简称
     */
    @JSONField(name = DeptTreeConstants.MDEP_SNAME)
    private String mdepSname;

    /**
     * 部门类属性：全称
     */
    @JSONField(name = DeptTreeConstants.MDEP_LNAME)
    private String mdepLname;

    /**
     * 部门类属性：法人公司
     */
    @JSONField(name = DeptTreeConstants.MDEP_MCOP)
    private String mdepMcop;

    /**
     * 部门类属性：部门级别
     */
    @JSONField(name = DeptTreeConstants.MDEP_GRADE)
    private String mdepGrade;

    /**
     * 部门类属性：办公园区
     */
    @JSONField(name = DeptTreeConstants.MDEP_RPAK)
    private String mdepRpak;

    /**
     * 部门类属性：部门职责
     */
    @JSONField(name = DeptTreeConstants.MDEP_DUTY)
    private String mdepDuty;

    /**
     * 部门类属性：部门编制
     */
    @JSONField(name = DeptTreeConstants.MDEP_TL_NUM)
    private int mdepTlNum;

    /**
     * 部门类属性：下层编制
     */
    @JSONField(name = DeptTreeConstants.MDEP_LL_NUM)
    private int mdepLlNum;

    /**
     * 部门类属性：主责岗位
     */
    @JSONField(name = DeptTreeConstants.MDEP_LEADER_POST)
    private String mdepLeaderPost;

    /**
     * 部门类属性：负责人
     */
    @JSONField(name = DeptTreeConstants.MDEP_LEADER)
    private String mdepLeader;

    /**
     * 职员人数
     */
    @JSONField(name = DeptTreeConstants.MDEP_NUM_TOT)
    private String mdepNumTot;

    /**
     * 部门岗位数
     */
    private Integer mdepPostNum;

    /**
     * 部门在编数
     */
    private Integer mdepInJobPostNum;

    /**
     * 部门排序
     */
    @JSONField(name = DeptTreeConstants.MDEP_SEQ)
    private int mdepSeq;

    /**
     * 部门层级编码
     */
    @JSONField(name = DeptTreeConstants.MDEP_LVL_CODE)
    private String mdepLvlCode;

    /**
     * 生效日期
     */
    @JSONField(name = DeptTreeConstants.MDEP_BEG)
    private Date mdepBeg;

    /**
     * 失效日期
     */
    @JSONField(name = DeptTreeConstants.MDEP_END)
    private Date mdepEnd;


    public Integer getMdepInJobPostNum() {
        return mdepInJobPostNum;
    }

    public void setMdepInJobPostNum(Integer mdepInJobPostNum) {
        this.mdepInJobPostNum=mdepInJobPostNum;
    }

    public String getMdepParName() {
        return mdepParName;
    }

    public void setMdepParName(String mdepParName) {
        this.mdepParName=mdepParName;
    }

    public Integer getMdepPostNum() {
        return mdepPostNum;
    }

    public void setMdepPostNum(Integer mdepPostNum) {
        this.mdepPostNum=mdepPostNum;
    }

    public Date getMdepBeg() {
        return mdepBeg;
    }

    public void setMdepBeg(Date mdepBeg) {
        this.mdepBeg = mdepBeg;
    }

    public Date getMdepEnd() {
        return mdepEnd;
    }

    public void setMdepEnd(Date mdepEnd) {
        this.mdepEnd = mdepEnd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id=id;
    }

    public String getMdepCode() {
        return mdepCode;
    }

    public void setMdepCode(String mdepCode) {
        this.mdepCode=mdepCode;
    }

    public String getMdepName() {
        return mdepName;
    }

    public void setMdepName(String mdepName) {
        this.mdepName=mdepName;
    }

    public String getMdepPar() {
        return mdepPar;
    }

    public void setMdepPar(String mdepPar) {
        this.mdepPar=mdepPar;
    }

    public String getMdepSname() {
        return mdepSname;
    }

    public void setMdepSname(String mdepSname) {
        this.mdepSname=mdepSname;
    }

    public String getMdepMcop() {
        return mdepMcop;
    }

    public void setMdepMcop(String mdepMcop) {
        this.mdepMcop=mdepMcop;
    }

    public String getMdepGrade() {
        return mdepGrade;
    }

    public void setMdepGrade(String mdepGrade) {
        this.mdepGrade=mdepGrade;
    }

    public String getMdepRpak() {
        return mdepRpak;
    }

    public void setMdepRpak(String mdepRpak) {
        this.mdepRpak=mdepRpak;
    }

    public String getMdepDuty() {
        return mdepDuty;
    }

    public void setMdepDuty(String mdepDuty) {
        this.mdepDuty=mdepDuty;
    }

    public int getMdepTlNum() {
        return mdepTlNum;
    }

    public void setMdepTlNum(int mdepTlNum) {
        this.mdepTlNum = mdepTlNum;
    }

    public int getMdepLlNum() {
        return mdepLlNum;
    }

    public void setMdepLlNum(int mdepLlNum) {
        this.mdepLlNum = mdepLlNum;
    }

    public String getMdepLeaderPost() {
        return mdepLeaderPost;
    }

    public void setMdepLeaderPost(String mdepLeaderPost) {
        this.mdepLeaderPost=mdepLeaderPost;
    }

    public String getMdepLeader() {
        return mdepLeader;
    }

    public void setMdepLeader(String mdepLeader) {
        this.mdepLeader=mdepLeader;
    }

    public String getMdepNumTot() {
        return mdepNumTot;
    }

    public void setMdepNumTot(String mdepNumTot) {
        this.mdepNumTot=mdepNumTot;
    }

    public String getMdepLname() {
        return mdepLname;
    }

    public void setMdepLname(String mdepLname) {
        this.mdepLname = mdepLname;
    }

    public int getMdepSeq() {
        return mdepSeq;
    }

    public void setMdepSeq(int mdepSeq) {
        this.mdepSeq = mdepSeq;
    }

    public String getMdepLvlCode() {
        return mdepLvlCode;
    }

    public void setMdepLvlCode(String mdepLvlCode) {
        this.mdepLvlCode = mdepLvlCode;
    }
    
}
