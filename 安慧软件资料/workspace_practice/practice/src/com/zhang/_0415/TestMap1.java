package com.zhang._0415;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class TestMap1 {
	public static void main(String[] args) {
		// Map:键值对的形式存储数据---key，value

		Map<Integer, String> map = new HashMap<Integer, String>();

		// 添加
		map.put(1, "hello");
		map.put(2, "world");

		// 相同的key会覆盖value
		map.put(2, "java");

		/****************** 下面是map的遍历 ************************/
		System.out.println("------------通过键找值（keySet方法）遍历----------------");
		// 获取所有的key集合
		Set<Integer> set = map.keySet();
		// 遍历key集合（增强型for），通过key找到value
		for (Integer i : set) {
			String value = map.get(i);// 得到所有的value
			System.out.println("key:" + i + "--" + "vaule:" + value);
		}

		System.out.println("------------通过Entry（entrySet方法）遍历---------");
		// 获取EntrySet的Set集合
		Set<Entry<Integer, String>> entry = map.entrySet();
		// 遍历EntrySet，获取key和value
		for (Entry<Integer, String> en : entry) {
			Integer key = en.getKey();// 获取key
			String value = en.getValue();// 获取value
			System.out.println("key:" + key + "--" + "value:" + value);
		}

		System.out.println("---------通过values只获取值-------");
		// 获取值
		Collection<String> coll = map.values();
		// 遍历值集合
		for (String s : coll) {
			System.out.println(s);
		}
	}

}
