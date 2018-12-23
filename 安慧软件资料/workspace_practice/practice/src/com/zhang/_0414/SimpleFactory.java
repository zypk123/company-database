package com.zhang._0414;

/**
 * 简单工厂（静态工厂）
 * 
 * @author zhangyu
 *
 */
public class SimpleFactory {

	/**
	 * 静态方法，根据传入的参数的不同，返回不同的实例
	 * @param productName 产品名称
	 * @return 产品实例
	 */
	public static Product factory(String productName) throws Exception {
		if (productName.equals("washer")) {
			return new Washer();
		} else if (productName.equals("icebox")) {
			return new Icebox();
		} else if (productName.equals("airCondition")) {
			return new AirCondition();
		} else {
			throw new Exception("没有该产品");
		}
	}

	public static void main(String[] args) {
		try {
			SimpleFactory.factory("washer");
			SimpleFactory.factory("icebox");
			SimpleFactory.factory("airCondition");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
