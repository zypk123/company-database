package com.demo.genericity;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型学习1
 *
 * @author zhangyu
 * @create 2017-07-19 16:38
 **/
public class TestGenericity1 {
    public static void main(String[] args) {
        List<Integer> a = new ArrayList<Integer>();
        List<String> b = new ArrayList<String>();

        System.out.println(a.getClass() == b.getClass()); // 结果true

        /******************************************解释******************************************************/
        /**
         * 反编译后的代码：
         * List a = new ArrayList();
         List b = new ArrayList();

         * 这里就是java的泛型擦除,也就是编译后泛型信息都会丢失,对于编译后的代码,里面存的只是一个Object,也就是classa和classb擦除后都只是ArrayList类型
         *
         *
         *
         */
    }
}
