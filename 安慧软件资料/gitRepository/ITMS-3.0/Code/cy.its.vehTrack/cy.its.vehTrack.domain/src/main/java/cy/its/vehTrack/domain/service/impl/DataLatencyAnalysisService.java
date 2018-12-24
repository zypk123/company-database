package cy.its.vehTrack.domain.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cy.its.vehTrack.domain.repository.IBigdataAnalysisRepository;
import cy.its.vehTrack.domain.repository.IDataLatencyAnalysisRepository;
import cy.its.vehTrack.domain.service.IDataLatencyAnalysisService;

@Service
public class DataLatencyAnalysisService implements IDataLatencyAnalysisService {

	@Autowired
	IDataLatencyAnalysisRepository repository;
	@Autowired
	IBigdataAnalysisRepository bigdataRepository;

	@Override
	public String findDataLatencyList(String query) throws Exception {
		// TODO Auto-generated method stub
		String result = this.repository.findDataLatencyList(query);
		long id=(long) JSONObject.parseObject(result).get("jobId");
		return (String) this.bigdataRepository.findResultById(id).get("result");
	}

}
