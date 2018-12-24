package cy.its.violation.domain.model.violation;

public class FilterStatistic {
	private String status_flag;

	private String discarded_reason_name;

	private Long total;

	public String getStatus_flag() {
		return status_flag;
	}

	public void setStatus_flag(String status_flag) {
		this.status_flag = status_flag;
	}

	public String getDiscarded_reason_name() {
		return discarded_reason_name;
	}

	public void setDiscarded_reason_name(String discarded_reason_name) {
		this.discarded_reason_name = discarded_reason_name;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
}
