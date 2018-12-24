package cy.its.device.domain.model;

import java.util.List;

public class DataPathDynamicDetail {

	/**
	 * 系统编号
	 */
	private String deviceSysNbr;

	/**
	 * 系统名称
	 */
	private String deviceName;
	
	/**
	 * 平均传输延迟
	 */
	private Integer avgDelay;

	/**
	 * 传输经过节点列表
	 */
	private List<DataPathNode> dataPathNodes;

	public String getDeviceSysNbr() {
		return deviceSysNbr;
	}

	public void setDeviceSysNbr(String deviceSysNbr) {
		this.deviceSysNbr = deviceSysNbr;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public Integer getAvgDelay() {
		return avgDelay;
	}

	public void setAvgDelay(Integer avgDelay) {
		this.avgDelay = avgDelay;
	}

	public List<DataPathNode> getDataPathNodes() {
		return dataPathNodes;
	}

	public void setDataPathNodes(List<DataPathNode> dataPathNodes) {
		this.dataPathNodes = dataPathNodes;
	}	
}


