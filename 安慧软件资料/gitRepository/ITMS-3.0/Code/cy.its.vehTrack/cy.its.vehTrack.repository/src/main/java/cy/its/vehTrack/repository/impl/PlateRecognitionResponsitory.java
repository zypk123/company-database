package cy.its.vehTrack.repository.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;

import cy.its.vehTrack.domain.repository.IPlateRecognitionResponsitory;
import cy.its.vehTrack.repository.bigData.Config;
import cy.its.vehTrack.repository.bigData.util.RESTUtil;

@Repository
public class PlateRecognitionResponsitory implements IPlateRecognitionResponsitory {

	@Override
	public Map<String, Object> findPlateRecogRate(String query) throws Exception {
		String result = RESTUtil.load(Config.getProperties("plate_recog_service"), query);
		return JsonToMap(result);
	}
	public static Map<String, Object> JsonToMap(String result){
		Map<String, Object> map =new HashMap<String, Object>();
		map.put("result", result);
		return map;
	}

}
