package com.zhang.classloader;

import java.lang.reflect.Method;

/**
 * @author zhangyu
 * @create 2017-12-11 13:59
 **/
public class TestMyClassLoader {

    public static void main(String[] args) {

        try {
            MyClassLoader myClassLoder = new MyClassLoader();
            Class clazz = myClassLoder.loadClass("com.zhang.classloader.TestMyClassLoader");
            Object object = clazz.newInstance();
            Method method = clazz.getMethod("say", null);
            Object o = method.invoke(object, null);
            System.out.println(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 定义一个方法
     *
     * @return
     */
    public String say() {
        return "here";
    }
}
