package cy.its.vehTrack.domain.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.vehTrack.domain.repository.IViolationCarResponsitory;
import cy.its.vehTrack.domain.service.IViolationCarService;

@Service
public class ViolationCarService implements IViolationCarService {
	@Autowired
	IViolationCarResponsitory responsitory;

	@Override
	public Map<String, Object> fingViolationCarInfo(String query) throws Exception {
		// TODO Auto-generated method stub
		return responsitory.fingViolationCarInfo(query);
	}

}

