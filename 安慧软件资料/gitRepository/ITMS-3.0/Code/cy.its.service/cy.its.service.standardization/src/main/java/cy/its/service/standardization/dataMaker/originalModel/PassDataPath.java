package cy.its.service.standardization.dataMaker.originalModel;

import java.util.Map;


public class PassDataPath extends BaseOrginalModel {

	/**
	 * 设备编号
	 */
	private String deviceNbr;

	/**
	 * 设备编号
	 */
	private String deviceKey;

	/**
	 * 抓拍编号
	 */
	private String snapNbr;

	/**
	 * 过车时间
	 */
	private String passTime;

	/**
	 * 过车传输经过各节点时间路径 
	 * key-经过节点时间关键字; value-经过节点时间,格式:yyyy-MM-dd HH:mm:ss.fff
	 * key取值:
	 *  serverReceivingTime:       监控服务器处理时间
	 *  afterserverReceivingTime:  后置监控服务器处理时间（仅有监控服务器级联时有值）
	 *  ice2mqTime:                ICE2MQ处理时间
	 *  preTime:                   前置机处理时间 
	 *  afterTime:                 后置机处理时间 
	 *  uploadTime:                上传开始时间 
	 *  uploadEndTime:             上传结束时间
	 */
	private Map<String, String> dataTimePath;

//	/**
//	 * 总耗时，单位为秒。
//	 */
//	private int totalTime;

	public String getDeviceNbr() {
		return deviceNbr;
	}

	public void setDeviceNbr(String deviceNbr) {
		this.deviceNbr = deviceNbr;
	}

	public String getDeviceKey() {
		return deviceKey;
	}

	public void setDeviceKey(String deviceKey) {
		this.deviceKey = deviceKey;
	}

	public String getSnapNbr() {
		return snapNbr;
	}

	public void setSnapNbr(String snapNbr) {
		this.snapNbr = snapNbr;
	}

	public String getPassTime() {
		return passTime;
	}

	public void setPassTime(String passTime) {
		this.passTime = passTime;
	}

	public Map<String, String> getDataTimePath() {
		return dataTimePath;
	}

	public void setDataTimePath(Map<String, String> dataTimePath) {
		this.dataTimePath = dataTimePath;
	}

//	public int getTotalTime() {
//		return totalTime;
//	}
//
//	public void setTotalTime(int totalTime) {
//		this.totalTime = totalTime;
//	}
}
