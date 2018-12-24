package cy.its.trafficSituation.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.StringUtil;
import cy.its.trafficSituation.domain.model.TrafficEventProcess;
import cy.its.trafficSituation.domain.repository.ITrafficEventProcessRepository;
import cy.its.trafficSituation.mybatis.client.TrafficEventProcessMapper;

@Service
public class TrafficEventProcessRepository implements ITrafficEventProcessRepository {
	@Autowired
	TrafficEventProcessMapper trafficEventProcessMapper;
	public TrafficEventProcess aggregateOfId(String id) throws Exception {
		return trafficEventProcessMapper.selectByPrimaryKey(id);
	}
	
	public String save(TrafficEventProcess obj) {
		obj.setEventProcessId(StringUtil.generateUUID());
		trafficEventProcessMapper.insertSelective(obj);
		return obj.getEventProcessId();
	}

	public int delete(String id) {
		return trafficEventProcessMapper.deleteByPrimaryKey(id);
	}

	public int update(TrafficEventProcess obj) {
		return trafficEventProcessMapper.updateByPrimaryKey(obj);
	}

	public TrafficEventProcess selectByEventId(String eventId) {
		return trafficEventProcessMapper.selectByEventId(eventId);
	}

}
