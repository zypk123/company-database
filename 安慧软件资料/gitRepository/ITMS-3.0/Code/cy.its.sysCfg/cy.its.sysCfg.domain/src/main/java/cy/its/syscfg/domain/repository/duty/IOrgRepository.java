package cy.its.syscfg.domain.repository.duty;

import java.util.List;

import cy.its.com.domain.IRepository;
import cy.its.syscfg.domain.criteria.OrgCriteria;
import cy.its.syscfg.domain.model.duty.Organization;

public interface IOrgRepository extends IRepository<Organization> {

	List<Organization> findOrganizations(OrgCriteria criteria);

	void organizationChanged();
	
	List<Organization> findOrgOfParent(String parentOrgPrivilegeCode);
	
	List<Organization> organizationsOfCode(String orgCode);
}
