package com.huntkey.rx.sceo.serviceCenter.common.emun;

/***********************************************************************
 * @author chenxj
 * 
 * @email: kaleson@163.com
 * 
 * @date : 2017年8月17日 下午6:54:47
 * 
 **********************************************************************/

// 值类型
public enum ValueType {

	type_string(1, "string", "String type"), 
	type_int(2, "int", "int type"), 
	type_date(3, "date", "date type");

	// 描述
	private String desc;

	// 编码
	private int key;

	// 值
	private String value;

	private ValueType(int key, String value, String desc) {
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
