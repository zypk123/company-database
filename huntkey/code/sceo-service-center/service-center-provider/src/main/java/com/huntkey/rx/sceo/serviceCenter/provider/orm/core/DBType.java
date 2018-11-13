package com.huntkey.rx.sceo.serviceCenter.provider.orm.core;

/***********************************************************************
 * @author chenxj
 * 
 * @email: kaleson@163.com
 * 
 * @date : 2017年6月22日 下午4:45:51
 * 
 *       数据库类型
 * 
 **********************************************************************/
public enum DBType {
	HBASE("hbase"), MYSQL("mysql");

	private String name;

	private String code;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	private DBType(String name) {
		this.name = name;
	}
}
