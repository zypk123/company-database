package cy.its.vehTrack.repository.impl;


import org.springframework.stereotype.Repository;

import cy.its.vehTrack.domain.repository.IVioSpaceTimeRepository;
import cy.its.vehTrack.repository.bigData.Config;
import cy.its.vehTrack.repository.bigData.util.RESTUtil;

@Repository
public class VioSpaceTimeRepository implements IVioSpaceTimeRepository {

	@Override
	public String getVioDistribution(String query) throws Exception {
		String result = RESTUtil.load(Config.getProperties("vio_distribution_service"), query);
		return result;
	}

	@Override
	public String getVioTrendStat(String query) throws Exception {
		
		String result = RESTUtil.load(Config.getProperties("vio_trendstat_service"), query);
		return result;
	}

}
