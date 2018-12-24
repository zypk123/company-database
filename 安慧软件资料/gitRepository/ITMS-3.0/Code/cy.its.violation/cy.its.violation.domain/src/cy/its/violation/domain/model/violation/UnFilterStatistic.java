package cy.its.violation.domain.model.violation;

public class UnFilterStatistic {
	private String violationType;
	private Long d15;
	private Long d7;
	private Long d3;
	private Long d2;
	private Long d1;

	public String getViolationType() {
		return violationType;
	}

	public void setViolationType(String violationType) {
		this.violationType = violationType;
	}

	public Long getD15() {
		if (d15 == null) {
			d15 = 0l;
		}
		return d15;
	}

	public void setD15(Long d15) {
		this.d15 = d15;
	}

	public Long getD7() {
		if (d7 == null) {
			d7 = 0l;
		}
		return d7;
	}

	public void setD7(Long d7) {
		this.d7 = d7;
	}

	public Long getD3() {
		if (d3 == null) {
			d3 = 0l;
		}
		return d3;
	}

	public void setD3(Long d3) {
		this.d3 = d3;
	}

	public Long getD2() {
		if (d2 == null) {
			d2 = 0l;
		}
		return d2;
	}

	public void setD2(Long d2) {
		this.d2 = d2;
	}

	public Long getD1() {
		if (d1 == null) {
			d1 = 0l;
		}
		return d1;
	}

	public void setD1(Long d1) {
		this.d1 = d1;
	}
}
