package cy.its.vehTrack.domain.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.vehTrack.domain.repository.IPlateRecognitionResponsitory;
import cy.its.vehTrack.domain.service.IPlateRecognitionService;

@Service
public class PlateRecognitionService implements IPlateRecognitionService {
	@Autowired
	IPlateRecognitionResponsitory responsitory;

	@Override
	public Map<String, Object> findPlateRecogRate(String query) throws Exception {
		// TODO Auto-generated method stub
		return responsitory.findPlateRecogRate(query);
	}

}
