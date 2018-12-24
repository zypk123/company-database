package cy.its.video.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.StringUtil;
import cy.its.video.domain.model.TrafficVideoLog;
import cy.its.video.domain.repository.ITrafficVideoLogRepository;
import cy.its.video.mybatis.client.TrafficVideoLogMapper;

@Service
public class TrafficVideoLogRepository implements ITrafficVideoLogRepository {

	@Autowired
	TrafficVideoLogMapper trafficVideoLogMapper;

	@Override
	public TrafficVideoLog aggregateOfId(String id) throws Exception {
		return trafficVideoLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public String save(TrafficVideoLog obj) {
		String returnID = StringUtil.generateUUID();
		obj.setVideoLogId(returnID);
		int result = trafficVideoLogMapper.insertSelective(obj);
		if (result <= 0) {
			returnID = null;
		}
		return returnID;
	}

	@Override
	public int delete(String id) {
		return trafficVideoLogMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(TrafficVideoLog obj) {
		return trafficVideoLogMapper.updateByPrimaryKey(obj);
	}

	@Override
	public List<TrafficVideoLog> getVideoCruiseEvent(String videoPlanLogId) {
		return trafficVideoLogMapper.selectByPlanLogID(videoPlanLogId);
	}

}
