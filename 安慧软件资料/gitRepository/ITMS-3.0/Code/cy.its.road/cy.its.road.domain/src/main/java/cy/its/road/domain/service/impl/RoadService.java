package cy.its.road.domain.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.road.domain.criteria.CrossCriteria;
import cy.its.road.domain.criteria.RoadCriteria;
import cy.its.road.domain.criteria.RoadSectionCriteria;
import cy.its.road.domain.model.road.Cross;
import cy.its.road.domain.model.road.Road;
import cy.its.road.domain.model.road.RoadSection;
import cy.its.road.domain.repository.highway.IServiceAreaRepository;
import cy.its.road.domain.repository.highway.ISpecialSectionRepository;
import cy.its.road.domain.repository.interflow.IInterFlowRepository;
import cy.its.road.domain.repository.road.ICrossRepository;
import cy.its.road.domain.repository.road.IRoadRepository;
import cy.its.road.domain.repository.road.IRoadSectionRepository;
import cy.its.road.domain.service.IRoadService;

/**
 * 道路服务
 * 
 * @author STJ
 *
 */
@Service
public class RoadService implements IRoadService {

	@Autowired
	IRoadRepository roadRepository;
	
	@Autowired
	IRoadSectionRepository roadSectionRepository;
	
	@Autowired
	ICrossRepository crossRepository;
	
//	@Autowired
//	ISiteRepository siteRepository;
	
	@Autowired
	IServiceAreaRepository serviceAreaRepository;
	
	@Autowired
	ISpecialSectionRepository specialSectionRepository;
	
	@Autowired
	IInterFlowRepository interFlowRepository;

	public List<Road> findRoads(RoadCriteria roadCriteria) {
		return roadRepository.findRoads(roadCriteria);
	}

	public Road roadOfId(String roadId) throws Exception {
		return roadRepository.aggregateOfId(roadId);
	}

	public String saveRoad(Road road) {
		road.setUpdateTime(new Date());
		return roadRepository.save(road);
	}

	public void updateRoad(Road road) {
		road.setUpdateTime(new Date());
		roadRepository.update(road);
	}

	public void deleteRoad(String roadId) {
		roadRepository.delete(roadId);
	}
	//批量删除
	public void removeRoad(Map<String,Object> roadIds){
		roadRepository.remove(roadIds);
	}
	public List<Cross> findCrosses(CrossCriteria crossCriteria) {	
		if(crossCriteria.getNeedTotal()){
			crossCriteria.setTotal(crossRepository
			.countCrosses(crossCriteria));
		}
		
		return crossRepository.findCrosses(crossCriteria);
	}

	public Cross crossOfId(String crossId) throws Exception {
		return crossRepository.aggregateOfId(crossId);
	}

	public void saveCross(Cross cross) {
		crossRepository.save(cross);
	}

	public void updateCross(Cross cross) {
		crossRepository.update(cross);
	}

	public void deleteCross(String crossId) {
		crossRepository.delete(crossId);
	}
	public void removeCross(Map<String,Object> crossIds){
		crossRepository.removeCross(crossIds);
	}
	public List<RoadSection> findRoadSectiones(
			RoadSectionCriteria roadSectionCriteria) {
//		if(roadSectionCriteria.getNeedTotal()){
//			roadSectionCriteria.setTotal(roadSectionRepository
//			.countRoadSections(roadSectionCriteria));
//		}
		return roadSectionRepository.findRoadSections(roadSectionCriteria);
	}

	public RoadSection roadSectionOfId(String roadSectionId) throws Exception {
		return roadSectionRepository.aggregateOfId(roadSectionId);
	}

	public void saveRoadSection(RoadSection roadSection) {
		roadSectionRepository.save(roadSection);
	}

	public void updateRoadSection(RoadSection roadSection) {
		roadSectionRepository.update(roadSection);
	}

	public void deleteRoadSection(String roadSectionId) {
		roadSectionRepository.delete(roadSectionId);
	}
	public void removeRoadSection(Map<String, Object> roadSectionIds){
		roadSectionRepository.remove(roadSectionIds);
	}
	public void roadChanged() {
		roadRepository.roadChanged();
	}
}
