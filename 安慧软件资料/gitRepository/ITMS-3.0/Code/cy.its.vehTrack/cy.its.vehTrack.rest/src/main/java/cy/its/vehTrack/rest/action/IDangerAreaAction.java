package cy.its.vehTrack.rest.action;

import java.util.Map;

import cy.its.vehTrack.rest.dto.DangerAreaInputBean;

public interface IDangerAreaAction {
	Map<String,Object> findDangerAreaCarList(DangerAreaInputBean bean) throws Exception;

}
