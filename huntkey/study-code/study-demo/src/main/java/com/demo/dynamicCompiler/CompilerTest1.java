package com.demo.dynamicCompiler;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 动态编译
 *
 * @author zhangyu
 * @create 2017-07-24 10:49
 **/
public class CompilerTest1 {
    public static void main(String[] args) throws IOException {
        // 编译程序
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        int result = javaCompiler.run(null, null, null, "-d", "./temp/", "temp/Hello.java");
        System.out.println(result == 0 ? "恭喜编译成功" : "对不起编译失败");

        // 运行程序
        Runtime run = Runtime.getRuntime();
        Process process = run.exec("java -cp ./temp temp/com/Hello");
        InputStream in = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String info = "";
        while ((info = reader.readLine()) != null) {
            System.out.println(info);
        }
    }


}
