package cy.its.road.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.road.domain.model.highway.SpecialSection;

public interface SpecialSectionMapper {
    int deleteByPrimaryKey(String specialSectionId);

    int insert(SpecialSection record);

    int insertSelective(SpecialSection record);

    SpecialSection selectByPrimaryKey(String specialSectionId);

    int updateByPrimaryKeySelective(SpecialSection record);

    int updateByPrimaryKey(SpecialSection record);
    
    List<SpecialSection> selectSpecialSections(Map<String, Object> params);
    
    int deleteByRoadId(String roadId);

	int countSpecialSections(Map<String, Object> parseParams);
}