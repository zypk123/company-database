package cy.its.vehTrack.domain.repository;

import java.util.Map;


public interface IViolationCarResponsitory {
	
	public Map<String,Object>fingViolationCarInfo(String query) throws Exception;
	
}
