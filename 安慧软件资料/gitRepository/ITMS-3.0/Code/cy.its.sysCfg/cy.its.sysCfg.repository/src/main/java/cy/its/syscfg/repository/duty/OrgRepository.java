package cy.its.syscfg.repository.duty;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.bus.EventBus;
import cy.its.com.util.StringUtil;
import cy.its.syscfg.data.OrgData;
import cy.its.syscfg.domain.criteria.OrgCriteria;
import cy.its.syscfg.domain.model.duty.Organization;
import cy.its.syscfg.domain.repository.duty.IOrgRepository;
import cy.its.syscfg.mybatis.client.CustomMapper;
import cy.its.syscfg.mybatis.client.T_Sys_OrgMapper;
import cy.its.syscfg.mybatis.model.T_Sys_OrgWithBLOBs;
import cy.its.syscfg.util.Convert;

@Service
public class OrgRepository implements IOrgRepository {
	@Autowired
	T_Sys_OrgMapper t_Sys_OrgMapper;

	@Autowired
	CustomMapper customMapper;

	@Autowired
	OrgData orgData;
	
	@Autowired
	EventBus eventBus;

	public Organization aggregateOfId(String id) {
		return orgData.dataOfId(id);
	}

	@Override
	public String save(Organization obj) {
		obj.setOrgId(StringUtil.generateUUID());
		T_Sys_OrgWithBLOBs org = Convert.convert(obj);
		if (t_Sys_OrgMapper.insertSelective(org) == 1) {
			return org.getOrgId();
		}

		return null;
	}

	@Override
	public int delete(String id) {
		return t_Sys_OrgMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(Organization obj) {
		T_Sys_OrgWithBLOBs org = Convert.convert(obj);
		return t_Sys_OrgMapper.updateByPrimaryKeySelective(org);
	}

	@Override
	public List<Organization> findOrganizations(OrgCriteria criteria) {
		return orgData.findOrganization(criteria);
	}

	@Override
	public void organizationChanged() {
		eventBus.publish(orgData.getTopic(), "");
	}
	@Override
	public List<Organization> findOrgOfParent(String parentOrgPrivilegeCode)
	{
		return orgData.findOrgOfParent(parentOrgPrivilegeCode);
	}

	@Override
	public List<Organization> organizationsOfCode(String orgCode) {
		return orgData.organizationsOfCode(orgCode);
	}

}
