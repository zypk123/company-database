package cy.its.service.standardization.dataMaker.originalModel;

import java.util.Map;

public class FrontTrafficEvent extends BaseOrginalModel{
	
	private String eventId;

    private String deviceNo;

    private String snapNbr;

    private String addr;

    private String time;

    private EventType type;

    private int objPos;

    private int objSize;

    private String desc;

    private RecordInfo[] records;

    private VehicleImageDescription[] imageDescriptions;

    private Map<String, String> extendedProperties;

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public String getSnapNbr() {
		return snapNbr;
	}

	public void setSnapNbr(String snapNbr) {
		this.snapNbr = snapNbr;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public int getObjPos() {
		return objPos;
	}

	public void setObjPos(int objPos) {
		this.objPos = objPos;
	}

	public int getObjSize() {
		return objSize;
	}

	public void setObjSize(int objSize) {
		this.objSize = objSize;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public RecordInfo[] getRecords() {
		return records;
	}

	public void setRecords(RecordInfo[] records) {
		this.records = records;
	}

	public VehicleImageDescription[] getImageDescriptions() {
		return imageDescriptions;
	}

	public void setImageDescriptions(VehicleImageDescription[] imageDescriptions) {
		this.imageDescriptions = imageDescriptions;
	}

	public java.util.Map<String, String> getExtendedProperties() {
		return extendedProperties;
	}

	public void setExtendedProperties(java.util.Map<String, String> extendedProperties) {
		this.extendedProperties = extendedProperties;
	}
}
