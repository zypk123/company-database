package com.huntkey.rx.sceo.serviceCenter.common.model;

import com.huntkey.rx.sceo.serviceCenter.common.emun.SortType;

/**
 * Created by kongquan on 2017/8/10.
 */
public class SortNode {

	// 排序字段
	private String attr;

	// 排序 asc 升序 desc 降序
	private String sort;

	public SortNode(String attr, SortType sortType) {
		this.attr = attr;
		this.sort = sortType.getValue();
	}

	public String getAttr() {
		return attr;
	}

	public void setAttr(String attr) {
		this.attr = attr;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

}
