package com.zhang.genericity;

/**
 * 泛型类
 *
 * @author zhangyu
 * @create 2017-12-19 9:55
 **/
public class Box<T> {

    private T data;

    public Box() {

    }

    public Box(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
