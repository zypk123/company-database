package cy.its.vehTrack.repository.impl;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;

import cy.its.vehTrack.domain.repository.ICollAnalyzeRepository;
import cy.its.vehTrack.repository.bigData.Config;
import cy.its.vehTrack.repository.bigData.util.RESTUtil;

@Repository
public class CollAnalyzeRepository implements ICollAnalyzeRepository {

	/**
	 * 
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws Exception
	 */
	public JSONObject findCollAnalyResList(String query) throws FileNotFoundException, IOException, Exception {
		String returnStr = RESTUtil.load(Config.getProperties("area_collision_service"), query);
		JSONObject obj = JSONObject.parseObject(returnStr);
		return obj;

	}

}
