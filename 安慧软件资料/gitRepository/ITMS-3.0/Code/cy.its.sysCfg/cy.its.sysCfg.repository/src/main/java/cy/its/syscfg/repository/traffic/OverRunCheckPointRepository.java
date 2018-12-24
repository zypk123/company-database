package cy.its.syscfg.repository.traffic;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.StringUtil;
import cy.its.syscfg.domain.model.traffic.OverRunCheckPoint;
import cy.its.syscfg.domain.repository.traffic.IOverRunCheckPointRepository;
import cy.its.syscfg.mybatis.client.OverRunCheckPointMapper;

@Service
public class OverRunCheckPointRepository implements IOverRunCheckPointRepository {
     
	@Autowired
	OverRunCheckPointMapper overRunCheckPointMapper;
	public OverRunCheckPoint aggregateOfId(String id) throws Exception {
		return overRunCheckPointMapper.selectByPrimaryKey(id);
	}

	public String save(OverRunCheckPoint obj) {
		obj.setCheckpointId(StringUtil.generateUUID());
		overRunCheckPointMapper.insertSelective(obj);
		return obj.getCheckpointId();
	}

	public int delete(String id) {
		return overRunCheckPointMapper.deleteByPrimaryKey(id);
	}

	public int update(OverRunCheckPoint obj) {
		return overRunCheckPointMapper.updateByPrimaryKey(obj);
	}

	public List<OverRunCheckPoint> selectAll(Map map) {
		
		return overRunCheckPointMapper.selectAll(map);
	}

}
