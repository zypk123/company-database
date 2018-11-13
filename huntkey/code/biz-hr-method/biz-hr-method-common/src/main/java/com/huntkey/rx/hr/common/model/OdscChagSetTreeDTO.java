package com.huntkey.rx.hr.common.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xuyf on 2017/11/15 0015.
 */
public class OdscChagSetTreeDTO implements Comparable<OdscChagSetTreeDTO> {

    @JSONField(name = NodeConstant.ID)
    private String id;

    @JSONField(name = NodeConstant.PID)
    private String pid;

    @JSONField(name = OdscChagSetConstants.ODSC_FLAG)
    private String odscFlag;

    @JSONField(name = OdscChagSetConstants.ODSC_DEPT_CODE)
    private String odscDeptCode;

    @JSONField(name = OdscChagSetConstants.ODSC_PDEPT)
    private String odscPdept;

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

    private List<OdscChagSetTreeDTO> childList = new ArrayList<>();

    private Map<String, OdscChagSetTreeDTO> childMap = new LinkedHashMap<>();

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

    public List<OdscChagSetTreeDTO> getChildList() {
        return childList;
    }

    public void setChildList(List<OdscChagSetTreeDTO> childList) {
        this.childList = childList;
    }

    public Map<String, OdscChagSetTreeDTO> getChildMap() {
        return childMap;
    }

    public void setChildMap(Map<String, OdscChagSetTreeDTO> childMap) {
        this.childMap = childMap;
    }

    @Override
    public int compareTo(OdscChagSetTreeDTO o) {
        if (StringUtil.isNullOrEmpty(this.odscLvl)){
            return 1;
        }else{
            return this.odscLvl.compareTo(o.odscLvl);
        }
    }
}
