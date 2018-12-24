package cy.its.vehTrack.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.vehTrack.domain.repository.IVioSpaceTimeRepository;
import cy.its.vehTrack.domain.service.IVioSpaceTimeService;

@Service
public class VioSpaceTimeService implements IVioSpaceTimeService {
	@Autowired
	IVioSpaceTimeRepository vioResponsitory;

	@Override
	public String getVioDistribution(String query) throws Exception {
		// TODO Auto-generated method stub
		return this.vioResponsitory.getVioDistribution(query);
	}

	@Override
	public String getVioTrendStat(String query) throws Exception {
		// TODO Auto-generated method stub
		return this.vioResponsitory.getVioTrendStat(query);
	}

}
