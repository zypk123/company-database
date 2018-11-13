package com.demo.genericity;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型学习2
 *
 * @author zhangyu
 * @create 2017-07-19 16:45
 **/
public class TestGenericity2 {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<String>();
        stringList.add("张三");
        stringList.add("李四");
        String str1 = stringList.get(0);
        stringList.get(1);

        /****
         * 下面看下反编译后的class文件
         *   List stringList = new ArrayList();
             stringList.add("张三");
             stringList.add("李四");
             String str1 = (String)stringList.get(0);
             stringList.get(1);

         * 通过代码可以看到当调用stringList.get(0),编译器会自动编译为强转为泛型类型的代码.
         * 说明通过get()方法返回的数据也是Object类型的，即使加了泛型
         * 当该对象被赋值的时候才会进行强制转换,否则就不会进行强转
         *
         */

        /***************总结************
         *
         * 使用泛型的话,运行期把对象都是当成object来处理的,所以可以运用的方法都是object的方法,且在赋值操作时,编译器会自动强转为指定泛型类型
         *
         *
         * */
    }
}
