package cy.its.com.domain;

import cy.its.com.util.StringUtil;

public class Criteria {

	/**
	 * 页数 从1开始递增计数
	 */
	int pageNum = 1;

	/**
	 * 页面大小 从1开始递增计数
	 */
	int pageSize = Integer.MAX_VALUE;

	/**
	 * 总记录数
	 */
	long total = Integer.MIN_VALUE;

	private String orderName;
	
	protected String orderType = "asc";

	/**
	 * 是否需要统计总记录数
	 */
	boolean needTotal = false;

	public void setNeedTotal(boolean needTotal) {
		this.needTotal = needTotal;
	}

	public boolean getNeedTotal() {
		return this.needTotal;
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

	public int getFromIndex() {
		return pageNum * pageSize - pageSize;
	}

	public int getToIndex() {
		return pageNum * pageSize - 1;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long l) {
		this.total = l;
	}

	public void setNoPage() {
		this.setPageNum(1);
		this.setPageSize(Integer.MAX_VALUE);
	}

	public String getOrderName() {
		if(!StringUtil.isNullOrEmpty(this.orderName)){
			return camelToUnderline(this.orderName);
		}
		return this.orderName;
	}

	public void setOrderName(String order) {
		this.orderName = camelToUnderline(order);;
	}

	private final char UNDERLINE = '_';

	private String camelToUnderline(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (Character.isUpperCase(c)) {
				sb.append(UNDERLINE);
				sb.append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
	
}
