package cy.its.vehTrack.domain.service;

import java.util.Map;

public interface IUnlicensedCarsService {
	public Map<String ,Object> findUnlicensedCarsInfo(String query) throws Exception;
}
