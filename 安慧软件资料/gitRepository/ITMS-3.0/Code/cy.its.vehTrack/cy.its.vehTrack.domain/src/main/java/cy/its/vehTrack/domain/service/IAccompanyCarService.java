package cy.its.vehTrack.domain.service;

import com.alibaba.fastjson.JSONObject;

public interface IAccompanyCarService {
	public JSONObject findAccompanyCarList(String query) throws Exception;

}
