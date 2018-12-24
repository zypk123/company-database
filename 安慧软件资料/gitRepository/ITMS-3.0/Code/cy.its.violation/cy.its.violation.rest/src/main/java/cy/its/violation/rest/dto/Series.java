package cy.its.violation.rest.dto;

import java.util.List;

public class Series {
	public String name;
	public String type;
	public List data;
	public MarkPoint markPoint;

	public Series(String name, String type, List list, MarkPoint markPoint) {
		this.name = name;
		this.type = type;
		this.data = list;
		// this.markPoint=markPoint;
		// this.markLine=markLine;
	}

	public Series() {

	}
}
