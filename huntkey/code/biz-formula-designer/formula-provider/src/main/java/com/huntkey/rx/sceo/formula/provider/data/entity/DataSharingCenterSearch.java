package com.huntkey.rx.sceo.formula.provider.data.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenfei on 2017/8/1.
 */
public class DataSharingCenterSearch {

    private List<DataSharingCenterCondition> conditions = new ArrayList<DataSharingCenterCondition>();
    private String type;
    private List<String> ids = new ArrayList<String>();

    public List<DataSharingCenterCondition> getConditions() {
        return conditions;
    }

    public DataSharingCenterSearch addCondtion(DataSharingCenterCondition condition) {
        this.getConditions().add(condition);
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getIds() {
        return ids;
    }

    public DataSharingCenterSearch addId(String id) {
        this.ids.add(id);
        return this;
    }
}
