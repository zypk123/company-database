package com.zhang.genericity;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author zhangyu
 * @create 2017-12-18 17:36
 **/
public class Test2 {

    public static void main(String[] args) {

        ArrayList<Person> personList = new ArrayList<Person>();

        personList.add(new Person("张三"));
        personList.add(new Person("李四"));
        personList.add(new Person("王五"));
        personList.add(new Person("赵六"));

        ArrayList<Student> studentList = new ArrayList<Student>();
        studentList.add(new Student("学生1"));
        studentList.add(new Student("学生2"));
        studentList.add(new Student("学生3"));
        studentList.add(new Student("学生4"));


        outArraylist(personList);
        System.out.println("**********************");
        outArraylist(studentList);

    }

    //定义上限限定方法
    public static void outArraylist(ArrayList<? extends Person> al) {

        Iterator<? extends Person> it = al.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }


}
