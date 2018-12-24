package cy.its.vehTrack.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import cy.its.vehTrack.domain.repository.IBigdataAnalysisRepository;
import cy.its.vehTrack.domain.repository.ISparkRepository;
import cy.its.vehTrack.domain.service.ISparkService;

public class SparkService implements ISparkService {
	@Autowired
	ISparkRepository repository;
	@Autowired
	IBigdataAnalysisRepository bigdataRepository;

	@Override
	public String getSparkJobId(String query, String serviceName) throws Exception {
		String result = this.repository.getSparkJobId(query, serviceName);
		long id=(long) JSONObject.parseObject(result).get("jobId");
		Thread.sleep(2000);
		return (String) this.bigdataRepository.findResultById(id).get("result");
	}

}
