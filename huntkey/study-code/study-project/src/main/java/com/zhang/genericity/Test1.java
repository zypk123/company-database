package com.zhang.genericity;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangyu
 * @create 2017-12-18 16:03
 **/
public class Test1 {

    public static void main(String[] args) throws Exception {
        List<String> list = new ArrayList<String>();
        list.add("1");

        // 通过反射的方式，跳过编译器
        // 添加不是String的类型
        Method method = list.getClass().getMethod("add", Object.class);
        method.invoke(list, 123);

//        for (String s : list) {
//            System.out.println(s);
//        }


    }
}
