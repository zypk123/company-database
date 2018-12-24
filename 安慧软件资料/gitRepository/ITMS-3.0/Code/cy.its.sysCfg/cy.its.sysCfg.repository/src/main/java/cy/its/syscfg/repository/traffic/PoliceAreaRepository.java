package cy.its.syscfg.repository.traffic;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.StringUtil;
import cy.its.syscfg.domain.model.traffic.PoliceArea;
import cy.its.syscfg.domain.repository.traffic.IPoliceAreaRepository;
import cy.its.syscfg.mybatis.client.PoliceAreaMapper;

@Service
public class PoliceAreaRepository implements IPoliceAreaRepository {
	@Autowired
	PoliceAreaMapper policeAreaMapper;
	public PoliceArea aggregateOfId(String id) throws Exception {
		return policeAreaMapper.selectByPrimaryKey(id);
	}

	public String save(PoliceArea obj) {
		obj.setPoliceAreaId(StringUtil.generateUUID());
		policeAreaMapper.insertSelective(obj);
		return obj.getPoliceAreaId();
	}

	public int delete(String id) {
		return policeAreaMapper.deleteByPrimaryKey(id);
	}

	public int update(PoliceArea obj) {
		return policeAreaMapper.updateByPrimaryKey(obj);
	}

	public List<PoliceArea> selectAll(Map map) {
		return policeAreaMapper.selectAll(map);
	}

}
