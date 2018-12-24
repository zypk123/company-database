package cy.its.sysCfg.rest.action;

import java.util.List;

import cy.its.sysCfg.rest.dto.OverRunCheckPointDto;

public interface IOverRunCheckPointAction {
	String add(OverRunCheckPointDto overRunCheckPointDto);
	int delete(String id);
	int update(OverRunCheckPointDto overRunCheckPointDto);
	List<OverRunCheckPointDto> selectAll();
}
