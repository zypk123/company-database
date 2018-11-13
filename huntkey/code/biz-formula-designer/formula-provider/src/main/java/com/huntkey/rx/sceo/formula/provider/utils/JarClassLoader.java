package com.huntkey.rx.sceo.formula.provider.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


/**
 * 插件类加载器，在插件目录中搜索jar包，并为发现的资源(jar)构造一个类加载器,将对应的jar添加到classpath中
 *
 * @author zhangyang
 */
public class JarClassLoader extends URLClassLoader {
    private static Logger logger = LoggerFactory.getLogger(JarClassLoader.class);

    private List<JarURLConnection> cachedJarFiles = new ArrayList<JarURLConnection>();

    public JarClassLoader(ClassLoader parent) {
        super(new URL[]{}, parent);
    }

    /**
     * 将指定的文件url添加到类加载器的classpath中去，并缓存jar connection，方便以后卸载jar
     *
     * @param file
     */
    public void addURLFile(URL file) {
        try {
            // 打开并缓存文件url连接
            URLConnection uc = file.openConnection();
            if (uc instanceof JarURLConnection) {
                uc.setUseCaches(true);
                cachedJarFiles.add((JarURLConnection) uc);
            }
        } catch (Exception e) {
            logger.error("Failed to cache plugin JAR file: " + file.toExternalForm());
        }
        addURL(file);
    }

    /**
     * 卸载jar包
     */
    public void unloadJarFiles() {
        for (JarURLConnection url : cachedJarFiles) {
            try {
                logger.info("Unloading plugin JAR file " + url.getJarFile().getName());
                url.getJarFile().close();
                url = null;
            } catch (Exception e) {
                logger.error("Failed to unload JAR file\n" + e);
            }
        }
    }

    /**
     * 通过define方式加载class
     *
     * @param name
     * @param bytes
     * @param off
     * @param len
     * @return
     * @throws Exception
     */
    public Class<?> loadSelfClass(String name, byte[] bytes, int off, int len) throws Exception {
        return defineClass(name, bytes, off, len);
    }
}