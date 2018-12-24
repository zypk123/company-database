package cy.its.road.mybatis.client;

import cy.its.road.domain.model.interflow.Ramp;

public interface RampMapper {
    int deleteByPrimaryKey(String rampId);

    int insert(Ramp record);

    int insertSelective(Ramp record);

    Ramp selectByPrimaryKey(String rampId);

    int updateByPrimaryKeySelective(Ramp record);

    int updateByPrimaryKey(Ramp record);
}