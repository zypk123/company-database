package com.huntkey.rx.model;

import java.util.List;

public class ClassInfo {

	// 类名称
	private String name;

	// 父类名称
	private String parentClassName;

	// 类型：实类或者虚类
	private String type;

	// 类描述
	private String remark;

	// 属性列表
	private List<PropertyInfo> propertyList;

	//方法列表
	private List<MethodInfo> mehtodInfoList;

	private boolean isChild;

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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<PropertyInfo> getPropertyList() {
		return propertyList;
	}

	public void setPropertyList(List<PropertyInfo> propertyList) {
		this.propertyList = propertyList;
	}

	public String getParentClassName() {
		return parentClassName;
	}

	public void setParentClassName(String parentClassName) {
		this.parentClassName = parentClassName;
	}

	public List<MethodInfo> getMehtodInfoList() {
		return mehtodInfoList;
	}

	public void setMehtodInfoList(List<MethodInfo> mehtodInfoList) {
		this.mehtodInfoList = mehtodInfoList;
	}

	public boolean isChild() {
		return isChild;
	}

	public void setChild(boolean child) {
		isChild = child;
	}

	@Override
	public String toString() {
		return "TableInfo [name=" + name + ", type=" + type + ", remark=" + remark + ", propertyList=" + propertyList
				+ "]";
	}
}