package cy.its.vehTrack.repository.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cy.its.platform.common.utils.SqlHelper;
import cy.its.vehTrack.domain.criteria.PassCriteria;
import cy.its.vehTrack.domain.model.PassInfo;
import cy.its.vehTrack.domain.repository.IPassRepository;
import cy.its.vehTrack.mybatis.client.PassMapper;
import cy.its.vehTrack.mybatis.model.Pass;
import cy.its.vehTrack.mybatis.util.ObjectToMap;
import cy.its.vehTrack.repository.bigData.Config;
import cy.its.vehTrack.repository.bigData.model.PassCriteriaBD;
import cy.its.vehTrack.repository.bigData.util.RESTUtil;

@Repository
public class PassRepository implements IPassRepository {
	
	@Autowired
	private PassMapper passMapper;
	
	@Override 
	public List<PassInfo> findPassInfo(PassCriteria passCriteria) throws Exception {
		if(!Boolean.parseBoolean(Config.getProperties("use_big_data"))){
			return findPassInfoInDatabase(passCriteria);
		}else{
			return findPassInfoInBigData(passCriteria); 
		}
	}
	
	/**
	 * 调用大数据服务，查询过车记录
	 * @param passCriteria
	 * @return
	 * @throws Exception 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	private List<PassInfo> findPassInfoInBigData(PassCriteria passCriteria) throws FileNotFoundException, IOException, Exception{
		PassCriteriaBD criteria = new PassCriteriaBD(passCriteria);
		String params = criteria.toString();
		String result = RESTUtil.load(Config.getProperties("pass_service"), params);
		JSONObject obj = JSONObject.parseObject(result);
		passCriteria.setTotal(obj.getJSONObject("result").getIntValue("total"));
		JSONArray array = obj.getJSONObject("result").getJSONArray("rows");
		List<PassInfo> passList = new ArrayList<PassInfo>();
		for(int index=0; index<array.size(); index++){
			passList.add(new PassInfo(array.getJSONObject(index)));
		}
		return passList;
	}
	
	/**
	 * 查询数据库过车记录
	 * @param passCriteria
	 * @return
	 */
	private List<PassInfo> findPassInfoInDatabase(PassCriteria passCriteria){
		Map map = ObjectToMap.beanToMap(passCriteria);
		String plateNbr = (String) map.get("plateNbr");
		if("*".equals(plateNbr)){
			map.put("plateNbr", "");
		}else if(plateNbr.contains("*")){
			map.put("plateNbr", plateNbr.replace("*", ""));
		}
		//System.out.println(SqlHelper.getMapperSql(passMapper, "findPassInfo", map));
		PageHelper.startPage(passCriteria.getPageNum(), passCriteria.getPageSize(),true);
		Page<Pass> page =  (Page<Pass>) passMapper.findPassInfo(map);
		passCriteria.setTotal((int) page.getTotal());
		List<PassInfo> passInfo = new ArrayList<PassInfo>();
		for(Pass pass : page.getResult()){
			passInfo.add(pass.convertToModel());
		}
		return passInfo; 
	}
}
