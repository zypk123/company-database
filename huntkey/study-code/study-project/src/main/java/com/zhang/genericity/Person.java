package com.zhang.genericity;

/**
 * @author zhangyu
 * @create 2017-12-18 17:34
 **/
public class Person {

    protected String name;

    Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "person:" + this.name;
    }

    public void personMethod() {
        System.out.println("--------personMethod-----");
    }

}
