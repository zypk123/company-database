package cy.its.vehTrack.repository.impl;

import cy.its.vehTrack.domain.repository.ISparkRepository;
import cy.its.vehTrack.repository.bigData.Config;
import cy.its.vehTrack.repository.bigData.util.RESTUtil;

public class SparkRepository implements ISparkRepository {

	@Override
	public String getSparkJobId(String query, String serviceName) throws Exception {
		// TODO Auto-generated method stub
		String id=RESTUtil.load(Config.getProperties("danger_car_topn_service"), query);
		return id;
	}

}
