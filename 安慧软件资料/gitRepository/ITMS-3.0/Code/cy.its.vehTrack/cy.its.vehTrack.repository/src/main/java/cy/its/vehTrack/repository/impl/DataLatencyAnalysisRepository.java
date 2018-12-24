package cy.its.vehTrack.repository.impl;

import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;

import cy.its.vehTrack.domain.repository.IDataLatencyAnalysisRepository;
import cy.its.vehTrack.repository.bigData.Config;
import cy.its.vehTrack.repository.bigData.util.RESTUtil;

@Repository
public class DataLatencyAnalysisRepository implements IDataLatencyAnalysisRepository {

	@Override
	public String findDataLatencyList(String query) throws Exception {
		String id=RESTUtil.load(Config.getProperties("data_latency_service"), query);
		return id;
	}

}
