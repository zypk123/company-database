package cy.its.sysCfg.rest.action;

import java.util.List;


import cy.its.sysCfg.rest.dto.PoliceAreaDto;

public interface IPoliceAreaAction {
	String add(PoliceAreaDto policeAreaDto);
	int delete(String id);
	int update(PoliceAreaDto policeAreaDto);
	List<PoliceAreaDto> selectAll();
}
