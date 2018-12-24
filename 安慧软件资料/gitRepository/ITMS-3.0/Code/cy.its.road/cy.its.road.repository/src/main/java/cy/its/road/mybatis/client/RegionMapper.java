package cy.its.road.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.road.domain.model.region.Region;

public interface RegionMapper {
    int deleteByPrimaryKey(String regionalId);

    int insert(Region record);

    int insertSelective(Region record);

    Region selectByPrimaryKey(String regionalId);

    int updateByPrimaryKeySelective(Region record);

    int updateByPrimaryKey(Region record);
    
    List<Region> selectRegions(Map<String, Object> params);

	int countRegions(Map<String, Object> parseRegionParams);
	
    int removeByPrimaryKey(Map<String,Object> regionalIds);

}