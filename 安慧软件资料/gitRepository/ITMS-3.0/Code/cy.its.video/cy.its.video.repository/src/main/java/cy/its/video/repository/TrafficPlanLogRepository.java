package cy.its.video.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.StringUtil;
import cy.its.platform.common.utils.SqlHelper;
import cy.its.video.domain.model.TrafficPlanLog;
import cy.its.video.domain.repository.ITrafficPlanLogRepository;
import cy.its.video.mybatis.client.TrafficPlanLogMapper;

@Service
public class TrafficPlanLogRepository implements ITrafficPlanLogRepository {

	@Autowired
	TrafficPlanLogMapper trafficPlanLogMapper;

	@Override
	public TrafficPlanLog aggregateOfId(String id) throws Exception {
		return trafficPlanLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public String save(TrafficPlanLog obj) {
		String returnID = StringUtil.generateUUID();
		obj.setVideoPlanLogId(returnID);
		int result = trafficPlanLogMapper.insertSelective(obj);
		if (result <= 0) {
			returnID = null;
		}
		return returnID;
	}

	@Override
	public int delete(String id) {
		return trafficPlanLogMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(TrafficPlanLog obj) {
		return trafficPlanLogMapper.updateByPrimaryKey(obj);
	}

	@Override
	public List<TrafficPlanLog> getTrafficPlanLog(Map<String, Object> map) {
		String sql = SqlHelper.getMapperSql(trafficPlanLogMapper, "getLogByCondition", map);
		return trafficPlanLogMapper.getLogByCondition(map);
	}

	@Override
	public int countTrafficPlanLogTotal(Map<String, Object> map) {
		return trafficPlanLogMapper.countLogByCondition(map);
	}

}
