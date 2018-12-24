package cy.its.vehTrack.domain.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.alibaba.fastjson.JSONObject;

public interface ICollAnalyzeService {

	/**
	 * 
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws Exception
	 */
	public JSONObject findCollAnalyResList(String query) throws FileNotFoundException, IOException, Exception;
}
