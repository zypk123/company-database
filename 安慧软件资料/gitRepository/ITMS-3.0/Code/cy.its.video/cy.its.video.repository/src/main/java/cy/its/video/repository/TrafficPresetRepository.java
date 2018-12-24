package cy.its.video.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.StringUtil;
import cy.its.video.domain.model.TrafficPreset;
import cy.its.video.domain.repository.ITrafficPresetRepository;
import cy.its.video.mybatis.client.TrafficPresetMapper;

@Service
public class TrafficPresetRepository implements ITrafficPresetRepository {

	@Autowired
	TrafficPresetMapper trafficPresetMapper;

	@Override
	public TrafficPreset aggregateOfId(String id) throws Exception {
		return trafficPresetMapper.selectByPrimaryKey(id);
	}

	@Override
	public String save(TrafficPreset obj) {
		String returnID = StringUtil.generateUUID();
		obj.setPresetRecordId(returnID);
		int result = trafficPresetMapper.insertSelective(obj);
		if (result <= 0) {
			returnID = null;
		}
		return returnID;
	}

	@Override
	public int delete(String id) {
		return trafficPresetMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(TrafficPreset obj) {
		return trafficPresetMapper.updateByPrimaryKey(obj);
	}

	@Override
	public List<TrafficPreset> selectByCondition(Map<String, String> map) {
		return trafficPresetMapper.selectByCondition(map);
	}

}
