package com.zhang._0413;

public class TestAnimal {

	public static void main(String[] args) {
		Animal a = new Cat(); // 多态
		System.out.println(a.name);// ----动物 属性不存在重写
		a.eat();// 猫吃东西----- 方法存在重写
		a.run();

		Animal a1 = new BsCat();
		System.out.println(a1.name);
		a1.eat();
		a1.run();

		Cat a2 = new BsCat();
		System.out.println(a2.name);
		a2.eat();
		a2.run();

		BsCat a3 = new BsCat();
		System.out.println(a3.name);
		a3.eat();
		a3.run();

		// 若想得到子类的属性，可以采用对象下转型(需要强转)
		Cat a4 = (Cat) a;
		System.out.println(a4.name);// 猫

		BsCat a5 = (BsCat) a2;
		System.out.println(a5.name);// 波斯猫

	}

}
