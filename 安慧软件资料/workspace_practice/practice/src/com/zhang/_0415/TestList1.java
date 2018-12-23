package com.zhang._0415;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestList1 {
	public static void main(String[] args) {
		// list：有序，可重复的

		Student s1 = new Student("张三", 18);
		Student s2 = new Student("李四", 19);
		Student s3 = new Student("王五", 20);
		Student s4 = new Student("赵六", 21);
		Student s5 = new Student("阿七", 22);

		List<Student> list = new ArrayList<Student>();
		list.add(s1); // 为list添加元素
		list.add(s2);
		list.add(s3);
		list.add(s4);
		list.add(s5);

		/*********************** list的遍历 *************************/
		System.out.println("---------普通for循环遍历----------");// 取决于list是有序的
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}

		System.out.println("---------增强型for循环遍历-------------");// 缺点：丢失索引
		for (Student s : list) {
			System.out.println(s);
		}

		System.out.println("----------迭代器遍历----------");
		Iterator<Student> it = list.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}

	}

}
