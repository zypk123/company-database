package com.zhang.entity;

import java.io.Serializable;

/**
 * 产品类
 *
 * @author zhangyu
 * @create 2017-07-20 13:39
 **/
public class Product implements Serializable {

    private static final long serialVersionUID = 1435515995276255188L;
    private long id; // 产品ID
    private String name; // 产品名称
    private long price; // 产品价格

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
