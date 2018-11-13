package com.demo.dynamicCompiler;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author zhangyu
 * @create 2017-07-24 11:46
 **/
public class TestCompil {
    public static void main(String[] args) throws IOException {
        // 通过IO流创建一个临时文件，然后动态编译
        String strjava = "public class TestJava{public static void main(String[] args){System.out.println(\"nihao\");}}";
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("./temp/TestJava.java")));
        bw.write(strjava);
        bw.flush();
        bw.close();
        //动态编译
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int flag = compiler.run(null, null, null, "./temp/TestJava.java");
        System.out.println(flag == 0 ? "编译成功" : "编译失败");

        //两种动态执行编译方法
        //1.通过Runtime.getRuntime();启动新的线程进行
        Runtime run = Runtime.getRuntime();
        Process process = run.exec("java -cp ./temp   TestJava");// -cp 和 -classpath 一样，是指定类运行所依赖其他类的路径

        InputStream is = process.getInputStream();
        BufferedReader bis = new BufferedReader(new InputStreamReader(is));
        String info = null;
        while (null != (info = bis.readLine())) {
            System.out.println(info);
        }

        //2.通过反射动态执行
        try {
            URL[] urls = new URL[]{new URL("file:/" + "E:/work/idea/studyProject/study-demo/temp/")};
            URLClassLoader loader = new URLClassLoader(urls);
            // 通过反射调用此类
            Class clazz = loader.loadClass("TestJava");
            Method m = clazz.getMethod("main", String[].class);
            // m.invoke(null,new String[]{"aa","bb"});
            // 由于可变参数是jdk5.0之后才有，上面代码会编译成m.invoke(null,"aa","bb");会发生参数不匹配的问题
            // 因此必须加上Object 强转
            m.invoke(null, (Object) new String[]{});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
