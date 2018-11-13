package com.huntkey.rx.model;

public class PropertyInfo {

	private String name;
	private String type;
	private String remark;
	private int len;
	private int precision;
	private String fomula;
	private String limitFomula;
	private String className;

	// 物理表的列名称
	private String filedName;

	public int getLen() {
		return len;
	}

	public void setLen(int len) {
		this.len = len;
	}

	public int getPrecision() {
		return precision;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFomula() {
		return fomula;
	}

	public void setFomula(String fomula) {
		this.fomula = fomula;
	}

	public String getLimitFomula() {
		return limitFomula;
	}

	public void setLimitFomula(String limitFomula) {
		this.limitFomula = limitFomula;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getFiledName() {
		return filedName;
	}

	public void setFiledName(String filedName) {
		this.filedName = filedName;
	}

}
