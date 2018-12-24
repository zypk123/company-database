package cy.its.sysCfg.rest.action;

import java.util.Map;

import cy.its.syscfg.domain.model.traffic.SysArea;

public interface IAreaAction {
	
	String deleteByPrimaryKey(String areaId);

	String insert(SysArea record);

	String insertSelective(SysArea record);

	SysArea findByPrimaryKey(String areaId);

	String updateByPrimaryKeySelective(SysArea record);

	String updateByPrimaryKey(SysArea record);
	
	Map<String,Object> findAreaList(SysArea record);

}
