/**
 *name :无牌车车辆分析
 *author:wangyunqi
 *date:2016/03/14
 * 
 */
package cy.its.vehTrack.repository.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;

import cy.its.vehTrack.domain.repository.IUnlicensedCarsResponsitory;
import cy.its.vehTrack.repository.bigData.Config;
import cy.its.vehTrack.repository.bigData.util.RESTUtil;

@Repository
public class UnlicensedCarsResponsitory implements IUnlicensedCarsResponsitory {

	/**
	 * 无牌车车辆分析
	 */
	@Override
	public Map<String, Object> findUnlicensedCarsInfo(String query) throws Exception {
		String result = RESTUtil.load(Config.getProperties("null_plateNbr_car_service"), query);
		JSONObject obj = JSONObject.parseObject(result);
		Map<String, Object> map =new HashMap<String, Object>();
		map.put("result", obj);
		return map;
	}

}
