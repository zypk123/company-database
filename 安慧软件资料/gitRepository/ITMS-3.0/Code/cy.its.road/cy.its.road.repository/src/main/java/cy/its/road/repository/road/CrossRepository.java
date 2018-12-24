package cy.its.road.repository.road;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.ParamUtil;
import cy.its.com.util.StringUtil;
import cy.its.road.domain.criteria.CrossCriteria;
import cy.its.road.domain.model.road.Cross;
import cy.its.road.domain.repository.road.ICrossRepository;
import cy.its.road.mybatis.client.CrossMapper;

@Service
public class CrossRepository implements ICrossRepository {

	@Autowired
	CrossMapper crossMapper;
	
	public Cross aggregateOfId(String id) throws Exception {
		return crossMapper.selectByPrimaryKey(id);
	}

	public String save(Cross obj) {
		obj.setCrossId(StringUtil.generateUUID());
		crossMapper.insertSelective(obj);
		return obj.getCrossId();
	}

	public int delete(String id) {
		return crossMapper.deleteByPrimaryKey(id);
	}
	
	public int removeCross(Map<String,Object> crossIds){
		return crossMapper.removeByPrimaryKey(crossIds);
	}
	
	public int update(Cross obj) {
		return crossMapper.updateByPrimaryKeySelective(obj);
	}

	public List<Cross> findCrosses(CrossCriteria criteria) {
		return crossMapper.selectCrosses(ParamUtil.parseParams(criteria));
	}

	@Override
	public int countCrosses(CrossCriteria crossCriteria) {
		return crossMapper.countCrosses(ParamUtil.parseParams(crossCriteria));
	} 
}
