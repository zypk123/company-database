package com.zhang._0414;
/**
 * 实现工厂方法接口的具体实现类
 * 创建空调的工厂
 * @author zhangyu
 *
 */
public class CreateAirCondition implements Factory {

	@Override
	public Product create() {
		return new AirCondition();
	}

}
