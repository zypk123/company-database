package com.huntkey.rx.sceo.common.service.common.model;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Max;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class Menu implements Serializable{
	private static final long serialVersionUID = 560235336265922136L;
	
	@Length(min=0,max=32)
	private String id;
	@NotEmpty
	@Length(min=1,max=32)
	private String parentId;	//上级菜单
	@NotEmpty
	@Length(min=1,max=32)
	private String parentIds;	//上级菜单IDS
	@NotEmpty
	@Length(min=1,max=512)
	private String permission; //权限标识
	@NotEmpty
	@Length(min=1,max=32)
	private String name;		//菜单名
	@Length(min=0,max=64)
	private String href;		//链接
	@Length(min=0,max=64)
	private String prevHref;		//链接前缀链接
	@Length(min=0,max=64)
	private String icon; //菜单图标
	@Max(2147483647)
	private int sort;			//排序
	@NotEmpty
	@Length(min=1,max=1)
	private String showFlag;	//是否显示
	private String deleteFlag;	//删除标记
	@Length(min=0,max=512)
	private String remark; //备注
	private List<Menu> childList; //子菜单
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getParentIds() {
		return parentIds;
	}
	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getPrevHref() {
		return prevHref;
	}
	public void setPrevHref(String prevHref) {
		this.prevHref = prevHref;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getShowFlag() {
		return showFlag;
	}
	public void setShowFlag(String showFlag) {
		this.showFlag = showFlag;
	}
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public List<Menu> getChildList() {
		return childList;
	}
	public void setChildList(List<Menu> childList) {
		this.childList = childList;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "Menu [id=" + id + ", parentId=" + parentId + ", parentIds=" + parentIds + ", name=" + name + ", href="
				+ href + ", sort=" + sort + ", showFlag=" + showFlag + ", deleteFlag=" + deleteFlag + ", childList="
				+ childList + "]";
	}
}
