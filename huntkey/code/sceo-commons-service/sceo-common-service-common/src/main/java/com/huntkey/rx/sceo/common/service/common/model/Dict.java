package com.huntkey.rx.sceo.common.service.common.model;

import java.io.Serializable;

import javax.validation.constraints.Max;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Component("Dict")
public class Dict implements Serializable{
	
	private static final long serialVersionUID = 4425183616064301214L;
	public final static String DICT_DEFAULT = "1"; //字典
	public final static String DICT_VALUE = "2"; //字典值
	public final static String DICT_TREE = "3"; //树
	
	@Length(min=0,max=32)
	private String id;
	
	@NotEmpty
	@Length(min=1,max=32)
	private String code;
	
	@Length(min=0,max=32)
	private String value;
	
	@NotEmpty
	@Length(min=1,max=32)
	private String label;
	
	@NotEmpty
	@Length(min=1,max=1)
	private String type;
	
	@NotEmpty
	@Length(min=1,max=32)
	private String parentId;
	@Max(2147483647)
	private int sort;
	private String creator;
	@Length(min=0,max=512)
	private String remark;
	private String deleteFlag;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Override
	public String toString() {
		return "Dict{" +
				"id='" + id + '\'' +
				", code='" + code + '\'' +
				", value='" + value + '\'' +
				", label='" + label + '\'' +
				", type='" + type + '\'' +
				", parentId='" + parentId + '\'' +
				", sort=" + sort +
				", creator='" + creator + '\'' +
				", remark='" + remark + '\'' +
				", deleteFlag='" + deleteFlag + '\'' +
				'}';
	}
}
