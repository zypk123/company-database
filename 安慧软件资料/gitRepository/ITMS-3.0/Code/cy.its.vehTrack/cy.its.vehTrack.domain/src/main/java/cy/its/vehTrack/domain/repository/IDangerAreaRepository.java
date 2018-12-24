package cy.its.vehTrack.domain.repository;

import com.alibaba.fastjson.JSONObject;

public interface IDangerAreaRepository {
	
	public JSONObject findDangerAreaCarList(String query) throws Exception;

}
