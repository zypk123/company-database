package com.huntkey.rx.sceo.formula.provider.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FilenameFilter;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 热替换类加载器
 * @author chenfei on 2017/5/15.
 */
public class HotSwapClassLoader extends ClassLoader {

    private static Logger logger = LoggerFactory.getLogger(HotSwapClassLoader.class);

    private static final String JAR_URL_TEMPLATE = "jar:file:/[name]!/";
    private static final String JAR_URL_REPLACEMENT = "[name]";

    protected static HotSwapClassLoader CLASS_LOADER = new HotSwapClassLoader(HotSwapClassLoader.class.getClassLoader());

    public HotSwapClassLoader(ClassLoader classLoader) {
        super(classLoader);
    }

    /**
     * 类加载方法
     *
     * @param classByte
     * @param className
     * @return
     * @throws ClassNotFoundException
     */
    public static Class<?> loadClass(byte[] classByte, String className) throws ClassNotFoundException {

        try {

            Class<?> clazz = CLASS_LOADER.findLoadedClass(className);

            if (null != clazz) {
                logger.info("clazz: {}, so need to hot swapped.", clazz);
                return new HotSwapClassLoader(CLASS_LOADER).defineClass(className, classByte, 0, classByte.length);
            }

            return CLASS_LOADER.defineClass(className, classByte, 0, classByte.length);
        } catch (ClassFormatError classFormatError) {
            classFormatError.printStackTrace();
        }

        throw new RuntimeException("load failure.");
    }

    /**
     * 有依赖jar的类加载方法
     *
     * @param classByte
     * @param className
     * @return
     * @throws ClassNotFoundException
     */
    public static Class<?> loadClass(byte[] classByte, String className, String dependencyJarLocalPath, String funcFunId) throws ClassNotFoundException {
        try {
            // 判断class是否已经加载过
            Class<?> clazz = CLASS_LOADER.findLoadedClass(className);
            // 创建自定义ClassLoader JarClassLoader，以HotSwapClassLoader为parent，这样JarClassLoader只需要加载上传的jar和class，不用管其他需要加载的系统jar
            JarClassLoader classLoader = null;
            if (null != clazz) {
                classLoader = new JarClassLoader(new HotSwapClassLoader(CLASS_LOADER));
            } else {
                classLoader = new JarClassLoader(CLASS_LOADER);
            }

            // 读取jar目录
            File jarsDir = new File(dependencyJarLocalPath + funcFunId);
            // 过滤jar文件
            File[] jars = jarsDir.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    if (name.endsWith("jar")) {
                        return true;
                    }
                    return false;
                }
            });
            // 构造urls，用于创建ClassLoader
            if (null != jars) {
                for (File jar : jars) {
                    String pluginurl = JAR_URL_TEMPLATE.replace(JAR_URL_REPLACEMENT, jar.getPath());
                    URL url = null;
                    try {
                        url = new URL(pluginurl);
                        classLoader.addURLFile(url);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
            // TODO 添加一个是否要加载的标志位，为了在编译成功后直接加载以判断加载是否能成功，如果已经加载了则本机的zk监听器中就无需再重复加载
            ClassLoaderUtils.FUNC_CLASSLOADER_MAP.put(funcFunId, classLoader);

            // 加载class
            return classLoader.loadSelfClass(className, classByte, 0, classByte.length);
        } catch (ClassFormatError classFormatError) {
            classFormatError.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("load failure.");
    }
}
