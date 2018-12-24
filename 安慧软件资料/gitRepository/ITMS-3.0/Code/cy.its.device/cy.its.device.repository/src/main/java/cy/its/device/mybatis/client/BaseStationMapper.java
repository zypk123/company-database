package cy.its.device.mybatis.client;

import cy.its.device.domain.model.BaseStation;
import cy.its.device.domain.model.SysParam;

public interface BaseStationMapper {
    int deleteByPrimaryKey(String deviceId);

    int insert(BaseStation record);

    int insertSelective(BaseStation record);

	void updateByPrimaryKey(BaseStation sysParam);

	SysParam selectByPrimaryKey(String deviceId);
}