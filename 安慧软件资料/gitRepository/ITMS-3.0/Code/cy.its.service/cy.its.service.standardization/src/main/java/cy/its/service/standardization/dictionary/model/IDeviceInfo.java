package cy.its.service.standardization.dictionary.model;

public interface IDeviceInfo {

	String getDtype();

//	String getDeviceKey();

	String getDeviceNbr();

	String getDeviceId();

	String getSysComponentId();

//	String getSectionIdList();

	String getOrgId();

	String getSiteId();

	String getOrgName();

	String getOrgCode();

	String getOrgPrivilegeCode();

	String getSiteCode();

	String getDistrict();

	String getSiteName();

	String getRoadType();

	String getRoadSegCode();

	String getCrossCode();

	String getRoadCode();

	String getSysNbr();
	
	String getDeviceType();


//	String getCollectionType();

	/**
	 * 获取方向类型
	 * 
	 * @param directName
	 * @return
	 */
//	String getDirectType(String directName);

}