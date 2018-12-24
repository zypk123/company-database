package cy.its.syscfg.domain.service;

import java.util.List;
import java.util.Map;

import cy.its.syscfg.domain.model.traffic.PolicePost;

public interface IPolicePostService {
	
	PolicePost policePostOfId(String policePostId) throws Exception;
	
	String savePolicePost(PolicePost policePost);
	
	int deletePolicePostOfId(String policePostId);
	int updatePolice(PolicePost policePost);
	List<PolicePost> selectAll(Map map);
}
