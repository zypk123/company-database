package cy.its.device.domain.service;

import java.util.List;

import cy.its.device.domain.criteria.PassSiteCriteria;
import cy.its.device.domain.criteria.SiteCriteria;
import cy.its.device.domain.model.site.Lane;
import cy.its.device.domain.model.site.Section;
import cy.its.device.domain.model.site.Site;


public interface ISiteService {

	/**
	 * 查询符合条件的点位信息列表
	 * 
	 * @param siteCriteria
	 *            查询条件
	 * @return 点位信息列表
	 */
	public List<Site> findSites(SiteCriteria siteCriteria);
	
	/**
	 * 查询指定道路上指定方向上包含支持过车设备的点位列表
	 * @param roadId
	 * @param directionType
	 * @return
	 */
	public List<Site> findPassSites(PassSiteCriteria criteria); 
	

	/**
	 * 查询符合条件的带有支持视频设备的点位
	 * @param roadType
	 * @param roadId
	 * @return
	 */
	public List<Site> findVideoSites(String roadType, String roadId); 

	/**
	 * 查询指定的点位信息
	 * 
	 * @param siteId
	 *            点位ID
	 * @return 点位信息
	 * @throws Exception
	 */
	public Site siteOfId(String siteId) throws Exception;
	
	/**
	 * 查询指定的点位信息
	 * 
	 * @param siteCode
	 *            点位code
	 * @return 点位信息
	 * @throws Exception
	 * by gyf
	 */
	public Site siteOfCode(String siteCode); 


	/**
	 * 保存点位信息
	 * 
	 * @param site
	 *            点位信息
	 */
	public void saveSite(Site site);

	/**
	 * 更新点位信息
	 * 
	 * @param site
	 *            点位信息
	 */
	public void updateSite(Site site);

	/**
	 * 删除指定的点位
	 * 
	 * @param siteId
	 *            点位ID
	 */
	public void deleteSite(String siteId);
	

	/**
	 * 获取指定点位的断面列表
	 * @param siteId
	 * @return
	 */
	public List<Section> sectionsOfSite(String siteId);	

	/**
	 * 获取指定的断面
	 * @param sectionId
	 * @return
	 * @throws Exception
	 */
	public Section sectionOfId(String sectionId) throws Exception;

	/**
	 * 创建断面
	 * @param section
	 * @return
	 */
	public String createSection(Section section);

	/**
	 * 更新断面
	 * @param section
	 */
	public void updateSection(Section section);

	/**
	 * 删除指定的断面
	 * @param sectionId
	 */
	public void deleteSection(String sectionId);
	
	/**
	 * 查询指定多个点位下的断面列表
	 * @param siteIds
	 * @return
	 */
	public List<Section> sectionsOfSites(List<String> siteIds);	
	
	/**
	 * 统计多个点位下的断面数
	 * @param siteIds
	 * @return
	 */
	public int sectionCountOfSites(List<String> siteIds);
	
	/**
	 * 获取断面下的车道列表
	 * @param sectionId
	 * @return
	 */
	public List<Lane> lanesOfSection(String sectionId);
	
	/**
	 * 获取指定的车道
	 * @param laneId
	 * @return
	 * @throws Exception
	 */
	public Lane laneOfId(String laneId) throws Exception;

	/**
	 * 创建车道
	 * @param lane
	 * @return
	 */
	public String createLane(Lane lane);

	/**
	 * 更新车道
	 * @param lane
	 */
	public void updateLane(Lane lane);

	/**
	 * 删除车道
	 * @param laneId
	 */
	public void deleteLane(String laneId);
	
	/**
	 * 点位变更通知
	 */
	public void siteChanged();	
	
}