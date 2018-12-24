package cy.its.sysCfg.rest.action;

import java.util.List;

import cy.its.sysCfg.rest.dto.EnforceStationDto;

public interface IEnforceStationAction {

	String add(EnforceStationDto enforceStationDto);
	int delete(String id);
	int update(EnforceStationDto enforceStationDto);
	List<EnforceStationDto> selectAll();
	
}
