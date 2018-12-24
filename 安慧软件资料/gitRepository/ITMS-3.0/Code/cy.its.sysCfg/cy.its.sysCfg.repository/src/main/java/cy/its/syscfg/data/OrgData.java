package cy.its.syscfg.data;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cy.its.com.bus.EventBus;
import cy.its.com.constant.ConstValue;
import cy.its.com.util.Data;
import cy.its.com.util.StringUtil;
import cy.its.syscfg.domain.criteria.OrgCriteria;
import cy.its.syscfg.domain.model.duty.Organization;
import cy.its.syscfg.mybatis.client.CustomMapper;
import cy.its.syscfg.mybatis.model.T_Sys_Org;
import cy.its.syscfg.util.Convert;

@Service
public final class OrgData extends Data<CustomMapper, Organization> {

	private Comparator<Organization> comparator = new Comparator<Organization>() {
		@Override
		public int compare(Organization o1, Organization o2) {
			return StringUtil.padRight(o1.orgPrivilegeCode, 8, '0')
					.compareTo(StringUtil.padRight(o2.orgPrivilegeCode, 8, '0'));
		}
	};

	@Autowired(required = true)
	public OrgData(@Qualifier(value = "customMapper") CustomMapper customMapper,
			@Qualifier(value = "eventBus") EventBus eventBus) {
		super(customMapper, ConstValue.ROUTE_KEY_CACHECHANGE_ORG, eventBus);
	}

	public List<Organization> findOrganization(OrgCriteria criteria) {
		String tmpOrgPrivilegeCode = null;
		if (!StringUtil.isNullOrEmpty(criteria.orgId)) {
			Organization org = dataOfId(criteria.orgId);
			if (org != null) {
				tmpOrgPrivilegeCode = org.orgPrivilegeCode;
			}

			final String orgPrivilegeCode = tmpOrgPrivilegeCode;
			return findDatas((o) -> StringUtil.isNullOrEmpty(criteria.orgId) ? true
					: (StringUtil.isNullOrEmpty(orgPrivilegeCode) ? false
							: (StringUtil.isNullOrEmpty(o.orgPrivilegeCode) ? false
									: o.orgPrivilegeCode.startsWith(orgPrivilegeCode)
											|| o.orgPrivilegeCode.equals(orgPrivilegeCode))),

					comparator, criteria);
		} else {
			final String orgPrivilegeCode = criteria.orgPrivCode;
			return findDatas((o) -> orgPrivilegeCode.equals(o.orgPrivilegeCode) ? true : false, comparator, criteria);
		}

	}

	public List<Organization> findOrgOfParent(String parentOrgPrivilegeCode) {

		if (!StringUtil.isNullOrEmpty(parentOrgPrivilegeCode)) {
			List<Organization> all = this.allDatas();
			if (all != null) {
				return all.stream().filter(o -> !StringUtil.isNullOrEmpty(o.orgPrivilegeCode)
						&& o.orgPrivilegeCode.startsWith(parentOrgPrivilegeCode)).collect(Collectors.toList());
			}
		}

		return null;
	}

	public List<Organization> organizationsOfCode(String orgCode) {
		List<Organization> all = allDatas();
		if (all != null) {
			return all.stream().filter(o -> orgCode.equals(o.orgCode)).collect(Collectors.toList());
		}
		return null;
	}
	
	// @Override
	// public void accept(Event event) {
	// load();
	// }

	@Override
	protected List<Organization> readMapper(CustomMapper mapper) {
		List<T_Sys_Org> lstOrgs = mapper.selectAllOrgs();
		return lstOrgs.stream().map((sysOrg) -> Convert.convert(sysOrg)).collect(Collectors.toList());
	}

	@Override
	protected Map<String, Organization> groupList(List<Organization> lstData) {
		return lstData.stream().collect(Collectors.toMap(Organization::getIdentityId, (o) -> o));
	}

}
