package com.huntkey.rx.sceo.formula.provider.utils;

import com.huntkey.rx.sceo.formula.provider.utils.mem.JavaStringCompiler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Map;

/**
 * 编译器工具类
 * <p>
 * @author chenfei on 2017/5/31.
 */
public final class CompilerUtils {

    private static Logger logger = LoggerFactory.getLogger(CompilerUtils.class);

    public static byte[] compile(File sourceFile, String classPath) {

        BufferedReader reader = null;
        try {

            String fileName = sourceFile.getName();
            StringBuffer sourceCodeBuff = new StringBuffer();
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(sourceFile)));
            String line = null;
            while ((line = reader.readLine()) != null) {
                sourceCodeBuff.append(line + "\n");
            }

            Map<String, byte[]> results = new JavaStringCompiler(classPath).compile(fileName, sourceCodeBuff.toString());

            String className = fileName.replace(".java", "");
            for (String key : results.keySet()) {
                if (key.endsWith(className)) {
                    return results.get(key);
                }
            }

            throw new RuntimeException("Compile failure, can not be found class: " + className);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static byte[] compile(String fileName, String sourceCode, String classPath) {

        logger.info("fileName: {}", fileName);
        logger.info("sourceCode: {}", sourceCode);

        try {
            Map<String, byte[]> results = new JavaStringCompiler(classPath).compile(fileName, sourceCode);
            String className = fileName.replace(".java", "");
            for (String key : results.keySet()) {
                if (key.endsWith(className)) {
                    return results.get(key);
                }
            }

            throw new RuntimeException("Compile failure, can not be found class: " + className);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] compileWithDependencyJar(String fileName, String sourceCode, String classPath, String dependencyJarPath) {

        logger.info("fileName: {}", fileName);
        logger.info("sourceCode: {}", sourceCode);
        logger.info("dependencyJarPath: {}", dependencyJarPath);

        try {
            Map<String, byte[]> results = new JavaStringCompiler(classPath, dependencyJarPath).compile(fileName, sourceCode);
            String className = fileName.replace(".java", "");
            for (String key : results.keySet()) {
                if (key.endsWith(className)) {
                    return results.get(key);
                }
            }

            throw new RuntimeException("Compile failure, can not be found class: " + className);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
