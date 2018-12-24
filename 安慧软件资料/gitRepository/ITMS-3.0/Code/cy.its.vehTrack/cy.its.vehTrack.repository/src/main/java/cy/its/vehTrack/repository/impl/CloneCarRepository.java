package cy.its.vehTrack.repository.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tools.ant.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cy.its.platform.common.utils.SqlHelper;
import cy.its.vehTrack.domain.model.CloneCarDB;
import cy.its.vehTrack.domain.repository.ICloneCarRespository;
import cy.its.vehTrack.mybatis.client.CloneCarMapper;
import cy.its.vehTrack.mybatis.model.CloneCar;
import cy.its.vehTrack.mybatis.util.ObjectToMap;
import cy.its.vehTrack.repository.bigData.Config;
import cy.its.vehTrack.repository.bigData.model.CloneCarCriteriaBD;
import cy.its.vehTrack.repository.bigData.util.RESTUtil;

@Repository
public class CloneCarRepository  implements ICloneCarRespository{
	
	@Autowired
	CloneCarMapper cloneMapper;

	@Override
	public Map<String, Object> findCloneCarInfo(String query,String queryDB) throws Exception {
		//if(Boolean.parseBoolean(Config.getProperties("use_big_data"))){
		/*if (false){
			return findCloneCarInfoFromBigData(query);
		}else {*/
		//从 oracle 中查询
			return findCloneCarInfoFormOracle(queryDB);	
		//}
	}
	
	public Map<String, Object> findCloneCarInfoFromBigData(String query) throws FileNotFoundException, IOException, Exception{
		
		String result = RESTUtil.load(Config.getProperties("clone_car_service"), query);
		JSONObject obj = JSONObject.parseObject(result);
		String rows = obj.getJSONObject("result").getString("rows");
		List<CloneCarDB> cloneCarInfo = JSONObject.parseArray(rows, CloneCarDB.class);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("error", "");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", obj.getJSONObject("result").getIntValue("total"));
		resultMap.put("rows", cloneCarInfo);
		returnMap.put("result", resultMap);
		return returnMap;
		}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<String, Object> findCloneCarInfoFormOracle(String query) throws Exception{
		CloneCarCriteriaBD cloneCar = JSON.parseObject(query, CloneCarCriteriaBD.class);
		Map map = ObjectToMap.beanToMap(cloneCar);
		map.put("clone_flag", strToArray(cloneCar.getClone_flag()));
		PageHelper.startPage(cloneCar.getPageNumber(), cloneCar.getPageSize(), true);
		//System.out.println(SqlHelper.getMapperSql(cloneMapper, "findCloneCarInfor", map));
		Page<CloneCar> page = (Page<CloneCar>) cloneMapper.findCloneCarInfor(map);
		cloneCar.setTotalCount((int) page.getTotal());
		List<CloneCar> cloneCarList = page.getResult();
		List<CloneCarDB> cloneCarInfo =new ArrayList<CloneCarDB>();
		for (CloneCar car:cloneCarList){
			cloneCarInfo.add(convertBean(car));
		}
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("error", "");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", page.getTotal());
		resultMap.put("rows", cloneCarInfo);
		returnMap.put("result", resultMap);
		return returnMap;
	}

	
	/**
	 * 套牌车状态更新
	 * @param clone_vehicle_id
	 * @param clone_flag
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void updateConfirmFlag(String clone_vehicle_id, String clone_flag,String confirm_desc) throws Exception {
		@SuppressWarnings("rawtypes")
		Map map =new HashMap();
		map.put("clone_vehicle_id", clone_vehicle_id);
		map.put("clone_flag", clone_flag);
		map.put("confirm_desc", confirm_desc);
		map.put("confirmor", "admin");
		map.put("confirm_time", new Date());
		//System.out.println(SqlHelper.getMapperSql(cloneMapper, "updateConfirmFlag", map));
		cloneMapper.updateConfirmFlag(map);
	}
	
	public String[] strToArray(String str){
		if (str !="" && str!=null){
			return str.split(",");
		}
		return null;
		
	}
	/**
	 * bean的转换
	 * @param bean
	 * @return
	 */
	public CloneCarDB convertBean(CloneCar bean){
		CloneCarDB carDB= new CloneCarDB();
		carDB.setClone_vehicle_id(bean.getCloneVehicleId());
		carDB.setClone_flag(bean.getCloneFlag());
		carDB.setConfirm_reason(bean.getConfirmReason());
		carDB.setConfirm_time(toStr(bean.getConfirmTime()));
		carDB.setCount_form(bean.getCountForm());
		carDB.setConfirmor(bean.getConfirmor());
		carDB.setDevice_nbr1(bean.getDeviceNbr1());
		carDB.setDevice_nbr2(bean.getDeviceNbr2());
		carDB.setImage_url1(bean.getImageUrl1());
		carDB.setImage_url2(bean.getImageUrl2());
		carDB.setPass_time1(toStr(bean.getPassTime1()));
		carDB.setPass_time2(toStr(bean.getPassTime2()));
		carDB.setPlate_color(bean.getPlateColor());
		carDB.setPlate_color1(bean.getPlateColor1());
		carDB.setPlate_color2(bean.getPlateColor2());
		carDB.setPlate_nbr(bean.getPlateNbr());
		carDB.setPlate_type(bean.getPlateType());
		carDB.setRegister_plate_color(bean.getRegisterPlateColor());
		carDB.setRegister_vehicle_brand(bean.getRegisterVehicleBrand());
		carDB.setRegister_vehicle_color(bean.getRegisterVehicleColor());
		carDB.setRegister_vehicle_type(bean.getRegisterVehicleType());
		carDB.setSite_code1(bean.getSiteCode1());
		carDB.setSite_code2(bean.getSiteCode2());
		carDB.setSnap_nbr1(bean.getSnapNbr1());
		carDB.setSnap_nbr2(bean.getSnapNbr2());
		carDB.setVehicle_brand(bean.getVehicleBrand());
		carDB.setVehicle_color(bean.getVehicleColor());
		carDB.setBetweenDistance(bean.getBetweenDistance());
		carDB.setConfirm_desc(bean.getConfirmDesc());
		return carDB;
		
	}
	public String toStr(Date date){
		if (date != null){
			return DateUtils.format(date, "yyyy-MM-dd HH:mm:ss");
		}
		return "";
		
	}
	
}
