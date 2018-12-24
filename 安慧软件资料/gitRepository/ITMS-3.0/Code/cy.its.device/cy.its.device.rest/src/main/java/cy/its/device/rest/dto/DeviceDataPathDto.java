package cy.its.device.rest.dto;

import java.util.Date;

import cy.its.com.dto.BaseDto;

public class DeviceDataPathDto extends BaseDto{
		/*****查询的条件字段********/
		// 道路代码
		private String roadCode;
		
		// 设备编号
		private String deviceSysNbr;
		
		// 点位代码
		private String siteCode;
		
		// 平均延迟 范围 最小值
		private Integer minDelay;
		
		// 平均延迟 范围 最小值
		private Integer maxDelay;
		
		// 起始过车时间
		private String passTimeFrom;
		
		// 结束过车时间
		private String passTimeTo;
		
		// 机构权限代码
		private String orgPrivilegeCode;
	
		//道路类型
		private String roadType;
	
		//道路ID
		private String roadId;
		
		/**
		 * 是否接入稽查布控系统
		 * 0 : 否
		 * 1 : 是
		 * 2 : 全
		 */
		private int isConnectTrackSys;
		/*****数据统计展示字段********/

	    private String roadName;

	    private String siteName;

	    private String direction;

	    private String orgName;

	    private Integer uploadTotal;

	    private Integer avgDelay;
	    
	    private String connectTrackSys;
	    /*****单独设备时间统计字段********/
	    /**
	    * 设备到前置监控服务器时间延迟
	    */
	    private Integer dev2svrDelay;
	    
	    /**
	    * 设备到监控服务器时间延迟（没有前置监控服务器节点）
	    */
	    private Integer dev2server;

	    /**
	    * 前置监控服务器到后置监控服务器时间延迟
	    */
	    private Integer svr2afsvrDelay;

	    /**
	    * 后置监控服务器到ICE2MQ时间延迟
	    */
	    private Integer afsvr2imDelay;
	    
	    /**
	    * ICE2MQ到上传开始时间延迟
	    */	
	    private Integer im2updsDelay;
	    /**
	    * 上传开始到上传结束时间延迟
	    */
	    private Integer upds2updeDelay;
	    
	    /**
	    * ICE2MQ到管控平台的时间延迟
	    */
	    private Integer im2Itms;
	    
	    
	    
	    private String deviceName;
	    
		/*****查询的条件字段********/
		public String getRoadCode() {
			return roadCode;
		}

		public void setRoadCode(String roadCode) {
			this.roadCode = roadCode;
		}

		public String getDeviceSysNbr() {
			return deviceSysNbr;
		}

		public void setDeviceSysNbr(String deviceSysNbr) {
			this.deviceSysNbr = deviceSysNbr;
		}

		public String getSiteCode() {
			return siteCode;
		}

		public void setSiteCode(String siteCode) {
			this.siteCode = siteCode;
		}

		public Integer getMinDelay() {
			return minDelay;
		}

		public void setMinDelay(Integer minDelay) {
			this.minDelay = minDelay;
		}

		public Integer getMaxDelay() {
			return maxDelay;
		}

		public void setMaxDelay(Integer maxDelay) {
			this.maxDelay = maxDelay;
		}

		public String getPassTimeFrom() {
			return passTimeFrom;
		}

		public void setPassTimeFrom(String passTimeFrom) {
			this.passTimeFrom = passTimeFrom;
		}

		public String getPassTimeTo() {
			return passTimeTo;
		}

		public void setPassTimeTo(String passTimeTo) {
			this.passTimeTo = passTimeTo;
		}

		public String getOrgPrivilegeCode() {
			return orgPrivilegeCode;
		}

		public void setOrgPrivilegeCode(String orgPrivilegeCode) {
			this.orgPrivilegeCode = orgPrivilegeCode;
		}
		
		public String getRoadType() {
			return roadType;
		}

		public void setRoadType(String roadType) {
			this.roadType = roadType;
		}

		public String getRoadId() {
			return roadId;
		}

		public void setRoadId(String roadId) {
			this.roadId = roadId;
		}
		
		public int getIsConnectTrackSys() {
			return isConnectTrackSys;
		}

		public void setIsConnectTrackSys(int isConnectTrackSys) {
			this.isConnectTrackSys = isConnectTrackSys;
		}

		/*****数据统计展示字段********/

		public String getConnectTrackSys() {
			return connectTrackSys;
		}

		public void setConnectTrackSys(String connectTrackSys) {
			this.connectTrackSys = connectTrackSys;
		}

		public String getRoadName() {
			return roadName;
		}

		public void setRoadName(String roadName) {
			this.roadName = roadName;
		}

		public String getSiteName() {
			return siteName;
		}

		public void setSiteName(String siteName) {
			this.siteName = siteName;
		}

		public String getDirection() {
			return direction;
		}

		public void setDirection(String direction) {
			this.direction = direction;
		}

		public String getOrgName() {
			return orgName;
		}

		public void setOrgName(String orgName) {
			this.orgName = orgName;
		}

		public Integer getUploadTotal() {
			return uploadTotal;
		}

		public void setUploadTotal(Integer uploadTotal) {
			this.uploadTotal = uploadTotal;
		}

		public Integer getAvgDelay() {
			return avgDelay;
		}

		public void setAvgDelay(Integer avgDelay) {
			this.avgDelay = avgDelay;
		}

		public Integer getDev2svrDelay() {
			return dev2svrDelay;
		}

		public void setDev2svrDelay(Integer dev2svrDelay) {
			this.dev2svrDelay = dev2svrDelay;
		}

		public Integer getSvr2afsvrDelay() {
			return svr2afsvrDelay;
		}

		public void setSvr2afsvrDelay(Integer svr2afsvrDelay) {
			this.svr2afsvrDelay = svr2afsvrDelay;
		}

		public Integer getAfsvr2imDelay() {
			return afsvr2imDelay;
		}

		public void setAfsvr2imDelay(Integer afsvr2imDelay) {
			this.afsvr2imDelay = afsvr2imDelay;
		}

		public Integer getIm2updsDelay() {
			return im2updsDelay;
		}

		public void setIm2updsDelay(Integer im2updsDelay) {
			this.im2updsDelay = im2updsDelay;
		}

		public Integer getUpds2updeDelay() {
			return upds2updeDelay;
		}

		public void setUpds2updeDelay(Integer upds2updeDelay) {
			this.upds2updeDelay = upds2updeDelay;
		}
		
		public Integer getIm2Itms() {
			return im2Itms;
		}

		public void setIm2Itms(Integer im2Itms) {
			this.im2Itms = im2Itms;
		}

		public String getDeviceName() {
			return deviceName;
		}

		public void setDeviceName(String deviceName) {
			this.deviceName = deviceName;
		}

		public Integer getDev2server() {
			return dev2server;
		}

		public void setDev2server(Integer dev2server) {
			this.dev2server = dev2server;
		}
		
}
