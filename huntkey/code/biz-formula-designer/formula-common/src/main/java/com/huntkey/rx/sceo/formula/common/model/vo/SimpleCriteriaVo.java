package com.huntkey.rx.sceo.formula.common.model.vo;

/**
 * ORM的richfind接口入参
 *
 * @author zhangyu
 * @create 2017-11-10 13:56
 **/
public class SimpleCriteriaVo {

    String edmName;
    SimpleSearchVo search;

    public String getEdmName() {
        return edmName;
    }

    public void setEdmName(String edmName) {
        this.edmName = edmName;
    }

    public SimpleSearchVo getSearch() {
        return search;
    }

    public void setSearch(SimpleSearchVo search) {
        this.search = search;
    }
}
