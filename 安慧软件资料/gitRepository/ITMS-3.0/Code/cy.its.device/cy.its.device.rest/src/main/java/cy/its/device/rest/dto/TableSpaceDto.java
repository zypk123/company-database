package cy.its.device.rest.dto;

public class TableSpaceDto {
	private String tableSpaceName;			//表空间名
	private String tableSpaceSize;			//表空间大小(M)
	private String usedSpace;				//已使用空间(M)
	private String useLevel;				//使用比(%)
	private String freeSpace;				//空闲空间(M)
	private String largestChunk;			//最大块(M)
	
	public String getTableSpaceName() {
		return tableSpaceName;
	}
	public void setTableSpaceName(String tableSpaceName) {
		this.tableSpaceName = tableSpaceName;
	}
	public String getTableSpaceSize() {
		return tableSpaceSize;
	}
	public void setTableSpaceSize(String tableSpaceSize) {
		this.tableSpaceSize = tableSpaceSize;
	}
	public String getUsedSpace() {
		return usedSpace;
	}
	public void setUsedSpace(String usedSpace) {
		this.usedSpace = usedSpace;
	}
	public String getUseLevel() {
		return useLevel;
	}
	public void setUseLevel(String useLevel) {
		this.useLevel = useLevel;
	}
	public String getFreeSpace() {
		return freeSpace;
	}
	public void setFreeSpace(String freeSpace) {
		this.freeSpace = freeSpace;
	}
	public String getLargestChunk() {
		return largestChunk;
	}
	public void setLargestChunk(String largestChunk) {
		this.largestChunk = largestChunk;
	}
}
