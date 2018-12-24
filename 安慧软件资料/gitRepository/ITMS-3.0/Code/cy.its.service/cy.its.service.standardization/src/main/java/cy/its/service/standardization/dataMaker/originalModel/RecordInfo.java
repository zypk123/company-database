package cy.its.service.standardization.dataMaker.originalModel;

import java.util.Map;

public class RecordInfo {
	
	private String beginTime;

	private String endTime;

	private String recordId;

	private RecordCodec codec;

	private Map<String, String> extendedProperties;

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public RecordCodec getCodec() {
		return codec;
	}

	public void setCodec(RecordCodec codec) {
		this.codec = codec;
	}

	public Map<String, String> getExtendedProperties() {
		return extendedProperties;
	}

	public void setExtendedProperties(Map<String, String> extendedProperties) {
		this.extendedProperties = extendedProperties;
	}
}
