package com.zhang._0415;

import java.util.ArrayList;
import java.util.List;

public class TestList2 {
	public static void main(String[] args) {
		List<Student> list = new ArrayList<Student>();

		// Ôö
		list.add(new Student("zhangyu", 22));
		list.add(new Student("lisi", 23));

		// É¾
		list.remove(0);

		// ¸Ä
		list.set(0, new Student("wangwu", 45));

		// ²é
		System.out.println(list.get(0));

	}

}
