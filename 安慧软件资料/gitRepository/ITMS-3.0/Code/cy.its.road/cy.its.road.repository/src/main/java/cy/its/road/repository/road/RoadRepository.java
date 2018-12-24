package cy.its.road.repository.road;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cy.its.com.bus.EventBus;
import cy.its.com.util.ParamUtil;
import cy.its.com.util.StringUtil;
import cy.its.platform.common.utils.SqlHelper;
import cy.its.road.data.RoadData;
import cy.its.road.domain.criteria.RoadCriteria;
import cy.its.road.domain.model.road.Road;
import cy.its.road.domain.repository.road.IRoadRepository;
import cy.its.road.mybatis.client.RoadMapper;

@Service
public class RoadRepository implements IRoadRepository {

	@Autowired
	RoadMapper roadMapper;
	
	@Autowired
	RoadData roadData;	

	@Autowired
	EventBus eventBus;

	public Road aggregateOfId(String id) throws Exception {
		return roadData.dataOfId(id);
	}

	public String save(Road obj) {
		obj.setRoadId(StringUtil.generateUUID());
		roadMapper.insertSelective(obj);
		return obj.getRoadId();
	}

	public int delete(String id) {
		return roadMapper.deleteByPrimaryKey(id);
	}
	public int remove(Map<String ,Object> roadIds){
		return roadMapper.removeByPrimaryKey(roadIds);
	}
	
	public int update(Road obj) {
		return roadMapper.updateByPrimaryKeySelective(obj);
	}

	public List<Road> findRoads(RoadCriteria criteria) {
		PageHelper.startPage(criteria.getPageNum(), criteria.getPageSize());
		if(!StringUtil.isNullOrEmpty(criteria.getOrderName())){
			PageHelper.orderBy(criteria.getOrderName() + " " + criteria.getOrderType());
		}
		Page<Road> page = (Page<Road>) roadMapper.selectRoads(ParamUtil.parseParams(criteria));
		criteria.setTotal(page.getTotal());
		return page.getResult();
	}
	
	public void roadChanged() {
		eventBus.publish(roadData.getTopic(), "");
	}
}
