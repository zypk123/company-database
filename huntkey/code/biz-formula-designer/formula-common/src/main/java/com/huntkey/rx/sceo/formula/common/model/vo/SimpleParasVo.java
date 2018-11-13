package com.huntkey.rx.sceo.formula.common.model.vo;

/**
 * @author lulx on 2017/7/15 0015 下午 8:10
 */
public class SimpleParasVo {
    public String getPropId() {
        return propId;
    }

    public void setPropId(String propId) {
        this.propId = propId;
    }

    public String getVarId() {
        return varId;
    }

    public void setVarId(String varId) {
        this.varId = varId;
    }

    private String propId;
    private String varId;
    private String id;
    private String edmpFormula;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEdmpFormula() {
        return edmpFormula;
    }

    public void setEdmpFormula(String edmpFormula) {
        this.edmpFormula = edmpFormula;
    }

    public SimpleParasVo(String id, String edmpFormula) {
        this.id = id;
        this.edmpFormula = edmpFormula;
    }

    public SimpleParasVo() {
    }
}
