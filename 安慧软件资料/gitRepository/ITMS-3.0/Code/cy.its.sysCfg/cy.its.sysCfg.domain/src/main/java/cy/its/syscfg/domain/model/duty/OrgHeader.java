package cy.its.syscfg.domain.model.duty;

import cy.its.com.domain.Value;

/**
 * 机构负责人
 */
public class OrgHeader extends Value {
	/**
	 * 负责人名称
	 */
	private java.lang.String orgHeaderName;

	/**
	 * 负责人电话
	 */
	private java.lang.String orgHeaderPhone;

	public OrgHeader(String orgHeaderName, String orgHeaderPhone) {
		this.orgHeaderName = orgHeaderName;
		this.orgHeaderPhone = orgHeaderPhone;
	}

	/**
	 * @return the orgHeaderName
	 */
	public java.lang.String getOrgHeaderName() {
		return orgHeaderName;
	}

	/**
	 * @return the orgHeaderPhone
	 */
	public java.lang.String getOrgHeaderPhone() {
		return orgHeaderPhone;
	}

	@Override
	protected boolean IsEmpty() {
		// TODO Auto-generated method stub
		return stringIsEmpty(this.orgHeaderName)
				|| stringIsEmpty(this.orgHeaderPhone);
	}
}
