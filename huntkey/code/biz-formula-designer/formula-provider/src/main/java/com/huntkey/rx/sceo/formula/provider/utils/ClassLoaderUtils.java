package com.huntkey.rx.sceo.formula.provider.utils;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author chenfei on 2017/6/1.
 */
public final class ClassLoaderUtils {

    /**
     * 类加载方法
     *
     * @param classData
     * @param className
     * @return
     */
    public static Class<?> loadClass(byte[] classData, String className) {

        try {
            return HotSwapClassLoader.loadClass(classData, className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("load class failure.", e);
        }
    }

    /**
     * 有依赖jar的类加载方法
     *
     * @param classData
     * @param className
     * @return
     */
    public static Class<?> loadClass(byte[] classData, String className, String dependencyJarLocalPath, String funcFunId) {

        try {
            return HotSwapClassLoader.loadClass(classData, className, dependencyJarLocalPath, funcFunId);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("load class failure.", e);
        }
    }

    /**
     * @param className
     * @deprecated
     */
    public static void unloadClass(String className) {

        // deprecated.
    }

    public static ClassLoader getClassLoader() {
        return HotSwapClassLoader.CLASS_LOADER;
    }

    public static ConcurrentHashMap<String, JarClassLoader> FUNC_CLASSLOADER_MAP = new ConcurrentHashMap<String, JarClassLoader>();
//    public static ConcurrentHashMap<String, List> FUNC_CLASSLOADER_MAP = new ConcurrentHashMap<String, List>();
}

