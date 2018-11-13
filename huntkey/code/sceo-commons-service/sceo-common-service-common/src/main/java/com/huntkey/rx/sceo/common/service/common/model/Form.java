package com.huntkey.rx.sceo.common.service.common.model;

public class Form {
	public static enum FormType {form,temp};
	private String id;// ID
	private String name;// '表单名',
	private FormType type;// '类型：模板（temp）表单（form）',
	private String tables;//关联的表,多个表使用英文都好分隔，第一个是主表
	private String form;// '表单json',
	private String creator;// '创建人',
	private String remark;// '备注信息',
	private String deleteFlag;// '删除标记',
	private String formData;// '删除标记',
	private String flowKey;//流程标示
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTables() {
		return tables;
	}
	public void setTables(String tables) {
		this.tables = tables;
	}
	public String getForm() {
		return form;
	}
	public void setForm(String form) {
		this.form = form;
	}
	
	public FormType getType() {
		return type;
	}
	public void setType(FormType type) {
		this.type = type;
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
	public String getFormData() {
		return formData;
	}
	public void setFormData(String formData) {
		this.formData = formData;
	}
	public String getFlowKey() {
		return flowKey;
	}
	public void setFlowKey(String flowKey) {
		this.flowKey = flowKey;
	}
	
	
}
