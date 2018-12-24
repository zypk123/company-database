/**
 *name :Œ£œ’º› ª≥µ¡æ∑÷Œˆ
 *author:wangyunqi
 *date:2016/03/14
 * 
 */
package cy.its.vehTrack.domain.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cy.its.vehTrack.domain.repository.IBigdataAnalysisRepository;
import cy.its.vehTrack.domain.repository.IDangerCarResponsitory;
import cy.its.vehTrack.domain.service.IDangerCarService;

@Service
public class DangerCarService implements IDangerCarService {
	@Autowired
	IDangerCarResponsitory dangerCarResponsitory;
	@Autowired
	IBigdataAnalysisRepository bigdataRepository;

	@Override
	public String findDangerCarTopN(String query) throws Exception {
		// TODO Auto-generated method stub
		//return this.dangerCarResponsitory.findDangerCarTopN(query);
		String result = this.dangerCarResponsitory.findDangerCarTopN(query);
		long id=(long) JSONObject.parseObject(result).get("jobId");
		Thread.sleep(5000);
		return (String) this.bigdataRepository.findResultById(id).get("result");
	}

	@Override
	public String findDangerCarWholeAnylsis(String query) throws Exception {
		// TODO Auto-generated method stub
		String result = this.dangerCarResponsitory.findDangerCarWholeAnylsis(query);
		long id=(long) JSONObject.parseObject(result).get("jobId");
		Thread.sleep(1000);
		return (String) this.bigdataRepository.findResultById(id).get("result");
	}

	@Override
	public Map<String, Object> findDangerCarSimpleAnylsis(String query) throws Exception {
		// TODO Auto-generated method stub
		return this.dangerCarResponsitory.findDangerCarSimpleAnylsis(query);
	}

	@Override
	public String findDangerCarMulitAnylsis(String query) throws Exception {
		// TODO Auto-generated method stub
		//return this.dangerCarResponsitory.findDangerCarMulitAnylsis(query);
		String result = this.dangerCarResponsitory.findDangerCarMulitAnylsis(query);
		long id=(long) JSONObject.parseObject(result).get("jobId");
		Thread.sleep(1000);
		return (String) this.bigdataRepository.findResultById(id).get("result");
	}

}
