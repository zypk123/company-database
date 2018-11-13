package com.zhang.classloader;

/**
 * ClassLoader测试类
 *
 * @author zhangyu
 * @create 2017-12-08 14:56
 **/
public class TestClassLoader {

    public static void main(String[] args) {

        /**
         * BootstrapClassLoader 加载  jre/lib
         */
        System.out.println(System.getProperty("sun.boot.class.path"));

        /**
         * Extention ClassLoader   jre/lib/ext
         */
        System.out.println(System.getProperty("java.ext.dirs"));

        /**
         * AppClassLoader  bin目录
         */
        System.out.println(System.getProperty("java.class.path"));

    }
}
