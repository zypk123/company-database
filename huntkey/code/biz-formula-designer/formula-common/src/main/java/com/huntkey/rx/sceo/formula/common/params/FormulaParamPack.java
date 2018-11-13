package com.huntkey.rx.sceo.formula.common.params;

import java.util.List;

/**
 * This entity that packaged formula-content and formula parameters
 * for HTTP request of formula pre-calc.
 *
 * @author chenfei on 2017/7/5.
 */
public class FormulaParamPack {

    private String formulaId;

    /**
     * formula expression.
     */
    private String formulaStr;

    private List<FormulaItem> formulaItems;

    /**
     * list of formula item value.
     */
    private List<FormulaItemValue> formulaItemsValues;

    public String getFormulaId() {
        return formulaId;
    }

    public void setFormulaId(String formulaId) {
        this.formulaId = formulaId;
    }

    public String getFormulaStr() {
        return formulaStr;
    }

    public void setFormulaStr(String formulaStr) {
        this.formulaStr = formulaStr;
    }

    public List<FormulaItem> getFormulaItems() {
        return formulaItems;
    }

    public void setFormulaItems(List<FormulaItem> formulaItems) {
        this.formulaItems = formulaItems;
    }

    public List<FormulaItemValue> getFormulaItemsValues() {
        return formulaItemsValues;
    }

    public void setFormulaItemsValues(List<FormulaItemValue> formulaItemsValues) {
        this.formulaItemsValues = formulaItemsValues;
    }

    @Override
    public String toString() {
        return "FormulaParamPack{" +
                "formulaId='" + formulaId + '\'' +
                ", formulaStr='" + formulaStr + '\'' +
                ", formulaItems=" + formulaItems +
                ", formulaItemsValues=" + formulaItemsValues +
                '}';
    }
}
