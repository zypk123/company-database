package cy.its.device.rest.dto;

import cy.its.com.dto.BaseDto;

public class CameraInfoDto extends BaseDto{
	private String deviceId;			//电子监控系统ID
	private String sysComponentId;		//组件ID
	private String sysComponentType;	//组件类型
	private String assembleSerialNbr;	//相机序列号
	private String brand;				//品牌
	private String model;				//型号
	private String directionType;		//方向类型
	private String cameraIp;			//相机IP
	private Long cameraPort;			//相机端口
	private String connChannel;			//通道号
	private String userName;			//用户名
	private String password;			//设备口令（密码）
	private String softwareVersion;		//软件版本
	private String onlineStatus;		//在线状态
	private String statusUpdateTime;	//设备状态更新时间
	private String specification;		//规格
	private String deviceNbr;			//相机编号
	private String componentName;		//相机名称
	private String laneNbr;				//车道编号列表
	private String deviceKey;			//唯一性标识
	private String oldDeviceKey;		//旧的唯一性标识（用于验证判断唯一性）
	
	private String isVideoSupport;		//是否支持视频  1 支持 0 不支持
    private String cameraSn;			//视频平台备案编号 支持视频输出的相机在视频平台中的编号
    private String accessMode;			//接入方式  工控机接入;终端盒接入:终端盒会对其数据进行处理(如改变设备编号)后再转发，
    									//但不处理图片，图片上叠加编号仍为相机上配置的编号;终端盒转发:直接转发;监控服务器直接接入;

    private String videoAccessMode;		//视频接入方式
    
    private String directionName;		//方向名称
	
	
	
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getSysComponentType() {
		return sysComponentType;
	}
	public void setSysComponentType(String sysComponentType) {
		this.sysComponentType = sysComponentType;
	}
	public String getAssembleSerialNbr() {
		return assembleSerialNbr;
	}
	public void setAssembleSerialNbr(String assembleSerialNbr) {
		this.assembleSerialNbr = assembleSerialNbr;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getDirectionType() {
		return directionType;
	}
	public void setDirectionType(String directionType) {
		this.directionType = directionType;
	}
	public String getCameraIp() {
		return cameraIp;
	}
	public void setCameraIp(String cameraIp) {
		this.cameraIp = cameraIp;
	}
	public Long getCameraPort() {
		return cameraPort;
	}
	public void setCameraPort(Long cameraPort) {
		this.cameraPort = cameraPort;
	}
	public String getConnChannel() {
		return connChannel;
	}
	public void setConnChannel(String connChannel) {
		this.connChannel = connChannel;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSoftwareVersion() {
		return softwareVersion;
	}
	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}
	public String getOnlineStatus() {
		return onlineStatus;
	}
	public void setOnlineStatus(String onlineStatus) {
		this.onlineStatus = onlineStatus;
	}
	public String getStatusUpdateTime() {
		return statusUpdateTime;
	}
	public void setStatusUpdateTime(String statusUpdateTime) {
		this.statusUpdateTime = statusUpdateTime;
	}
	public String getSysComponentId() {
		return sysComponentId;
	}
	public void setSysComponentId(String sysComponentId) {
		this.sysComponentId = sysComponentId;
	}
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public String getDeviceNbr() {
		return deviceNbr;
	}
	public void setDeviceNbr(String deviceNbr) {
		this.deviceNbr = deviceNbr;
	}
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	public String getLaneNbr() {
		return laneNbr;
	}
	public void setLaneNbr(String laneNbr) {
		this.laneNbr = laneNbr;
	}
	public String getDeviceKey() {
		return deviceKey;
	}
	public void setDeviceKey(String deviceKey) {
		this.deviceKey = deviceKey;
	}
	public String getOldDeviceKey() {
		return oldDeviceKey;
	}
	public void setOldDeviceKey(String oldDeviceKey) {
		this.oldDeviceKey = oldDeviceKey;
	}
	public String getIsVideoSupport() {
		return isVideoSupport;
	}
	public void setIsVideoSupport(String isVideoSupport) {
		this.isVideoSupport = isVideoSupport;
	}
	public String getCameraSn() {
		return cameraSn;
	}
	public void setCameraSn(String cameraSn) {
		this.cameraSn = cameraSn;
	}
	public String getAccessMode() {
		return accessMode;
	}
	public void setAccessMode(String accessMode) {
		this.accessMode = accessMode;
	}
	public String getVideoAccessMode() {
		return videoAccessMode;
	}
	public void setVideoAccessMode(String videoAccessMode) {
		this.videoAccessMode = videoAccessMode;
	}
	public String getDirectionName() {
		return directionName;
	}
	public void setDirectionName(String directionName) {
		this.directionName = directionName;
	}
	
}
