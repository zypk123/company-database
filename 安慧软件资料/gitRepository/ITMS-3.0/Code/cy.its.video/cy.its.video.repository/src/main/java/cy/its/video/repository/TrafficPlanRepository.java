package cy.its.video.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.StringUtil;
import cy.its.video.domain.model.TrafficPlan;
import cy.its.video.domain.repository.ITrafficPlanRepository;
import cy.its.video.mybatis.client.TrafficPlanMapper;

@Service
public class TrafficPlanRepository implements ITrafficPlanRepository {

	@Autowired
	TrafficPlanMapper trafficPlanMapper;

	@Override
	public TrafficPlan aggregateOfId(String id) throws Exception {
		return trafficPlanMapper.selectByPrimaryKey(id);
	}

	@Override
	public String save(TrafficPlan obj) {
		String returnID = StringUtil.generateUUID();
		obj.setVideoPlanId(returnID);
		int result = trafficPlanMapper.insert(obj);
		if (result <= 0) {
			returnID = null;
		}
		return returnID;
	}

	@Override
	public int delete(String id) {
		return trafficPlanMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(TrafficPlan obj) {
		return trafficPlanMapper.updateByPrimaryKey(obj);
	}

	@Override
	public List<TrafficPlan> getTrafficPlanByCondition(Map<String, String> map) {
		return trafficPlanMapper.getTrafficPlanByCondition(map);
	}

}
