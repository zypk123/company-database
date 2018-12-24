package cy.its.road.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.road.domain.model.road.RoadSection;

public interface RoadSectionMapper {
    int deleteByPrimaryKey(String roadSectionId);

    int insert(RoadSection record);

    int insertSelective(RoadSection record);

    RoadSection selectByPrimaryKey(String roadSectionId);

    int updateByPrimaryKeySelective(RoadSection record);

    int updateByPrimaryKey(RoadSection record);
        
    List<RoadSection> selectRoadSections(Map<String, Object> params);

	int countRoadSections(Map<String, Object> parseParams);
	//ÅúÁ¿É¾³ý 
	int removeByPrimaryKey(Map<String,Object> roadSectionIds);
}