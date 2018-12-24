/**
 * @Title: WhiteListDto.java
 * @Package cy.its.violation.rest.dto
 * @Description: TODO(这里要填写用途)
 * @author wangyf wangyf@cychina.cn
 * @date 2015年11月4日 下午3:01:45
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.violation.rest.dto;

/**
  * @ClassName: WhiteListDto
  * @Description: TODO(这里要填写用途)
  * @author wangyf wangyf@cychina.cn
  * @date 2015年11月4日 下午3:01:45
  *
  */

public class WhiteListDto {
	//车辆Id
	private String vehicleId;
	//号牌号码
	private String plateNbr;
	//号牌类型
	private String plateType;
	//号牌颜色
	private String plateColor;
	//车辆品牌
	private String vehicleBrand;
	//车辆型号
	private String vehicleModel;
	//车辆颜色 
	private String vehicleColor;
	//管理部门
	private String orgId;
	//机动车所有人
	private String vehicleOwner;
	//机动车联系人
	private String vehicleContactor;
	//机动车联系人手机
	private String vehicleContactorTel;
	//数据来源
	private String dataSource;
	//免除标记
	private String noPunishFlag;
	//累计免处罚次数
	private String noPunishCounts;
	//累计免扣分
	private String noPunishScore;
	//累计免处罚金额
	private String noPunishMoney;
	//查车保护标记
	private String queryProtectFlag;
	//有效日期
	private String validEndDate;
	//备注
	private String remark;
	
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getPlateNbr() {
		return plateNbr;
	}
	public void setPlateNbr(String plateNbr) {
		this.plateNbr = plateNbr;
	}
	public String getPlateType() {
		return plateType;
	}
	public void setPlateType(String plateType) {
		this.plateType = plateType;
	}
	public String getPlateColor() {
		return plateColor;
	}
	public void setPlateColor(String plateColor) {
		this.plateColor = plateColor;
	}
	public String getVehicleBrand() {
		return vehicleBrand;
	}
	public void setVehicleBrand(String vehicleBrand) {
		this.vehicleBrand = vehicleBrand;
	}
	public String getVehicleModel() {
		return vehicleModel;
	}
	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}
	public String getVehicleColor() {
		return vehicleColor;
	}
	public void setVehicleColor(String vehicleColor) {
		this.vehicleColor = vehicleColor;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getVehicleOwner() {
		return vehicleOwner;
	}
	public void setVehicleOwner(String vehicleOwner) {
		this.vehicleOwner = vehicleOwner;
	}
	public String getVehicleContactor() {
		return vehicleContactor;
	}
	public void setVehicleContactor(String vehicleContactor) {
		this.vehicleContactor = vehicleContactor;
	}
	public String getVehicleContactorTel() {
		return vehicleContactorTel;
	}
	public void setVehicleContactorTel(String vehicleContactorTel) {
		this.vehicleContactorTel = vehicleContactorTel;
	}
	public String getDataSource() {
		return dataSource;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	public String getNoPunishFlag() {
		return noPunishFlag;
	}
	public void setNoPunishFlag(String noPunishFlag) {
		this.noPunishFlag = noPunishFlag;
	}
	public String getNoPunishCounts() {
		return noPunishCounts;
	}
	public void setNoPunishCounts(String noPunishCounts) {
		this.noPunishCounts = noPunishCounts;
	}
	public String getNoPunishScore() {
		return noPunishScore;
	}
	public void setNoPunishScore(String noPunishScore) {
		this.noPunishScore = noPunishScore;
	}
	public String getNoPunishMoney() {
		return noPunishMoney;
	}
	public void setNoPunishMoney(String noPunishMoney) {
		this.noPunishMoney = noPunishMoney;
	}
	
	public String getQueryProtectFlag() {
		return queryProtectFlag;
	}
	public void setQueryProtectFlag(String queryProtectFlag) {
		this.queryProtectFlag = queryProtectFlag;
	}
	public String getValidEndDate() {
		return validEndDate;
	}
	public void setValidEndDate(String validEndDate) {
		this.validEndDate = validEndDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
