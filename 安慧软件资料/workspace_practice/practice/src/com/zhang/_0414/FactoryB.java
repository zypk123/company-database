package com.zhang._0414;

/**
 * 创建产品型号为B的产品工厂
 * @author zhangyu
 *
 */
public class FactoryB implements AbstractFactory {

	@Override
	public Washe1 createWasher() {
		return new WasherB();
	}

	@Override
	public Icebox1 createIcebox() {
		return new IceboxB();
	}
}
