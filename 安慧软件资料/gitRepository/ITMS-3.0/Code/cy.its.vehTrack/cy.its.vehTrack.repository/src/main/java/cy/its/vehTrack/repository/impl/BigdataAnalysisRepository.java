package cy.its.vehTrack.repository.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cy.its.vehTrack.domain.repository.IBigdataAnalysisRepository;
import cy.its.vehTrack.mybatis.client.BigdataAnalysisMapper;
import cy.its.vehTrack.mybatis.model.BigdataAnalysisWithBLOBs;

@Repository
public class BigdataAnalysisRepository implements IBigdataAnalysisRepository {
	@Autowired
	BigdataAnalysisMapper mapper;

	@Override
	public Map findResultById(Long id) {
		BigdataAnalysisWithBLOBs bean = this.mapper.selectByPrimaryKey(id);
		Map map = new HashMap();
		map.put("result", bean.getANALYSIS_RESULT());
		return map;

	}

}
