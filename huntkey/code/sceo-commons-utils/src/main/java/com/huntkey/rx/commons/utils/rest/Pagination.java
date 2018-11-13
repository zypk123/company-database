package com.huntkey.rx.commons.utils.rest;

import java.util.List;

/**
 * 
 * @ClassName: Pagination
 * @Description: 分页类
 * @author: zhangyu
 * @date: 2017年4月13日上午10:03:37
 *
 */
public class Pagination<T> {

	private int pageNum; // 当前页
	private int pageSize;// 每页的大小
	private long total; // 总记录数
	private List<T> list;

	public Pagination(List<T> list, int pageNum, int pageSize, long total) {
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.total = total;
		this.list = list;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "{" + "pageNum:" + pageNum + ", pageSize:" + pageSize + ", total:" + total + ", list:" + list + '}';
	}

}
