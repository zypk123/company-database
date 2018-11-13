package com.zhang.genericity;

/**
 * @author zhangyu
 * @create 2017-12-18 17:35
 **/
public class Student extends Person {

    Student(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "student:" + this.name;
    }

    public void studentMethod() {
        System.out.println("--------studentMethod-----");
    }

}
