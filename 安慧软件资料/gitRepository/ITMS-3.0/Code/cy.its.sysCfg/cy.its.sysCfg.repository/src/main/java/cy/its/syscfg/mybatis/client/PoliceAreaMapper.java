package cy.its.syscfg.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.syscfg.domain.model.traffic.PoliceArea;

public interface PoliceAreaMapper {
    int deleteByPrimaryKey(String policeAreaId);

	int insert(PoliceArea record);

	int insertSelective(PoliceArea record);

	PoliceArea selectByPrimaryKey(String policeAreaId);

	int updateByPrimaryKeySelective(PoliceArea record);

	int updateByPrimaryKey(PoliceArea record);
	
	List<PoliceArea> selectAll(Map map);
}