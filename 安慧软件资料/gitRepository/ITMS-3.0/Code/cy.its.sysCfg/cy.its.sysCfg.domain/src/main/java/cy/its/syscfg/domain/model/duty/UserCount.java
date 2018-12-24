package cy.its.syscfg.domain.model.duty;

public class UserCount {
	
	/**
	 * 统计项
	 */
	private String countInfo;
	
	/**
	 * 统计数
	 */
	private Long count;

	public String getCountInfo() {
		return countInfo;
	}

	public void setCountInfo(String countInfo) {
		this.countInfo = countInfo;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
}
