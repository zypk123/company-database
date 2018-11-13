package com.huntkey.rx.sceo.serviceCenter.common.model;

import com.huntkey.rx.sceo.serviceCenter.common.emun.OperatorType;
import com.huntkey.rx.sceo.serviceCenter.common.emun.ValueType;

/**
 * 条件对象 Created by kongquan on 2017/8/10.
 */
public class ConditionNode {
	private String attr;
	private String operator;
	private String value;
	private String type;

	///////////////////////////////////表示关系的常量/////////////////////////
	public final static String AND = "and";
	public final static String OR = "or";
	public final static String NOT = "not";
	//////////////////////////常量的定义//////////////////////////////////////
	public final static String ATTR = "attr";
	public final static String OPERATOR = "operator";
	public final static String VALUE = "value";
	////////////////////////////////////////////////////////////////////////////
	public ConditionNode(String attr, OperatorType operatorType, String value) {
		this.attr = attr;
		this.operator = operatorType.getValue();
		this.value = value;
		this.type = ValueType.type_string.getValue();
	}

	// 添加type 初始化
	public ConditionNode(String attr, OperatorType operator, String value, String type) {
		this(attr, operator, value);
		this.type = type;
	}

	public String getAttr() {
		return attr;
	}

	public void setAttr(String attr) {
		this.attr = attr;
	}

	public String getOperator() {
		return operator;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
