package cy.its.device.domain.repository.site;

import java.util.List;

import cy.its.com.domain.IRepository;
import cy.its.device.domain.criteria.PassSiteCriteria;
import cy.its.device.domain.criteria.SiteCriteria;
import cy.its.device.domain.model.site.Site;

public interface ISiteRepository extends IRepository<Site> {
	List<Site> findSites(SiteCriteria criteria);
	
	void siteChanged();
		
	Site selectByCode(String siteCode);

	int countSites(SiteCriteria siteCriteria);

	List<Site> findPassSites(PassSiteCriteria passSiteCriteria);
	
	List<Site> findVideoSites(String roadType, String roadId);
}
