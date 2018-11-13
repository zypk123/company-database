package com.demo.genericity;

/**
 * @author zhangyu
 * @create 2017-07-19 16:55
 **/
public class Human {

    private String name;
    private int age;

    public Human(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void say() {
        System.out.println("-------人可以说话--------");
    }
}
