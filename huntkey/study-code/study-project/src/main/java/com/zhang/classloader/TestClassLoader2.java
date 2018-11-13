package com.zhang.classloader;

/**
 * @author zhangyu
 * @create 2017-12-08 17:45
 **/
public class TestClassLoader2 {


    public static void main(String[] args) {

        // AppClassLoader
        ClassLoader classLoader = TestClassLoader2.class.getClassLoader();
        System.out.println(classLoader);

        // ExtClassLoader
        ClassLoader parentClassLoader = classLoader.getParent();
        System.out.println(parentClassLoader);

        ClassLoader parentParentClassLoader = parentClassLoader.getParent();
        System.out.println(parentParentClassLoader);

    }

}
