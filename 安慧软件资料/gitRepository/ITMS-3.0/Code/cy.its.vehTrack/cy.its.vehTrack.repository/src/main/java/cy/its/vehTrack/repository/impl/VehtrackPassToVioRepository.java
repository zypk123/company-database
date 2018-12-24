package cy.its.vehTrack.repository.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cy.its.platform.common.utils.SqlHelper;
import cy.its.vehTrack.domain.repository.IVehtrackPassToVioRepository;
import cy.its.vehTrack.mybatis.client.VehtrackPassToVioMapper;
import cy.its.vehTrack.mybatis.model.VehtrackPassToVio;

@Repository
public class VehtrackPassToVioRepository implements IVehtrackPassToVioRepository {
	@Autowired
	VehtrackPassToVioMapper mapper;

	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, Object> queryVehtrackPassToVios(Map map) throws Exception {
		PageHelper.startPage(Integer.parseInt(map.get("pageNumber").toString()),
				Integer.parseInt(map.get("pageSize").toString()), true);
		//System.out.println(SqlHelper.getMapperSql(mapper, "selectVioList", map));
		Page<VehtrackPassToVio> page = (Page<VehtrackPassToVio>) mapper.selectVioList(map);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("error", "");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", page.getTotal());
		resultMap.put("rows", JSONObject.toJSON(page.getResult()));
		returnMap.put("result", resultMap);
		return returnMap;
	}

	@Override
	public Map<String, Object> updateVehtrackPassToVio(@SuppressWarnings("rawtypes") Map map) throws Exception {
		// TODO Auto-generated method stub
		//System.out.println(SqlHelper.getMapperSql(mapper, "updateByExampleSelective", map));
		int flag = this.mapper.updateByExampleSelective(map);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", flag);
		return resultMap;
	}

}
