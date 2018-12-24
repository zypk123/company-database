package cy.its.device.rest.dto;

import java.util.ArrayList;
import java.util.List;

import cy.its.com.dto.BaseDto;
import cy.its.com.util.StringUtil;
import cy.its.device.domain.criteria.PassSiteCriteria;

public class PassSiteQueryDto extends BaseDto {
	
	private String siteName;
	
	private String roadId;
	
	private String roadIds;
	
	private String directionType;
	
	private String districtCodes;

	public PassSiteCriteria parseToCriteria(){
		PassSiteCriteria criteria = new PassSiteCriteria();
		List<String> roadIdList = StringToList(roadIds);
		if(this.roadId != null){
			roadIdList.add(this.roadId);
		}
		if(roadIdList.size() > 0){
			criteria.setRoadIds(roadIdList);
		}
		criteria.setDistrictCodes(StringToList(districtCodes));
		criteria.setDirectionType(directionType);
		criteria.setOrgPrivCode(this.getCurrentOrgPrivilegeCode());
		criteria.setSiteName(siteName);
		return criteria;
	}

	/**
	 * 逗号分隔字符串转List
	 * @param strs
	 * @return
	 */
	private List<String> StringToList(String strs){
		List<String> returnList = new ArrayList<String>();
		if(!StringUtil.isNullOrEmpty(strs)){
			String[] strArray = strs.split(",");
			for(String str : strArray){
				returnList.add(str);
			}
		}
		return returnList;
	}
	
	
	public String getRoadId() {
		return roadId;
	}
	
	public void setRoadId(String roadId) {
		this.roadId = roadId;
	}

	public String getRoadIds() {
		return roadIds;
	}

	public void setRoadIds(String roadIds) {
		this.roadIds = roadIds;
	}

	public String getDirectionType() {
		return directionType;
	}

	public void setDirectionType(String directionType) {
		this.directionType = directionType;
	}

	public String getDistrictCodes() {
		return districtCodes;
	}

	public void setDistrictCodes(String districtCodes) {
		this.districtCodes = districtCodes;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	
}
