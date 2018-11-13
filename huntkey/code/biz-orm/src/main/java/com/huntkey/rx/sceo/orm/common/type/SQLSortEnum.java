package com.huntkey.rx.sceo.orm.common.type;

/**
 * Created by linziy on 2017/11/18.
 */
public enum SQLSortEnum {
    DESC(1, "DESC", "降序"),
    ASC(2, "ASC", "升序");

    private int num;
    private String sortType;
    private String desc;


    private SQLSortEnum(int num, String sortType, String desc) {
        this.num = num;
        this.sortType = sortType;
        this.desc = desc;
    }

    public int getNum() {
        return num;
    }

    public String getDesc() {
        return desc;
    }

    public String getSortType() {
        return this.sortType;
    }

    public String getSortTypeWithSpace() {
        return " " + this.sortType + " ";
    }

}
