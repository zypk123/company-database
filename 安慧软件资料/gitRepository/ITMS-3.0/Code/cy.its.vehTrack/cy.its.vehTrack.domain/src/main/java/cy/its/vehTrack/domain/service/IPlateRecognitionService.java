package cy.its.vehTrack.domain.service;

import java.util.Map;

public interface IPlateRecognitionService {
	
	public Map<String,Object> findPlateRecogRate(String query) throws Exception;
}
