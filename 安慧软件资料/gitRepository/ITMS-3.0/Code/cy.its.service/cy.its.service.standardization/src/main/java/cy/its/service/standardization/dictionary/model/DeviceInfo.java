package cy.its.service.standardization.dictionary.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cy.its.service.common.dataAccess.MapColumn;
import cy.its.service.common.StringUtil;

public class DeviceInfo implements IDeviceInfo{

	@MapColumn
	private String dtype;
	
	@MapColumn
	private String deviceNbr;

	@MapColumn
	private String deviceId;
	
	@MapColumn
	private String sysComponentId;
	
	@MapColumn
	private String sysNbr;
	
	@MapColumn
	private String collectionType;
	
	@MapColumn
	private String sectionIdList;
	
	@MapColumn
	private String directionType;
	
	@MapColumn
	private String orgId;
	
	@MapColumn
	private String siteId;
	
	@MapColumn
	private String orgName;
	
	@MapColumn
	private String orgCode;
	
	@MapColumn
	private String orgPrivilegeCode;
	
	@MapColumn
	private String siteCode;
	
	@MapColumn
	private String district;
	
	@MapColumn
	private String siteName;
	
	@MapColumn
	private String roadType;
	
	/**
	 * 路段代码
	 */
	@MapColumn
	private String roadSegCode;
	
	/**
	 * 路口代码
	 */
	@MapColumn
	private String crossCode;
	
	@MapColumn
	private String roadCode;
	
	@Override
	public String getDtype() {
		return dtype;
	}

	public void setDtype(String dtype) {
		this.dtype = dtype;
	}

	@Override
	public String getDeviceNbr() {
		return deviceNbr;
	}

	public void setDeviceNbr(String deviceNbr) {
		this.deviceNbr = deviceNbr;
	}
	
	@Override
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	@Override
	public String getSysComponentId() {
		return sysComponentId;
	}

	public void setSysComponentId(String sysComponentId) {
		this.sysComponentId = sysComponentId;
	}

//	@Override
//	public String getSectionIdList() {
//		return sectionIdList;
//	}

	public void setSectionIdList(String sectionIdList) {
		this.sectionIdList = sectionIdList;
	}

	@Override
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	@Override
	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	
	@Override
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	@Override
	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	
	@Override
	public String getOrgPrivilegeCode() {
		return orgPrivilegeCode;
	}

	public void setOrgPrivilegeCode(String orgPrivilegeCode) {
		this.orgPrivilegeCode = orgPrivilegeCode;
	}
	
	@Override
	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	
	@Override
	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}
	
	@Override
	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	@Override
	public String getRoadType() {
		return roadType;
	}

	public void setRoadType(String roadType) {
		this.roadType = roadType;
	}
	
	@Override
	public String getRoadSegCode() {
		return roadSegCode;
	}

	public void setRoadSegCode(String roadSegCode) {
		this.roadSegCode = roadSegCode;
	}
	
	@Override
	public String getCrossCode() {
		return crossCode;
	}

	public void setCrossCode(String crossCode) {
		this.crossCode = crossCode;
	}

	@Override
	public String getRoadCode() {
		return roadCode;
	}

	public void setRoadCode(String roadCode) {
		this.roadCode = roadCode;
	}

	@Override
	public String getSysNbr() {
		return sysNbr;
	}

	public void setSysNbr(String sysNbr) {
		this.sysNbr = sysNbr;
	}
	
//	@Override
//	public String getCollectionType() {
//		return collectionType;
//	}

	public void setCollectionType(String collectionType) {
		this.collectionType = collectionType;
	}

	/**
	 * 设置方向名称和类型
	 * 
	 * @param mapSection
	 */
	public void setDirectNameAndType(Map<String, Section> mapSection) {
		if (!StringUtil.isNullOrEmpty(this.sectionIdList)) {
			List<Section> lst = Arrays.asList(this.sectionIdList.split(SPLIT)).stream().distinct()
					.map(id -> mapSection.get(id)).filter(s -> s != null).collect(Collectors.toList());
			if (lst.size() > 0) {
				this.directMap = new HashMap<String, String>(lst.size());
				lst.forEach(s -> {
					if (!this.directMap.containsKey(s.getDirectionName())) {
						this.directMap.put(s.getDirectionName(), s.getDirectionType());
					}
				});
			}
		}

	}

	Map<String, String> directMap;
	final static String SPLIT = ",";
	final static String DTYPE_GK = "1";
	final static String DTYPE_ZN = "2";
	final static String DEFAULT_DIRECT_TYPE = "";

	@Override
	public String getDeviceType() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 获取方向类型
	 * 
	 * @param directName
	 * @return
	 */
//	@Override
//	public String getDirectType(String directName) {
//		if (StringUtil.isNullOrEmpty(this.directionType) && this.directMap != null) {
//			return this.directMap.get(directName);
//		}
//
//		return this.directionType;
//	}
}