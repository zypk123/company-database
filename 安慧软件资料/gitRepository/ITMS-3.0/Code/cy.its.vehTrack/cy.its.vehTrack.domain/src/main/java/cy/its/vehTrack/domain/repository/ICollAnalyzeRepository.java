package cy.its.vehTrack.domain.repository;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.alibaba.fastjson.JSONObject;

public interface ICollAnalyzeRepository {

	public JSONObject findCollAnalyResList(String query) throws FileNotFoundException, IOException, Exception;
}
