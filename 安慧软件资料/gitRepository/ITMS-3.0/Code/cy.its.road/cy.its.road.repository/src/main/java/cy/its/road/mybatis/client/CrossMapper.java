package cy.its.road.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.road.domain.model.road.Cross;


public interface CrossMapper {
    int deleteByPrimaryKey(String crossId);

    int insert(Cross record);

    int insertSelective(Cross record);

    Cross selectByPrimaryKey(String crossId);

    int updateByPrimaryKeySelective(Cross record);

    int updateByPrimaryKey(Cross record);
    
    List<Cross> selectCrosses(Map<String, Object> params);

	int countCrosses(Map<String, Object> parseParams);
	
	int removeByPrimaryKey(Map<String,Object> roadIds);
}