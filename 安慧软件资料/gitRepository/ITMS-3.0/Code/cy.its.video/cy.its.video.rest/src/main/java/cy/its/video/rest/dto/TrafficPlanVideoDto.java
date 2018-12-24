package cy.its.video.rest.dto;

import cy.its.com.dto.BaseDto;

/**
 * 网巡预案视频
 * 
 * @author Administrator
 *
 */
public class TrafficPlanVideoDto extends BaseDto {

	private String relatedVideoId;

	private String videoPlanId;

	private String deviceId;

	private Short sortIndex;

	private String presetList;

	private String sysComponentId;

	// 对应于视频播放控件的预置位
	private Short preset;

	private Short cruiseTime;

	private String videoDeviceName;

	private String preViewParam;

	private String playBackParam;

	private String presetDesc;

	/**
	 * 根据deviceID生成
	 */
	private String deviceIp;

	/**
	 * 相机服务器ip
	 */
	private String cameraServerTypeIp;

	/**
	 * 根据deviceID生成
	 */
	private String deviceSysNbr;

	/**
	 * 根据deviceID得到siteID再得到siteName
	 */
	private String place;

	private String videoAccessMode;

	private String CameraIp;
	private String CameraPort;
	private String TypeCode;
	private String ConnUser;
	private String ConnPsd;
	private String RegionId;// todo
	private String ServerPort;// todo
	private String ServerUser;// todo
	private String ServerPassword;// todo

	public String getCameraIp() {
		return CameraIp;
	}

	public void setCameraIp(String cameraIp) {
		CameraIp = cameraIp;
	}

	public String getCameraPort() {
		return CameraPort;
	}

	public void setCameraPort(String cameraPort) {
		CameraPort = cameraPort;
	}

	public String getTypeCode() {
		return TypeCode;
	}

	public void setTypeCode(String typeCode) {
		TypeCode = typeCode;
	}

	public String getConnUser() {
		return ConnUser;
	}

	public void setConnUser(String connUser) {
		ConnUser = connUser;
	}

	public String getConnPsd() {
		return ConnPsd;
	}

	public void setConnPsd(String connPsd) {
		ConnPsd = connPsd;
	}

	public String getRegionId() {
		return RegionId;
	}

	public void setRegionId(String regionId) {
		RegionId = regionId;
	}

	public String getServerPort() {
		return ServerPort;
	}

	public void setServerPort(String serverPort) {
		ServerPort = serverPort;
	}

	public String getServerUser() {
		return ServerUser;
	}

	public void setServerUser(String serverUser) {
		ServerUser = serverUser;
	}

	public String getServerPassword() {
		return ServerPassword;
	}

	public void setServerPassword(String serverPassword) {
		ServerPassword = serverPassword;
	}

	public String getVideoAccessMode() {
		return videoAccessMode;
	}

	public void setVideoAccessMode(String videoAccessMode) {
		this.videoAccessMode = videoAccessMode;
	}

	public String getDeviceSysNbr() {
		return deviceSysNbr;
	}

	public void setDeviceSysNbr(String deviceSysNbr) {
		this.deviceSysNbr = deviceSysNbr;
	}

	public String getRelatedVideoId() {
		return relatedVideoId;
	}

	public void setRelatedVideoId(String relatedVideoId) {
		this.relatedVideoId = relatedVideoId;
	}

	public String getVideoPlanId() {
		return videoPlanId;
	}

	public void setVideoPlanId(String videoPlanId) {
		this.videoPlanId = videoPlanId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public Short getSortIndex() {
		return sortIndex;
	}

	public void setSortIndex(Short sortIndex) {
		this.sortIndex = sortIndex;
	}

	public String getPresetList() {
		return presetList;
	}

	public void setPresetList(String presetList) {
		this.presetList = presetList;
	}

	public Short getCruiseTime() {
		return cruiseTime;
	}

	public void setCruiseTime(Short cruiseTime) {
		this.cruiseTime = cruiseTime;
	}

	public String getVideoDeviceName() {
		return videoDeviceName;
	}

	public void setVideoDeviceName(String videoDeviceName) {
		this.videoDeviceName = videoDeviceName;
	}

	public String getDeviceIp() {
		return deviceIp;
	}

	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getCameraServerTypeIp() {
		return cameraServerTypeIp;
	}

	public void setCameraServerTypeIp(String cameraServerTypeIp) {
		this.cameraServerTypeIp = cameraServerTypeIp;
	}

	public String getPreViewParam() {
		return preViewParam;
	}

	public void setPreViewParam(String preViewParam) {
		this.preViewParam = preViewParam;
	}

	public String getPlayBackParam() {
		return playBackParam;
	}

	public void setPlayBackParam(String playBackParam) {
		this.playBackParam = playBackParam;
	}

	public String getPresetDesc() {
		return presetDesc;
	}

	public void setPresetDesc(String presetDesc) {
		this.presetDesc = presetDesc;
	}

	public Short getPreset() {
		return preset;
	}

	public void setPreset(Short preset) {
		this.preset = preset;
	}

	public String getSysComponentId() {
		return sysComponentId;
	}

	public void setSysComponentId(String sysComponentId) {
		this.sysComponentId = sysComponentId;
	}

}
