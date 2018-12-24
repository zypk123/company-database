package cy.its.vehTrack.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.vehTrack.mybatis.model.Pass;

public interface CloneCarMapper {
	
	public void updateConfirmFlag(Map map) throws Exception;
	
	public List findCloneCarInfor(Map map)throws Exception;
}
