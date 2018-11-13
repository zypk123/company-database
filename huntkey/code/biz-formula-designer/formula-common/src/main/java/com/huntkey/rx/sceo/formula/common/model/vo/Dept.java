package com.huntkey.rx.sceo.formula.common.model.vo;

/**
 * @author zhouyou on 2017/11/8.
 */
public class Dept {
    private String formulaId;
    private String mdepLeader;
    private String deptTreeId;

    public String getFormulaId() {
        return formulaId;
    }

    public void setFormulaId(String formulaId) {
        this.formulaId = formulaId;
    }

    public String getMdepLeader() {
        return mdepLeader;
    }

    public void setMdepLeader(String mdepLeader) {
        this.mdepLeader = mdepLeader;
    }

    public String getDeptTreeId() {
        return deptTreeId;
    }

    public void setDeptTreeId(String deptTreeId) {
        this.deptTreeId = deptTreeId;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "formulaId='" + formulaId + '\'' +
                ", mdepLeader='" + mdepLeader + '\'' +
                ", deptTreeId='" + deptTreeId + '\'' +
                '}';
    }
}
