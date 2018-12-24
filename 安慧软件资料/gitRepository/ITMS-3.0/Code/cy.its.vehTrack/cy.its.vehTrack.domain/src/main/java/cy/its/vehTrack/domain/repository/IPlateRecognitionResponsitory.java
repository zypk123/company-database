package cy.its.vehTrack.domain.repository;

import java.util.Map;

public interface IPlateRecognitionResponsitory {
	
	public Map<String,Object> findPlateRecogRate(String query) throws Exception;

}
