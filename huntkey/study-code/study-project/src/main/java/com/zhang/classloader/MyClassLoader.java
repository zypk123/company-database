package com.zhang.classloader;

import java.io.IOException;
import java.io.InputStream;

/**
 * 自定义ClassLoader
 *
 * @author zhangyu
 * @create 2017-12-11 11:59
 **/
public class MyClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            String classPath = name.substring(name.lastIndexOf(".") + 1) + ".class";
            InputStream is = getClass().getResourceAsStream(classPath);
            if (is == null) {
                return super.findClass(name);
            }
            byte[] b = new byte[is.available()];
            return defineClass(name, b, 0, b.length);

        } catch (IOException e) {
            return super.findClass(name);
        }
    }

}
