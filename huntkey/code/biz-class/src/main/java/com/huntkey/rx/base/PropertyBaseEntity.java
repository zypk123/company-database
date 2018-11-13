package com.huntkey.rx.base;

public abstract class PropertyBaseEntity extends BaseEntity{

	private String pid;

	private String className;

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
}
