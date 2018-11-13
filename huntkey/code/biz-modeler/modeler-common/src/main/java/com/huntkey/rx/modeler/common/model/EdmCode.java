package com.huntkey.rx.modeler.common.model;

import java.util.List;

public class EdmCode {
	private String id; // 主键id

	private String codeType;// 编码类型

	private String codeValue; // 编码值

	private String codeName;// 编码名称

	private String codeDesc; // 编码描述

	private Integer codeSeq;// 排序字段

	private Byte codeStatus;// 编码状态

	private String codeExt1; // 备注1

	private String codeExt2;// 备注2

	private String codeExt3;// 备注3

	private String codeExt4;// 备注4

	private List<EdmCode> children; // 子类

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType == null ? null : codeType.trim();
	}

	public String getCodeValue() {
		return codeValue;
	}

	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue == null ? null : codeValue.trim();
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName == null ? null : codeName.trim();
	}

	public String getCodeDesc() {
		return codeDesc;
	}

	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc == null ? null : codeDesc.trim();
	}

	public Integer getCodeSeq() {
		return codeSeq;
	}

	public void setCodeSeq(Integer codeSeq) {
		this.codeSeq = codeSeq;
	}

	public Byte getCodeStatus() {
		return codeStatus;
	}

	public void setCodeStatus(Byte codeStatus) {
		this.codeStatus = codeStatus;
	}

	public String getCodeExt1() {
		return codeExt1;
	}

	public void setCodeExt1(String codeExt1) {
		this.codeExt1 = codeExt1 == null ? null : codeExt1.trim();
	}

	public String getCodeExt2() {
		return codeExt2;
	}

	public void setCodeExt2(String codeExt2) {
		this.codeExt2 = codeExt2 == null ? null : codeExt2.trim();
	}

	public String getCodeExt3() {
		return codeExt3;
	}

	public void setCodeExt3(String codeExt3) {
		this.codeExt3 = codeExt3 == null ? null : codeExt3.trim();
	}

	public String getCodeExt4() {
		return codeExt4;
	}

	public void setCodeExt4(String codeExt4) {
		this.codeExt4 = codeExt4 == null ? null : codeExt4.trim();
	}

	public List<EdmCode> getChildren() {
		return children;
	}

	public void setChildren(List<EdmCode> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "${" +
				"id:'" + id + '\'' +
				", codeType:'" + codeType + '\'' +
				", codeValue:'" + codeValue + '\'' +
				", codeName:'" + codeName + '\'' +
				", codeDesc:'" + codeDesc + '\'' +
				", codeSeq:" + codeSeq +
				", codeStatus:" + codeStatus +
				", codeExt1:'" + codeExt1 + '\'' +
				", codeExt2:'" + codeExt2 + '\'' +
				", codeExt3:'" + codeExt3 + '\'' +
				", codeExt4:'" + codeExt4 + '\'' +
				", children:" + children +
				'}';
	}
}