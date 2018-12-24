package cy.its.syscfg.domain.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.syscfg.domain.model.traffic.PolicePost;
import cy.its.syscfg.domain.repository.traffic.IPolicePostRepository;
import cy.its.syscfg.domain.service.IPolicePostService;

@Service
public class PolicePostService implements IPolicePostService {

	@Autowired
	IPolicePostRepository policePostRepository;
	
	public String savePolicePost(PolicePost policePost) {
		return policePostRepository.save(policePost);
	}


	public PolicePost policePostOfId(String policePostId) throws Exception {
		return policePostRepository.aggregateOfId(policePostId);
	}

	public int deletePolicePostOfId(String policePostId) {
		return policePostRepository.delete(policePostId);
	}

	public int updatePolice(PolicePost policePost) {
		return policePostRepository.update(policePost);
	}
	public List<PolicePost> selectAll(Map map){
		return policePostRepository.selectAll(map);
	}
}
