package cy.its.vehTrack.domain.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.vehTrack.domain.repository.IUnlicensedCarsResponsitory;
import cy.its.vehTrack.domain.service.IUnlicensedCarsService;

@Service
public class UnlicensedCarsService implements IUnlicensedCarsService {

	@Autowired
	IUnlicensedCarsResponsitory responsitory;
	@Override
	public Map<String, Object> findUnlicensedCarsInfo(String query) throws Exception {
		// TODO Auto-generated method stub
		return this.responsitory.findUnlicensedCarsInfo(query);
	}

}
