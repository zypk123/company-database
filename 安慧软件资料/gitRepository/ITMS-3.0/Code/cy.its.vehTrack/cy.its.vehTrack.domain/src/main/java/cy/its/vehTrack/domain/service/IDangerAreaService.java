package cy.its.vehTrack.domain.service;

import com.alibaba.fastjson.JSONObject;

public interface IDangerAreaService {
	public JSONObject findDangerAreaCarList(String query) throws Exception;
}
