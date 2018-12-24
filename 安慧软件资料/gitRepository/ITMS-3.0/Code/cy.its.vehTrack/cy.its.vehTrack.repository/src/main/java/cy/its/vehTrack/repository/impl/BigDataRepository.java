package cy.its.vehTrack.repository.impl;

import org.springframework.stereotype.Repository;

import cy.its.vehTrack.domain.repository.IBigDataRepository;
import cy.its.vehTrack.repository.bigData.Config;
import cy.its.vehTrack.repository.bigData.util.RESTUtil;

@Repository
public class BigDataRepository implements IBigDataRepository {

	@Override
	public String loaddBigData(String serviceName, String query) throws Exception {
		// TODO Auto-generated method stub
		String result = RESTUtil.load(Config.getProperties(serviceName), query);
		return result;
	}

}
