package com.huntkey.rx.sceo.serviceCenter.common.model;

/**
 * 分页对象 Created by kongquan on 2017/8/10.
 */
public class PagenationNode {

	private int startPage;
	private int rows;

	public PagenationNode(int startPage, int rows) {
		this.startPage = startPage;
		this.rows = rows;
		validate();
	}

	// 校验参数是否有效
	private void validate() {
		if (this.rows <= 0) {
			throw new IllegalArgumentException("argument rows must be greater than 0!");
		}

		if (this.startPage <= 0) {
			throw new IllegalArgumentException("argument startPage must be greater than 0!");
		}
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

}
