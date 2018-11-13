package com.huntkey.rx.hr.common.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.annotation.JSONField;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;

import java.util.Date;
import java.util.List;

/**
 * Created by xuyf on 2017/11/14 0014.
 */
public class DeptTreeDTO implements Cloneable{

    @JSONField(name = NodeConstant.ID)
    private String changeSetId;

    @JSONField(name = NodeConstant.PID)
    private String id;

    private String mdepCode;

    @JSONField(name = MDepChagSetConstants.MDEP_NAME_HIS)
    private String mdepName;

    @JSONField(name = MDepChagSetConstants.MDEP_SNAME_HIS)
    private String mdepSname;

    @JSONField(name = MDepChagSetConstants.MDEP_PAR_HIS)
    private String mdepPar;

    private String mdepParName;

    @JSONField(name = MDepChagSetConstants.MDEP_LVL_HIS)
    private String mdepLvlCode;

    @JSONField(name = MDepChagSetConstants.MDEP_GRADE_HIS)
    private String mdepGrade;

    @JSONField(name = MDepChagSetConstants.MDEP_SEQ_HIS)
    private String mdepSeq;

    @JSONField(name = MDepChagSetConstants.MDEP_RPAK_HIS)
    private String mdepRpak;

    private String mdepRpakName;

    @JSONField(name = MDepChagSetConstants.MDEP_MCOP_HIS)
    private String mdepMcop;

    private String mdepMcopName;

    @JSONField(name = MDepChagSetConstants.MDEP_DUTY_HIS)
    private String mdepDuty;

    @JSONField(name = MDepChagSetConstants.MDEP_BEG_HIS)
    private Date mdepBeg;

    @JSONField(name = MDepChagSetConstants.MDEP_END_HIS)
    private Date mdepEnd;

    @JSONField(name = MDepChagSetConstants.MDEP_TNUM_HIS)
    private int mdepTlNum;

    private int mdepLlNum;

    private String mdepLeaderPost;

    @JSONField(name = MDepChagSetConstants.MDEP_LEADER)
    private String mdepLeaderPostName;

    private String mdepLeaderPosDutyType;

    private String mdepLeader;

    private String mdepLeaderName;

    private String mdepAssistant;

    private String mdepAssistantName;

    private int postNum;

    private int inPostNum;

    @JSONField(name = MDepChagSetConstants.MDEP_TNUM_HIS)
    private String mdepTnumHis;

    /**
     * 最晚失效日期
     */
    private Date mdepMaxEnd;

    private List<DeptTreeDTO> childDeptList;

    private String newChargeEmpCode;
    private String newChargePostName;
    private String newChargePostCode;
    private String newChargeDutyType;
    private String newChargeDeptCode;
    private String oldChargeEmpCode;
    private String oldChargeEmpName;
    private String newSubCooperateNames;
    private List<String> newSubCooperateCodes;
    private List<String> newSubCooperatePostCode;
    private List<String> newSubCooperateDutyType;
    private List<String> newSubCooperateDeptCode;
    private String newChargeEmpName;
    private List<String> newSubCooperateNos;
    private List<String> oldSubCooperateCodes;
    private String oldSubCooperateNames;
    private String isEdit;

    public String getChangeSetId() {
        return changeSetId;
    }

    public void setChangeSetId(String changeSetId) {
        this.changeSetId = changeSetId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMdepCode() {
        return mdepCode;
    }

    public void setMdepCode(String mdepCode) {
        this.mdepCode = mdepCode;
    }

    public String getMdepName() {
        return mdepName;
    }

    public void setMdepName(String mdepName) {
        this.mdepName = mdepName;
    }

    public String getMdepSname() {
        return mdepSname;
    }

    public void setMdepSname(String mdepSname) {
        this.mdepSname = mdepSname;
    }

    public String getMdepPar() {
        return mdepPar;
    }

    public void setMdepPar(String mdepPar) {
        this.mdepPar = mdepPar;
    }

    public String getMdepParName() {
        return mdepParName;
    }

    public void setMdepParName(String mdepParName) {
        this.mdepParName = mdepParName;
    }

    public String getMdepLvlCode() {
        return mdepLvlCode;
    }

    public void setMdepLvlCode(String mdepLvlCode) {
        this.mdepLvlCode = mdepLvlCode;
    }

    public String getMdepGrade() {
        return mdepGrade;
    }

    public void setMdepGrade(String mdepGrade) {
        this.mdepGrade = mdepGrade;
    }

    public String getMdepSeq() {
        return mdepSeq;
    }

    public void setMdepSeq(String mdepSeq) {
        this.mdepSeq = mdepSeq;
    }

    public String getMdepRpak() {
        return mdepRpak;
    }

    public void setMdepRpak(String mdepRpak) {
        this.mdepRpak = mdepRpak;
    }

    public String getMdepRpakName() {
        return mdepRpakName;
    }

    public void setMdepRpakName(String mdepRpakName) {
        this.mdepRpakName = mdepRpakName;
    }

    public String getMdepMcop() {
        return mdepMcop;
    }

    public void setMdepMcop(String mdepMcop) {
        this.mdepMcop = mdepMcop;
    }

    public String getMdepMcopName() {
        return mdepMcopName;
    }

    public void setMdepMcopName(String mdepMcopName) {
        this.mdepMcopName = mdepMcopName;
    }

    public String getMdepDuty() {
        return mdepDuty;
    }

    public void setMdepDuty(String mdepDuty) {
        this.mdepDuty = mdepDuty;
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
        this.mdepLeaderPost = mdepLeaderPost;
    }

    public String getMdepLeaderPostName() {
        return mdepLeaderPostName;
    }

    public void setMdepLeaderPostName(String mdepLeaderPostName) {
        this.mdepLeaderPostName = mdepLeaderPostName;
    }

    public String getMdepLeaderPosDutyType() {
        return mdepLeaderPosDutyType;
    }

    public void setMdepLeaderPosDutyType(String mdepLeaderPosDutyType) {
        this.mdepLeaderPosDutyType = mdepLeaderPosDutyType;
    }

    public String getMdepLeader() {
        return mdepLeader;
    }

    public void setMdepLeader(String mdepLeader) {
        this.mdepLeader = mdepLeader;
    }

    public String getMdepLeaderName() {
        return mdepLeaderName;
    }

    public void setMdepLeaderName(String mdepLeaderName) {
        this.mdepLeaderName = mdepLeaderName;
    }

    public String getMdepAssistant() {
        return mdepAssistant;
    }

    public void setMdepAssistant(String mdepAssistant) {
        this.mdepAssistant = mdepAssistant;
    }

    public String getMdepAssistantName() {
        return mdepAssistantName;
    }

    public void setMdepAssistantName(String mdepAssistantName) {
        this.mdepAssistantName = mdepAssistantName;
    }

    public int getPostNum() {
        return postNum;
    }

    public void setPostNum(int postNum) {
        this.postNum = postNum;
    }

    public int getInPostNum() {
        return inPostNum;
    }

    public void setInPostNum(int inPostNum) {
        this.inPostNum = inPostNum;
    }

    public String getMdepTnumHis() {
        return mdepTnumHis;
    }

    public void setMdepTnumHis(String mdepTnumHis) {
        this.mdepTnumHis = mdepTnumHis;
    }

    public Date getMdepMaxEnd() {
        return mdepMaxEnd;
    }

    public void setMdepMaxEnd(Date mdepMaxEnd) {
        this.mdepMaxEnd = mdepMaxEnd;
    }

    @Override
    public DeptTreeDTO clone() {
        try{
            return (DeptTreeDTO)super.clone();

        }catch(CloneNotSupportedException e){
            throw new RuntimeException("部门类克隆失败");
        }
    }


    public List<DeptTreeDTO> getChildDeptList() {
        return childDeptList;
    }

    public void setChildDeptList(List<DeptTreeDTO> childDeptList) {
        this.childDeptList = childDeptList;
    }

    public String getNewChargeEmpCode() {
        return newChargeEmpCode;
    }

    public void setNewChargeEmpCode(String newChargeEmpCode) {
        this.newChargeEmpCode = newChargeEmpCode;
    }

    public String getNewChargePostName() {
        return newChargePostName;
    }

    public void setNewChargePostName(String newChargePostName) {
        this.newChargePostName = newChargePostName;
    }

    public String getNewChargePostCode() {
        return newChargePostCode;
    }

    public void setNewChargePostCode(String newChargePostCode) {
        this.newChargePostCode = newChargePostCode;
    }

    public String getNewChargeDutyType() {
        return newChargeDutyType;
    }

    public void setNewChargeDutyType(String newChargeDutyType) {
        this.newChargeDutyType = newChargeDutyType;
    }

    public String getNewChargeDeptCode() {
        return newChargeDeptCode;
    }

    public void setNewChargeDeptCode(String newChargeDeptCode) {
        this.newChargeDeptCode = newChargeDeptCode;
    }

    public String getNewSubCooperateNames() {
        return newSubCooperateNames;
    }

    public void setNewSubCooperateNames(String newSubCooperateNames) {
        this.newSubCooperateNames = newSubCooperateNames;
    }

    public List<String> getNewSubCooperateCodes() {
        return newSubCooperateCodes;
    }

    public void setNewSubCooperateCodes(List<String> newSubCooperateCodes) {
        this.newSubCooperateCodes = newSubCooperateCodes;
    }

    public List<String> getNewSubCooperatePostCode() {
        return newSubCooperatePostCode;
    }

    public void setNewSubCooperatePostCode(List<String> newSubCooperatePostCode) {
        this.newSubCooperatePostCode = newSubCooperatePostCode;
    }

    public List<String> getNewSubCooperateDutyType() {
        return newSubCooperateDutyType;
    }

    public void setNewSubCooperateDutyType(List<String> newSubCooperateDutyType) {
        this.newSubCooperateDutyType = newSubCooperateDutyType;
    }

    public List<String> getNewSubCooperateDeptCode() {
        return newSubCooperateDeptCode;
    }

    public void setNewSubCooperateDeptCode(List<String> newSubCooperateDeptCode) {
        this.newSubCooperateDeptCode = newSubCooperateDeptCode;
    }

    public String getNewChargeEmpName() {
        return newChargeEmpName;
    }

    public void setNewChargeEmpName(String newChargeEmpName) {
        this.newChargeEmpName = newChargeEmpName;
    }

    public List<String> getNewSubCooperateNos() {
        return newSubCooperateNos;
    }

    public void setNewSubCooperateNos(List<String> newSubCooperateNos) {
        this.newSubCooperateNos = newSubCooperateNos;
    }

    public String getOldChargeEmpCode() {
        return oldChargeEmpCode;
    }

    public void setOldChargeEmpCode(String oldChargeEmpCode) {
        this.oldChargeEmpCode = oldChargeEmpCode;
    }

    public String getOldChargeEmpName() {
        return oldChargeEmpName;
    }

    public void setOldChargeEmpName(String oldChargeEmpName) {
        this.oldChargeEmpName = oldChargeEmpName;
    }

    public List<String> getOldSubCooperateCodes() {
        return oldSubCooperateCodes;
    }

    public void setOldSubCooperateCodes(List<String> oldSubCooperateCodes) {
        this.oldSubCooperateCodes = oldSubCooperateCodes;
    }

    public String getOldSubCooperateNames() {
        return oldSubCooperateNames;
    }

    public void setOldSubCooperateNames(String oldSubCooperateNames) {
        this.oldSubCooperateNames = oldSubCooperateNames;
    }

    public String getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(String isEdit) {
        this.isEdit = isEdit;
    }
}
