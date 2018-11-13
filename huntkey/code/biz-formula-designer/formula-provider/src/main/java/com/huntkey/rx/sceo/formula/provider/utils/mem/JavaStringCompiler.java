package com.huntkey.rx.sceo.formula.provider.utils.mem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.tools.*;
import javax.tools.JavaCompiler.CompilationTask;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.*;
/**
 * @author chenfei on 2017/5/15.
 */
public class JavaStringCompiler {

    private static Logger logger = LoggerFactory.getLogger(JavaStringCompiler.class);

    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
    StandardJavaFileManager stdManager;

    public JavaStringCompiler(String classPath) {
        try {
            this.stdManager = this.compiler.getStandardFileManager((DiagnosticListener) null, (Locale) null, (Charset) null);
            stdManager.setLocation(StandardLocation.CLASS_PATH, wrapClassPath(classPath));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public JavaStringCompiler(String classPath, String dependencyJarPath) {
        try {
            this.stdManager = this.compiler.getStandardFileManager((DiagnosticListener) null, (Locale) null, (Charset) null);
            stdManager.setLocation(StandardLocation.CLASS_PATH, wrapClassPath(classPath, dependencyJarPath));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, byte[]> compile(String fileName, String source) throws IOException {
        MemoryJavaFileManager manager = new MemoryJavaFileManager(this.stdManager);
        Throwable var4 = null;

        Map var8;
        try {

            JavaFileObject javaFileObject = manager.makeStringSource(fileName, source);
            CompilationTask task = this.compiler.getTask((Writer) null, manager, (DiagnosticListener) null, null, (Iterable) null, Arrays.asList(new JavaFileObject[]{javaFileObject}));
            Boolean result = task.call();
            if (result == null || !result.booleanValue()) {
                throw new RuntimeException("Compilation failed.");
            }

            var8 = manager.getClassBytes();
        } catch (Throwable var17) {
            var4 = var17;
            throw new RuntimeException(var17);
        } finally {
            if (manager != null) {
                if (var4 != null) {
                    try {
                        manager.close();
                    } catch (Throwable var16) {
                        var4.addSuppressed(var16);
                    }
                } else {
                    manager.close();
                }
            }

        }

        return var8;
    }

    private static List<File> wrapClassPath(String classPath) throws Exception {

        File cpFile = new File(classPath);
        List<File> pathFiles = new ArrayList<File>();
        pathFiles.add(cpFile);

        wrapRecursely(cpFile, pathFiles);

        return pathFiles;
    }

    private static List<File> wrapClassPath(String classPath, String dependencyJarPath) throws Exception {

        File cpFile = new File(classPath);
        List<File> pathFiles = new ArrayList<File>();
        pathFiles.add(cpFile);

        wrapRecursely(cpFile, pathFiles);
        wrapRecursely(new File(dependencyJarPath), pathFiles);
        logger.info("pathFiles: {}", pathFiles);

        return pathFiles;
    }

    private static void wrapRecursely(File file, List<File> pathFiles) {

        if (file.isDirectory()) {
            File[] files = file.listFiles();

            for (File f : files) {
                wrapRecursely(f, pathFiles);
            }
        } else {
            if (file.getName().endsWith("jar") || file.getName().endsWith("class")) {
                pathFiles.add(file);
            }
        }
    }


    public Class<?> loadClass(String name, Map<String, byte[]> classBytes) throws ClassNotFoundException, IOException {
        MemoryClassLoader classLoader = new MemoryClassLoader(classBytes);
        Throwable var4 = null;

        Class var5;
        try {
            var5 = classLoader.loadClass(name);
        } catch (Throwable var14) {
            var4 = var14;
            throw new RuntimeException(var14);
        } finally {
            if (classLoader != null) {
                if (var4 != null) {
                    try {
                        classLoader.close();
                    } catch (Throwable var13) {
                        var4.addSuppressed(var13);
                    }
                } else {
                    classLoader.close();
                }
            }

        }

        return var5;
    }
}
