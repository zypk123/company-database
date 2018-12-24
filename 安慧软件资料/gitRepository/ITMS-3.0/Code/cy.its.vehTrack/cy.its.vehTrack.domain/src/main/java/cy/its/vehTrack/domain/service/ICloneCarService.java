package cy.its.vehTrack.domain.service;

import java.util.Map;
public interface ICloneCarService {

	
	/**
	 * 套牌车分析查询
	 * @param query
	 * @return Map
	 */
	public  Map<String, Object> findCloneCarInfo(String query,String queryDB) throws Exception;
	
	/**
	 * 套牌车状态更新
	 * @param clone_vehicle_id
	 * @param clone_flag
	 */
	public void updateConfirmFlag(String clone_vehicle_id,String clone_flag,String confirm_desc) throws Exception;

}
