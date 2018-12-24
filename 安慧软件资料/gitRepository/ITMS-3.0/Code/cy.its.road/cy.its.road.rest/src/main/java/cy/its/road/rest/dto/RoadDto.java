package cy.its.road.rest.dto;

import java.util.Date;

import cy.its.com.dto.BaseDto;
/**
 * 
  * @ClassName: RoadDto
  * @Description: TODO(这里要填写用途)
  * @author wangyf wangyf@cychina.cn
  * @date 2015年10月27日 下午3:15:46
  *
 */
public class RoadDto extends BaseDto{
	private String roadId;
	//道路代码
    private String roadCode;
    //旧的代码
    private String oldRoadCode;
    //道路名称
    private String roadName;
    //道路类型 
    private String roadType;
    //道路别名
    private String roadAliasName;
	
    public String districtCode;
    //行政区划表
    private String districtCodeList;
    //行政区划详细里程
    private String districtMileage;
    //机构管理
    private String orgId;
    //公路行政等级
    private String roadAdminGrade;
    //超速行为认定方式
    private String vioConfirmModel;
    //道路方向
    private String isOneDirection;
    //上行方向
    private String upDirection;
    //下行方向
    private String downDirection;
    //路面结构
    private String roadStructure;
    //道路地形
    private String roadLandscape;
    //道路线型
    private String roadLinear;
    //道路物理隔离设施
    private String roadIsolation;
    //中央隔离设施
    private String centralIsolation;
    //防护设施类型
    private String protectFacilities;
    //起始里程
    private String roadBeginMileage;
    //结束里程
    private String roadEndMileage;
    //总长
    private String roadLength;
    //起点名称
    private String roadBeginName;
    //终点名称
    private String roadEndName;
    //非城市方向类型
    private String noDirectionTypeCity;
    //城市方向类型
    private String directionTypeCity;
    //机构权限代码
    private String orgPrivilegeCode;
    
	public String getOrgPrivilegeCode() {
		return orgPrivilegeCode;
	}
	public void setOrgPrivilegeCode(String orgPrivilegeCode) {
		this.orgPrivilegeCode = orgPrivilegeCode;
	}
	public String getRoadLength() {
		return roadLength;
	}
	public void setRoadLength(String roadLength) {
		this.roadLength = roadLength;
	}
	
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	public String getRoadId() {
		return roadId;
	}
	public void setRoadId(String roadId) {
		this.roadId = roadId;
	}
	public String getRoadCode() {
		return roadCode;
	}
	public void setRoadCode(String roadCode) {
		this.roadCode = roadCode;
	}
	
	public String getOldRoadCode() {
		return oldRoadCode;
	}
	public void setOldRoadCode(String oldRoadCode) {
		this.oldRoadCode = oldRoadCode;
	}
	public String getRoadName() {
		return roadName;
	}
	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}
	
	public String getRoadAliasName() {
		return roadAliasName;
	}
	public void setRoadAliasName(String roadAliasName) {
		this.roadAliasName = roadAliasName;
	}
	public String getRoadType() {
		return roadType;
	}
	public void setRoadType(String roadType) {
		this.roadType = roadType;
	}
	public String getVioConfirmModel() {
		return vioConfirmModel;
	}
	public void setVioConfirmModel(String vioConfirmModel) {
		this.vioConfirmModel = vioConfirmModel;
	}
	public String getDistrictCodeList() {
		return districtCodeList;
	}
	public void setDistrictCodeList(String districtCodeList) {
		this.districtCodeList = districtCodeList;
	}
	public String getDistrictMileage() {
		return districtMileage;
	}
	public void setDistrictMileage(String districtMileage) {
		this.districtMileage = districtMileage;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getRoadAdminGrade() {
		return roadAdminGrade;
	}
	public void setRoadAdminGrade(String roadAdminGrade) {
		this.roadAdminGrade = roadAdminGrade;
	}
	public String getIsOneDirection() {
		return isOneDirection;
	}
	public void setIsOneDirection(String isOneDirection) {
		this.isOneDirection = isOneDirection;
	}
	public String getUpDirection() {
		return upDirection;
	}
	public void setUpDirection(String upDirection) {
		this.upDirection = upDirection;
	}
	public String getDownDirection() {
		return downDirection;
	}
	public void setDownDirection(String downDirection) {
		this.downDirection = downDirection;
	}
	public String getRoadStructure() {
		return roadStructure;
	}
	public void setRoadStructure(String roadStructure) {
		this.roadStructure = roadStructure;
	}
	public String getRoadLandscape() {
		return roadLandscape;
	}
	public void setRoadLandscape(String roadLandscape) {
		this.roadLandscape = roadLandscape;
	}
	public String getRoadLinear() {
		return roadLinear;
	}
	public void setRoadLinear(String roadLinear) {
		this.roadLinear = roadLinear;
	}
	public String getRoadIsolation() {
		return roadIsolation;
	}
	public void setRoadIsolation(String roadIsolation) {
		this.roadIsolation = roadIsolation;
	}
	public String getCentralIsolation() {
		return centralIsolation;
	}
	public void setCentralIsolation(String centralIsolation) {
		this.centralIsolation = centralIsolation;
	}
	public String getProtectFacilities() {
		return protectFacilities;
	}
	public void setProtectFacilities(String protectFacilities) {
		this.protectFacilities = protectFacilities;
	}
	public String getRoadBeginMileage() {
		return roadBeginMileage;
	}
	public void setRoadBeginMileage(String roadBeginMileage) {
		this.roadBeginMileage = roadBeginMileage;
	}
	public String getRoadEndMileage() {
		return roadEndMileage;
	}
	public void setRoadEndMileage(String roadEndMileage) {
		this.roadEndMileage = roadEndMileage;
	}
	public String getRoadBeginName() {
		return roadBeginName;
	}
	public void setRoadBeginName(String roadBeginName) {
		this.roadBeginName = roadBeginName;
	}
	public String getRoadEndName() {
		return roadEndName;
	}
	public void setRoadEndName(String roadEndName) {
		this.roadEndName = roadEndName;
	}
	
	public String getNoDirectionTypeCity() {
		return noDirectionTypeCity;
	}
	public void setNoDirectionTypeCity(String noDirectionTypeCity) {
		this.noDirectionTypeCity = noDirectionTypeCity;
	}
	public String getDirectionTypeCity() {
		return directionTypeCity;
	}
	public void setDirectionTypeCity(String directionTypeCity) {
		this.directionTypeCity = directionTypeCity;
	}
	
  
    
}