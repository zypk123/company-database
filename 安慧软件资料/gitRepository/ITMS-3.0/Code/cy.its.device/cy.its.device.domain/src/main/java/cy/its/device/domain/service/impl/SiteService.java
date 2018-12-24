package cy.its.device.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.device.domain.criteria.PassSiteCriteria;
import cy.its.device.domain.criteria.SiteCriteria;
import cy.its.device.domain.model.site.Lane;
import cy.its.device.domain.model.site.Section;
import cy.its.device.domain.model.site.Site;
import cy.its.device.domain.repository.site.ILaneRepository;
import cy.its.device.domain.repository.site.ISectionRepository;
import cy.its.device.domain.repository.site.ISiteRepository;
import cy.its.device.domain.service.ISiteService;

@Service
public class SiteService implements ISiteService {

	@Autowired
	ISiteRepository siteRepository;
	
	@Autowired
	ISectionRepository sectionRepository;
	
	@Autowired
	ILaneRepository laneRepository;
	
	public List<Site> findSites(SiteCriteria siteCriteria) {
		return siteRepository.findSites(siteCriteria);
	}
	
	@Override
	public List<Site> findPassSites(PassSiteCriteria criteria) {
		return siteRepository.findPassSites(criteria);
	}
	
	public Site siteOfId(String siteId) throws Exception {
		return siteRepository.aggregateOfId(siteId);
	}
	
	public void saveSite(Site site) {
		siteRepository.save(site);
	}
	public void updateSite(Site site) {
		siteRepository.update(site);
	}

	public void deleteSite(String siteId) {
		siteRepository.delete(siteId);
	}

	public void siteChanged() {
		siteRepository.siteChanged();
	}
	public Section sectionOfId(String sectionId) throws Exception {
		return sectionRepository.aggregateOfId(sectionId);
	}
	public String saveSection(Section section) {
		return sectionRepository.save(section);
	}
	public void updateSection(Section section) {
		sectionRepository.update(section);
	}

	public void deleteSection(String sectionId) {
		sectionRepository.delete(sectionId);
	}
    
	public List<Section> findSectionBySiteId(String siteId)
	{
		return sectionRepository.findSectionBySiteId(siteId);
	}

	@Override
	public Lane laneOfId(String laneId) throws Exception {
		return laneRepository.aggregateOfId(laneId);
	}
	
	@Override
	public void updateLane(Lane lane) {
		laneRepository.update(lane);
	}

	@Override
	public void deleteLane(String laneId) {
		laneRepository.delete(laneId);
	}
	
	@Override
	public Site siteOfCode(String siteCode) {
		return siteRepository.selectByCode(siteCode);
	}

	@Override
	public List<Section> sectionsOfSite(String siteId) {
		return sectionRepository.findSectionBySiteId(siteId);
	}

	@Override
	public String createSection(Section section) {
		return sectionRepository.save(section);
	}

	@Override
	public List<Lane> lanesOfSection(String sectionId) {
		return laneRepository.findLaneBySectionId(sectionId);
	}

	@Override
	public String createLane(Lane lane) {
		return laneRepository.save(lane);
	}

	@Override
	public List<Section> sectionsOfSites(List<String> siteIds) {
		return sectionRepository.findSectionBySiteIds(siteIds);
	}

	@Override
	public int sectionCountOfSites(List<String> siteIds) {
		return sectionRepository.countSectionBySiteIds(siteIds);
	}

	@Override
	public List<Site> findVideoSites(String roadType, String roadId) {
		return siteRepository.findVideoSites(roadType, roadId);
	}
}
