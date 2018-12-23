package com.zhang._0415;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class TestSet1 {
	public static void main(String[] args) {
		// set:无序，不可以重复
		// set去重原理：重写泛型类型的hashcode和equal方法

		Student s1 = new Student("张三", 18);
		Student s2 = new Student("李四", 19);
		Student s3 = new Student("王五", 20);
		Student s4 = new Student("赵六", 21);
		Student s5 = new Student("阿七", 22);

		Set<Student> set = new HashSet<Student>();
		set.add(s1);
		set.add(s2);
		set.add(s3);
		set.add(s4);
		set.add(s5);

		/************************* 下面是set的遍历 *****************************/
		System.out.println("-----------增强for循环遍历--------");// 由于set是无序的，所以不能用普for循环来遍历
		for (Student s : set) {
			System.out.println(s );
		}

		System.out.println("----------迭代器遍历------------");
		Iterator<Student> it = set.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}

	}

}
