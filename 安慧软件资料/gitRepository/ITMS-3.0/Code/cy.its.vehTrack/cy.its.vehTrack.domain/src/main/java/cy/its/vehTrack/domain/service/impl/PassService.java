package cy.its.vehTrack.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.vehTrack.domain.criteria.PassCriteria;
import cy.its.vehTrack.domain.model.PassInfo;
import cy.its.vehTrack.domain.repository.IPassRepository;
import cy.its.vehTrack.domain.service.IPassService;

@Service
public class PassService implements IPassService{

	@Autowired
	IPassRepository passRepository;
	
	@Override
	public List<PassInfo> findPassInfo(PassCriteria passCriteria) throws Exception {
		return passRepository.findPassInfo(passCriteria);
	}
	
}
