package cy.its.vehTrack.rest.action;

import java.util.Map;

import cy.its.vehTrack.rest.dto.AccompanySearchInputBean;

public interface IAccompanyCarAction {

	public Map<String, Object> findAccompanyCarList(AccompanySearchInputBean bean) throws Exception;
}
