package com.huntkey.rx.sceo.formula.engine.function;

import com.huntkey.rx.sceo.formula.common.function.FunctionDescriber;
import com.huntkey.rx.sceo.formula.engine.exception.FormulaException;
import com.huntkey.rx.sceo.formula.engine.expression.Expression;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Modifier;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 内置函数处理器
 *
 * 函数对象辅助器维护了一个函数的映射表，并根据框架的要求生成相应函数的实例
 * @author chenfei on 2017/6/15.
 */
public class FunctionHandlerBuildIn implements FunctionHandler {

    /**
     * 父节点
     */
    protected FunctionHandler parent = null;

    /**
     * 构造函数
     *
     * @param parent 父节点
     */
    public FunctionHandlerBuildIn(FunctionHandler parent) {
        this.parent = parent;
    }

    /**
     * 构造函数
     */
    public FunctionHandlerBuildIn() {
        this(null);
    }

    /**
     * 函数映射表
     */
    protected ConcurrentHashMap<String, Class<Function>> mappings = new ConcurrentHashMap<String, Class<Function>>();

    /**
     * 注册函数映射
     *
     * @param funcName 函数名
     * @param funClass 对应的函数实现的类名
     */
    public void addFunction(String funcName, Class<Function> funClass) {
        mappings.put(funcName, funClass);
    }

    /**
     * 注销函数映射
     *
     * @param funcName 函数名
     */
    public void removeFunction(String funcName) {
        mappings.remove(funcName);
    }

    /**
     * 加载函数描述符
     *
     * @return
     */
    public List<FunctionDescriber> loadFunctionDescriber() {

        List<FunctionDescriber> desces = new ArrayList<FunctionDescriber>();

        try {
            for (String functionName : STATIC_MAPPINGS.keySet()) {

                Class<Function> clazz = STATIC_MAPPINGS.get(functionName);
                desces.add(clazz.newInstance().buildFunctionDescriber());

            }
        } catch (Exception e) {
            throw new RuntimeException("Some error occured when get function describer.", e);
        }

        return desces;
    }

    /**
     * 函数表达式搜索顺序
     *
     * @param funcName 函数名
     * @return
     */
    @Override
    public Expression customize(String funcName) {
        try {
            Class<Function> found = STATIC_MAPPINGS.get(funcName.toUpperCase());
            if (found != null) {
                return found.newInstance();
            }
            found = mappings.get(funcName);
            if (found != null) {
                return found.newInstance();
            }
            if (parent != null) {
                return parent.customize(funcName);
            }
            throw new FormulaException("Can not find function :" + funcName);
        } catch (Exception ex) {
            throw new FormulaException("Can not create function :" + funcName);
        }
    }

    /**
     * 内置静态的函数映射表
     */
    public static ConcurrentHashMap<String, Class<Function>> STATIC_MAPPINGS = new ConcurrentHashMap<String, Class<Function>>();
    protected static Map<String, Integer> STATIC_MAPPINGS_USER_BEHAVIOR = new HashMap<String, Integer>();

    /**
     * @deprecated
     *
     * @param scanPath
     * @param packageName
     */
    private static void initFunctions(String scanPath, String packageName) {

        File functionDirectory = new File(scanPath);
        if (functionDirectory.isFile() || !functionDirectory.canRead()) {
            throw new IllegalStateException("The directory can not to be read.:" + functionDirectory.getAbsolutePath());
        }

        try {
            for (File f : functionDirectory.listFiles()) {

                if (f.isDirectory()) {
                    String fileName = f.getName();
                    String subScanPath = scanPath + "/" + fileName;
                    String subPackageName = packageName + "." + fileName;

                    initFunctions(subScanPath, subPackageName);
                } else {
                    String fileName = f.getName();
                    Class clazz = Class.forName(packageName + "." + fileName.replace(".class", ""));
                    if (Modifier.isAbstract(clazz.getModifiers()) || clazz.isInterface()
                            || clazz.getName().contains("FunctionHandler") || clazz.getName().contains("$")) {
                        continue;
                    }
                    STATIC_MAPPINGS.put(clazz.getSimpleName().toUpperCase(), (Class<Function>) Class.forName(clazz.getName()));
                    STATIC_MAPPINGS_USER_BEHAVIOR.put(clazz.getSimpleName().toUpperCase(), 0);
                }
            }
        } catch (ClassNotFoundException e) {
            throw new FormulaException("Can not initiate the build-in functions.", e);
        }
    }

    /**
     * 从包package中获取所有的Class
     *
     * @param pack
     * @return
     */
    public static Set<Class<?>> getClasses(String pack) {

        Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
        boolean recursive = true;
        String packageName = pack;
        String packageDirName = packageName.replace('.', '/');
        Enumeration<URL> dirs;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
            while (dirs.hasMoreElements()) {
                URL url = dirs.nextElement();
                String protocol = url.getProtocol();
                if ("file".equals(protocol)) {
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    findAndAddClassesInPackageByFile(packageName, filePath, recursive, classes);
                } else if ("jar".equals(protocol)) {
                    JarFile jar;
                    jar = ((JarURLConnection) url.openConnection()).getJarFile();
                    Enumeration<JarEntry> entries = jar.entries();
                    while (entries.hasMoreElements()) {
                        JarEntry entry = entries.nextElement();
                        String name = entry.getName();
                        if (name.charAt(0) == '/') {
                            name = name.substring(1);
                        }
                        if (name.startsWith(packageDirName)) {
                            int idx = name.lastIndexOf('/');
                            if (idx != -1) {
                                packageName = name.substring(0, idx).replace('/', '.');
                            }
                            if ((idx != -1) || recursive) {
                                if (name.endsWith(".class") && !entry.isDirectory()) {
                                    String className = name.substring(packageName.length() + 1, name.length() - 6);
                                    classes.add(Class.forName(packageName + '.' + className));
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return classes;
    }

    /**
     * 以文件的形式来获取包下的所有Class
     *
     * @param packageName
     * @param packagePath
     * @param recursive
     * @param classes
     */
    public static void findAndAddClassesInPackageByFile(String packageName, String packagePath, final boolean recursive, Set<Class<?>> classes) {
        File dir = new File(packagePath);
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        File[] dirfiles = dir.listFiles(new FileFilter() {

            @Override
            public boolean accept(File file) {
                return (recursive && file.isDirectory()) || (file.getName().endsWith(".class"));
            }
        });
        for (File file : dirfiles) {
            if (file.isDirectory()) {
                findAndAddClassesInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive, classes);
            } else {
                String className = file.getName().substring(0, file.getName().length() - 6);
                try {
                    classes.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + '.' + className));
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * 加载自定义函数
     *
     * @param clazz
     * @return
     */
    public boolean loadCustomizedFunction2FormulaEngine(Class<Function> clazz) {
        try {
            STATIC_MAPPINGS.put(clazz.getSimpleName().toUpperCase(), clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    /**
     * 卸载自定义函数
     *
     * @param className
     * @return
     */
    public Class<Function> unloadCustomizedFunctionFromFromulaEngine(String className) {
        return STATIC_MAPPINGS.remove(className.toUpperCase());
    }

    /**
     * 初始化内置的函数
     */
    static {
        URL location = FunctionHandlerBuildIn.class.getProtectionDomain().getCodeSource().getLocation();

        String packageName = FunctionHandlerBuildIn.class.getPackage().getName();

        try {
            Set<Class<?>> classes = getClasses(packageName);

            for (Class<?> clazz : classes) {

                if (Modifier.isAbstract(clazz.getModifiers()) || clazz.isInterface()
                        || clazz.getName().contains("FunctionHandler") || clazz.getName().contains("$")) {
                    continue;
                }

                System.out.println("scan class: " + clazz.getName());

                STATIC_MAPPINGS.put(clazz.getSimpleName().toUpperCase(), (Class<Function>) Class.forName(clazz.getName()));
                STATIC_MAPPINGS_USER_BEHAVIOR.put(clazz.getSimpleName().toUpperCase(), 0);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public Map<String, Integer> getStaticMappingsUserBehavior(){
        return STATIC_MAPPINGS_USER_BEHAVIOR;
    }
}
