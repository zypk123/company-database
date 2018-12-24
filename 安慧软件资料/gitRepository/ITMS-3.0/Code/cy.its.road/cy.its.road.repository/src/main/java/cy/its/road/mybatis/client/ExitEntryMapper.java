package cy.its.road.mybatis.client;

import cy.its.road.domain.model.interflow.ExitEntry;

public interface ExitEntryMapper {
    int deleteByPrimaryKey(String gatewayId);

    int insert(ExitEntry record);

    int insertSelective(ExitEntry record);

    ExitEntry selectByPrimaryKey(String gatewayId);

    int updateByPrimaryKeySelective(ExitEntry record);

    int updateByPrimaryKey(ExitEntry record);
}