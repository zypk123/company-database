package cy.its.road.domain.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.road.domain.criteria.RegionCriteria;
import cy.its.road.domain.model.region.Region;
import cy.its.road.domain.repository.region.IRegionRepository;
import cy.its.road.domain.service.IRegionService;

/**
 * 区间服务
 * 
 * @author STJ
 *
 */
@Service
public class RegionService implements IRegionService {

	@Autowired
	IRegionRepository regionRepository;

	/**
	 * 查询符合条件的区间列表
	 * 
	 * @param regionCriteria
	 *            查询条件
	 * @return 区间列表
	 */
	public List<Region> findRegions(RegionCriteria regionCriteria) {	
		return regionRepository.findRegions(regionCriteria);
	}

	/**
	 * 查询指定的区间详细信息
	 * 
	 * @param regionId
	 *            区间唯一 标识
	 * @return 区间详细信息
	 * @throws Exception 
	 */
	public Region regionOfId(String regionId) throws Exception {
		return regionRepository.aggregateOfId(regionId);
	}

	/**
	 * 创建新区间
	 * 
	 * @param region
	 *            区间
	 */
	public void saveRegion(Region region) {
		regionRepository.save(region);
	}

	/**
	 * 更新区间
	 * 
	 * @param region
	 * 区间
	 */
	public void updateRegion(Region region) {
		regionRepository.update(region);
	}

	/**
	 * 删除指定的区间
	 * 
	 * @param regionId
	 * 区间唯一标识
	 */
	public void deleteRegion(String regionId) {
		regionRepository.delete(regionId);
	}
	public void removeRegion(Map<String,Object> regionIds){
		regionRepository.remove(regionIds);
	}

}
