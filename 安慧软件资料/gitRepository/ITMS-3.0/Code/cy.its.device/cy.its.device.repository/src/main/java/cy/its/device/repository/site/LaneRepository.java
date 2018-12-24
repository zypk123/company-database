package cy.its.device.repository.site;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.StringUtil;
import cy.its.device.domain.model.site.Lane;
import cy.its.device.domain.repository.site.ILaneRepository;
import cy.its.device.mybatis.client.LaneMapper;


@Service
public class LaneRepository implements ILaneRepository {
	
	@Autowired
	LaneMapper laneMapper;
	
	@Override
	public Lane aggregateOfId(String id) throws Exception {
		return laneMapper.selectByPrimaryKey(id);
	}

	@Override
	public String save(Lane obj) {
		obj.setLaneId(StringUtil.generateUUID());
		laneMapper.insertSelective(obj);
		return obj.getLaneId();
	}

	@Override
	public int delete(String id) {
		return laneMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(Lane obj) {
		return laneMapper.updateByPrimaryKeySelective(obj);
	}

	@Override
	public List<Lane> findLaneBySectionId(String sectionId) {
		return laneMapper.findLaneBySectionId(sectionId);
	}

}
