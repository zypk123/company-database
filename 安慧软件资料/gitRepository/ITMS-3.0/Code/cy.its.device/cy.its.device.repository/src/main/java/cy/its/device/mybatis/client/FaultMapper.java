package cy.its.device.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.device.domain.model.Fault;

public interface FaultMapper {
    int deleteByPrimaryKey(String faultId);

    int insert(Fault record);

    int insertSelective(Fault record);

    Fault selectByPrimaryKey(String faultId);

    int updateByPrimaryKeySelective(Fault record);

    int updateByPrimaryKey(Fault record);
    
    List<Fault> selectFaults(Map<String, Object> params);

	int countFaults(Map<String, Object> parseParams);
}