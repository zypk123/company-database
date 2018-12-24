package cy.its.service.standardization.dictionary.model;

import cy.its.service.common.dataAccess.MapColumn;

public class SurveyUpgrade_DeviceInfo implements IDeviceInfo {

	@MapColumn
	private String dtype;

	@MapColumn
	private String deviceKey;
	
	@MapColumn
	private String deviceNbr;

	@MapColumn
	private String deviceId;
	
	@MapColumn
	private String sysComponentId;
	
	@MapColumn
	private String sysNbr;
	
//	@MapColumn
//	private String collectionType;
//	
//	@MapColumn
//	private String sectionIdList;
//	
//	@MapColumn
//	private String directionType;
	
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
	
	@MapColumn
	private String deviceType;

	/* (non-Javadoc)
	 * @see cy.its.service.standardization.dictionary.model.IDeviceInfo#getDtype()
	 */
	@Override
	public String getDtype() {
		return dtype;
	}

	public void setDtype(String dtype) {
		this.dtype = dtype;
	}

	/* (non-Javadoc)
	 * @see cy.its.service.standardization.dictionary.model.IDeviceInfo#getDeviceKey()
	 */
	public String getDeviceKey() {
		return deviceKey;
	}

	public void setDeviceKey(String deviceKey) {
		this.deviceKey = deviceKey;
	}

	/* (non-Javadoc)
	 * @see cy.its.service.standardization.dictionary.model.IDeviceInfo#getDeviceNbr()
	 */
	@Override
	public String getDeviceNbr() {
		return deviceNbr;
	}

	public void setDeviceNbr(String deviceNbr) {
		this.deviceNbr = deviceNbr;
	}
	
	/* (non-Javadoc)
	 * @see cy.its.service.standardization.dictionary.model.IDeviceInfo#getDeviceId()
	 */
	@Override
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	/* (non-Javadoc)
	 * @see cy.its.service.standardization.dictionary.model.IDeviceInfo#getSysComponentId()
	 */
	@Override
	public String getSysComponentId() {
		return sysComponentId;
	}

	public void setSysComponentId(String sysComponentId) {
		this.sysComponentId = sysComponentId;
	}

	/* (non-Javadoc)
	 * @see cy.its.service.standardization.dictionary.model.IDeviceInfo#getSectionIdList()
	 */
//	@Override
//	public String getSectionIdList() {
//		return sectionIdList;
//	}
//
//	public void setSectionIdList(String sectionIdList) {
//		this.sectionIdList = sectionIdList;
//	}


	/* (non-Javadoc)
	 * @see cy.its.service.standardization.dictionary.model.IDeviceInfo#getOrgId()
	 */
	@Override
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	/* (non-Javadoc)
	 * @see cy.its.service.standardization.dictionary.model.IDeviceInfo#getSiteId()
	 */
	@Override
	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	/* (non-Javadoc)
	 * @see cy.its.service.standardization.dictionary.model.IDeviceInfo#getOrgName()
	 */
	@Override
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/* (non-Javadoc)
	 * @see cy.its.service.standardization.dictionary.model.IDeviceInfo#getOrgCode()
	 */
	@Override
	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	/* (non-Javadoc)
	 * @see cy.its.service.standardization.dictionary.model.IDeviceInfo#getOrgPrivilegeCode()
	 */
	@Override
	public String getOrgPrivilegeCode() {
		return orgPrivilegeCode;
	}

	public void setOrgPrivilegeCode(String orgPrivilegeCode) {
		this.orgPrivilegeCode = orgPrivilegeCode;
	}

	/* (non-Javadoc)
	 * @see cy.its.service.standardization.dictionary.model.IDeviceInfo#getSiteCode()
	 */
	@Override
	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	/* (non-Javadoc)
	 * @see cy.its.service.standardization.dictionary.model.IDeviceInfo#getDistrict()
	 */
	@Override
	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	/* (non-Javadoc)
	 * @see cy.its.service.standardization.dictionary.model.IDeviceInfo#getSiteName()
	 */
	@Override
	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	/* (non-Javadoc)
	 * @see cy.its.service.standardization.dictionary.model.IDeviceInfo#getRoadType()
	 */
	@Override
	public String getRoadType() {
		return roadType;
	}

	public void setRoadType(String roadType) {
		this.roadType = roadType;
	}

	/* (non-Javadoc)
	 * @see cy.its.service.standardization.dictionary.model.IDeviceInfo#getRoadSegCode()
	 */
	@Override
	public String getRoadSegCode() {
		return roadSegCode;
	}

	public void setRoadSegCode(String roadSegCode) {
		this.roadSegCode = roadSegCode;
	}
	
	/* (non-Javadoc)
	 * @see cy.its.service.standardization.dictionary.model.IDeviceInfo#getCrossCode()
	 */
	@Override
	public String getCrossCode() {
		return crossCode;
	}

	public void setCrossCode(String crossCode) {
		this.crossCode = crossCode;
	}

	/* (non-Javadoc)
	 * @see cy.its.service.standardization.dictionary.model.IDeviceInfo#getRoadCode()
	 */
	@Override
	public String getRoadCode() {
		return roadCode;
	}

	public void setRoadCode(String roadCode) {
		this.roadCode = roadCode;
	}

	/* (non-Javadoc)
	 * @see cy.its.service.standardization.dictionary.model.IDeviceInfo#getSysNbr()
	 */
	@Override
	public String getSysNbr() {
		return sysNbr;
	}

	public void setSysNbr(String sysNbr) {
		this.sysNbr = sysNbr;
	}
	
	
	/* (non-Javadoc)
	 * @see cy.its.service.standardization.dictionary.model.IDeviceInfo#getCollectionType()
	 */
//	@Override
//	public String getCollectionType() {
//		return collectionType;
//	}
//
//	public void setCollectionType(String collectionType) {
//		this.collectionType = collectionType;
//	}

//	/**
//	 * 设置方向名称和类型
//	 * 
//	 * @param mapSection
//	 */
//	public void setDirectNameAndType(Map<String, Section> mapSection) {
//		if (!StringUtil.isNullOrEmpty(this.sectionIdList)) {
//			List<Section> lst = Arrays.asList(this.sectionIdList.split(SPLIT)).stream().distinct()
//					.map(id -> mapSection.get(id)).filter(s -> s != null).collect(Collectors.toList());
//			if (lst.size() > 0) {
//				this.directMap = new HashMap<String, String>(lst.size());
//				lst.forEach(s -> {
//					if (!this.directMap.containsKey(s.getDirectionName())) {
//						this.directMap.put(s.getDirectionName(), s.getDirectionType());
//					}
//				});
//			}
//		}
//
//	}

	@Override
	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}


//	Map<String, String> directMap;
	final static String SPLIT = ",";
	final static String DTYPE_GK = "1";
	final static String DTYPE_ZN = "2";
	final static String DEFAULT_DIRECT_TYPE = "";

//	/* (non-Javadoc)
//	 * @see cy.its.service.standardization.dictionary.model.IDeviceInfo#getDirectType(java.lang.String)
//	 */
//	@Override
//	public String getDirectType(String directName) {
//		if (StringUtil.isNullOrEmpty(this.directionType) && this.directMap != null) {
//			return this.directMap.get(directName);
//		}
//
//		return this.directionType;
//	}
}
