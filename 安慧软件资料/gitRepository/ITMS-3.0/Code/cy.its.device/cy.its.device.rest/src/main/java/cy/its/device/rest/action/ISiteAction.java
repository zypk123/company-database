package cy.its.device.rest.action;

import java.util.List;
import java.util.Map;

import cy.its.device.rest.dto.LaneDto;
import cy.its.device.rest.dto.SiteDto;
import cy.its.device.rest.dto.SiteTreeDto;

public interface ISiteAction {

	/**
	 * 根据跟节点ID获取
	 * @return
	 * @throws Exception
	 */
	public List<SiteTreeDto> getSiteTreeByRootId(String rootId,String type) throws Exception;
	
	/**
	 * 根据点位名称查找点位信息
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> findSiteByName(String name) throws Exception;
	
	/**
	 * 查询点位
	 * @param siteDto
	 * @return 结果集
	 */
	public Map<String,Object> findSite(SiteDto siteDto);
	
	/**
	 * 添加点位及其相关信息（断面、车道）
	 * @param siteDto
	 * @return
	 */
	public String goAddSite(String jsonSitelString) throws Exception;
	
	/**
	 * 编辑点位及其相关信息（断面、车道）
	 * @param siteDto
	 * @return
	 */
	public void goEditSite(String jsonSitelString) throws Exception;
	
	
	/**
	 * 查询某点位的相关信息
	 * @param siteId 点位ID
	 * @return
	 * @throws Exception
	 */
	public SiteDto querySite(String siteId) throws Exception;
	
	/**
	 * 删除点位
	 * @param ids 点位Id串
	 * @return
	 */
	public String goDeleteSites(String ids);
	
	/**
	 * 查询所有车道信息
	 * @param siteId
	 * @param siteType
	 * @return
	 * @throws Exception
	 */
	public List<LaneDto> queryLaneNum(String siteId, String directionType) throws Exception;
	
}
