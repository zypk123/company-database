package cy.its.sysCfg.rest.action;

import java.util.List;

import cy.its.sysCfg.rest.dto.PolicePostDto;

public interface IPolicePostAction {	
	String addPolicePost(PolicePostDto policePostDto);
	int deletePolicePostOfId(String id);
	int updatePolicePost(PolicePostDto policePostDto);
	List<PolicePostDto> selectAll();
}
