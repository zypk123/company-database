package cy.its.vehTrack.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.vehTrack.domain.repository.IBigDataRepository;
import cy.its.vehTrack.domain.service.IBigDataService;

@Service
public class BigDataService implements IBigDataService {
	@Autowired
	IBigDataRepository repository;

	@Override
	public String loaddBigData(String serviceName, String query) throws Exception {
		// TODO Auto-generated method stub
		return this.repository.loaddBigData(serviceName, query);
	}

}
