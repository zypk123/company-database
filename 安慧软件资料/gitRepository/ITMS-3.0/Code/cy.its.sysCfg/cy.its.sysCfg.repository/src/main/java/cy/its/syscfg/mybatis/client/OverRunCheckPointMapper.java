package cy.its.syscfg.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.syscfg.domain.model.traffic.OverRunCheckPoint;

public interface OverRunCheckPointMapper {
    int deleteByPrimaryKey(String checkpointId);

    int insert(OverRunCheckPoint record);

    int insertSelective(OverRunCheckPoint record);

    OverRunCheckPoint selectByPrimaryKey(String checkpointId);

    int updateByPrimaryKeySelective(OverRunCheckPoint record);

    int updateByPrimaryKey(OverRunCheckPoint record);
    
    List<OverRunCheckPoint> selectAll(Map map);
}