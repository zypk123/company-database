package cy.its.device.domain.model;
public class TableSpaceMonitor {
	private String tableSpaceName;		//表空间名
	private double tableSpaceSize;		//表空间大小(M)
	private double usedSpace;				//已使用空间(M)
	private String useLevel;			//使用比(%)
	private double freeSpace;				//空闲空间(M)
	private double largestChunk;			//最大块(M)
	
	
	public String getTableSpaceName() {
		return tableSpaceName;
	}
	public void setTableSpaceName(String tableSpaceName) {
		this.tableSpaceName = tableSpaceName;
	}
	public double getTableSpaceSize() {
		return tableSpaceSize;
	}
	public void setTableSpaceSize(double tableSpaceSize) {
		this.tableSpaceSize = tableSpaceSize;
	}
	public double getUsedSpace() {
		return usedSpace;
	}
	public void setUsedSpace(double usedSpace) {
		this.usedSpace = usedSpace;
	}
	public String getUseLevel() {
		return useLevel;
	}
	public void setUseLevel(String useLevel) {
		this.useLevel = useLevel;
	}
	public double getFreeSpace() {
		return freeSpace;
	}
	public void setFreeSpace(double freeSpace) {
		this.freeSpace = freeSpace;
	}
	public double getLargestChunk() {
		return largestChunk;
	}
	public void setLargestChunk(double largestChunk) {
		this.largestChunk = largestChunk;
	}
}
