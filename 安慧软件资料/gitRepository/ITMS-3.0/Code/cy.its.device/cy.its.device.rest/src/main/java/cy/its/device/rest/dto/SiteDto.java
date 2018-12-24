package cy.its.device.rest.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import cy.its.com.dto.BaseDto;
import cy.its.com.util.StringUtil;
import cy.its.device.domain.criteria.SiteCriteria;
import cy.its.device.domain.model.site.Site;

public class SiteDto extends BaseDto{
	
	/**
	 * 转化成查询条件对象
	 * @return 点位查询条件对象
	 */
	public SiteCriteria convertToCriteria(){
		SiteCriteria criteria = new SiteCriteria();
		criteria.setNeedTotal(true);
		criteria.setPageSize(this.getPageSize());
		criteria.setPageNum(this.getPageNumber());
		criteria.districtCode = this.districtCode;
		criteria.setOrderName(this.getOrderName());
		criteria.setOrderType(this.getOrderType());
		List<String> orgIdList = new ArrayList<String>();
		if(!StringUtil.isNullOrEmpty(this.getOrgIds())){
			String[] orgIds = this.getOrgIds().split(",");
			for(String id : orgIds){
				orgIdList.add(id);
			}
			criteria.orgIds = orgIdList;
		}
		criteria.roadId = this.roadId;
		criteria.siteCode = this.siteCode;
		criteria.siteName = this.siteName;
		criteria.orgPrvCode = this.getCurrentOrgPrivilegeCode();
		return criteria;
	}
	
	/**
	 * 转化成领域对象
	 * @return
	 */
	public Site convertToModel(){
		Site site = new Site();
		site.setCrossId(this.crossId);
		site.setDistrictCode(this.districtCode);
		site.setMileage(this.mileage);
		if(this.kilomileage != null && !"".equals(this.kilomileage)){
			site.setKilomileage(Integer.parseInt(this.kilomileage));
		}
		site.setOrgId(this.orgId);
		site.setRemark(this.remark);
		site.setRoadId(this.roadId);
		site.setRoadSectionId(this.roadSectionId);
		site.setSiteCode(this.siteCode);
		site.setSiteId(this.siteId);
		site.setSiteLandscape(this.siteLandscape);
		if(!StringUtil.isNullOrEmpty(this.siteLatitude)){
			site.setSiteLatitude(new BigDecimal(this.siteLatitude));
		}
		if(!StringUtil.isNullOrEmpty(this.siteLongitude)){
			site.setSiteLongitude(new BigDecimal(this.siteLongitude));
		}
		site.setSiteName(this.siteName);
		site.setSiteSimpleName(this.siteSimpleName);
		site.setSiteType(this.siteType);
		return site;
	}

	/**
	 * 用领域对象来初始化dto
	 * @param site 领域对象
	 */
	public SiteDto(Site site){
		this.siteId = site.getSiteId();
		this.crossId = site.getCrossId();
		this.districtCode = site.getDistrictCode();
//		if(!StringUtil.isNullOrEmpty(site.getMileage())){
//			String data[] = site.getMileage().split("公里\\+");
//			this.km = data[0];
//			this.meter = data[1].substring(0,data[1].lastIndexOf("米"));
//		}
		this.mileage = site.getMileage();
		this.kilomileage = String.valueOf(site.getKilomileage());
		this.orgId = site.getOrgId();
		this.remark = site.getRemark();
		this.roadId = site.getRoadId();
		this.roadSectionId = site.getRoadSectionId();
		this.siteCode = site.getSiteCode();
		this.siteLandscape = site.getSiteLandscape();
		this.siteAliasName = site.getSiteAliasName();
		if(site.getSiteLatitude() != null){
			this.siteLatitude = site.getSiteLatitude().toString();
		}
		if(site.getSiteLongitude() != null){
			this.siteLongitude = site.getSiteLongitude().toString();
		}
		this.siteName = site.getSiteName();
		this.siteSimpleName = site.getSiteSimpleName();
		this.siteType = site.getSiteType();
	}
	
	public String getKilomileage() {
		return kilomileage;
	}

	public void setKilomileage(String kilomileage) {
		this.kilomileage = kilomileage;
	}

	//默认构造方法
	public SiteDto(){
		
	}

	//点位Id
	private String siteId;

	//所属道路Id
	private String roadId;

	//所属路口ID
	private String crossId;
	
	//所属路口名称
	private String crossName;

	//所属路段ID
	private String roadSectionId;
	
	//所属路段名称
	private String roadSectionName;

	//点位编码
	private String siteCode;

	//点位名称
	private String siteName;

	//点位名称简称
	private String siteSimpleName;

	//点位类型
	private String siteType;
	
	//公里数
	private String kilomileage;
	
	//米数
	private String mileage;

	//所属行政区划编码
	private String districtCode;

	//所属机构Id
	private String orgId;
	
	//机构权限过滤代码
	private String orgPrivilegeCode;
	
	//所属机构Id列表，逗号分隔
	private String orgIds;

	//点位经度
	private String siteLongitude;

	//点位纬度
	private String siteLatitude;

	//点位地形
	private String siteLandscape;

	//备注
	private String remark;
	
	//别名
	private String siteAliasName;
	
	//点位下所有断面
	private List<TransectDto> sections;	

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getRoadId() {
		return roadId;
	}

	public void setRoadId(String roadId) {
		this.roadId = roadId;
	}

	public String getCrossId() {
		return crossId;
	}

	public void setCrossId(String crossId) {
		this.crossId = crossId;
	}

	public String getRoadSectionId() {
		return roadSectionId;
	}

	public void setRoadSectionId(String roadSectionId) {
		this.roadSectionId = roadSectionId;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSiteSimpleName() {
		return siteSimpleName;
	}

	public void setSiteSimpleName(String siteSimpleName) {
		this.siteSimpleName = siteSimpleName;
	}

	public String getSiteType() {
		return siteType;
	}

	public void setSiteType(String siteType) {
		this.siteType = siteType;
	}

	public String getMileage() {
		return mileage;
	}

	public void setMileage(String mileage) {
		this.mileage = mileage;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	public String getOrgPrivilegeCode() {
		return orgPrivilegeCode;
	}

	public void setOrgPrivilegeCode(String orgPrivilegeCode) {
		this.orgPrivilegeCode = orgPrivilegeCode;
	}

	public String getSiteLongitude() {
		return siteLongitude;
	}

	public void setSiteLongitude(String siteLongitude) {
		this.siteLongitude = siteLongitude;
	}

	public String getSiteLatitude() {
		return siteLatitude;
	}

	public void setSiteLatitude(String siteLatitude) {
		this.siteLatitude = siteLatitude;
	}

	public String getSiteLandscape() {
		return siteLandscape;
	}

	public void setSiteLandscape(String siteLandscape) {
		this.siteLandscape = siteLandscape;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}

	public List<TransectDto> getSections() {
		return sections;
	}

	public void setSections(List<TransectDto> sections) {
		this.sections = sections;
	}

	public String getCrossName() {
		return crossName;
	}

	public void setCrossName(String crossName) {
		this.crossName = crossName;
	}

	public String getRoadSectionName() {
		return roadSectionName;
	}

	public void setRoadSectionName(String roadSectionName) {
		this.roadSectionName = roadSectionName;
	}

	public String getSiteAliasName() {
		return siteAliasName;
	}

	public void setSiteAliasName(String siteAliasName) {
		this.siteAliasName = siteAliasName;
	}

}
