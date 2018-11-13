package com.zhang.lambda.demo;

/**
 * @author zhangyu
 * @create 2018-01-18 14:57
 **/
public class Demo2 {

    public static void main(String[] args) {

        // lambda代替匿名内部类

        // 1. 使用匿名内部类
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world !");
            }
        }).start();


        // 2. 使用 lambda 表达式
        new Thread(() -> System.out.println("hello lambda ！")).start();


        // 3. 使用匿名内部类
        Runnable run1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world !");
            }
        };

        // 4. 使用 lambda 表达式
        Runnable run2 = () -> System.out.println("hello lambda ！");

        run1.run();
        run2.run();


    }
}
