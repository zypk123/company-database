package cy.its.syscfg.domain.repository.home;

import java.util.List;

import cy.its.com.domain.IRepository;
import cy.its.syscfg.domain.model.home.SysSiteLink;

public interface ISiteLinkRepository extends IRepository<SysSiteLink> {

	List<SysSiteLink> allSiteLinks();

}
