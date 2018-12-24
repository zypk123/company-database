package cy.its.road.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.road.domain.model.interflow.Interflow;


public interface InterflowMapper {
    int deleteByPrimaryKey(String interflowId);

    int insert(Interflow record);

    int insertSelective(Interflow record);

    Interflow selectByPrimaryKey(String interflowId);

    int updateByPrimaryKeySelective(Interflow record);

    int updateByPrimaryKey(Interflow record);
    
    List<Interflow> selectInterflows(Map<String, Object> params);

	int countInterflows(Map<String, Object> parseParams);
}