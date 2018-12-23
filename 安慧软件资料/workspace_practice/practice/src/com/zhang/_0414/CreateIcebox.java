package com.zhang._0414;
/**
 * 实现工厂方法接口的具体实现类
 * 创建冰箱的工厂
 * @author zhangyu
 *
 */
public class CreateIcebox implements Factory {

	@Override
	public Product create() {
		return new Icebox();
	}

}
