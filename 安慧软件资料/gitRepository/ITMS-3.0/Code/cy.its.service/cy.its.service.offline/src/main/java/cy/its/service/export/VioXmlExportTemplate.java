package cy.its.service.export;

import java.util.List;

public class VioXmlExportTemplate {

	private String baseFileFormat;

	private String encoding;

	private String warpNodeName;

	private String txtFileSuffix;

	private String txtFileMulti;

	public String getBaseFileFormat() {
		return baseFileFormat;
	}

	public void setBaseFileFormat(String baseFileFormat) {
		this.baseFileFormat = baseFileFormat;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getWarpNodeName() {
		return warpNodeName;
	}

	public void setWarpNodeName(String warpNodeName) {
		this.warpNodeName = warpNodeName;
	}

	public String getTxtFileSuffix() {
		return txtFileSuffix;
	}

	public void setTxtFileSuffix(String txtFileSuffix) {
		this.txtFileSuffix = txtFileSuffix;
	}

	public String getTxtFileMulti() {
		return txtFileMulti;
	}

	public void setTxtFileMulti(String txtFileMulti) {
		this.txtFileMulti = txtFileMulti;
	}

	public String getImageStartIndex() {
		return imageStartIndex;
	}

	public void setImageStartIndex(String imageStartIndex) {
		this.imageStartIndex = imageStartIndex;
	}

	public String getImageCompose() {
		return imageCompose;
	}

	public void setImageCompose(String imageCompose) {
		this.imageCompose = imageCompose;
	}

	public List<FieldDescription> getFieldDescriptions() {
		return fieldDescriptions;
	}

	public void setFieldDescriptions(List<FieldDescription> fieldDescriptions) {
		this.fieldDescriptions = fieldDescriptions;
	}

	private String imageStartIndex;

	private String imageCompose;

	private List<FieldDescription> fieldDescriptions;

}
