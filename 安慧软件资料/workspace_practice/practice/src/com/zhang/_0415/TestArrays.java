package com.zhang._0415;

import java.util.Arrays;

public class TestArrays {
	public static void main(String[] args) {
		int[] arr = new int[] { 1, 32, 65, 9541, 321, 32 };
		System.out.println(arr);  //数组是引用类型，打印的是地址值
		System.out.println(Arrays.toString(arr));//打印数组的方法
		System.out.println("--------------");

		Arrays.sort(arr);//对数组进行排序
		System.out.println(arr);
		System.out.println(Arrays.toString(arr));
	}

}
