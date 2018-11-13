package com.huntkey.rx.sceo.formula.common.model.vo;

import java.util.List;
/**
 * @author chenfei on 2017/5/15.
 */
public class SearchObject {
    private List<String> columns;
    private List<Condition> conditions;

    public SearchObject() {
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }
}
