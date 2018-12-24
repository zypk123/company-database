package cy.its.device.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.ParamUtil;
import cy.its.com.util.StringUtil;
import cy.its.device.domain.criteria.SurveySystemCriteria;
import cy.its.device.domain.model.SurveySys;
import cy.its.device.domain.repository.surveySystem.ISurveySysRepository;
import cy.its.device.mybatis.client.SurveySysMapper;

@Service
public class SurveySysRepository implements ISurveySysRepository {
	
	@Autowired
	SurveySysMapper surveySysMapper;
	
	public SurveySys aggregateOfId(String id) throws Exception {
		return surveySysMapper.selectByPrimaryKey(id);
	}

	public String save(SurveySys obj) {
		obj.setSurveySystemId(StringUtil.generateUUID());
		surveySysMapper.insertSelective(obj);
		return obj.getSurveySystemId();
	}

	public int delete(String id) {
		return surveySysMapper.deleteByPrimaryKey(id);
	}

	public int update(SurveySys obj) {
		return surveySysMapper.updateByPrimaryKeySelective(obj);
	}

	public List<SurveySys> findSurveySystems(SurveySystemCriteria criteria) {		
		return surveySysMapper.selectSurveySyses(ParamUtil.parseParams(criteria));
	}

	public int countSurveySystems(SurveySystemCriteria criteria) {
		return surveySysMapper.countSurveySystems(ParamUtil.parseParams(criteria));
	}

}
