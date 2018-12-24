package cy.its.video.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.StringUtil;
import cy.its.video.domain.model.TrafficPlanVideo;
import cy.its.video.domain.repository.ITrafficPlanVideoRepository;
import cy.its.video.mybatis.client.TrafficPlanVideoMapper;

@Service
public class TrafficPlanVideoRepository implements ITrafficPlanVideoRepository {

	@Autowired
	TrafficPlanVideoMapper trafficPlanVideoMapper;

	@Override
	public TrafficPlanVideo aggregateOfId(String id) throws Exception {
		return trafficPlanVideoMapper.selectByPrimaryKey(id);
	}

	@Override
	public String save(TrafficPlanVideo obj) {
		String returnID = StringUtil.generateUUID();
		obj.setRelatedVideoId(returnID);
		int resultCode = trafficPlanVideoMapper.insertSelective(obj);
		if (resultCode <= 0) {
			returnID = null;
		}
		return returnID;
	}

	@Override
	public int delete(String id) {
		return trafficPlanVideoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(TrafficPlanVideo obj) {
		return trafficPlanVideoMapper.updateByPrimaryKey(obj);
	}

	@Override
	public List<TrafficPlanVideo> getTrafficPlanVideo(String videoPlanID) {
		return trafficPlanVideoMapper.getDataByVideoPlanID(videoPlanID);
	}

}
