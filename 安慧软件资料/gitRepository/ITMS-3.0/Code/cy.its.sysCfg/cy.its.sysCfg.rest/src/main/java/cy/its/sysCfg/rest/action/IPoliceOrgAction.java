package cy.its.sysCfg.rest.action;

import java.util.List;

import cy.its.sysCfg.rest.dto.PoliceOrgDto;

public interface IPoliceOrgAction {
	List<PoliceOrgDto> selectAll(String currentOrgPrivilegeCodeString);
}
