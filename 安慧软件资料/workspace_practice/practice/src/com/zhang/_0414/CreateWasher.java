package com.zhang._0414;

/**
 * 实现工厂方法接口的具体实现类
 * 创建洗衣机的工厂
 * @author zhangyu
 *
 */
public class CreateWasher implements Factory{

	@Override
	public Product create() {
		return new Washer();
	}
}
