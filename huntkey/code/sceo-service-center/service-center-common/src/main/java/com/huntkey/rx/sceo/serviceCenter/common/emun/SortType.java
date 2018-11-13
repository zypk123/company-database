package com.huntkey.rx.sceo.serviceCenter.common.emun;

/***********************************************************************
 * @author chenxj
 * 
 * @email: kaleson@163.com
 * 
 * @date : 2017年8月17日 下午6:54:47
 * 
 **********************************************************************/

// 字段排序的方式
public enum SortType {
	ASC(1, "asc", "升序"), 
	DESC(2, "desc", "降序");
	
	private String desc;

	// 编码
	private int key;

	// 值
	private String value;

	private SortType(int key, String value, String desc) {
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
