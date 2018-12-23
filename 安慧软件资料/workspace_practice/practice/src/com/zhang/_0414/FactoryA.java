package com.zhang._0414;

/**
 * 创建产品型号为A的产品工厂
 * @author zhangyu
 *
 */
public class FactoryA implements AbstractFactory {

	@Override
	public Washe1 createWasher() {
		return new WasherA();
	}

	@Override
	public Icebox1 createIcebox() {
		return new IceboxA();
	}
}
