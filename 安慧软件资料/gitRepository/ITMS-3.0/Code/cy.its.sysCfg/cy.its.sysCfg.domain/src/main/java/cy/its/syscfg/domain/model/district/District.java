package cy.its.syscfg.domain.model.district;

import cy.its.com.domain.Entity;

public class District extends Entity<String> {
	public java.lang.String districtCode;
	public java.lang.String districtName;
	public java.lang.String parentDistrictCode;
	public java.lang.String districtGrade;
	public java.lang.String remark;

	@Override
	public String getIdentityId() {
		return districtCode;
	}
}
