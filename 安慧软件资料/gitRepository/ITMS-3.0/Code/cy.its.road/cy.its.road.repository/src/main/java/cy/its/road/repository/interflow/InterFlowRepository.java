package cy.its.road.repository.interflow;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.ParamUtil;
import cy.its.com.util.StringUtil;
import cy.its.road.domain.criteria.InterflowCriteria;
import cy.its.road.domain.criteria.RoadInterflowCriteria;
import cy.its.road.domain.model.interflow.Interflow;
import cy.its.road.domain.model.interflow.RoadInterflow;
import cy.its.road.domain.repository.interflow.IInterFlowRepository;
import cy.its.road.mybatis.client.InterflowMapper;
import cy.its.road.mybatis.client.RoadInterflowMapper;

@Service
public class InterFlowRepository implements IInterFlowRepository {
	
	@Autowired
	InterflowMapper interflowMapper;
	
	@Autowired
	RoadInterflowMapper roadInterflowMapper;
	
	public Interflow aggregateOfId(String id) throws Exception {
		return interflowMapper.selectByPrimaryKey(id);
	}

	public String save(Interflow obj) {
		obj.setInterflowId(StringUtil.generateUUID());
		interflowMapper.insertSelective(obj);
		 return obj.getInterflowId();
	}

	public int delete(String id) {
		return interflowMapper.deleteByPrimaryKey(id);
	}

	public int update(Interflow obj) {
		return interflowMapper.updateByPrimaryKeySelective(obj);
	}

	public List<Interflow> findInterflows(InterflowCriteria interflowCriteria) {
		return interflowMapper.selectInterflows(ParamUtil.parseParams(interflowCriteria));
	}

	@Override
	public int deleteRoadInterflow(RoadInterflowCriteria criteria) {
		RoadInterflow flow = new RoadInterflow();
		flow.setInterflowId(criteria.interflowId);
		flow.setRoadId(criteria.roadId);
		return roadInterflowMapper.deleteSelective(flow);
	}

	@Override
	public int countInterflows(InterflowCriteria interflowCriteria) {
		return interflowMapper.countInterflows(ParamUtil.parseParams(interflowCriteria));
	}

	

}
