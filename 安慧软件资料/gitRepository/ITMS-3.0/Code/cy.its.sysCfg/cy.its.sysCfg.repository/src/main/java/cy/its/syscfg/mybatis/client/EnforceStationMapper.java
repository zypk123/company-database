package cy.its.syscfg.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.syscfg.domain.model.traffic.EnforceStation;

public interface EnforceStationMapper {
    int deleteByPrimaryKey(String lawEnforceStationId);

    int insert(EnforceStation record);

    int insertSelective(EnforceStation record);

    EnforceStation selectByPrimaryKey(String lawEnforceStationId);

    int updateByPrimaryKeySelective(EnforceStation record);

    int updateByPrimaryKey(EnforceStation record);
    
    List<EnforceStation> selectAll(Map map);
}