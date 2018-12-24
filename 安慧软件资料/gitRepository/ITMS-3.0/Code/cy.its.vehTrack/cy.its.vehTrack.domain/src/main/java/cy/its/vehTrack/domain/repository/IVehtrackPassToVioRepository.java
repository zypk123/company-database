package cy.its.vehTrack.domain.repository;

import java.util.Map;

public interface IVehtrackPassToVioRepository {
	
	
	public Map<String, Object> queryVehtrackPassToVios(@SuppressWarnings("rawtypes") Map map) throws Exception;
	
	public Map<String, Object> updateVehtrackPassToVio(@SuppressWarnings("rawtypes") Map map) throws Exception;

}
