package cy.its.road.domain.service;

import java.util.List;
import java.util.Map;

import cy.its.road.domain.criteria.CrossCriteria;
import cy.its.road.domain.criteria.RoadCriteria;
import cy.its.road.domain.criteria.RoadSectionCriteria;
import cy.its.road.domain.model.road.Cross;
import cy.its.road.domain.model.road.Road;
import cy.its.road.domain.model.road.RoadSection;

public interface IRoadService {

	/**
	 * 查询符合条件的道路信息 列表
	 * 
	 * @param roadCriteria
	 *            查询条件
	 * @return 道路信息列表
	 */
	public List<Road> findRoads(RoadCriteria roadCriteria);

	/**
	 * 查询指定的道路
	 * 
	 * @param roadId
	 *            道路唯一标识ID
	 * @return 道路明细
	 * @throws Exception 
	 */
	public Road roadOfId(String roadId) throws Exception;

	/**
	 * 创建新道路
	 * 
	 * @param road
	 *            道路信息
	 * @return 
	 */
	public String saveRoad(Road road);

	/**
	 * 更新道路
	 * 
	 * @param road
	 *            道路
	 */
	public void updateRoad(Road road);

	/**
	 * 删除指定的道路
	 * 
	 * @param roadId
	 *            道路唯一标识ID
	 */
	public void deleteRoad(String roadId);
	
	//批量删除
	public void removeRoad(Map<String,Object> roadIds);
	/**
	 * 查询符合条件的路口列表
	 * 
	 * @param crossCriteria
	 *            查询条件
	 * @return 路口列表
	 */
	public List<Cross> findCrosses(
			CrossCriteria crossCriteria);

	/**
	 * 查询指定的路口
	 * 
	 * @param crossId
	 *            路口唯一标识ID
	 * @return 路口
	 * @throws Exception 
	 */
	public Cross crossOfId(String crossId) throws Exception;
	
	/**
	 * 创建新 路口
	 * 
	 * @param roadJunction
	 *            路口
	 */
	public void saveCross(Cross cross);
	
	
	/**
	 * 更新路口
	 * 
	 * @param cross
	 *            路口
	 */
	public void updateCross(Cross cross);

	/**
	 * 删除指定的路口
	 * 
	 * @param crossId
	 * 路口唯一标识ID
	 */
	public void deleteCross(String crossId);
	
	public void removeCross(Map<String,Object> crossIds);
	
	/**
	 * 查询符合条件的路段列表
	 * 
	 * @param roadSectionCriteria
	 *            查询条件
	 * @return 路段列表
	 */
	public List<RoadSection> findRoadSectiones(
			RoadSectionCriteria roadSectionCriteria);

	/**
	 * 查询指定的路段
	 * 
	 * @param roadSectionId
	 *            路段唯一标识ID
	 * @return 路段
	 * @throws Exception 
	 */
	public RoadSection roadSectionOfId(String roadSectionId) throws Exception;
	
	/**
	 * 创建新 路段
	 * 
	 * @param roadJunction
	 *            路段
	 */
	public void saveRoadSection(RoadSection roadSection);
	
	
	/**
	 * 更新路段
	 * 
	 * @param roadSection
	 *            路段
	 */
	public void updateRoadSection(RoadSection roadSection);

	/**
	 * 删除指定的路段
	 * 
	 * @param roadSectionId
	 *            路段唯一标识ID
	 */
	public void deleteRoadSection(String roadSectionId);
	
	public void removeRoadSection(Map<String,Object> roadSectionIds);
	/**
	 * 道路变更通知
	 */
	public void roadChanged();
}