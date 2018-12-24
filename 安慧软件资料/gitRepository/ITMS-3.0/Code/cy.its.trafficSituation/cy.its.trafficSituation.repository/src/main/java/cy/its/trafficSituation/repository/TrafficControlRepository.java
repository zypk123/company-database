package cy.its.trafficSituation.repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.StringUtil;
import cy.its.trafficSituation.domain.model.TrafficControl;
import cy.its.trafficSituation.domain.repository.ITrafficControlRepository;
import cy.its.trafficSituation.mybatis.client.TrafficControlMapper;

@Service
public class TrafficControlRepository implements ITrafficControlRepository {

	@Autowired
	TrafficControlMapper trafficControlMapper;
	public TrafficControl aggregateOfId(String id) throws Exception {
		return trafficControlMapper.selectByPrimaryKey(id);
	}

	public String save(TrafficControl obj) {
		obj.setTrafficControlId(StringUtil.generateUUID());
		trafficControlMapper.insertSelective(obj);		
		return obj.getTrafficControlId();
	}

	public int delete(String id) {
		return trafficControlMapper.deleteByPrimaryKey(id);
	}

	public int update(TrafficControl obj) {
		return trafficControlMapper.updateByPrimaryKey(obj);
	}
	
	public int updateSelective(TrafficControl obj){
		return trafficControlMapper.updateByPrimaryKeySelective(obj);
	}

	public List<TrafficControl> selectAll(Map map) {
		return trafficControlMapper.selectAll(map);
	}

	/*
	  * <p>Title: selectValid</p>
	  * <p>Description:查询有效的交通管制 </p>
	  * @param map
	  * @return
	  * @see cy.its.trafficSituation.domain.repository.ITrafficControlRepository#selectValid(java.util.Map)
	  */
	
	
	@Override
	public List<TrafficControl> selectValid(Map map) {
		map.put("validTime", new Date());
		return trafficControlMapper.selectValid(map);
	}

	/*
	  * <p>Title: selectCount</p>
	  * <p>Description: </p>
	  * @return
	  * @see cy.its.trafficSituation.domain.repository.ITrafficControlRepository#selectCount()
	  */
	
	
	@Override
	public long selectCount(Map map) {
		map.put("validTime", new Date());
		return trafficControlMapper.selectCount(map);
	}

}
