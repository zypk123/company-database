package cy.its.syscfg.repository.traffic;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.StringUtil;
import cy.its.syscfg.domain.model.traffic.PolicePost;
import cy.its.syscfg.domain.repository.traffic.IPolicePostRepository;
import cy.its.syscfg.mybatis.client.PolicePostMapper;

@Service
public class PolicePostRepository implements IPolicePostRepository {

	@Autowired 
	PolicePostMapper policePostMapper;
	
	public PolicePost aggregateOfId(String id) throws Exception {	
		return policePostMapper.selectByPrimaryKey(id);
	}

	public String save(PolicePost obj) {
		obj.setPolicePostId(StringUtil.generateUUID());
		policePostMapper.insertSelective(obj);
		return obj.getPolicePostId();
	}

	public int delete(String id) {
		return policePostMapper.deleteByPrimaryKey(id);
	}

	public int update(PolicePost obj) {
		return policePostMapper.updateByPrimaryKey(obj);
	}
	public List<PolicePost> selectAll(Map map){
		return policePostMapper.selectAll(map);
	}

}
