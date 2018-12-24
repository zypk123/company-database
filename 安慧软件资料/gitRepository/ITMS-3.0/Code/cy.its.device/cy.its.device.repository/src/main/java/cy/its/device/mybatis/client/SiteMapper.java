package cy.its.device.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.device.domain.model.site.Site;


public interface SiteMapper {

    Site selectByPrimaryKey(String siteId);

    Site selectByCode(String siteCode);
    
    int deleteByPrimaryKey(String siteId);
    
    int insert(Site record);

    int insertSelective(Site record);    

    int updateByPrimaryKeySelective(Site record);

    int updateByPrimaryKey(Site record);
    
    List<Site> selectSites(Map<String, Object> params);

	int countSites(Map<String, Object> parseParams);

	List<Site> selectPassSites(Map<String, Object> paramMap);
	
	List<Site> selectVideoSites(Map<String, Object> paramMap);
}