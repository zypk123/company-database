package cy.its.service.export;

public class FieldDescription {
	private String Description;

	private String Header;

	private String BindField;

	private String ExportField;

	private String FormatString;

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getBindField() {
		return BindField;
	}

	public void setBindField(String bindField) {
		BindField = bindField;
	}

	public String getExportField() {
		return ExportField;
	}

	public void setExportField(String exportField) {
		ExportField = exportField;
	}

	public String getFormatString() {
		return FormatString;
	}

	public void setFormatString(String formatString) {
		FormatString = formatString;
	}

}
