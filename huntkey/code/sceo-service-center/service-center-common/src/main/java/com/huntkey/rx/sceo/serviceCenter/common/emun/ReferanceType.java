package com.huntkey.rx.sceo.serviceCenter.common.emun;

/***********************************************************************
 * @author chenxj
 * 
 * @email: kaleson@163.com
 * 
 * @date : 2017年8月17日 下午6:54:47
 * 
 **********************************************************************/

// 比较符
public enum ReferanceType {

	Base(1, "base", "基本属性"), 
	Referance(2, "ref", "基本属性，对象引用"), 
	SubAttribute(3, "sub", "基本属性，属性集"), 
	All(4, "all", "基本属性，对象引用，属性集");

	// 描述
	private String desc;

	// 编码
	private int key;

	// 值
	private String value;

	private ReferanceType(int key, String value, String desc) {
		this.key = key;
		this.value = value;
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
