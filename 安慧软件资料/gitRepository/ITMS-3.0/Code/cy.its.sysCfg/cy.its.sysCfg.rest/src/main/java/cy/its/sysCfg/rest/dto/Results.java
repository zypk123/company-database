package cy.its.sysCfg.rest.dto;

import java.util.List;

public class Results<T> {
	
	private long total;
	private List<T> rows;

	public Results() {
	}

	public Results(long total, List<T> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}
