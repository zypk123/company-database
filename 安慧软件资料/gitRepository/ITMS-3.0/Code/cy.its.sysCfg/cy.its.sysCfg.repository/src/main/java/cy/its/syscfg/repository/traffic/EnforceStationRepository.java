package cy.its.syscfg.repository.traffic;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.StringUtil;
import cy.its.syscfg.domain.model.traffic.EnforceStation;
import cy.its.syscfg.domain.repository.traffic.IEnforceStationRepository;
import cy.its.syscfg.mybatis.client.EnforceStationMapper;

@Service
public class EnforceStationRepository implements IEnforceStationRepository {
	@Autowired
	EnforceStationMapper enforceStationMapper;

	public EnforceStation aggregateOfId(String id) throws Exception {
		return enforceStationMapper.selectByPrimaryKey(id);
	}

	public String save(EnforceStation obj) {
		obj.setLawEnforceStationId(StringUtil.generateUUID());
		enforceStationMapper.insertSelective(obj);
		return obj.getLawEnforceStationId();
	}

	public int delete(String id) {
		return enforceStationMapper.deleteByPrimaryKey(id);
	}

	public int update(EnforceStation obj) {
		return enforceStationMapper.updateByPrimaryKey(obj);
	}

	public List<EnforceStation> selectAll(Map map) {
		return enforceStationMapper.selectAll(map);
	}

}
