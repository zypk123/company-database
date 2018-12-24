package cy.its.sysCfg.rest.action;

import java.util.List;

import cy.its.sysCfg.rest.dto.OrgTreeDto;

public interface IOrgAction {
	
	public List<OrgTreeDto> getOrgRoot(String rootId)  throws Exception;
	
}
