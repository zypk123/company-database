package cy.its.device.rest.dto;

/**
* @Title: DeviceClusterDto.java 
* @Package cy.its.device.rest.dto 
* @Description: 地图监控，设备聚合DTO
* @author chenzhiying
* @date 2015年10月13日 下午3:09:04  
* @version V3.0 
 */
public class DeviceClusterDto {
	private String orgId;
	private String total;
	private String longitude;
	private String latitude;
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}	
}
