package cy.its.video.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.ParamUtil;
import cy.its.com.util.StringUtil;
import cy.its.video.domain.criteria.TrafficVideoGroupCriteria;
import cy.its.video.domain.model.TrafficVideoGroup;
import cy.its.video.domain.repository.ITrafficVideoGroupRepository;
import cy.its.video.mybatis.client.TrafficVideoGroupMapper;

@Service
public class TrafficVideoGroupRepository implements ITrafficVideoGroupRepository {

	@Autowired
	TrafficVideoGroupMapper trafficVideoGroupMapper;

	@Override
	public TrafficVideoGroup aggregateOfId(String id) throws Exception {
		// TODO Auto-generated method stub
		return trafficVideoGroupMapper.selectByPrimaryKey(id);
	}

	@Override
	public String save(TrafficVideoGroup obj) {
		String returnID = StringUtil.generateUUID();
		obj.setGroupId(returnID);
		int resultCode = trafficVideoGroupMapper.insertSelective(obj);
		if (resultCode <= 0) {
			returnID = null;
		}
		return returnID;
	}

	@Override
	public int delete(String id) {
		return trafficVideoGroupMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(TrafficVideoGroup obj) {
		return trafficVideoGroupMapper.updateByPrimaryKeySelective(obj);
	}

	@Override
	public List<TrafficVideoGroup> getVideoGroupByCondition(TrafficVideoGroupCriteria criteria) {
		Map<String, Object> map = ParamUtil.parseParams(criteria);
		if (!StringUtil.isNullOrEmpty(criteria.getOrgAuthorityCode())) {
			map.replace("orgAuthorityCode", "%" + criteria.getOrgAuthorityCode() + "%");
		}
		return trafficVideoGroupMapper.getVideoGroupByCondition(map);
	}

}
