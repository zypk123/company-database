package com.huntkey.rx.sceo.formula.common.model.vo;

/**
 * @author chenfei on 2017/5/15.
 */
public class ParamObject{
    private String edmName;
    private SearchObject search;

    public ParamObject() {
    }

    public String getEdmName() {
        return edmName;
    }

    public void setEdmName(String edmName) {
        this.edmName = edmName;
    }

    public SearchObject getSearch() {
        return search;
    }

    public void setSearch(SearchObject search) {
        this.search = search;
    }
}
