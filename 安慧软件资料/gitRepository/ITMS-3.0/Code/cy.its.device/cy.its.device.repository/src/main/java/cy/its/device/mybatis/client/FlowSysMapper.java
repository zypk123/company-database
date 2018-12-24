package cy.its.device.mybatis.client;

import cy.its.device.domain.model.FlowSys;

public interface FlowSysMapper {

	FlowSys selectByPrimaryKey(String deviceId);

	void deleteByPrimaryKey(String deviceId);
	
	void insert(FlowSys sysParam);

	void insertSelective(FlowSys sysParam);	

	void updateByPrimaryKeySelective(FlowSys sysParam);
	
	void updateByPrimaryKey(FlowSys sysParam);
}
