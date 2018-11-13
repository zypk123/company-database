package com.huntkey.rx.model;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EntityInfo {

	/**
	 * 需要继承的父类名称；
	 */
	private String extendName;

	/**
	 * 实体名
	 */
	private String entityName;

	/**
	 * 实体描述
	 */
	private String entityDesc;

	/**
	 * 实体所在包路径
	 */
	private String entityPackage;

	/*
	 * 实体类名
	 */
	private String className;

	/**
	 * 包路径 + 类名
	 */
	private String packageClassName;

	/**
	 * 表名
	 */
	private String tableName;

	/**
	 * 需要导入的包
	 */
	private Set<String> imports = new HashSet<String>();

	/**
	 * 属性名以及对应的类型
	 */
	private Map<String, PropertyInfo> propTypes;

	/**
	 * 属性名以及注释的对应
	 */
	private Map<String, String> propRemarks;

	/**
	 * 属性名和jdbc类型的映射
	 */
	private Map<String, String> propJdbcTypes;

	/**
	 * 属性名和字段名的映射
	 */
	private Map<String, String> propNameColumnNames;

	//方法的定义
	private Map<String, Object> propMethods;

	public Map<String, String> getPropJdbcTypes() {
		return propJdbcTypes;
	}

	public void setPropJdbcTypes(Map<String, String> propJdbcTypes) {
		this.propJdbcTypes = propJdbcTypes;
	}

	public Map<String, String> getPropRemarks() {
		return propRemarks;
	}

	public void setPropRemarks(Map<String, String> propRemarks) {
		this.propRemarks = propRemarks;
	}

	public Map<String, PropertyInfo> getPropTypes() {
		return propTypes;
	}

	public void setPropTypes(Map<String, PropertyInfo> propTypes) {
		this.propTypes = propTypes;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getEntityPackage() {
		return entityPackage;
	}

	public void setEntityPackage(String entityPackage) {
		this.entityPackage = entityPackage;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Set<String> getImports() {
		return imports;
	}

	public void setImports(Set<String> imports) {
		this.imports = imports;
	}

	public String getEntityDesc() {
		return entityDesc;
	}

	public void setEntityDesc(String entityDesc) {
		this.entityDesc = entityDesc;
	}

	public String getPackageClassName() {
		return packageClassName;
	}

	public void setPackageClassName(String packageStr) {
		this.packageClassName = packageStr;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Map<String, String> getPropNameColumnNames() {
		return propNameColumnNames;
	}

	public void setPropNameColumnNames(Map<String, String> propNameColumnNames) {
		this.propNameColumnNames = propNameColumnNames;
	}

	public String getExtendName() {
		return extendName;
	}

	public void setExtendName(String extendName) {
		this.extendName = extendName;
	}

	public Map<String, Object> getPropMethods() {
		return propMethods;
	}

	public void setPropMethods(Map<String, Object> propMethods) {
		this.propMethods = propMethods;
	}

}