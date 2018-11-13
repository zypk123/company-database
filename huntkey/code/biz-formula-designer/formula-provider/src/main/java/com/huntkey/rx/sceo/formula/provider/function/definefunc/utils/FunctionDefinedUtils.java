package com.huntkey.rx.sceo.formula.provider.function.definefunc.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author chenfei on 2017/8/6.
 */
public final class FunctionDefinedUtils {

    /**
     * 获取上传文件的源码以及类的全限定名
     *
     * @param inputStream
     * @return
     */
    public static String[] getSourceCodeAndClassFullName(InputStream inputStream) {

        String[] arr = new String[2];
        StringBuilder classFullName = new StringBuilder();

        StringBuffer buff = new StringBuffer();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;

            while ((line = reader.readLine()) != null) {

                // FIXME refactor later.
                if (line.contains("package ")) {
                    classFullName.append(line.replace("package", "")
                            .replace(";", "").trim());
                }

                if (line.contains(" class ")) {
                    classFullName.append("." + line.replace("public", "")
                            .replace("class", "").replace("{", "")
                            .trim());
                }

                buff.append(line + "\n");
            }
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

        arr[0] = buff.toString();
        arr[1] = classFullName.toString();

        return arr;
    }
}
