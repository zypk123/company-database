package com.huntkey.rx.sceo.formula.common.model.vo;

/**
 * @author zhouyou on 2017/10/18.
 */
public class Pagenation {
    private String startPage;
    private String rows;

    public Pagenation() {
    }

    public String getStartPage() {
        return startPage;
    }

    public void setStartPage(String startPage) {
        this.startPage = startPage;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "Pagenation{" +
                "startPage='" + startPage + '\'' +
                ", rows='" + rows + '\'' +
                '}';
    }
}
