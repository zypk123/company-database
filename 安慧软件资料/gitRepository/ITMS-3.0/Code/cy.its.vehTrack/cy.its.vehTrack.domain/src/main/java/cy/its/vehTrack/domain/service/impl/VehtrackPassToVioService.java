package cy.its.vehTrack.domain.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.vehTrack.domain.repository.IVehtrackPassToVioRepository;
import cy.its.vehTrack.domain.service.IVehtrackPassToVioService;

@Service
public class VehtrackPassToVioService implements IVehtrackPassToVioService {
	@Autowired
	IVehtrackPassToVioRepository repository;

	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, Object> queryVehtrackPassToVios(Map map) throws Exception {
		return this.repository.queryVehtrackPassToVios(map);
	}

	@Override
	public Map<String, Object> updateVehtrackPassToVio(@SuppressWarnings("rawtypes") Map map) throws Exception {
		// TODO Auto-generated method stub
		return this.repository.updateVehtrackPassToVio(map);
	}

}
