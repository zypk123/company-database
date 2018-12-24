package cy.its.vehTrack.rest.dto;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import cy.its.com.dto.BaseDto;
import cy.its.com.util.StringUtil;

public class VehtrackPassToVioBean extends BaseDto {
	private String plateNbr;
	private String distritCodes;
	private String orgAuthorityCode;
	private String identifyType;
	private String roadCodes;
	private String vehLocalization;
	private String startDateTime;
	private String endDateTime;
	private String totalCount;
	private String confirmFlags;
	private String justNonlocalVeh;
	// 当前页数
	private int pageNumber;
	// 分页大小
	private int pageSize;

	
	public String getPlateNbr() {
		return plateNbr;
	}

	public void setPlateNbr(String plateNbr) {
		this.plateNbr = plateNbr;
	}

	public String getDistritCodes() {
		return distritCodes;
	}

	public void setDistritCodes(String distritCodes) {
		this.distritCodes = StringUtil.convertDistritCodes(distritCodes);
	}

	public String getOrgAuthorityCode() {
		return orgAuthorityCode;
	}

	public void setOrgAuthorityCode(String orgAuthorityCode) {
		this.orgAuthorityCode = orgAuthorityCode;
	}

	public String getIdentifyType() {
		return identifyType;
	}

	public void setIdentifyType(String identifyType) {
		this.identifyType = identifyType;
	}

	public String getRoadCodes() {
		return roadCodes;
	}

	public void setRoadCodes(String roadCodes) {
		this.roadCodes = roadCodes;
	}

	public String getVehLocalization() {
		return vehLocalization;
	}

	public void setVehLocalization(String vehLocalization) {
		this.vehLocalization = vehLocalization;
	}

	public String getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}

	public String getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}

	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getConfirmFlags() {
		return confirmFlags;
	}

	public void setConfirmFlags(String confirmFlags) {
		this.confirmFlags = confirmFlags;
	}

	public String getJustNonlocalVeh() {
		return justNonlocalVeh;
	}

	public void setJustNonlocalVeh(String justNonlocalVeh) {
		this.justNonlocalVeh = justNonlocalVeh;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map beanToMap() {
		Map map = new HashMap();
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field field : fields) {
			try {
				map.put(field.getName(), field.get(this));
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return map;
	}

}