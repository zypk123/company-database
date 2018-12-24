package cy.its.device.rest.dto;

import cy.its.com.dto.BaseDto;

/**
* @Title: DeviceQryForMapDto.java 
* @Package cy.its.device.rest.dto 
* @Description: 地图上设备查询条件DTO 
* @author chenzhiying
* @date 2015年10月13日 下午3:46:03  
* @version V3.0 
 */
public class DeviceQryForMapDto  extends BaseDto{
 
 private Boolean isNeedCluster;
 private Double left;
 private Double bottom;
 private Double right;
 private Double top;

public Boolean getIsNeedCluster() {
	return isNeedCluster;
}
public void setIsNeedCluster(Boolean isNeedCluster) {
	this.isNeedCluster = isNeedCluster;
}
public Double getLeft() {
	return left;
}
public void setLeft(Double left) {
	this.left = left;
}
public Double getBottom() {
	return bottom;
}
public void setBottom(Double bottom) {
	this.bottom = bottom;
}
public Double getRight() {
	return right;
}
public void setRight(Double right) {
	this.right = right;
}
public Double getTop() {
	return top;
}
public void setTop(Double top) {
	this.top = top;
}
 
}
