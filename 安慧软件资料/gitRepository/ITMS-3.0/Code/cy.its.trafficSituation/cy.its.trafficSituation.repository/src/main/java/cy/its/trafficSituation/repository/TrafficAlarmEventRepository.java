package cy.its.trafficSituation.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.trafficSituation.domain.model.TrafficAlarmEvent;
import cy.its.trafficSituation.domain.repository.ITrafficAlarmEventRepository;
import cy.its.trafficSituation.mybatis.client.TrafficAlarmEventMapper;

@Service
public class TrafficAlarmEventRepository implements ITrafficAlarmEventRepository {

	@Autowired
	TrafficAlarmEventMapper trafficAlarmEventMapper;
	public TrafficAlarmEvent aggregateOfId(String id) throws Exception {
		return trafficAlarmEventMapper.selectByPrimaryKey(id);
	}

	public String save(TrafficAlarmEvent obj) {
		// TODO Auto-generated method stub
		return null;
	}

	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int update(TrafficAlarmEvent obj) {
		return trafficAlarmEventMapper.updateByPrimaryKeySelective(obj);
	}

	public List<TrafficAlarmEvent> selectAll(Map<String, Object> map) {
		return trafficAlarmEventMapper.selectAll(map);
	}

	/*
	  * <p>Title: selectCountByType</p>
	  * <p>Description: </p>
	  * @param map
	  * @return
	  * @see cy.its.trafficSituation.domain.repository.ITrafficAlarmEventRepository#selectCountByType(java.util.Map)
	  */
	
	
	@Override
	public long selectCountByType(Map map) {
		return trafficAlarmEventMapper.selectCountByType(map);
	}

}
