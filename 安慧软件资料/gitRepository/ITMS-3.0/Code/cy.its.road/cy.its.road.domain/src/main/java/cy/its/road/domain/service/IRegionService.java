package cy.its.road.domain.service;

import java.util.List;
import java.util.Map;

import cy.its.road.domain.criteria.RegionCriteria;
import cy.its.road.domain.model.region.Region;

public interface IRegionService {

	/**
	 * 查询符合条件的区间列表
	 * 
	 * @param regionCriteria
	 *            查询条件
	 * @return 区间列表
	 */
	public List<Region> findRegions(RegionCriteria regionCriteria);

	/**
	 * 查询指定的区间详细信息
	 * 
	 * @param regionId
	 *            区间唯一 标识
	 * @return 区间详细信息
	 * @throws Exception 
	 */
	public Region regionOfId(String regionId) throws Exception;

	/**
	 * 创建新区间
	 * 
	 * @param region
	 *            区间
	 */
	public void saveRegion(Region region);

	/**
	 * 更新区间
	 * 
	 * @param region
	 *            区间
	 */
	public void updateRegion(Region region);

	/**
	 * 删除指定的区间
	 * 
	 * @param regionId
	 *            区间唯一标识
	 */
	public void deleteRegion(String regionId);
	//批量删除
	public void removeRegion(Map<String,Object> regionIds);
	
}