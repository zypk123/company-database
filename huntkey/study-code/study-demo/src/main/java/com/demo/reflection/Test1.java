package com.demo.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class Test1 {
    public static void main(String[] args) {

        // Class对象
        // Class clazz = new Student().getClass();
        // Class clazz = Student.class;

        try {
            Class clazz = Class.forName("com.demo.reflection.Student");

            // 得到字段
            Field[] fieids = clazz.getDeclaredFields();

            for (Field f : fieids) {
                // Modifier.toString(f.getModifiers()) 得到访问修饰符
                // f.getType().getSimpleName() 得到数据类型
                // f.getName() 得到属性名

                System.out.println(
                        Modifier.toString(f.getModifiers()) + " " + f.getType().getSimpleName() + " " + f.getName());
            }

            // 得到方法
            Method[] methods = clazz.getDeclaredMethods();

            for (Method m : methods) {
                // Modifier.toString(f.getModifiers()) 得到访问修饰符
                // f.getReturnType().getSimpleName() 得到返回值类型
                // f.getName() 得到方法名
                // f.getParameterTypes() 得到参数名
                System.out.println(Modifier.toString(m.getModifiers()) + " " + m.getReturnType().getSimpleName() + " "
                        + m.getName() + " " + Arrays.toString(m.getParameterTypes()));
            }

            // 得到一个Student对象
            Student student = (Student) clazz.newInstance();
            student.setName("张三");
            System.out.println(student.getName());

            // 调用私有的
            Method method1 = clazz.getDeclaredMethod("sleep");// 找到方法
            method1.setAccessible(true);// 打破封装private
            method1.invoke(student);

            Method method2 = clazz.getDeclaredMethod("test", String.class);// 找到方法
            method2.setAccessible(true);// 打破封装private
            method2.invoke(student, "Test测试");


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
