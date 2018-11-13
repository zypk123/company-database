package com.huntkey.rx.sceo.formula.provider.data.entity;

/**
 * @author chenfei on 2017/8/1.
 */
public class DataSharingCenterParam {

    private String edmName;
    private DataSharingCenterSearch search = new DataSharingCenterSearch();

    public String getEdmName() {
        return edmName;
    }

    public void setSearch(DataSharingCenterSearch search) {
        this.search = search;
    }

    public void setEdmName(String edmName) {
        this.edmName = edmName;
    }

    public DataSharingCenterSearch getSearch() {
        return search;
    }

    public DataSharingCenterParam buildCondition2Add(String attr, String operator, String value) {

        DataSharingCenterCondition condition = new DataSharingCenterCondition(attr, operator, value);
        getSearch().addCondtion(condition);
        return this;
    }
}
