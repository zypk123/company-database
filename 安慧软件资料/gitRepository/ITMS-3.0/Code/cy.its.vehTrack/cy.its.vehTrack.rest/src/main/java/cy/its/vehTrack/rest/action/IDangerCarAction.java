/**
 *name :危险驾驶车辆分析
 *author:wangyunqi
 *date:2016/03/14
 * 
 */
package cy.its.vehTrack.rest.action;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import cy.its.vehTrack.rest.dto.DangerCarDto;

public interface IDangerCarAction {
	
	
	/**
	 * 危险驾驶车辆TOPN
	 * @param dangerDto
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> findDangerCarTopN(DangerCarDto dangerDto) throws Exception;
	
	
	/**
	 * 危险驾驶车辆整体分析
	 * @param dangerDto
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> findDangerCarWholeAnylsis(DangerCarDto dangerDto) throws Exception;
	
	/**
	 * 危险驾驶车辆单项分析
	 * @param dangerDto
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> findDangerCarSimpleAnylsis(DangerCarDto dangerDto) throws Exception;
	
	/**
	 * 危险驾驶车辆联项分析
	 * @param dangerDto
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> findDangerCarMulitAnylsis(DangerCarDto dangerDto) throws Exception;


}
