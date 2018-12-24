package cy.its.syscfg.domain.model.duty;

import cy.its.com.domain.AggregateRoot;

public class Organization extends AggregateRoot {
	/**
	 * 机构唯一标识
	 */
	private java.lang.String orgId;

	/**
	 * 机构代码
	 */
	public java.lang.String orgCode;

	/**
	 * 机构名称
	 */
	public java.lang.String orgName;

	/**
	 * 父机构代码
	 */
	public java.lang.String parentOrgCode;
	
	/**
	 * 父机构ID
	 */
	public java.lang.String parentOrgId;

	/**
	 * 业务指导机构
	 */
	public java.lang.String parentInstructOrgId;

	/**
	 * 机构类型
	 */
	public java.lang.String orgType;

	/**
	 * 是否是部门 0-独立的单位，如总队、支队、大队、派出所；1-单位下的部门，如装备处、科技处、法制办、秩序处。
	 */
	public java.lang.String isDepartment;

	/**
	 * 是否是高速管理部门 高速支队数据权限和其它支队数据权限分开。0：否；1：是。
	 */
	public java.lang.String isHighwayOrg;
	/**
	 * 机构级别(072)： 1：（省厅）总队 2：（市局）支队 3：（县局）大队 4：（派出所）中队
	 */
	public java.lang.String orgLevel;

	/**
	 * 联系方式
	 */
	public java.lang.String orgPhoneNbr;

	/**
	 * 机构负责人
	 */
	public OrgHeader orgHeader;

	/**
	 * 签章
	 */
	public java.lang.String orgSignature;
	
	/**
	 * 通知书签章
	 */
	public java.lang.String signatureNotifier;

	/**
	 * 辖区对应的行政区划范围
	 */
	public java.lang.String sponsorDistributes;
	
	/**
	 * 机构驻地信息
	 */
	public OrgSeat orgSeat;

	/**
	 * 创建人
	 */
	private java.lang.String createBy;

	/**
	 * 更新人
	 */
	public java.lang.String updateBy;
	
	/**
	 * 备注
	 */
	public java.lang.String remark;
	
	/**
	 * 机构权限代码
	 */
	public java.lang.String orgPrivilegeCode ;

	public Organization(OrgHeader orgHeader, OrgSeat orgSeat, String createBy,
			String updateBy) {
		this.orgHeader = orgHeader;
		this.orgSeat = orgSeat;
		this.createBy = createBy;
		this.updateBy = updateBy;
	}

	public Organization(OrgHeader orgHeader, OrgSeat orgSeat, String orgId,
			String createBy, String updateBy) {
		this(orgHeader, orgSeat, createBy, updateBy);
		this.orgId = orgId;
	}

	@Override
	public String getIdentityId() {
		return orgId;
	}

	public java.lang.String getCreateBy() {
		return createBy;
	}
	
	public void setOrgId(java.lang.String orgId) {
		this.orgId = orgId;
	}
}
