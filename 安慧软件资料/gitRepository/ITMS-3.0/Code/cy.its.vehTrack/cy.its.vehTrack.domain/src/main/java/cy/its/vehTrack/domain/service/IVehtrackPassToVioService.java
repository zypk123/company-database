package cy.its.vehTrack.domain.service;

import java.util.Map;

public interface IVehtrackPassToVioService {
	public Map<String, Object> queryVehtrackPassToVios(@SuppressWarnings("rawtypes") Map map) throws Exception;
	
	public Map<String, Object> updateVehtrackPassToVio(@SuppressWarnings("rawtypes") Map map) throws Exception;
}
