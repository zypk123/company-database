package com.huntkey.rx.hr.common.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;

/**
 * Created by xuyf on 2017/11/15 0015.
 */
public class OdscChagSetDTO implements Comparable<OdscChagSetDTO>{

    @JSONField(name = NodeConstant.ID)
    private String id;

    @JSONField(name = NodeConstant.PID)
    private String pid;

    @JSONField(name = OdscChagSetConstants.ODSC_FLAG)
    private String odscFlag;

    @JSONField(name = OdscChagSetConstants.ODSC_DEPT_CODE)
    private String odscDeptCode;

    @JSONField(name = OdscChagSetConstants.ODSC_DEPT_ID)
    private String odscDeptId;

    @JSONField(name = OdscChagSetConstants.ODSC_PDEPT)
    private String odscPdept;

    @JSONField(name = OdscChagSetConstants.ODSC_PDEPT_NAME)
    private String odscPdeptName;

    @JSONField(name = OdscChagSetConstants.ODSC_LVL)
    private String odscLvl;

    @JSONField(name = OdscChagSetConstants.ODSC_NAME)
    private String odscName;

    @JSONField(name = OdscChagSetConstants.ODSC_SNAME)
    private String odscSname;

    @JSONField(name = OdscChagSetConstants.ODSC_DGRADE)
    private String odscDgrade;

    @JSONField(name = OdscChagSetConstants.ODSC_RPAK)
    private String odscRpak;

    /**
     * 办公园区名称
     */
    @JSONField(serialize = false)
    private String odscRpakName;

    @JSONField(name = OdscChagSetConstants.ODSC_MCOP)
    private String odscMcop;

    /**
     * 法人公司名称
     */
    @JSONField(serialize = false)
    private String odscMcopName;

    @JSONField(name = OdscChagSetConstants.ODSC_FUNCTION)
    private String odscFunction;

    @JSONField(name = OdscChagSetConstants.ODSC_TL_PNUM)
    private int odscTlPnum;

    @JSONField(name = OdscChagSetConstants.ODSC_LL_PNUM)
    private int odscLlPnum;

    /**
     * 部门岗位数
     */
    private Integer mdepPostNum;

    /**
     * 部门在编数
     */
    private Integer mdepInJobPostNum;

    @JSONField(name = OdscChagSetConstants.ODSC_PDEPT_OLD)
    private String odscPdeptOld;

    @JSONField(name = OdscChagSetConstants.ODSC_PDEPT_NAME_OLD)
    private String odscPdeptNameOld;

    @JSONField(name = OdscChagSetConstants.ODSC_NAME_OLD)
    private String odscNameOld;

    @JSONField(name = OdscChagSetConstants.ODSC_SNAME_OLD)
    private String odscSnameOld;

    @JSONField(name = OdscChagSetConstants.ODSC_DGRADE_OLD)
    private String odscDgradeOld;

    @JSONField(name = OdscChagSetConstants.ODSC_RPAK_OLD)
    private String odscRpakOld;

    @JSONField(name = OdscChagSetConstants.ODSC_MCOP_OLD)
    private String odscMcopOld;

    @JSONField(name = OdscChagSetConstants.ODSC_FUNC_OLD)
    private String odscFuncOld;

    @JSONField(name = OdscChagSetConstants.ODSC_TL_OLD)
    private int odscTlOld;

    @JSONField(name = OdscChagSetConstants.ODSC_LL_OLD)
    private int odscLlOld;


    public Integer getMdepInJobPostNum() {
        return mdepInJobPostNum;
    }

    public void setMdepInJobPostNum(Integer mdepInJobPostNum) {
        this.mdepInJobPostNum=mdepInJobPostNum;
    }

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

    public String getOdscFlag() {
        return odscFlag;
    }

    public void setOdscFlag(String odscFlag) {
        this.odscFlag = odscFlag;
    }

    public String getOdscDeptCode() {
        return odscDeptCode;
    }

    public void setOdscDeptCode(String odscDeptCode) {
        this.odscDeptCode = odscDeptCode;
    }

    public String getOdscPdept() {
        return odscPdept;
    }

    public void setOdscPdept(String odscPdept) {
        this.odscPdept = odscPdept;
    }

    public String getOdscLvl() {
        return odscLvl;
    }

    public void setOdscLvl(String odscLvl) {
        this.odscLvl = odscLvl;
    }

    public String getOdscName() {
        return odscName;
    }

    public void setOdscName(String odscName) {
        this.odscName = odscName;
    }

    public String getOdscSname() {
        return odscSname;
    }

    public void setOdscSname(String odscSname) {
        this.odscSname = odscSname;
    }

    public String getOdscDgrade() {
        return odscDgrade;
    }

    public void setOdscDgrade(String odscDgrade) {
        this.odscDgrade = odscDgrade;
    }

    public String getOdscRpak() {
        return odscRpak;
    }

    public void setOdscRpak(String odscRpak) {
        this.odscRpak = odscRpak;
    }

    public String getOdscRpakName() {
        return odscRpakName;
    }

    public void setOdscRpakName(String odscRpakName) {
        this.odscRpakName = odscRpakName;
    }

    public String getOdscMcop() {
        return odscMcop;
    }

    public void setOdscMcop(String odscMcop) {
        this.odscMcop = odscMcop;
    }

    public String getOdscMcopName() {
        return odscMcopName;
    }

    public void setOdscMcopName(String odscMcopName) {
        this.odscMcopName = odscMcopName;
    }

    public String getOdscFunction() {
        return odscFunction;
    }

    public void setOdscFunction(String odscFunction) {
        this.odscFunction = odscFunction;
    }

    public int getOdscTlPnum() {
        return odscTlPnum;
    }

    public void setOdscTlPnum(int odscTlPnum) {
        this.odscTlPnum = odscTlPnum;
    }

    public int getOdscLlPnum() {
        return odscLlPnum;
    }

    public void setOdscLlPnum(int odscLlPnum) {
        this.odscLlPnum = odscLlPnum;
    }

    public String getOdscPdeptOld() {
        return odscPdeptOld;
    }

    public void setOdscPdeptOld(String odscPdeptOld) {
        this.odscPdeptOld = odscPdeptOld;
    }

    public String getOdscNameOld() {
        return odscNameOld;
    }

    public void setOdscNameOld(String odscNameOld) {
        this.odscNameOld = odscNameOld;
    }

    public String getOdscSnameOld() {
        return odscSnameOld;
    }

    public void setOdscSnameOld(String odscSnameOld) {
        this.odscSnameOld = odscSnameOld;
    }

    public String getOdscDgradeOld() {
        return odscDgradeOld;
    }

    public void setOdscDgradeOld(String odscDgradeOld) {
        this.odscDgradeOld = odscDgradeOld;
    }

    public String getOdscRpakOld() {
        return odscRpakOld;
    }

    public void setOdscRpakOld(String odscRpakOld) {
        this.odscRpakOld = odscRpakOld;
    }

    public String getOdscMcopOld() {
        return odscMcopOld;
    }

    public void setOdscMcopOld(String odscMcopOld) {
        this.odscMcopOld = odscMcopOld;
    }

    public String getOdscFuncOld() {
        return odscFuncOld;
    }

    public void setOdscFuncOld(String odscFuncOld) {
        this.odscFuncOld = odscFuncOld;
    }

    public int getOdscTlOld() {
        return odscTlOld;
    }

    public void setOdscTlOld(int odscTlOld) {
        this.odscTlOld = odscTlOld;
    }

    public int getOdscLlOld() {
        return odscLlOld;
    }

    public void setOdscLlOld(int odscLlOld) {
        this.odscLlOld = odscLlOld;
    }

    public Integer getMdepPostNum() {
        return mdepPostNum;
    }

    public void setMdepPostNum(Integer mdepPostNum) {
        this.mdepPostNum=mdepPostNum;
    }

    @Override
    public int compareTo(OdscChagSetDTO o) {
        return this.odscLvl.compareTo(o.odscLvl);
    }


    public String getOdscPdeptName() {
        return odscPdeptName;
    }

    public void setOdscPdeptName(String odscPdeptName) {
        this.odscPdeptName=odscPdeptName;
    }

    public String getOdscDeptId() {
        return odscDeptId;
    }

    public void setOdscDeptId(String odscDeptId) {
        this.odscDeptId = odscDeptId;
    }

    public String getOdscPdeptNameOld() {
        return odscPdeptNameOld;
    }

    public void setOdscPdeptNameOld(String odscPdeptNameOld) {
        this.odscPdeptNameOld = odscPdeptNameOld;
    }
}
