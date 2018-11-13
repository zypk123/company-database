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
public enum OperatorType {

	Equals(1, "=", "equals"), 
	NotEquals(2, "!=", "not equqls"), 
	Like(3, "like", "like"), 
	GreaterEquals(4, ">=", "greater or equals"), 
	Less(5, "<", "less than"), 
	Greater(6, ">", "greater than"), 
	In(7, "in", "in the erea"),
	NotIn(8, "!in", "not in the area"),
	IsNull(9, "is", "is null"),
	NotNull(10, "!is", "is null"),
	LessEquals(11, "<=","less or equals");

	// 描述
	private String desc;

	// 编码
	private int key;

	// 值
	private String value;

	private OperatorType(int key, String value, String desc) {
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
