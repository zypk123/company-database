package com.zhang._0530;

public class Test {
	public static void main(String[] args) {
		int[] arr = new int[] { 2, 6, 9, 3, 1, 8 };
		int[] index = new int[] { 4, 5, 3, 0, 1, 5, 2, 3, 4, 2, 5 };
		String tel = "";
		for (int i : index) {
			tel += arr[i];
		}
		System.out.println("联系方式：" + tel);
	}
}
